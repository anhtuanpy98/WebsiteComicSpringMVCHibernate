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

	<form:form action="employee/insert.htm" modelAttribute="employee" method="POST">
		<div style="display:none">
			<label>Id:</label>
			<form:input path="id"/>
		</div>
		<div>
			<label>Name:</label>
			<form:input path="name" type="text"/>
		</div>
		<div>
			<label>Address:</label>
			<form:input path="addRess" type="text"/>
		</div>
		<div>
			<label>Birthday:</label>
			<form:input path="birthDay"/>
		</div>
		<div>
			<label>PhoneNumber:</label>
			<form:input path="phoneNumber" type="text" />
		</div>
		<div>
			<button class = "btn btn-default">Insert</button>
		</div>
	</form:form>
	
	<form action="employee/index.htm" method="GET">
		<div>
			<button class = "btn btn-default">Index</button>
		</div>
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>