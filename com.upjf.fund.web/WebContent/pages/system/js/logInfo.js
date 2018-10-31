$(function(){
	window.dialogIndex = 1;
    
	initData();
	/*只允许查询一年内的日志*/
	layui.use('laydate',function(){
		var laydate = layui.laydate;
		laydate.render({
			elem: '#beginDate',
			type: 'datetime',
			min: getBeforeDatetime(),
			max: getNowDatetime()
		});
		laydate.render({
			elem: '#endDate',
			type: 'datetime',
			min: getBeforeDatetime(),
			max: getNowDatetime()
		});
	});
	
})

/**
 * 获取当前时间
 * @returns
 */
function getNowDatetime(){
	return new Date().format("yyyy-MM-dd HH:mm:ss");
}
/**
 * 获取一年前的日期
 * @returns
 */
function getBeforeDatetime(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	if(month<10){ 
		month = "0"+month;
	}    
	var day = date.getDate();
	return (year-1)+"-"+month+"-"+day+" 00:00:00";
}


//初始化页面数据
function initData(){
    $.jgrid.defaults.styleUI = "Bootstrap";
	$("#tableLogList").jqGrid({
		url : basePath+"system/logInfoList",
		datatype : "json",
		mtype : "POST",
		height : 'auto',
		autowidth : true,
		shrinkToFit : true,
		viewrecords : true,
		rownumbers : true,
		rownumWidth : 50,
		postData : $.serializeObject($("#searchFrom")),
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : "#pagerLogList",
		colNames : [ "序号", "操作模块", "操作内容", "操作URI","操作参数", "操作IP","操作用户","操作时间"],
		colModel : [ {
			name : "pid",
			index : "pid",
			hidden : true
		}, {
			name : "moduleName",
			index : "moduleName",
			sortable : false
		}, {
			name : "remark",
			index : "remark",
			sortable : false
		}, {
			name : "uri",
			index : "uri",
			sortable : false
		}, {
			name : "paramValue",
			index : "paramValue",
			sortable : false,
			formatter : paramValueCell
		}, {
			name : "ip",
			index : "ip",
			sortable : false
		}, {
			name : "operatorName",
			index : "operatorName",
			sortable : false
		}, {
			name : "operateDate",
			index : "operateDate",
			sortable : false
			,
			formatter : formatterDate
		}],
		gridComplete : function() { //列表生成后,给某一列绑定操作 例如删除操作
			
		}
	});
	
	$("#tableLogList").jqGrid('setLabel','rn', '序号');
	 // 设置jqgrid的宽度  
    $(window).bind("resize",function() {
	    var width = $(".jqGrid_wrapper").width();
	    $("#tableLogList").setGridWidth(width);
    })
    
	$("#tableLogList").jqGrid("navGrid", "#pagerLogList", {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : true
	});
    
    
}

//格式化长度
function paramValueCell(cellvalue, options, rowdata){
	var valueSize = 0;
	if(cellvalue != 'undefined' && cellvalue != ""&& cellvalue != null){
		valueSize = cellvalue.length;
	}
	if(valueSize > 40){
		return '<span title='+cellvalue+'>'+cellvalue.substring(0,40)+'...</span>'
	}else{
		return '<span title='+cellvalue+'>'+cellvalue+'</span>'  
	}
}