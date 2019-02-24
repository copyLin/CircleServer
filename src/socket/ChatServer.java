package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.linxl.circle.gson.ChatItem;

import dao.ChatRecordDao;
import net.sf.json.JSONObject;
import vo.ChatRecord;

public class ChatServer {

	private static final int PORT = 8090;
	private Map<String, Socket> mClientList = new HashMap<String, Socket>();
	//private Map<String, BufferedWriter> mClientOutputStreams = new HashMap<String, BufferedWriter>();
	private ServerSocket server = null;
	private ExecutorService mExecutors = null; // 线程池对象
	
	public static void main(String[] args) {
		new ChatServer();
	}

	public ChatServer() {

		try {
			server = new ServerSocket(PORT);
			mExecutors = Executors.newCachedThreadPool(); // 创建线程池
			System.out.println("服务器已启动，等待客户端连接...");
			Socket client = null;
			
			while (true) {
				client = server.accept(); 
				mExecutors.execute(new Service(client)); // 启动一个线程，用以守候从客户端发来的消息
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class Service implements Runnable {

		private Socket socket;
		private BufferedReader in = null;
		private BufferedWriter out = null;
		private String message = "";
		private ChatRecordDao chatRecordDao;
		private List<ChatRecord> chatRecords;

		public Service(Socket socket) {

			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));// 获得输入流对象
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));//获得输出流对象
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		@Override
		public void run() {
			try {
				
				while (true) {					
					if ((message = in.readLine())!= null) {
						System.out.println(message);
						if(message.startsWith("enter:")){
							
							String userId = message.substring(6);
							mClientList.put(userId, socket);
							//mClientOutputStreams.put(userId, out);
							System.out.println(userId + " 用户上线");
							System.out.println("当前连接总数:" + mClientList.size());
							
							chatRecordDao = new ChatRecordDao();
							chatRecords = chatRecordDao.getUnreadChatRecord(Integer.parseInt(userId));
							if(!chatRecords.isEmpty()){
								for(ChatRecord chatRecord : chatRecords){
									ChatItem item = new ChatItem(chatRecord.getId(), chatRecord.getContent(), String.valueOf(chatRecord.getFromId()), String.valueOf(chatRecord.getToId()), String.valueOf(chatRecord.getSendTime()), chatRecord.isFlag());
									JSONObject json = JSONObject.fromObject(item);
									String jsonData = json.toString();
									System.out.println(userId + "未接收数据：" + item);
									out.write(jsonData + "\n");
									out.flush();
									chatRecordDao.updateFlag(chatRecord.getId());
								}
							}
							
						}else if(message.startsWith("exit:")){
							String userId = message.substring(5);
							System.out.println(userId + " 用户下线");
							this.closeSocket(userId);
							break;
							
						}else{
							this.handleMessage(message);
						}
						
					}else{
						
					}
				}
			} catch (EOFException e1) {
				e1.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 关闭客户端
		 * 
		 * @throws IOException
		 */
		public void closeSocket(String userId) {
			try{
				mClientList.remove(userId);
				//mClientOutputStreams.remove(userId);
			
				in.close();
				out.close();
				socket.close();
				
				System.out.println("当前连接总数:" + mClientList.size());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		/**
		 * 将接收的消息转发给指定的客户端
		 * 
		 * @param item
		 */
		public void handleMessage(String jsonData) {
			try{
				JSONObject jsonObject = JSONObject.fromObject(jsonData);
				ChatItem item = (ChatItem) JSONObject.toBean(jsonObject, ChatItem.class);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ChatRecord chatRecord = new ChatRecord(0, item.getContent(), Integer.valueOf(item.getFromId()), Integer.valueOf(item.getToId()), formatter.parse(item.getSendTime()), item.isFlag());
				
				String toId = item.getToId();
				System.out.println("目标用户：" + toId);
				
				if(mClientList.get(toId) != null){
	    			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(mClientList.get(toId).getOutputStream(), "utf-8"));// 获得输出流对象
	    			writer.write(jsonData + "\n");
	    			writer.flush();// 转发
	    			chatRecord.setFlag(true);
	    			System.out.println("目标用户在线 >>" + item); // 在控制台输出
	    			chatRecordDao.addChatRecord(chatRecord);
				}else{
					System.out.println("目标用户不在线 >>" + item); // 在控制台输出
					chatRecordDao.addChatRecord(chatRecord);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}
