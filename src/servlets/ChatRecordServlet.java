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

import com.example.linxl.circle.gson.ChatItem;

import dao.ChatRecordDao;
import net.sf.json.JSONArray;
import vo.ChatRecord;

/**
 * Servlet implementation class ChatRecordServlet
 */
@WebServlet("/chatRecordServlet")
public class ChatRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String fId = request.getParameter("fromId");
		String tId = request.getParameter("toId");
		String currentId = request.getParameter("currentId");
		
		ChatRecordDao chatRecordDao = new ChatRecordDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<ChatItem> items = new ArrayList<>();
		List<ChatRecord> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = chatRecordDao.getChatRecord(Integer.valueOf(fId), Integer.valueOf(tId));
		}else {
			list = chatRecordDao.getChatRecord(Integer.valueOf(fId), Integer.valueOf(tId), Integer.valueOf(currentId));
		}
		
		if(!list.isEmpty()){
			for(ChatRecord chatRecord : list){
				int id = chatRecord.getId();
			    String content = chatRecord.getContent();
			    String fromId = String.valueOf(chatRecord.getFromId());
			    String toId = String.valueOf(chatRecord.getToId());
			    String sendTime = formatter.format(chatRecord.getSendTime());
			    boolean flag = chatRecord.isFlag();
			    
			    ChatItem item = new ChatItem(id, content, fromId, toId, sendTime, flag);
			    items.add(item);
			}
			
			//System.out.println("ChatRecord:" + items);
			
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
