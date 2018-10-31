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
	var title = '投资主体';
	if(investType == '2'){
		title = '投资人';
	}
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableInvestInOutList").jqGrid({
		url : basePath+"report/investInOutList",
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
		pager : "#pagerInvestInOutList",
		colNames : [ "序号", title, "参与投资项目数","预计投资总金额(万元)","实际投资总金额(万元)","预计总回报(万元)","实际总回报(万元)"],
		colModel : [ {
			name : "pid",
			index : "pid",
			hidden : true
		}, {
			name : "invest_name",
			index : "invest_name",
			sortable : false
		}, {
			name : "project_count",
			index : "project_count",
			sortable : false,
			formatter : paramInvestCountCell
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
	
	$("#tableInvestInOutList").jqGrid('setLabel','rn', '序号');
    
	$("#tableInvestInOutList").jqGrid("navGrid", "#pagerInvestInOutList", {
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

//投资项目数量内容格式化
function paramInvestCountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var investSubjectId = rowdata.invest_subject_id;
		var url = basePath+"report/investSubInOutCompanyListPage?investSubId="+investSubjectId;
		if(investType == '2'){
			 url = basePath+"report/investorInOutCompanyListPage?investorId="+investSubjectId;
		}
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//实际投资总金额(万元)内容格式化
function paramRealityInvestAmountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var contributiveId = rowdata.invest_subject_id;
		var url = basePath+"report/investInOutInvestListPage?investType="+investType+"&contributiveId="+contributiveId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//实际总回报(万元)内容格式化
function paramRealityReceivedAmountCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == 0 || cellvalue == "0"){
		return "<a href=\"javascript:void(0)\" class=\"table_bnt\">0</a>";
	}else{
		var receiverId = rowdata.invest_subject_id;
		var url = basePath+"report/investInOutRepayListPage?investType="+investType+"&receiverId="+receiverId;
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}