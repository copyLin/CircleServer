package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.IdleItem;
import com.example.linxl.circle.gson.LostItem;
import com.example.linxl.circle.gson.QuestionItem;
import com.example.linxl.circle.gson.UserItem;

import dao.IdleDao;
import dao.ImageDao;
import dao.LostDao;
import dao.QuestionDao;
import dao.UserDao;
import net.sf.json.JSONObject;
import vo.Idle;
import vo.Lost;
import vo.Question;
import vo.User;

/**
 * Servlet implementation class ItemDetailServlet
 */
@WebServlet("/itemDetailServlet")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailServlet() {
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
		
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		
		switch (label){
			case "Question":
				QuestionDao questionDao = new QuestionDao();
				Question question = questionDao.getQuestionById(Integer.valueOf(keyId));
				
				String questionId = String.valueOf(question.getId());
				String qUserImg = userDao.getUserImg(question.getUserId());
				String qUserName = userDao.getUserName(question.getUserId());
				String qUserId = String.valueOf(question.getUserId());
				String qSendTime = String.valueOf(question.getSendTime());
				String qContent = question.getContent();
				boolean qFlag = question.isFlag();
				int qReportNum = question.getReportNum();
				List<String> qImages = imageDao.getImageName(question.getUserId(), question.getSendTime());
			
				QuestionItem questionItem = new QuestionItem(questionId, qUserImg, qUserName, qUserId, qSendTime, qContent, qFlag, qReportNum, qImages);
				
				String jsonString1 = JSONObject.fromObject(questionItem).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString1);
				break;
			case "Lost":
				LostDao lostDao = new LostDao();
				Lost lost = lostDao.getLostById(Integer.valueOf(keyId));
				
				String lostId = String.valueOf(lost.getId());
			    String lUserImg = userDao.getUserImg(lost.getUserId());
			    String lUserName = userDao.getUserName(lost.getUserId());
			    String lUserId = String.valueOf(lost.getUserId());
			    String lSendTime = String.valueOf(lost.getSendTime());
			    String lContent = lost.getContent();
			    String eventTime = String.valueOf(lost.getEventTime());
			    String location = lost.getLocation();
			    String contact = lost.getContact();
			    boolean lFlag = lost.isFlag();
			    int lReportNum = lost.getReportNum();
			    List<String> lImages = imageDao.getImageName(lost.getUserId(), lost.getSendTime());
			    
			    LostItem lostItem = new LostItem(lostId, lUserImg, lUserName, lUserId, lSendTime, lContent, eventTime, location, contact, lFlag, lReportNum, lImages);
				
				String jsonString2 = JSONObject.fromObject(lostItem).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString2);
				break;
			case "Idle":
				IdleDao idleDao = new IdleDao();
				Idle idle = idleDao.getIdleById(Integer.valueOf(keyId));
				
				String idleId = String.valueOf(idle.getId());
			    String iUserImg = userDao.getUserImg(idle.getUserId());
			    String iUserName = userDao.getUserName(idle.getUserId());
			    String iUserId = String.valueOf(idle.getUserId());
			    String iSendTime = String.valueOf(idle.getSendTime());
			    String iContent = idle.getContent();
			    String idleName = idle.getName();
			    String price = String.valueOf(idle.getPrice());
			    boolean iFlag = idle.isFlag();
			    int iReportNum = idle.getReportNum();
			    List<String> iImages = imageDao.getImageName(idle.getUserId(), idle.getSendTime());
			    
			    IdleItem idleItem = new IdleItem(idleId, iUserImg, iUserName, iUserId, iSendTime, iContent, idleName, price, iFlag, iReportNum, iImages);
				
				String jsonString3 = JSONObject.fromObject(idleItem).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString3);
				break;
			case "User":
				User user = userDao.getUser(Integer.valueOf(keyId));
				UserItem userItem = new UserItem(String.valueOf(user.getId()), String.valueOf(user.getName()), String.valueOf(user.getImage()), String.valueOf(user.getDepartment()), String.valueOf(user.getMajor()), String.valueOf(user.getWords()));
				String jsonString4 = JSONObject.fromObject(userItem).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString4);
				break;
			default:
				response.getWriter().append("NoData");
				break;
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
