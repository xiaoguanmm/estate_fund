$(function(){
			var url = basePath+"finance/queryInvestortPaybackList?receivedType=3"
			var colNames = ["项目名称","付款公司","收款公司","投入本金(万元)","应回利润(万元)",
			                "已回款本金(万元)","已回款利润(万元)","回款状态",
			                "project_id","corp_id","invest_subject_id","project_name"];
			var colModel = [
			                {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
			                	var projectId = rowObject.project_id;
			                	return '<a href="javascript:;" class="table_bnt ">'+cellvalue+'</a>'
			                }},
						    {name: "contributive_name",index: "contributive_name",sortable :false},
						    {name: "receiver_name",index: "receiver_name",sortable :false},
						    {name: "pay_amount_total",index: "pay_amount_total",sortable :false},
						    {name: "received_profit",index: "received_profit", sortable :false},
						    {name: "receiver_amount_total",index: "receiver_amount_total", sortable :false},
						    {name: "profit_total",index: "profit_total", sortable :false},
						    {name: "received_status",index: "received_status", sortable :false},
						    {name: "project_id",index:"project_id",sortable :false,hidden:true},
						    {name: "corp_id",index:"corp_id",sortable :false,hidden:true},
						    {name: "invest_subject_id",index:"invest_subject_id",sortable :false,hidden:true},
						    {name: "project_name",index:"project_name",sortable :false,hidden:true},
			                ];
			var complateFunc = function(){}
			loadData(url, colNames, colModel, complateFunc);
			$("#payback").on("click",function(){
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
				var project_id = rowData.project_id;//项目id
            	var project_name = rowData.project_name;//项目名称
            	var contributive_id = rowData.corp_id;//付款公司
				var contributive_name = rowData.contributive_name;//付款公司名称
            	var receiver_id = rowData.invest_subject_id;//收款公司
            	var receiver_name = rowData.receiver_name;//收款公司名称
				window.location.href = basePath+"finance/toInvestorHistoryPaybackList?project_id="+project_id+
						"&contributive_id="+contributive_id+"&receiver_id="+receiver_id+
						"&contributive_name="+encodeURI(encodeURI(contributive_name))+"&receiver_name="+encodeURI(encodeURI(receiver_name))+
						"&project_name="+encodeURI(encodeURI(project_name));
			});
			
			
			$("#data_list").jqGrid("navGrid", "#page_list", {
				edit : false,
				add : false,
				del : false,
				search : false,
				refresh : true
			});
			
});