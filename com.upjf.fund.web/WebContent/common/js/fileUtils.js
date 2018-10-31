/**
 * 多文件上传
 * @param seletButton 选择文件按钮
 * @param listView 文件列表
 * @param uploadButton 上传文件按钮
 * @param gridDom 上传成功之后文件展示列表
 * @param url 上传文件路径
 */
function uploadFiles(seletButton,listView,uploadButton,gridDom,url,data){
	layui.use('upload',function(){
		var upload = layui.upload;
		 /*多文件上传*/
		var uploadListIns = upload.render({
		    elem: seletButton,
		    url: url,
		    accept: 'file',
		    multiple: true,
		    auto: false,
		    data:data,
		    bindAction: uploadButton,
		    choose: function(obj){
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      //读取本地文件
		      obj.preview(function(index, file, result){
		        var tr = $(['<tr id="upload-'+ index +'">',
		                    '<td>'+ file.name +'</td>',
		                    '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
		                    '<td>等待上传</td>',
		                    '<td>',
		                    '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
		                    '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
		                    '</td>',
		                    '</tr>'
		                   ].join(''));
		        
		        //单个重传
		        tr.find('.demo-reload').on('click', function(){
		          	obj.upload(index, file);
		        });
		        //删除
		        tr.find('.demo-delete').on('click', function(){
			        delete files[index]; //删除对应的文件
			        tr.remove();
			        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
		        });
		        listView.append(tr);
		      });
		    },
		    before: function(obj){
		    	this.data.pid = $("#pid").val();
		    },
		    done: function(res, index, upload){
		      if(res.successMsg){
		          var tr = listView.find('tr#upload-'+ index),
		        	  tds = tr.children();
		          tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		          tds.eq(3).html(''); //清空操作
		          return delete this.files[index]; //删除文件队列已经上传成功的文件
		      }
		      this.error(index, upload);
		    }, 
		    allDone: function(obj){
		    	/*上传文件全部成功之后，刷新文件列表*/
		    		if(gridDom){
		    			gridDom.jqGrid('setGridParam',{  
		    		           datatype:'json',  
		    		           postData:data, //发送数据  
		    		           page:1  
		    		      }).trigger("reloadGrid"); 
		    		}
		    },
		    error: function(index, upload){
		        var tr = listView.find('tr#upload-'+ index),
		      	    tds = tr.children();
		        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
		        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
		  });
	});
}

/**
 * 多文件上传
 * @param seletButton 选择文件按钮
 * @param listView 文件列表
 * @param uploadButton 上传文件按钮
 * @param gridDom 上传成功之后文件展示列表
 * @param url 上传文件路径
 * @param pid 主表主键，根据主表主键查询附件表
 */
function uploadFiles1(seletButton,listView,uploadButton,gridDom,url,data,pid){
	layui.use('upload',function(){
		var upload = layui.upload;
		 /*多文件上传*/
		var uploadListIns = upload.render({
		    elem: seletButton,
		    url: url,
		    accept: 'file',
		    multiple: true,
		    auto: false,
		    data:data,
		    bindAction: uploadButton,
		    choose: function(obj){
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      //读取本地文件
		      obj.preview(function(index, file, result){
		        var tr = $(['<tr id="upload-'+ index +'">',
		                    '<td>'+ file.name +'</td>',
		                    '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
		                    '<td>等待上传</td>',
		                    '<td>',
		                    '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
		                    '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
		                    '</td>',
		                    '</tr>'
		                   ].join(''));
		        
		        //单个重传
		        tr.find('.demo-reload').on('click', function(){
		          	obj.upload(index, file);
		        });
		        //删除
		        tr.find('.demo-delete').on('click', function(){
			        delete files[index]; //删除对应的文件
			        tr.remove();
			        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
		        });
		        listView.append(tr);
		      });
		    },
		    before: function(obj){
		    	this.data.pid = $("#"+pid).val();
		    },
		    done: function(res, index, upload){
		      if(res.successMsg){
		          var tr = listView.find('tr#upload-'+ index),
		        	  tds = tr.children();
		          tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		          tds.eq(3).html(''); //清空操作
		          return delete this.files[index]; //删除文件队列已经上传成功的文件
		      }
		      this.error(index, upload);
		    }, 
		    allDone: function(obj){
		    	/*上传文件全部成功之后，刷新文件列表*/
		    		if(gridDom){
		    			gridDom.jqGrid('setGridParam',{  
		    		           datatype:'json',  
		    		           postData:data, //发送数据  
		    		           page:1  
		    		      }).trigger("reloadGrid"); 
		    		}
		    },
		    error: function(index, upload){
		        var tr = listView.find('tr#upload-'+ index),
		      	    tds = tr.children();
		        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
		        tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
		  });
	});
}

/**
 * 单文件上传
 * @param seletButton 选择文件按钮
 * @param uploadButton 上传文件按钮
 * @param gridDom 上传成功之后文件展示列表
 * @param url 上传文件路径
 */
function uploadFile(seletButton,uploadButton,gridDom,url){
	layui.use('upload',function(){
		upload.render({
			elem: seletButton,
			url: url,
			auto: false,
			bindAction: uploadButton,
			done: function(res){
				layer.msg("文件上传成功",{icon:1});
				if(gridDom){
					gridDom.trigger("reloadGrid");
				}
			}
		});
	});
	
}

/**
 * 文件查看
 * 只针对图片
 * @param url
 */
function showFile(url){
	var regexp = /^.+(\.jpg|\.jpeg|\.png|\.bmp)$/g;
	/*图片查看*/
	if(regexp.test(url)){
		layer.open({
			type: 1,
			title: ["附件信息"],
			maxmin: true,
			skin: 'layui-layer-demo',
			area: ['800px', '600px'],
			content: '<img src="'+basePath+'estateFundFile/showAccessory?path='+url+'">'
		});
	}else{
		layer.msg("查看只针对图片文件，若想查看详情，请先下载",{icon:7});
	}
}

function downloadFile(url){
	/*首先校验文件是否存在，不存在则不允许下载(避免如果文件不存在页面跳转到空白页)*/
	var isExist = false;
	$.ajax({
		type: "POST",
		url: basePath+"estateFundFile/checkFileIsExist?path="+url,
		async: false,
		dataType: "json",
		success: function(data){
			if(data&&data.successMsg){
				isExist=true; 
			}else{
				layer.msg(data.errMsg,{icon:7});
			}
		},
		error: function(data){
			layer.msg(data,{icon:2});
		}
	});
	
	if(isExist){
		window.location.href=basePath+'estateFundFile/showAccessory?path='+url;
	}
}

/**
 * 作逻辑删除
 * @param url
 */
function deleteFile(url,dataListId){
	var dom = $("#data_list");
	if(dataListId){
		dom= $("#"+dataListId);
	}
	$.ajax({
		type: "POST",
		url: basePath+"estateFundFile/deleteFile?path="+url,
		async: false,
		dataType: "json",
		success: function(data){
			if(data&&data.successMsg){
				layer.msg(data.successMsg,{icon:1});
				dom.trigger("reloadGrid");
			}else{
				layer.msg(data.errMsg,{icon:7});
			}
		},
		error: function(data){
			layer.msg(data,{icon:2});
		}
	});
}

/**
 * 展示附件列表
 */
var loadFlg = false;
function showAccessoryList(url,pid){
	
	layer.open({
        type: 1,
        title: ['附件信息'],
        area: ['800px', '400px'],
        btnAlign: 'c',
        content: $("#show_accessory"),
        success:function(layero, index){
        	if(loadFlg){
        		$("#accessory_list").jqGrid('setGridParam',{  
		            datatype:'json',  
		            postData:{pid:pid}, //发送数据  
		            page:1  
		        }).trigger("reloadGrid");
        	}else{
        		var colNames = ["文件名","文件类型","上传时间","上传人","操作"]
        		var colModel = [
        		                {name:"file_all_name", index:"file_all_name",sortable:false},
        		                {name:"accessory_type_name", index:"accessory_type_name",sortable:false},
        		                {name:"create_date", index:"create_date",sortable:false},
        		                {name:"user_name", index:"user_name",sortable:false},
        		                {name:"operation", index:"operation",sortable:false,formatter:function(cellvalue, options, rowObject){
        		                	var url = "'"+rowObject.file_url+'&fileName='+rowObject.file_all_name+"'";
        		                	return '<a onclick="showFile('+encodeURI(url)+')" class="table_bnt  table_bnt-margin">查看</a>'+
        		                	'<a onclick="downloadFile('+encodeURI(url)+')" class="table_bnt">下载</a>';
        		                }}
        		                
        		                ]
        		$("#accessory_list").jqGrid({
        			url:url,
        			mtype : "POST",  
        			contentType : "application/json",  
        			datatype : "json",
        			postData:{pid:pid},
        			autowidth: true,   
        			shrinkToFit: true, 
        			rownumbers: true,  
        			viewrecords: true, 
        			scrollOffset: 0,
        			height : 'auto',
        			rowNum: 5,                           
        			rowList: [5, 10, 15],             
        			colNames: colNames,
        			cellEdit : false, 
        			colModel: colModel,
        			pager: "#accessory_page_list", 
        			gridComplete:function(){
        				$("#accessory_list").jqGrid('setLabel','rn', '序号',{'font-size':'8px'});
        				loadFlg = true;
        			}
        		});
        	}
        	
        },
        cancel:function(){
        }
        
	});
}

/**
 * 清空文件列表
 */
function clearFiles(){
	delete files;
	$("tr[id^=upload]").remove();
}
