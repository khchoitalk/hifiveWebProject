package favorite.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import favorite.exception.FavoriteException;
import favorite.model.dao.FavoriteDao;
import favorite.model.vo.Favorite;
import user.model.vo.User;

public class FavoriteService {

	public ArrayList<Favorite> selectAll(String userId) {
		Connection con = getConnection();
		ArrayList<Favorite> list = new FavoriteDao().selectAll(con, userId);
		close(con);		
		return list;
	}
	
	// favorite등록했는지
	public int selectFavorite(String userId, String f_User_Id) {
		Connection con = getConnection();
		int result = new FavoriteDao().selectFavorite(con, userId, f_User_Id);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int deleteFavorite(String userId, String f_User_Id) throws FavoriteException {
		Connection con = getConnection();
		int result = new FavoriteDao().deleteFavorite(con, userId, f_User_Id);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int insertFavorite(String userId, String f_User_Id) throws FavoriteException {
		Connection con = getConnection();
		int result = new FavoriteDao().insertFavorite(con, userId, f_User_Id);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	public User selectFavoriteUser(String userId){
		Connection con = getConnection();
		User user = new FavoriteDao().selectFavoriteUser(con, userId);
		close(con);
		return user;
	}
}