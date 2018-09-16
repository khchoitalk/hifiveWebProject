package message.controller;

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

import message.model.service.MessageRequestService;
import message.model.vo.MessageRequest;
import user.model.service.UserService;

/**
 * Servlet implementation class MessageRequestListServlet
 */
@WebServlet("/mrlist")
public class MessageRequestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageRequestListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내가 받은 대화 신청 목록
		
		String user_id = request.getParameter("uid");

		ArrayList<MessageRequest> list = new MessageRequestService().selectMyAll(user_id);
		// 전송될 json 객체 선언 : 객체 하나만 내보낼 수 있음
		JSONObject json = new JSONObject();
		// list는 json 배열에 저장하고, json 배열을 전송용 json 객체에 저장함
		JSONArray jarr = new JSONArray();

		for(MessageRequest mr : list){
			JSONObject job = new JSONObject();
			job.put("request_no", mr.getRequest_no());
			job.put("user_id", mr.getUser_id());

			job.put("sender", mr.getSender());
			job.put("accept", mr.getAccept());
			job.put("userName", new MessageRequestService().selectSenderName(user_id, mr.getSender())); // 이름
			
			jarr.add(job);
		}
		
		// json 배열을 전송용 json 객체에 저장함
		json.put("list", jarr);
				
		// json 내보내기
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
