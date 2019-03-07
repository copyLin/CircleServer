package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IdleDao;
import dao.LostDao;
import dao.QuestionDao;
import dao.ReportRecordDao;
import vo.ReportRecord;

/**
 * Servlet implementation class NewReportServlet
 */
@WebServlet("/newReportServlet")
public class NewReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String keyId = request.getParameter("keyId");
		String label = request.getParameter("label");
		String reason = request.getParameter("reason");
		String userId = request.getParameter("userId");
		
		ReportRecordDao reportRecordDao = new ReportRecordDao();
		
		ReportRecord reportRecord = new ReportRecord(0, Integer.valueOf(keyId), label, reason, Integer.valueOf(userId));
		reportRecordDao.addReportRecord(reportRecord);
		
		switch(label){
		case "Question":
			QuestionDao questionDao = new QuestionDao();
			questionDao.updateReportNum(Integer.valueOf(keyId));
			break;
		case "Lost":
			LostDao lostDao = new LostDao();
			lostDao.updateReportNum(Integer.valueOf(keyId));
			break;
		case "Idle":
			IdleDao idleDao = new IdleDao();
			idleDao.updateReportNum(Integer.valueOf(keyId));
			break;
		default:
			break;
		}
		
		System.out.println(reportRecord);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
