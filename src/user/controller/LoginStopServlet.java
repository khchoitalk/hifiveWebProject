package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class LoginStopServlet
 */
@WebServlet("/loginStop")
public class LoginStopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginStopServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String[] arr = request.getParameterValues("userid");
		
		JSONObject job = new JSONObject();
		PrintWriter out = response.getWriter();
		
		try{
			for(String userid : arr) {
				System.out.println(userid + action);
				if(action.equals("stop")) {
					if(new UserService().updateLogin(userid, "Y") > 0) {
						job.put("result", "1");
					}
					else {
						System.out.println("로그인 제한 실패");
						job.put("result", "2");
					}
				} else {
					if(new UserService().updateLogin(userid, "N") > 0) {
						job.put("result", "3");
					}
					else {
						job.put("result", "4");
					}
				}
			}
			response.setContentType("application/json; charset=utf-8");
			
			out.append(job.toJSONString());
			out.flush();
			out.close();
		}catch(Exception e){

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
