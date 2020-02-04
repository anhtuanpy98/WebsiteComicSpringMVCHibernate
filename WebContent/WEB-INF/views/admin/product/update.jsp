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
</head>
<body>
${message}

	<form:form action="import/insert2.htm" modelAttribute="product2" method="POST">
		<div>
			<label>Name mới:</label>
			<form:input path="id"/>
		</div>
		<div>
			<label>Name mới:</label>
			<form:input path="name"/>
		</div>
		
		<div>
			<label>Detail mới:</label>
			<form:input path="detail"/>
		</div>
		<div>
			<label>Price mới:</label>
			<form:input path="price"/>
		</div>
		<div>
			<label>ImageId mới:</label>
			<form:select path="image.id" items="${images}" itemValue="id" itemLabel="fileName"/>
		</div>
		<div>
			<label>PriceNew mới:</label>
			<form:input path="priceNew"/>
		</div>
		<div>
			<label>Status mới:</label>
			<form:input path="status"/>
		</div>
		<div>
			<label>Amount mới:</label>
			<form:input path="amount"/>
		</div>
		<div>
			<button class = "btn btn-default">Edit</button>
		</div>
	</form:form>
	<form action="import/index.htm" method="GET">
		<div>
			<button class = "btn btn-default">Index</button>
		</div>
	</form>
</body>
</html>