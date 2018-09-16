package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class InfoUpdateServlet
 */
@WebServlet("/infoupdate")
public class InfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InfoUpdateServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		RequestDispatcher view = null;		

		User user = new User();
		user.setUser_Id(request.getParameter("userid"));		
		user.setAddress(request.getParameter("address"));
		user.setNationality(request.getParameter("countries"));
		user.setJob(request.getParameter("job"));		
		user.setPhone(request.getParameter("phone"));
		if(request.getParameter("hobby") != null)
	         user.setHobby(String.join(",", request.getParameterValues("hobby")));
		user.setContent(request.getParameter("introduction"));		
		

	    try {	      	
	    	if(new UserService().updateUser(user) > 0){
	    		response.sendRedirect("/hifive/views/user/mypage.jsp");	

	    	}else{
	    		view = request.getRequestDispatcher("views/user/UserException.java");
		        request.setAttribute("message", "값이 없음");
		        view.forward(request, response);
	    	}    	

	    } catch (UserException e) {
	        view = request.getRequestDispatcher("views/user/userError.jsp");
	        request.setAttribute("message", e.getMessage());
	        view.forward(request, response);
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
