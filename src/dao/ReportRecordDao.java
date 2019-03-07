package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

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

}
