package com.cinema.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.cinema.models.User;
import com.cinema.utils.moviedatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
	@FXML
	private AnchorPane signin_form;

	@FXML
	private Button signin_loginbtn;

	@FXML
	private Hyperlink signin_createAccount;

	@FXML
	private Button signIn_closebtn;

	@FXML
	private FontAwesomeIcon SignIn_Close;

	@FXML
	private Button signin_Minizebtn;

	@FXML
	private FontAwesomeIcon signin_minize;

	@FXML
	private TextField signin_username;
	
	@FXML
    private ToggleButton togglebutton;
    @FXML
    private Label shownPassword;

    @FXML
    private PasswordField signin_password;
    
 

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;
	
	User user ;

//	private double x = 0;
//	private double y = 0;


	@FXML
	void signin(ActionEvent event) {
	    String sql = "SELECT * FROM user Where name =? and Password=?";

	    connect = moviedatabase.connectDb();
	    try {
	        prepare = connect.prepareStatement(sql);
	        prepare.setString(1, signin_username.getText());
	        prepare.setString(2, signin_password.getText());
	        result = prepare.executeQuery();

	        Alert alert;

	        if (signin_username.getText().isEmpty() || signin_password.getText().isEmpty()) {
	            alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Error Message");
	            alert.setHeaderText(null);
	            alert.setContentText("Please fill all blank fields");
	            alert.showAndWait();

	        } else {
	            if (result.next()) {
	            	
	            	String Status = result.getString("status");
	                String userType = result.getString("usertype");
	                
	                if(Status.equals("Active")) {
	                	 LocalDate hireDate = result.getDate("Hire_Date") != null ? result.getDate("Hire_Date").toLocalDate() : null;
	 					User user = new User(
	 							result.getInt("userid"),
	 							result.getString("name"),
	 							result.getString("email"),
	 							userType,
	 							result.getString("phone"),
	 							hireDate,
	 							result.getString("address"),
	 							result.getInt("salary"),
	 							result.getString("status"),
	 							result.getString("imagepath"),
	 							result.getString("Password")
	 							);
	 					this.user =user;
	 	                alert = new Alert(AlertType.INFORMATION);
	 	                alert.setTitle("Information Message");
	 	                alert.setHeaderText(null);
	 	                alert.setContentText("Successfully Login!");
	 	                alert.showAndWait();
	 	                openLoadingScreen(userType); 
	 	                signin_loginbtn.getScene().getWindow().hide();
	                }else if(Status.equals("Inactive")) {
	                	alert = new Alert(AlertType.ERROR);
		                alert.setTitle("permission denied");
		                alert.setHeaderText("Denied!!!!!");
		                alert.setContentText("You don't have permission");
		                alert.showAndWait();
	                }
	               

	            } else {
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Wrong Username/Password");
	                alert.showAndWait();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
//	public int getUserID(String username, String pass) {
//		int id = 0;
//		String sql = "Select userid  from user where name =? and password =?";
//		try(Connection con = moviedatabase.connectDb();
//				PreparedStatement psmt = con.prepareStatement(sql)){
//			psmt.setString(1, username);
//			psmt.setString(2, pass);
//			ResultSet rs = psmt.executeQuery();
//			if(rs.next()) {
//				id = rs.getInt(1);
//			}
//			
//		} catch (Exception e) {
//			e.rintStackTrace();
//			System.out.println("getUserID error");
//		}
//		return id;	
//	}
	public User getUser() {
		return user;
		
	}
	@FXML
    void togglebutton(ActionEvent event) {
    if(togglebutton.isSelected()) {
       shownPassword.setVisible(true);
       shownPassword.textProperty().bind(Bindings.concat(signin_password.getText()));
       togglebutton.setText("Hide");
     }else {
       shownPassword.setVisible(false);
       togglebutton.setText("Show");
     }
    }
  
   @FXML
      void passwordFieldKeyTyped(KeyEvent event) {
     shownPassword.textProperty().bind(Bindings.concat(signin_password.getText()));
      }

	@FXML
	void signIn_close(ActionEvent event) {
		System.exit(0);
	}

	
	@FXML
	void signIn_minimize(ActionEvent event) {
		Stage stage = (Stage) signin_form.getScene().getWindow();
		stage.setIconified(true);
	}
	private void openLoadingScreen(String role) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/Loading.fxml"));
	        Parent root = loader.load();
	        
	        // Access the controller and pass the user object
	        LoadingController controller = loader.getController();
	        
	        if (controller != null) {
	            controller.setUser(user);  // Make sure user is not null
	        } else {
	            System.err.println("Failed to get LoadingController.");
	        }
	        
	        Stage stage = new Stage();
	        stage.initStyle(StageStyle.TRANSPARENT);
	        stage.setScene(new Scene(root));
	        stage.show();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    public void setUser(User user) {
        if (user != null) {
            this.user = user;
        } else {
            System.err.println("User is null in LoadingController.");
        }
    }


	 @Override
	  public void initialize(URL url, ResourceBundle rb) {
		 Platform.runLater(() -> signin_username.requestFocus());

		 signin_username.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.ENTER) {
			        signin_password.requestFocus();
			        event.consume();
			    }
			});
		 signin_password.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.ENTER) {
			    	event.consume();
			        signin(null);
			    }
			});

	    shownPassword.setVisible(false);
	 
	 if (user != null) {
         System.out.println("User data: " + user.getUserName());
     } else {
         System.err.println("User is not set in LoadingController.");
     }
 }



}
