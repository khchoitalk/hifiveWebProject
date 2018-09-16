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
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/adminnoticedetail")
public class adminNoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminNoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int noticeno = Integer.parseInt(request.getParameter("rnum"));
		
		NoticeService nservice = new NoticeService();
		RequestDispatcher view = null;

		try {	
			nservice.addReadCount(noticeno);
			Notice noticeN = nservice.selectNotice(noticeno);

			if(noticeN != null){
				view = request.getRequestDispatcher(
						"views/support/notice/adminnoticeDetail.jsp");
				request.setAttribute("noticeN", noticeN);
				
				view.forward(request, response);
				
			}else{
				view = request.getRequestDispatcher(
						"views/support/notice/adminnoticeDetail.jsp");
				request.setAttribute("message", "내용이 없습니다.");
				view.forward(request, response);
			}		
		
		} catch (NoticeException e) {
			System.out.println("실패1");
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
