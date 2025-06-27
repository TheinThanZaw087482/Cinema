package com.cinema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
	private double x=0;
	private double y=0;
	public static void main(String[] args) {
		
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		Parent root= FXMLLoader.load(getClass().getResource("/com/cinema/views/login.fxml"));
		Scene sc= new Scene(root);
		root.setOnMousePressed((MouseEvent event)->{
			x=event.getSceneX();
			y=event.getSceneY();
		});
		
		
		root.setOnMouseDragged((MouseEvent event)->{
			st.setX(event.getSceneX() -x);
			st.setY(event.getSceneX() -y);
		});
		st.initStyle(StageStyle.TRANSPARENT);
		st.setScene(sc);
		st.show();
	}

}
