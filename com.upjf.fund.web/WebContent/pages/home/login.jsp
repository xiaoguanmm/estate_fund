<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">
<title>欢迎登录汇联优品智能风控系统</title>
<%@ include file="../../../common/common.jsp"%>
<link href="${basePath}pages/home/css/login.min.css" rel="stylesheet">
<link href="${basePath}common/css/animate.min.css" rel="stylesheet">
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<script type="text/javascript"> 
$(function(){
	
	/*如果session失效直接在顶层跳转到登录页面*/
	if(window !=top){  
	    top.location.href=location.href;  
	} 
	$('#Kaptcha').click(function() {
	        	$(this).hide().attr('src',$(this).attr("src")+"?" + Math.floor(Math.random() * 100)).fadeIn();     
	    });
	validator.validate($("#loginForm"), function (dom) {
		$.ajax({
		    url: dom.attr("action"),
		    cache: true,
		    type: "POST",
		    data: $("#loginForm").serialize(),
		    async: false,
			success : function(msg) { //表单提交后更新页面显示的数据
				if (msg && msg["successMsg"]) {
					window.location.href=$("#loginForm").attr("action").replace("/login","/main");
				} else {
					layer.msg(msg["errMsg"], {time:1000,icon:7});
					if("验证码错误"==msg["errMsg"]||"验证码失效"==msg["errMsg"]){
						$("input[name=captcha]").val("");
						$("#Kaptcha").trigger("click");
					}
					validator.resetForm(dom);
				}
			},
			error:function(msg){
				alert(msg["errMsg"]);
			}
		});
	
	});
});
</script>
<style type="text/css">
.form-controlslogon {
	 padding:19px 10px 19px 32px;
	font-size: 16px;
}
</style>
</head>
<body>
 <div class=" text-center loginscreen  animated fadeInDown" style="padding-top:100px;">
     <div class="img-circle_div">
             <div >
                 <img alt="image" src="${basePath}common/img/login_logo.png" /><br />
                 <img class="login_logo_sl" src="${basePath}common/img/login_logo_sl.png">
             </div>
         </div>
     <div class="middle-box">
             <div class="login_logo-2">
                 <img src="${basePath}common/img/login_logo-2.png">
             </div>
             <div class="login_logo-2-jian">
                 <img src="${basePath}common/img/login_logo-2-jian.png">
             </div>
             <div class="login_box">
                 <h5 style="color:#fff;margin-bottom:24px;font-size: 18px;" >欢迎登录</h5>
   			<div class="">
        	<form method="post" action="${basePath}systemManage/login" id="loginForm" >
             <div class="form-group">
                <img src="${basePath}common/img/username.png" class="img-slogon">
                <input type="text" name="userName" value="admin" class="form-control form-controlslogon" validate-rule="notEmpty" validate-message="用户名不能为空" placeholder="用户名" />
             </div>

             <div class="form-group">
                 <img src="${basePath}common/img/password.png" class="img-slogon">
                 <input type="password" name="password" value="111111"  class="form-control form-controlslogon" validate-rule="notEmpty" validate-message="密码不能为空" placeholder="密码" />
             </div>

             <div class="form-group">
                 <div class="input-group">
                 <input type="text" name="captcha" class="form-control pword" maxlength="4" validate-rule="notEmpty" validate-message="验证码不能为空" placeholder="验证码" />
                 <span class="input-group-btn">
                        <img id="Kaptcha" src="${basePath}Kaptcha.jpg">
                 </span>
                 </div>
             </div>
             <div class="form-group">
                 <button type="submit"  class="btn btn-login btn-block btn-denglu">登录</button>
             </div>

        </form>
   </div>
  </div>
  
 </div>
 </div>
	<div class="signup-footer">
		<div class="text-center">&copy;2018  地产基金项目管理系统   All Rights Reserved </div>
	</div>
	<div class="signin">
		<img src="${basePath}common/img/login-background.jpg">
	</div>
</body>
</html>