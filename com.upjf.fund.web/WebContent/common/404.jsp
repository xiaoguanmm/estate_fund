<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>404</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=path%>/common/css/bootstrap.min14ed.css" rel="stylesheet">
    <!-- custom CSS here -->
    <style type="text/css">
    body {
	    font-family: 'Nova Flat', cursive;
	    background-color: #1E96F4;
		color: #fff;
	}
	.pad-top {
	    padding-top:60px;
	}
	.text-center {
	    text-align:center;
	}
	#error-link {
	    font-size:150px;
	    padding:10px;
	}
    </style>
</head>
<body>
    
   
       <div class="container">
      
          <div class="row pad-top text-center">
                 <div class="col-md-6 col-md-offset-3 text-center">
                  <h1><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 噢，你做了什么？ </font></font></h1>
                   <h5><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 找不到了</font></font></h5> 
              <span id="error-link"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">404</font></font></span>
                     </div>
        </div>

    </div>
  
    <script src="<%=path%>/common/js/jquery.min.js"></script>
    <!--bootstrap JavaScript file  -->
    <script src="<%=path%>/common/js/bootstrap.min.js"></script>
     <!--Count Number JavaScript file  -->
    <script src="<%=path%>/common/js/countUp.js"></script>
       <!--Custom JavaScript file  -->
    <script src="<%=path%>/common/js/custom.js"></script>
    
</body>
</html>