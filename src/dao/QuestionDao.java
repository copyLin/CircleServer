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
import vo.Question;

public class QuestionDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addQuestion(Question item){
		String sql = "insert into question(userId, sendTime, content) values(?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getUserId(), item.getSendTime(), item.getContent()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Question> getQuestion(int currentId){
		List<Question> list = new ArrayList<>();
		String sql = "select * from question where id < ? and flag = false and reportNum < 20 order by id desc limit 20";
		try{
			list = (List<Question>) qr.query(sql, new BeanListHandler(Question.class), currentId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Question> getMyQuestion(int userId, int currentId){
		List<Question> list = new ArrayList<>();
		String sql = "select * from question where id < ? and userId = ? order by id desc limit 20";
		try{
			list = (List<Question>) qr.query(sql, new BeanListHandler(Question.class), currentId, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public String getQuestionContent(int id) {
		String content = null;
		String sql = "select content from question where id = ?";
		try{
			content = (String) qr.query(sql, new ScalarHandler(), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return content;
	}

	public Question getQuestionById(int id) {
		Question question = null;
		String sql = "select * from question where id = ?";
		try{
			question = (Question) qr.query(sql, new BeanHandler(Question.class), id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return question;
	}

	public void deleteQuestion(int id) {
		String sql = "delete from question where id = ?";
		try{
			qr.update(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<Question> getMyQuestion(int userId) {
		List<Question> list = new ArrayList<>();
		String sql = "select * from question where userId = ? order by id desc limit 20";
		try{
			list = (List<Question>) qr.query(sql, new BeanListHandler(Question.class), userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Question> getQuestion() {
		List<Question> list = new ArrayList<>();
		String sql = "select * from question where flag = false and reportNum < 20 order by id desc limit 20";
		try{
			list = (List<Question>) qr.query(sql, new BeanListHandler(Question.class));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateFlag(int id, boolean flag) {
		String sql = "update question set flag = ? where id = ?";
		try{
			qr.update(sql, flag, id);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<Question> searchQuestion(String text) {
		List<Question> list = new ArrayList<>();
		String sql = "select * from question where content like ? order by id desc";
        String content = "%"+text+"%";
        try {      
            list = (List<Question>) qr.query(sql, new BeanListHandler(Question.class), content); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}

	public void updateReportNum(int id) {
		String sql = "update question set reportNum = reportNum + 1 where id = ?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
