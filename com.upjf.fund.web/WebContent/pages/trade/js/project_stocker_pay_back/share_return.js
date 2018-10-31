$(function(){
			var url = basePath+"finance/queryProjectStockholderPaybackList?receivedType=1"
			var colNames = ["项目名称","项目公司","股东类别","回款公司","注册资本(万元)",
			                "持股比例","投入本金(万元)","应回利润(万元)","已回款本金",
			                "已回款利润","回款状态","project_id","corp_id","business_prj_id","prj_name","corporation_info_id"];
			var colModel = [
			                {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
			                	var projectId = rowObject.project_id;
			                	return '<a href="javascript:;" class="table_bnt ">'+cellvalue+'</a>'
			                }},
			                {name: "prj_corp_name",index: "prj_corp_name",sortable :false},
						    {name: "stockholder_type",index: "stockholder_type",sortable :false},
						    {name: "corp_name",index: "corp_name",sortable :false},
						    {name: "register_capital",index: "register_capital",sortable :false},
						    {name: "hold_stock_ratio",index: "hold_stock_ratio", sortable :false},
						    {name: "pay_amount_total",index: "pay_amount_total", sortable :false},
						    {name: "received_profit",index: "received_profit", sortable :false},
						    {name: "receiver_amount_total",index: "receiver_amount", sortable :false},
						    {name: "profit_total",index: "profit", sortable :false},
						    {name: "received_status",index: "received_status", sortable :false},
						    {name: "project_id",index:"project_id",sortable :false,hidden:true},
						    {name: "corp_id",index:"corp_id",sortable :false,hidden:true},
						    {name: "business_prj_id",index:"business_prj_id",sortable :false,hidden:true},
						    {name: "prj_name",index:"prj_name",sortable :false,hidden:true,formatter:function(cellvalue, options, rowObject){
						    	
						    	return rowObject.project_name;
						    }},
						    {name: "corporation_info_id",index:"corporation_info_id",sortable :false,hidden:true},
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
				var project_id = rowData.project_id;
            	var corp_id = rowData.corp_id;
            	var business_prj_id = rowData.business_prj_id;
				var corp_name = rowData.corp_name;
            	var prj_corp_name = rowData.prj_corp_name;
            	var project_name = rowData.prj_name;
            	var corporation_info_id = rowData.corporation_info_id;
				window.location.href = basePath+"finance/toStockholderPaybackList?sourceModule=trade&project_id="+project_id+
						"&business_prj_id="+business_prj_id+"&corporation_info_id="+corporation_info_id+
						"&corp_id="+corp_id+"&corp_name="+encodeURI(encodeURI(corp_name))+"&prj_corp_name="+encodeURI(encodeURI(prj_corp_name))+
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