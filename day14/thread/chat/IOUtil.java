package roberto.day14.thread.chat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class IOUtil {

	private static String path_folder_backup;
	private static File folder_backup;

	static {
		File file_props = new File("C:\\Users\\Roberto\\proxima-workspace\\java.base\\trunk\\src\\roberto\\day14\\thread\\chat\\data.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file_props);
			Properties props = new Properties();
			props.load(fis);
			path_folder_backup = (String) props.get("path_folder_BCK");
			folder_backup = new File(path_folder_backup);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * @throws IOException 
	 *  
	 **/
	public static void saveMessage(exercises.day13.chat.Message mess) throws IOException {
//		File fileToWrite = new File("C:\\Users\\titano\\Desktop\\backupMessage\\backup_" + System.currentTimeMillis() + ".ser");
		File fileToWrite = new File(
				IOUtil.folder_backup.getCanonicalPath() + "\\backup_" + System.currentTimeMillis() + ".ser");

		FileOutputStream fos = new FileOutputStream(fileToWrite);

		try (ObjectOutputStream writer = new ObjectOutputStream(fos)) {
			writer.writeObject(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method save a message Object in local file system under lacation: ->
	 * C:\Users\titano\Desktop\backupMessage
	 *
	 * @param Message to perist
	 * @return void
	 */
	public static void saveMessage2(exercises.day13.chat.Message mess) throws FileNotFoundException {
		LocalDateTime now = LocalDateTime.now();
		String time = now.getDayOfMonth() + "_" + now.getMonth() + "_" + now.getYear() + "_" + now.getHour() + "_"
				+ now.getMinute() + "_" + now.getSecond();

		FileOutputStream fos = new FileOutputStream(
				new File("C:\\Users\\titano\\Desktop\\backupMessage\\backup_" + time + ".ser"));
		try (ObjectOutputStream writer = new ObjectOutputStream(fos)) {
			writer.writeObject(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * */
	public static List<exercises.day13.chat.Message> readMessage(File f, String time) {
		File[] files = f.listFiles();
		List<File> listFiles = Arrays.asList(files);
		String timeCompare = time + ".ser";
		List<File> listFilesMatch = new ArrayList<File>();

		listFiles.forEach((fil) -> {
			if (fil.getName().compareTo(timeCompare) > 0)
				listFilesMatch.add(fil);
		});

		List<exercises.day13.chat.Message> listMessages = new ArrayList<>();
		listFilesMatch.forEach((fil) -> {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(fil));
				exercises.day13.chat.Message mess = (exercises.day13.chat.Message) in.readObject();
				listMessages.add(mess);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return listMessages;
	}

	/**
	 * 
	 * */
	public static List<exercises.day13.chat.Message> readAllMessage(File fil) {
		File file = new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message");
		File[] files = file.listFiles();
		List<File> listFiles = Arrays.asList(files);
		List<exercises.day13.chat.Message> listMessages = new ArrayList<exercises.day13.chat.Message>();
		listFiles.forEach((f) -> {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				exercises.day13.chat.Message mess = (exercises.day13.chat.Message) in.readObject();
				listMessages.add(mess);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return listMessages;
	}
}
