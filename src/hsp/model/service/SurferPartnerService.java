package hsp.model.service;


import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;
import java.sql.Connection;
import java.util.ArrayList;

import hsp.exception.SurferPartnerException;
import hsp.model.dao.SurferPartnerDao;
import hsp.model.vo.SurferPartner;
import user.model.dao.UserDao;
import user.model.vo.User;

public class SurferPartnerService {
	

	public SurferPartnerService(){}
	

	public SurferPartner selectSurfer(String userId){

		Connection con = getConnection();
		SurferPartner sp = new SurferPartnerDao().selectSurfer(con, userId);
		close(con);
		return sp;
	}
	

	public SurferPartner selectPartner(String userId) {

		Connection con = getConnection();
		SurferPartner sp = new SurferPartnerDao().selectPartner(con, userId);
		close(con);
		return sp;
	}


	public int updateSurfer(SurferPartner sp) throws SurferPartnerException{
		Connection con = getConnection();
		int result = new SurferPartnerDao().updateSurfer(con, sp);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int updatePartner(SurferPartner sp) throws SurferPartnerException{
		Connection con = getConnection();
		int result = new SurferPartnerDao().updatePartner(con, sp);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}


	public int insertSurfer(SurferPartner sp) throws SurferPartnerException{
		Connection con = getConnection();
		int result = new SurferPartnerDao().insertSurfer(con, sp);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int insertPartner(SurferPartner sp) throws SurferPartnerException{
		Connection con = getConnection();
		int result = new SurferPartnerDao().insertPartner(con, sp);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public ArrayList<User> searchSP(SurferPartner sp, char ch){
		Connection con = getConnection();
		ArrayList<User> list = new SurferPartnerDao().searchSP(con, sp, ch);
		close(con);
		return list;
	}
	
	// 매칭 된 서퍼 
	public SurferPartner selectMSurfer(String userId){ 

		Connection con = getConnection();
		SurferPartner sp = new SurferPartnerDao().selectMSurfer(con, userId);
		close(con);
		return sp;
	}
	
	// 매칭 된 파트너
	public SurferPartner selectMPartner(String userId) {

		Connection con = getConnection();
		SurferPartner sp = new SurferPartnerDao().selectMPartner(con, userId);
		close(con);
		return sp;
	}

	// 서퍼 process 수정
	public int updateSurferProcess(String user_id) {
		Connection con = getConnection();
		int result = new SurferPartnerDao().updateSurferProcess(con, user_id);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	// 파트너 process 수정
	public int updatePartnerProcess(String user_id) {
		Connection con = getConnection();
		int result = new SurferPartnerDao().updatePartnerProcess(con, user_id);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	

}
