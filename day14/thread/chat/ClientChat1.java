package roberto.day14.thread.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class ClientChat1 {
	
	private static Logger log = Logger.getLogger(ClientChat1.class.getName());

	public static void main(String[] args) {
		
		try ( Socket socketClient = new Socket(InetAddress.getLocalHost(), 10001) ){
			log.info("CLIENT-CHAT 1.0 is up and connected to SERVER-CHAT 1.0.........");

			try( ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream()) ){
				exercises.day13.chat.Message mess = new exercises.day13.chat.Message();
				mess.setUserName("roberto");
				mess.setTextMessage(new String("Messaggio del ****" +  System.nanoTime()));
				os.writeObject(mess);
				log.info("CLIENT-CHAT 1.0 close.......");
			}
		} 
		catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}