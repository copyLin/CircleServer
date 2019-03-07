package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.ViewPointItem;

import dao.DeliveryDao;
import dao.IdleDao;
import dao.LostDao;
import dao.QuestionDao;
import dao.UserDao;
import dao.ViewPointDao;
import net.sf.json.JSONArray;
import vo.ViewPoint;

/**
 * Servlet implementation class MyViewPointServlet
 */
@WebServlet("/myViewPointServlet")
public class MyViewPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyViewPointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String currentId = request.getParameter("currentId");
		
		ViewPointDao viewPointDao = new ViewPointDao();
		UserDao userDao = new UserDao();
		
		List<ViewPointItem> items = new ArrayList<>();
		List<ViewPoint> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = viewPointDao.getMyPoint(Integer.valueOf(userId));
		}else {
			list = viewPointDao.getMyPoint(Integer.valueOf(userId), Integer.valueOf(currentId));
		}
		
		if(!list.isEmpty()){
			for(ViewPoint viewPoint : list){
				String viewPointId = String.valueOf(viewPoint.getId());
			    String userImg = userDao.getUserImg(viewPoint.getUserId());
			    String userName = userDao.getUserName(viewPoint.getUserId());
			    String content = viewPoint.getContent();
			    String sendTime = String.valueOf(viewPoint.getSendTime());
			    String keyId = String.valueOf(viewPoint.getKeyId());
			    String label = viewPoint.getLabel();
			    String tip = null;
			    switch(viewPoint.getLabel()){
			    	case "Question":
			    		QuestionDao questionDao = new QuestionDao();
			    		tip = questionDao.getQuestionContent(viewPoint.getKeyId());
			    		break;
			    	case "Lost":
			    		LostDao lostDao = new LostDao();
			    		tip = lostDao.getLostContent(viewPoint.getKeyId());
			    		break;
			    	case "Idle":
			    		IdleDao idleDao = new IdleDao();
			    		tip = idleDao.getIdleContent(viewPoint.getKeyId());
			    		break;
			    	case "Delivery":
			    		DeliveryDao deliveryDao = new DeliveryDao();
			    		tip = deliveryDao.getDeliveryContent(viewPoint.getKeyId());
			    		break;
			    	default:
			    		break;
			    }
			    
			    if(tip == null){
			    	tip = "未找到该内容，或已被删除";
			    }
			    
			    ViewPointItem item = new ViewPointItem(viewPointId, userId, userImg, userName, content, sendTime, tip, keyId, label);
			    items.add(item);
			}
			String jsonString = JSONArray.fromObject(items).toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(jsonString);
		}else {
			response.getWriter().append("NoMoreData");
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
