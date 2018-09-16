package request.model.dao;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import request.exception.RequestException;
import request.model.vo.Request;

public class RequestDao {
	public RequestDao() {
	}

	// 내(서퍼)가 호스트에게 요청한 리스트 (4)
	public ArrayList<Request> myHostList1(Connection con, String userId) {
		ArrayList<Request> list = new ArrayList<Request>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from request where user_id=? and role='S' and process='P'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Request r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));

				list.add(r);
			}
		} catch (Exception e) {

		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 나(호스트)에게 요청한 유저(서퍼)리스트 (1)
	public ArrayList<Request> myHostList2(Connection con, String userId) {
		ArrayList<Request> list = new ArrayList<Request>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from request where r_user_id=? and role='S' and process='P'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Request r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));

				list.add(r);
			}
		} catch (Exception e) {

		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 내(호스트)가 서퍼에게 요청한 리스트 (2)
	public ArrayList<Request> mySurferList1(Connection con, String userId) {
		ArrayList<Request> list = new ArrayList<Request>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from request where user_id=? and role='H'and process='P'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Request r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));

				list.add(r);
			}
		} catch (Exception e) {

		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 나(서퍼)에게 요청한 유저(호스트)리스트 (3)
	public ArrayList<Request> mySurferList2(Connection con, String userId) {
		ArrayList<Request> list = new ArrayList<Request>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from request where r_user_id=? and role='H' and process='P'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Request r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));

				list.add(r);
			}
		} catch (Exception e) {

		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 내가 파트너 요청한 리스트
	public ArrayList<Request> myPartnerList1(Connection con, String userId) {
		ArrayList<Request> list = new ArrayList<Request>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from request where user_id=? and role='P' and process='P'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Request r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));

				list.add(r);
			}
		} catch (Exception e) {

		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 상대방이 나에게 파트너 요청
	public ArrayList<Request> myPartnerList2(Connection con, String userId) {
		ArrayList<Request> list = new ArrayList<Request>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from request where r_user_id=? and role='P' and process='P'";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Request r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));

				list.add(r);
			}
		} catch (Exception e) {

		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 내가 한 요청 취소했을 때
	public int deleteRequest(Connection con, int request_no) throws RequestException {
		int result = 0;
	    PreparedStatement pstmt = null;
	      
	    String query = "delete from request where request_no=?";
	    
	    try{
	       pstmt = con.prepareStatement(query);
	       pstmt.setInt(1, request_no);	       
	       
	       result = pstmt.executeUpdate();
	       
	       if(result <= 0)
	    	   throw new RequestException("요청 취소 실패");
	    } catch(Exception e){
	        
	    } finally{
	        close(pstmt);
	    }
	    return result;		   
	}
	// 내가 받은 요청 거절
	public int refuseRequest(Connection con, int request_no) throws RequestException {
		int result = 0;
	    PreparedStatement pstmt = null;
	      
	    String query = "update request set process='N' where request_no = ?";
	    
	    try{
	       pstmt = con.prepareStatement(query);
	       pstmt.setInt(1, request_no);	       
	       
	       result = pstmt.executeUpdate();
	       
	       if(result <= 0)
	    	   throw new RequestException("요청 취소 실패");
	    } catch(Exception e){
	        e.printStackTrace();
	    } finally{
	        close(pstmt);
	    }
	    return result;		   
	}
	
	// 내가 받은 요청 수락
	public int acceptRequest(Connection con, int request_no) throws RequestException {
		int result = 0;
	    PreparedStatement pstmt = null;

	    String query = "update request set process='M' where request_no = ?";

	    try{
	    	pstmt = con.prepareStatement(query);	
	    	pstmt.setInt(1, request_no);      

	    	result = pstmt.executeUpdate();

	    	if(result <= 0)
	    		throw new RequestException("요청 취소 실패");
	    } catch(Exception e){
		       
	    } finally{
	    	close(pstmt);
	    }
	    return result;			   
	}
	
	// 요청 등록
	public int insertRequest(Connection con, String loginid, String loginrole, String profileid) throws RequestException {
		int result = 0;
	    PreparedStatement pstmt = null;

	    String query = "insert into request values "
	    		+ "(REQNO_SEQ.nextval, ?, ?, sysdate, ?, default)";

	    try{
	    	pstmt = con.prepareStatement(query);	
	    	pstmt.setString(1, loginid);   
	    	pstmt.setString(2, profileid);
	    	pstmt.setString(3, loginrole);  

	    	result = pstmt.executeUpdate();

	    	if(result <= 0)
	    		throw new RequestException("요청 취소 실패");
	    } catch(Exception e){
		       
	    } finally{
	    	close(pstmt);
	    }
	    return result;	
	}
	
	// 내가 신청한 사람인지 확인
	public Request checkRequest(Connection con, String loginid, String loginrole, String profileid) throws RequestException {
		Request r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;


	    String query = "select * from request "
	    		+ "where user_id = ? and r_user_id = ? and role = ? and process='P'";

	    try{
	    	pstmt = con.prepareStatement(query);
	    	pstmt.setString(1, loginid);   
	    	pstmt.setString(2, profileid);
	    	pstmt.setString(3, loginrole);  

			rset = pstmt.executeQuery();

			if(rset.next()) {
				r = new Request();
				r.setRequest_no(rset.getInt("request_no"));
				r.setUser_id(rset.getString("user_id"));
				r.setR_user_id(rset.getString("r_user_id"));
				r.setRequest_date(rset.getDate("request_date"));
				r.setRole(rset.getString("role"));
				r.setProcess(rset.getString("process"));
			}
	    } catch(Exception e){
		       
	    } finally{
	    	close(rset);
	    	close(pstmt);
	    }
	    return r;
	}
	
	// 요청 종류
	public String checkRole(Connection con, int request_no) {
		String role = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

	    String query = "select role from request where request_no = ?";

	    try{
	    	pstmt = con.prepareStatement(query);
	    	pstmt.setInt(1, request_no); 

			rset = pstmt.executeQuery();
			
			if(rset.next())
				role = rset.getString(1);
			
	    } catch(Exception e){
		       
	    } finally{
	    	close(rset);
	    	close(pstmt);
	    }
		return role;
	}

	public String selectUser_Id(Connection con, int request_no) {
		String user_id = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

	    String query = "select user_id from request where request_no =?";

	    try{
	    	pstmt = con.prepareStatement(query);
	    	pstmt.setInt(1, request_no); 

			rset = pstmt.executeQuery();
			
			if(rset.next())
				user_id = rset.getString(1);
	    } catch(Exception e){
		       
	    } finally{
	    	close(rset);
	    	close(pstmt);
	    }
		return user_id;
	}

	public String selectR_User_Id(Connection con, int request_no) {
		String r_user_id = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

	    String query = "select r_user_id from request where request_no =?";

	    try{
	    	pstmt = con.prepareStatement(query);
	    	pstmt.setInt(1, request_no); 

			rset = pstmt.executeQuery();
			
			if(rset.next())
				r_user_id = rset.getString(1);
	    } catch(Exception e){
		       
	    } finally{
	    	close(rset);
	    	close(pstmt);
	    }
		return r_user_id;
	}
}