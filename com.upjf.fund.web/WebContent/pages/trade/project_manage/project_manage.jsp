<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目管理</title>
<script type="text/javascript" src="${basePath}pages/trade/js/project_manage/project_manage.js"></script>

</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <form id="projectSearchFormId" action="${basePath}/projectManageController/proList" method="post">
                    	<input type="text" hidden="hidden" id="projectProvice" name="projectProvinceId" value="${projectVo.projectProvinceId}">
                    	<input type="text" hidden="hidden" id="projectCity" name="projetCityId" value="${projectVo.projetCityId}">
                    	<input type="text" hidden="hidden" id="projectRegion" name="projectRegionId" value="${projectVo.projectRegionId}">
                    	<input type="text" hidden="hidden" id="projectCategory" name="projectCategoryId" value="${projectVo.projectCategoryId}">
						<input type="text" hidden="hidden" id="landGetWay" name="landgetWayId" value="${projectVo.landgetWayId}">
						<input type="text" hidden="hidden" id="project_progressId"  name="projectProgress" value="${projectVo.projectProgress}">
						<input id="formCurPage" name="curPage" hidden="hidden" value="">
                		<input id="formPageSize" name="pageSize" hidden="hidden" value="">
                		<input type="text" hidden="hidden" id="linkedPrjId" name="linkedPrjId" value="${linkedPrjId}">
                		<input type="text" hidden="hidden" id="businessPrjPid" name="businessPrjPid" value="${businessPrjPid}">
                		
	                    <div class="search-box_div">
	                        <div class="search-span">
	                        	<span>项目名称：</span>
	                        </div>
	                        <div class="search-text">
	                        	<input id="projectName" name="projectName" type="text" value="${projectVo.projectName}">
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span">
	                        	<span>项目公司：</span>
	                        </div>
	                        <div class="search-text">
	                        	<input id="projectCompany" name="projectCompany" type="text" value="${projectVo.projectCompany}">
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>项目地址：</span></div>
	                        <div class="search-text ">
	                            <div class="layui-form"  style="display:inline-block;width:150px;">
	                                <select id="provinceSelectId"  lay-filter="provinceFilter" lay-verify="" lay-search>
	                                    <option value="" >--请选择--</option>
	                                    <c:forEach items="${provinceList}" var="province">
	                                    	<option value="${province.areaCode}">${province.areaName}</option>
	                                    </c:forEach>
	                                </select>
	                            </div>
	                            <div class="layui-form" style="display:inline-block;width:150px;">
	                                <select  id="citySelectId"  lay-filter="cityFilter" lay-verify="" lay-search>
	                                    <option value="">--请选择--</option>
	                                    
	                                </select>
	                            </div>
	                            <div class="layui-form"  style="display:inline-block;width:150px;">
	                                <select  id="regionSelectId"  lay-filter="regionFilter" lay-verify="" lay-search>
	                                    <option value="">--请选择--</option>
	                                </select>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>项目种类：</span></div>
	                        <div class="search-text layui-form">
	                                <select id="proCateGorySelectId" lay-filter="proCateGoryFilter" lay-verify="" lay-search>
	                                    <option value="">-请选择-</option>
	                                    <c:forEach items="${proCateGoryList}" var="proCateGory">
	                                    	<option value="${proCateGory.pid}">${proCateGory.valueDes}</option>
	                                    </c:forEach>
	                                </select>
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>土地获取方式：</span></div>
	                        <div class="search-text layui-form">
	                                <select id="landGetWaySelectId" lay-filter="landGetWayFilter" lay-verify="" lay-search>
	                                    <option value="">-请选择-</option>
	                                    <c:forEach items="${landGetWayList}" var="landGetWay">
	                                    	<option value="${landGetWay.pid}">${landGetWay.valueDes}</option>
	                                    </c:forEach>
	                                </select>
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>项目进度：</span></div>
	                        <div class="search-text">
	                            <div class="layui-form">
	                                <select id="progressSelectId" lay-filter="progressFilter" lay-verify="" lay-search>
	                                    <option value="">-请选择-</option>
	                                    <c:forEach items="${progressList}" var="progress">
	                                    	<option value="${progress.pid}">${progress.valueDes}</option>
	                                    </c:forEach>
	                                </select>
	                            </div>
	                        </div>
	                    </div>
                    <div class="search-box_div search_box_div_left">
                        <button type="button" id="projectSearchButtonId" class="search-btn search-btn-chaxu">
                            <img src="${basePath}/common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="button" id="projectResetButtonId" class="search-btn search-btn-cz">
                            <img src="${basePath}/common/img/chognzhi-reach.png">
                            <span>重置</span>
                        </button>
                    </div>
                    </form>
                </div>
            </div>
        </div>

		<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span>项目管理</span>
                </div>
                <c:if test="${entranceType != 'linkProj'}">
	                <div class="top_btn">
	                    <a id="addProjectId" class="main_btn">
	                        <img src="${basePath}/common/img/add.png">
	                        <span>新增项目</span>
	                    </a>
	                    <a id="updateProjectId" class="main_btn">
	                        <img src="${basePath}/common/img/chance.png">
	                        <span>修改项目</span>
	                    </a>
	                    <a id="toSeeProject" class="main_btn">
	                        <img src="${basePath}/common/img/looking.png">
	                        <span>查看</span>
	                    </a>
	                    <a id="toChangeProgress" class="main_btn">
	                        <img src="${basePath}/common/img/changed.png">
	                        <span>变更项目进度</span>
	                    </a>
	                    <a id="delProject" class="main_btn main_btn_dele">
	                        <img src="${basePath}/common/img/delete.png">
	                        <span>删除</span>
	                    </a>
	                </div>
                </c:if>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>
                                    	<input type="checkbox" class="hook_all" id="checkAll">全选
                                    </th>
                                    <th>项目编号</th>
                                    <th>项目名称</th>
                                    <th>项目公司</th>
                                    <th>项目地区</th>
                                    <th>项目种类</th>
                                    <th>土地获取方式</th>
                                    <th>总货值（万元）</th>
                                    <th>最新项目进度时间</th>
                                    <th>项目进度状态</th>
                                </tr>
                            </thead>
                            <tbody id="project_TbodyId">
                            	<c:forEach items="${projectVoList}" var="projectVo">
	                            	<tr>
	                                    <td>
	                                    	<input type="checkbox" class="hook_inp" value="${projectVo.projectPid}">
	                                    </td>
	                                    <td>${projectVo.projectCode}</td>
	                                    <td>${projectVo.projectName}</td>
	                                    <td>${projectVo.projectCompany}</td>
	                                    <td>${projectVo.projectAddressFullName}</td>
	                                    <td>${projectVo.projectCategory}</td>
	                                    <td>${projectVo.landGetWay}</td>
	                                    <td>${projectVo.allPrice}</td>
	                                    <td>${projectVo.latestProgressTime}</td>
	                                    <td>${projectVo.latestProgressName}</td>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- 项目列表底部翻页栏开始 -->
                    <div id="pager_list" class="ui-jqgrid-pager" dir="ltr">
	                    <div id="project_pager_list" class="ui-pager-control" role="group">
	                        <table class="ui-pg-table ui-common-table ui-pager-table">
	                            <tbody>
	                            <tr>
	                                <td id="project_pager_list_left" align="left">
	                                   <table class="ui-pg-table navtable ui-common-table">
	                                       <tbody>
	                                       <tr>
	                                           <td class="ui-pg-button" title="刷新表格" id="project_refresh_table_list">
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
	                                            <td id="project_first_pager_list" onclick="getProjectListByPage('project_firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
	                                                <input type="text"  id="project_firstPageInput" hidden="hidden" name="firstPageInput" value="1">
	                                                <span class="glyphicon glyphicon-step-backward"></span>
	                                            </td>
	                                            <td id="project_prev_pager_list"  onclick="getProjectListByPage('project_upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
	                                            	<input type="text"  id="project_upPageInput" hidden="hidden" name="upPageInput" value="${page.upPage}">
	                                                <span class="glyphicon glyphicon-backward"></span>
	                                            </td>
	                                            <td class="ui-pg-button ui-disabled" style="cursor: default;">
	                                                <span class="ui-separator"></span>
	                                            </td>
	                                            <td id="input_pager_list" dir="ltr">
	                                                <input id="project_searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="${page.curPage}" role="textbox">
													共<span id="project_sp_1_pager_list">${page.totalPage}</span>页
	                                            </td>
	                                            <td class="ui-pg-button ui-disabled" style="cursor: default;">
	                                                <span class="ui-separator"></span>
	                                            </td>
	                                            <td id="project_next_pager_list" onclick="getProjectListByPage('project_nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
	                                            	<input type="text" id="project_nextPageInput" name="nextPageInput" hidden="hidden" value="${page.nextPage}">
	                                                <span class="glyphicon glyphicon-forward"></span>
	                                            </td>
	                                            <td id="project_last_pager_list" onclick="getProjectListByPage('project_lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
	                                            	<input id="project_lastPageInput" name="lastPageInput" hidden="hidden" value="${page.endPage}" type="text">
	                                                <span class="glyphicon glyphicon-step-forward"></span>
	                                            </td>
	                                            <td dir="ltr">
	                                                <select id="project_corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
	                                                    <option role="option" value="10" <c:if test="${page.pageSize==10}">selected='selected'</c:if>>10</option>
                                                        <option role="option" value="20" <c:if test="${page.pageSize==20}">selected='selected'</c:if>>20</option>
                                                        <option role="option" value="30" <c:if test="${page.pageSize==30}">selected='selected'</c:if>>30</option>
	                                                </select>
	                                            </td>
	                                        </tr>
	                                        </tbody>
	                                    </table>
	                                </td>
	
	                                <td id="pager_list_right" align="right">
	                                    <div dir="ltr" style="text-align: right" class="ui-paging-info" id="project_totalCountId">共${page.totalCount} 条</div>
	                                </td>
	                             </tr>
	                            </tbody>
	                        </table>
	                    </div>
                	</div>
                	<!-- 项目列表底部翻页栏结束 -->
                </div>
                <c:if test="${entranceType == 'linkProj'}">
	                <div class="btn_edge">
	                	<button type="submit" id="confirmButton" class="btn_keep btn_all">
	                        <img src="${basePath}common/img/keep.png">
	                        <span>确定</span>
	                    </button>
	                    <a href="javascript:;" onclick="history.go(-1);" class="btn_cancel btn_all">
	                        <img src="${basePath}common/img/cancel.png">
	                        <span>取消</span>
	                    </a>
	                </div>
                </c:if>
            </div>
        </div>

    </div>

</body>
</html>