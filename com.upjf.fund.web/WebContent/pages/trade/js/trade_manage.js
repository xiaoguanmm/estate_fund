//表格中点击th里的 选择按钮，下面会全部被选中
$ (function () {
	
	var basePath = $('#basePathId').val();
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
		var form =layui.form;
		form.on('select(corpQualityFilter)', function(data){
			  var corpQualityVal =  data.value;
			  $('#corpQuality').val(corpQualityVal);
		});
		form.on('select(bankFilter)', function(data){
			var bankVal =  data.value;
			$('#bankId').val(bankVal);
		});
		form.on('select(update_bankFilter)', function(data){
			var bankVal =  data.value;
			$('#updateBankSelect').val(bankVal);
		});
	})
	//控制头部tab切换
	$('#myTab a').click(function (e) {
	    e.preventDefault();
	    $(this).tab('show');
	});
    
// .....................................1.企业管理.............................................................
	    //新增企业按钮
		$('#addCorporationId').on('click', function(){
			window.location.href=basePath+"/corporationController/toAdd";
		});
		
		//修改企业按钮
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
			  }else{
				  window.location.href=basePath+"/corporationController/toUpdate";
			  }
		});	
		
	
		//查询按钮
		$('#corSearchButton').on('click', function(){
			$('#formPageSize').val("");
			$('#formCurPage').val("");
			$('#corSearchForm').submit();
		});
		
		//重置按钮
		$('#corFormRest').on('click', function(){
			$('#corNameId').val('');
			$('#orgCodeCertId').val('');
			$('#businessLicenceCodeId').val('');
			$('#customerManagerId').val('');
		});
		
		//企业信息首页按钮
		$('#first_pager_list').on('click', function(){
			var options=$("#corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formPageSize').val(optionsVal);
			$('#formCurPage').val("1");
			$('#corSearchForm').submit();
		});
		
		//企业信息末页按钮
		$('#last_pager_list').on('click', function(){
			var options=$("#corPageSelect option:selected");
			var optionsVal = options.val();
			var lastPageVal = $('#lastPageInput').val();
			$('#formPageSize').val(optionsVal);
			$('#formCurPage').val(lastPageVal);
			$('#corSearchForm').submit();
		});
		
		//企业信息上页按钮
		$('#prev_pager_list').on('click', function(){
			var options=$("#corPageSelect option:selected");
			var optionsVal = options.val();
			var upPageVal = $('#upPageInput').val();
			$('#formCurPage').val(upPageVal);
			$('#formPageSize').val(optionsVal);
			$('#corSearchForm').submit();
		});
		
		//企业信息下页按钮
		$('#next_pager_list').on('click', function(){
			var nextPageVal = $('#nextPageInput').val();
			var options=$("#corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formCurPage').val(nextPageVal);
			$('#formPageSize').val(optionsVal);
			$('#corSearchForm').submit();
		});
		
		//企业信息页码输入框
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
		
		//企业信息每页显示条数下拉事件
		$('#corPageSelect').on('change', function(){
			var options=$("#corPageSelect option:selected");
			var optionsVal = options.val();
			$('#formCurPage').val("1");
			$('#formPageSize').val(optionsVal);
			$('#corSearchForm').submit();
		});
	
    
      //企业-删除按钮
      $('#delCorporationId').on('click', function(){
    	  var pids="";
		  $("#corTbodyId input[type='checkbox']").each(function () {
			 if ($(this).is(":checked")) {
				var corPidVal = $(this).val();
				pids = pids + corPidVal+",";
			 }
		  });
          layer.open({
              type: 1,
              title: ['删除操作提示', 'text-align:center;'],
              area: ['300px', '200px'],
              btnAlign: 'c',
              content: '<div class="content_text">确定删除选中的企业数据吗？</div>',
              btn: ['删除', '取消'],
              btn1: function(index, layero){
            	  $.ajax({
					    url: basePath+"/corporationController/delete",
					    type: "POST",
					    data:{
					    	corporationIds:pids
					    },
					    dataType:"json",
					    async: false,
						success : function(data) {
							layer.close(index);
							if (data["success"] == "s") {
								layer.msg(data["msg"],{icon: 1,time: 800},function(){
									$('#corSearchForm').submit();
								});
							} else {
								layer.msg("错误",{icon:7,time: 800});
							}
						}
					});
              },
              btn2: function(index, layero){
            	  layer.close(index);
              }
          });
      });

      //公司资料扫描件-删除按钮
      $('.datum_delt').on('click', function(){
          layer.open({
              type: 1,
              title: ['删除操作提示', 'text-align:center;'],
              area: ['300px', '200px'],
              btnAlign: 'c',
              content: '<div class="content_text">确定删除选中的此批数据吗？</div>',
              btn: ['删除', '取消'],
              btn1: function(index, layero){
                  //按钮【按钮二】的回调
              },
              btn2: function(index, layero){
                  //按钮【按钮三】的回调
              }
          });
      });
      //公司资料扫描件-上传按钮
      $('.datum_upload').on('click', function(){
          layer.open({
              type: 1,
              title: ['上传文件', 'font-size:18px;'],
              area: ['510px', '267px'],
              btnAlign: 'c',
              content: $('#Upload_file'),
              btn: ['上传', '取消'],
              btn1: function(index, layero){
                  //按钮【按钮二】的回调
              },
              btn2: function(index, layero){
                  //按钮【按钮三】的回调
              }
          });
      });
      
      //公司资料扫描件-图片查看
      $('.datum_img').on('click', function(){
          layer.photos({
              photos: '.datum_img'
              ,anim: 0 //0-6的选择，指定弹出图片动画类型，默认随机
          });
      })

		
		
		//企业基础信息保存按钮
		$('#corBaseSubmit').click(function () {
			var arr = ['corName','orgCodeCert','businessLicenceCode'];
			if(cheackAndShowWarns(arr)){
				focusWarnInput(arr);
				return false;
			}
			
			$.ajax({
			    url: basePath+"/corporationController/saveBase",
			    type: "POST",
			    data:$('#corBaseForm').serialize(),
			    dataType:"json",
			    async: false,
				success : function(data) {
					var resultMsg=data["msg"]
					var success=data["success"]
					if(success == "s"){
						var newPid=data["newPid"]
						$('#newCorPid').val(newPid);
						$('#corporationName').val($('#corName').val());
					}
					layer.open({
		                type: 1,
		                title: ['提示信息', 'text-align:center;'],
		                area: ['240px', '140px'],
		                btnAlign: 'c',
		                shade: 0,
		                closeBtn: 0,
		                content: "<div class='content_text content_text_keep'>"+resultMsg+"</div>",
		                time: 900
		            });
				}
			});
		});
		
		//企业基础信息必填项校验
		$("#corName,#orgCodeCert,#businessLicenceCode").blur( function () {
			var aStr = $(this).attr("id");
			var brr = [aStr];
			cheackAndShowWarns(brr);
		});
		
		
		
		//企业信息-银行管理保存按钮
		$('#bankAccountSubmit').click(function () {
			
			var newCorPidVal = $('#newCorPid').val();
			if(newCorPidVal == ''){
				layer.open({
	                type: 1,
	                title: ['提示信息', 'text-align:center;'],
	                area: ['240px', '140px'],
	                btnAlign: 'l',
	                shade: 0,
	                content: "<div class='content_text content_text_keep content_bank_text'>请先保存企业基本信息</div>",
	                time: 1000
	            });
				return false;
			}else{
				$('#corpId').val(newCorPidVal);
			}
			
			$.ajax({
			    url: basePath+"/corporationController/saveBank",
			    type: "POST",
			    data:$('#bankAccountForm').serialize(),
			    dataType:"json",
			    async: false,
				success : function(data) {
					var resultMsg=data["msg"]
					var success=data["success"]
					
					if(success == "s"){//刷新银行列表
						layer.msg(resultMsg,{icon: 1,time: 1000});
						getBankList();
					}else{
						layer.msg(resultMsg,{icon: 7,time: 1000});
					}
				}
			});
		});
      
		

// .....................................2.项目管理.............................................................

  //新增项目---项目基本信息--点击城市更新  非城市更新 显示隐藏div    然而功能没实现
  $ (function () {
      var options=$("#breed option:selected");
      if(options.val('breed_city')){
          $('.btn_breed_city').show();
      }else{
          $('.btn_breed_city').hide();
      }
  });

  //新增项目---项目基本信息--点击’新增业态组成‘

      $('#add_Makeuup1').on('click', function(){
          layer.open({
              type: 1,
              title: ['新增业态组成','font-size:18px;'],
              area: ['590px', '400px'],
              btnAlign: 'c',
              content: $('#add_Makeuup'),
              btn: ['确定', '取消'],
              btn1: function(index, layero){
                  //按钮【按钮二】的回调
              },
              btn2: function(index, layero){
                  //按钮【按钮三】的回调
              }
          });
      });


  //新增项目---项目基本信息--点击’修改‘

      $('.modify_Makeuup1').on('click', function(){
          layer.open({
              type: 1,
              title: ['修改业态组成','font-size:18px;'],
              area: ['590px', '400px'],
              btnAlign: 'c',
              content: $('#modify_Makeuup'),
              btn: ['确定', '取消'],
              btn1: function(index, layero){
                  //按钮【按钮二】的回调
              },
              btn2: function(index, layero){
                  //按钮【按钮三】的回调
              }
          });
      });
  //新增项目---项目基本信息--点击’查看‘

      $('.see_Makeuup1').on('click', function(){
          layer.open({
              type: 1,
              title: ['查看业态组成','font-size:18px;'],
              area: ['590px', '400px'],
              btnAlign: 'c',
              content: $('#see_Makeuup'),
              btn: ['确定'],
              btn1: function(index, layero){
                  //按钮【按钮二】的回调
              }

          });
      });

  // .....................................3.项目公司管理.............................................................

  //新增项目公司---项目公司股东信息--点击表格中的’查看‘
  //和
  ////新增项目公司---项目公司股东信息--股权历史变更记录----点击表格中的’查看‘
      $('.see_partner_stock').on('click', function(){
          layer.open({
              type: 1,
              title: ['查看股东股权信息','font-size:18px;'],
              area: ['1040px', '600px'],
              btnAlign: 'c',
              content: $('#see_partner_stock1'),
              btn: ['关闭'],
              btn1: function(index, layero){
                  //按钮的回调
              }
          });
      });

  // .....................................4.资管计划管理.............................................................

  //新增投资主体---投资主体管理--点击’新增投资主体‘
  //新增投资主体---投资主体管理--点击’修改投资主体‘

  

  // .....................................5.投资主体业务管理.............................................................

  //查看详情---付款信息--点击’查看付款明细‘
      $('#subject_Pay_details1').on('click', function(){
          layer.open({
              type: 1,
              title: ['付款明细','font-size:18px;'],
              area: ['1040px', '780px'],
              btnAlign: 'c',
              content: $('#subject_Pay_details'),
              btn: ['关闭'],
              btn1: function(index, layero){
                  //按钮的回调
              }
          });
      });

  //查看详情---回款信息--点击’查看回款明细‘
      $('#subject_reture_details1').on('click', function(){
          layer.open({
              type: 1,
              title: ['深圳市融鑫资产管理有限公司 回款明细','font-size:18px;'],
              area: ['1040px', '700px'],
              btnAlign: 'c',
              content: $('#subject_reture_details'),
              btn: ['关闭'],
              btn1: function(index, layero){
                  //按钮的回调
              }
          });
      });

  // .....................................8.投资主体付款请求管理.............................................................

      //搜索栏中，时间弹窗控件（利用layui插件）
      layui.use('laydate',function(){
    	  var laydate = layui.laydate;
    	  //执行一个laydate实例
    	  laydate.render({
    		  elem: '#pay_time1' //指定元素
    	  });
    	  laydate.render({
    		  elem: '#pay_time2' //指定元素
    	  });
      });

  // .....................................10.项目股东回款管理.............................................................
  //查看详情---回款信息--点击’查看回款明细‘
      $('#Share_Return_Detailed1').on('click', function(){
          layer.open({
              type: 1,
              title: ['深圳市汇联资管二十三号投资管理合伙企业 回款明细','font-size:18px;'],
              area: ['1040px', '700px'],
              btnAlign: 'c',
              content: $('#Share_Return_Detailed'),
              btn: ['关闭'],
              btn1: function(index, layero){
                  //按钮的回调
              }
          });
      });

  // .....................................11.投资主体回款信息列表.............................................................
  //查看回款记录---点击’回款明细‘
      $('#Subject_Return_Detailed1').on('click', function(){
          layer.open({
              type: 1,
              title: ['回款明细','font-size:18px;'],
              area: ['1040px', '700px'],
              btnAlign: 'c',
              content: $('#Subject_Return_Detailed'),
              btn: ['关闭'],
              btn1: function(index, layero){
                  //按钮的回调
              }
          });
      });


  // .....................................12.项投资人回款信息列表.............................................................
  //查看回款记录---点击’回款明细‘
      $('#Investor_Return_Detailed1').on('click', function(){
          layer.open({
              type: 1,
              title: ['回款明细','font-size:18px;'],
              area: ['1040px', '700px'],
              btnAlign: 'c',
              content: $('#Investor_Return_Detailed'),
              btn: ['关闭'],
              btn1: function(index, layero){
                  //按钮的回调
              }
          });
      });

  //查看回款记录---点击’新增回款记录‘
      $('#Investor_Return_Add1').on('click', function(){
          layer.open({
              type: 1,
              title: ['XXX公司  新增回款','font-size:18px;'],
              area: ['1040px', '700px'],
              btnAlign: 'c',
              content: $('#Investor_Return_Add'),
              btn: ['保存', '取消'],
              btn1: function(index, layero){
                  //按钮【按钮一】的回调
              },
              btn2: function(index, layero){
                  //按钮【按钮二】的回调
              }
          });
      });


  //查看回款记录---点击’修改回款记录‘
      $('#Investor_Return_Modify1').on('click', function(){
          layer.open({
              type: 1,
              title: ['XXX公司  修改回款','font-size:18px;'],
              area: ['1040px', '700px'],
              btnAlign: 'c',
              content: $('#Investor_Return_Add'),
              btn: ['保存', '取消'],
              btn1: function(index, layero){
                  //按钮【按钮一】的回调
              },
              btn2: function(index, layero){
                  //按钮【按钮二】的回调
              }
          });
      });
      
})


//进入修改企业银行信息
function toUpdateBank(pid){
	$.ajax({
	    url: basePath+"/corporationController/getBank",
	    type: "POST",
	    data:{
	    	pid:pid
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var resultMsg=data["msg"]
			var successCode=data["success"]
			if(successCode == 's'){
				var bankInfo=data["bankInfo"]
				$('#updatePid').val(bankInfo.pid);
				$('#updateCorPid').val(bankInfo.corpId);
				$('#update_corporationName').val($('#corporationName').val());
				$('#update_accountName').val(bankInfo.accountName);
				$("#update_bankSelectId").val(bankInfo.bankId);
				layui.use('form', function(){
					  var form = layui.form;
					  form.render('select');
				});
				
				$("#update_account").val(bankInfo.account);
				$("#updateRemark").val(bankInfo.remark);
				
				layer.open({
			        type: 1,
			        title: ['修改企业银行','font-size:18px;'],
			        area: ['590px', '400px'],
			        btnAlign: 'c',
			        content: $('#update_bankInfo'),
			        btn: ['确定', '取消'],
			        btn1: function(index, layero){
			        	layer.close(index);
			        	updateBank();
			        },
			        btn2: function(index, layero){
			        	layer.close(index)
			        }
			    });
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//保存更新的企业银行信息
function updateBank(){
	$.ajax({
	    url: basePath+"/corporationController/updateBank",
	    type: "POST",
	    data:$('#updateBankForm').serialize(),
	    dataType:"json",
	    async: false,
		success : function(data) {
			//成功变更之后,重新加载企业银行信息列表,变更失败,则不重新加载
			var resultMsg=data["msg"]
			var successCode=data["success"]
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
				getBankList();
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//删除企业银行信息
function delBank(pid){
	$.ajax({
	    url: basePath+"/corporationController/delBank",
	    type: "POST",
	    data:{
	    	pid:pid
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			//删除成功之后,重新加载企业银行信息列表,删除失败,则不重新加载
			var resultMsg=data["msg"]
			var successCode=data["success"]
			
			if(successCode == 's'){
				layer.msg(resultMsg,{icon: 1,time: 1000});
				getBankList();
			}else{
				layer.msg(resultMsg,{icon: 7,time: 1000});
			}
		}
	});
}

//刷新企业银行信息列表
function getBankList(){
	$.ajax({
	    url: basePath+"/corporationController/getBankList",
	    type: "POST",
	    data:{
	    	corPid:$('#newCorPid').val()
	    },
	    dataType:"json",
	    async: false,
		success : function(data) {
			var bankListMsg=data["msg"]
			var bankListCode=data["success"]
			
			if(bankListCode == 's'){
				$('#corBankListTbody > tr').remove();
				$('#corBankListTbody').hide();
				$('#pager_list').hide();
				var corBankList = data["corBankList"];
				var pageObj = data["page"];
				
				//页码重新赋值
				$('#upPageInput').val(pageObj.upPage);
				$('#searchPage').val(pageObj.curPage);
				$('#sp_1_pager_list').text(pageObj.totalPage);
				$('#nextPageInput').val(pageObj.nextPage);
				$('#lastPageInput').val(pageObj.endPage);
				$('#totalCountId').text("共 "+pageObj.totalCount+"条");
				$("#corPageSelect option[value='"+pageObj.pageSize+"']").attr("selected","selected");
				
				//清空填写表单
				$('#accountName').val("");
				$('#account').val("");
				$('#remark').val("");
				$("#bankSelectId").val("");
				$('#bankAccountDiv').hide();
				layui.use('form', function(){
					  var form = layui.form;
					  form.render('select');
				});
				$('#bankAccountDiv').show();
				
				var listLength = corBankList.length;
				var htmlStr = "";
				if(listLength >0){
					for(var i=0;i<listLength;i++){
						var corBank = corBankList[i];
						var newhtmlStr = "<tr>" +
								"<td>"+$('#corporationName').val()+"</td>" +
								"<td>"+corBank.accountName+"</td>" +
								"<td>"+corBank.bankName+"</td>" +
								"<td>"+corBank.account+"</td>" +
								"<td>" +
									"<a href='javascript:void(0)' onclick=\"toUpdateBank('"+corBank.pid+"')\" class='table_bnt'>修改</a>" +
									"<a href='javascript:void(0)' onclick=\"delBank('"+corBank.pid+"')\" class='table_bnt'>删除</a>" +
								"</td>" +
								"</tr>";
						htmlStr = htmlStr + newhtmlStr;
					}
					$('#corBankListTbody').append(htmlStr);
				}
				
				$('#corBankListTbody').show();
				$('#pager_list').show();
			}else{
				layer.msg(bankListMsg,{icon: 7,time: 1000});
			}
		}
	});
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

