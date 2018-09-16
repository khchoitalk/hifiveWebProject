package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.exception.UserException;
import user.model.service.UserService;

/**
 * Servlet implementation class SearchIdServlet
 */
@WebServlet("/searchid")
public class SearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SearchIdServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		 
		 try {			 
			
			String userEmail = request.getParameter("searchuseremail");
			String userId = new UserService().searchId(userEmail); 
			String returnValue = "0";
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if(userId != null){
				//편지 보낸 시간
				msg.setSentDate(new Date());
				InternetAddress from = new InternetAddress("trevelsfriend@gmail.com");
				
				//이메일 발신자
				msg.setFrom(from);
				
				//이메일 수신자
				 //사용자가 입력한 이멜 주소 받아오기
				InternetAddress to = new InternetAddress(userEmail);
				msg.setRecipient(Message.RecipientType.TO, to);
								
				
				//이메일 제목
				msg.setSubject("Travel's couch 고객센터 입니다. ","UTF-8");
				
				//이메일 내용
				msg.setText("문의하신" + userEmail + "로 가입된 아이디는 " + userId + " 입니다", "UTF-8");
				
				//이메일 헤더
				msg.setHeader("content-Type", "text/html");
				javax.mail.Transport.send(msg);
				System.out.println(session + "이 " + " 이메일 보내기를 성공");
				
				
				returnValue = "1";
				out.append(returnValue);
				out.flush();
			} else {
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
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(request, response);
		}


}	

		class MyAuthentication1 extends Authenticator {
			
			PasswordAuthentication pa;
			
			public MyAuthentication1(){
				
				String id = "trevelsfriend@gmail.com"; // 구글 id
				String pw = "ruddnr4657!"; //구글 비밀번호
				
				//ID와 비밀번호를 입력한다.
				pa = new PasswordAuthentication(id, pw);
						
			}
			
			// 시스템에서 사용하는 인증정보
			public PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}

}
