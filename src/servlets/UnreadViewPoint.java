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
 * Servlet implementation class UnreadViewPoint
 */
@WebServlet("/unreadViewPoint")
public class UnreadViewPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnreadViewPoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String toId = request.getParameter("toId");
		
		ViewPointDao viewPointDao = new ViewPointDao();
		UserDao userDao = new UserDao();
		
		List<ViewPoint> list = viewPointDao.getUnreadViewPoint(Integer.valueOf(toId));
		List<ViewPointItem> items = new ArrayList<>();
		if(!list.isEmpty()){
			for(ViewPoint viewPoint : list){
				String viewPointId = String.valueOf(viewPoint.getId());
				String userId = String.valueOf(viewPoint.getUserId());
				String userImg = userDao.getUserImg(viewPoint.getUserId());
				String userName = userDao.getUserName(viewPoint.getUserId());
				String content = viewPoint.getContent();
				String sendTime = String.valueOf(viewPoint.getSendTime());
				String tip = null;
				String keyId = String.valueOf(viewPoint.getKeyId());
				String label = viewPoint.getLabel();
				
				viewPointDao.updateFlag(viewPoint.getId());
				
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
			    	tip = "未找到该内容，可能已被删除";
			    }
				
				ViewPointItem item = new ViewPointItem(viewPointId, userId, userImg, userName, content, sendTime, tip, keyId, label);
			    items.add(item);
			}
			String jsonString = JSONArray.fromObject(items).toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(jsonString);
		}else {
			response.getWriter().append("NoData");
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
