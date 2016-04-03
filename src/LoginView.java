
public class LoginView extends AbstractView {
	public LoginView(Program p) {
		super(p);
	}

	@Override
	public void show() {
		System.out.println("Login view");
		User user = program.getLoggedUser();
		if(user instanceof Admin){
			System.out.println(user.getUserName()+ " successfully logged in as admin");
		} else if (user instanceof Customer) {
			System.out.println("Hello, "+ user.getFullName());
		} else {
			System.out.println("No user logged in");
		}
	}

}
