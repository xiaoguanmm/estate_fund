var form='';
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
//		var $ = jQuery = layui.$;
		
		laydate.render({
			elem: '#investStartDate',
			done: function(value, date, endDate){
				// 校验投资起始日不为空
				validator.resetFormField($("#investPlanManageForm"),"investStartDate");
			}
		});
		laydate.render({
			elem: '#investSubjectStartDate',
			done: function(value, date, endDate){
				// 校验日期不为空
//				validator.resetFormField($("#addInvestSubjectForm"),"investStartDate");
			}
		});
		
		// 新增资管计划时【项目名称】不能为空触发校验
		form.on('select(projectInfoPid)',function(data){
			if(data.value){
				validator.resetFormField($("#investPlanManageForm"),"projectInfoPid");
			}
		});
		// 新增资管计划时【项目公司】不能为空触发校验
		form.on('select(businessPrjInfoPid)',function(data){
			if(data.value){
				validator.resetFormField($("#investPlanManageForm"),"businessPrjInfoPid");
			}
		});
		// 新增资管计划时【股东】不能为空触发校验
		form.on('select(stockholderInfoPid)',function(data){
			if(data.value){
				validator.resetFormField($("#investPlanManageForm"),"stockholderInfoPid");
			}
		});
		
		/*************************************资管计划基础信息js开始*******************************************/
		
		/*【项目名称】下拉框     根据项目名称异步加载项目公司(一个项目只能找到对应的项目公司。而一个项目公司则可以对应多个项目)*/
		form.on('select(projectInfoPid)',function(data){
			
			if(data.value){
				validator.resetFormField($("#investPlanManageForm"),"projectInfoPid");
			}
			$("#businessPrjInfoPid").find("option[value!='']").remove();
			form.render("select");
			var projectInfoPid = data.value;
			loadBusinessPrjInfo(projectInfoPid, null,"businessPrjInfoPid");
		});
		/* 【项目公司】下拉框    根据项目名称和项目公司异步加载股东*/
		form.on('select(businessPrjInfoPid)',function(data){
			if(data.value){
				validator.resetFormField($("#investPlanManageForm"),"businessPrjInfoPid");
			}
			var projectInfoPid = $("#projectInfoPid").val();
			$("#stockholderInfoPid").find("option[value!='']").remove();
			form.render("select");
			var businessPrjInfoPid = data.value;
			loadStockByBusinessPrjPid(projectInfoPid,businessPrjInfoPid,null);
		});
		
		form.on('select(investType)',function(data){
			debugger;
			if(data.value){
				validator.resetFormField($("#investPlanManageForm"),"investType");
			}
			if(data.value == '1'){
				validator.resetFiled($("#investPlanManageForm"),"returnedProfit");
				$("#returnedProfitDiv").hide();
			} else {
				$("#returnedProfitDiv").show();
			}
		});
		
		/*************************************资管计划基础信息js结束*******************************************/
		
	});
	validator.validate($("#investPlanManageForm"));
	validator.validate($("#addInvestSubjectForm"));

	
	/*************************************资管计划基础信息js开始(layui外面一层)*******************************************/
	var investPlanManagePid = $("#investPlanManagePid").val();
	var investPlanManageOperation = $("#investPlanManageOperation").val();
	if(investPlanManageOperation != "add"){
		$("#projectInfoPid").attr("disabled",true);
		$("#businessPrjInfoPid").attr("disabled",true);
		$("#stockholderInfoPid").attr("disabled",true);
		
		// 加载投资主体列表
		queryInvestSubjectList(investPlanManagePid);
	}
	
	
	
	// 新增、修改 资管计划        保存 
	validator.validate($("#investPlanManageForm"), function(dom){
		debugger;
		$("#projectInfoPid").attr("disabled",false);
		$("#businessPrjInfoPid").attr("disabled",false);
		$("#stockholderInfoPid").attr("disabled",false);
		$.ajax({
  		    url: dom.attr("action"),
  		    cache: true,
  		    type: "POST",
  		    data: dom.serialize(),
  		    async: false,
  			success : function(msg) {
  				if (msg && msg["successMsg"]) {
  					layer.msg(msg["successMsg"],{icon: 1});
  					dom.find("input[name=investPlanManagePid]").val(msg.investPlanManagePid);
  					// 刷新 投资主体列表
  					queryInvestSubjectList(msg.investPlanManagePid);
  				} else {
  					layer.msg(msg["errMsg"], {time:1000,icon:7});
  				}
  			},
  			error:function(msg){
  				alert(msg["errMsg"]);
  			}
  		}); 
	});
	
	/*************************************资管计划基础信息js结束(layui外面一层)*******************************************/
	
	/*************************************新增投资主体js开始(layui外面一层)*******************************************/
	
	// 新增投资主体(项目名称、项目公司数据直接带过来且只读，不能选择)
	$('#add_Investment1').on('click', function(){
		
		document.getElementById("addInvestSubjectForm").reset();
		$("#addInvestSubjectForm").find("input[name='investSubjectPid']").val('');
		
		var investPlanManagePid = $("#investPlanManagePid").val();
		$("#subjectFormInvestPlanManagePid").val(investPlanManagePid);
		
		var projectInfoPid = $("#projectInfoPid").val();
		$("#prjId").val(projectInfoPid);
		$("#prjId").attr("disabled",true);
		// 加载楼盘名称(此时不能异步加载，不然选择附件按钮会重复点击)
		loadBuildingName(projectInfoPid);
		
		var businessPrjInfoPid = $("#businessPrjInfoPid").val();
		$("#investBusinessPrjInfoPid").val(businessPrjInfoPid);
		$("#investBusinessPrjInfoPid").attr("disabled",true);
		
		form.render();
		if(investPlanManagePid) {
			layer.open({
				type: 1,
				title: ['新增投资主体','font-size:18px;'],
				area: ['1040px', '620px'],
				btnAlign: 'c',
				content: $('#add_Investment'),
				btn: ['保存', '取消'],
				success: uploadInvestSubjectAccessory(),
				btn1: function(index, layero){
					$("#prjId").attr("disabled",false);
					$("#investBusinessPrjInfoPid").attr("disabled",false);
					/*手动触发表单校验*/
					validator.triggerForm($("#addInvestSubjectForm"));
					/*校验通过提交表单*/
					if(validator.isValid($("#addInvestSubjectForm"))){
						var data = $("#addInvestSubjectForm").serialize();
						$.ajax({
							url: $("#addInvestSubjectForm").attr("action"),
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
//									validator.resetForm($("#addInvestSubjectForm"));
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
	
	// 修改投资主体(项目名称、项目公司数据直接带过来且只读，不能选择)
	$('#modify_Investment1').on('click', function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId && rowsId.length == 1) {
			$("#prjId").attr("disabled",true);
			$("#investBusinessPrjInfoPid").attr("disabled",true);
			$("#investSubjectName").attr("disabled",true);
			// 回显下拉选择框
			$("#addInvestSubjectForm").find("select[name=prjId]").val(rowData.projectInfoPid);
			$("#addInvestSubjectForm").find("input[name=buildName]").val(rowData.building_name);
			$("#addInvestSubjectForm").find("select[name=contributiveType]").val(rowData.contributive_type);
			$("#addInvestSubjectForm").find("select[name=businessPrjInfoPid]").val(rowData.businessPrjInfoPid);
			$("#addInvestSubjectForm").find("input[name=expectContributiveAmount]").val(rowData.expect_contributive_amount);
			$("#addInvestSubjectForm").find("select[name=investSubjectName]").val(rowData.invest_subject_id);
			$("#addInvestSubjectForm").find("input[name=holdStockRate]").val(rowData.hold_stock_rate);
			var isHuilianCorp = rowData.is_huilian_corp;
			var isHuilianCorpCode = '';
			if(isHuilianCorp == '是') {
				isHuilianCorpCode = '1';
			} else if(isHuilianCorp == '否') {
				isHuilianCorpCode = '0';
			} else {
				isHuilianCorpCode = '';
			}
			$("input:radio[name='isHuilianCorp'][value='"+isHuilianCorpCode+"']").attr("checked",'checked');
			$("#addInvestSubjectForm").find("select[name=dividendType]").val(rowData.dividend_type);
			$("#addInvestSubjectForm").find("select[name=level]").val(rowData.level);
			$("#addInvestSubjectForm").find("input[name=expectAllReceiverAccount]").val(rowData.expect_all_receiver_account);
			$("#addInvestSubjectForm").find("input[name=term]").val(rowData.term);
			$("#addInvestSubjectForm").find("input[name=expectIncome]").val(rowData.expect_income);
			$("#addInvestSubjectForm").find("input[name=annualizedInterestRate]").val(rowData.annualized_interest_rate);
			$("#addInvestSubjectForm").find("input[name=expectIncomeRate]").val(rowData.expect_income_rate);
			$("#addInvestSubjectForm").find("input[name=investStartDate]").val(formatDate(rowData.invest_start_date));
			
			$("#addInvestSubjectForm").find("input[name=investSubjectPid]").val(rowData.investSubjectPid);
			form.render('select');
			form.render();
			
			if(investPlanManagePid) {
				layer.open({
					type: 1,
					title: ['修改投资主体','font-size:18px;'],
					area: ['1040px', '620px'],
					btnAlign: 'c',
					content: $('#add_Investment'),
					btn: ['保存', '取消'],
					success: uploadInvestSubjectAccessory(),
					btn1: function(index, layero){
						$("#prjId").attr("disabled",false);
						$("#investBusinessPrjInfoPid").attr("disabled",false);
						$("#investSubjectName").attr("disabled",false);
						
						/*手动触发表单校验*/
						validator.triggerForm($("#addInvestSubjectForm"));
						/*校验通过提交表单*/
						if(validator.isValid($("#addInvestSubjectForm"))){
							var data = $("#addInvestSubjectForm").serialize();
							$.ajax({
								url: $("#addInvestSubjectForm").attr("action"),
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
//										validator.resetForm($("#addInvestSubjectForm"));
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
		} else if(rowsId && rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	// 查看付款信息(accessType=1代表从资管计划页面进入)
	$('#viewInvestSubjectPayment').on('click', function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		var url = basePath+"investPlanManage/toInvestSubjectPaymentInfo.action?investPlanManagePid="+rowData.investManagePlanPid+"&investSubjectPid="+rowData.investSubjectPid
				+"&accessType=1&investSubjectName="+rowData.name;
		if(rowsId && rowsId.length == 1) {
			if(investPlanManagePid) {
				window.location.href = encodeURI(encodeURI(url));
			} else {
				layer.msg("请先保存资管计划基础信息！", {time:1000,icon:7});
			}
		} else if(rowsId && rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	// 投资人信息管理(accessType=1代表从资管计划页面进入)
	$('#investorsInfoManage').on('click', function(){
		debugger;
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "investorBusinessManage/toInvestorsInfoManagePage.action?investPlanManagePid="+rowData.investManagePlanPid+"&investSubjectPid="+rowData.investSubjectPid
					+"&accessType=1";
		if(rowsId && rowsId.length == 1) {
			if(investPlanManagePid) {
				window.location.href = encodeURI(encodeURI(url));
			} else {
				layer.msg("请先保存资管计划基础信息！", {time:1000,icon:7});
			}
		} else if(rowsId && rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*删除投资主体*/
	$("#delInvestSubject").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId && rowsId.length >= 1) {
			var investSubjectPids = "";
			for (var i = 0; i < rowsId.length; i++) {
				var rowData = $("#data_list").jqGrid("getRowData", rowsId[i]);
				investSubjectPid = rowData.investSubjectPid;
				investSubjectPids += investSubjectPid + ",";
			}
			if(investSubjectPids.length > 0) {
				investSubjectPids = investSubjectPids.substr(0,investSubjectPids.length-1);
			}
			
			layer.confirm('确认删除？',function(){
				$.ajax({
	      		    url: basePath+"investPlanManage/deleteInvestSubjects",
	      		    cache: true,
	      		    type: "POST",
	      		    data: {
	      		    	investSubjectPids: investSubjectPids
	      		    },
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
	
	
	// 预计总回款    失去焦点事件
	$("#investExpectAllReceiverAccount").blur(function(){
		calculateExpectIncome();
	});
	// 预计出资额    失去焦点事件
	$("#expectContributiveAmount").blur(function(){
		calculateExpectIncome();
	});
	
	
	
	/*************************************新增投资主体js结束(layui外面一层)*******************************************/
	
	
});

/*初始化表单信息(清form表单)*/
function initForm(dom){
  dom.find(":input").not(":disabled").not(".radio").val("");
  layui.use('form',function(){
    var form = layui.form;
    form.render();
  });
}

// 计算预期收益（预计总回款-预计出资额）、预期收益率
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

// 加载投资主体列表
var loadFlg = false;
function queryInvestSubjectList(investPlanManagePid) {
	
	var data = {investPlanManagePid:investPlanManagePid};
	if(loadFlg){
		$("#data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	} else {
		var url = basePath+"investPlanManage/queryInvestSubjectList";
		var colNames = ["investSubjectPid","investManagePlanPid","stockholderPid","projectInfoPid","投资主体名称","businessPrjInfoPid",
		                "楼盘名称","出资类别code","出资类别","股东类别code","级别","预期收益","预期收益率","投资起始日",
		                
		                "股东类别","企业名称","是否为汇联上市公司","预计出资额(万元)","持股比例(%)","实际出资(万元)","出资期数(月)","分红方式code","分红方式","年化利率(%)","预计总回款(万元)","已收本金(万元)","已收回报(万元)","投资人"
		               ];
		
		var colModel = [
		                {name: "investSubjectPid",index: "investSubjectPid", sortable :false, hidden : true},
		                {name: "investManagePlanPid",index: "investManagePlanPid", sortable :false, hidden : true},
		                {name: "stockholderPid",index: "stockholderPid", sortable :false, hidden : true},
		                {name: "projectInfoPid",index: "projectInfoPid", sortable :false, hidden : true},
		                {name: "invest_subject_id",index: "invest_subject_id", sortable :false, hidden : true},
		                {name: "businessPrjInfoPid",index: "businessPrjInfoPid", sortable :false, hidden : true},
		                {name: "building_name",index: "building_name", sortable :false, hidden : true},
		                {name: "contributive_type",index: "contributive_type", sortable :false, hidden : true},
		                {name: "contributiveTypeName",index: "contributiveTypeName", sortable :false, hidden : true},
		                {name: "stockholder_type",index: "stockholder_type", sortable :false, hidden : true},
		                {name: "level",index: "level", sortable :false, hidden : true},
		                {name: "expect_income",index: "expect_income", sortable :false, hidden : true},
		                {name: "expect_income_rate",index: "expect_income_rate", sortable :false, hidden : true},
		                {name: "invest_start_date",index: "invest_start_date", sortable :false, hidden : true},
		                
		                
		                {name: "stockholderTypeName",index: "stockholderTypeName",sortable :false},
		                {name: "name",index: "name",sortable :false},
		                {name: "is_huilian_corp",index: "is_huilian_corp",sortable :false,
		                	formatter:function(cellvalue, options, rowObject){
		                		var isHuilianCorp = rowObject.is_huilian_corp;
		                		if(isHuilianCorp == '1') {
		                			return "是";
		                		} else if(isHuilianCorp == '0') {
		                			return "否";
		                		} else {
		                			return "";
		                		}
		                	}
		                },
		                {name: "expect_contributive_amount",index: "expect_contributive_amount",sortable :false},
		                {name: "hold_stock_rate",index: "hold_stock_rate", sortable :false},
		                {name: "realInvestAmount",index: "realInvestAmount", sortable :false},
		                {name: "term",index: "term", sortable :false},
		                
		                {name: "dividend_type",index: "dividend_type", sortable :false, hidden : true},
		                {name: "dividendTypeName",index: "dividendTypeName", sortable :false},
		                
		                {name: "annualized_interest_rate",index: "annualized_interest_rate", sortable :false},
		                {name: "expect_all_receiver_account",index: "expect_all_receiver_account", sortable :false, hidden : true},
		                {name: "receivedPrincipal",index: "receivedPrincipal", sortable :false},
		                {name: "receivedProfit",index: "receivedProfit", sortable :false},
		                {name: "investPersonCount",index: "investPersonCount", sortable :false,
		                	formatter:function(cellvalue, options, rowObject){
		                		var str = "<a href='javascript:void(0);' class='table_bnt show_accessory' "
		                				+" onclick= \"viewInvestorBusinessManage('"+rowObject.investManagePlanPid+"','"+rowObject.investSubjectPid+"')\" >"+rowObject.investPersonCount+"</a>";
		                		
		                		return str;
		                	}
		                }   
		                ];
		
		var complateFunc = function(){
			loadFlg = true;
		}
		loadData(url, colNames, colModel, complateFunc, data);
	}
}

//投资主体列表中  查看  超链接
function viewInvestorBusinessManage(investManagePlanPid,investSubjectPid) {
	var url = basePath + "investorBusinessManage/toInvestorsInfoManagePage.action?investPlanManagePid="+investManagePlanPid+"&investSubjectPid="+investSubjectPid+"&accessType=1";
	window.location.href = url;
}

/**根据项目名称异步加载项目公司**/
function loadBusinessPrjInfo(projectInfoPid,businessPrjInfoPid,id){
	$.ajax({
		url: basePath+"prjContactCorp/getBusinessPrjInfoPidByPrjId",
	    type: "POST",
	    data:{
	    	projectInfoPid: projectInfoPid
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(businessPrjInfoPid==n.businessPrjInfoPid){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#"+id).append('<option value="'+n.businessPrjInfoPid+'"'+selected+'>'+n.prjCorpName+'</option>');
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
/**根据项目名称、项目公司 异步加载股东信息**/
function loadStockByBusinessPrjPid(projectInfoPid,businessPrjInfoPid,stockholderInfoPid){
	
	$.ajax({
		url: basePath+"prjContactCorp/getStockholderPidByBusinessPrjPid",
		type: "POST",
		data:{
			businessPrjInfoPid:businessPrjInfoPid,
			projectInfoPid:projectInfoPid
		},
		async: false,
		success : function(msg) {
			if (msg && msg.successMsg) {
				var result = msg.result;
				var selected = '';
				$.each(result,function(i,n){
					if(stockholderInfoPid==n.stockholderInfoPid){
						selected = 'selected ="selected"'
					}else{
						selected= '';
					}
					$("#stockholderInfoPid").append('<option value="'+n.stockholderInfoPid+'"'+selected+'>'+n.stockholderInfoName+'</option>');
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

/**根据项目名称异步加载楼盘名称**/
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

//新增、修改  投资主体  弹窗上传附件
var investSubjectLoadFlag = false;
function uploadInvestSubjectAccessory() {
	
	var investSubjectPid = $("#addInvestSubjectForm").find("input[name=investSubjectPid]").val();
	
	$("#addInvestSubject_select_file").on("click",function(){
		investSubjectPid = $("#addInvestSubjectForm").find("input[name=investSubjectPid]").val();
		if(investSubjectPid!=""){
			$("#addInvestSubject_selectFileButton").trigger("click");
		}else{
			layer.msg("请先保存数据",{icon:7});
		}
	});
	
	var data = {pid:investSubjectPid,accessoryType:1};
	// 异步     第二次刷新附件列表时，不自动查询后台数据库的bug;加状态
	if(investSubjectLoadFlag){
		$("#investSubjectAccessory_data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		uploadFiles1($("#addInvestSubject_selectFileButton"), $("#addInvestSubject_fileList"), $("#addInvestSubject_uploadFileButton"),$("#investSubjectAccessory_data_list")
				, basePath+"investPlanManage/uploadInvestSubjectAccessory",data,"investSubjectPid");
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
			investSubjectLoadFlag = true;
		};
		loadData1("investSubjectAccessory_data_list","investSubjectAccessory_page_list",url,colNames,colModel,complateFunc,data);
		
	}
}

