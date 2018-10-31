<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>企业管理</title>
<script type="text/javascript" src="${basePath}pages/trade/js/firm_manage/firm_manage.js"></script>
</head>
<body>
    <div class="bg-gray">
    	<input type="text" id="basePathId" value="${basePath}" hidden="hidden">
        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                	<form action="${basePath}/corporationController/corInfoList" id="corSearchForm" name="corSearchForm" method="post">
                		<input id="formCurPage" name="curPage" hidden="hidden" value="">
                		<input id="formPageSize" name="pageSize" hidden="hidden" value="">
                		<div class="search-box_div">
                        	<div class="search-span">
                        		<span>企业名称：</span>
                        	</div>
                        	<div class="search-text">
                        		<input type="text" name="name" id="corNameId" value="${review.name}" >
                        	</div>
                    	</div>
	                    <div class="search-box_div">
	                        <div class="search-span">
	                        	<span>组织机构代码：</span>
	                        </div>
	                        <div class="search-text">
	                        	<input type="text" name="orgCodeCert" id="orgCodeCertId" value="${review.orgCodeCert}" >
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span">
	                        	<span>营业执照号码：</span>
	                        </div>
	                        <div class="search-text">
	                        	<input type="text" name="businessLicenceCode" id="businessLicenceCodeId" value="${review.businessLicenceCode}" >
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span">
	                        	<span>客户经理：</span>
	                        </div>
	                        <div class="search-text">
	                        	<input type="text" name="customerManager" id="customerManagerId" value="${review.customerManager}" >
	                        </div>
	                    </div>
	                    <div class="search-box_div search_box_div_left">
	                        <button type="submit"  class="search-btn search-btn-chaxu" id="corSearchButton">
	                            <img src="${basePath}/common/img/search-btn.png">
	                            <span>查询</span>
	                        </button>
	                        <button type="button" class="search-btn search-btn-cz" id="corFormRest">
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
                    <span>企业管理</span>
                </div>
                <div class="top_btn">
                    <a id="addCorporationId" class="main_btn">
                        <img src="${basePath}/common/img/add.png">
                        <span>新增企业</span>
                    </a>
                    <a  id="updateCorporationId" class="main_btn">
                        <img src="${basePath}/common/img/chance.png">
                        <span>修改企业</span>
                    </a>
                    <a id="delCorporationId" class="main_btn main_btn_dele">
                        <img src="${basePath}/common/img/delete.png">
                        <span>删除</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--列表-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" class="hook_all" id="checkAll">全选</th>
                                    <th>企业名称</th>
                                    <th>企业简称</th>
                                    <th>组织机构代码</th>
                                    <th>营业执照号码</th>
                                    <th>法人代表</th>
                                    <th>注册资本（万元)</th>
                                    <th>客户经理</th>
                                    <th>联系电话</th>
                                </tr>
                            </thead>
                            <tbody id="corTbodyId">
                            	<c:forEach items="${corList}" var="cor">
                            	 	<tr>
                                    	<td>
                                    		<input type="checkbox" class="hook_inp" value="${cor.pid}">
                                    	</td>
                                    	<td>${cor.name}</td>
                                    	<td>${cor.simpleName}</td>
                                    	<td>${cor.orgCodeCert}</td>
                                    	<td>${cor.businessLicenceCode}</td>
                                    	<td>${cor.legalRepresentative}</td>
                                    	<td>${cor.registerCapital}</td>
                                    	<td>${cor.customerManager}</td>
                                    	<td>${cor.phone}</td>
                                	</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!--底部翻页按钮栏-->
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
                                                <td id="first_pager_list" onclick="getCorInfoListByPage('firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
                                                <input type="text"  id="firstPageInput" hidden="hidden" name="firstPageInput" value="1">
                                                    <span class="glyphicon glyphicon-step-backward"></span>
                                                </td>
                                                <td id="prev_pager_list" onclick="getCorInfoListByPage('upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
                                                	<input type="text"  id="upPageInput" hidden="hidden" name="upPageInput" value="${page.upPage}">
                                                    <span class="glyphicon glyphicon-backward"></span>
                                                </td>
                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
                                                    <span class="ui-separator"></span>
                                                </td>
                                                <td id="input_pager_list" dir="ltr">
                                                    <input id="searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="${page.curPage}" role="textbox">
													共<span id="sp_1_pager_list">${page.totalPage}</span>页
                                                </td>
                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
                                                    <span class="ui-separator"></span>
                                                </td>
                                                <td id="next_pager_list" onclick="getCorInfoListByPage('nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
                                                	<input type="text" id="nextPageInput" name="nextPageInput" hidden="hidden" value="${page.nextPage}">
                                                    <span class="glyphicon glyphicon-forward"></span>
                                                </td>
                                                <td id="last_pager_list" onclick="getCorInfoListByPage('lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
                                                	<input id="lastPageInput" name="lastPageInput" hidden="hidden" value="${page.endPage}" type="text">
                                                    <span class="glyphicon glyphicon-step-forward"></span>
                                                </td>
                                                <td dir="ltr">
                                                    <select id="corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
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
                                        <div dir="ltr" style="text-align: right" class="ui-paging-info">共 ${page.totalCount} 条</div>
                                    </td>
                                 </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!--底部翻页按钮栏-完成-->

                </div>
            </div>
        </div>

    </div>

</body>
</html>