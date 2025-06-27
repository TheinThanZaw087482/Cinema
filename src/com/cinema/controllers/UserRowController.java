package com.cinema.controllers;

import java.io.File;

import com.cinema.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class UserRowController {
	@FXML
	private AnchorPane staff_box;
	
	@FXML
	private HBox Userrow;
    @FXML
    private Label lb_ID;

    @FXML
    private ImageView IVimage;

    @FXML
    private Label lb_name;

    @FXML
    private Label lb_userType;

    @FXML
    private Label lb_phone;

    @FXML
    private Label lb_gmail;

    @FXML
    private Label lb_hire_date;

    @FXML
    private Label lb_address;

    @FXML
    private Label lb_salary;

    @FXML
    private Label lb_Statatus;
    
    User user;
    
    DashboardControllerAdmin Admincontroller;

	public DashboardControllerAdmin getAdmincontroller() {
		return Admincontroller;
	}

	public void setAdmincontroller(DashboardControllerAdmin admincontroller) {
		Admincontroller = admincontroller;
	}
	
	public void setUserData(User user) {
		if(user==null) return;
		this.user =user;
		lb_ID.setText("\t"+user.getUserID());

		 try {
		        File file = new File("resources/com/cinema/images/userImage/", user.getImagePath());
		        if (file.exists()) {
		            String uri = file.toURI().toString();
		            System.out.println("Loading image from: " + uri);
		            IVimage.setImage(new Image(uri));
		            double radius = Math.min(IVimage.getFitWidth(), IVimage.getFitHeight()) / 2;
		            Circle clip = new Circle(radius, radius, radius);
		            IVimage.setClip(clip);
		            
		        } else {
		            System.err.println("File not found: " + file.getAbsolutePath());
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }


		lb_name.setText("\t"+user.getUserName());
		lb_userType.setText(user.getUserType());
		lb_phone.setText(user.getPhone());
		lb_gmail.setText(user.getEmail());
		lb_hire_date.setText(user.getHire_Date()+"");
		lb_address.setText(user.getAddress());
		lb_salary.setText(user.getSalary()+"");
		lb_Statatus.setText(user.getStatus());
		Userrow.setOnMouseClicked(e->{
		  Admincontroller.SelectUserRow(user);
		});
		
	}
    
    
    

}
