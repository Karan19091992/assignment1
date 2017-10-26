package tejas.cloud.assignment1.model;

public class User {
	private int accountID;
	private String password;
	
	public User(int accountID) {
		this.accountID = accountID;
	}

	public User(int accountID, String password) {
		this.accountID = accountID;
		this.password = password;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
