import java.io.Serializable;

public class User implements Serializable{
	private final String firstName;
	private final String lastName;
	private final String userName;
	protected String hash;
	
	public User(String firstName, String lastName, String userName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}
	
	@Override
	public int hashCode(){
		return this.userName.hashCode();
	}
	
	@Override
	public boolean equals(Object user){
		if(user instanceof User)
			return this.userName.equals(((User) user).getUserName());
		return false;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFullName() {
		return firstName+" "+lastName;
	}
	public String getUserName() {
		return userName;
	}

	public boolean authenticate(String password){
		return SecurityController.hashPassword(password).equals(this.hash);
	}
	public void setPassword(String password) {
		this.hash = SecurityController.hashPassword(password);
	}
	public String getHash(){
		return this.hash;
	}
	
	
}
