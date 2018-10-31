$(function() {
	var exportUrl = $("#exportUrl").val();
	/** 导出记录* */
	$("#export").on("click", function() {
		/* 首先判断记录总条数超过10000条不允许导出，影响性能 */
		var records = $("#data_list").jqGrid('getGridParam', 'records');
		if (records > 10000) {
			layer.msg("导出记录数不允许超过10000条，请重新筛选数据后再行导出", {
				time : 1000,
				icon : 7
			});
			return;
		}
		if(records==0){
			layer.msg("没有记录可以导出", {
				time : 1000,
				icon : 7
			});
			return;
		}
		var param = $("#data_list").jqGrid('getGridParam');
		var data = param.postData;
		var dataParams = "";
		var prjId = $("#prjId").val();
		if (prjId) {
			data["prjId"] = prjId;
		}
		var receiverId = $("#receiverId").val();
		if (receiverId) {
			data["receiverId"] = receiverId;
		}
		var receivedType = $("#receivedType").val();
		if (receivedType) {
			data["receivedType"] = receivedType;
		}

		for ( var key in data) {
			dataParams += key + "=" + data[key] + "&";
		}
		window.location.href = exportUrl + "?" + dataParams;
	});
});
/**
 * 查看项目详情
 * 
 * @param url
 */
function showProjectInfo(prjId) {
	if ($.trim(prjId) == "") {
		layer.msg("找不到项目信息", {
			icon : 7
		})
		return;
	}
	window.location.href = basePath + "projectManageController/toProject?type=3&projectPid=" + prjId;
}