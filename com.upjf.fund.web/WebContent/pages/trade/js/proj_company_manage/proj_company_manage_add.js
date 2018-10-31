var form='';
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
//	    var $ = jQuery = layui.$;
		
		/**********************************项目公司基本信息js开始***************************************/
		
		// 选择企业时，查找该企业信息并填充下面输入框
		$("select[name=corporationPid]").change(function(){
			var corporationPid = $("select[name=corporationPid]").val();
			if(corporationPid == '-1') {
				document.getElementById("addProjCompanyBaseInfoForm").reset();
				document.getElementById("name").readOnly=false;
				document.getElementById("simpleName").readOnly=false;
				document.getElementById("orgCodeCert").readOnly=false;
				document.getElementById("businessLicenceCode").readOnly=false;
				document.getElementById("corpQuality").readOnly=false;
				document.getElementById("legalRepresentative").readOnly=false;
				document.getElementById("registerCapital").readOnly=false;
				document.getElementById("customerManager").readOnly=false;
				document.getElementById("phone").readOnly=false;
				$("#corpQuality").attr("disabled",false);
			} else {
				$.ajax({
					url: basePath+"tradeManage/queryCorporationByPrimaryKey",
					cache: true,
					type: "POST",
					data: {corporationPid:corporationPid},
					async: false,
					success : function(data) { //表单提交后更新页面显示的数据
						var ret = eval("(" + data + ")");
						if(ret && ret.code == "0000"){
							var existCorporationInfo = ret.existCorporationInfo;
							$("#name").val(existCorporationInfo.name);
							$("#simpleName").val(existCorporationInfo.simpleName);
							$("#orgCodeCert").val(existCorporationInfo.orgCodeCert);
							$("#businessLicenceCode").val(existCorporationInfo.businessLicenceCode);
							$("#corpQuality").val(existCorporationInfo.corpQuality);
							$("#legalRepresentative").val(existCorporationInfo.legalRepresentative);
							$("#registerCapital").val(existCorporationInfo.registerCapital);
							$("#customerManager").val(existCorporationInfo.customerManager);
							$("#phone").val(existCorporationInfo.phone);
							$("#prjCorpName").val(existCorporationInfo.prjCorpName);
							$(".readonly").attr("readonly","readonly");
							$("#corpQuality").attr("disabled",true);
						}
					},
					error:function(){
					}
				});
			}
		});
		
		// 新增股东时【股东名称】不能为空触发校验
		form.on('select(stockholderName)',function(data){
			if(data.value){
				validator.resetFormField($("#addStockholderInfoForm"),"stockholderName");
			}
		});
		
		// 股权变更历史记录  页面   返回按钮    重刷当前tab
		var tabType = $("#tabType").val();
		var projCompanyOperation = $("#projCompanyOperation").val();
		// 1:项目公司基本信息;2:项目公司合同附件;3:项目公司股东信息
		if(tabType == '1') {
			//当为‘修改’页面时，将【选择企业】栏位置灰
			if('' != projCompanyOperation && projCompanyOperation != 'undefined' && projCompanyOperation == 'modify') {
				$("#corporationPidDiv").hide();
				$("#name").attr("disabled",true);
				$("#orgCodeCert").attr("disabled",true);
			}
			
		} else if(tabType == '3') {
			
			$("#projCompanyStockholderInfo").tab('show');
			$("#projCompanyStockholderInfo").trigger("click");
			$("#stock_data_list").trigger("reloadGrid");
		}
		
		// 查看项目公司
		if(projCompanyOperation == "view"){
			$("#addProjCompanyBaseInfoForm").find(":input").attr("disabled",true);
			form.render();
		}
		
		
		// 新增、修改    项目公司基本信息--保存
		validator.validate($("#addProjCompanyBaseInfoForm"), function(dom){
			var corporationName = $("#name").val();
			// 使用disabled后，后台会取不到值。必须在点击时取消置灰
			$("#name").attr("disabled",false);
			$("#orgCodeCert").attr("disabled",false);
			$("#corpQuality").attr("disabled",false);
			var corporationPid = $("#corporationPid").val();
			$("#corpPid").val(corporationPid);
			var data = $("#addProjCompanyBaseInfoForm").serialize();
			$.ajax({
				url: $("#addProjCompanyBaseInfoForm").attr("action"),
				cache: true,
				type: "POST",
				data: data,
				async: false,
				success : function(msg) { //表单提交后更新页面显示的数据
					if (msg && msg["successMsg"]) {
						$("input[name='businessPrjInfoId']").val(msg["businessPrjInfoId"]);
						layer.msg(msg["successMsg"],{time:1000,icon: 1});
						$("#data_list").trigger("reloadGrid");
//						validator.resetForm($("#addProjCompanyBaseInfoForm"));
						
						// 保存成功后，引导去“项目公司合同附件”Tab页签
						$("#projCompanyContract").trigger("click");
						
					} else {
						layer.msg(msg["errMsg"], {time:1000,icon:7});
					}
				},
				error:function(msg){
					alert(msg["errMsg"]);
				}
			});
		});
		
		/**********************************项目公司基本信息js结束***************************************/
		
		
		/**********************************项目公司合同附件js开始***************************************/	
		var businessPrjInfoId = $("#businessPrjInfoId").val();
		var contractName = '';
		var data = {businessPrjInfoId:businessPrjInfoId,contractName:contractName};
		$("#contractName").blur(function(){
			contractName = $("#contractName").val();
		});
		$("#select_file").on("click",function(){
			if(contractName != '' && contractName != 'undefined'){
				$("#selectFileButton").trigger("click");
			} else {
				layer.msg("请先输入合同名称!",{icon:7});
			}
		});
		uploadPrjCompanyContract($("#selectFileButton"), $("#fileList"), $("#uploadFileButton"),$("#file_data_list"), basePath+"tradeManage/uploadProjCompContractAccessory",data);
		
		// 点击项目公司合同附件Tab按钮时，才查询附件列表
		$("#projCompanyContract").on("click",function(){
			var businessPrjInfoId = $("#businessPrjInfoId").val();
//			if("${projCompanyOperation}"!="add" && businessPrjInfoId != "" && businessPrjInfoId != 'undefined' && businessPrjInfoId != null){
			if(businessPrjInfoId != "" && businessPrjInfoId != 'undefined' && businessPrjInfoId != null){
				var url = basePath+"tradeManage/queryProjCompContractAccessoryList";
				var colNames = ["合同名称","合同附件","上传时间","上传人","操作"]
				var colModel = [
				                {name:"contract_name", index:"contract_name",sortable:false},
				                {name:"file_all_name", index:"file_all_name",sortable:false},
				                {name:"create_date", index:"create_date",sortable:false},
				                {name:"user_name", index:"user_name",sortable:false},
				                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
				                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
				                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
				                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
				                }}
				                ]
				var complateFunc = function(){};
				loadData1("file_data_list","file_page_list",url, colNames, colModel, complateFunc,data);
				
			} else {
				$("#projCompanyBaseInfo").tab('show');
				$("#projCompanyBaseInfo").trigger("click");
				$("#projCompanyContract").removeClass("active");
				layer.msg("请先保存项目公司基本信息！", {time:1000,icon:7});
			}
			
		});
		
		/**********************************项目公司合同附件js结束***************************************/	
	});
	
	
	/**********************************项目公司股东信息列表开始***************************************/	
	validator.validate($("#addStockholderInfoForm"));
	
	$("#projCompanyStockholderInfo").on("click",function(){
		var businessPrjInfoId = $("#businessPrjInfoId").val();
		var projectInfoPid = $("#projectInfoPid").val();
		var projCompanyOperation = $("#projCompanyOperation").val();
		debugger;
		if(projCompanyOperation!="view" && (projectInfoPid == "" || projectInfoPid == 'undefined' || projectInfoPid == null)) {
			layer.open({
				type: 1,
				title: ['操作提示', 'text-align:center;'],
				area: ['300px', '200px'],
				btnAlign: 'c',
				content: '<div class="content_text">请先新增项目！</div>',
				btn: ['确定', '取消'],
				btn1: function(index, layero){
					window.location.href=basePath+"/projectManageController/toProject?type=1&accessType=1&businessPrjInfoId="+businessPrjInfoId;
					layer.close(index);
				},
				btn2: function(index, layero){
					layer.close(index);
				}
			});
		}
		
		if(projectInfoPid != "" && projectInfoPid != 'undefined' && projectInfoPid != null) {
			if(businessPrjInfoId != "" && businessPrjInfoId != 'undefined' && businessPrjInfoId != null){
				var data = {businessPrjInfoId:businessPrjInfoId,projectInfoPid:projectInfoPid};
				var url = basePath+"tradeManage/queryProjCompanyStockholderList";
				var colNames = ["stockholderPid","corp_id","股东往来","备注","股东类别id","stockRightsChangeId","prj_id","businessPrjInfoPid","projectInfoPid","businessPrjInfoCorpPid",
				                "股东类别","股东","出资额(万元)","持股比例","实际出资(万元)","出资期数(月)","股权确认状态code","股权确认状态","附件资料"];
				var colModel = [
				                {name: "stockholderPid",index: "stockholderPid", sortable :false, hidden : true},
				                {name: "corp_id",index: "corp_id", sortable :false, hidden : true},
				                {name: "stockholder_contacts",index: "stockholder_contacts", sortable :false, hidden : true},
				                {name: "remark",index: "remark", sortable :false, hidden : true},
				                {name: "stockholder_type",index: "stockholder_type", sortable :false, hidden : true},
				                {name: "stock_rights_change_id",index: "stock_rights_change_id", sortable :false, hidden : true},
				                {name: "prj_id",index: "prj_id", sortable :false, hidden : true},
				                {name: "businessPrjInfoPid",index: "businessPrjInfoPid", sortable :false, hidden : true},
				                {name: "projectInfoPid",index: "projectInfoPid", sortable :false, hidden : true},
				                {name: "businessPrjInfoCorpPid",index: "businessPrjInfoCorpPid", sortable :false, hidden : true},
				                
				                
				                {name: "stockholder_type_name",index: "stockholder_type_name",sortable :false},
				                {name: "name",index: "name",sortable :false},
				                {name: "register_capital",index: "register_capital",sortable :false},
				                {name: "hold_stock_ratio",index: "hold_stock_ratio",sortable :false},
				                {name: "reality_invest_total",index: "reality_invest_total", sortable :false},
				                {name: "sum_periods",index: "sum_periods", sortable :false},
				                
				                {name: "stock_rights_status",index: "stock_rights_status", sortable :false, hidden : true},
				                
				                {name: "stock_rights_status_name",index: "stock_rights_status_name", sortable :false},
				                {name: "fujian",index: "fujian", sortable :false,
				                	formatter:function(cellvalue, options, rowObject){
				                		var obj = JSON.stringify(rowObject);
				                		if(null != obj && obj != "") {
				                			obj = obj.replace(" ", "");
				                		}
				                		var url = "'"+basePath+"tradeManage/queryStockholderAccessoryList"+"'";
				                		var str1 = '<a href="javascritp:;" class="table_bnt show_accessory" str='+obj+' onclick="viewStock('+url+',this)"  >查看</a>';
				                		return str1;
				                	}
				                }   
				                ];
				var complateFunc = function(){}
				loadData1("stock_data_list","stock_page_list",url, colNames, colModel, complateFunc, data);
				
			} else {
				$("#projCompanyBaseInfo").tab('show');
				$("#projCompanyBaseInfo").trigger("click");
				$("#projCompanyStockholderInfo").removeClass("active");
				layer.msg("请先保存项目公司基本信息！", {time:1000,icon:7});
			}
		}
	});
	
	
	/**********************************项目公司股东信息列表结束***************************************/	
	
	
	
	
	/**********************************项目公司股东信息js开始***************************************/	
	/*--------------新增股东--------------*/
	$('#add_partner_firm1').on('click', function(){
		var businessPrjInfoId = $("#businessPrjInfoId").val();
		document.getElementById("addStockholderInfoForm").reset();
		$("#addStockholderInfoForm").find("input[name='stockholderPid']").val('');
		$("#addStockholderInfoForm").find("select[name=stockholderName]").val('');
		$("#addStockholderInfoForm").find("select[name=stockholderType]").val('');
		form.render("select");
		$("#addStockholderInfoForm").find("input[name=businessPrjInfoId]").val(businessPrjInfoId);
		
		form.render();
		layer.open({
			type: 1,
			title: ['新增股东公司','font-size:18px;'],
			area: ['1040px', '620px'],
			btnAlign: 'c',
			content: $('#add_partner_firm'),
			btn: ['保存', '取消'],
			success: uploadStockholderAccessory(),
			btn1: function(index, layero){
				
				/*手动触发表单校验*/
				validator.triggerForm($("#addStockholderInfoForm"));
				/*校验通过提交表单*/
				if(validator.isValid($("#addStockholderInfoForm"))){
					var data = $("#addStockholderInfoForm").serialize();
					$.ajax({
						url: $("#addStockholderInfoForm").attr("action"),
						cache: true,
						type: "POST",
						data: data,
						async: false,
						success : function(msg) { //表单提交后更新页面显示的数据
							if (msg && msg["successMsg"]) {
								$("input[name='stockholderPid']").val(msg["stockholderPid"]);
								layer.msg(msg["successMsg"],{icon: 1});
								$("#stock_data_list").trigger("reloadGrid");
								validator.resetForm($("#addStockholderInfoForm"));
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
	});
	
	
	/*---------修改股东--------*/
	$("#modify_partner_firm").on("click",function(){
		
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			//修改时  股东名称置灰
			$("#corporationInfoPid").attr("disabled","disabled");
			
			var stockRightsStatus = rowData.stock_rights_status;
			// 已确认的股权不能再修改
			if(stockRightsStatus == '0') {
				// 回显
				$("#addStockholderInfoForm").find("input[name=businessPrjInfoId]").val(rowData.businessPrjInfoPid);
				$("#addStockholderInfoForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
				$("#addStockholderInfoForm").find("input[name=corPid]").val(rowData.corp_id);
				$("#addStockholderInfoForm").find("select[name=stockholderName]").val(rowData.corp_id);
				$("#addStockholderInfoForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
				form.render('select');
				$("#addStockholderInfoForm").find("input[name=registerCapital]").val(rowData.register_capital);
				$("#addStockholderInfoForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
				$("#addStockholderInfoForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
				$("#addStockholderInfoForm").find("textarea[name=remark]").val(rowData.remark);

				layer.open({
					type: 1,
					title: ['修改股东公司','font-size:18px;'],
					area: ['1040px', '620px'],
					btnAlign: 'c',
					content: $('#add_partner_firm'),
					btn: ['保存', '取消'],
					success: uploadStockholderAccessory(),
					btn1: function(index, layero){
						
						$("#corporationInfoPid").attr("disabled",false);
						var data = $("#addStockholderInfoForm").serialize();
						$.ajax({
							url: $("#addStockholderInfoForm").attr("action"),
							cache: true,
							type: "POST",
							data: data,
							async: false,
							success : function(msg) { //表单提交后更新页面显示的数据
								
								if (msg && msg["successMsg"]) {
									layer.close(index);
									layer.msg(msg["successMsg"],{icon: 1});
									$("#stock_data_list").trigger("reloadGrid");
									form.render();
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
			} else if (stockRightsStatus == '1'){
				layer.msg("该股权已经确认，不可修改股东信息和股权变更", {time:1000,icon:5});
			}
			
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------股权确认--------*/
	$("#sure_stock1").on("click",function(){
		// 股权确认  buttType=1
		$("#equityConfirmForm").find("input[name=buttType]").val(1);
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			// 股东名称等置灰
			$("#stockholderNameConfirm").attr("disabled","disabled");
			$("#stockholderTypeConfirm").attr("disabled","disabled");
			$("#registerCapitalConfirm").attr("disabled","disabled");
			$("#stockholderContactsConfirm").attr("disabled","disabled");
			$("#holdStockRatioConfirm").attr("disabled","disabled");
			$("#remarkConfirm").attr("disabled","disabled");
			$("input[name='stockRightsStatus']").attr("disabled",false);
			
			
			$("#equityConfirmForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
			$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
			// 回显下拉选择框
			$("#equityConfirmForm").find("select[name=stockholderName]").val(rowData.corp_id);
			$("#equityConfirmForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
			form.render('select');
			$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
			$("#equityConfirmForm").find("input[name=registerCapital]").val(rowData.register_capital);
			$("#equityConfirmForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
			$("#equityConfirmForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
			$("input:radio[name='stockRightsStatus'][value='"+rowData.stock_rights_status+"']").attr("checked",'checked');
			$("#equityConfirmForm").find("textarea[name=remark]").val(rowData.remark);
			form.render();
			
			layer.open({
				type: 1,
				title: ['股权确认','font-size:18px;'],
				area: ['1040px', '680px'],
				btnAlign: 'c',
				content: $('#sure_stock'),
				success: viewStockholderAccessory(),
				btn: ['保存', '取消'],
				btn1: function(index, layero){
					// 使用disabled后，后台获取不到form表单数据，需要在保存之前将disabled设置为false
					$("#stockholderNameConfirm").attr("disabled",false);
					$("#stockholderTypeConfirm").attr("disabled",false);
					$("#registerCapitalConfirm").attr("disabled",false);
					$("#stockholderContactsConfirm").attr("disabled",false);
					$("#holdStockRatioConfirm").attr("disabled",false);
					$("#remarkConfirm").attr("disabled",false);
					
					var data = $("#equityConfirmForm").serialize();
					$.ajax({
						url: basePath+"tradeManage/updateStockholderInfo",
						cache: true,
						type: "POST",
						data: data,
						async: false,
						success : function(msg) { //表单提交后更新页面显示的数据
							clearShade(layero);
							if (msg && msg["successMsg"]) {
								layer.msg(msg["successMsg"],{icon: 1});
								$("#stock_data_list").trigger("reloadGrid");
								//validator.resetForm($("#equityConfirmForm"));
								layer.close(index);
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
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------查看股权--------*/
	$("#looking_stock").on("click",function(){
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			// 股东名称等置灰
			$("#stockholderNameConfirm").attr("disabled","disabled");
			$("#stockholderTypeConfirm").attr("disabled","disabled");
			$("#registerCapitalConfirm").attr("disabled","disabled");
			$("#stockholderContactsConfirm").attr("disabled","disabled");
			$("#holdStockRatioConfirm").attr("disabled","disabled");
			$("input[name='stockRightsStatus']").attr("disabled","disabled");
			$("#remarkConfirm").attr("disabled","disabled");
			$("#stockRightsRemark").attr("disabled","disabled");
			
			
			$("#equityConfirmForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
			$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
			// 回显下拉选择框
			$("#equityConfirmForm").find("select[name=stockholderName]").val(rowData.corp_id);
			$("#equityConfirmForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
			form.render('select');
			$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
			$("#equityConfirmForm").find("input[name=registerCapital]").val(rowData.register_capital);
			$("#equityConfirmForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
			$("#equityConfirmForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
			$("input:radio[name='stockRightsStatus'][value='"+rowData.stock_rights_status+"']").attr("checked",'checked');
			$("#equityConfirmForm").find("textarea[name=remark]").val(rowData.remark);
			form.render();
			
			layer.open({
				type: 1,
				title: ['查看股权','font-size:18px;'],
				area: ['1040px', '680px'],
				btnAlign: 'c',
				content: $('#sure_stock'),
				success: viewStockholderAccessory(),
				btn: ['关闭'],
				btn1: function(index, layero){
					layer.close(index);
				}
			});
		} else if(rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------股权变更--------*/
	$("#change_stock1").on("click",function(){
		// 股权变更  buttType=2
		$("#stockholderChangeForm").find("input[name=buttType]").val(2);
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			//修改时  股东名称置灰
			$("#stockholderNameChange").attr("disabled","disabled");
			$("#stockholderTypeChange").attr("disabled","disabled");
			$("#registerCapitalChange").attr("disabled","disabled");
			$("#stockholderContactsChange").attr("disabled","disabled");
			
			var stockRightsStatus = rowData.stock_rights_status;
			// 已确认的股权不能再变更
			if(stockRightsStatus == '0') {
				// 回显下拉选择框
				$("#stockholderChangeForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
				$("#stockholderChangeForm").find("input[name=corPid]").val(rowData.corp_id);
				$("#stockholderChangeForm").find("select[name=stockholderName]").val(rowData.corp_id);
				$("#stockholderChangeForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
				form.render('select');
				$("#stockholderChangeForm").find("input[name=registerCapital]").val(rowData.register_capital);
				$("#stockholderChangeForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
				$("#stockholderChangeForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
				$("#stockholderChangeForm").find("textarea[name=remark]").val(rowData.remark);
				
				layer.open({
					type: 1,
					title: ['股权变更','font-size:18px;'],
					area: ['1040px', '620px'],
					btnAlign: 'c',
					content: $('#change_stock'),
					success: stockRightsChangeAccessory(),
					btn: ['保存', '取消'],
					btn1: function(index, layero){
						$("#stockholderNameChange").attr("disabled",false);
						$("#stockholderTypeChange").attr("disabled",false);
						$("#registerCapitalChange").attr("disabled",false);
						$("#stockholderContactsChange").attr("disabled",false);
						
						var data = $("#stockholderChangeForm").serialize();
						$.ajax({
							url: basePath+"tradeManage/updateStockholderInfo",
							cache: true,
							type: "POST",
							data: data,
							async: false,
							success : function(msg) { //表单提交后更新页面显示的数据
								if (msg && msg["successMsg"]) {
									$("#stockholderChangeForm").find("input[name=stockRightsChangePid]").val(msg["stockRightsChangePid"]);
									layer.msg(msg["successMsg"],{icon: 1});
									$("#stock_data_list").trigger("reloadGrid");
									validator.resetForm($("#addStockholderInfoForm"));
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
			} else if (stockRightsStatus == '1'){
				layer.msg("该股权已经确认，不可再次变更", {time:1000,icon:5});
			}
			
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------股权变更历史--------*/
	$("#change_stock_his").on("click",function(){
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "tradeManage/toStockChangeHisPage.action?stockholderPid="+rowData.stockholderPid+"&businessPrjPid="+rowData.businessPrjInfoPid+"&operation=modify&stockholderName="+rowData.name;
		if(rowsId.length == 1) {
			window.location.href = encodeURI(encodeURI(url));
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------新增项目公司----项目公司股东信息----点击“删除”----------*/
	$("#delete_stock").on("click",function(){
		var rowsId = $("#stock_data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#stock_data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length >= 1) {
			var stockholderPids = "";
			for (var i = 0; i < rowsId.length; i++) {
				var rowData = $("#stock_data_list").jqGrid("getRowData", rowsId[i]);
				stockholderPid = rowData.stockholderPid;
				stockholderPids += stockholderPid + ",";
			}
			if(stockholderPids.length > 0) {
				stockholderPids = stockholderPids.substr(0,stockholderPids.length-1);
			}
			
			$.ajax({
				url: basePath+"tradeManage/deleteStockholders",
				cache: true,
				type: "POST",
				data: {stockholderPids:stockholderPids},
				async: false,
				success : function(msg) { //表单提交后更新页面显示的数据
					if (msg && msg["successMsg"]) {
						layer.msg(msg["successMsg"],{icon: 1});
						$("#stock_data_list").trigger("reloadGrid");
					} else {
						layer.msg(msg["errMsg"], {time:1000,icon:7});
					}
				},
				error:function(msg){
					alert(msg["errMsg"]);
				}
			});
			
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	

	/**********************************项目公司股东信息js结束***************************************/	
	
	
})


// 关闭新增项目公司页面
function closeAddPage() {
	window.location.href = basePath +"tradeManage/projectEnterpriseManage";
}


//去除遮罩层的bug(无效果)
function clearShade(layero) {
	var mask = $(".layui-layer-shade");
	mask.appendTo(layero.parent());
}



//新增、修改股东弹窗上传附件
var loadFlg = false;
function uploadStockholderAccessory() {
	
	var stockholderPid = $("#addStockholderInfoForm").find("input[name=stockholderPid]").val();
	
	$("#addStock_select_file").on("click",function(){
		stockholderPid = $("#addStockholderInfoForm").find("input[name=stockholderPid]").val();
		if(stockholderPid!=""){
			$("#addStock_selectFileButton").trigger("click");
		}else{
			layer.msg("请先保存数据",{icon:7});
		}
	});
	var data = {pid:stockholderPid};
	// 异步     第二次刷新附件列表时，不自动查询后台数据库的bug;加状态
	if(loadFlg){
		$("#addStock_data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		uploadFiles1($("#addStock_selectFileButton"), $("#addStock_fileList"), $("#addStock_uploadFileButton"),$("#addStock_data_list"),
				basePath+"tradeManage/uploadStockholderAccessory",data,"stockholderPid");
		var url = basePath+"tradeManage/queryStockholderAccessoryList";
		var colNames = ["文件名","上传时间","上传人","操作"]
		var colModel = [
		                {name:"file_all_name", index:"file_all_name",sortable:false},
		                {name:"create_date", index:"create_date",sortable:false},
		                {name:"user_name", index:"user_name",sortable:false},
		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
		                }}
		                ]
		var complateFunc = function(){
			loadFlg = true;
		};
		loadData1("addStock_data_list","addStock_page_list",url,colNames,colModel,complateFunc,data);
		
	}
}

//股权确认、查看股权 弹窗附件查看
var viewloadFlg = false;
function viewStockholderAccessory() {
	var stockholderPid = $("#equityConfirmForm").find("input[name=stockholderPid]").val();
	var data = {stockholderPid:stockholderPid};
	// 异步     第二次刷新附件列表时，不自动查询后台数据库的bug;加状态
	if(viewloadFlg){
		$("#equityConfirm_data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		uploadFiles($("#addStock_selectFileButton"), $("#addStock_fileList"), $("#addStock_uploadFileButton"),$("#addStock_data_list"), basePath+"tradeManage/uploadStockholderAccessory",data);
		var url = basePath+"tradeManage/queryStockholderAccessoryList";
		var colNames = ["文件名","上传时间","上传人","操作"]
		var colModel = [
		                {name:"file_all_name", index:"file_all_name",sortable:false},
		                {name:"create_date", index:"create_date",sortable:false},
		                {name:"user_name", index:"user_name",sortable:false},
		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
		                }}
		                ]
		var complateFunc = function(){
			viewloadFlg = true;
		};
		loadData1("equityConfirm_data_list","equityConfirm_page_list",url,colNames,colModel,complateFunc,data);
		
	}
}

//股权变更弹窗上传附件
var stockChangeloadFlg = false;
function stockRightsChangeAccessory() {
	var stockRightsChangePid = $("#stockholderChangeForm").find("input[name=stockRightsChangePid]").val();
	
	$("#stockRightsChange_select_file").on("click",function(){
		stockRightsChangePid = $("#stockholderChangeForm").find("input[name=stockRightsChangePid]").val();
		if(stockRightsChangePid!=""){
			$("#stockRightsChange_selectFileButton").trigger("click");
		}else{
			layer.msg("请先保存数据",{icon:7});
		}
	});
	
	var data = {pid:stockRightsChangePid};
	// 异步     第二次刷新附件列表时，不自动查询后台数据库的bug;加状态
	if(stockChangeloadFlg){
		$("#stockRightsChange_data_list").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		uploadFiles1($("#stockRightsChange_selectFileButton"), $("#stockRightsChange_fileList"), $("#stockRightsChange_uploadFileButton"),$("#stockRightsChange_data_list"), 
				basePath+"tradeManage/uploadStockRightsChangeAccessory",data,"stockRightsChangePid");
		var url = basePath+"tradeManage/queryStockRightsChangeAccessoryList";
		var colNames = ["文件名","上传时间","上传人","操作"]
		var colModel = [
		                {name:"file_all_name", index:"file_all_name",sortable:false},
		                {name:"create_date", index:"create_date",sortable:false},
		                {name:"user_name", index:"user_name",sortable:false},
		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
		                }}
		                ]
		var complateFunc = function(){
			stockChangeloadFlg = true;
		};
		loadData1("stockRightsChange_data_list","stockRightsChange_page_list",url,colNames,colModel,complateFunc,data);
	}
	
}


//股东列表中  查看  超链接
function viewStock(url,obj) {
	var retObj = $(obj).attr("str");
	var rowData = eval("("+retObj+")");
	var data = {stockholderPid:rowData.stockholderPid};
	layer.open({
		type: 1,
		title: ['查看股东','font-size:18px;'],
		area: ['1040px', '680px'],
		btnAlign: 'c',
		content: $('#add_partner_firm'),
		success: function(layero, index){
			// 股东名称等置灰
			$("#corporationInfoPid").attr("disabled","disabled");
			$("#stockholderType").attr("disabled","disabled");
			$("#stockRegisterCapital").attr("disabled","disabled");
			$("#stockholderContacts").attr("disabled","disabled");
			$("#holdStockRatio").attr("disabled","disabled");
			$("#remark").attr("disabled","disabled");
			$("#uploadDiv").hide();
			
			$("#addStockholderInfoForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
			$("#addStockholderInfoForm").find("input[name=corPid]").val(rowData.corp_id);
			$("#addStockholderInfoForm").find("select[name=stockholderName]").val(rowData.corp_id);
			$("#addStockholderInfoForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
			form.render('select');
			$("#addStockholderInfoForm").find("input[name=registerCapital]").val(rowData.register_capital);
			$("#addStockholderInfoForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
			$("#addStockholderInfoForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
			$("#addStockholderInfoForm").find("textarea[name=remark]").val(rowData.remark);
			form.render();
			
			/************************附件列表开始*********************/
			var dataListId = "addStock_data_list";
			var pageList = "addStock_page_list";
			showAccessory1(url,data,dataListId,pageList);
			
			/************************附件列表结束*********************/
        },
		btn: ['关闭'],
		btn1: function(index, layero){
			$("#corporationInfoPid").attr("disabled",false);
			$("#stockholderType").attr("disabled",false);
			$("#stockRegisterCapital").attr("disabled",false);
			$("#stockholderContacts").attr("disabled",false);
			$("#holdStockRatio").attr("disabled",false);
			$("#remark").attr("disabled",false);
			$("#uploadDiv").show();
			layer.close(index);
		}
	});
}

/**
 * 加载附件列表
 * @param viewloadFlg
 * @param data
 * @param dataListId
 * @param pageList
 */
function showAccessory1(url,data,dataListId,pageList) {
	if(viewloadFlg){
		$("#"+dataListId).jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		var colNames = ["文件名","上传时间","上传人","操作"]
		var colModel = [
		                {name:"file_all_name", index:"file_all_name",sortable:false},
		                {name:"create_date", index:"create_date",sortable:false},
		                {name:"user_name", index:"user_name",sortable:false},
		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
		                }}
		                ]
		$("#"+dataListId).jqGrid({
			url:url,
			mtype : "POST",  
			contentType : "application/json",  
			datatype : "json",
			postData: data,
			autowidth: true,   
			shrinkToFit: true, 
			rownumbers: true,  
			viewrecords: true, 
			scrollOffset: 0,
			height : 'auto',
			rowNum: 5,                           
			rowList: [5, 10, 15],             
			colNames: colNames,
			cellEdit : false, 
			colModel: colModel,
			pager: "#"+pageList, 
			gridComplete:function(){
				$("#"+dataListId).jqGrid('setLabel','rn', '序号',{'font-size':'8px'});
				viewloadFlg = true;
			}
		});
		
	}
}

/**
 * 多文件上传
 * @param seletButton 选择文件按钮
 * @param listView 文件列表
 * @param uploadButton 上传文件按钮
 * @param gridDom 上传成功之后文件展示列表
 * @param url 上传文件路径
 */
function uploadPrjCompanyContract(seletButton,listView,uploadButton,gridDom,url,data){
	layui.use('upload',function(){
		var upload = layui.upload;
		 /*多文件上传*/
		var uploadListIns = upload.render({
		    elem: seletButton,
		    url: url,
		    accept: 'file',
		    multiple: true,
		    auto: false,
		    data:data,
		    bindAction: uploadButton,
		    choose: function(obj){
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
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
		                    '</tr>'
		                   ].join(''));
		        
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
		        listView.append(tr);
		      });
		    },
		    before: function(obj){
		    	this.data.contractName = $("#contractName").val();
		    	this.data.businessPrjInfoId = $("#businessPrjInfoId").val();
		    },
		    done: function(res, index, upload){
		      if(res.successMsg){
		          var tr = listView.find('tr#upload-'+ index),
		        	  tds = tr.children();
		          tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		          tds.eq(3).html(''); //清空操作
		          return delete this.files[index]; //删除文件队列已经上传成功的文件
		      }
		      this.error(index, upload);
		    }, 
		    allDone: function(obj){
		    	/*上传文件全部成功之后，刷新文件列表*/
		    		if(gridDom){
		    			gridDom.jqGrid('setGridParam',{  
		    		           datatype:'json',  
		    		           postData:data, //发送数据  
		    		           page:1  
		    		      }).trigger("reloadGrid"); 
		    		}
		    },
		    error: function(index, upload){
		        var tr = listView.find('tr#upload-'+ index),
		      	    tds = tr.children();
		        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
		        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
		  });
	});
}




