package hsp.controller;

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
import hsp.model.vo.Host;
import user.model.vo.User;

/**
 * Servlet implementation class HostSearchServlet
 */
@WebServlet("/hostsearch")
public class HostSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num").trim());
		String gender = request.getParameter("gender");
		String check = request.getParameter("check");
		String sleeping = request.getParameter("sleeping");
		String destination = request.getParameter("destination");
		System.out.println("넘어온 값 : "+num+","+gender+","+check+","+sleeping+","+destination);
		
		Host host = new Host();
		host.setUser_num(num);
		host.setP_gender(gender);
		host.setCheck1(check);
		host.setCheck2(sleeping);
		host.setCity(destination);
		
		ArrayList<User> list = new HostService().searchHost(host);
		
		JSONObject job1 = new JSONObject();
		JSONArray jar = new JSONArray();
		
		for(User u : list){
			JSONObject job2 = new JSONObject();
			job2.put("id", u.getUser_Id());
			job2.put("name", u.getUser_Name());
			job2.put("address", u.getAddress());
			job2.put("nationality", u.getNationality());
			job2.put("image", u.getProfile_image());
			jar.add(job2);
		}
		job1.put("list", jar);
		System.out.println("가져온값 : "+job1.toJSONString());
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.append(job1.toJSONString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
