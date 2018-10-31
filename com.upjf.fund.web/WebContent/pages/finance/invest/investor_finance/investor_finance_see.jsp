<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人财务管理详情页</title>
<script type="text/javascript" src="${basePath}pages/finance/invest/js/investor_finance/investor_finance_see.js"></script>
</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
		<!--正文开始-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span >投资人财务详情</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#busine_Informaed">投资基础信息</a></li>
                        <li><a href="#busine_enterprise">企业基本信息</a></li>
                        <li><a href="#busine_Bank">银行账户管理</a></li>
                        <li><a href="#busine_payments">付款信息</a></li>
                        <li><a href="#busine_received">回款信息</a></li>
                    </ul>
                    <div class="tab-content myTab_content">

						<!-------------------------投资基础信息-开始-------------------------------------------------------------->
                        <div class="tab-pane active" id="busine_Informaed">
                            <div class="main_edge">
                                <div class="fillbox-left">
                                    <div>
                                    	<input type="text" hidden="hidden" id="investorPid" value="${investor.investor_id}" />
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.project_name}" class="fillin-input" >
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">楼盘名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.building_name}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目公司：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.prj_corp_name}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">股东：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.stockHolderName}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">投资主体名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.invest_subject_name}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">投资人名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.investor_name}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">是否为汇联上市公司：</label>
                                            <div class="fillin layui_input_block layui-form">
                                                <input type="radio" name="huilian" value="1" title="是"  <c:if test="${investor.is_huilian_corp == '1'}">checked</c:if> />
                                                <input type="radio" name="huilian" value="0" title="否"  <c:if test="${investor.is_huilian_corp != '1'}">checked</c:if> />
                                            </div>
                                        </div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">级别：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.level}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计出资额(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.expect_contributive_amount}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">出资期数(月)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.term}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">持股比例(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.hold_stock_rate}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">年化利率(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.annualized_interest_rate}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">分红方式：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.dividend_type_name}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">投资起始日：</label>
                                            <div class="fillin">
                                                <input type="text" value="<fmt:formatDate value='${investor.invest_start_date}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计总回款(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.expect_all_receiver_account}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期收益率(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.expect_income_rate}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期收益(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.expect_income}" class="fillin-input">
                                            </div>
                                        </div>
                                         <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">出资类别：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.contributive_type_name}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际出资额(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.total_realPay_amount}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际收益(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.total_return_amount}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际收益率：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investor.real_return_rate}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline" >
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>附件材料</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover" id="investFile_tableId">
                                        <thead>
	                                        <tr>
	                                            <th>文件名</th>
	                                            <th>上传时间</th>
	                                            <th>上传人</th>
	                                            <th>操作</th>
	                                        </tr>
                                        </thead>
                                        <tbody id="investFile_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="btn_edge btn_edge_left">
                                <a href="javascript:history.go(-1);" class="btn_cancel btn_all">
                                    <img src="${basePath}/common/img/return.png">
                                    <span>返回</span>
                                </a>
                            </div>

                        </div>
						<!------------------------投资基础信息-结束--------------------------------------------------------------->

						<!-------------------------企业基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_enterprise">
                            <div class="main_edge">
                                <div class="fillbox-left" >
                                    <div>
                                    	<input type="text" hidden="hidden" id="corBasePid" value="${corporationInfo.pid}">
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>企业名称：</label>
                                            <div class="fillin">
                                                <input type="text" id="corporationNameId" value="${corporationInfo.name}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">企业简称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.simpleName}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">组织机构代码：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.orgCodeCert}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">营业执照号码：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.businessLicenceCode}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">所有制性质：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.corpQualityName}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">法人代表：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.legalRepresentative }" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">注册资金万元：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.registerCapital }" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">客户经理：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.customerManager }" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">联系电话：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.phone }" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>公司资料扫描件</span>
                                </div>
                                <c:forEach items="${corDataInfoList}" var="corDataInfo">
                                	<div class="datum">
	                                    <div class="datum_main">
	                                        <span>${corDataInfo.valueDes}</span>
	                                        <input type="button" id="${corDataInfo.pid}_priview" onclick="priviewCorFile('${corDataInfo.pid}_Img')" value="预览" class="datum_btn datum_imges datum_btnactive" />
	                                    </div>
                                   		<div class="datum_img" style="margin-left: 0px" >
                                           	<img id="${corDataInfo.pid}_Img" style="width: 266px;height: 150px" title="" class="dataInfo_img" src="${basePath}/common/img/datum.png">
                                   		</div>
                                	</div>
                                </c:forEach>
                            </div>
                            <div class="btn_edge btn_edge_left">
                                <a href="javascript:history.go(-1);" class="btn_cancel btn_all">
                                    <img src="${basePath}/common/img/return.png">
                                    <span>返回</span>
                                </a>
                            </div>


                        </div>
<!-------------------------企业基本信息-开始-------------------------------------------------------------->

<!-------------------------银行账户管理-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_Bank">
                            <div class="main_edge">
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover" id="corBank_tableId">
                                        <thead>
                                        <tr>
                                            <th>企业名称</th>
                                            <th>开户名</th>
                                            <th>开户行</th>
                                            <th>开户账号</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="corBank_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                                <div class="btn_edge btn_edge_left">
                                    <a href="javascript:history.go(-1);" class="btn_cancel btn_all">
                                        <img src="${basePath}/common/img/return.png">
                                        <span>返回</span>
                                    </a>
                                </div>
                            </div>

                        </div>
<!------------------------银行账户管理-结束--------------------------------------------------------------->

<!-------------------------付款信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_payments">
                            <div class="top_btn top_btn_40">
                                <a href="javascript:void(0)" class="main_btn" id="investor_Finance_Pay1">
                                    <img src="${basePath}/common/img/looking.png">
                                    <span>查看付款明细</span>
                                </a>
                            </div>
                            <div class="title_explain">
                                <ul>
                                    <li>
                                        <span>应付总金额：</span>
                                        <span  class="title_explain_red" id="prePayAmountId">暂无数据</span>
                                    </li>
                                    <li>
                                        <span>实付总金额：</span>
                                        <span class="title_explain_red" id="actualPayAmountId">暂无数据</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="tabled_one">

                                <!--表格-->
                                <div class="tabled_two">
                                    <table class="table table-bordered table-hover" id="investorFinanceDetail_tableId">
                                        <thead>
                                        <tr>
                                            <th>选择</th>
                                            <th>付款时间</th>
                                            <th>出资公司</th>
                                            <th>付款账号</th>
                                            <th>付款期数</th>
                                            <th>应付金额(万元)</th>
                                            <th>实付金额(万元)</th>
                                            <th>收款公司</th>
                                            <th>收款账号</th>
                                            <th>录入时间</th>
                                            <th>财务录入操作者</th>
                                        </tr>
                                        </thead>
                                        <tbody id="investorFinanceDetail_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!-------------------------付款信息-开始-------------------------------------------------------------->

<!-------------------------回款信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_received">
                            <div class="top_btn top_btn_40">
                                <a href="javascript:void(0)" class="main_btn" id="investor_Finance_reture1">
                                    <img src="${basePath}/common/img/looking.png">
                                    <span>查看回款明细</span>
                                </a>
                            </div>
                            <div class="title_explain">
                                <ul>
                                    <li>
                                        <span>回款本金合计：</span>
                                        <span  class="title_explain_red" id="investorReceive_ReceiverAmount">暂无数据</span>
                                    </li>
                                    <li>
                                        <span>回款利润合计：</span>
                                        <span class="title_explain_red" id="investorReceive_totalProfit">暂无数据</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="tabled_one">

                                <!--表格-->
                                <div class="tabled_two">
                                    <table class="table table-bordered table-hover" id="investorReceive_tableId">
                                        <thead>
                                        <tr>
                                            <th>选择</th>
                                            <th>回款时间</th>
                                            <th>回款公司</th>
                                            <th>回款本金(万元)</th>
                                            <th>回款利润(万元)</th>
                                            <th>收款账号</th>
                                            <th>付款公司</th>
                                            <th>付款账号</th>
                                            <th>录入时间</th>
                                            <th>财务录入操作者</th>
                                        </tr>
                                        </thead>
                                        <tbody id="investorReceive_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!-------------------------回款信息-开始-------------------------------------------------------------->

                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--查看付款明细 -弹窗-开始-->
    <div id="investor_Finance_Pay" class="add_Makeuup">
        <div>
        	<input type="text" id="investorPay_investorPayPid" value="" hidden="hidden" />
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_projectName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_receiverName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资公司：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_contributiveName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_receiverBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_payBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_receiverAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_payAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_payTerm" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_payAmount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款时间：</label>
                    <div class="fillin">
                       <input type="text" id="investorPay_payDate" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" id="investorPay_payRemark" disabled="disabled"></textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">财务录入操作者：</label>
                    <div class="fillin">
                        <input type="text" id="investorPay_realUserName" disabled="disabled" value="" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">录入时间：</label>
                    <div class="fillin">
                        <input type="text" id="investorPay_investorOpDate" value="" disabled="disabled" class="fillin-input notoptional">
                    </div>
                </div>
            </div>
        </div>
        <div class="main_edge">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            <!--表格-->
            <div class="tabled_datum_940">
                <table class="table table-bordered table-hover" id = "eachOneOfInvestorFile_tableId">
                    <thead>
	                    <tr>
	                        <th>文件名</th>
	                        <th>上传时间</th>
	                        <th>上传人</th>
	                        <th>操作</th>
	                    </tr>
                    </thead>
                    <tbody id="eachOneOfInvestorFile_tbodyId">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--查看付款明细-弹窗-结束-->

    <!--查看回款明细 -弹窗-开始-->
    <div id="investor_Finance_reture" class="add_Makeuup">
        <div>
        	<input type="text" id="investorReturnDetail_investorReturnPid" hidden="hidden" value="" >
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_projectName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_receiverCompany" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款公司：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_contributiveCpmpany" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_receiverBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_payBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_receiverAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_payAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款本金(万元)：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_receiverAmount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款状态：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_receivedStatus" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款利润(万元)：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_profit" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款时间：</label>
                    <div class="fillin">
                       <input type="text" id="investorReturnDetail_receiverDate" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">回款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" disabled="disabled" id="investorReturnDetail_reveiverRemark"></textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款录入操作者：</label>
                    <div class="fillin">
                        <input type="text" id="investorReturnDetail_realUserName" disabled="disabled" value="" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款录入时间：</label>
                    <div class="fillin">
                        <input type="text" id="investorReturnDetail_createDate" value="" disabled="disabled" class="fillin-input notoptional">
                    </div>
                </div>
            </div>
        </div>
        <div class="main_edge">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            <!--表格-->
            <div class="tabled_datum_940">
                <table class="table table-bordered table-hover" id="investorReturnDetail_tableId">
                    <thead>
                    <tr>
                        <th>文件名</th>
                        <th>上传时间</th>
                        <th>上传人</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="investorReturnDetail_tbodyId">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--查看回款明细-弹窗-结束-->

	<!--------------------------------------------查看企业银行账户信息弹窗-开始-------------------------------------------------------------->
		<div id="see_bankInfo" class="add_Makeuup">
			<div class="main_edge" >
	             <div class="fillbox-left">
	                 <div>
	                     <div class="fillbox_inline">
	                         <label class="user-name bank-modify-lable"><span class="textmust">*</span>企业名称：</label>
	                         <div class="fillin fillin_text1">
	                             <input id="see_corporationName"  type="text" value="" class="fillin-input" readonly="readonly" >
	                         </div>
	                     </div>
	                     <div class="fillbox_inline">
	                         <label class="user-name bank-modify-lable">开户名：</label>
	                         <div class="fillin">
	                             <input id="see_accountName" name="accountName" type="text" value="" readonly="readonly" class="fillin-input">
	                         </div>
	                     </div>
	                 </div>
	                 <div>
	                     <div class="fillbox_inline">
	                         <label class="user-name bank-modify-lable">开户行：</label>
	                         <div class="fillin-inputlay layui-form">
	               			 <select id="see_bankSelectId" lay-filter="seeForm_bankFilter" lay-verify="" lay-search disabled="true">
	                  			<option value="" selected>---请选择---</option>
	                  				<c:forEach items="${bankList}"  var="bank">
	                  					<option value="${bank.pid}">${bank.valueDes}</option>
	                  				</c:forEach>
	               			 </select>
	    				 </div>
	                     </div>
	                     <div class="fillbox_inline">
	                         <label class="user-name bank-modify-lable">开户账号：</label>
	                         <div class="fillin">
	                             <input id="see_account" name="account" type="text" value="" readonly="readonly" class="fillin-input">
	                         </div>
	                     </div>
	                 </div>
	                 <div>
	                     <div class="fillbox_inline">
	                         <label class="user-name bank-modify-lable">备注：</label>
	                         <div class="fillin">
	                             <textarea id="seeRemark" name="remark" readonly="readonly" class="fillin-input bank_modify_tarea_remarks"></textarea>
	                         </div>
	                     </div>
	                 </div>
	             </div>
         </div>
    </div>
	<!--------------------------------------------查看企业银行账户信息弹窗-结束-------------------------------------------------------------->
</body>
</html>