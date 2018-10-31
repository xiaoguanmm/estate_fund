<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改项目公司</title>
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
                    <span >修改项目公司</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#jectfirm_base_Informa">项目公司基本信息</a></li>
                        <li><a href="#jectfirm_bargain">项目公司合同附件</a></li>
                        <li><a href="#jectfirm_schedule">项目公司股东信息</a></li>
                    </ul>
                    <div class="tab-content myTab_content">

<!-------------------------项目公司基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane active" id="jectfirm_base_Informa">

                            <div class="main_edge">
                                <div class="fillbox-left" >
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>企业名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="南山物业投资有限公司" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">企业简称：</label>
                                            <input type="text" value="南山物业投资有限公司" class="fillin-input">
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">组织机构代码：</label>
                                            <div class="fillin">
                                                <input type="text" value="5645132" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">营业执照号码：</label>
                                            <div class="fillin">
                                                <input type="text" value="1564564" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">所有制性质：</label>
                                            <div class="fillin">
                                                <input type="text" value="私企" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">法人代表：</label>
                                            <div class="fillin">
                                                <input type="text" value="张三" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">注册资金(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">客户经理：</label>
                                            <div class="fillin">
                                                <input type="text" value="王五" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">联系电话：</label>
                                            <div class="fillin">
                                                <input type="text" value="15562626363" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="btn_edge btn_edge_left" >
                                <a href="jectfirm_manage.html" class="btn_cancel btn_all">
                                    <img src="../img/cancel.png">
                                    <span>取消</span>
                                </a>
                                <a href="javascript:void(0)" class="btn_keep btn_all">
                                    <img src="../img/keep.png">
                                    <span>保存</span>
                                </a>
                            </div>

                        </div>
<!---------------------------项目公司基本信息-结束------------------------------------------------------------>


<!-------------------------项目公司合同附件-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="jectfirm_bargain">
                            <div class="main_edge">
                                <div class="fillbox-left" style="margin-left: 15.33333333%;">
                                    <div class="fillbox_inline">
                                        <label class="user-name product-modify-span">合同名称：</label>
                                        <div class="fillin">
                                            <input type="text" value="项目管理合同" class="fillin-input">
                                        </div>
                                    </div>
                                    <div class="fillin_bntleft fillbox_inline_0">
                                        <div class="fillin">
                                            <!--<input type="text" id="textName" style="height: 32px;border:1px solid #ddd;border-radius: 4px;width: 215px;">-->
                                            <div class="fillin_bntleft_bg">
                                                <img src="../img/uploaded.png">
                                                <span>上传文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn_edge btn_edge_left">
                                <a href="jectfirm_manage.html" class="btn_cancel btn_all">
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
                                    <span>项目公司合同</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th><th>合同名称</th><th>合同附件</th><th>上传时间</th><th>上传人</th><th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td><td>XXX项目公司合作协议</td><td>证照.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>2</td><td>XXX项目公司合作协议</td><td>工程.pdf</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>3</td><td>XXX项目公司合作协议</td><td>销售.excel</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!------------------------项目公司合同附件-结束--------------------------------------------------------------->

<!-------------------------项目公司股东信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="jectfirm_schedule">
                            <div class="main_edge">
                                <div class="top_btn">
                                    <a href="javascript:void(0)" class="main_btn" id="add_partner_firm1">
                                        <img src="../img/add.png">
                                        <span>新增股东</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn" id="modify_partner_firm">
                                        <img src="../img/chance.png">
                                        <span>修改股东</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn" id="sure_stock1">
                                        <img src="../img/sure.png">
                                        <span>股权确认</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn" id="change_stock1">
                                        <img src="../img/changed.png">
                                        <span>股权变更</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn" id="Payment_request1">
                                        <img src="../img/request.png">
                                        <span>股东付款请求</span>
                                    </a>
                                    <a href="jectfirm_manage_paytnfo.html" class="main_btn">
                                        <img src="../img/looking.png">
                                        <span>查看股东付款信息</span>
                                    </a>
                                    <a href="jectfirm_manage_changestock.html" class="main_btn">
                                        <img src="../img/changed.png">
                                        <span>股权变更历史</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn" id="looking_stock">
                                        <img src="../img/looking.png">
                                        <span>查看股权</span>
                                    </a>
                                    <a href="javascript:void(0)" class="main_btn main_btn_dele">
                                        <img src="../img/delete.png">
                                        <span>删除</span>
                                    </a>

                                </div>
                                <!--表格-->
                                <div class="tabled_datum_20">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" class="hook_all" id="checkAll">选择</th>
                                            <th>股东类别</th><th>股东</th><th>注册资本(万元)</th><th>持股比例</th><th>实际出资(万元)</th><th>出资期数(月)</th><th>股权确认状态</th><th>附件资料</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>企业</td><td>深圳市碧桂园房地产开发有限公司</td><td>600000.00</td><td>60%</td><td>600000.00</td><td>21</td><td>未确认</td><td><a href="#" class="table_bnt see_partner_stock">查看</a></td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" class="hook_inp"></td>
                                            <td>有限合伙</td><td>深圳市汇联资管二十三号投资管理合伙企业</td><td>400000.00</td><td>40%</td><td>400000.00</td><td>21</td><td>未确认</td><td><a href="#" class="table_bnt see_partner_stock">查看</a></td>

                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                            </div>

                        </div>
<!------------------------项目公司股东信息-结束--------------------------------------------------------------->


                    </div>
                </div>
            </div>
        </div>
    </div>



<!--新增和修改股东 -弹窗-开始-->
    <div id="add_partner_firm" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01">深圳市碧桂园房地产开发有限公司</option>
                                <option value="02">深圳市汇联资管二十三号投资管理合伙企业</option>
                                <option value="03">汇联投资服务(深圳)有限公司</option>
                                <option value="04">甲公司</option>
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
                        <textarea class="fillin-input tarea_remarks" style="width: 305px;"></textarea>
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
<!--新增和修改股东-弹窗-结束-->

    <!--点击表格中的‘查看’ -弹窗-开始-->
    <div id="see_partner_stock1" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01">深圳市碧桂园房地产开发有限公司</option>
                                <option value="02" selected>深圳市汇联资管二十三号投资管理合伙企业</option>
                                <option value="03">汇联投资服务(深圳)有限公司</option>
                                <option value="04">甲公司</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin">
                        <input type="text" value="企业" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">注册资本(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="50000.00" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="50000.00" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline" style="vertical-align: top;">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" value="50" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" style="width: 305px;">代持</textarea>
                    </div>
                </div>
            </div>
            <!--<div class="fillin_bntleft fillbox_inline_0">-->
                <!--<div class="fillin">-->
                    <!--&lt;!&ndash;<input type="text" id="textName" style="height: 32px;border:1px solid #ddd;border-radius: 4px;width: 215px;">&ndash;&gt;-->
                    <!--<div class="report-file_upon">-->
                        <!--<img src="../img/uploaded.png">-->
                        <!--<span>上传文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value">-->
                    <!--</div>-->
                <!--</div>-->
            <!--</div>-->
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
                        <td>合作协议1.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                    </tr>
                    <tr>
                        <td>合作协议2.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--点击表格中的‘查看’-弹窗-结束-->

        <!--股权确认/和查看股权 -弹窗-开始-->
    <div id="sure_stock" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01">深圳市碧桂园房地产开发有限公司</option>
                                <option value="02">深圳市汇联资管二十三号投资管理合伙企业</option>
                                <option value="03">汇联投资服务(深圳)有限公司</option>
                                <option value="04">甲公司</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin">
                        <input type="text" value="企业" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">注册资本(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="60000.00" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="60000.00" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" value="60" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline" >
                    <label class="user-name product-modify-span">股权确认状态：</label>
                    <div class="fillin layui_input_block layui-form">
                        <input type="radio" name="confirm" value="yes" title="已确认" checked>
                        <input type="radio" name="confirm" value="no" title="未确认" >
                    </div>
                </div>

            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" style="width: 305px;">代持</textarea>
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">股权确认备注：</label>
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
                        <td>合作协议1.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                    </tr>
                    <tr>
                        <td>合作协议2.doc</td><td>2017-7-13 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--股权确认/和查看股权-弹窗-结束-->

    <!--股权变更 -弹窗-开始-->
    <div id="change_stock" class="add_Makeuup">
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01">深圳市碧桂园房地产开发有限公司</option>
                                <option value="02" selected>深圳市汇联资管二十三号投资管理合伙企业</option>
                                <option value="03">汇联投资服务(深圳)有限公司</option>
                                <option value="04">甲公司</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin">
                        <input type="text" value="企业" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">注册资本(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="50000.00" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" value="50000.00" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline" style="vertical-align: top;">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" value="50" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" style="width: 305px;">代持</textarea>
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
    <!--股权变更-弹窗-结束-->

    <!--项目股东付款请求 -弹窗-开始-->
    <div id="Payment_request" class="add_Makeuup">
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
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资股东：</label>
                    <div class="fillin-inputlay">
                        <form class="layui-form" action="">
                            <select lay-verify="" lay-search>
                                <option value="">-请选择-</option>
                                <option value="01" selected>深圳市汇联资管二十三号投资管理合伙企业</option>
                            </select>
                        </form>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin">
                        <input type="text" value="招商银行宝安支行" class="fillin-input">
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
                    <div class="fillin">
                        <input type="text" value="1235628" class="fillin-input">
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
    <!--项目股东付款请求-弹窗-结束-->



<!--控制头部tab切换-->
    <script>
        $('#myTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        })
    </script>



</body>
</html>