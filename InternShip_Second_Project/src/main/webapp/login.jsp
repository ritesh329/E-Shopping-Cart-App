<%@page import="shoppingcart.DbCon"%>
<%@ page import=" model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shopping cart login</title>
<%@include file="includes/head.jsp"%>
</head>
<body>

	<div class="container">

		<div class="card w-50 mx-auto my-5">

			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">

					<div class="form-group">

						<label>Email address</label> <input type="email"
							class="form-control" name="login-email"
							placeholder="enter the email" required />


					</div>
					<div class="form-group">

						<label>password</label> <input type="password"
							class="form-control" name="login-password"
							placeholder="enter the password" required />


					</div>

					<div class="text-center">

						<button type="submit" class="btn btn-primary">Login</button>
						
					</div>
				</form>
			</div>

		</div>
	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>