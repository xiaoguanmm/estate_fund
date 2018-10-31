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
<title>字典管理</title>
 <link href="${basePath}common/css/profile.min.css" rel="stylesheet">
 <link href="${basePath}pages/system/css/management.css" rel="stylesheet">
 <style type="text/css">
 	.dict-data{
 		display:none;
 	}
 	.fillin-input-150{
 		width: 150px;
 	}
 	
 </style>
</head>
<body>
   <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-span"><span>字典名称：</span></div>
                    <div class="search-text"><input class="search-text" name="name" type="text"></div>
                    <div class="search-span"><span>字典编码：</span></div>
                    <div class="search-text"><input class="search-text" name="code" type="text"></div>
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
                    <span>字典管理</span>
                </div>
                <div class="top_btn">
                	 <a href="javascript:;" id="addDict" class="main_btn sys_btn">
                        <img src="${basePath}common/img/addto.png">
                        <span>新增字典</span>
                    </a>
                    <a href="javascript:;" id="modifyDict" class="main_btn sys_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改字典</span>
                    </a>
                    <a href="javascript:;" id="deleteDict" class="main_btn sys_btn">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除字典</span>
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
          <div id="parametersTemp" style="display: none">
              <div class="fillboxparameters">
                   <div>
                      <div class="fillin form-group">
                       <input type="text" name="values" validate-rule="notEmpty" class="fillin-input fillin-input-150 dict-val dict-val-v form-control" placeholder="值">
                      </div>
                        <div class="fillin form-group">
                       	<input type="text" name="valueDeses" validate-rule="notEmpty" class="fillin-input fillin-input-150 dict-val dict-val-t form-control" placeholder="值描述">
                       </div>
                       <div class="parameters-delete"></div><!--删除图标-->
	              </div>
              </div>
          </div>
      <div id="dict_Manage_Add" class="add_Makeuup">
       <form id="addDictForm" action="${basePath }systemManage/addDict" method="post">
	       <input type="hidden" class="hide_field" name="pid">
	       <input type="hidden" class="hide_field" name="oriName">
	       <input type="hidden" class="hide_field" name="oriCode">
        <div>
            <div class="fillbox_inline">
                <label class="product-modify-span"><span class="textmust">*</span>字典名称：</label>
                <div class="fillin form-group">
                    <input type="text" validate-rule="notEmpty" name="name" class="fillin-input form-control">
                </div>
            </div>
            <div class="fillbox_inline">
                <label class="product-modify-span"><span class="textmust">*</span>字典码：</label>
                <div class="fillin form-group">
                    <input type="text" validate-rule="notEmpty" name="code" class="fillin-input form-control">
                </div>
            </div>
            <div class="fillbox_inline form-group">
                <label class="product-modify-span"><span class="textmust">*</span>字典状态：</label>
                <div class="fillin layui_input_block layui-form ">
                    <input type="radio" name="status" checked="checked" value="1" title="启用">
                    <input type="radio" name="status" value="0" title="禁用" >
                </div>
            </div>
            <div class="fillbox_inline">
                <label class="product-modify-span">字典描述：</label>
                <div class="fillin form-group">
                    <textarea class="tarea_remarks form-control" name="remark" style="width: 306px;"></textarea>
                </div>
            </div>
            <div class="fillbox_inline">
            	<label class="product-modify-span">字典值(K-V)：</label>
            	<div class="fillin parameters-inline">
                     <div class="fillboxparameters-out">
                         <div class="fillboxparameters">
                          <div>
                             <div class="fillin form-group">
                              <input type="text" name="values" validate-rule="notEmpty" class="fillin-input fillin-input-150 dict-val dict-val-v form-control" placeholder="值">
                             </div>
                               <div class="fillin form-group">
                              	<input type="text" name="valueDeses" validate-rule="notEmpty" class="fillin-input fillin-input-150 dict-val dict-val-t form-control" placeholder="值描述">
                              </div>
                              <div class="parameters-delete"></div>
                          </div>
                         </div>
                     </div>
                     
              </div>
              <div class="parameters-add parameters-add-right">
                                  <div class="parameters-add-img"></div>
                                  <span class="parameters-add-span">新增</span>
              </div>
            </div>
         </div>
        </form>
    </div>
    
    </div>
</body>
<script type="text/javascript" src="${basePath}pages/system/js/dict.js"></script>
<script type="text/javascript" src="${basePath}common/js/management.js"></script>
<script type="text/javascript">
$(function(){
	validator.validate($("#addDictForm"));
	var url = basePath+"systemManage/queryDictList";
	var colNames = ["字典名称","字典编码","状态","描述","创建人","创建时间","修改人","修改时间","pid","status"];
	var colModel = [
					   {name: "name",index: "name",sortable :false},
					   {name: "code",index: "code",sortable :false},
					   {name: "status_name",index: "status_name",sortable :false},
					   {name: "remark",index: "remark",sortable :false},
					   {name: "create_name",index: "create_name", sortable :false},
					   {name: "create_date",index: "create_date", sortable :false},
					   {name: "update_user_name",index: "update_user_name", sortable :false},
					   {name: "update_date",index: "update_date", sortable :false},
					   {name: "pid",index: "pid", sortable :false,hidden:true},
					   {name: "status",index: "status", sortable :false,hidden:true}
					];
	var complateFunc = function(){}
	loadData(url, colNames, colModel, complateFunc);
});

</script>
</html>