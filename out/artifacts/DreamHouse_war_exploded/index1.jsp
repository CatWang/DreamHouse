<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2015/6/14
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
  <title>Dream House</title>
  <link rel="stylesheet" type="text/css" href="css/home.css">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/supersized.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script type="text/javascript" src="js/jquery_1.7.2.js"></script>
  <script type="text/javascript" src="js/jQueryRotateCompressed.2.2.js"></script>

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body scroll=no>
<div id="cont">
  <div class="stage" id="stage1">
    <div class="stage_box">
    </div>
    <a class="index_link btn_down" index="1">
      <div class='sign' id='sign1_1'></div>
      <div class='sign' id='sign2_1'></div>
      <div class='sign' id='sign3_1'_></div>
    </a>


  </div>
  <div class="stage" id="stage2" name ="refresh">
    <div class="page-container">
      <h1>Sign Up</h1>
      <s:form action="LoginAciton" method="POST">
        <s:textfield name="admin.user_id" tooltip="User ID" cssClass="username" placeholder="学号"/>
        <s:password name="admin.pwd" tooltip="password" cssClass="password" placeholder="密码"/>
        <s:submit id="submit" value="Login" />
      </s:form>
    </div>

  </div>

</div>
<script type="text/javascript" src="js/js.js"></script>
</body>
</html>