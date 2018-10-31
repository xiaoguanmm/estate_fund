<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增资管计划</title>
    <%@ include file="../../../../common/common.jsp"%>
    <link href="${basePath}pages/finance/invest/css/invest.css" rel="stylesheet">
    

</head>
<body>
	
	<input type="hidden" name="investPlanManageOperation" id="investPlanManageOperation" value="${investPlanManageOperation }" /> 
    <div class="bg-gray">
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <c:if test="${investPlanManageOperation eq 'add'}">
                    	<span >新增资管计划</span>
                    </c:if>
                    <c:if test="${investPlanManageOperation eq 'modify'}">
                    	<span >修改资管计划</span>
                    </c:if>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                    <div class="myTab_content_one">
                        <div class="title_bggray">
                            <span>资管计划基础信息</span>
                        </div>
                        <div class="tab-pane" id="schedule">
                        	<div>
                        		<form id="investPlanManageForm" action="${basePath}investPlanManage/addInvestPlanManage" method="post">
                        			<input type="hidden" id="investPlanManagePid" name="investPlanManagePid" value="${investPlanManage.pid }" />
	                            <div class="main_edge">
	                                <div class="fillbox-left">
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
	                                            <div class="fillin-inputlay layui-form form-group">
	                                                <select id="projectInfoPid" name="projectInfoPid" lay-filter="projectInfoPid" lay-verify="" lay-search="" class="form-control" validate-rule="notEmpty">
						                            	<option value="">--请选择--</option>
						                               	<fund:options code="${Globals.PROJECT_NAME }" value="${investPlanManage.prjId }" />
						                            </select>
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目公司：</label>
	                                            <div class="fillin-inputlay layui-form form-group">
	                                                <select id="businessPrjInfoPid" name="businessPrjInfoPid" lay-filter="businessPrjInfoPid" lay-verify="" lay-search="" 
	                                                	class="form-control" validate-rule="notEmpty">
						                        		<option value="">--请选择--</option>
							                        	<fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE }" value="${investPlanManage.businessPrjInfoId }" ></fund:enterprise>
							                        </select>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>股东：</label>
	                                            <div class="fillin-inputlay layui-form form-group">
	                                                <select id="stockholderInfoPid" name="stockholderInfoPid" lay-filter="stockholderInfoPid" class="form-control" lay-verify="" 
	                                                	validate-rule="notEmpty" lay-search>
							                            <option value="">--请选择--</option>
							                            <fund:enterprise type="${Globals.STOCK_TYPE}" value="${investPlanManage.stockholderId }" ></fund:enterprise>
							                        </select>
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>投资起始日：</label>
	                                            <div class="fillin form-group">
			                                        <input type="text" id="investStartDate" name="investStartDate" validate-rule="notEmpty"
			                                        	value="<fmt:formatDate value='${investPlanManage.investStartDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input form-control" />
			                                    </div>
			                                    
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>投资方式：</label>
	                                            <div class="fillin-inputlay layui-form form-group">
	                                                <select id="investType" name="investType" value="${investPlanManage.investType }" lay-filter="investType" validate-rule="notEmpty"
	                                                	class="form-control" lay-verify="" lay-search>
							                            <option value="">--请选择--</option>
							                            <option value="1">股权投资</option>
							                            <option value="2">债权投资</option>
							                            <option value="3">股权+债券投资</option>
							                            <option value="4">其它</option>
							                            
							                        </select>
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>期限(月)：</label>
	                                            <div class="fillin form-group">
	                                                <input type="text" id="term" name="term" validate-rule="notEmpty" value="${investPlanManage.term }" class="fillin-input form-control">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>预计出资规模（万元）：</label>
	                                            <div class="fillin form-group">
	                                                <input type="text" id="expectInvestAmount" name="expectInvestAmount" value="${investPlanManage.expectInvestAmount }"
	                                                	validate-rule="notEmpty|rangeNumberBits[14-2]" class="fillin-input form-control">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>预计总回款（万元）：</label>
	                                            <div class="fillin form-group">
	                                                <input type="text" id="expectAllReceiverAccount" name="expectAllReceiverAccount" value="${investPlanManage.expectAllReceiverAccount }" 
	                                                	validate-rule="notEmpty|rangeNumberBits[14-2]" class="fillin-input form-control">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">实际出资金额(万元)：</label>
	                                            <div class="fillin">
	                                                <input type="text" id="realityInvestAmount" name="realityInvestAmount" value="${investPlanManage.realityInvestAmount }" 
	                                                	readonly="readonly" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">已收本金(万元)：</label>
	                                            <div class="fillin">
	                                                <input type="text" id="receiveredPrincipal" name="receiveredPrincipal" value="${investPlanManage.receiveredPrincipal }"
	                                                	readonly="readonly" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">已收回款(万元)：</label>
	                                            <div class="fillin">
	                                                <input type="text" id="receiveredPayback" name="receiveredPayback" value="${investPlanManage.receiveredPayback }" 
	                                                	readonly="readonly" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">客户经理：</label>
	                                            <div class="fillin">
	                                                <input type="text" id="customerManager" name="customerManager" value="${investPlanManage.customerManager }" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">联系电话：</label>
	                                            <div class="fillin">
	                                                <input type="text" id="phone" name="phone" value="${investPlanManage.phone }" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div id="returnedProfitDiv" class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>应回利润(万元)：</label>
	                                            <div class="fillin form-group">
	                                                <input type="text" id="returnedProfit" name="returnedProfit" validate-rule="notEmpty|rangeNumberBits[14-2]" lay-filter="returnedProfit"
	                                                	value="${investPlanManage.returnedProfit }" class="fillin-input form-control">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <c:if test="${investPlanManageOperation eq 'add' or investPlanManageOperation eq 'modify'}">
		                            <div class="btn_edge btn_edge_left">
		                                <a href="${basePath}/investPlanManage/toInvestPlanManageIndex" class="btn_cancel btn_all">
		                                    <img src="${basePath}common/img/cancel.png">
		                                    <span>取消</span>
		                                </a>
		                                <button type="submit" class="btn_keep btn_all">
		                                    <img src="${basePath}common/img/keep.png">
		                                    <span>保存</span>
		                                </button>
		                            </div>
	                            </c:if>
	                            </form>
							</div>

                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>投资主体管理</span>
                                </div>
                                <div class="top_btn top_btn_40">
                                    <a href="javascript:void(0);" class="main_btn" id="add_Investment1">
                                        <img src="${basePath}common/img/add.png">
                                        <span>新增投资主体</span>
                                    </a>
                                    <a href="javascript:void(0);" class="main_btn" id="modify_Investment1">
                                        <img src="${basePath}common/img/chance.png">
                                        <span>修改投资主体</span>
                                    </a>
                                    <a href="javascript:void(0);" class="main_btn" id="viewInvestSubjectPayment">
                                        <img src="${basePath}common/img/looking.png">
                                        <span>查看付款信息</span>
                                    </a>
                                    <a href="javascript:void(0);" class="main_btn" id="investorsInfoManage">
                                        <img src="${basePath}common/img/informa.png">
                                        <span>投资人信息管理</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn main_btn_dele" id="delInvestSubject">
                                        <img src="${basePath}common/img/delete.png">
                                        <span>删除</span>
                                    </a>
                                </div>
                                <!--表格-->
                                <div class="tabled_two tabled_margin_20">
                                    <table class="table table-bordered table-hover" id="data_list"></table>
                                    <div id="page_list"></div>
                                </div>

                            </div>

                        </div>


                    </div>
            </div>
        </div>
    </div>

    <!--新增/修改投资主体 -弹窗-开始-->
    <div id="add_Investment" class="add_Makeuup">
    	<form id="addInvestSubjectForm" action="${basePath}investPlanManage/addInvestSubject" method="post">
    		<input type="hidden" id="subjectFormInvestPlanManagePid" name="investPlanManagePid" value="" />
    		<input type="hidden" id="investSubjectPid" name="investSubjectPid" />
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="prjId" id="prjId" validate-rule="notEmpty" class="fillin-input form-control" lay-verify="" lay-search="" lay-filter="prjId">
                        	<option value="">--请选择--</option>
                            <fund:options code="${Globals.PROJECT_NAME}" value="${InvestSubject.prjId}"/>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">楼盘名称：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <input type="text" id="buildName" name="buildName" value="" class="fillin-input" readonly="readonly">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">出资类别：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="contributiveType" id="contributiveType" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="contributiveType">
                        	<option value="">--请选择--</option>
                         	<fund:options code="${Globals.CONTRIBUTIVE_TYPE}" value="${InvestSubject.contributiveType}"/>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目公司：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select id="investBusinessPrjInfoPid" name="businessPrjInfoPid" lay-filter="investBusinessPrjInfoPid" lay-verify="" lay-search="" 
                                class="form-control" validate-rule="notEmpty">
                       		<option value="">--请选择--</option>
                        	<fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE }" value="${investPlanManage.businessPrjInfoId }" ></fund:enterprise>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">预计出资额(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="expectContributiveAmount" name="expectContributiveAmount" value="${InvestSubject.expectContributiveAmount }" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline" >
                    <label class="user-name product-modify-span"><span class="textmust">*</span>投资主体名称：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select id="investSubjectName" name="investSubjectName" lay-filter="investSubjectName" lay-verify="" lay-search="" 
                                class="form-control" validate-rule="notEmpty">
                       		<option value="">--请选择--</option>
                        	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }" value="${investSubject.pid }" ></fund:enterprise>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" id="holdStockRate" name="holdStockRate" value="" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline" >
                    <label class="user-name product-modify-span">是否为汇联上市公司：</label>
                    <div class="fillin layui_input_block layui-form">
                        <input type="radio" name="isHuilianCorp" value="1" title="是" checked>
                        <input type="radio" name="isHuilianCorp" value="0" title="否" >
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">分红方式：</label>
                    <div class="fillin-inputlay layui-form form-group">
                    	<select name="dividendType" id="dividendType" class="form-control" lay-verify="" lay-search="" lay-filter="dividendType">
                         	<fund:options code="${Globals.DIVIDEND_TYPE}" value="${investSubject.dividendType}"/>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline" >
                    <label class="user-name product-modify-span">级别：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select id="level" name="level" class="form-control" lay-verify="" lay-search>
                            <option value="1">优先</option>
                            <option value="2">劣后</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">预计总回款(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="investExpectAllReceiverAccount" name="expectAllReceiverAccount" value="${investSubject.expectAllReceiverAccount }" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">出资期数(月)：</label>
                    <div class="fillin">
                        <input type="text" id="investSubjectTerm" name="term" value="${investSubject.term }" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">预期收益(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="expectIncome" name="expectIncome" value="${investSubject.expectIncome }" class="fillin-input" readonly="readonly">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">年化利率(%)：</label>
                    <div class="fillin">
                        <input type="text" id="annualizedInterestRate" name="annualizedInterestRate" value="${investSubject.annualizedInterestRate }" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">预期收益率(%)：</label>
                    <div class="fillin">
                        <input type="text" id="expectIncomeRate" name="expectIncomeRate" value="${investSubject.expectIncomeRate }" class="fillin-input" readonly="readonly">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">投资起始日：</label>
                    <div class="fillin form-group">
                        <input type="text" id="investSubjectStartDate" name="investStartDate"
                        	value="<fmt:formatDate value='${investSubject.investStartDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input form-control">
                    </div>
                    
                </div>
            </div>
            <div class="fillin_bntleft fillbox_inline_0" id="uploadDiv">
                <div class="fillin">
                    <div class="layui-upload fillbox-left" style="width: 100%;">
                        <button type="button" class="layui-btn layui-btn-normal" id="addInvestSubject_select_file">选择文件</button> 
					    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="addInvestSubject_selectFileButton">选择文件</button> 
					    <button type="button" class="layui-btn" id="addInvestSubject_uploadFileButton">开始上传</button>
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
						      <tbody id="addInvestSubject_fileList"></tbody>
						    </table>
					    </div>
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
                <table id="investSubjectAccessory_data_list" class="table table-bordered table-hover"></table>
	            <div id="investSubjectAccessory_page_list"></div>
            </div>
        </div>
        </form>
    </div>
    <!--新增/修改投资主体-弹窗-结束-->




</body>
<script type="text/javascript" src="${basePath}pages/trade/js/invest_plan_manage/invest_plan_manage_add.js"></script>
<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
</html>