package report.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import report.exception.ReportException;
import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportDetailServlet
 */
@WebServlet("/reportdetail")
public class ReportDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		
		String admin = "";
		if(request.getParameter("userid") != null) {
			admin = request.getParameter("userid");
		}
		
		int reportno = Integer.parseInt(request.getParameter("rnum"));
		
		ReportService rservice = new ReportService();
		RequestDispatcher view = null;

		try {		
			//상세보기시 조회수 1 증가 처리
			rservice.addReadCount(reportno);
			
			Report reportR = rservice.selectReport(reportno);
			
			if(reportR != null){
				request.setAttribute("reportR", reportR);
			}else{
				request.setAttribute("message", "게시글이 없습니다.");
			}	
			if(admin.equals("admin")) {
				view = request.getRequestDispatcher(
						"views/support/report/adminReportDetail.jsp");
			} else {
				view = request.getRequestDispatcher(
						"views/support/report/reportDetail.jsp");
			}
			view.forward(request, response);
		
		} catch (ReportException e) {
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
