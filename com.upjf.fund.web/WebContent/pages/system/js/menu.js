$(function(){
	layui.use('form',function(){
		var form = layui.form;
		form.on('select(parentId)', function(data) {
			if(data.value){
			validator.resetFormField($("#addMenuForm"),"parentId");
			if (data.value == "0") {
				$("#addMenuForm input[name=level]").val("1");
			} else {
				$("#addMenuForm input[name=level]").val("2");
			}
			}
		});
	});
	$('.targetfield-list-padding').dblclick(function(){
        $(this).siblings('.menu-rulecoding').toggle();
    });
	
	validator.validate($("#addMenuForm"));
	
	/*新增菜单*/
	$("#menuAdd").on("click",function(){
		layer.open({
            type: 1,
            title: ['新增菜单'],
            area: ['600px', '450px'],
            btnAlign: 'c',
            content: $('#menu_Manage_Add'),
            btn: ['保存', '取消'],
            success:function(index,layero){
            	$("#addMenuForm input[name=modifyFlg]").val("0");
            	initForm($("#addMenuForm"));
            	 
        	},
        	btn1: function(index,layero){
        		validator.triggerForm($("#addMenuForm"));
        		if(validator.isValid($("#addMenuForm"))){
        			if(!checkFormData()){
        				return ;
        			}
        			var url = $("#addMenuForm").attr("action");
        			var data = $("#addMenuForm").serialize();
              	  	$.ajax({
            		    url: url,
            		    cache: true,
            		    type: "POST",
            		    data: data,
            		    async: false,
            			success : function(msg) { //表单提交后更新页面显示的数据
            				if (msg && msg["successMsg"]) {
            					layer.msg(msg["successMsg"],{icon: 1});
            					window.location.reload();
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
        	btn2: function(index,layero){
        		 validator.resetForm($("#addMenuForm"));
        	},
        	cancel: function(index,layero){
        		 validator.resetForm($("#addMenuForm"));
        	}
            });
	});
	
	/*修改菜单*/
	$(".menu-modify").on("click",function(){
		var dom = $(this);
		layer.open({
            type: 1,
            title: ['修改菜单'],
            area: ['600px', '450px'],
            btnAlign: 'c',
            content: $('#menu_Manage_Add'),
            btn: ['保存', '取消'],
            success:function(index,layero){
            	$("#addMenuForm input[name=modifyFlg]").val("1");
            	fillFormData(dom);
            	 
        	},
        	btn1: function(index,layero){
        		validator.triggerForm($("#addMenuForm"));
        		if(validator.isValid($("#addMenuForm"))){
        			if(!checkFormData()){
        				return ;
        			}
        			var url = $("#addMenuForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,"/modifyMenu");
        			var data = $("#addMenuForm").serialize();
              	  	$.ajax({
            		    url: url,
            		    cache: true,
            		    type: "POST",
            		    data: data,
            		    async: false,
            			success : function(msg) { //表单提交后更新页面显示的数据
            				if (msg && msg["successMsg"]) {
            					layer.msg(msg["successMsg"],{icon: 1});
            					window.location.reload();
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
        	btn2: function(index,layero){
        		 validator.resetForm($("#addMenuForm"));
        	},
        	cancel: function(index,layero){
        		 validator.resetForm($("#addMenuForm"));
        	}
            });
	});
	
	/*删除菜单*/
	$(".menu-delete").on("click",function(){
		var maxSeriesNo;
		var parentId = $(this).parent().parent().parent().attr("alt");
    	var pid =$(this).parent().parent().attr("alt");
    	/*如果当前菜单层级是根，则判断其下是否还有子菜单，否则不予以删除*/
    	if(parentId == "0"){
    		var subMenus = $(".menu-rulecoding[alt="+pid+"]").find(">div").length;
    		if(subMenus>0){
    			layer.msg("当前菜单下存在子菜单，请先删除子菜单！",{icon:7});
    			return false;
    		}
    	}
    	var seriesNo = $(this).parent().parent().find("span[class=series-no-text]").text();
    	var maxSeriesNo;
    	if(parentId == "0"){
			/*父菜单*/
			var rootMenus = $(".targetfield-list-bottom").not(":hidden").length;
			maxSeriesNo = rootMenus;
		}else{
			//找到指定的父菜单下的子菜单组有多少子菜单
			var subMenus = $(".menu-rulecoding[alt="+parentId+"]").find(">div").length;
			maxSeriesNo = subMenus;
		}
    	layer.confirm('确认删除？',function(){
    		var url = $("#addMenuForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,"/delMenu");
			$.ajax({
      		    url: url,
      		    cache: true,
      		    type: "POST",
      		    data: {
      		    	pid:pid,
        			parentId:parentId,
        			seriesNo:seriesNo,
        			maxSeriesNo:maxSeriesNo
      		    },
      		    async: false,
      		  success:function(data){
      			if("error"==data[0]){
      				layer.msg("删除失败",{icon:7});
      			}else{
      				window.location.reload();
      			}
      		  },
      		  error:function(msg){
      			layer.msg("删除失败",{icon:7});
      		  }
      		});
		},function(){
		});
	});
})

/**
 * 初始化加载Menu列表
 * @param menus
 */
function initMenuList(menus){
	  var menusLength = menus.length;
	  if(menusLength>0){
	  var pid,name, url,level,series_no,parent_id;
		  for(var i=0;i<menusLength;i++){
			  var menuDom = $("#menuCloneTemp").clone(),
			  pid = menus[i].pid,
			  name = menus[i].name,
			  url = menus[i].url,
			  level = menus[i].level,
			  series_no = menus[i].series_no,
			  parent_id = menus[i].parent_id;
			  menuDom.attr("alt",parent_id);
			  var parentDom = menuDom.find(">div:first");
			  var childsDom = menuDom.find(">div:nth-child(2)");
			  parentDom.attr("alt",pid);
			  parentDom.attr("level",level);
			  parentDom.find("span[class=series-no-text]").text(series_no);
			  parentDom.find("span[class=menu-name-text]:eq(0)").text(name);
			  parentDom.find("span[class=menu-name-text]:eq(1)").text(url);
			  /*第一层均为根菜单故据此加载加载父菜单选项*/
			  var option = "<option value="+pid+">"+name+"</option>";
			  $("select[name=parentId]").append(option);
			  //系统菜单最多两层 故只判断一次childs子元素
			  if(typeof(menus[i].childs) != "undefined" && menus[i].childs.length>0){
				  for(var j=0;j<menus[i].childs.length;j++){
					  pid = menus[i].childs[j].pid;
					  name = menus[i].childs[j].name;
					  url = menus[i].childs[j].url;
					  level = menus[i].childs[j].level;
					  series_no = menus[i].childs[j].series_no;
					  parent_id = menus[i].childs[j].parent_id;
					  childsDom.attr("alt",parent_id);
					  var childDom = childsDom.find(">div:first").clone();
					  childDom.attr("alt",pid);
					  childDom.attr("level",level);
					  childDom.find("span[class=series-no-text]").text(series_no);
					  childDom.find("span[class=menu-name-text]:eq(0)").text(name);
					  childDom.find("span[class=menu-name-text]:eq(1)").text(url);
					  
					  childsDom.append(childDom);
				  }
				  /*移除子菜单组模板*/
				  childsDom.find(">div:first").remove();
			  }else{
				  childsDom.remove();
			  }
			  /*将目录列表设为可见并移除ID*/
			  menuDom.css("display","block");
			  menuDom.removeAttr("id");
			  $("#menuArray").append(menuDom);
		  }
		  layui.use('form',function(){
				var form = layui.form;
				form.render();
			});
		  
	  }
}

/**
 * 修改时填充表单数据
 * @param dom
 */
function fillFormData(dom){
	var parentId = $(dom).parent().parent().parent().attr("alt");
	var seriesNo = $(dom).parent().parent().find("span[class=series-no-text]").text();
	var pid = $(dom).parent().parent().attr("alt");
	var name = $(dom).parent().parent().find("span[class=menu-name-text]:eq(0)").text();
	var url = $(dom).parent().parent().find("span[class=menu-name-text]:eq(1)").text();
	var level = $(dom).parent().parent().attr("level");
	/*填充*/
	$("#addMenuForm input[name=pid]").val(pid);
	$("#addMenuForm select[name=parentId]").val(parentId);
	$("#addMenuForm input[name=seriesNo]").val(seriesNo);
	$("#addMenuForm input[name=oriSeriesNo]").val(seriesNo);
	$("#addMenuForm input[name=name]").val(name);
	$("#addMenuForm input[name=url]").val(url);
	$("#addMenuForm input[name=oriName]").val(name);
	$("#addMenuForm input[name=oriUrl]").val(url);
	$("#addMenuForm input[name=level]").val(level);
	$("#addMenuForm input[name=parentId]").val(parentId);
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
	/*修改URL地址*/
}

/**
 * 初始化表单置空
 * @param dom
 */
function initForm(dom){
	dom.find(":input").not(":disabled").not(".hide_field").val("");
	$("#addMenuForm input[name=level]").val("1");
	layui.use('form',function(){
		var form = layui.form;
		form.render();
	});
}

/**
 * 校验表单
 */
function checkFormData(){
	  //校验菜单名称
	var menu_name = $.trim($("#addMenuForm input[name=name]").val())
	var checkFlg = true;
	$("#menuArray span[class=menu-name-text]:even").each(function(){
		if($.trim($(this).text()) == menu_name  && menu_name != $.trim($("input[name=oriName]").val())){
			layer.msg("不能有相同的菜单名称",{icon:7});
			$("#addMenuForm input[name=name]").focus();
			checkFlg = false;
			return false;
		}
	});
	if(!checkFlg){
		return checkFlg;
	}
	
	var url = $.trim($("#addMenuForm input[name=url]").val())
	var url_regxp =/^(\/\w+)+$/;
	if(!url_regxp.test(url) && url!="#"){
		layer.msg("请填写合法的url地址",{icon:7});
		$("#addMenuForm input[name=url]").focus();
		return false;
	}
	$("#menuArray span[class=menu-name-text]:odd").each(function(){
		var otherUrl = $.trim($(this).text());
		if(url!="#" && url == otherUrl && url != $.trim($("input[name=oriUrl]").val())){
			layer.msg("该链接已使用",{icon:7});
			$("#addMenuForm input[name=url]").focus();
			return false;
		}
	});
	
	var rootMenus,subMenus;
	var parentId = $("#addMenuForm select[name=parentId]").val();
	var series_no = $.trim($("#addMenuForm input[name=seriesNo]").val());
	var num_regxp = /^\d+$/;
	if(!num_regxp.test(series_no)){
		layer.msg("请输入数字",{icon:7});
		$("#addMenuForm input[name=seriesNo]").focus();
		return false;
	}
	var maxSeriesNo;
	var modifyFlg =  $("#addMenuForm input[name=modifyFlg]").val();
	
	if(parentId == "0"){
		/*父菜单*/
		rootMenus = $(".targetfield-list-bottom").not(":hidden").length;
		var scope = eval(rootMenus+1);
		if(modifyFlg=="1"){
			scope = rootMenus;
		}
		if(series_no>rootMenus+1){
			layer.msg("显示序号必须在1~"+scope+"之间",{icon:7});
			$("#addMenuForm input[name=seriesNo]").focus();
			return false;
		}
		maxSeriesNo = rootMenus;
	}else{
		//找到指定的父菜单下的子菜单组有多少子菜单
		subMenus = $(".menu-rulecoding[alt="+parentId+"]").find(">div").length;
		var scope = eval(subMenus+1);
		if(modifyFlg=="1"){
			scope = subMenus;
		}
		if(series_no>subMenus+1){
			layer.msg("显示序号必须在1~"+scope+"之间",{icon:7});
			$("#addMenuForm input[name=seriesNo]").focus();
			return false;
		}
		maxSeriesNo = subMenus;
	}
	if(series_no>99){
		layer.msg("显示顺序必须小于100",{icon:7});
		$("#addMenuForm input[name=seriesNo]").focus();
		return false;
	}
	$("#addMenuForm input[name=maxSeriesNo]").val(maxSeriesNo);
	return true;
}