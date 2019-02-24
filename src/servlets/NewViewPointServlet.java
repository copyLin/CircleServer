package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ViewPointDao;
import vo.ViewPoint;

/**
 * Servlet implementation class NewViewPointServlet
 */
@WebServlet("/newViewPointServlet")
public class NewViewPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewViewPointServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String keyId = request.getParameter("keyId");
		String label = request.getParameter("label");
		String toId = request.getParameter("toId");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		String sendTime = request.getParameter("sendTime");
		
		ViewPointDao viewPointDao = new ViewPointDao();
		
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ViewPoint viewPoint = new ViewPoint(0, Integer.valueOf(keyId), label, Integer.valueOf(toId), content, Integer.valueOf(userId), formatter.parse(sendTime), false);
			viewPointDao.addViewPoint(viewPoint);
			
			System.out.println(viewPoint);
		}catch(Exception e){
			e.printStackTrace();
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
