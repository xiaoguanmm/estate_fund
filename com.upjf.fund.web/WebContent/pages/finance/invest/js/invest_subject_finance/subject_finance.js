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
		
		//投资主体
		form.on('select(investSubjectFilter)', function(data){
			var investSubject =  data.value;
			$('#investSubject').val(investSubject);
		});
		
		//出资类别
		form.on('select(contributiveTypeFilter)', function(data){
			var contributiveType =  data.value;
			$('#contributiveType').val(contributiveType);
		});
		
		//检索回显
		$('#investSubjectSelectId').val($('#investSubject').val());
		$('#contributiveTypeSelectId').val($('#contributiveType').val());
		form.render('select');
	})
	
	
	
	//项目信息列表-查询按钮
	$('#investSubjectSearchButtonId').on('click', function(){
		$('#formPageSize').val("");
		$('#formCurPage').val("");
		$('#investSubjectSearchFormId').submit();
	});
	
	//项目信息列表-重置按钮
	$('#investSubjectResetButtonId').on('click', function(){
		$('#projectName').val('');
		$('#investSubject').val('');
		$('#contributiveType').val('');
		
		$('#investSubjectSelectId').val('');
		$('#contributiveTypeSelectId').val('');
		form.render('select');
	});
	
	//查看详情按钮:传入投资主体主键,项目主键
	$('#subjectFianceDetailAid').on('click', function(){
		  var pids = new Array();
		  var projectPids = new Array();
  	      var i = 0;
		  $("#subject_TbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				projectPids[i] = $(this).next().val();
				pids[i] = corPidVal;
				i++;
			 }
		  });
        
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var subjectPid = pids[0];
			  var projectPid = projectPids[0];
			  window.location.href=basePath+"/investSubjectFinanceController/toSubjectFinanceDetail?subjectPid="+subjectPid+"&projectPid="+projectPid;
		  }else{
			  layer.msg("请选择要查看的项目!",{icon:7,time: 1000});
		  }
	});
	
	
	//项目管理信息列表-刷新按钮
	$('#subject_refresh_table_list').on('click', function(){
		window.location.href=basePath+"/investSubjectFinanceController/subjectFinanceList";
	});	
	
	//项目管理信息列表-页码输入框
	$('#subject_searchPage').on('keypress', function(){
		if(window.event.keyCode==13){
			var searchPageVal = $('#subject_searchPage').val();
			var options=$("#subject_corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formCurPage').val(searchPageVal);
			$('#formPageSize').val(optionsVal);
			$('#investSubjectSearchFormId').submit();
		}
	});
	
	
	//项目管理信息列表-每页显示条数下拉事件
	$('#subject_corPageSelect').on('change', function(){
		var options=$("#subject_corPageSelect option:selected");
		var optionsVal = options.val();
		var searchPageVal = $('#subject_searchPage').val();
		$('#formCurPage').val(searchPageVal);
		$('#formPageSize').val(optionsVal);
		$('#investSubjectSearchFormId').submit();
	});
})


//投资主体财务管理列表首页、上一页、下一页、尾页按钮
function getSubjectListByPage(idCode) {
	var options=$("#subject_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	$('#formPageSize').val(pageSize);
	$('#formCurPage').val(curPage);
	$('#investSubjectSearchFormId').submit();
}







