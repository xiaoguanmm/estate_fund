<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目股东出资审核管理</title>
    <%@ include file="../../../../common/common.jsp"%>
    <script type="text/javascript">
		$(function(){
			var url = basePath+"finance/queryProjectStockholderPaybackList?receivedType=1"
			var colNames = ["项目名称","项目公司","股东类别","回款公司","注册资本(万元)",
			                "持股比例","投入本金(万元)","应回利润(万元)","已回款本金",
			                "已回款利润","回款状态","project_id","corp_id","business_prj_id","prj_name","corporation_info_id"];
			var colModel = [
			                {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
			                	var prjId = "'"+rowObject.project_id+"'";
			                	if(!cellvalue){
			                		cellvalue="";
			                	}
						    	return '<a href="javascritp:;" class="table_bnt" onclick="showProjectInfo('+prjId+');return false;">'+cellvalue+'</a>';
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
				window.location.href = basePath+"finance/toStockholderPaybackList?project_id="+project_id+
						"&business_prj_id="+business_prj_id+"&corporation_info_id="+corporation_info_id+
						"&corp_id="+corp_id+"&corp_name="+encodeURI(encodeURI(corp_name))+"&prj_corp_name="+encodeURI(encodeURI(prj_corp_name))+
						"&project_name="+encodeURI(encodeURI(project_name));
			});
		});
    
    </script>


</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                            <select name="prjId" lay-verify="" lay-search="">
                                <option value="">--请选择--</option>
                                <fund:options code="${Globals.PROJECT_NAME}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款公司：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverId" lay-verify="" lay-search="">
                                <option value="">--请选择--</option>
                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款状态：</span></div>
                        <div class="search-text layui-form">
                          <select name="receivedStatus" lay-verify="" lay-search="">
                              <option value="">--请选择--</option>
                              <option value="1">已完结</option>
                              <option value="2">未完结</option>
                              <option value="">未回款</option>
                          </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>股东类别：</span></div>
                        <div class="search-text layui-form">
                          <select name="stockholderType" lay-verify="" lay-search="">
                              <option value="">--请选择--</option>
                              <fund:options code="${Globals.STOCKHOLDER_TYPE}"/>
                          </select>
                        </div>
                    </div>
                    <div class="search-box_div search_box_div_left">
                        <button type="button"  class="search-btn search-btn-chaxu">
                            <img src="${basePath}common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="button" class="search-btn search-btn-cz">
                            <img src="${basePath}common/img/chognzhi-reach.png">
                            <span>重置</span>
                        </button>
                    </div>

                </div>
            </div>
        </div>

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>项目股东回款</span>
                </div>
                <div class="top_btn">
                	<input type="hidden" id="exportUrl" value="${basePath}finance/exportStockholderPaybackData">
                    <a href="javascript:;" id="payback" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>回款</span>
                    </a>
                    <a href="javascript:;" id="export" class="main_btn">
                        <img src="${basePath}common/img/export.png">
                        <span>导出</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled-two">
                        <table id="data_list" class="table table-bordered table-hover"></table>
                        <div id="page_list"></div>
                    </div>
                    <div id="show_accessory" class="add_Makeuup">
                    <table id="accessory_list" class="table table-bordered table-hover"></table><div id="accessory_page_list"></div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
<script type="text/javascript" src="${basePath}pages/finance/js/finance_manage.js"></script>
<script type="text/javascript" src="${basePath}pages/finance/received/js/received_manage.js"></script>
</html>