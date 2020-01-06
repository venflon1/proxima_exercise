package roberto.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerChat {
	
	private static Logger log = Logger.getLogger(ServerChat.class.getName());
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			
			while(true) {
				Socket clientSocket = serverSocket.accept();
				log.info("server accepted connection to " + clientSocket.getInetAddress());
				ObjectInputStream oos = new ObjectInputStream(clientSocket.getInputStream());
				exercises.day13.chat.Message mess = (exercises.day13.chat.Message) oos.readObject();
				System.out.println(mess);
				saveMessage(mess);
				clientSocket.close();
			}
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	private static void saveMessage(exercises.day13.chat.Message mess) {
		try {
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message\\" + System.currentTimeMillis() + ".ser")));
			writer.writeObject(mess);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}