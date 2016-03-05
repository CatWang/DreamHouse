/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 50623
Source Host           : 127.0.0.1:3306
Source Database       : helphanddb

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2015-05-30 22:16:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `course_id` varchar(20) NOT NULL,
  `course_type` varchar(45) DEFAULT NULL,
  `course_title` varchar(45) DEFAULT NULL,
  `teacher` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `credit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `stu_id` varchar(20) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `hour` varchar(45) DEFAULT NULL,
  `time` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`stu_id`,`course_id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course_info` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu_id` FOREIGN KEY (`stu_id`) REFERENCES `student_info` (`stu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_grade
-- ----------------------------
DROP TABLE IF EXISTS `student_grade`;
CREATE TABLE `student_grade` (
  `stu_id` varchar(20) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `semester` varchar(45) DEFAULT NULL,
  `score` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`stu_id`,`course_id`,`year`),
  KEY `course-id_idx` (`course_id`),
  CONSTRAINT `course-id` FOREIGN KEY (`course_id`) REFERENCES `course_info` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stu-id` FOREIGN KEY (`stu_id`) REFERENCES `student_info` (`stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `stu_id` varchar(20) NOT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
