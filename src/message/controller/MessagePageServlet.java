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

import message.model.service.MessageService;
import message.model.vo.Message;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class MessagePageServlet
 */
@WebServlet("/mpage")
public class MessagePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int list_no = Integer.parseInt(request.getParameter("listno")); 
		String rid = request.getParameter("rid");
		
		// 전송될 json 객체 선언 : 객체 하나만 내보낼 수 있음
		JSONObject json = new JSONObject();
		// list는 json 배열에 저장하고, json 배열을 전송용 json 객체에 저장함
		JSONArray jarr = new JSONArray();
		
		ArrayList<Message> myMessage = new MessageService().selectMyMessage(list_no);
		User ruser = new UserService().selectUser(rid);

		json.put("rusername", ruser.getUser_Name());
		json.put("ruserimg", ruser.getProfile_image());
		for(Message m : myMessage){			
			JSONObject job = new JSONObject();
			job.put("sender", m.getSender());
			job.put("content", m.getContent());
			
			jarr.add(job);
		}
		
		json.put("list", jarr);
		
		// json 내보내기
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json.toJSONString());
		out.flush();	
		
		
		/*RequestDispatcher view = null;
		view = request.getRequestDispatcher("views/message/messagePage.jsp");
		request.setAttribute("mList", myMessage);
		request.setAttribute("user_id", user_id);
		request.setAttribute("list_no", list_no);
		view.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
