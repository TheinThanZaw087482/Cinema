package com.cinema.controllers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cinema.models.User;
import com.cinema.models.getData;
import com.cinema.utils.moviedatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class signupController  implements Initializable {
	@FXML
	private AnchorPane signUp_form;

	@FXML
	private Button signUp_btn;

	@FXML
	private TextField SignUp_email;

	@FXML
	private TextField SignUp_username;

	@FXML
	private ComboBox<String> signUp_Combo;
	@FXML
	private ComboBox<String> Current_Combo;

	@FXML
	private PasswordField SignUp_password;

	@FXML
	private DatePicker add_Staff_DatePicker;

	@FXML
	private TextField add_Staff_Salary;

	@FXML
	private TextArea add_Staff_Address;

	@FXML 
	private TextField add_Staff_Phone;

	@FXML 
	private ImageView add_Staff_Image;
	
	@FXML 
	private Label signUpHeader;
	
	DashboardControllerAdmin admincontroller;
	
	public void setAdminController(DashboardControllerAdmin controller) {
	    this.admincontroller = controller;
	}
	
	DashboardControllerstaff staffcontroller;
	public void setStaffController(DashboardControllerstaff staffcontroller) {
		this.staffcontroller=staffcontroller;
	}

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

//	private double x = 0;
//	private double y = 0;

	private String[] currentList_SignUp= {"Admin","Staff"};

	private String[] current = {"Active","Inactive"};
	@FXML
	void SignUp_Combo(ActionEvent event) {
		List<String> listCurrent_SignUp= new ArrayList<>();
		
		for (String data_SignUp : currentList_SignUp) {
			listCurrent_SignUp.add(data_SignUp);
		}
		ObservableList<String> listC_SignUp=FXCollections.observableArrayList(listCurrent_SignUp);
		signUp_Combo.setItems(listC_SignUp);
	}

	@FXML
	void Status_Combo(ActionEvent event) {
		List<String> listCurrent_Status= new ArrayList<>();

		for (String data_Status : current) {
			listCurrent_Status.add(data_Status);
		}
		ObservableList<String> listC_Status=FXCollections.observableArrayList(listCurrent_Status);
		Current_Combo.setItems(listC_Status);
	}

	public int getUserID(String username, String pass) {
		int id = 0;
		String sql = "Select userid  from user where name =? and password =?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setString(1, username);
			psmt.setString(2, pass);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getUserID error");
		}
		return id;	
	}

	public boolean validEmail() {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-z)-9._]*@[a-zA-z0-9]+([.][a-zA-Z]+)+");

		Matcher match = pattern.matcher(SignUp_email.getText());
		Alert alert;
		if (match.find() && match.group().matches(SignUp_email.getText())) {

			return true;
		} else {

			alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Invalid email");
			alert.showAndWait();

			return false;
		}
	}
	
	public boolean validPhone() {
		Pattern pattern = Pattern.compile("^09\\d{7,9}$");
		Matcher match = pattern.matcher(add_Staff_Phone.getText());

		Alert alert;
		if (match.find() && match.group().matches(add_Staff_Phone.getText())) {
			return true;
		} else {

			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Invalid phone");
			alert.showAndWait();
			return false;
		}
	}
	
	public boolean validSalary() {
		Pattern pattern = Pattern.compile("^[1-9]\\d*$");
		Matcher match = pattern.matcher(add_Staff_Salary.getText());

		Alert alert;
		if (match.find() && match.group().matches(add_Staff_Salary.getText())) {

			return true;
		} else {

			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Invalid salary");
			alert.showAndWait();
			return false;
		}
	}
	
	@FXML
	void importImage(){
		FileChooser open = new FileChooser();
		open.setTitle("Open Image File");
		open.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

		Stage stage = (Stage) signUp_form.getScene().getWindow();
		File file = open.showOpenDialog(stage);

		if (file != null) {
			
			Image image = new Image(file.toURI().toString(), 215, 223, false, true);
			add_Staff_Image.setImage(image);
			saveImageToProjectFolder(file);
		}
	}

	// Function to copy the image to "images/" folder
	private void saveImageToProjectFolder(File selectedFile) {
		// Define the target directory relative to the project
		File directory = new File("resources/com/cinema/images/userImage");

		if (!directory.exists()) {
			directory.mkdirs(); // Create the folder if it doesn't exist
		}

		// Get the image file name
		String imageName = selectedFile.getName();
		File destinationFile = new File(directory, imageName);

		try {
			// Copy the selected image to the project's images folder
			Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// Store only the relative path for later retrieval
			getData.path = imageName;
			System.out.println("Image saved successfully: " + destinationFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@FXML
	public void InsertOrUpdate() {
		if(signUp_btn.getText().equals("Add")) {
			signup();
		}else {
			UpdateAccount();
		}
	}
	
	void signup() {
		String sql = "INSERT INTO user (email,name,password, usertype,phone,Hire_Date,address,"
				+ "salary,status,imagepath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		String sql1 = "SELECT name FROM user WHERE name = ?";
		String sql2 = "SELECT email FROM user WHERE email = ?";
		String sql3 = "SELECT phone FROM user WHERE phone = ?";

		connect = moviedatabase.connectDb();
		try {
			Alert alert;
			if (add_Staff_Image.getImage()==null || SignUp_email.getText().isEmpty() || SignUp_username.getText().isEmpty()
					|| SignUp_password.getText().isEmpty() || signUp_Combo.getValue() == null ||
					add_Staff_Phone.getText().isEmpty() || add_Staff_DatePicker.getValue() == null || 
					add_Staff_Address.getText().isEmpty() || add_Staff_Salary.getText().isEmpty() ||
					Current_Combo.getValue() == null) { // Added usertype check

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
				return;
			}

			//check address is already exists
			else if (SignUp_password.getText().length() < 8) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Invalid Password. Password must be at least 8 characters.");
				alert.showAndWait();
				return;
			}
			else if (validEmail() && validPhone() && validSalary()) {
				prepare = connect.prepareStatement(sql1);
				prepare.setString(1, SignUp_username.getText());
				result = prepare.executeQuery();

				prepare = connect.prepareStatement(sql2);
				prepare.setString(1, SignUp_email.getText());
				ResultSet result1 = prepare.executeQuery();
				
				prepare = connect.prepareStatement(sql3);
				prepare.setString(1, add_Staff_Phone.getText());
				ResultSet result2 = prepare.executeQuery();
				
				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText(SignUp_username.getText() + "is already exists.");
					alert.showAndWait();
					return;
				} 
				else if (result1.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText(SignUp_email.getText() + " is already exists.");
					alert.showAndWait();
					return;
				} else if (result2.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText(add_Staff_Phone.getText() + " is already exists.");
					alert.showAndWait();
					return;
				} 
				else {
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, SignUp_email.getText());
					prepare.setString(2, SignUp_username.getText());
					prepare.setString(3, SignUp_password.getText());
					prepare.setString(4, signUp_Combo.getValue()); 
					prepare.setString(5,add_Staff_Phone.getText());
					prepare.setDate(6,Date.valueOf(add_Staff_DatePicker.getValue()));
					prepare.setString(7, add_Staff_Address.getText());
					prepare.setInt(8,Integer.parseInt( add_Staff_Salary.getText()));
					prepare.setString(9, Current_Combo.getValue());
					prepare.setString(10, getData.path);
					prepare.executeUpdate();
				
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully created a new account!");
					alert.showAndWait();
					Stage stage = (Stage) SignUp_email.getScene().getWindow();
					stage.close();
					admincontroller.loadAllUsers();
					SignUp_email.setText("");
					SignUp_username.setText("");
					SignUp_password.setText("");
					signUp_Combo.setValue(null); // Clear Combobox selection
				}
			}

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
		//	alert.setContentText("Username or Email already exists.");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void UpdateAccount() {
	    String sqlCheck = "SELECT name FROM user WHERE name = ?";
	    String sqlUpdate = "UPDATE user SET email = ?, password = ?, usertype = ?, phone = ?, Hire_Date = ?, "
	                     + "address = ?, salary = ?, status = ?, imagepath = ? WHERE name = ?";

	    connect = moviedatabase.connectDb();

	    try {
	        Alert alert;

	        // Field validation
	        if (add_Staff_Image.getImage()==null || SignUp_email.getText().isEmpty() || SignUp_username.getText().isEmpty()
	                || SignUp_password.getText().isEmpty() || signUp_Combo.getValue() == null) {

	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Please fill all blank fields");
	            alert.showAndWait();
	            return;
	        }

	        if (validEmail() && validPhone() && validSalary()) {
	            // Check if the user exists
	            prepare = connect.prepareStatement(sqlCheck);
	            prepare.setString(1, SignUp_username.getText());
	            result = prepare.executeQuery();

	            if (result.next()) {
	                prepare = connect.prepareStatement(sqlUpdate);
	                prepare.setString(1, SignUp_email.getText());
	                prepare.setString(2, SignUp_password.getText());
	                prepare.setString(3, signUp_Combo.getValue());
	                prepare.setString(4, add_Staff_Phone.getText());
	                prepare.setDate(5, Date.valueOf(add_Staff_DatePicker.getValue()));
	                prepare.setString(6, add_Staff_Address.getText());
	                prepare.setInt(7, Integer.parseInt(add_Staff_Salary.getText()));
	                prepare.setString(8, Current_Combo.getValue());
	                prepare.setString(9, getData.path);
	                prepare.setString(10, SignUp_username.getText()); // WHERE name = ?

	                int rowsUpdated = prepare.executeUpdate();

	                if (rowsUpdated > 0) {
	                    alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("User info updated successfully!");
	                    alert.showAndWait();
	                    Stage stage = (Stage) SignUp_email.getScene().getWindow();
						stage.close();
	                    admincontroller.loadAllUsers();

	                    // Clear form fields
	                    SignUp_email.setText("");
	                    SignUp_username.setText("");
	                    SignUp_password.setText("");
	                    signUp_Combo.setValue(null);
	                    add_Staff_Phone.setText("");
	                    add_Staff_DatePicker.setValue(null);
	                    add_Staff_Address.setText("");
	                    add_Staff_Salary.setText("");
	                    Current_Combo.setValue(null);
	                    getData.path = ""; 
	                    add_Staff_Image.setImage(null);
	                } else {
	                    alert = new Alert(AlertType.ERROR);
	                    alert.setTitle("Error Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("No matching user found to update.");
	                    alert.showAndWait();
	                }

	            } else {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("User does not exist.");
	                alert.showAndWait();
	            }

	        } else {
	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Invalid Email Address");
	            alert.showAndWait();
	        }

	    } catch (java.sql.SQLIntegrityConstraintViolationException e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error Message");
	        alert.setHeaderText(null);
	        alert.setContentText("Username or Email already exists.");
	        alert.showAndWait();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public void setSignUpData(User user) {
		 signUp_btn.setText("Update");
		 signUpHeader.setText("Update Staff");
         SignUp_email.setText(user.getEmail());
         SignUp_username.setText(user.getUserName());
         SignUp_username.setEditable(false);
         signUp_Combo.setValue(user.getUserType());
		 Current_Combo.setValue(user.getStatus());
		 SignUp_password.setText(user.getPass());
         SignUp_password.setVisible(false);
         add_Staff_DatePicker.setValue(user.getHire_Date());
         add_Staff_Salary.setText(user.getSalary()+"");
         add_Staff_Address.setText(user.getAddress());
         add_Staff_Phone.setText(user.getPhone());
         getData.path= user.getImagePath();
         try {
		        File file = new File("resources/com/cinema/images/userImage/", user.getImagePath());
		        if (file.exists()) {
		            String uri = file.toURI().toString();
		            System.out.println("Loading image from: " + uri);
		            add_Staff_Image.setImage(new Image(uri));
		            double radius = Math.min(add_Staff_Image.getFitWidth(), add_Staff_Image.getFitHeight()) / 2;
		            Circle clip = new Circle(radius, radius, radius);
		            add_Staff_Image.setClip(clip);
		            
		        } else {
		            System.err.println("File not found: " + file.getAbsolutePath());
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }


		
		
	}



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		SignUp_Combo(null);
		Status_Combo(null);

	}
}

