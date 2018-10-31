var form;
var basePath;
$ (function () {
	basePath = $('#basePathId').val();
    
	
	 //控制头部tab切换
	 $('#myTab a').click(function (e) {
         e.preventDefault();
         $(this).tab('show');
     })
	
	
	//初始化分页栏
	installPageHtml("investorFinanceDetail");
	installPageHtml("corBank");
	installPageHtml("investFile");
	installPageHtml("eachOneOfInvestorFile");
	installPageHtml("investorReceive");
	installPageHtml("investorReturnDetail");
	
	//初始化加载企业资料
	getAllCorFile();
	
	//初始化加载企业银行列表
	var corPid = $('#corBasePid').val();
	getBankList(corPid,'','');
	
	//初始化加载投资人附件列表
	var investorPid = $('#investorPid').val();
	getSubjectFileList(investorPid,'','');
	
	//初始化投资人财务详情付款列表
	getInvestorFinanceDetailList(investorPid,'','');
	
	//初始化投资人财务详情回款列表
	getInvestorReceiveDetailList(investorPid,'','');
	
    //引入下拉框,并设置值变化时更新隐藏域
	layui.use('form',function(){
		form =layui.form;
	})
	
	
	//查看每一条投资人付款明细
    $('#investor_Finance_Pay1').on('click', function(){
    	  var investorPayPids = new Array();
	      var i = 0;
		  $("#investorFinanceDetail_tbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var cheackPidVal = $(this).val();
				investorPayPids[i] = cheackPidVal;
				i++;
			 }
		  });
      
		  if(investorPayPids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(investorPayPids.length == 1){
			  var investorPayPid = investorPayPids[0];
			  //加载当前选中付款记录数据
			  getSubjectPaymentDetail(investorPayPid);
			  //加载当前选中付款记录附件
			  getSubjectPaymentDetailFileList(investorPayPid,'','');
		  }else{
			  layer.msg("请选择要查看的记录!",{icon:7,time: 1000});
		  }
    });
	
	
	//查看每一条投资人回款明细
	$('#investor_Finance_reture1').on('click', function(){
  	  	  var investorReturnPids = new Array();
	      var i = 0;
		  $("#investorReceive_tbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var cheackPidVal = $(this).val();
				investorReturnPids[i] = cheackPidVal;
				i++;
			 }
		  });
    
		  if(investorReturnPids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(investorReturnPids.length == 1){
			  var investorReturnPid = investorReturnPids[0];
			  //加载当前选中回款记录数据
			  getSubjectReturnDetail(investorReturnPid);
			  
			  //加载当前选中回款记录附件
			  getSubjectReturnDetailFileList(investorReturnPid,'','');
		  }else{
			  layer.msg("请选择要查看的记录!",{icon:7,time: 1000});
		  }
        
    });
	
	
	//列表刷新按钮
	var corpId = $('#corBasePid').val();
	var investorPidVal = $('#investorPid').val();
    $('#corBank_refresh_table_list,'+
    	'#investFile_refresh_table_list,'+
    	'#investorFinanceDetail_refresh_table_list,'+
    	'#eachOneOfInvestorFile_refresh_table_list,'+
    	'#investorReceive_refresh_table_list,'+
    	'#investorReturnDetail_refresh_table_list').on('click', function(){
    		
    	var curDomIdFirstStr = $(this).attr('id').split("_")[0];
    	var options=$("#"+curDomIdFirstStr+"_corPageSelect option:selected");
		var pageSize = options.val();
		
		//企业银行
    	if(corpId != "" && curDomIdFirstStr == 'corBank'){
    		getBankList(corpId,"1",pageSize);
    	}
    	
    	//投资人附件
    	if(investorPidVal != "" && curDomIdFirstStr == 'investFile'){
    		getSubjectFileList(investorPidVal,"1",pageSize);
    	}
    	
    	//投资人财务详情付款信息列表
    	if(investorPidVal != "" && curDomIdFirstStr == 'investorFinanceDetail'){
    		getInvestorFinanceDetailList(investorPidVal,"1",pageSize);
    	}
    	
    	//每条投资人付款明细附件列表
    	var subjectPayPidVal = $('#investorPay_investorPayPid').val();
    	if(subjectPayPidVal != "" && curDomIdFirstStr == 'eachOneOfInvestorFile'){
    		getSubjectPaymentDetailFileList(subjectPayPidVal,"1",pageSize);
    	}
    	
    	//投资人财务详情回款信息列表
    	if(investorPidVal != "" && curDomIdFirstStr == 'investorReceive'){
    		getInvestorReceiveDetailList(investorPidVal,"1",pageSize);
    	}
    	
    	//每条投资人回款明细附件列表
    	var subjectReturnPidVal = $('#investorReturnDetail_investorReturnPid').val();
    	if(subjectReturnPidVal != "" && curDomIdFirstStr == 'investorReturnDetail'){
    		getSubjectReturnDetailFileList(subjectReturnPidVal,"1",pageSize);
    	}
    	
    });
	
    
    //列表页码输入框
	$('#corBank_searchPage,'+
		'#investFile_searchPage,'+
		'#investorFinanceDetail_refresh_table_list,'+
		'#eachOneOfInvestorFile_refresh_table_list,'+
		'#investorReceive_refresh_table_list,'+
		'#investorReturnDetail_refresh_table_list').on('keypress', function(){
		if(window.event.keyCode==13){
			var curDomIdFirstStr = $(this).attr('id').split("_")[0];
			var curPage = $("#"+curDomIdFirstStr+"_searchPage").val();
			
			var options=$("#"+curDomIdFirstStr+"_corPageSelect option:selected");
			var pageSize = options.val();
			
			//企业银行
	    	if(corpId != "" && curDomIdFirstStr == 'corBank'){
	    		getBankList(corpId,curPage,pageSize);
	    	}
	    	
	    	//投资人附件
	    	if(investorPidVal != "" && curDomIdFirstStr == 'investFile'){
	    		getSubjectFileList(investorPidVal,curPage,pageSize);
	    	}
	    	
	    	//投资人财务详情付款信息列表
	    	if(investorPidVal != "" && curDomIdFirstStr == 'investorFinanceDetail'){
	    		getInvestorFinanceDetailList(investorPidVal,curPage,pageSize);
	    	}
	    	
	    	//每条投资人付款明细附件列表
	    	var subjectPayPidVal = $('#investorPay_investorPayPid').val();
	    	if(subjectPayPidVal != "" && curDomIdFirstStr == 'eachOneOfInvestorFile'){
	    		getSubjectPaymentDetailFileList(subjectPayPidVal,curPage,pageSize);
	    	}
	    	
	    	//投资人财务详情回款信息列表
	    	if(investorPidVal != "" && curDomIdFirstStr == 'investorReceive'){
	    		getInvestorReceiveDetailList(investorPidVal,"1",pageSize);
	    	}
	    	
	    	//每条投资人回款明细附件列表
	    	var subjectReturnPidVal = $('#investorReturnDetail_investorReturnPid').val();
	    	if(subjectReturnPidVal != "" && curDomIdFirstStr == 'investorReturnDetail'){
	    		getSubjectReturnDetailFileList(subjectReturnPidVal,curPage,pageSize);
	    	}
	    	
		}
	});
    
	//每页显示条数下拉事件
	$('#corBank_corPageSelect,'+
		'#investFile_corPageSelect,'+
		'#investorFinanceDetail_searchPage,'+
		'#eachOneOfInvestorFile_searchPage,'+
		'#investorReceive_searchPage,'+
		'#investorReturnDetail_searchPage').on('change', function(){
		var curDomIdFirstStr = $(this).attr('id').split("_")[0];
		var curPage = $("#"+curDomIdFirstStr+"_searchPage").val();
		var options=$("#"+curDomIdFirstStr+"_corPageSelect option:selected");
		var pageSize = options.val();
		
		//企业银行
    	if(corpId != "" && curDomIdFirstStr == 'corBank'){
    		getBankList(corpId,curPage,pageSize);
    	}
    	
    	//投资人附件
    	if(investorPidVal != "" && curDomIdFirstStr == 'investFile'){
    		getSubjectFileList(investorPidVal,curPage,pageSize);
    	}
    	
    	//投资人财务详情付款信息列表
    	if(investorPidVal != "" && curDomIdFirstStr == 'investorFinanceDetail'){
    		getInvestorFinanceDetailList(investorPidVal,curPage,pageSize);
    	}
    	
    	
    	//每条投资人付款明细附件列表
    	var subjectPayPidVal = $('#investorPay_investorPayPid').val();
    	if(subjectPayPidVal != "" && curDomIdFirstStr == 'eachOneOfInvestorFile'){
    		getSubjectPaymentDetailFileList(subjectPayPidVal,curPage,pageSize);
    	}
    	
    	//投资人财务详情回款信息列表
    	if(investorPidVal != "" && curDomIdFirstStr == 'investorReceive'){
    		getInvestorReceiveDetailList(investorPidVal,curPage,pageSize);
    	}
    	
    	//每条投资人回款明细附件列表
    	var subjectReturnPidVal = $('#investorReturnDetail_investorReturnPid').val();
    	if(subjectReturnPidVal != "" && curDomIdFirstStr == 'investorReturnDetail'){
    		getSubjectReturnDetailFileList(subjectReturnPidVal,curPage,pageSize);
    	}
    	
	});
	
})


//加载该企业的企业资料信息
function getAllCorFile(){
	var corPid = $('#corBasePid').val();
	$.ajax({
	    url: basePath+"/corporationController/getAllCorFile",
	    type: "POST",
	    data:{corPid:corPid},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg = data["msg"]
			var success = data["success"]
			if(success == "s"){
				var dataInfoList = data["dataInfoList"]
				var datum_imgList = $('.dataInfo_img');
				if(dataInfoList.length > 0){
					for(var i = 0;i<dataInfoList.length;i++){  
                        var dataInfo = dataInfoList[i];
                        var corDataType = dataInfo.corDataType;
                        var fileUrl = dataInfo.fileUrl;
                        var fileTypeName = dataInfo.fileTypeName;
                        
                        for(var j = 0;j<datum_imgList.length;j++){  
                            var datum_img = datum_imgList[j];
                            var datumImgId = datum_img.id;
                            var datumId = datumImgId.split("_Img")[0];
                            if(corDataType == datumId){
                            	$('#'+datumImgId).attr('src',basePath + 'estateFundFile/showAccessory?path='+encodeURI(fileUrl)+'&fileName='+encodeURI(fileTypeName));
                            	$('#'+datumImgId).attr('title',fileTypeName);
                            }
                        }
                    }
				}
			}else{
				layer.msg(resultMsg,{icon:7});
			}
		}
	});
}

//下载附件
function downLoadFile(fileUrl,fileName){
	if(fileUrl != ''){
		window.location.href=basePath+"/estateFundFile/downloadAccessory?path="+encodeURI(fileUrl)+"&fileName="+encodeURI(fileName);
	}else{
		layer.msg("尚未上传文件!",{icon: 7,time: 1500});
	}
}


//预览企业资料图片
function priviewCorFile(elemId){
	var regexp = /^.+(\.jpg|\.jpeg|\.png|\.bmp)$/g;
	var fileName = $('#'+elemId).attr('title');
	var fileFullUrl = $('#'+elemId).attr('src');
	if(fileFullUrl.indexOf('?path=') != -1){
		var fileUrl = fileFullUrl.split("?path=")[1].split("&fileName=")[0];
		/*图片查看*/
		if(regexp.test(fileUrl)){
			layer.open({
				type: 1,
				title: ["附件信息:"+fileName],
				maxmin: true,
				skin: 'layui-layer-demo',
				area: ['800px', '600px'],
				content: '<img src="'+basePath+'estateFundFile/showAccessory?path='+fileUrl+'&fileName='+encodeURI(fileName)+'">'
			});
		}else{
			layer.msg("查看只针对图片文件，若想查看详情，请先下载",{icon:7});
		}
	}else{
		layer.msg("尚未上传图片",{icon:7});
	}
	
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


//加载企业银行信息列表
function getBankList(corPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/corporationController/getBankList",
	    type: "POST",
	    data:{
	    	corPid:corPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var bankListMsg=data["msg"]
			var bankListCode=data["success"]
			
			if(bankListCode == 's'){
				var corBankList = data["corBankList"];
				var pageObj = data["page"];
				var listLength = corBankList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var corBank = corBankList[i];
						var accountName = corBank.accountName == null?"":corBank.accountName;
						var bankName = corBank.bankName == null?"":corBank.bankName;
						var account = corBank.account == null?"":corBank.account;
						var newhtmlStr = "<tr>" +
								"<td>"+$('#corporationNameId').val()+"</td>" +
								"<td>"+accountName+"</td>" +
								"<td>"+bankName+"</td>" +
								"<td>"+account+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"toSeeBank('"+corBank.pid+"')\" class='table_bnt'>查看</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				
				reSetPageParams('corBank',htmlStr,pageObj);
			}else{
				layer.msg(bankListMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//加载投资人财务详情附件列表
function getSubjectFileList(subjectPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/investSubjectFinanceController/getSubjectFileList",
	    type: "POST",
	    data:{
	    	subjectPid:subjectPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				var subjectFileList = data["subjectFileList"];
				var pageObj = data["page"];
				var listLength = subjectFileList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var subjectFile = subjectFileList[i];
						var fileName = subjectFile.real_name == null?"":subjectFile.real_name;
						var createDate = subjectFile.create_date == null?"":new Date(subjectFile.create_date).format("yyyy-MM-dd HH:mm:ss");
						var userName = subjectFile.user_name == null?"":subjectFile.user_name;
						var fileUrl = subjectFile.file_url == null?"":subjectFile.file_url;
						var newhtmlStr = "<tr>" +
								"<td>"+fileName+"</td>" +
								"<td>"+createDate+"</td>" +
								"<td>"+userName+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				reSetPageParams('investFile',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//加载选中目标的投注人财务详情的付款信息明细
function getSubjectPaymentDetail(subjectPayPid){
	$.ajax({
	    url: basePath+"/investSubjectFinanceController/getSubjectPaymentDetail",
	    type: "POST",
	    data:{subjectPayPid:subjectPayPid},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg = data["msg"]
			var success = data["success"]
			if(success == "s"){
				var subjectPaymentDetail = data["subjectPaymentDetail"]
				if(subjectPaymentDetail != null){
					var subjectPayPid = subjectPaymentDetail.subjectPayPid == null ?"":subjectPaymentDetail.subjectPayPid;
					var projectName = subjectPaymentDetail.projectName == null ?"":subjectPaymentDetail.projectName;
					var receiverName = subjectPaymentDetail.receiverName == null ?"":subjectPaymentDetail.receiverName;
					var contributiveName = subjectPaymentDetail.contributiveName == null ?"":subjectPaymentDetail.contributiveName;
					var receiverBankname = subjectPaymentDetail.receiverBankname == null ?"":subjectPaymentDetail.receiverBankname;
					var payBankName = subjectPaymentDetail.payBankName == null ?"":subjectPaymentDetail.payBankName;
					var receiverAccount = subjectPaymentDetail.receiverAccount == null ?"":subjectPaymentDetail.receiverAccount;
					var payAccount = subjectPaymentDetail.payAccount == null ?"":subjectPaymentDetail.payAccount;
					var payTerm = subjectPaymentDetail.payTerm == null ?"":subjectPaymentDetail.payTerm;
					var payAmount = subjectPaymentDetail.payAmount == null ?"":subjectPaymentDetail.payAmount;
					var payDate = subjectPaymentDetail.payDate == null ?"":new Date(subjectPaymentDetail.payDate).format("yyyy-MM-dd HH:mm:ss");
					var payRemark = subjectPaymentDetail.payRemark == null ?"":subjectPaymentDetail.payRemark;
					var realUserName = subjectPaymentDetail.realUserName == null ?"":subjectPaymentDetail.realUserName;
					var investorOpDate = subjectPaymentDetail.investorOpDate == null ?"":new Date(subjectPaymentDetail.investorOpDate).format("yyyy-MM-dd HH:mm:ss");
					
					$("#investorPay_investorPayPid").val(subjectPayPid);
					$("#investorPay_projectName").val(projectName);
					$("#investorPay_receiverName").val(receiverName);
					$("#investorPay_contributiveName").val(contributiveName);
					$("#investorPay_receiverBankName").val(receiverBankname);
					$("#investorPay_payBankName").val(payBankName);
					$("#investorPay_receiverAccount").val(receiverAccount);
					$("#investorPay_payAccount").val(payAccount);
					$("#investorPay_payTerm").val(payTerm);
					$("#investorPay_payAmount").val(payAmount);
					$("#investorPay_payDate").val(payDate);
					$("#investorPay_payRemark").val(payRemark);
					$("#investorPay_realUserName").val(realUserName);
					$("#investorPay_investorOpDate").val(investorOpDate);
				}
				
				layer.open({
		            type: 1,
		            title: ['付款明细','font-size:18px;'],
		            area: ['1040px', '640px'],
		            btnAlign: 'c',
		            content: $('#investor_Finance_Pay'),
		            btn: ['关闭'],
		            btn1: function(index, layero){
		                layer.close(index);
		            }
		        });
			}else{
				layer.msg(resultMsg,{icon:7,time: 1000});
			}
		}
	});
}


//加载选中目标的投注人财务详情的回款信息明细
function getSubjectReturnDetail(subjectReturnPid){
	$.ajax({
	    url: basePath+"/investSubjectFinanceController/getSubjectReturnDetail",
	    type: "POST",
	    data:{subjectReturnPid:subjectReturnPid},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg = data["msg"]
			var success = data["success"]
			if(success == "s"){
				var subjectReturnDetail = data["subjectReturnDetail"]
				if(subjectReturnDetail != null){
					var subjectReturnPid = subjectReturnDetail.subjectReturnPid == null ?"":subjectReturnDetail.subjectReturnPid;
					var projectName = subjectReturnDetail.projectName == null ?"":subjectReturnDetail.projectName;
					var receiverCompany = subjectReturnDetail.receiverCompany == null ?"":subjectReturnDetail.receiverCompany;
					var contributiveCpmpany = subjectReturnDetail.contributiveCpmpany == null ?"":subjectReturnDetail.contributiveCpmpany;
					var receiverBankName = subjectReturnDetail.receiverBankName == null ?"":subjectReturnDetail.receiverBankName;
					var payBankName = subjectReturnDetail.payBankName == null ?"":subjectReturnDetail.payBankName;
					var receiverAccount = subjectReturnDetail.receiverAccount == null ?"":subjectReturnDetail.receiverAccount;
					var payAccount = subjectReturnDetail.payAccount == null ?"":subjectReturnDetail.payAccount;
					var receiverAmount = subjectReturnDetail.receiverAmount == null ?"":subjectReturnDetail.receiverAmount;
					var receivedStatus = subjectReturnDetail.receivedStatus == null ?"":subjectReturnDetail.receivedStatus;
					var profit = subjectReturnDetail.profit == null ?"":subjectReturnDetail.profit;
					var receiverDate = subjectReturnDetail.receiverDate == null ?"":new Date(subjectReturnDetail.receiverDate).format("yyyy-MM-dd HH:mm:ss");
					var reveiverRemark = subjectReturnDetail.reveiverRemark == null ?"":subjectReturnDetail.reveiverRemark;
					var realUserName = subjectReturnDetail.realUserName == null ?"":subjectReturnDetail.realUserName;
					var createDate = subjectReturnDetail.createDate == null ?"":new Date(subjectReturnDetail.createDate).format("yyyy-MM-dd HH:mm:ss");
					
					$("#investorReturnDetail_investorReturnPid").val(subjectReturnPid);
					$("#investorReturnDetail_projectName").val(projectName);
					$("#investorReturnDetail_receiverCompany").val(receiverCompany);
					$("#investorReturnDetail_contributiveCpmpany").val(contributiveCpmpany);
					$("#investorReturnDetail_receiverBankName").val(receiverBankName);
					$("#investorReturnDetail_payBankName").val(payBankName);
					$("#investorReturnDetail_receiverAccount").val(receiverAccount);
					$("#investorReturnDetail_payAccount").val(payAccount);
					$("#investorReturnDetail_receiverAmount").val(receiverAmount);
					$("#investorReturnDetail_receivedStatus").val(receivedStatus);
					$("#investorReturnDetail_profit").val(profit);
					$("#investorReturnDetail_receiverDate").val(receiverDate);
					$("#investorReturnDetail_reveiverRemark").val(reveiverRemark);
					$("#investorReturnDetail_realUserName").val(realUserName);
					$("#investorReturnDetail_createDate").val(createDate);
				}
				
				layer.open({
		            type: 1,
		            title: ['回款明细','font-size:18px;'],
		            area: ['1040px', '640px'],
		            btnAlign: 'c',
		            content: $('#investor_Finance_reture'),
		            btn: ['关闭'],
		            btn1: function(index, layero){
		               layer.close(index);
		            }
		        });
			}else{
				layer.msg(resultMsg,{icon:7,time: 1000});
			}
		}
	});
}



//加载投资人每条付款明细的附件列表
function getSubjectPaymentDetailFileList(subjectPayPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/investSubjectFinanceController/getSubjectPaymentDetailFileList",
	    type: "POST",
	    data:{
	    	subjectPayPid:subjectPayPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				var subjectPaymentDetailFileList = data["subjectPaymentDetailFileList"];
				var pageObj = data["page"];
				var listLength = subjectPaymentDetailFileList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var subjectPaymentDetailFile = subjectPaymentDetailFileList[i];
						
						var fileName = subjectPaymentDetailFile.file_all_name == null?"":subjectPaymentDetailFile.file_all_name;
						var createDate = subjectPaymentDetailFile.create_date == null?"":new Date(subjectPaymentDetailFile.create_date).format("yyyy-MM-dd HH:mm:ss");
						var userName = subjectPaymentDetailFile.user_name == null?"":subjectPaymentDetailFile.user_name;
						var fileUrl = subjectPaymentDetailFile.file_url == null?"":subjectPaymentDetailFile.file_url;
						var newhtmlStr = "<tr>" +
								"<td>"+fileName+"</td>" +
								"<td>"+createDate+"</td>" +
								"<td>"+userName+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				reSetPageParams('eachOneOfInvestorFile',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//加载投资人财务详情回款信息明细中的附件列表
function getSubjectReturnDetailFileList(subjectReturnPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/investSubjectFinanceController/getSubjectReturnDetailFileList",
	    type: "POST",
	    data:{
	    	subjectReturnPid:subjectReturnPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				var subjectReturnDetailFileList = data["subjectReturnDetailFileList"];
				var pageObj = data["page"];
				var htmlStr = "";
				if(subjectReturnDetailFileList != null && subjectReturnDetailFileList.length > 0){
					var listLength = subjectReturnDetailFileList.length;
					for(var i=0;i<listLength;i++){
						var subjectReturnDetailFile = subjectReturnDetailFileList[i];
						
						var fileName = subjectReturnDetailFile.file_all_name == null?"":subjectReturnDetailFile.file_all_name;
						var createDate = subjectReturnDetailFile.create_date == null?"":new Date(subjectReturnDetailFile.create_date).format("yyyy-MM-dd HH:mm:ss");
						var userName = subjectReturnDetailFile.user_name == null?"":subjectReturnDetailFile.user_name;
						var fileUrl = subjectReturnDetailFile.file_url == null?"":subjectReturnDetailFile.file_url;
						var newhtmlStr = "<tr>" +
								"<td>"+fileName+"</td>" +
								"<td>"+createDate+"</td>" +
								"<td>"+userName+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				reSetPageParams('investorReturnDetail',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}




//加载投资人财务详情付款信息列表
function getInvestorFinanceDetailList(subjectPid,curPage,pageSize){
	$.ajax({
	    url: basePath+"/investorFinanceController/investorFinanceDetailList",
	    type: "POST",
	    data:{
	    	subjectPid:subjectPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				var investorFinanceDetailList = data["investorFinanceDetailList"];
				var prePayAndActualPayAmount = data["PrePayAndActualPayAmount"];
				var pageObj = data["page"];
				
				var expectContributiveAmount = prePayAndActualPayAmount.total_pay_amount == null ?"":prePayAndActualPayAmount.total_pay_amount;
				var actualPayAmount = prePayAndActualPayAmount.total_receiver_amount == null?"":prePayAndActualPayAmount.total_receiver_amount;
				//页码重新赋值
				if(expectContributiveAmount !=''){
					$('#prePayAmountId').text(expectContributiveAmount+"万元");
				}
				if(actualPayAmount !=''){
					$('#actualPayAmountId').text(actualPayAmount+"万元");
				}
				
				var listLength = investorFinanceDetailList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var investorFinanceDetail = investorFinanceDetailList[i];
						var payMentPid = investorFinanceDetail.payMentPid == null?"":investorFinanceDetail.payMentPid;
						var payDate = investorFinanceDetail.pay_date == null?"":new Date(investorFinanceDetail.pay_date).format("yyyy-MM-dd HH:mm:ss");
						var contributiceCompany = investorFinanceDetail.investor_name == null?"":investorFinanceDetail.investor_name;
						var payAccmount = investorFinanceDetail.pay_account == null?"":investorFinanceDetail.pay_account;
						var payTerm = investorFinanceDetail.pay_term == null?"":investorFinanceDetail.pay_term;
						var payAmount = investorFinanceDetail.pay_amount == null?"":investorFinanceDetail.pay_amount;
						var receiver_amount = investorFinanceDetail.receiver_amount == null?"":investorFinanceDetail.receiver_amount;
						var receiveCompany = investorFinanceDetail.invest_subject_name == null?"":investorFinanceDetail.invest_subject_name;
						var receiverAccount = investorFinanceDetail.receiver_account == null?"":investorFinanceDetail.receiver_account;
						var investor_op_date = investorFinanceDetail.investor_op_date == null?"":new Date(investorFinanceDetail.investor_op_date).format("yyyy-MM-dd HH:mm:ss");
						var userRealname = investorFinanceDetail.user_real_name == null?"":investorFinanceDetail.user_real_name;
						var newhtmlStr =
							    "<tr>" +
							        "<td><input type='checkbox' class='hook_inp' value='"+payMentPid+"'></td>"+
									"<td>"+payDate+"</td>" +
									"<td>"+contributiceCompany+"</td>" +
									"<td>"+payAccmount+"</td>" +
									"<td>"+payTerm+"</td>" +
									"<td>"+payAmount+"</td>" +
									"<td>"+receiver_amount+"</td>" +
									"<td>"+receiveCompany+"</td>" +
									"<td>"+receiverAccount+"</td>" +
									"<td>"+investor_op_date+"</td>" +
									"<td>"+userRealname+"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				reSetPageParams('investorFinanceDetail',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//加载投资人财务详情回款信息列表
function getInvestorReceiveDetailList(subjectPid,curPage,pageSize){
	$.ajax({
	    url: basePath+"/investorFinanceController/investorReceiveDetailList",
	    type: "POST",
	    data:{
	    	subjectPid:subjectPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				var investorReceiveDetailList = data["investorReceiveDetailList"];
				var receiverAmountAndProfit = data["receiverAmountAndProfit"];
				var pageObj = data["page"];
				
				
				if(receiverAmountAndProfit != null){
					var totalReceiverAmount = receiverAmountAndProfit.total_receiver_amount == null ?"":receiverAmountAndProfit.total_receiver_amount;
					var totalProfit = receiverAmountAndProfit.total_profit == null ?"":receiverAmountAndProfit.total_profit;
					//页码重新赋值
					if(totalReceiverAmount !=''){
						$('#investorReceive_ReceiverAmount').text(totalReceiverAmount+"万元");
					}
					if(totalProfit !=''){
						$('#investorReceive_totalProfit').text(totalProfit+"万元");
					}
				}
				var htmlStr = "";
				if(investorReceiveDetailList != null && investorReceiveDetailList.length > 0){
						var listLength = investorReceiveDetailList.length;
						for(var i=0;i<listLength;i++){
							var investorReceiveDetail = investorReceiveDetailList[i];
							
							var investorRecervePid = investorReceiveDetail.receivedPid == null?"":investorReceiveDetail.receivedPid;
							var receiverDate = investorReceiveDetail.receiver_date == null?"":new Date(investorReceiveDetail.receiver_date).format("yyyy-MM-dd HH:mm:ss");
							var receiverCompanyName = investorReceiveDetail.receiver_name == null?"":investorReceiveDetail.receiver_name;
							var receiverAmount = investorReceiveDetail.receiver_amount == null?"":investorReceiveDetail.receiver_amount;
							var profit = investorReceiveDetail.profit == null?"":investorReceiveDetail.profit;
							var receiverAccount = investorReceiveDetail.receiver_account == null?"":investorReceiveDetail.receiver_account;
							var contributiveCompanyName = investorReceiveDetail.contributive_name == null?"":investorReceiveDetail.contributive_name;
							var payAccount = investorReceiveDetail.pay_account == null?"":investorReceiveDetail.pay_account;
							var createDate = investorReceiveDetail.create_date == null?"":new Date(investorReceiveDetail.create_date).format("yyyy-MM-dd HH:mm:ss");
							var realUserName = investorReceiveDetail.user_real_name == null?"":investorReceiveDetail.user_real_name;
							
							var newhtmlStr =
								    "<tr>" +
								        "<td><input type='checkbox' class='hook_inp' value='"+investorRecervePid+"'></td>"+
										"<td>"+receiverDate+"</td>" +
										"<td>"+receiverCompanyName+"</td>" +
										"<td>"+receiverAmount+"</td>" +
										"<td>"+profit+"</td>" +
										"<td>"+receiverAccount+"</td>" +
										"<td>"+contributiveCompanyName+"</td>" +
										"<td>"+payAccount+"</td>" +
										"<td>"+createDate+"</td>" +
										"<td>"+realUserName+"</td>" +
									"</tr>";
							htmlStr = htmlStr + newhtmlStr;
						}
				}
				reSetPageParams('investorReceive',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//进入查看企业银行信息
function toSeeBank(pid){
	$.ajax({
	    url: basePath+"/corporationController/getBank",
	    type: "POST",
	    data:{
	    	pid:pid
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var successCode=data["success"]
			if(successCode == 's'){
				var bankInfo=data["bankInfo"]
				$('#see_corporationName').val($('#corporationNameId').val());
				$('#see_accountName').val(bankInfo.accountName);
				$("#see_bankSelectId").val(bankInfo.bankId);
				layui.use('form', function(){
					  var form = layui.form;
					  form.render('select');
				});
				
				$("#see_account").val(bankInfo.account);
				$("#seeRemark").val(bankInfo.remark);
				
				layer.open({
			        type: 1,
			        title: ['查看企业银行','font-size:18px;'],
			        area: ['590px', '400px'],
			        btnAlign: 'c',
			        content: $('#see_bankInfo')
			    });
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
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


//给分页栏重新赋值及充填列表
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



//信息列表首页、上一页、下一页、尾页按钮
function getObjListByPage(idCode) {
	var corpId = $('#corBasePid').val();
	var investorPidVal = $('#investorPid').val();
	var subjectPayPidVal = $('#investorPay_investorPayPid').val();
	var subjectReturnPidVal = $('#investorReturnDetail_investorReturnPid').val();
	
	var curDomIdFirstStr = idCode.split('_')[0];
	var options=$("#"+curDomIdFirstStr+"_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	
	//企业银行
	if(corpId != "" && curDomIdFirstStr == 'corBank'){
		getBankList(corpId,curPage,pageSize);
	}
	
	//投资人附件
	if(investorPidVal != "" && curDomIdFirstStr == 'investFile'){
		getSubjectFileList(investorPidVal,curPage,pageSize);
	}
	
	//投资人财务详情付款
	if(investorPidVal != "" && curDomIdFirstStr == 'investorFinanceDetail'){
		getInvestorFinanceDetailList(investorPidVal,curPage,pageSize);
	}
	
	//投资人财务详情付款信息明细
	if(subjectPayPidVal != "" && curDomIdFirstStr == 'eachOneOfInvestorFile'){
		getSubjectPaymentDetailFileList(subjectPayPidVal,curPage,pageSize);
	}
	
	//投资人财务详情回款
	if(investorPidVal != "" && curDomIdFirstStr == 'investorReceive'){
		getInvestorReceiveDetailList(investorPidVal,curPage,pageSize);
	}
	
	//每条投资人回款明细附件列表
	if(subjectReturnPidVal != "" && curDomIdFirstStr == 'investorReturnDetail'){
		getSubjectReturnDetailFileList(subjectReturnPidVal,curPage,pageSize);
	}
}



