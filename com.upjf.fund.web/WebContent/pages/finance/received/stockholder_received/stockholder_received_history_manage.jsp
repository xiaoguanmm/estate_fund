<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>回款记录列表</title>
    <%@ include file="../../../../common/common.jsp"%>
    <link href="${basePath}pages/finance/invest/css/invest.css" rel="stylesheet">
    <script type="text/javascript">
		$(function(){
			var url = basePath+"finance/queryStockholderPaybackList?prjId=${project_id}&receiverId=${corp_id}&contributiveId=${business_prj_id}&receivedType=1"
			var colNames = ["回款时间","回款账号","回款本金(万元)","回款利润(万元)","项目名称",
			                "付款公司","付款账号","回款状态","回款备注","回款录入人","回款录入时间",
			                "pid","receiver_bank_id","pay_bank_id","received_status"];
			var colModel = [
			                {name:"receiver_date",index:"receiver_date",sortable:false},
			                {name: "receiver_account",index: "receiver_account",sortable :false},
						    {name: "receiver_amount",index: "receiver_amount",sortable :false},
						    {name: "profit",index: "profit",sortable :false},
						    {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
			                	var prjId = "'"+"${project_id}"+"'";
						    	return '<a href="javascritp:;" class="table_bnt" onclick="showProjectInfo('+prjId+');return false;">${project_name}</a>';
						    }},
						    {name: "prj_corp_name",index: "prj_corp_name", sortable :false,formatter:function(cellvalue, options, rowObject){
						    	return '<span>${prj_corp_name}</span>';
						    }},
						    {name: "pay_account",index: "pay_account", sortable :false},
						    {name: "received_status_name",index: "received_status_name", sortable :false},
						    {name: "reveiver_remark",index: "reveiver_remark", sortable :false},
						    {name: "create_user",index: "create_user", sortable :false},
						    {name: "create_date",index: "create_date", sortable :false},
						    {name: "pid",index:"pid",sortable :false,hidden:true},
						    {name: "receiver_bank_id",index:"receiver_bank_id",sortable :false,hidden:true},
						    {name: "pay_bank_id",index:"pay_bank_id",sortable :false,hidden:true},
						    {name: "received_status",index:"received_status",sortable :false,hidden:true}
			                ];
			var complateFunc = function(){}
			loadData(url, colNames, colModel, complateFunc);
	});
    
    </script>


</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>回款账号：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverAccount" id="searchReceiverAccount" lay-verify="" lay-search="" lay-filter="receiverAccount">
                                <option value="">--请选择--</option>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                         <div class="search-span"><span>回款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" name="paybackDateStart" class="layui-input" id="paybackDateStart" >
                                </li>
                                <li>
                                    <img src="${basePath}common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" name="paybackDateEnd" class="layui-input" id="paybackDateEnd" >
                                </li>
                            </ul>
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
	                <a href="javascript:history.go(-1);" title="关闭" style="margin: 16px 0px;" class="history_page">关闭</a>
                </div>
            </div>
        </div>

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>回款列表 </span>
                    <span class="title-head_red">&nbsp;&nbsp;${corp_name}</span>
                </div>
                <div class="top_btn">
                	<c:if test="${sourceModule != 'trade'}">
	                    <a href="javascript:;" id="add_payback" class="main_btn">
	                        <img src="${basePath}common/img/add.png">
	                        <span>新增回款</span>
	                    </a>
	                    <a href="javascript:;" id="modify_payback" class="main_btn">
	                        <img src="${basePath}common/img/chance.png">
	                        <span>修改回款</span>
	                    </a>
                	</c:if>
                    <a href="javascript:;" id="view_payback" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看回款明细</span>
                    </a>
                    <c:if test="${sourceModule != 'trade'}">
	                    <a href="javascript:;" id="delete_payback" class="main_btn">
	                        <img src="${basePath}common/img/delete.png">
	                        <span>删除回款记录</span>
	                    </a>
	                    <a href="javascript:;" id="export" class="main_btn">
	                        <img src="${basePath}common/img/export.png">
	                        <span>导出</span>
	                    </a>
                    </c:if>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>回款本金合计：</span>
                            <span class="title_explain_red"><span class="title_explain_red" id="receiver_amount_total">${amount.receiver_amount}</span>万元</span>
                        </li>
                        <li>
                            <span>回款利润合计：</span>
                            <span class="title_explain_red"><span class="title_explain_red" id="profit_amount_total">${amount.profit_amount}</span>万元</span>
                        </li>
                    </ul>
                </div>
                <div class="tabled_one">
                    <!--表格-->
                    <div class="tabled-two">
                        <table id="data_list" class="table table-bordered table-hover"></table>
                        <div id="page_list"></div>
                    </div>
                    <div id="show_accessory" class="add_Makeuup">
                    <table id="accessory_list" class="table table-bordered table-hover"></table><div id="accessory_page_list"></div>
                    </div>
                </div>
               <div id="payback_operation" class="add_Makeuup">
                <input type="hidden" id="uploadFileUrl" value="${basePath}finance/uploadStockholderPaybackAccessory">
                <input type="hidden" id="showFileListUrl" value="${basePath}finance/queryStockholderPaybackAccessoryList">
                <input type="hidden" id="delPaybackUrl" value="${basePath}finance/delStockholderPaybackRecord">
                <input type="hidden" id="exportUrl" value="${basePath}finance/exportStockholderHistoryPaybackData">
                <input type="hidden" id="projectStatus" value="${project_status}">
                <input type="hidden" id="received_status" value="${received_status}">
                <form id="investForm" action="${basePath}finance/addStockholderPaybackRecord" method="post">
                	<input type="hidden" class="hide_field" id="corporationInfoId" value="${corporation_info_id}">
                	<input type="hidden" class="hide_field" id="pid" name="pid">
                	<input type="hidden" class="hide_field" name="prjId" value="${project_id}">
                	<input type="hidden" class="hide_field" id="corpId" name="receiverId" value="${corp_id}">
                	<input type="hidden" class="hide_field" name="contributiveId" value="${business_prj_id}">
                	<!-- 回款类别 -->
                	<input type="hidden" class="hide_field" id="receivedType" name="receivedType" value="1">
                    <div class="main_edge">
                        <div class="fillbox-left">
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>项目名称：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select id="prjId" disabled="disabled" class="form-control" lay-verify="" lay-search="" lay-filter="prjId">
                                               <option value="${project_id}">${project_name}</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款公司：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select id="receiverId" disabled="disabled" class="form-control" lay-verify="" lay-search="" lay-filter="receiverId">
                                                <option value="${corp_id}">${corp_name}</option>
                                            </select>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款公司：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select id="contributiveId" disabled="disabled" class="form-control" lay-verify="" lay-search="" lay-filter="contributiveId">
                                                <option value="${corporation_info_id}">${prj_corp_name}</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款银行：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="receiverBankId" id="receiverBankId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverBankId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款银行：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="payBankId" id="payBankId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="payBankId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款账户：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                     	<select name="receiverAccount" id="receiverAccount" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverAccount">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款账户：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                    	<select name="payAccount" id="payAccount" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="payAccount">
                                          <option value="">--请选择--</option>
                                       </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>回款本金(万元)：</label>
                                    <div class="fillin form-group">
                                        <input type="text" name="receiverAmount" id="receiverAmount" validate-rule="notEmpty|rangeNumberBits[14-2]" class="fillin-input form-control">
                                    </div>
                                </div>
                            </div>
                            <div>
                            	<div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>回款状态：</label>
                                    <div class=" fillin-inputlay layui-form form-group">
                                        <select name="receivedStatus" id="receivedStatus" class="fillin-input form-control" validate-rule="notEmpty" lay-verify="" lay-filter="receivedStatus">
                                                <option value="">--请选择--</option>
                                                <option value="1">已完结</option>
                                                <option value="2">未完结</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>回款利润(万元)：</label>
                                    <div class="fillin form-group">
                                        <input type="text" name="profit" validate-rule="notEmpty|rangeNumberBits[14-2]" id="profit" class="fillin-input form-control">
                                    </div>
                                </div>
                            </div>
                            <div>
                            	<div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>回款时间：</label>
                                    <div class="fillin form-group">
                                         <input type="text" name="receiverDate" readonly="readonly" validate-rule="notEmpty" class="fillin-input form-control" id="receiverDate" >
                                    </div>
                                </div>
                            </div>
                            
                            <div>
                                <div class="fillbox_inline ">
                                    <label class="product-modify-span">回款备注：</label>
                                    <div class="fillin form-group">
                                        <textarea validate-rule="charLength[0-4000]" name="reveiverRemark" id="reveiverRemark" class="fillin-input tarea_remarks form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                       </div>
                    </div>
                    </form>
                    
                     <div class="layui-upload fillbox-left" style="width: 90%;" id="upload_file">
	                    	<button type="button" class="layui-btn layui-btn-normal" id="select_file">选择文件</button> 
						    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="selectFileButton">选择文件</button> 
						    <button type="button" class="layui-btn" id="uploadFileButton">开始上传</button>
						    <div class="layui-upload-list">
						    <table class="layui-table">
						      <thead>
						        <tr>
							        <th>文件名</th>
							        <th>大小</th>
							        <th>状态</th>
							        <th>操作</th>
						      	</tr>
						      </thead>
						      <tbody id="fileList"></tbody>
						    </table>
						   </div>
					  </div>
					  <div class="main_edge">
                        <div class="title_bggray" style="margin: 0 10px;">
                        	<span>附件材料</span>
                        </div>
                         <!--表格-->
	                    <div class="tabled-two" style="margin: 0 10px;">
	                        <table id="file_data_list" class="table table-bordered table-hover"></table>
	                        <div id="file_page_list"></div>
	                    </div>
                    </div>
                 </div>
            </div>
        </div>

    </div>
</body>
<script type="text/javascript" src="${basePath}pages/finance/js/finance_manage.js"></script>
<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
<script type="text/javascript" src="${basePath}pages/finance/invest/js/invest_subject.js"></script>
<script type="text/javascript" src="${basePath}pages/finance/received/js/received.js"></script>
<script type="text/javascript" src="${basePath}pages/finance/received/js/received_manage.js"></script>
</html>