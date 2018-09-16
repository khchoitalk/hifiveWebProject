package request.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import request.exception.RequestException;
import request.model.service.RequestService;

/**
 * Servlet implementation class RequestRefuseServlet
 */
@WebServlet("/requestrefuse")
public class RequestRefuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestRefuseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내가 받은 요청 거절할 때	
		int request_no = Integer.parseInt(request.getParameter("request_no"));
		
		RequestDispatcher view = null;
		try{
			if(new RequestService().refuseRequest(request_no) > 0){
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