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

import message.model.service.MessageListService;
import message.model.vo.MessageList;
import user.model.service.UserService;

/**
 * Servlet implementation class MessageListServlet
 */
@WebServlet("/mlist")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("uid");
		ArrayList<MessageList> mList1 = new MessageListService().selectMyList1(user_id);
		ArrayList<MessageList> mList2 = new MessageListService().selectMyList2(user_id);
		
		// 전송될 json 객체 선언 : 객체 하나만 내보낼 수 있음
		JSONObject json = new JSONObject();
		// list는 json 배열에 저장하고, json 배열을 전송용 json 객체에 저장함
		JSONArray jarr = new JSONArray();
		JSONArray jarr2 = new JSONArray();
		
		for(MessageList list : mList1){
			JSONObject job = new JSONObject();
			job.put("list_no", list.getList_no());
			job.put("user1", list.getUser1());
			job.put("user2", list.getUser2());
			job.put("userimg", new UserService().selectUser(list.getUser2()).getProfile_image());
			
			job.put("userName", new MessageListService().selectUserName(list.getUser1(), list.getUser2()));
		
			jarr.add(job);					
		}
		for(MessageList list : mList2){
			JSONObject job = new JSONObject();
			job.put("list_no", list.getList_no());
			job.put("user1", list.getUser1());
			job.put("user2", list.getUser2());
			job.put("userimg", new UserService().selectUser(list.getUser1()).getProfile_image());

			job.put("userName", new MessageListService().selectUserName2(list.getUser2(), list.getUser1()));
		
			jarr2.add(job);
		}

		// json 배열을 전송용 json 객체에 저장함
		json.put("list", jarr); // 내가 user1
		json.put("list2", jarr2); // 내가 user2
		
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
