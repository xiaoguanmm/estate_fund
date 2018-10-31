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
<title>权限管理</title>
 <link href="${basePath}pages/system/css/management.css" rel="stylesheet">
 <link href="${basePath}pages/finance/invest/css/invest.css" rel="stylesheet">
 <script type="text/javascript" src="${basePath}pages/system/js/authority.js"></script>
 <script type="text/javascript">
$(function(){
	validator.validate($("#addAuthForm"));
	var url = basePath+"systemManage/queryAuthorityList";
	var colNames = ["权限名称","权限类型","上级权限名","权限描述","权限编码","创建人","创建时间","修改人","修改时间","pid","resource_id","parent_id","type"];
	var colModel = [
					   {name: "name",index: "name",sortable :false},
					   {name: "type",index: "type",sortable :false,formatter:function(cellvalue, options, rowObject){
						   if(cellvalue=="0"){
							   return '<span title=\"菜单权限\">菜单权限</span>';  
						   }
						   if(cellvalue=="1"){
							   return '<span title=\"增删改\">增删改</span>';
						   }
						   if(cellvalue=="2"){
							   return '<span title=\"查询权限\">查询权限</span>';
						   }
					   }},
					   {name: "parent_name",index: "parent_name",sortable :false},
					   {name: "description",index: "description",sortable :false},
					   {name: "authority_code",index: "authority_code",sortable :false},
					   {name: "create_user_name",index: "create_user_name",sortable :false},
					   {name: "create_date",index: "create_date", sortable :false},
					   {name: "update_user_name",index: "update_user_name", sortable :false},
					   {name: "update_date",index: "update_date", sortable :false},
					   {name: "pid",index: "pid", sortable :false,hidden:true},
					   {name: "resource_id",index: "resource_id", sortable :false,hidden:true},
					   {name: "parent_id",index: "parent_id", sortable :false,hidden:true},
					   {name: "type",index: "type", sortable :false,hidden:true}
					  ];
	
	var complateFunc = function(){}
	loadData(url, colNames, colModel, complateFunc);
});

</script>
 <style type="text/css">
 	.authority-data{
		display:none;
 	}
 	.fillin-input-194{
 		width: 194px;
 	}
 	.tarea_remarks{
 		width: 306px;
 	}
 	.menu_sel{
 		margin-right: 0px;
 	}
 </style>
</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
            
            <div class="search-box">
        			<div class="search-box_div">
	                    <div class="search-span"><span>权限名：</span></div>
	                    <div class="search-text"><input name="name" type="text"></div>
                    </div>
                    <div class="search-box_div">
	                    <div class="search-span"><span>权限类型：</span></div>
	                    <div class="search-text layui-form">
	                      <select name="type" lay-verify="" lay-search="">
	                  	   <option value="">--请选择--</option>
	                  	   <option value="0">菜单权限</option>
	                  	   <option value="1">增删改</option>
	                  	   <option value="2">查询权限</option>
	                     </select>
	                  	</div>
                  	</div>
                  	<div class="search-box_div">
	                   <div class="search-span"><span>上级权限名：</span></div>
	                   <div class="search-text layui-form">
		                   <select name="parentId" class="form-control" lay-verify="" lay-search="">
		                          <option value="">--请选择--</option>
		                          <fund:options code="${Globals.AUTHORITY}"/>
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
        
         <div id="Auth_Manage_Add" class="add_Makeuup">
         	<form id="addAuthForm" action="${basePath }systemManage/addAuthority" method="post">
         		<input type="hidden" class="hide_field" name="pid">
         		<input type="hidden" class="hide_field" name="oriName">
                <input type="hidden" class="hide_field" name="oriAuthId">
                <div>
	                <div class="fillbox_inline">
		                <label class="product-modify-span"><span class="textmust">*</span>权限类型：</label>
		                <div class="fillin fillin-inputlay layui-form form-group">
	                      	 <select name="type" id="authType" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="authType">
	                          <option value="">--请选择--</option>
	                          <option value="0">菜单权限</option>
	                  	   	  <option value="1">增删改</option>
	                  	   	  <option value="2">查询权限</option>
	                      	</select>
		                </div>
		            </div>
		            <div class="resource-info fillbox_inline" style="display: none;">
		                <label class="product-modify-span"><span class="textmust">*</span>菜单名称：</label>
		                <div class="fillin fillin-inputlay menu_sel layui-form form-group">
	                      	 <select name="resourceId" id="resourceId" class="form-control" lay-verify="" lay-search="" lay-filter="resourceId">
	                          <option value="">-请选择-</option>
	                          <fund:options code="${Globals.MENU}"></fund:options>
	                      	</select>
		                </div>
		            </div>
		            <div class="fillbox_inline">
		            	<label class="product-modify-span"><span class="textmust">*</span>权限名称：</label>
		            	<div class="fillin form-group">
		            		<input type="text" name="name" validate-rule="notEmpty" class="fillin-input form-control">
		            	</div>
		            </div>
		            <div class="fillbox_inline">
		            	<label class="product-modify-span"><span class="textmust">*</span>权限编码：</label>
		            	<div class="fillin form-group">
		            		 <input type="text" name="authorityCode" validate-rule="notEmpty" class="fillin-input form-control">
		            	</div>
		            </div>
		            <div class="fillbox_inline">
		            	<label class="product-modify-span">上级权限名：</label>
		            	 <div class="fillin fillin-inputlay menu_sel layui-form form-group">
	                      	 <select name="parentId" id="parentId" class="form-control" lay-verify="" lay-search="" lay-filter="parentId">
	                          <option value="">--请选择--</option>
	                          <fund:options code="${Globals.AUTHORITY}"/>
	                      	</select>
		                </div>
		            </div>
		              <div class="fillbox_inline">
		            	<label class="product-modify-span">权限描述：</label>
		            	<div class="fillin form-group">
		            		 <textarea type="text" name="description" class="tarea_remarks form-control"></textarea>
		            	</div>
		            </div>
                </div>
         	</form>
         </div>
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>权限管理</span>
                </div>
                <div class="top_btn">
                <a href="javascript:;" id="addAuthority" class="main_btn sys_btn">
                        <img src="${basePath}common/img/addto.png">
                        <span>新增权限</span>
                    </a>
                     <a href="javascript:;" id="modifyAuthority" class="main_btn sys_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改权限</span>
                    </a>
                     <a href="javascript:;" id="deleteAuthority" class="main_btn sys_btn">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除权限</span>
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
    </div>
</body>
<style type="text/css">
    .ui-autocomplete{
        display:block;
        z-index:100000000000000000000000000000000;
    }
    .ui-autocomplete li:hover{
    	background: #009EDC; 
    }
</style>





</html>