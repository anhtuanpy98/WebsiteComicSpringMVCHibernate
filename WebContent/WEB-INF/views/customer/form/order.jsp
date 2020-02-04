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

     
</head>
<body>
<%@ include file="header.jsp" %>
	
	 <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Comic</h1>

      </div>
      <!-- /.col-lg-3 -->
      <div class="col-lg-9">
	      <div class="row">

				<c:forEach var="u" items="${products}">
					 <div class="col-lg-4 col-md-6 mb-4">
		           		 <div class="card h-100">
						
						<form:form action="form/edit/${u.id}.htm">
					        <div class="card mt-4">
					          <img class="card-img-top img-fluid" src="images/${u.image.fileName}" alt="">
					          <div class="card-body">
					            <h3 class="card-title">${u.detail}</h3>
					            <h4>$${u.priceNew}</h4>
					            <p class="card-text">${u.name}</p>
					            <p class="card-text">Số lượng: ${u.amount}</p>
					            <p class="card-text">Tổng tiền: ${u.amount*u.priceNew}</p>
					            <a href="form/delete/${u.id}.htm">Bỏ mua</a>
					            <div>
									<button class = "btn btn-default">Số lượng mua</button>
								</div>
					          </div>
					        </div>
					   </form:form>
					   </div>
				   </div>
			</c:forEach>
			
	      </div>
	      
	      <h2 class="form-title">Đặt hàng</h2>
	      				<div>
							<label>Tổng tiền: Xem trên thanh công cụ ${TongTien}</label>
						
						</div>

                        <form:form action="form/order-identify.htm" method="POST" modelAttribute="customer">
							<div>
								<label>Name</label>
								<form:input path="name" type="text"/>
							</div>
							<!-- 
							<div>
								<label>Birthday</label>
								<form:input path="birthDay" type="date"/>
							</div>
							-->
							<div>
								<label>Address</label>
								<form:input path="addRess" type="text"/>
							</div>
							
							<div>
								<label>Tel</label>
								<form:input path="tel" type="text"/>
							</div>
							 
							<div>
								<label>Email</label>
								<form:input path="email" type="email"/>
							</div>
							
							<div>
								<button class = "btn btn-default">Mua</button>
							</div>
						</form:form>
	      
	   </div>

      </div>

      </div>
    <%@ include file="footer.jsp" %>

   
    
    <!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>