<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>菜单管理</title>
    <link href="${basePath}pages/system/css/management.css" rel="stylesheet">
    <link href="${basePath}common/css/product.css" rel="stylesheet">
    <script type="text/javascript">
    
   $(document).ready(function(){
	   /*加载菜单*/
	  var menus = [${menus}][0];
	  initMenuList(menus);
   });
    </script>
    <style type="text/css">
		#menuCloneTemp{
			display: none;
		}
    </style>
</head>
<body>
    <div class="bg-gray">
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>菜单管理</span>
                </div>
                 <div class="top_btn">
                 	<%--                     <shiro:hasPermission name="system_menuManag_add"> --%>
                    <a href="javascript:;" id="menuAdd" class="main_btn sys_btn menu-addmenu">
                        <img src="${basePath}common/img/addto.png">
                        <span>新增菜单</span>
                    </a>
<%--                     </shiro:hasPermission> --%>
                 </div>
                <div class="tabled-one">
                 <div id="menuCloneTemp" class="targetfield-list-bottom">
                 	<!-- 父菜单 -->
	                 <div class="targetfield-list targetfield-list-padding">
	                 	   <span class="series-no">序号：</span>
	                 	   <span class="series-no-text"></span>
	                       <span class="menu-name">菜单名称：</span>
	                       <span class="menu-name-text"></span>
	                       <span class="menu-name">菜单链接：</span>
	                       <span class="menu-name-text"></span>
	                       <div style="float:right;margin-top: -1px;">
<%-- 	                       	   <shiro:hasPermission name="system_menuManag_update"> --%>
	                           <input type="button" class="ruleground-edit menu-modify" title="修改">
<%-- 	                           </shiro:hasPermission> --%>
<%-- 	                           <shiro:hasPermission name="system_menuManag_del"> --%>
	                           <input type="button" class="ruleground-delete menu-delete" title="删除">
<%-- 	                           </shiro:hasPermission> --%>
	                           <input type="hidden" name="pid">
	                       </div>
	                  </div>
	                  <!-- 子菜单组 -->
	                  <div class="menu-rulecoding" style="display: none;">
                            <div class="ruleground-rulecoding-column">
                            	<span class="series-no">序号：</span>
	                 	   		<span class="series-no-text"></span>
                                <span class="menu-name">菜单名称：</span>
                                <span class="menu-name-text"></span>
                                <span class="menu-name">菜单链接：</span>
                                <span class="menu-name-text"></span>
                                <div style="float: right">
<%--                                 	<shiro:hasPermission name="system_menuManag_update"> --%>
                                    <input type="button" class="ruleground-edit-gray menu-modify" title="修改">
<%--                                     </shiro:hasPermission> --%>
<%--                                     <shiro:hasPermission name="system_menuManag_del"> --%>
                                    <input type="button" class="ruleground-delete-gray menu-delete" title="删除">
<%--                                     </shiro:hasPermission> --%>
                                </div>
                            </div>
                      </div>
                  </div>
                  <!-----------------------------菜单-开始---------------------------------------->
                  <div id="menuArray" class="targetfield-list-start">
                  </div>
                </div>

	<div id="menu_Manage_Add" class="add_Makeuup">
       <form id="addMenuForm" action="${basePath }systemManage/addMenu" method="post">
	       <input type="hidden" class="hide_field" name="modifyFlg"/>
           <input type="hidden" class="hide_field" name="level" value="1">
           <input type="hidden" class="hide_field" name="maxSeriesNo" value="">
           <input type="hidden" class="hide_field" name="pid" value="">
           <input type="hidden" class="hide_field" name="oriSeriesNo" value="">
        <div>
            <div class="fillbox_inline">
                <label class="product-modify-span"><span class="textmust">*</span>父级菜单：</label>
                <div class="fillin-inputlay layui-form form-group">
                        <select name="parentId"  class="form-control" validate-rule="notEmpty" lay-verify="" lay-search="" lay-filter="parentId">
                        	<option value="">--请选择--</option>
                            <option value="0">根菜单</option>
                        </select>
                </div>
            </div>
            <div class="fillbox_inline">
                <label class="product-modify-span"><span class="textmust">*</span>菜单名称：</label>
                <div class="fillin form-group">
                   <input type="hidden" name="oriName" value="">
                   <input type="text" name="name" validate-rule="notEmpty|charLength[1-50]" class="fillin-input form-control">
                </div>
            </div>
            <div class="fillbox_inline form-group">
                <label class="product-modify-span"><span class="textmust">*</span>菜单链接：</label>
                <div class="fillin form-group">
                    <input type="hidden" name="oriUrl" value="">
                    <input type="text" name="url" validate-rule="notEmpty|charLength[1-300]" class="fillin-input form-control">
                </div>
            </div>
            <div class="fillbox_inline">
                <label class="product-modify-span">显示顺序：</label>
                <div class="fillin form-group">
                   <input type="text" name="seriesNo" validate-rule="notEmpty" class="fillin-input form-control">
                </div>
            </div>
         </div>
        </form>
    </div>
   </div>
  </div>
</div>
</body>
<script type="text/javascript" src="${basePath}pages/system/js/menu.js"></script>
</html>