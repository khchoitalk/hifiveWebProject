package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/noticelist")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 페이지별 조회 처리용 컨트롤러
		//내보내는 값에 한글이 포함되어 있을 경우
		response.setContentType("text/html; charset=utf-8");
		//페이지 값 처리용 변수
		int currentPage = 1;
		//한 페이지당 출력할 목록 갯수
		int limit = 10;
		
		//전달된 페이지값 추출
		if(request.getParameter("page") != null){
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeService nservice = new NoticeService();
		RequestDispatcher view = null;
		
		try {					
			//전체 목록 갯수 조회함
			int listCount = nservice.getListCount();
			
			//해당 페이지에 보이게할 목록 조회
			ArrayList<Notice> noticeList = nservice.selectAllNotice(currentPage, limit);

			//총 페이지수 계산 
			//목록이 최소 1개일 때 1 페이지로 처리하기
			//위해서 0.9를 더하기 함
			int maxPage = (int)((double)listCount / limit + 0.9);
			//현재 페이지에 보여줄 시작 페이지 수
			//1, 11, 21, ....
			//현재 페이지가 13페이지면 시작 페이지는 11페이지가 됨.
			int startPage = (((int)((double)currentPage / limit 
					+ 0.9)) - 1) * limit + 1;
			//만약, 목록 아래에 보여질 페이지 갯수가 10개이면
			//끝페이지수는 20페이지가 되어야 함
			int endPage = startPage + limit - 1;
			if(maxPage < endPage)
				endPage = maxPage;
			if(noticeList.size() > 0){
				view = request.getRequestDispatcher("views/support/notice/noticeList.jsp");
				request.setAttribute("noticeList", noticeList);	        	
			}else{
				view = request.getRequestDispatcher(
						"views/support/notice/noticeList.jsp");
				request.setAttribute("message", "게시글이 없습니다.");
			}		
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("result", 0);
			
        	view.forward(request, response);
		
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
