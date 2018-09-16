package user.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import hsp.model.vo.SurferPartner;
import user.exception.UserException;
import user.model.vo.User;

public class UserDao {

   public UserDao() {}

   // 로그인 
   public String loginCheck(Connection con, String userId, String userPw) throws UserException {
      String userName = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select * from users "
            + "where user_id = ? and user_pw = ?";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);
         pstmt.setString(2, userPw);

         rset = pstmt.executeQuery();

         if(rset.next())
            userName = rset.getString("user_name");
         else
            userName = null;         
      } catch(Exception e){
         e.printStackTrace();

      } finally{
         close(rset);
         close(pstmt);
      }      
      return userName;
   }

   // 관리자용 회원 전체 조회
   public ArrayList<User> selectAllUser(Connection con) throws UserException {
      ArrayList<User> list = new ArrayList<User>();
      Statement stmt = null;
      ResultSet rset = null;

      String query = "select * from users";

      try{
         stmt = con.createStatement();
         rset = stmt.executeQuery(query);


         while(rset.next()){
            User user = new User();
            user.setUser_Id(rset.getString("user_id"));
            user.setUser_Pw(rset.getString("user_pw"));
            user.setUser_Name(rset.getString("user_name"));
            user.setEmail(rset.getString("email"));
            user.setPhone(rset.getString("phone"));
            user.setBirth(rset.getDate("birth"));
            user.setGender(rset.getString("gender"));
            user.setJoin_Date(rset.getDate("join_date"));
            user.setSafety_check(rset.getString("safety_check"));
            user.setNationality(rset.getString("nationality"));
            user.setAddress(rset.getString("address"));
            user.setHobby(rset.getString("hobby"));
            user.setJob(rset.getString("job"));
            user.setContent(rset.getString("content"));
            user.setRestriction(rset.getString("restriction"));
            user.setProfile_image(rset.getString("profile_image"));

            list.add(user);
         }         
         if(list.size() == 0)
            throw new UserException("회원 정보가 존재하지 않습니다.");         
      } catch(Exception e){
         e.printStackTrace();
         throw new UserException(e.getMessage());
      } finally{
         close(rset);
         close(stmt);
      }      
      return list;
   }

   // 회원가입
   public int insertUser(Connection con, User user) throws UserException {
      int result = 0;
      PreparedStatement pstmt = null;

      String query = "insert into users values "
            + "(?, ?, ?, ?, ?, ?, ?, sysdate, default, '', '', '', '', '', default, 'profile.png')";

      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, user.getUser_Id());
         pstmt.setString(2, user.getUser_Pw());
         pstmt.setString(3, user.getUser_Name());
         pstmt.setString(4, user.getEmail());
         pstmt.setString(5, user.getPhone());
         pstmt.setDate(6, user.getBirth());
         pstmt.setString(7, user.getGender());

         result = pstmt.executeUpdate();

         if(result <= 0)
            throw new UserException("회원 가입 실패");   
      } catch(Exception e){
         e.printStackTrace();
         throw new UserException(e.getMessage());
      } finally{
         close(pstmt);
      }

      return result;
      
   }


   // 회원탈퇴
   public int deleteUser(Connection con, String userId) throws UserException {
      int result = 0;
      PreparedStatement pstmt = null;

      String query = "delete from users where user_id = ?";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);

         result = pstmt.executeUpdate();

         if(result <= 0)
            throw new UserException("회원 탈퇴 실패");         
      } catch(Exception e){
         e.printStackTrace();
         throw new UserException(e.getMessage());
      } finally{
         close(pstmt);
      }
      return result;
   }


// 회원 한 명 선택
   public User selectUser(Connection con, String userId){
      User user = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select * from users where user_id = ?";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);

         rset = pstmt.executeQuery();

         if(rset.next()){
            user = new User();
            user.setUser_Id(userId);
            user.setUser_Pw(rset.getString("user_pw"));
            user.setUser_Name(rset.getString("user_name"));
            user.setEmail(rset.getString("email"));
            user.setPhone(rset.getString("phone"));
            user.setBirth(rset.getDate("birth"));
            user.setGender(rset.getString("gender"));
            user.setJoin_Date(rset.getDate("join_date"));
            user.setSafety_check(rset.getString("safety_check"));
            user.setNationality(rset.getString("nationality"));
            user.setAddress(rset.getString("address"));
            user.setHobby(rset.getString("hobby"));
            user.setJob(rset.getString("job"));
            user.setContent(rset.getString("content"));   
            user.setRestriction(rset.getString("restriction"));
            user.setProfile_image(rset.getString("profile_image"));
         } else {

         }
      } catch(Exception e){
         e.printStackTrace();

      } finally {
         close(rset);
         close(pstmt);
      }
      return user;
   }


		String query = "update users set address=?, NATIONALITY=?, job=?, hobby=?, content=?, phone=? where user_id = ?";

   // 회원 정보 수정
   public int updateUser(Connection con, User user) throws UserException {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update users set address=?, NATIONALITY=?, job=?, hobby=?, content=?, phone=? where user_id = ?";

      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, user.getAddress());
         pstmt.setString(2, user.getNationality());
         pstmt.setString(3, user.getJob());
         pstmt.setString(4, user.getHobby());
         pstmt.setString(5, user.getContent());
         pstmt.setString(6, user.getPhone());
         pstmt.setString(7, user.getUser_Id());

         result = pstmt.executeUpdate();

         if(result < 0){
            throw new UserException("마이페이지 수정 실패");
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new UserException(e.getMessage());
      } finally {
         close(pstmt);
      }

      return result;
   }

   // 프사 업로드
   public int updateProfileImg(Connection con, User user) throws UserException {
      int result = 0;
      PreparedStatement pstmt = null;

      String query = "update users set profile_image=? where user_id = ?";

      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, user.getProfile_image());
         pstmt.setString(2, user.getUser_Id());

         result = pstmt.executeUpdate();

         if(result < 0){
            throw new UserException("프사 업데이트 실패");
         }
      } catch (Exception e) {
         e.printStackTrace();
         throw new UserException(e.getMessage());
      } finally {
         close(pstmt);
      }

      return result;
   }



   // 아이디 찾기
   public String searchId(Connection con, String userEmail) throws UserException{
      String userId = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select * from users "
            + "where email = ? ";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userEmail);

         rset = pstmt.executeQuery();

         if(rset.next()){
            userId = rset.getString("user_id");                  
         } 
      } catch(Exception e){
         e.printStackTrace();

      } finally {
         close(rset);
         close(pstmt);
      }
      return userId;
   }

   public int selectCheckId(Connection con, String userId)throws UserException {
      int idCount = -1;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select count(user_id) "
            + "from users where user_id = ? ";

      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);

         rset = pstmt.executeQuery();

         if(rset.next()){
            idCount = rset.getInt(1);
         }

      } catch (Exception e) {
         e.printStackTrace();

      }finally{
         close(rset);
         close(pstmt);
      }

      return idCount;
   }

   // 안전 유의사항 체크
   public int safetyCheck(Connection con, String userid) throws UserException {
      int result = 0;
      PreparedStatement pstmt = null;

      String query = "update users set safety_check = ? where user_id = ?";

      try {
         pstmt = con.prepareStatement(query);         
         pstmt.setString(1, "Y");
         pstmt.setString(2, userid);

         result = pstmt.executeUpdate();

         if(result <= 0)
            throw new UserException("안전유의사항 체크 업데이트 실패!");
      } catch (Exception e) {
         e.printStackTrace();
         throw new UserException(e.getMessage());
      }finally{
         close(pstmt);
      }

      return result;
   }

   public int selectCheckEmail(Connection con, String email)throws UserException {
      int emailCount = -1;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select count(email) "
            + "from users where email = ? ";

      try {
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, email);

         rset = pstmt.executeQuery();

         if(rset.next()){
            emailCount = rset.getInt(1);
         }

      } catch (Exception e) {
         e.printStackTrace();

      }finally{
         close(rset);
         close(pstmt);
      }

      return emailCount;

   }

   // 회원 이름 조회
   public String getUserName(Connection con, String userid) {
      String userName = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select user_name from users where user_id = ?";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userid);

         rset = pstmt.executeQuery();

         if(rset.next())
            userName = rset.getString(1);
      } catch(Exception e){

      } finally {
         close(rset);
         close(pstmt);
      }
      return userName;
   }

   public String getProfileImage(Connection con, String userid) {
      String profileImage = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select profile_image from users where user_id = ?";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userid);

         rset = pstmt.executeQuery();

         if(rset.next())
            profileImage = rset.getString(1);
      } catch(Exception e){

      } finally {
         close(rset);
         close(pstmt);
      }
      return profileImage;
   }
   //비밀번호 찾기 
   public String searchPw(Connection con, String userId, String userEmail) {
      String userpw = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;

      String query = "select * from users "
            + "where user_id = ? and email = ? ";

      try{
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, userId);
         pstmt.setString(2, userEmail);

         rset = pstmt.executeQuery();

         if(rset.next()){
            userpw = rset.getString("user_pw");                  
         } 
      } catch(Exception e){
         e.printStackTrace();

      } finally {
         close(rset);
         close(pstmt);
      }
      return userpw;
   }




	//비밀번호 변경
	public String updatePass(Connection con, User user) throws UserException {
		String userpw = null;
		PreparedStatement pstmt = null;
		
		String query = "update users set user_pw = ? where user_id = ?";

      try {
    	  
    	  pstmt = con.prepareStatement(query);
    	  pstmt.setString(1, user.getUser_Pw());
    	  pstmt.setString(2, user.getUser_Id());


         int result = pstmt.executeUpdate();

         if(result > 0 ){
            userpw = user.getUser_Pw();
         }

      } catch (Exception e) {
         e.printStackTrace();

      } finally {   	 
         close(pstmt);
      }

      return userpw;
   }

   public int updateLogin(Connection con, String userid, String val) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String query = "update users set RESTRICTION = ? where user_id = ?";
      
      try {
         System.out.println(val + userid);
         pstmt = con.prepareStatement(query);         
         pstmt.setString(1, val);
         pstmt.setString(2, userid);
                  
         result = pstmt.executeUpdate();

         if(result <= 0)
            throw new UserException("로그인 허용/비허용 처리 실패");
      } catch (Exception e) {
         e.printStackTrace();
      }finally{
         close(pstmt);
      }
      
      return result;
   }

}