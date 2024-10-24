<%@ page import="model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="shoppingcart.DbCon"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="DAO.productDao"%>
<%@ page import="java.text.DecimalFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);

}

ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
List<cart> cartProduct = null;
if (cart_list != null) {
	productDao pDao = new productDao(DbCon.getConnection());
	cartProduct = pDao.getCardProducts(cart_list);
	double total = pDao.getTotalCardPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);

}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CART PAGE</title>
<%@include file="includes/head.jsp"%>
<style>
.table tbody td {
	vertical-align: middle;
}

a i {
	color: Green;
	font-size: 25px;
}
</style>
</head>
<body>

	<%@include file="includes/navbar.jsp"%>

	<div class="container">

		<div class="d-flex py-3">
			<h3>Total price:$ ${ (total>0) ? dcf.format(total): 0 }</h3>
			<a class="mx-3 btn btn-primary" href="check-Out-Servlet">Check
				Out</a>
		</div>

	</div>
	<table class="table table-loght">

		<thead>

			<tr>

				<th scope="col">Name</th>
				<th scope="col">Category</th>
				<th scope="col">Price</th>
				<th scope="col">Buy Now</th>
				<th scope="col">Cancel</th>

			</tr>

		</thead>

		<tbody>
			<%
			if (cart_list != null) {
				for (cart c : cartProduct) {
			%>
			<tr>

				<td><%=c.getName()%></td>
				<td><%=c.getCategory()%></td>
				<td><%=dcf.format(c.getPrice())%></td>
				<td>

					<form action="Order-now-servlet" method="post" class="form-inline">


						<input type="hidden" name="id" value="<%=c.getId()%>"
							class="form-input">
						<div class="form-group d-flex justify-content-between w-50">

							<a class="btn btn-sm btn-decre"
								href="quantityIN?action=dec&id=<%=c.getId()%>"><i
								class="fas fa-minus-square"></i></a> <input type="text"
								name="quantity" class="form-control w-50"
								value="<%=c.getQuantity()%>" readonly> <a
								class="btn btn-sm btn-incre"
								href="quantityIN?action=inc&id=<%=c.getId()%>"><i
								class="fas fa-plus-square"></i></a>


						</div>

						<button type="submit" class="btn btn-primary btn-sm ">Buy</button>

					</form>

				</td>
				<td><a class="btn btn-5m btn-danger"
					href="Remove-from-cartServlet?id=<%=c.getId()%>">Remove</a></td>
			</tr>


			<%
			}

			}
			%>


		</tbody>

	</table>

	<%@include file="includes/footer.jsp"%>
</body>
</html>