package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String password = request.getParameter("password");
		UserDao userdao = new UserDao();
		if(userId != null && password != null){
			if(userId != "" && password != ""){
				try{
				User user = userdao.getUser(Integer.valueOf(userId));
				if(user != null){
					if(user.getPassword().equals(password)){
						response.getWriter().append("LoginSuccess");
					}else{
						response.getWriter().append("WrongNumber");
					}
				}else{
					response.getWriter().append("WrongNumber");
				}
				
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}else{
				response.getWriter().append("NullError");
			}
			
		}else{
			response.getWriter().append("NullError");
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
