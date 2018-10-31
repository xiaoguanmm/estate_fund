var basePath;
var form;
var upload;
var files;
var uploadListIns;
var uploadTotal;
var uploadSuccess;
var type;
$ (function () {
	basePath = $('#basePathId').val();
	type = $('#editType').val();
	
	//表格中点击th里的 选择按钮，下面会全部被选中
	var flag = true;
	$(".hook_all").click(function() {
		var cb = $("input[type=checkbox]");
		for(var i = 0; i < cb.length; i++) {
			cb[i].checked = flag;
		}
		flag = !flag;
	});
	
	
	//初始化股东付款请求附件分页栏
	installPageHtml("stockHolderPayFile");
	
	//渲染上传文件
	layui.use('upload', function(){
		  upload = layui.upload;
		  installLayuiUpload('stockHolderPay_fileSelectList','stockHolderPay_fileUploadList','stockHolderPay_startUploadListAction','stockHolderPay_fileUploadDiv',basePath+'/projectStockerController/uploadStockHolderPayFile');
	});
	
	
    //引入下拉框,并设置值变化时更新隐藏域
	layui.use('form',function(){
		form =layui.form;
		
		//项目名称
		form.on('select(projectNameFilter)', function(data){
			var projectId =  data.value;
			$('#projectPid').val(projectId);
			
			var elemId = ['projectPid'];
			cheackAndShowWarns(elemId);
			
			//获取项目公司信息
			if(projectId != null && projectId !=''){
				var hiddenElemIds = ['businessPrjInfoPid','receive_corpId','contributiveId','receiverBankId','payBankId','receiverAccount','payAccount'];
				var selectIds = ['receiverNameSelect','contributiveNameSelect','receiveBankNameSelect','payBankNameSelect','receiveAccountSelect','payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				getRelationData(projectId,'','receiverNameSelect',basePath+'/projectStockerController/getReceiveCompany');
				form.render('select');
			}else{
				var hiddenElemIds = ['businessPrjInfoPid','receive_corpId','contributiveId','receiverBankId','payBankId','receiverAccount','payAccount'];
				var selectIds = ['receiverNameSelect','contributiveNameSelect','receiveBankNameSelect','payBankNameSelect','receiveAccountSelect','payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				form.render('select');
			}
		});
		
		//收款公司
		form.on('select(receiverNameFilter)', function(data){
			var receiverId =  data.value;
			var targetElem =  data.elem[1];
			$('#businessPrjInfoPid').val(receiverId);
			var elemId = ['businessPrjInfoPid'];
			cheackAndShowWarns(elemId);
			
			//获取股东信息,及收款银行
			if(receiverId != null && receiverId !=''){
				var hiddenElemIds = ['contributiveId','receiverBankId','payBankId','receiverAccount','payAccount'];
				var selectIds = ['contributiveNameSelect','receiveBankNameSelect','payBankNameSelect','receiveAccountSelect','payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				
				var projectIdVal = $('#projectPid').val();
				getRelationData(receiverId,projectIdVal,'contributiveNameSelect',basePath+'/projectStockerController/getContributiveCompany');
				
				if(targetElem != null && targetElem != 'undefined'){
					var corporationPid =  data.elem[1].title;
					$('#receive_corpId').val(corporationPid);
					getRelationData(corporationPid,'','receiveBankNameSelect',basePath+'/projectStockerController/getBankInfo');
				}
				form.render('select');
			}else{
				var hiddenElemIds = ['contributiveId','receiverBankId','payBankId','receiverAccount','payAccount'];
				var selectIds = ['contributiveNameSelect','receiveBankNameSelect','payBankNameSelect','receiveAccountSelect','payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				form.render('select');
			}

			
		});
		
		//出资股东
		form.on('select(contributiveNameFilter)', function(data){
			var contributiveId =  data.value;
			$('#contributiveId').val(contributiveId);
			
			var elemId = ['contributiveId'];
			cheackAndShowWarns(elemId);
			
			var targetElem =  data.elem.selectedOptions["0"];
			if(targetElem != null && targetElem != 'undefined'){
				var stockHolderId = data.elem.selectedOptions["0"].title;
				$('#stockholderPid').val(stockHolderId);
			}
			
			//获取付款银行
			if(contributiveId != null && contributiveId !=''){
				var hiddenElemIds = ['payBankId','payAccount'];
				var selectIds = ['payBankNameSelect','payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				getRelationData(contributiveId,'','payBankNameSelect',basePath+'/projectStockerController/getBankInfo');
				form.render('select');
			}else{
				var hiddenElemIds = ['payBankId','payAccount'];
				var selectIds = ['payBankNameSelect','payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				form.render('select');
			}
		});
		
		//收款银行
		form.on('select(receiveBankNameFilter)', function(data){
			var receiveBankId =  data.value;
			$('#receiverBankId').val(receiveBankId);
			
			var elemId = ['receiverBankId'];
			cheackAndShowWarns(elemId);
			
			//获取收款款银行账号
			if(receiveBankId != null && receiveBankId !=''){
				var hiddenElemIds = ['receiverAccount'];
				var selectIds = ['receiveAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				var corporationPid = $('#receive_corpId').val();
				getRelationData(receiveBankId,corporationPid,'receiveAccountSelect',basePath+'/projectStockerController/getBankAccount');
				form.render('select');
			}else{
				var hiddenElemIds = ['receiverAccount'];
				var selectIds = ['receiveAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				form.render('select');
			}
		});
		
		//付款银行
		form.on('select(payBankNameFilter)', function(data){
			var payBankId =  data.value;
			$('#payBankId').val(payBankId);
			
			var elemId = ['payBankId'];
			cheackAndShowWarns(elemId);
			
			//获取付款银行账号
			if(payBankId != null && payBankId !=''){
				var hiddenElemIds = ['payAccount'];
				var selectIds = ['payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				var contributiveId = $('#contributiveId').val();
				getRelationData(payBankId,contributiveId,'payAccountSelect',basePath+'/projectStockerController/getBankAccount');
				form.render('select');
			}else{
				var hiddenElemIds = ['payAccount'];
				var selectIds = ['payAccountSelect'];
				delHiddenInputValue(hiddenElemIds);
				delSelectElemOption(selectIds);
				form.render('select');
			}
		});
		
		
		//收款账号
		form.on('select(receiveAccountFilter)', function(data){
			var receiverAccount =  data.value;
			$('#receiverAccount').val(receiverAccount);
			
			var elemId = ['receiverAccount'];
			cheackAndShowWarns(elemId);
		});
		
		
		//付款账号
		form.on('select(payAccountFilter)', function(data){
			var payAccount =  data.value;
			$('#payAccount').val(payAccount);
			
			var elemId = ['payAccount'];
			cheackAndShowWarns(elemId);
		});
		
		form.render('select');
	})
	
	
	//引入layui时间控件
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  
		  laydate.render({
			   elem: '#payDate',
			   done: function(value, date, endDate){
				   var elemIds = ['payDate'];
				   cheackAndShowWarns(elemIds);
			   }
		  });
	 });
	
	//修改,查看模式
	if(type == '2' || type == '3'){
		//切换标题
		if(type == '2'){
			$('#editTitleSpanName').text('修改股东付款请求');
			
			//打开上传
	    	$('#stockHolderPay_fileSelectList').show();
			$('#stockHolderPay_fileSelectList2').hide();
			$('#stockHolderPay_startUploadListAction').show();
			$('#stockHolderPay_startUploadListAction2').hide();
		}
		if(type == '3'){
			$('#editTitleSpanName').text('查看股东付款请求');
			//隐藏上传
	    	$('#stockHolderPay_fileSelectList').hide();
			$('#stockHolderPay_fileSelectList2').hide();
			$('#stockHolderPay_startUploadListAction').hide();
			$('#stockHolderPay_startUploadListAction2').hide();
			$('#saveAndBackButtonDiv').hide();
			//$('input').attr('disabled',"disabled");
			//$('textarea').attr('disabled',"disabled");
			$('.disAbledElem').attr('disabled',"disabled");
		}
		
		
		//设置项目选中
		var projectPidVal = $('#projectPid').val();
		$('#projectNameSelect').val(projectPidVal);
		
		//异步获取项目公司并设置选中
		var selectIds = ['receiverNameSelect','contributiveNameSelect','receiveBankNameSelect','payBankNameSelect','receiveAccountSelect','payAccountSelect'];
		delSelectElemOption(selectIds);
		getRelationData(projectPidVal,'','receiverNameSelect',basePath+'/projectStockerController/getReceiveCompany');
		
		var businessPrjInfoPid = $('#businessPrjInfoPid').val();
		$('#receiverNameSelect').val(businessPrjInfoPid);
		
		//异步获取项目公司的银行,并设置选中
		var selectIds = ['receiveBankNameSelect','receiveAccountSelect'];
		delSelectElemOption(selectIds);
		var corporationPid = $('#receive_corpId').val();
		getRelationData(corporationPid,'','receiveBankNameSelect',basePath+'/projectStockerController/getBankInfo');
		var receiverBankId = $('#receiverBankId').val();
		$('#receiveBankNameSelect').val(receiverBankId);
		
		//异步获取项目公司的银行账户,并设置选中
		var selectIds = ['receiveAccountSelect'];
		delSelectElemOption(selectIds);
		getRelationData(receiverBankId,corporationPid,'receiveAccountSelect',basePath+'/projectStockerController/getBankAccount');
		var receiverAccount = $('#receiverAccount').val();
		$('#receiveAccountSelect').val(receiverAccount);
		
		//异步获取出资股东,并设置选中
		var selectIds = ['contributiveNameSelect','payBankNameSelect','payAccountSelect'];
		delSelectElemOption(selectIds);
		var receiverId = $('#businessPrjInfoPid').val();
		var contributiveId = $('#contributiveId').val();
		getRelationData(receiverId,projectPidVal,'contributiveNameSelect',basePath+'/projectStockerController/getContributiveCompany');
		$('#contributiveNameSelect').val(contributiveId);
		
		
		//异步获取出资股东的银行,并设置选中
		var selectIds = ['payBankNameSelect','payAccountSelect'];
		delSelectElemOption(selectIds);
		getRelationData(contributiveId,'','payBankNameSelect',basePath+'/projectStockerController/getBankInfo');
		var payBankId = $('#payBankId').val();
		$('#payBankNameSelect').val(payBankId);
		
		
		//异步获取出资股东的银行账户并设置选中
		var selectIds = ['payAccountSelect'];
		delSelectElemOption(selectIds);
		getRelationData(payBankId,contributiveId,'payAccountSelect',basePath+'/projectStockerController/getBankAccount');
		var payAccount = $('#payAccount').val();
		$('#payAccountSelect').val(payAccount);
		
		
		//异步加载附件
		var payMentPid = $('#stockPaymentPid').val();
    	var options=$("#stockHolderPayFile_corPageSelect option:selected");
    	var pageSize = options.val();
    	getStockHolderVoucherList(payMentPid,"1",pageSize);
		
		
	}
	
	
	//保存股东付款请求
	$('#stockHolderPay_submitButtonId').on('click', function(){
		var elemIds = ['projectPid','businessPrjInfoPid','contributiveId','receiverBankId','payBankId','receiverAccount','payAccount','payTerm','payAmount','payDate'];
		if(cheackAndShowWarns(elemIds)){
			return false;
		}else{
			saveStockHolderPayInfo();
		}
	});
	
	
	//付款期数,预计付款金额,预计付款时间失去焦点事件
	$('#payTerm,#payAmount,#payDate').on('blur', function(){
		var curElemId = $(this).attr("id");
		var elemIds = [curElemId];
		cheackAndShowWarns(elemIds);
	});	
	
	
	//分页栏-刷新按钮
	$('#stockHolderPayFile_refresh_table_list').on('click', function(){
		var payMentPid = $('#stockPaymentPid').val();
		getStockHolderVoucherList(payMentPid,'','');
	});	
	
	//分页栏-页码输入框
	$('#stockHolderPayFile_searchPage').on('keypress', function(){
		if(window.event.keyCode==13){
			var payMentPid = $('#stockPaymentPid').val();
			var curPage = $('#stockHolderPayFile_searchPage').val();
			var options=$("#stockHolderPayFile_corPageSelect option:selected");
			var pageSize = options.val();
			getStockHolderVoucherList(payMentPid,curPage,pageSize);
		}
	});
	
	
	//分页栏-每页显示条数下拉事件
	$('#stockHolderPayFile_corPageSelect').on('change', function(){
		var payMentPid = $('#stockPaymentPid').val();
		var options=$("#stockHolderPayFile_corPageSelect option:selected");
		var pageSize = options.val();
		var curPage = $('#stockHolderPayFile_searchPage').val();
		getStockHolderVoucherList(payMentPid,curPage,pageSize);
	});
	
	
	//选择文件及文件上传按钮
	$('#stockHolderPay_startUploadListAction2,#stockHolderPay_fileSelectList2').on('click', function(){
		var stockPaymentPid = $('#stockPaymentPid').val();
		if(stockPaymentPid != null && stockPaymentPid !=''){
			$('#stockHolderPay_fileSelectList').show();
			$('#stockHolderPay_fileSelectList2').hide();
			$('#stockHolderPay_startUploadListAction').show();
			$('#stockHolderPay_startUploadListAction2').hide();
		}else{
			$('#stockHolderPay_fileSelectList').hide();
			$('#stockHolderPay_fileSelectList2').show();
			$('#stockHolderPay_startUploadListAction').hide();
			$('#stockHolderPay_startUploadListAction2').show();
		}
	});
	
})


//分页栏-首页、上一页、下一页、尾页按钮
function getObjListByPage(idCode) {
	var payMentPid = $('#stockPaymentPid').val();
	var options=$("#stockHolderPayFile_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	getStockHolderVoucherList(payMentPid,curPage,pageSize);
}

//清空隐藏域值
function delHiddenInputValue(elemIds) {
	if(elemIds != null && elemIds.length > 0){
		for(var i = 0;i< elemIds.length;i++ ){
			var targetElemId = elemIds[i];
			$('#'+targetElemId).val("");
		}
	}
}

//清除下拉框内容
function delSelectElemOption(elemIds) {
	if(elemIds != null && elemIds.length > 0){
		for(var i = 0;i< elemIds.length;i++ ){
			var targetElemId = elemIds[i];
			var htmlStr = "<option value='' title=''>---请选择---</option>";
			$('#'+targetElemId+'> option').remove();
			$('#'+targetElemId).append(htmlStr);
			$('#'+targetElemId).val("");
		}
	}
}


//校验必填项并对必填项为空做提示
function cheackAndShowWarns(tags) {
	var flag = false;
	for(var i=0;i<tags.length;i++){
		var tag = tags[i];
		var elmId = "#"+tag;
		var elmWarnId = elmId+"Warn";
		
		var elmIdVal = $(elmId).val();
		if(elmIdVal == ''){
			$(elmWarnId).show();
			flag = true;
		}else{
			$(elmWarnId).hide();
		}
	}
	return flag;
}

//鼠标自动聚焦到必填空值input
function focusWarnInput(tags) {
	for(var i=0;i<tags.length;i++){
		var tag = tags[i];
		var elmId = "#"+tag;
		var elmIdVal = $(elmId).val();
		if(elmIdVal == ''){
			$(elmId).focus();
			break;
		}
	}
}


//保存股东付款请求
function saveStockHolderPayInfo(){
	$.ajax({
	    url: basePath+"/tradeManage/addStockholderPaymentRequest",
	    type: "POST",
	    data:$('#stockHolderPay_formId').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			var errMsg=data["errMsg"]
			var successMsg=data["successMsg"]
			
			if(successMsg != null && successMsg !='' && successMsg !='undefined'){
				var stockPaymentPid = data["stockPaymentPid"];
				$('#stockPaymentPid').val(stockPaymentPid);
				layer.msg(successMsg,{icon: 1,time: 1000});
				
				$('#stockHolderPay_fileSelectList').show();
				$('#stockHolderPay_fileSelectList2').hide();
				$('#stockHolderPay_startUploadListAction').show();
				$('#stockHolderPay_startUploadListAction2').hide();
			}
			
			if(errMsg !=null && errMsg !='' && errMsg !='undefined'){
				layer.msg(errMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//下拉框联动函数
function getRelationData(firstRelationPid,secondRelationPid,selectElemId,url){
	$.ajax({
	    url: url,
	    type: "POST",
	    data:{
	    	firstRelationPid:firstRelationPid,
	    	secondRelationPid:secondRelationPid
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg = data["msg"];
			var successCode = data["success"];
			
			if(successCode == 's'){
				var resultDataList = data["resultDataList"];
				if(resultDataList != null && resultDataList.length > 0){
					var htmlStr = "<option value='' title=''>---请选择---</option>";
					$('#'+selectElemId+'> option').remove();
					
					for(var i=0;i < resultDataList.length;i++){
						var resultData = resultDataList[i];
						var dataValue = resultData.dataValue;
						var dataText = resultData.dataText;
						var dataTitle = resultData.dataTitle;
						var newHtmlStr = "";
						if(dataTitle != null && dataTitle !='' && dataTitle !='undefined'){
							newHtmlStr = "<option value='"+dataValue+"' title='"+dataTitle+"'>"+dataText+"</option>";
						}else{
							newHtmlStr = "<option value='"+dataValue+"' title=''>"+dataText+"</option>";
						}
						htmlStr = htmlStr + newHtmlStr;
					}
					
					$('#'+selectElemId).append(htmlStr);
					$('#'+selectElemId).val("");
				}
			}
		}
	});
}


//加载股东付款凭证
function getStockHolderVoucherList(payMentPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/projectStockerController/getStockHolderVoucherList",
	    type: "POST",
	    data:{
	    	payMentPid:payMentPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				var voucherList = data["voucherList"];
				var pageObj = data["page"];
				var listLength = voucherList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var voucher = voucherList[i];
						var fileName = voucher.file_all_name == null?"":voucher.file_all_name;
						var accessoryTypeName = voucher.accessory_type_name == null?"":voucher.accessory_type_name;
						var createDate = voucher.create_date == null?"":new Date(voucher.create_date).format("yyyy-MM-dd HH:mm:ss");
						var userRealName = voucher.userRealName == null?"":voucher.userRealName;
						var fileUrl = voucher.file_url == null?"":voucher.file_url;
						var newhtmlStr = "<tr>" +
								"<td>"+fileName+"</td>" +
								"<td>"+accessoryTypeName+"</td>" +
								"<td>"+createDate+"</td>" +
								"<td>"+userRealName+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				reSetPageParams('stockHolderPayFile',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//预览附件图片
function showFile(url,fileName){
	var regexp = /^.+(\.jpg|\.jpeg|\.png|\.bmp)$/g;
	/*图片查看*/
	if(regexp.test(url)){
		layer.open({
			type: 1,
			title: ["附件信息:"+fileName],
			maxmin: true,
			skin: 'layui-layer-demo',
			area: ['800px', '600px'],
			content: '<img src="'+basePath+'estateFundFile/showAccessory?path='+encodeURI(url)+'&fileName='+encodeURI(fileName)+'">'
		});
	}else{
		layer.msg("查看只针对图片文件，若想查看详情，请先下载",{icon:7});
	}
}


//下载附件
function downLoadFile(fileUrl,fileName){
	if(fileUrl != ''){
		window.location.href=basePath+"/estateFundFile/downloadAccessory?path="+encodeURI(fileUrl)+"&fileName="+encodeURI(fileName);
	}else{
		layer.msg("尚未上传文件!",{icon: 7,time: 1500});
	}
}


//给分页栏重新赋值
function reSetPageParams(prefixCode,htmlStr,pageObj) {
	$("#"+prefixCode+"_tbodyId > tr").remove();
	$("#"+prefixCode+"_tbodyId").hide();
	$("#"+prefixCode+"_pager_list").hide();
	
	$("#"+prefixCode+"_upPageInput").val(pageObj.upPage);
	$("#"+prefixCode+"_searchPage").val(pageObj.curPage);
	$("#"+prefixCode+"_sp_1_pager_list").text(pageObj.totalPage);
	$("#"+prefixCode+"_nextPageInput").val(pageObj.nextPage);
	$("#"+prefixCode+"_lastPageInput").val(pageObj.endPage);
	$("#"+prefixCode+"_totalCountId").text("共 "+pageObj.totalCount+"条");
	$("#"+prefixCode+"_corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
	
	$("#"+prefixCode+"_tbodyId").append(htmlStr);
	$("#"+prefixCode+"_tbodyId").show();
	$("#"+prefixCode+"_pager_list").show();
}


//初始化翻页栏
function installPageHtml(prefixCode) {
	var pageHtmlStr =""+
		"<div id='"+prefixCode+"_pager_list' class='ui-jqgrid-pager' dir='ltr'>"+
		    "<div id='"+prefixCode+"_pager_list' class='ui-pager-control' role='group'>"+
		        "<table class='ui-pg-table ui-common-table ui-pager-table'>"+
		            "<tbody>"+
			            "<tr>"+
			                "<td id='"+prefixCode+"_pager_list_left' align='left'>"+
			                   "<table class='ui-pg-table navtable ui-common-table'>"+
			                       "<tbody>"+
				                       "<tr>"+
				                           "<td class='ui-pg-button' title='刷新表格' id='"+prefixCode+"_refresh_table_list'>"+
				                               "<div class='ui-pg-div'>"+
				                                   "<span class='glyphicon glyphicon-refresh'></span>"+
				                               "</div>"+
				                           "</td>"+
				                       "</tr>"+
			                       "</tbody>"+
			                   "</table>"+
			                "</td>"+
			                "<td id='pager_list_center' align='center' style=' width: 373px;'>" +
			                    "<table class='ui-pg-table ui-common-table ui-paging-pager'>" +
			                        "<tbody>"+
				                        "<tr>"+
				                            "<td id='"+prefixCode+"_first_pager_list' onclick='getObjListByPage(&quot;"+prefixCode+"_firstPageInput&quot;)' class='ui-pg-button ui-disabled' title='首页' style='cursor: default;'>"+
				                                "<input type='text'  id='"+prefixCode+"_firstPageInput' hidden='hidden' name='firstPageInput' value='1'>"+
				                                "<span class='glyphicon glyphicon-step-backward'></span>"+
				                            "</td>"+
				                            "<td id='"+prefixCode+"_prev_pager_list'  onclick='getObjListByPage(&quot;"+prefixCode+"_upPageInput&quot;)' class='ui-pg-button ui-disabled' title='上一页' style='cursor: default;'>"+
				                            	"<input type='text'  id='"+prefixCode+"_upPageInput' hidden='hidden' name='upPageInput' value='1'>"+
				                                "<span class='glyphicon glyphicon-backward'></span>"+
				                            "</td>"+
				                            "<td class='ui-pg-button ui-disabled' style='cursor: default;'>"+
				                                "<span class='ui-separator'></span>"+
				                            "</td>"+
				                            "<td id='input_pager_list' dir='ltr'>"+
				                                "<input id='"+prefixCode+"_searchPage' name='searchPage'  class='ui-pg-input form-control' type='text' size='2' maxlength='7' value='1' role='textbox'>"+
												"共<span id='"+prefixCode+"_sp_1_pager_list'>0</span>页"+
				                            "</td>"+
				                            "<td class='ui-pg-button ui-disabled' style='cursor: default;'>"+
				                                "<span class='ui-separator'></span>"+
				                            "</td>"+
				                            "<td id='"+prefixCode+"_next_pager_list' onclick='getObjListByPage(&quot;"+prefixCode+"_nextPageInput&quot;)' class='ui-pg-button ui-disabled' title='下一页'>"+
				                            	"<input type='text' id='"+prefixCode+"_nextPageInput' name='nextPageInput' hidden='hidden' value='1'>"+
				                                "<span class='glyphicon glyphicon-forward'></span>"+
				                            "</td>"+
				                            "<td id='"+prefixCode+"_last_pager_list' onclick='getObjListByPage(&quot;"+prefixCode+"_lastPageInput&quot;)' class='ui-pg-button ui-disabled' title='尾页'>"+
				                            	"<input id='"+prefixCode+"_lastPageInput' name='lastPageInput' hidden='hidden' value='1' type='text'>"+
				                                "<span class='glyphicon glyphicon-step-forward'></span>"+
				                            "</td>"+
				                            "<td dir='ltr'>"+
				                                "<select id='"+prefixCode+"_corPageSelect' name='corPageSelect' class='ui-pg-selbox form-control' >"+
				                                    "<option role='option' value='10' selected='selected'>10</option>"+
				                                    "<option role='option' value='20' >20</option>"+
				                                    "<option role='option' value='30' >30</option>"+
				                                "</select>"+
				                            "</td>"+
				                        "</tr>"+
			                        "</tbody>"+
			                    "</table>"+
			                "</td>"+
			                "<td id='pager_list_right' align='right'>"+
			                    "<div dir='ltr' style='text-align: right' class='ui-paging-info' id='"+prefixCode+"_totalCountId'>共 0 条</div>"+
			                "</td>"+
			             "</tr>"+
		            "</tbody>"+
		        "</table>"+
		    "</div>"+
		"</div>";
	
	$('#'+prefixCode+"_tableId").after(pageHtmlStr);
}


//上传附件
function installLayuiUpload(elemId,demoListViewId,bindActionId,fileUploadDivId,uploadUrl){
	var demoListView = $('#'+demoListViewId);
	  uploadListIns = upload.render({
	    elem: '#'+elemId,
	    url: uploadUrl,
	    accept: 'file',
	    multiple: true,
	    auto: false,
	    bindAction: '#'+bindActionId,
	    choose: function(obj){   
	    	if(uploadTotal == uploadSuccess && uploadSuccess !='' && uploadTotal !='' && uploadSuccess != null && uploadTotal != null){
	        	for(var p in files){
	        		delete files[p];
	        	}
		    	demoListView.find('tr').remove();
			    uploadListIns.config.elem.next()[0].value = '';
			    uploadTotal = '';
			    uploadSuccess = '';
	        }
	    	files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	    	//读取本地文件
	    	obj.preview(function(index, file, result){
	    		var tr = $(['<tr id="upload-'+ index +'">',
	    		            '<td>'+ file.name +'</td>',
	    		            '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
	    		            '<td>等待上传</td>',
	    		            '<td>',
	           					'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
	            				'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
	            			'</td>',
	          				'</tr>'].join(''));
	        
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	        	obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	        	delete files[index]; //删除对应的文件
	        	tr.remove();
	        	uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	      });
	    	$('#'+fileUploadDivId).css('display','block');
	    },
	    before: function(obj){
	    	uploadListIns.config.data.payMentPid = $('#stockPaymentPid').val();
	    },
	    done: function(res, index, upload){
	      if(res.success == 's'){ //上传成功
	        var tr = demoListView.find('tr#upload-'+ index);
	        tds = tr.children();
	        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
	        tds.eq(3).html(''); 					
	        
	        //刷新股东付款请求附件列表
	        if(res.refreshType == 'stockHolderPay'){
	        	var payMentPid = $('#stockPaymentPid').val();
	        	var options=$("#stockHolderPayFile_corPageSelect option:selected");
	        	var pageSize = options.val();
	        	getStockHolderVoucherList(payMentPid,"1",pageSize);
	        }
	        return ; 
	      }
	      
	      this.error(index, upload);
	    },
	    allDone: function(obj){ //当文件全部被提交后，才触发
	        uploadTotal = obj.total;
	        uploadSuccess = obj.successful;
	    },
	    error: function(index, upload){
	       var tr = demoListView.find('tr#upload-'+ index);
	       tds = tr.children();
	       tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	       tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  });
}
