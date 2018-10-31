<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>项目公司管理</title>
    <%@ include file="../../../common/common.jsp"%>
     
    <script type="text/javascript" src="${basePath}pages/trade/js/trade_manage.js"></script>
	<script type="text/javascript" src="${basePath}pages/trade/js/proj_company_manage/proj_company_manage_index.js"></script>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目公司：</span></div>
                        <div class="search-text">
                        	<select id="businessPrjInfoPid" name="businessPrjInfoPid" lay-verify="" lay-search="" class="form-control">
                        		<option value="">--请选择--</option>
	                        	<fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE }"></fund:enterprise>
	                        </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>组织机构代码：</span></div>
                        <div class="search-text"><input type="text" id="orgCodeCert" name="orgCodeCert" ></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>营业执照号码：</span></div>
                        <div class="search-text"><input type="text" id="businessLicenceCode" name="businessLicenceCode" ></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                            <select id="projectInfoPid" name="projectInfoPid" lay-verify="" lay-search>
                            	<option value="">--请选择--</option>
                               	<fund:options code="${Globals.PROJECT_NAME }"/>
                            </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>法人代表：</span></div>
                        <div class="search-text"><input type="text" id="legalRepresentative" name="legalRepresentative" ></div>
                    </div>

                    <div class="search-box_div search_box_div_left">
                        <button type="button"  class="search-btn search-btn-chaxu">
                            <img src="${basePath}common/img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="button" id="projCompanyQueryReset" class="search-btn search-btn-cz">
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
                    <span>项目公司管理</span>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0);" onclick="addProjCompany();" class="main_btn">
                        <img src="${basePath}common/img/add.png">
                        <span>新增项目公司</span>
                    </a>
                    <!-- <a href="jectfirm_manage_modify.html" id="modifyProjCompany" class="main_btn"> -->
                    <a href="javascript:void(0);" id="modifyProjCompany" class="main_btn">
                        <img src="${basePath}common/img/chance.png">
                        <span>修改项目公司</span>
                    </a>
                    <a href="javascript:void(0);" id="linkToProject" class="main_btn">
                        <img src="${basePath}common/img/relation.png">
                        <span>关联项目</span>
                    </a>
                    <!-- <a href="jectfirm_manage_see.html" id="viewProjCompany" class="main_btn"> -->
                    <a href="javascript:void(0);" id="viewProjCompany" class="main_btn">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看项目公司</span>
                    </a>
                    <a href="javascript:void(0)" id="delProjCompany" class="main_btn main_btn_dele">
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