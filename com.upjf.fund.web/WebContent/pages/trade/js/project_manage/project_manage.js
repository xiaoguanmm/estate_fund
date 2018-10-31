var form;
var basePath;
$ (function () {
	basePath = $('#basePathId').val();
	
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
		
		//省市区
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
		
		//项目种类
		form.on('select(proCateGoryFilter)', function(data){
			var proCateGory =  data.value;
			$('#projectCategory').val(proCateGory);
		});
		
		//土地获取方式
		form.on('select(landGetWayFilter)', function(data){
			var landGetWay =  data.value;
			$('#landGetWay').val(landGetWay);
		});
		
		//项目进度状态
		form.on('select(progressFilter)', function(data){
			var progress =  data.value;
			$('#project_progressId').val(progress);
		});
		
		
		//初始化及回显
		var projectProviceVal = $('#projectProvice').val();
		var projectCityVal = $('#projectCity').val();
		var projectRegionVal = $('#projectRegion').val();
		var projectCategoryVal = $('#projectCategory').val();
		var landGetWayVal = $('#landGetWay').val();
		var progressIdVal = $('#project_progressId').val();
		
		if(projectProviceVal != ''){
			$('#provinceSelectId').val(projectProviceVal);
			getSonAreaInfo(projectProviceVal,"citySelectId");
		}
		if(projectCityVal != ''){
			$('#citySelectId').val(projectCityVal);
			getSonAreaInfo(projectCityVal,"regionSelectId");
		}
		if(projectRegionVal != ''){
			$('#regionSelectId').val(projectRegionVal);
		}
		if(projectCategoryVal != ''){
			$('#proCateGorySelectId').val(projectCategoryVal);
		}
		if(landGetWayVal != ''){
			$('#landGetWaySelectId').val(landGetWayVal);
		}
		if(progressIdVal != ''){
			$('#progressSelectId').val(progressIdVal);
		}
		form.render('select');
		
	})
	
	
	//项目信息列表-查询按钮
	$('#projectSearchButtonId').on('click', function(){
		$('#formPageSize').val("");
		$('#formCurPage').val("");
		$('#projectSearchFormId').submit();
	});
	
	//项目信息列表-重置按钮
	$('#projectResetButtonId').on('click', function(){
		$('#projectName').val('');
		$('#projectCompany').val('');
		
		$('#projectProvice').val('');
		$('#projectCity').val('');
		$('#projectRegion').val('');
		$('#projectCategory').val('');
		$('#landGetWay').val('');
		$('#project_progressId').val('');
		
		$('#provinceSelectId').val('');
		$('#citySelectId').val('');
		$('#regionSelectId').val('');
		$('#proCateGorySelectId').val('');
		$('#landGetWaySelectId').val('');
		$('#progressSelectId').val('');
		form.render('select');
	});
	
	
	//项目管理信息列表-刷新按钮
	$('#project_refresh_table_list').on('click', function(){
		window.location.href=basePath+"/projectManageController//proList";
	});	
	
	//项目管理信息列表-页码输入框
	$('#project_searchPage').on('keypress', function(){
		if(window.event.keyCode==13){
			var searchPageVal = $('#project_searchPage').val();
			var options=$("#project_corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formCurPage').val(searchPageVal);
			$('#formPageSize').val(optionsVal);
			$('#projectSearchFormId').submit();
		}
	});
	
	
	//项目管理信息列表-每页显示条数下拉事件
	$('#project_corPageSelect').on('change', function(){
		var options=$("#project_corPageSelect option:selected");
		var optionsVal = options.val();
		var searchPageVal = $('#project_searchPage').val();
		$('#formCurPage').val(searchPageVal);
		$('#formPageSize').val(optionsVal);
		$('#projectSearchFormId').submit();
	});
	
	
	//项目管理信息列表-新增按钮
	$('#addProjectId').on('click', function(){
		window.location.href=basePath+"/projectManageController/toProject?type=1";
	});

	
	
	//项目管理信息列表-修改按钮
	$('#updateProjectId').on('click', function(){
		var pids = new Array();
    	  var i = 0;
		  $("#project_TbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids[i] = corPidVal;
				i++;
			 }
		  });
          
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var pid = pids[0];
			  window.location.href=basePath+"/projectManageController/toProject?type=2&projectPid="+pid;
		  }else{
			  layer.msg("请选择要修改的项目!",{icon:7,time: 1000});
		  }
	});
	
	//查看项目信息
	$('#toSeeProject').on('click', function(){
		  var pids = new Array();
    	  var i = 0;
		  $("#project_TbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids[i] = corPidVal;
				i++;
			 }
		  });
          
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var pid = pids[0];
			  window.location.href=basePath+"/projectManageController/toProject?type=3&projectPid="+pid;
		  }else{
			  layer.msg("请选择要查看的项目!",{icon:7,time: 1000});
		  }
	});
	
	//变更项目进度状态
	$('#toChangeProgress').on('click', function(){
		var pids = new Array();
    	  var i = 0;
		  $("#project_TbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids[i] = corPidVal;
				i++;
			 }
		  });
          
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var pid = pids[0];
			  window.location.href=basePath+"/projectManageController/toChangeProgress?projectPid="+pid;
		  }else{
			  layer.msg("请选择要变更进度的项目!",{icon:7,time: 1000});
		  }
	});
	
	//项目信息列表-删除按钮
	$('#delProject').on('click', function(){
  	  var pids="";
		  $("#project_TbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids = pids + corPidVal+",";
			 }
		  });
		  if(pids == ""){
			  layer.msg("请选择要删除的记录!",{icon:7,time: 1000});
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
					  delProject(pids);
				  },
				  btn2: function(index, layero){
					  layer.close(index);
				  }
			  });
		  }
    });
	
	/*---------关联项目进来点“确定”--------*/
	$('#confirmButton').on('click', function(){
		var pids = new Array();
  	    var i = 0;
		  $("#project_TbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids[i] = corPidVal;
				i++;
			 }
		  });
		
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var pid = pids[0];
			  layer.open({
				  type: 1,
				  title: ['关联项目操作提示', 'text-align:center;'],
				  area: ['300px', '200px'],
				  btnAlign: 'c',
				  content: '<div class="content_text">确定要关联选中的项目数据吗？</div>',
				  btn: ['确定', '取消'],
				  btn1: function(index, layero){
					  layer.close(index);
					  linkProject(pid);
				  },
				  btn2: function(index, layero){
					  layer.close(index);
				  }
			  });
			  
		  }else{
			  layer.msg("请选择要关联的项目!",{icon:7,time: 1000});
		  }
	});
	
      
})


//项目管理列表首页、上一页、下一页、尾页按钮
function getProjectListByPage(idCode) {
	var options=$("#project_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	$('#formPageSize').val(pageSize);
	$('#formCurPage').val(curPage);
	$('#projectSearchFormId').submit();
}

//删除项目信息
function delProject(pids) {
	$.ajax({
	    url: basePath+"/projectManageController/delProject",
	    type: "POST",
	    data:{
	    	projectPids:pids
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var successCode = data["success"];
			var resultMsg = data["msg"];
			if (successCode == "s") {
				layer.msg(resultMsg,{icon: 1,time: 1000});
				$('#projectSearchFormId').submit();
			} else {
				layer.msg(resultMsg,{icon:7,time: 1000});
			}
		}
	});
}

//关联项目信息
function linkProject(pid) {
	var linkedPrjId = $("#linkedPrjId").val();
	var businessPrjPid = $("#businessPrjPid").val();
	var url1 = basePath + "tradeManage/projectEnterpriseManage";
	$.ajax({
		url: basePath+"tradeManage/linkedToProject",
		cache: true,
		type: "POST",
		data:{
			projectInfoPid:pid,
			linkedPrjId:linkedPrjId,
			businessPrjPid:businessPrjPid
		},
		async: false,
		success : function(msg) { //表单提交后更新页面显示的数据
			if (msg && msg["successMsg"]) {
				layer.msg(msg["successMsg"],{icon: 1});
				window.location.href = url1;
			} else {
				layer.msg(msg["errMsg"], {time:1000,icon:7});
			}
		},
		error:function(msg){
			alert(msg["errMsg"]);
		}
	});
	
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

