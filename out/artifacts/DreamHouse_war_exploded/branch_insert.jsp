<%@ page import="dream.entity.Branch" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="dream.factory.DAOFactory" %>
<%@ page import="dream.daoInterface.IBranchDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2015/6/15
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert Branch Data</title>
  <link rel="stylesheet" type="text/css" href="css/order.css">
  <style>
    @import url("http://www.w3cplus.com/demo/css3/base.css");
    /*任务一：引入本地字体文件*/
    @font-face {
      font-family: 'sansationregular';
      src: url('http://www.w3cplus.com/demo/css3/3DAnimationDropdownMenu/sansation_regular-webfont.eot');
      src: url('http://www.w3cplus.com/demo/css3/3DAnimationDropdownMenu/sansation_regular-webfont.eot?#iefix') format('http://www.w3cplus.com/demo/css3/3DAnimationDropdownMenu/embedded-opentype'),
      url('http://www.w3cplus.com/demo/css3/3DAnimationDropdownMenu/sansation_regular-webfont.woff') format('woff'),
      url('http://www.w3cplus.com/demo/css3/3DAnimationDropdownMenu/sansation_regular-webfont.ttf') format('truetype'),
      url('http://www.w3cplus.com/demo/css3/3DAnimationDropdownMenu/sansation_regular-webfont.svg#sansationregular') format('svg');
      font-weight: normal;
      font-style: normal;
    }
    body {
      width: 100%;
      height: 100%;
      background-image: url(images/loginbg.jpg);
      background-size: cover;
      margin: 0;
      padding: 0;
    }

    /* basic menu styles */
    .nav-menu {
      display: block;
      /* background: #74adaa;*/
      width:950px;
      margin: 50px auto 150px;
    }
    .nav-menu > li {
      display: inline;
      float:left;
      border-right:1px solid #94c0be;
    }
    .nav-menu > li:last-child {
      border-right: none;
    }
    .nav-menu li a {
      color: #fff;
      display: block;
      text-decoration: none;
      /*调用本地字体*/
      font-family:sansationregular;
      -webkit-font-smoothing: antialiased;
      -moz-font-smoothing: antialiased;
      font-smoothing: antialiased;
      text-transform: capitalize;
      overflow: visible;
      line-height: 20px;
      font-size: 20px;
      padding: 15px 30px 15px 31px;
    }


    .three-d {
      /* 任务三、设置3D舞台布景 */
      -webkit-perspective: 200px;
      -moz-perspective: 200px;
      -ms-perspective: 200px;
      -o-perspective: 200px;
      perspective: 200px;
      /*任务四、设置3D舞台布景过渡效果*/
      -webkit-transition: all .07s linear;
      -moz-transition: all .07s linear;
      -ms-transition: all .07s linear;
      -o-transition: all .07s linear;
      transition:all .7s linear;
      position: relative;
    }

    .three-d:not(.active):hover {
      cursor: pointer;
    }

    /*任务五、给不是当前状态的3D舞台的悬浮与聚焦状态设置变形效果*/
    .three-d:not(.active):hover .three-d-box,
    .three-d:not(.active):focus .three-d-box {
      -wekbit-transform: translateZ(-25px) rotateX(90deg);
      -moz-transform: translateZ(-25px) rotateX(90deg);
      -o-transform: translateZ(-25px) rotateX(90deg);
      -ms-transform: translateZ(-25px) rotateX(90deg);
      transform: translateZ(-25px) rotateX(90deg);
      /*    -webkit-transform:rotateX(90deg) translatez(-25px);*/
      /*   -webkit-transform:rotatex(90deg);*/
    }

    .three-d-box {
      /*任务六、给3D舞台中“.three-d-box”设置过渡与变形效果*/
      -webkit-transition: all .3s ease-out;
      -moz-transition: all .3s ease-out;
      -ms-transition: all .3s ease-out;
      -o-transition: all .3s ease-out;
      transition: all .3s ease-out;
      -webkit-transform: translatez(-25px);
      -moz-transform: translatez(-25px);
      -ms-transform: translatez(-25px);
      -o-transform: translatez(-25px);
      transform: translatez(-25px);
      -webkit-transform-style: preserve-3d;
      -moz-transform-style: preserve-3d;
      -ms-transform-style: preserve-3d;

      -o-transform-style: preserve-3d;
      transform-style: preserve-3d;
      -webkit-pointer-events: none;
      -moz-pointer-events: none;
      -ms-pointer-events: none;
      -o-pointer-events: none;
      pointer-events: none;
      position: absolute;
      top: 0;
      left: 0;
      display: block;
      width: 100%;
      height: 100%;
    }

    /*任务七、给导航设置3D前，与3D后变形效果*/
    .front {
      -webkit-transform: rotatex(0deg) translatez(25px);
      -moz-transform: rotatex(0deg) translatez(25px);
      -ms-transform: rotatex(0deg) translatez(25px);
      -o-transform: rotatex(0deg) translatez(25px);
      transform: rotatex(0deg) translatez(25px);
    }

    .back {
      -webkit-transform: rotatex(-90deg) translatez(25px);
      -moz-transform: rotatex(-90deg) translatez(25px);
      -ms-transform: rotatex(-90deg) translatez(25px);
      -o-transform: rotatex(-90deg) translatez(25px);
      transform: rotatex(-90deg) translatez(25px);
      color: #FFE7C4;
    }

    .front, .back {
      /*          border:1px solid red;*/
      display: block;
      width: 100%;
      height: 100%;
      position: absolute;
      top: 0;
      left: 0;
      background: #74adaa;
      padding: 15px 30px 15px 31px;
      color: white;
      -webkit-pointer-events: none;
      -moz-pointer-events: none;
      -ms-pointer-events: none;
      -o-pointer-events: none;
      pointer-events: none;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
    }
    /*任务八、设置导航当前状态与悬浮状态下的背景效果*/
    .nav-menu li .active .front,
    .nav-menu li .active .back,
    .nav-menu li a:hover .front,
    .nav-menu li a:hover .back {
      background-color: #51938f;
      -webkit-background-size: 5px 5px;
      background-size: 5px 5px;
      background-position: 0 0, 30px 30px;
      background-image: -webkit-linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480), linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480);
      background-image: -moz-linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480), linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480);
      background-image: -ms-linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480), linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480);
      background-image: -o-linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480), linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480);
      background-image: linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480), linear-gradient(45deg, #478480 25%, transparent 25%, transparent 75%, #478480 75%, #478480);
    }
    .nav-menu ul {
      position: absolute;
      text-align: left;
      line-height: 40px;
      font-size: 14px;
      width: 200px;
      -webkit-transition: all 0.3s ease-in;
      -moz-transition: all 0.3s ease-in;
      -ms-transition: all 0.3s ease-in;
      -o-transition: all 0.3s ease-in;
      transition: all 0.3s ease-in;
      -webkit-transform-origin: 0px 0px;
      -moz-transform-origin: 0px 0px;
      -ms-transform-origin: 0px 0px;
      -o-transform-origin: 0px 0px;
      transform-origin: 0px 0px;
      -webkit-transform: rotateX(-90deg);
      -moz-transform: rotateX(-90deg);
      -ms-transform: rotateX(-90deg);
      -o-transform: rotateX(-90deg);
      transform: rotateX(-90deg);
      -webkit-backface-visibility: hidden;
      -moz-backface-visibility: hidden;
      -ms-backface-visibility: hidden;
      -o-backface-visibility: hidden;
      backface-visibility: hidden;
    }
    /*任务九、显示下拉导航菜单，并其设置一个变形效果*/
    .nav-menu > li:hover ul {
      /*          display: block;*/
      -webkit-transform: rotateX(0deg);
      -moz-transform: rotateX(0deg);
      -ms-transform: rotateX(0deg);
      -o-transform: rotateX(0deg);
      transform: rotateX(0deg);
    }
  </style>
</head>
<body>
<div id="nav">
  <ul class="nav-menu clearfix unstyled">

    <li><a href="<s:url action="LogoutAction"/>" class="three-d active">
      Logout
      <span class="three-d-box"><span class="front">Logout</span><span class="back">Logout</span></span>
    </a></li>

    <li><a href="branch.jsp" class="three-d">
      Check all
      <span class="three-d-box"><span class="front">Check all</span><span class="back">Check all</span></span></a>
      <ul class="clearfix unstyled drop-menu">
        <li><a href="branch.jsp" class="three-d">
          Branch
          <span class="three-d-box"><span class="front">Branch</span><span class="back">Branch</span></span>
        </a></li>
        <li><a href="client.jsp" class="three-d">
          Client
          <span class="three-d-box"><span class="front">Client</span><span class="back">Client</span></span>
        </a></li>
        <li><a href="staff.jsp" class="three-d">
          Staff
          <span class="three-d-box"><span class="front">Staff</span><span class="back">Staff</span></span>
        </a></li>
        <li><a href="property.jsp" class="three-d">
          Property
          <span class="three-d-box"><span class="front">Property</span><span class="back">Property</span></span>
        </a></li>
        <li><a href="owner.jsp" class="three-d">
          Owner
          <span class="three-d-box"><span class="front">Owner</span><span class="back">Owner</span></span>
        </a></li>
        <li><a href="viewing.jsp" class="three-d">
          Viewing
          <span class="three-d-box"><span class="front">Viewing</span><span class="back">Viewing</span></span>
        </a></li>
        <li><a href="lease.jsp" class="three-d">
          Lease
          <span class="three-d-box"><span class="front">Lease</span><span class="back">Lease</span></span>
        </a></li>
        <li><a href="newspaper.jsp" class="three-d">
          Newspaper
          <span class="three-d-box"><span class="front">Newspaper</span><span class="back">Newspaper</span></span>
        </a></li>
        <li><a href="ad.jsp" class="three-d">
          Ad
          <span class="three-d-box"><span class="front">Ad</span><span class="back">Ad</span></span>
        </a></li>
      </ul>
    </li>

    <li><a href="branch_update.jsp" class="three-d">
      Upadate
      <span class="three-d-box"><span class="front">Upadate</span><span class="back">Upadate</span></span>
    </a>
      <ul class="clearfix unstyled drop-menu">
        <li><a href="branch_update.jsp" class="three-d">
          Branch
          <span class="three-d-box"><span class="front">Branch</span><span class="back">Branch</span></span>
        </a></li>
        <li><a href="client_update.jsp" class="three-d">
          Client
          <span class="three-d-box"><span class="front">Client</span><span class="back">Client</span></span>
        </a></li>
        <li><a href="staff_update.jsp" class="three-d">
          Staff
          <span class="three-d-box"><span class="front">Staff</span><span class="back">Staff</span></span>
        </a></li>
        <li><a href="property_update.jsp" class="three-d">
          Property
          <span class="three-d-box"><span class="front">Property</span><span class="back">Property</span></span>
        </a></li>
        <li><a href="owner_update.jsp" class="three-d">
          Owner
          <span class="three-d-box"><span class="front">Owner</span><span class="back">Owner</span></span>
        </a></li>
        <li><a href="viewing_update.jsp" class="three-d">
          Viewing
          <span class="three-d-box"><span class="front">Viewing</span><span class="back">Viewing</span></span>
        </a></li>
        <li><a href="lease_update.jsp" class="three-d">
          Lease
          <span class="three-d-box"><span class="front">Lease</span><span class="back">Lease</span></span>
        </a></li>
        <li><a href="newspaper_update.jsp" class="three-d">
          Newspaper
          <span class="three-d-box"><span class="front">Newspaper</span><span class="back">Newspaper</span></span>
        </a></li>
        <li><a href="ad_update.jsp" class="three-d">
          Ad
          <span class="three-d-box"><span class="front">Ad</span><span class="back">Ad</span></span>
        </a></li>
      </ul>
    </li>

    <li><a href="branch_insert.jsp" class="three-d">
      Insert
      <span class="three-d-box"><span class="front">Insert</span><span class="back">Insert</span></span></a>
      <ul class="clearfix unstyled drop-menu">
        <li><a href="branch_insert.jsp" class="three-d">
          Branch
          <span class="three-d-box"><span class="front">Branch</span><span class="back">Branch</span></span>
        </a></li>
        <li><a href="client_insert.jsp" class="three-d">
          Client
          <span class="three-d-box"><span class="front">Client</span><span class="back">Client</span></span>
        </a></li>
        <li><a href="staff_insert.jsp" class="three-d">
          Staff
          <span class="three-d-box"><span class="front">Staff</span><span class="back">Staff</span></span>
        </a></li>
        <li><a href="property_insert.jsp" class="three-d">
          Property
          <span class="three-d-box"><span class="front">Property</span><span class="back">Property</span></span>
        </a></li>
        <li><a href="owner_insert.jsp" class="three-d">
          Owner
          <span class="three-d-box"><span class="front">Owner</span><span class="back">Owner</span></span>
        </a></li>
        <li><a href="viewing_insert.jsp" class="three-d">
          Viewing
          <span class="three-d-box"><span class="front">Viewing</span><span class="back">Viewing</span></span>
        </a></li>
        <li><a href="lease_insert.jsp" class="three-d">
          Lease
          <span class="three-d-box"><span class="front">Lease</span><span class="back">Lease</span></span>
        </a></li>
        <li><a href="newspaper_insert.jsp" class="three-d">
          Newspaper
          <span class="three-d-box"><span class="front">Newspaper</span><span class="back">Newspaper</span></span>
        </a></li>
        <li><a href="ad_insert.jsp" class="three-d">
          Ad
          <span class="three-d-box"><span class="front">Ad</span><span class="back">Ad</span></span>
        </a></li>
      </ul>
    </li>

    <li><a href="branch_delete.jsp" class="three-d">
      Delete
      <span class="three-d-box"><span class="front">Delete</span><span class="back">Delete</span></span></a>
      <ul class="clearfix unstyled drop-menu">
        <li><a href="branch_delete.jsp" class="three-d">
          Branch
          <span class="three-d-box"><span class="front">Branch</span><span class="back">Branch</span></span>
        </a></li>
        <li><a href="client_delete.jsp" class="three-d">
          Client
          <span class="three-d-box"><span class="front">Client</span><span class="back">Client</span></span>
        </a></li>
        <li><a href="staff_delete.jsp" class="three-d">
          Staff
          <span class="three-d-box"><span class="front">Staff</span><span class="back">Staff</span></span>
        </a></li>
        <li><a href="property_delete.jsp" class="three-d">
          Property
          <span class="three-d-box"><span class="front">Property</span><span class="back">Property</span></span>
        </a></li>
        <li><a href="owner_delete.jsp" class="three-d">
          Owner
          <span class="three-d-box"><span class="front">Owner</span><span class="back">Owner</span></span>
        </a></li>
        <li><a href="viewing_delete.jsp" class="three-d">
          Viewing
          <span class="three-d-box"><span class="front">Viewing</span><span class="back">Viewing</span></span>
        </a></li>
        <li><a href="lease_delete.jsp" class="three-d">
          Lease
          <span class="three-d-box"><span class="front">Lease</span><span class="back">Lease</span></span>
        </a></li>
        <li><a href="newspaper_delete.jsp" class="three-d">
          Newspaper
          <span class="three-d-box"><span class="front">Newspaper</span><span class="back">Newspaper</span></span>
        </a></li>
        <li><a href="ad_delete.jsp" class="three-d">
          Ad
          <span class="three-d-box"><span class="front">Ad</span><span class="back">Ad</span></span>
        </a></li>
      </ul>
    </li>

    <li><a href="branch_query.jsp" class="three-d">
      Query
      <span class="three-d-box"><span class="front">Query</span><span class="back">Query</span></span></a>
    </li>

  </ul>
</div>

<div class="bar">
  <div class="bar-title bar-title-18"><h2>Insert Branch Data</h2></div>
  <div class="coursetable">
      <ul class="separator-class">

      </ul>
    </div>
    <div class="courses">
      <div class="separator">
        <s:form action="insertBranchAction" method="post">
          <ul class="separator-class">

            <li><s:textfield name="branch.branchNo" tooltip="Branch NO"
                             class="tf" placeholder="branch No"/></li>
            <li><s:textfield name="branch.street" tooltip="street"
                             class="tf" placeholder="street" /></li>
            <li><s:textfield name="branch.city" tooltip="city"
                             class="tf" placeholder="city" /></li>
            <li><s:textfield name="branch.postcode" tooltip="postcode"
                             class="tf"  placeholder="postcode" /></li>
            <li><s:textfield name="branch.teleNo" tooltip="telephone"
                             class="tf"  placeholder="teleNo" /></li>
            <li><s:textfield name="branch.managerNo" tooltip="managerNo"
                             class="tf"  placeholder="managerNo" /></li>
            <li><s:submit id="submit" class="tf" value="insert" /> </li>
          </ul>
        </s:form>


      </div>
    </div>
  </div>
</div>

</body>
</html>