package atmInterfaceProject;

//import java.util.ArrayList;

public class User {
	
	private String name;
	private String userName;
	private String pin;
	
	public User(String name, String userName, String pin) {
		this.name = name;
		this.userName = userName;
		this.pin = pin;

	}
	
	// Below functions are getters and setters for user data
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
}
