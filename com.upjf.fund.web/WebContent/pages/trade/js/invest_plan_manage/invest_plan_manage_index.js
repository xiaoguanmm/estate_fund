var form='';
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
		
	});
	
	
	var url = basePath+"investPlanManage/queryInvestPlanManageList";
	var colNames = ["investManagePlanPid","projectPid","businessPrjPid","corporationPid","stockholderPid",
	                "项目名称","项目公司","股东","投资起始日","期限(月)","投资方式","预计出资规模（万元）","实际出资规模（万元）","已收本金","已收回款","预计总回款"];
	var colModel = [
	                   {name: "investManagePlanPid",index: "investManagePlanPid",sortable :false, hidden : true},
					   {name: "projectPid",index: "projectPid",sortable :false, hidden : true},
					   {name: "businessPrjPid",index: "businessPrjPid",sortable :false, hidden : true},
					   {name: "corporationPid",index: "corporationPid",sortable :false, hidden : true},
					   {name: "stockholderPid",index: "stockholderPid",sortable :false, hidden : true},
					   
					   
					   {name: "project_name",index: "project_name",sortable :false,
						   formatter:function(cellvalue, options, rowObject){
						       var str = "<a href='javascript:void(0)' class='table_bnt show_accessory' " +
						       		"onclick= \"viewInvestPlanManage('"+rowObject.projectPid+"')\" >"+rowObject.project_name+"</a>";
						       return str;
				   	   	   }
					   },
					   {name: "prj_corp_name",index: "prj_corp_name",sortable :false},
					   {name: "stockholderName",index: "stockholderName",sortable :false},
					   {name: "invest_start_date",index: "invest_start_date",sortable :false},
					   {name: "term",index: "term", sortable :false},
					   {name: "invest_type",index: "invest_type", sortable :false,
						   formatter:function(cellvalue, options, rowObject){
						       if(rowObject.invest_type == '1') {
						    	   return "股权投资";
						       } else if(rowObject.invest_type == '2') {
						    	   return "债权投资";
						       } else if(rowObject.invest_type == '3') {
						    	   return "股权+债券投资";
						       } else if(rowObject.invest_type == '4') {
						    	   return "其它";
						       } else {
						    	   return "";
						       }
				   	   	   }
					   },
					   {name: "expect_invest_amount",index: "expect_invest_amount", sortable :false},
					   {name: "reality_invest_amount",index: "reality_invest_amount", sortable :false},	   
					   {name: "receivered_principal",index: "receivered_principal", sortable :false},	   
					   {name: "receivered_payback",index: "receivered_payback", sortable :false},	   
					   {name: "expect_all_receiver_account",index: "expect_all_receiver_account", sortable :false}
				   ];
	
	var complateFunc = function(){
	}
	loadData(url, colNames, colModel, complateFunc);
	
	
	// 新增资管计划
	$("#addInvestPlanManage").on("click",function(){
		window.location.href = basePath + "investPlanManage/toInvestPlanManageAddPage.action?operation=add";
	});
	
	/*---------修改资管计划--------*/
	$("#modifyInvestPlanManage").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		var url = basePath + "investPlanManage/toInvestPlanManageAddPage.action?operation=modify&investManagePlanPid="+rowData.investManagePlanPid;
		if(rowsId.length == 1) {
			window.location.href = url;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能修改一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
	
	
	
	
	/*---------删除资管计划---------*/
	$("#delInvestPlan").on("click",function(){
		
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
	
	
});



//资管计划管理列表中  查看  超链接(跳转至项目管理模块)
function viewInvestPlanManage(projectPid) {
	window.location.href = basePath + "/projectManageController/toProject?type=3&projectPid="+projectPid;
}





