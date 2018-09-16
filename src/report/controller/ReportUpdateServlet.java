package report.controller;

import java.io.IOException;

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
 * Servlet implementation class ReportUpdateServlet
 */
@WebServlet("/reportupdate")
public class ReportUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view =null;
	
		Report report = new Report();
		report.setTitle(request.getParameter("rtitle"));
		// 엔터값 처리
		String content = request.getParameter("rcontent");
		content = content.replace("\r\n", "<br>");
		report.setContent(content);
		report.setReport_no(Integer.parseInt(request.getParameter("reportno")));
		report.setComplete("N");
		String admin = "user";
		
		if(request.getParameter("userid") != null) {
			admin = request.getParameter("userid");
			report.setComplete("Y");
		}
		ReportService rservice = new ReportService();
		
		try{
			if(rservice.updateReport(report) > 0){
				request.setAttribute("message", "신고글 수정 성공");
				Report r = rservice.selectReport(report.getReport_no());
				request.setAttribute("reportR", r);
			}else{
				request.setAttribute("message", "신고글 수정 실패");
			}
			
			if(admin.equals("admin")) {
				view = request.getRequestDispatcher(
						"views/support/report/adminReportDetail.jsp");
			} else {
				view = request.getRequestDispatcher(
						"views/support/report/reportDetail.jsp");
			}
			view.forward(request, response);

		} catch (ReportException e){

			view = request.getRequestDispatcher("views/support/report/reportError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);


		}
	}

}
