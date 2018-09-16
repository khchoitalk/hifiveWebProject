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
 * Servlet implementation class ReportUpdateDetail
 */
@WebServlet("/rupdatedetail")
public class ReportUpdateDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportUpdateDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html; charset=utf-8");
		
		int reportno = Integer.parseInt(request.getParameter("reportno"));
		
		ReportService rservice = new ReportService();
		RequestDispatcher view = null;

		try {								
			Report reportR = rservice.selectReport(reportno);
			
			// 엔터값 처리
			String content = reportR.getContent();
			content = content.replace("<br>", "\r\n");
			reportR.setContent(content);
			
			if(reportR != null){
				view = request.getRequestDispatcher(
						"views/support/report/reportUpdate.jsp");
				request.setAttribute("reportdetail", reportR);
				
				view.forward(request, response);
				
			}else{
				view = request.getRequestDispatcher(
						"views/support/report/reportDetail.jsp");
				request.setAttribute("message", "게시글이 없습니다.");
				view.forward(request, response);
			}		
		
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
