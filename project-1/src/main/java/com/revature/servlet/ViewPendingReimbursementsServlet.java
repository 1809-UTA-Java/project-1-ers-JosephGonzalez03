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
import com.revature.service.CompanyManagerUser;
import com.revature.util.HibernateUtil;

/**
 * Servlet implementation class ViewPendingReimbursementsServlet
 */
@WebServlet("/ViewPendingReimbursements")
public class ViewPendingReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// load pending and resolved reimbursements here
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.jsp");
		} else {
			String mUsername = (String) session.getAttribute("manager");
			ErsUserDao dao = HibernateUtil.getErsUserDao();
			ErsUser mUser = dao.getErsUserByUsername(mUsername);
			CompanyManagerUser manager = new CompanyManagerUser(mUser);

			request.getSession().setAttribute("resolvedList", manager.viewAllResolvedReimbursementReqs());
			request.getSession().setAttribute("pendingList", manager.viewAllPendingReimbursementReqs());
			request.getRequestDispatcher("pendingReimbursementHome.jsp").forward(request, response);
		}
	}

}
