var form='';
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
		
	});
	
	
	var url = basePath+"tradeManage/queryProjCompanyList";
	var colNames = ["businessPrjPid","projectInfoPid",
	                "项目公司","项目公司简称","项目名称","组织机构代码","营业执照号码","注册资本（万元）","法人代表","联系电话","股东公司"];
	var colModel = [
					   {name: "businessPrjPid",index: "businessPrjPid",sortable :false, hidden : true},
					   {name: "projectInfoPid",index: "projectInfoPid",sortable :false, hidden : true},
					   
					   {name: "prj_corp_name",index: "prj_corp_name",sortable :false},
					   {name: "simple_name",index: "simple_name",sortable :false},
					   {name: "project_name",index: "project_name",sortable :false},
					   {name: "org_code_cert",index: "org_code_cert",sortable :false},
					   {name: "business_licence_code",index: "business_licence_code", sortable :false},
					   {name: "register_capital",index: "register_capital", sortable :false},
					   {name: "legal_representative",index: "legal_representative", sortable :false},
					   {name: "phone",index: "phone", sortable :false},	   
					   {name: "stockholderCount",index: "stockholderCount", sortable :false,
						   formatter:function(cellvalue, options, rowObject){
						       var str = "<a href='javascript:void(0)' class='table_bnt show_accessory'" +
				       				" onclick= \"viewBusinessPrjInfo('"+rowObject.businessPrjPid+"','"+rowObject.projectInfoPid+"')\" >"+rowObject.stockholderCount+"</a>";
						       return str;
				   	   	   }
					   }   
				   ];
	
	var complateFunc = function(){
	}
	loadData(url, colNames, colModel, complateFunc);
	
	
	/*---------修改项目公司--------*/
	$("#modifyProjCompany").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "tradeManage/toProjCompanyAddPage.action?operation=modify&tabType=1&businessPrjPid="+rowData.businessPrjPid+"&projectInfoPid="+rowData.projectInfoPid;
		if(rowsId.length == 1) {
			window.location.href = url;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------查看项目公司--------*/
	$("#viewProjCompany").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "tradeManage/toProjCompanyAddPage.action?operation=view&tabType=1&businessPrjPid="+rowData.businessPrjPid+"&projectInfoPid="+rowData.projectInfoPid;
		if(rowsId.length == 1) {
			window.location.href = url;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能查看一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------删除项目公司---------*/
	$("#delProjCompany").on("click",function(){
		
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length >= 1) {
			var businessPrjPids = "";
			for (var i = 0; i < rowsId.length; i++) {
				var rowData = $("#data_list").jqGrid("getRowData", rowsId[i]);
				businessPrjPid = rowData.businessPrjPid;
				businessPrjPids += businessPrjPid + ",";
			}
			if(businessPrjPids.length > 0) {
				businessPrjPids = businessPrjPids.substr(0,businessPrjPids.length-1);
			}
			
			$.ajax({
				url: basePath+"tradeManage/deleteBusinessPrjInfos",
				cache: true,
				type: "POST",
				data: {businessPrjPids:businessPrjPids},
				async: false,
				success : function(msg) { //表单提交后更新页面显示的数据
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
			
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	/*---------关联项目--------*/
	$("#linkToProject").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "projectManageController/proList.action?entranceType=linkProj&businessPrjPid="+rowData.businessPrjPid+"&linkedPrjId="+rowData.projectInfoPid;
		if(rowsId.length == 1) {
			window.location.href = url;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能查看一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	//重置按钮
	$('#projCompanyQueryReset').on('click',function(){
		$("#businessPrjInfoPid").val("");
		$('#businessLicenceCode').val('');
		$('#stockholderName').val('');
		$('#projectInfoPid').val('');
		$('#legalRepresentative').val('');
		$("#data_list").trigger("reloadGrid");
	});
	
});


// 新增项目公司
function addProjCompany() {
	window.location.href = basePath + "tradeManage/toProjCompanyAddPage.action?operation=add";
}


//项目公司列表中  查看  超链接
function viewBusinessPrjInfo(businessPrjPid,projectInfoPid) {
	window.location.href = basePath + "tradeManage/toProjCompanyAddPage.action?operation=view&tabType=1&businessPrjPid="+businessPrjPid+"&projectInfoPid="+projectInfoPid;;
}





