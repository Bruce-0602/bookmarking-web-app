package com.bruce.bookmark_thrillio.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bruce.bookmark_thrillio.managers.UserManager;

/**
 * Servlet implementation class AuthController
 */
@WebServlet(urlPatterns = {"/auth", "/auth/logout"})
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!request.getServletPath().contains("logout")) {
			// login
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			long userId = UserManager.getInstance().authenticate(email, password);
			if (userId != -1) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				// for servlet, NO need / in the front
				request.getRequestDispatcher("bookmark/mybooks").forward(request, response);
				
			} else {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}
		
		} else {
			// logout
			request.getSession().invalidate();
			// for jsp, need / in the front
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
