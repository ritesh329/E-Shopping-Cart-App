package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.orderModel;
import shoppingcart.DbCon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import DAO.orderDao;

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/Order-now-servlet")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.print("hiiiii");

		try (PrintWriter out = response.getWriter()) {

			out.print("yes");

//			LocalDate formatttere = LocalDate.now();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(0);
			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {

				System.out.print("hello ");
				String productId = request.getParameter("id");

				int productquantity = Integer.parseInt(request.getParameter("quantity"));
				if (productquantity <= 0) {
					productquantity = 1;
				}

				orderModel order = new orderModel();
				order.setId(Integer.parseInt(productId));
				order.setUid(auth.getId());
				order.setQuantity(productquantity);
				order.setDate(formatter.format(date));
				orderDao orderrr = new orderDao(DbCon.getConnection());
				boolean result = orderrr.insertOrder(order);
				if (result) {
					response.sendRedirect("order.jsp");

				} else {

					out.print("order failed");
				}

			} else {
				System.out.print(" else hello ");

				response.sendRedirect("login.jsp");

			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.print(" do post hiiiii");
		doGet(request, response);

	}

}
