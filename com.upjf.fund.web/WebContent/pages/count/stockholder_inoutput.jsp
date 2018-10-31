<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目股东投入产出统计</title>
<%@ include file="../../../common/common.jsp"%>


</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <form action="" method="post" id="searchFrom" name="searchFrom" >
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目股东：</span></div>
                        <div class="search-text layui-form">
	                        <select name="stockholderId" lay-verify="" lay-search="">
	                        	<option value="">-请选择-</option>
								<c:forEach var="stockholder" items="${stockholderList}">
	                            	<option value="${stockholder.corp_id }">${stockholder.name }</option>
	                           </c:forEach>
	                        </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>查询日期：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" class="layui-input" id="startDate" name="startDate" lay-key="3" readonly="readonly">
                                </li>
                                <li>
                                    <img src="<%=path %>/common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" class="layui-input" id="endDate" name="endDate" lay-key="4" readonly="readonly">
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="search-box_div search_box_div_left">
                        <button type="button"  class="search-btn search-btn-chaxu" onclick="searchList('#tableStockholderInOutList')">
                            <img src="<%=path %>/common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="reset" class="search-btn search-btn-cz">
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
                    <span>项目股东投入产出统计</span>
                </div>
                <div class="top_btn">
                    <a href="<%=path %>/report/stockholderInOutFormPage" class="main_btn">
                        <img src="<%=path %>/common/img/form.png">
                        <span>列表</span>
                    </a>
                </div>
                <div class="tabled_one">
					<!--表格-->
                    <table id="tableStockholderInOutList"></table>
                    <!--底部翻页按钮栏 -->
		         	<div id="pagerStockholderInOutList"></div>
                </div>
            </div>
        </div>

    </div>
	<script type="text/javascript" src="<%=path %>/pages/count/js/stockholder_inoutput.js"></script>
</body>
</html>