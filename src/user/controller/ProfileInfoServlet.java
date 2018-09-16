package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hsp.model.service.HostService;
import hsp.model.service.SurferPartnerService;
import hsp.model.vo.Host;
import hsp.model.vo.SurferPartner;
import user.exception.UserException;
import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class ProfileInfoServlet
 */
@WebServlet("/profileinfo")
public class ProfileInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		
		RequestDispatcher view = null;
		User user = new UserService().selectUser(userId);
		
		Host host = new HostService().selectHost(userId);
		SurferPartner surfer = new SurferPartnerService().selectSurfer(userId);
		SurferPartner partner = new SurferPartnerService().selectPartner(userId);
		 
		if(user != null){
			view = request.getRequestDispatcher("views/user/profile.jsp"); // info 페이지
			request.setAttribute("profileuser", user);
			if(host != null) {
				request.setAttribute("profileH", host);
			}
			if(surfer != null) {
				request.setAttribute("profileS", surfer);
			}
			if(partner != null) {
				request.setAttribute("profileP", partner);
			}
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher(""); // 에러페이지
			request.setAttribute("message", userId + "에 대한 조회 실패");
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
