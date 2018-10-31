<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资主体详情页</title>
    <%@ include file="../../../common/common.jsp"%>
    <!-- 引用 财务管理--- 投资主体财务管理see页面js -->
	<script type="text/javascript" src="${basePath}pages/finance/invest/js/invest_subject_finance/subject_finance_see.js"></script>
	<!-- copy 项目基本信息、项目合同  页面js -->
	<script type="text/javascript" src="${basePath}pages/trade/js/invest_subject_business_manage/invest_subject_business_see.js"></script>
</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
		<input id="type" type="text" hidden="hidden" value="${type}">
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span >投资主体详情页</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#busine_Informaed">投资基础信息</a></li>
                        <li><a href="#busine_base_Informa">项目基本信息</a></li>
                        <li><a href="#busine_bargain">项目合同</a></li>
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
                                    	<input type="text" hidden="hidden" id="investSubjectPid" value="${investSubject.subjectPid}" />
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.projectName}" class="fillin-input" >
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">楼盘名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.buildName}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">股东：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.stockHolderName}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目公司：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.projectCompany}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">出资类别：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.contributiveTypeName}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">投资主体名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.corporationName}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">是否为汇联上市公司：</label>
                                            <div class="fillin layui_input_block layui-form">
                                                <input type="radio" name="huilian" value="1" title="是" disabled="disabled" <c:if test="${investSubject.isHuilianCorp == '1'}">checked</c:if> />
                                                <input type="radio" name="huilian" value="0" title="否" disabled="disabled" <c:if test="${investSubject.isHuilianCorp == '0'}">checked</c:if> />
                                            </div>
                                        </div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">级别：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.level}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计出资额(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.expectContributiveAmount}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">出资期数(月)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.payTerm}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">持股比例(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.holdStockRate}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">年化利率(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.annualizedInterestRate}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">分红方式：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.dividendTypeName}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">投资起始日：</label>
                                            <div class="fillin">
                                                <input type="text" value="<fmt:formatDate value='${investSubject.investStartDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计总回款(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.expectAllReceiverAccount}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期收益率(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.expectIncomeRate}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期收益(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.expectIncome}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际出资额(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.totalRealPayAmount}" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际收益(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.hasReceiveProfit}" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际收益率：</label>
                                            <div class="fillin">
                                                <input type="text" value="${investSubject.realYieldRate}" class="fillin-input">
                                            </div>
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
                                    <table class="table table-bordered table-hover" id="subjectFile_tableId">
                                        <thead>
	                                        <tr>
	                                            <th>文件名</th>
	                                            <th>上传时间</th>
	                                            <th>上传人</th>
	                                            <th>操作</th>
	                                        </tr>
                                        </thead>
                                        <tbody id="subjectFile_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <%-- <div class="btn_edge btn_edge_left">
                                <a href="javascript:history.go(-1);" class="btn_cancel btn_all">
                                    <img src="${basePath}/common/img/return.png">
                                    <span>返回</span>
                                </a>
                            </div> --%>

                        </div>
<!------------------------投资基础信息-结束--------------------------------------------------------------->

<!-------------------------项目基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_base_Informa">
								<form id="projectBaseForm">
									<input type="text" hidden="hidden" id="pid" name="pid" value="${projectInfo.pid}">
									<input type="text" hidden="hidden" id="projectCode" name="projectCode" value="${projectInfo.projectCode}">
									<input type="text" hidden="hidden" id="businessPrjInfoId" name="businessPrjInfoId" value="${projectInfo.businessPrjInfoId}">
									<input type="text" hidden="hidden" id="developersId" name="developersId" value="${projectInfo.developersId}">
									<input type="text" hidden="hidden" id="projectProvice" name="projectProvice" value="${projectInfo.projectProvice}">
									<input type="text" hidden="hidden" id="projectCity" name="projectCity" value="${projectInfo.projectCity}">
									<input type="text" hidden="hidden" id="projectRegion" name="projectRegion" value="${projectInfo.projectRegion}">
									<input type="text" hidden="hidden" id="projectCategory" name="projectCategory" value="${projectInfo.projectCategory}">
									<input type="text" hidden="hidden" id="landGetWay" name="landGetWay" value="${projectInfo.landGetWay}">
		                            <div class="main_edge">
		                                <div class="fillbox-left" >
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
		                                            <div class="fillin">
		                                                <input id="projectName" name="projectName" type="text" value="${projectInfo.projectName}" class="fillin-input">
		                                                <span id="projectNameWarn" class="warn_span">*必填</span>
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目公司：</label>
		                                            <div class="fillin-inputlay layui-form">
		                                                  <select  id="busPrjInfoSelect_Id" lay-filter="busPrjInfoSelectId" lay-verify="" lay-search>
		                                                      <option value="">---请选择---</option>
		                                                      <c:forEach items="${busProInfoList}" var="busProInfo">
		                                                      		<option value="${busProInfo.pid}">${busProInfo.prjCorpName}</option>
		                                                      </c:forEach>
		                                                  </select>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">楼盘名称：</label>
		                                            <div class="fillin">
		                                                <input id="buildingName" name="buildingName" type="text" value="${projectInfo.buildingName}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">开 发 商：</label>
		                                            <div class="fillin-inputlay layui-form">
		                                                  <select id="developersSelect_Id" lay-filter="developersSelectId" lay-verify="" lay-search>
		                                                      <option value="">---请选择---</option>
		                                                      <c:forEach items="${corInfoList}" var="corInfo">
		                                                      		<option value="${corInfo.pid}">${corInfo.name}</option>
		                                                      </c:forEach>
		                                                  </select>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div id="projectAddress" class="fillbox_inline">
		                                            <label class="user-name product-modify-span">项目地址(省-市-区)：</label>
		                                            <div class="search-text ">
		                                                <div class="layui-form"  style="display:inline-block;width:150px;">
		                                                    <select id="provinceSelectId" lay-filter="provinceFilter" lay-verify="" lay-search>
		                                                        <option value="">---请选择---</option>
		                                                        <c:forEach items="${provinceList}" var="province">
		                                                        	<option value="${province.areaCode}">${province.areaName}</option>
		                                                        </c:forEach>
		                                                    </select>
		                                                </div>
		                                                <div class="layui-form"  style="display:inline-block;width:150px;">
		                                                    <select id="citySelectId"  lay-filter="cityFilter" lay-verify="" lay-search>
		                                                        <option value="">---请选择---</option>
		                                                    </select>
		                                                </div>
		                                                <div class="layui-form"  style="display:inline-block;width:150px;">
		                                                    <select id="regionSelectId" lay-filter="regionFilter"  lay-verify="" lay-search>
		                                                        <option value="">---请选择---</option>
		                                                    </select>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline">
		                                            <label class="user-name product-modify-span">详细地址：</label>
		                                            <div class="fillin">
		                                                <input id="projectDetail" name="projectDetail" type="text" value="${projectInfo.projectDetail}" class="fillin-input" style="width:457px">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目种类：</label>
		                                            <div class="fillin-inputlay layui-form">
		                                                  <select id="proCateGorySelectId" lay-filter="proCateGoryFilter" lay-verify="" lay-search >
		                                                      <option value="">---请选择---</option>
		                                                      <c:forEach items="${proCateGoryList}" var="proCateGory">
		                                                      		<option value="${proCateGory.pid}">${proCateGory.valueDes}</option>
		                                                      </c:forEach>
		                                                  </select>
		                                            </div>
		                                            <span id="projectCategoryWarn" class="warn_span">*必填</span>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span"><span class="textmust">*</span>土地获取方式：</label>
		                                            <div class="fillin-inputlay layui-form">
		                                                  <select id="landGetWaySelectId" lay-filter="landGetWayFilter" lay-verify="" lay-search>
		                                                      <option value="">-请选择-</option>
		                                                      <c:forEach items="${landGetWayList}" var="landGetWay">
		                                                      		<option value="${landGetWay.pid}">${landGetWay.valueDes}</option>
		                                                      </c:forEach>
		                                                  </select>
		                                            </div>
		                                            <span id="landGetWayWarn" class="warn_span">*必填</span>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目用地性质：</label>
		                                            <div class="fillin">
		                                                <input id="projectLandQuality" name="projectLandQuality"  type="text" value="${projectInfo.projectLandQuality}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目占地面积(㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="projectOccupationArea" name="projectOccupationArea" type="text" value="${projectInfo.projectOccupationArea}" class="fillin-input">
		                                                <span id="projectOccupationAreaWarn" class="warn_span">*必填</span>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目建设用地面积(㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="projectUserArea" name="projectUserArea" type="text" value="${projectInfo.projectUserArea}" class="fillin-input">
		                                                <span id="projectUserAreaWarn" class="warn_span">*必填</span>
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目容积率(%)：</label>
		                                            <div class="fillin">
		                                                <input id="projectCubageRate" name="projectCubageRate" type="text" value="${projectInfo.projectCubageRate}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目总建面（㎡）：</label>
		                                            <div class="fillin">
		                                                <input id="projectArea" name="projectArea" type="text" value="${projectInfo.projectArea}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">土地获取成本(万元)：</label>
		                                            <div class="fillin">
		                                                <input id="projectAreaCost" name="projectAreaCost" type="text" value="${projectInfo.projectAreaCost}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">楼面地价(元/㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="priceOfPerFloor" name="priceOfPerFloor" type="text" value="${projectInfo.priceOfPerFloor}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">计容楼面地价(元/㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="cubagePerFloor" name="cubagePerFloor"  type="text" value="${projectInfo.cubagePerFloor}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		
		                                </div>
		                            </div>
		                            <div class="main_edged main_edge_bottom">
		                                <div class="fillbox-left" >
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">计容建面(㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="cubageBuildingArea" name="cubageBuildingArea" type="text" value="${projectInfo.cubageBuildingArea}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">可售总建面(㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="saleAllArea" name="saleAllArea"  type="text" value="${projectInfo.saleAllArea}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">计容可售建面(㎡)：</label>
		                                            <div class="fillin">
		                                                <input id="cubageSaleArea" name="cubageSaleArea" type="text" value="${projectInfo.cubageSaleArea}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">总货值(万元)：</label>
		                                            <div class="fillin">
		                                                <input id="allPrice" name="allPrice" type="text" value="${projectInfo.allPrice}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目总投(万元)：</label>
		                                            <div class="fillin">
		                                                <input id="projectAllPutInto" name="projectAllPutInto" type="text" value="${projectInfo.projectAllPutInto}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width" >
		                                            <label class="user-name product-modify-span">项目期限：</label>
		                                            <div class="fillin">
		                                                <input id="projectTerm" name="projectTerm" type="text" value="<fmt:formatDate value='${projectInfo.projectTerm}'  pattern='yyyy-MM-dd'/>" class="fillin-input layui-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">预期开工时间：</label>
		                                            <div class="fillin">
		                                                <input id="expectStartWorkDate" name="expectStartWorkDate"  type="text" value="<fmt:formatDate value='${projectInfo.expectStartWorkDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input layui-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">预期开盘时间：</label>
		                                            <div class="fillin">
		                                                <input id="expectOpeningDate" name="expectOpeningDate" type="text" value="<fmt:formatDate value='${projectInfo.expectOpeningDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目预期收益(万元)：</label>
		                                            <div class="fillin">
		                                                <input id="expectEarnings" name="expectEarnings" type="text" value="${projectInfo.expectEarnings}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                        <div class="fillbox_inline fillbox_inline_width">
		                                            <label class="user-name product-modify-span">项目预期收益率(%)：</label>
		                                            <div class="fillin">
		                                                <input id="expectEarningsRate" name="expectEarningsRate" type="text" value="${projectInfo.expectEarningsRate}" class="fillin-input">
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		
		                            <div class="main_edged" style="margin-top:40px;">
		                                <div class="fillbox-left" >
		                                    <div class="btn_breed_city" id="breed_city_divID">
		                                        <div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">更新范围单元面积(㎡)：</label>
		                                                <div class="fillin">
		                                                    <input id="updateRangePerArea" name="updateRangePerArea" type="text" value="${projectInfo.updateRangePerArea}" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">可开发建设用地：</label>
		                                                <div class="fillin">
		                                                    <input id="developBuildArea" name="developBuildArea" type="text" value="${projectInfo.developBuildArea}" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">拟拆除建筑面积(㎡)：</label>
		                                                <div class="fillin">
		                                                    <input id="prepareDismantleArea" name="prepareDismantleArea" type="text" value="${projectInfo.prepareDismantleArea}" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">容积比率(%)：</label>
		                                                <div class="fillin">
		                                                    <input id="cubageRate" name="cubageRate" type="text" value="${projectInfo.cubageRate}" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">建筑面积(㎡)：</label>
		                                                <div class="fillin">
		                                                    <input id="buildArea" name="buildArea" type="text" value="${projectInfo.buildArea}" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">预期售价(元/㎡)：</label>
		                                                <div class="fillin">
		                                                    <input id="expectPricePerArea" name="expectPricePerArea" type="text" value="${projectInfo.expectPricePerArea}" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div>
		                                            <div class="fillbox_inline fillbox_inline_width">
		                                                <label class="user-name product-modify-span">预期完成收地日期：</label>
		                                                <div class="fillin">
		                                                    <input id="expectResumptionDate" name="expectResumptionDate"  type="text" value="<fmt:formatDate value='${projectInfo.expectResumptionDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input city_refresh">
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		
		                                    <div>
		                                        <div class="fillbox_inline">
		                                            <label class="user-name product-modify-span">项目备注：</label>
		                                            <div class="fillin">
		                                                <textarea class="fillin-input project_base_tarea" name="remark">${projectInfo.remark}</textarea>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
									
		                            <%-- <div class="btn_edge btn_edge_left">
		                                <a href="javascript:history.go(-1);" class="btn_cancel btn_all">
		                                    <img src="${basePath}/common/img/return.png">
		                                    <span>返回</span>
		                                </a>
		                            </div> --%>
								</form>
								
								<div class="main_edge main_edge_bottom">
	                                <div class="title_bggray">
	                                    <span>业态组成</span>
	                                </div>
	                                <%-- <div class="top_btn_little" id="addProjectCompositionDiv">
	                                    <a href="javascript:void(0)" class="main_btn" id="add_Makeuup1">
	                                        <img src="${basePath}/common/img/add.png">
	                                        <span>新增业态组成</span>
	                                    </a>
	                                </div> --%>
	                                <!--表格-->
	                                <div class="tabled_datum">
	                                    <table class="table table-bordered table-hover">
	                                        <thead>
	                                        <tr>
	                                            <th>序号</th>
	                                            <th>业态组成</th>
	                                            <th>对应业态建面(㎡)</th>
	                                            <th>可售建面(㎡)</th>
	                                            <th>销售均价(元/㎡)</th>
	                                            <th>货值(万元)</th>
	                                            <th>操作</th>
	                                        </tr>
	                                        </thead>
	                                        <tbody id="businessTbodyId">
		                                        
	                                        </tbody>
	                                    </table>
	                                    <!-- 底部翻页栏开始 -->
			                            <div id="pager_list" class="ui-jqgrid-pager" dir="ltr">
					                        <div id="pg_pager_list" class="ui-pager-control" role="group">
					                            <table class="ui-pg-table ui-common-table ui-pager-table">
					                                <tbody>
					                                <tr>
					                                    <td id="pager_list_left" align="left">
					                                       <table class="ui-pg-table navtable ui-common-table">
					                                           <tbody>
					                                           <tr>
					                                               <td class="ui-pg-button" title="刷新表格" id="refresh_table_list">
					                                                   <div class="ui-pg-div">
					                                                       <span class="glyphicon glyphicon-refresh"></span>
					                                                   </div>
					                                               </td>
					                                           </tr>
					                                           </tbody>
					                                       </table>
					                                    </td>
					
					                                    <td id="pager_list_center" align="center" style=" width: 373px;">
					                                        <table class="ui-pg-table ui-common-table ui-paging-pager">
					                                            <tbody>
					                                            <tr>
					                                                <td id="first_pager_list" onclick="getListByPage('firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
					                                                    <input type="text"  id="firstPageInput" hidden="hidden" name="firstPageInput" value="1">
					                                                    <span class="glyphicon glyphicon-step-backward"></span>
					                                                </td>
					                                                <td id="prev_pager_list"  onclick="getListByPage('upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
					                                                	<input type="text"  id="upPageInput" hidden="hidden" name="upPageInput" value="1">
					                                                    <span class="glyphicon glyphicon-backward"></span>
					                                                </td>
					                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
					                                                    <span class="ui-separator"></span>
					                                                </td>
					                                                <td id="input_pager_list" dir="ltr">
					                                                    <input id="searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="1" role="textbox">
																		共<span id="sp_1_pager_list">0</span>页
					                                                </td>
					                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
					                                                    <span class="ui-separator"></span>
					                                                </td>
					                                                <td id="next_pager_list" onclick="getListByPage('nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
					                                                	<input type="text" id="nextPageInput" name="nextPageInput" hidden="hidden" value="1">
					                                                    <span class="glyphicon glyphicon-forward"></span>
					                                                </td>
					                                                <td id="last_pager_list" onclick="getListByPage('lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
					                                                	<input id="lastPageInput" name="lastPageInput" hidden="hidden" value="1" type="text">
					                                                    <span class="glyphicon glyphicon-step-forward"></span>
					                                                </td>
					                                                <td dir="ltr">
					                                                    <select id="corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
					                                                        <option role="option" value="10" selected='selected'>10</option>
					                                                        <option role="option" value="20" >20</option>
					                                                        <option role="option" value="30" >30</option>
					                                                    </select>
					                                                </td>
					                                            </tr>
					                                            </tbody>
					                                        </table>
					                                    </td>
					
					                                    <td id="pager_list_right" align="right">
					                                        <div dir="ltr" style="text-align: right" class="ui-paging-info" id="totalCountId">共 0 条</div>
					                                    </td>
					                                 </tr>
					                                </tbody>
					                            </table>
					                        </div>
					                    </div>
					                    <!-- 底部翻页栏结束 -->
	                                </div>
	                            </div>
	                        </div>
<!---------------------------项目基本信息-结束------------------------------------------------------------>

<!-------------------------项目合同-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_bargain">
                            <div class="main_edge" id="project_contractNameDiv">
                                <div class="fillbox-left" style="margin-left: 15.33333333%;">
                                    <div class="fillbox_inline">
                                        <label class="user-name product-modify-span">合同名称：</label>
                                        <div class="fillin" id="contractNameDiv">
                                            <input id="project_contractName" name="contractName" type="text" value="" class="fillin-input">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn_edge btn_edge_left" id="project_contract_Buttons">
                            	<div style="margin-left: -3%;">
	                                <a href="${basePath}/projectManageController/proList" class="btn_cancel btn_all">
	                                    <img src="${basePath}/common/img/cancel.png">
	                                    <span>返回</span>
	                                </a>
	                                <button type="button" style="margin-right: 20px" hidden="hidden" class="btn_keep btn_all" id="contract_fileSelectList">
		                                <img src="${basePath}/common/img/folder.png">
		                                <span>选择文件</span>
		                            </button>
		                        	<button type="button" style="margin-right: 20px"  class="btn_keep btn_all" id="contract_fileSelectList2">
		                                <img src="${basePath}/common/img/folder.png">
		                                <span>选择文件</span>
		                            </button>
		                            <button  type="button" class="btn_upload btn_upload_box_shadow" hidden="hidden" id="contract_startUploadListAction">
		                                <img src="${basePath}/common/img/uploaded.png">
		                                <span>上传保存</span>
		                            </button>
		                            <button  type="button" class="btn_upload btn_upload_box_shadow" id="contract_startUploadListAction2">
		                                <img src="${basePath}/common/img/uploaded.png">
		                                <span>上传保存</span>
		                            </button>
	                            </div>
                            </div>
                            <div id="contract_fileUploadDiv" class="layui-upload" style="display: none;">
								  <div style="margin: 0px 40px 0px 40px;width: 76%" class="layui-upload-list">
									    <table class="layui-table">
										      <thead>
										        <tr><th>文件名</th>
										        <th>大小</th>
										        <th>状态</th>
										        <th>操作</th>
										      </tr></thead>
										      <tbody id="contract_fileUploadList">
										      
										      </tbody>
									    </table>
								  </div>
							</div> 
                            <div class="main_edge">
                                <div class="title_bggray" id="project_contract_title_div">
                                    <span>项目合同</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
	                                        <tr>
	                                            <th>合同名称</th>
	                                            <th>合同附件</th>
	                                            <th>上传时间</th>
	                                            <th>上传人</th>
	                                            <th>操作</th>
	                                        </tr>
                                        </thead>
                                        <tbody id="contract_businessTbodyId">
                                        	
                                        </tbody>
                                    </table>
                                    <!-- 项目合同列表底部翻页栏开始 -->
		                            <div id="contract_pager_list" class="ui-jqgrid-pager" dir="ltr">
				                        <div id="contract_pager_list" class="ui-pager-control" role="group">
				                            <table class="ui-pg-table ui-common-table ui-pager-table">
				                                <tbody>
				                                <tr>
				                                    <td id="contract_pager_list_left" align="left">
				                                       <table class="ui-pg-table navtable ui-common-table">
				                                           <tbody>
				                                           <tr>
				                                               <td class="ui-pg-button" title="刷新表格" id="contract_refresh_table_list">
				                                                   <div class="ui-pg-div">
				                                                       <span class="glyphicon glyphicon-refresh"></span>
				                                                   </div>
				                                               </td>
				                                           </tr>
				                                           </tbody>
				                                       </table>
				                                    </td>
				
				                                    <td id="pager_list_center" align="center" style=" width: 373px;">
				                                        <table class="ui-pg-table ui-common-table ui-paging-pager">
				                                            <tbody>
				                                            <tr>
				                                                <td id="contract_first_pager_list" onclick="getContractListByPage('contract_firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
				                                                    <input type="text"  id="contract_firstPageInput" hidden="hidden" name="firstPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-step-backward"></span>
				                                                </td>
				                                                <td id="contract_prev_pager_list"  onclick="getContractListByPage('contract_upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
				                                                	<input type="text"  id="contract_upPageInput" hidden="hidden" name="upPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-backward"></span>
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="input_pager_list" dir="ltr">
				                                                    <input id="contract_searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="1" role="textbox">
																	共<span id="contract_sp_1_pager_list">0</span>页
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="contract_next_pager_list" onclick="getContractListByPage('contract_nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
				                                                	<input type="text" id="contract_nextPageInput" name="nextPageInput" hidden="hidden" value="1">
				                                                    <span class="glyphicon glyphicon-forward"></span>
				                                                </td>
				                                                <td id="contract_last_pager_list" onclick="getContractListByPage('contract_lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
				                                                	<input id="contract_lastPageInput" name="lastPageInput" hidden="hidden" value="1" type="text">
				                                                    <span class="glyphicon glyphicon-step-forward"></span>
				                                                </td>
				                                                <td dir="ltr">
				                                                    <select id="contract_corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
				                                                        <option role="option" value="10" selected='selected'>10</option>
				                                                        <option role="option" value="20" >20</option>
				                                                        <option role="option" value="30" >30</option>
				                                                    </select>
				                                                </td>
				                                            </tr>
				                                            </tbody>
				                                        </table>
				                                    </td>
				
				                                    <td id="pager_list_right" align="right">
				                                        <div dir="ltr" style="text-align: right" class="ui-paging-info" id="contract_totalCountId">共 0 条</div>
				                                    </td>
				                                 </tr>
				                                </tbody>
				                            </table>
				                        </div>
				                    </div>
				                    <!-- 项目合同列表底部翻页栏结束 -->
                                </div>
                            </div>

                        </div>
<!------------------------项目合同-结束--------------------------------------------------------------->

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
                                   		<div class="datum_img" >
                                           	<img id="${corDataInfo.pid}_Img" style="width: 266px;height: 150px" title="" class="dataInfo_img" src="${basePath}/common/img/datum.png">
                                   		</div>
                                	</div>
                                </c:forEach>
                            </div>
                            <%-- <div class="btn_edge btn_edge_left">
                                <a href="javascript:history.go(-1);" class="btn_cancel btn_all">
                                    <img src="${basePath}/common/img/return.png">
                                    <span>返回</span>
                                </a>
                            </div> --%>


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
                                    <a href="${basePath}/investSubjectFinanceController/subjectFinanceList" class="btn_cancel btn_all">
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
                                <a href="javascript:void(0)" class="main_btn" id="Subject_Finance_Pay1">
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
                                    <table class="table table-bordered table-hover" id="subFinanceDetail_tableId">
                                        <thead>
                                        <tr>
                                            <th>选择</th>
                                            <th>付款时间</th>
                                            <th>出资主体</th>
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
                                        <tbody id="subFinanceDetail_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!-------------------------付款信息-结束-------------------------------------------------------------->

<!-------------------------回款信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_received">
                            <div class="top_btn top_btn_40">
                                <a href="javascript:void(0)" class="main_btn" id="Subject_Finance_reture1">
                                    <img src="${basePath}/common/img/looking.png">
                                    <span>查看回款明细</span>
                                </a>
                            </div>
                            <div class="title_explain">
                                <ul>
                                    <li>
                                        <span>回款本金合计：</span>
                                        <span  class="title_explain_red" id="subjectReceive_ReceiverAmount">暂无数据</span>
                                    </li>
                                    <li>
                                        <span>回款利润合计：</span>
                                        <span class="title_explain_red" id="subjectReceive_totalProfit">暂无数据</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="tabled_one">

                                <!--表格-->
                                <div class="tabled_two">
                                    <table class="table table-bordered table-hover" id="subjectReceive_tableId">
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
                                        <tbody id="subjectReceive_tbodyId">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!-------------------------回款信息-结束-------------------------------------------------------------->

                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--查看业态组成 -弹窗-开始-->
    <div id="see_Makeuup" class="add_Makeuup">
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">业态组成：</label>
            <div class="fillin-inputlay">
                <form class="layui-form" action="">
                    <select lay-verify="" lay-search>
                        <option value="">-请选择-</option>
                        <option value="01">住宅</option>
                        <option value="02" selected>洋房</option>
                        <option value="03">别墅</option>
                        <option value="04">公寓</option>
                        <option value="05">保障房</option>
                        <option value="06">商业</option>
                        <option value="07">其他</option>
                    </select>
                </form>
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">可售建面(㎡)：</label>
            <div class="fillin">
                <input type="text" value="10000.00" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">对应业态建面(㎡)：</label>
            <div class="fillin">
                <input type="text" value="10000.00" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">销售均价(RMB/㎡)：</label>
            <div class="fillin">
                <input type="text" value="100000.00" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">货值：</label>
            <div class="fillin">
                <input type="text" value="1000000000.00" class="fillin-input">
            </div>
        </div>
    </div>
    <!--查看业态组成-弹窗-结束-->

    <!--查看付款明细 -弹窗-开始-->
    <div id="Subject_Finance_Pay" class="add_Makeuup">
        <div>
        	<input type="text" id="subjectPay_subjectPayPid" value="" hidden="hidden" />
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_projectName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_receiverName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资主体：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_contributiveName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_receiverBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_payBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_receiverAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_payAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_payTerm" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_payAmount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款时间：</label>
                    <div class="fillin">
                       <input type="text" id="subjectPay_payDate" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" id="subjectPay_payRemark" disabled="disabled"></textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">财务录入操作者：</label>
                    <div class="fillin">
                        <input type="text" id="subjectPay_realUserName" disabled="disabled" value="" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">录入时间：</label>
                    <div class="fillin">
                        <input type="text" id="subjectPay_investorOpDate" value="" disabled="disabled" class="fillin-input notoptional">
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
                <table class="table table-bordered table-hover" id = "eachOneOfSubjectFile_tableId">
                    <thead>
	                    <tr>
	                        <th>文件名</th>
	                        <th>上传时间</th>
	                        <th>上传人</th>
	                        <th>操作</th>
	                    </tr>
                    </thead>
                    <tbody id="eachOneOfSubjectFile_tbodyId">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--查看付款明细-弹窗-结束-->

    <!--查看回款明细 -弹窗-开始-->
    <div id="Subject_Finance_reture" class="add_Makeuup">
        <div>
        	<input type="text" id="subjectReturnDetail_subjectReturnPid" hidden="hidden" value="" >
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_projectName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_receiverCompany" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款公司：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_contributiveCpmpany" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_receiverBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_payBankName" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_receiverAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_payAccount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款本金(万元)：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_receiverAmount" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款状态：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_receivedStatus" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款利润(万元)：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_profit" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款时间：</label>
                    <div class="fillin">
                       <input type="text" id="subjectReturnDetail_receiverDate" value="" disabled="disabled" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">回款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" disabled="disabled" id="subjectReturnDetail_reveiverRemark"></textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款录入操作者：</label>
                    <div class="fillin">
                        <input type="text" id="subjectReturnDetail_realUserName" disabled="disabled" value="" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款录入时间：</label>
                    <div class="fillin">
                        <input type="text" id="subjectReturnDetail_createDate" value="" disabled="disabled" class="fillin-input notoptional">
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
                <table class="table table-bordered table-hover" id="subjectReturnDetail_tableId">
                    <thead>
                    <tr>
                        <th>文件名</th>
                        <th>上传时间</th>
                        <th>上传人</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="subjectReturnDetail_tbodyId">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--查看回款明细-弹窗-结束-->

    <!--上传公司扫描件弹窗-开始-->
    <div id="Upload_file"  class="Upload_file">
        <label class="user-name" style="width:110px;">选择上传文件：</label>
        <div class="fillin">
            <input type="text" id="textName" style="height: 32px;border:1px solid #ddd;border-radius: 4px;width: 215px;" />
            <div class="report-file">
                <span>选择文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value">
            </div>
        </div>
    </div>
    <!--上传公司扫描件弹窗-结束-->

<!--控制头部tab切换-->
    <script>
        $('#myTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        })
    </script>



</body>
</html>