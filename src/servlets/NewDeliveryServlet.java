package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DeliveryDao;
import vo.Delivery;

/**
 * Servlet implementation class NewDeliveryServlet
 */
@WebServlet("/newDeliveryServlet")
public class NewDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewDeliveryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String result = "Send message fail";
		
		DeliveryDao deliveryDao = new DeliveryDao();
		
		String userId = request.getParameter("userId");
		String sendTime = request.getParameter("sendTime");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		
		System.out.println("NewDelivery_data:" + userId + sendTime + content + price);
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Delivery delivery = new Delivery(0, Integer.valueOf(userId), formatter.parse(sendTime), content, Double.valueOf(price), false, 0);
			deliveryDao.addDelivery(delivery);
			result = "Send message success";
			System.out.println("NewDelivery_item" + delivery);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
