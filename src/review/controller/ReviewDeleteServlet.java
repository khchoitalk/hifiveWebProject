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

/**
 * Servlet implementation class ReviewDeleteServlet
 */
@WebServlet("/reviewdelete")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int review_no = Integer.parseInt(request.getParameter("reviewno"));
		String userid = request.getParameter("uid");
		
		RequestDispatcher view = null;
		
		try{
			if(new ReviewService().deleteReview(review_no) > 0)
				response.sendRedirect("/hifive/profileinfo?userid="+userid);
			else{
				view = request.getRequestDispatcher("views/user/userError.jsp");
				request.setAttribute("message", "리뷰 삭제 실패");
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
