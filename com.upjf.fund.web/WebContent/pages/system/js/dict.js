$(function(){
	layui.use('form',function(){
		var form = layui.form;
	});
	
	/*新增字典*/
	$("#addDict").click(function(){
		layer.open({
            type: 1,
            title: ['新增字典'],
            area: ['800px', '600px'],
            btnAlign: 'c',
            content: $('#dict_Manage_Add'),
            btn: ['保存', '取消'],
            success: function(index,layero){
            	initForm($("#addDictForm"));
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
               validator.triggerForm($("#addDictForm"));
            	/*校验通过提交表单*/
               if(validator.isValid($("#addDictForm"))){
            	  var data = $("#addDictForm").serialize();
            	  $.ajax({
          		    url: $("#addDictForm").attr("action"),
          		    cache: true,
          		    type: "POST",
          		    data: data,
          		    async: false,
          			success : function(msg) { //表单提交后更新页面显示的数据
          				if (msg && msg["successMsg"]) {
          					layer.msg(msg["successMsg"],{icon: 1});
          					$("#data_list").trigger("reloadGrid");
		            	    validator.resetForm($("#addDictForm"));
		            	    layer.close(index);
          				} else {
          					layer.msg(msg["errMsg"], {time:1000,icon:7});
          				}
          			},
          			error:function(msg){
          				alert(msg["errMsg"]);
          			}
          		});
            	   
            	   
               }               
            },
            btn2: function(index, layero){
            	validator.resetForm($("#addDictForm"));
            },
            cancel: function(index,layero){
            	validator.resetForm($("#addDictForm"));
            }
        });
	});
	
	/*修改字典*/
	$("#modifyDict").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		layer.open({
            type: 1,
            title: ['修改字典'],
            area: ['800px', '600px'],
            btnAlign: 'c',
            content: $('#dict_Manage_Add'),
            btn: ['保存', '取消'],
            success:function(index,layero){
            	initFormData($("#addDictForm"));
            	var rowData = getRowData();
            	fillDetailData(rowData.pid);
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
               validator.triggerForm($("#addDictForm"));
            	/*校验通过提交表单*/
               if(validator.isValid($("#addDictForm"))){
            	  var data = $("#addDictForm").serialize();
            	  var url = getOperatrionUrl("modifyDict");
            	  $.ajax({
          		    url: url,
          		    cache: true,
          		    type: "POST",
          		    data: data,
          		    async: false,
          			success : function(msg) { //表单提交后更新页面显示的数据
          				if (msg && msg["successMsg"]) {
          					layer.msg(msg["successMsg"],{icon: 1});
          					$("#data_list").trigger("reloadGrid");
		            	    validator.resetForm($("#addDictForm"));
		            	    layer.close(index);
          				} else {
          					layer.msg(msg["errMsg"], {time:1000,icon:7});
          				}
          			},
          			error:function(msg){
          				alert(msg["errMsg"]);
          			}
          		});
               }               
            },
            btn2: function(index, layero){
            	validator.resetForm($("#addDictForm"));
            },
            cancel: function(index,layero){
            	validator.resetForm($("#addDictForm"));
            }
        });

	});

	/*删除字典*/
	$("#deleteDict").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		var pid = rowData.pid;
		var url = getOperatrionUrl("delDict");
		layer.confirm('删除字典信息',function(){
			$.ajax({
			    url: url,
			    type: "POST",
			    data:{
			    	pid:pid
			    },
			    async: false,
				success : function(msg) {
					if (msg && msg["successMsg"]) {
						layer.msg(msg["successMsg"],{icon: 1});
						setTimeout(function () {
							$("#data_list").trigger("reloadGrid");
					     }, 1500);
					} else {
						layer.msg(msg["errMsg"],{icon:7});
					}
				},
				error:function(msg){
					alert(msg["errMsg"]);
				}
			});
			
		},function(){
		});
	});
	
	$(document).on("click",".parameters-add",function(){
		$('.fillboxparameters-out').append($('.fillboxparameters:first').clone(true));
		$('.fillboxparameters-out').find(".fillboxparameters:last").find(":input").each(function(){
			validator.addField($(this));
		});
	});
	$(document).on("click",".parameters-delete",function(){
		$(this).parent().parent().detach();
	});
});

/**
 * 判断是否只勾选了一条记录
 * @returns {Boolean}
 */
function checkedSelectedRows(){
	var rowIds=$('#data_list').jqGrid('getGridParam','selarrrow');
	if(rowIds.length>1){
		layer.msg("只能选中一条数据",{icon:7})
		return false;
	}
	if(rowIds.length==0){
		layer.msg("请选择数据",{icon:7})
		return false;
	}
	return true;
}

/**获取操作URL*/
function getOperatrionUrl(operation){
	operation = "/"+operation;
	return $("#addDictForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,operation);
}

/**
 * 获取选中行数据
 * @returns
 */
function getRowData(){
	var rowid = $("#data_list").getGridParam("selrow");
	return $("#data_list").jqGrid("getRowData",rowid);
}

/**初始化表单信息*/
function initForm(dom){
	dom.find(":input").not(":disabled").not(".hide_field").not(":radio").val("");
	dom.find(".fillboxparameters:not(:first)").remove();
	validator.destroy($("#addDictForm"));
	validator.validate($("#addDictForm"));
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}

/**初始化表数据*/
function initFormData(dom){
	var rowData = getRowData();
	var pid = rowData.pid;
	var status = rowData.status;
	$("#addDictForm").find("input[name=pid]").val(pid);
	$("#addDictForm").find("input[name=name]").val(rowData.name);
	$("#addDictForm").find("input[name=code]").val(rowData.code);
	$("#addDictForm").find("input[name=oriName]").val(rowData.name);
	$("#addDictForm").find("input[name=oriCode]").val(rowData.code);
	$("#addDictForm").find("input[name=status][value="+status+"]").trigger("click");
	$("#addDictForm").find("textarea[name=remark]").val(rowData.remark);
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}

/**填充表单数据*/
function fillDetailData(pid){
	$("#addDictForm").find(".fillboxparameters").remove();
	var data = getDictDetailInfos(pid);
	for(var i=0;i<data.length;i++){
		var paramDom = $("#parametersTemp").find('.fillboxparameters').clone(true);
		paramDom.find("input[name=values]").val(data[i].value)
		paramDom.find("input[name=valueDeses]").val(data[i].valueDes)
		$("#addDictForm").find('.fillboxparameters-out').append(paramDom);
		$('.fillboxparameters-out').find(".fillboxparameters:last").find(":input").each(function(){
			validator.addField($(this));
		});
	}
}
/**获取字典详情数据*/
function getDictDetailInfos(pid){
	var detailInfos="";
	var url = getOperatrionUrl("getDictDetailInfos");
	$.ajax({
	    url: url,
	    type: "POST",
	    data:{
	    	pid: pid
	    },
	    async: false,
		success : function(msg) {
			if (msg && msg["successMsg"]) {
				detailInfos= eval('('+msg["data"]+')');
			}
		},
		error:function(msg){
			alert(msg["errMsg"]);
		}
	});
	return detailInfos;
}