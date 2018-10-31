var form='';
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
//	    var $ = jQuery = layui.$;
		
		validator.validate($("#stockholderPaymentForm"));
		laydate.render({
			elem: '#payDate',
			done: function(value, date, endDate){
				// 校验日期不为空
//				validator.resetFormField($("#stockholderPaymentForm"),"investStartDate");
			}
		});
		
		/*获取收款账户(即项目公司对应的企业银行账户)*/
		form.on('select(receiverBankId)',function(data){
			
			if(data.value){
//				validator.resetFormField($("#stockholderPaymentForm"),"receiverBankId");
			}
			$("#receiverAccount").find("option[value!='']").remove();
			form.render("select");
			var businessPrjInfoCorpPid = $("#stockholderPaymentForm").find("input[name=businessPrjInfoCorpPid]").val();
			var bankId = data.value;
			loadReceivedAccount(businessPrjInfoCorpPid,bankId,null);
		});
		
		/*获取付款账户(即股东对应的企业银行账户)*/
		form.on('select(payBankId)',function(data){
			if(data.value){
//				validator.resetFormField($("#stockholderPaymentForm"),"payBankId");
			}
			$("#payAccount").find("option[value!='']").remove();
			form.render("select");
			var corpId = $("#stockholderPaymentForm").find("input[name=corPid]").val();
			var bankId = data.value;
			loadPayAccount(corpId,bankId,null);
		});
		
	});
	
	/*---------项目股东付款请求  ----------*/
	$("#Payment_request1").on("click",function(){
		
		document.getElementById("stockholderPaymentForm").reset();
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			//项目名称置灰
			$("#prjId").attr("disabled",true);
			$("#businessPrjInfoPid").attr("disabled",true);
			$("#contributiveId").attr("disabled",true);
			
			// 回显下拉选择框
			$("#stockholderPaymentForm").find("select[name=prjId]").val(rowData.projectInfoPid);
			$("#stockholderPaymentForm").find("select[name=businessPrjInfoPid]").val(rowData.businessPrjInfoPid);
			$("#stockholderPaymentForm").find("select[name=contributiveId]").val(rowData.corp_id);
			
			$("#stockholderPaymentForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
			
			// 加载收款银行信息(即项目公司对应的企业银行信息)
			var receiverId = rowData.businessPrjInfoCorpPid;
			$("#stockholderPaymentForm").find("input[name=businessPrjInfoCorpPid]").val(receiverId);
			$("#receiverBankId").find("option[value!='']").remove();
			form.render("select");
			loadBankInfo(receiverId,null,"receive");
			// 加载付款银行信息(即股东对应的企业银行信息)
			var paymengtId = rowData.corp_id;
			$("#stockholderPaymentForm").find("input[name=corPid]").val(rowData.corp_id);
			$("#payBankId").find("option[value!='']").remove();
			form.render("select");
			loadBankInfo(paymengtId,null,"pay");
			
			layer.open({
				type: 1,
				title: ['项目股东付款请求','font-size:18px;'],
				area: ['1040px', '620px'],
				btnAlign: 'c',
				content: $('#Payment_request'),
				success: uploadStockPaymentAccessory(),
				btn: ['保存', '取消'],
				btn1: function(index, layero){
					$("#prjId").attr("disabled",false);
					$("#businessPrjInfoPid").attr("disabled",false);
					$("#contributiveId").attr("disabled",false);
					
					var data = $("#stockholderPaymentForm").serialize();
					$.ajax({
						url: $("#stockholderPaymentForm").attr("action"),
						cache: true,
						type: "POST",
						data: data,
						async: false,
						success : function(msg) { //表单提交后更新页面显示的数据
							if (msg && msg["successMsg"]) {
								layer.msg(msg["successMsg"],{icon: 1});
								$("#stockholderPaymentForm").find("input[name=stockPaymentPid]").val(msg["stockPaymentPid"]);
								$("#stock_data_list").trigger("reloadGrid");
//								validator.resetForm($("#stockholderPaymentForm"));
							} else {
								layer.msg(msg["errMsg"], {time:1000,icon:7});
							}
						},
						error:function(msg){
							alert(msg["errMsg"]);
						}
					});
				},
				btn2: function(index, layero){
					//按钮【按钮三】的回调
				}
			});
		} else if(rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------查看股东付款信息  ----------*/
	$("#viewStockPaymentReq").on("click",function(){
		
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "tradeManage/toStockPaymentInfoPage.action?corp_id="+rowData.corp_id+"&businessPrjPid="+rowData.businessPrjInfoPid+"&operation=modify&stockholderName="+rowData.name+"&viewStockPayment=viewStockPayment";
		if(rowsId.length == 1) {
			window.location.href = encodeURI(encodeURI(url));
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/**------------------------------------------------------- 加载股东付款明细列表  开始 ---------------------------------------------------*/
	var viewStockPayment = $("#viewStockPayment").val();
	if(viewStockPayment == "viewStockPayment") {
		
		// 股东对应的企业id
		var corp_id = $("#corp_id").val();
		var data = {corp_id:corp_id};
		var url = basePath+"tradeManage/queryStockPaymentInfoList";
		var colNames = ["prj_id","receiver_id","contributive_id","receiver_bank_id","pay_bank_id","reveiver_remark","businessPrjInfoCorpPid",
		                "收款公司","收款金额(万元)","收款账号","收款时间","付款期数","付款金额(万元)","出资股东","付款账号","付款时间","付款备注","财务确认状态","付款凭证"];
		var colModel = [
		                {name: "prj_id",index: "prj_id", sortable :false, hidden : true},
		                {name: "receiver_id",index: "receiver_id", sortable :false, hidden : true},
		                {name: "contributive_id",index: "contributive_id", sortable :false, hidden : true},
		                {name: "receiver_bank_id",index: "receiver_bank_id", sortable :false, hidden : true},
		                {name: "pay_bank_id",index: "pay_bank_id", sortable :false, hidden : true},
		                {name: "reveiver_remark",index: "reveiver_remark", sortable :false, hidden : true},
		                {name: "businessPrjInfoCorpPid",index: "businessPrjInfoCorpPid", sortable :false, hidden : true},
		                
		                {name: "receiver_name",index: "receiver_name",sortable :false},
		                {name: "receiver_amount",index: "receiver_amount",sortable :false},
		                {name: "receiver_account",index: "receiver_account",sortable :false},
		                {name: "receiver_date",index: "receiver_date",sortable :false},
		                {name: "pay_term",index: "pay_term", sortable :false},
		                {name: "pay_amount",index: "pay_amount", sortable :false},
		                {name: "contributive_name",index: "contributive_name", sortable :false},
		                {name: "pay_account",index: "pay_account", sortable :false},
		                {name: "pay_date",index: "pay_date", sortable :false},
		                {name: "pay_remark",index: "pay_remark", sortable :false},
		                {name: "finance_confirm_status",index: "finance_confirm_status", sortable :false,
		                	formatter:function(cellvalue, options, rowObject){
		                		var financeConfirmStatus = rowObject.finance_confirm_status;
		                		if(financeConfirmStatus == '0') {
		                			return "未确认";
		                		} else if(financeConfirmStatus == '1') {
		                			return "已确认";
		                		} else {
		                			return "";
		                		}
		                	}
		                },
		                {name: "show_accessory",index: "show_accessory", sortable :false,formatter:function(cellvalue, options, rowObject){
					    	var url = "'"+basePath+"finance/queryStockholderInvestAccessoryList"+"'";
					    	var paymentId = "'"+rowObject.pid+"'";
					    	return '<a href="javascritp:;" class="table_bnt show_accessory" onclick="showAccessoryList('+url+','+paymentId+')">查看</a>';
					    }}
		                ];
		var complateFunc = function(){}
		loadData1("data_list","page_list",url, colNames, colModel, complateFunc, data);
	}
		
	/**------------------------------------------------------- 加载股东付款明细列表  结束 ---------------------------------------------------*/
	
	/*-----------查看付款明细 ----------*/
	$("#Pay_details1").on("click",function(){
		
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			$("#Pay_details").find(":input").attr("disabled",true);
			form.render();
			
			// 加载收款银行信息(即项目公司对应的企业银行信息)
			var receiverId = rowData.businessPrjInfoCorpPid;
			// 收款公司(项目公司中间表主键)
			$("#Pay_details").find("select[name=businessPrjInfoPid]").val(rowData.receiver_id);
			var receiverBankId = rowData.receiver_bank_id;
			$("#receiverBankId").find("option[value!='']").remove();
			form.render("select");
			loadBankInfo(receiverId,receiverBankId,"receive");
			//加载收款账户
			var receiverAccount = rowData.receiver_account;
			loadReceivedAccount(receiverId,receiverBankId,receiverAccount);
			
			// 加载付款银行信息(即股东对应的企业银行信息)
			var paymengtId = rowData.contributive_id;
			// 出资股东
			$("#Pay_details").find("select[name=contributiveId]").val(rowData.contributive_id);
			var payBankId = rowData.pay_bank_id;
			$("#payBankId").find("option[value!='']").remove();
			form.render("select");
			loadBankInfo(paymengtId,payBankId,"pay");
			//加载付款账户
			var payAccount = rowData.pay_account;
			loadPayAccount(paymengtId,payBankId,payAccount);
			
			// 回显下拉选择框
			$("#Pay_details").find("select[name=prjId]").val(rowData.prj_id);
			form.render('select');
			$("#Pay_details").find("input[name=receiverAmount]").val(rowData.receiver_amount);
			$("#Pay_details").find("input[name=payAmount]").val(rowData.pay_amount);
			$("#Pay_details").find("input[name=receiverDate]").val(formatDate(rowData.receiver_date));
			$("#Pay_details").find("input[name=payTerm]").val(rowData.pay_term);
			$("#Pay_details").find("input[name=payDate]").val(formatDate(rowData.pay_date));
			$("#Pay_details").find("textarea[name=payRemark]").val(rowData.pay_remark);
			$("#Pay_details").find("textarea[name=reveiverRemark]").val(rowData.reveiver_remark);
			var financeConfirmStatus = rowData.finance_confirm_status;
			var finance_confirm_status = '';
			if(financeConfirmStatus == '未确认') {
				finance_confirm_status = '0';
			} else if(financeConfirmStatus == '已确认') {
				finance_confirm_status = '1';
			}
			$("input:radio[name='financeConfirmStatus'][value='"+finance_confirm_status+"']").attr("checked",'checked');
			form.render();
			
			layer.open({
				type: 1,
				title: ['项目股东付款请求','font-size:18px;'],
				area: ['1040px', '620px'],
				btnAlign: 'c',
				content: $('#Pay_details'),
				/*success: uploadStockPaymentAccessory(),*/
				btn: ['关闭'],
				btn1: function(index, layero){
					layer.close(index);
				},
				btn2: function(index, layero){
				}
			});
		} else if(rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	
});




// 项目股东付款请求   上传附件
function uploadStockPaymentAccessory() {
	
	var stockPaymentPid = $("#stockholderPaymentForm").find("input[name=stockPaymentPid]").val();
	var data = {pid:stockPaymentPid};
	$("#stockPayment_select_file").on("click",function(){
		stockPaymentPid = $("#stockholderPaymentForm").find("input[name=stockPaymentPid]").val();
		if(stockPaymentPid!=""){
			$("#stockPayment_selectFileButton").trigger("click");
		}else{
			layer.msg("请先保存数据",{icon:7});
		}
	});
	// 异步     第二次刷新附件列表时，不自动查询后台数据库的bug;加状态
	if(loadFlg){
		$("#stockPayment_data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		uploadFiles($("#stockPayment_selectFileButton"), $("#stockPayment_fileList"), $("#stockPayment_uploadFileButton"),$("#stockPayment_data_list"), basePath+"finance/uploadInvestSubjectPaymentAccessory",data);
		var url = basePath+"finance/queryInvestSubjectAccessoryList";
		loadStockPaymentAccessory(url, data, null, "stockPayment_data_list", "stockPayment_page_list");
	}
}

/**
 * 加载银行信息
 * corpId:企业pid
 * bankId：银行pid
 * mark：收付款类型
 */
function loadBankInfo(corpId,bankId,mark){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestSubjectBankInfo",
	    type: "POST",
	    data:{
	    	corpId: corpId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				if("receive"==mark){
					var receiverSelected = '';
					$.each(result,function(i,n){
						if(bankId == n.pid){
							receiverSelected = 'selected="selected"';
						}else{
							receiverSelected= '';
						}
						$("#receiverBankId").append('<option value="'+n.pid+'"'+receiverSelected+'>'+n.name+'</option>');
					});
				}
				if("pay"==mark){
					var paySelected = '';
					$.each(result,function(i,n){
						if(bankId == n.pid){
							paySelected = 'selected="selected"';
						}else{
							paySelected = '';
						}
						$("#payBankId").append('<option value="'+n.pid+'"'+paySelected+'>'+n.name+'</option>');
					});
					
				}
				layui.use('form',function(){
					var form = layui.form;
					form.render("select");
				});
			}
		},
		error:function(msg){
			alert(msg["errMsg"]);
		}
	});
}

/**加载收款银行账户信息*/
function loadReceivedAccount(corpId,bankId,receiverAccount){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestSubjectBankAccount",
	    type: "POST",
	    data:{
	    	corpId: corpId,
	    	bankId: bankId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(receiverAccount == n){
						selected = 'selected="selected"';
					}else{
						selected= '';
					}
					$("#receiverAccount").append('<option value="'+n+'"'+selected+'>'+n+'</option>');
				});
				layui.use('form',function(){
					var form = layui.form;
					form.render("select");
				});
			}
		},
		error:function(msg){
			alert(msg["errMsg"]);
		}
	});
}

/**加载付款银行账户信息*/
function loadPayAccount(corpId,bankId,payAccount){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestSubjectBankAccount",
	    type: "POST",
	    data:{
	    	corpId: corpId,
	    	bankId: bankId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(payAccount == n){
						selected = 'selected="selected"';
					}else{
						selected= '';
					}
					$("#payAccount").append('<option value="'+n+'"'+selected+'>'+n+'</option>');
				});
				layui.use('form',function(){
					var form = layui.form;
					form.render("select");
				});
			}
		},
		error:function(msg){
			alert(msg["errMsg"]);
		}
	});
}

/**
 * 加载附件列表
 */
function loadStockPaymentAccessory(url,data,pageStatus,dataListId,pageListId){
	var colNames = ["文件名","文件类型","上传时间","上传人","操作"]
	var colModel = [
	                 {name:"file_all_name", index:"file_all_name",sortable:false},
	                 {name:"accessory_type_name", index:"accessory_type_name",sortable:false},
	                 {name:"create_date", index:"create_date",sortable:false},
	                 {name:"user_name", index:"user_name",sortable:false},
	                 {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
	                	 var url = "'"+rowObject.file_url+'&filePid='+rowObject.file_pid+'&fileName='+rowObject.file_all_name+"'";
	                	 var del= '<a onclick="deleteFile('+encodeURI(url)+','+dataListId+')" class="table_bnt file_del">删除</a>';
	                	 if(pageStatus=="view"||pageStatus=="readonly"){
	                		 del='<a onclick="deleteFile('+encodeURI(url)+','+dataListId+')" class="table_bnt file_del op_hide">删除</a>';
	                	 }
	                	 return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
	                	 		'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>'+del;
	                 }}
	                  
	                ]
	var complateFunc = function(){
		loadFlg = true;
	};
	loadData1(dataListId,pageListId,url,colNames,colModel,complateFunc,data);
	
}

