<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>投资人详情页</title>
     <%@ include file="../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}pages/finance_manage/js/finance_manage.js"></script>

</head>
<body>
    <div class="bg-gray">

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="../img/title-head.png">
                    <span >投资人详情页</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#busine_Informaed">投资基础信息</a></li>
                        <li><a href="#busine_enterprise">企业基本信息</a></li>
                        <li><a href="#busine_Bank">银行账户管理</a></li>
                        <li><a href="#busine_payments">付款信息</a></li>
                        <li><a href="#busine_received">回款信息</a></li>
                    </ul>
                    <div class="tab-content myTab_content">

<!-------------------------投资基础信息-开始-------------------------------------------------------------->
                        <div class="tab-pane active" id="busine_Informaed">
                            <div class="main_edge">
                                <div class="fillbox-left">
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="南山项目" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">楼盘名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="南山项目" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">股东：</label>
                                            <div class="fillin">
                                                <input type="text" value="深圳市汇联资管二十三号投资管理合伙企业" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目公司：</label>
                                            <div class="fillin">
                                                <input type="text" value="南山物业投资有限公司" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">出资类别：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" lay-search>
                                                        <option value="01">GP</option>
                                                        <option value="02">优先级LP</option>
                                                        <option value="03">中间级LP</option>
                                                        <option value="04">其他(LP)</option>
                                                        <option value="05">财务顾问</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">投资主体名称：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" lay-search>
                                                        <option value="">-请选择-</option>
                                                        <option value="01">深圳市汇联基金管理有限公司</option>
                                                        <option value="02" selected>汇联投资服务（深圳）有限公司</option>
                                                        <option value="03">深圳市融鑫资产管理有限公司</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                        <label class="user-name product-modify-span">预计出资额(万元)：</label>
                                        <div class="fillin">
                                            <input type="text" value="40000.00" class="fillin-input">
                                        </div>
                                    </div>
                                        <div class="fillbox_inline" >
                                            <label class="user-name product-modify-span">级别：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" lay-search>
                                                        <option value="01">优先</option>
                                                        <option value="02">劣后</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">持股比例(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="53" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">出资期数(月)：</label>
                                            <div class="fillin">
                                                <input type="text" value="21" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">分红方式：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" lay-search>
                                                        <option value="01">管理费</option>
                                                        <option value="02">固定年化</option>
                                                        <option value="03">剩余利润</option>
                                                        <option value="04">顾问费</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">年化利率(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="13" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计总回款(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="60000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">投资起始日：</label>
                                            <div class="fillin">
                                                <input type="text" value="2017-7-13" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期收益(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="20000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期收益率(%)：</label>
                                            <div class="fillin">
                                                <input type="text" value="50" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际收益(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="20000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际出资额(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="--" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际收益率：</label>
                                            <div class="fillin">
                                                <input type="text" value="--" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>附件材料</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>文件名</th><th>上传时间</th><th>上传人</th><th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>合作协议1.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                                        </tr>
                                        <tr>
                                            <td>合作协议2.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="btn_edge btn_edge_left">
                                <a href="subject_finance.html" class="btn_cancel btn_all">
                                    <img src="../img/return.png">
                                    <span>返回</span>
                                </a>
                            </div>

                        </div>
<!------------------------投资基础信息-结束--------------------------------------------------------------->

<!-------------------------企业基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_enterprise">
                            <div class="main_edge">
                                <div class="fillbox-left" >
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>企业名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">企业简称：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">组织机构代码：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">营业执照号码：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">所有制性质：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">法人代表：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">注册资金万元：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">客户经理：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">联系电话：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>公司资料扫描件</span>
                                </div>
                                <div class="datum">
                                    <div class="datum_main">
                                        <span>营业执照副本</span>
                                        <input type="button" value="预览" class="datum_btn datum_btnactive datum_imges">
                                    </div>
                                    <div class="datum_img">
                                        <img src="../img/datum.png" layer-src="../img/datum.png" layer-pid alt="营业执照副本" layer-index="0">
                                    </div>
                                </div>
                                <div class="datum">
                                    <div class="datum_main">
                                        <span>组织机构代码证</span>
                                        <input type="button" value="预览" class="datum_btn datum_btnactive datum_imges">
                                    </div>
                                    <div class="datum_img">
                                        <img layer-pid src="../img/datum.png" layer-src="../img/datum.png"  alt="组织机构代码证" layer-index="1">
                                    </div>
                                </div>
                                <div class="datum">
                                    <div class="datum_main">
                                        <span>机构代码信用证</span>
                                        <input type="button" value="预览" class="datum_btn datum_btnactive  datum_imges">
                                    </div>
                                    <div class="datum_img">
                                        <img src="../img/datum.png" layer-pid src="../img/datum.png" layer-src="../img/datum.png"  alt="机构代码信用证" layer-index="1">
                                    </div>
                                </div>
                                <div class="datum">
                                    <div class="datum_main">
                                        <span>法人证件头像页</span>
                                        <input type="button" value="预览" class="datum_btn datum_btnactive datum_imges">
                                    </div>
                                    <div class="datum_img">
                                        <img src="../img/datum.png" layer-pid src="../img/datum.png" layer-src="../img/datum.png"  alt="法人证件头像页" layer-index="1">
                                    </div>
                                </div>
                                <div class="datum">
                                    <div class="datum_main">
                                        <span>法人证件国徽页</span>
                                        <input type="button" value="预览" class="datum_btn datum_btnactive datum_imges">
                                    </div>
                                    <div class="datum_img">
                                        <img src="../img/datum.png" layer-pid src="../img/datum.png" layer-src="../img/datum.png"  alt="法人证件国徽页" layer-index="1">
                                    </div>
                                </div>
                            </div>
                            <div class="btn_edge btn_edge_left">
                                <a href="subject_finance.html" class="btn_cancel btn_all">
                                    <img src="../img/return.png">
                                    <span>返回</span>
                                </a>
                            </div>


                        </div>
<!-------------------------企业基本信息-开始-------------------------------------------------------------->

<!-------------------------银行账户管理-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_Bank">
                            <div class="main_edge">
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>企业名称</th><th>开户名</th><th>开户行</th><th>开户账号</th><th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>深圳市汇联资管二十三号投资管理合伙企业</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>中国银行深圳分行</td><td>12359312</td><td><a href="javascript:void(0)" class="table_bnt">查看</a></td>
                                        </tr>
                                        <tr>
                                            <td>深圳市汇联资管二十三号投资管理合伙企业</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>中国银行深圳分行</td><td>12359312</td><td><a href="javascript:void(0)" class="table_bnt">查看</a></td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="btn_edge btn_edge_left">
                                    <a href="subject_finance.html" class="btn_cancel btn_all">
                                        <img src="../img/return.png">
                                        <span>返回</span>
                                    </a>
                                </div>
                            </div>

                        </div>
<!------------------------银行账户管理-结束--------------------------------------------------------------->

<!-------------------------付款信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_payments">
                            <div class="top_btn top_btn_40">
                                <a href="javascript:void(0)" class="main_btn" id="Investor_Finance_Pay1">
                                    <img src="../img/looking.png">
                                    <span>查看付款明细</span>
                                </a>
                            </div>
                            <div class="title_explain">
                                <ul>
                                    <li>
                                        <span>应付总金额：</span>
                                        <span  class="title_explain_red">50000.00万元</span>
                                    </li>
                                    <li>
                                        <span>实付总金额：</span>
                                        <span class="title_explain_red">10000.00万元</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="tabled_one">

                                <!--表格-->
                                <div class="tabled_two">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" class="hook_all" id="checkAll">选择</th>
                                            <th>付款时间</th><th>出资主体</th><th>付款账号</th><th>付款期数</th><th>付款金额(万元)</th><th>收款公司</th><th>收款账号</th><th>录入时间</th><th>财务录入操作者</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>深圳市融鑫资产管理有限公司</td><td>2221中国银行</td><td>1</td><td>5000.00</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>1111中国银行</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>深圳市融鑫资产管理有限公司</td><td>2221中国银行</td><td>1</td><td>5000.00</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>1111中国银行</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>深圳市融鑫资产管理有限公司</td><td>2221中国银行</td><td>1</td><td>5000.00</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>1111中国银行</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>深圳市融鑫资产管理有限公司</td><td>2221中国银行</td><td>1</td><td>5000.00</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>1111中国银行</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!-------------------------付款信息-开始-------------------------------------------------------------->

<!-------------------------回款信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="busine_received">
                            <div class="top_btn top_btn_40">
                                    <a href="javascript:void(0)" class="main_btn" id="Investor_Finance_reture1">
                                    <img src="../img/looking.png">
                                    <span>查看回款明细</span>
                                </a>
                            </div>
                            <div class="title_explain">
                                <ul>
                                    <li>
                                        <span>回款本金合计：</span>
                                        <span  class="title_explain_red">30000.00万元</span>
                                    </li>
                                    <li>
                                        <span>回款利润合计：</span>
                                        <span class="title_explain_red">10000.00万元</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="tabled_one">

                                <!--表格-->
                                <div class="tabled_two">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" class="hook_all" >选择</th>
                                            <th>回款时间</th><th>回款账号</th><th>项目名称</th><th>付款公司</th><th>回款本金(万元)</th><th>回款利润(万元)</th><th>回款备注</th><th>回款录入时间</th><th>回款录入人</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>南山项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>15000.00</td><td>5000.00</td><td>回款备注</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>南山项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>15000.00</td><td>5000.00</td><td>回款备注</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>南山项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>15000.00</td><td>5000.00</td><td>回款备注</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>2018-07-13</td><td>123566中国银行深圳宝安支行</td><td>南山项目</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>15000.00</td><td>5000.00</td><td>回款备注</td><td>2018-07-13 10:10:10</td><td>admin</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!-------------------------回款信息-开始-------------------------------------------------------------->

                    </div>
                </div>
            </div>
        </div>
    </div>


    <!--查看付款明细 -弹窗-开始-->
    <div id="Investor_Finance_Pay" class="add_Makeuup">
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
                                <option value="">-请选择-</option>
                                <option value="01">深圳市融鑫资产管理有限公司</option>
                                <option value="02" selected>深圳市汇联资管二十三号投资管理合伙企业</option>
                                <option value="03">南山物业投资有限公司</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资主体：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01" selected>深圳市融鑫有限公司</option>
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
                                <option value="02" selected>农业银行</option>
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
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
                    <div class="fillin">
                        <input type="text" value="1" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="360000.00" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款时间：</label>
                    <div class="fillin">
                        <input type="text" value="2018-07-13" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks">付款备注</textarea>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">财务录入操作者：</label>
                    <div class="fillin">
                        <input type="text" disabled="disabled" value="admin" class="fillin-input notoptional">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">录入时间：</label>
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
    <!--查看付款明细-弹窗-结束-->

    <!--查看回款明细 -弹窗-开始-->
    <div id="Investor_Finance_reture" class="add_Makeuup">
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

    <!--上传公司扫描件弹窗-开始-->
    <div id="Upload_file"  class="Upload_file">
        <label class="user-name" style="width:110px;">选择上传文件：</label>
        <div class="fillin">
            <input type="text" id="textName" style="height: 32px;border:1px solid #ddd;border-radius: 4px;width: 215px;" />
            <div class="report-file">
                <span>选择文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value">
            </div>
        </div>
    </div>
    <!--上传公司扫描件弹窗-结束-->

<!--控制头部tab切换-->
    <script>
        $('#myTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        })
    </script>



</body>
</html>