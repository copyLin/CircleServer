package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dbutils.JdbcUtils;
import vo.ReportRecord;

public class ReportRecordDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void addReportRecord(ReportRecord item){
		String sql = "insert into reportRecord(keyId, label, reason, userId) values(?, ?, ?, ?)";
		try{
			qr.update(sql, new Object[]{item.getKeyId(), item.getLabel(), item.getReason(), item.getUserId()});
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public List<ReportRecord> getReportRecord(int keyId, String label, int userId){
		List<ReportRecord> list = new ArrayList<>(); 
		String sql = "select * from reportRecord where keyId = ? and label = ? and userId = ?";
		try{
			list = (List<ReportRecord>) qr.query(sql, new BeanListHandler(ReportRecord.class), keyId, label, userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
}
