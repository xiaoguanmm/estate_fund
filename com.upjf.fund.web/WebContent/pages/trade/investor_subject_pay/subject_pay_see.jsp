<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>付款明细</title>
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
                    <span>付款明细</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_content_one">
                    <div class="main_edge">
                        <div class="fillbox-left">
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
                    </div>
                    <div class="btn_edge btn_edge_left">
                        <a href="share_pay.html" class="btn_cancel btn_all">
                            <img src="../img/return.png">
                            <span>返回</span>
                        </a>
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
                                    <td>付款截图1.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                                </tr>
                                <tr>
                                    <td>付款截图2.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

</body>
</html>