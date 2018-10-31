<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>股权历史变更记录</title>
    <%@ include file="../../../common/common.jsp"%>
    <%@ include file="stock_sure.jsp"%>
    <script type="text/javascript" src="${basePath}pages/trade/js/trade_manage.js"></script>
    <script type="text/javascript" src="${basePath}pages/trade/js/proj_company_manage/stock_change_his.js"></script>

</head>
<body>
    <div class="bg-gray">
    	<input type="hidden" id="stockholderPid" name="stockholderPid" value="${stockholderPid }" />
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <span class="title-head_red">${stockholderName }</span>
                    <img src="${basePath}common/img/title-head.png">
                    <!-- <span class="title-head_red"></span> -->
                    <span>股权历史变更记录</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0)" class="main_btn" id="detail_stock1">
                        <img src="${basePath}common/img/looking.png">
                        <span>查看股权变更明细</span>
                    </a>
                    <a href="${basePath}/tradeManage/toProjCompanyAddPage?tabType=3&businessPrjPid=${businessPrjInfoId}&operation=${operation}" class="btn_cancel btn_all">
                        <img src="${basePath}common/img/return.png">
                        <span>返回</span>
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


    <!--点击表格中的‘查看’ -弹窗-开始-->
    <!-- <div id="see_partner_stock1" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">注册资本(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline" style="vertical-align: top;">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" value="" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" style="width: 305px;">代持</textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="main_edge">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            表格
            <div class="tabled_datum_940">
                <table class="table table-bordered table-hover">
                </table>
            </div>
        </div>
    </div> -->
    <!--点击表格中的‘查看’-弹窗-结束-->

</body>
</html>