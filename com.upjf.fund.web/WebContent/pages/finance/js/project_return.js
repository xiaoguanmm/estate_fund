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
		laydate.render({
			elem: '#returnStartDate' //指定元素
		});
		laydate.render({
			elem: '#returnEndDate' //指定元素
		});
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableProjectReturnList").jqGrid({
		url : basePath+"finance/projectReturnList",
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
		pager : "#pagerProjectReturnList",
		colNames : [ "序号", "项目名称", "出资主体", "出资主体投资规模（万元）","投资起始日", "期限","投资方式","出资主体持股比例","回款时间","回款金额（合计）"],
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
			name : "receiver_date_year",
			index : "receiver_date_year",
			sortable : false,
			formatter : paramDateNullCell
		}, {
			name : "total_receiver_amount",
			index : "total_receiver_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableProjectReturnList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectReturnList").jqGrid("navGrid", "#pagerProjectReturnList", {
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