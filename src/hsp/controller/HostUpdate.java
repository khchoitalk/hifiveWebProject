package hsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsp.exception.HostException;
import hsp.model.service.HostService;
import hsp.model.vo.Host;

/**
 * Servlet implementation class HostUpdate
 */
@WebServlet("/hupdate")
public class HostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Host host = new Host();
		host.setUser_id(request.getParameter("userid"));		
		host.setUser_num(Integer.parseInt(request.getParameter("num")));
		host.setP_gender(request.getParameter("gender"));

		if(request.getParameter("hostcheck")==null)
			host.setCheck1("해당없음");
		else
			host.setCheck1(String.join(",", request.getParameterValues("hostcheck")));
		
		host.setCheck2(request.getParameter("sleeping"));
		host.setCity(request.getParameter("city"));
		host.setContent(request.getParameter("etc"));

		RequestDispatcher view = null;
		
		try {
			if(new HostService().updateHost(host) > 0){
				response.sendRedirect("/hifive/main.jsp");
			}else{
	    		view = request.getRequestDispatcher("views/user/hostError.jsp");
		        request.setAttribute("message", "수정 실패");
		        view.forward(request, response);
	    	}    	
	    } catch (HostException e) {
	        view = request.getRequestDispatcher("views/user/hostError.jsp");
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
