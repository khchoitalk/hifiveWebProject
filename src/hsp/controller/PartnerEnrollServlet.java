package hsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsp.exception.SurferPartnerException;
import hsp.model.service.SurferPartnerService;
import hsp.model.vo.SurferPartner;

/**
 * Servlet implementation class PartnerEnrollServlet
 */
@WebServlet("/partnerenroll")
public class PartnerEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartnerEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		
		SurferPartner sp = new SurferPartner();
		sp.setCity(request.getParameter("destination"));
		sp.setStart_date(java.sql.Date.valueOf(request.getParameter("startdate")));
		sp.setEnd_date(java.sql.Date.valueOf(request.getParameter("enddate")));
		sp.setUser_num(Integer.parseInt(request.getParameter("num")));
		sp.setUser_id(request.getParameter("userid"));		
		
		RequestDispatcher view = null;
		
		try {
			int result = new SurferPartnerService().insertPartner(sp);
			
			if(result > 0){
				response.sendRedirect("/hifive/views/user/mypage.jsp");
			}else{
	    		view = request.getRequestDispatcher("");
		        request.setAttribute("message", "수정 실패");
		        view.forward(request, response);
			}			
		} catch (SurferPartnerException e) {
    		view = request.getRequestDispatcher("");
	        request.setAttribute("message", "수정 실패");
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
