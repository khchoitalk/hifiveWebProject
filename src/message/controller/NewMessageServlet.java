package message.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import message.model.service.MessageListService;

/**
 * Servlet implementation class NewMessageServlet
 */
@WebServlet("/newmessage")
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int result = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String userid = request.getParameter("uid");
		int count = new MessageListService().listCount(userid);
		
		JSONObject json = new JSONObject();

		if(result < count) {
			json.put("result", "1");
		} else {
			json.put("result", "2");
		}

		
		result = count;		


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
