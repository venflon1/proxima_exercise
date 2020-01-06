package roberto.day13;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.InflaterInputStream;

public class ListMessageChat {
	public static void main(String[] args) {
		File file = new File("C:\\Users\\titano\\eclipse-workspace\\networking_exercise\\backup_message");
		File[] files = file.listFiles();
		List<File> listFiles = Arrays.asList(files);
		listFiles.forEach( (f) -> {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
				exercises.day13.chat.Message mess = (exercises.day13.chat.Message) in.readObject();
				System.out.println(mess);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}
}
