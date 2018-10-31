<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资主体回款信息列表</title>
     <%@ include file="../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}pages/trade_manage/js/trade_manage.js"></script>


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
                        <div class="search-span"><span>付款公司：</span></div>
                        <div class="search-text"><input type="text"></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>收款公司：</span></div>
                        <div class="search-text"><input type="text"></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款状态：</span></div>
                        <div class="search-text">
                            <form class="layui-form" action="">
                                <select name="breed" lay-verify="" lay-search="">
                                    <option value="">-请选择-</option>
                                    <option value="01">未回款</option>
                                    <option value="02">未完结</option>
                                    <option value="03">已完结</option>
                                </select>
                            </form>
                        </div>
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
                    <span>投资主体回款信息列表</span>
                </div>
                <div class="top_btn">
                    <a href="subject_return_paytnfo.html" class="main_btn">
                        <img src="../img/looking.png">
                        <span>查看回款记录</span>
                    </a>
                </div>
                <div class="tabled_one">

                    <!--表格-->
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th><input type="checkbox" class="hook_all" id="checkAll">选择</th>
                                    <th>项目名称</th><th>付款公司</th><th>收款公司</th><th>投入本金(万元)</th><th>应回利润(万元)</th><th>已回款本金</th><th>已回款利润</th><th>回款状态</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td>南山项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>深圳市融鑫资产管理有限公司</td><td>30000.00</td><td>1000.00</td><td>5000.00</td><td>1000.00</td><td>未完结</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td>福田项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>深圳市融鑫资产管理有限公司</td><td>30000.00</td><td>1000.00</td><td>5000.00</td><td>1000.00</td><td>未回款</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td>龙华项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>深圳市融鑫资产管理有限公司</td><td>30000.00</td><td>1000.00</td><td>5000.00</td><td>1000.00</td><td>已完结</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="hook_inp"></td>
                                    <td>龙岗项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>深圳市融鑫资产管理有限公司</td><td>30000.00</td><td>1000.00</td><td>5000.00</td><td>1000.00</td><td>未完结</td>
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