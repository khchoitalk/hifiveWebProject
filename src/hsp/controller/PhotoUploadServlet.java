package hsp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import hsp.model.service.HostService;
import hsp.model.vo.Host;

/**
 * Servlet implementation class PhotoUploadServlet
 */
@WebServlet("/photoupload")
public class PhotoUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhotoUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int maxSize = 1024 * 1024 * 10;

		RequestDispatcher view = null;
		// enctype 속성이 "multipart/form-data"로 전송 체크
		if (!ServletFileUpload.isMultipartContent(request)) {

		}
		// 파일이 업로드되어 저장될 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/photoUpload");

		// request 를 MultipartRequest 로 변환함
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		String userid = mrequest.getParameter("photouserid");
		String originalFileName1 = "";
		String originalFileName2 = "";
		String originalFileName3 = "";
		originalFileName1 = mrequest.getFilesystemName("photo1");
		originalFileName2 = mrequest.getFilesystemName("photo2");
		originalFileName3 = mrequest.getFilesystemName("photo3");
		
		String renameFileName1 = "";
		String renameFileName2 = "";
		String renameFileName3 = "";

		Host host = new HostService().selectHost(userid);

		if (originalFileName1 != null) {
			renameFileName1 = userid + "photo1." 
					+ originalFileName1.substring(originalFileName1.lastIndexOf(".") + 1);
			renameFileName1 = renameFileName1.toLowerCase();
			// 파일명 바꾸려면 File 객체의 renameTo() 사용함
			File originFile1 = new File(savePath + "\\" + originalFileName1);
			File renameFile1 = new File(savePath + "\\" + renameFileName1);

			// 파일 이름바꾸기 실행 >> 실패할 경우 직접 바꾸기함
			// 새 파일만들고 원래 파일 내용 읽어서 복사하고
			// 복사가 끝나면 원래 파일 삭제함
			if (!originFile1.renameTo(renameFile1)) {
				int read = -1;
				byte[] buf = new byte[1024];

				FileInputStream fin = new FileInputStream(originFile1);
				FileOutputStream fout = new FileOutputStream(renameFile1);

				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}

				fin.close();
				fout.close();

				// 원본 파일 삭제함
				originFile1.delete();
			}
		} else {
			if(host.getImage1() != null && !host.getImage1().equals("sample.jpg")) {
				renameFileName1 = host.getImage1();
			}
			else 
				renameFileName1 = "sample.jpg";
		}

		if (originalFileName2 != null) {
			renameFileName2 = userid + "photo2." 
					+ originalFileName2.substring(originalFileName2.lastIndexOf(".") + 1);
			
			renameFileName2 = renameFileName2.toLowerCase();
			// 파일명 바꾸려면 File 객체의 renameTo() 사용함
			File originFile2 = new File(savePath + "\\" + originalFileName2);
			File renameFile2 = new File(savePath + "\\" + renameFileName2);

			// 파일 이름바꾸기 실행 >> 실패할 경우 직접 바꾸기함
			// 새 파일만들고 원래 파일 내용 읽어서 복사하고
			// 복사가 끝나면 원래 파일 삭제함
			if (!originFile2.renameTo(renameFile2)) {
				int read = -1;
				byte[] buf = new byte[1024];

				FileInputStream fin = new FileInputStream(originFile2);
				FileOutputStream fout = new FileOutputStream(renameFile2);

				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}

				fin.close();
				fout.close();

				// 원본 파일 삭제함
				originFile2.delete();
			}
		} else {
			if(host.getImage2() != null && !host.getImage2().equals("sample.jpg")) {
				renameFileName2 = host.getImage2();
			}
			else
				renameFileName2 = "sample.jpg";
		}

		if (originalFileName3 != null) {			
			renameFileName3 = userid + "photo3." 
					+ originalFileName3.substring(originalFileName3.lastIndexOf(".") + 1);
			renameFileName3 = renameFileName3.toLowerCase();
			// 파일명 바꾸려면 File 객체의 renameTo() 사용함
			File originFile3 = new File(savePath + "\\" + originalFileName3);
			File renameFile3 = new File(savePath + "\\" + renameFileName3);

			// 파일 이름바꾸기 실행 >> 실패할 경우 직접 바꾸기함
			// 새 파일만들고 원래 파일 내용 읽어서 복사하고
			// 복사가 끝나면 원래 파일 삭제함
			if (!originFile3.renameTo(renameFile3)) {
				int read = -1;
				byte[] buf = new byte[1024];

				FileInputStream fin = new FileInputStream(originFile3);
				FileOutputStream fout = new FileOutputStream(renameFile3);

				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}

				fin.close();
				fout.close();

				// 원본 파일 삭제함
				originFile3.delete();
			}
		} else {
			if(host.getImage3() != null && !host.getImage3().equals("sample.jpg")) {
				renameFileName3 = host.getImage3();
			}
			else 
				renameFileName3 = "sample.jpg";
		}

		try {	
			if(host != null) {
				if(new HostService().updatePhoto(renameFileName1, renameFileName2, renameFileName3, userid) > 0) {
					response.sendRedirect("/hifive/views/user/mypage.jsp");
				}
			} else {
				System.out.println("호스트 등록 정보 없음");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
