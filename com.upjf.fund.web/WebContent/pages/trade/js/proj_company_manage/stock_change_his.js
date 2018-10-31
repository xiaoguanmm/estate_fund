var form='';
$(function(){
	layui.use(['form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
//	    var $ = jQuery = layui.$;
		
		
		
		/**********************************股权变更历史js开始***************************************/	
		var stockholderPid = $("#stockholderPid").val();
		var url = basePath+"tradeManage/queryStockChangeHisList?stockholderPid="+stockholderPid;
		var colNames = ["stockholderPid","stockRightsChangePid","corpId","股东往来","备注","股东类别",
		                "申请变更时间","申请变更人id","申请变更人","股东类别","股东","注册资本(万元)","持股比例","实际出资(万元)","出资期数(月)","股权确认状态code","股权确认状态","股权变更确认时间","股权变更确认人id","股权变更确认人","股权确认备注","附件资料"];
		var colModel = [
		                {name: "stockholderPid",index: "stockholderPid", sortable :false, hidden : true},
		                {name: "stockRightsChangePid",index: "stockRightsChangePid", sortable :false, hidden : true},
		                {name: "corp_id",index: "corp_id", sortable :false, hidden : true},
		                {name: "stockholder_contacts",index: "stockholder_contacts", sortable :false, hidden : true},
		                {name: "remark",index: "remark", sortable :false, hidden : true},
		                {name: "stockholder_type",index: "stockholder_type", sortable :false, hidden : true},
		                
		                {name: "create_date",index: "create_date",sortable :false},
		                
		                {name: "create_id",index: "create_id",sortable :false, hidden : true},
		                {name: "create_name",index: "create_name",sortable :false},
		                
		                {name: "stockholder_type_name",index: "stockholder_type_name",sortable :false},
		                {name: "name",index: "name",sortable :false},
		                {name: "register_capital",index: "register_capital",sortable :false},
		                {name: "hold_stock_ratio",index: "hold_stock_ratio",sortable :false},
		                {name: "actual_funding",index: "actual_funding", sortable :false},
		                {name: "term",index: "term", sortable :false},
		                
		                {name: "stock_rights_status",index: "stock_rights_status", sortable :false, hidden : true},
		                {name: "stock_rights_status_name",index: "stock_rights_status_name", sortable :false},
		                
		                {name: "change_date",index: "change_date", sortable :false},
		                
		                {name: "change_id",index: "change_id", sortable :false, hidden : true},
		                {name: "change_name",index: "change_name", sortable :false},
		                
		                {name: "stock_rights_remark",index: "stock_rights_remark", sortable :false},
		                {name: "fujian",index: "fujian", sortable :false,
		                	formatter:function(cellvalue, options, rowObject){
		                		var obj = JSON.stringify(rowObject);
		                		if(null != obj && obj != "") {
		                			obj = obj.replace(" ", "");
								}
						    	var url = "'"+basePath+"tradeManage/queryStockRightsChangeAccessoryList"+"'";
						    	var str1 = '<a href="javascritp:;" class="table_bnt show_accessory" str='+obj+' onclick="viewStockRightsChange('+url+',this)"  >查看</a>';
						    	return str1;
		                	}
		                }   
		                ];
		
		var complateFunc = function(){
			/*---------新增项目公司---项目公司股东信息---股权变更历史--点击’查看股权变更明细‘('查看股权'弹窗)--------*/
		      $("#detail_stock1").on("click",function(){
					
					var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
					var rowData = $("#data_list").jqGrid("getRowData",rowsId);
					if(rowsId.length == 1) {
						// 股东名称等置灰
						$("#stockholderNameConfirm").attr("disabled","disabled");
						$("#stockholderTypeConfirm").attr("disabled","disabled");
						$("#registerCapitalConfirm").attr("disabled","disabled");
						$("#stockholderContactsConfirm").attr("disabled","disabled");
						$("#holdStockRatioConfirm").attr("disabled","disabled");
						$("#remarkConfirm").attr("disabled","disabled");
						$("input:radio[name='stockRightsStatus']").attr("disabled","disabled");
						$("textarea[name='stockRightsRemark']").attr("disabled","disabled");
						
						
						$("#equityConfirmForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
						$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
						// 回显下拉选择框
						$("#equityConfirmForm").find("select[name=stockholderName]").val(rowData.corp_id);
						$("#equityConfirmForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
						form.render('select');
						$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
						$("#equityConfirmForm").find("input[name=registerCapital]").val(rowData.register_capital);
						$("#equityConfirmForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
						$("#equityConfirmForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
						$("input:radio[name='stockRightsStatus'][value='"+rowData.stock_rights_status+"']").attr("checked",'checked');
						$("#equityConfirmForm").find("textarea[name=remark]").val(rowData.remark);
						form.render();
						
						var data = {pid:rowData.stockRightsChangePid};
						var url = "'"+basePath+"tradeManage/queryStockRightsChangeAccessoryList"+"'";
						layer.open({
							type: 1,
							title: ['股权明细','font-size:18px;'],
							area: ['1040px', '640px'],
							btnAlign: 'c',
							content: $('#sure_stock'),
							success: function(layero, index){
								
								/************************附件列表开始*********************/
								
								var dataListId = "equityConfirm_data_list";
								var pageList = "equityConfirm_page_list";
								showAccessory(url,data,dataListId,pageList);
								
								/************************附件列表结束*********************/
					        },
							btn: ['关闭'],
							btn1: function(index, layero){
								layer.close(index);
							}
						});
					} else if(rowsId.length > 1) {
						layer.msg("每次只能查看一条数据,请重新选择！", {time:1300,icon:5});
					} else {
						layer.msg("请选择数据！", {time:1300,icon:7});
					}
				});
			
		}
		
		loadData(url, colNames, colModel, complateFunc);
		
		
		
		
		
		
		/**********************************项目公司股东信息js结束***************************************/	
		
	});
	
	
	
	
	
})


// 列表中  查看  超链接
var viewloadFlg = false;
function viewStockRightsChange(url,obj) {
	
	var retObj = $(obj).attr("str");
	var rowData = eval("("+retObj+")");
	var data = {pid:rowData.stockRightsChangePid};
	layer.open({
		type: 1,
		title: ['查看股权','font-size:18px;'],
		area: ['1040px', '680px'],
		btnAlign: 'c',
		content: $('#sure_stock'),
		success: function(layero, index){
			// 股东名称等置灰
			$("#stockholderNameConfirm").attr("disabled","disabled");
			$("#stockholderTypeConfirm").attr("disabled","disabled");
			$("#registerCapitalConfirm").attr("disabled","disabled");
			$("#stockholderContactsConfirm").attr("disabled","disabled");
			$("#holdStockRatioConfirm").attr("disabled","disabled");
			$("input[name='stockRightsStatus']").attr("disabled","disabled");
			$("#remarkConfirm").attr("disabled","disabled");
			$("#stockRightsRemark").attr("disabled","disabled");
			
			$("#equityConfirmForm").find("input[name=stockholderPid]").val(rowData.stockholderPid);
			$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
			// 回显下拉选择框
			$("#equityConfirmForm").find("select[name=stockholderName]").val(rowData.corp_id);
			$("#equityConfirmForm").find("select[name=stockholderType]").val(rowData.stockholder_type);
			form.render('select');
			$("#equityConfirmForm").find("input[name=corPid]").val(rowData.corp_id);
			$("#equityConfirmForm").find("input[name=registerCapital]").val(rowData.register_capital);
			$("#equityConfirmForm").find("input[name=stockholderContacts]").val(rowData.stockholder_contacts);
			$("#equityConfirmForm").find("input[name=holdStockRatio]").val(rowData.hold_stock_ratio);
			$("input:radio[name='stockRightsStatus'][value='"+rowData.stock_rights_status+"']").attr("checked",'checked');
			$("#equityConfirmForm").find("textarea[name=remark]").val(rowData.remark);
			form.render();
			
			/************************附件列表开始*********************/
			var dataListId = "equityConfirm_data_list";
			var pageList = "equityConfirm_page_list";
			showAccessory(url,data,dataListId,pageList);
			
			/************************附件列表结束*********************/
        },
		btn: ['关闭'],
		btn1: function(index, layero){
			layer.close(index);
		}
	});
}

/**
 * 加载附件列表
 * @param viewloadFlg
 * @param data
 * @param dataListId
 * @param pageList
 */
function showAccessory(url,data,dataListId,pageList) {
	
	if(viewloadFlg){
		$("#"+dataListId).jqGrid('setGridParam',{  
            datatype:'json',  
            postData:data, //发送数据  
            page:1  
        }).trigger("reloadGrid");
	}else{
		var colNames = ["文件名","上传时间","上传人","操作"]
		var colModel = [
		                {name:"file_all_name", index:"file_all_name",sortable:false},
		                {name:"create_date", index:"create_date",sortable:false},
		                {name:"user_name", index:"user_name",sortable:false},
		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
		                }}
		                ]
		$("#"+dataListId).jqGrid({
			url:url,
			mtype : "POST",  
			contentType : "application/json",  
			datatype : "json",
			postData: data,
			autowidth: true,   
			shrinkToFit: true, 
			rownumbers: true,  
			viewrecords: true, 
			scrollOffset: 0,
			height : 'auto',
			rowNum: 5,                           
			rowList: [5, 10, 15],             
			colNames: colNames,
			cellEdit : false, 
			colModel: colModel,
			pager: "#"+pageList, 
			gridComplete:function(){
				$("#"+dataListId).jqGrid('setLabel','rn', '序号',{'font-size':'8px'});
				viewloadFlg = true;
			}
		});
		
	}
}


