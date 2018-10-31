<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="/WEB-INF/tag/tags.tld" prefix="fund"%>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   request.setAttribute("basePath", basePath);
%>
<link href="<%=path%>/common/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="<%=path%>/common/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="<%=path%>/common/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="<%=path%>/common/css/bootstrapValidator.css" rel="stylesheet">
<link href="<%=path%>/common/css/jquery-ui.css" rel="stylesheet">
<!-- jqgrid-->
<link href="<%=path%>/common/css/ui.jqgridffe4.css?0820" rel="stylesheet">
<link href="<%=path%>/common/js/plugins/layui/css/layui.css" rel="stylesheet">
<link href="<%=path%>/common/css/profile.min.css" rel="stylesheet">
<link href="<%=path%>/common/css/base.css" rel="stylesheet">
<!--浏览器顶部显示公司品牌图标引入  -->
<link rel="shortcut icon" type="image/x-icon" href="<%=path%>/common/img/iconkey.ico" media="screen" />
 
<script type="text/javascript" src="<%=path%>/common/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/common/js/jquery-ui.js"></script>

<!--[if lt IE 9]>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <![endif]-->

<!-- jquery-form.js -->
<!-- bootstrap -->
<script src="<%=path%>/common/js/bootstrap.min.js?v=3.3.6"></script>
<!-- jqgrid-->
<script src="<%=path%>/common/js/plugins/jqgrid/i18n/grid.locale-cnffe4.js?0820"></script>
<script src="<%=path%>/common/js/plugins/jqgrid/jquery.jqGrid.minffe4.js?0820"></script>
<script type="text/javascript" src="<%=path%>/common/js/plugins/layer/layer.js"></script>
<script src="<%=path%>/common/js/plugins/layui/layui.js"></script>
<!-- validate表单校验 -->
<script src="<%=path%>/common/js/plugins/validate/bootstrapValidator.js"></script>
<script src="<%=path%>/common/js/plugins/validate/zh_CN.js"></script>
<script src="<%=path%>/common/js/plugins/validate/validator.js"></script>
<script type="text/javascript" src="<%=path%>/common/js/common.js" ></script>
<script type="text/javascript">
//声明basePath
var basePath = "${basePath}";

</script>