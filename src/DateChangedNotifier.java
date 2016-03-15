import java.util.Date;
import java.util.Observable;

public class DateChangedNotifier extends Observable implements Runnable{
	private Date oldDate = new Date();

	@Override
	public void run() {
		//initialization
		notifyObservers();
		while(true){
			Date newDate = new Date();
			if(newDate.getDate() == oldDate.getDate()){
				try {
					//Sleep until next day
					Thread.sleep((24*3600-newDate.getSeconds()-3600*newDate.getHours()-60*newDate.getMinutes())*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				oldDate = newDate;
				notifyObservers();
			}
		}
	}

}
