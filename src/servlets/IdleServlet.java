package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.IdleItem;

import dao.IdleDao;
import dao.ImageDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import vo.Idle;

/**
 * Servlet implementation class IdleServlet
 */
@WebServlet("/idleServlet")
public class IdleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdleServlet() {
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
		
		//System.out.println("IdleServlet_currentId: " + currentId);
		
		IdleDao idleDao = new IdleDao();
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		
		List<IdleItem> items = new ArrayList<>();
		List<Idle> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = idleDao.getIdle();
		}else {
			list = idleDao.getIdle(Integer.parseInt(currentId));
		}
		
		//System.out.println("IdleServlet_items: " + list);
		
		if(!list.isEmpty()){
			for(Idle idle : list){
				String idleId = String.valueOf(idle.getId());
			    String userImg = userDao.getUserImg(idle.getUserId());
			    String userName = userDao.getUserName(idle.getUserId());
			    String userId = String.valueOf(idle.getUserId());
			    String sendTime = String.valueOf(idle.getSendTime());
			    String content = idle.getContent();
			    String idleName = idle.getName();
			    String price = String.valueOf(idle.getPrice());
			    boolean flag = idle.isFlag();
			    int reportNum = idle.getReportNum();
			    List<String> images = imageDao.getImageName(idle.getUserId(), idle.getSendTime());
			    
			    IdleItem item = new IdleItem(idleId, userImg, userName, userId, sendTime, content, idleName, price, flag, reportNum, images);
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
