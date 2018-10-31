// ......................................................项目管理新增界面..................................................
var basePath;
var form;
var upload;
var files;
var uploadListIns;
var uploadTotal;
var uploadSuccess;
var type;
$ (function () {
		basePath = $('#basePathId').val();
		
		//初始化设置默认选中的'是否取得预售证'
		type = $('#type').val();
		if(type == 1){
			$('#acquireSaleCertifyRadio2').prop("checked", true);
		}
		
		if(type == 3){
			$('input').attr("readonly","readonly");
			$('#projectInfoButtonDiv').hide();
			$('#addProjectCompositionDiv').hide();
			$('#project_contractNameDiv').hide();
			$('#project_contract_Buttons').hide();
			$('#project_contract_title_div').hide();
			$('#project_progressDiv').hide();
			$('#project_progress_buttons').hide();
			$('#project_budgetDiv').hide();
			$('#project_budget_buttons').hide();
			$('#operateTypeChangeSpan').text('查看项目');
		}
		
		if(type == 2){
			$('#progress_fileSelectList').show();
			$('#progress_fileSelectList2').hide();
			$('#startUploadListAction').show();
			$('#startUploadListAction2').hide();
			$('#operateTypeChangeSpan').text('修改项目');
		}
		
		//渲染上传文件
		layui.use('upload', function(){
			  upload = layui.upload;
			  installLayuiUpload('contract_fileSelectList','contract_fileUploadList','contract_startUploadListAction','contract_fileUploadDiv',basePath+'/projectManageController/uploadContractFile');
			  installLayuiUpload('progress_fileSelectList','progress_fileUploadList','startUploadListAction','progress_fileUploadDiv',basePath+'/projectManageController/uploadProgressFile');
			  installLayuiUpload('budget_fileSelectList','budget_fileUploadList','budget_startUploadListAction','budget_fileUploadDiv',basePath+'/projectManageController/uploadBudgetFile');
		});
		
	    //引入下拉框,并设置值变化时更新隐藏域
		layui.use('form',function(){
			form =layui.form;
			
			//初始化项目基本信息默认不选择项目种类,隐藏局部div
			if($('#pid').val() == ''){
				$('#breed_city_divID').hide();
			}else{
				var proCateGorySelectText = $("#proCateGorySelectId option:selected").text();
				if(proCateGorySelectText == '城市更新'){
					$('#breed_city_divID').show();
				}else{
					$('#breed_city_divID').hide();
				}
			}
			
			//初始化项目进度不选择项目进度状态,隐藏局部div
			if($('#pid').val() == ''){
				$('#progress_saleShowDiv').css('display','none');
			}else{
				var progressSelectIdText = $("#progressSelectId option:selected").text();
				if(progressSelectIdText == '销售'){
					$('#progress_saleShowDiv').css('display','block');
				}else{
					$('#progress_saleShowDiv').css('display','none');
				}
			}
			
			
			
			//省级
			form.on('select(provinceFilter)', function(data){
				  var provinceCode =  data.value;
				  if(provinceCode == ''){
					  installSonArea("citySelectId");
					  installSonArea("regionSelectId");
				  }else{
					  installSonArea("regionSelectId")
					  getSonAreaInfo(provinceCode,"citySelectId");
				  }
				  $('#projectProvice').val(provinceCode);
			});
			
			//市级
			form.on('select(cityFilter)', function(data){
				var cityCode =  data.value;
				if(cityCode == ''){
					installSonArea("regionSelectId");
				}else{
					getSonAreaInfo(cityCode,"regionSelectId");
				}
				$('#projectCity').val(cityCode);
			});
			
			//区级
			form.on('select(regionFilter)', function(data){
				var projectRegion =  data.value;
				$('#projectRegion').val(projectRegion);
			});
			
			//项目公司
			form.on('select(busPrjInfoSelectId)', function(data){
				var busPrjInfo =  data.value;
				$('#businessPrjInfoId').val(busPrjInfo);
			});
			
			//开发商
			form.on('select(developersSelectId)', function(data){
				var developers =  data.value;
				$('#developersId').val(developers);
			});
			
			//项目种类
			form.on('select(proCateGoryFilter)', function(data){
				var proCateGory =  data.value;
				var optionText = data.elem[data.elem.selectedIndex].text;
				
				$('#projectCategory').val(proCateGory);
				
				var elemId = ['projectCategory'];
				cheackAndShowWarns(elemId);
				if(optionText == '城市更新'){
					$('#breed_city_divID').show();
				}else{
					$('#breed_city_divID').hide();
					$('.city_refresh').val('');
				}
			});
			
			//土地获取方式
			form.on('select(landGetWayFilter)', function(data){
				var landGetWay =  data.value;
				$('#landGetWay').val(landGetWay);
				
				var elemId = ['landGetWay'];
				cheackAndShowWarns(elemId);
			});
			
			
			//业态组成
			form.on('select(businessTypeFilter)', function(data){
				var businessType =  data.value;
				$('#add_businessType').val(businessType);
			});
			
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
					}
				}
			});
			
			//证载建面
			form.on('select(certBuildAreaFilter)', function(data){
				var certBuildArea =  data.value;
				$('#progress_cert_build_areaId').val(certBuildArea);
			});
			
			
			//修改,查看下拉框数据回显
			if($('#projectProvice').val() != ''){
				$('#provinceSelectId').val($('#projectProvice').val());
				getSonAreaInfo($('#projectProvice').val(),"citySelectId");
			}
			if($('#projectCity').val() != ''){
				$('#citySelectId').val($('#projectCity').val());
				getSonAreaInfo($('#projectCity').val(),"regionSelectId");
			}
			if($('#projectRegion').val() != ''){
				$('#regionSelectId').val($('#projectRegion').val());
			}
			if($('#businessPrjInfoId').val() != ''){
				$('#busPrjInfoSelect_Id').val($('#businessPrjInfoId').val());
			}
			if($('#developersId').val() != ''){
				$('#developersSelect_Id').val($('#developersId').val());
			}
			if($('#projectCategory').val() != ''){
				$('#proCateGorySelectId').val($('#projectCategory').val());
				form.render('select');
				var proCateGoryVal = $('#proCateGorySelectId').next().find("input").val();
				if(proCateGoryVal == '城市更新'){
					$('#breed_city_divID').show();
				}else{
					$('#breed_city_divID').hide();
					$('.city_refresh').val('');
				}
			}
			if($('#landGetWay').val() != ''){
				$('#landGetWaySelectId').val($('#landGetWay').val());
			}
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
			  
			  //项目基本信息-项目期限时间
			  laydate.render({
				   elem: '#projectTerm'
			  });
			  
			  //项目基本信息-预期开工时间
			  laydate.render({
				  elem: '#expectStartWorkDate'
			  });
			  
			  //项目基本信息-预期开盘时间
			  laydate.render({
				  elem: '#expectOpeningDate'
			  });
			  
			  //项目基本信息-预期完成收地日期
			  laydate.render({
				  elem: '#expectResumptionDate'
			  });
			  
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
			  
			  //预算日期
			  laydate.render({
				  elem: '#budgetDate'
			  });
		 });
		
		//控制头部tab切换
		$('#myTab a').click(function (e) {
		    e.preventDefault();
		    $(this).tab('show');
		});
    
		
		//保存项目基本信息
		$('#projectBaseSubmit').on('click', function(){
			  var elemIds = ['projectName','projectUserArea','projectOccupationArea','projectCategory','landGetWay'];
			  if(cheackAndShowWarns(elemIds)){
				  focusWarnInput(elemIds);
				  return false;
			  }
			  
			  savaProjectBaseInfo();
		});
		
		
		//校验选项-项目基本信息光标失焦校验
		$('#projectName,#projectUserArea,#projectOccupationArea').on('blur', function(){
			var elemId = [$(this).attr('id')];
			cheackAndShowWarns(elemId);
		});
		
		
		//新增业态组成
		$('#add_Makeuup1').on('click', function(){
			$("#projectId").val($('#pid').val());
			$("#busCompoPId").val('');
			$('#businessTypeSelectId').val('');
			form.render('select');
			$('#saleArea').val('');
			$('#salePerMeter').val('');
			$('#compositionArea').val('');
			$('#projectValue').val('');
			
			layer.open({
				type: 1,
				title: ['新增业态组成','font-size:18px;'],
				area: ['590px', '400px'],
				btnAlign: 'c',
				content: $('#add_Makeuup'),
				btn: ['确定', '取消'],
				btn1: function(index, layero){
					layer.close(index);
					if($('#pid').val() == ''){
						layer.msg("请先保存项目基本信息!",{icon: 7,time: 1500});
					}else{
						saveBusinessComposition();
					}
				},
				btn2: function(index, layero){
					layer.close(index);
				}
			});
		});
		
		
		//项目业态组成信息列表刷新按钮
        $('#refresh_table_list').on('click', function(){
        	var pid = $('#pid').val();
        	var options=$('#corPageSelect option:selected');
			var pageSize = options.val();
        	if(pid != ""){
        		getBusinessCompoList(pid,"1",pageSize);
        	}
        });
		
        
        //项目业态组成信息列表-页码输入框
		$('#searchPage').on('keypress', function(){
			if(window.event.keyCode==13){
				var curPage = $('#searchPage').val();
				var options=$("#corPageSelect option:selected");
				var pageSize = options.val();
				var pid = $('#pid').val();
				if(pid != ""){
					getBusinessCompoList(pid,curPage,pageSize);
	        	}
			}
		});
        
		
		//项目业态组成信息列表-每页显示条数下拉事件
		$('#corPageSelect').on('change', function(){
			var curPage = $('#searchPage').val();
			var options=$("#corPageSelect option:selected");
			var pageSize = options.val();
			var pid = $('#pid').val();
			if(pid != ""){
				getBusinessCompoList(pid,curPage,pageSize);
        	}
		});
		
		
		
		
		//项目进度选择文件及文件上传按钮
		$('#startUploadListAction2,#progress_fileSelectList2').on('click', function(){
			 var progressId = $('#progressId').val();
			 var progressStatus = $('#project_progressId').val();
			 if(progressId == ''){
				  layer.msg("请先保存项目进度信息!",{icon: 7,time: 2000});
				  return false;
			 }else if(!cheackProProgress(progressId,progressStatus)){
				 layer.msg("请先保存项目基本信息!",{icon: 7,time: 2000});
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
		
		
		//项目合同附件选择文件和上传保存按钮
		$('#contract_fileSelectList2,#contract_startUploadListAction2').on('click', function(){
			 var projectId = $('#pid').val();
			 if(projectId == ''){
				  layer.msg("请先保存项目基本信息!",{icon: 7,time: 2000});
				  return false;
			 }else{
				 $('#contract_fileSelectList').show();
				 $('#contract_fileSelectList2').hide();
				 $('#contract_startUploadListAction').show();
				 $('#contract_startUploadListAction2').hide();
			 }
		});
		
		
		
		//项目进度附件列表刷新按钮
        $('#contract_refresh_table_list').on('click', function(){
        	var projectId = $('#pid').val();
        	var options=$('#contract_corPageSelect option:selected');
			var pageSize = options.val();
        	if(projectId != ""){
        		getContractList(projectId,"1",pageSize);
        	}
        });
		
        //项目进度附件列表-页码输入框
		$('#contract_searchPage').on('keypress', function(){
			if(window.event.keyCode==13){
				var curPage = $('#contract_searchPage').val();
				var options=$("#contract_corPageSelect option:selected");
				var pageSize = options.val();
				var projectId = $('#pid').val();
				if(projectId != ""){
					getContractList(projectId,curPage,pageSize);
	        	}
			}
		});
        
		//项目进度附件列表-每页显示条数下拉事件
		$('#contract_corPageSelect').on('change', function(){
			var curPage = $('#contract_searchPage').val();;
			var options=$("#contract_corPageSelect option:selected");
			var pageSize = options.val();
			var projectId = $('#pid').val();
			if(projectId != ""){
				getContractList(projectId,curPage,pageSize);
        	}
			
		});
		
		
		//项目预算选择文件及开始上传,保存按钮
		$('#budget_fileSelectList2,#budget_startUploadListAction2,#budget_progress_submit').on('click', function(){
			 var projectId = $('#pid').val();
			 var elemIds = ['budgetName'];
			 if(projectId == ''){
				 layer.msg("请先保存项目基本信息!",{icon: 7,time: 2000});
				 return false;
			 }
			 
			 if(cheackAndShowWarns(elemIds)){
				 focusWarnInput(elemIds);
				 return false;
			 }
			 
			 var curIdName = $(this).attr('id');
			 if(curIdName == 'budget_fileSelectList2' || curIdName == 'budget_startUploadListAction2'){
				 $('#budget_fileSelectList').show();
				 $('#budget_fileSelectList2').hide();
				 $('#budget_startUploadListAction').show();
				 $('#budget_startUploadListAction2').hide();
			 }
			 
			 if(curIdName == 'budget_progress_submit'){
				 saveBudget();
			 }
			 
		});
		
		
		//预算名称失去焦点
		$('#budgetName').on('blur', function(){
			 var elemIds = ['budgetName'];
			 if(cheackAndShowWarns(elemIds)){
				 return false;
			 }
		});
		
		
		
		//项目预算附件列表刷新按钮
        $('#budget_refresh_table_list').on('click', function(){
        	var projectId = $('#pid').val();
        	var options=$('#budget_corPageSelect option:selected');
			var pageSize = options.val();
        	if(projectId != ""){
        		getBudgetList(projectId,"1",pageSize);
        	}
        });
		
        //项目进度附件列表-页码输入框
		$('#budget_searchPage').on('keypress', function(){
			if(window.event.keyCode==13){
				var curPage = $('#budget_searchPage').val();
				var options=$("#budget_corPageSelect option:selected");
				var pageSize = options.val();
				var projectId = $('#pid').val();
				if(projectId != ""){
					getBudgetList(projectId,curPage,pageSize);
	        	}
			}
		});
        
		//项目进度附件列表-每页显示条数下拉事件
		$('#budget_corPageSelect').on('change', function(){
			var curPage = $('#budget_searchPage').val();;
			var options=$("#budget_corPageSelect option:selected");
			var pageSize = options.val();
			var projectId = $('#pid').val();
			if(projectId != ""){
				getBudgetList(projectId,curPage,pageSize);
        	}
			
		});
		
		
		
		
		//修改和查看项目业态,合同,进度,预算列表数据回显
		var compositionOptions=$('#corPageSelect option:selected');
		var compositionPageSize = compositionOptions.val();
		var contract_options=$("#contract_corPageSelect option:selected");
		var contract_pageSize = contract_options.val();
		var progress_options=$("#progress_corPageSelect option:selected");
		var progress_pageSize = progress_options.val();
		var budget_options=$("#budget_corPageSelect option:selected");
		var budget_pageSize = budget_options.val();
		if($('#pid').val() != ""){
			getBusinessCompoList($('#pid').val(),"1",compositionPageSize);
			getContractList($('#pid').val(),"1",contract_pageSize);
			getProProgressList($('#pid').val(),"1",progress_pageSize);
			getBudgetList($('#pid').val(),"1",budget_pageSize);
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


//项目合同附件列表首页、上一页、下一页、尾页按钮
function getContractListByPage(idCode) {
	var projectId = $('#pid').val();
	var options=$("#contract_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	if(projectId != ""){
		getContractList(projectId,curPage,pageSize);
	}
}

//项目预算附件列表首页、上一页、下一页、尾页按钮
function getBudgetListByPage(idCode) {
	var projectId = $('#pid').val();
	var options=$("#budget_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	if(projectId != ""){
		getBudgetList(projectId,curPage,pageSize);
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

//清空项目进度数据表单
function emptyProgressFrom(){
	$('#progress_expectResumptionDate').val('');
	$('#realityResumptionDate').val('');
	$('#expectConstructionDate').val('');
	$('#realityConstructionDate').val('');
	$('#expectSaleCertifyDate').val('');
	$('#expectLiquidationDate').val('');
	$('#progressSelectId').val('');
	$('#realityLiquidationDate').val('');
	$('#realitySaleCertifyDate').val('');
	$('#certBuildAreaSelect').val('');
	$('#hasPushValue').val('');
	$('#progress_remark').val('');
	form.render();
}

//保存项目业态组成信息
function saveBusinessComposition() {
	var newProjectPid = $('#pid').val();
	if(newProjectPid == ''){
		layer.msg("请先保存项目基本信息!",{icon: 7,time: 1500});
	}else{
		$.ajax({
		    url: basePath+"/projectManageController/saveBusCompo",
		    type: "POST",
		    data:$('#add_busCompoForm').serialize(),
		    dataType:"json",
		    async: false,
			success : function(data) {
				var resultMsg=data["msg"];
				var successCode=data["success"];
				
				if(successCode == 's'){
					layer.msg(resultMsg,{icon: 1,time: 1000});
					var projectPid = $('#pid').val();
		        	var options=$("#corPageSelect option:selected");
					var pageSize = options.val();
					getBusinessCompoList(projectPid,"1",pageSize);
				}else{
					layer.msg(resultMsg,{icon: 7,time: 1500});
				}
				
			}
		});
	}
}


//项目业态组成信息列表首页、上一页、下一页、尾页按钮
function getListByPage(idCode) {
	var pid = $('#pid').val();
	var options=$("#corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	if(pid != ""){
		getBusinessCompoList(pid,curPage,pageSize);
	}
}


//刷新项目业态组成信息列表
function getBusinessCompoList(projectId,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/projectManageController/getBusComPoList",
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
				$('#businessTbodyId > tr').remove();
				$('#businessTbodyId').hide();
				$('#pager_list').hide();
				var busCompoList = data["busCompoList"];
				var pageObj = data["page"];
				
				//页码重新赋值
				$('#upPageInput').val(pageObj.upPage);
				$('#searchPage').val(pageObj.curPage);
				$('#sp_1_pager_list').text(pageObj.totalPage);
				$('#nextPageInput').val(pageObj.nextPage);
				$('#lastPageInput').val(pageObj.endPage);
				$('#totalCountId').text("共 "+pageObj.totalCount+"条");
				$("#corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
				
				var listLength = busCompoList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var busCompo = busCompoList[i];
						var newhtmlStr = "<tr>" +
								"<td>"+busCompo.sortNum+"</td>" +
								"<td>"+busCompo.businessTypeName+"</td>" +
								"<td>"+busCompo.compositionArea+"</td>" +
								"<td>"+busCompo.saleArea+"</td>" +
								"<td>"+busCompo.salePerMeter+"</td>" +
								"<td>"+busCompo.projectValue+"</td>";
								;
						if(type != 3){
							newhtmlStr = newhtmlStr +
							"<td>" +
								"<a href='javascript:void(0)' onclick=\"toUpdateBusCompo('"+busCompo.pid+"')\" class='table_bnt'>修改</a>" +
								"<a href='javascript:void(0)' onclick=\"delBusCompo('"+busCompo.pid+"')\" class='table_bnt'>删除</a>" +
							"</td>" +
							"</tr>";
						}else{
							newhtmlStr = newhtmlStr +"<td></td></tr>";
						}
						htmlStr = htmlStr + newhtmlStr;
					}
					$('#businessTbodyId').append(htmlStr);
				}
				
				$('#businessTbodyId').show();
				$('#pager_list').show();
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//刷新项目合同附件信息列表
function getContractList(projectId,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/projectManageController/getContractList",
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
				$('#contract_businessTbodyId > tr').remove();
				$('#contract_businessTbodyId').hide();
				$('#contract_pager_list').hide();
				var contractList = data["contractList"];
				var pageObj = data["page"];
				
				//页码重新赋值
				$('#contract_upPageInput').val(pageObj.upPage);
				$('#contract_searchPage').val(pageObj.curPage);
				$('#contract_sp_1_pager_list').text(pageObj.totalPage);
				$('#contract_nextPageInput').val(pageObj.nextPage);
				$('#contract_lastPageInput').val(pageObj.endPage);
				$('#contract_totalCountId').text("共 "+pageObj.totalCount+"条");
				$("#contract_corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
				
				var listLength = contractList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var contract = contractList[i];
						var newhtmlStr = "<tr>" +
								"<td>"+contract.contractName+"</td>" +
								"<td>"+contract.contractFullName+"</td>" +
								"<td>"+contract.uploadTime+"</td>" +
								"<td>"+contract.userName+"</td>";
								
						if(type != 3){
							newhtmlStr = newhtmlStr +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"showFile('"+contract.fileUrl+"','"+contract.contractFullName+"')\" class='table_bnt'>查看</a>" +
									"<a href='javascript:void(0)' onclick=\"downLoadFile('"+contract.fileUrl+"','"+contract.contractFullName+"')\" class='table_bnt'>下载</a>" +
									"<a href='javascript:void(0)' onclick=\"delContract('"+contract.contractPid+"')\" class='table_bnt datum_delt'>删除</a>" +
								"</td>" +
								"</tr>";
						}else{
							newhtmlStr = newhtmlStr + 
							"<td>" +
								"<a href='javascript:void(0)' onclick=\"showFile('"+contract.fileUrl+"','"+contract.contractFullName+"')\" class='table_bnt'>查看</a>" +
								"<a href='javascript:void(0)' onclick=\"downLoadFile('"+contract.fileUrl+"','"+contract.contractFullName+"')\" class='table_bnt'>下载</a>" +
							"</td>" +
							"</tr>";
						}
						htmlStr = htmlStr + newhtmlStr;
					}
					$('#contract_businessTbodyId').append(htmlStr);
				}
				
				$('#contract_businessTbodyId').show();
				$('#contract_pager_list').show();
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
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
								"<td>"+operatorName+"</td>";
								
						if(type != 3){
							newhtmlStr = newhtmlStr +
							"<td>" +
								"<a href='javascript:void(0)' onclick=\"showFile('"+progressFileUrl+"','"+progressFileName+"')\" class='table_bnt'>查看</a>" +
								"<a href='javascript:void(0)' onclick=\"downLoadFile('"+progressFileUrl+"','"+progressFileName+progressFileType+"')\" class='table_bnt'>下载</a>" +
								"<a href='javascript:void(0)' onclick=\"delProgress('"+progressFilePid+"')\" class='table_bnt datum_delt'>删除</a>" +
							"</td>" +
							"</tr>";
						}else{
							newhtmlStr = newhtmlStr +
							"<td>" +
								"<a href='javascript:void(0)' onclick=\"showFile('"+progressFileUrl+"','"+progressFileName+"')\" class='table_bnt'>查看</a>" +
								"<a href='javascript:void(0)' onclick=\"downLoadFile('"+progressFileUrl+"','"+progressFileName+progressFileType+"')\" class='table_bnt'>下载</a>" +
							"</td>" +
							"</tr>";
						}
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

//刷新项目预算列表
function getBudgetList(projectId,curPage,pageSize){
	
	$.ajax({
	    url: basePath+"/projectManageController/getBudgetList",
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
				$('#budget_businessTbodyId > tr').remove();
				$('#budget_businessTbodyId').hide();
				$('#budget_pager_list').hide();
				var budgetList = data["budgetList"];
				var pageObj = data["page"];
				
				//页码重新赋值
				$('#budget_upPageInput').val(pageObj.upPage);
				$('#budget_searchPage').val(pageObj.curPage);
				$('#budget_sp_1_pager_list').text(pageObj.totalPage);
				$('#budget_nextPageInput').val(pageObj.nextPage);
				$('#budget_lastPageInput').val(pageObj.endPage);
				$('#budget_totalCountId').text("共 "+pageObj.totalCount+"条");
				$("#budget_corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
				
				var listLength = budgetList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var budget = budgetList[i];
						var budgetName = budget.budgetName == null?"":budget.budgetName;
						var projectProfitBudget = budget.projectProfitBudget == null?"":budget.projectProfitBudget;
						var budgetDateStr = budget.budgetDateStr == null?"":budget.budgetDateStr;
						var budgetGist = budget.budgetGist == null?"":budget.budgetGist;
						var fileName = budget.fileName == null?"":budget.fileName;
						var fileUrl = budget.fileUrl == null?"":budget.fileUrl;
						var operatorName = budget.operatorName == null?"":budget.operatorName;
						var operatorTime = budget.operatorTime == null?"":budget.operatorTime;
						var budgetAccessoryPid = budget.budgetAccessoryPid == null?"":budget.budgetAccessoryPid;
						
						var newhtmlStr = "<tr>" +
								"<td>"+budgetName+"</td>" +
								"<td>"+projectProfitBudget+"</td>" +
								"<td>"+budgetDateStr+"</td>" +
								"<td>"+budgetGist+"</td>" +
								"<td>"+fileName+"</td>" +
								"<td>"+operatorName+"</td>" +
								"<td>"+operatorTime+"</td>";
								
						
						if(type != 3){
							newhtmlStr = newhtmlStr +
							"<td>" +
								"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
								"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
								"<a href='javascript:void(0)' onclick=\"delBudget('"+budgetAccessoryPid+"')\" class='table_bnt datum_delt'>删除</a>" +
							"</td>" +
							"</tr>";
						}else{
							newhtmlStr = newhtmlStr +
							"<td>" +
								"<a href='javascript:void(0)' onclick=\"showFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>查看</a>" +
								"<a href='javascript:void(0)' onclick=\"downLoadFile('"+fileUrl+"','"+fileName+"')\" class='table_bnt'>下载</a>" +
							"</td>" +
							"</tr>";
						}
						htmlStr = htmlStr + newhtmlStr;
					}
					$('#budget_businessTbodyId').append(htmlStr);
				}
				
				$('#budget_businessTbodyId').show();
				$('#budget_pager_list').show();
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}


//删除项目预算状态
function delBudget(pid){
	$.ajax({
	    url: basePath+"/projectManageController/delBudget",
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
			
			var options=$("#budget_corPageSelect option:selected");
			var pageSize = options.val();
			getBudgetList($('#pid').val(),"1",pageSize);
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


//删除项目合同附件
function delContract(pid){
	$.ajax({
	    url: basePath+"/projectManageController/delContract",
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
			
			var options=$("#contract_corPageSelect option:selected");
			var pageSize = options.val();
			getContractList($('#pid').val(),"1",pageSize);
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




//修改项目业态组成信息
function toUpdateBusCompo(pid){
	$("#projectId").val($('#pid').val());
	$("#busCompoPId").val(pid);
	
	$.ajax({
	    url: basePath+"/projectManageController/toUpdateBusCompo",
	    type: "POST",
	    data:{pid:pid},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"];
			var successCode=data["success"];
			
			if(successCode == 's'){
				var busCompoInfo = data["busCompoInfo"];
				$('#businessTypeSelectId').val(busCompoInfo.businessType);
				form.render('select');
				$('#saleArea').val(busCompoInfo.saleArea);
				$('#salePerMeter').val(busCompoInfo.salePerMeter);
				$('#compositionArea').val(busCompoInfo.compositionArea);
				$('#projectValue').val(busCompoInfo.projectValue);
				
				layer.open({
					type: 1,
					title: ['修改业态组成','font-size:18px;'],
					area: ['590px', '400px'],
					btnAlign: 'c',
					content: $('#add_Makeuup'),
					btn: ['确定', '取消'],
					btn1: function(index, layero){
						layer.close(index);
						saveBusinessComposition();
					},
					btn2: function(index, layero){
						layer.close(index);
					}
				});
				
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1500});
			}
		}
	});
}

//删除项目业态组成信息
function delBusCompo(pid){
	$.ajax({
	    url: basePath+"/projectManageController/delBusComPo",
	    type: "POST",
	    data:{pid:pid},
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"];
			var successCode=data["success"];
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
				var options=$("#corPageSelect option:selected");
				var pageSize = options.val();
				getBusinessCompoList($('#pid').val(),"1",pageSize);
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1500});
			}
		}
	});
}

//保存项目基本信息
function savaProjectBaseInfo() {
	$.ajax({
	    url: basePath+"/projectManageController/saveProInfo",
	    type: "POST",
	    data:$('#projectBaseForm').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"];
			var successCode=data["success"];
			
			if(successCode == 's'){
				var pid = data["pid"];
				var projectCode = data["projectCode"];
				
				//显示合同附件选择及上传按钮
				$('#contract_fileSelectList').show();
				$('#contract_fileSelectList2').hide();
				$('#contract_startUploadListAction').show();
				$('#contract_startUploadListAction2').hide();
				
				$('#projectCode').val(projectCode);
				$('#pid').val(pid);
				layer.msg(resultMsg,{icon: 1,time: 1000});
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1500});
			}
		}
	});
}



//获取市级或区级信息
function getSonAreaInfo(parentCode,elemId){
	if(parentCode != ''){
		$.ajax({
		    url: basePath+"/sysAreaInfoController/getSonAreaList",
		    type: "POST",
		    data:{
		    	parentCode:parentCode
		    },
		    dataType:"json",
		    async: false,
			success : function(data) {
				var resultMsg=data["msg"];
				var successCode=data["success"];
				
				if(successCode == 's'){
					var sonAreaList = data["sonAreaList"];
					var htmlStr = "<option value=''>---请选择---</option>";
					$('#'+elemId+'> option').remove();
					$('#projectAddress').hide();
					
					if(sonAreaList.length > 0){
						for(var i=0;i < sonAreaList.length;i++){
							var sonArea = sonAreaList[i];
							var newHtmlStr = "<option value='"+sonArea.areaCode+"'>"+sonArea.areaName+"</option>";
							htmlStr = htmlStr + newHtmlStr;
						}
					}
					
					$('#'+elemId).append(htmlStr);
					$('#'+elemId).val("");
					form.render('select');
					$('#projectAddress').show();
				}
			}
		});
	}
}

//重新初始化市,区级显示
function installSonArea(elemId){
	var htmlStr = "<option value=''>---请选择---</option>";
	$('#'+elemId+'> option').remove();
	$('#projectAddress').hide();
	$('#'+elemId).append(htmlStr);
	$('#'+elemId).val("");
	form.render('select');
	$('#projectAddress').show();
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
	    	//项目进度
	    	uploadListIns.config.data.progressId = $('#progressId').val();
	    	
	    	//项目合同
	    	uploadListIns.config.data.contractName = $('#project_contractName').val();
	    	uploadListIns.config.data.projectPid = $('#pid').val();
	    	
	    	//项目预算
	    	uploadListIns.config.data.projectBudgetPid = $('#projectBudgetPid').val();
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
	        
	        //刷新项目合同附件列表
	        if(res.refreshType == 'contract'){
	        	var projectPid = $('#pid').val();
	        	var options=$("#contract_corPageSelect option:selected");
	        	var pageSize = options.val();
	        	getContractList(projectPid,"1",pageSize);
	        }
	        
	        //刷新项目预算记录列表
	        if(res.refreshType == 'budget'){
	        	var projectPid = $('#pid').val();
	        	var options=$("#budget_corPageSelect option:selected");
	        	var pageSize = options.val();
	        	getBudgetList(projectPid,"1",pageSize);
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
