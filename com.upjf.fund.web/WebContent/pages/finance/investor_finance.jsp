<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人财务管理</title>
     <%@ include file="../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}pages/finance_manage/js/finance_manage.js"></script>

</head>
<body>
    <div class="bg-gray">

        <!--搜索-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="search-box">
                    <div class="search-box_div">
                        <div class="search-span"><span>项目名称：</span></div>
                        <div class="search-text"><input type="text"></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>投资主体名称：</span></div>
                        <div class="search-text"><input type="text"></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>投资人名称：</span></div>
                        <div class="search-text"><input type="text"></div>
                    </div>

                    <div class="search-box_div search_box_div_left">
                        <button type="button"  class="search-btn search-btn-chaxu">
                            <img src="../img/search-btn.png">
                            <span>查询</span>
                        </button>
                        <button type="button" class="search-btn search-btn-cz">
                            <img src="../img/chognzhi-reach.png">
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
                    <img src="../img/title-head.png">
                    <span>投资人财务管理</span>
                </div>
                <div class="top_btn">
                    <a href="investor_finance_see.html" class="main_btn">
                        <img src="../img/looking.png">
                        <span>查看详情</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" class="hook_all" id="checkAll">选择</th>
                                    <th>项目名称</th><th>楼盘名称</th><th>投资主体名称</th><th>投资人名称</th><th>预计出资额(万元)</th><th>持股比例(%)</th><th>实际出资(万元)</th><th>出资期数(月)</th><th>分红方式</th><th>年化利率(%)</th><th>预计总回款(万元)</th><th>已收本金(万元)</th><th>已收回报(万元)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td><a href="../trade_manage/project_manage_see.html" class="table_bnt">南山项目</a></td><td>清上华居</td><td>深圳市汇联基金管理有限公司</td><td>甲公司</td><td>--</td><td>0</td><td>--</td><td>21</td><td>管理费</td><td>2</td><td>5,000.00 </td><td>5,000.00 </td><th>--</th>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td><a href="../trade_manage/project_manage_see.html" class="table_bnt">福田项目</a></td><td>红树湾公馆</td><td>汇联投资服务（深圳）有限公司</td><td>XXX经营商户</td><td>5000.00</td><td>12.5</td><td>15000.00</td><td>21</td><td>固定年化</td><td>12</td><td>25000.00 </td><td>20000.00 </td><th>--</th>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td><a href="../trade_manage/project_manage_see.html" class="table_bnt">南山项目</a></td><td>清上华居</td><td>深圳市融鑫资产管理有限公司</td><td>X公司</td><td>35000.00</td><td>82.5</td><td>35000.00</td><td>21</td><td>剩余利润</td><td>--</td><td>35000.00 </td><td>26000.00 </td><th>10000.00</th>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td><a href="../trade_manage/project_manage_see.html" class="table_bnt">南山项目</a></td><td>清上华居</td><td>前海汇联金融服务（深圳）有限公司</td><td>张三</td><td>--</td><td>--</td><td>--</td><td>--</td><td>顾问费</td><td>8</td><td>15000.00 </td><td>--</td><th>10000.00</th>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <!--底部翻页按钮栏-->
                    <div id="pager_list" class="ui-jqgrid-pager" dir="ltr">
                        <div id="pg_pager_list" class="ui-pager-control" role="group">
                            <table class="ui-pg-table ui-common-table ui-pager-table">
                                <tbody>
                                <tr>
                                    <td id="pager_list_left" align="left">
                                       <table class="ui-pg-table navtable ui-common-table">
                                           <tbody>
                                           <tr>
                                               <td class="ui-pg-button" title="刷新表格" id="refresh_table_list">
                                                   <div class="ui-pg-div">
                                                       <span class="glyphicon glyphicon-refresh"></span>
                                                   </div>
                                               </td>
                                           </tr>
                                           </tbody>
                                       </table>
                                    </td>

                                    <td id="pager_list_center" align="center" style=" width: 373px;">
                                        <table class="ui-pg-table ui-common-table ui-paging-pager">
                                            <tbody>
                                            <tr>
                                                <td id="first_pager_list" class="ui-pg-button ui-disabled" title="First Page" style="cursor: default;">
                                                    <span class="glyphicon glyphicon-step-backward"></span>
                                                </td>
                                                <td id="prev_pager_list" class="ui-pg-button ui-disabled" title="Previous Page" style="cursor: default;">
                                                    <span class="glyphicon glyphicon-backward"></span>
                                                </td>
                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
                                                    <span class="ui-separator"></span>
                                                </td>
                                                <td id="input_pager_list" dir="ltr">
                                                    <input class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="0" role="textbox">共
                                                    <span id="sp_1_pager_list">1</span>页
                                                </td>
                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
                                                    <span class="ui-separator"></span>
                                                </td>
                                                <td id="next_pager_list" class="ui-pg-button ui-disabled" title="Next Page">
                                                    <span class="glyphicon glyphicon-forward"></span>
                                                </td>
                                                <td id="last_pager_list" class="ui-pg-button ui-disabled" title="Last Page">
                                                    <span class="glyphicon glyphicon-step-forward"></span>
                                                </td>
                                                <td dir="ltr">
                                                    <select class="ui-pg-selbox form-control" role="listbox" title="Records per Page">
                                                        <option role="option" value="10" selected="selected">10</option>
                                                        <option role="option" value="20" selected="selected">20</option>
                                                        <option role="option" value="30" selected="selected">30</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>

                                    <td id="pager_list_right" align="right">
                                        <div dir="ltr" style="text-align: right" class="ui-paging-info">1 - 1　共 1 条</div>
                                    </td>
                                 </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!--底部翻页按钮栏-完成-->

                </div>
            </div>
        </div>

    </div>

</body>
</html>