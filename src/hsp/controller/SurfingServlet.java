package hsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import hsp.model.service.SurferPartnerService;
import hsp.model.vo.SurferPartner;
import request.model.service.MatchingService;

/**
 * Servlet implementation class SurfingServlet
 */
@WebServlet("/surfing")
public class SurfingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SurfingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");		


		SurferPartner surfer = new SurferPartnerService().selectSurfer(userId);

		JSONObject job = new JSONObject();
		if(surfer != null) {
			job.put("result", "0");
			job.put("destination", surfer.getCity());
			job.put("startdate", surfer.getStart_date().toString());
			job.put("enddate", surfer.getEnd_date().toString());
			job.put("num", surfer.getUser_num());			
		}
		else {
			job.put("result", "1");
		}
		
		if(new MatchingService().surferMatching(userId)!=null)
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
