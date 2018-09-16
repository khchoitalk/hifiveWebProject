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
 * Servlet implementation class SearchPwdFServlet
 */
@WebServlet("/searchpwdfinal")
public class SearchPwdFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPwdFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties props = System.getProperties();
		 props.put("mail.smtp.user", "trevelsfriend@gmail.com");
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.port", "465");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.socketFactory.port", "465");
		 props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 props.put("mail.smtp.socketFactory.fallback", "false");
		 
		 Authenticator auth = new MyAuthentication1();
		 
		 //session 생성 및 MimeMessage생성
		 
		 Session session = Session.getDefaultInstance(props, auth);
		 MimeMessage msg = new MimeMessage(session);
		 PrintWriter out = response.getWriter();
		 
		 //이메일 수신자
		 String userEmail = request.getParameter("spwemail");	
		 String changepw = request.getParameter("chpw");
		 String userId = request.getParameter("spwid");
		 String sincePw = request.getParameter("sincepw");
		 String returnValue = "0";
		 
		 System.out.println(changepw + ", " + sincePw);
		 
		 try {			 
			
			if(userId != null && userEmail != null && changepw != null){
				
				
			User user = new User();
			user.setUser_Id(userId);
			user.setUser_Pw(changepw);
			
			changepw = new UserService().updatePass(user);		
			
				//편지 보낸 시간
				msg.setSentDate(new Date());
				InternetAddress from = new InternetAddress("trevelsfriend@gmail.com");
				
				//이메일 발신자
				msg.setFrom(from);
							
				//사용자가 입력한 이멜 주소 받아오기
				InternetAddress to = new InternetAddress(userEmail);
				msg.setRecipient(Message.RecipientType.TO, to);								
				
				//이메일 제목
				msg.setSubject("Travel's couch 고객센터 입니다. ","UTF-8");
							
				//이메일 내용
				msg.setText("문의하신" + userId + "의 임시비밀번호는  " + sincePw + "입니다", "UTF-8");
				
				//이메일 헤더
				msg.setHeader("content-Type", "text/html");
				javax.mail.Transport.send(msg);
											
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
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e2) {
			e2.printStackTrace();
		} catch (UserException e3) {
			e3.printStackTrace();
		} 
		 
	
		 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
