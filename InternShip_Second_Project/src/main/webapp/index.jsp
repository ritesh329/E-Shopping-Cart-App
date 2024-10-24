<%@ page import="model.*"%>
<%@ page import="DAO.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="servlet.*"%>
<%@ page import="shoppingcart.DbCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
System.out.print(auth);




if (auth != null) {
	System.out.print(auth);
	request.setAttribute("auth", auth);
}

ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);
}

productDao pd = new productDao(DbCon.getConnection());
List<product> products = pd.getAllProducts();


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
<%@include file="includes/head.jsp"%>
</head>
<body>

	<%@include file="includes/navbar.jsp"%>

	<div class="container">

		<div class="card-header my-3">All Products</div>
		<div class="row">


			<%
			if (!products.isEmpty()) {
				for (product p : products) {
			%>

			<div class="col-md-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="product-image/<%=p.getImage()%>" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Price :<%=p.getPrice()%>
						</h6>
						<h6 class="category"><%=p.getCategory()%>
						</h6>
						<p class="mt-3 d-flex justify-content-between">

							<a href="Add-to-cart?id=<%=p.getId()%>"
								class="btn btn-primary btn-dark">Add to card</a> <a
								href="Order-now-servlet?quantity=1&id=<%=p.getId()%>"
								class="btn btn-primary">Buy Now</a>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>