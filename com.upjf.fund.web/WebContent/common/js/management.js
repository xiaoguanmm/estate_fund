$(function(){
	window.dataType = {
			"zh0-25":/^[\u4E00-\u9FA5\uf900-\ufa2d]{0,25}$/,//中文1-25字符
			"en1-200":/^[0-9a-zA-Z_-]{1,200}$/	//英文数据组下划线1-50字符
		}
	window.successCallback = null;
	window.errorCallback = null;
});


//***************************************************************用户管理*************************************************************//
//菜单管理--双击横条---显示隐藏下拉内容
$(function(){
    $('.targetfield-list-padding').dblclick(function(){
        $(this).siblings('.menu-rulecoding').toggle();
    });
})




/**
 * 打开隐藏域
 * @param dom
 */
function openHideArea(dom){
	var	area= ["700px", "700px"];
	if(typeof(dom)!="undefined"){
		var width = ($(dom).outerWidth()+2)+"px";
		var height = ($(dom).outerHeight()+2)+"px";
		area=[width,height]
	}
	dialogIndex = layer.open({
		type: 1,
		title: false ,
		closeBtn: 0,
		skin: "layui-layer-nobg", //没有背景色
		content: $(dom),
		area: area
	});
}
$(function(){
	 /*************************公共部分start*****************************/
	window.dialogIndex = 1;
	$('.TureOff,.tureoff,.personal-data-close,.user-management-newuser-close,.user-management-authorization-tureoff,.user-management-authorization-close,.import-excel-no').click(function() {
		if($(this).parents("form").length>0){
			$(this).parents("form").find("input[type=text]").val("");
			$(this).parents("form").find("input[type=password]").val("");
			$(this).parents().find("form").find("textarea").val("");
			$(this).parents("form").find("input[type=radio]").attr("checked",false);
		}else if($(this).parent().find("form").length>0){
			$(this).parent().find("form").find("input[type=text]").val("");
			$(this).parent().find("form").find("input[type=password]").val("");
			$(this).parent().find("form").find("textarea").val("");
			$(this).parent().find("form").find("input[type=radio]").attr("checked",false);
		}
		if($(this).attr("class").indexOf("user-management-authorization-close")>0){
			$("#roles").empty();
		}
		/*移除以显示的错误信息*/
		$(".Validform_error").removeClass("Validform_error");
		$(".Validform_wrong").removeClass("Validform_wrong");
		
		layer.close(dialogIndex);
		
	});
	
	 $(".search-btn-chaxu").click(function(){
		 params={};
		 $(".search-box").find(":input").not("button").each(function(){
			var fieldName = $(this).attr("name");
			var fieldVal = $(this).val();
			params[fieldName] = fieldVal
		 });
		 $("#data_list").jqGrid('setGridParam',{  
	            datatype:'json',  
	            postData:params, //发送数据  
	            page:1  
	        }).trigger("reloadGrid"); 
	 });
	 
	 $(".search-btn-cz").click(function(){
		 $(".search-box").find(":input").val("");
	 });
	 
	 $("#refresh_table_list").click(function(){
		 $("#data_list").trigger("reloadGrid");
	 });
	 
	 
	 
	 /*************************公共部分end*****************************/
	 
	/*************************用户管理start*****************************/
	
	 /*************************用户管理end*****************************/
	 
	 /*************************权限管理start*****************************/
	/*$('#addAuthority').click(function() {
		$("#form_title").text("新增权限");
    	$(".targetfield-details-title-h5 h5").text("新增权限列表");
    	$("#addAuthorityForm").attr("action",$("#addAuthorityForm").attr("action").replace(/\/[a-zA-Z0-9]+([a-zA-Z0-9])$/,"/addAuthority"));
    	$("#addAuthorityForm input[name=parentId]").val(0);
    	openHideArea($(".authority-data"));
    	$('select[name=type]').val("").trigger('change');

	});*/
	 /*************************权限管理end*****************************/

	
	 /*************************角色管理start*****************************/
	/*************************角色管理end*****************************/
	
})
