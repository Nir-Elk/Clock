package viewmodel;

import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ViewModel {
	
	private Timer timer;
	private TimerTask tm;
	private Model m;
	public StringProperty time;
	
	public ViewModel() {
		m = new Model();
		time = new SimpleStringProperty();
		time.bind(m.time);
	}
	
	public void start() {
		this.stop();
		timer = new Timer();
		tm = new TimerTask() {
			@Override
			public void run() {
				m.check();
			}
		};
		timer.scheduleAtFixedRate(tm, 0, 10);
	}
	public void stop() {
		if(timer != null)
		{
			tm.cancel();
			timer.cancel();
		}
	}
}
