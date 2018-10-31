<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人利润分配</title>
    <%@ include file="../../../../common/common.jsp"%>
    <script type="text/javascript">
		$(function(){
			var url = basePath+"finance/queryInvestortPaybackList?receivedType=3"
			var colNames = ["项目名称","股东公司","付款公司","收款公司","投入本金(万元)","应回利润(万元)",
			                "已回款本金(万元)","已回款利润(万元)","回款状态",
			                "project_id","corp_id","invest_subject_id","project_name","stockholder_id"];
			var colModel = [
			                {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
			                	var prjId = "'"+rowObject.project_id+"'";
			                	if(!cellvalue){
			                		cellvalue="";
			                	}
						    	return '<a href="javascritp:;" class="table_bnt" onclick="showProjectInfo('+prjId+');return false;">'+cellvalue+'</a>';
						    }},
						    {name: "stockholder_name",index: "stockholder_name",sortable :false},
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
						    {name: "stockholder_id",index:"stockholder_id",sortable :false,hidden:true}
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
            	var stockholder_id = rowData.stockholder_id;//所属股东
            	var contributive_id = rowData.corp_id;//付款公司
            	var receiver_id = rowData.invest_subject_id;//收款公司
				var contributive_name = rowData.contributive_name;//付款公司名称
            	var receiver_name = rowData.receiver_name;//收款公司名称
				window.location.href = basePath+"finance/toInvestorHistoryPaybackList?project_id="+project_id+
						"&contributive_id="+contributive_id+"&receiver_id="+receiver_id+"&stockholder_id="+stockholder_id+
						"&contributive_name="+encodeURI(encodeURI(contributive_name))+"&receiver_name="+encodeURI(encodeURI(receiver_name))+
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
                        <div class="search-span"><span>付款公司：</span></div>
                        <div class="search-text layui-form">
                            <select name="contributiveId" lay-verify="" lay-search="">
                                <option value="">--请选择--</option>
                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>收款公司：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverId" lay-verify="" lay-search="">
                                <option value="">--请选择--</option>
                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
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
                    <span>投资人利润分配</span>
                </div>
                <div class="top_btn">
                	<input type="hidden" id="exportUrl" value="${basePath}finance/exportInvestorPaybackData">
                    <a href="javascript:;" id="payback" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看回款记录</span>
                    </a>
                    <a href="javascript:;" id="export" class="main_btn">
                        <img src="${basePath}common/img/export.png">
                        <span>导出</span>
                    </a>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>回款本金合计：</span>
                            <span class="title_explain_red">${amount.receiver_amount}万元</span>
                        </li>
                        <li>
                            <span>回款利润合计：</span>
                            <span class="title_explain_red">${amount.profit_amount}万元</span>
                        </li>
                    </ul>
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