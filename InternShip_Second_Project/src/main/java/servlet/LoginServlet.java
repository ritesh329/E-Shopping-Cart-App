package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import DAO.userDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import shoppingcart.DbCon;

import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("serial")
@WebServlet("/user-login")

//@WebServlet({ "/LoginServlet", "/user-login" })
public class LoginServlet extends HttpServlet {

//		protected void doPost(HttpServletRequest request, HttpServletResponse response)
//				throws ServletException, IOException {
//			response.setContentType("text/html;charset=UTF-8");
//			try (PrintWriter out = response.getWriter()) {
//				String email = request.getParameter("login-email");
//				String password = request.getParameter("login-password");
//
//				userDao udao =new userDao(DbCon.getConnection());
//				User user = udao.userlogin(email, password);
//				if (user != null) {
//					request.getSession().setAttribute("auth", user);
////					System.out.print("user logged in");
//					response.sendRedirect("index.jsp");
//				} else {
//					out.println("there is no user");
//				}
//
//			} catch (ClassNotFoundException|SQLException e) {
//				e.printStackTrace();
//			} 
//
//		}
//	
//	

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		res.sendRedirect("login.jsp");
//
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		try (PrintWriter out = res.getWriter()) {

			String email = req.getParameter("login-email");
			String password = req.getParameter("login-password");

			try {
				userDao udao = new userDao(DbCon.getConnection());
				User user = udao.userlogin(email, password);
				if (user != null) {
//				        	 out.print("user login");

					req.getSession().setAttribute("auth", user);

					res.sendRedirect("index.jsp");
				} else {

					out.print("user login failed");
				}
			} catch (Exception e) {

			}

		}
	}
}
