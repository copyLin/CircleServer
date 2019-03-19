package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class MyLostServlet
 */
@WebServlet("/myLostServlet")
public class MyLostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLostServlet() {
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
		
		LostDao lostDao = new LostDao();
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<LostItem> items = new ArrayList<>();
		List<Lost> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = lostDao.getMyLost(Integer.parseInt(userId));
		}else {
			list = lostDao.getMyLost(Integer.parseInt(userId), Integer.parseInt(currentId));
		}
		
		System.out.println("MyLostServlet_items: " + list);
		
		if(!list.isEmpty()){
			for(Lost lost : list){
				String lostId = String.valueOf(lost.getId());
			    String userImg = userDao.getUserImg(lost.getUserId());
			    String userName = userDao.getUserName(lost.getUserId());
			    String sendTime = formatter.format(lost.getSendTime());
			    String content = lost.getContent();
			    String eventTime = formatter.format(lost.getEventTime());
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
