package request.model.service;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import request.exception.RequestException;
import request.model.dao.RequestDao;
import request.model.vo.Request;

public class RequestService {

   // 내가 호스트에게 요청한 리스트
   public ArrayList<Request> myHostList1(String userId) {
      Connection con = getConnection();
      ArrayList<Request> list = new RequestDao().myHostList1(con, userId);
      close(con);
      return list;
   }

   // 나에게 호스트 요청한 유저(서퍼)리스트
   public ArrayList<Request> myHostList2(String userId) {
      Connection con = getConnection();
      ArrayList<Request> list = new RequestDao().myHostList2(con, userId);
      close(con);
      return list;
   }

   // 내가 서퍼에게 요청한 리스트
   public ArrayList<Request> mySurferList1(String userId) {
      Connection con = getConnection();
      ArrayList<Request> list = new RequestDao().mySurferList1(con, userId);
      close(con);
      return list;
   }

   // 나에게 서퍼 요청한 유저(호스트)리스트
   public ArrayList<Request> mySurferList2(String userId) {
      Connection con = getConnection();
      ArrayList<Request> list = new RequestDao().mySurferList2(con, userId);
      close(con);
      return list;
   }
   
   // 내가 파트너 요청한 리스트
   public ArrayList<Request> myPartnerList1(String userId) {
      Connection con = getConnection();
      ArrayList<Request> list = new RequestDao().myPartnerList1(con, userId);
      close(con);
      return list;
   }
   
   // 나에게 파트너 요청한 유저(파트너) 리스트
   public ArrayList<Request> myPartnerList2(String userId) {
      Connection con = getConnection();
      ArrayList<Request> list = new RequestDao().myPartnerList2(con, userId);
      close(con);
      return list;
   }

   // 내가 한 요청 취소했을 때
   public int deleteRequest(int request_no) throws RequestException {
	   Connection con = getConnection();
	   int result = new RequestDao().deleteRequest(con, request_no);
	   if(result > 0)
		   commit(con);
	   else
		   rollback(con);
	   close(con);
	   return result;
   }

   // 요청 거절했을 때
   public int refuseRequest(int request_no) throws RequestException {
	   Connection con = getConnection();
  	   int result = new RequestDao().refuseRequest(con, request_no);
  	   if(result > 0)
  		   commit(con);
  	   else
  		   rollback(con);
  	   close(con);
  	   return result;
  	}
   
   // 요청 수락했을 때
   public int acceptRequest(int request_no) throws RequestException {
	   Connection con = getConnection();
  	   int result = new RequestDao().acceptRequest(con, request_no);
  	   if(result > 0)
  		   commit(con);
  	   else
  		   rollback(con);
  	   close(con);
  	   return result;
   }
   
   // 요청 등록
   public int insertRequest(String loginid, String loginrole, String profileid) throws RequestException {
	   Connection con = getConnection();
	   int result = new RequestDao().insertRequest(con, loginid, loginrole, profileid);
	   if(result > 0)
		   commit(con);
	   else
		   rollback(con);
	   close(con);
	   return result;
   }
   
   // 내가 신청한 사람인지 확인
   public Request checkRequest(String loginid, String loginrole, String profileid) throws RequestException {
	   Connection con = getConnection();
	   Request r = new RequestDao().checkRequest(con, loginid, loginrole, profileid);
	   close(con);
	   return r;
   }
   
   // 요청 종류
   public String checkRole(int request_no){
	   Connection con = getConnection();
	   String role = new RequestDao().checkRole(con, request_no);
	   close(con);
	   return role;
   }
   
   // 요청번호로 user_id 아이디 가지고오기   
   public String selectUser_Id(int request_no){
	   Connection con = getConnection();
	   String user1 = new RequestDao().selectUser_Id(con, request_no);
	   close(con);
	   return user1;
   }
   // 요청번호로 user2 아이디 가지고오기
   public String selectR_User_Id(int request_no){
	   Connection con = getConnection();
	   String user2 = new RequestDao().selectR_User_Id(con, request_no);
	   close(con);
	   return user2;
   }

}