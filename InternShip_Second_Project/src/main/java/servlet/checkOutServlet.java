package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.cart;
import model.orderModel;
import shoppingcart.DbCon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.orderDao;

/**
 * Servlet implementation class checkOutServlet
 */
@WebServlet("/check-Out-Servlet")
public class checkOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(0);

			ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");

			if (cart_list != null && auth != null) {
				for (cart c : cart_list) {
					orderModel orderr = new orderModel();
					orderr.setId(c.getId());
					orderr.setUid(c.getId());
					orderr.setQuantity(c.getQuantity());
					orderr.setDate(formatter.format(date));

					orderDao dDao = new orderDao(DbCon.getConnection());
					boolean result = dDao.insertOrder(orderr);
					if (!result) {
						break;
					}
					cart_list.clear();
					response.sendRedirect("order.jsp");

				}
			} else {

				if (auth == null)
					response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
