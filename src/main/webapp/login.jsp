<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringSecurity3 Login</title>
</head>
<body onload="document.f.j_username.focus();">
	<h3>Login with Username and Password</h3>
	
	<form name="f" action="/SpringSecurity3/j_spring_security_check" method="POST">
		<table>
			<tbody>
				<tr>
					<td>User:</td>
					<td><input type="text" name="j_username" value=""></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password"></td>
				</tr>
				<tr>
					<td colspan="2"><input name="submit" type="submit"
						value="Login"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>