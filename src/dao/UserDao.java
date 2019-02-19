package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dbutils.JdbcUtils;
import vo.User;

public class UserDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public User getUser(int userId){
		User user = null;
		String sql = "select * from user where id = ?";
		try{
			user = (User) qr.query(sql, new BeanHandler(User.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public String getUserName(int userId){
		String userName = null;
		String sql = "select name from user where id = ?";
		try{
			userName = (String) qr.query(sql, new ScalarHandler(), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userName;
	}
	
	public String getUserImg(int userId){
		String userImg = null;
		String sql = "select image from user where id = ?";
		try{
			userImg = (String) qr.query(sql, new ScalarHandler(), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userImg;
	}

	public void updatePassword(String password, int userId) {
		String sql = "update user set password = ? where id = ?";
		try{
			qr.update(sql, password, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
