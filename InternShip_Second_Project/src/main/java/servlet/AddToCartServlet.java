package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet({ "/Add-to-cart" })
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		try (PrintWriter out = response.getWriter()) {

			ArrayList<cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			cart cn = new cart();
			cn.setId(id);
			cn.setQuantity(1);
			HttpSession session = request.getSession();
			ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");

			if (cart_list == null) {

				cartList.add(cn);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");

			} else {

				cartList = cart_list;
				boolean exist = false;
				for (cart c : cart_list) {
					if (c.getId() == id) {
						exist = true;
						out.println(
								"<h3 style='color:crimson; text-align:center'>Item already exist in cart.<a href='cart.jsp'>Go to card Page</a></h3>");

					}
				}
				if (!exist) {

					cartList.add(cn);
					response.sendRedirect("index.jsp");

				}

			}
//			    for(cart c:cart_list) {
//			    	
//			    	  out.print(c.getId());
//			    }
//			    
//			    

		}
	}
}







