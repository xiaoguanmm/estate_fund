  $(function() {
	window.dialogIndex = 1;
	$('.popup-sign-out0').click(function() {
		layer.confirm('退出风控模型系统',function(){
			window.location.href = basePath+"logout";
		},function(){
		});
	});
	
	/*$('.personal-data1').click(function() {
		openHideArea($(".personal-data"));

	});*/
	$('.TureOff,.tureoff,.personal-data-close').unbind("click");
	$('.TureOff,.tureoff,.personal-data-close').click(function() {
		layer.close(dialogIndex);
		$("input[name$=password]").val("");
		/*移除以显示的错误信息*/
		$(".Validform_error").removeClass("Validform_error");
		$(".Validform_wrong").removeClass("Validform_wrong");
	});
	
	/*清空密码栏位*/
	$(".modify-reset").click(function(){
		$("input[name$=password]").val("");
	});
	
/*	dataType["sameori"] = function(gets,obj,curform,regxp){
		var oripassword = $("input[name=oripassword]").val();
		if(gets==oripassword){
			return false;
		}
	}*/
	
/*	var v_form = $("#userInfoForm").Validform({
		tiptype:3,
		showAllError:true,
	    ajaxPost:true,
	    postonce:true,
	    ignoreHidden:true,
		datatype:dataType,
	    beforeCheck : function(curform) {
	     	
	    },
	    beforeSubmit : function(curform) {
	    	
	    },
		callback:function(msg){
				if (msg && msg["successMsg"]) {
					layer.msg(msg["successMsg"]+"请重新登录",{icon: 1});
					setTimeout(function () {
						window.location.href=basePath+"logout";
				     }, 1500);
				} else {
					layer.msg(msg["errMsg"],{icon:7});
				}
			}
		});*/
/*	v_form.addRule([{
		ele:"#newPwd",
		dataype:"sameori",
		nullmsg:"请输入密码",
		errormsg:"不能与原密码一致"
	}]);*/
});
  
  /**
   * 加载主页面菜单
   */
  function loanMainMenuList(menus){
	   /*加载菜单*/
	  var menus = [menus][0];
	  var menusLength = menus.length;
	  if(menusLength>0){
	  var pid,name, url,level,series_no,parent_id,icon;
	  var rootPath = basePath;
	  var idx=1;
		  for(var i=0;i<menusLength;i++){
			  var menuDom = $("#mainMenuCloneTemp").clone(); 
			  name = menus[i].name;
			  url = menus[i].url;
			  icon = menus[i].icon;
			  var parentDom = menuDom.find(">a");
			  var childsDom = menuDom.find(">ul");
			  if(icon!=""&&typeof(icon)!="undefined"){
//				  parentDom.find("i img").attr("src",rootPath+"/common/img/"+icon);
			  }
			  parentDom.find(".nav-label").text(name);
			  if(url=="#"){
				  parentDom.attr("href","javascript:;");
			  }else{
				  parentDom.attr("href",url);
			  }
			  //risk系统菜单最多两层 故只判断一次childs子元素
			  if(typeof(menus[i].childs) != "undefined" && menus[i].childs.length>0){
				  for(var j=0;j<menus[i].childs.length;j++){
					  name = menus[i].childs[j].name;
					  url = menus[i].childs[j].url;
					  var path = rootPath+url;
					  if(url=="#"){
						  path="javascript:;";
					  }
					  var childDom = childsDom.find(">li:first").clone();
					  childDom.find("a").attr("onclick","openTab('"+path+"','"+name+"','"+path+"')").attr("data-index",idx).text(name);
					  childsDom.append(childDom);
					  idx++;
				  }
				  /*移除子菜单组模板*/
				  childsDom.find(">li:first").remove();
			  }else{
				  childsDom.remove();
			  }
			  /*将目录列表设为可见并移除ID*/
			  menuDom.css("display","block");
			  menuDom.removeAttr("id");
			  $("#side-menu").append(menuDom);
		  }
	  }
	  
  }