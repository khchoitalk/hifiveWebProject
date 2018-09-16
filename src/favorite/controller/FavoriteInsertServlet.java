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
 * Servlet implementation class FavoriteInsertServlet
 */
@WebServlet("/favoriteinsert")
public class FavoriteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = (String)request.getSession().getAttribute("userId"); // 내 아이디
		String f_UserId = request.getParameter("f_userid"); // 상대방 아이디
	
		try{
			if(new FavoriteService().insertFavorite(userId, f_UserId) > 0){
				response.sendRedirect("views/favorite/favorite.jsp");
			} else{
				RequestDispatcher view = request.getRequestDispatcher("views/favorite/favoriteError.jsp");
				request.setAttribute("message", "favorite 등록 실패");
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
