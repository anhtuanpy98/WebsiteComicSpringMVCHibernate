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
<title>Comic</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<table class="table table-hover">
		<tr>
			<th>Mã NV</th>
			<th>Họ và tên</th>
			<th>Địa chỉ</th>
			<th>Ngày sinh</th>
			<th>Số điện thoại</th>
		</tr>
	
		<c:forEach var="s" items="${employees}">
			<tr>
				<td>${s.id}</td>
				<td>${s.name}</td>
				<td>${s.addRess}</td>
				<td><fmt:formatDate value="${s.birthDay}" pattern="MM-dd-yyyy"/></td>
				<td>${s.phoneNumber}</td>
				<td><a href="employee/delete/${s.id}.htm">Delete</a></td>
				<td><a href="employee/edit/${s.id}.htm">Update</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<form action="employee/insert.htm" method="GET">
		<div>
			<button class = "btn btn-default">Insert</button>
		</div>
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>