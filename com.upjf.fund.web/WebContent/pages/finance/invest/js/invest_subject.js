layui.use('form',function(){
	var form = layui.form;
	var investType = $("#investType").val();
	/*新增操作URL*/
	var addOperationUrl = $("#addOperationUrl").val();
	/*修改操作URL*/
	var modifiyOperationUrl = $("#modifiyOperationUrl").val();
	/*查看操作URL*/
	var viewOperationUrl = $("#viewOperationUrl").val();
	/*删除操作URL*/
	var deleteOperationUrl = $("#deleteOperationUrl").val();
	form.on('select(payTerm)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"payTerm");
		}
	});
	
	/*根据项目公司异步加载收款公司(投资主体公司上一级，项目股东关联公司)*/
	form.on('select(prjId)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"prjId");
		}
		$("#receiverId").find("option[value!='']").remove();
		form.render("select");
		var prjId = data.value;
		if(investType == "3"){
			loadStockholder(prjId, null);
		}else{
			loadReceiverCorp(prjId,null);
		}
	});
	
	/*根据项目股东加载投资主体*/
	form.on('select(stockholderId)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"stockholderId");
		}
		$("#receiverId").find("option[value!='']").remove();
		form.render("select");
		var stockholderId = data.value;
		var stockholderId = $(data.elem).find("option[value='"+stockholderId+"']").attr("corp-id");
		var prjId = $("#prjId").val();
		loadInvestSubject(prjId,stockholderId,null);
	});
	
	
	/*根据收款公司和项目公司异步加载出资主体(关联资管计划表)及收款银行信息*/
	form.on('select(receiverId)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"receiverId");
		}
		var prjId = $("#prjId").val();
		$("#contributiveId").find("option[value!='']").remove();
		form.render("select");
		var receiverId = data.value;
		if(investType == "3"){
			var parentId = $(data.elem).find("option[value='"+receiverId+"']").attr("parent-id");
			loadInvestorCorp(parentId, null);
		}else{
			loadInvestSubject(prjId,receiverId,null);
		}
		if(investType == "2"){
			var stockholderId = $(data.elem).find("option[value='"+receiverId+"']").attr("stockholder-id");
			$("#stockholderId").val(stockholderId);
		}
		/*加载收款银行信息*/
		$("#receiverBankId").find("option[value!='']").remove();
		form.render("select");
		loadBankInfo(receiverId,null,"receive");
	});
	/*获取付款银行信息*/
	form.on('select(contributiveId)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"contributiveId");
		}
		$("#payBankId").find("option[value!='']").remove();
		form.render("select");
		var corpId = data.value;
		loadBankInfo(corpId,null,"pay");
	});
	
	/*获取收款账户*/
	form.on('select(receiverBankId)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"receiverBankId");
		}
		$("#receiverAccount").find("option[value!='']").remove();
		form.render("select");
		var corpId = $("#receiverId").val();
		var bankId = data.value;
		loadReceivedAccount(corpId,bankId,null);
	});
	
	/*获取付款账户*/
	form.on('select(payBankId)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"payBankId");
		}
		$("#payAccount").find("option[value!='']").remove();
		form.render("select");
		var corpId = $("#contributiveId").val();
		var bankId = data.value;
		loadPayAccount(corpId,bankId,null);
	});
	
	/*收款账户*/
	form.on('select(receiverAccount)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"receiverAccount");
		}
	});
	
	/*付款账户*/
	form.on('select(payAccount)',function(data){
		if(data.value){
			validator.resetFormField($("#investForm"),"payAccount");
		}
	});
	
	/*新增*/
	$("#add_invest").on("click",function(){
		if(getProjectStatus()=="3"){
			layer.msg("项目状态已完结，不可新增记录",{time:1000,icon:7});
			return ;
		}
		
		window.location.href = addOperationUrl;
	});
	
	/*修改*/
	$("#modify_invest").on("click",function(){
		if(getProjectStatus()=="3"){
			layer.msg("项目状态已完结，不可修改记录",{time:1000,icon:7});
			return ;
		}
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		window.location.href = modifiyOperationUrl+"&pid="+rowData.pid;
	});
	
	/*查看*/
	$("#view_invest").on("click",function(){
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		window.location.href = viewOperationUrl+"&pid="+rowData.pid;
	});
	
	/*删除*/
	$("#delete_invest").on("click",function(){
		if(getProjectStatus()=="3"){
			layer.msg("项目状态已完结，不可删除记录",{time:1000,icon:7});
			return ;
		}
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		layer.confirm('确认删除？',function(){
			$.ajax({
      		    url: deleteOperationUrl,
      		    cache: true,
      		    type: "POST",
      		    data: {
      		    	pid: rowData.pid,
      		    },
      		    async: false,
      		    success : function(msg) {
    				if (msg && msg["successMsg"]) {
    					layer.msg(msg["successMsg"],{icon: 1});
    					$("#data_list").trigger("reloadGrid");
    					$("#pay_amount").text(msg.amount.pay_amount);
    					$("#receiver_amount").text(msg.amount.receiver_amount);
    				} else {
    					layer.msg(msg["errMsg"], {time:1000,icon:7});
    				}
    			},
    			error:function(msg){
    				alert(msg["errMsg"]);
    			}
      		});
		},function(){
		});
	});
	
});

/**
 * 初始化付款期数下拉列表
 */
function initPayTermSelect(val){
	var options = '';
	var selected = ''
	for(var i=0;i<100;i++){
		if(val==i){
			selected = 'selected ="selected"';
		}else{
			selected = '';
		}
		options+='<option value="'+i+'" '+selected+'>'+i+'</option>'
	}
	$("#payTerm").append(options);
	layui.use('form',function(){
		var form = layui.form;
		form.render("select");
	});
}

/**
 * 判断是否只勾选了一条记录
 * @returns {Boolean}
 */
function checkedSelectedRows(){
	var rowIds=$('#data_list').jqGrid('getGridParam','selarrrow');
	if(rowIds.length>1){
		layer.msg("只能选中一条数据",{icon:7})
		return false;
	}
	if(rowIds.length==0){
		layer.msg("请选择数据",{icon:7})
		return false;
	}
	return true;
}

/**
 * 获取选中行数据
 * @returns
 */
function getRowData(){
	var rowid = $("#data_list").getGridParam("selrow");
	return $("#data_list").jqGrid("getRowData",rowid);
}

/**
 * 获取项目状态
 * @returns
 */
function getProjectStatus(){
	var rowData = getRowData();
	return projectStatus = rowData.project_status;
}

/**根据项目加载项目股东*/
function loadStockholder(prjId,stockholderId){
	$.ajax({
		url: basePath+"prjContactCorp/getProjectStockholder",
	    type: "POST",
	    data:{
	    	prjId: prjId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				/*如果存在股东栏位，则直接填充股东，否则填充收款公司*/
				$.each(result,function(i,n){
					if(stockholderId==n.pid){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#stockholderId").append('<option value="'+n.pid+'" corp-id="'+n.corp_id+'" '+selected+'>'+n.name+'</option>');
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


/**根据项目公司异步加载收款公司(投资主体公司上一级，项目股东关联公司)*/
function loadReceiverCorp(prjId,receiverId){
	$.ajax({
		url: basePath+"prjContactCorp/getProjectStockholderCorp",
	    type: "POST",
	    data:{
	    	prjId: prjId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(receiverId==n.pid){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#receiverId").append('<option value="'+n.pid+'" stockholder-id="'+n.stockholder_id+'" '+selected+'>'+n.name+'</option>');
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

/**根据收款公司(项目公司)和项目公司异步加载出资主体(关联资管计划表)*/
function loadInvestSubject(prjId,receiverId,contributiveId){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestSubjectCorp",
	    type: "POST",
	    data:{
	    	prjId: prjId,
	    	corpId: receiverId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				var dom = $("#contributiveId");
				var investType = $("#investType").val();
				if(investType == "3"){
					dom = $("#receiverId");
				}
				$.each(result,function(i,n){
					if(contributiveId==n.pid){
						selected = 'selected="selected"';
					}else{
						selected = '';
					}
					dom.append('<option value="'+n.pid+'" parent-id="'+n.parentId+'" '+selected+'>'+n.name+'</option>');
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

/**根据项目公司异步加载收款公司(投资主体)*/
function loadReceiverCorpInvestSubject(prjId,receiverId){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestSubjectCorpByPrjId",
	    type: "POST",
	    data:{
	    	prjId: prjId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(receiverId==n.pid){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#receiverId").append('<option value="'+n.pid+'" parent-id="'+n.parentId+'" '+selected+'>'+n.name+'</option>');
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

/**根据收款公司(投资主体)异步加载出资主体(投资人)*/
function loadInvestorCorp(parentId,contributiveId){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestorCorpByInvestSubjectPid",
	    type: "POST",
	    data:{
	    	parentId: parentId
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(contributiveId==n.pid){
						selected = 'selected="selected"';
					}else{
						selected = '';
					}
					$("#contributiveId").append('<option value="'+n.pid+'" '+selected+'>'+n.name+'</option>');
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

/**加载银行信息*/
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
						$("#receiverBankId").append('<option value="'+n.pid+'" '+receiverSelected+'>'+n.name+'</option>');
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
						$("#payBankId").append('<option value="'+n.pid+'" '+paySelected+'>'+n.name+'</option>');
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
					$("#receiverAccount").append('<option value="'+n+'" '+selected+'>'+n+'</option>');
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
					$("#payAccount").append('<option value="'+n+'" '+selected+'>'+n+'</option>');
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
function loadAccessoryFiles(url,data,pageStatus,dataList,pager){
	var colNames = ["文件名","文件类型","上传时间","上传人","操作"]
	var colModel = [
	                 {name:"file_all_name", index:"file_all_name",sortable:false},
	                 {name:"accessory_type_name", index:"accessory_type_name",sortable:false},
	                 {name:"create_date", index:"create_date",sortable:false},
	                 {name:"user_name", index:"user_name",sortable:false},
	                 {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
	                	 var url = "'"+rowObject.file_url+'&filePid='+rowObject.file_pid+'&fileName='+rowObject.file_all_name+"'";
	                	 var dataListId;
	                	 if(dataList){
	                		 var dataListId = "'"+dataList.attr("id")+"'";
	                	 }
	                	 var del= '<a onclick="deleteFile('+encodeURI(url)+','+dataListId+')" class="table_bnt file_del">删除</a>';
	                	 if(pageStatus=="view"||pageStatus=="readonly"){
	                		 del='<a onclick="deleteFile('+encodeURI(url)+','+dataListId+')" class="table_bnt file_del op_hide">删除</a>';
	                	 }
	                	 return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
	                	 		'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>'+del;
	                 }}
	                  
	                ]
	var complateFunc = function(){};
	loadData(url, colNames, colModel, complateFunc,data,dataList,pager);
}

