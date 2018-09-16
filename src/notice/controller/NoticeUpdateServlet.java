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
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/noticeupdate")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
	      RequestDispatcher view =null;
	      
	      Notice notice = new Notice();
	      notice.setTitle(request.getParameter("ntitle"));
	      // 엔터값 처리
	      String content = request.getParameter("ncontent");
	      content = content.replace("\r\n", "<br>");
	      notice.setContent(content);	      
	      notice.setNotice_no(Integer.parseInt(request.getParameter("noticeno")));
	      try{
	         if(new NoticeService().updateNotice(notice) > 0){
	            response.sendRedirect("/hifive/adminnoticelist");
	         }else{
	            view = request.getRequestDispatcher("views/support/notice/noticeError.jsp");
	            request.setAttribute("message", "신고글 수정 실패");
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
