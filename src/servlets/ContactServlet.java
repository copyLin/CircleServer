package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.ContactItem;

import dao.ChatRecordDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import vo.ChatRecord;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/contactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
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
		
		ChatRecordDao chatRecordDao = new ChatRecordDao();
		UserDao userDao = new UserDao();
		
		List<ChatRecord> list = chatRecordDao.getRecentChat(Integer.valueOf(userId));
		List<ContactItem> items = new ArrayList<>();
		if(!list.isEmpty()){
			for(ChatRecord chatRecord : list){
				String contactId = String.valueOf(chatRecord.getFromId());
			    String contactName = userDao.getUserName(chatRecord.getFromId());
				String contactImg = userDao.getUserImg(chatRecord.getFromId());
			    String recentChat = chatRecord.getContent();
			    
			    ContactItem item = new ContactItem(contactId, contactName, contactImg, recentChat);
			    items.add(item);
			}
			String jsonString = JSONArray.fromObject(items).toString();
    		response.setContentType("text/html;charset=utf-8");
    		response.getWriter().append(jsonString);
		}else{
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
