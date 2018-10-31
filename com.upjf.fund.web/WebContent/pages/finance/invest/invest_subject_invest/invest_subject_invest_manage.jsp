<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资主体出资管理</title>
    <%@ include file="../../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}pages/finance/js/finance_manage.js"></script>
	<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
	<script type="text/javascript" src="${basePath}pages/finance/invest/js/invest.js"></script>
    <script type="text/javascript">
		$(function(){
			var url = basePath+"finance/queryInvestSubjectInvestList?paymentType=2"
			var colNames = ["付款时间","项目名称","出资主体","付款账号","付款期数",
			                "付款金额(万元)","收款公司","收款账号",
			                "付款备注","付款凭证","财务录入操作者","录入时间","pid","project_status"];
			var colModel = [
			                {name:"pay_date",index:"pay_date",sortable:false},
			                {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
			                	var prjId = "'"+rowObject.prj_id+"'";
			                	if(!cellvalue){
			                		cellvalue="";
			                	}
						    	return '<a href="javascritp:;" class="table_bnt" onclick="showProjectInfo('+prjId+');return false;">'+cellvalue+'</a>';
						    }},
			                {name: "contributive_name",index: "contributive_name", sortable :false},
			                {name: "pay_account",index: "pay_account", sortable :false},
			                {name: "pay_term",index: "pay_term", sortable :false},
			                {name: "pay_amount",index: "pay_amount", sortable :false},
			                {name: "receiver_name",index: "receiver_name",sortable :false},
						    {name: "receiver_account",index: "receiver_account",sortable :false},
						    {name: "pay_remark",index: "pay_remark", sortable :false},
						    {name: "show_accessory",index: "show_accessory", sortable :false,formatter:function(cellvalue, options, rowObject){
						    	var url = "'"+basePath+"finance/queryInvestSubjectAccessoryList"+"'";
						    	var paymentId = "'"+rowObject.pid+"'";
						    	return '<a href="javascritp:;" class="table_bnt show_accessory" onclick="showAccessoryList('+url+','+paymentId+')">查看</a>';
						    }},
						    {name: "investor_op_name",index: "investor_op_name", sortable :false},
						    {name: "investor_op_date",index: "investor_op_date", sortable :false},
						    {name: "pid",index:"pid",sortable :false,hidden:true},
						    {name: "project_status",index:"project_status",sortable :false,hidden:true}
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
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                            <select name="prjId" lay-verify="" lay-search="">
                                <option value="">--请选择--</option>
                                <fund:options code="${Globals.PROJECT_NAME}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>付款公司：</span></div>
                       <div class="search-text layui-form">
                                <select name="contributiveId" lay-verify="" lay-search="">
                                    <option value="">--请选择--</option>
                                    <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                                </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>收款公司：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverId" lay-verify="" lay-search="">
                                <option value="">--请选择--</option>
                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>付款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" name="payDateStart" class="layui-input" id="payDateStart" >
                                </li>
                                <li>
                                    <img src="${basePath}common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" name="payDateEnd" class="layui-input" id="payDateEnd" >
                                </li>
                            </ul>
                        </div>
                    </div>
<%--                     <div class="search-box_div">
                        <div class="search-span"><span>收款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" name="receiverDateStart" class="layui-input" id="receiverDateStart" >
                                </li>
                                <li>
                                    <img src="${basePath}common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" name="receiverDateEnd" class="layui-input" id="receiverDateEnd" >
                                </li>
                            </ul>
                        </div>
                    </div> --%>
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
                    <span>投资主体出资管理</span>
                </div>
                <div class="top_btn">
	                <input type="hidden" id="addOperationUrl" value="${basePath}finance/toAddInvestSubjectInvest?operation=add">
	                <input type="hidden" id="modifiyOperationUrl" value="${basePath}finance/toAddInvestSubjectInvest?operation=modify">
	                <input type="hidden" id="viewOperationUrl" value="${basePath}finance/toAddInvestSubjectInvest?operation=view">
	                <input type="hidden" id="deleteOperationUrl" value="${basePath}finance/delInvestSubjectPaymentRecord?operation=delete">
                    <a href="javascript:;" id="add_invest" class="main_btn">
                        <img src="${basePath}common/img/add.png">
                        <span>新增投资主体出资</span>
                    </a>
                    <a href="javascript:;" id="modify_invest" class="main_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改投资主体出资</span>
                    </a>
                    <a href="javascript:;" id="view_invest" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看出资明细</span>
                    </a>
                    <a href="javascript:;" id="delete_invest" class="main_btn">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除出资请求</span>
                    </a>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>付款总金额：</span>
                            <span class="title_explain_red"><span class="title_explain_red" id="pay_amount">${amount.pay_amount}</span>万元</span>
                        </li>
                        <%-- <li>
                            <span>收款总金额：</span>
                            <span class="title_explain_red"><span class="title_explain_red" id="receiver_amount">${amount.receiver_amount}</span>万元</span>
                        </li> --%>
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
            </div>
        </div>

    </div>

</body>
<script type="text/javascript" src="${basePath}pages/finance/invest/js/invest_subject.js"></script>
</html>