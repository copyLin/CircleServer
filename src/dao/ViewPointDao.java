package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dbutils.JdbcUtils;
import vo.ViewPoint;

public class ViewPointDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addViewPoint(ViewPoint item){
		String sql = "insert into viewPoint(keyId, label, toId, content, userId, sendTime) values(?, ?, ?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getKeyId(), item.getLabel(), item.getToId(), item.getContent(), item.getUserId(), item.getSendTime()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<ViewPoint> getViewPoint(int keyId, String label){
		List<ViewPoint> list = new ArrayList<>();
		String sql = "select * from viewPoint where keyId = ? and label = ? order by id desc";
		try{
			list = (List<ViewPoint>) qr.query(sql, new BeanListHandler(ViewPoint.class), keyId, label);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ViewPoint> getUnreadPoint(int keyId){
		List<ViewPoint> list = new ArrayList<>();
		String sql = "select * from viewPoint where keyId = ? and flag = false";
		try{
			list = (List<ViewPoint>) qr.query(sql, new BeanListHandler(ViewPoint.class), keyId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ViewPoint> getMyPoint(int userId, int currentId){
		List<ViewPoint> list = new ArrayList<>();
		String sql = "select * from viewPoint where userId = ? and id < ? order by id desc limit 20";
		try{
			list = (List<ViewPoint>) qr.query(sql, new BeanListHandler(ViewPoint.class), userId, currentId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<ViewPoint> getUnreadViewPoint(int toId) {
		List<ViewPoint> list = new ArrayList<>();
		String sql = "select * from viewPoint where toId = ? and flag = false order by id desc";
		try{
			list = (List<ViewPoint>) qr.query(sql, new BeanListHandler(ViewPoint.class), toId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<ViewPoint> getMyPoint(int userId) {
		List<ViewPoint> list = new ArrayList<>();
		String sql = "select * from viewPoint where userId = ? order by id desc limit 20";
		try{
			list = (List<ViewPoint>) qr.query(sql, new BeanListHandler(ViewPoint.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void deleteViewPoint(int id) {
		String sql = "delete from viewPoint where id = ?";
		try{
			qr.update(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateFlag(int id){
		String sql = "update viewPoint set flag = true where id = ?";
		try{
			qr.update(sql, id);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
