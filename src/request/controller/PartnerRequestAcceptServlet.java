package request.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import hsp.model.service.SurferPartnerService;
import hsp.model.vo.SurferPartner;
import request.exception.RequestException;
import request.model.service.RequestService;

/**
 * Servlet implementation class PartnerRequestAcceptServlet
 */
@WebServlet("/praccept")
public class PartnerRequestAcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartnerRequestAcceptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int req_no = Integer.parseInt(request.getParameter("req_no"));
		
		String user_id = new RequestService().selectUser_Id(req_no);
		String r_user_id = new RequestService().selectR_User_Id(req_no);
		
		SurferPartner p1 = new SurferPartnerService().selectPartner(user_id);
		SurferPartner p2 = new SurferPartnerService().selectPartner(r_user_id);
		
		JSONObject json = new JSONObject();
		
		if((p1.getStart_date().equals(p2.getStart_date())) && (p1.getEnd_date().equals(p2.getEnd_date())) ){
			try{
				if(new RequestService().acceptRequest(req_no) > 0){
					json.put("result", 1);
				} else{
					RequestDispatcher view = request.getRequestDispatcher("views/request/requestError.jsp");
					request.setAttribute("message", "에러");
					view.forward(request, response);
				}
			} catch(RequestException e){
				RequestDispatcher view = request.getRequestDispatcher("views/request/requestError.jsp");
				request.setAttribute("message", e.getMessage());
				view.forward(request, response);
			}
		} else{
			json.put("result", 0);
		}		
		response.setContentType("application/json; charset=utf-8");
	    PrintWriter out = response.getWriter();
	    out.print(json.toJSONString());
	    out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
