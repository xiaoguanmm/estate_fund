$(function(){
	window.dialogIndex = 1;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
		var laydate = layui.laydate;
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableSituationTotalList").jqGrid({
		url : basePath+"finance/projectSituationTotalList",
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
		pager : "#pagerSituationTotalList",
		colNames : [ "序号", "项目名称", "项目总投（万元）", "项目总货值（万元）","项目预期收益（万元）", "项目预期收益率","出资主体","持股比例","投资方式","上市公司出资规模（万元）","出资主体投资收益（万元）","出资主体投资收益率"],
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
			name : "project_all_put_into",
			index : "project_all_put_into",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "all_price",
			index : "all_price",
			sortable : false,
			formatter : paramAmountNullCell
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
			name : "stockholder_name",
			index : "stockholder_name",
			sortable : false
		}, {
			name : "hold_stock_ratio",
			index : "hold_stock_ratio",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "invest_type",
			index : "invest_type",
			sortable : false
		}, {
			name : "contributive_amount",
			index : "contributive_amount",
			sortable : false,
			formatter : paramAmountNullCell
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
	
	$("#tableSituationTotalList").jqGrid('setLabel','rn', '序号');
    
	$("#tableSituationTotalList").jqGrid("navGrid", "#pagerSituationTotalList", {
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

//时间周期空内容格式化
function paramDateNullCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == ""){
		return "无";
	}
	return cellvalue;
}

//回款金额空内容格式化
function paramAmountNullCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == ""){
		return "0";
	}
	return cellvalue;
}

