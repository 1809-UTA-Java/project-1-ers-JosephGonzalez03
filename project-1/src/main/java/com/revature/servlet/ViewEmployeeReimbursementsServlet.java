package com.revature.servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class ViewEmployeeReimbursementsServlet
 */
@WebServlet("/ViewEmployeeReimbursements")
public class ViewEmployeeReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// populate with employee's personal info
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.jsp");
		} else {
			String eUsername = (String) session.getAttribute("employee");
			ErsUserDao dao = HibernateUtil.getErsUserDao();
			ErsUser mUser = dao.getErsUserByUsername(eUsername);
			CompanyEmployeeUser employee = new CompanyEmployeeUser(mUser);

			request.getSession().setAttribute("pendingList", employee.viewPendingReimbursementReqs());
			request.getSession().setAttribute("resolvedList", employee.viewResolvedReimbursementReqs());
			request.getRequestDispatcher("reimbursementHome.jsp").forward(request, response);
		}
	}

}
