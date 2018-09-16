package hsp.controller;

import java.io.IOException;
import java.util.Arrays;
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
 * Servlet implementation class HostEnrollServlet
 */
@WebServlet("/hostenroll")
public class HostEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostEnrollServlet() {
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
		if(request.getParameter("possible") == null)
			host.setCheck1("해당없음");
		else
			host.setCheck1(String.join(",", request.getParameterValues("possible")));
		
		host.setCheck2(request.getParameter("sleeping"));
		host.setCity(request.getParameter("hostcity"));
		host.setContent(request.getParameter("hostetc"));
		System.out.println(request.getParameter("possible"));
		
		RequestDispatcher view = null;
		
		try {
			int result = new HostService().insertHost(host);
			
			if(result > 0){
				response.sendRedirect("/hifive/views/user/mypage.jsp");
			}else{
	    		view = request.getRequestDispatcher("");
		        request.setAttribute("message", "수정 실패");
		        view.forward(request, response);
			}			
		} catch (HostException e) {
    		view = request.getRequestDispatcher("");
	        request.setAttribute("message", "수정 실패");
	        view.forward(request, response);
		}
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);


		
	}

}
