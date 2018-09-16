package hsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import hsp.model.service.HostService;
import hsp.model.vo.Host;
import request.model.service.MatchingService;



/**
 * Servlet implementation class HostingServlet
 */
@WebServlet("/hosting")
public class HostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userid");		
	
		Host host = new HostService().selectHost(userId);
		
		JSONObject job = new JSONObject();
		if(host != null) {
			job.put("result", "0");
			job.put("num", host.getUser_num());
			job.put("gender", host.getP_gender());			
			job.put("check1", host.getCheck1());
			job.put("check2", host.getCheck2());
			job.put("city", host.getCity());
			job.put("content", host.getContent());
			job.put("photo1", host.getImage1());
			job.put("photo2", host.getImage2());
			job.put("photo3", host.getImage3());
		}
		else {
			job.put("result", "1");
		}
		
		if(new MatchingService().hostMatching(userId)!=null)
			job.put("matching", "M");		
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.append(job.toJSONString());
		out.flush();
		out.close();	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
