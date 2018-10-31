/**角色授权控制js*/
/**通过d3.js 绘制SVG矢量图 */
d3.json(basePath+"systemManage/getAuthorities", function(error, root) {
for(var i = 0 ;i<root.length;i++){
	var role_management_empower_title = "<div class=\"role-management-empower-title\">"+
									    "<span style=\"color: #1f9086;font-weight: bold;\"></span>"+
									    "</div>";
	
	var role_management_empower_frame = "<div class=\"role-management-empower-frame\" style=\"overflow-x: scroll;white-space: nowrap;overflow-y: hidden; \">"+
    									"<div class=\"role-management-empower-main\">"+
    									"</div>"+
    									"</div>";
	$(".role-authority").append("<div class=\"authority-group\">"+role_management_empower_title+role_management_empower_frame+"</div>");
	var width = 800, height = 800;
	var cluster = d3.layout.tree().size([width, height - 200]);
	var svg = d3.select($(".role-management-empower-main")[i]).append("svg")
	.attr("width", width)
	.attr("height", height)
	.append("g")
	.attr("transform", "translate(40,0)");
	
    var nodes = cluster.nodes(root[i]);
    var links = cluster.links(nodes);
    var link = svg.selectAll(".link")
      .data(links)
      .enter()
      .append("path")
      .attr("class", "link")
      .attr("d", function(d){
        return "M"+d.source.y+" "+d.source.x+
        "L"+(d.source.y+140)+" "+d.source.x+
        " L"+(d.source.y+140)+" "+d.target.x+" L"+
        d.target.y+" "+d.target.x;

      })
      .attr("style",function(){
        return "stroke:#F7881F"
      });

  var node = svg.selectAll(".node")
      .data(nodes)
      .enter()
      .append("g")
      .attr("class", "node")
      .attr("transform", function(d) { 
        return "translate(" + (d.y-20) + "," + (d.x+ -10) + ")"; 
      });

  node.append("rect")
    .attr("width",function(d){
    	var length = d.name.length;
    	var exLength = (length-8)>0?(length-8)*12:0;
    	return 120+exLength;
    })
    .attr("height",20)
    .attr("x",0)
    .attr("y",0)
    .attr("style","fill:#35AD5B;");
    
  var circle = node.append("circle")
  .attr("cx",12)
  .attr("cy",10)
  .attr("r",10)
  .attr("fill","#EFFAFF");
  
circle.on("click", function(){
  if($(this).next("polyline").attr("is-checked")=="Y"){
      $(this).next("polyline").attr("stroke","#bbb").attr("is-checked","N");
      var parentPid = $(this).next("polyline").attr("pid");
      //取消选中，则其所有子权限均取消
      cancelSubAuthorities(parentPid);
  }else{
      $(this).next("polyline").attr("stroke","red").attr("is-checked","Y");
      var subPid = $(this).next("polyline").attr("parent_id");
      var parentPid = $(this).next("polyline").attr("pid");
      //选中则其所有父权限均选中，且子权限均选中
      selectedParentAndSubAuthorities(subPid,parentPid);
  }
});
node.append("polyline")
  .attr("points","3,10 10,18 18,2")
  .attr("stroke","#bbb")
  .attr("stroke-width",2)
  .attr("is-checked","N")
  .style("fill","none")
  .attr("pid",function(d){
      return d.pid;
  }).attr("parent_id",function(d){
	  if(d.parent_id==0){
		  $($(".role-management-empower-title")[i]).find("span").text(d.name);
	  }
	  return d.parent_id;
  });

 node.append("text")
    .attr("dx", function(d) { 
    	var length = d.name.length;
    	var exLength = (length-8)>0?((length-8)/8)*50:0;
      return 70+exLength;
    })
    .attr("dy", 14)
    .style("text-anchor", function(d) { 
      return "middle";
    })
    .style("fill","#fff")
    .text(function(d) { return d.name; });
}
fillRoleAuthorities();

});

/**
 * 默认选中已有权限
 * @param auths
 */
function fillRoleAuthorities(){
	for(var i=0; i<auths.length; i++){
		$("polyline[pid="+auths[i]+"]").attr("stroke","red").attr("is-checked","Y");
	}
}
/**
 * 取消选中，则其所有子权限均取消
 * @param pid
 */
function cancelSubAuthorities(pid){
	if($("polyline[parent_id="+pid+"]").length==0){
		return false;
	}
	$("polyline[parent_id="+pid+"]").attr("stroke","#bbb").attr("is-checked","N");
	$("polyline[parent_id="+pid+"]").each(function(){
		pid=$(this).attr("pid");
		cancelSubAuthorities(pid);
	});
}


/**
 * 选中所有子权限
 * @param pid
 */
function selectedSubAuthorities(pid){
	if($("polyline[parent_id="+pid+"]").length==0){
		return false;
	}
	$("polyline[parent_id="+pid+"]").attr("stroke","red").attr("is-checked","Y");
	$("polyline[parent_id="+pid+"]").each(function(){
		pid=$(this).attr("pid");
		selectedSubAuthorities(pid);
	});
}

/**
 * 子权限选中则其所有父权限均选中
 * @param pid
 */
function selectedParentAuthorities(pid){
	if($("polyline[pid="+pid+"]").length==0){
		return false;
	}
	$("polyline[pid="+pid+"]").attr("stroke","red").attr("is-checked","Y");
	pid = $("polyline[pid="+pid+"]").attr("parent_id");
	selectedParentAuthorities(pid);
}
/**
 * 选中所有分子权限
 * @param subPid
 * @param parentPid
 */
function selectedParentAndSubAuthorities(subPid,parentPid){
	selectedParentAuthorities(subPid);
	selectedSubAuthorities(parentPid);
}



$(function(){
	var sys_mange_auths =0;
	var sys_mange_seleds = 0;
	$(".role-management-empower-baocun").click(function(){
		sys_mange_auths=0;
		sys_mange_seleds=0;
		//首先校验 角色是否勾选了权限
		var checkedLen = $("polyline[is-checked=Y]").length; 
		if(checkedLen>0){
			var pids ="";
			$("polyline[is-checked=Y]").each(function(i,n){
				if(i==checkedLen-1){
					pids+=$(n).attr("pid");
					return false;
				}
				pids+=$(n).attr("pid")+",";
			});
			var roleId = $("#roleId").val();
			if(!roleId){
				layer.msg("没有获取到角色信息！",{icon:7});
				return false;
			}
			if("admin"==code){
				var sys_manage_pid = 0;
				$("polyline[parent_id=0]").each(function(i,n){
					if($(this).next().text()=="系统管理"){
						sys_manage_pid = $(this).attr("pid");
						return false;
					}
				});
				if(sys_manage_pid!=0){
					//获取系统管理已选中个数
					getSysteMangeAuths(sys_manage_pid);
					if(sys_mange_auths!=sys_mange_seleds){
						layer.msg("系统管理员，系统管理权限应全部勾选，请勾选未勾选权限",{icon:7});
						return false;
					}
				}
			}
			
			$.ajax({
			    url: basePath+"systemManage/addRoleAuthorities",
			    type: "POST",
			    data:{
			    	authorityPids:pids,
			    	roleId:roleId
			    },
			    async: false,
				success : function(msg) {
					if (msg && msg["successMsg"]) {
						layer.msg(msg["successMsg"],{icon: 1});
						setTimeout(function () {
							history.go(-1);
					     }, 1500);
					} else {
						layer.msg(msg["errMsg"],{icon:7});
					}
				},
				error:function(msg){
					alert(msg["errMsg"]);
				}
			});
		}else{
			layer.msg("请选择角色权限信息！",{icon:7});
		}
	});
	
	function getSysteMangeAuths(pid){
		if($("polyline[pid="+pid+"]").length==0){
			return;
		}
		var isChecked = $("polyline[pid="+pid+"]").attr("is-checked");
		if(isChecked=="Y"){
			sys_mange_seleds++;
		}
		sys_mange_auths++;
		$("polyline[parent_id="+pid+"]").each(function(){
			pid=$(this).attr("pid");
			getSysteMangeAuths(pid);
		});
	}
	
});