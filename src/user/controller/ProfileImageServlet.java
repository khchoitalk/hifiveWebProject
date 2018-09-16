package user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import user.model.service.UserService;
import user.model.vo.User;

/**
 * Servlet implementation class ProfileImageServlet
 */
@WebServlet("/pimage")
public class ProfileImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 업로드할 파일의 용량 제한 : 10Mbyte로 제한한다면
		int maxSize = 1024 * 1024 * 10;
		
		RequestDispatcher view = null;
		// enctype 속성이 "multipart/form-data"로 전송 체크
		if (!ServletFileUpload.isMultipartContent(request)) {
			 
		}
		// 파일이 업로드되어 저장될 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/profileUpfiles");

		// request 를 MultipartRequest 로 변환함
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		
		String userid = mrequest.getParameter("imguserid");
		// 저장폴더에 기록된 원래 파일명 조회
		String originalFileName = mrequest.getFilesystemName("pimg");
		String renameFileName = "";
		
		if (originalFileName != null) {
			renameFileName = userid + "profileimage." 
					+ originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			renameFileName = renameFileName.toLowerCase();
			// 파일명 바꾸려면 File 객체의 renameTo() 사용함
			File originFile = new File(savePath + "\\" + originalFileName);
			File renameFile = new File(savePath + "\\" + renameFileName);

			// 파일 이름바꾸기 실행 >> 실패할 경우 직접 바꾸기함
			// 새 파일만들고 원래 파일 내용 읽어서 복사하고
			// 복사가 끝나면 원래 파일 삭제함
			if (!originFile.renameTo(renameFile)) {
				int read = -1;
				byte[] buf = new byte[1024];

				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);

				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}

				fin.close();
				fout.close();
				// 원본 파일 삭제함
				originFile.delete();
			}

			
		}
		
		User user = new User();
		UserService uService = new UserService();
		
		
		try {
			user = uService.selectUser(userid);
			user.setProfile_image(renameFileName);
			
			if(uService.updateProfileImg(user) > 0) {
				response.sendRedirect("/hifive/views/user/mypage.jsp");
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
