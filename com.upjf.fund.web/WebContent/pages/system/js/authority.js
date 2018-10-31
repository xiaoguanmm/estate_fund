$(function(){
	layui.use('form',function(){
		var form = layui.form;
	});
	
	/*新增权限*/
	$('#addAuthority').click(function() {
    	layer.open({
            type: 1,
            title: ['新增权限'],
            area: ['700px', '500px'],
            btnAlign: 'c',
            content: $('#Auth_Manage_Add'),
            btn: ['保存', '取消'],
            success: function(index,layero){
            	initForm($("#addAuthForm"));
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
            	validator.triggerForm($("#addAuthForm"));
            	/*校验通过提交表单*/
            	if(validator.isValid($("#addAuthForm"))){
            		 if($("#authType").val()=="0"&&$("#resourceName").val()==""){
            			 layer.msg("请选择菜单名称",{icon:7});
            			 return ;
            		 }
            		 var data = $("#addAuthForm").serialize();
	            	 var url = getOperatrionUrl("addAuthority");
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
			            	    validator.resetForm($("#addAuthForm"));
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
        		validator.resetForm($("#addAuthForm"));
        	},
        	cancel: function(index,layero){
        		validator.resetForm($("#addAuthForm"));
        	}
        });

	});
	
	/*修改权限*/
	$("#modifyAuthority").on("click",function(){
		/*校验选中状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		
    	layer.open({
            type: 1,
            title: ['修改权限'],
            area: ['700px', '500px'],
            btnAlign: 'c',
            content: $('#Auth_Manage_Add'),
            btn: ['保存', '取消'],
            success: function(index, layero){
            	initFormData($("#addAuthForm"));
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
            	validator.triggerForm($("#addAuthForm"));
            	/*校验通过提交表单*/
            	if(validator.isValid($("#addAuthForm"))){
            		if($("#authType").val()=="0"&&$("#resourceName").val()==""){
            			 layer.msg("请选择菜单名称",{icon:7});
            			 return ;
            		 }
            		 var data = $("#addAuthForm").serialize();
	            	 var url = getOperatrionUrl("modifyAuthority");
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
			            	    validator.resetForm($("#addAuthForm"));
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
        		validator.resetForm($("#addAuthForm"));
        	},
        	cancel: function(index,layero){
        		validator.resetForm($("#addAuthForm"));
        	}
        });
		
		
	});
	
	/*删除权限*/
	$("#deleteAuthority").on("click",function(){
		/*校验选中状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		var pid = rowData.pid;
		var url = getOperatrionUrl("delAuthority");
		layer.confirm('删除权限信息',function(){
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
	
   /*查询父级权限*/
   $("input[name=parentName]").autocomplete({
	   minLength: 1,
     	source: function( request, response ) {
           $.ajax({
               url: basePath+"systemManage/getSimpleAuthorities",
               type: "POST",
               async: false,
               data: {
                 maxRows: 10,
                 name_startsWith: request.term
               },
               success: function( data ) {
                 response( $.map( data.authorities, function(item) {
                   return {
                     label: item.name,
                     value: item.pid
                   }
                 }));
               },
               error:function(msg){
            	   
            	   alert(msg);
               }
             });
           },
     	focus: function( event, ui ) {
	       	$(this).val( ui.item.label);
	       	return false;
     	},
     	select: function( event, ui ) {
	        $(this).val( ui.item.label );
	        $(this).parent().next().next().val(ui.item.value);
	        return false;
     }
   });
	
	$("#p_name").focusout(function(){
		if($.trim($(this).val())==""){
			 $("#parentId").val("");
		}
	});
	
	layui.use('form',function(){
		var form = layui.form;
		form.on('select(authType)', function(data){
		  if(data.value){
			  validator.resetFormField($("#addAuthForm"),"type");
			}
		  
		  if(data.value=="0"){
				$(".resource-info").show();
			}else{
				$(".resource-info").find(":input").val("");
				$(".resource-info").hide();
			}
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
	return $("#addAuthForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,operation);
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
	dom.find(":input").not(":disabled").not(".hide_field").val("");
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
	$(".resource-info").hide();
}

/**初始化表数据*/
function initFormData(dom){
	var rowData = getRowData();
	var pid = rowData.pid;
	var parent_id = rowData.parent_id;
	var resource_id = rowData.resource_id;
	var type = rowData.type;
	$("#addAuthForm").find("input[name=pid]").val(pid);
	$("#addAuthForm").find("select[name=type]").val(type);
	layui.use('form',function(){
		var form = layui.form;	    		
    	if(type=="0"){
			$(".resource-info").show();
			$("#addAuthForm").find("select[name=resourceId]").val(resource_id);
			$(".resource-info").show();
		}else{
			$(".resource-info").hide();
			$("#addAuthForm").find("select[name=resourceId]").val("");
		}
    	$("#addAuthForm").find("select[name=parentId]").val(parent_id);
    	form.render('select');
	});
	$("#addAuthForm").find("input[name=name]").val(rowData.name);
	$("#addAuthForm").find("input[name=oriName]").val(rowData.name);
	$("#addAuthForm").find("input[name=authorityCode]").val(rowData.authority_code);
	$("#addAuthForm").find("input[name=oriAuthId]").val(rowData.authority_code);
	$("#addAuthForm").find("select[name=parentId]").val(parent_id);
	$("#addAuthForm").find("textarea[name=description]").val(rowData.description);
}