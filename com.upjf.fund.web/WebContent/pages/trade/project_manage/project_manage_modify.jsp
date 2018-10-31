<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改项目</title>
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
                    <span >修改项目</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#base_Informa">项目基本信息</a></li>
                        <li><a href="#bargain">项目合同</a></li>
                        <li><a href="#schedule">项目进度</a></li>
                        <li><a href="#budget">项目预算</a></li>
                    </ul>
                    <div class="tab-content myTab_content">

<!-------------------------项目基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane active" id="base_Informa">

                            <div class="main_edge">
                                <div class="fillbox-left" >
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="南山项目" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目公司：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select name="company" lay-verify="" lay-search>
                                                        <option value="">-请选择-</option>
                                                        <option value="01" selected>南山物业投资有限公司</option>
                                                        <option value="02">福田物业投资有限公司</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">楼盘名称：</label>
                                            <div class="fillin">
                                                <input type="text" value="碧桂园小区" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">开 发 商：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select name="companyd" lay-verify="" lay-search>
                                                        <option value="">-请选择-</option>
                                                        <option value="01" selected>XXX开发有限公司</option>
                                                        <option value="02">福田物业投资有限公司</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目地址：</label>
                                            <div class="search-text ">
                                                <form class="layui-form" action="" style="display:inline-block;width:150px;">
                                                    <select  name="province" lay-verify="" lay-search>
                                                        <option value="">--省--</option>
                                                        <option value="01">北京省</option>
                                                        <option value="02" selected>广东省</option>
                                                        <option value="03">广西省</option>
                                                        <option value="04">湖南省</option>
                                                        <option value="05">湖北省</option>
                                                    </select>
                                                </form>
                                                <form class="layui-form" action="" style="display:inline-block;width:150px;">
                                                    <select  name="city" lay-verify="" lay-search>
                                                        <option value="">--市--</option>
                                                        <option value="01">北京市</option>
                                                        <option value="02">上海市</option>
                                                        <option value="03">广州市</option>
                                                        <option value="04" selected>深圳市</option>
                                                    </select>
                                                </form>
                                                <form class="layui-form" action="" style="display:inline-block;width:150px;">
                                                    <select  name="area" lay-verify="" lay-search>
                                                        <option value="">--区--</option>
                                                        <option value="01" selected>南山区</option>
                                                        <option value="02">福田区</option>
                                                        <option value="03">罗湖区</option>
                                                        <option value="04">宝安区</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">详细地址：</label>
                                            <div class="fillin">
                                                <input type="text" value="深南大道1008号" class="fillin-input" style="width:457px">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目种类：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select name="breed" lay-verify="" lay-search id="breed" onchange="func()">
                                                        <option value="breed_city">城市更新</option>
                                                        <option value="breed_notcity">非城市更新</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>土地获取方式：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select name="land" lay-verify="" lay-search>
                                                        <option value="">-请选择-</option>
                                                        <option value="01" selected>招拍挂</option>
                                                        <option value="02">并购</option>
                                                        <option value="03">承债式收购</option>
                                                        <option value="04">城市更新</option>
                                                        <option value="05">其他</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目用地性质：</label>
                                            <div class="fillin">
                                                <input type="text" value="商用" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目占地面积(㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目建设用地面积(㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目容积率：</label>
                                            <div class="fillin">
                                                <input type="text" value="50" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目总建面（㎡）：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">土地获取成本(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="100000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">楼面地价(RMB/㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">计容楼面地价(RMB/㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="main_edge main_edge_bottom">
                                <div class="title_bggray">
                                    <span>业态组成</span>
                                </div>
                                <div class="top_btn_little">
                                    <a href="javascript:void(0)" class="main_btn" id="add_Makeuup1">
                                        <img src="../img/add.png">
                                        <span>新增业态组成</span>
                                    </a>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th><th>业态组成</th><th>对应业态建面(㎡)</th><th>可售建面(㎡)</th><th>销售均价(RMB/㎡)</th><th>货值(万元)</th><th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td><td>住宅</td><td>10000.00</td><td>10000.00</td><td>100000.00</td><td>1000000000.00</td><td><a href="javascript:void(0)" class="table_bnt modify_Makeuup1">修改</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>2</td><td>洋房</td><td>10000.00</td><td>10000.00</td><td>100000.00</td><td>1000000000.00</td><td><a href="javascript:void(0)" class="table_bnt modify_Makeuup1">修改</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="main_edged main_edge_bottom">
                                <div class="fillbox-left" >
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">计容建面(㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">可售总建面(㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="2000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">计容可售建面(㎡)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">总货值(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="2000000000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目总投(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="1000000000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目期限：</label>
                                            <div class="fillin">
                                                <input type="text" value="24" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期开工时间：</label>
                                            <div class="fillin">
                                                <input type="text" value="2018-8-23" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期开盘时间：</label>
                                            <div class="fillin">
                                                <input type="text" value="2020-9-27" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目预期收益：</label>
                                            <div class="fillin">
                                                <input type="text" value="100000000.00" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目预期收益率：</label>
                                            <div class="fillin">
                                                <input type="text" value="1" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="main_edged" style="margin-top:40px;">
                                <div class="fillbox-left" >
                                    <div class="btn_breed_city">
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">更新范围单元面积(㎡)：</label>
                                                <div class="fillin">
                                                    <input type="text" value="1000000.00" class="fillin-input">
                                                </div>
                                            </div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">可开发建设用地：</label>
                                                <div class="fillin">
                                                    <input type="text" value="1000000.00" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">拟拆除建筑面积(㎡)：</label>
                                                <div class="fillin">
                                                    <input type="text" value="1000000.00" class="fillin-input">
                                                </div>
                                            </div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">容积比率：</label>
                                                <div class="fillin">
                                                    <input type="text" value="100" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">建筑面积(㎡)：</label>
                                                <div class="fillin">
                                                    <input type="text" value="1000000.00" class="fillin-input">
                                                </div>
                                            </div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">预期每平方米售价(元)：</label>
                                                <div class="fillin">
                                                    <input type="text" value="10000.00" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">预期完成收地日期：</label>
                                                <div class="fillin">
                                                    <input type="text" value="2021-07-26" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目备注：</label>
                                            <div class="fillin">
                                                <textarea class="fillin-input tarea_remarks">备注</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="btn_edge btn_edge_left" >
                                <a href="project_manage.html" class="btn_cancel btn_all">
                                    <img src="../img/cancel.png">
                                    <span>取消</span>
                                </a>
                                <a href="javascript:void(0)" class="btn_keep btn_all">
                                    <img src="../img/keep.png">
                                    <span>保存</span>
                                </a>
                            </div>

                        </div>
<!---------------------------项目基本信息-结束------------------------------------------------------------>


<!-------------------------项目合同-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="bargain">
                            <div class="main_edge">
                                <div class="fillbox-left" style="margin-left: 15.33333333%;">
                                    <div class="fillbox_inline">
                                        <label class="user-name product-modify-span">合同名称：</label>
                                        <div class="fillin">
                                            <input type="text" value="证照" class="fillin-input">
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
                                    <span>项目合同</span>
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
                                            <td>1</td><td>证照</td><td>证照.doc</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>2</td><td>工程</td><td>工程.pdf</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>3</td><td>销售</td><td>销售.excel</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!------------------------项目合同-结束--------------------------------------------------------------->

<!-------------------------项目进度-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="schedule">
                            <div class="main_edge">
                                <div class="fillbox-left">
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期完成收地时间：</label>
                                            <div class="fillin">
                                                <input type="text" value="2018-08-10" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际完成收地时间：</label>
                                            <div class="fillin">
                                                <input type="text" value="2018-08-15" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期开始施工日期：</label>
                                            <div class="fillin">
                                                <input type="text" value="2019-08-10" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际开始施工日期：</label>
                                            <div class="fillin">
                                                <input type="text" value="2019-08-20" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期取得预售证日期：</label>
                                            <div class="fillin">
                                                <input type="text" value="2023-08-16" class="fillin-input">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">预期清算日期：</label>
                                            <div class="fillin">
                                                <input type="text" value="2024-09-22" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">项目进度状态：</label>
                                            <div class="fillin-inputlay">
                                                <form class="layui-form" action="">
                                                    <select lay-verify="" lay-search>
                                                        <option value="">-请选择-</option>
                                                        <option value="01" selected>证照</option>
                                                        <option value="02">工程</option>
                                                        <option value="03">销售</option>
                                                        <option value="04">已完结</option>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">实际清算日期：</label>
                                            <div class="fillin">
                                                <input type="text" value="2024-09-29" class="fillin-input">
                                            </div>
                                        </div>
                                    </div>
                                    <!--当选择’销售‘的时候display:block    --开始-->
                                    <div style="display: none">
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">是否取得预售证：</label>
                                                <div class="fillin layui_input_block layui-form">
                                                    <input type="radio" name="sex" value="nan" title="男" checked>
                                                    <input type="radio" name="sex" value="nv" title="女" >
                                                </div>
                                            </div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">实际取得预售证日期：</label>
                                                <div class="fillin">
                                                    <input type="text" value="2023-09-29" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">证载建面：</label>
                                                <div class="fillin-inputlay">
                                                    <form class="layui-form" action="">
                                                        <select lay-verify="" >
                                                            <option value="01">住宅</option>
                                                            <option value="02">非住宅</option>
                                                        </select>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">已推货值：</label>
                                                <div class="fillin">
                                                    <input type="text" value="" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="fillbox_inline">
                                                <label class="user-name product-modify-span">剩余推盘货值：</label>
                                                <div class="fillin">
                                                    <input type="text" value="" class="fillin-input">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--当选择销售的时候display:block    --结束-->

                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">备注：</label>
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
                                    <span>项目进度状态</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th><th>项目进度状态</th><th>进度附件</th><th>备注</th><th>项目进度更新时间</th><th>更新人</th><th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td><td>证照</td><td>证照.doc</td><td>证照</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>2</td><td>工程</td><td>工程.pdf</td><td>建设中</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>3</td><td>销售</td><td>销售.excel</td><td>开盘中</td><td>2017-07-11 10:10:10</td><td>admin</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">修改</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!------------------------项目进度-结束--------------------------------------------------------------->

<!-------------------------项目预算-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="budget">
                            <div class="main_edge">
                                <div class="fillbox-left" style="margin-left: 15.333333%;display:inline-block">
                                    <div class="fillbox_inline fillbox_block" >
                                        <label class="user-name product-modify-span">预算名称：</label>
                                        <div class="fillin">
                                            <input type="text" value="预算名称" class="fillin-input">
                                        </div>
                                    </div>
                                    <div class="fillbox_inline fillbox_block">
                                        <label class="user-name product-modify-span">项目整体利润预算(万元)：</label>
                                        <div class="fillin">
                                            <input type="text" value="100000.56" class="fillin-input">
                                        </div>
                                    </div>
                                    <div class="fillbox_inline fillbox_block">
                                        <label class="user-name product-modify-span">预算日期：</label>
                                        <div class="fillin">
                                            <input type="text" value="2017-07-12 " class="fillin-input">
                                        </div>
                                    </div>
                                    <div class="fillbox_inline fillbox_block">
                                        <label class="user-name product-modify-span">预算依据：</label>
                                        <div class="fillin">
                                            <textarea class="fillin-input tarea_remarks" style="width: 305px;">碧桂园提供</textarea>
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
                                    <span>项目预算更新记录</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>序号</th><th>预算名称</th><th>项目整体利润预算(万元)</th><th>预算日期</th><th>预算依据</th><th>依据附件</th><th>操作人</th><th>操作日期</th><th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1</td><td>预算名称</td><td>100000.56</td><td>2017-07-12 </td><td>碧桂园提供</td><td>word.xlsx</td><td>admin</td><td>2017-07-12 10:10:10</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>2</td><td>预算名称</td><td>100000.56</td><td>2017-07-12 </td><td>碧桂园提供</td><td>word.xlsx</td><td>admin</td><td>2017-07-12 10:10:10</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        <tr>
                                            <td>3</td><td>预算名称</td><td>100000.56</td><td>2017-07-12 </td><td>碧桂园提供</td><td>word.xlsx</td><td>admin</td><td>2017-07-12 10:10:10</td><td><a href="javascript:void(0)" class="table_bnt">查看</a><a href="javascript:void(0)" class="table_bnt">下载</a><a href="javascript:void(0)" class="table_bnt datum_delt">删除</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
<!------------------------项目预算-结束--------------------------------------------------------------->

                    </div>
                </div>
            </div>
        </div>
    </div>



    <!--新增业态组成 -弹窗-开始-->
    <div id="add_Makeuup" class="add_Makeuup">
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">业态组成：</label>
            <div class="fillin-inputlay">
                <form class="layui-form" action="">
                    <select lay-verify="" lay-search>
                        <option value="">-请选择-</option>
                        <option value="01">住宅</option>
                        <option value="02">洋房</option>
                        <option value="03">别墅</option>
                        <option value="04">公寓</option>
                        <option value="05">保障房</option>
                        <option value="06">商业</option>
                        <option value="07">其他</option>
                    </select>
                </form>
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">可售建面(㎡)：</label>
            <div class="fillin">
                <input type="text" value="" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">对应业态建面(㎡)：</label>
            <div class="fillin">
                <input type="text" value="" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">销售均价(RMB/㎡)：</label>
            <div class="fillin">
                <input type="text" value="" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">货值：</label>
            <div class="fillin">
                <input type="text" value="" class="fillin-input">
            </div>
        </div>
    </div>
    <!--新增业态组成-弹窗-结束-->


    <!--修改业态组成 -弹窗-开始-->
    <div id="modify_Makeuup" class="add_Makeuup">
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">业态组成：</label>
            <div class="fillin-inputlay">
                <form class="layui-form" action="">
                    <select lay-verify="" lay-search>
                        <option value="">-请选择-</option>
                        <option value="01">住宅</option>
                        <option value="02" selected>洋房</option>
                        <option value="03">别墅</option>
                        <option value="04">公寓</option>
                        <option value="05">保障房</option>
                        <option value="06">商业</option>
                        <option value="07">其他</option>
                    </select>
                </form>
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">可售建面(㎡)：</label>
            <div class="fillin">
                <input type="text" value="10000.00" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">对应业态建面(㎡)：</label>
            <div class="fillin">
                <input type="text" value="10000.00" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">销售均价(RMB/㎡)：</label>
            <div class="fillin">
                <input type="text" value="100000.00" class="fillin-input">
            </div>
        </div>
        <div class="fillbox_inline">
            <label class="user-name product-modify-span">货值：</label>
            <div class="fillin">
                <input type="text" value="1000000000.00" class="fillin-input">
            </div>
        </div>
    </div>
    <!--修改业态组成-弹窗-结束-->

<!--控制头部tab切换-->
    <script>
        $('#myTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        })
    </script>



</body>
</html>