<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>回款列表</title>
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
                        <div class="search-span"><span>回款账号：</span></div>
                        <div class="search-text"><input type="text"></div>
                    </div>
                    <div class="search-box_div">
                        <div class="search-span"><span>回款时间：</span></div>
                        <div class="search-text">
                            <ul class="search-text_inline">
                                <li>
                                    <input type="text" class="layui-input" id="pay_time1" >
                                </li>
                                <li>
                                    <img src="../img/pay_time.png">
                                </li>
                                <li>
                                    <input type="text" class="layui-input" id="pay_time2" >
                                </li>
                            </ul>
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
                    <span>回款列表</span>
                    <span class="title-head_red">深圳市汇联资管二十三号投资管理合伙企业</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="top_btn">
                    <a href="javascript:void(0)" class="main_btn" id="Share_Return_Detailed1">
                        <img src="../img/looking.png">
                        <span>查看回款明细</span>
                    </a>
                </div>
                <div class="title_explain">
                    <ul>
                        <li>
                            <span>回款本金合计：</span>
                            <span  class="title_explain_red">20000.00万元</span>
                        </li>
                        <li>
                            <span>回款利润合计：</span>
                            <span class="title_explain_red">4000.00万元</span>
                        </li>
                    </ul>
                </div>
                <div class="tabled_one">
                    <div class="tabled_two">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" class="hook_all" >选择</th>
                                <th>回款时间</th><th>回款账号</th><th>回款本金(万元)</th><th>回款利润(万元)</th><th>项目名称</th><th>付款公司</th><th>付款账号</th><th>回款备注</th><th>回款录入人</th><th>回款录入时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="hook_inp"></td>
                                <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>15000.00</td><td>5000.00</td><td><a href="project_manage_see.html" class="table_bnt">南山项目</a></td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>123456中国银行深圳高新支行</td><td>回款备注</td><td>admin</td><td>2018-07-13 10:10:10</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" class="hook_inp"></td>
                                <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>15000.00</td><td>5000.00</td><td><a href="project_manage_see.html" class="table_bnt">南山项目</a></td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>123456中国银行深圳高新支行</td><td>回款备注</td><td>admin</td><td>2018-07-13 10:10:10</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" class="hook_inp"></td>
                                <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>15000.00</td><td>5000.00</td><td><a href="project_manage_see.html" class="table_bnt">南山项目</a></td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>123456中国银行深圳高新支行</td><td>--</td><td>admin</td><td>2018-07-13 10:10:10</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" class="hook_inp"></td>
                                <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>15000.00</td><td>5000.00</td><td><a href="project_manage_see.html" class="table_bnt">南山项目</a></td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>123456中国银行深圳高新支行</td><td>--</td><td>admin</td><td>2018-07-13 10:10:10</td>

                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

    </div>

    <!--查看回款明细 -弹窗-开始-->
    <div id="Share_Return_Detailed" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">南山项目</option>
                                <option value="02">福田项目</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">深圳市融鑫资产管理有限公司</option>
                                <option value="02">南山物业投资有限公司</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款公司：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01" selected>深圳市汇联资管二十三号投资管理合伙企业</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">中国银行深圳高新支行</option>
                                <option value="02" selected>招商银行深圳南山支行</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">中国银行</option>
                                <option value="02" selected>农业银行深圳高新支行</option>
                                <option value="03">招商银行</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">2654561</option>
                                <option value="02" selected>2656999</option>
                                <option value="03">2636556</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">256456185</option>
                                <option value="02" selected>05648561564</option>
                                <option value="03">941564511</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款本金(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="10000.00" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款状态：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="01">已完结</option>
                                <option value="02" selected>未完结</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>回款利润(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="0" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款时间：</label>
                    <div class="fillin">
                        <input type="text" value="2018-07-06" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">回款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks">回款备注</textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款录入人：</label>
                    <div class="fillin">
                        <input type="text" disabled="disabled" value="admin" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">回款录入时间：</label>
                    <div class="fillin">
                        <input type="text" value="2018-07-13 10:10:10" disabled="disabled" class="fillin-input notoptional">
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
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>文件名</th><th>上传时间</th><th>上传人</th><th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>付款截图1.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                    </tr>
                    <tr>
                        <td>付款截图2.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--查看回款明细-弹窗-结束-->

</body>
</html>