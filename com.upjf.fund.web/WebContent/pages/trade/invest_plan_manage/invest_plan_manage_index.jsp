<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>资管计划管理</title>
     <%@ include file="../../../common/common.jsp"%>
<%--      <script type="text/javascript" src="${basePath}pages/trade/js/trade_manage.js"></script> --%>
     <script type="text/javascript" src="${basePath}pages/trade/js/invest_plan_manage/invest_plan_manage_index.js"></script>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text">
							<select id="projectName" name="projectInfoPid" lay-verify="" lay-search="" class="fillin-input form-control" validate-rule="notEmpty">
                            	<option value="">--请选择--</option>
                               	<fund:options code="${Globals.PROJECT_NAME }"/>
                            </select>
						</div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>项目公司：</span></div>
                        <div class="search-text">
                        	<select id="PrjCompanyName" name="businessPrjInfoPid" lay-verify="" lay-search="" class="fillin-input form-control" validate-rule="notEmpty">
                        		<option value="">--请选择--</option>
	                        	<fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE }"></fund:enterprise>
	                        </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>股东：</span></div>
                        <div class="search-text">
                       		<select id="stockholderName" name="corporationInfoPid" lay-filter="stockholderName" class="fillin-input form-control" lay-verify="" lay-search>
	                            <option value="">--请选择--</option>
	                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
	                        </select>
                       	</div>
                    </div>

                    <div class="search-box_div search_box_div_left">
                        <button type="button"  class="search-btn search-btn-chaxu">
                            <img src="${basePath}common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="button" class="search-btn search-btn-cz" id="investPlanQueryReset">
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
                    <span>资管计划管理</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0);" id="addInvestPlanManage" class="main_btn">
                        <img src="${basePath}common/img/add.png">
                        <span>新增资管计划</span>
                    </a>
                    <a href="javascript:void(0);" id="modifyInvestPlanManage" class="main_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改资管计划</span>
                    </a>
                    <a href="javascript:void(0);" id="delInvestPlanManage" class="main_btn main_btn_dele">
                        <img src="${basePath}common/img/delete.png">
                        <span>删除</span>
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