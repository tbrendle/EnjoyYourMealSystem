
public class PageNotFoundView extends AbstractView {

	public PageNotFoundView(Program p) {
		super(p);
	}

	@Override
	public void show() {
		System.out.println("This command does not exist");
	}
	


}
