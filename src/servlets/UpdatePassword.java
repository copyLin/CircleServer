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
 * Servlet implementation class ChangePassword
 */
@WebServlet("/updatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
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
		String oldPassword = request.getParameter("oldPassword");
		String firstInput = request.getParameter("firstInput");
		String secondInput = request.getParameter("secondInput");
		
		System.out.println("ChangePassword" + oldPassword + firstInput + secondInput); 
		
		UserDao userDao = new UserDao();
		User user = userDao.getUser(Integer.valueOf(userId));
		if(oldPassword.equals(user.getPassword())){
			if(firstInput.equals(secondInput)){
				userDao.updatePassword(firstInput, Integer.valueOf(userId));
				response.getWriter().append("修改成功");
			}else{
				response.getWriter().append("新密码不一致");
			}
		}else{
			response.getWriter().append("密码错误");
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
