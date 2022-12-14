package com.bruce.bookmark_thrillio.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bruce.bookmark_thrillio.constants.KidFriendlyStatus;
import com.bruce.bookmark_thrillio.entities.Bookmark;
import com.bruce.bookmark_thrillio.entities.User;
import com.bruce.bookmark_thrillio.managers.BookmarkManager;
import com.bruce.bookmark_thrillio.managers.UserManager;

@WebServlet(urlPatterns = { "/bookmark", "/bookmark/save", "/bookmark/mybooks" })
public class BookmarkController extends HttpServlet {
	// Singleton Controller
	// Don't need this because Tomcat server will create a Singleton instance of
	// servlet
	/*
	 * private static BookmarkController instance = new BookmarkController();
	 * 
	 * private BookmarkController() { }
	 * 
	 * public static BookmarkController getInstance() { return instance; }
	 */

	public BookmarkController() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		System.out.println("Servlet path: " + request.getServletPath());
		
		if (request.getSession().getAttribute("userId") != null) {
			long userId = (long)request.getSession().getAttribute("userId");
		
			if (request.getServletPath().contains("save")) {
				// save
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				
				String bid = request.getParameter("bid");
				
				User user = UserManager.getInstance().getUser(userId);
				Bookmark bookmark = BookmarkManager.getInstance().getBook(Long.parseLong(bid));
				BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
				
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books", list);
				
			} else if (request.getServletPath().contains("mybooks")) {
				// mybooks
				dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books", list);
			} else {
				dispatcher = request.getRequestDispatcher("/browse.jsp");
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(false, userId);
				request.setAttribute("books", list);
			}
		} else {
			// session is expiried or 1st time visit
			dispatcher = request.getRequestDispatcher("/login.jsp");
			
		}
		
		
		

		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);

	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user, bookmark);

	}
}
