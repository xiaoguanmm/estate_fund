$(function(){
	layui.use('form',function(){
		var form = layui.form;
	});
	
	/*新增角色*/
	$('#addRole').click(function() {
    	layer.open({
            type: 1,
            title: ['新增角色'],
            area: ['600px', '400px'],
            btnAlign: 'c',
            content: $('#Role_Manage_Add'),
            btn: ['保存', '取消'],
            success: function(index,layero){
            	initForm($("#addRoleForm"));
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
            	validator.triggerForm($("#addRoleForm"));
            	/*校验通过提交表单*/
            	if(validator.isValid($("#addRoleForm"))){
            		var data = $("#addRoleForm").serialize();
	            	var url = getOperatrionUrl("addRole");
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
			            	    validator.resetForm($("#addRoleForm"));
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
        		validator.resetForm($("#addRoleForm"));
        	},
        	cancel: function(index,layero){
        		validator.resetForm($("#addRoleForm"));
        	}
        });
	});
	
	/*修改角色*/
	$("#modifyRole").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		if("admin"==rowData.code){
			layer.msg("系统管理员角色不允许删除",{icon:7});
			return ;
		}
    	layer.open({
            type: 1,
            title: ['新增角色'],
            area: ['600px', '400px'],
            btnAlign: 'c',
            content: $('#Role_Manage_Add'),
            btn: ['保存', '取消'],
            success: function(index,layero){
            	initFormData($("#addRoleForm"));
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
            	validator.triggerForm($("#addRoleForm"));
            	/*校验通过提交表单*/
            	if(validator.isValid($("#addRoleForm"))){
            	    var data = $("#addRoleForm").serialize();
	            	var url = getOperatrionUrl("modifyRole");
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
			            	    validator.resetForm($("#addRoleForm"));
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
        		validator.resetForm($("#addRoleForm"));
        	},
        	cancel: function(index,layero){
        		validator.resetForm($("#addRoleForm"));
        	}
        });
		
	});
	
	/*角色数据授权*/
	$("#authRole").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		var pid = rowData.pid;
		var name = rowData.name;
		var code = rowData.code;
		window.location.href=basePath+"systemManage/roleAuthority?pid="+pid+"&name="+encodeURI(encodeURI(name))+"&code="+code;
	});
	
	/*删除角色*/
	$("#deleteRole").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		if("admin"==rowData.code){
			layer.msg("系统管理员角色不允许删除",{icon:7});
			return ;
		}
		var pid = rowData.pid;
		var url = getOperatrionUrl("delRole");
		layer.confirm('删除角色信息',function(){
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
	return $("#addRoleForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,operation);
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
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}

/**初始化表数据*/
function initFormData(dom){
	var rowData = getRowData();
	$("#addRoleForm").find("input[name=pid]").val(rowData.pid);
	$("#addRoleForm").find("input[name=name]").val(rowData.name);
	$("#addRoleForm").find("input[name=code]").val(rowData.code);
	$("#addRoleForm").find("input[name=oriName]").val(rowData.name);
	$("#addRoleForm").find("input[name=oriCode]").val(rowData.code);
	$("#addRoleForm").find("textarea[name=description]").val(rowData.description);
	/*$("#addRoleForm").attr("action",$("#addRoleForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,"/modifyRole"));*/
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}

