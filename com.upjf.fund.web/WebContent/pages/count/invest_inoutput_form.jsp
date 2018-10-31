<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>股东投入产出--柱状图</title>
<%@ include file="../../../common/common.jsp"%>
<script type="text/javascript" src="<%=path %>/common/js/plugins/echarts/echarts-all.js"></script>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <form action="<%=path %>/report/investInOutFormPage" method="post" id="searchFrom" name="searchFrom" >
                <input type="hidden" name="investType" value="${investType }">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>查询日期：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" class="layui-input" id="startDate" name="startDate" lay-key="3" readonly="readonly" value="${startDate }">
                                </li>
                                <li>
                                    <img src="<%=path %>/common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" class="layui-input" id="endDate" name="endDate" lay-key="4" readonly="readonly" value="${endDate }">
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="search-box_div search_box_div_left">
                        <button type="submit"  class="search-btn search-btn-chaxu">
                            <img src="<%=path %>/common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="button" class="search-btn search-btn-cz" onclick="resetForm()">
                            <img src="<%=path %>/common/img/chognzhi-reach.png">
                            <span>重置</span>
                        </button>
                    </div>

                </div>
                </form>
            </div>
        </div>

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="<%=path %>/common/img/title-head.png">
                    <span>股东投入产出--柱状图</span>
                    <a href="<%=path %>/report/investInOutListPage?investType=${investType}" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="tabled_one">
                    <div style="padding:20px 20px 60px 40px;min-height: 600px" id="containerForm">
                        
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div style="display: none" id="amountTypeList">${amountTypeList }</div>
    <div style="display: none" id="investNameList">${investNameList }</div>
    <div style="display: none" id="expectInvestAmountList">${expectInvestAmountList }</div>
    <div style="display: none" id="expectAllReceiverAccountList">${expectAllReceiverAccountList }</div>
    <div style="display: none" id="realityInvestAmountList">${realityInvestAmountList }</div>
    <div style="display: none" id="realityReceivedAmountList">${realityReceivedAmountList }</div>
	<script type="text/javascript" src="<%=path %>/pages/count/js/invest_inoutput_form.js"></script>
</body>
</html>