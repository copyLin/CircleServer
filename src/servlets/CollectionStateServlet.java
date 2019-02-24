package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectionDao;
import vo.Collection;

/**
 * Servlet implementation class CollectionStateServlet
 */
@WebServlet("/collectionStateServlet")
public class CollectionStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionStateServlet() {
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
		String keyId = request.getParameter("keyId");
		String label = request.getParameter("label");
		
		CollectionDao collectionDao = new CollectionDao();
		List<Collection> list = collectionDao.getCollection(Integer.valueOf(userId), Integer.valueOf(keyId), label);
		
		if(!list.isEmpty()){
			response.getWriter().append("StateTrue");
		}
		response.getWriter().append("StateFalse");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
