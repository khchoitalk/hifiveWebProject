package request.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hsp.model.service.HostService;
import hsp.model.service.SurferPartnerService;
import hsp.model.vo.Host;
import hsp.model.vo.SurferPartner;
import request.model.service.MatchingService;
import request.model.service.RequestService;
import request.model.vo.Matching;
import request.model.vo.Request;
import user.model.service.UserService;

/**
 * Servlet implementation class RequestListServlet
 */
@WebServlet("/requestlist")
public class RequestListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // main에 출력 시킬 리스트 목록
      String userId = request.getParameter("uid");
      
      ArrayList<Request> h_list_2 = new RequestService().myHostList2(userId);
      ArrayList<Request> s_list_1 = new RequestService().mySurferList1(userId);
      ArrayList<Request> s_list_2 = new RequestService().mySurferList2(userId);
      ArrayList<Request> h_list_1 = new RequestService().myHostList1(userId);      
      ArrayList<Request> p_list_1 = new RequestService().myPartnerList1(userId);
      ArrayList<Request> p_list_2 = new RequestService().myPartnerList2(userId);
      
      Matching m_host = new MatchingService().hostMatching(userId); // 매칭이 이미 되었는지 확인
      Matching m_surfer = new MatchingService().surferMatching(userId); // 매칭이 이미 되었는지 확인
      Matching m_partner = new MatchingService().partnerMatching(userId); // 매칭이 이미 되었는지 확인
    
      // 전송될 json 객체 선언 : 객체 하나만 내보낼 수 있음
      JSONObject json = new JSONObject();
      // list는 json 배열에 저장하고, json 배열을 전송용 json 객체에 저장함
      JSONArray jarr_h1 = new JSONArray();      
      JSONArray jarr_h2 = new JSONArray();
      JSONArray jarr_s1 = new JSONArray();      
      JSONArray jarr_s2 = new JSONArray();
      JSONArray jarr_p1 = new JSONArray();      
      JSONArray jarr_p2 = new JSONArray();

      // 나(호스트)에게 요청한 유저(서퍼)리스트
      for(Request r : h_list_2){
          JSONObject job = new JSONObject();
          SurferPartner surfer = new SurferPartnerService().selectSurfer(r.getUser_id());
          job.put("request_no", r.getRequest_no());
          job.put("user_id", r.getUser_id()); // 상대방 아이디
          job.put("request_date", r.getRequest_date().toString()); // 요청 날짜
          job.put("user_name", new UserService().getUserName(r.getUser_id()));
          job.put("image", new UserService().getProfileImage(r.getUser_id()));
          job.put("start_date", surfer.getStart_date().toString());
          job.put("end_date", surfer.getEnd_date().toString());
          
          jarr_h2.add(job);   
       }
      
      // 내(호스트)가 서퍼에게 요청한 리스트
      for(Request r : s_list_1){
          JSONObject job = new JSONObject();
          Host host = new HostService().selectHost(r.getUser_id()); // 내 아이디         
          SurferPartner surfer = new SurferPartnerService().selectSurfer(r.getR_user_id()); // 상대방 아이디
          job.put("request_no", r.getRequest_no());
          job.put("r_user_id", r.getR_user_id()); // 상대방 아이디
          job.put("request_date", r.getRequest_date().toString()); // 요청 날짜
          job.put("user_name", new UserService().getUserName(r.getR_user_id()));
          job.put("image", new UserService().getProfileImage(r.getR_user_id()));
          job.put("start_date", surfer.getStart_date().toString());
          job.put("end_date", surfer.getEnd_date().toString());
          
          jarr_s1.add(job);   
       }
      
      // 나(서퍼)에게 요청한 유저(호스트)리스트 
      for(Request r : s_list_2){
          JSONObject job = new JSONObject();
          Host host = new HostService().selectHost(r.getUser_id());
          SurferPartner surfer = new SurferPartnerService().selectSurfer(r.getR_user_id());
          job.put("request_no", r.getRequest_no());
          job.put("user_id", r.getUser_id()); // 상대방 아이디
          job.put("request_date", r.getRequest_date().toString()); // 요청 날짜
          job.put("user_name", new UserService().getUserName(r.getUser_id()));
          job.put("image", new UserService().getProfileImage(r.getUser_id()));
          job.put("start_date", surfer.getStart_date().toString());
          job.put("end_date", surfer.getEnd_date().toString());
          
          jarr_s2.add(job);   
      }
      
      // 내(서퍼)가 호스트에게 요청한 리스트
      for(Request r : h_list_1){
         JSONObject job = new JSONObject();
         Host host = new HostService().selectHost(r.getR_user_id()); // 상대방
         SurferPartner surfer = new SurferPartnerService().selectSurfer(r.getUser_id());
         job.put("request_no", r.getRequest_no());
         job.put("r_user_id", r.getR_user_id()); // 상대방 아이디
         job.put("request_date", r.getRequest_date().toString()); // 요청 날짜         
         job.put("user_name", new UserService().getUserName(r.getR_user_id())); // 이름
         job.put("image", new UserService().getProfileImage(r.getR_user_id())); // 프로필사진
         job.put("start_date", surfer.getStart_date().toString());
         job.put("end_date", surfer.getEnd_date().toString());
         
         jarr_h1.add(job);   
      }         
      
      // 내가 파트너 요청한 리스트
      for(Request r : p_list_1){
         JSONObject job = new JSONObject();
         SurferPartner partner = new SurferPartnerService().selectPartner(r.getR_user_id());
         job.put("request_no", r.getRequest_no());
         job.put("r_user_id", r.getR_user_id()); // 상대방 아이디
         job.put("request_date", r.getRequest_date().toString()); // 요청 날짜
         job.put("user_name", new UserService().getUserName(r.getR_user_id())); // 이름
         job.put("image", new UserService().getProfileImage(r.getR_user_id())); // 프로필사진
         job.put("start_date", partner.getStart_date().toString());
         job.put("end_date", partner.getEnd_date().toString());
         jarr_p1.add(job);   
      }
      
      // 상대방이 나에게 파트너 요청
      for(Request r : p_list_2){
         JSONObject job = new JSONObject();
         SurferPartner partner = new SurferPartnerService().selectPartner(r.getUser_id());
         job.put("request_no", r.getRequest_no());
         job.put("user_id", r.getUser_id()); // 상대방 아이디
         job.put("request_date", r.getRequest_date().toString()); // 요청 날짜
         job.put("user_name", new UserService().getUserName(r.getUser_id()));
         job.put("image", new UserService().getProfileImage(r.getUser_id()));
         job.put("start_date", partner.getStart_date().toString());
         job.put("end_date", partner.getEnd_date().toString());
         jarr_p2.add(job);   
      }
            
      json.put("list_h2", jarr_h2); // 나(호스트)에게 요청한 유저(서퍼)리스트
      json.put("list_s1", jarr_s1); // 내(호스트)가 서퍼에게 요청한 리스트
      json.put("list_s2", jarr_s2); // 내(서퍼)가 호스트에게 요청한 리스트
      json.put("list_h1", jarr_h1); // 내(서퍼)가 호스트에게 요청한 리스트
      json.put("list_p1", jarr_p1); // 내가 파트너 요청한 리스트
      json.put("list_p2", jarr_p2); // 상대방이 나에게 파트너 요청
      
      if(m_host != null) // 내가 호스트일때, 서퍼 매칭 정보 찾음
    	  json.put("m_host", 1);
      else
    	  json.put("m_host", 0);
       
      if(m_surfer != null) // 내가 서퍼일때, 호스트 매칭 정보 찾음
    	  json.put("m_surfer", 1);
      else
    	  json.put("m_surfer", 0);
      
      if(m_partner != null) // 파트너 매칭 정보 찾음
    	  json.put("m_partner", 1);
      else
    	  json.put("m_partner", 0);      
     
      response.setContentType("application/json; charset=utf-8");
      PrintWriter out = response.getWriter();
      out.print(json.toJSONString());
      out.flush();
      
   }

}