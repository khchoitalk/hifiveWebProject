package request.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import hsp.model.service.HostService;
import hsp.model.service.SurferPartnerService;
import message.model.service.MessageListService;
import request.exception.RequestException;
import request.model.service.MatchingService;
import request.model.service.RequestService;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/request")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginid = request.getParameter("loginid");		
		String profileid = request.getParameter("profileid");	
		String profilerole = request.getParameter("profilerole");	

		JSONObject job = new JSONObject();
		String loginrole = "";

		try {
			if(profilerole.toUpperCase().equals("H")) {
				if(new SurferPartnerService().selectSurfer(loginid) != null) {
					loginrole = "S";
					if(new MatchingService().surferMatching(loginid)!= null || new MatchingService().surferMatching(profileid)!= null ){
						job.put("result", "3");
					} else {					
						if(new RequestService().checkRequest(loginid, loginrole, profileid) != null) {
							job.put("result", "0");
						} else {
							if(new RequestService().insertRequest(loginid, loginrole, profileid) > 0) {							
								if(new MessageListService().checkMList(loginid, profileid)==0)
									new MessageListService().insertMessageRequest(profileid, loginid);
								job.put("result", "1");							
							}
						}
					}
				} else {
					job.put("result", "2");
				}

			} else if(profilerole.toUpperCase().equals("S")) {
				if(new HostService().selectHost(loginid) != null) {
					loginrole = "H";
					if(new MatchingService().hostMatching(loginid)!=null || new MatchingService().hostMatching(profileid)!=null){
						job.put("result", "3");
					}
					else{
						if(new RequestService().checkRequest(loginid, loginrole, profileid) != null) {
							job.put("result", "0");
						} else {
							if(new RequestService().insertRequest(loginid, loginrole, profileid) > 0) {
								if(new MessageListService().checkMList(loginid, profileid)==0)
									new MessageListService().insertMessageRequest(profileid, loginid);
								job.put("result", "1");
							}
						}
					}
				} else {
					job.put("result", "2");
				}				
			} else if(profilerole.toUpperCase().equals("P")) {
				if(new SurferPartnerService().selectPartner(loginid) != null) {
					loginrole = "P";
					if(new MatchingService().partnerMatching(loginid)!=null || new MatchingService().partnerMatching(profileid)!=null){
						job.put("result", 3);
					}
					else{
						if(new RequestService().checkRequest(loginid, loginrole, profileid) != null) {
							job.put("result", "0");
						} else {
							if(new RequestService().insertRequest(loginid, loginrole, profileid) > 0) {	
								if(new MessageListService().checkMList(loginid, profileid)==0)
									new MessageListService().insertMessageRequest(profileid, loginid);
								job.put("result", "1");
							}
						}
					}
				} else {
					job.put("result", "2");
				}				
			}

			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.append(job.toJSONString());
			out.flush();
			out.close();	
		} catch (RequestException e) {
			System.out.println("요청 오류");
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
