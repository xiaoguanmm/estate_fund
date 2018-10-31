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
	$("#tableStockholderInOutList").jqGrid({
		url : basePath+"report/stockholderInOutList",
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
		pager : "#pagerStockholderInOutList",
		colNames : [ "序号", "项目股东", "参与投资项目数","预计投资总金额(万元)","实际投资总金额(万元)","预计总回报(万元)","实际总回报(万元)"],
		colModel : [ {
			name : "pid",
			index : "pid",
			hidden : true
		}, {
			name : "stockholder_name",
			index : "stockholder_name",
			sortable : false
		}, {
			name : "project_count",
			index : "project_count",
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
	
	$("#tableStockholderInOutList").jqGrid('setLabel','rn', '序号');
    
	$("#tableStockholderInOutList").jqGrid("navGrid", "#pagerStockholderInOutList", {
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

//股东项目数量内容格式化
function paramStockholderCountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var corpId = rowdata.corp_id;
		var url = basePath+"report/projectInOutCompanyListPage?intoType=2&stockholderId="+corpId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//实际投资总金额(万元)内容格式化
function paramRealityInvestAmountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var corpId = rowdata.corp_id;
		var url = basePath+"report/projectInOutInvestListPage?intoType=2&corpId="+corpId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//实际总回报(万元)内容格式化
function paramRealityReceivedAmountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var corpId = rowdata.corp_id;
		var url = basePath+"report/projectInOutRepayListPage?intoType=2&corpId="+corpId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}