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

import com.example.linxl.circle.gson.DeliveryItem;

import dao.DeliveryDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import vo.Delivery;

/**
 * Servlet implementation class MyDeliveryServlet
 */
@WebServlet("/myDeliveryServlet")
public class MyDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyDeliveryServlet() {
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
		
		DeliveryDao deliveryDao = new DeliveryDao();
		UserDao userDao = new UserDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<DeliveryItem> items = new ArrayList<>();
		List<Delivery> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = deliveryDao.getMyDelivery(Integer.parseInt(userId));
		}else {
			list = deliveryDao.getMyDelivery(Integer.parseInt(userId), Integer.parseInt(currentId));
		}
		
		System.out.println("MyDeliveryServlet_items: " + list);
		
		if(!list.isEmpty()){
			for(Delivery delivery : list){
				String deliveryId = String.valueOf(delivery.getId());
			    String userImg = userDao.getUserImg(delivery.getUserId());
			    String userName = userDao.getUserName(delivery.getUserId());
			    String sendTime = formatter.format(delivery.getSendTime());
			    String content = delivery.getContent();
			    String price = String.valueOf(delivery.getPrice());
			    boolean flag = delivery.isFlag();
			    int reportNum = delivery.getReportNum();
			    
			    DeliveryItem item = new DeliveryItem(deliveryId, userImg, userName, userId, sendTime, content, price, flag, reportNum);
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
