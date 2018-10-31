<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资主体业务管理</title>
    <%@ include file="../../../common/common.jsp"%>
	
</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="fillin-inputlay layui-form">
	                        <div class="fillin">
                                <input type="text" id="projectName" name="projectName" class="fillin-input">
                            </div>
	                    </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>投资主体名称：</span></div>
                        <div class="search-text">
							<select id="investSubjectCorpId" name="investSubjectCorpId" lay-filter="" class="fillin-input form-control" lay-verify="" lay-search>
	                            <option value="">--请选择--</option>
	                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
	                        </select>
						</div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>出资类别：</span></div>
                        <div class="fillin-inputlay layui-form form-group">
	                        <select id="contributiveType" name="contributiveType" class="form-control" lay-verify="" lay-search="" lay-filter="">
	                        	<option value="">--请选择--</option>
	                         	<fund:options code="${Globals.CONTRIBUTIVE_TYPE}" value="${InvestSubject.contributiveType}"/>
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
                    <span>投资主体业务管理</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0);" class="main_btn" id="viewInvestSubjectDetail">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看详情</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover" id="data_list"></table>
                        <div id="page_list"></div> 
                    </div>

                </div>
            </div>
        </div>

    </div>

</body>
</html>
<script type="text/javascript" src="${basePath}pages/trade/js/invest_subject_business_manage/invest_subject_business.js"></script>