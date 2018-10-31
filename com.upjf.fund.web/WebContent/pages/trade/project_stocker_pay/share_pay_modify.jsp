<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改股东付款请求</title>
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
                    <span>修改股东付款请求</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_content_one">
                    <div class="main_edge">
                        <div class="fillbox-left">
                            <div>
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                                    <div class="fillin">
                                        <input type="text" value="南山项目" class="fillin-input">
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                                    <div class="fillin-inputlay">
                                        <form class="layui-form" action="">
                                            <select lay-verify="" lay-search>
                                                <option value="01">南山物业投资有限公司</option>
                                            </select>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline" >
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资主体：</label>
                                    <div class="fillin-inputlay">
                                        <form class="layui-form" action="">
                                            <select lay-verify="" lay-search>
                                                <option value="01">深圳市汇联资管二十三号投资管理合伙企业</option>
                                                <option value="02">XXX有限公司</option>
                                            </select>
                                        </form>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                                    <div class="fillin">
                                        <input type="text" value="中国银行深圳分行" class="fillin-input">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline" >
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                                    <div class="fillin-inputlay">
                                        <form class="layui-form" action="">
                                            <select lay-verify="" lay-search>
                                                <option value="01">招商银行</option>
                                                <option value="02">农业银行</option>
                                                <option value="03">建设银行</option>
                                            </select>
                                        </form>
                                    </div>
                                </div>
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                                    <div class="fillin">
                                        <input type="text" value="112345617" class="fillin-input">
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                                    <div class="fillin-inputlay">
                                        <form class="layui-form" action="">
                                            <select lay-verify="" lay-search>
                                                <option value="01">5265331</option>
                                                <option value="02">4489754561</option>
                                                <option value="03">265485111</option>
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
                                <div class="fillbox_inline">
                                    <label class="user-name product-modify-span">付款备注：</label>
                                    <div class="fillin">
                                        <textarea class="fillin-input tarea_remarks">备注</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="fillin_bntleft">
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
                        <a href="share_pay.html" class="btn_cancel btn_all">
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
                                    <td>付款截图1.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                </tr>
                                <tr>
                                    <td>付款截图2.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
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