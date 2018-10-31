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
<title>角色管理</title>
 <link href="${basePath}common/css/profile.min.css" rel="stylesheet">
 <link href="${basePath}pages/system/css/management.css" rel="stylesheet">
 <link href="${basePath}pages/finance/invest/css/invest.css" rel="stylesheet">
 <script type="text/javascript">
 $(function(){
		validator.validate($("#addRoleForm"));
		var url = basePath+"systemManage/queryRoleList";
		var colNames = ["角色名称","角色编码","角色描述","创建人","创建时间","修改人","修改时间",'pid'];
		var colModel = [
						   {name: "name",index: "name",sortable :false},
						   {name: "code",index: "code",sortable :false},
						   {name: "description",index: "description",width:300,align:"left",sortable :false},
						   {name: "create_user_name",index: "create_user_name",sortable :false},
						   {name: "create_date",index: "create_date", sortable :false},
						   {name: "update_user_name",index: "update_user_name", sortable :false},
						   {name: "update_date",index: "update_date", sortable :false},
						   {name: "pid",index: "pid", sortable :false,hidden:true}
						];
		
		var complateFunc = function(){}
		loadData(url, colNames, colModel, complateFunc);
		
	});
 </script>
 <style type="text/css">
 	.role-data{
 		display:none;
 	}
 	.tarea_remarks{
 		width: 306px;
 	}
 </style>
</head>
<body>
   <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                        <div class="search-span"><span>角色名称：</span></div>
                       <div class="search-text"><input class="search-text" name="name" type="text"></div>
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

		<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>角色管理</span>
                </div>
                <div class="top_btn">
                	<a href="javascript:;" id="addRole" class="main_btn sys_btn">
                        <img src="${basePath}common/img/addto.png">
                        <span>新增角色</span>
                    </a>
                    <a href="javascript:;" id="modifyRole" class="main_btn sys_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改角色</span>
                    </a>
                    <a href="javascript:;" id="authRole" class="main_btn sys_btn">
                        <img src="${basePath}common/img/Data_License.png">
                        <span>角色数据授权</span>
                    </a>
                    <a href="javascript:;" id="deleteRole" class="main_btn sys_btn">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除角色</span>
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
        
        <div id="Role_Manage_Add" class="add_Makeuup">
        	<form id="addRoleForm" action="${basePath }systemManage/addRole" method="post">
        		<input type="hidden" class="hide_field" name="pid">
        		<input type="hidden" class="hide_field" name="oriName">
            	<input type="hidden" class="hide_field" name="oriCode">
            <div>
	            <div class="fillbox_inline">
	                <label class="product-modify-span"><span class="textmust">*</span>角色名称：</label>
	                <div class="fillin form-group">
	                    <input type="text" validate-rule="notEmpty" name="name" class="fillin-input form-control">
	                </div>
	            </div>
	            <div class="fillbox_inline">
	                <label class="product-modify-span"><span class="textmust">*</span>角色编码：</label>
	                <div class="fillin form-group">
	                    <input type="text" validate-rule="notEmpty" name="code" class="fillin-input form-control">
	                </div>
	            </div>
	            <div class="fillbox_inline">
	                <label class="product-modify-span">描述：</label>
	                <div class="fillin form-group">
	                    <textarea name="description" class="tarea_remarks form-control"></textarea>
	                </div>
	            </div>
           
         </div>
        	</form>
        
        </div>
        <!--点击"新增角色"出现的弹窗-结束-->
    </div>
</body>
 <script type="text/javascript" src="${basePath}pages/system/js/role.js"></script>
</html>