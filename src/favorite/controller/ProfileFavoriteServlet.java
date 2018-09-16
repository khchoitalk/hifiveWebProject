package favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import favorite.model.service.FavoriteService;

/**
 * Servlet implementation class ProfileFavoriteServlet
 */
@WebServlet("/profilefavorite")
public class ProfileFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_userId = request.getParameter("userid"); // 프로필 아이디
		String userId = (String)request.getSession().getAttribute("userId");// 세션에 있는 내 아이디

		JSONObject json = new JSONObject();
		
		if(p_userId.equals(userId)){ // 내 프로필 창 들어갔을때 
			json.put("result", 0);
		} else if (new FavoriteService().selectFavorite(userId, p_userId) > 0){ // 상대방이 이미 favorite 등록 되어 있을때
			json.put("result", 1); 
		} else { // 등록 안되어 있을 때
			json.put("result", 2);
		}
		
		response.setContentType("application/json; charset=utf-8");
	    PrintWriter out = response.getWriter();
	    out.print(json.toJSONString());
	    out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
