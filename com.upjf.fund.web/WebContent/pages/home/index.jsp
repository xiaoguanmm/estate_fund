<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp">
<title>汇联优品-风控模型系统</title>
<%@ include file="../../../common/common.jsp"%>
<script type="text/javascript" src="${basePath}pages/home/js/index.js"></script>
<style type="text/css">
	.personal-data1{
		cursor: pointer;
	}
	.error{
    	text-align: left;
    }
    .new-user-chgpwd{
	    display:none;
	    position:absolute;
	    vertical-align: middle;
	    top: 50%;
	    left: 50%;
	    -webkit-transform: translate(-50%, -50%);
	    -moz-transform: translate(-50%, -50%);
	    -ms-transform: translate(-50%, -50%);
	    -o-transform: translate(-50%, -50%);
	    transform: translate(-50%, -50%);
	}
</style>
 <!--弹窗设置  -->
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden"><!--顶部导航开始-->
<div>
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">

            <div class="navbar-header" style="display: none;">
                <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
            </div>

            <ul class="nav navbar-top-links J_tabExit"><a href="javascript:void(0);" class="roll-nav roll-right" id="Sign_Out"><i class="fa fa fa-sign-out"></i> 退出</a></ul>
            <ul class="nav navbar-top-links navbar-right">
                <div class="fa J_menuItem personal-data1"><a id="Personal_Profile1"><li class="dropdown hidden-xs jg_hand">${userInfo.name}</li></a></div>
            </ul>
            <ul class="nav navbar-top-links navbar-left-fk">
                <img alt="image" class="img-circle" src="${basePath}common/img/logo.png" />
                <img src="${basePath}common/img/logo-fk.png">
            </ul>
        </nav>
    </div>
</div>

 <div id="wrapper">
  <!--左侧导航开始-->
  <div class="navbar-default navbar-static-side" role="navigation">
  <div class="nav-close">
   <i class="fa fa-times-circle"></i>
  </div>

  <div class="sidebar-collapse">
   <ul class="nav" id="side-menu" style="margin-bottom: 100px;">
	   <li id="mainMenuCloneTemp" style="display: none;" class>
	    	<a href="javascript:;"> <i class="fa fa-home trade_manage"></i> <span class="nav-label"></span> <span class="fa arrow"></span></a>
		       <ul class="nav nav-second-level collapse" style="background:#27263e;">
		          <li><a class="J_menuItem" onclick="" data-index="0"></a></li>
		       </ul>
	    </li>
    </ul>
  </div>
   </div>
  </nav>
  <!--左侧导航结束-->

  <!--上侧部分开始-->
  <div id="page-wrapper" class="gray-bg dashbard-1">

   <div class="row content-tabs" style="border-left: 1px #e7eaec solid;">
    <button class="roll-nav roll-left J_tabLeft">
     <i class="fa fa-backward"></i>
    </button>
    <nav class="page-tabs J_menuTabs">
    <div class="page-tabs-content" id="page-tabs-content">
     <a href="javascript:;" name="J_menuTab" class="active J_menuTab" data-id="homepage.html" style="padding: 2px 20px;">首页</a>
    </div>
    </nav>
    <button class="roll-nav roll-right J_tabRight">
     <i class="fa fa-forward"></i>
    </button>
    <div class="btn-group roll-nav roll-right">
     <button class="dropdown J_tabClose" data-toggle="dropdown">
      关闭操作<span class="caret"></span>
     </button>
     <ul role="menu" class="dropdown-menu dropdown-menu-right">
      <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
      <li class="divider"></li>
      <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
      <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
     </ul>
    </div>
    
   </div>
   <div class="row J_mainContent" id="content-main" >
    <!-- 首页 -->
    <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${basePath}pages/home/homePage.jsp" frameborder="0" data-id="homepage.html" seamless></iframe>
   </div>

  </div>
 </div>

 <!--个人资料 弹窗-开始-->
 <div  id="Personal_Profile"  class="add_Makeuup">
 	<form id="userInfoForm" method="post" action="${basePath }systemManage/changePassword">
 		 <input type="hidden" name="pid" value="${userInfo.pid}">
	     <div>
	         <div class="fillbox_inline">
	             <label class="product-modify-span">用户名：</label>
	             <div class="fillin form-group">
	                 <input type="text" value="${userInfo.userName}" class="fillin-input notoptional form-control" disabled="disabled">
	             </div>
	         </div>
	         <div class="fillbox_inline">
	             <label class="user-name product-modify-span">姓名：</label>
	             <div class="fillin form-group">
	                 <input type="text" value="${userInfo.name}" class="fillin-input notoptional form-control" disabled="disabled">
	             </div>
	         </div>
	         <div class="fillbox_inline">
	             <label class="product-modify-span">手机号：</label>
	             <div class="fillin form-group">
	                 <input type="text" value="${userInfo.tel}" class="fillin-input notoptional form-control" disabled="disabled">
	             </div>
	         </div>
	         <div class="fillbox_inline">
	             <label class="product-modify-span">职位：</label>
	             <div class="fillin form-group">
	                 <input type="text" value="${userInfo.duty}" class="fillin-input notoptional form-control" disabled="disabled">
	             </div>
	         </div>
	         <div class="fillbox_inline">
	             <label class="product-modify-span">所属部门：</label>
	             <div class="fillin form-group">
	                 <input type="text" value="${userInfo.departmentCode}" class="fillin-input notoptional form-control" disabled="disabled">
	             </div>
	         </div>
	         <div class="fillbox_inline form-div">
	             <label class="product-modify-span">原密码：</label>
	             <div class="fillin form-group">
	                 <input type="password" name="oripassword" validate-rule="notEmpty" class="fillin-input form-control">
	             </div>
	         </div>
	         <div class="fillbox_inline form-div">
	             <label class="product-modify-span">新密码：</label>
	             <div class="fillin form-group">
	                 <input type="password" name="password" validate-rule="notEmpty|different[oripassword]|identical[repassword]" class="fillin-input form-control">
	             </div>
	         </div>
	         <div class="fillbox_inline form-div">
	             <label class="product-modify-span">确认密码：</label>
	             <div class="fillin form-group">
	                 <input type="password" name="repassword" validate-rule="notEmpty|identical[password]" class="fillin-input form-control">
	             </div>
	         </div>
	     </div>
     </form>
 </div>
 
  <div  id="Changepwd_Profile"  class="add_Makeuup">
 	<form id="chgnewpwdForm" method="post" action="${basePath }systemManage/chgnewpwd">
 	<input type="hidden" name="pid" value="${userInfo.pid}">
 		<div>
 			<div class="fillbox_inline form-div">
 				<label class="product-modify-span">新密码：</label>
	            <div class="fillin form-group">
	             <input type="password" name="password" validate-rule="notEmpty|identical[repassword]" class="fillin-input form-control">
	            </div>
 			</div>
 			 <div class="fillbox_inline form-div">
	             <label class="product-modify-span">确认密码：</label>
	             <div class="fillin form-group">
	                 <input type="password" name="repassword" validate-rule="notEmpty|identical[password]" class="fillin-input form-control">
	             </div>
	         </div>
 		</div>
 		
 	</form>
 </div>



   </body>
 <script type="text/javascript">
	//右上角--‘退出’
	$(function(){
		validator.validate($("#userInfoForm"));
		validator.validate($("#chgnewpwdForm"));
		var is_new = "${userInfo.isNew}";
		   if(is_new=="Y"){
			   layer.open({
					type : 1,
					title : [ '${userInfo.name}', 'font-size:18px;' ],
					area : [ '600px', '400px' ],
					btnAlign : 'c',
					content : $('#Changepwd_Profile'),
					btn : [ '确定', '取消' ],
					btn1: function(index, layero) {
						validator.triggerForm($("#chgnewpwdForm"));
						if(validator.isValid($("#chgnewpwdForm"))){
							var data = $("#chgnewpwdForm").serialize();
							$.ajax({
			          		    url: $("#chgnewpwdForm").attr("action"),
			          		    cache: true,
			          		    type: "POST",
			          		    data: data,
			          		    async: false,
			          			success : function(msg) {
			          				if (msg && msg["successMsg"]) {
										layer.msg(msg["successMsg"]+"请重新登录",{icon: 1});
										setTimeout(function () {
											window.location.href=basePath+"logout";
									     }, 1500);
									} else {
										layer.msg(msg["errMsg"],{icon:7});
									}
			          			},
			          			error:function(msg){
			          				alert(msg["errMsg"]);
			          			}
			          		});
							
							
						}
						
					},
					btn2: function(index, layero) {
						window.location.href=basePath+"logout";
					},
					cancel: function(index,layero){
						window.location.href=basePath+"logout";
					}
				});			   

		   }
		
		
		$('#Sign_Out').on('click',function() {
			layer.open({
				type : 1,
				title : [ '退出管理系统', 'text-align:center;' ],
				area : [ '300px', '200px' ],
				btnAlign : 'c',
				content : '<div class="content_text">确定退出地产资金管理系统？</div>',
				btn : [ '退出', '取消' ],
				btn1: function(index, layero) {
					window.location.href = basePath+"logout";
				},
				btn2: function(index, layero) {
					//按钮【按钮二】的回调
				}
			});
		});
		
	//右上角--‘个人资料’
		$('#Personal_Profile1').on('click', function() {
			layer.open({
				type : 1,
				title : [ '${userInfo.name}', 'font-size:18px;' ],
				area : [ '600px', '700px' ],
				btnAlign : 'c',
				content : $('#Personal_Profile'),
				btn : [ '确定', '取消' ],
				btn1: function(index, layero) {
					validator.triggerForm($("#userInfoForm"));
					if(validator.isValid($("#userInfoForm"))){
						var data = $("#userInfoForm").serialize();
						$.ajax({
		          		    url: $("#userInfoForm").attr("action"),
		          		    cache: true,
		          		    type: "POST",
		          		    data: data,
		          		    async: false,
		          			success : function(msg) {
		          				if (msg && msg["successMsg"]) {
									layer.msg(msg["successMsg"]+"请重新登录",{icon: 1});
									setTimeout(function () {
										window.location.href=basePath+"logout";
								     }, 1500);
								} else {
									layer.msg(msg["errMsg"],{icon:7});
								}
		          			},
		          			error:function(msg){
		          				alert(msg["errMsg"]);
		          			}
		          		});
						
						
					}
					
				},
				btn2: function(index, layero) {
					validator.resetForm($("#userInfoForm"));
				},
				cancel: function(index,layero){
					validator.resetForm($("#userInfoForm"));
				}
			});
		});
	
		 var menus = ${menuList};
			loanMainMenuList(menus);
		
	});

</script>
<script type="text/javascript" src="${basePath}common/js/jquery.metisMenu.js"></script>
<script type="text/javascript" src="${basePath}common/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${basePath}common/js/hplus.min.js?v=4.1.0"></script>
<script type="text/javascript" src="${basePath}common/js/contabs.min.js"></script>

</html>