package com.Game.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewLunch  extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		 Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));        
	        Scene scene = new Scene(root);
	        scene.setFill(Color.TRANSPARENT);
	        arg0.initStyle(StageStyle.TRANSPARENT);
	        arg0.setScene(scene);
	        arg0.show();
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
