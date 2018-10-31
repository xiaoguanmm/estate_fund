<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人付款列表</title>
<script type="text/javascript" src="${basePath}pages/finance/js/finance_manage.js"></script>
<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
<script type="text/javascript" src="${basePath}pages/trade/js/investor_pay/investor_pay.js"></script>
</head>
<body>
    <div class="bg-gray">

        <!--搜索部分开始-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                            <select name="prjId" lay-verify="" lay-search="">
                                <option value="">-请选择-</option>
                                <fund:options code="${Globals.PROJECT_NAME}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>付款公司：</span></div>
                       <div class="search-text layui-form">
                                <select name="contributiveId" lay-verify="" lay-search="">
                                    <option value="">-请选择-</option>
                                    <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                                </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>收款公司：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverId" lay-verify="" lay-search="">
                                <option value="">-请选择-</option>
                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>付款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" name="payDateStart" class="layui-input" id="payDateStart" >
                                </li>
                                <li>
                                    <img src="${basePath}common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" name="payDateEnd" class="layui-input" id="payDateEnd" >
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>收款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" name="receiverDateStart" class="layui-input" id="receiverDateStart" >
                                </li>
                                <li>
                                    <img src="${basePath}common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" name="receiverDateEnd" class="layui-input" id="receiverDateEnd" >
                                </li>
                            </ul>
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
		<!--搜索部分结束-->
		
		
		<!--正文开始-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>投资人付款列表</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:;" id="view_invest" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看付款明细</span>
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
        <!--正文结束-->
    </div>

</body>
<script type="text/javascript">
</script>
</html>