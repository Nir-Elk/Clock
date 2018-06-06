package model;

import java.time.LocalTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
	
	public StringProperty time;

	public Model() {
		time = new SimpleStringProperty();
	}

	public void check() {
		LocalTime lt = LocalTime.now();
		//time.set(lt.getHour()%12+":"+lt.getMinute()+":"+lt.getSecond());
		time.set(lt.getHour()%12+":"+lt.getMinute()+":"+lt.getSecond()+":"+(int)(lt.getNano()/10000000));
	}

}
