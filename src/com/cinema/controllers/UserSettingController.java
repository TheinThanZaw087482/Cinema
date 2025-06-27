package com.cinema.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.cinema.models.User;
import com.cinema.utils.moviedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class UserSettingController implements Initializable {

	@FXML
	private AnchorPane category;

	@FXML
	private AnchorPane changeform;



	@FXML
	private PasswordField currentpwd;

	@FXML
	private PasswordField newpwd;

	@FXML
	private PasswordField confirmpwd;

	@FXML
	private Button back_btn;
	@FXML
	private Button choiceChangePwd;

	@FXML
	private Button btn_change;
	
	User CurrentUser;
	
	DashboardControllerstaff dashboardstaff;




	@FXML
	public void backMain() {
		category.setVisible(true);
		changeform.setVisible(false);

	}
	@FXML
	public void toChangePwd() {
		category.setVisible(false);
		changeform.setVisible(true);
		
	}
	public void ChangePassword() {
	    String sql = "UPDATE `user` SET `password` = ? WHERE `user`.`userid` = ?";
	    try (Connection con = moviedatabase.connectDb();
	         PreparedStatement psmt = con.prepareStatement(sql)) {

	        psmt.setString(1, confirmpwd.getText());
	        psmt.setInt(2, CurrentUser.getUserID());

	        int rowsAffected = psmt.executeUpdate(); 

	        if (rowsAffected > 0) {
	            new Alert(AlertType.INFORMATION, "Password changed successfully!", ButtonType.OK).showAndWait();
	            backMain();
	        } else {
	            new Alert(AlertType.ERROR, "Failed to update password!", ButtonType.OK).showAndWait();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	@FXML 
	public void PwdChangeAction() {
		
		if(currentpwd.getText().isEmpty() || newpwd.getText().isEmpty() || confirmpwd.getText().isEmpty()) {
			new Alert(AlertType.ERROR ,"Please fill all blank fields",ButtonType.OK).showAndWait();
		}else if(!newpwd.getText().equals(confirmpwd.getText())) {
			new Alert(AlertType.ERROR ,"New Password and Confirm Password must be the same",ButtonType.OK).showAndWait();
			confirmpwd.requestFocus();
			
		}else if(!CurrentUser.getPass().equals(currentpwd.getText())) {
			new Alert(AlertType.ERROR ,"Incorrct Current Passwrod!",ButtonType.OK).showAndWait();
			currentpwd.requestFocus();
			
		}else if(confirmpwd.getText().length()<8) {
			new Alert(AlertType.ERROR ,"Password must be at least 8 characters!",ButtonType.OK).showAndWait();
		}else {
			ChangePassword();
		}
		
	}
	public void setStaffController(DashboardControllerstaff dashboardstaff ) {
		this.dashboardstaff =dashboardstaff;
	}
	public void setCurrentUser(User CurrentUser) {
		this.CurrentUser =CurrentUser;	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backMain();
		

	}
	




}
