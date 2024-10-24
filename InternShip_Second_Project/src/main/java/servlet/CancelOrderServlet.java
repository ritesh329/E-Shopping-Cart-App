package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingcart.DbCon;

import java.io.IOException;
import java.io.PrintWriter;

import DAO.orderDao;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/cancel-order-servlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			String id = request.getParameter("id");
			System.out.print("helo this is id" + id);
			if (id != null) {
				orderDao orderdao = new orderDao(DbCon.getConnection());
				orderdao.cancelOrder(Integer.parseInt(id));

			}
			response.sendRedirect("order.jsp");

		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
