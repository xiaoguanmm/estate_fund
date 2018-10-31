// .....................................企业管理初始化列表.............................................................
$ (function () {
		var basePath = $('#basePathId').val();
		
		//表格中点击th里的 选择按钮，下面会全部被选中
    	var flag = true;
    	$(".hook_all").click(function() {
    		var cb = $("input[type=checkbox]");
    		for(var i = 0; i < cb.length; i++) {
    			cb[i].checked = flag;
    		}
    		flag = !flag;
    	});
    
    	
	    //企业信息列表-新增企业按钮
		$('#addCorporationId').on('click', function(){
			window.location.href=basePath+"/corporationController/toAddCorInfo";
		});
		
		
		//企业信息列表-修改企业按钮
		$('#updateCorporationId').on('click', function(){
			var pids = new Array();
	    	  var i = 0;
			  $("#corTbodyId input[type='checkbox']").each(function () {
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
				  window.location.href=basePath+"/corporationController/toUpdateCorInfo?pid="+pid;
			  }else{
				  layer.msg("请选择要修改的企业!",{icon:7,time: 1000});
			  }
		});	
		
	
		//企业信息列表-查询按钮
		$('#corSearchButton').on('click', function(){
			$('#formPageSize').val("");
			$('#formCurPage').val("");
			$('#corSearchForm').submit();
		});
		
		
		//企业信息列表-重置按钮
		$('#corFormRest').on('click', function(){
			$('#corNameId').val('');
			$('#orgCodeCertId').val('');
			$('#businessLicenceCodeId').val('');
			$('#customerManagerId').val('');
		});
		
		
		//企业信息-刷新按钮
		$('#refresh_table_list').on('click', function(){
			window.location.href=basePath+"/corporationController/corInfoList";
		});	
		
		
		//企业信息列表-页码输入框
		$('#searchPage').on('keypress', function(){
			if(window.event.keyCode==13){
				var searchPageVal = $('#searchPage').val();
				var options=$("#corPageSelect option:selected");
				var optionsVal = options.val();
				$('#formCurPage').val(searchPageVal);
				$('#formPageSize').val(optionsVal);
				$('#corSearchForm').submit();
			}
		});
		
		
		//企业信息列表-每页显示条数下拉事件
		$('#corPageSelect').on('change', function(){
			var options=$("#corPageSelect option:selected");
			var optionsVal = options.val();
			var searchPageVal = $('#searchPage').val();
			$('#formCurPage').val(searchPageVal);
			$('#formPageSize').val(optionsVal);
			$('#corSearchForm').submit();
		});
	
    
       //企业信息列表-删除按钮
       $('#delCorporationId').on('click', function(){
    	  var pids="";
		  $("#corTbodyId input[type='checkbox']").each(function () {
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
				  content: '<div class="content_text">确定删除选中的企业数据吗？</div>',
				  btn: ['确定', '取消'],
				  btn1: function(index, layero){
					  layer.close(index);
					  deleteCorInfo(pids);
				  },
				  btn2: function(index, layero){
					  layer.close(index);
				  }
			  });
		  }
      });
      
})


//企业信息列表首页、上一页、下一页、尾页按钮
function getCorInfoListByPage(idCode) {
	var options=$("#corPageSelect option:selected");
	var pageSize = options.val();
	var curPage = $("#"+idCode).val();
	$('#formPageSize').val(pageSize);
	$('#formCurPage').val(curPage);
	$('#corSearchForm').submit();
}


//删除企业信息
function deleteCorInfo(pids) {
	$.ajax({
	    url: basePath+"/corporationController/delCorInfo",
	    type: "POST",
	    data:{
	    	corporationIds:pids
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var successCode = data["success"];
			var resultMsg = data["msg"];
			if (successCode == "s") {
				layer.msg(data["msg"],{icon: 1,time: 1000},function(){
					$('#corSearchForm').submit();
				});
			} else {
				layer.msg(resultMsg,{icon:7,time: 1000});
			}
		}
	});
}