<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
	<base href = "${pageContext.servletContext.contextPath}/">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Order</title>
    
    <!-- Bootstrap core CSS -->
  <link href="startbootstrap-shop-item-gh-pages/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="startbootstrap-shop-item-gh-pages/css/shop-item.css" rel="stylesheet">

    <!-- Font Icon
    <link rel="stylesheet" href="colorlib-regform-7/fonts/material-icon/css/material-design-iconic-font.min.css">
	 -->
    <!-- Main css 
    <link rel="stylesheet" href="colorlib-regform-7/css/style.css">
    -->
</head>
<body>
<%@ include file="header.jsp" %>
	${message}
    <div class="main">
	
		<div class="col-lg-9">
			
			
			        <div class="card mt-4">
			          <img class="card-img-top img-fluid" src="${product.image}" alt="">
			          <div class="card-body">
			            <h3 class="card-title">${product.detail}</h3>
			            <h4>$${product.priceNew}</h4>
			            <p class="card-text">${product.name}</p>
			            <div>
							<button class = "btn btn-default">Edit</button>
						</div>
			          </div>
			        </div>
				
				<form:form action="form/edit2/${id}.htm" modelAttribute="product">
			        <div class="card mt-4">
			         
			            <p class="card-text">
			            <label>Nhập Amount:</label>
			            <form:input path="amount"/>
			            </p>

			            <div>
							<button class = "btn btn-default">Edit</button>
						</div>
			          </div>
			        </div>
			   </form:form>
		
		
      </div>
      <!-- /.col-lg-9 -->
	

    </div>
    <%@ include file="footer.jsp" %>
    
     <!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

    <!-- JS
    <script src="colorlib-regform-7/vendor/jquery/jquery.min.js"></script>
    <script src="colorlib-regform-7/js/main.js"></script>
     -->
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>