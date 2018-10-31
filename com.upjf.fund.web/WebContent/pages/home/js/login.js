$(function() {
	/*如果session失效直接在顶层跳转到登录页面*/
	if(window !=top){  
	    top.location.href=location.href;  
	} 
	$('#Kaptcha').click(    
	        function() {
	        	$(this).hide().attr('src',$(this).attr("src")+"?" + Math.floor(Math.random() * 100)).fadeIn();     
	    });
	    
	$("#loginForm").validate({
		rules : {
			u_xname : {
				required : true
			},
			password : {
				required : true
			},
			captcha : {
				required : true
			}
		},
		submitHandler : function(form) {
			$.ajax({
			    url: $("#loginForm").attr("action"),
			    cache: true,
			    type: "POST",
			    data: $("#loginForm").serialize(),
			    async: false,
				success : function(msg) { //表单提交后更新页面显示的数据
					if (msg && msg["successMsg"]) {
						window.location.href=$("#loginForm").attr("action").replace("/login","/main");
					} else {
						layer.msg(msg["errMsg"], {time:1000,icon:7});
						if("验证码错误"==msg["errMsg"]||"验证码失效"==msg["errMsg"]){
							$("input[name=captcha]").val("");
							$("#Kaptcha").trigger("click");
						}
					}
				},
				error:function(msg){
					alert(msg["errMsg"]);
				}
			});
			return false;
		}
	});
});

