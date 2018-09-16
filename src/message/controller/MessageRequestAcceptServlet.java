package message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import message.exception.MessageException;
import message.model.service.MessageRequestService;

/**
 * Servlet implementation class MessageRequestAcceptServlet
 */
@WebServlet("/mraccept")
public class MessageRequestAcceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageRequestAcceptServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String userId = request.getParameter("uid");
		System.out.println(sender + ", " + userId);
		RequestDispatcher view = null;
		try {
			if(new MessageRequestService().updateRequest(userId, sender) > 0){
				response.sendRedirect("views/message/messageList.jsp");
			} else{
				view = request.getRequestDispatcher("views/message/messageError.jsp");
				request.setAttribute("message", "에러");
				view.forward(request, response);
			}
		} catch(MessageException e){
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
