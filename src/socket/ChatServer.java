package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	private ServerSocket server = null;
	private ExecutorService mExecutors = null;
	
	public static void main(String[] args) {
		new ChatServer();
	}

	public ChatServer() {

		try {
			server = new ServerSocket(PORT);
			mExecutors = Executors.newCachedThreadPool();
			System.out.println("服务器已启动，等待客户端连接...");
			Socket client = null;
			
			while (true) {
				client = server.accept(); 
				mExecutors.execute(new Service(client));
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
				in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
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
							
							System.out.println(userId + " 用户上线");
							System.out.println("当前连接总数:" + mClientList.size());
							
							chatRecordDao = new ChatRecordDao();
							chatRecords = chatRecordDao.getUnreadChatRecord(Integer.parseInt(userId));
							if(!chatRecords.isEmpty()){
								if(isConnected(socket)){
									for(ChatRecord chatRecord : chatRecords){
										ChatItem item = new ChatItem(chatRecord.getId(), chatRecord.getContent(), String.valueOf(chatRecord.getFromId()), String.valueOf(chatRecord.getToId()), String.valueOf(chatRecord.getSendTime()), chatRecord.isFlag());
										JSONObject json = JSONObject.fromObject(item);
										String jsonData = json.toString();
										System.out.println(userId + "未接收数据：" + item);
										out.write(jsonData + "\n");
										out.flush();
										chatRecordDao.updateFlag(chatRecord.getId());
									}
								}else {
									closeSocket(userId);
								}
							}
							
						}else if(message.startsWith("exit:")){
							String userId = message.substring(5);
							System.out.println(userId + " 用户下线");
							closeSocket(userId);
							break;
							
						}else{
							this.handleMessage(message);
						}
					}
				}
			} catch (Exception e) {
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
			
				if (in != null){
                    in.close();
                }
                if (out != null){
                    out.close();
                }
                if (socket != null){
                    socket.close();
                }
				
				System.out.println("当前连接总数:" + mClientList.size());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		/**
		 * 将接收的消息转发给指定的客户端
		 * 
		 * @param jsonData
		 */
		public void handleMessage(String jsonData) {
			try{
				JSONObject jsonObject = JSONObject.fromObject(jsonData);
				ChatItem item = (ChatItem) JSONObject.toBean(jsonObject, ChatItem.class);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ChatRecord chatRecord = new ChatRecord(0, item.getContent(), Integer.valueOf(item.getFromId()), Integer.valueOf(item.getToId()), formatter.parse(item.getSendTime()), item.isFlag(), true);
				
				String toId = item.getToId();
				Socket client = mClientList.get(toId);
				if(client != null){
					boolean connected = isConnected(client);
					System.out.println("connected state: " + connected);
					if(connected){
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "utf-8"));// 获得输出流对象
                        writer.write(jsonData + "\n");
                        writer.flush();
                        chatRecord.setFlag(true);
                        chatRecordDao.addChatRecord(chatRecord);
                            	
                        System.out.println("目标用户在线 >>" + item);
					}else {
						mClientList.remove(toId);
						client.close();
						chatRecordDao.addChatRecord(chatRecord);
						System.out.println("目标用户不在线 >>" + item);
					}
				}else {
					chatRecordDao.addChatRecord(chatRecord);
					System.out.println("目标用户不在线 >>" + item);
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		private boolean isConnected(Socket socket){
			boolean state = false;
	        try{
	            socket.sendUrgentData(0xFF);
	            state = true;
	        }catch(Exception e){
	        	System.out.println("catch exception");
	        	e.printStackTrace();
	            state = false;
	        }
	        return state;
	    }
	}
}
