package servlets;

import java.io.File;
import java.io.IOException;
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

import dao.UserDao;

/**
 * Servlet implementation class UploadUserImage
 */
@WebServlet("/uploadUserImage")
public class UploadUserImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadUserImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String realPath = this.getServletContext().getRealPath("image/");
		String dirFlag = System.getProperty("file.separator");
				
		String userId = null;
		String fileName = null;
		
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
							
							System.out.println("UploadUserImage:" + userId);
						}
					}else{
						fileName = item.getName();
						if (fileName != null && !fileName.equals("")) {
							File saveFile = new File(realPath + dirFlag + "user_img", fileName);
							item.write(saveFile);
						}
					}
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		UserDao userDao = new UserDao();
		userDao.updateImage(fileName, userId);
		response.getWriter().append("Success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
