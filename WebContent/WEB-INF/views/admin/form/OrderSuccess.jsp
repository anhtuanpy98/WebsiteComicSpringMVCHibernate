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
${message}
${message2}
<%@ include file="header.jsp" %>
	<div>
		<p>Order Success</p>
	</div>
	
	<div class="col-lg-9">
	<c:forEach var="u" items="${products}">
			<form action="form/order/${u.id}.htm">
	        <div class="card mt-4">
	          <img class="card-img-top img-fluid" src="${u.image}" alt="">
	          <div class="card-body">
	            <h3 class="card-title">${u.detail}</h3>
	            <h4>$${u.priceNew}</h4>
	            <p class="card-text">${u.name}</p>
	          </div>
	        </div>
			</form>
		</c:forEach>
      </div>
      <!-- /.col-lg-9 -->
	
<%@ include file="footer.jsp" %>
 <!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>