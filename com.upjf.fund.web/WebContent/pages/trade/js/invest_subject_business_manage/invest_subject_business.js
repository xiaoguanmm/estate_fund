var form='';
var accessType = $("#accessType").val();
var investPlanManagePid = $("#investPlanManagePid").val();
var projectInfoPid = $("#prjPid").val();
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
		
	});
	
	// 加载投资主体业务管理列表
	var url = basePath+"investSubjectBusinessManage/queryInvestSubjectBusinessManageList";
	var colNames = ["investSubjectPid","contributive_type","investSubjectCorpId","projectInfoPid",
	                "项目名称","楼盘名称","出资类别","投资主体名称","预计出资额(万元)","持股比例(%)","实际出资(万元)","出资期数(月)","分红方式code","分红方式","年化利率(%)","预计总回款(万元)","已收本金(万元)","已收回报(万元)"
	               ];
	
	var colModel = [
	                {name: "investSubjectPid",index: "investSubjectPid", sortable :false, hidden : true},
	                {name: "contributive_type",index: "contributive_type", sortable :false, hidden : true},
	                {name: "investSubjectCorpId",index: "investSubjectCorpId", sortable :false, hidden : true},
	                {name: "projectInfoPid",index: "projectInfoPid", sortable :false, hidden : true},
	                
	                {name: "project_name",index: "project_name",sortable :false,
	                	formatter:function(cellvalue, options, rowObject){
					        var str = "<a href='javascript:void(0)' class='table_bnt show_accessory' " +
					       		"onclick= \"viewInvestPlanManage('"+rowObject.projectInfoPid+"')\" >"+rowObject.project_name+"</a>";
					        return str;
			   	   	    }
	                },
	                {name: "building_name",index: "building_name",sortable :false},
	                {name: "contributiveTypeName",index: "contributiveTypeName",sortable :false},
	                {name: "investSubjectName",index: "investSubjectName",sortable :false},
	                {name: "expect_contributive_amount",index: "expect_contributive_amount",sortable :false},
	                {name: "hold_stock_rate",index: "hold_stock_rate", sortable :false},
	                {name: "realInvestAmount",index: "realInvestAmount", sortable :false},
	                {name: "term",index: "term", sortable :false},
	                
	                {name: "dividend_type",index: "dividend_type", sortable :false, hidden : true},
	                {name: "dividendTypeName",index: "dividendTypeName", sortable :false},
	                
	                {name: "annualized_interest_rate",index: "annualized_interest_rate", sortable :false},
	                {name: "expect_all_receiver_account",index: "expect_all_receiver_account", sortable :false, hidden : true},
	                {name: "receivedPrincipal",index: "receivedPrincipal", sortable :false},
	                {name: "receivedProfit",index: "receivedProfit", sortable :false}
	                ];
	
	var complateFunc = function(){}
	loadData(url, colNames, colModel, complateFunc);
	
	
	// 查看投资主体详情
	$('#viewInvestSubjectDetail').on('click', function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			window.location.href = basePath + "investSubjectBusinessManage/toInvestSubjectBusinessManangeDetail?type=3&subjectPid="+rowData.investSubjectPid+"&projectPid="+rowData.projectInfoPid;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
	});
	
		
});

//投资主体业务管理列表中  查看  超链接(跳转至项目管理模块)
function viewInvestPlanManage(projectPid) {
	window.location.href = basePath + "/projectManageController/toProject?type=3&projectPid="+projectPid;
}






