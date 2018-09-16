package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class SearchPwdServlet
 */
@WebServlet("/searchpwd")
public class SearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SearchPwdServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 
		 PrintWriter out = response.getWriter();
		 
		 //이메일 수신자
		 String userEmail = request.getParameter("spwemail");
		 String userId = request.getParameter("spwid");
		 String returnValue = "0";		 
		 
		 try {			 
						
			String userPw = new UserService().searchPw(userId, userEmail); 
								
			if(userId != null && userEmail != null && userPw != null){				
				response.setContentType("text/html; charset=utf-8");
				returnValue = "1";
				out.append(returnValue);
				out.flush();				
				
			} else {			
				response.setContentType("text/html; charset=utf-8");
				returnValue = "0";
				out.append(returnValue);
				out.flush();
									
			}			
			
			out.close();
				
		} catch (UserException e) {
			e.printStackTrace();
		} 
		 
	
		 
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
