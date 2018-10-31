<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>出资现金流确认</title>
     <%@ include file="../../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
    <link href="${basePath}pages/finance/invest/css/invest.css" rel="stylesheet">
</head>
<body>
    <div class="bg-gray">

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>
                    	<c:if test="${showDetail ne 'Y'}">
                    		出资现金流确认
                    	</c:if>
                    	<c:if test="${showDetail eq 'Y'}">
                    		查看出资现金流确认
                    	</c:if>
                   	</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_content_one">
                <input type="hidden" id="expectInvestAmount" value="${investManagePlan.expectInvestAmount}">
                <form id="stockholderInvestConfirmForm" action="${basePath}finance/stockholderInvestConfirm" method="post">
                	<input type="hidden" id="pid" name="pid" value="${payment.pid}">
                	<input type="hidden" name="prjId" value="${payment.prjId}">
                	<input type="hidden" name="receiverId" value="${payment.receiverId}">
                	<input type="hidden" name="contributiveId" value="${payment.contributiveId}">
                	<input type="hidden" name="stockholderId" value="${payment.stockholderId}">
                    <div class="main_edge">
                        <div class="fillbox-left">
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>项目名称：</label>
                                    <div class="fillin-inputlay layui-form">
                                            <select disabled="disabled" lay-verify="">
                                            	<option value="">-请选择-</option>
                                                <fund:options code="${Globals.PROJECT_NAME}" value="${payment.prjId}"/>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款公司：</label>
                                    <div class="fillin-inputlay layui-form">
                                            <select disabled="disabled" lay-verify="">
                                                <option value="">-请选择-</option>
                                                <fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE}" value="${payment.receiverId}"/>
                                            </select>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>出资主体：</label>
                                    <div class="fillin-inputlay layui-form">
                                            <select disabled="disabled" lay-verify="">
                                                <option value="">-请选择-</option>
                                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}" value="${payment.contributiveId}"/>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款银行：</label>
                                    <div class="fillin-inputlay layui-form">
                                            <select disabled="disabled" lay-verify="">
                                                <option value="">--请选择--</option>
                                                <fund:options code="${Globals.BANK_NAME}" value="${payment.receiverBankId}"/>
                                            </select>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款银行：</label>
                                    <div class="fillin-inputlay layui-form">
                                            <select disabled="disabled" lay-verify="">
                                                <option value="">--请选择--</option>
                                                <fund:options code="${Globals.BANK_NAME}" value="${payment.payBankId}"/>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>收款账户：</label>
                                    <div class="fillin">
                                        <input type="text" disabled="disabled" value="${payment.receiverAccount}" class="fillin-input notoptional">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                                    <div class="fillin">
                                            <input type="text" disabled="disabled" value="${payment.payAccount}" class="fillin-input notoptional">
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款期数：</label>
                                    <div class="fillin">
                                        <input type="text" disabled="disabled" value="${payment.payTerm}" class="fillin-input notoptional">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                                    <div class="fillin">
                                        <input type="text" disabled="disabled" id="payAmount" value="${payment.payAmount}" class="fillin-input notoptional">
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>付款时间：</label>
                                    <div class="fillin">
                                        <input type="text" disabled="disabled" value="<fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd"/>" class="fillin-input notoptional">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline ">
                                    <label class="user-name product-modify-span">付款备注：</label>
                                    <div class="fillin">
                                        <textarea disabled="disabled" class="fillin-input tarea_remarks notoptional">${payment.payRemark}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div>
                            	<div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>财务确认状态：</label>
                                    <div class="fillin-inputlay layui-form form-group">
                                            <select name="financeConfirmStatus" id="financeConfirmStatus" class="form-control" validate-rule="notEmpty" lay-verify="" lay-filter="financeConfirmStatus">
                                                <option value="">--请选择--</option>
                                                <option value="1" <c:if test="${payment.financeConfirmStatus ==1}">selected="selected"</c:if>>已确认</option>
                                                <option value="0" <c:if test="${payment.financeConfirmStatus ==0}">selected="selected"</c:if>>未确认</option>
                                            </select>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>实付金额(万元)：</label>
                                    <div class="fillin form-group">
                                        <input type="text" name="receiverAmount"  validate-rule="notEmpty|rangeNumberBits[18-2]" value="${payment.receiverAmount}" class="fillin-input form-control">
                                    </div>
                                </div>
                            </div>
                            <div>
                            	<div class="fillbox_inline">
                                    <label class="product-modify-span"><span class="textmust">*</span>实付时间：</label>
                                    <div class="fillin form-group">
                                        <input type="text" id="receiverDate" name="receiverDate" value="<fmt:formatDate value="${payment.receiverDate}" pattern="yyyy-MM-dd"/>" validate-rule="notEmpty" readonly="readonly" class="fillin-input form-control" >
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="product-modify-span">预计待付本金(万元)：</label>
                                    <div class="fillin">
                                        <input type="text" id="notPayCorpus" disabled="disabled" class="fillin-input notoptional">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline ">
                                    <label class="product-modify-span">收款备注：</label>
                                    <div class="fillin form-group">
                                        <textarea name="reveiverRemark" validate-rule="charLength[0-4000]" class="fillin-input tarea_remarks form-control">${payment.reveiverRemark}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div id="prompt" style="display: none;">
                                <div class="fillbox_inline ">
                                    <label class="product-modify-span" style="color: red;">提示：</label>
                                    <div class="fillin">
                                       	<span style="color: red; padding: 5px 10px;">未找到该股东【预计出资规模】金额，请检查该股东的资管计划,否则无法计算【预计待付本金】</span>
                                    </div>
                                </div>
                            </div>
                       </div>
                       <c:if test="${showDetail ne 'Y' }">
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
                    
                    <c:if test="${showDetail ne 'Y' }">
	                    <div class="layui-upload fillbox-left" style="width: 80%;">
						    <button type="button" class="layui-btn layui-btn-normal" id="selectFileButton">选择文件</button> 
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
<script type="text/javascript">
	layui.use(['laydate','form','jquery'],function(){
		var laydate = layui.laydate;
		var form = layui.form;
		var $ = jQuery = layui.$;
		
		form.on('select(financeConfirmStatus)',function(data){
			if(data.value){
				validator.resetFormField($("#stockholderInvestConfirmForm"),"financeConfirmStatus");
			}
		});
		
		laydate.render({
			elem: '#receiverDate',
			done: function(value, date, endDate){
				validator.resetFormField($("#stockholderInvestConfirmForm"),"receiverDate");
			}
		});
		var returnedProfit = $("#expectInvestAmount").val();
		
		if(returnedProfit){
			$("#notPayCorpus").val(returnedProfit-$("input[name=receiverAmount]").val());
			
			$("input[name=receiverAmount]").bind('input propertychange',function(){
				var val = $(this).val();
				$("#notPayCorpus").val(returnedProfit-val);
			});
		}else{
			$("#prompt").show();
		}
		
		var confirmStatus;
		
		if("${showDetail}"=="Y"){
			$("#stockholderInvestConfirmForm").find(":input").attr("disabled",true);
			form.render();
		}
		
		validator.validate($("#stockholderInvestConfirmForm"), function(dom){
			if(confirmStatus=="1"){
				layer.msg("财务确认状态为已确认，不可再次确认",{icon:7});
				return ;
			}
			
			if($("#notPayCorpus").val()<0){
				layer.msg("收款金额不可大于付款金额",{icon:7});
				return ;
			}
			
			if($("#financeConfirmStatus").val()=="1"){
				layer.confirm("财务状态确认之后，不可更改！ 确认保存？",function(){
					$.ajax({
		      		    url: dom.attr("action"),
		      		    cache: true,
		      		    type: "POST",
		      		    data: $("#stockholderInvestConfirmForm").serialize(),
		      		    async: false,
		      			success : function(msg) { //表单提交后更新页面显示的数据
		      				if (msg && msg["successMsg"]) {
		      					layer.msg(msg["successMsg"],{icon: 1});
		      					if(msg["readonly"]){
		      						dom.find(":input").attr("disabled",true);
		      						confirmStatus = dom.find("select[name=financeConfirmStatus]").val();
		      						form.render();
		      					}
		      				} else {
		      					layer.msg(msg["errMsg"], {time:1000,icon:7});
		      				}
		      			},
		      			error:function(msg){
		      				alert(msg["errMsg"]);
		      			}
		      		});
				},function(){});
				
			}else{
				$.ajax({
	      		    url: dom.attr("action"),
	      		    cache: true,
	      		    type: "POST",
	      		    data: $("#stockholderInvestConfirmForm").serialize(),
	      		    async: false,
	      			success : function(msg) { //表单提交后更新页面显示的数据
	      				if (msg && msg["successMsg"]) {
	      					layer.msg(msg["successMsg"],{icon: 1});
	      				} else {
	      					layer.msg(msg["errMsg"], {time:1000,icon:7});
	      				}
	      			},
	      			error:function(msg){
	      				alert(msg["errMsg"]);
	      			}
	      		});
			}
		});
		var pid = $("#stockholderInvestConfirmForm").find("input[name=pid]").val();
		var data = {pid:pid};
		uploadFiles($("#selectFileButton"), $("#fileList"), $("#uploadFileButton"),$("#data_list"), basePath+"finance/uploadStockholderPaymentAccessory",data);
		
		var data = {pid:pid};
		var url = basePath+"finance/queryStockholderInvestAccessoryList";
		var colNames = ["文件名","文件类型","上传时间","上传人","操作"]
		var colModel = [
		                 {name:"file_all_name", index:"file_all_name",sortable:false},
		                 {name:"accessory_type_name", index:"accessory_type_name",sortable:false},
		                 {name:"create_date", index:"create_date",sortable:false},
		                 {name:"user_name", index:"user_name",sortable:false},
		                 {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	 var url = "'"+rowObject.file_url+'&filePid='+rowObject.file_pid+'&fileName='+rowObject.file_all_name+"'";
		                	 var del= '<a onclick="deleteFile('+encodeURI(url)+')" class="table_bnt">删除</a>';
		                	 if("${showDetail}"=="Y"){
		                		 del = '';
		                	 }
		                	 return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	 		'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>'+del;
		                 }}
		                  
		                ]
		var complateFunc = function(){};
		loadData(url, colNames, colModel, complateFunc,data);
	});
	
	</script>
</html>