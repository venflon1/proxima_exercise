package roberto.day14.thread.chat;

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

public class ServerChat2 {

	private static Logger log = Logger.getLogger(ServerChat2.class.getName());

	public static void main(String[] args) throws IOException {

			ServerSocket serverSocket = new ServerSocket(10002);
			log.info("SERVER-CHAT 2.0 is up.........");
			Socket client = null;
			ObjectInputStream in = null;
			ObjectOutputStream out = null;
			List<exercises.day13.chat.Message> messageList = null;
			exercises.day13.chat.Message mess = null;

			while(true) {
				log.info("SERVER-CHAT 2.0 wait connection for anyone client......");
				client = serverSocket.accept();
				log.info("SERVER-CHAT 2.0 connection established to client with ip:"+client.getInetAddress()
				         + ", port:"+client.getPort());
				
				// leggo quello che il client mi invia
				in = new ObjectInputStream(client.getInputStream());
				Long readValue = in.readLong();
				log.info("received message for CLIENT (ip, port)=("+client.getInetAddress()+","+ client.getPort() +")");
				log.info(readValue.toString());
				
				// invio al client una list di oggetti Message
				// messList = readAllMessage(new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message"));
				messageList = IOUtil.readMessage(new File("C:\\Users\\Roberto\\Desktop\\backupMessage"), readValue+"");
//				Socket sock = new Socket(InetAddress.getLocalHost(), 10010);
				out = new ObjectOutputStream(client.getOutputStream());
				out.writeObject(messageList);
				log.info("SERVER-CHAT 2.0 send object to CLIENT (ip, port)= ("+client.getInetAddress()+","+ client.getPort() +")"); 
			}
	} // end main method
}