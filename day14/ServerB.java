package roberto.day14;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;


public class ServerB {
	
	private static Logger log = Logger.getLogger(ServerB.class.getName());
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			
			Socket client = null;
			ObjectInputStream in = null;
			ObjectOutputStream out = null;
			List<Message> messList = null;
			exercises.day13.chat.Message mess = null;
			while(true) {
//				log.info("SERVER B wait connection for anyone client......");
				client = serverSocket.accept();
//				log.info("SERVER B connection established to client with ip:"+client.getInetAddress()
//				         + ", port:"+client.getPort());
				
				// leggo quello che il client mi invia
				in = new ObjectInputStream(client.getInputStream());
				Long readValue = in.readLong();
//				log.info("received message for CLIENT (ip, port)=("+client.getInetAddress()+","+ client.getPort() +")");
//				log.info(readValue.toString());
				
				// invio al client una list di oggetti Message
				// messList = readAllMessage(new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message"));
				messList = readMessage(new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message"), readValue+"");
				Socket sock = new Socket(InetAddress.getLocalHost(), 10010);
				out = new ObjectOutputStream(sock.getOutputStream());
				out.writeObject(messList);
//				log.info("SERVER B send object to CLIENT (ip, port)= ("+client.getInetAddress()+","+ client.getPort() +")"); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List<exercises.day13.chat.Message> readAllMessage(File fil){
		File file = new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message");
		File[] files = file.listFiles();
		List<File> listFiles = Arrays.asList(files);
		List<exercises.day13.chat.Message> listMessages = new ArrayList<exercises.day13.chat.Message>();
		listFiles.forEach( (f) -> {
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					exercises.day13.chat.Message mess = (exercises.day13.chat.Message) in.readObject();
					listMessages.add(mess);
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		);
		
		return  listMessages;
	}
	
	public static List<Message> readMessage(File f, String time) {
		File file = new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message");
		File[] files = file.listFiles();
		List<File> listFiles = Arrays.asList(files);
		String timeCompare = time+".ser";
		List<File> listFilesMatch = new ArrayList<File>();
		
		listFiles.forEach( (fil) ->{ 
					if(fil.getName().compareTo(timeCompare) > 0) listFilesMatch.add(fil);
				});
		
		List<Message> listMessages = new ArrayList<Message>();
		listFilesMatch.forEach( (fil) -> {
			try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(fil));
					Message mess = (Message) in.readObject();
					listMessages.add(mess);
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		);
		
		return listMessages;
	}
}
