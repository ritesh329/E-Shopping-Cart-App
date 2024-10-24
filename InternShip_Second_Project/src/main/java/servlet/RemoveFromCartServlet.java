package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/Remove-from-cartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {

			String id = request.getParameter("id");
			out.print("Product id:" + id);
			if (id != null) {

				ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
				if (cart_list != null) {

					for (cart c : cart_list) {
						if (c.getId() == Integer.parseInt(id)) {

							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}

					response.sendRedirect("cart.jsp");

				}

			} else {

				response.sendRedirect("cart.jsp");

			}

		}

	}

}
