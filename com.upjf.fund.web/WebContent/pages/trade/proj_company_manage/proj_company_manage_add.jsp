<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>新增项目公司</title>
     <%@ include file="../../../common/common.jsp"%>
     <%@ include file="stock_sure.jsp"%>
    

</head>
<body>
	<input type="hidden" id="businessPrjInfoId" name="businessPrjInfoId" value="${businessPrjInfo.pid }" />
	<input type="hidden" id="tabType" name="tabType" value="${tabType }" />
	<input type="hidden" id="projCompanyOperation" name="projCompanyOperation" value="${projCompanyOperation }" />
	
	
    <div class="bg-gray">
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <c:if test="${projCompanyOperation eq 'add'}">
                    	<span >新增项目公司</span>
                    </c:if>
                    <c:if test="${projCompanyOperation eq 'modify'}">
                    	<span >修改项目公司</span>
                    </c:if>
                    <c:if test="${projCompanyOperation eq 'view'}">
                    	<span >查看项目公司</span>
                    </c:if>
                    
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#jectfirm_base_Informa" id="projCompanyBaseInfo">项目公司基本信息</a></li>
                        <li><a href="#jectfirm_bargain" id="projCompanyContract">项目公司合同附件</a></li>
                        <li><a href="#jectfirm_schedule" id="projCompanyStockholderInfo">项目公司股东信息</a></li>
                    </ul>
                    <div class="tab-content myTab_content">

<!-------------------------项目公司基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane active" id="jectfirm_base_Informa">
							<form action="${basePath }tradeManage/addProjCompanyBaseInfo" method="post" name="addProjCompanyBaseInfoForm" id="addProjCompanyBaseInfoForm">
								<input type="hidden" id="corpPid" name="corpPid" value="${corporationInfo.pid}" />
								<input type="hidden" name="businessPrjInfoId" value="${businessPrjInfo.pid }" />
                            <div class="main_edge">
                                <div class="fillbox-left" >
                                   <div>
                                        <div class="fillbox_inline" id="corporationPidDiv">
                                            <label class="user-name product-modify-span">选择企业：</label>
                                            <div class="fillin" style="width: 306px;">
                                                <select name="corporationPid" id="corporationPid" class="form-control" lay-verify="" lay-search="">
					                            	<option value="-1">--请选择--</option>
					                               	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }" value="${corporationInfo.pid}" />
					                            </select>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>企业名称：</label>
                                            <div class="fillin form-group">
                                                <input type="text" value="${corporationInfo.name}" validate-rule="notEmpty" id="name" name="name" class="fillin-input form-control readonly">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">企业简称：</label>
                                            <div class="fillin form-group">
                                            	<input type="text" value="${corporationInfo.simpleName}" id="simpleName" name="simpleName" class="fillin-input readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>组织机构代码：</label>
                                            <div class="fillin form-group">
                                                <input type="text" value="${corporationInfo.orgCodeCert}" validate-rule="notEmpty" id="orgCodeCert" name="orgCodeCert" class="fillin-input form-control readonly">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">营业执照号码：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.businessLicenceCode}" id="businessLicenceCode" name="businessLicenceCode" class="fillin-input readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">所有制性质：</label>
                                            <div class="fillin" style="width: 306px;">
                                                <select name="corpQuality" id="corpQuality" class="form-control readonly" lay-verify="" lay-search="">
					                               	<fund:options code="${Globals.CORPORATION_PROPERTY }" value="${corporationInfo.corpQuality}" ></fund:options>
					                            </select>
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">法人代表：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.legalRepresentative}" id="legalRepresentative" name="legalRepresentative" class="fillin-input readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">注册资金(万元)：</label>
                                            <div class="fillin">
                                                <input type="text" value="${corporationInfo.registerCapital}" id="registerCapital" name="registerCapital" class="fillin-input readonly">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">客户经理：</label>
                                            <div class="fillin">
                                                <input type="text" id="customerManager" name="customerManager" value="${corporationInfo.customerManager}" class="fillin-input readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span">联系电话：</label>
                                            <div class="fillin">
                                                <input type="text" id="phone" name="phone" value="${corporationInfo.phone}" class="fillin-input readonly">
                                            </div>
                                        </div>
                                        <div class="fillbox_inline">
                                            <label class="user-name product-modify-span"><span class="textmust">*</span>项目公司名称：</label>
                                            <div class="fillin form-group">
                                                <input type="text" id="prjCorpName" name="prjCorpName" value="${businessPrjInfo.prjCorpName}" validate-rule="notEmpty" class="fillin-input form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
							
							<c:if test="${projCompanyOperation eq 'add' or projCompanyOperation eq 'modify'}">
	                            <div class="btn_edge btn_edge_left" >
	                                <a href="${basePath}/tradeManage/projectEnterpriseManage" class="btn_cancel btn_all">
	                                    <img src="${basePath}common/img/cancel.png">
	                                    <span>取消</span>
	                                </a>
	                                <button type="submit" class="btn_keep btn_all">
		                                <img src="${basePath}common/img/keep.png">
		                                <span>保存</span>
	                                </button>
	                            </div>
                            </c:if>
							</form>
                        </div>
<!---------------------------项目公司基本信息-结束------------------------------------------------------------>


<!-------------------------项目公司合同附件-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="jectfirm_bargain">
                        	<form action="${basePath }tradeManage/addPrjCompanyContract" method="post" name="projCompanyContractForm" id="projCompanyContractForm">
                        		<input type="hidden" id="type" name="type" />
								<input type="hidden" id="corpPid" name="corpPid" />
								<input type="hidden" name="businessPrjInfoId" value="${businessPrjInfo.pid }" />
                        	
                        	<c:if test="${projCompanyOperation eq 'add' or projCompanyOperation eq 'modify'}">
	                            <div class="main_edge">
	                                <div class="fillbox-left" style="margin-left: 15.33333333%;">
	                                    <div class="fillbox_inline">
	                                        <label class="user-name product-modify-span" style="width: 102px;"><span class="textmust">*</span>合同名称：</label>
	                                        <div class="fillin form-group">
	                                            <input type="text" id="contractName" name="contractName" value="" validate-rule="notEmpty" class="fillin-input form-control">
	                                        </div>
	                                    </div>
	                                    <div class="layui-upload fillbox-left" style="width: 80%;">
					                    	<button type="button" class="layui-btn layui-btn-normal" id="select_file">选择文件</button> 
										    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="selectFileButton">选择文件</button> 
										    <button type="button" class="layui-btn" id="uploadFileButton">开始上传</button>
										    <div class="layui-upload-list">
										    <table class="layui-table">
										      <thead>
										        <tr>
											        <th>文件名</th>
											        <th>大小</th>
											        <th>状态</th>
											        <th>操作</th>
										      	</tr>
										      </thead>
										      <tbody id="fileList"></tbody>
										    </table>
										   </div>
									  </div>
	                                </div>
	                            </div>
                            
	                            <%-- <div class="btn_edge btn_edge_left">
	                                <a href="jectfirm_manage.html" class="btn_cancel btn_all">
	                                    <img src="${basePath}common/img/cancel.png">
	                                    <span>取消</span>
	                                </a>
	                                <button type="button" class="btn_keep btn_all">
	                                    <img src="${basePath}common/img/keep.png">
	                                    <span>保存</span>
	                                </button>
	                            </div> --%>
                            </c:if>
                            </form>
                            
                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>项目公司合同</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table id="file_data_list" class="table table-bordered table-hover"></table>
                                    <div id="file_page_list"></div>
                                </div>
                            </div>

                        </div>
<!------------------------项目公司合同附件-结束--------------------------------------------------------------->

<!-------------------------项目公司股东信息-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="jectfirm_schedule">
                            <div class="main_edge">
                                <div class="top_btn">
                                
                                	<c:if test="${projCompanyOperation eq 'add' or projCompanyOperation eq 'modify'}">
	                                    <a href="javascript:void(0);" class="main_btn" id="add_partner_firm1">
	                                        <img src="${basePath}common/img/add.png">
	                                        <span>新增股东</span>
	                                    </a>
	                                    <a href="javascript:void(0);" class="main_btn" id="modify_partner_firm">
	                                        <img src="${basePath}common/img/chance.png">
	                                        <span>修改股东</span>
	                                    </a>
	                                    <a href="javascript:void(0);" class="main_btn" id="sure_stock1">
	                                        <img src="${basePath}common/img/sure.png">
	                                        <span>股权确认</span>
	                                    </a>
                                    </c:if>
                                    
                                    <a href="javascript:void(0);" class="main_btn" id="looking_stock">
                                        <img src="${basePath}common/img/looking.png">
                                        <span>查看股权</span>
                                    </a>
                                    
                                    <c:if test="${projCompanyOperation eq 'add' or projCompanyOperation eq 'modify'}">
	                                    <a href="javascript:void(0);" class="main_btn" id="change_stock1">
	                                        <img src="${basePath}common/img/changed.png">
	                                        <span>股权变更</span>
	                                    </a>
                                    </c:if>
                                    
                                    <a href="javascript:void(0);" class="main_btn" id="change_stock_his">
                                        <img src="${basePath}common/img/changed.png">
                                        <span>股权变更历史</span>
                                    </a>
                                    
                                    <c:if test="${projCompanyOperation eq 'add' or projCompanyOperation eq 'modify'}">
	                                    <a href="javascript:void(0);" class="main_btn" id="Payment_request1">
	                                        <img src="${basePath}common/img/request.png">
	                                        <span>股东付款请求</span>
	                                    </a>
                                    </c:if>
                                    
                                    <a href="javascript:void(0);" id="viewStockPaymentReq" class="main_btn">
                                        <img src="${basePath}common/img/looking.png">
                                        <span>查看股东付款信息</span>
                                    </a>
                                    
                                    <c:if test="${projCompanyOperation eq 'add' or projCompanyOperation eq 'modify'}">
	                                    <a href="javascript:void(0);" class="main_btn main_btn_dele" id="delete_stock">
	                                        <img src="${basePath}common/img/delete.png">
	                                        <span>删除</span>
	                                    </a>
                                    </c:if>

                                </div>
                                <!--表格-->
                                <div class="tabled_datum_20">
                                    <table class="table table-bordered table-hover" id="stock_data_list">
                                        <div id="stock_page_list"></div>
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
    	<form action="${basePath }tradeManage/addStockholderInfo" method="post" name="addStockholderInfoForm" id="addStockholderInfoForm">
    		<input type="hidden" name="businessPrjInfoId" />
    		<input type="hidden" id="stockholderPid" name="stockholderPid" />
    		<input type="hidden" id="projectInfoPid" name="projectInfoPid" value="${projectInfoPid }" />
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>股东：</label>
                    <div class="fillin-inputlay layui-form form-group" >
                        <select name="stockholderName" id="corporationInfoPid" validate-rule="notEmpty" lay-filter="stockholderName" class="fillin-input form-control" lay-verify="" lay-search>
                            <option value="">--请选择--</option>
                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin-inputlay layui-form">
                        <select name="stockholderType" id="stockholderType" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="">
                           	<fund:options code="${Globals.STOCKHOLDER_TYPE }"></fund:options>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">出资额(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="stockRegisterCapital" name="registerCapital" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="stockholderContacts" name="stockholderContacts" value="" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline" style="vertical-align: top;">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" id="holdStockRatio" name="holdStockRatio" value="" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" id="remark" name="remark" style="width: 305px;"></textarea>
                    </div>
                </div>
            </div>
            <div class="fillin_bntleft fillbox_inline_0" id="uploadDiv">
                <div class="fillin">
                    <div class="layui-upload fillbox-left" style="width: 100%;">
                        <%-- <img src="${basePath}common/img/uploaded.png">
                        <span>上传文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value"> --%>
                        <button type="button" class="layui-btn layui-btn-normal" id="addStock_select_file">选择文件</button> 
					    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="addStock_selectFileButton">选择文件</button> 
					    <button type="button" class="layui-btn" id="addStock_uploadFileButton">开始上传</button>
					    <div class="layui-upload-list">
						    <table class="layui-table">
						      <thead>
						        <tr>
							        <th>文件名</th>
							        <th>大小</th>
							        <th>状态</th>
							        <th>操作</th>
						      	</tr>
						      </thead>
						      <tbody id="addStock_fileList"></tbody>
						    </table>
					    </div>
                        
                        
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
                <table id="addStock_data_list" class="table table-bordered table-hover"></table>
	            <div id="addStock_page_list"></div>
            </div>
        </div>
        </form>
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
                        <!--<img src="${basePath}common/img/uploaded.png">-->
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
    <!--股权确认/和查看股权-弹窗-结束-->

    <!--股权变更 -弹窗-开始-->
    <div id="change_stock" class="add_Makeuup">
    	<form action="${basePath }tradeManage/addStockholderInfo" method="post" name="stockholderChangeForm" id="stockholderChangeForm">
    		<input type="hidden" name="businessPrjInfoId" />
    		<input type="hidden" name="stockholderPid" />
    		<input type="hidden" name="corPid" id="corPid" />
    		<input type="hidden" name="buttType" />
    		<input type="hidden" name="stockRightsChangePid" id="stockRightsChangePid" />
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay form-group layui-form" >
                        <select name="stockholderName" id="stockholderNameChange" lay-filter="stockholderNameChange" class="fillin-input form-control" lay-verify="" lay-search>
                            <option value="">--请选择--</option>
                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin-inputlay layui-form">
                        <select name="stockholderType" id="stockholderTypeChange" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="">
                           	<fund:options code="${Globals.STOCKHOLDER_TYPE }"></fund:options>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">注册资本(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="registerCapitalChange" name="registerCapital" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="stockholderContactsChange" name="stockholderContacts" value="" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline" style="vertical-align: top;">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" id="holdStockRatioChange" name="holdStockRatio" value="" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" id="remarkChange" name="remark" style="width: 305px;"></textarea>
                    </div>
                </div>
            </div>
            <div class="fillin_bntleft fillbox_inline_0">
                <div class="fillin">
                    <div class="layui-upload fillbox-left" style="width: 100%;">
                        <%-- <img src="${basePath}common/img/uploaded.png">
                        <span>上传文件</span><input tabindex="3" size="3" name="report" class="file-prew" type="file" onchange="document.getElementById('textName').value=this.value"> --%>
                        <button type="button" class="layui-btn layui-btn-normal" id="stockRightsChange_select_file">选择文件</button> 
					    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="stockRightsChange_selectFileButton">选择文件</button> 
					    <button type="button" class="layui-btn" id="stockRightsChange_uploadFileButton">开始上传</button>
					    <div class="layui-upload-list">
						    <table class="layui-table">
						      <thead>
						        <tr>
							        <th>文件名</th>
							        <th>大小</th>
							        <th>状态</th>
							        <th>操作</th>
						      	</tr>
						      </thead>
						      <tbody id="stockRightsChange_fileList"></tbody>
						    </table>
					    </div>
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
                <table id="stockRightsChange_data_list" class="table table-bordered table-hover"></table>
	            <div id="stockRightsChange_page_list"></div>
            </div>
            
        </div>
        </form>
    </div>
    <!--股权变更-弹窗-结束-->

    <!--项目股东付款请求 -弹窗-开始-->
    <div id="Payment_request" class="add_Makeuup">
    	<form action="${basePath }tradeManage/addStockholderPaymentRequest" method="post" name="stockholderPaymentForm" id="stockholderPaymentForm">
    		<input type="hidden" name="corPid" />
    		<input type="hidden" name="businessPrjInfoCorpPid" />
    		<input type="hidden" name="stockholderPid" />
    		<input type="hidden" id="pid" name="stockPaymentPid" value="${stockPaymentPid }" />
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>项目名称：</label>
                    <div class="fillin-inputlay layui-form">
                        <select id="prjId" name="prjId" lay-verify="" lay-search="" class="form-control" validate-rule="notEmpty">
                            <fund:options code="${Globals.PROJECT_NAME}" value=""/>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款公司：</label>
                    <div class="fillin-inputlay layui-form">
                        <select id="businessPrjInfoPid" name="businessPrjInfoPid" lay-verify="" lay-search="" lay-filter="businessPrjInfoPid" class="form-control" validate-rule="notEmpty">
                        	<fund:enterprise type="${Globals.PROJECT_ENTERPRISE_TYPE }"></fund:enterprise>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>出资股东：</label>
                    <div class="fillin-inputlay layui-form">
                        <select id="contributiveId" name="contributiveId" lay-verify="" lay-search="" class="form-control" validate-rule="notEmpty">
                        	<%-- <fund:enterprise type="${Globals.STOCK_TYPE}"></fund:enterprise> --%>
                        	<fund:enterprise type="${Globals.ENTERPRISE_TYPE }"></fund:enterprise>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款银行：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="receiverBankId" id="receiverBankId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverBankId">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款银行：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="payBankId" id="payBankId" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="payBankId">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>收款账户：</label>
                    <div class="fillin-inputlay layui-form form-group">
                        <select name="receiverAccount" id="receiverAccount" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="receiverAccount">
	                        <option value="">--请选择--</option>
	                    </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款账户：</label>
                    <div class="fillin-inputlay layui-form">
                    	<select name="payAccount" id="payAccount" validate-rule="notEmpty" class="form-control" lay-verify="" lay-search="" lay-filter="payAccount">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款期数：</label>
                    <div class="fillin">
                        <input type="text" name="payTerm" value="${payment.payTerm}" validate-rule="notEmpty|between[1-99]" class="fillin-input form-control">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款金额(万元)：</label>
                    <div class="fillin form-group">
                        <input type="text" name="payAmount" id="payAmount" validate-rule="notEmpty|rangeNumberBits[14-2]" value="${payment.payAmount}" class="fillin-input form-control">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span"><span class="textmust">*</span>付款时间：</label>
                    <div class="fillin">
                        <input type="text" name="payDate" validate-rule="notEmpty" readonly="readonly" id="payDate" 
                        	value="<fmt:formatDate value="${payment.payDate}" pattern="yyyy-MM-dd"/>" class="fillin-input form-control">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">付款备注：</label>
                    <div class="fillin">
                        <textarea validate-rule="charLength[0-4000]" name="payRemark" class="fillin-input tarea_remarks form-control">${payment.payRemark}</textarea>
                    </div>
                </div>
            </div>
            <div class="fillin_bntleft fillbox_inline_0">
                <div class="fillin">
                    <div class="layui-upload fillbox-left" style="width: 100%;">
                        <button type="button" class="layui-btn layui-btn-normal" id="stockPayment_select_file">选择文件</button> 
					    <button type="button" class="layui-btn layui-btn-normal" style="display: none;" id="stockPayment_selectFileButton">选择文件</button> 
					    <button type="button" class="layui-btn" id="stockPayment_uploadFileButton">开始上传</button>
					    <div class="layui-upload-list">
						    <table class="layui-table">
						      <thead>
						        <tr>
							        <th>文件名</th>
							        <th>大小</th>
							        <th>状态</th>
							        <th>操作</th>
						      	</tr>
						      </thead>
						      <tbody id="stockPayment_fileList"></tbody>
						    </table>
					    </div>
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
                <table id="stockPayment_data_list" class="table table-bordered table-hover"></table>
	            <div id="stockPayment_page_list"></div>
            </div>
        </div>
        </form>
    </div>
    <!--项目股东付款请求-弹窗-结束-->


<script type="text/javascript" src="${basePath}pages/trade/js/trade_manage.js"></script>
<script type="text/javascript" src="${basePath}pages/trade/js/proj_company_manage/proj_company_manage_add.js"></script>
<script type="text/javascript" src="${basePath}pages/trade/js/proj_company_manage/stock_payment_request.js"></script>
<script type="text/javascript" src="${basePath}common/js/fileUtils.js"></script>




</body>
</html>