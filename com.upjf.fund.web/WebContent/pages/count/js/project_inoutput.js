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
	$("#tableProjectInOutList").jqGrid({
		url : basePath+"report/projectInOutList",
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
		pager : "#pagerProjectInOutList",
		colNames : [ "序号", "项目名称", "项目公司", "项目最新进度时间","项目进度状态", "股东公司","预计投资总金额(万元)","实际投资总金额(万元)","预计总回报(万元)","实际总回报(万元)"],
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
			name : "prj_corp_name",
			index : "prj_corp_name",
			sortable : false
		}, {
			name : "project_progress_date",
			index : "project_progress_date",
			sortable : false,
			formatter : formatterDate
		}, {
			name : "project_progress_name",
			index : "project_progress_name",
			sortable : false
		}, {
			name : "stockholder_count",
			index : "stockholder_count",
			sortable : false,
			formatter : paramStockholderCountCell
		},  {
			name : "expect_invest_amount",
			index : "expect_invest_amount",
			sortable : false,
			formatter : paramAmountNullCell
		},{
			name : "reality_invest_amount",
			index : "reality_invest_amount",
			sortable : false,
			formatter : paramRealityInvestAmountCell
		}, {
			name : "expect_all_receiver_account",
			index : "expect_all_receiver_account",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "reality_received_amount",
			index : "reality_received_amount",
			sortable : false,
			formatter : paramRealityReceivedAmountCell
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableProjectInOutList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectInOutList").jqGrid("navGrid", "#pagerProjectInOutList", {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : true
	});
    
    
}

//金额空内容格式化
function paramAmountNullCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == ""){
		return "0";
	}
	return cellvalue;
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

//股东数量内容格式化
function paramStockholderCountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var projectId = rowdata.pid;
		var businessPrjInfoId = rowdata.business_prj_info_id;
		var url = basePath+"report/projectInOutCompanyListPage?intoType=1&projectId="+projectId+"&businessPrjInfoId="+businessPrjInfoId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//实际投资总金额(万元)内容格式化
function paramRealityInvestAmountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var projectId = rowdata.pid;
		var businessPrjInfoId = rowdata.business_prj_info_id;
		var url = basePath+"report/projectInOutInvestListPage?projectId="+projectId+"&businessPrjInfoId="+businessPrjInfoId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//实际总回报(万元)内容格式化
function paramRealityReceivedAmountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var projectId = rowdata.pid;
		var businessPrjInfoId = rowdata.business_prj_info_id;
		var url = basePath+"report/projectInOutRepayListPage?projectId="+projectId+"&businessPrjInfoId="+businessPrjInfoId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}