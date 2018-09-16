package request.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

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
import request.model.service.MatchingService;
import request.model.vo.Matching;
import user.model.service.UserService;

/**
 * Servlet implementation class MyPlanListServlet
 */
@WebServlet("/myplan")
public class MyPlanListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPlanListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 매칭 목록
		String userid = request.getParameter("userid");

		Matching m_host = new MatchingService().hostMatching(userid); // 내가 호스트일때, 서퍼 매칭 정보 찾음
		Matching m_surfer = new MatchingService().surferMatching(userid); // 내가 서퍼일때, 호스트 매칭 정보 찾음
		Matching m_partner = new MatchingService().partnerMatching(userid); // 파트너 매칭 정보 찾음
		
		// 전송될 json 객체 선언 : 객체 하나만 내보낼 수 있음
	    JSONObject json = new JSONObject();
	    
	    // 내가 호스트일때, 서퍼 정보 받아서 출력
	    JSONObject job1 = new JSONObject();
	    if(m_host != null) {
		    SurferPartner surfer = new SurferPartnerService().selectMSurfer(m_host.getUser2());
		    job1.put("matching_no", m_host.getMatching_no());
		    job1.put("user_id", surfer.getUser_id());
	    	job1.put("start_date", surfer.getStart_date().toString());
	    	job1.put("end_date", surfer.getEnd_date().toString());
	    	job1.put("image", new UserService().getProfileImage(surfer.getUser_id()));
	    	job1.put("user_name", new UserService().getUserName(surfer.getUser_id()));
	    }
	    	    
    	// 내가 서퍼일때, 호스트 정보 받아서 출력
	    JSONObject job2 = new JSONObject();
	    if(m_surfer != null) {
		    Host host = new HostService().selectMHost(m_surfer.getUser1());
		    SurferPartner surfer = new SurferPartnerService().selectMSurfer(m_surfer.getUser2()); 
		    job2.put("matching_no", m_surfer.getMatching_no());
	    	job2.put("user_id", host.getUser_id());
	    	job2.put("image", new UserService().getProfileImage(host.getUser_id()));
	    	job2.put("user_name", new UserService().getUserName(host.getUser_id()));
	    	job2.put("start_date", surfer.getStart_date().toString());
	    	job2.put("end_date", surfer.getEnd_date().toString());
	    }
    	
    	JSONObject job3 = new JSONObject();
    	if(m_partner != null) {
	    	SurferPartner partner = null;    	
	    	if(userid.equals(m_partner.getUser1()))
	    		partner = new SurferPartnerService().selectMPartner(m_partner.getUser2());
	    	else
	    		partner = new SurferPartnerService().selectMPartner(m_partner.getUser1());	
	    	job3.put("matching_no", m_partner.getMatching_no());
	    	job3.put("user_id", partner.getUser_id());
	    	job3.put("start_date", partner.getStart_date().toString());
	    	job3.put("end_date", partner.getEnd_date().toString());
	    	job3.put("image", new UserService().getProfileImage(partner.getUser_id()));
	    	job3.put("user_name", new UserService().getUserName(partner.getUser_id()));
    	}
    	json.put("host", job1);
    	json.put("surfer", job2);
    	json.put("partner", job3);
    	
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
