package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dbutils.JdbcUtils;
import vo.ChatRecord;

public class ChatRecordDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addChatRecord(ChatRecord item){
		String sql = "insert into chatRecord(content, fromId, toId, sendTime) values(?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getContent(), item.getFromId(), item.getToId(), item.getSendTime()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<ChatRecord> getChatRecord(int fromId, int toId, int currentId){
		List<ChatRecord> list = new ArrayList<>();
		String sql = "select * from chatRecord where fromId in (?, ?) and toId in (?, ?) and id < ? order by id desc limit 20";
		try{
			list = (List<ChatRecord>) qr.query(sql, new BeanListHandler(ChatRecord.class), new Object[]{fromId, toId, fromId, toId, currentId});
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<ChatRecord> getUnreadChatRecord(int userId){
		List<ChatRecord> list = new ArrayList<>();
		String sql = "select * from chatRecord where toId = ? and flag = false order by id desc";
		try{
			list = (List<ChatRecord>) qr.query(sql, new BeanListHandler(ChatRecord.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void updateFlag(int id){
		String sql = "update chatRecord set flag = true where id = ?";
		try{
			qr.update(sql, id);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<ChatRecord> getRecentChat(int userId) {
		List<ChatRecord> list = new ArrayList<>();
		String sql = "select * from chatRecord where id in(select max(id) from chatRecord where toId = ? and state = true group by fromId)  order by id desc";
		try{
			list = (List<ChatRecord>) qr.query(sql, new BeanListHandler(ChatRecord.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<ChatRecord> getChatRecord(int fromId, int toId) {
		List<ChatRecord> list = new ArrayList<>();
		String sql = "select * from chatRecord where fromId in (?, ?) and toId in (?, ?) order by id desc limit 20";
		try{
			list = (List<ChatRecord>) qr.query(sql, new BeanListHandler(ChatRecord.class), new Object[]{fromId, toId, fromId, toId});
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public void updateState(int contactId, int userId) {
		String sql = "update chatRecord set state = false where fromId = ? and toId = ?";
		try{
			qr.update(sql, contactId, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
