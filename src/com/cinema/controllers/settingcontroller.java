package com.cinema.controllers;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.cinema.models.User;
import com.cinema.utils.moviedatabase;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class settingcontroller extends Application implements Initializable {


	@FXML
	private AnchorPane AddRoomForm;

	@FXML
	private TextField room;

	@FXML
	private Button addroom;

	@FXML
	private AnchorPane ChangePriceForm;
	
	@FXML
	private AnchorPane changePasswordform;

	@FXML
	private TextField singleseat;

	@FXML
	private TextField preseat;
	
	@FXML
	private TextField currentpwd;
	
	@FXML
	private TextField newpwd;
	
	@FXML
	private TextField confirmpwd;

	@FXML
	private TextField coupleseat;

	@FXML
	private Button singleadd;

	@FXML
	private Button singledecrease;

	@FXML
	private Button preadd;

	@FXML
	private Button predecrease;

	@FXML
	private Button coupleadd;

	@FXML
	private Button coupledecrease;

	@FXML
	private Button addseat1;

	@FXML
	private TextField singleseat11;

	@FXML
	private TextField preseat11;

	@FXML
	private TextField coupleseat11;

	@FXML
	private Button singleadd11;

	@FXML
	private Button singledecrease11;

	@FXML
	private Button preadd11;

	@FXML
	private Button predecrease11;

	@FXML
	private Button coupleadd11;

	@FXML
	private Button coupledecrease11;

	@FXML
	private Button addseat11;

	@FXML
	private TextField room11;

	@FXML
	private Button addroom11;

	@FXML
	private AnchorPane MenuForm;

	@FXML
	private Button btn_Add_Room;

	@FXML
	private Button btn_Change_Price;
	
	@FXML
	private Button btn_Change_Password;
	
	DashboardControllerAdmin admincontroller;
	
	
	
	User CurrentUser;
	
	

	private Connection connect;
	private PreparedStatement prepare;

	@FXML
	void addroom(ActionEvent event) {
		String sql = "INSERT INTO `room` (`roomname`) VALUES (?)";

		connect = moviedatabase.connectDb();
		Alert alert;

		try {

			prepare=connect.prepareStatement(sql);

			prepare.setString(1, room.getText()); // Set parameter 2 (roomname)
			prepare.executeUpdate();
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("Successfully updated ");
			alert.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addseat(ActionEvent event) {
		String sql_single = "UPDATE seat SET price = ? WHERE type = 'Front Standard';";
		String sql_pre = "UPDATE seat SET price = ? WHERE type = 'Premium';";
		String sql_couple = "UPDATE seat SET price = ? WHERE type = 'Couple';";
		connect = moviedatabase.connectDb();
		Alert alert;

		try {

			prepare = connect.prepareStatement(sql_single);
			prepare.setString(1, singleseat.getText());
			prepare.executeUpdate();

			// Update Premium seats
			prepare = connect.prepareStatement(sql_pre);
			prepare.setString(1, preseat.getText());
			prepare.executeUpdate();

			// Update Couple seats
			prepare = connect.prepareStatement(sql_couple);
			prepare.setString(1, coupleseat.getText());
			prepare.executeUpdate();

			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Message");
			alert.setHeaderText(null);
			alert.setContentText("Successfully updated ");
			alert.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void coupleaddact(ActionEvent event) {
		try {
			int currentPrice = Integer.parseInt(coupleseat.getText());
			coupleseat.setText(String.valueOf(currentPrice + 500));
		} catch (NumberFormatException e) {
			// Handle the case where the text field doesn't contain a valid number
			coupleseat.getText();// Or any default starting value
		}
	}

	@FXML
	void coupledecreact(ActionEvent event) {
		try {
			int currentPrice = Integer.parseInt(coupleseat.getText());
			coupleseat.setText(String.valueOf(currentPrice - 500));
		} catch (NumberFormatException e) {
			// Handle the case where the text field doesn't contain a valid number
			coupleseat.getText();// Or any default starting value
		}
	}

	@FXML
	void preaddact(ActionEvent event) {
		try {
			int currentPrice = Integer.parseInt(preseat.getText());
			preseat.setText(String.valueOf(currentPrice + 500));
		} catch (NumberFormatException e) {
			// Handle the case where the text field doesn't contain a valid number
			preseat.getText();// Or any default starting value
		}
	}

	@FXML
	void predecreact(ActionEvent event) {
		try {
			int currentPrice = Integer.parseInt(preseat.getText());
			preseat.setText(String.valueOf(currentPrice - 500));
		} catch (NumberFormatException e) {
			// Handle the case where the text field doesn't contain a valid number
			preseat.getText();// Or any default starting value
		}
	}

	@FXML
	void singleaddact(ActionEvent event) {

		try {
			int currentPrice = Integer.parseInt(singleseat.getText());
			singleseat.setText(String.valueOf(currentPrice + 500));
		} catch (NumberFormatException e) {
			// Handle the case where the text field doesn't contain a valid number
			singleseat.getText();// Or any default starting value
		}

	}

	void selectSingleSeat() {
		String sql = "SELECT price FROM seat WHERE type = 'Front Standard'";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) { // Ensure data is available
				int price = rs.getInt(1);
				if (singleseat != null) {
					singleseat.setText(String.valueOf(price)); // Set price
				} else {
					System.out.println("Error: singleseat is null!");
				}
			} else {
				singleseat.setText("Not Found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			singleseat.setText("Error");
		}
	}

	void selectPremiumSeat() {
		String sql = "SELECT price FROM seat WHERE type = 'Premium'";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) { // Ensure data is available
				int price = rs.getInt(1);
				if (preseat != null) {
					preseat.setText(String.valueOf(price)); // Set price
				} else {
					System.out.println("Error: preseat is null!");
				}
			} else {
				preseat.setText("Not Found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			preseat.setText("Error");
		}
	}

	void selectCoupleSeat() {
		String sql = "SELECT price FROM seat WHERE type = 'Couple'";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) { // Ensure data is available
				int price = rs.getInt(1);
				if (coupleseat != null) {
					coupleseat.setText(String.valueOf(price)); // Set price
				} else {
					System.out.println("Error: coupleseat is null!");
				}
			} else {
				coupleseat.setText("Not Found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			coupleseat.setText("Error");
		}
	}

	@FXML
	void singledecreact(ActionEvent event) {
		try {
			int currentPrice = Integer.parseInt(singleseat.getText());
			singleseat.setText(String.valueOf(currentPrice - 500));
		} catch (NumberFormatException e) {
			// Handle the case where the text field doesn't contain a valid number
			singleseat.getText();// Or any default starting value
		}

	}
	@FXML
	public void ShowChangePriceForm() {
		MenuForm.setVisible(false);
		ChangePriceForm.setVisible(true);
		

	}
	@FXML
	public void ShowAddRoomForm() {
		MenuForm.setVisible(false);
		AddRoomForm.setVisible(true);
		

	}
	@FXML
	public void BackMainAction() {
		MenuForm.setVisible(true);
		AddRoomForm.setVisible(false);
		ChangePriceForm.setVisible(false);
		changePasswordform.setVisible(false);
	}
	@FXML
	public void ShowChangePwdForm() {
		MenuForm.setVisible(false);
		changePasswordform.setVisible(true);
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
	
	public void ChangePassword() {
	    String sql = "UPDATE `user` SET `password` = ? WHERE `user`.`userid` = ?";
	    try (Connection con = moviedatabase.connectDb();
	         PreparedStatement psmt = con.prepareStatement(sql)) {

	        psmt.setString(1, confirmpwd.getText());
	        psmt.setInt(2, CurrentUser.getUserID());

	        int rowsAffected = psmt.executeUpdate(); 

	        if (rowsAffected > 0) {
	            new Alert(AlertType.INFORMATION, "Password changed successfully!", ButtonType.OK).showAndWait();
	            BackMainAction();
	        } else {
	            new Alert(AlertType.ERROR, "Failed to update password!", ButtonType.OK).showAndWait();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void SetDashboardAdminController(DashboardControllerAdmin admincontroller) {
		this.admincontroller = admincontroller;
		
	}
	@FXML
	 private Button backup_btn;
	
	@FXML
	 private Button restore_btn;
	 
	  @FXML
	     void handleBackup(ActionEvent event) {
	      FileChooser fileChooser = new FileChooser();
	         fileChooser.setTitle("Select Backup File Location");

	         // Extension Filter
	         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL Files", "*.sql"));

	         // Default Directory (relative path, eg: backup folder inside project)
	         File defaultDir = new File("backup"); // relative to project folder
	         if (!defaultDir.exists()) {
	             defaultDir.mkdir(); // create if not exists
	         }
	         fileChooser.setInitialDirectory(defaultDir);

	         // Default file name
	         fileChooser.setInitialFileName("backup_" + getDateString() + ".sql");

	         // Show save dialog
	         File file = fileChooser.showSaveDialog(null);
	         if (file != null) {
	             backupDatabase(file.getPath());  // full absolute path
	         }
	     }
	  
	     private String getDateString() {
	      LocalDateTime now = LocalDateTime.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	      return now.format(formatter);
	     }


	 public void backupDatabase(String savePath) {
	     String user = "root";
	     String password = ""; // XAMPP default is empty
	     String database = "cinemadb"; 

	     try {
	         // mysqldump command (with --password=)
	         String command = "C:\\xampp\\mysql\\bin\\mysqldump.exe -u" + user +
	                          " --password=" + password + " " + database;

	         // Windows CMD command: mysqldump > output.sql
	         ProcessBuilder pb = new ProcessBuilder(
	             "cmd.exe", "/c", command + " > \"" + savePath + "\""
	         );

	  
	     pb.redirectErrorStream(true);
	     Process process = pb.start();
	     int exitCode = process.waitFor();

	     Alert alert = new Alert(Alert.AlertType.INFORMATION);

	     if (exitCode == 0) {
	         System.out.println("Backup completed: " + savePath);
	         
	         alert.setAlertType(Alert.AlertType.INFORMATION);
	         alert.setTitle("Backup successful");
	         alert.setHeaderText(null);
	         alert.setContentText("Backup completed successfully");
	     } else {
	         alert.setAlertType(Alert.AlertType.ERROR);
	         alert.setTitle("Backup successful");
	         alert.setHeaderText("An error occurred");
	         alert.setContentText("Backup Failed");
	     }
	     alert.showAndWait();

	 } catch (Exception e) {
	     e.printStackTrace();
	 }
	}
	//Dashboardcontrolleradmin.java
	 
	 @FXML
	 private void onRestore(ActionEvent event) {
	     FileChooser fileChooser = new FileChooser();
	     fileChooser.setTitle("Select Backup File");

	     // Extension Filter
	     fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SQL Files", "*.sql"));

	     File defaultDir = new File("backup"); 
	     if (!defaultDir.exists()) {
	         defaultDir.mkdir();
	     }
	     fileChooser.setInitialDirectory(defaultDir);

	     File file = fileChooser.showOpenDialog(null);
	     if (file != null) {
	         restoreDatabase(file.getPath());
	     }
	 }

	 public void restoreDatabase(String filePath) {
	     String user = "root";
	     String password = ""; 
	     String database = "cinemadb"; 

	     try {
	         String[] command = new String[]{
	             "C:\\xampp\\mysql\\bin\\mysql.exe",
	             "-u" + user,
	             "--password=" + password,
	             database,
	             "-e",
	             "source " + filePath
	         };

	         ProcessBuilder pb = new ProcessBuilder(command);
	         pb.redirectErrorStream(true);
	         Process process = pb.start();

	         int exitCode = process.waitFor();
		     Alert alert = new Alert(Alert.AlertType.INFORMATION);

		     if (exitCode == 0) {
		         
		         alert.setAlertType(Alert.AlertType.INFORMATION);
		         alert.setTitle("Restore successful");
		         alert.setHeaderText(null);
		         alert.setContentText("Restore completed successfully");
		     } else {
		         alert.setAlertType(Alert.AlertType.ERROR);
		         alert.setTitle("Restore successful");
		         alert.setHeaderText("An error occurred");
		         alert.setContentText("Restore Failed");
		     }
		     alert.showAndWait();

	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	 }
	


	public static void main(String[] args) {
		launch(args); // Launch JavaFX application
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		selectSingleSeat();
		selectPremiumSeat();
		selectCoupleSeat();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader.load(getClass().getResource("/com/cinema/views/setting.fxml")); 
		primaryStage.setTitle("Movie Theater Settings");

		primaryStage.show();

	}



}
