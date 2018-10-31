<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>优品风控模型-首页</title>
<%@ include file="../../../common/common.jsp"%>
<style>
  .homepage-signin {
        display: block;
        position: fixed;
        top: -50%;
        left: -50%;
        width: 200%;
        height: 200%;
        z-index: -1;
    }
  .homepage-signin img {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        margin: auto;
        min-width: 50%;
        min-height: 50%;
    }
    .homepage-img{
        margin:0 auto;
        text-align:center;
        vertical-align:middle;
        margin-top:220px;
    }
    .homepage-img1{
        margin-bottom:30px;
    }
</style>
</head>
<body>
<!--<div class="homepage-img">-->
    <!--<img src="img/homepage-logo1.png" class="homepage-img1"><br />-->
    <!--<img src="img/homepage-logo2.png">-->
<!--</div>-->
<div class="homepage-signin">
    <img src="<%=path %>/common/img/index-bj.jpg">
</div>
</body>
</html>
