<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href = "${pageContext.servletContext.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BanTruyenTranh</title>
 
    <!-- Bootstrap core CSS -->
  <link href="startbootstrap-shop-item-gh-pages/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="startbootstrap-shop-item-gh-pages/css/shop-item.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
${message}

	<form:form action="image/insert.htm" enctype="multipart/form-data" modelAttribute="image" method="post">
		<div>
			<label for="Description"><i class="zmdi zmdi-account material-icons-name"></i></label>
            <form:input type="text" path="description" id="description"/>
		</div>
		
		<div class="form-group">
			<div>Hình ảnh</div>
			<input name="photo" type="file">
		</div>
		
		<div>
			<button class = "btn btn-default">Insert</button>
		</div>
		
	</form:form>
	
	<form:form action= "image/index.htm">
		<div>
			<button class = "btn btn-default">Index</button>
		</div>
	</form:form>
	<%@ include file="footer.jsp" %>
	
<!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>