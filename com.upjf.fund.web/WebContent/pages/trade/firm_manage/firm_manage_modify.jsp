<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>修改企业</title>
<script type="text/javascript" src="${basePath}pages/trade/js/firm_manage/firm_manage_modify.js"></script>

</head>
<body>
    <div class="bg-gray">
		<input id="basePathId" type="text" hidden="hidden" value="${basePath}">
		<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}/common/img/title-head.png">
                    <span >修改企业</span>
                    <a href="javascript:history.go(-1);" title="关闭" class="history_page">关闭</a>
                </div>
                <div class="myTab_title">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#base_Information">企业基本信息</a></li>
                        <li><a href="#Bank_account">银行账户管理</a></li>
                    </ul>
                    <div class="tab-content myTab_content">

						<!-------------------------企业基本信息-开始-------------------------------------------------------------->
                        <div class="tab-pane active" id="base_Information">
							<form id="updateBaseCorInfoForm">
								<input type="text" id="CorpQuality" name="corpQuality" hidden="hidden" value="${baseCorInfo.corpQuality}">
								<input type="text" id="corBasePid" name="pid" hidden="hidden" value="${baseCorInfo.pid}">
								<div class="main_edge" style="margin-left: 10%">
	                                <div class="fillbox-left" >
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>企业名称：</label>
	                                            <div class="fillin">
	                                                <input id="update_corName" name="name" type="text" value="${baseCorInfo.name}" class="fillin-input">
	                                                <span id="update_corNameWarn" class="warn_span">*必填</span>
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">企业简称：</label>
	                                            <div class="fillin">
	                                                <input id="update_simpleName" name="simpleName" type="text" value="${baseCorInfo.simpleName}" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>组织机构代码：</label>
	                                            <div class="fillin">
	                                                <input id="update_orgCodeCert" name="orgCodeCert" type="text" value="${baseCorInfo.orgCodeCert}" class="fillin-input">
	                                            	<span id="update_orgCodeCertWarn" class="warn_span">*必填</span>
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>营业执照号码：</label>
	                                            <div class="fillin">
	                                                <input id="update_businessLicenceCode" name="businessLicenceCode" type="text" value="${baseCorInfo.businessLicenceCode}" class="fillin-input">
	                                            	<span id="update_businessLicenceCodeWarn" class="warn_span">*必填</span>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
							                    <label class="user-name product-modify-span">所有制性质：</label>
							                    <div class="fillin-inputlay layui-form">
						                            <select id="update_corpQuality" lay-filter="update_corpQualityFilter">
						                            	<option value="">-----请选择-----</option>
						                                <c:forEach items="${corList}" var="dict">
						                                	<option value="${dict.pid}" <c:if test="${baseCorInfo.corpQuality == dict.pid}">selected='selected'</c:if> >${dict.valueDes}</option>
						                                </c:forEach>
						                            </select>
							                    </div>
								            </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">法人代表：</label>
	                                            <div class="fillin">
	                                                <input id="update_legalRepresentative" name="legalRepresentative" type="text" value="${baseCorInfo.legalRepresentative}" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">注册资金(万元)：</label>
	                                            <div class="fillin">
	                                                <input id="update_registerCapital" name="registerCapital" type="text" value="${baseCorInfo.registerCapital}" class="fillin-input">
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">客户经理：</label>
	                                            <div class="fillin">
	                                                <input id="update_customerManager" name="customerManager" type="text" value="${baseCorInfo.customerManager}" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline fillbox_inline_width">
	                                            <label class="user-name product-modify-span">联系电话：</label>
	                                            <div class="fillin">
	                                                <input id="update_phone" name="phone" type="text" value="${baseCorInfo.phone}" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="btn_edge" >
	                                <a href="${basePath}/corporationController/corInfoList" class="btn_cancel btn_all">
	                                    <img src="${basePath}/common/img/cancel.png">
	                                    <span>返回</span>
	                                </a>
	                                <a href="javascript:;" class="btn_keep btn_all" id="update_corBaseSubmit">
	                                    <img src="${basePath}/common/img/keep.png">
	                                    <span>保存</span>
	                                </a>
	                            </div>
	                            <div class="main_edge">
	                                <div class="title_bggray">
	                                    <span>公司资料扫描件</span>
	                                </div>
	                                <c:forEach items="${corDataInfoList}" var="corDataInfo">
	                                	<div class="datum">
		                                    <div class="datum_main">
		                                        <span>${corDataInfo.valueDes}</span>
		                                        <input type="button" value="上传" id="${corDataInfo.pid}" hidden="hidden" title="${corDataInfo.valueDes}" class="datum_btn datum_btnactive datum_upload" />
		                                        <input type="button" value="上传" id="${corDataInfo.pid}" class="datum_btn datum_btnactive datum_upload2" />
		                                        <input type="button" id="${corDataInfo.pid}_del" onclick="delCorFile('${corDataInfo.pid}')" value="删除" class="datum_btn " />
		                                        <input type="button" id="${corDataInfo.pid}_priview" onclick="priviewCorFile('${corDataInfo.pid}_Img')" value="预览" class="datum_btn datum_imges" />
		                                    </div>
                                    		<div class="datum_img" >
                                            	<img id="${corDataInfo.pid}_Img" style="width: 266px;height: 150px" title="" class="dataInfo_img" src="${basePath}/common/img/datum.png">
                                    		</div>
	                                	</div>
	                                </c:forEach>
	                            </div>
	                            
							</form>
                        </div>
						<!---------------------------企业基本信息-结束------------------------------------------------------------>



						<!-------------------------银行账户管理-开始-------------------------------------------------------------->
                        <div class="tab-pane" id="Bank_account">
							<form id="update_bankFormId">
								<input type="text" hidden="hidden" id="bankId" name="bankId" value="">
								<input type="text" hidden="hidden" id="corpId" name="corpId" value="${baseCorInfo.pid}">
								<div class="main_edge" id="bankAccountDiv">
	                                <div class="fillbox-left">
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span"><span class="textmust">*</span>企业名称：</label>
	                                            <div class="fillin fillin_text1">
	                                                <input id="corporationName" type="text" value="${baseCorInfo.name}" class="fillin-input" readonly="readonly" >
	                                            </div>
	                                        </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">开户名：</label>
	                                            <div class="fillin">
	                                                <input id="accountName" name="accountName" type="text" value="" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">开户行：</label>
	                                            <div class="fillin-inputlay layui-form">
							                            <select id="bankSelectId" lay-filter="update_bankFilter" lay-verify="" lay-search>
						                                	<option value="" selected>---请选择---</option>
						                                	<c:forEach items="${bankList}"  var="bank">
						                                		<option value="${bank.pid}">${bank.valueDes}</option>
						                                	</c:forEach>
							                            </select>
								                 </div>
		                                    </div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">开户账号：</label>
	                                            <div class="fillin">
	                                                <input id="account" name="account" type="text" value="" class="fillin-input">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div>
	                                        <div class="fillbox_inline">
	                                            <label class="user-name product-modify-span">备注：</label>
	                                            <div class="fillin">
	                                                <textarea id="remark" name="remark" class="fillin-input tarea_remarks"></textarea>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="btn_edge btn_edge_left">
	                                <a href="${basePath}/corporationController/corInfoList" class="btn_cancel btn_all">
	                                    <img src="${basePath}/common/img/cancel.png">
	                                    <span>返回</span>
	                                </a>
	                                <button id="update_bankSubmit" type="button" class="btn_keep btn_all">
	                                    <img src="${basePath}/common/img/keep.png">
	                                    <span>保存</span>
	                                </button>
	                            </div>
							</form>
                            
                            
                            <div class="main_edge">
                                <div class="title_bggray">
                                    <span>企业银行账户列表</span>
                                </div>
                                <!--表格-->
                                <div class="tabled_datum">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>企业名称</th>
                                            <th>开户名</th>
                                            <th>开户行</th>
                                            <th>开户账号</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="corBankListTbody">
	                                         <c:forEach items="${corBankList}" var="corBankInfo">
	                                        	<tr>
	                                            	<td>${baseCorInfo.name}</td>
		                                            <td>${corBankInfo.accountName}</td>
		                                            <td>${corBankInfo.bankName}</td>
		                                            <td>${corBankInfo.account}</td>
		                                            <td>
		                                            	<a href="javascript:void(0)" onclick="toUpdateBank('${corBankInfo.pid}')" class="table_bnt" title="修改">修改</a>
		                                            	<a href="javascript:void(0)" onclick="delBank('${corBankInfo.pid}')" class="table_bnt" title="删除">删除</a>
		                                            </td>
	                                        	</tr>
	                                         </c:forEach>
                                        </tbody>
                                    </table>
                                    <!-- 企业银行底部翻页栏开始 -->
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
				                                                <td id="first_pager_list" onclick="getBankListByPage('firstPageInput')" class="ui-pg-button ui-disabled" title="首页" style="cursor: default;">
				                                                    <input type="text"  id="firstPageInput" hidden="hidden" name="firstPageInput" value="1">
				                                                    <span class="glyphicon glyphicon-step-backward"></span>
				                                                </td>
				                                                <td id="prev_pager_list"  onclick="getBankListByPage('upPageInput')" class="ui-pg-button ui-disabled" title="上一页" style="cursor: default;">
				                                                	<input type="text"  id="upPageInput" hidden="hidden" name="upPageInput" value="${page.upPage}">
				                                                    <span class="glyphicon glyphicon-backward"></span>
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="input_pager_list" dir="ltr">
				                                                    <input id="searchPage" name="searchPage"  class="ui-pg-input form-control" type="text" size="2" maxlength="7" value="${page.curPage}" role="textbox">
																	共<span id="sp_1_pager_list">${page.totalPage}</span>页
				                                                </td>
				                                                <td class="ui-pg-button ui-disabled" style="cursor: default;">
				                                                    <span class="ui-separator"></span>
				                                                </td>
				                                                <td id="next_pager_list" onclick="getBankListByPage('nextPageInput')" class="ui-pg-button ui-disabled" title="下一页">
				                                                	<input type="text" id="nextPageInput" name="nextPageInput" hidden="hidden" value="${page.nextPage}">
				                                                    <span class="glyphicon glyphicon-forward"></span>
				                                                </td>
				                                                <td id="last_pager_list" onclick="getBankListByPage('lastPageInput')" class="ui-pg-button ui-disabled" title="尾页">
				                                                	<input id="lastPageInput" name="lastPageInput" hidden="hidden" value="${page.endPage}" type="text">
				                                                    <span class="glyphicon glyphicon-step-forward"></span>
				                                                </td>
				                                                <td dir="ltr">
				                                                    <select id="corPageSelect" name="corPageSelect" class="ui-pg-selbox form-control" >
				                                                        <option role="option" value="10" <c:if test="${page.pageSize==10}">selected='selected'</c:if> >10</option>
				                                                        <option role="option" value="20" <c:if test="${page.pageSize==20}">selected='selected'</c:if> >20</option>
				                                                        <option role="option" value="30" <c:if test="${page.pageSize==30}">selected='selected'</c:if> >30</option>
				                                                    </select>
				                                                </td>
				                                            </tr>
				                                            </tbody>
				                                        </table>
				                                    </td>
				
				                                    <td id="pager_list_right" align="right">
				                                        <div dir="ltr" style="text-align: right" class="ui-paging-info" id="totalCountId">共 ${page.totalCount} 条</div>
				                                    </td>
				                                 </tr>
				                                </tbody>
				                            </table>
				                        </div>
				                    </div>
				                    <!-- 企业银行底部翻页栏结束 -->
                                </div>
                            </div>
                            
                            

                        </div>
						<!------------------------银行账户管理-结束--------------------------------------------------------------->

                    </div>
                </div>
            </div>
        </div>
    </div>

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
		
		<!--------------------------------------------修改企业银行账户信息弹窗-开始-------------------------------------------------------------->
		<div id="update_bankInfo" class="add_Makeuup">
			<form id="updateBankForm">
				<input type="hidden"  id="updatePid" name="pid" value="">
				<input type="hidden"  id="updateCorPid" name="corpId" value="">
				<input type="hidden" id="updateBankSelect" name="bankId" value="">
				<div class="main_edge"  id="updateBankAccountDiv">
		             <div class="fillbox-left">
		                 <div>
		                     <div class="fillbox_inline">
		                         <label class="user-name bank-modify-lable"><span class="textmust">*</span>企业名称：</label>
		                         <div class="fillin fillin_text1">
		                             <input id="update_corporationName" type="text" value="" class="fillin-input" readonly="readonly" >
		                         </div>
		                     </div>
		                     <div class="fillbox_inline">
		                         <label class="user-name bank-modify-lable">开户名：</label>
		                         <div class="fillin">
		                             <input id="update_accountName" name="accountName" type="text" value="" class="fillin-input">
		                         </div>
		                     </div>
		                 </div>
		                 <div>
		                     <div class="fillbox_inline">
		                         <label class="user-name bank-modify-lable">开户行：</label>
		                         <div class="fillin-inputlay layui-form">
		               			 <select id="update_bankSelectId" lay-filter="updateForm_bankFilter" lay-verify="" lay-search>
		                  			<option value="" selected>---请选择---</option>
		                  				<c:forEach items="${bankList}"  var="bank">
		                  					<option value="${bank.pid}">${bank.valueDes}</option>
		                  				</c:forEach>
		               			 </select>
		    				 </div>
		                     </div>
		                     <div class="fillbox_inline">
		                         <label class="user-name bank-modify-lable">开户账号：</label>
		                         <div class="fillin">
		                             <input id="update_account" name="account" type="text" value="" class="fillin-input">
		                         </div>
		                     </div>
		                 </div>
		                 <div>
		                     <div class="fillbox_inline">
		                         <label class="user-name bank-modify-lable">备注：</label>
		                         <div class="fillin">
		                             <textarea id="updateRemark" name="remark" class="fillin-input bank_modify_tarea_remarks"></textarea>
		                         </div>
		                     </div>
		                 </div>
		             </div>
	         </div>
		</form>
    </div>
	<!--------------------------------------------修改企业银行账户信息弹窗-结束-------------------------------------------------------------->
	
</body>
</html>