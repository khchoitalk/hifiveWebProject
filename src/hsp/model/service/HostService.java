package hsp.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import hsp.exception.HostException;
import hsp.model.dao.HostDao;
import hsp.model.dao.SurferPartnerDao;
import hsp.model.vo.Host;
import user.model.vo.User;

public class HostService {

	public HostService(){}	

	public Host selectHost(String userId){

		Connection con = getConnection();
		Host host = new HostDao().selectHost(con, userId);
		close(con);
		return host;
	}


	public int updateHost(Host host) throws HostException{
		Connection con = getConnection();
		int result = new HostDao().updateHost(con, host);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}


	public int insertHost(Host host) throws HostException {
		Connection con = getConnection();
		int result = new HostDao().insertHost(con, host);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}


	public int updatePhoto(String renameFileName1, String renameFileName2, String renameFileName3, String userid) throws HostException {
		Connection con = getConnection();
		int result = new HostDao().updatePhoto(con, renameFileName1, renameFileName2, renameFileName3, userid);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}


	// 매칭 된 호스트
	public Host selectMHost(String userId){

		Connection con = getConnection();
		Host host = new HostDao().selectMHost(con, userId);
		close(con);
		return host;
	}

	public ArrayList<User> searchHost(Host host) {
		Connection con = getConnection();
		ArrayList<User> list = new HostDao().searchHost(con, host);
		close(con);
		return list;
	}
	
	// process 수정
	public int updateProcess (String user_id) {
		Connection con = getConnection();
		int result = new HostDao().updateProcess(con, user_id);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}


}
