$(function(){
	layui.use(['laydate','form'],function(){
		var laydate = layui.laydate;
		var form = layui.form;
		laydate.render({
			elem: '#receiverDate',
			done: function(value, date, endDate){
				validator.resetFormField($("#investForm"),"receiverDate");
			}
		});
		form.on('select(receivedStatus)',function(data){
			if(data.value){
				validator.resetFormField($("#investForm"),"receivedStatus");
			}
		});
			
	});
	/*文件上传URL*/
	var uploadFileUrl = $("#uploadFileUrl").val();
	/*文件查看URL*/
	var showFileListUrl = $("#showFileListUrl").val();
	/*删除记录URL*/
	var delPaybackUrl = $("#delPaybackUrl").val();
	/*收款公司*/
	var receiverId = $("#corpId").val();
	/*付款公司*/
	var contributiveId = $("#corporationInfoId").val();
	/*文件列表加载标识*/
	var file_load_flag=false;
	/*项目状态*/
	var projectStatus = $("#projectStatus").val();
	/*回款状态*/
	var received_status = $("#received_status").val();
	/*pid值*/
	var pid = $("#investForm").find("input[name=pid]").val();
	/*加载搜索栏位的回款账号信息*/
	loadReceiverAccount();
	/*加载收款银行信息*/
	loadBankInfo(receiverId,null,"receive");
	/*加载付款银行信息*/
	loadBankInfo(contributiveId,null,"pay");
	/*加入校验*/
	validator.validate($("#investForm"));
	/*校验是否保存数据*/
	$("#select_file").on("click",function(){
		var paymentId = $("#investForm").find("input[name=pid]").val();
		if(paymentId!=""){
			$("#selectFileButton").trigger("click");
		}else{
			layer.msg("请先保存数据",{icon:7});
		}
	});
	var data = {pid:pid};
	
	/*初始化加载上传文件控件*/
	uploadFiles($("#selectFileButton"), $("#fileList"), $("#uploadFileButton"),$("#file_data_list"), uploadFileUrl,data);
	
	/**新增回款**/
	$("#add_payback").on("click",function(){
		/*校验项目状态*/
		if(projectStatus=="3"){
			layer.msg("项目状态已完结，不可新增记录",{time:1000,icon:7});
			return ;
		}
		if(received_status == "1"){
			layer.msg("回款已完结，不可再进行增加",{time:1000,icon:7});
			return ;
		}
		/*初始化表单*/
		initForm($("#investForm"));
		$("#pid").val("");
		layer.open({
            type: 1,
            title: ['新增回款'],
            area: ['1200px', '800px'],
            btnAlign: 'c',
            content: $('#payback_operation'),
            btn: ['保存', '取消'],
            success:function(index, layero){
            	if(file_load_flag){
            		$("#file_data_list").jqGrid('setGridParam',{  
			            datatype:'json',  
			            postData:{pid:null}, 
			            page:1,
			            gridComplete:function(){
			            	$(".file_del").show();
			            }
			        }).trigger("reloadGrid");
            	}else{
	        		loadAccessoryFiles(showFileListUrl, data, null,$("#file_data_list"),"#file_page_list");
	        		file_load_flag= true;
            	}
            	clearFiles();
            },
            btn1: function(index, layero){
            	/*手动触发表单校验*/
	           validator.triggerForm($("#investForm"));
	            /*校验通过提交表单*/
               if(validator.isValid($("#investForm"))){
            	   var receivedStatus = $("#receivedStatus").val();
            	   var statusFlg = true;
            	   if(receivedStatus == "1"){
            		   layer.confirm("回款状态为 【已完结】，保存后，不可再对回款记录进行新增、修改、删除，确认保存？",function(){
            			   saveData()
            		   },function(){});
            	   }else{
            		   saveData();
            	   }
            	   
            	   
               }
            },
            btn2: function(index, layero){
            	validator.resetForm($("#investForm"));
            },
            cancel:function(index,layero){
            	validator.resetForm($("#investForm"));
            }
		});
	});
	
	/**修改回款**/
	$("#modify_payback").on("click",function(){
		/*校验项目状态*/
		if(projectStatus=="3"){
			layer.msg("项目状态已完结，不可修改记录",{time:1000,icon:7});
			return ;
		}
		if(received_status == "1"){
			layer.msg("回款已完结，不可再进行修改",{time:1000,icon:7});
			return ;
		}
		/*校验勾选记录条数*/
		if(!checkedSelectedRows()){
			return ; 
		}
	   	var rowid = $("#data_list").getGridParam("selrow");
		var rowData=$("#data_list").jqGrid("getRowData",rowid);
	  	initFormData(rowData);
		layer.open({
	        type: 1,
	        title: ['修改回款'],
	        area: ['1200px', '800px'],
	        btnAlign: 'c',
	        content: $('#payback_operation'),
	        btn: ['保存', '取消'],
	        success:function(index, layero){
	        	clearFiles();
	        	if(file_load_flag){
	            	$("#file_data_list").jqGrid('setGridParam',{  
			            datatype:'json',  
			            postData:{pid:rowData.pid}, //发送数据  
			            page:1,
			            gridComplete:function(){
			            	$(".file_del").show();
			            }
			        }).trigger("reloadGrid");
	        	}else{
	        		initPaybackAccessoryList(rowData);
	        		file_load_flag = true;
	        	}
	        },
	        btn1: function(index, layero){
	        	/*手动触发表单校验*/
	           validator.triggerForm($("#investForm"));
	            /*校验通过提交表单*/
	           if(validator.isValid($("#investForm"))){
	        	   var receivedStatus = $("#receivedStatus").val();
	        	   if(receivedStatus == "1"){
            		   layer.confirm("回款状态为 【已完结】，保存后，不可再对回款记录进行新增、修改、删除，确认保存？",function(){
            			   saveData()
            		   },function(){});
            	   }else{
            		   saveData();
            	   }
	           }
	        },
	        btn2: function(index, layero){
	        	validator.resetForm($("#investForm"));
	        },
	        cancel:function(index,layero){
	        	validator.resetForm($("#investForm"));
	        }
	        
		});
	});
	
	/**查看回款**/
	$("#view_payback").on("click",function(){
		/*校验勾选记录条数*/
		if(!checkedSelectedRows()){
			return ; 
		}
	   	var rowid = $("#data_list").getGridParam("selrow");
		var rowData=$("#data_list").jqGrid("getRowData",rowid);
	   	initFormData(rowData);
		$("#upload_file").hide();
		layer.open({
	        type: 1,
	        title: ['查看回款'],
	        area: ['1200px', '800px'],
	        btnAlign: 'c',
	        content: $('#payback_operation'),
	        success:function(index, layero){
	        	if(file_load_flag){
	            	$("#file_data_list").jqGrid('setGridParam',{  
			            datatype:'json',  
			            postData:{pid:rowData.pid}, //发送数据  
			            page:1,
			            gridComplete:function(){
			            	$(".file_del").hide();
			            }
			        }).trigger("reloadGrid");
	        	}else{
	        		initPaybackAccessoryList(rowData);
	        		file_load_flag = true;
	        	}
	        	$("#investForm").find(":input").attr("disabled","disabled");
	        	layui.use('form',function(){
					var form = layui.form;
					form.render();
				});
	        },
	        cancel:function(index, layero){
	        	$("#investForm").find(":input").not("#prjId,#receiverId,#contributiveId").attr("disabled",false);
	        	layui.use('form',function(){
					var form = layui.form;
					form.render();
				});
	        	$("#upload_file").show();
	        }
	    });
	});
	/**删除回款**/
	$("#delete_payback").on("click",function(){
		/*校验项目状态*/
		if(projectStatus=="3"){
			layer.msg("项目状态已完结，不可删除记录",{time:1000,icon:7});
			return ;
		}
		if(received_status == "1"){
			layer.msg("回款已完结，不可删除",{time:1000,icon:7});
			return ;
		}
		/*校验勾选记录条数*/
		if(!checkedSelectedRows()){
			return ; 
		}
		var rowid = $("#data_list").getGridParam("selrow");
		var rowData=$("#data_list").jqGrid("getRowData",rowid);
		layer.confirm('确认删除？',function(){
			$.ajax({
      		    url: delPaybackUrl,
      		    cache: true,
      		    type: "POST",
      		    data: {
      		    	pid: rowData.pid,
      		    	receiverId: receiverId,
      		    	prjId: $("#prjId").val(),
      		    	receivedType: $("#receivedType").val(),
      		    	contributiveId: $("#contributiveId").val(),
      		    	stockholderId: $("#stockholderId").val()
      		    },
      		    async: false,
      		    success : function(msg) {
    				if (msg && msg["successMsg"]) {
    					layer.msg(msg["successMsg"],{icon: 1});
    					$("#data_list").trigger("reloadGrid");
    					$("#receiver_amount_total").text(msg.amount.receiver_amount);
    					$("#profit_amount_total").text(msg.amount.profit_amount);
    				} else {
    					layer.msg(msg["errMsg"], {time:1000,icon:7});
    				}
    			},
    			error:function(msg){
    				alert(msg["errMsg"]);
    			}
      		});
		},function(){
		});
	});
	
	function saveData(){
		   var data = $("#investForm").serialize();
		   var url = $("#investForm").attr("action");
		   $.ajax({
			   url: url,
			   cache: true,
			   type: "POST",
			   data: data,
			   async: false,
			   success : function(msg) {
				   if (msg && msg["successMsg"]) {
					   layer.msg(msg["successMsg"],{icon: 1});
					   $("#investForm").find("input[name=pid]").val(msg.pid);
					   $("#data_list").trigger("reloadGrid");
					   $("#receiver_amount_total").text(msg.amount.receiver_amount);
					   $("#profit_amount_total").text(msg.amount.profit_amount);
					   received_status=msg.received_status;
				   } else {
					   layer.msg(msg["errMsg"], {time:1000,icon:7});
				   }
			   },
			   error:function(msg){
				   alert(msg["errMsg"]);
			   }
		   });
	}
	
	/*初始化加载回款账号信息*/
	function loadReceiverAccount(){
		var corpId = $("#corpId").val();
		$.ajax({
			url: basePath+"prjContactCorp/getInvestSubjectBankAccount",
		    type: "POST",
		    data:{
		    	corpId: corpId
		    },
		    async: false,
			success : function(msg) {
				if (msg && msg.successMsg) {
					var result = msg.result;
					$.each(result,function(i,n){
						$("#searchReceiverAccount").append('<option value="'+n+'">'+n+'</option>');
					});
					layui.use('form',function(){
						var form = layui.form;
						form.render("select");
					});
				}
			},
			error:function(msg){
				alert(msg["errMsg"]);
			}
		});
	}
	
	/*校验选择状态*/
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
	
	/*初始化表单信息*/
	function initForm(dom){
		dom.find(":input").not(":disabled").not(".hide_field").val("");
		layui.use('form',function(){
			var form = layui.form;
			form.render();
		});
	}
	
	/*初始化表单数据*/
	function initFormData(rowData){
		var form = layui.form;
		var pid = rowData.pid;
		var receiverDate = rowData.receiver_date;//回款时间
		var receiverBankId = rowData.receiver_bank_id; //收款银行
		var payBankId = rowData.pay_bank_id;//付款银行
		var receiverAccount = rowData.receiver_account;//收款账号
		var payAccount = rowData.pay_account;//付款账号
		var receiverAmount = rowData.receiver_amount;//回款本金
		var profit = rowData.profit;//回款利润
		var receivedStatus = rowData.received_status;//回款状态
		var reveiverRemark = rowData.reveiver_remark;//回款备注
		$("#receiverAccount").find("option[value!='']").remove();
		$("#payAccount").find("option[value!='']").remove();
		form.render("select");
		/*加载收款银行账户信息*/
		loadReceivedAccount(receiverId,receiverBankId,receiverAccount);
		/*加载付款银行账户信息*/
		loadPayAccount(contributiveId,payBankId,payAccount);
		$("#pid").val(pid);
		layui.use('form',function(){
			$("#receiverBankId").val(receiverBankId);
			$("#payBankId").val(payBankId);
			$("#receivedStatus").val(receivedStatus);
			form.render('select');
		});
		receiverDate = formatDate(receiverDate);
		$("#receiverDate").val(receiverDate);
		$("#receiverAmount").val(receiverAmount);
		$("#profit").val(profit);
		$("#reveiverRemark").val(reveiverRemark);
	}
	
	/*初始化加载附件信息*/
	function initPaybackAccessoryList(rowData){
		data.pid = rowData.pid;
		loadAccessoryFiles(showFileListUrl, data, "view",$("#file_data_list"),"#file_page_list");
	}
	
	/*格式化日期YYYY-MM-DD*/
	function formatDate(date){
		date = new Date(date);
		var year = date.getFullYear(),
	        month = date.getMonth() + 1,
	        day = date.getDate();
		return year+"-"+month+"-"+day;
	}
});
/**
 * 查看项目详情
 * @param url
 */
function showProjectInfo(prjId){
	if($.trim(prjId)==""){
		layer.msg("找不到项目信息",{icon:7})
		return ;
	}
	window.location.href = basePath+"projectManageController/toProject?type=3&projectPid="+prjId;
}