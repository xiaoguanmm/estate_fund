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
	$("#tableCashList").jqGrid({
		url : basePath+"finance/cashFlowList",
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
		pager : "#pagerCashList",
		colNames : [ "序号", "项目名称", "有限合伙(项目股东)", "发生主体","事项", "账户收入(万元)","账户支出(万元)","现金余额(万元)","发生时间","备注"],
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
			name : "occur_name",
			index : "occur_name",
			sortable : false
		}, {
			name : "cash",
			index : "cash",
			sortable : false,
			formatter : paramCashCell
		}, {
			name : "in_amount",
			index : "in_amount",
			sortable : false,
			formatter : paramInAmountCell
		}, {
			name : "out_amount",
			index : "out_amount",
			sortable : false,
			formatter : paramOutAmountCell
		}, {
			name : "total_amount",
			index : "total_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "receiver_date",
			index : "receiver_date",
			sortable : false,
			formatter : formatterDate
		}, {
			name : "reveiver_remark",
			index : "reveiver_remark",
			sortable : false
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableCashList").jqGrid('setLabel','rn', '序号');
    
	$("#tableCashList").jqGrid("navGrid", "#pagerCashList", {
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
		var projectId = rowdata.prj_id;
		var url = basePath+"/projectManageController/toProject?type=2&projectPid="+projectId;
			
		return "<a href=\""+url+"\" class=\"table_bnt\">"+cellvalue+"</a>";
	}
}

//事项内容格式化
function paramCashCell(cellvalue, options, rowdata){
	var type = rowdata.type;
	var stockholder_name = rowdata.stockholder_name;
	var occur_name = rowdata.occur_name;
	
	if(type == '1'){
		cellvalue = stockholder_name +"  出资到 "+occur_name;
	}else if(type == '2'){
		cellvalue = occur_name +"  出资到  "+stockholder_name;
	}else if(type == '3'){
		cellvalue = occur_name+"  付费到  "+stockholder_name;
	}else if(type == '4'){
		cellvalue = stockholder_name +"  付费到  "+occur_name;
	}
	var valueSize = 0;
	if(cellvalue != 'undefined' && cellvalue != ""&& cellvalue != null){
		valueSize = cellvalue.length;
	}
	if(valueSize > 40){
		return '<span title='+cellvalue+'>'+cellvalue.substring(0,40)+'...</span>'
	}else{
		return '<span title='+cellvalue+'>'+cellvalue+'</span>'  
	}
}

//收入内容格式化
function paramInAmountCell(cellvalue, options, rowdata){
	var type = rowdata.type;
	var receiverAmount = rowdata.receiver_amount;
	
	if(type == '1'){
		cellvalue = "--";
	}else if(type == '2'){
		if(receiverAmount != undefined && receiverAmount != ''){
			cellvalue = Math.abs(receiverAmount);
		}else{
			cellvalue = "0";
		}
	}else if(type == '3'){
		if(receiverAmount != undefined && receiverAmount != ''){
			cellvalue = Math.abs(receiverAmount);
		}else{
			cellvalue = "0";
		}
	}else if(type == '4'){
		cellvalue = "--";
	}
	
	return cellvalue;
}

//支出内容格式化
function paramOutAmountCell(cellvalue, options, rowdata){
	var type = rowdata.type;
	var receiverAmount = rowdata.receiver_amount;
	
	if(type == '1'){
		if(receiverAmount != undefined && receiverAmount != ''){
			cellvalue = Math.abs(receiverAmount);
		}else{
			cellvalue = "0";
		}
	}else if(type == '2'){
		cellvalue = "--";
	}else if(type == '3'){
		cellvalue = "--";
	}else if(type == '4'){
		if(receiverAmount != undefined && receiverAmount != ''){
			cellvalue = Math.abs(receiverAmount);
		}else{
			cellvalue = "0";
		}
	}
	
	return cellvalue;
}
//金额空内容格式化
function paramAmountNullCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == ""){
		return "0";
	}
	return cellvalue;
}
