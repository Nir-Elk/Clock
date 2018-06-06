package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import viewmodel.ViewModel;

public class SampleController implements Initializable {
	ViewModel vm;
	
	@FXML
	Text digitalclock;
	@FXML
	Canvas analogclock;
	@FXML
	Button stop;
	public void start()
	{
		vm.start();
		vm.time.addListener((a,b,c)->Platform.runLater(()->{draw(); digitalclock.setText(vm.time.get());}));

	}
	public void stop()
	{
		vm.stop();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vm = new ViewModel();
		stop.setOnAction(e->stop());
		digitalclock.setText("00:00:00");
	}
	
	private void draw() {
		GraphicsContext gc = analogclock.getGraphicsContext2D();
		String[] timevector = vm.time.get().split(":");
		double h = analogclock.getHeight();
		double w = analogclock.getWidth();
		double mx = h/2, my = w/2;
		double alpha = 0;
		double r = Math.min(h, w)/2;
		double H = ToAng(Integer.parseInt(timevector[0])/12.0*360);
		double M = ToAng(Integer.parseInt(timevector[1])/60.0*360);
		double S = ToAng(Integer.parseInt(timevector[2])/60.0*360);
		double N = ToAng(Integer.parseInt(timevector[3])/100.0*360);
		for(double i = 0;i<12;i++)
		{
			gc.fillOval(mx+r*Math.cos(i/12.0*360), my+r*Math.sin(i/12.0*360), 20, 20);
		}
		gc.clearRect(0, 0, w, h);
		gc.setStroke(Color.BLACK);
		gc.strokeOval(alpha, alpha, w-2*alpha, h-2*alpha);
		gc.strokeLine(mx, my, mx+r*2/3*Math.cos(H), my+r*2/3*Math.sin(H));
		gc.strokeLine(mx, my, mx+r*3/4*Math.cos(M), my+r*3/4*Math.sin(M));
		gc.setStroke(Color.RED);
		gc.strokeLine(mx, my, mx+r*4/5*Math.cos(S), my+r*4/5*Math.sin(S));
		gc.setFill(Color.BLUE);
		gc.fillOval(mx+r*Math.cos(N)-5, my+r*Math.sin(N)-5, 10,10);
		
		
	}
	
	public double ToAng(double d)
	{
		return Math.toRadians(d-90);
	}
	
}
