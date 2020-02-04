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

	<form:form action="user/insert.htm" modelAttribute="user">

		<div>
			<label>Name:</label>
			<form:input path="name"/>
		</div>
		<div>
			<label>UserName:</label>
			<form:input path="userName"/>
		</div>
		<div>
			<label>Password:</label>
			<form:input path="passWord"/>
		</div>
		<div>
			<label>Rule:</label>
			<form:input path="rule"/>
		</div>
		<div>
			<label>Status:</label>
			<form:input path="status"/>
		</div>
		
		<div>
			<label>Id_Employee:</label>
			<form:select path="employee.id" items="${employees}" itemValue="id" itemLabel="name"/>
		</div>
			
		</div>
		<div>
			<button class = "btn btn-default">Insert</button>
		</div>
	</form:form>
	
	<form action="user/index.htm" method="GET">
		<div>
			<button class = "btn btn-default">Index</button>
		</div>
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>