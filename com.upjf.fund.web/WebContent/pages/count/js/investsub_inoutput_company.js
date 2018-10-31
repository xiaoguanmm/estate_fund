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
	$("#tableInvestSubInOutCompanyList").jqGrid({
		url : basePath+"report/investSubInOutCompanyList",
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
		pager : "#pagerInvestSubInOutCompanyList",
		colNames : [ "序号", "投资主体", "项目名称", "项目股东", "项目最新进度时间","项目进度状态","预计投资总金额(万元)","实际投资总金额(万元)","预计总回报(万元)","实际总回报(万元)"],
		colModel : [ {
			name : "investsub_id",
			index : "investsub_id",
			hidden : true
		}, {
			name : "investsub_name",
			index : "investsub_name",
			sortable : false
		}, {
			name : "project_name",
			index : "project_name",
			sortable : false
		}, {
			name : "stockholder_name",
			index : "stockholder_name",
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
		},  {
			name : "expect_invest_amount",
			index : "expect_invest_amount",
			sortable : false,
			formatter : paramAmountNullCell
		},{
			name : "reality_invest_amount",
			index : "reality_invest_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "expect_all_receiver_account",
			index : "expect_all_receiver_account",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "reality_received_amount",
			index : "reality_received_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableInvestSubInOutCompanyList").jqGrid('setLabel','rn', '序号');
    
	$("#tableInvestSubInOutCompanyList").jqGrid("navGrid", "#pagerInvestSubInOutCompanyList", {
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

function resetForm(){
	$("select").find("option:selected").attr('selected',false);
}