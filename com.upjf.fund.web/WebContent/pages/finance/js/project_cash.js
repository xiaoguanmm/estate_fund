$(function(){
	window.dialogIndex = 1;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#startDate' //指定元素
		});
		laydate.render({
			elem: '#endDate' //指定元素
		});
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableProjectCashList").jqGrid({
		url : basePath+"finance/projectCashList",
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
		pager : "#pagerProjectCashList",
		colNames : [ "序号", "项目名称", "出资主体", "出资主体投资规模（万元）","上市公司投资规模（万元）", "投资起始日","期限","投资方式","持股比例","项目现状","出资主体已投规模（万元）","待投规模（万元）","回款情况本金（万元）","回款情况收益（万元）"],
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
			name : "invest_start_date",
			index : "invest_start_date",
			sortable : false,
			formatter : formatterDate
		}, {
			name : "term",
			index : "term",
			sortable : false
		}, {
			name : "invest_type",
			index : "invest_type",
			sortable : false
		}, {
			name : "hold_stock_ratio",
			index : "hold_stock_ratio",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "project_progress_name",
			index : "project_progress_name",
			sortable : false
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
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableProjectCashList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectCashList").jqGrid("navGrid", "#pagerProjectCashList", {
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