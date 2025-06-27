package com.cinema.controllers;
import java.io.File;
import java.time.format.DateTimeFormatter;

import com.cinema.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
public class UserInfoController {

    @FXML
    private ImageView image_view;

    @FXML
    private Label name;

    @FXML
    private Label phone;

    @FXML
    private Label email;

    @FXML
    private Label hire_date;

    @FXML
    private Label position;

    @FXML
    private Label salary;

    @FXML
    private TextArea address;
	
	DashboardControllerstaff dashboardstaff;
	
	DashboardControllerAdmin dashboradadmin;
	
	public void setStaffController(DashboardControllerstaff dashboardstaff ) {
		this.dashboardstaff =dashboardstaff;
	}
	public void setAdminController(DashboardControllerAdmin dashboradadmin) {
		this.dashboradadmin=dashboradadmin;
	}
	public void setData(User user) {
	    if (user != null) {
	        name.setText(user.getUserName());
	        phone.setText(user.getPhone());
	        email.setText(user.getEmail());

	        // Format hire date properly
	        if (user.getHire_Date() != null) {
	            hire_date.setText(user.getHire_Date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	        } else {
	            hire_date.setText("Not Available");
	        }

	        position.setText(user.getUserType());
	        salary.setText(String.valueOf(user.getSalary()));
	        address.setText(user.getAddress());

	        try {
		        File file = new File("resources/com/cinema/images/userImage/", user.getImagePath());
		        if (file.exists()) {
		            String uri = file.toURI().toString();
		            System.out.println("Loading image from: " + uri);
		            image_view.setImage(new Image(uri));
		            double radius = Math.min(image_view.getFitWidth(), image_view.getFitHeight()) / 2;
		            Circle clip = new Circle(radius, radius, radius);
		            image_view.setClip(clip);
		            
		        } else {
		            System.err.println("File not found: " + file.getAbsolutePath());
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

	    } else {
	        // Handle null user case, for example, show a message or reset the fields
	        name.setText("Unknown");
	        phone.setText("N/A");
	        email.setText("N/A");
	        hire_date.setText("N/A");
	        position.setText("N/A");
	        salary.setText("N/A");
	        address.setText("N/A");
	    }
	}

	@Override
	public String toString() {
		return "UserInfoController [image_view=" + image_view + ", name=" + name + ", phone=" + phone + ", email="
				+ email + ", hire_date=" + hire_date + ", position=" + position + ", salary=" + salary + ", address="
				+ address + ", dashboardstaff=" + dashboardstaff + "]";
	}



}


