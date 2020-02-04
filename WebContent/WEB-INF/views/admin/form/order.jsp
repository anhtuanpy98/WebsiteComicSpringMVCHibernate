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
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	  <meta name="description" content="">
	  <meta name="author" content="">
    
    <!-- Bootstrap core CSS -->
  <link href="startbootstrap-shop-item-gh-pages/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="startbootstrap-shop-item-gh-pages/css/shop-item.css" rel="stylesheet">
    
    <title>Order</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="colorlib-regform-7/fonts/material-icon/css/material-design-iconic-font.min.css">
	 
    <!-- Main css -->
    <link rel="stylesheet" href="colorlib-regform-7/css/style.css">
     
</head>
<body>
<%@ include file="header.jsp" %>
	${message}
	
		<div class="col-lg-9">
			
			<c:forEach var="u" items="${products}">
				
				<form:form action="form/edit/${u.id}.htm">
			        <div class="card mt-4">
			          <img class="card-img-top img-fluid" src="${u.image}" alt="">
			          <div class="card-body">
			            <h3 class="card-title">${u.detail}</h3>
			            <h4>$${u.priceNew}</h4>
			            <p class="card-text">${u.name}</p>
			            <p class="card-text">${u.amount}</p>
			            <a href="form/delete/${u.id}.htm">Delete</a>
			            <div>
							<button class = "btn btn-default">Edit</button>
						</div>
			          </div>
			        </div>
			   </form:form>
		</c:forEach>
		
      </div>
      <!-- /.col-lg-9 -->
		
		
		
        <!-- Sign up form
        <section class="signup">
            <div class="container">
             
                <div class="signup-content">
                    <div class="signup-form">
                    -->
                        <h2 class="form-title">Order</h2>
                        <form action="form/order-identify.htm" method="POST" class="register-form" id="register-form" modelAttribute="email">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="name" id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="phone"><i class="zmdi zmdi-account material-icons-phone"></i></label>
                                <input type="text" name="phone" id="phone" placeholder="Your PhoneNumber"/>
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="Your Email"/>
                            </div>
                           
                            
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="ORDER"/>
                            </div>
                        </form>
                        <!-- 
                    </div> 
                </div>   
            </div>
        </section>
         -->

    <%@ include file="footer.jsp" %>

    <!-- JS -->
    <script src="colorlib-regform-7/vendor/jquery/jquery.min.js"></script>
    <script src="colorlib-regform-7/js/main.js"></script>
    
    <!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>