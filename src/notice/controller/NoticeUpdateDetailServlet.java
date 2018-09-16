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
 * Servlet implementation class NoticeUpdateDetailServlet
 */
@WebServlet("/nupdatedetail")
public class NoticeUpdateDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeno = Integer.parseInt(request.getParameter("noticeno"));
	      
	      NoticeService nservice = new NoticeService();
	      RequestDispatcher view = null;

	      try {                        
	         Notice noticeN = nservice.selectNotice(noticeno);
	         
	         // 엔터값 처리
	         String content = noticeN.getContent();
	         content = content.replace("<br>", "\r\n");
	         noticeN.setContent(content);
	         
	         if(noticeN != null){
	            view = request.getRequestDispatcher(
	                  "views/support/notice/noticeUpdate.jsp");
	            request.setAttribute("noticedetail", noticeN);
	            
	            view.forward(request, response);
	            
	         }else{
	            view = request.getRequestDispatcher(
	                  "views/support/notice/noticeDetail.jsp");
	            request.setAttribute("message", "게시글이 없습니다.");
	            view.forward(request, response);
	         }      
	      
	      } catch (NoticeException e) {
	         System.out.println("실패");
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
