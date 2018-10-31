<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目股东付款请求管理</title>
<script type="text/javascript" src="${basePath}pages/trade/js/project_stocker_pay/share_pay.js"></script>
</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
        <!--搜索部分-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                	<form id="stockHolderPay_SearchFormId" action="${basePath}/projectStockerController/stoPayList" method="post">
                		<input type="text" hidden="hidden" id="projectPid" name="prjId" value="${payMent.prjId}">
                		<input type="text" hidden="hidden" id="contributiveId" name="contributiveId" value="${payMent.contributiveId}">
                		<input type="text" hidden="hidden" id="financeConfirmStatus" name="financeConfirmStatus" value="${payMent.financeConfirmStatus}">
                		<input id="formCurPage" name="curPage" hidden="hidden" value="">
                		<input id="formPageSize" name="pageSize" hidden="hidden" value="">
	                	 <div class="search-box_div">
	                        <div class="search-span"><span>项目名称：</span></div>
	                        <div class="search-text  layui-form">
	                             <select id="projectId_SelectId" lay-filter="projectIdFilter"  lay-verify="" lay-search>
	                                 <option value="" selected="selected">---请选择---</option>
	                                 <c:forEach items="${projectList}" var="project">
	                                 	<option value="${project.pid}">${project.projectName}</option>
	                                 </c:forEach>
	                             </select>
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>出资主体：</span></div>
	                        <div class="search-text  layui-form">
	                            <select id="contributiveId_SelectId" lay-filter="contributiveIdFilter"  lay-verify="" lay-search>
	                                <option value="" selected="selected">---请选择---</option>
	                                <c:forEach items="${corInfoList}" var="corInfo">
	                                	<option value="${corInfo.pid}">${corInfo.name}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="search-box_div">
	                        <div class="search-span"><span>财务确认状态：</span></div>
	                        <div class="search-text  layui-form">
	                            <select id="financeConfirmStatus_SelectId" lay-filter="financeConfirmStatusFilter" lay-verify="" lay-search>
	                                <option value="" selected="selected">---请选择---</option>
	                                <option value="0">未确认</option>
	                                <option value="1">已确认</option>
	                            </select>
	                        </div>
	                    </div>
	
	                    <div class="search-box_div search_box_div_left" >
	                        <button type="button"  id="stockHolderPay_SearchButtonId" class="search-btn search-btn-chaxu">
	                            <img src="${basePath}/common/img/search-btn.png">
	                            <span>查询</span>
	                        </button>
	                        <button type="button" class="search-btn search-btn-cz" id="stockHolderPay_resetButtonId">
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
                    <span>项目股东付款请求管理</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0)" class="main_btn" id="add_stockHolderPayId"> 
                        <img src="${basePath}/common/img/add.png">
                        <span>新增股东付款请求</span>
                    </a>
                    <a href="javascript:void(0)" class="main_btn" id="update_stockHolderPayId">
                        <img src="${basePath}/common/img/chance.png">
                        <span>修改股东付款请求</span>
                    </a>
                    <a href="javascript:void(0)" class="main_btn" id="see_stockHolderPayId">
                        <img src="${basePath}/common/img/looking.png">
                        <span>查看付款明细</span>
                    </a>
                    <a href="javascript:void(0)" class="main_btn main_btn_dele"  id="del_stockHolderPayId">
                        <img src="${basePath}/common/img/delete.png">
                        <span>删除</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table  class="table table-bordered table-hover" id="stockHolderPay_tableId">
                            <thead>
                                <tr>
                                    <th style="width: 4%">
                                    	<input type="checkbox" class="hook_all" id="checkAll">全选
                                    </th>
                                    <th>项目名称</th>
                                    <th>出资主体</th>
                                    <th>付款账号</th>
                                    <th>付款期数</th>
                                    <th>付款金额(万元)</th>
                                    <th>财务确认状态</th>
                                    <th>收款公司</th>
                                    <th>收款账号</th>
                                    <th>收款金额(万元)</th>
                                    <th>收款时间</th>
                                    <th>付款备注</th>
                                    <th>出资操作者</th>
                                    <th>出资操作时间</th>
                                    <th>出资确认人</th>
                                    <th>出资确认时间</th>
                                    <th>付款凭证</th>
                                </tr>
                            </thead>
                            <tbody id="stockHolderPay_tbodyId">
                            	<c:forEach items="${stockHolderPayList}" var="stockHolderPay">
	                            	<tr>
	                                    <td>
	                                    	<input type="checkbox" class="hook_inp" value="${stockHolderPay.pid}">
	                                    	<input type="text" hidden="hidden"  value="${stockHolderPay.finance_confirm_status}">
	                                    </td>
	                                    <td>${stockHolderPay.project_name}</td>
	                                    <td>${stockHolderPay.contributive_name}</td>
	                                    <td>${stockHolderPay.pay_account}</td>
	                                    <td>${stockHolderPay.pay_term}</td>
	                                    <td>${stockHolderPay.pay_amount}</td>
	                                    <c:choose>
		                                    <c:when test="${stockHolderPay.finance_confirm_status == '1'}">
		                                    	<td>已确认</td>
		                                    </c:when>
		                                    <c:otherwise>
		                                    	<td>未确认</td>
		                                    </c:otherwise>
	                                    </c:choose>
	                                    
	                                    <td>${stockHolderPay.receiver_name}</td>
	                                    <td>${stockHolderPay.receiver_account}</td>
	                                    <td>${stockHolderPay.receiver_amount}</td>
	                                    <td><fmt:formatDate value='${stockHolderPay.receiver_date}'  pattern='yyyy-MM-dd HH:mm:ss'/></td>
	                                    <td>${stockHolderPay.pay_remark}</td>
	                                    <td>${stockHolderPay.investor_op_name}</td>
	                                    <td><fmt:formatDate value='${stockHolderPay.investor_op_date}'  pattern='yyyy-MM-dd HH:mm:ss'/></td>
	                                    <td>${stockHolderPay.investor_confirm_name}</td>
	                                    <td><fmt:formatDate value='${stockHolderPay.investor_confirm_date}'  pattern='yyyy-MM-dd HH:mm:ss'/></td>
	                                    <td><a href="javascript:void(0)" class="table_bnt" onclick="toVoucherFileList('${stockHolderPay.pid}')">查看</a></td>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--项目股东付款请求列表底部翻页按钮栏--开始-->
                    <div id="pager_list" class="ui-jqgrid-pager" dir="ltr" style="margin-bottom: 20px">
	                    <div id="stockHolderPay_pager_list" class="ui-pager-control" role="group">
	                        <table class="ui-pg-table ui-common-table ui-pager-table">
	                            <tbody>
	                            <tr>
	                                <td id="stockHolderPay_pager_list_left" align="left">
	                                   <table class="ui-pg-table navtable ui-common-table">
	                                       <tbody>
	                                       <tr>
	                                           <td class="ui-pg-button" title="刷新表格" id="stockHolderPay_refresh_table_list">
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
	                                            <td id="stockHolderPay_first_pager_list" onclick="getObjectListByPage('stockHolderPay_firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
	                                                <input type="text"  id="stockHolderPay_firstPageInput" hidden="hidden" name="firstPageInput" value="1">
	                                                <span class="glyphicon glyphicon-step-backward"></span>
	                                            </td>
	                                            <td id="stockHolderPay_prev_pager_list"  onclick="getObjectListByPage('stockHolderPay_upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
	                                            	<input type="text"  id="stockHolderPay_upPageInput" hidden="hidden" name="upPageInput" value="${page.upPage}">
	                                                <span class="glyphicon glyphicon-backward"></span>
	                                            </td>
	                                            <td class="ui-pg-button ui-disabled" style="cursor: default;">
	                                                <span class="ui-separator"></span>
	                                            </td>
	                                            <td id="input_pager_list" dir="ltr">
	                                                <input id="stockHolderPay_searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="${page.curPage}" role="textbox">
													共<span id="stockHolderPay_sp_1_pager_list">${page.totalPage}</span>页
	                                            </td>
	                                            <td class="ui-pg-button ui-disabled" style="cursor: default;">
	                                                <span class="ui-separator"></span>
	                                            </td>
	                                            <td id="stockHolderPay_next_pager_list" onclick="getObjectListByPage('stockHolderPay_nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
	                                            	<input type="text" id="stockHolderPay_nextPageInput" name="nextPageInput" hidden="hidden" value="${page.nextPage}">
	                                                <span class="glyphicon glyphicon-forward"></span>
	                                            </td>
	                                            <td id="stockHolderPay_last_pager_list" onclick="getObjectListByPage('stockHolderPay_lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
	                                            	<input id="stockHolderPay_lastPageInput" name="lastPageInput" hidden="hidden" value="${page.endPage}" type="text">
	                                                <span class="glyphicon glyphicon-step-forward"></span>
	                                            </td>
	                                            <td dir="ltr">
	                                                <select id="stockHolderPay_corPageSelect"  class="ui-pg-selbox form-control" >
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
	                                    <div dir="ltr" style="text-align: right" class="ui-paging-info" id="stockHolderPay_totalCountId">共${page.totalCount} 条</div>
	                                </td>
	                             </tr>
	                            </tbody>
	                        </table>
	                    </div>
                	</div>
                    <!--项目股东付款请求列表底部翻页按钮栏-结束-->
                </div>
            </div>
        </div>

    </div>
     <!-- 凭证弹窗开始 -->
     <div class="add_Makeuup" id="stockHolderPayVoucher_FileListDivId">
     	<input type="text" hidden="hidden" id="cheackedPayMentPid" value="" />
         <div class="tabled_datum_940">
             <table class="table table-bordered table-hover" id="stockHolderPayVoucher_tableId">
                 <thead>
                 <tr>
                     <th>文件名</th>
                     <th>文件类型</th>
                     <th>上传时间</th>
                     <th>上传人</th>
                     <th>操作</th>
                 </tr>
                 </thead>
                 <tbody id="stockHolderPayVoucher_tbodyId">
                 </tbody>
             </table>
         </div>
     </div>
     <!-- 凭证弹窗结束 -->

</body>


</html>