<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>系统操作日志</title>
<%@ include file="../../../common/common.jsp"%>
<link href="<%=path %>/common/css/product.css" rel="stylesheet">

</head>
<body>
    <div class="bg-gray">
        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <form action="" method="post" id="searchFrom" name="searchFrom" > 
                <div class="search-box_div">
                    <div class="search-span"><span>操作用户：</span></div>
                    <div class="search-text"><input type="text" name="operatorName" maxlength="25"></div>
                    <div class="search-span"><span>模块名称：</span></div>
                    <div class="search-text"><input type="text" name="moduleName" maxlength="25"></div>
                    <div class="search-span"><span>操作内容：</span></div>
                    <div class="search-text"><input type="text" name="remark" maxlength="25"></div>
                    <div class="search-span"><span>操作时间：</span></div>
                    <div class="search-text">
                    	<input type="text" id="beginDate" name="beginDate" maxlength="25" readonly="readonly">
                    		&nbsp;&nbsp;至&nbsp;&nbsp; 
                    	<input type="text" id="endDate" name="endDate" maxlength="25" readonly="readonly">
                    </div>
                </div>
                <div class="search-box_div">
                    <button type="button"  class="search-btn search-btn-chaxu" style="margin-left:24px;" onclick="searchList('#tableLogList')">
                        <img src="<%=path %>/common/img/search-btn.png">
                        <span>查询</span>
                    </button>
                    <button type="reset" class="search-btn search-btn-cz">
                        <img src="<%=path %>/common/img/chognzhi-reach.png">
                        <span>重置</span>
                    </button>
                </div>
                </form>
            </div>
        </div>

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="<%=path %>/common/img/title-head.png">
                    <span>系统操作日志</span>
                </div>
                <div class="tabled-one">
		         	<table id="tableLogList"></table>
		         	<div id="pagerLogList"></div>
                </div>
        	</div>
        </div>
   </div>
   
   <script type="text/javascript" src="<%=path %>/pages/system/js/logInfo.js"></script>
</body>
</html>