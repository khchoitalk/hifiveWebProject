package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserChangePwdServlet
 */
@WebServlet("/changepwd")
public class UserChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserChangePwdServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nowPW = request.getParameter("nowpw");
		String changePW = request.getParameter("changepw");
		String userId = request.getParameter("userid");
		String returnValue = "0";
		PrintWriter out = response.getWriter();
		
		
		try {
			User user = new UserService().selectUser(userId);
				if(nowPW.equals(user.getUser_Pw()) && userId.equals(user.getUser_Id())){
					user.setUser_Id(userId);
					user.setUser_Pw(changePW);
					
					String userpw = new UserService().updatePass(user);	
					
					returnValue = "1";
					response.setContentType("text/html; charset=utf-8");
					out.append(returnValue);
					out.flush();
			} else {
				returnValue = "0";
				response.setContentType("text/html; charset=utf-8");
				out.append(returnValue);
				out.flush();
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	
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
