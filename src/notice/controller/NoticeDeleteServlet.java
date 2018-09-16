package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.exception.NoticeException;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/noticedelete")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		int noticeno = Integer.parseInt(request.getParameter("noticeno"));
		RequestDispatcher view = null;
		try{
			System.out.println("서블릿 : " + noticeno);
			if(new NoticeService().deleteNotice(noticeno) > 0){
				response.sendRedirect("/hifive/adminnoticelist");

			}else{
				view = request.getRequestDispatcher("views/support/notice/noticeError.jsp");
				request.setAttribute("message", noticeno + "번 신고글 삭제 실패");
				view.forward(request, response);
			}

		} catch (NoticeException e){

			view = request.getRequestDispatcher("views/support/notice/noticeError.jsp");
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
