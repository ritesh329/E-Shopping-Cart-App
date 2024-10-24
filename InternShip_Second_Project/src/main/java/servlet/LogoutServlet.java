package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try (PrintWriter out = res.getWriter()) {

			if (req.getSession().getAttribute("auth") != null) {

				req.getSession().removeAttribute("auth");
				res.sendRedirect("login.jsp");

			} else {

				res.sendRedirect("index.jsp");
			}

		} catch (Exception e) {

		}
	}

}
