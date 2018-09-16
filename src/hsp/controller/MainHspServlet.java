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
import hsp.model.service.SurferPartnerService;
import hsp.model.vo.Host;
import hsp.model.vo.SurferPartner;

/**
 * Servlet implementation class MainHspServlet
 */
@WebServlet("/mainhsp")
public class MainHspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainHspServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");

		JSONObject json = new JSONObject();
		
		Host host = new HostService().selectHost(userId);
		Host mHost = new HostService().selectMHost(userId);
		SurferPartner surfer = new SurferPartnerService().selectSurfer(userId);
		SurferPartner mSurfer = new SurferPartnerService().selectMSurfer(userId);
		SurferPartner partner = new SurferPartnerService().selectPartner(userId);
		SurferPartner mPartner = new SurferPartnerService().selectMPartner(userId);
		
		if(host==null && mHost==null)
			json.put("host", 0);
		else
			json.put("host", 1);
		
		if(surfer==null && mSurfer==null)
			json.put("surfer", 0);
		else
			json.put("surfer", 1);
		
		if(partner==null && mPartner==null)
			json.put("partner", 0);
		else
			json.put("partner", 1);
		
		// json 내보내기
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json.toJSONString());
		out.flush();
		
	}
}