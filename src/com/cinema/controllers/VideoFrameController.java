package com.cinema.controllers;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.cinema.models.MovieData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

public class VideoFrameController implements Initializable {


	@FXML
    private AnchorPane movieframe;

    @FXML
    private ImageView movie_image_view;

    @FXML
    private Label movielabel;
    
    MovieData moviedata;
    

     private DashboardControllerstaff dashboardController; // Add this as a field

    public void setDashboardController(DashboardControllerstaff controller) {
        this.dashboardController = controller;
    }

    public void setMovieData(MovieData moviedata) {
        if (moviedata == null) return;

        this.moviedata = moviedata;
        movielabel.setText(moviedata.getMoviename());

        try {
	        File file = new File("resources/com/cinema/images/movieImage/", moviedata.getImage());
	        if (file.exists()) {
	            String uri = file.toURI().toString();
	            System.out.println("Loading image from: " + uri);
	            movie_image_view.setImage(new Image(uri, 244, 144, false, true));
	            
	        } else {
	            System.err.println("File not found: " + file.getAbsolutePath());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

        movie_image_view.setOnMouseClicked(event -> {
            if (moviedata != null && dashboardController != null) {
                dashboardController.choicedMovie(moviedata);
            }
        });
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
