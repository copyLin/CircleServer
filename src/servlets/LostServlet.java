package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.LostItem;

import dao.ImageDao;
import dao.LostDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import vo.Lost;

/**
 * Servlet implementation class LostServlet
 */
@WebServlet("/lostServlet")
public class LostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String currentId = request.getParameter("currentId");
		
		//System.out.println("LostServlet_currentId: " + currentId);
		
		LostDao lostDao = new LostDao();
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		
		List<LostItem> items = new ArrayList<>();
		List<Lost> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = lostDao.getLost();
		}else {
			list = lostDao.getLost(Integer.parseInt(currentId));
		}
		
		//System.out.println("LostServlet_items: " + list);
		
		if(!list.isEmpty()){
			for(Lost lost : list){
				String lostId = String.valueOf(lost.getId());
			    String userImg = userDao.getUserImg(lost.getUserId());
			    String userName = userDao.getUserName(lost.getUserId());
			    String userId = String.valueOf(lost.getUserId());
			    String sendTime = String.valueOf(lost.getSendTime());
			    String content = lost.getContent();
			    String eventTime = String.valueOf(lost.getEventTime());
			    String location = lost.getLocation();
			    String contact = lost.getContact();
			    boolean flag = lost.isFlag();
			    int reportNum = lost.getReportNum();
			    List<String> images = imageDao.getImageName(lost.getUserId(), lost.getSendTime());
			    
			    LostItem item = new LostItem(lostId, userImg, userName, userId, sendTime, content, eventTime, location, contact, flag, reportNum, images);
			    items.add(item);
			}
			String jsonString = JSONArray.fromObject(items).toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(jsonString);
		}else{
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
