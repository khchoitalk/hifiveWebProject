package user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class UserEnrollServlet
 */
@WebServlet("/enroll")
public class UserEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserEnrollServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("joinuserid"); 
		String userPwd = request.getParameter("userpwd1");
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		Date birth = Date.valueOf(request.getParameter("birth"));
		
		User user = new User();
		user.setUser_Id(userId);
		user.setUser_Pw(userPwd);
		user.setUser_Name(userName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setBirth(birth);
		user.setGender(gender);
		
		try{
			if(new UserService().insertUser(user) > 0){ //회원가입 성공
				response.sendRedirect("/hifive/index.jsp");
				return;
			} else{
			/*	RequestDispatcher errorPage = request.getRequestDispatcher("에러페이지");
				request.setAttribute("message", "회원 가입 실패");
				errorPage.forward(request, response);*/
			}
		} catch(UserException e){
			e.printStackTrace();
			/*RequestDispatcher errorPage = request.getRequestDispatcher("에러페이지");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);*/
		}
		
	}
}