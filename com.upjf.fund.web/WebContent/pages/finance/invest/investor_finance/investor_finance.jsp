<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人财务管理</title>
<script type="text/javascript" src="${basePath}pages/finance/invest/js/investor_finance/investor_finance.js"></script>
</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                	<form id="investorSearchFormId" action="${basePath}/investorFinanceController/investorFinanceList" method="post">
                		<input type="text" hidden="hidden" id="investSubject" name="investSubjectId" value="${investSubjectFinanceVo.investSubjectId}">
                		<input type="text" hidden="hidden" id="investorPid" name="investorPid" value="${investSubjectFinanceVo.investorPid}">
                    	<input type="text" hidden="hidden" id="contributiveType" name="contributiveType" value="${investSubjectFinanceVo.contributiveType}">
                    	<input id="formCurPage" name="curPage" hidden="hidden" value="">
                		<input id="formPageSize" name="pageSize" hidden="hidden" value="">
	                    <div class="search-box_div">
	                        <div class="search-span"><span>项目名称：</span></div>
	                        <div class="search-text">
	                        	<input type="text" id="projectName" name="projectName" value="${investSubjectFinanceVo.projectName}">
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>投资主体：</span></div>
	                        <div class="search-text layui-form">
	                               <select id="investSubjectSelectId" lay-filter="investSubjectFilter" lay-verify="" lay-search>
	                                   <option value="">---请选择---</option>
	                                   <c:forEach items="${investSubjectList}" var="investSubject">
	                                   		<option value="${investSubject.pid}">${investSubject.name}</option>
	                                   </c:forEach>
	                               </select>
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>投资人：</span></div>
	                        <div class="search-text layui-form">
	                               <select id="investorSelectId" lay-filter="investorFilter" lay-verify="" lay-search>
	                                   <option value="">---请选择---</option>
	                                   <c:forEach items="${investSubjectList}" var="investor">
	                                   		<option value="${investor.pid}">${investor.name}</option>
	                                   </c:forEach>
	                               </select>
	                        </div>
	                    </div>
	
	                    <div class="search-box_div search_box_div_left">
	                        <button type="button" id="investorSearchButtonId" class="search-btn search-btn-chaxu">
	                            <img src="${basePath}/common/img/search-btn.png">
	                            <span>查询</span>
	                        </button>
	                        <button type="button" id="investorResetButtonId" class="search-btn search-btn-cz">
	                            <img src="${basePath}/common/img/chognzhi-reach.png">
	                            <span>重置</span>
	                        </button>
	                    </div>
					</form>
                </div>
            </div>
        </div>

		<!--正文开始-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span>投资人财务管理</span>
                </div>
                <div class="top_btn">
                    <a id="investorFianceDetailAid" class="main_btn">
                        <img src="${basePath}/common/img/looking.png">
                        <span>查看详情</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>选择</th>
                                    <th>项目名称</th>
                                    <th>楼盘名称</th>
                                    <th>投资主体名称</th>
                                    <th>投资人名称</th>
                                    <th>预计出资额(万元)</th>
                                    <th>持股比例(%)</th>
                                    <th>实际出资(万元)</th>
                                    <th>出资期数(月)</th>
                                    <th>分红方式</th>
                                    <th>年化利率(%)</th>
                                    <th>预计总回款(万元)</th>
                                    <th>已收本金(万元)</th>
                                    <th>已收回报(万元)</th>
                                </tr>
                            </thead>
                            <tbody id="investor_tbodyId">
                                <c:forEach items="${investorFinanceList}" var="investorFinance">
	                            	<tr>
	                                    <td>
	                                    	<input type="checkbox" class="hook_inp" value="${investorFinance.investor_id}">
	                                    	<input type="text" hidden="hidden" name="projectPid" value="${investorFinance.prj_id}">
	                                    </td>
	                                     <td>
	                                    	<a href='${basePath}/projectManageController/toProject?type=3&projectPid=${investorFinance.prj_id}' class="table_bnt">
	                                    		${investorFinance.project_name}
	                                    	</a>
	                                    </td>
	                                    <td>${investorFinance.building_name}</td>
	                                    <td>${investorFinance.invest_subject_name}</td>
	                                    <td>${investorFinance.investor_name}</td>
	                                    <td>${investorFinance.expect_contributive_amount}</td>
	                                    <td>${investorFinance.hold_stock_rate}</td>
	                                    <td>${investorFinance.total_realPay_amount}</td>
	                                    <td>${investorFinance.term}</td>
	                                    <td>${investorFinance.dividend_type_name}</td>
	                                    <td>${investorFinance.annualized_interest_rate}</td>
	                                    <td>${investorFinance.expect_all_receiver_account}</td>
	                                    <td>${investorFinance.total_receiver_amount}</td>
	                                    <th>${investorFinance.total_return_profit}</th>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!--投资人财务管理底部翻页按钮栏--开始-->
                    <div id="pager_list" class="ui-jqgrid-pager" dir="ltr">
	                    <div id="investor_finance_pager_list" class="ui-pager-control" role="group">
	                        <table class="ui-pg-table ui-common-table ui-pager-table">
	                            <tbody>
	                            <tr>
	                                <td id="project_pager_list_left" align="left">
	                                   <table class="ui-pg-table navtable ui-common-table">
	                                       <tbody>
	                                       <tr>
	                                           <td class="ui-pg-button" title="刷新表格" id="investor_finance_refresh_table_list">
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
	                                            <td id="investor_finance_first_pager_list" onclick="getInvestorListByPage('investor_finance_firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
	                                                <input type="text"  id="investor_finance_firstPageInput" hidden="hidden" name="firstPageInput" value="1">
	                                                <span class="glyphicon glyphicon-step-backward"></span>
	                                            </td>
	                                            <td id="investor_finance_prev_pager_list"  onclick="getInvestorListByPage('project_upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
	                                            	<input type="text"  id="investor_finance_upPageInput" hidden="hidden" name="upPageInput" value="${page.upPage}">
	                                                <span class="glyphicon glyphicon-backward"></span>
	                                            </td>
	                                            <td class="ui-pg-button ui-disabled" style="cursor: default;">
	                                                <span class="ui-separator"></span>
	                                            </td>
	                                            <td id="input_pager_list" dir="ltr">
	                                                <input id="investor_finance_searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="${page.curPage}" role="textbox">
													共<span id="investor_finance_sp_1_pager_list">${page.totalPage}</span>页
	                                            </td>
	                                            <td class="ui-pg-button ui-disabled" style="cursor: default;">
	                                                <span class="ui-separator"></span>
	                                            </td>
	                                            <td id="investor_finance_next_pager_list" onclick="getInvestorListByPage('investor_finance_nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
	                                            	<input type="text" id="investor_finance_nextPageInput" name="nextPageInput" hidden="hidden" value="${page.nextPage}">
	                                                <span class="glyphicon glyphicon-forward"></span>
	                                            </td>
	                                            <td id="investor_finance_last_pager_list" onclick="getInvestorListByPage('investor_finance_lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
	                                            	<input id="investor_finance_lastPageInput" name="lastPageInput" hidden="hidden" value="${page.endPage}" type="text">
	                                                <span class="glyphicon glyphicon-step-forward"></span>
	                                            </td>
	                                            <td dir="ltr">
	                                                <select id="investor_finance_corPageSelect"  class="ui-pg-selbox form-control" >
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
	                                    <div dir="ltr" style="text-align: right" class="ui-paging-info" id="investor_finance_totalCountId">共${page.totalCount} 条</div>
	                                </td>
	                             </tr>
	                            </tbody>
	                        </table>
	                    </div>
                	</div>
                    <!--投资人财务管理底部翻页按钮栏-结束-->

                </div>
            </div>
        </div>
        <!-- 正文结束 -->

    </div>

</body>
</html>