package core;
import java.util.Date;
import java.util.Observable;

/**
 * Notifier watching for a changing date, to let Customers know the day changed and it could be the user's birthday
 */
public class DateChangedNotifier extends Observable implements Runnable{
	private Date oldDate = new Date();

	/**
	 * Running function as it implements Runnable
	 * This functions checks if the date changed, then notifies observers (users) that the day changed for them to check if it is their birthday
	 * If the day didn't changed, it sleeps until next day 
	 */
	@Override
	public void run() {
		//initialization
		setChanged();
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
				setChanged();
				notifyObservers();
			}
		}
	}

}
