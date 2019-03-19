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

import com.example.linxl.circle.gson.QuestionItem;

import dao.ImageDao;
import dao.QuestionDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import vo.Question;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/questionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public QuestionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String currentId = request.getParameter("currentId");
		
		//System.out.println("QuestionServlet_currentId: " + currentId);
		
		QuestionDao questionDao = new QuestionDao();
		UserDao userDao = new UserDao();
		ImageDao imageDao = new ImageDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<QuestionItem> items = new ArrayList<>();
		List<Question> list = new ArrayList<>();
		
		if(currentId.equals("0")){
			list = questionDao.getQuestion();
		}else {
			list = questionDao.getQuestion(Integer.parseInt(currentId));
		}
		
		//System.out.println("QuestionServlet_items: " + list);
		
		if(!list.isEmpty()){
			for(Question question : list){
				String questionId = String.valueOf(question.getId());
				String userImg = userDao.getUserImg(question.getUserId());
				String userName = userDao.getUserName(question.getUserId());
				String userId = String.valueOf(question.getUserId());
				String sendTime = formatter.format(question.getSendTime());
				String content = question.getContent();
				boolean flag = question.isFlag();
				int reportNum = question.getReportNum();
				List<String> images = imageDao.getImageName(question.getUserId(), question.getSendTime());
			
				QuestionItem item = new QuestionItem(questionId, userImg, userName, userId, sendTime, content, flag, reportNum, images);
				items.add(item);
			}
			String jsonString = JSONArray.fromObject(items).toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().append(jsonString);
		}else {
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
