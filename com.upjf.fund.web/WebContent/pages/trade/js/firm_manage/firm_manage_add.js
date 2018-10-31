// .....................................新增企业页面.............................................................
$ (function () {
		 var basePath = $('#basePathId').val();
	    
	     //引入下拉框,并设置值变化时更新隐藏域
		 layui.use('form',function(){
			var form =layui.form;
			form.on('select(corpQualityFilter)', function(data){
				  var corpQualityVal =  data.value;
				  $('#corpQuality').val(corpQualityVal);
			});
			form.on('select(bankFilter)', function(data){
				var bankVal =  data.value;
				$('#bankId').val(bankVal);
			});
			form.on('select(update_bankFilter)', function(data){
				var bankVal =  data.value;
				$('#updateBankSelect').val(bankVal);
			});
		 });
		 
		 
		 //启用上传
		 layui.use('upload', function(){
			  var upload = layui.upload;
			  var curElemId;
			  //执行实例
			  var uploadInst = upload.render({
			    elem: '.datum_upload' //绑定元素
			    ,url: basePath + '/corporationController/uploadCorDataFile' //上传接口
			    ,before: function(obj){ 
			    	curElemId = $(this)[0].item[0].id;
			    	$(this)[0].data.corPid = $('#newCorPid').val();
			    	$(this)[0].data.corDataType = curElemId;
			    	$(this)[0].data.fileTypeName = $(this)[0].item[0].title;
			    },done: function(res){
			    	if(res.success == 's'){
			    		var fileUrl = res.fileUrl;
			    		var fileName = res.fileName;
			    		$('#'+curElemId+'_Img').attr('src',basePath + 'estateFundFile/showAccessory?path='+encodeURI(fileUrl)+'&fileName='+encodeURI(fileName));
			    		$('#'+curElemId+'_Img').attr('title',fileName);
			    		$('#'+curElemId+'_del').addClass('datum_btnactive');
				    	$('#'+curElemId+'_priview').addClass('datum_btnactive');
			    	}
			    }
			    ,error: function(){
			    	layer.msg("文件上传失败,请重试!",{icon: 7,time: 1000});
			    }
			  });
			});
		 
		 
		 //点击上传校验
		 $('.datum_upload2').click(function () {
				var newCorPidVal = $('#newCorPid').val();
				if(newCorPidVal == ''){
					layer.msg("请先保存企业基本信息!",{icon: 7,time: 1000});
					return false;
				}else{
					$('.datum_upload').attr('hidden',false);
					$('.datum_upload2').attr('hidden',true);
				}
		});
		 
		
		 //控制头部tab切换
		 $('#myTab a').click(function (e) {
		    e.preventDefault();
		    $(this).tab('show');
		 });
    
		
	    //企业基础信息保存按钮
	    $('#corBaseSubmit').click(function () {
			var arr = ['corName','orgCodeCert','businessLicenceCode'];
			if(cheackAndShowWarns(arr)){
				focusWarnInput(arr);
				return false;
			}
			saveBaseCorInfo();
		});
		
		//企业基础信息必填项校验
		$("#corName,#orgCodeCert,#businessLicenceCode").blur( function () {
			var aStr = $(this).attr("id");
			var brr = [aStr];
			cheackAndShowWarns(brr);
		});
		
		
		
		//企业信息-银行管理保存按钮
		$('#bankAccountSubmit').click(function () {
			
			var newCorPidVal = $('#newCorPid').val();
			if(newCorPidVal == ''){
				layer.msg("请先保存企业基本信息!",{icon: 7,time: 1000});
				return false;
			}else{
				$('#corpId').val(newCorPidVal);
				saveCorBankInfo();
			}
		});
		
		
		//公司资料扫描件-删除按钮
        $('.datum_delt').on('click', function(){
           layer.open({
               type: 1,
               title: ['删除操作提示', 'text-align:center;'],
               area: ['300px', '200px'],
               btnAlign: 'c',
               content: '<div class="content_text">确定删除选中的此批数据吗？</div>',
               btn: ['删除', '取消'],
               btn1: function(index, layero){
                  //按钮【按钮二】的回调
               },
               btn2: function(index, layero){
                  //按钮【按钮三】的回调
               }
           });
        });
       
       
        //企业银行账户列表刷新按钮
        $('#refresh_table_list').on('click', function(){
        	var newCorPid = $('#newCorPid').val();
        	var options=$('#corPageSelect option:selected');
			var pageSize = options.val();
        	if(newCorPid != ""){
        		getBankList(newCorPid,"1",pageSize);
        	}
        });
		
        //企业银行账户信息列表-页码输入框
		$('#searchPage').on('keypress', function(){
			if(window.event.keyCode==13){
				var curPage = $('#searchPage').val();
				var options=$("#corPageSelect option:selected");
				var pageSize = options.val();
				var newCorPid = $('#newCorPid').val();
				if(newCorPid != ""){
	        		getBankList(newCorPid,curPage,pageSize);
	        	}
			}
		});
        
		//企业信息列表-每页显示条数下拉事件
		$('#corPageSelect').on('change', function(){
			var curPage = $('#searchPage').val();
			var options=$("#corPageSelect option:selected");
			var pageSize = options.val();
			var newCorPid = $('#newCorPid').val();
			if(newCorPid != ""){
        		getBankList(newCorPid,curPage,pageSize);
        	}
			
		});
        
})


//预览图片
function priviewCorFile(elemId){
	var regexp = /^.+(\.jpg|\.jpeg|\.png|\.bmp)$/g;
	var fileName = $('#'+elemId).attr('title');
	var fileFullUrl = $('#'+elemId).attr('src');
	if(fileFullUrl.indexOf('?path=') != -1){
		var fileUrl = fileFullUrl.split("?path=")[1].split("&fileName=")[0];
		/*图片查看*/
		if(regexp.test(fileUrl)){
			layer.open({
				type: 1,
				title: ["附件信息:"+fileName],
				maxmin: true,
				skin: 'layui-layer-demo',
				area: ['800px', '600px'],
				content: '<img src="'+basePath+'estateFundFile/showAccessory?path='+fileUrl+'&fileName='+encodeURI(fileName)+'">'
			});
		}else{
			layer.msg("查看只针对图片文件，若想查看详情，请先下载",{icon:7});
		}
	}else{
		layer.msg("尚未上传图片",{icon:7});
	}
	
}

//删除图片
function delCorFile(elemId){
	var corPid = $('#newCorPid').val();
	$.ajax({
	    url: basePath+"/corporationController/delCorFile",
	    type: "POST",
	    data:{corPid:corPid,corDataType:elemId},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg = data["msg"]
			var success = data["success"]
			if(success == "s"){
				$('#'+elemId+'_Img').attr('src',basePath + '/common/img/datum.png')
				$('#'+elemId+'_del').removeClass('datum_btnactive');
				$('#'+elemId+'_priview').removeClass('datum_btnactive');
				
				layer.msg(resultMsg,{icon:1});
			}else{
				layer.msg(resultMsg,{icon:7});
			}
		}
	});
}

//企业银行账户信息列表首页、上一页、下一页、尾页按钮
function getBankListByPage(idCode) {
	var newCorPid = $('#newCorPid').val();
	var options=$("#corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	if(newCorPid != ""){
		getBankList(newCorPid,curPage,pageSize);
	}
}



//保存企业基本信息
function saveBaseCorInfo() {
	$.ajax({
	    url: basePath+"/corporationController/saveBase",
	    type: "POST",
	    data:$('#corBaseForm').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var success=data["success"]
			if(success == "s"){
				var newPid=data["newPid"]
				$('#newCorPid').val(newPid);
				$('#corporationName').val($('#corName').val());
				
				//显示真实上传按钮
				$('.datum_upload').attr('hidden',false);
				$('.datum_upload2').attr('hidden',true);
				layer.msg(resultMsg,{icon: 1,time: 1000});
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//保存企业银行账户信息
function saveCorBankInfo() {
	$.ajax({
	    url: basePath+"/corporationController/saveBank",
	    type: "POST",
	    data:$('#bankAccountForm').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var success=data["success"]
			
			if(success == "s"){//刷新银行列表
				layer.msg(resultMsg,{icon: 1,time: 1000});
				var newCorPid = $('#newCorPid').val();
	        	var options=$("#corPageSelect option:selected");
				var pageSize = options.val();
				getBankList(newCorPid,"1",pageSize);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//进入修改企业银行信息
function toUpdateBank(pid){
	$.ajax({
	    url: basePath+"/corporationController/getBank",
	    type: "POST",
	    data:{
	    	pid:pid
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var successCode=data["success"]
			if(successCode == 's'){
				var bankInfo=data["bankInfo"]
				$('#updatePid').val(bankInfo.pid);
				$('#updateCorPid').val(bankInfo.corpId);
				$('#update_corporationName').val($('#corporationName').val());
				$('#update_accountName').val(bankInfo.accountName);
				$("#update_bankSelectId").val(bankInfo.bankId);
				layui.use('form', function(){
					  var form = layui.form;
					  form.render('select');
				});
				
				$("#update_account").val(bankInfo.account);
				$("#updateRemark").val(bankInfo.remark);
				
				layer.open({
			        type: 1,
			        title: ['修改企业银行','font-size:18px;'],
			        area: ['590px', '400px'],
			        btnAlign: 'c',
			        content: $('#update_bankInfo'),
			        btn: ['确定', '取消'],
			        btn1: function(index, layero){
			        	layer.close(index);
			        	updateBank();
			        },
			        btn2: function(index, layero){
			        	layer.close(index)
			        }
			    });
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//保存更新的企业银行信息
function updateBank(){
	$.ajax({
	    url: basePath+"/corporationController/updateBank",
	    type: "POST",
	    data:$('#updateBankForm').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			//成功变更之后,重新加载企业银行信息列表,变更失败,则不重新加载
			var resultMsg=data["msg"]
			var successCode=data["success"]
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
				var newCorPid = $('#newCorPid').val();
	        	var options=$("#corPageSelect option:selected");
				var pageSize = options.val();
				getBankList(newCorPid,"1",pageSize);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//删除企业银行信息
function delBank(pid){
	$.ajax({
	    url: basePath+"/corporationController/delBank",
	    type: "POST",
	    data:{
	    	pid:pid
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			//删除成功之后,重新加载企业银行信息列表,删除失败,则不重新加载
			var resultMsg=data["msg"]
			var successCode=data["success"]
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
				var newCorPid = $('#newCorPid').val();
	        	var options=$("#corPageSelect option:selected");
				var pageSize = options.val();
				getBankList(newCorPid,"1",pageSize);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//刷新企业银行信息列表
function getBankList(corPid,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/corporationController/getBankList",
	    type: "POST",
	    data:{
	    	corPid:corPid,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var bankListMsg=data["msg"]
			var bankListCode=data["success"]
			
			if(bankListCode == 's'){
				$('#corBankListTbody > tr').remove();
				$('#corBankListTbody').hide();
				$('#pager_list').hide();
				var corBankList = data["corBankList"];
				var pageObj = data["page"];
				
				//页码重新赋值
				$('#upPageInput').val(pageObj.upPage);
				$('#searchPage').val(pageObj.curPage);
				$('#sp_1_pager_list').text(pageObj.totalPage);
				$('#nextPageInput').val(pageObj.nextPage);
				$('#lastPageInput').val(pageObj.endPage);
				$('#totalCountId').text("共 "+pageObj.totalCount+"条");
				$("#corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
				
				//清空填写表单
				$('#accountName').val("");
				$('#account').val("");
				$('#remark').val("");
				$("#bankSelectId").val("");
				$('#bankAccountDiv').hide();
				layui.use('form', function(){
					  var form = layui.form;
					  form.render('select');
				});
				$('#bankAccountDiv').show();
				
				var listLength = corBankList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var corBank = corBankList[i];
						var newhtmlStr = "<tr>" +
								"<td>"+$('#corporationName').val()+"</td>" +
								"<td>"+corBank.accountName+"</td>" +
								"<td>"+corBank.bankName+"</td>" +
								"<td>"+corBank.account+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"toUpdateBank('"+corBank.pid+"')\" class='table_bnt'>修改</a>" +
									"<a href='javascript:void(0)' onclick=\"delBank('"+corBank.pid+"')\" class='table_bnt'>删除</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
					$('#corBankListTbody').append(htmlStr);
				}
				
				$('#corBankListTbody').show();
				$('#pager_list').show();
			}else{
				layer.msg(bankListMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//校验必填项并对必填项为空做提示
function cheackAndShowWarns(tags) {
	var flag = false;
	for(var i=0;i<tags.length;i++){
		var tag = tags[i];
		var elmId = "#"+tag;
		var elmWarnId = elmId+"Warn";
		
		var elmIdVal = $(elmId).val();
		if(elmIdVal == ''){
			$(elmWarnId).show();
			flag = true;
		}else{
			$(elmWarnId).hide();
		}
	}
	return flag;
}


//鼠标自动聚焦到必填空值input
function focusWarnInput(tags) {
	for(var i=0;i<tags.length;i++){
		var tag = tags[i];
		var elmId = "#"+tag;
		var elmIdVal = $(elmId).val();
		if(elmIdVal == ''){
			$(elmId).focus();
			break;
		}
	}
}

