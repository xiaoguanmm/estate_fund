<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人业务管理列表</title>
     <%@ include file="../../../common/common.jsp"%>
    <%-- <script type="text/javascript" src="${basePath}pages/trade_manage/js/trade_manage.js"></script> --%>

</head>
<body>
    <div class="bg-gray">
        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text">
                        	<select id="projectInfoPid" name="projectInfoPid" lay-filter="" lay-verify="" lay-search="" class="form-control" >
                            	<option value="">--请选择--</option>
                               	<fund:options code="${Globals.PROJECT_NAME }" />
                            </select>
						</div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>投资主体名称：</span></div>
                        <div class="search-text">
							<select id="investSubjectCorpId" name="investSubjectCorpId" lay-filter="" class="fillin-input form-control" lay-verify="" lay-search>
	                            <option value="">--请选择--</option>
	                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
	                        </select>
						</div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>投资人名称：</span></div>
                        <div class="search-text">
                        	<select id="investorCorpId" name="investorCorpId" lay-filter="" class="fillin-input form-control" lay-verify="" lay-search>
	                            <option value="">--请选择--</option>
	                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
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
                    <span>投资人业务管理列表</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0);" class="main_btn" id="addInvestor">
                        <img src="${basePath}common/img/add.png">
                        <span>新增投资人</span>
                    </a>
                    <a href="javascript:void(0);" class="main_btn" id="modifyInvestor">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改投资人</span>
                    </a>
                    <a href="javascript:void(0);" class="main_btn" id="viewInvestorDetail">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看详情</span>
                    </a>
                    <a href="javascript:void(0);" class="main_btn main_btn_dele" id="delInvestor">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除</span>
                    </a>
                </div>
                <div class="tabled_one">
                    <!--表格-->
					<div class="tabled_two">
                        <table class="table table-bordered table-hover" id="data_list"></table>
                        <div id="page_list"></div> 
                    </div>
                </div>
            </div>
        </div>
        
        <!--新增、修改投资人弹窗-->
        <div class="bg-white add_Makeuup" id="add_Investor_dialog">
        	<form action="${basePath }investorBusinessManage/addInvestor" method="post" name="addInvestorForm" id="addInvestorForm">
        		<input type="hidden" id="investPlanManagePid" name="investPlanManagePid" value="${investPlanManagePid }" />
        		<input type="hidden" id="investSubjectPid" name="investSubjectPid" value="${investSubjectPid }" />
				<input type="hidden" id="prjPid" name="prjPid" value="${projectInfoPid }" />
				<input type="hidden" id="buildingName" name="buildingName" value="${buildName }" />
				<input type="hidden" id="accessType" name="accessType" value="${accessType }" />
				<input type="hidden" id="stockholderCorpPId" name="stockholderCorpPId" value="${stockholderCorpId }" />
				<input type="hidden" id="investSubjectCorpPId" name="investSubjectCorpPId" value="${investSubjectCorpId }" />
				<input type="hidden" id="businessPrjInfoPid" name="businessPrjInfoPid" value="${businessPrjInfoPid }" />
				<input type="hidden" id="stockholderPid" name="stockholderPid" value="${stockholderPid }" />
				<input type="hidden" id="investorPid" name="investorPid" />
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
	                    <label class="user-name product-modify-span"><span class="textmust">*</span>股东名称：</label>
	                    <div class="fillin-inputlay layui-form form-group">
	                        <select id="stockholderCorpId" name="stockholderCorpId" lay-filter="stockholderCorpId" lay-verify="" lay-search="" 
	                                class="form-control" validate-rule="notEmpty">
	                       		<option value="">--请选择--</option>
	                        	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }" value="" ></fund:enterprise>
	                        </select>
	                    </div>
	                </div>
	                <div class="fillbox_inline" >
	                    <label class="user-name product-modify-span"><span class="textmust">*</span>投资主体名称：</label>
	                    <div class="fillin-inputlay layui-form form-group">
	                        <select id="investSubjectCorPid" name="investSubjectCorPid" lay-filter="investSubjectCorPid" lay-verify="" lay-search="" 
	                                class="form-control" validate-rule="notEmpty">
	                       		<option value="">--请选择--</option>
	                        	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }" value="" ></fund:enterprise>
	                        </select>
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
	                    <label class="user-name product-modify-span">预计出资额(万元)：</label>
	                    <div class="fillin">
	                        <input type="text" id="expectContributiveAmount" name="expectContributiveAmount" value="${InvestSubject.expectContributiveAmount }" class="fillin-input">
	                    </div>
	                </div>
	            </div>
	            <div>
	            	<div class="fillbox_inline" >
	                    <label class="user-name product-modify-span"><span class="textmust">*</span>投资人名称：</label>
	                    <div class="fillin-inputlay layui-form form-group">
	                        <select id="investorName" name="investorName" lay-filter="investorName" lay-verify="" lay-search="" 
	                                class="form-control" validate-rule="notEmpty">
	                       		<option value="">--请选择--</option>
	                        	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }" value="${investSubject.pid }" ></fund:enterprise>
	                        </select>
	                    </div>
	                </div>
	                <div class="fillbox_inline">
	                    <label class="user-name product-modify-span">持股比例(%)：</label>
	                    <div class="fillin">
	                        <input type="text" id="holdStockRate" name="holdStockRate" value="" class="fillin-input">
	                    </div>
	                </div>
	            </div>
	            <div>
	                <div class="fillbox_inline" >
	                    <label class="user-name product-modify-span">级别：</label>
	                    <div class="fillin-inputlay layui-form form-group">
	                        <select id="level" name="level" class="form-control" lay-verify="" lay-search>
	                            <option value="1">优先</option>
	                            <option value="2">劣后</option>
	                        </select>
	                    </div>
	                </div>
	                <div class="fillbox_inline">
	                    <label class="user-name product-modify-span">分红方式：</label>
	                    <div class="fillin-inputlay layui-form form-group">
	                    	<select name="dividendType" id="dividendType" class="form-control" lay-verify="" lay-search="" lay-filter="dividendType">
	                         	<fund:options code="${Globals.DIVIDEND_TYPE}" value="${investSubject.dividendType}"/>
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div>
	            	<div class="fillbox_inline">
	                    <label class="user-name product-modify-span">出资期数：</label>
	                    <div class="fillin">
	                        <input type="text" id="term" name="term" value="${investSubject.term }" class="fillin-input">
	                    </div>
	                </div>
	                <div class="fillbox_inline">
	                    <label class="user-name product-modify-span">预计总回款(万元)：</label>
	                    <div class="fillin">
	                        <input type="text" id="investExpectAllReceiverAccount" name="expectAllReceiverAccount" value="${investSubject.expectAllReceiverAccount }" class="fillin-input">
	                    </div>
	                </div>
	            </div>
	            <div>
	            	<div class="fillbox_inline">
	                    <label class="user-name product-modify-span">年化利率(%)：</label>
	                    <div class="fillin">
	                        <input type="text" id="annualizedInterestRate" name="annualizedInterestRate" value="${investSubject.annualizedInterestRate }" class="fillin-input">
	                    </div>
	                </div>
	                <div class="fillbox_inline">
	                    <label class="user-name product-modify-span">预期收益(万元)：</label>
	                    <div class="fillin">
	                        <input type="text" id="expectIncome" name="expectIncome" value="${investSubject.expectIncome }" class="fillin-input" readonly="readonly">
	                    </div>
	                </div>
	            </div>
	            <div>
	            	<div class="fillbox_inline">
	                    <label class="user-name product-modify-span">投资起始日：</label>
	                    <div class="fillin form-group">
	                        <input type="text" id="investStartDate" name="investStartDate"
	                        	value="<fmt:formatDate value='${investSubject.investStartDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input form-control">
	                    </div>
	                </div>
	                <div class="fillbox_inline">
	                    <label class="user-name product-modify-span">预期收益率(%)：</label>
	                    <div class="fillin">
	                        <input type="text" id="expectIncomeRate" name="expectIncomeRate" value="${investSubject.expectIncomeRate }" class="fillin-input" readonly="readonly">
	                    </div>
	                </div>
	            </div>
	            <div class="fillin_bntleft fillbox_inline_0" id="uploadDiv">
	                <div class="fillin">
	                    <div class="layui-upload fillbox-left" style="width: 100%;">
	                        <button type="button" class="layui-btn layui-btn-normal" id="addInvestor_select_file">选择文件</button> 
						    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="addInvestor_selectFileButton">选择文件</button> 
						    <button type="button" class="layui-btn" id="addInvestor_uploadFileButton">开始上传</button>
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
							      <tbody id="addInvestor_fileList"></tbody>
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
	                <table id="investorAccessory_data_list" class="table table-bordered table-hover"></table>
		            <div id="investorAccessory_page_list"></div>
	            </div>
	        </div>
            </form>
        </div>

    </div>

</body>
	<script type="text/javascript" src="${basePath}pages/trade/js/investor_business_manage/investor_business.js"></script>
	<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
</html>