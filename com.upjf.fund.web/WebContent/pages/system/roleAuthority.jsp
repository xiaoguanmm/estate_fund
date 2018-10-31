<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">
<title>权限管理</title>
 <link href="${basePath}pages/system/css/management.css" rel="stylesheet">
 <link href="${basePath}common/css/product.css" rel="stylesheet">
 <style type="text/css">
 	.link-line{
 		width:20px;
 		height:1px;
 		background-color:#ddd;
 		display: inline-block;
 		margin-bottom:4px;
 		
 	}
 	.sub-authority{
 		margin-left: -30px;
 	}
 	.sub-authority1{
 		display: block; 
 		margin-top: -50px;
  		margin-left: 98px; 
 	}
 	
 	.inline-div{
 		float: left;
 	}
 	circle:hover,polyline:hover{
                cursor: pointer;
            }
 </style>
</head>
<body>
       <div class="bg-gray" >
<!--正文-->
        <div class="bg-white">
            <div class="bg-border">
                <div class="title-head">
                    <img src="${basePath}common/img/title-head.png">
                    <span>角色数据授权</span>
                </div>

                <div class="tabled-one">
                    <div class="role-management-empower-inpo">
                        <span>角色名称：</span><span style="color:#fe0000">${roleName}</span>
                        <input id="roleId" type="hidden" value="${roleId}">
                    </div>
                    <div class="role-authority"></div>
                    <div class="modify-sure-reset modify-sure-reset-shot modify-sure-reset-auto">
                        <button type="button" class="modify-reset modify-zong" onclick="history.go(-1);">
                            <img src="${basePath}common/img/guanbi.png">
                            <span>关闭</span>
                        </button>
                        <button type="button" class="modify-sure modify-zong role-management-empower-baocun">
                            <img src="${basePath}common/img/baocun.png">
                            <span>保存</span>
                        </button>
                    </div>
                    <div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
<style>
            .node {
              font: 12px sans-serif;
            }

            .link {
              fill: none;
              stroke: #ccc;
              stroke-width: 1.5px;
            }
            
            .selected{
                fill:#ccc;
            } 
        </style>
<script type="text/javascript" src="${basePath}common/js/management.js"></script>
<script type="text/javascript" src="${basePath}pages/system/js/d3.v3.min.js"></script>
<script type="text/javascript">
	var auths = "${authorities}";
	auths = auths.substring(auths.indexOf("[")+1,auths.indexOf("]")).split(",");
	var code = "${code}";
</script>
<script type="text/javascript" src="${basePath}pages/system/js/roleAuthority.js"></script>
</html>
