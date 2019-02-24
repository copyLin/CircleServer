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
import vo.Idle;

public class IdleDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public void addIdle(Idle item){
		String sql = "insert into Idle(userId, sendTime, name, content, price) values(?, ?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getUserId(), item.getSendTime(), item.getName(), item.getContent(), item.getPrice()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Idle> getIdle(int currentId){
		List<Idle> list = new ArrayList<>();
		String sql = "select * from idle where id < ? and flag = false order by id desc limit 20";
		try{
			list = (List<Idle>) qr.query(sql, new BeanListHandler(Idle.class), currentId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Idle> getMyIdle(int userId, int currentId){
		List<Idle> list = new ArrayList<>();
		String sql = "select * from idle where id < ? and userId = ? order by id desc limit 20";
		try{
			list = (List<Idle>) qr.query(sql, new BeanListHandler(Idle.class), currentId, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public String getIdleContent(int id) {
		String content = null;
		String sql = "select content from idle where id = ?";
		try{
			content = (String) qr.query(sql, new ScalarHandler(), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return content;
	}

	public Idle getIdleById(int id) {
		Idle idle = null;
		String sql = "select * from idle where id = ?";
		try{
			idle = (Idle) qr.query(sql, new BeanHandler(Idle.class), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return idle;
	}

	public void deleteIdle(int userId, Date sendTime) {
		String sql = "delete from idle where userId = ? and sendTime = ?";
		try{
			qr.update(sql, userId, sendTime);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Idle> getMyIdle(int userId) {
		List<Idle> list = new ArrayList<>();
		String sql = "select * from idle where userId = ? order by id desc limit 20";
		try{
			list = (List<Idle>) qr.query(sql, new BeanListHandler(Idle.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Idle> getIdle() {
		List<Idle> list = new ArrayList<>();
		String sql = "select * from idle where flag = false order by id desc limit 20";
		try{
			list = (List<Idle>) qr.query(sql, new BeanListHandler(Idle.class));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void updateFlag(int userId, Date sendTime, boolean flag) {
		String sql = "update idle set flag = ? where userId = ? and sendTime = ?";
		try{
			qr.update(sql, flag, userId, sendTime);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Idle> searchIdle(String text) {
		List<Idle> list = new ArrayList<>();
		String sql = "select * from idle where name like ? order by id desc";
        String name = "%" + text + "%";
        try {      
            list = (List<Idle>) qr.query(sql, new BeanListHandler(Idle.class), name); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
}
