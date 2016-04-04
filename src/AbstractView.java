public abstract class AbstractView {
	protected Program program;
	
	public AbstractView(Program p){
		program = p;
	}
	
	public abstract void show();
	
	public void show(Object result){
		show();
	}
}
