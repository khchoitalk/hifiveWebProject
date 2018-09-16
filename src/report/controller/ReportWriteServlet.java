package report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import report.exception.ReportException;
import report.model.service.ReportService;
import report.model.vo.Report;

/**
 * Servlet implementation class ReportWriteServlet
 */
@WebServlet("/reportwrite")
public class ReportWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    } 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher view =null;
	
		Report report = new Report();
		report.setTitle(request.getParameter("rtitle"));
		report.setUser_id(request.getParameter("rwriter"));
		
		// 엔터값 처리
		String content = request.getParameter("rcontent");
		content = content.replace("\r\n", "<br>");
		report.setContent(content);		

		try{
			if(new ReportService().insertReport(report) > 0){
				response.sendRedirect("/hifive/reportlist");
			}else{
				view = request.getRequestDispatcher("views/support/report/reportWrite.jsp");
				request.setAttribute("result", "1");
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
