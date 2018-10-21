package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.ErsUser;
import com.revature.repository.ErsUserDao;
import com.revature.service.CompanyManagerUser;
import com.revature.util.HibernateUtil;

/**
 * Servlet implementation class PullEmployeeRequestsServelet
 */
@WebServlet("/PullEmployeeRequests")
public class PullEmployeeRequestsServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.jsp");
		} else {
			String mUsername = (String) session.getAttribute("manager");
			ErsUserDao dao = HibernateUtil.getErsUserDao();
			ErsUser mUser = dao.getErsUserByUsername(mUsername);
			CompanyManagerUser manager = new CompanyManagerUser(mUser);

			String employeeUsername = request.getParameter("username");

			// assert employee exist
			if (dao.getErsUserByUsername(employeeUsername) == null) {
				out.println("Invalid username entered");

				RequestDispatcher rd = request.getRequestDispatcher("allEmployees.jsp");
				rd.include(request, response);
			} else {

				ErsUser e = dao.getErsUserByUsername(employeeUsername);

				request.getSession().setAttribute("username", employeeUsername);
				
				request.getSession().setAttribute("rList", manager.viewReimbursementReqs(e));
				request.getRequestDispatcher("allEmployeeReimbursementRequests.jsp").forward(request, response);
			}
		}
	}

}
