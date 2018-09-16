package message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.model.service.MessageRequestService;

/**
 * Servlet implementation class MessageRequestCancelServlet
 */
@WebServlet("/mrequestc")
public class MessageRequestCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageRequestCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String user_id = request.getParameter("userid");

		RequestDispatcher view = null;
		try{
			if(new MessageRequestService().deleteRequest(user_id, sender) > 0) {
				response.sendRedirect("views/message/messageList.jsp");
			} else {
				view = request.getRequestDispatcher("views/message/messageError.jsp");
				request.setAttribute("message", "대화 요청 취소 실패");
				view.forward(request, response);
			}
		} catch(Exception e){
			view = request.getRequestDispatcher("views/message/messageError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
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
