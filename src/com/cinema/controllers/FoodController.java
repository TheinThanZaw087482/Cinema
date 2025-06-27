package com.cinema.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.cinema.models.FoodData;
import com.cinema.utils.moviedatabase;

import javafx.animation.TranslateTransition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FoodController implements Initializable {
	//start here
	@FXML
	private AnchorPane foodSelling_form;

	@FXML
	private Button food_ch_btn;

	@FXML
	private Button drink_ch_btn;

	@FXML
	private Button set_ch_btn;

	@FXML
	private Button all_ch_btn1;

	@FXML
	private AnchorPane food_receipts;

	@FXML
	private Button delete_fchoice_btn;

	@FXML
	private Button sell_btn;

	@FXML
	private TableView<FoodData> choice_tableview;

	@FXML
	private TableColumn<FoodData, String> choice_food_col;

	@FXML
	private TableColumn<FoodData, Integer> choice_col_price;

	@FXML
	private TableColumn<FoodData, Integer> choice_col_quantity;


	@FXML
	private AnchorPane foodbox;

	@FXML
	private AnchorPane drinkBox;

	@FXML
	private AnchorPane setBox;

	@FXML
	private Label food_totalPrice;

	@FXML
	private AnchorPane food_ch_form;

	@FXML
	private AnchorPane drink_ch_form;

	@FXML
	private AnchorPane set_ch_form;

	@FXML
	private AnchorPane all_ch_form;

	@FXML
	private AnchorPane allbox;

	@FXML
	private GridPane FoodPane;

	FoodData choicefood;

	private ObservableList<FoodData> foodlistdata = FXCollections.observableArrayList(); // Initialize list

	public ObservableList<FoodData> getAll() {
		ObservableList<FoodData> all = FXCollections.observableArrayList();

		String sqla = "SELECT * FROM food;";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmta = con.prepareStatement(sqla);

				ResultSet rs = psmta.executeQuery()) {

			while (rs.next()) {
				FoodData fdata = new FoodData(
						rs.getInt(1), rs.getString(2), rs.getString(3),  rs.getInt(4),
						rs.getString(5));
				all.add(fdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	} 

	public ObservableList<FoodData> getFood() {

		ObservableList<FoodData> food = FXCollections.observableArrayList();

		String sqlf = "SELECT * FROM food where food_category = 'food';";

		try (Connection con = moviedatabase.connectDb();

				PreparedStatement psmtf = con.prepareStatement(sqlf);

				ResultSet rs = psmtf.executeQuery()) {

			while (rs.next()) {
				FoodData fdata = new FoodData(
						rs.getInt(1), rs.getString(2), rs.getString(3),  rs.getInt(4),
						rs.getString(5));
				food.add(fdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return food;
	}

	public ObservableList<FoodData> getDrink() {

		ObservableList<FoodData> drink = FXCollections.observableArrayList();

		String sqld = "SELECT * FROM food where food_category = 'drink';";

		try (Connection con = moviedatabase.connectDb();

				PreparedStatement psmtd = con.prepareStatement(sqld);

				ResultSet rs = psmtd.executeQuery()) {

			while (rs.next()) {
				FoodData fdata = new FoodData(
						rs.getInt(1), rs.getString(2), rs.getString(3),  rs.getInt(4),
						rs.getString(5));
				drink.add(fdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return drink;
	}

	public ObservableList<FoodData> getSet() {

		ObservableList<FoodData> set = FXCollections.observableArrayList();

		String sqls = "SELECT * FROM food where food_category = 'set';";

		try (Connection con = moviedatabase.connectDb();

				PreparedStatement psmts = con.prepareStatement(sqls);

				ResultSet rs = psmts.executeQuery()) {

			while (rs.next()) {
				FoodData fdata = new FoodData(
						rs.getInt(1), rs.getString(2), rs.getString(3),  rs.getInt(4),
						rs.getString(5));
				set.add(fdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}

	void display() throws IOException {
		FoodPane.getColumnConstraints().clear();
		FoodPane.getRowConstraints().clear();
		FoodPane.getChildren().clear();

		foodlistdata.clear();
		foodlistdata.addAll(getAll()); 

		int row = 0;
		int col = 0;

		for (FoodData food : foodlistdata) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/foodcard.fxml"));
			AnchorPane pane = loader.load();
			FoodCardController controller = loader.getController();
			controller.setfoodController(this);
			controller.setfooddata(food);

			FoodPane.add(pane, col, row);

			col++;

			if (col == 4) { // Move to the next row after 4 columns
				col = 0;
				row++;
			}
		}
	}
	@FXML
	void showAll(ActionEvent event) throws IOException {
		display();
	}

	@FXML
	void showDrinks(ActionEvent event)  throws IOException {
		FoodPane.getColumnConstraints().clear();
		FoodPane.getRowConstraints().clear();
		FoodPane.getChildren().clear();

		foodlistdata.clear();
		foodlistdata.addAll(getDrink()); 

		int row = 0;
		int col = 0;

		for (FoodData food : foodlistdata) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/foodcard.fxml"));
			AnchorPane pane = loader.load();
			FoodCardController controller = loader.getController();
			controller.setfoodController(this);
			controller.setfooddata(food);

			FoodPane.add(pane, col, row);

			col++;

			if (col == 4) { // Move to the next row after 4 columns
				col = 0;
				row++;
			}
		}

	}

	@FXML
	void showFood(ActionEvent event)  throws IOException {
		FoodPane.getColumnConstraints().clear();
		FoodPane.getRowConstraints().clear();
		FoodPane.getChildren().clear();

		foodlistdata.clear();
		foodlistdata.addAll(getFood()); 

		int row = 0;
		int col = 0;

		for (FoodData food : foodlistdata) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/foodcard.fxml"));
			AnchorPane pane = loader.load();
			FoodCardController controller = loader.getController();
			controller.setfoodController(this);
			controller.setfooddata(food);

			FoodPane.add(pane, col, row);

			col++;

			if (col == 4) { // Move to the next row after 4 columns
				col = 0;
				row++;
			}
		}
	}

	@FXML
	void showSet(ActionEvent event)  throws IOException {
		FoodPane.getColumnConstraints().clear();
		FoodPane.getRowConstraints().clear();
		FoodPane.getChildren().clear();

		foodlistdata.clear();
		foodlistdata.addAll(getSet()); 

		int row = 0;
		int col = 0;

		for (FoodData food : foodlistdata) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/foodcard.fxml"));
			AnchorPane pane = loader.load();
			FoodCardController controller = loader.getController();
			controller.setfoodController(this);
			controller.setfooddata(food);

			FoodPane.add(pane, col, row);

			col++;

			if (col == 4) { // Move to the next row after 4 columns
				col = 0;
				row++;
			}
		}
	}

	private ObservableList<FoodData> foodList = FXCollections.observableArrayList();

	public void choicedFood(FoodData fooddata) {
		boolean found = false;

		for (FoodData f : foodList) {
			if (f.getFid() == fooddata.getFid()) {
				int newQty = f.getQuantity() + 1;
				f.setQuantity(newQty);
				choice_tableview.refresh();
				found = true;
				break;
			}
		}

		if (!found) {
			fooddata.setQuantity(1);
			foodList.add(fooddata);
		}

		// Set columns
		choice_food_col.setCellValueFactory(new PropertyValueFactory<>("fname"));
		choice_col_price.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		choice_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		choice_tableview.setItems(foodList);

		int totalPrice = 0;
		for (FoodData f : foodList) {
			totalPrice += f.getFprice() * f.getQuantity();
		}

		DecimalFormat formatter = new DecimalFormat("#,###");
		String formatted = formatter.format(totalPrice);
		food_totalPrice.setText("Total Price: " + formatted + " MMK");
	}




	@FXML
	void delete_fchoice(ActionEvent event) {
	    // Retrieve the selected food item from the TableView
	    FoodData selectedFood = choice_tableview.getSelectionModel().getSelectedItem();
	    
	    if (selectedFood != null) {
	        // Remove the selected food item from the ObservableList
	        foodList.remove(selectedFood);
	        choice_tableview.getSelectionModel().clearSelection();

	        // Recalculate the total price after deletion
	        int totalPrice = 0;
	        for (FoodData food : foodList) {
	            totalPrice += food.getFprice() * food.getQuantity();
	        }

	        // Format the total price
	        DecimalFormat formatter = new DecimalFormat("#,###");
	        String formatted = formatter.format(totalPrice);

	        // Update the total price label
	        food_totalPrice.setText("Total Price: " + formatted + " MMK");
	    }
	}


	LocalDateTime now = LocalDateTime.now();

	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d/M/yyyy");
	String foodDate = now.format(dateformatter);

	DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("h:mm a");
	String foodTime = now.format(timeformatter);

	ObservableList<String> foodName;
	ObservableList<String> foodPrice;
	int totalPrice = 0;

	@FXML
	void sell_Food(ActionEvent event) throws IOException {


		ObservableList<String> foodName = FXCollections.observableArrayList();  // Initialize the list
		ObservableList<String> foodPrice = FXCollections.observableArrayList();  // Initialize the list

		for (FoodData food : foodList) {
			int itemTotal = food.getFprice() * food.getQuantity(); 
			totalPrice += itemTotal;
			foodName.add(food.getFname() + "   x   " + food.getQuantity());
			foodPrice.add(itemTotal + " MMK");
		}


		DecimalFormat formatter = new DecimalFormat("#,###");
		String formattedPrice = formatter.format(totalPrice);

		// Set the total price label
		food_totalPrice.setText("Total Price: " + formattedPrice + " MMK");

		// Call the food popup method
		if (foodList != null) {
			food_popup(foodDate, foodTime, foodName, foodPrice, formattedPrice);  // Pass the lists to the popup
		}

		// Clear the food list and reset the table selection
		foodList.clear();
		totalPrice = 0;
		food_totalPrice.setText(null);
		choice_tableview.getSelectionModel().clearSelection();
	}

	void food_popup(String foodDate, String foodTime, ObservableList<String> foodName, ObservableList<String> foodPrice, String totalCost) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/food_popup.fxml"));
		Parent p = fxmlLoader.load();
		FoodReceipts fr = fxmlLoader.getController();

		// Pass data to the popup controller
		fr.set_food_date_Label(foodDate);
		fr.set_food_time_Label(foodTime);
		fr.set_foodList(foodName, foodPrice);
		fr.set_total_cost_Label(totalCost);

		// Show the popup
		Stage ps = new Stage();
		ps.initModality(Modality.APPLICATION_MODAL); // Blocks the main window
		ps.setTitle("Receipts");
		ps.setScene(new Scene(p));
		ps.showAndWait();
	}

	//    private DashboardControllerstaff dashboardController; // Add this as a field
	//
	//   public void setFoodDashboardController(DashboardControllerstaff controller) {
	//       this.dashboardController = controller;
	//   }

	private void setupButtonAnimation(Button button) {
		DropShadow normalShadow = new DropShadow(25, Color.rgb(0, 0, 0, 0.2));
		normalShadow.setOffsetX(15);
		normalShadow.setOffsetY(15);

		DropShadow hoverShadow = new DropShadow(10, Color.rgb(0, 0, 0, 0.3));
		hoverShadow.setOffsetX(2);
		hoverShadow.setOffsetY(2);

		button.setEffect(normalShadow);

		button.setOnMouseEntered(e -> {
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), button);
			tt.setToY(2);
			tt.play();

			button.setEffect(hoverShadow);
		});

		button.setOnMouseExited(e -> {
			TranslateTransition tt = new TranslateTransition(Duration.millis(200), button);
			tt.setToY(0);
			tt.play();

			button.setEffect(normalShadow);
		});
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			display();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		choice_food_col.setCellValueFactory(new PropertyValueFactory<>("fname"));
		choice_col_price.setCellValueFactory(new PropertyValueFactory<>("fprice"));

		choice_tableview.setItems(foodList); 

		setupButtonAnimation(all_ch_btn1);
	}




}