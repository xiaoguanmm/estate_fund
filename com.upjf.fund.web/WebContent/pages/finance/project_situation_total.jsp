<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目情况(总表)</title>
<%@ include file="../../../common/common.jsp"%>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
            	<form action="<%=path %>/finance/exportProjectSituationTotalList" method="post" id="searchFrom" name="searchFrom" > 
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                             <select name="projectId" lay-verify="" lay-search="">
                             	<option value="">-请选择-</option>
								<c:forEach var="project" items="${projectList}">
                                 	<option value="${project.pid }">${project.project_name }</option>
                                </c:forEach>
                             </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>出资主体：</span></div>
                        <div class="search-text layui-form">
                            <select name="stockholderId" lay-verify="" lay-search="">
                                <option value="">-请选择-</option>
                                <c:forEach var="stockholder" items="${stockholderList}">
                                 	<option value="${stockholder.corp_id }">${stockholder.name }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="search-box_div search_box_div_left" style="width: 500px">
                        <button type="button"  class="search-btn search-btn-chaxu" onclick="searchList('#tableSituationTotalList')">
                            <img src="<%=path %>/common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="reset" class="search-btn search-btn-cz">
                            <img src="<%=path %>/common/img/chognzhi-reach.png">
                            <span>重置</span>
                        </button>
                        <a href="javascript:void(document.searchFrom.submit());" class="main_btn" style="margin-left: 20px">
	                        <img src="<%=path %>/common/img/export.png">
	                        <span>导出</span>
	                    </a>
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
                    <span>项目情况(总表)</span>
                </div>
                <div class="tabled_one">
                    <!--表格-->
                    <table id="tableSituationTotalList"></table>
                    <!--底部翻页按钮栏 -->
		         	<div id="pagerSituationTotalList"></div>

                </div>
            </div>
        </div>

    </div>
<script type="text/javascript" src="<%=path %>/pages/finance/js/project_situation_total.js"></script>
</body>
</html>