package review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.exception.ReviewException;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewWriteServlet
 */
@WebServlet("/reviewwrite")
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = (String) request.getSession().getAttribute("userId");
		String r_UserId = request.getParameter("uid");
		String content = request.getParameter("review");
		
		Review review = new Review();
		review.setUser_id(userId);
		review.setR_user_id(r_UserId);
		
		content = content.replace("\r\n", "<br>");
		review.setContent(content);
		
		RequestDispatcher view = null;
		
		try{
			if(new ReviewService().insertReview(review) > 0)
				response.sendRedirect("/hifive/profileinfo?userid="+r_UserId);
			else{
				view = request.getRequestDispatcher("views/user/userError.jsp");
				request.setAttribute("message", "리뷰 등록 실패");
				view.forward(request, response);
			}			
		} catch(ReviewException e){
			view = request.getRequestDispatcher("views/user/userError.jsp");
			request.setAttribute("message", e.getCause());
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
