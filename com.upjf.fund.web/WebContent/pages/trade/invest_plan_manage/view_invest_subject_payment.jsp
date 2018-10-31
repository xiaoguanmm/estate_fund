<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资主体付款信息列表</title>
     <%@ include file="../../../common/common.jsp"%>
	
</head>
<body>
    <div class="bg-gray">
    <input type="hidden" name="investPlanManagePid" value="${investPlanManagePid }" />
    <input type="hidden" name="investSubjectPid" value="${investSubjectPid }" />
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <span class="title-head_red">${investSubjectName }</span>
                    <img src="${basePath}common/img/title-head.png">
                    <span>付款信息</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0)" class="main_btn" id="view_invest">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看付款明细</span>
                    </a>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>应付总金额：</span>
                            <span class="title_explain_red"><span class="title_explain_red" id="pay_amount">${amount.pay_amount}</span>万元</span>
                        </li>
                        <li>
                            <span>实付总金额：</span>
                            <span class="title_explain_red"><span class="title_explain_red" id="receiver_amount">${amount.receiver_amount}</span>万元</span>
                        </li>
                    </ul>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled-two">
                        <table id="data_list" class="table table-bordered table-hover"></table>
                        <div id="page_list"></div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!--查看付款明细 -弹窗-开始-->
    <!-- <div id="plan_Pay_details" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">南山项目</option>
                                <option value="02">福田项目</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01">深圳市融鑫资产管理有限公司</option>
                                <option value="02" selected>深圳市汇联资管二十三号投资管理合伙企业</option>
                                <option value="03">南山物业投资有限公司</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资主体：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01" selected>深圳市融鑫有限公司</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">中国银行深圳高新支行</option>
                                <option value="02" selected>招商银行深圳南山支行</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">中国银行</option>
                                <option value="02" selected>农业银行</option>
                                <option value="03">招商银行</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">2654561</option>
                                <option value="02" selected>2656999</option>
                                <option value="03">2636556</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">256456185</option>
                                <option value="02" selected>05648561564</option>
                                <option value="03">941564511</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
                    <div class="fillin">
                        <input type="text" value="1" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="360000.00" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款时间：</label>
                    <div class="fillin">
                        <input type="text" value="2018-07-13" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks">付款备注</textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">财务录入操作者：</label>
                    <div class="fillin">
                        <input type="text" disabled="disabled" value="admin" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">录入时间：</label>
                    <div class="fillin">
                        <input type="text" value="2018-07-13 10:10:10" disabled="disabled" class="fillin-input notoptional">
                    </div>
                </div>
            </div>
        </div>
        <div class="main_edge">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            <div class="tabled_datum_940">
                <table class="table table-bordered table-hover">
                </table>
            </div>
        </div>
    </div> -->
    <!--查看付款明细-弹窗-结束-->

</body>
</html>

<script type="text/javascript">

layui.use('form',function(){
	var form = layui.form;
	
	/*查看付款明细*/
	$("#view_invest").on("click",function(){
		var rowsId = $("#data_list").jqGrid("getGridParam", "selarrrow");
		var rowData = $("#data_list").jqGrid("getRowData",rowsId);
		if(rowsId.length == 1) {
			window.location.href = basePath+"finance/toAddInvestSubjectInvest?operation=view&pid="+rowData.pid;
		} else if(rowsId.length > 1) {
			layer.msg("每次只能选择一条数据,请重新选择！", {time:1300,icon:5});
		} else {
			layer.msg("请选择数据！", {time:1300,icon:7});
		}
		
	});
	
});

$(function(){
	var data = 
	{
		investPlanManagePid : "${investPlanManagePid}",
		investSubjectPid : "${investSubjectPid}",
		paymentType:2,
		accessType:1
				
	};
	var url = basePath+"finance/queryInvestSubjectInvestList"
	var colNames = ["付款时间","项目名称","出资主体","付款账号","付款期数",
	                "付款金额(万元)","收款公司","收款账号",
	                "付款备注","付款凭证","财务录入操作者","录入时间","pid","project_status"];
	var colModel = [
	                {name:"pay_date",index:"pay_date",sortable:false},
	                {name:"project_name",index:"project_name",sortable:false,formatter:function(cellvalue, options, rowObject){
	                	var prjId = "'"+rowObject.prj_id+"'";
	                	if(!cellvalue){
	                		cellvalue="";
	                	}
				    	return '<a href="javascritp:;" class="table_bnt" onclick="showProjectInfo('+prjId+');return false;">'+cellvalue+'</a>';
				    }},
	                {name: "contributive_name",index: "contributive_name", sortable :false},
	                {name: "pay_account",index: "pay_account", sortable :false},
	                {name: "pay_term",index: "pay_term", sortable :false},
	                {name: "pay_amount",index: "pay_amount", sortable :false},
	                {name: "receiver_name",index: "receiver_name",sortable :false},
				    {name: "receiver_account",index: "receiver_account",sortable :false},
				    {name: "pay_remark",index: "pay_remark", sortable :false},
				    {name: "show_accessory",index: "show_accessory", sortable :false,formatter:function(cellvalue, options, rowObject){
				    	var url = "'"+basePath+"finance/queryInvestSubjectAccessoryList"+"'";
				    	var paymentId = "'"+rowObject.pid+"'";
				    	return '<a href="javascritp:;" class="table_bnt show_accessory" onclick="showAccessoryList('+url+','+paymentId+')">查看</a>';
				    }},
				    {name: "investor_op_name",index: "investor_op_name", sortable :false},
				    {name: "investor_op_date",index: "investor_op_date", sortable :false},
				    {name: "pid",index:"pid",sortable :false,hidden:true},
				    {name: "project_status",index:"project_status",sortable :false,hidden:true}
	                ];
	var complateFunc = function(){}
	loadData(url, colNames, colModel, complateFunc, data);
});
  
</script>
