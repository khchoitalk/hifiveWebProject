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
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/noticewrite")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view =null;
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		
		Notice notice = new Notice();
		notice.setTitle(request.getParameter("ntitle"));
		notice.setContent(request.getParameter("ncontent"));
		
	      // 엔터값 처리
	      String content = request.getParameter("ncontent");
	      content = content.replace("\r\n", "<br>");
	      notice.setContent(content);
		
		try{
			if(new NoticeService().insertNotice(notice) > 0){
				response.sendRedirect("/hifive/adminnoticelist");
			}else{
				view = request.getRequestDispatcher("views/support/notice/noticeError.jsp");
				request.setAttribute("message", "공지글 등록 실패");
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
