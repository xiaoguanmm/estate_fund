<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人回款列表</title>
<script type="text/javascript" src="${basePath}pages/finance/js/finance_manage.js"></script>
<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
<script type="text/javascript" src="${basePath}pages/trade/js/investor_pay_back/investor_return.js"></script>
</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>回款公司：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverId" lay-verify="" lay-search="">
                                <option value="">-请选择-</option>
                                <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款账号：</span></div>
                        <div class="search-text layui-form">
                            <select name="receiverAmount" lay-verify="" lay-search="">
                                <option value="">-请选择-</option>
                                <fund:options code="${Globals.PROJECT_NAME}"/>
                            </select>
                        </div>
                    </div>
                     <div class="search-box_div">
                         <div class="search-span"><span>回款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" name="paybackDateStart" class="layui-input" id="paybackDateStart" >
                                </li>
                                <li>
                                    <img src="${basePath}common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" name="paybackDateEnd" class="layui-input" id="paybackDateEnd" >
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

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>投资人回款列表</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:;" id="payback" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看回款记录</span>
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
</html>