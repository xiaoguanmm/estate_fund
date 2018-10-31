<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../../../common/common.jsp"%>
<title>投入产出项目信息</title>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
           		<form action="" method="post" id="searchFrom" name="searchFrom">
           		<input type="hidden" name="investSubId" id="investSubId" value="${investSubId }"/>
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                             <select name="projectId" lay-verify="" lay-search="" >
                             	<option value="">-请选择-</option>
								<c:forEach var="project" items="${projectList}">
                                 	<option value="${project.pid }" <c:if test="${project.pid eq projectId }">selected="selected"</c:if>>${project.project_name }</option>
                                </c:forEach>
                             </select>
                        </div>
                    </div>
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
                        <div class="search-span"><span>项目进度状态：</span></div>
                        <div class="search-text layui-form">
                             <select name="projectProgress" lay-verify="" lay-search="">
                             	<option value="">-请选择-</option>
								<fund:options code="PROJECT_PROGRESS"/>
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
                        <button type="button"  class="search-btn search-btn-chaxu" onclick="searchList('#tableInvestSubInOutCompanyList')">
                            <img src="<%=path %>/common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="reset" class="search-btn search-btn-cz" onclick="resetForm()">
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
                    <span>投入产出项目信息</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="tabled_one">
                    <!--表格-->
                    <table id="tableInvestSubInOutCompanyList"></table>
                    <!--底部翻页按钮栏 -->
		         	<div id="pagerInvestSubInOutCompanyList"></div>
                </div>
            </div>
        </div>

    </div>
	<script type="text/javascript" src="<%=path %>/pages/count/js/investsub_inoutput_company.js"></script>
</body>
</html>