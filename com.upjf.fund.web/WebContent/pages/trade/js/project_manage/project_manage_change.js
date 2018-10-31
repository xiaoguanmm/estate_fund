// ......................................................项目管理变更项目进度界面..................................................
var basePath;
var form;
var upload;
var files;
var uploadListIns;
var uploadTotal;
var uploadSuccess;
$ (function () {
		basePath = $('#basePathId').val();
		
		 $('#progress_fileSelectList').show();
		 $('#progress_fileSelectList2').hide();
		 $('#startUploadListAction').show();
		 $('#startUploadListAction2').hide();
		
		//渲染上传文件
		layui.use('upload', function(){
			  upload = layui.upload;
			  installLayuiUpload('progress_fileSelectList','progress_fileUploadList','startUploadListAction','progress_fileUploadDiv',basePath+'/projectManageController/uploadProgressFile');
		});
		
	    //引入下拉框,并设置值变化时更新隐藏域
		layui.use('form',function(){
			form =layui.form;
			
			//项目进度状态
			form.on('select(progressFilter)', function(data){
				var progress =  data.value;
				$('#project_progressId').val(progress);
				
				var elemId = ['project_progressId'];
				cheackAndShowWarns(elemId);
				
				var optionText = data.elem[data.elem.selectedIndex].text;
				if(optionText == '销售'){
					$('#progress_saleShowDiv').css('display','block');
				}else{
					$('#progress_saleShowDiv').css('display','none');
				}
				
				var progressId = $('#progressId').val();
				if( progressId != ''){
					var result = cheackProProgress(progressId,progress);
					if(!result){
						 $('#progress_fileSelectList2').show();
						 $('#progress_fileSelectList').hide();
						 $('#startUploadListAction2').show();
						 $('#startUploadListAction').hide();
					}else{
						 $('#progress_fileSelectList').show();
						 $('#progress_fileSelectList2').hide();
						 $('#startUploadListAction').show();
						 $('#startUploadListAction2').hide();
					}
				}
			});
			
			//证载建面
			form.on('select(certBuildAreaFilter)', function(data){
				var certBuildArea =  data.value;
				$('#progress_cert_build_areaId').val(certBuildArea);
			});
			
			
			//数据回显
			if($('#project_progressId').val() != ''){
				$('#progressSelectId').val($('#project_progressId').val());
				form.render('select');
				var progressVal = $('#progressSelectId').next().find("input").val();
				if(progressVal == '销售'){
					$('#progress_saleShowDiv').css('display','block');
				}else{
					$('#progress_saleShowDiv').css('display','none');
					$('.progress_sale_refresh').val('');
				}
			}
			if($('#progress_cert_build_areaId').val() != ''){
				$('#certBuildAreaSelect').val($('#progress_cert_build_areaId').val());
			}
			form.render('select');
			
		})
		
		//引入layui时间控件
		layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  
			  //项目进度-预期完成收地日期
			  laydate.render({
				  elem: '#progress_expectResumptionDate'
			  });
			  
			  //项目进度-实际完成收地时间
			  laydate.render({
				  elem: '#realityResumptionDate'
			  });
			  
			  //项目进度-预期开始施工日期
			  laydate.render({
				  elem: '#expectConstructionDate'
			  });
			  
			  //项目进度-实际开始施工日期
			  laydate.render({
				  elem: '#realityConstructionDate'
			  });
			  
			  //项目进度-预期清算日期
			  laydate.render({
				  elem: '#expectLiquidationDate'
			  });
			  
			  //项目进度-实际清算日期
			  laydate.render({
				  elem: '#realityLiquidationDate'
			  });
			  
			  //项目进度-实际取得预售证日期
			  laydate.render({
				  elem: '#realitySaleCertifyDate'
			  });
			  
			  //项目进度-预期取得预售证日期
			  laydate.render({
				  elem: '#expectSaleCertifyDate'
			  });
		 });
		
		//控制头部tab切换
		$('#myTab a').click(function (e) {
		    e.preventDefault();
		    $(this).tab('show');
		});
    
		
		//保存项目进度信息
		$('#project_progress_submit').on('click', function(){
			  var elemIds = ['project_progressId'];
			  if(cheackAndShowWarns(elemIds)){
				  return false;
			  }
			  if($('#pid').val() == ''){
				  layer.msg("请先保存项目基本信息!",{icon: 7,time: 2000});
				  return false;
			  }
			  
			  $('#progress_ProjectId').val($('#pid').val());
			  
			  saveProProgress();
		});
		
		
		
		//项目进度选择文件及文件上传按钮
		$('#startUploadListAction2,#progress_fileSelectList2').on('click', function(){
			 var progressId = $('#progressId').val();
			 var progressStatus = $('#project_progressId').val();
			 if(progressId == ''){
				  layer.msg("请先保存项目进度信息!",{icon: 7,time: 2000});
				  return false;
			 }else if(!cheackProProgress(progressId,progressStatus)){
				 layer.msg("请先保存项目进度信息!",{icon: 7,time: 2000});
				 $('#progress_fileSelectList').hide();
				 $('#progress_fileSelectList2').show();
				 return false;
			 }else{
				 $('#progress_fileSelectList').show();
				 $('#progress_fileSelectList2').hide();
				 $('#startUploadListAction').show();
				 $('#startUploadListAction2').hide();
			 }
		});
      
		
		//项目进度附件列表刷新按钮
        $('#progress_refresh_table_list').on('click', function(){
        	var projectId = $('#pid').val();
        	var options=$('#progress_corPageSelect option:selected');
			var pageSize = options.val();
        	if(projectId != ""){
        		getProProgressList(projectId,"1",pageSize);
        	}
        });
		
        //项目进度附件列表-页码输入框
		$('#progress_searchPage').on('keypress', function(){
			if(window.event.keyCode==13){
				var curPage = $('#progress_searchPage').val();
				var options=$("#progress_corPageSelect option:selected");
				var pageSize = options.val();
				var projectId = $('#pid').val();
				if(projectId != ""){
					getProProgressList(projectId,curPage,pageSize);
	        	}
			}
		});
        
		//项目进度附件列表-每页显示条数下拉事件
		$('#progress_corPageSelect').on('change', function(){
			var curPage = $('#progress_searchPage').val();;
			var options=$("#progress_corPageSelect option:selected");
			var pageSize = options.val();
			var projectId = $('#pid').val();
			if(projectId != ""){
				getProProgressList(projectId,curPage,pageSize);
        	}
			
		});
		
		
		var progress_options=$("#progress_corPageSelect option:selected");
		var progress_pageSize = progress_options.val();
		if($('#progress_ProjectId').val() != ""){
			getProProgressList($('#progress_ProjectId').val(),"1",progress_pageSize);
		}
      
})

//项目进度附件列表首页、上一页、下一页、尾页按钮
function getProgressListByPage(idCode) {
	var projectId = $('#pid').val();
	var options=$("#progress_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	if(projectId != ""){
		getProProgressList(projectId,curPage,pageSize);
	}
}



//校验变动的项目进度信息是否与最新一条一致
function cheackProProgress(pid,projectProgress) {
	var isTheSame = false;
	$.ajax({
	    url: basePath+"/projectManageController/cheackProProgress",
	    type: "POST",
	    data:{pid:pid,projectProgress:projectProgress},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"];
			var successCode=data["success"];
			
			if(successCode == 's'){
				isTheSame = data["isTheSame"];
			}
		}
	});
	
	return isTheSame;
}

//保存项目进度信息表
function saveProProgress() {
	var projectProgress = $('#project_progressId').val();
	var progressId = $('#progressId').val();
	
	var resultVal = cheackProProgress(progressId,projectProgress);
	if(!resultVal){
		$('#progressId').val('');
	}
	
	$.ajax({
	    url: basePath+"/projectManageController/saveProProgress",
	    type: "POST",
	    data:$('#project_progress_form').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"];
			var successCode=data["success"];
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
				var progressId=data["progressId"];
				$('#progressId').val(progressId);
				
				var projectPid = $('#pid').val();
	        	var options=$("#progress_corPageSelect option:selected");
				var pageSize = options.val();
				
				//显示进度附件选择和上传按钮
				$('#progress_fileSelectList').show();
				$('#progress_fileSelectList2').hide();
				$('#startUploadListAction').show();
				$('#startUploadListAction2').hide();
				
				uploadListIns.config.progressId = progressId;
				//刷新项目进度信息列表
				getProProgressList(projectPid,"1",pageSize);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1500});
			}
			
		}
	});
}




//刷新项目进度附件信息列表
function getProProgressList(projectId,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/projectManageController/getProProgressList",
	    type: "POST",
	    data:{
	    	projectId:projectId,
	    	curPage:curPage,
	    	pageSize:pageSize
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var successCode=data["success"]
			
			if(successCode == 's'){
				$('#progress_businessTbodyId > tr').remove();
				$('#progress_businessTbodyId').hide();
				$('#progress_pager_list').hide();
				var progressList = data["progressFileList"];
				var pageObj = data["page"];
				
				//页码重新赋值
				$('#progress_upPageInput').val(pageObj.upPage);
				$('#progress_searchPage').val(pageObj.curPage);
				$('#progress_sp_1_pager_list').text(pageObj.totalPage);
				$('#progress_nextPageInput').val(pageObj.nextPage);
				$('#progress_lastPageInput').val(pageObj.endPage);
				$('#progress_totalCountId').text("共 "+pageObj.totalCount+"条");
				$("#progress_corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
				
				var listLength = progressList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var proProgress = progressList[i];
						var progressStatusName  = proProgress.progressStatusName == null ?"":proProgress.progressStatusName;
						var progressFileName  = proProgress.progressFileName == null ?"":proProgress.progressFileName;
						var progressRemark  = proProgress.progressRemark == null ?"":proProgress.progressRemark;
						var progressUpdateTime  = proProgress.progressUpdateTime == null ?"":proProgress.progressUpdateTime;
						var operatorName  = proProgress.operatorName == null ?"":proProgress.operatorName;
						var progressFileUrl  = proProgress.progressFileUrl == null ?"":proProgress.progressFileUrl;
						var progressFilePid  = proProgress.progressFilePid == null ?"":proProgress.progressFilePid;
						var progressFileType  = proProgress.progressFileType == null ?"":"."+proProgress.progressFileType;
						var newhtmlStr = "<tr>" +
								"<td>"+progressStatusName+"</td>" +
								"<td>"+progressFileName+"</td>" +
								"<td>"+progressRemark+"</td>" +
								"<td>"+progressUpdateTime+"</td>" +
								"<td>"+operatorName+"</td>"+
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+progressFileUrl+"','"+progressFileName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+progressFileUrl+"','"+progressFileName+progressFileType+"')\" class='table_bnt'>下载</a>" +
									"<a href='javascript:void(0)' onclick=\"delProgress('"+progressFilePid+"')\" class='table_bnt datum_delt'>删除</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
					$('#progress_businessTbodyId').append(htmlStr);
				}
				
				$('#progress_businessTbodyId').show();
				$('#progress_pager_list').show();
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//删除项目进度状态
function delProgress(pid){
	$.ajax({
	    url: basePath+"/projectManageController/delProgress",
	    type: "POST",
	    data:{pid:pid},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"];
			var successCode=data["success"];
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1500});
			}
			
			var options=$("#progress_corPageSelect option:selected");
			var pageSize = options.val();
			getProProgressList($('#pid').val(),"1",pageSize);
		}
	});
}



//下载项目进度附件
function downLoadFile(fileUrl,fileName){
	if(fileUrl != ''){
		window.location.href=basePath+"/estateFundFile/downloadAccessory?path="+encodeURI(fileUrl)+"&fileName="+encodeURI(fileName);
	}else{
		layer.msg("尚未上传文件!",{icon: 7,time: 1500});
	}
}

//预览图片
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


//上传附件
function installLayuiUpload(elemId,demoListViewId,bindActionId,fileUploadDivId,uploadUrl){
	var demoListView = $('#'+demoListViewId);
	  uploadListIns = upload.render({
	    elem: '#'+elemId,
	    url: uploadUrl,
	    accept: 'file',
	    multiple: true,
	    auto: false,
	    bindAction: '#'+bindActionId,
	    choose: function(obj){   
	    	if(uploadTotal == uploadSuccess && uploadSuccess !='' && uploadTotal !='' && uploadSuccess != null && uploadTotal != null){
	        	for(var p in files){
	        		delete files[p];
	        	}
		    	demoListView.find('tr').remove();
			    uploadListIns.config.elem.next()[0].value = '';
			    uploadTotal = '';
			    uploadSuccess = '';
	        }
	    	files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	    	//读取本地文件
	    	obj.preview(function(index, file, result){
	    		var tr = $(['<tr id="upload-'+ index +'">',
	    		            '<td>'+ file.name +'</td>',
	    		            '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
	    		            '<td>等待上传</td>',
	    		            '<td>',
	           					'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
	            				'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
	            			'</td>',
	          				'</tr>'].join(''));
	        
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	        	obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	        	delete files[index]; //删除对应的文件
	        	tr.remove();
	        	uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	      });
	    	$('#'+fileUploadDivId).css('display','block');
	    },
	    before: function(obj){
	    	uploadListIns.config.data.progressId = $('#progressId').val();
	    },
	    done: function(res, index, upload){
	      if(res.success == 's'){ //上传成功
	        var tr = demoListView.find('tr#upload-'+ index);
	        tds = tr.children();
	        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
	        tds.eq(3).html(''); 					
	        
	        //刷新项目进度附件列表
	        if(res.refreshType == 'progress'){
	        	var projectPid = $('#pid').val();
	        	var options=$("#progress_corPageSelect option:selected");
	        	var pageSize = options.val();
	        	getProProgressList(projectPid,"1",pageSize);
	        }
	        return ; 
	      }
	      
	      this.error(index, upload);
	    },
	    allDone: function(obj){ //当文件全部被提交后，才触发
	        uploadTotal = obj.total;
	        uploadSuccess = obj.successful;
	    },
	    error: function(index, upload){
	       var tr = demoListView.find('tr#upload-'+ index);
	       tds = tr.children();
	       tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	       tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  });
}
