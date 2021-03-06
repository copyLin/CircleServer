package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dbutils.JdbcUtils;
import vo.Delivery;

public class DeliveryDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addDelivery(Delivery item){
		String sql = "insert into delivery(userId, sendTime, content, price) values(?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getUserId(), item.getSendTime(), item.getContent(), item.getPrice()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Delivery> getDelivery(int currentId){
		List<Delivery> list = new ArrayList<>();
		String sql = "select * from delivery where id < ? and flag = false and reportNum < 20 order by id desc limit 20";
		try{
			list = (List<Delivery>) qr.query(sql, new BeanListHandler(Delivery.class), currentId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Delivery> getMyDelivery(int userId, int currentId){
		List<Delivery> list = new ArrayList<>();
		String sql = "select * from delivery where id < ? and userId = ? order by id desc limit 20";
		try{
			list = (List<Delivery>) qr.query(sql, new BeanListHandler(Delivery.class), currentId, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public String getDeliveryContent(int id) {
		String content = null;
		String sql = "select content from delivery where id = ?";
		try{
			content = (String) qr.query(sql, new ScalarHandler(), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return content;
	}

	public void deleteDelivery(int id) {
		String sql = "delete from delivery where id = ?";
		try{
			qr.update(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Delivery> getDelivery() {
		List<Delivery> list = new ArrayList<>();
		String sql = "select * from delivery where flag = false and reportNum < 20 order by id desc limit 20";
		try{
			list = (List<Delivery>) qr.query(sql, new BeanListHandler(Delivery.class));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Delivery> getMyDelivery(int userId) {
		List<Delivery> list = new ArrayList<>();
		String sql = "select * from delivery where userId = ? order by id desc limit 20";
		try{
			list = (List<Delivery>) qr.query(sql, new BeanListHandler(Delivery.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateFlag(int id, boolean flag) {
		String sql = "update delivery set flag = ? where id = ?";
		try{
			qr.update(sql, flag, id);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Delivery> searchDelivery(String text) {
		List<Delivery> list = new ArrayList<>();
		String sql = "select * from delivery where content like ? order by id desc";
        String content = "%" + text + "%";
        try {      
            list = (List<Delivery>) qr.query(sql, new BeanListHandler(Delivery.class), content); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

}
