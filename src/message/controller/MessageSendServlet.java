package message.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.model.service.MessageService;
import message.model.vo.Message;

/**
 * Servlet implementation class MessageSendServlet
 */
@WebServlet("/msend")
public class MessageSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String rid = request.getParameter("rid");
		Message msg = new Message();
		msg.setList_no(Integer.parseInt(request.getParameter("listno")));
		msg.setSender(request.getParameter("userid"));
		msg.setContent(request.getParameter("content"));
		System.out.println(msg);

		RequestDispatcher view = null;
		try{
			if(new MessageService().sendMessage(msg) > 0) {
				response.sendRedirect("/hifive/views/message/messagePage.jsp?listno="+msg.getList_no()+"&uid="+msg.getSender()+"&rid="+rid);					
			} else {
				response.setContentType("text/html;charset=utf-8"); 
				PrintWriter out= response.getWriter(); 
				out.println("<script>");
				out.println("alert('메세지를 입력해주세요');");
				out.println("history.back();");
				out.println("</script>");
			}
		} catch(Exception e){
			view = request.getRequestDispatcher("views/message/messageError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
	}

}
