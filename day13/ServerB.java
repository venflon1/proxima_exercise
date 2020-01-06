package roberto.day13;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ServerB {
	
	private static Logger log = Logger.getLogger(ServerB.class.getName());
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			
			Socket client = null;
			ObjectInputStream in = null;
			ObjectOutputStream out = null;
			ArrayList<exercises.day13.chat.Message> messList = null;
			exercises.day13.chat.Message mess = null;
			while(true) {
				log.info("SERVER B wait connection for anyone client......");
				client = serverSocket.accept();
				log.info("SERVER B connection established to client with ip:"+client.getInetAddress()
				         + ", port:"+client.getPort());
				
				// leggo quello che il client mi invia
				in = new ObjectInputStream(client.getInputStream());
				Long readValue = in.readLong();
				log.info("received message for CLIENT (ip, port)=("+client.getInetAddress()+","+ client.getPort() +")");
				log.info(readValue.toString());
				
				// invio al client una list di oggetti Message
				messList = new ArrayList<exercises.day13.chat.Message>();
				for(int i=0; i<4; i++) {
					mess = new exercises.day13.chat.Message();
					mess.setTextMessage("aeiou" + System.nanoTime());
					messList.add(mess);
				}
				Socket sock = new Socket(InetAddress.getLocalHost(), 10010);
				out = new ObjectOutputStream(sock.getOutputStream());
				out.writeObject(messList);
				log.info("SERVER B send object to CLIENT (ip, port)= ("+client.getInetAddress()+","+ client.getPort() +")"); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
