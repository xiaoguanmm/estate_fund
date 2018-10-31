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
		
		//投资人
		form.on('select(investorFilter)', function(data){
			var investorId =  data.value;
			$('#investorPid').val(investorId);
		});
		
		
		//检索回显
		$('#investSubjectSelectId').val($('#investSubject').val());
		$('#investorSelectId').val($('#investorPid').val());
		form.render('select');
	})
	
	
	
	//查询按钮
	$('#investorSearchButtonId').on('click', function(){
		$('#formPageSize').val("");
		$('#formCurPage').val("");
		$('#investorSearchFormId').submit();
	});
	
	//重置按钮
	$('#investorResetButtonId').on('click', function(){
		$('#projectName').val('');
		$('#investSubject').val('');
		$('#investorPid').val('');
		
		$('#investSubjectSelectId').val('');
		$('#investorSelectId').val('');
		form.render('select');
	});
	
	//查看详情按钮
	$('#investorFianceDetailAid').on('click', function(){
		  var pids = new Array();
  	      var i = 0;
		  $("#investor_tbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids[i] = corPidVal;
				i++;
			 }
		  });
        
		  if(pids.length > 1){
			  layer.msg("请勿多选!",{icon:7,time: 1000});
		  }else if(pids.length == 1){
			  var investorPid = pids[0];
			  window.location.href=basePath+"/investorFinanceController/toInvestorFinanceDetail?investorPid="+investorPid;
		  }else{
			  layer.msg("请选择要查看的记录!",{icon:7,time: 1000});
		  }
	});
	
	
	//信息列表-刷新按钮
	$('#investor_finance_refresh_table_list').on('click', function(){
		window.location.href=basePath+"/investorFinanceController/investorFinanceList";
	});	
	
	//信息列表-页码输入框
	$('#investor_finance_searchPage').on('keypress', function(){
		if(window.event.keyCode==13){
			var searchPageVal = $('#investor_finance_searchPage').val();
			var options=$("#investor_finance_corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formCurPage').val(searchPageVal);
			$('#formPageSize').val(optionsVal);
			$('#investorSearchFormId').submit();
		}
	});
	
	
	//信息列表-每页显示条数下拉事件
	$('#investor_finance_corPageSelect').on('change', function(){
		var options=$("#investor_finance_corPageSelect option:selected");
		var optionsVal = options.val();
		var searchPageVal = $('#investor_finance_searchPage').val();
		$('#formCurPage').val(searchPageVal);
		$('#formPageSize').val(optionsVal);
		$('#investorSearchFormId').submit();
	});
})


//列表首页、上一页、下一页、尾页按钮
function getInvestorListByPage(idCode) {
	var options=$("#investor_finance_corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	$('#formPageSize').val(pageSize);
	$('#formCurPage').val(curPage);
	$('#investorSearchFormId').submit();
}







