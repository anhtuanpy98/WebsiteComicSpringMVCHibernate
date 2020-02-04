<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href = "${pageContext.servletContext.contextPath}/">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>
    
    <!-- Bootstrap core CSS -->
  <link href="startbootstrap-shop-item-gh-pages/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="startbootstrap-shop-item-gh-pages/css/shop-item.css" rel="stylesheet">

    
</head>
<body>
	
    <div class="container">
 		<%@ include file="header.jsp" %>
        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form:form method="POST" class="register-form" id="register-form" acton="user/insert.htm" modelAttribute="user">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <form:input type="text" path="name" id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="userName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <form:input type="text" path="userName" id="userName" placeholder="Your UserName"/>
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <form:input type="password" path="passWord" id="pass" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <label for="rule"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <form:input type="text" path="rule" id="rule" placeholder="Your Rule"/>
                            </div>
                            <div class="form-group">
                                <label for="status"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <form:input type="text" path="status" id="rule" placeholder="Your Status"/>
                                
                            </div>
                            <div class="form-group">
                                <label for="Id_Employee"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <form:select path="employee.id" items="${employees}" itemValue="id" itemLabel="name"/>
                            </div>
                            
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
        
        <form action="user/index.htm" method="GET">
			<div>
				<button class = "btn btn-default">Index</button>
			</div>
		</form>
        
		<%@ include file="footer.jsp" %>
    </div>
    
	<!-- Bootstrap core JavaScript -->
  <script src="startbootstrap-shop-item-gh-pages/vendor/jquery/jquery.min.js"></script>
  <script src="startbootstrap-shop-item-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
		

</html>