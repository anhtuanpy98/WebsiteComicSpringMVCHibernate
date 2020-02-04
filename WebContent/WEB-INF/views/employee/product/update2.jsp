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
<title>LAB 6</title>
</head>
<body>
<%@ include file="header.jsp" %>
${message}

	<form:form action="import/insert4.htm" modelAttribute="product2">
					<div style="display:none">
						<label>Id</label>
			            <form:input type="text" path="id"/>
					</div>
					<div>
						<label>Name</label>
			            <form:input type="text" path="name"/>
					</div>
					
					<div>
						<label>Số lượng</label>
			            <form:input type="number" path="amount" min="0"/>
					</div>
		<div>
			<button class = "btn btn-default">Sửa số lượng truyện</button>
		</div>
	</form:form>
	<form:form action= "import/index.htm">
		<div>
			<button class = "btn btn-default">Index</button>
		</div>
	</form:form>
	<%@ include file="footer.jsp" %>
</body>
</html>
