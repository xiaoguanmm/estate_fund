<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">
<title>用户管理</title>
 <link href="${basePath}pages/system/css/management.css" rel="stylesheet">
 <script type="text/javascript">
 $(function(){
	 validator.validate($("#addUserInfoForm"));
	 var url = basePath+"systemManage/queryMenuList";
	 var colNames = ["用户名" , "姓名","所属部门","手机号","职位","是否禁用","创建人" ,"创建时间","修改人","修改时间","pid","status","department_code"];
	 var colModel = [
	 				   {name: "user_name",index: "user_name",sortable :false},
	 				   {name: "name",index: "name",sortable :false},
	 				   {name: "department",index: "department",sortable :false},
	 				   {name: "tel",index: "tel",sortable :false},
	 				   {name: "duty",index: "duty",sortable :false},
	 				   {name: "status_name",index: "status_name",sortable :false},
	 				   {name: "create_user_name",index: "create_user_name",sortable :false},
	 				   {name: "create_date",index: "create_date", sortable :false},
	 				   {name: "update_user_name",index: "update_user_name", sortable :false},
	 				   {name: "update_date",index: "update_date", sortable :false},
	 				   {name: "pid",index: "pid", sortable :false,hidden:true} ,
	 				   {name: "status",index: "status", sortable :false,hidden:true} ,
	 				   {name: "department_code",index: "department_code", sortable :false,hidden:true}  
	 				];
	 var complateFuc = function(){};
	 loadData(url,colNames,colModel,complateFuc);
	 });
 </script>
</head>
<body>
    <div class="bg-gray">
    <!-- 禁止chrome自动填充 -->
	<input style="display:none" type="text" disabled="disabled" name="userName"/>
	<!-- 禁止chrome自动填充 end -->
        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                 <div class="search-box_div">
                   <div class="search-span"><span>用户名：</span></div>
                   <div class="search-text"><input type="text" name="userName" autocomplete="off"></div>
                   </div>
                    <div class="search-box_div">
                   <div class="search-span"><span>姓名：</span></div>
                   <div class="search-text"><input type="text" name="name"></div>
                   </div>
                    <div class="search-box_div">
                   <div class="search-span"><span>是否禁用：</span></div>
                   <div class="search-text layui-form">
                      <select name="status" lay-verify="" lay-search="">
                          <option value="">--请选择--</option>
                          <option value="1">已启用</option>
                          <option value="0">已禁用</option>
                      </select>
                   </div>
                   </div>
                   <div class="search-box_div search_box_div_left">
                   <button type="button"  class="search-btn search-btn-chaxu">
                       <img src="${basePath}common/img/search-btn.png">
                       <span>查询</span>
                   </button>
                   <button type="button" class="search-btn search-btn-cz">
                       <img src="${basePath}common/img/chognzhi-reach.png">
                       <span>重置</span>
                   </button>
					</div>
                </div>
        </div>
        </div>

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>用户列表</span>
                </div>
                <div class="top_btn">
                	<a href="javascript:;" id="addUserInfo" class="main_btn sys_btn">
                        <img src="${basePath}common/img/addto.png">
                        <span>新增用户</span>
                    </a>
                     <a href="javascript:;" id="modifyUserInfo" class="main_btn sys_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改用户</span>
                    </a>
                    <a href="javascript:;" id="authUserInfo" class="main_btn sys_btn">
                        <img src="${basePath}common/img/Data_License.png">
                        <span>用户数据授权</span>
                    </a>
                    <a href="javascript:;" id="startUserInfo" class="main_btn sys_btn">
                        <img src="${basePath}common/img/Enable.png">
                        <span>启用/禁用</span>
                    </a>
                    <a href="javascript:;" id="deleteUserInfo" class="main_btn sys_btn">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除用户</span>
                    </a>
                </div>
                <div class="tabled-one">
                    <!--表格-->
                    <div class="tabled-two">
                        <table id="data_list" class="table table-bordered table-hover"></table>
                        <div id="page_list"></div>
                    </div>
                </div>
            </div>
        </div>
		<div id="pwdHiddenArea" style="display: none;">
			<div class="fillbox_inline pwd_div">
	             <label class="product-modify-span"><span class="textmust">*</span>密码：</label>
	             <div class="fillin form-group">
	                 <input type="password" id="pswd" name="password" validate-rule="notEmpty"  class="fillin-input form-control">
	             </div>
	        </div>
		</div>
		
		
	<div id='User_Manage_Add' class="add_Makeuup">
       <form id="addUserInfoForm" action="${basePath }systemManage/addUserInfo" method="post">
       <input type="hidden" class="hide_field" id="pid" name="pid">
       <input type="hidden" class="hide_field" id="oriUserName" name="oriUserName">
        <div>
            <div class="fillbox_inline">
                <label class="product-modify-span"><span class="textmust">*</span>用户名：</label>
                <div class="fillin form-group">
                    <input type="text" name="userName" id="userName" validate-rule="notEmpty" class="fillin-input form-control">
                </div>
            </div>
            <div id="uName" class="fillbox_inline">
                <label class="product-modify-span"><span class="textmust">*</span>姓名：</label>
                <div class="fillin form-group">
                    <input type="text" validate-rule="notEmpty" name="name" id="name" class="fillin-input form-control">
                </div>
            </div>
            <div class="fillbox_inline form-group">
                <label class="product-modify-span">所属部门：</label>
                <div class="fillin-inputlay form-group layui-form">
                     <select  name="departmentCode" id="departmentCode" class="form-control " lay-verify="" lay-search>
                       <option value="">--请选择--</option>
                     </select>
                </div>
            </div>
            <div class="fillbox_inline">
                <label class="product-modify-span">职位：</label>
                <div class="fillin form-group">
                     <input type="text" name="duty" id="duty" class="fillin-input form-control">
                </div>
            </div>
             <div class="fillbox_inline">
                <label class="product-modify-span">手机号：</label>
                <div class="fillin form-group">
                     <input type="text" name="tel" id="tel" class="fillin-input form-control">
                </div>
            </div>
            <div class="fillbox_inline form-group" id="is-forbidden">
                <label class="product-modify-span"><span class="textmust">*</span>是否禁用：</label>
                <div class="fillin layui_input_block layui-form form-group">
                    <input type="radio" id="status_start" name="status" checked="checked" value="1" title="启用">
                    <input type="radio" id="status_stop" name="status" value="0" title="禁用" >
                </div>
            </div>
            <div id="reset-pwd" class="fillbox_inline">
                <label class="product-modify-span">重置密码：</label>
                <div class="fillin layui_input_block layui-form form-group">
                   <input type="radio" id="reset_yes" name="resetPassword" value="Y" title="是">
                   <input type="radio" id="reset_no" name="resetPassword" value="N" checked="checked" title="否" >
                </div>
            </div>
         </div>
        </form>
    </div>
        
        <!--点击"数据授权"出现的弹窗-开始-->
        <div id="User_Auth_Add" class="add_Makeuup">
        	<form id="userAuthForm" action="${basePath }systemManage/userAuthorization" method="post">
        	<input type="hidden" name="pid">
        		<div>
                  <div class="fillbox_inline">
                      <label class="product-modify-span">用户名：</label>
                      <div class="fillin form-group">
                          <input type="text" id="authoritied-user-name" class="fillin-input notoptional" disabled="disabled">
                      </div>
                  </div>
                   <div>
                      <label class="product-modify-span"><span class="textmust">*</span>数据角色：</label>
                      <div class="fillin layui-form form-group"  id="roles">
                      	
                      </div>
                  </div>
                   
                </div>
        	</form>
        </div>
        <!--点击"数据授权"出现的弹窗-结束-->

    </div>
</body>
<script type="text/javascript" src="${basePath}pages/system/js/userInfo.js"></script>
</html>