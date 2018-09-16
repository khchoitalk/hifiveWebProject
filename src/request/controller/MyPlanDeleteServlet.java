package request.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsp.model.service.HostService;
import hsp.model.service.SurferPartnerService;
import request.exception.MatchingException;
import request.model.service.MatchingService;
import request.model.vo.Matching;

/**
 * Servlet implementation class MyPlanDeleteServlet
 */
@WebServlet("/myplandelete")
public class MyPlanDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPlanDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int matching_no = Integer.parseInt(request.getParameter("matchingno"));
		Matching m = new MatchingService().selectMatching(matching_no);
		
		try{
			if(new MatchingService().deleteMatching(matching_no) > 0){ // 삭제 하면 각각 상태 P로 돌려놔야함
				if(m.getType().equals("H")){
					new HostService().updateProcess(m.getUser1());
					new SurferPartnerService().updateSurferProcess(m.getUser2());
				} else{
					new SurferPartnerService().updatePartnerProcess(m.getUser1());
					new SurferPartnerService().updatePartnerProcess(m.getUser2());
				}				
				response.sendRedirect("/hifive/main.jsp");
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("views/request/requestError.jsp");
				request.setAttribute("message", "에러 다시 확인하세요");
				view.forward(request, response);
			}				
		} catch(MatchingException e){
			RequestDispatcher view = request.getRequestDispatcher("views/request/requestError.jsp");
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
