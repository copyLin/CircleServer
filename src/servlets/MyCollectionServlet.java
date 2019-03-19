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

import com.example.linxl.circle.gson.CollectionItem;

import dao.CollectionDao;
import net.sf.json.JSONArray;
import vo.Collection;

/**
 * Servlet implementation class MyCollectionServlet
 */
@WebServlet("/myCollectionServlet")
public class MyCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCollectionServlet() {
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
		
		//System.out.println("MyCollection:" + userId + ", " + currentId);
		
		CollectionDao collectionDao = new CollectionDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<Collection> list = new ArrayList<>();;
		if(currentId.equals("0")){
			list = collectionDao.getMyCollection(Integer.valueOf(userId));
		}else{
			list = collectionDao.getMyCollection(Integer.valueOf(userId), Integer.valueOf(currentId));
		}
		List<CollectionItem> items = new ArrayList<>();
		if(!list.isEmpty()){
			for(Collection collection : list){
				int id = collection.getId();
				String keyId = String.valueOf(collection.getKeyId());
				String label = collection.getLabel();
				String name = collection.getName();
				String time = formatter.format(collection.getCollectionTime());
				
				CollectionItem item = new CollectionItem(id, keyId, label, name, time);
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
