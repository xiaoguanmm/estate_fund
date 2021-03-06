<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../../../common/common.jsp"%>
<title><c:if test="${investType eq 1 }">投资主体</c:if><c:if test="${investType eq 2 }">投资人</c:if>回报汇总</title>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <form action="" method="post" id="searchFrom" name="searchFrom" >
                <input type="hidden" name="investType" value="${investType }">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text layui-form">
                             <select name="projectId" lay-verify="" lay-search="">
                             	<option value="">-请选择-</option>
								<c:forEach var="project" items="${projectList}">
                                 	<option value="${project.pid }" <c:if test="${project.pid eq projectId }">selected="selected"</c:if>>${project.project_name }</option>
                                </c:forEach>
                             </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>付款公司：</span></div>
                        <div class="search-text layui-form">
	                        <select name="contributiveId" lay-verify="" lay-search="">
	                        	<option value="">-请选择-</option>
	                        	<c:if test="${investType eq 1 }">
		                           	<c:forEach var="stockholder" items="${stockholderList}">
		                            	<option value="${stockholder.corp_id }" >${stockholder.name }</option>
		                           	</c:forEach>
	                        	</c:if>
	                        	<c:if test="${investType eq 2 }">
		                        	<c:forEach var="investSub" items="${investSubCompanyList}">
		                            	<option value="${investSub.invest_subject_id }">${investSub.invest_name }</option>
		                           	</c:forEach>
	                        	</c:if>
	                        </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款公司：</span></div>
                        <div class="search-text layui-form">
	                        <select name="receiverId" lay-verify="" lay-search="">
	                        	<option value="">-请选择-</option>
	                        	<c:if test="${investType eq 1 }">
	                        		<c:forEach var="investSub" items="${investSubCompanyList}">
		                            	<option value="${investSub.invest_subject_id }" <c:if test="${investSub.invest_subject_id eq receiverId }">selected="selected"</c:if>>${investSub.invest_name }</option>
		                           	</c:forEach>
	                        	</c:if>
	                        	<c:if test="${investType eq 2 }">
	                        		<c:forEach var="investor" items="${investorCompanyList}">
		                            	<option value="${investor.invest_subject_id }" <c:if test="${investor.invest_subject_id eq receiverId }">selected="selected"</c:if>>${investor.invest_name }</option>
		                           	</c:forEach>
	                        	</c:if>
	                        </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款账号：</span></div>
                        <div class="search-text"><input type="text" name="receiverAccount"></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>财务确认状态：</span></div>
                        <div class="search-text layui-form">
                             <select name="receivedStatus" lay-verify="" lay-search="">
                             	<option value="">-请选择-</option>
                                <option value="3">未回款</option>
                                <option value="1">已完结</option>
                                <option value="2">未完结</option>
                             </select>
                        </div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" class="layui-input" id="receiverStartDate" name="receiverStartDate" readonly="readonly">
                                </li>
                                <li>
                                    <img src="<%=path %>/common/img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" class="layui-input" id="receiverEndDate" name="receiverEndDate" readonly="readonly">
                                </li>
                            </ul>
                        </div>
                    </div>

                    <div class="search-box_div search_box_div_left">
                        <button type="button"  class="search-btn search-btn-chaxu" onclick="searchList('#tableInvestInOutRepayList')">
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
                    <span><c:if test="${investType eq 1 }">投资主体</c:if><c:if test="${investType eq 2 }">投资人</c:if>回报汇总</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0)" class="main_btn" onclick="getRepayDetail()">
                        <img src="<%=path %>/common/img/looking.png">
                        <span>查看回款明细</span>
                    </a>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>回款总金额：</span>
                            <span  class="title_explain_red" id="totalAmount"></span>
                        </li>
                    </ul>
                </div>
                <div class="tabled_one">
                    <!--表格-->
                    <table id="tableInvestInOutRepayList"></table>
                    <!--底部翻页按钮栏 -->
		         	<div id="pagerInvestInOutRepayList"></div>

                </div>
            </div>
        </div>

    </div>

    <!--查看回款明细 -弹窗-开始-->
    <div id="Item_Inoutput_Received" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin-inputlay">
						<input type="text" value="" class="fillin-input" readonly="readonly" id="projectName">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin-inputlay">
						<input type="text" value="" class="fillin-input" readonly="readonly" id="receiverName">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款公司：</label>
                    <div class="fillin-inputlay">
						<input type="text" value="" class="fillin-input" readonly="readonly" id="contributiveName">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin-inputlay">
						<input type="text" value="" class="fillin-input" readonly="readonly" id="receiverBankName">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input" readonly="readonly" id="payBankName">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账号：</label>
                    <div class="fillin-inputlay">
						<input type="text" value="" class="fillin-input" readonly="readonly" id="receiverAccount">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账号：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input" readonly="readonly" id="payAccount">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款本金(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input" readonly="readonly" id="receiverAmount">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款时间：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input" readonly="readonly" id="receiverDate">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款利润(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input" readonly="readonly" id="profit">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款状态：</label>
                    <div class="fillin-inputlay">
						<input type="text" value="" class="fillin-input" readonly="readonly" id="receivedStatus">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" readonly="readonly" id="reveiverRemark"></textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款录入人：</label>
                    <div class="fillin">
                        <input type="text" value="" disabled="disabled" class="fillin-input notoptional" id="createName">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款录入时间：</label>
                    <div class="fillin">
                        <input type="text" value="" disabled="disabled" class="fillin-input notoptional" id="createDate">
                    </div>
                </div>
            </div>

        </div>
        <div class="main_edge">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            <!--表格-->
            <div class="tabled_datum_940">
	            <div class="tabled_one">
	                <!--表格-->
	                <table id="tableInvestInOutRepayDetailList"></table>
	                <!--底部翻页按钮栏 -->
	       			<div id="pagerInvestInOutRepayDetailList"></div>
	            </div>
            </div>
        </div>
    </div>
    <!--查看回款明细-弹窗-结束-->
    <script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>
	<script type="text/javascript" src="<%=path %>/pages/count/js/invest_inoutput_repay.js"></script>
</body>
</html>