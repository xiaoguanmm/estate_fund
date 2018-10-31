<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>股东付款信息列表</title>
     <%@ include file="../../../common/common.jsp"%>
    <%-- <script type="text/javascript" src="${basePath}pages/trade_manage/js/trade_manage.js"></script> --%>

</head>
<body>
	<input type="hidden" name="viewStockPayment" id="viewStockPayment" value="${viewStockPayment }" /> 
	<input type="hidden" name="corp_id" id="corp_id" value="${corp_id }" /> 
    <div class="bg-gray">
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <span class="title-head_red">${stockholderName }</span>
                    <img src="${basePath}common/img/title-head.png">
                    <span>付款信息</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0)" class="main_btn" id="Pay_details1">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看付款明细</span>
                    </a>
                    <a href="${basePath}/tradeManage/toProjCompanyAddPage?tabType=3&businessPrjPid=${businessPrjInfoId}&operation=${operation}" class="btn_cancel btn_all">
                        <img src="${basePath}common/img/return.png">
                        <span>返回</span>
                    </a>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>应付总金额：</span>
                            <span  class="title_explain_red">${amount.pay_amount}万元</span>
                        </li>
                        <li>
                            <span>实付总金额：</span>
                            <span class="title_explain_red">${amount.receiver_amount}万元</span>
                        </li>
                    </ul>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover" id="data_list">
                        	<div id="page_list"></div>
                        </table>
                    </div>
                    
                    <!--底部刷新表达-完成-->
			        <div id="show_accessory" class="add_Makeuup">
			        	<table id="accessory_list" class="table table-bordered table-hover"></table>
			        	<div id="accessory_page_list"></div>
			        </div>
                    
                </div>
            </div>
        </div>

    </div>

    <!--查看付款明细 -弹窗-开始-->
    <div id="Pay_details" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin-inputlay layui-form">
                        <select id="prjId" name="prjId" lay-verify="" lay-search="" class="form-control">
                            <fund:options code="${Globals.PROJECT_NAME}" value=""/>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin-inputlay layui-form">
                        <select id="businessPrjInfoPid" name="businessPrjInfoPid" lay-verify="" lay-search="" lay-filter="" class="form-control">
                        	<fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE }"></fund:enterprise>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资股东：</label>
                    <div class="fillin-inputlay layui-form">
                        <select id="contributiveId" name="contributiveId" lay-verify="" lay-search="" class="form-control">
                        	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }"></fund:enterprise>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="receiverBankId" id="receiverBankId" class="form-control" lay-verify="" lay-search="" lay-filter="">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="payBankId" id="payBankId" class="form-control" lay-verify="" lay-search="" lay-filter="">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="receiverAccount" id="receiverAccount" class="form-control" lay-verify="" lay-search="" lay-filter="">
	                        <option value="">--请选择--</option>
	                    </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin-inputlay layui-form">
                    	<select name="payAccount" id="payAccount" class="form-control" lay-verify="" lay-search="" lay-filter="">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款金额(万元)：</label>
                    <div class="fillin form-group">
                        <input type="text" name="receiverAmount" id="receiverAmount" value="${payment.receiverAmount}" class="fillin-input form-control">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                    <div class="fillin form-group">
                        <input type="text" name="payAmount" id="payAmount" value="${payment.payAmount}" class="fillin-input form-control">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款时间：</label>
                    <div class="fillin">
                        <input type="text" name="receiverDate" readonly="readonly" id="receiverDate" 
                        	value="<fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd"/>" class="fillin-input form-control">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
                    <div class="fillin">
                        <input type="text" name="payTerm" value="${payment.payTerm}" class="fillin-input form-control">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款时间：</label>
                    <div class="fillin">
                        <input type="text" name="payDate" readonly="readonly" id="payDate" 
                        	value="<fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd"/>" class="fillin-input form-control">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea name="payRemark" class="fillin-input tarea_remarks form-control">${payment.payRemark}</textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">收款备注：</label>
                    <div class="fillin">
                        <textarea name="reveiverRemark" class="fillin-input tarea_remarks form-control">${payment.reveiverRemark}</textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>财务确认状态：</label>
                    <div class="fillin layui_input_block layui-form">
                        <input type="radio" name="financeConfirmStatus" value="1" title="已确认" checked>
                        <input type="radio" name="financeConfirmStatus" value="0" title="未确认" >
                    </div>
                </div>
            </div>
        </div>
        <!-- <div class="main_edge">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            <div class="tabled_datum_940">
                <table id="stockPayment_data_list" class="table table-bordered table-hover"></table>
	            <div id="stockPayment_page_list"></div>
            </div>
        </div> -->
        
    </div>
    <!--查看付款明细-弹窗-结束-->

</body>
	<script type="text/javascript" src="${basePath}pages/trade/js/proj_company_manage/stock_payment_request.js"></script>
    <script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
</html>