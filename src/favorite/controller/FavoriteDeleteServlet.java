package favorite.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import favorite.exception.FavoriteException;
import favorite.model.service.FavoriteService;

/**
 * Servlet implementation class FavoriteDeleteServlet
 */
@WebServlet("/favoritedelete")
public class FavoriteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String f_UserId = request.getParameter("f_userid"); // 상대
		String userId = (String) request.getSession().getAttribute("userId"); // 내아이디
		
		try{
			if(new FavoriteService().deleteFavorite(userId, f_UserId) > 0){
				response.sendRedirect("views/favorite/favorite.jsp");
			} else{
				RequestDispatcher view = request.getRequestDispatcher("views/favorite/favoriteError.jsp");
				request.setAttribute("message", "favorite 취소 실패");
				view.forward(request, response);	
			}
		} catch(FavoriteException e){
			RequestDispatcher view = request.getRequestDispatcher("views/favorite/favoriteError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);	
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
