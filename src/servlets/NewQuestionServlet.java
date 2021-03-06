package servlets;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ImageDao;
import dao.QuestionDao;
import vo.Image;
import vo.Question;

/**
 * Servlet implementation class NewQuestionServlet
 */
@WebServlet("/newQuestionServlet")
public class NewQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String realPath = this.getServletContext().getRealPath("image/");
		String dirFlag = System.getProperty("file.separator");
		String result = "Send message fail";
		
		QuestionDao questionDao = new QuestionDao();
		ImageDao imageDao = new ImageDao();
		
		String userId = null;
		String sendTime = null;
		String content = null;
		String fileName = null;
		List<String> images = new ArrayList<>();
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try{
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while(iter.hasNext()){
					FileItem item = iter.next();
					if(item.isFormField()){
						if(item.getFieldName().equals("userId")){
							userId = item.getString("utf-8");
						}else if(item.getFieldName().equals("sendTime")){
							sendTime = item.getString("utf-8");
						}else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						}
					}else{
						fileName = item.getName();
						if (fileName != null && !fileName.equals("")) {
							File dirPath = new File(realPath + dirFlag + userId);
					        if(!dirPath.exists()){
					            dirPath.mkdir();
					            System.out.println("创建文件夹成功");
					        }
							File saveFile = new File(realPath + dirFlag + userId, item.getName());
							item.write(saveFile);
							images.add(fileName);
						}
					}
				}
				
				System.out.println("NewQuestion_data:" + userId + sendTime + content + images);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Question question = new Question(0, Integer.valueOf(userId), formatter.parse(sendTime), content, false, 0);
				questionDao.addQuestion(question);
				
				System.out.println("NewQuestion:" + question);
				
				if(images != null){
					for(String img : images){
						Image image = new Image(0, img, Integer.valueOf(userId), formatter.parse(sendTime));
						imageDao.addImage(image);
					}
				}
				result = "Send message success";
				
			}catch(Exception e){
				e.printStackTrace();
			}
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
