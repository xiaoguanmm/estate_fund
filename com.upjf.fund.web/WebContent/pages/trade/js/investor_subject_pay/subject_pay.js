$(function(){
	var url = basePath+"finance/queryInvestSubjectInvestList?paymentType=2"
	var colNames = ["付款时间","项目名称","出资主体","付款账号","付款期数",
	                "付款金额(万元)","收款公司","收款金额(万元)","收款账号",
	                "付款备注","付款凭证","财务录入操作者","录入时间","pid"];
	var colModel = [
	                {name:"pay_date",index:"pay_date",sortable:false},
	                {name:"project_name",index:"project_name",sortable:false},
	                {name: "contributive_name",index: "contributive_name", sortable :false},
	                {name: "pay_account",index: "pay_account", sortable :false},
	                {name: "pay_term",index: "pay_term", sortable :false},
	                {name: "pay_amount",index: "pay_amount", sortable :false},
	                {name: "receiver_name",index: "receiver_name",sortable :false},
				    {name: "receiver_amount",index: "receiver_amount",sortable :false},
				    {name: "receiver_account",index: "receiver_account",sortable :false},
				    {name: "pay_remark",index: "pay_remark", sortable :false},
				    {name: "show_accessory",index: "show_accessory", sortable :false,formatter:function(cellvalue, options, rowObject){
				    	var url = "'"+basePath+"finance/queryInvestSubjectAccessoryList"+"'";
				    	var paymentId = "'"+rowObject.pid+"'";
				    	return '<a href="javascritp:;" class="table_bnt show_accessory" onclick="showAccessoryList('+url+','+paymentId+')">查看</a>';
				    }},
				    {name: "investor_op_name",index: "investor_op_name", sortable :false},
				    {name: "investor_op_date",index: "investor_op_date", sortable :false},
				    {name: "pid",index:"pid",sortable :false,hidden:true}
	                ];
	var complateFunc = function(){}
	loadData(url, colNames, colModel, complateFunc);
	
	
	
	/*查看*/
	$("#view_invest").on("click",function(){
		var rowIds=$('#data_list').jqGrid('getGridParam','selarrrow');
		if(rowIds.length>1){
			layer.msg("只能选中一条数据",{icon:7})
			return ;
		}
		if(rowIds.length==0){
			layer.msg("请选择数据",{icon:7})
			return ;
		}
		var rowid = $("#data_list").getGridParam("selrow");
		var rowData=$("#data_list").jqGrid("getRowData",rowid);
		var pid = rowData.pid;
		window.location.href = basePath+"finance/toAddInvestSubjectInvest?operation=view&pid="+pid;
	});
	
	$("#data_list").jqGrid("navGrid", "#page_list", {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : true
	});
	
})


