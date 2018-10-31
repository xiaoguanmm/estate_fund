<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改资管计划</title>
     <%@ include file="../../../common/common.jsp"%>
    <script type="text/javascript" src="${basePath}pages/trade_manage/js/trade_manage.js"></script>

</head>
<body>
    <div class="bg-gray">

<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="../img/title-head.png">
                    <span >修改资管计划</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                    <div class="myTab_content_one">

                        <div class="title_bggray">
                            <span>资管计划基础信息</span>
                        </div>
                        <div class="tab-pane" id="schedule">
                            <div class="main_edge">
                                <div class="fillbox-left">
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目名称：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" >
                                                        <option value="01">南山项目</option>
                                                        <option value="02">福田项目</option>
                                                        <option value="03">宝安项目</option>
                                                        <option value="04">龙岗项目</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目公司：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" >
                                                        <option value="01">南山物业投资有限公司</option>
                                                        <option value="02">福田物业投资有限公司</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">股东：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" >
                                                        <option value="01">深圳市汇联资管二十三号投资管理合伙企业</option>
                                                        <option value="02">深圳市碧桂园房地产开发有限公司</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">投资起始日：</label>
                                            <div class="fillin">
                                                <input type="text" value="2018-12-11" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">投资方式：</label>
                                            <div class="fillin">
                                                <input type="text" value="" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">期限(月)：</label>
                                            <div class="fillin">
                                                <input type="text" value="21" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计出资规模（万元）：</label>
                                            <div class="fillin">
                                                <input type="text" value="652000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预计总回款（万元）：</label>
                                            <div class="fillin">
                                                <input type="text" value="880000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际出资金额(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="752000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">已收本金：</label>
                                            <div class="fillin">
                                                <input type="text" value="100000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">已收回款：</label>
                                            <div class="fillin">
                                                <input type="text" value="1001000.00" class="fillin-input">
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
                                                <input type="text" value="18645789540" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn_edge btn_edge_left">
                                <a href="project_manage.html" class="btn_cancel btn_all">
                                    <img src="../img/cancel.png">
                                    <span>取消</span>
                                </a>
                                <button type="button" class="btn_keep btn_all">
                                    <img src="../img/keep.png">
                                    <span>保存</span>
                                </button>
                            </div>


                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>投资主体管理</span>
                                </div>
                                <div class="top_btn top_btn_40">
                                    <a href="javascript:void(0)" class="main_btn" id="add_Investment1">
                                        <img src="../img/add.png">
                                        <span>新增投资主体</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn" id="modify_Investment1">
                                        <img src="../img/chance.png">
                                        <span>修改投资主体</span>
                                    </a>
                                    <a href="management_plan_paytnfo.html" class="main_btn">
                                        <img src="../img/looking.png">
                                        <span>查看付款信息</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn">
                                        <img src="../img/informa.png">
                                        <span>投资人信息管理</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn main_btn_dele">
                                        <img src="../img/delete.png">
                                        <span>删除</span>
                                    </a>
                                </div>
                                <!--表格-->
                                <div class="tabled_two tabled_margin_20">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" class="hook_all" id="checkAll">选择</th>
                                            <th>股东类别</th><th>企业名称</th><th>是否为汇联上市公司</th><th>预计出资额(万元)</th><th>持股比例(%)</th><th>实际出资(万元)</th><th>出资期数(月)</th><th>分红方式</th><th>年化利率(%)</th><th>预计总回款(万元)</th><th>已收本金(万元)</th><th>已收回报(万元)</th><th>投资人</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>GP</td><td>深圳市汇联基金管理有限公司</td><td>否</td><td>--</td><td>0</td><td>--</td><td>21</td><td>管理费</td><td>2</td><td> 5,000.00 </td><td> 5,000.00 </td><td>--</td><td><a href="" class="table_bnt">1</a></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>优先级LP</td><td>汇联投资服务（深圳）有限公司</td><td>是</td><td>5000.00</td><td>12.5</td><td>15000.00</td><td>21</td><td>固定年化</td><td>12</td><td> 15000.00 </td><td> 15000.00 </td><td>--</td><td><a href="" class="table_bnt">4</a></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>中间级LP</td><td>深圳市融鑫资产管理有限公司</td><td>否</td><td>35000.00</td><td>86.5</td><td>35000.00</td><td>21</td><td>剩余利润</td><td>--</td><td> 15000.00 </td><td> 25000.00 </td><td>10000.00</td><td><a href="" class="table_bnt">3</a></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>财务顾问</td><td>前海汇联金融服务（深圳）有限公司</td><td>是</td><td>--</td><td>--</td><td>--</td><td>21</td><td>顾问费</td><td>8</td><td> 15000.00 </td><td> 25000.00 </td><td>10000.00</td><td><a href="" class="table_bnt">6</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                            </div>

                        </div>
<!------------------------变更项目进度-结束--------------------------------------------------------------->


                    </div>
            </div>
        </div>
    </div>

    <!--新增/修改投资主体 -弹窗-开始-->
    <div id="add_Investment" class="add_Makeuup">
        <div>
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
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">项目公司：</label>
                    <div class="fillin">
                        <input type="text" value="南山物业投资有限公司" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">预计出资额(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="60" class="fillin-input">
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
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" value="53" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline" >
                    <label class="user-name product-modify-span">是否为汇联上市公司：</label>
                    <div class="fillin layui_input_block layui-form">
                        <input type="radio" name="huilian" value="yes" title="是" checked>
                        <input type="radio" name="huilian" value="no" title="否" >
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
                    <label class="user-name product-modify-span">预计总回款(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="60000.00" class="fillin-input">
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
                    <label class="user-name product-modify-span">预期收益(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="20000.00" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">年化利率(%)：</label>
                    <div class="fillin">
                        <input type="text" value="12" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">预期收益率(%)：</label>
                    <div class="fillin">
                        <input type="text" value="50" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">投资起始日：</label>
                    <div class="fillin">
                        <input type="text" value="2018-7-13" class="fillin-input">
                    </div>
                </div>
            </div>
            <div class="fillin_bntleft fillbox_inline_0">
                <div class="fillin">
                    <!--<input type="text" id="textName" style="height: 32px;border:1px solid #ddd;border-radius: 4px;width: 215px;">-->
                    <div class="report-file_upon">
                        <img src="../img/uploaded.png">
                        <span>上传文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value">
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
                        <td>合作协议1.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                    </tr>
                    <tr>
                        <td>合作协议2.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--新增/修改投资主体-弹窗-结束-->




</body>
</html>