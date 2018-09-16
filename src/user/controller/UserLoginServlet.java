package user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;


@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public UserLoginServlet() {
        super();        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	String userId = request.getParameter("userid");
		String userPw = request.getParameter("userpw");
		
		try {
			User user = new UserService().selectUser(userId);		
			String userName = new UserService().loginCheck(userId, userPw);
			String returnValue = "0";
			String blockcheck = user.getRestriction();			
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
					
			if(userId.equals("admin")){
				session.setAttribute("userName", userName);
				session.setAttribute("userId", userId);
				session.setAttribute("loginuser", user);
				returnValue = "2";
				out.flush();
			}else if(userName != null && user != null && blockcheck.equals("N")){
				session.setAttribute("userName", userName);
				session.setAttribute("userId", userId);
				session.setAttribute("loginuser", user);				
				returnValue = "1";
				out.flush();
			} else if(userName != null && user != null && blockcheck.equals("Y")){
				returnValue = "3";
				out.flush();
			} else {
				returnValue = "0";
				out.flush();
			}
					
			response.setContentType("text/html; charset=utf-8");
			
			out.append(returnValue);	
			out.close();
			return;
			
		} catch(UserException e){
			e.printStackTrace();
		}
	}

    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
