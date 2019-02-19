package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dbutils.JdbcUtils;
import vo.Collection;

public class CollectionDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public void addCollection(Collection item){
		String sql = "insert into collection(name, userId, collectionTime, keyId, label) values(?, ?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getName(), item.getUserId(), item.getCollectionTime(), item.getKeyId(), item.getLabel()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<Collection> getMyCollection(int userId, int currentId){
		List<Collection> list = new ArrayList<>();
		String sql = "select * from collection where userId = ? and id < ? order by id desc limit 20";
		try{
			list = (List<Collection>) qr.query(sql, new BeanListHandler(Collection.class), userId, currentId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Collection> getMyCollection(int userId) {
		List<Collection> list = new ArrayList<>();
		String sql = "select * from collection where userId = ? order by id desc limit 20";
		try{
			list = (List<Collection>) qr.query(sql, new BeanListHandler(Collection.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void deleteCollection(int id) {
		String sql = "delete from collection where id = ?";
		try{
			qr.update(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
