<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增项目</title>
<script type="text/javascript" src="${basePath}pages/trade/js/project_manage/project_manage_add.js"></script>

</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
		<input id="type" type="text" hidden="hidden" value="${type}">
		<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span id="operateTypeChangeSpan">新增项目</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#base_Informa">项目基本信息</a></li>
                        <li><a href="#bargain">项目合同</a></li>
                        <li><a href="#schedule">项目进度</a></li>
                        <li><a href="#budget">项目预算</a></li>
                    </ul>
                    <div class="tab-content myTab_content">
						
						
						<!-------------------------项目基本信息-开始--------------------------------------------->
							
							<div class="tab-pane active" id="base_Informa">
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
									<input type="text" hidden="hidden" id="accessType" name="accessType" value="${accessType}">
									<input type="text" hidden="hidden" id="businessPrjIdFromAddStock" name="businessPrjIdFromAddStock" value="${businessPrjIdFromAddStock}">
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
									
		                            <div class="btn_edge btn_edge_left" id="projectInfoButtonDiv">
		                                <a href="${basePath}/projectManageController/proList" class="btn_cancel btn_all">
		                                    <img src="${basePath}/common/img/cancel.png">
		                                    <span>返回</span>
		                                </a>
		                                <a href="javascript:void(0)" class="btn_keep btn_all" id="projectBaseSubmit">
		                                    <img src="${basePath}/common/img/keep.png">
		                                    <span>保存</span>
		                                </a>
		                            </div>
								</form>
								
								<div class="main_edge main_edge_bottom">
	                                <div class="title_bggray">
	                                    <span>业态组成</span>
	                                </div>
	                                <div class="top_btn_little" id="addProjectCompositionDiv">
	                                    <a href="javascript:void(0)" class="main_btn" id="add_Makeuup1">
	                                        <img src="${basePath}/common/img/add.png">
	                                        <span>新增业态组成</span>
	                                    </a>
	                                </div>
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
                        <div class="tab-pane" id="bargain">
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

						<!-------------------------项目进度-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="schedule">
                        	<form id="project_progress_form">
                        		<input type="text" hidden="hidden" id="progressId"  name="pid" value="${projectProgress.pid}">
                        		<input type="text" hidden="hidden" id="progress_ProjectId"  name="prjId" value="${projectProgress.prjId}">
                        		<input type="text" hidden="hidden" id="project_progressId"  name="projectProgress" value="${projectProgress.projectProgress}">
                        		<input type="text" hidden="hidden" id="progress_cert_build_areaId"  name="certBuildArea" value="${projectProgress.certBuildArea}">
                        		
	                        	<div class="main_edge">
	                                <div class="fillbox-left">
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">预期完成收地时间：</label>
	                                            <div class="fillin">
	                                                <input id="progress_expectResumptionDate" name="expectResumptionDate" type="text" value="<fmt:formatDate value='${projectProgress.expectResumptionDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">实际完成收地时间：</label>
	                                            <div class="fillin">
	                                                <input id="realityResumptionDate"  name="realityResumptionDate" type="text" value="<fmt:formatDate value='${projectProgress.realityResumptionDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">预期开始施工日期：</label>
	                                            <div class="fillin">
	                                                <input id="expectConstructionDate"  name="expectConstructionDate" type="text" value="<fmt:formatDate value='${projectProgress.expectConstructionDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">实际开始施工日期：</label>
	                                            <div class="fillin">
	                                                <input id="realityConstructionDate"  name="realityConstructionDate" type="text" value="<fmt:formatDate value='${projectProgress.realityConstructionDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">预期取得预售证日期：</label>
	                                            <div class="fillin">
	                                                <input id="expectSaleCertifyDate"  name="expectSaleCertifyDate" type="text" value="<fmt:formatDate value='${projectProgress.expectSaleCertifyDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">预期清算日期：</label>
	                                            <div class="fillin">
	                                                <input id="expectLiquidationDate"  name="expectLiquidationDate" type="text" value="<fmt:formatDate value='${projectProgress.expectLiquidationDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目进度状态：</label>
	                                            <div class="fillin-inputlay layui-form">
	                                                 <select id="progressSelectId" lay-filter="progressFilter" lay-verify="" lay-search>
	                                                       <option value="">-请选择-</option>
	                                                       <c:forEach items="${progressList}" var="progress">
	                                                       		<option value="${progress.pid}">${progress.valueDes}</option>
	                                                       </c:forEach>
	                                                 </select>
	                                            </div>
	                                            <span id="project_progressIdWarn" class="warn_span">*必填</span>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">实际清算日期：</label>
	                                            <div class="fillin">
	                                                <input id="realityLiquidationDate"  name="realityLiquidationDate" type="text" value="<fmt:formatDate value='${projectProgress.realityLiquidationDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--当选择’销售‘的时候display:block    --开始-->
	                                    <div style="display:none" id="progress_saleShowDiv">
	                                        <div>
	                                            <div class="fillbox_inline fillbox_inline_width">
	                                                <label class="user-name product-modify-span">是否取得预售证：</label>
	                                                <div class="fillin layui_input_block layui-form fillbox_inline">
	                                                    <input id="acquireSaleCertifyRadio1" type="radio" name="acquireSaleCertify" value="0" title="否" <c:if test="${projectProgress.acquireSaleCertify == 0}">checked </c:if> />
	                                                    <input id="acquireSaleCertifyRadio2" type="radio" name="acquireSaleCertify" value="1" title="是" <c:if test="${projectProgress.acquireSaleCertify == 1}">checked </c:if>  />
	                                                </div>
	                                            </div>
	                                            <div class="fillbox_inline fillbox_inline_width">
	                                                <label class="user-name product-modify-span">实际取得预售证日期：</label>
	                                                <div class="fillin">
	                                                    <input id="realitySaleCertifyDate"  name="realitySaleCertifyDate" type="text" value="<fmt:formatDate value='${projectProgress.realitySaleCertifyDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input progress_sale_refresh">
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div>
	                                            <div class="fillbox_inline fillbox_inline_width">
	                                                <label class="user-name product-modify-span">证载建面：</label>
	                                                <div class="fillin-inputlay layui-form">
	                                                    <select  id="certBuildAreaSelect" lay-filter="certBuildAreaFilter" lay-verify="" >
	                                                        <option value="" selected="selected">---请选择---</option>
	                                                        <c:forEach items="${certBuildAreaList}" var="certBuildArea">
	                                                        	<option value="${certBuildArea.pid}">${certBuildArea.valueDes}</option>
	                                                        </c:forEach>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                            <div class="fillbox_inline fillbox_inline_width">
	                                                <label class="user-name product-modify-span">已推货值(万元)：</label>
	                                                <div class="fillin">
	                                                    <input id="hasPushValue"  name="hasPushValue" type="text" value="${projectProgress.hasPushValue}" class="fillin-input progress_sale_refresh">
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div>
	                                            <div class="fillbox_inline fillbox_inline_width">
	                                                <label class="user-name product-modify-span">剩余推盘货值(万元)：</label>
	                                                <div class="fillin">
	                                                    <input id="surplusPubshValue"  name="surplusPubshValue" type="text" value="${projectProgress.surplusPubshValue}" class="fillin-input progress_sale_refresh">
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--当选择销售的时候display:block    --结束-->
	
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">备注：</label>
	                                            <div class="fillin">
	                                                <textarea id="progress_remark"  name="remark" class="fillin-input project_base_tarea">${projectProgress.remark}</textarea>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    
	                                </div>
	                            </div>
	                            
	                            <div class="btn_edge btn_edge_left" id="project_progressDiv">
		                           		<a href="${basePath}/projectManageController/proList" class="btn_cancel btn_all">
		                                    <img src="${basePath}/common/img/cancel.png">
		                                    <span>返回</span>
		                                </a>
		                                <button type="button"  class="btn_keep btn_all" id="project_progress_submit">
		                                    <img src="${basePath}/common/img/keep.png">
		                                    <span>保存</span>
		                                </button>
	                               		
	                            </div>
                        	</form>
                        	<div style="margin-left: 40px" id="project_progress_buttons"> 
	                        	<button type="button" style="margin-right: 20px" hidden="hidden" class="btn_keep btn_all" id="progress_fileSelectList">
	                                <img src="${basePath}/common/img/folder.png">
	                                <span>选择文件</span>
	                            </button>
	                        	<button type="button" style="margin-right: 20px"  class="btn_keep btn_all" id="progress_fileSelectList2">
	                                <img src="${basePath}/common/img/folder.png">
	                                <span>选择文件</span>
	                            </button>
	                            <button  type="button" class="btn_upload btn_upload_box_shadow" hidden="hidden" id="startUploadListAction">
	                                <img src="${basePath}/common/img/uploaded.png">
	                                <span>开始上传</span>
	                            </button>
	                            <button  type="button" class="btn_upload btn_upload_box_shadow" id="startUploadListAction2">
	                                <img src="${basePath}/common/img/uploaded.png">
	                                <span>开始上传</span>
	                            </button>
                        	</div>
					   		<div id="progress_fileUploadDiv" class="layui-upload" style="display: none;">
							  <div style="margin: 0px 40px 0px 40px;width: 76%" class="layui-upload-list">
							    <table class="layui-table">
							      <thead>
							        <tr><th>文件名</th>
							        <th>大小</th>
							        <th>状态</th>
							        <th>操作</th>
							      </tr></thead>
							      <tbody id="progress_fileUploadList">
							      
							      </tbody>
							    </table>
							  </div>
							</div> 
                            
                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>项目进度状态</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>项目进度状态</th>
                                            <th>进度附件</th>
                                            <th>备注</th>
                                            <th>项目进度更新时间</th>
                                            <th>更新人</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="progress_businessTbodyId">
	                                        
                                        </tbody>
                                    </table>
                                    <!-- 项目进度列表底部翻页栏开始 -->
		                            <div id="pager_list" class="ui-jqgrid-pager" dir="ltr">
				                        <div id="progress_pager_list" class="ui-pager-control" role="group">
				                            <table class="ui-pg-table ui-common-table ui-pager-table">
				                                <tbody>
				                                <tr>
				                                    <td id="progress_pager_list_left" align="left">
				                                       <table class="ui-pg-table navtable ui-common-table">
				                                           <tbody>
				                                           <tr>
				                                               <td class="ui-pg-button" title="刷新表格" id="progress_refresh_table_list">
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
				                                                <td id="progress_first_pager_list" onclick="getProgressListByPage('progress_firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
				                                                    <input type="text"  id="progress_firstPageInput" hidden="hidden" name="firstPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-step-backward"></span>
				                                                </td>
				                                                <td id="progress_prev_pager_list"  onclick="getProgressListByPage('progress_upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
				                                                	<input type="text"  id="progress_upPageInput" hidden="hidden" name="upPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-backward"></span>
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="input_pager_list" dir="ltr">
				                                                    <input id="progress_searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="1" role="textbox">
																	共<span id="progress_sp_1_pager_list">0</span>页
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="progress_next_pager_list" onclick="getProgressListByPage('progress_nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
				                                                	<input type="text" id="progress_nextPageInput" name="nextPageInput" hidden="hidden" value="1">
				                                                    <span class="glyphicon glyphicon-forward"></span>
				                                                </td>
				                                                <td id="progress_last_pager_list" onclick="getProgressListByPage('progress_lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
				                                                	<input id="progress_lastPageInput" name="lastPageInput" hidden="hidden" value="1" type="text">
				                                                    <span class="glyphicon glyphicon-step-forward"></span>
				                                                </td>
				                                                <td dir="ltr">
				                                                    <select id="progress_corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
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
				                                        <div dir="ltr" style="text-align: right" class="ui-paging-info" id="progress_totalCountId">共 0 条</div>
				                                    </td>
				                                 </tr>
				                                </tbody>
				                            </table>
				                        </div>
				                    </div>
				                    <!-- 项目进度列表底部翻页栏结束 -->
                                </div>
                            </div>

                        </div>
						<!------------------------项目进度-结束--------------------------------------------------------------->

						<!-------------------------项目预算-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="budget">
                        	<form id = "project_budgetForm">
                        		<input id="projectBudgetPid" name="pid" type="text" hidden="hidden" value="">
                        		<input id="prjId" name="prjId" type="text" hidden="hidden" value="">
	                        	<div class="main_edge"  id="budgetDataDiv">
	                                <div class="fillbox-left" style="margin-left: 15.333333%;display:inline-block">
	                                    <div class="fillbox_inline fillbox_block" >
	                                        <label class="user-name product-modify-span"><span class="textmust">*</span>预算名称：</label>
	                                        <div class="fillin">
	                                            <input id="budgetName" name="budgetName" type="text" value="${projectBudget.budgetName}" class="fillin-input">
	                                        </div>
	                                        <span id="budgetNameWarn" class="warn_span">*必填</span>
	                                    </div>
	                                    <div class="fillbox_inline fillbox_block">
	                                        <label class="user-name product-modify-span">项目整体利润预算(万元)：</label>
	                                        <div class="fillin">
	                                            <input id="projectProfitBudget" name="projectProfitBudget" type="text" value="${projectBudget.projectProfitBudget}" class="fillin-input">
	                                        </div>
	                                    </div>
	                                    <div class="fillbox_inline fillbox_block">
	                                        <label class="user-name product-modify-span">预算日期：</label>
	                                        <div class="fillin">
	                                            <input id="budgetDate" name="budgetDate" type="text" value="<fmt:formatDate value='${projectBudget.budgetDate}'  pattern='yyyy-MM-dd'/>" class="fillin-input">
	                                        </div>
	                                    </div>
	                                    <div class="fillbox_inline fillbox_block">
	                                        <label class="user-name product-modify-span">预算依据：</label>
	                                        <div class="fillin">
	                                            <textarea id="budgetGist" name="budgetGist" class="fillin-input tarea_remarks" style="width: 305px;">${projectBudget.budgetGist}</textarea>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="btn_edge btn_edge_left" id="project_budgetDiv">
		                           		<a href="${basePath}/projectManageController/proList" class="btn_cancel btn_all">
		                                    <img src="${basePath}/common/img/cancel.png">
		                                    <span>返回</span>
		                                </a>
		                                <button type="button"  class="btn_keep btn_all" id="budget_progress_submit">
		                                    <img src="${basePath}/common/img/keep.png">
		                                    <span>保存</span>
		                                </button>
	                            </div>
                        	</form>
                            <div  style="margin-left: 40px;" id="project_budget_buttons">
	                               <button type="button" style="margin-right: 20px" hidden="hidden" class="btn_keep btn_all" id="budget_fileSelectList">
		                                <img src="${basePath}/common/img/folder.png">
		                                <span>选择文件</span>
		                            </button>
		                        	<button type="button" style="margin-right: 20px"  class="btn_keep btn_all" id="budget_fileSelectList2">
		                                <img src="${basePath}/common/img/folder.png">
		                                <span>选择文件</span>
		                            </button>
		                            <button  type="button" class="btn_upload btn_upload_box_shadow" hidden="hidden" id="budget_startUploadListAction">
		                                <img src="${basePath}/common/img/uploaded.png">
		                                <span>开始上传</span>
		                            </button>
		                            <button  type="button" class="btn_upload btn_upload_box_shadow" id="budget_startUploadListAction2">
		                                <img src="${basePath}/common/img/uploaded.png">
		                                <span>开始上传</span>
		                            </button>
                            </div>
                            <div id="budget_fileUploadDiv" class="layui-upload" style="display: none;">
								  <div style="margin: 0px 40px 0px 40px;width: 76%" class="layui-upload-list">
									    <table class="layui-table">
										      <thead>
										        <tr><th>文件名</th>
										        <th>大小</th>
										        <th>状态</th>
										        <th>操作</th>
										      </tr></thead>
										      <tbody id="budget_fileUploadList">
										      
										      </tbody>
									    </table>
								  </div>
							</div>
                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>项目预算更新记录</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
	                                        <tr>
	                                            <th>预算名称</th>
	                                            <th>项目整体利润预算(万元)</th>
	                                            <th>预算日期</th>
	                                            <th>预算依据</th>
	                                            <th>依据附件</th>
	                                            <th>操作人</th>
	                                            <th>操作日期</th>
	                                            <th>操作</th>
	                                        </tr>
                                        </thead>
                                        <tbody id="budget_businessTbodyId">
                                        	
                                        </tbody>
                                    </table>
                                    <!-- 项目预算列表底部翻页栏开始 -->
		                            <div id="budget_pager_list" class="ui-jqgrid-pager" dir="ltr">
				                        <div id="budget_pager_list" class="ui-pager-control" role="group">
				                            <table class="ui-pg-table ui-common-table ui-pager-table">
				                                <tbody>
				                                <tr>
				                                    <td id="budget_pager_list_left" align="left">
				                                       <table class="ui-pg-table navtable ui-common-table">
				                                           <tbody>
				                                           <tr>
				                                               <td class="ui-pg-button" title="刷新表格" id="budget_refresh_table_list">
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
				                                                <td id="budget_first_pager_list" onclick="getBudgetListByPage('budget_firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
				                                                    <input type="text"  id="budget_firstPageInput" hidden="hidden" name="firstPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-step-backward"></span>
				                                                </td>
				                                                <td id="budget_prev_pager_list"  onclick="getBudgetListByPage('budget_upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
				                                                	<input type="text"  id="budget_upPageInput" hidden="hidden" name="upPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-backward"></span>
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="input_pager_list" dir="ltr">
				                                                    <input id="budget_searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="1" role="textbox">
																	共<span id="budget_sp_1_pager_list">0</span>页
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="budget_next_pager_list" onclick="getBudgetListByPage('budget_nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
				                                                	<input type="text" id="budget_nextPageInput" name="nextPageInput" hidden="hidden" value="1">
				                                                    <span class="glyphicon glyphicon-forward"></span>
				                                                </td>
				                                                <td id="budget_last_pager_list" onclick="getBudgetListByPage('budget_lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
				                                                	<input id="budget_lastPageInput" name="lastPageInput" hidden="hidden" value="1" type="text">
				                                                    <span class="glyphicon glyphicon-step-forward"></span>
				                                                </td>
				                                                <td dir="ltr">
				                                                    <select id="budget_corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
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
				                                        <div dir="ltr" style="text-align: right" class="ui-paging-info" id="budget_totalCountId">共 0 条</div>
				                                    </td>
				                                 </tr>
				                                </tbody>
				                            </table>
				                        </div>
				                    </div>
				                    <!-- 项目预算列表底部翻页栏结束 -->
                                </div>
                            </div>

                        </div>
						<!------------------------项目预算-结束--------------------------------------------------------------->

                    </div>
                </div>
            </div>
        </div>
    </div>



    <!------------------------------------------------新增及修改业态组成 -弹窗-开始------------------------------------------------>
    <div id="add_Makeuup" class="add_Makeuup">
    	<form id="add_busCompoForm">
    		<input type="text" hidden="hidden" id="add_businessType" name="businessType" value="" >
    		<input type="text" hidden="hidden" id="projectId" name="projectId" value="" >
    		<input type="text" hidden="hidden" id="busCompoPId" name="pid" value="" >
	    	<div class="fillbox_inline">
	            <label class="user-name product-modify-span">业态组成：</label>
	            <div class="fillin-inputlay layui-form">
	                  <select id="businessTypeSelectId" lay-filter="businessTypeFilter" lay-verify="" lay-search>
	                      <option value=""  >-请选择-</option>
	                      <c:forEach items="${busCompoList}" var="busCompo">
	                      		<option value="${busCompo.pid}" >${busCompo.valueDes}</option>
	                      </c:forEach>
	                  </select>
	            </div>
	        </div>
	        <div class="fillbox_inline">
	            <label class="user-name product-modify-span">可售建面(㎡)：</label>
	            <div class="fillin">
	                <input id="saleArea" name="saleArea" type="text" value="" class="fillin-input">
	            </div>
	        </div>
	        <div class="fillbox_inline">
	            <label class="user-name product-modify-span">对应业态建面(㎡)：</label>
	            <div class="fillin">
	                <input id="salePerMeter" name="salePerMeter" type="text" value="" class="fillin-input">
	            </div>
	        </div>
	        <div class="fillbox_inline">
	            <label class="user-name product-modify-span">销售均价(元/㎡)：</label>
	            <div class="fillin">
	                <input id="compositionArea" name="compositionArea" type="text" value="" class="fillin-input">
	            </div>
	        </div>
	        <div class="fillbox_inline">
	            <label class="user-name product-modify-span">货值(万元)：</label>
	            <div class="fillin">
	                <input id="projectValue" name="projectValue" type="text" value="" class="fillin-input">
	            </div>
	        </div>
    	</form>
    </div>
    <!------------------------------------------------新增业态组成-弹窗-结束------------------------------------------------>
    
</body>
</html>