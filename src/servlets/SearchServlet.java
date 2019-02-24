package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.DeliveryItem;
import com.example.linxl.circle.gson.IdleItem;
import com.example.linxl.circle.gson.LostItem;
import com.example.linxl.circle.gson.QuestionItem;
import com.example.linxl.circle.gson.UserItem;

import dao.DeliveryDao;
import dao.IdleDao;
import dao.ImageDao;
import dao.LostDao;
import dao.QuestionDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.Delivery;
import vo.Idle;
import vo.Lost;
import vo.Question;
import vo.User;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String label = request.getParameter("label");
		String text = request.getParameter("text");
		
		System.out.println("SearchServlet:" + label + text);
		
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		
		switch (label){
		case "校园话题":
			QuestionDao questionDao = new QuestionDao();
			
			List<Question> questionList = new ArrayList<>();
			List<QuestionItem> questionItems = new ArrayList<>();
			
			questionList = questionDao.searchQuestion(text);
			
			if(!questionList.isEmpty()){
				for(Question question : questionList){
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
					questionItems.add(questionItem);
				}
				String jsonString1 = JSONArray.fromObject(questionItems).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString1);
			}else{
				response.getWriter().append("NoData");
			}
			break;
		case "寻物启事":
			LostDao lostDao = new LostDao();
			
			List<Lost> lostList = new ArrayList<>();
			List<LostItem> lostItems = new ArrayList<>();

			lostList = lostDao.searchLost(text);

			if(!lostList.isEmpty()){
				for(Lost lost : lostList){
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
					lostItems.add(lostItem);
				}
				String jsonString = JSONArray.fromObject(lostItems).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString);
			}else{
				response.getWriter().append("NoData");
			}
			break;
		case "闲置物品":
			IdleDao idleDao = new IdleDao();
			
			List<Idle> idleList = new ArrayList<>();
			List<IdleItem> idleItems = new ArrayList<>();

			idleList = idleDao.searchIdle(text);

			if(!idleList.isEmpty()){
				for(Idle idle : idleList){
					String idleId = String.valueOf(idle.getId());
				    String iUserImg = userDao.getUserImg(idle.getUserId());
				    String iUserName = userDao.getUserName(idle.getUserId());
				    String iUserId = String.valueOf(idle.getUserId());
				    String iSendTime = String.valueOf(idle.getSendTime());
				    String iContent = idle.getContent();
				    String idleName = idle.getName();
				    String iPrice = String.valueOf(idle.getPrice());
				    boolean iFlag = idle.isFlag();
				    int iReportNum = idle.getReportNum();
				    List<String> iImages = imageDao.getImageName(idle.getUserId(), idle.getSendTime());
				    
				    IdleItem idleItem = new IdleItem(idleId, iUserImg, iUserName, iUserId, iSendTime, iContent, idleName, iPrice, iFlag, iReportNum, iImages);
					idleItems.add(idleItem);
				}
				String jsonString = JSONArray.fromObject(idleItems).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString);
			}else{
				response.getWriter().append("NoData");
			}
			break;
		case "校园跑腿":
			DeliveryDao deliveryDao = new DeliveryDao();
			
			List<Delivery> deliveryList = new ArrayList<>();
			List<DeliveryItem> deliveryItems = new ArrayList<>();
			
			deliveryList = deliveryDao.searchDelivery(text);
			
			if(!deliveryList.isEmpty()){
				for(Delivery delivery : deliveryList){
					String deliveryId = String.valueOf(delivery.getId());
				    String dUserImg = userDao.getUserImg(delivery.getUserId());
				    String dUserName = userDao.getUserName(delivery.getUserId());
				    String dUserId = String.valueOf(delivery.getUserId());
				    String dSendTime = String.valueOf(delivery.getSendTime());
				    String dContent = delivery.getContent();
				    String dPrice = String.valueOf(delivery.getPrice());
				    boolean dFlag = delivery.isFlag();
				    int dReportNum = delivery.getReportNum();
				    
				    DeliveryItem item = new DeliveryItem(deliveryId, dUserImg, dUserName, dUserId, dSendTime, dContent, dPrice, dFlag, dReportNum);
				    deliveryItems.add(item);
				}
				String jsonString = JSONArray.fromObject(deliveryItems).toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(jsonString);
			}else{
				response.getWriter().append("NoData");
			}
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