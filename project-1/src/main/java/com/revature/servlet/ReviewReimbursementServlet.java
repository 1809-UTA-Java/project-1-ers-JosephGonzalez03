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
 * Servlet implementation class ReviewReimbursementServlet
 */
@WebServlet("/ReviewReimbursement")
public class ReviewReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post reimbursement review here
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.jsp");
		} else {
			String mUsername = (String) session.getAttribute("manager");
			ErsUserDao dao = HibernateUtil.getErsUserDao();
			ErsUser mUser = dao.getErsUserByUsername(mUsername);
			CompanyManagerUser manager = new CompanyManagerUser(mUser);

			int rmbmtId = Integer.parseInt(request.getParameter("rmbmtId"));
			String reviewOption = request.getParameter("reviewOption");
			
			manager.reviewPendingReimbursementReq(mUser, rmbmtId, reviewOption);
			request.getRequestDispatcher("ViewPendingReimbursements").forward(request, response);
		}
	}

}
