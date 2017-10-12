<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form class="form-signin" method="POST"
		action="/springsecurity3/j_spring_security_check">
		<input type="text" class="form-control" name="j_username"
			placeholder="Username" required autofocus> <br> 
		<input
			type="password" class="form-control" name="j_password"
			placeholder="Password" required> <input type="submit"
			class="submit-btn" value="Sign in">
	</form>
</body>
</html>