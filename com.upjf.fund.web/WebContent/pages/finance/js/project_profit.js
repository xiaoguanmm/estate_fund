$(function(){
	window.dialogIndex = 1;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableProjectProfitList").jqGrid({
		url : basePath+"finance/projectProfitList",
		datatype : "json",
		mtype : "POST",
		height : 500,
		autowidth : true,
		shrinkToFit : true,
		viewrecords : true,
		rownumbers : true,
		rownumWidth : 50,
		postData : $.serializeObject($("#searchFrom")),
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#pagerProjectProfitList",
		colNames : [ "序号", "项目名称", "出资主体", "出资主体投资规模(万元)","出资主体持股比例", "项目预期收益(万元)","项目预期收益率","出资主体投资收益(万元)","出资主体投资收益率"],
		colModel : [ {
			name : "pid",
			index : "pid",
			hidden : true
		}, {
			name : "project_name",
			index : "project_name",
			sortable : false,
			formatter : paramProjectNameCell
		}, {
			name : "stockholder_name",
			index : "stockholder_name",
			sortable : false
		}, {
			name : "investor_scope_amount",
			index : "investor_scope_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "hold_stock_ratio",
			index : "hold_stock_ratio",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "expect_earnings",
			index : "expect_earnings",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "expect_earnings_rate",
			index : "expect_earnings_rate",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "investor_expect_earnings",
			index : "investor_expect_earnings",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "investor_expect_earnings_rate",
			index : "investor_expect_earnings_rate",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableProjectProfitList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectProfitList").jqGrid("navGrid", "#pagerProjectProfitList", {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : true
	});
    
}

//项目名称内容格式化
function paramProjectNameCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == ""){
		return "";
	}else{
		var projectId = rowdata.pid;
		var url = basePath+"/projectManageController/toProject?type=2&projectPid="+projectId;
			
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//支出内容格式化
function paramHoldStockRatioCell(cellvalue, options, rowdata){
	if(cellvalue != undefined && cellvalue != ""){
		return cellvalue +"%";
	}
	return "";
}

//金额空内容格式化
function paramAmountNullCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == ""){
		return "0";
	}
	return cellvalue;
}