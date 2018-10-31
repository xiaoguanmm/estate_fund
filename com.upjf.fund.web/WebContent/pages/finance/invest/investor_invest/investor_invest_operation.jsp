<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增投资人付款请求</title>
     <%@ include file="../../../../common/common.jsp"%>
    <link href="${basePath}pages/finance/invest/css/invest.css" rel="stylesheet">
    <script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
    <script type="text/javascript" src="${basePath}pages/finance/invest/js/invest_subject.js"></script>
	<script type="text/javascript">
	layui.use(['laydate','form','jquery'],function(){
		var laydate = layui.laydate;
		var form = layui.form;
		var $ = jQuery = layui.$;
		if("${operation}"!="add"){
			init();
		}else{
			initPayTermSelect();
		}
		function init(){
			var prjId = "${payment.prjId}"; //项目id
			var stockholderId = "${payment.stockholderId}";//股东公司
			var receiverId = "${payment.receiverId}"; //收款公司
			var contributiveId = "${payment.contributiveId}"; //出资主体
			var receiverBankId = "${payment.receiverBankId}"; //收款银行
			var payBankId = "${payment.payBankId}"; //付款银行
			var receiverAccount = "${payment.receiverAccount}"; //收款账户
			var payAccount = "${payment.payAccount}"; //付款账户
			var payTerm = "${payment.payTerm}"; //付款期数
			/*根据项目异步加载所属股东*/
			loadStockholder(prjId, stockholderId);
			/*根据项目公司异步加载收款公司(投资主体)*/
			loadReceiverCorpInvestSubject(prjId, receiverId);
			var parentId = $("#receiverId").find("option[value="+receiverId+"]").attr("parent-id");
			/*根据收款公司(投资主体)异步加载出资主体(投资人)*/
			loadInvestorCorp(parentId, contributiveId);
			/*加载收款银行信息*/
			loadBankInfo(receiverId,receiverBankId,"receive");
			/*加载付款银行信息*/
			loadBankInfo(contributiveId,payBankId,"pay");
			/*加载收款银行账户信息*/
			loadReceivedAccount(receiverId,receiverBankId,receiverAccount);
			/*加载付款银行账户信息*/
			loadPayAccount(contributiveId,payBankId,payAccount);
			/*初始化付款期数*/
			initPayTermSelect(payTerm);
		}
		if("${operation}"=="view"){
			$("#investForm").find(":input").attr("disabled",true);
			form.render();
		}
		laydate.render({
			elem: '#payDate',
			done: function(value, date, endDate){
				validator.resetFormField($("#investForm"),"payDate");
			}
		});
		validator.validate($("#investForm"), function(dom){
			$.ajax({
      		    url: dom.attr("action"),
      		    cache: true,
      		    type: "POST",
      		    data: dom.serialize(),
      		    async: false,
      			success : function(msg) {
      				if (msg && msg["successMsg"]) {
      					layer.msg(msg["successMsg"],{icon: 1});
      					dom.find("input[name=pid]").val(msg.pid);
      				} else {
      					layer.msg(msg["errMsg"], {time:1000,icon:7});
      				}
      			},
      			error:function(msg){
      				alert(msg["errMsg"]);
      			}
      		}); 
		});
			var pid = $("#investForm").find("input[name=pid]").val();
			var data = {pid:pid};
			$("#select_file").on("click",function(){
				var paymentId = $("#investForm").find("input[name=pid]").val();
				if(paymentId!=""){
					$("#selectFileButton").trigger("click");
				}else{
					layer.msg("请先保存数据",{icon:7});
				}
			});
			uploadFiles($("#selectFileButton"), $("#fileList"), $("#uploadFileButton"),$("#data_list"), basePath+"finance/uploadInvestorPaymentAccessory",data);
			
			var url = basePath+"finance/queryInvestorInvestAccessoryList";
			loadAccessoryFiles(url, data, "${operation}");
	});
	</script>
</head>
<body>
    <div class="bg-gray">

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                      <span>
	                    <c:choose>
	                    	<c:when test="${operation eq 'modify'}">
	                    		修改投资人付款请求
	                    	</c:when>
	                    	<c:when test="${operation eq 'view'}">
	                    		查看投资人付款请求
	                    	</c:when>
	                    	<c:otherwise>
	                    		新增投资人付款请求
	                    	</c:otherwise>
	                    </c:choose>
                    </span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_content_one">
                <form id="investForm" action="${basePath}finance/addInvestorInvest" method="post">
                	<input type="hidden" id="pid" name="pid" value="${payment.pid}">
                	<input type="hidden" id="investType" name="investType" value="3">
                	<!-- 所属股东 -->
                    <div class="main_edge">
                        <div class="fillbox-left">
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>项目名称：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="prjId" id="prjId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="prjId">
                                            	<option value="">--请选择--</option>
                                                <fund:options code="${Globals.PROJECT_NAME}" value="${payment.prjId}"/>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>项目股东：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="stockholderId" id="stockholderId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="stockholderId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                
                            </div>
                            <div>
                            	<div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款公司：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="receiverId" id="receiverId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>出资主体：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="contributiveId" id="contributiveId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="contributiveId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                
                            </div>
                            <div>
                           	 <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款银行：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="receiverBankId" id="receiverBankId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverBankId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款银行：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="payBankId" id="payBankId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="payBankId">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                
                            </div>
                            <div>
                            <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款账户：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                     	<select name="receiverAccount" id="receiverAccount" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverAccount">
                                                <option value="">--请选择--</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款账户：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                    	<select name="payAccount" id="payAccount" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="payAccount">
                                          <option value="">--请选择--</option>
                                       </select>
                                    </div>
                                </div>
                               
                            </div>
                            <div>
                             <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                                    <div class="fillin form-group">
                                        <input type="text" name="payAmount" id="payAmount" validate-rule="notEmpty|rangeNumberBits[14-2]" value="${payment.payAmount}" class="fillin-input form-control">
                                    </div>
                                </div>
                            <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款期数：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                    	<select name="payTerm" id="payTerm" validate-rule="notEmpty" class="form-control" lay-verify="" lay-filter="payTerm">
                                          <option value="">--请选择--</option>
                                       </select>
                                    </div>
                                </div>
                               
                            </div>
                            <div>
                             <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款时间：</label>
                                    <div class="fillin form-group">
                                        <input type="text" name="payDate" validate-rule="notEmpty" readonly="readonly" id="payDate" value="<fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd"/>" class="fillin-input form-control">
                                    </div>
                                </div>
                                <div class="fillbox_inline ">
                                    <label class="product-modify-span">付款备注：</label>
                                    <div class="fillin form-group">
                                        <textarea validate-rule="charLength[0-4000]" name="payRemark" class="fillin-input tarea_remarks form-control" style="width: 305px;">${payment.payRemark}</textarea>
                                    </div>
                                </div>
                            </div>
                       </div>
                       <c:if test="${operation eq 'add' or operation eq 'modify'}">
	                       <div class="btn_edge btn_edge_left">
		                        <a href="javascript:;" onclick="history.go(-1);" class="btn_cancel btn_all">
		                            <img src="${basePath}common/img/cancel.png">
		                            <span>取消</span>
		                        </a>
		                        <button type="submit" class="btn_keep btn_all">
		                            <img src="${basePath}common/img/keep.png">
		                            <span>保存</span>
		                        </button>
	                    	</div>
                       </c:if>
                    </div>
                    </form>
                    
                    <c:if test="${operation eq 'add' or operation eq 'modify'}">
	                    <div class="layui-upload fillbox-left" style="width: 80%;">
	                    	<button type="button" class="layui-btn layui-btn-normal" id="select_file">选择文件</button> 
						    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="selectFileButton">选择文件</button> 
						    <button type="button" class="layui-btn" id="uploadFileButton">开始上传</button>
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
						      <tbody id="fileList"></tbody>
						    </table>
						   </div>
					  </div> 
                    </c:if>
                    
					
                    <div class="main_edge">
                        <div class="title_bggray">
                            <span>附件材料</span>
                        </div>
                         <!--表格-->
	                    <div class="tabled-two">
	                        <table id="data_list" class="table table-bordered table-hover"></table>
	                        <div id="page_list"></div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

</body>
</html>