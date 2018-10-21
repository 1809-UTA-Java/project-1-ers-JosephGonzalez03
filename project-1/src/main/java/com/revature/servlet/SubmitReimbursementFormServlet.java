package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.revature.model.ErsUser;
import com.revature.repository.ErsUserDao;
import com.revature.service.CompanyEmployeeUser;
import com.revature.util.HibernateUtil;

/**
 * Servlet implementation class SubmitReimbursementFormServlet
 */
@WebServlet("/SubmitReimbursementForm")
public class SubmitReimbursementFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: submit reimbursement form here
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.jsp");
		} else {
			String eUsername = (String) session.getAttribute("employee");
			ErsUserDao dao = HibernateUtil.getErsUserDao();
			ErsUser mUser = dao.getErsUserByUsername(eUsername);
			CompanyEmployeeUser employee = new CompanyEmployeeUser(mUser);

			double amount = Double.parseDouble(request.getParameter("amount"));
			String description = request.getParameter("description");
			String type = request.getParameter("type");
			
			// save byte[] provided by user (if provided)
			Part content = null;
			
			try {
				content = request.getPart("receipt");
			} catch (Exception e) {
				content = null;
			}

			employee.submitReimbursementReq(amount, description, content, type);
			
			request.getRequestDispatcher("ViewEmployeeReimbursements").forward(request, response);;
	
		}
	}
}
