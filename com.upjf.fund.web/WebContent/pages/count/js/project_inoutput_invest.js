$(function(){
	window.hasLoadTable = 0;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
		var laydate = layui.laydate;
		laydate.render({
			elem: '#payStartDate' //指定元素
		});
		laydate.render({
			elem: '#payEndDate' //指定元素
		});
		laydate.render({
			elem: '#receiverStartDate' //指定元素
		});
		laydate.render({
			elem: '#receiverEndDate' //指定元素
		});
	});
	
	
	initData();
})


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableProjectInOutInvestList").jqGrid({
		url : basePath+"report/projectInOutInvestList",
		datatype : "json",
		mtype : "POST",
		height : 450,
		autowidth : true,
		shrinkToFit : true,
		viewrecords : true,
		rownumbers : true,
		multiselect : true,
		multiboxonly:true,
		beforeSelectRow: beforeSelectRow,
		rownumWidth : 50,
		postData : $.serializeObject($("#searchFrom")),
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#pagerProjectInOutInvestList",
		colNames : [ "序号", "付款时间", "项目名称", "收款公司", "收款金额(万元)","收款账号","收款时间","付款期数","付款金额(万元)","付款公司","付款账号","财务确认状态","","","","","","","","",""],
		colModel : [ {
			name : "pid",
			index : "pid",
			hidden : true
		}, {
			name : "pay_date",
			index : "pay_date",
			sortable : false,
			formatter : formatterDate
		}, {
			name : "project_name",
			index : "project_name",
			sortable : false
		}, {
			name : "receiver_name",
			index : "receiver_name",
			sortable : false
		}, {
			name : "receiver_amount",
			index : "receiver_amount",
			sortable : false
		}, {
			name : "receiver_account",
			index : "receiver_account",
			sortable : false
		},  {
			name : "receiver_date",
			index : "receiver_date",
			sortable : false,
			formatter : formatterDate
		},{
			name : "pay_term",
			index : "pay_term",
			sortable : false
		}, {
			name : "pay_amount",
			index : "pay_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "contributive_name",
			index : "contributive_name",
			sortable : false
		}, {
			name : "pay_account",
			index : "pay_account",
			sortable : false
		}, {
			name : "finance_confirm_status",
			index : "finance_confirm_status",
			sortable : false,
			formatter : paramStatusCell
		}, {
			name : "pay_bank_name",
			index : "pay_bank_name",
			hidden : true
		}, {
			name : "receiver_bank_name",
			index : "receiver_bank_name",
			hidden : true
		}, {
			name : "create_name",
			index : "create_name",
			hidden : true
		}, {
			name : "create_date",
			index : "create_date",
			hidden : true,
			formatter : formatterDate
		}, {
			name : "pay_term",
			index : "pay_term",
			hidden : true
		}, {
			name : "pay_date",
			index : "pay_date",
			hidden : true,
			formatter : formatterDate
		}, {
			name : "pay_amount",
			index : "pay_amount",
			hidden : true
		}, {
			name : "pay_remark",
			index : "pay_remark",
			hidden : true
		}, {
			name : "receiver_remark",
			index : "receiver_remark",
			hidden : true
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		},
		loadComplete:function(data){
			if(data != undefined && data != "" && data.paramMap != undefined && data.paramMap != "" && data.paramMap != null){
				var totalAmount = data.paramMap.totalAmount;
				if(totalAmount == undefined || totalAmount == null || totalAmount == "" || totalAmount == "null"){
					totalAmount = "0";
				}
				$("#totalAmount").html(totalAmount+"万元");
			}else{
				$("#totalAmount").html("0万元");
			}
        }
	});
	
	$("#tableProjectInOutInvestList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectInOutInvestList").jqGrid("navGrid", "#pagerProjectInOutInvestList", {
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

//状态内容格式化
function paramStatusCell(cellvalue, options, rowdata){
	if(cellvalue == undefined || cellvalue == "" || cellvalue == "0"){
		return "未确定";
	}else if(cellvalue == "1"){
		return "已确定";
	}
}

function resetForm(){
	$("select").find("option:selected").attr('selected',false);
}

function beforeSelectRow(){
	$("#tableProjectInOutInvestList").jqGrid('resetSelection');
    return(true);
}

//获取投资详细
function getInvestDetail(){
	//获取多选到的id集合
	var ids = $("#tableProjectInOutInvestList").jqGrid("getGridParam", "selarrrow");
	if(ids == undefined || ids == "" || ids.length > 1){
		layer.msg("请选择一条数据！",{icon: 2});
		return false;
	}
	//由id获得对应数据行
	var rowData = $("#tableProjectInOutInvestList").jqGrid('getRowData', ids);
	if(rowData != null && rowData != undefined){
		$("#projectName").val(rowData.project_name);
		$("#receiverName").val(rowData.receiver_name);
		$("#contributiveName").val(rowData.contributive_name);
		$("#receiverBankName").val(rowData.receiver_bank_name);
		$("#payBankName").val(rowData.pay_bank_name);
		$("#receiverAccount").val(rowData.receiver_account);
		$("#payAccount").val(rowData.pay_account);
		$("#receiverAmount").val(rowData.receiver_amount);
		$("#receiverDate").val(rowData.receiver_date);
		$("#payRemark").val(rowData.pay_remark);
		$("#financeConfirmStatus").val(rowData.finance_confirm_status);
		$("#reveiverRemark").html(rowData.reveiver_remark);
		$("#createName").val(rowData.create_name);
		$("#createDate").val(rowData.create_date);
		$("#payDate").val(rowData.pay_date);
		$("#payTerm").val(rowData.pay_term);
		$("#payAmount").val(rowData.pay_amount);
	    layer.open({
	        type: 1,
	        title: [rowData.contributive_name+' 出款明细','font-size:18px;'],
	        area: ['1040px', '680px'],
	        btnAlign: 'c',
	        content: $('#Item_Inoutput_Out'),
	        btn: [ '关闭'],
	        btn1: function(index, layero){
	        	layer.close(index);
	        }
	    });
	    
	    var pid = rowData.pid; 
		showInvestDetail(pid);
	}
}

function showInvestDetail(pid){
	if(hasLoadTable == 1){
		$("#tableProjectInOutInvestDetailList").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:{pid:pid},
        }).trigger("reloadGrid");
	}else{
		$.jgrid.defaults.styleUI = "Bootstrap";
		$("#tableProjectInOutInvestDetailList").jqGrid({
			url : basePath+"finance/queryStockholderInvestAccessoryList",
			datatype : "json",
			mtype : "POST",
			height : 'auto',
			autowidth : true,
			shrinkToFit : true,
			viewrecords : true,
			rownumbers : true,
			rownumWidth : 100,
			postData : {'pid':pid},
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : "#pagerProjectInOutInvestDetailList",
			colNames : [ "序号", "文件名", "上传时间", "上传人", "操作"],
			colModel : [ {
				name : "pid",
				index : "pid",
				hidden : true
			}, {
				name : "file_all_name",
				index : "file_all_name",
				sortable : false
			}, {
				name : "create_date",
				index : "create_date",
				sortable : false,
				formatter : formatterDate
			}, {
				name : "user_name",
				index : "user_name",
				sortable : false
			}, {
				name : "operation",
				index : "operation",
				sortable : false,
				formatter : paramOperationCell
			}],
			loadComplete:function(data){
				hasLoadTable = 1;
	        }
		});
		
		$("#tableProjectInOutInvestDetailList").jqGrid('setLabel','rn', '序号');
	    
		$("#tableProjectInOutInvestDetailList").jqGrid("navGrid", "#pagerProjectInOutInvestDetailList", {
			edit : false,
			add : false,
			del : false,
			search : false,
			refresh : true
		});
	}
}

//操作内容格式化
function paramOperationCell(cellvalue, options, rowdata){
	if(rowdata != undefined && rowdata != ""){
   	 	var url = "'"+rowdata.file_url+'&filePid='+rowdata.file_pid+'&fileName='+rowdata.file_all_name+"'";
   	 	var cellStr = '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>  <a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
   	 	return cellStr;
	}
	return "";
}
