function StudentTable() {
	this.UNIT_HEIGHT = 25;
	this.UNIT_HEIGHT_SPACE = 8;
	this.UNIT_WIDTH = 76;
	this.UNIT_WIDTH_SPACE = 26;
	this.coursesData = [];
	this.flag = 0;
	this.isOpenFreeTime = true;
	this.freeTime = [
		{"classBegin": 11, "classOver": 13, "week": 1, "isFree": true},
		{"classBegin": 11, "classOver": 13, "week": 2, "isFree": true},
		{"classBegin": 11, "classOver": 13, "week": 3, "isFree": true},
		{"classBegin": 11, "classOver": 13, "week": 4, "isFree": true},
		{"classBegin": 11, "classOver": 13, "week": 5, "isFree": true},
    ];
	this.backgroundColorList = [
		"#68bbe3",
		"#f75f46",
		"#8cbf26",
		"#00aba9",
		"#fad81d",
		"#064df0",
		"#1fe99f",
		"#fda7a7",
		"#c76ae2",
		"#945a3b",
		"#b28850",
		"#59c3ee",
		"#a9ee59",
		"#f19149"
	];
}

StudentTable.prototype = {
	getCourseTop: function (classBegin) {
		var unit_top = this.UNIT_HEIGHT + this.UNIT_HEIGHT_SPACE;
		if(classBegin <= 5) {
			return (classBegin - 1) * unit_top + "px";
		} else if (classBegin <= 10) {
			return classBegin * unit_top + "px";
		} else {
			return (classBegin + 1) * unit_top + "px";
		}
	},
	getCourseLeft: function (week) {
		return (week - 1) * (this.UNIT_WIDTH + this.UNIT_WIDTH_SPACE) + "px";
	},
	getCourseHeight: function (classBegin, classOver) {
		return this.UNIT_HEIGHT + (classOver - classBegin) * (this.UNIT_HEIGHT + this.UNIT_HEIGHT_SPACE) + "px";
	},
	createCourse: function () {
		for(var i = 0; i < this.coursesData.length; i ++) {
			/*过滤掉时间异常的课*/
			if(!this.coursesData[i].classBegin) continue;

			if(this.isOpenFreeTime) {	
				/*标记非空闲时间*/
				for(var j = 0; j < this.freeTime.length; j ++) {
					if(
						(this.coursesData[i].weekday == this.freeTime[j].week && this.coursesData[i].weekFrom < 13) &&
						!(this.coursesData[i].classOver < this.freeTime[j].classBegin) && 
						!(this.freeTime[j].classOver < this.coursesData[i].classBegin)
					) {
						this.freeTime[j].isFree = false;
					}
				}
			}

			/*创建节点*/
			var course = document.createElement("div");
			if(this.coursesData[i].id != undefined) {
				course.innerHTML += '<div class="course-hover"><a href="/course/' + this.coursesData[i].id + '/" target="_blank">点击进入<br>课程信息页</a></div>';
			}
			course.innerHTML += "<div class='course-text'>" + this.coursesData[i].courseName + "<br>" + this.coursesData[i].location + "</div>";
			course.className = "course";
			course.style.top = this.getCourseTop(this.coursesData[i].classBegin);
			course.style.left = this.getCourseLeft(this.coursesData[i].weekday);
			course.style.height = this.getCourseHeight(this.coursesData[i].classBegin, this.coursesData[i].classOver);
			course.style.backgroundColor = this.backgroundColorList[(i + 1) % this.backgroundColorList.length];
			course.setAttribute("data-index", i);

			/*插入节点、绑定事件*/
			$(".courses").append(course);
			$(".course[data-index=" + i + "]").mouseover(function () {
				$(this).addClass("betop");
				var index = this.getAttribute("data-index");
				for(var i = studentTable.coursesData[index].classBegin; i <= studentTable.coursesData[index].classOver; i ++) {
					$(".ct-time li[data-class=" + i + "]").addClass("active");
				}
				$($(".ct-week li")[studentTable.coursesData[index].weekday - 1]).addClass("active");
			});
			$(".course").mouseout(function (e) {
				$(this).removeClass("betop");
				$(".ct-time li").removeClass("active");
				$(".ct-week li").removeClass("active");
				e.stopPropagation();
				return false;
			});
		}
		if(this.isOpenFreeTime) {
			this.createFreetime();	
		}
	},
	createFreetime: function () {
		for(var i = 0; i < this.freeTime.length; i ++) {
			if(this.freeTime[i].isFree) {
				var freetime = document.createElement("a");
				freetime.innerHTML = '<div class="freetime-wrap"><img src="/static/img/blank.gif" alt="点击查看此时段选修课"><br>点击查看此时段选修课</div>';
				freetime.className = "freetime";
				freetime.href = "/list/?week=" + this.freeTime[i].week;
				freetime.target = "_blank";
				freetime.style.top = this.getCourseTop(this.freeTime[i].classBegin);
				freetime.style.left = this.getCourseLeft(this.freeTime[i].week);
				$(".courses").append(freetime)
			}
		}
	}
}

$(document).ready(function() {
	window.studentTable = new StudentTable();
	$.ajax({
		type: "get",
		dataType: "json",
		url: "/student/get_course_set/",
		success: function(data) {
			studentTable.flag ++;
			if(data == 'NeedLogIn') {
				return false;
				console.log("失败了呢--2");
			}
			/* 格式化非公选课的数据 */
			for(var i = 0 ;i < data.length; i ++) {
				for(var j = 0; j < data[i].lessons.length; j ++) {
					var course = {};
					switch(data[i].lessons[j].weekday) {
						case "一": course.weekday = 1;break;
						case "二": course.weekday = 2;break;
						case "三": course.weekday = 3;break;
						case "四": course.weekday = 4;break;
						case "五": course.weekday = 5;break;
						case "六": course.weekday = 6;break;
						case "日": course.weekday = 7;break;
					}
					course.weekFrom = data[i].lessons[j].weekFrom;
					course.classBegin = data[i].lessons[j].classBegin;
					course.classOver = data[i].lessons[j].classOver;
					course.courseName = data[i].name;
					course.location = data[i].lessons[j].location;
					studentTable.coursesData.push(course);
				}
			}
			if(studentTable.flag == 2) {
				studentTable.createCourse();
			}
		},
		error: function() {
			studentTable.flag ++;
			if(studentTable.flag == 2) {
				studentTable.createCourse();
			}
		    console.log("失败了呢--1");
		}
	});

	$.ajax({
		type: "get",
		dataType: "json",
		url: "/student/get_course_set_2/",
		success: function(data) {
			studentTable.flag ++;
			if(data == 'NeedLogIn') {
				return false;
				console.log("失败了呢--3");
			}
			/* 格式化公选课的数据 */
			for(var i = 0 ;i < data.length; i ++) {
				var course = {};
				course.id = data[i].id;
				course.weekday = data[i].week;
				course.weekFrom = data[i].week_from;
				course.classBegin = data[i].class_begin;
				course.classOver = data[i].class_over;
				course.courseName = data[i].name;
				course.location = data[i].location;
				studentTable.coursesData.push(course);
			}
			if(studentTable.flag == 2) {
				studentTable.createCourse();
			}
		},
		error: function() {
			studentTable.flag ++;
			if(studentTable.flag == 2) {
				studentTable.createCourse();
			}
		    console.log("失败了呢--4");
		}
	})
});