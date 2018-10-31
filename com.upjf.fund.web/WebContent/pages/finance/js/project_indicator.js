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
	$("#tableProjectIndicatorList").jqGrid({
		url : basePath+"finance/projectIndicatorList",
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
		pager : "#pagerProjectIndicatorList",
		colNames : [ "序号", "项目名称", "更新单元范围面积（㎡）", "拟拆除建筑面积（㎡）","可开发建设用地（㎡）", "容积率","建筑面积（㎡）","计容可售建筑面积（㎡）","总货值（万元）","项目总投（万元）","出资主体","出资主体持股比例","出资主体投资规模（万元）","规划用地性质","土地获取方式","项目现状"],
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
			name : "update_range_per_area",
			index : "update_range_per_area",
			sortable : false
		}, {
			name : "prepare_dismantle_area",
			index : "prepare_dismantle_area",
			sortable : false
		}, {
			name : "develop_build_area",
			index : "develop_build_area",
			sortable : false
		}, {
			name : "project_cubage_rate",
			index : "project_cubage_rate",
			sortable : false,
			formatter : paramHoldStockRatioCell
		}, {
			name : "build_area",
			index : "build_area",
			sortable : false
		}, {
			name : "cubage_sale_area",
			index : "cubage_sale_area",
			sortable : false
		}, {
			name : "all_price",
			index : "all_price",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "project_all_put_into",
			index : "project_all_put_into",
			sortable : false,
			formatter : paramAmountNullCell
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
			name : "hold_stock_amount",
			index : "hold_stock_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "project_land_quality",
			index : "project_land_quality",
			sortable : false
		}, {
			name : "land_get_way_name",
			index : "land_get_way_name",
			sortable : false
		}, {
			name : "project_progress_name",
			index : "project_progress_name",
			sortable : false
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableProjectIndicatorList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectIndicatorList").jqGrid("navGrid", "#pagerProjectIndicatorList", {
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