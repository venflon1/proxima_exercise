package roberto.day14;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class ClientB {
	
	private static Logger log = Logger.getLogger(ClientB.class.getName());
	
	public static void main(String[] args) {
		try {
			Socket client = new Socket(InetAddress.getLocalHost(), 9090);
			
			// invio un long al server che e' in ascolto sulla porta 9090
			Long time = new Long(1576837101266L);
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			out.writeLong(time);
//			Log.info("CLIENT B send long object to server.......");
			// chiusura stream I/O
			out.close();
			
			// client riceve dal server una lista di oggetti di tipo exercises.day13.chat.Message
			ServerSocket serverSock = new ServerSocket(10010);
			Socket myClient = serverSock.accept();
			ObjectInputStream in = new ObjectInputStream(myClient.getInputStream());
			ArrayList<exercises.day13.chat.Message> listMess = (ArrayList<exercises.day13.chat.Message>)in.readObject();
//			log.info("CLIENT B received exercises.day13.chat.Message by server.......");
//			log.info(listexercises.day13.chat.Message.toString());
			listMess.forEach((mess) -> System.out.println(mess));
			// chiusura stream I/O
			in.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
