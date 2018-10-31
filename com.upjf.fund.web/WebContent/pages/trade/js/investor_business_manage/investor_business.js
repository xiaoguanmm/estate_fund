var form='';
var accessType = $("#accessType").val();
var investPlanManagePid = $("#investPlanManagePid").val();
var projectInfoPid = $("#prjPid").val();
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
		
		laydate.render({
			elem: '#investStartDate',
			done: function(value, date, endDate){
				// 校验日期不为空
//				validator.resetFormField($("#addInvestorForm"),"investStartDate");
			}
		});
		
		/*根据项目名称加载楼盘名称、股东信息*/
		form.on('select(prjId)',function(data){
			if(data.value){
				validator.resetFormField($("#addInvestorForm"),"prjId");
			}
			form.render();
			var prjId = data.value;
			loadBuildingName(prjId, null);
			/*加载股东名称*/
			$("#stockholderCorpId").find("option[value!='']").remove();
			form.render("select");
			loadStockInfoByPrjId(prjId,null,"stockholderCorpId");
		});
		
		/*根据股东名称加载投资主体名称*/
		form.on('select(stockholderCorpId)',function(data){
			if(data.value){
				validator.resetFormField($("#addInvestorForm"),"stockholderCorpId");
			}
			var stockholderCorpId = data.value;
			var stockholderPid = $(data.elem).find("option[value='"+stockholderCorpId+"']").attr("stockholderPid");
			
			$("#investSubjectCorPid").find("option[value!='']").remove();
			form.render("select");
			loadInvestSubjectCorpByStockCorpId(stockholderPid,null);
		});
		
	});
	
	// 根据条件查询投资人业务管理列表
	validator.validate($("#addInvestorForm"));
	
	var investSubjectPid = $("#investSubjectPid").val();
	var data = {investPlanManagePid:investPlanManagePid,
				investSubjectPid:investSubjectPid
			   };
	var url = basePath+"investorBusinessManage/queryInvestorBusinessManageList";
	var colNames = ["investorPid","investorCorpId","investManagePlanPid","stockholderPid","investSubjectPid","investSubjectCorpId","projectInfoPid","stockholderCorpId",
	                "项目名称","楼盘名称","投资主体名称","投资人名称","预计出资额(万元)","持股比例(%)","实际出资(万元)","出资期数","分红方式code","分红方式","年化利率(%)","预计总回款(万元)","已收本金(万元)","已收回报(万元)"
	               ];
	
	var colModel = [
	                {name: "investorPid",index: "investorPid", sortable :false, hidden : true},
	                {name: "investorCorpId",index: "investorCorpId", sortable :false, hidden : true},
	                {name: "investManagePlanPid",index: "investManagePlanPid", sortable :false, hidden : true},
	                {name: "stockholderPid",index: "stockholderPid", sortable :false, hidden : true},
	                {name: "investSubjectPid",index: "investSubjectPid", sortable :false, hidden : true},
	                {name: "investSubjectCorpId",index: "investSubjectCorpId", sortable :false, hidden : true},
	                {name: "projectInfoPid",index: "projectInfoPid", sortable :false, hidden : true},
	                {name: "stockholderCorpId",index: "stockholderCorpId", sortable :false, hidden : true},
	                
	                
	                {name: "project_name",index: "project_name",sortable :false,
	                	formatter:function(cellvalue, options, rowObject){
					        var str = "<a href='javascript:void(0)' class='table_bnt show_accessory' " +
					       		"onclick= \"viewProjectInfo('"+rowObject.projectInfoPid+"')\" >"+rowObject.project_name+"</a>";
					        return str;
			   	   	    }
	                },
	                {name: "building_name",index: "building_name",sortable :false},
	                {name: "investSubjectName",index: "investSubjectName",sortable :false},
	                {name: "investorName",index: "investorName", sortable :false},
	                {name: "expect_contributive_amount",index: "expect_contributive_amount",sortable :false},
	                {name: "hold_stock_rate",index: "hold_stock_rate", sortable :false},
	                {name: "realInvestAmount",index: "realInvestAmount", sortable :false},
	                {name: "maxPayTerm",index: "maxPayTerm", sortable :false},
	                {name: "dividend_type",index: "dividend_type", sortable :false, hidden : true},
	                {name: "dividendTypeName",index: "dividendTypeName", sortable :false},
	                {name: "annualized_interest_rate",index: "annualized_interest_rate", sortable :false},
	                {name: "expect_all_receiver_account",index: "expect_all_receiver_account", sortable :false, hidden : true},
	                {name: "receivedPrincipal",index: "receivedPrincipal", sortable :false},
	                {name: "receivedProfit",index: "receivedProfit", sortable :false}
	                ];
	
	var complateFunc = function(){
	}
	loadData(url, colNames, colModel, complateFunc, data);
	
	
	//重置按钮
	$('#investPlanQueryReset').on('click',function(){
		$('#projectName').val('');
		$('#PrjCompanyName').val('');
		$('#stockholderName').val('');
	});
	
	// 预计总回款    失去焦点事件
	$("#investExpectAllReceiverAccount").blur(function(){
		calculateExpectIncome();
	});
	// 预计出资额    失去焦点事件
	$("#expectContributiveAmount").blur(function(){
		calculateExpectIncome();
	});
	
	
	// 新增投资人
	$('#addInvestor').on('click', function(){
		
		document.getElementById("addInvestorForm").reset();
		$("#addInvestorForm").find("input[name='investorPid']").val('');
		
		//accessType=1代表从资管计划页面进入
		if(accessType == 1) {
			var buildingName = $("#buildingName").val();
			var stockholderCorpId = $("#stockholderCorpPId").val();
			var investSubjectCorpId = $("#investSubjectCorpPId").val();
			$("#addInvestorForm").find("select[name='prjId']").val(projectInfoPid);
			$("#addInvestorForm").find("input[name='buildName']").val(buildingName);
			$("#addInvestorForm").find("select[name='stockholderCorpId']").val(stockholderCorpId);
			$("#addInvestorForm").find("select[name='investSubjectCorPid']").val(investSubjectCorpId);
			form.render("select");
			form.render();
			$("#prjId").attr("disabled",true);
			$("#buildName").attr("disabled",true);
			$("#stockholderCorpId").attr("disabled",true);
			$("#investSubjectCorPid").attr("disabled",true);
			
			var businessPrjInfoPid = $("#businessPrjInfoPid").val();
			$("#investBusinessPrjInfoPid").val(businessPrjInfoPid);
			$("#investBusinessPrjInfoPid").attr("disabled",true);
			form.render();
		} 
		
		if(isSaveInvestPlanManage()) {
			layer.open({
				type: 1,
				title: ['新增投资人','font-size:18px;'],
				area: ['1040px', '620px'],
				btnAlign: 'c',
				content: $('#add_Investor_dialog'),
				btn: ['保存', '取消'],
				success: uploadInvestorAccessory(),
				btn1: function(index, layero){
					$("#prjId").attr("disabled",false);
					$("#buildName").attr("disabled",false);
					$("#stockholderCorpId").attr("disabled",false);
					$("#investSubjectCorPid").attr("disabled",false);
					/*手动触发表单校验*/
					validator.triggerForm($("#addInvestorForm"));
					/*校验通过提交表单*/
					if(validator.isValid($("#addInvestorForm"))){
						var data = $("#addInvestorForm").serialize();
						$.ajax({
							url: $("#addInvestorForm").attr("action"),
							cache: true,
							type: "POST",
							data: data,
							async: false,
							success : function(msg) { 
								
								//表单提交后更新页面显示的数据
								if (msg && msg["successMsg"]) {
									$("input[name='investorPid']").val(msg["investorPid"]);
									layer.msg(msg["successMsg"],{icon: 1});
									$("#data_list").trigger("reloadGrid");
//									validator.resetForm($("#addInvestorForm"));
								} else {
									layer.msg(msg["errMsg"], {time:1000,icon:7});
								}
							},
							error:function(msg){
								alert(msg["errMsg"]);
							}
						});
					}    
				},
				btn2: function(index, layero){
					
				}
			});
		} else {
			layer.msg("请先保存资管计划基础信息！", {time:1000,icon:7});
		}
	});
	
	// 修改投资人
	$('#modifyInvestor').on('click', function(){
		
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			$("#prjId").attr("disabled",true);
			$("#buildName").attr("disabled",true);
			$("#stockholderCorpId").attr("disabled",true);
			$("#investSubjectCorPid").attr("disabled",true);
			$("#investorName").attr("disabled",true);
			// 出资期数  不让修改
			$("#term").attr("disabled",true);
			// 回显下拉选择框
			$("#addInvestorForm").find("select[name=prjId]").val(rowData.projectInfoPid);
			$("#addInvestorForm").find("input[name=buildName]").val(rowData.building_name);
			$("#addInvestorForm").find("select[name=stockholderCorpId]").val(rowData.stockholderCorpId);
			$("#addInvestorForm").find("select[name=investSubjectCorPid]").val(rowData.investSubjectCorpId);
			$("#addInvestorForm").find("select[name=contributiveType]").val(rowData.contributive_type);
			$("#addInvestorForm").find("input[name=expectContributiveAmount]").val(rowData.expect_contributive_amount);
			$("#addInvestorForm").find("select[name=investorName]").val(rowData.investorCorpId);
			$("#addInvestorForm").find("input[name=holdStockRate]").val(rowData.hold_stock_rate);
			$("#addInvestorForm").find("select[name=level]").val(rowData.level);
			$("#addInvestorForm").find("select[name=dividendType]").val(rowData.dividend_type);
			$("#addInvestorForm").find("input[name=term]").val(rowData.pay_term);
			$("#addInvestorForm").find("input[name=expectAllReceiverAccount]").val(rowData.expect_all_receiver_account);
			$("#addInvestorForm").find("input[name=annualizedInterestRate]").val(rowData.annualized_interest_rate);
			$("#addInvestorForm").find("input[name=expectIncome]").val(rowData.expect_income);
			$("#addInvestorForm").find("input[name=investStartDate]").val(formatDate(rowData.invest_start_date));
			$("#addInvestorForm").find("input[name=expectIncomeRate]").val(rowData.expect_income_rate);
			
			$("#addInvestorForm").find("input[name=investorPid]").val(rowData.investorPid);
			form.render('select');
			form.render();
			
			if(isSaveInvestPlanManage()) {
				layer.open({
					type: 1,
					title: ['修改投资人','font-size:18px;'],
					area: ['1040px', '620px'],
					btnAlign: 'c',
					content: $('#add_Investor_dialog'),
					btn: ['保存', '取消'],
					success: uploadInvestorAccessory(),
					btn1: function(index, layero){
						$("#prjId").attr("disabled",false);
						$("#buildName").attr("disabled",false);
						$("#stockholderCorpId").attr("disabled",false);
						$("#investSubjectCorPid").attr("disabled",false);
						$("#investorName").attr("disabled",false);
						$("#term").attr("disabled",false);
						/*手动触发表单校验*/
						validator.triggerForm($("#addInvestorForm"));
						/*校验通过提交表单*/
						if(validator.isValid($("#addInvestorForm"))){
							var data = $("#addInvestorForm").serialize();
							$.ajax({
								url: $("#addInvestorForm").attr("action"),
								cache: true,
								type: "POST",
								data: data,
								async: false,
								success : function(msg) { 
									//表单提交后更新页面显示的数据
									if (msg && msg["successMsg"]) {
										$("input[name='investSubjectPid']").val(msg["investSubjectPid"]);
										layer.msg(msg["successMsg"],{icon: 1});
										$("#data_list").trigger("reloadGrid");
//										validator.resetForm($("#addInvestorForm"));
									} else {
										layer.msg(msg["errMsg"], {time:1000,icon:7});
									}
								},
								error:function(msg){
									alert(msg["errMsg"]);
								}
							});
						}    
					},
					btn2: function(index, layero){
						
					}
				});
			} else {
				layer.msg("请先保存资管计划基础信息！", {time:1000,icon:7});
			}
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	// 查看投资人详情
	$('#viewInvestorDetail').on('click', function(){
		
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			window.location.href = basePath + "investorBusinessManage/toInvestorBusinessManangeDetail?type=3&investorPid="+rowData.investorPid+"&projectPid="+rowData.projectInfoPid;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------删除投资人---------*/
	$("#delInvestor").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length >= 1) {
			var investorPids = "";
			for (var i = 0; i < rowsId.length; i++) {
				var rowData = $("#data_list").jqGrid("getRowData", rowsId[i]);
				investorPid = rowData.investorPid;
				investorPids += investorPid + ",";
			}
			if(investorPids.length > 0) {
				investorPids = investorPids.substr(0,investorPids.length-1);
			}
			
			layer.confirm('确认删除？',function(){
				$.ajax({
	      		    url: basePath+"investorBusinessManage/deleteInvestors",
	      		    cache: true,
	      		    type: "POST",
	      		    data: {investorPids:investorPids},
	      		    async: false,
	      		    success : function(msg) {
	    				if (msg && msg["successMsg"]) {
	    					layer.msg(msg["successMsg"],{icon: 1});
	    					$("#data_list").trigger("reloadGrid");
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
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
		
});

// 是否保存资管计划方法
function isSaveInvestPlanManage() {
	var isSave = false;
	// 从资管计划入口进入
	if(accessType == 1) {
		if(investPlanManagePid != null && investPlanManagePid != 'undefined' && investPlanManagePid != '') {
			isSave = true;
		}
	} else {
		isSave = true;
	}
	return isSave;
}

//计算预期收益（预计总回款-预计出资额）、预期收益率
function calculateExpectIncome() {
	// 预计出资额
	var expectContributiveAmount = $("#expectContributiveAmount").val();
	// 预计总回款
	var expectAllReceiverAccount = $("#investExpectAllReceiverAccount").val();
	// 预期收益
	var expectIncome;  
	// 预期收益率
	var expectIncomeRate;
	if(expectContributiveAmount != 'undefined' && expectAllReceiverAccount != 'undefined' 
		&& expectContributiveAmount != '' && expectAllReceiverAccount != ''
		&& expectContributiveAmount>=0 && expectAllReceiverAccount>=0) {
		if(expectAllReceiverAccount > expectContributiveAmount) {
			expectIncome = expectAllReceiverAccount - expectContributiveAmount;
			expectIncomeRate = expectIncome/expectContributiveAmount
			$("#expectIncome").val(expectIncome);
			$("#expectIncomeRate").val(expectIncomeRate);
		} else {
			layer.msg("预计总回款 应大于 预计出资额", {time:1000,icon:7});
		}
	}
}

/**根据项目名称加载楼盘名称**/
function loadBuildingName(projectInfoPid){
	
	$.ajax({
		url: basePath+"prjContactCorp/getProjectInfoByPrjId",
	    type: "POST",
	    data:{
	    	projectInfoPid: projectInfoPid
	    },
	    async: true,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				$("#buildName").val(result.buildingName);
				layui.use('form',function(){
					var form = layui.form;
					form.render();
				});
			}
		},
		error:function(msg){
			alert(msg["errMsg"]);
		}
	});
}

/**根据项目名称加载股东**/
function loadStockInfoByPrjId(projectInfoPid,stockholderCorpId,id){
	$.ajax({
		url: basePath+"prjContactCorp/getStockInfoByPrjId",
	    type: "POST",
	    data:{
	    	projectInfoPid: projectInfoPid
	    },
	    async: true,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(stockholderCorpId==n.stockholderCorpId){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#"+id).append('<option value="'+n.stockholderCorpId+'" stockholderPid="'+n.stockholderPid+'"'+selected+'>'+n.name+'</option>');
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

/**根据股东pid 加载投资主体名称*/
function loadInvestSubjectCorpByStockCorpId(stockholderPid,investSubjectCorPid){
	$.ajax({
		url: basePath+"prjContactCorp/getInvestSubjectCorpByStockCorpId",
	    type: "POST",
	    data:{
	    	stockholderPid: stockholderPid
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(investSubjectCorPid==n.investSubjectCorpId){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#investSubjectCorPid").append('<option value="'+n.investSubjectCorpId+'"'+selected+'>'+n.name+'</option>');
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

//新增、修改  投资人  弹窗上传附件
var investorLoadFlag = false;
function uploadInvestorAccessory() {
	var investorPid = $("#addInvestorForm").find("input[name=investorPid]").val();
	
	$("#addInvestor_select_file").on("click",function(){
		
		investorPid = $("#addInvestorForm").find("input[name=investorPid]").val();
		if(investorPid!=""){
			$("#addInvestor_selectFileButton").trigger("click");
		}else{
			layer.msg("请先保存数据",{icon:7});
		}
	});
	
	var data = {pid:investorPid,accessoryType:2};
	// 异步     第二次刷新附件列表时，不自动查询后台数据库的bug;加状态
	if(investorLoadFlag){
		$("#investorAccessory_data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		uploadFiles1($("#addInvestor_selectFileButton"), $("#addInvestor_fileList"), $("#addInvestor_uploadFileButton"),$("#investorAccessory_data_list")
				, basePath+"investPlanManage/uploadInvestSubjectAccessory",data,"investorPid");
		var url = basePath+"investPlanManage/queryInvestSubjectAccessoryList";
		var colNames = ["文件名","附件类型","上传时间","上传人","操作"]
		var colModel = [
		                {name:"file_all_name", index:"file_all_name",sortable:false},
		                {name:"invest_accessory_type", index:"invest_accessory_type",sortable:false,
		                	formatter:function(cellvalue, options, rowObject){
		                		var investAccessoryType = rowObject.invest_accessory_type;
		                		if(investAccessoryType == '1') {
		                			return "投资主体附件";
		                		} else {
		                			return "投资人附件";
		                		}
		                	}
		                },
		                {name:"create_date", index:"create_date",sortable:false},
		                {name:"user_name", index:"user_name",sortable:false},
		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
		                }}
		                ]
		var complateFunc = function(){
			investorLoadFlag = true;
		};
		loadData1("investorAccessory_data_list","investorAccessory_page_list",url,colNames,colModel,complateFunc,data);
		
	}
}

// "项目名称" 超链接(跳转至项目管理模块)
function viewProjectInfo(projectInfoPid) {
	window.location.href = basePath + "/projectManageController/toProject?type=3&projectPid="+projectInfoPid;
}







