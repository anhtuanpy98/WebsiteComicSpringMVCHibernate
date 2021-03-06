<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
		<base href = "${pageContext.servletContext.contextPath}/">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Comic Item -</title>

  <!-- Bootstrap core CSS -->
  <link href="startbootstrap-shop-item-gh-pages/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="startbootstrap-shop-item-gh-pages/css/shop-item.css" rel="stylesheet">

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
						<form action="form/order/${u.id}.htm">
				        <div class="card mt-4">
				          <img class="card-img-top img-fluid" src="images/${u.image.fileName}" alt="">
				          <div class="card-body">
				            <h3 class="card-title">${u.detail}</h3>
				            <h4>$${u.priceNew}</h4>
				            <p class="card-text">${u.name}</p>
				          </div>
				        </div>
						</form>
					</div>
				</div>
			</c:forEach>
			</div>
      </div>
      <!-- /.col-lg-9 -->
      </div>
      </div>

	<form:form action="form/order-success.htm" modelAttribute="code">
		<div>
			<label>Họ và tên khách hàng:</label>
			<form:input path="customer.name"/>
		</div>
		
		<div>
			<label>Email:</label>
			<form:input path="customer.email"/>
		</div>
		
		<div style="display:none">
			<label>Địa chỉ khách hàng:</label>
			<form:input path="customer.addRess"/>
		</div>
		
		<div style="display:none">
			<label>Số điện thoại:</label>
			<form:input path="customer.tel"/>
		</div>
		
		<div>
			<label>Mã xác nhận:</label>
			<form:input path="ma"/>
		</div>
		<div>
			<button class = "btn btn-default">Xác nhận</button>
		</div>
	</form:form>
<%@ include file="footer.jsp" %>
 <!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>