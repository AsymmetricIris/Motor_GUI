package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class MainSceneController implements Initializable {
	LocalTime time = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@FXML
	private Button btn_left;
	@FXML
	private Button btn_right;
	@FXML
	private Arc motor_shaft;
	@FXML
	private Text timer;
	@FXML
	private Text display;
	
	private double rotate_incr = 0.0;
	private double rotate_time = 0.01;
	private double speed_step = 5;
	
	Timeline timeline = new Timeline(
			new KeyFrame(Duration.seconds(rotate_time),
					e -> {
						motor_shaft.setRotate(motor_shaft.getRotate() + rotate_incr );
						time = LocalTime.now();
						timer.setText(time.format(formatter));
						display.setText(String.format("%.2f", rotate_incr));
			}));
	
	//Event listener on Button.onAction
	@FXML
	public void btnRightClicked(ActionEvent event) {
		rotate_incr += speed_step;
	}
	
	@FXML
	public void btnLeftClicked(ActionEvent event) {
		rotate_incr -= speed_step;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		timer.setText(time.format(formatter));
		display.setText(time.format(formatter));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
