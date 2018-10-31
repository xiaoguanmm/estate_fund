var form;
var basePath;
$ (function () {
	basePath = $('#basePathId').val();
	
	//初始化分页栏
	installPageHtml("stockHolderPayVoucher");
	
	//表格中点击th里的 选择按钮，下面会全部被选中
    var flag = true;
    $(".hook_all").click(function() {
        var cb = $("input[type=checkbox]");
        for(var i = 0; i < cb.length; i++) {
            cb[i].checked = flag;
        }
        flag = !flag;
    });
    
    //引入下拉框,并设置值变化时更新隐藏域
	layui.use('form',function(){
		form =layui.form;
		
		//项目名称
		form.on('select(projectIdFilter)', function(data){
			var projectId =  data.value;
			$('#projectPid').val(projectId);
		});
		
		//出资主体
		form.on('select(contributiveIdFilter)', function(data){
			var contributiveId =  data.value;
			$('#contributiveId').val(contributiveId);
		});
		
		//财务确认状态
		form.on('select(financeConfirmStatusFilter)', function(data){
			var financeConfirmStatus =  data.value;
			$('#financeConfirmStatus').val(financeConfirmStatus);
		});
		
		//检索数据回显
		$('#projectId_SelectId').val($('#projectPid').val());
		$('#contributiveId_SelectId').val($('#contributiveId').val());
		$('#financeConfirmStatus_SelectId').val($('#financeConfirmStatus').val());
		form.render('select');
	})
	
	
	
	//项目股东付款请求管理信息列表-查询按钮
	$('#stockHolderPay_SearchButtonId').on('click', function(){
		$('#formPageSize').val("");
		$('#formCurPage').val("");
		$('#stockHolderPay_SearchFormId').submit();
	});
	
	//项目股东付款请求管理信息列表-重置按钮
	$('#stockHolderPay_resetButtonId').on('click', function(){
		$('#projectPid').val('');
		$('#contributiveId').val('');
		$('#financeConfirmStatus').val('');
		$('#formCurPage').val('');
		$('#formPageSize').val('');
		
		$('#projectId_SelectId').val('');
		$('#contributiveId_SelectId').val('');
		$('#financeConfirmStatus_SelectId').val('');
		form.render('select');
	});
	
	
	//新增股东付款请求信息
	$('#add_stockHolderPayId').on('click', function(){
		window.location.href=basePath+"/projectStockerController/toStockHolderPay?type=1";
	});
	
	
	//修改股东付款请求信息
	$('#update_stockHolderPayId').on('click', function(){
		  var pids = new Array();
		  var financeStatusArray = new Array();
	      var i = 0;
		  $("#stockHolderPay_tbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var targetPidVal = $(this).val();
				var financeConfirmStatus = $(this).next().val();
				pids[i] = targetPidVal;
				financeStatusArray[i] = financeConfirmStatus;
				i++;
			 }
		  });
      
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var targetPid = pids[0];
			  var financeConfirmStatus = financeStatusArray[0];
			  if(financeConfirmStatus.indexOf("1") != -1){
				  layer.msg("财务已确认,不能进行修改!",{icon:7,time: 1500});
			  }else{
				  window.location.href=basePath+"/projectStockerController/toStockHolderPay?payMentPid="+targetPid+"&type=2";
			  }
		  }else{
			  layer.msg("请选择要修改的记录!",{icon:7,time: 1000});
		  }
	});
	
	
	//查看详情按钮:传入投资主体主键,项目主键
	$('#see_stockHolderPayId').on('click', function(){
		var pids = new Array();
	      var i = 0;
		  $("#stockHolderPay_tbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var targetPidVal = $(this).val();
				pids[i] = targetPidVal;
				i++;
			 }
		  });
    
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var targetPid = pids[0];
			  window.location.href=basePath+"/projectStockerController/toStockHolderPay?payMentPid="+targetPid+"&type=3";
		  }else{
			  layer.msg("请选择要查看的记录!",{icon:7,time: 1000});
		  }
	});
	
	//股东付款请求删除按钮
	$('#del_stockHolderPayId').on('click', function(){
	  	  var pids="";
	  	  var statusStr = "";
		  $("#stockHolderPay_tbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var targetPidVal = $(this).val();
				var financeconfirmStatus = $(this).next().val();
				statusStr = statusStr + financeconfirmStatus+",";
				pids = pids + targetPidVal+",";
			 }
		  });
		  if(pids == ""){
			  layer.msg("请选择要删除的记录!",{icon:7,time: 1000});
		  }else{
			  if(statusStr.indexOf("1") != -1){
				  layer.msg("财务已确认,不能删除!",{icon:7,time: 1500});
			  }else{
				  layer.open({
					  type: 1,
					  title: ['删除操作提示', 'text-align:center;'],
					  area: ['300px', '200px'],
					  btnAlign: 'c',
					  content: '<div class="content_text">确定删除选中的项目数据吗？</div>',
					  btn: ['确定', '取消'],
					  btn1: function(index, layero){
						  layer.close(index);
						  delStockHolderPay(pids);
					  },
					  btn2: function(index, layero){
						  layer.close(index);
					  }
				  });
			  }
		  }
    });
	
	
	
	//项目股东付款请求管理信息列表-刷新按钮
	$('#stockHolderPay_refresh_table_list').on('click', function(){
		window.location.href=basePath+"/projectStockerController/stoPayList";
	});	
	
	//项目股东付款请求管理信息列表-页码输入框
	$('#stockHolderPay_searchPage').on('keypress', function(){
		if(window.event.keyCode==13){
			var searchPageVal = $('#stockHolderPay_searchPage').val();
			var options=$("#stockHolderPay_corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formCurPage').val(searchPageVal);
			$('#formPageSize').val(optionsVal);
			$('#stockHolderPay_SearchFormId').submit();
		}
	});
	
	
	//项目股东付款请求管理信息列表-每页显示条数下拉事件
	$('#stockHolderPay_corPageSelect').on('change', function(){
		var options=$("#stockHolderPay_corPageSelect option:selected");
		var optionsVal = options.val();
		var searchPageVal = $('#stockHolderPay_searchPage').val();
		$('#formCurPage').val(searchPageVal);
		$('#formPageSize').val(optionsVal);
		$('#stockHolderPay_SearchFormId').submit();
	});
	
	//凭证弹窗-刷新按钮
	$('#stockHolderPayVoucher_refresh_table_list').on('click', function(){
		var payMentPid = $('#cheackedPayMentPid').val();
		getStockHolderVoucherList(payMentPid,'','');
	});	
	
	//凭证弹窗分页栏-页码输入框
	$('#stockHolderPayVoucher_searchPage').on('keypress', function(){
		if(window.event.keyCode==13){
			var payMentPid = $('#cheackedPayMentPid').val();
			var curPage = $('#stockHolderPayVoucher_searchPage').val();
			var options=$("#stockHolderPayVoucher_corPageSelect option:selected");
			var pageSize = options.val();
			getStockHolderVoucherList(payMentPid,curPage,pageSize);
		}
	});
	
	//凭证弹窗每页显示条数下拉事件
	$('#stockHolderPayVoucher_corPageSelect').on('change', function(){
		var payMentPid = $('#cheackedPayMentPid').val();
		var options=$("#stockHolderPayVoucher_corPageSelect option:selected");
		var pageSize = options.val();
		var curPage = $('#stockHolderPayVoucher_searchPage').val();
		getStockHolderVoucherList(payMentPid,curPage,pageSize);
	});
	
})

//付款凭证弹窗
function toVoucherFileList(pid) {
	getStockHolderVoucherList(pid,'','');
	layer.open({
        type: 1,
        title: ['付款凭证信息','font-size:18px;'],
        area: '800px',
        maxHeight:'574.5',
        btnAlign: 'c',
        content: $('#stockHolderPayVoucher_FileListDivId')
    });
}


//项目股东付款请求管理列表首页、上一页、下一页、尾页按钮
function getObjectListByPage(idCode) {
	var options=$("#stockHolderPay_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	$('#formPageSize').val(pageSize);
	$('#formCurPage').val(curPage);
	$('#stockHolderPay_SearchFormId').submit();
}

//凭证弹窗分页栏-首页、上一页、下一页、尾页按钮
function getObjListByPage(idCode) {
	var payMentPid = $('#cheackedPayMentPid').val();
	var options=$("#stockHolderPayVoucher_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	getStockHolderVoucherList(payMentPid,curPage,pageSize);
}


//删除股东付款请求
function delStockHolderPay(payMentPids) {
	$.ajax({
	    url: basePath+"/projectStockerController/delStockHolderPay",
	    type: "POST",
	    data:{
	    	payMentPids:payMentPids
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var successCode = data["success"];
			var resultMsg = data["msg"];
			if (successCode == "s") {
				layer.msg(resultMsg,{icon: 1,time: 1000});
				$('#stockHolderPay_SearchFormId').submit();
			} else {
				layer.msg(resultMsg,{icon:7,time: 1000});
			}
		}
	});
}


//加载股东付款凭证
function getStockHolderVoucherList(payMentPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/projectStockerController/getStockHolderVoucherList",
	    type: "POST",
	    data:{
	    	payMentPid:payMentPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var resultCode=data["success"]
			
			if(resultCode == 's'){
				$('#cheackedPayMentPid').val(payMentPid);
				var voucherList = data["voucherList"];
				var pageObj = data["page"];
				var listLength = voucherList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var voucher = voucherList[i];
						var fileName = voucher.file_all_name == null?"":voucher.file_all_name;
						var accessoryTypeName = voucher.accessory_type_name == null?"":voucher.accessory_type_name;
						var createDate = voucher.create_date == null?"":new Date(voucher.create_date).format("yyyy-MM-dd HH:mm:ss");
						var userRealName = voucher.userRealName == null?"":voucher.userRealName;
						var fileUrl = voucher.file_url == null?"":voucher.file_url;
						var newhtmlStr = "<tr>" +
								"<td>"+fileName+"</td>" +
								"<td>"+accessoryTypeName+"</td>" +
								"<td>"+createDate+"</td>" +
								"<td>"+userRealName+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
				}
				reSetPageParams('stockHolderPayVoucher',htmlStr,pageObj);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//预览附件图片
function showFile(url,fileName){
	var regexp = /^.+(\.jpg|\.jpeg|\.png|\.bmp)$/g;
	/*图片查看*/
	if(regexp.test(url)){
		layer.open({
			type: 1,
			title: ["附件信息:"+fileName],
			maxmin: true,
			skin: 'layui-layer-demo',
			area: ['800px', '600px'],
			content: '<img src="'+basePath+'estateFundFile/showAccessory?path='+encodeURI(url)+'&fileName='+encodeURI(fileName)+'">'
		});
	}else{
		layer.msg("查看只针对图片文件，若想查看详情，请先下载",{icon:7});
	}
}


//下载附件
function downLoadFile(fileUrl,fileName){
	if(fileUrl != ''){
		window.location.href=basePath+"/estateFundFile/downloadAccessory?path="+encodeURI(fileUrl)+"&fileName="+encodeURI(fileName);
	}else{
		layer.msg("尚未上传文件!",{icon: 7,time: 1500});
	}
}


//给分页栏重新赋值
function reSetPageParams(prefixCode,htmlStr,pageObj) {
	$("#"+prefixCode+"_tbodyId > tr").remove();
	$("#"+prefixCode+"_tbodyId").hide();
	$("#"+prefixCode+"_pager_list").hide();
	
	$("#"+prefixCode+"_upPageInput").val(pageObj.upPage);
	$("#"+prefixCode+"_searchPage").val(pageObj.curPage);
	$("#"+prefixCode+"_sp_1_pager_list").text(pageObj.totalPage);
	$("#"+prefixCode+"_nextPageInput").val(pageObj.nextPage);
	$("#"+prefixCode+"_lastPageInput").val(pageObj.endPage);
	$("#"+prefixCode+"_totalCountId").text("共 "+pageObj.totalCount+"条");
	$("#"+prefixCode+"_corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
	
	$("#"+prefixCode+"_tbodyId").append(htmlStr);
	$("#"+prefixCode+"_tbodyId").show();
	$("#"+prefixCode+"_pager_list").show();
}


//初始化翻页栏
function installPageHtml(prefixCode) {
	var pageHtmlStr =""+
		"<div id='"+prefixCode+"_pager_list' class='ui-jqgrid-pager' dir='ltr'>"+
		    "<div id='"+prefixCode+"_pager_list' class='ui-pager-control' role='group'>"+
		        "<table class='ui-pg-table ui-common-table ui-pager-table'>"+
		            "<tbody>"+
			            "<tr>"+
			                "<td id='"+prefixCode+"_pager_list_left' align='left'>"+
			                   "<table class='ui-pg-table navtable ui-common-table'>"+
			                       "<tbody>"+
				                       "<tr>"+
				                           "<td class='ui-pg-button' title='刷新表格' id='"+prefixCode+"_refresh_table_list'>"+
				                               "<div class='ui-pg-div'>"+
				                                   "<span class='glyphicon glyphicon-refresh'></span>"+
				                               "</div>"+
				                           "</td>"+
				                       "</tr>"+
			                       "</tbody>"+
			                   "</table>"+
			                "</td>"+
			                "<td id='pager_list_center' align='center' style=' width: 373px;'>" +
			                    "<table class='ui-pg-table ui-common-table ui-paging-pager'>" +
			                        "<tbody>"+
				                        "<tr>"+
				                            "<td id='"+prefixCode+"_first_pager_list' onclick='getObjListByPage(&quot;"+prefixCode+"_firstPageInput&quot;)' class='ui-pg-button ui-disabled' title='首页' style='cursor: default;'>"+
				                                "<input type='text'  id='"+prefixCode+"_firstPageInput' hidden='hidden' name='firstPageInput' value='1'>"+
				                                "<span class='glyphicon glyphicon-step-backward'></span>"+
				                            "</td>"+
				                            "<td id='"+prefixCode+"_prev_pager_list'  onclick='getObjListByPage(&quot;"+prefixCode+"_upPageInput&quot;)' class='ui-pg-button ui-disabled' title='上一页' style='cursor: default;'>"+
				                            	"<input type='text'  id='"+prefixCode+"_upPageInput' hidden='hidden' name='upPageInput' value='1'>"+
				                                "<span class='glyphicon glyphicon-backward'></span>"+
				                            "</td>"+
				                            "<td class='ui-pg-button ui-disabled' style='cursor: default;'>"+
				                                "<span class='ui-separator'></span>"+
				                            "</td>"+
				                            "<td id='input_pager_list' dir='ltr'>"+
				                                "<input id='"+prefixCode+"_searchPage' name='searchPage'  class='ui-pg-input form-control' type='text' size='2' maxlength='7' value='1' role='textbox'>"+
												"共<span id='"+prefixCode+"_sp_1_pager_list'>0</span>页"+
				                            "</td>"+
				                            "<td class='ui-pg-button ui-disabled' style='cursor: default;'>"+
				                                "<span class='ui-separator'></span>"+
				                            "</td>"+
				                            "<td id='"+prefixCode+"_next_pager_list' onclick='getObjListByPage(&quot;"+prefixCode+"_nextPageInput&quot;)' class='ui-pg-button ui-disabled' title='下一页'>"+
				                            	"<input type='text' id='"+prefixCode+"_nextPageInput' name='nextPageInput' hidden='hidden' value='1'>"+
				                                "<span class='glyphicon glyphicon-forward'></span>"+
				                            "</td>"+
				                            "<td id='"+prefixCode+"_last_pager_list' onclick='getObjListByPage(&quot;"+prefixCode+"_lastPageInput&quot;)' class='ui-pg-button ui-disabled' title='尾页'>"+
				                            	"<input id='"+prefixCode+"_lastPageInput' name='lastPageInput' hidden='hidden' value='1' type='text'>"+
				                                "<span class='glyphicon glyphicon-step-forward'></span>"+
				                            "</td>"+
				                            "<td dir='ltr'>"+
				                                "<select id='"+prefixCode+"_corPageSelect' name='corPageSelect' class='ui-pg-selbox form-control' >"+
				                                    "<option role='option' value='10' selected='selected'>10</option>"+
				                                    "<option role='option' value='20' >20</option>"+
				                                    "<option role='option' value='30' >30</option>"+
				                                "</select>"+
				                            "</td>"+
				                        "</tr>"+
			                        "</tbody>"+
			                    "</table>"+
			                "</td>"+
			                "<td id='pager_list_right' align='right'>"+
			                    "<div dir='ltr' style='text-align: right' class='ui-paging-info' id='"+prefixCode+"_totalCountId'>共 0 条</div>"+
			                "</td>"+
			             "</tr>"+
		            "</tbody>"+
		        "</table>"+
		    "</div>"+
		"</div>";
	
	$('#'+prefixCode+"_tableId").after(pageHtmlStr);
}