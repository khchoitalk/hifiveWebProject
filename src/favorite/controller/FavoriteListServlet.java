package favorite.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import favorite.model.service.FavoriteService;
import favorite.model.vo.Favorite;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class FavoriteListServlet
 */
@WebServlet("/favoritelist")
public class FavoriteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute("userId");
		
		ArrayList<Favorite> list = new FavoriteService().selectAll(userId); // 내가 선호하는 유저 목록
		
		// 전송될 json 객체 선언 : 객체 하나만 내보낼 수 있음
		JSONObject json = new JSONObject();
		// list는 json 배열에 저장하고, json 배열을 전송용 json 객체에 저장함
		JSONArray jarr = new JSONArray();

		for(Favorite f : list){			
			JSONObject job = new JSONObject();
			User user = new FavoriteService().selectFavoriteUser(f.getF_user_id());
			job.put("f_userid", f.getF_user_id());
			job.put("user_name", user.getUser_Name());
			job.put("address", user.getAddress());
			job.put("nationality", user.getNationality());
			job.put("image", user.getProfile_image());
			jarr.add(job);
		}
		
		// json 배열을 전송용 json 객체에 저장함
		json.put("list", jarr);
		// json 내보내기
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json.toJSONString());
		out.flush();			
			
	}

}