$(function(){
	window.hasLoadTable = 0;
	
	layui.use(['laydate','form','jquery'], function(){
		var form = layui.form;
		var $ = jQuery = layui.$;
		var laydate = layui.laydate;
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
	$("#tableProjectInOutRepayList").jqGrid({
		url : basePath+"report/projectInOutRepayList",
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
		pager : "#pagerProjectInOutRepayList",
		colNames : [ "序号", "回款时间", "项目名称", "回款公司", "回款账号","回款本金(万元)","回款利润(万元)","财务确认状态","付款公司","付款账号","回款备注","","","",""],
		colModel : [ {
			name : "pid",
			index : "pid",
			hidden : true
		}, {
			name : "receiver_date",
			index : "receiver_date",
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
			name : "receiver_account",
			index : "receiver_account",
			sortable : false
		}, {
			name : "receiver_amount",
			index : "receiver_amount",
			sortable : false,
			formatter : paramAmountNullCell
		}, {
			name : "profit",
			index : "profit",
			sortable : false,
			formatter : paramAmountNullCell
		},{
			name : "received_status",
			index : "received_status",
			sortable : false,
			formatter : paramStatusCell
		}, {
			name : "contributive_name",
			index : "contributive_name",
			sortable : false
		}, {
			name : "pay_account",
			index : "pay_account",
			sortable : false
		}, {
			name : "reveiver_remark",
			index : "reveiver_remark",
			sortable : false
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
	
	$("#tableProjectInOutRepayList").jqGrid('setLabel','rn', '序号');
    
	$("#tableProjectInOutRepayList").jqGrid("navGrid", "#pagerProjectInOutRepayList", {
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
	if(cellvalue == undefined || cellvalue == "" || cellvalue == "3"){
		return "未回款";
	}else if(cellvalue == "1"){
		return "已完结";
	}else if(cellvalue == "2"){
		return "未完结";
	}
}

function resetForm(){
	$("select").find("option:selected").attr('selected',false);
}
function beforeSelectRow(){
	$("#tableProjectInOutRepayList").jqGrid('resetSelection');
    return(true);
}

//获取回报详细
function getRepayDetail(){
	//获取多选到的id集合
	var ids = $("#tableProjectInOutRepayList").jqGrid("getGridParam", "selarrrow");
	if(ids == undefined || ids == "" || ids.length > 1){
		layer.msg("请选择一条数据！",{icon: 2});
		return false;
	}
	//由id获得对应数据行
	var rowData = $("#tableProjectInOutRepayList").jqGrid('getRowData', ids);
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
		$("#profit").val(rowData.profit);
		$("#receivedStatus").val(rowData.received_status);
		$("#reveiverRemark").html(rowData.reveiver_remark);
		$("#createName").val(rowData.create_name);
		$("#createDate").val(rowData.create_date);
		
	    layer.open({
	        type: 1,
	        title: [rowData.receiver_name+' 回款明细','font-size:18px;'],
	        area: ['1040px', '680px'],
	        btnAlign: 'c',
	        content: $('#Item_Inoutput_Received'),
	        btn: [ '关闭'],
	        btn1: function(index, layero){
	        	layer.close(index);
	        }
	    });
	    
	    var pid = rowData.pid; 
		showRepayDetail(pid);
	}
}

function showRepayDetail(subjectReturnPid){
	if(hasLoadTable == 1){
		$("#tableProjectInOutRepayDetailList").jqGrid('setGridParam',{  
            datatype:'json',  
            postData:{'subjectReturnPid':subjectReturnPid}, 
        }).trigger("reloadGrid");
	}else{
	    $.jgrid.defaults.styleUI = "Bootstrap";
		$("#tableProjectInOutRepayDetailList").jqGrid({
			url : basePath+"report/getReturnDetailFileList",
			datatype : "json",
			mtype : "POST",
			height : 'auto',
			autowidth : true,
			shrinkToFit : true,
			viewrecords : true,
			rownumbers : true,
			rownumWidth : 100,
			postData : {'subjectReturnPid':subjectReturnPid},
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : "#pagerProjectInOutRepayDetailList",
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
		
		$("#tableProjectInOutRepayDetailList").jqGrid('setLabel','rn', '序号');
	    
		$("#tableProjectInOutRepayDetailList").jqGrid("navGrid", "#pagerProjectInOutRepayDetailList", {
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
