<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<meta name="format-detection" content="telephone=no"/>
	<meta name="apple-mobile-app-status-bar-style" content="black" />
	<meta name="apple-touch-fullscreen" content="YES" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0, maximum-scale=1.0" />
	<title>登录/注册页面嘤嘤嘤</title>
	<link rel="stylesheet" href="static/css/login.css">
	<link rel="stylesheet" href="static/css/mdtext.css">
	<link rel="stylesheet" href="static/css/mdbtn.css">
	<script src="static/js/input.js"></script>
	<script src="static/js/mdbtno.js"></script>
	<script src="static/js/musicController.js"></script>
	<script src="static/js/login.js"></script>
</head>
<body data-page="signup">
	<div id="loginwrap" class="mainwrap">
		<div id="login">
			<div id="loginmusic">
				<div class="mdbtn" id="musiccontroller"><img src="static/img/turn_off.png"alt=""></div>
				<audio id="musicaudio"  controls="controls" preload="none"></audio>
			</div>
			<div id="logincenter" class="maincenter">		
				<div id="loginlogo">
					<img src="static/img/logo2.png"alt="">
				</div>
				<form action="" method="post" id="loginform" class="mainform" autocomplete="off"  spellcheck="false">
					<div class="inputtext">
						<input type="text" name="username" id="username" tabindex="1">
						<label for="username">用户名</label>
						<span class="hint">请输入用户名</span>
					</div>
					<div class="inputtext">
						<input type="password" name="password" id="password" tabindex="2">
						<label for="password">密码</label>
						<span class="hint">密码错误</span>
					</div>
					<div id="loginbtn" class="mdbtn submitbutton">登录</div>
					<!-- 这里加登录跳转事件在login.js的105行 -->
				</form>
				<div id="newaccount">
					<a href="###" tabindex="-1">新用户？注册</a>
				</div>
			</div>
		</div>
	</div>
	<div id="signupwrap" class="mainwrap">
		<div id="signup">
			<div id="signuplogo">
				<img src="static/img/logo2.png"alt="">
			</div>
			<div id="signupcenter" class="maincenter">
				<div id="signuptitle">用户注册</div>
				<form action="" method="post" id="signupform" class="mainform" autocomplete="off" >
					<div class="inputtext">
						<input type="text" name="newusername" id="newusername" tabindex="4">
						<label for="newusername">用户名 *</label>
						<span class="hint">请输入用户名</span>
					</div>
					<div class="inputtext">
						<input type="password" name="newpassword" id="newpassword" tabindex="5">
						<label for="newpassword">密码 *</label>
						<span class="hint">请输入密码</span>
					</div>
					<div class="inputtext">
						<input type="password" name="newpassword2" id="newpassword2" tabindex="6">
						<label for="newpassword2">确认密码 *</label>
						<span class="hint">两次密码不一致</span>
					</div>
					<div class="inputtext">
						<input type="text" name="newnickname" id="newnickname" tabindex="7">
						<label for="newnickname">昵称 *</label>
						<span class="hint">请输入昵称</span>
					</div>
					<div class="inputradio">
						<label>
							<input type="radio" name="sex" id="sex-male" checked="checked" tabindex="8">
							<span>男</span>
						</label>
						<label>
							<input type="radio" name="sex" id="sex-female" tabindex="9">
							<span>女</span>
						</label>
					</div>
					<div id="signupbtn" class="mdbtn submitbutton">注册</div>
					<!-- 这里加登录跳转事件在login.js的156行 -->
				</form>
				<div id="hasaccount">
					<a href="###" tabindex="-1">已有账号？登录</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>