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
import com.revature.util.HibernateUtil;

/**
 * Servlet implementation class UpdatePersonalInfoServlet
 */
@WebServlet("/UpdatePersonalInfo")
public class UpdatePersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// submit personal info update
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("index.jsp");
		} else {
			String eUsername = (String) session.getAttribute("employee");
			ErsUserDao dao = HibernateUtil.getErsUserDao();
			ErsUser mUser = dao.getErsUserByUsername(eUsername);
			CompanyEmployeeUser employee = new CompanyEmployeeUser(mUser);

			String newPassword = request.getParameter("password");
			String newFirstName = request.getParameter("firstName");
			String newLastName = request.getParameter("lastName");
			String newEmail = request.getParameter("email");
			
			mUser.setPassword(newPassword);
			mUser.setFirstName(newFirstName);
			mUser.setLastName(newLastName);
			mUser.setEmail(newEmail);
			employee.updateInfo(mUser);
			
			response.sendRedirect("ViewPersonalInfo");
		}
	}

}
