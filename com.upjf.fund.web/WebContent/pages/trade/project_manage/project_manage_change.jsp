<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>变更项目进度</title>
     <%@ include file="../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}pages/trade/js/project_manage/project_manage_change.js"></script>

</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
		<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span >变更项目进度</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                 <!-------------------------项目进度-开始-------------------------------------------------------------->
                <div class="tab-pane" id="schedule">
                	<form id="project_progress_form">
                		<input type="text" hidden="hidden" id="pid"  value="${projectProgress.prjId}">
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
                                         <label class="user-name product-modify-span">已推货值：</label>
                                         <div class="fillin">
                                             <input id="hasPushValue"  name="hasPushValue" type="text" value="${projectProgress.hasPushValue}" class="fillin-input progress_sale_refresh">
                                         </div>
                                     </div>
                                 </div>
                                 <div>
                                     <div class="fillbox_inline fillbox_inline_width">
                                         <label class="user-name product-modify-span">剩余推盘货值：</label>
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
            </div>
        </div>
    </div>




</body>
</html>