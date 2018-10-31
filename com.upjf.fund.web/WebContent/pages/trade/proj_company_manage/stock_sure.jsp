<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!--股权确认/和查看股权 -弹窗-开始-->
    <div id="sure_stock" class="add_Makeuup">
    	<form action="${basePath }tradeManage/updateStockholderInfo" method="post" name="equityConfirmForm" id="equityConfirmForm">
    		<input type="hidden" name="businessPrjInfoId" />
    		<input type="hidden" name="corPid" />
    		<input type="hidden" name="stockholderPid" />
    		<input type="hidden" name="buttType" />
        <div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东：</label>
                    <div class="fillin-inputlay form-group layui-form" >
                        <select name="stockholderName" id="stockholderNameConfirm" lay-filter="stockholderNameConfirm" class="fillin-input form-control" lay-verify="" lay-search>
                            <option value="">--请选择--</option>
                            <fund:enterprise type="${Globals.ENTERPRISE_TYPE}"></fund:enterprise>
                        </select>
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东类别：</label>
                    <div class="fillin-inputlay layui-form">
                        <select name="stockholderType" id="stockholderTypeConfirm" class="form-control " lay-verify="" lay-search="">
                           	<fund:options code="${Globals.STOCKHOLDER_TYPE }"></fund:options>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">注册资本(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="registerCapitalConfirm" name="registerCapital" class="fillin-input ">
                    </div>
                </div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">股东往来(万元)：</label>
                    <div class="fillin">
                        <input type="text" id="stockholderContactsConfirm" name="stockholderContacts" value="" class="fillin-input">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline">
                    <label class="user-name product-modify-span">持股比例(%)：</label>
                    <div class="fillin">
                        <input type="text" id="holdStockRatioConfirm" name="holdStockRatio" value="" class="fillin-input">
                    </div>
                </div>
                <div class="fillbox_inline" >
                    <label class="user-name product-modify-span">股权确认状态：</label>
                    <div class="fillin layui_input_block layui-form">
                        <input type="radio" name="stockRightsStatus" value="1" title="已确认">
                        <input type="radio" name="stockRightsStatus" value="0" title="未确认">
                    </div>
                </div>
            </div>
            <div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" id="remarkConfirm" name="remark" style="width: 305px;"></textarea>
                    </div>
                </div>
                <div class="fillbox_inline ">
                    <label class="user-name product-modify-span">股权确认备注：</label>
                    <div class="fillin">
                        <textarea class="fillin-input tarea_remarks" id="stockRightsRemark" name="stockRightsRemark" style="width: 305px;"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="main_edge" id="view_accessory">
            <div class="title_bggray">
                <span>附件资料</span>
            </div>
            <!--表格-->
            <div class="tabled_datum_940" >
                <table id="equityConfirm_data_list" class="table table-bordered table-hover"></table>
	            <div id="equityConfirm_page_list"></div>
            </div>
            
        </div>
        </form>
    </div>
    <!--股权确认/和查看股权-弹窗-结束-->



