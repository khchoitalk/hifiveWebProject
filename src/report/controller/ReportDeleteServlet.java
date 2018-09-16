package report.controller;

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
import report.exception.ReportException;
import report.model.service.ReportService;

/**
 * Servlet implementation class ReportDeleteServlet
 */
@WebServlet("/reportdelete")
public class ReportDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int reportno = Integer.parseInt(request.getParameter("reportno"));
		RequestDispatcher view = null;
		try{
			if(new ReportService().deleteReport(reportno) > 0){
				response.sendRedirect("/hifive/reportlist");
			
			}else{
				view = request.getRequestDispatcher("views/support/report/reportError.jsp");
				request.setAttribute("message", reportno + "번 신고글 삭제 실패");
				view.forward(request, response);
			}

		} catch (ReportException e){

			view = request.getRequestDispatcher("views/support/report/reportError.jsp");
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
