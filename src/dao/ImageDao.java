package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import dbutils.JdbcUtils;
import vo.Image;

public class ImageDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addImage(Image item){
		String sql = "insert into image(name, userId, sendTime) values(?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getName(), item.getUserId(), item.getSendTime()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<String> getImageName(int userId, Date sendTime){
		List<String> list = new ArrayList<>();
		String sql = "select name from image where userId = ? and sendTime = ?";
		try{
			list = (List<String>) qr.query(sql, new ColumnListHandler(), userId, sendTime);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void deleteImage(int userId, Date sendTime) {
		String sql = "delete from image where userId = ? and sendTime = ?";
		try{
			qr.update(sql, userId, sendTime);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
