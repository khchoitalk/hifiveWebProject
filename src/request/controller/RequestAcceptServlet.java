package request.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import request.exception.RequestException;
import request.model.service.RequestService;
import request.model.vo.Request;

/**
 * Servlet implementation class RequestAcceptServlet
 */
@WebServlet("/requestaccept")
public class RequestAcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestAcceptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int request_no = Integer.parseInt(request.getParameter("request_no"));
		String userId = (String) request.getSession().getAttribute("userId");
	
		RequestDispatcher view = null;
		try{			
			if(new RequestService().acceptRequest(request_no) > 0){
				if(new RequestService().checkRole(request_no).equals("S")){
					ArrayList<Request> h_list_2 = new RequestService().myHostList2(userId);  
				    ArrayList<Request> s_list_1 = new RequestService().mySurferList1(userId);
				    
				    for(Request r : h_list_2) 
				    	new RequestService().refuseRequest(r.getRequest_no());
				    for(Request r : s_list_1)
				    	new RequestService().deleteRequest(r.getRequest_no());
			    } else if (new RequestService().checkRole(request_no).equals("H")) {
			    	ArrayList<Request> s_list_2 = new RequestService().mySurferList2(userId);
			        ArrayList<Request> h_list_1 = new RequestService().myHostList1(userId);   
			        
			        for(Request r : s_list_2) 
				    	new RequestService().refuseRequest(r.getRequest_no());
				    for(Request r : h_list_1)
				    	new RequestService().deleteRequest(r.getRequest_no());			        
			    } else{
			    	ArrayList<Request> p_list_1 = new RequestService().myPartnerList1(userId);
			        ArrayList<Request> p_list_2 = new RequestService().myPartnerList2(userId);
			        
			        for(Request r : p_list_1) 
				    	new RequestService().refuseRequest(r.getRequest_no());
				    for(Request r : p_list_2)
				    	new RequestService().deleteRequest(r.getRequest_no());
			    }
				response.sendRedirect("/hifive/main.jsp");
			} else{
				view = request.getRequestDispatcher("views/request/requestError.jsp");
				request.setAttribute("message", "요청 거절 에러");
				view.forward(request, response);			}
					
		} catch(RequestException e){
			view = request.getRequestDispatcher("views/request/requestError.jsp");
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
