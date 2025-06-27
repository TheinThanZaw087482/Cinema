package com.cinema.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupController {

    @FXML
    private Label sell_date_Label;

    @FXML
    private Label sell_time_Label;

    @FXML
    private Label show_date_Label;

    @FXML
    private Label show_time_Label;

    @FXML
    private Label movie_name_Label;

    @FXML
    private Label room_Label;

    @FXML
    private Label no_of_seats_Label;

    @FXML
    private Label total_cost_Label;
    
    @FXML
    private Label lb_sale_voucherno;

    @FXML
    private Label seats_Label;

    @FXML
    private Button closeButton;
    
    

    @FXML
    void closePopup(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    	stage.initStyle(StageStyle.TRANSPARENT);
    }
        
	public void set_sell_date_Label(String sellDate) {    
    sell_date_Label.setText(sellDate);
	}
	
	public void set_sell_time_Label(String sellTime) {
    
    sell_time_Label.setText(sellTime);
	}

	public void set_movie_name_Label(String movietitle) {
        movie_name_Label.setText(movietitle);

	}

	public void set_show_date_Label(String showdate) {
		
		show_date_Label.setText(showdate);
	}

	public void set_show_time_Label(String showtime) {
		show_time_Label.setText(showtime);
	}

	public void set_no_of_seats_Label(String numSeats) {
		no_of_seats_Label.setText(numSeats);
		
	}

	public void set_room_Label(String room) {
		room_Label.setText(room);
	}

	public void set_total_cost_Label(String totalcost) {
		total_cost_Label.setText(totalcost);
	}

	public void set_seats_Label(String seat) {
		seats_Label.setText(seat);
	}
	public void set_VoucherNo(String voucherno) {
		lb_sale_voucherno.setText(voucherno);
	}

}
