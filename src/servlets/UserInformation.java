package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.linxl.circle.gson.UserItem;

import dao.UserDao;
import net.sf.json.JSONObject;
import vo.User;

/**
 * Servlet implementation class UserInformation
 */
@WebServlet("/userInformation")
public class UserInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformation() {
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
		
		UserDao userDao = new UserDao();
		User user = userDao.getUser(Integer.valueOf(userId));
		UserItem item = new UserItem(String.valueOf(user.getId()), String.valueOf(user.getName()), String.valueOf(user.getImage()), String.valueOf(user.getDepartment()), String.valueOf(user.getMajor()), String.valueOf(user.getWords()));
		String jsonString = JSONObject.fromObject(item).toString();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(jsonString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
