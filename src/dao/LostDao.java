package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dbutils.JdbcUtils;
import vo.Lost;

public class LostDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addLost(Lost item){
		String sql = "insert into lost(userId, sendTime, content, eventTime, location, contact) values(?, ?, ?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getUserId(), item.getSendTime(), item.getContent(), item.getEventTime(), item.getLocation(), item.getContact()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Lost> getLost(int currentId){
		List<Lost> list = new ArrayList<>();
		String sql = "select * from lost where id < ? and flag = false and reportNum < 20 order by id desc limit 20";
		try{
			list = (List<Lost>) qr.query(sql, new BeanListHandler(Lost.class), currentId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Lost> getMyLost(int userId, int currentId){
		List<Lost> list = new ArrayList<>();
		String sql = "select * from lost where id < ? and userId = ? order by id desc limit 20";
		try{
			list = (List<Lost>) qr.query(sql, new BeanListHandler(Lost.class), currentId, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public String getLostContent(int id) {
		String content = null;
		String sql = "select content from lost where id = ?";
		try{
			content = (String) qr.query(sql, new ScalarHandler(), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return content;
	}

	public Lost getLostById(int id) {
		Lost lost = null;
		String sql = "select * from lost where id = ?";
		try{
			lost = (Lost) qr.query(sql, new BeanHandler(Lost.class), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lost;
	}

	public void deleteLost(int id) {
		String sql = "delete from lost where id = ?";
		try{
			qr.update(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Lost> getMyLost(int userId) {
		List<Lost> list = new ArrayList<>();
		String sql = "select * from lost where userId = ? order by id desc limit 20";
		try{
			list = (List<Lost>) qr.query(sql, new BeanListHandler(Lost.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Lost> getLost() {
		List<Lost> list = new ArrayList<>();
		String sql = "select * from lost where flag = false and reportNum < 20 order by id desc limit 20";
		try{
			list = (List<Lost>) qr.query(sql, new BeanListHandler(Lost.class));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateFlag(int id, boolean flag) {
		String sql = "update lost set flag = ? where id = ?";
		try{
			qr.update(sql, flag, id);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Lost> searchLost(String text) {
		List<Lost> list = new ArrayList<>();
		String sql = "select * from lost where content like ? order by id desc";
        String content = "%" + text + "%";
        try {      
            list = (List<Lost>) qr.query(sql, new BeanListHandler(Lost.class), content); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	public void updateReportNum(int id) {
		String sql = "update lost set reportNum = reportNum + 1 where id = ?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
