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