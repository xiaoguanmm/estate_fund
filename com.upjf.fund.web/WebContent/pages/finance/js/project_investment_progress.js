$(function(){
	window.dialogIndex = 1;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#investStartDate' //指定元素
		});
		laydate.render({
			elem: '#investEndDate' //指定元素
		});
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableInvestmentProgressList").jqGrid({
		url : basePath+"finance/projectInvestmentProgressList",
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
		pager : "#pagerInvestmentProgressList",
		colNames : [ "序号", "项目名称", "出资主体", "出资主体投资规模（万元）","上市公司投资规模（万元）", "项目总投（万元）","投资起始日","期限(月)","持股比例","总货值（万元）","项目预期收益(万元)", "项目预期收益率", "出资主体投资收益（万元）","出资主体投资收益率", "出资主体已投规模（万元）","待投规模（万元）","回款情况本金（万元）","回款情况收益（万元）","项目现状"],
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
			name : "contributive_amount",
			index : "contributive_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "project_all_put_into",
			index : "project_all_put_into",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "invest_start_date",
			index : "invest_start_date",
			sortable : false,
			formatter : formatterDate
		}, {
			name : "term",
			index : "term",
			sortable : false
		}, {
			name : "hold_stock_ratio",
			index : "hold_stock_ratio",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "all_price",
			index : "all_price",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "expect_earnings",
			index : "expect_earnings",
			sortable : false
		}, {
			name : "expect_earnings_rate",
			index : "expect_earnings_rate",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "investor_expect_earnings",
			index : "investor_expect_earnings",
			sortable : false
		}, {
			name : "investor_expect_earnings_rate",
			index : "investor_expect_earnings_rate",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "actual_amount",
			index : "actual_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "need_amount",
			index : "need_amount",
			sortable : false,
			formatter : paramNeedAmountCell
		}, {
			name : "receiver_amount",
			index : "receiver_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "profit_amount",
			index : "profit_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "project_progress_name",
			index : "project_progress_name",
			sortable : false
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableInvestmentProgressList").jqGrid('setLabel','rn', '序号');
    
	$("#tableInvestmentProgressList").jqGrid("navGrid", "#pagerInvestmentProgressList", {
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

//待投资规模(计划投资-已经投资)
function paramNeedAmountCell(cellvalue, options, rowdata){
	var plan_amount = rowdata.plan_amount;
	var actual_amount = rowdata.actual_amount;
	if(plan_amount == undefined || plan_amount == ""){
		plan_amount = 0;
	}
	if(actual_amount == undefined || actual_amount == ""){
		actual_amount = 0;
	}
	var needAmount = plan_amount - actual_amount;
	return needAmount;
}