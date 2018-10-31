<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增股东付款请求</title>
<script type="text/javascript" src="${basePath}pages/trade/js/project_stocker_pay/share_pay_add.js"></script>
</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
		<input type="text" hidden="hidden" id="editType"  value="${type}"/>
		<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span id="editTitleSpanName">新增股东付款请求</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_content_one">
                	<form id = "stockHolderPay_formId"  method="post">
                		<input type="text" hidden="hidden" id="stockPaymentPid" name="paymentPid" value="${resultMap.pid}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="projectPid" name="prjId" value="${resultMap.prj_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="businessPrjInfoPid" name="businessPrjInfoPid" value="${resultMap.receiver_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="stockholderPid" name="stockholderPid" value="${resultMap.stockholder_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="receive_corpId"  value="${resultMap.corporation_info_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="contributiveId" name="contributiveId" value="${resultMap.contributive_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="receiverBankId" name="receiverBankId" value="${resultMap.receiver_bank_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="payBankId" name="payBankId" value="${resultMap.pay_bank_id}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="receiverAccount" name="receiverAccount" value="${resultMap.receiver_account}"/>
                		<input type="text" class="hiddenInputElem" hidden="hidden" id="payAccount" name="payAccount" value="${resultMap.pay_account}"/>
	                	<div class="main_edge">
	                        <div class="fillbox-left">
	                            <div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select class="disAbledElem" id="projectNameSelect" lay-filter="projectNameFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                                <c:forEach items="${projectList}" var="project">
                                                	<option value="${project.pid}">${project.projectName}</option>
                                                </c:forEach>
                                            </select>
	                                    </div>
	                                    <span id="projectPidWarn" class="warn_span">*必填</span>
	                                </div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select class="disAbledElem" id="receiverNameSelect" lay-filter="receiverNameFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                            </select>
	                                    </div>
	                                    <span id="businessPrjInfoPidWarn" class="warn_span">*必填</span>
	                                </div>
	                            </div>
	                            <div>
	                                <div class="fillbox_inline fillbox_inline_width" >
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资主体：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select class="disAbledElem" id="contributiveNameSelect" lay-filter="contributiveNameFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                            </select>
	                                    </div>
	                                    <span id="contributiveIdWarn" class="warn_span">*必填</span>
	                                </div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select  class="disAbledElem" id="receiveBankNameSelect" lay-filter="receiveBankNameFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                            </select>
	                                    </div>
	                                    <span id="receiverBankIdWarn" class="warn_span">*必填</span>
	                                </div>
	                            </div>
	                            <div>
	                                <div class="fillbox_inline fillbox_inline_width" >
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select class="disAbledElem" id="payBankNameSelect" lay-filter="payBankNameFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                            </select>
	                                    </div>
	                                    <span id="payBankIdWarn" class="warn_span">*必填</span>
	                                </div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select class="disAbledElem" id="receiveAccountSelect" lay-filter="receiveAccountFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                            </select>
	                                    </div>
	                                    <span id="receiverAccountWarn" class="warn_span">*必填</span>
	                                </div>
	                            </div>
	                            <div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
	                                    <div class="fillin-inputlay  layui-form">
                                            <select class="disAbledElem" id="payAccountSelect" lay-filter="payAccountFilter" lay-verify="" lay-search>
                                                <option value="" title=''>---请选择---</option>
                                            </select>
	                                    </div>
	                                    <span id="payAccountWarn" class="warn_span">*必填</span>
	                                </div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
	                                    <div class="fillin">
	                                        <input type="text"  id="payTerm" name="payTerm" value="${resultMap.pay_term}" class="fillin-input disAbledElem">
	                                        <span id="payTermWarn" class="warn_span">*必填</span>
	                                    </div>
	                                </div>
	                            </div>
	                            <div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>预计付款金额(万元)：</label>
	                                    <div class="fillin">
	                                        <input  type="text"  id="payAmount" name="payAmount" value="${resultMap.show_pay_amount}" class="fillin-input disAbledElem">
	                                        <span id="payAmountWarn" class="warn_span">*必填</span>
	                                    </div>
	                                </div>
	                                <div class="fillbox_inline fillbox_inline_width">
	                                    <label class="user-name product-modify-span"><span class="textmust">*</span>预计付款时间：</label>
	                                    <div class="fillin">
                                            <input autocomplete="off"  id="payDate" name="payDate" type="text" value="<fmt:formatDate value='${resultMap.pay_date}'  pattern='yyyy-MM-dd'/>" class="fillin-input disAbledElem">
                                            <span id="payDateWarn" class="warn_span">*必填</span>
                                        </div>
	                                </div>
	                            </div>
	                            <div>
	                                <div class="fillbox_inline">
	                                    <label class="user-name product-modify-span">付款备注：</label>
	                                    <div class="fillin">
	                                        <textarea class="fillin-input tarea_remarks disAbledElem" style="width: 842px" id="payRemark" name="payRemark">${resultMap.pay_remark}</textarea>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="btn_edge btn_edge_left" id="saveAndBackButtonDiv">
	                        <a href="${basePath}/projectStockerController/stoPayList" class="btn_cancel btn_all">
	                            <img src="${basePath}/common/img/cancel.png">
	                            <span>返回</span>
	                        </a>
	                        <button type="button" class="btn_keep btn_all" id="stockHolderPay_submitButtonId">
	                            <img src="${basePath}/common/img/keep.png">
	                            <span>保存</span>
	                        </button>
	                    </div>
                	</form>
                	
					<div style="margin-left: 40px" id="project_progress_buttons"> 
                       	<button type="button" style="margin-right: 20px" hidden="hidden" class="btn_keep btn_all" id="stockHolderPay_fileSelectList">
                               <img src="${basePath}/common/img/folder.png">
                               <span>选择文件</span>
                           </button>
                       	<button type="button" style="margin-right: 20px"  class="btn_keep btn_all" id="stockHolderPay_fileSelectList2">
                               <img src="${basePath}/common/img/folder.png">
                               <span>选择文件</span>
                           </button>
                           <button  type="button" class="btn_upload btn_upload_box_shadow" hidden="hidden" id="stockHolderPay_startUploadListAction">
                               <img src="${basePath}/common/img/uploaded.png">
                               <span>开始上传</span>
                           </button>
                           <button  type="button" class="btn_upload btn_upload_box_shadow" id="stockHolderPay_startUploadListAction2">
                               <img src="${basePath}/common/img/uploaded.png">
                               <span>开始上传</span>
                           </button>
                      	</div>
			   		<div id="stockHolderPay_fileUploadDiv" class="layui-upload" style="display: none;">
					  <div style="margin: 0px 40px 0px 40px;width: 76%" class="layui-upload-list">
					    <table class="layui-table">
					      <thead>
					        <tr><th>文件名</th>
					        <th>大小</th>
					        <th>状态</th>
					        <th>操作</th>
					      </tr></thead>
					      <tbody id="stockHolderPay_fileUploadList">
					      
					      </tbody>
					    </table>
					  </div>
					</div> 
					
                    <div class="main_edge">
                        <div class="title_bggray">
                            <span>附件材料</span>
                        </div>
                        <!--表格-->
                        <div class="tabled_datum">
                            <table class="table table-bordered table-hover"  id="stockHolderPayFile_tableId">
                                <thead>
                                <tr>
                                    <th>文件名</th>
                                    <th>文件类型</th>
                                    <th>上传时间</th>
                                    <th>上传人</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="stockHolderPayFile_tbodyId">
                                	
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

</body>
</html>