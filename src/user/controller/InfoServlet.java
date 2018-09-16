package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/info")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InfoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String userId = request.getParameter("userid");	
		User user = new UserService().selectUser(userId);			
	
		JSONObject job = new JSONObject();
		job.put("id", user.getUser_Id());
		job.put("name", user.getUser_Name());
		job.put("address", user.getAddress());
		job.put("gender", user.getGender());
		job.put("email", user.getEmail());
		job.put("birth", user.getBirth().toString());
		job.put("nationality", user.getNationality());
		job.put("job", user.getJob());
		job.put("hobby", user.getHobby());
		job.put("phone", user.getPhone());
		job.put("content", user.getContent());
		job.put("profileimg", user.getProfile_image());			
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.append(job.toJSONString());
		out.flush();
		out.close();	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
