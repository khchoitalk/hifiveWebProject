package favorite.model.dao;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import favorite.exception.FavoriteException;
import favorite.model.vo.Favorite;
import user.model.vo.User;

public class FavoriteDao {

	public ArrayList<Favorite> selectAll(Connection con, String userId) {
		ArrayList<Favorite> list = new ArrayList<Favorite>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from favorite where user_id = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Favorite favorite = new Favorite();
				favorite.setFavorite_no(rset.getInt("favorite_no"));
				favorite.setUser_id(rset.getString("user_id"));
				favorite.setF_user_id(rset.getString("f_user_id"));
				list.add(favorite);
			}
		} catch(Exception e){
			
		} finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int deleteFavorite(Connection con, String userId, String f_User_Id) throws FavoriteException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from favorite where user_id=? and f_user_id = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, f_User_Id);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new FavoriteException("취소 실패");
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int insertFavorite(Connection con, String userId, String f_User_Id) throws FavoriteException {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into favorite values (favorite_seq.nextval, ?, ?)";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, f_User_Id);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new FavoriteException("등록 실패");
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public int selectFavorite(Connection con, String userId, String f_User_Id) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "select * from favorite where user_id=? and f_user_id = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, f_User_Id);
			
			result = pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		return result;
	}

	public User selectFavoriteUser(Connection con, String userId) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select user_name, address, nationality, PROFILE_IMAGE from users where user_id = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				user = new User();
				user.setUser_Name(rset.getString(1));
				user.setAddress(rset.getString(2));
				user.setNationality(rset.getString(3));
				user.setProfile_image(rset.getString(4));
			}
		} catch(Exception e){
			
		} finally {
			close(rset);
			close(pstmt);
		}		
		return user;
	}

}
