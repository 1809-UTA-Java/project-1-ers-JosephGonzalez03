package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.ErsUser;
import com.revature.repository.ErsUserDao;
import com.revature.service.CompanyEmployeeUser;
import com.revature.service.CompanyManagerUser;
import com.revature.util.HibernateUtil;

@WebServlet("/Login")
public class LoginServet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		ErsUserDao dao = HibernateUtil.getErsUserDao();
		List<ErsUser> users = dao.getAllUsers();
		ErsUser currentUser = null;
		CompanyManagerUser manager = null;
		CompanyEmployeeUser employee = null;
		boolean foundUser = false;

		for (ErsUser user : users) {
			if (user.login(username, password)) {
				currentUser = dao.getErsUserByUsername(username);
				foundUser = true;
				break;
			}
		}

		if (foundUser) {
			HttpSession session = request.getSession();

			if (currentUser.getUserRole().getRole().contentEquals("manager")) {
				request.getSession().setAttribute("manager", currentUser.getUsername());
				request.getSession().removeAttribute("employee");
				request.getRequestDispatcher("managerHome.jsp").forward(request, response);
			} else {
				request.getSession().removeAttribute("manager");
				request.getSession().setAttribute("employee", currentUser.getUsername());
				request.getRequestDispatcher("employeeHome.jsp").forward(request, response);
			}
		} else {
			out.println("Incorrect username or password");

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
	}

}
