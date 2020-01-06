package roberto.day14;

import java.io.Serializable;

public class Message implements Serializable{
	private String username;
	private String textMessage;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTextMessage() {
		return textMessage;
	}
	
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	@Override
	public String toString() {
		return "Message [username=" + username + ", textMessage=" + textMessage + "]";
	}
}
