$(function(){
	layui.use('form',function(){
		var form = layui.form;
	});
	
	/*新增用户*/
	$('#addUserInfo').click(function() {
		layer.open({
	        type: 1,
	        title: ['新增用户'],
	        area: ['800px', '600px'],
	        btnAlign: 'c',
	        content: $('#User_Manage_Add'),
	        btn: ['保存', '取消'],
	        success: function(index, layero){
	        	initForm($("#addUserInfoForm"));
	        },
	        btn1: function(index, layero){
	        	/*手动触发表单校验*/
	           validator.triggerForm($("#addUserInfoForm"));
	        	/*校验通过提交表单*/
	           if(validator.isValid($("#addUserInfoForm"))){
	        	   if($.trim($("#addUserInfoForm").find("input[name=password]").val())==""){
	        		   layer.msg("请输入密码",{icon:7});
	        		   $("#addUserInfoForm").find("input[name=password]").focus();
	        		   return ;
	        	   }
	        	   
	        	  var data = $("#addUserInfoForm").serialize();
	        	  var url = getOperatrionUrl("addUserInfo");
	        	 $.ajax({
	      		    url: $("#addUserInfoForm").attr("action"),
	      		    cache: true,
	      		    type: "POST",
	      		    data: data,
	      		    async: false,
	      			success : function(msg) { //表单提交后更新页面显示的数据
	      				if (msg && msg["successMsg"]) {
	      					layer.msg(msg["successMsg"],{icon: 1});
	      					$("#data_list").trigger("reloadGrid");
		            	    validator.resetForm($("#addUserInfoForm"));
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
	        	validator.resetForm($("#addUserInfoForm"));
	        },
	        cancel: function(index,layero){
	        	validator.resetForm($("#addUserInfoForm"));
	        }
	    });
		
	});
	
	/*修改用户*/
	$("#modifyUserInfo").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		
    	layer.open({
            type: 1,
            title: ['修改用户'],
            area: ['800px', '600px'],
            btnAlign: 'c',
            content: $('#User_Manage_Add'),
            btn: ['保存', '取消'],
            success: function(index, layero){
            	initFormData($("#addUserInfoForm"));
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
               validator.triggerForm($("#addUserInfoForm"));
            	/*校验通过提交表单*/
               if(validator.isValid($("#addUserInfoForm"))){
            	  var data = $("#addUserInfoForm").serialize();
            	  var url = getOperatrionUrl("modifyUserInfo");
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
    	            	    validator.resetForm($("#addUserInfoForm"));
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
            	validator.resetForm($("#addUserInfoForm"));
            },
            cancel: function(index,layero){
            	validator.resetForm($("#addUserInfoForm"));
            }
        });
    	
	});
	
	/*禁用/启用*/
	$("#startUserInfo,#stopUserInfo").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		var userName = rowData.user_name;
		if("admin"==userName){
			layer.msg("不允许修改系统管理员状态",{icon:7});
			return ;
		}
		var nextStatus = rowData.status == "1"?"0":"1";
		var pid = rowData.pid;
		var url = getOperatrionUrl("switchStatus");
		$.ajax({
		    url: url,
		    type: "POST",
		    data:{
		    	pid:pid,
		    	status:nextStatus
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
	});
		
	/*删除用户*/
	$("#deleteUserInfo").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		var userName = rowData.user_name;
		if("admin"==userName){
			layer.msg("系统管理员不允许删除",{icon:7});
			return ;
		}
		var pid = rowData.pid;
		var url = getOperatrionUrl("delUserInfo");
		layer.confirm('删除用户信息',function(){
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
		
	/*用户授权*/
	$("#authUserInfo").on("click",function(){
		/*判断勾选状态*/
		if(!checkedSelectedRows()){
			return ;
		}
		var rowData = getRowData();
		$("#authoritied-user-name").val(rowData.user_name);
		var pid =rowData.pid;
		var url = getOperatrionUrl("getRoleInfo");
		/*查询角色信息*/
		$.ajax({
		    url: url,
		    type: "POST",
		    data:{
		    	pid:pid
		    },
		    async: false,
			success : function(msg) {
				if (msg && msg["successMsg"]) {
					var allRoleInfos = [msg["allRoleInfos"]][0];//所有角色信息
					var ownRoleInfos = [msg["ownRoleInfos"]][0];
					
					var roleCheckbox = $('<input type="checkbox" name="rolePid" title="">');
					 layui.use('form', function(){
						 for(var i=0 ;i<allRoleInfos.length;i++){
							 var roleCheckboxClone = roleCheckbox.clone();
							 for(var j=0;j<ownRoleInfos.length;j++){
								 if(allRoleInfos[i].pid == ownRoleInfos[j].role_id){
									 roleCheckboxClone.attr("checked","checked");
								 }
							 }
							 roleCheckboxClone.attr("id","rolePid"+i);
							 roleCheckboxClone.val(allRoleInfos[i].pid);
							 roleCheckboxClone.attr("title",allRoleInfos[i].name);
							 $("#roles").append(roleCheckboxClone);
							
								    var form = layui.form;
									 form.render();
								
							 if((i+1)%4==0){
								 $("#roles").append("<br/>")
							 }
						 }
					 });
				} else {
					layer.msg(msg["errMsg"],{icon:7});
				}
			},
			error:function(msg){
				alert(msg["errMsg"]);
			}
		});
		$("#userAuthForm").find("input[name=pid]").val(pid);
		
		layer.open({
            type: 1,
            title: ['数据角色授权'],
            area: ['800px', '600px'],
            btnAlign: 'c',
            content: $('#User_Auth_Add'),
            btn: ['保存', '取消'],
            btn1: function(index, layero){
            	  var pid = $("#userAuthForm").find("input[name=pid]").val();
            	    var rolePids="";
            	   $("#roles").find("input[name=rolePid]:checked").each(function(i,n){
            		   if(i>0){
            			   rolePids+="-"+$(n).val();
            		   }else{
            			   rolePids+=$(n).val();
            		   }
            	   });
            	   
            	   if(rolePids==""){
            		   layer.msg("请至少选择一种角色",{icon: 7});
            		   return false;
            	   }
            	   var user_name = $("#authoritied-user-name").val();
            	   if(user_name=="admin"){
            		   var sys_id;
            		   $("#roles").find(":input").each(function(){
            			   var label_text = $(this).attr("title");
            			   if(label_text=="系统管理员"){
            				   sys_id=$(this).attr("id");
            				   return false;
            			   }
            		   });
            		   if(sys_id){
            			   if(!$("#"+sys_id).is(":checked")){
            				   layer.msg("系统管理员必须勾选系统管理员角色",{icon: 7});
            				   return false;
            			   }
            		   }else{
            			   layer.msg("未找到系统管理员角色",{icon: 7});
            			   return false;
            		   }
            	   }
            	   var url = getOperatrionUrl("userAuthorization");
            	   $.ajax({
            			    url: url,
            			    type: "POST",
            			    data:{
            			    	pid:pid,
            			    	rolePids:rolePids
            			    },
            			    async: false,
            				success : function(msg) {
            					if (msg && msg["successMsg"]) {
            						layer.msg(msg["successMsg"],{icon: 1});
            						layer.close(index);
            						$("#roles").empty();
            					} else {
            						layer.msg(msg["errMsg"],{icon:7});
            					}
            				},
            				error:function(msg){
            					alert(msg["errMsg"]);
            				}
            			});
            },
            btn2: function(index, layero){
            	$("#roles").empty();
            },
            cancel: function(index, layero){
            	$("#roles").empty();
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
	return $("#addUserInfoForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,operation);
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
	/*显示用户状态*/
	$("#is-forbidden").show();
	/*隐藏重置密码功能,且设置值为N*/
	$("#reset-pwd").hide();
	$("#reset_no").trigger("click");
	/*显示密码输入栏位*/
	$("#addUserInfoForm").find(".pwd_div").remove();
	$("#uName").after($("#pwdHiddenArea .pwd_div").clone(true));
	/*去除用户名只读属性*/
	$("#userName").removeAttr("readonly");
	/*初始化用户状态为启用*/
	$("#status_start").trigger("click");
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}

/**初始化表数据*/
function initFormData(dom){
	var rowData=getRowData();
	var pid = rowData.pid;
	var status = rowData.status;
	var department_code = rowData.department_code;
	$("#pid").val(pid);
	$("#userName").val(rowData.user_name);
	$("#userName").attr("readonly",true);
	$("#oriUserName").val(rowData.user_name);
	$("#name").val(rowData.name);
	$("#tel").val(rowData.tel);
	$("#duty").val(rowData.duty);
	$("#departmentCode").val(department_code);
	if("admin"!=rowData.user_name){
    	$("#addUserInfoForm").find("input[name=status][value="+status+"]").trigger("click");
    	$("#is-forbidden").show();
	}else{
		$("#is-forbidden").hide();
	}
	$("#reset-pwd").show();
	$("#addUserInfoForm").find(".pwd").remove();
	$("#reset_no").trigger("click");
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}