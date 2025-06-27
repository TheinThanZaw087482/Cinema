package com.cinema.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cinema.models.Booking;
import com.cinema.models.MovieData;
import com.cinema.models.Sale;
import com.cinema.models.User;
import com.cinema.utils.moviedatabase;
import com.jfoenix.controls.JFXTimePicker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DashboardControllerstaff implements Initializable {
	@FXML
	private AnchorPane topform;

	@FXML
	private Button minimize;

	@FXML
	private Button closebtn;

	@FXML
	private Label titlelabel;

	@FXML
	private Label username;

	@FXML
	private Button dashboard_btn;

	@FXML
	private Button theater_btn;

	@FXML
	private Button booking_btn;

	@FXML
	private Button SignOut;

	@FXML
	private Button selling_btn;

	@FXML
	private AnchorPane dashboard_form;

	@FXML
	private Label ch_title;

	@FXML
	private ImageView choiced_image;

	@FXML
	private Label ch_description;

	@FXML
	private Label ch_date;

	@FXML
	private Label ch_duration;

	@FXML
	private Label ch_genere;

	@FXML
	private Button btn_toRoom;

	@FXML
	private HBox nowshowingbox;

	@FXML
	private HBox avaliable_date_box;
	@FXML
	private HBox avaliable_time_box;

	@FXML
	private HBox upcomingbox;

	@FXML
	private AnchorPane theater_form;

	@FXML
	private ImageView room_movie;

	@FXML
	private Label selectedmovie_movieTitle;

	@FXML
	private Label selectedmovie_genre;

	@FXML
	private Label selectedmovie_duration;

	@FXML
	private Label selectedmovie_description;

	@FXML
	private GridPane seatpane;

	@FXML
	private AnchorPane booking_sale_form;

	@FXML
	private Label lb_choiced_seat;

	@FXML
	private Label total_price1;

	@FXML
	private Button btn_booking;

	@FXML
	private Button btn_sale;

	@FXML
	private Label total_seat1;

	@FXML
	private AnchorPane cs_input_form;

	@FXML
	private Label lb_choiced_seat2;

	@FXML
	private Label total_price2;

	@FXML
	private Label total_seat2;

	@FXML
	private Button booking_cancel;

	@FXML
	private Button Booking_Confirm;

	@FXML
	private TextField txt_cs_name;

	@FXML
	private TextField txt_cs_phone;

	@FXML
	private TextField txt_cs_remark;

	@FXML
	private AnchorPane booking_form;

	@FXML
	private Button btn_booking_delete;

	@FXML
	private Button btn_booking_edit;

	@FXML
	private Button btn_booking_sale;

	

	MovieData choicedMovie;

	Booking selected_booking;

	MovieData updateBooking;

	int showID;
	LocalTime showTime;
	LocalDate ShowDate;
	String choice_room;
	LocalDate TodayDate = LocalDate.now();
	LocalTime nowTime = LocalTime.now();

	private ArrayList<Button> seatlist = new ArrayList<>();
	private ArrayList<Button> choicelist = new ArrayList<>();
	private ArrayList<String> seatnolist = new ArrayList<>();

	private Image availableImage = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/available.png"));
	private Image bookedImage = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/booked.png"));
	private Image soldImage = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/sold.png"));
	private Image selectedImage = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/selected.png"));
	private Image coup_available = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/couple_available.png"));
	private Image coup_booked = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/couple_booked.png"));
	private Image coup_sold = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/couple_sold.png"));
	private Image coup_selected = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/couple_selected.png"));

	private double x = 0;
	private double y = 0;

	@FXML
	void closebtn(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void logout(ActionEvent event) {
	    // Hide the current window
	    Stage currentStage = (Stage) SignOut.getScene().getWindow();
	    currentStage.close(); // (prefer close() instead of hide() when logging out)

	    try {
	        // Load login.fxml
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/login.fxml"));
	        Parent root = loader.load();

	        // Create new stage
	        Stage stage = new Stage();
	        Scene scene = new Scene(root);

	        // Make the window draggable
	        root.setOnMousePressed((MouseEvent e) -> {
	            x = e.getSceneX();
	            y = e.getSceneY();
	        });

	        root.setOnMouseDragged((MouseEvent e) -> {
	            stage.setX(e.getScreenX() - x);
	            stage.setY(e.getScreenY() - y);
	        });

	        // Set transparent style
	        stage.initStyle(StageStyle.TRANSPARENT);
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        System.err.println("Failed to load login page.");
	        e.printStackTrace();
	    }
	}
	@FXML
	void minimize(ActionEvent event) {
		Stage stage = (Stage) topform.getScene().getWindow();
		stage.setIconified(true);
	}



	// Method to edit booking and switch to the theater form

	public void createSeat() {
		seatlist.clear();
		int row = 0;
		int col = 0;
		for (char rowLabel = 'A'; rowLabel <= 'I'; rowLabel++) {
			for (int seatno = 1; seatno <= 10; seatno++) {
				String id = rowLabel + Integer.toString(seatno);

				Label rowlabel = new Label(rowLabel + "");
				ImageView imageView = new ImageView(availableImage);

				Button button = new Button();
				imageView.setFitWidth(75);
				imageView.setFitHeight(75);
				button.setGraphic(imageView);
				button.setStyle("-fx-background-color: transparent");
				button.setId(id);

				seatlist.add(button);

				seatpane.add(button, col, row);
				seatpane.add(rowlabel, 0, row);

				button.setOnAction(e -> handleSeatClick(button, button.getId()));
				if (seatno == 5) {
					col += 2;
				} else {
					col++;
				}
			}
			row++;
			col = 0;
		}

		char coupleRowLabel = 'J';

		for (int seatno = 1; seatno <= 5; seatno++) { // Only 5 couple seats in row J
			String id = coupleRowLabel + Integer.toString(seatno);

			Label rowlabel = new Label(coupleRowLabel + "");
			ImageView imageView = new ImageView(coup_available); // Use couple seat image

			Button button = new Button();
			imageView.setFitWidth(75);
			imageView.setFitHeight(75);
			button.setGraphic(imageView);
			button.setStyle("-fx-background-color: transparent");
			button.setId(id);

			seatlist.add(button);
			seatpane.add(button, col + 1, row); // Add couple seat to grid

			seatpane.add(rowlabel, 0, row); // Add row label to grid

			button.setOnAction(e -> handleSeatClick(button, button.getId()));

			col += 2; // Increment column for the next couple seat
		}

		// Ensure there's no space between seats
		seatpane.setHgap(0); // Remove horizontal gap between buttons (seats)
		seatpane.setVgap(0); // Remove vertical gap between rows

	}
	public boolean validBookingName() {

		Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");

		Matcher match = pattern.matcher(txt_cs_name.getText());
		Alert alert;
		if (match.find() && match.group().matches(txt_cs_name.getText())) {

			return true;
		} else {

			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Invalid Customer Name");
			alert.showAndWait();
			return false;
		}
	}
	
	public boolean validPhone() {
		Pattern pattern = Pattern.compile("^09\\d{7,9}$");
		Matcher match = pattern.matcher(txt_cs_phone.getText());

		Alert alert;
		if (match.find() && match.group().matches(txt_cs_phone.getText())) {

			return true;
		} else {

			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Invalid Phone Number");
			alert.showAndWait();
			return false;
		}
	}
	@FXML
	public void BookingConfirmOnAction() {
		String name = txt_cs_name.getText().trim(); // Trim to remove leading/trailing whitespace
		String phone = txt_cs_phone.getText().trim();
		String remark = txt_cs_remark.getText().trim();

		// Check if name or phone is blank
		if (name.isEmpty() || phone.isEmpty()) {
			new Alert(AlertType.ERROR, "Please input name and phone", ButtonType.OK).showAndWait();
			return;
		}  
		if(!validBookingName() || !validPhone()) { 
			//new Alert(AlertType.ERROR, "Wrong Input", ButtonType.OK).showAndWait();
			return;
		}else if (choicelist == null || choicelist.isEmpty()) {
			new Alert(AlertType.ERROR, "Please select at least one seat", ButtonType.OK).showAndWait();
			return;
		} else if (Booking_Confirm.getText().equals("Confirm")) {
			bookingSeat(name, phone, remark);
			disableSeats(showID);
			refreshSeat();
		} else {
			updateSeats(selected_booking.getCustomerid(), selected_booking.getShowID(), seatnolist);
			updateCustomer(selected_booking.getCustomerid(), name, phone, remark);
			disableSeats(selected_booking.getShowID());
			refreshSeat();
			refreshBookingTable();
			Booking_Confirm.setText("Confirm");
		}

	}

	@FXML
	public void BookingCancelOnAction() {
		booking_sale_form.setVisible(true);
		cs_input_form.setVisible(false);
		disableSeats(showID);
		Booking_Confirm.setText("Confirm");
		txt_cs_name.clear();
		txt_cs_phone.clear();
		txt_cs_remark.clear();
		refreshSeat();
	}

	@FXML
	public void BookingOnAction() {
		Booking_Confirm.setText("Confirm");
		booking_sale_form.setVisible(false);
		cs_input_form.setVisible(true);
	}

	@FXML
	public void BookingToSaleOnAction() {
		Booking booked = booking_table.getSelectionModel().getSelectedItem();
		if (booked != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to complete this sale?",
					ButtonType.OK, ButtonType.CANCEL);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				// User confirmed the sale
				SalebookingSeat(booked);

				refreshBookingTable();
				refreshSaleTable();
				disableSeats(booked.getShowID());
			}else {
				return;
			}
			
		} else {
			new Alert(AlertType.ERROR, "Please select a row first!", ButtonType.OK).showAndWait();
		}

	}

	public void handleSeatClick(Button seatButton, String seatId) {
		ImageView imageView = (ImageView) seatButton.getGraphic();
		if (imageView != null) {
			Image currentImage = imageView.getImage();
			if (currentImage.equals(selectedImage)) {
				imageView.setImage(availableImage);
				choicelist.remove(seatButton);
				seatnolist.remove(seatId);
			} else if (currentImage.equals(bookedImage)) {
				new Alert(AlertType.INFORMATION, "This Seat is already booked").show();
				// choicelist.add(seatButton);
				// seatnolist.add(seatId);
				// imageView.setImage(soldImage);
				// seatButton.setDisable(true);
				// sellSeat(seatId);
			} else if (currentImage.equals(coup_selected)) {
				imageView.setImage(coup_available);
				choicelist.remove(seatButton);
				seatnolist.remove(seatId);
			} else if (currentImage.equals(coup_booked)) {
				new Alert(AlertType.CONFIRMATION, "This Seat is already booked", ButtonType.OK).show();

			} else if (currentImage.equals(availableImage)) {
				choicelist.add(seatButton);
				seatnolist.add(seatId);
				imageView.setImage(selectedImage);
			} else {
				choicelist.add(seatButton);
				seatnolist.add(seatId);
				imageView.setImage(coup_selected);

			}
		}
		lb_choiced_seat.setText(seatnolist.toString());
		lb_choiced_seat2.setText(seatnolist.toString());
		total_price1.setText(getTotalPrice() + "");
		total_price2.setText(getTotalPrice() + "");
		total_seat1.setText(choicelist.size() + "");
		total_seat2.setText(choicelist.size() + "");
	}

	public void disableSeats(int ShowID) {

		String sql = "CALL `getAllSeatByShowID`(?)";

		try (Connection con = moviedatabase.connectDb(); CallableStatement csmt = con.prepareCall(sql)) {
			csmt.setInt(1, ShowID);

			ResultSet rs = csmt.executeQuery();

			// Create a map to store seat status (sold or booked)
			Map<String, String> seatStatusMap = new HashMap<>();
			while (rs.next()) {
				String seatId = rs.getString("seatid");
				String status = rs.getString("status");
				seatStatusMap.put(seatId, status);
			}

			// Update seat buttons based on the seat status
			for (Button seatButton : seatlist) {
				String seatId = seatButton.getId();
				ImageView imageView = (ImageView) seatButton.getGraphic();

				if (seatStatusMap.containsKey(seatId)) {
					// Seat is either sold or booked
					String status = seatStatusMap.get(seatId);
					if (status.equals("sold")) {
						// Seat is sold
						if (!check_couple(seatId)) {
							imageView.setImage(soldImage);
							seatButton.setDisable(true);
						} else {
							imageView.setImage(coup_sold);
							seatButton.setDisable(true);
						}
					} else if (status.equals("booked")) {

						if (!check_couple(seatId)) {
							imageView.setImage(bookedImage);
						} else {
							imageView.setImage(coup_booked);
						}
					}
				} else {
					// Seat is available
					if (!check_couple(seatId)) {
						imageView.setImage(availableImage);
						seatButton.setDisable(false);
					} else {
						imageView.setImage(coup_available);
						seatButton.setDisable(false);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void refreshSeat() {
		seatnolist.clear();
		choicelist.clear();
		lb_choiced_seat.setText("0");
		lb_choiced_seat2.setText("0");
		total_price1.setText("0");
		total_price2.setText("0");
		total_seat1.setText("0");
		total_seat2.setText("0");
		txt_cs_name.clear();
		txt_cs_phone.clear();
		txt_cs_remark.clear();
		booking_sale_form.setVisible(true);
		cs_input_form.setVisible(false);

	}

	public int getTotalPrice() {
		int totalprice = 0;
		if (!choicelist.isEmpty()) {
			String sql = "SELECT price FROM seat WHERE seatid = ?";
			try (Connection con = moviedatabase.connectDb()) {
				for (Button b : choicelist) {

					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, b.getId());
					ResultSet rs = psmt.executeQuery();
					if (rs.next()) {
						totalprice += rs.getInt(1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalprice;
	}
	public int getSeatPricebySeatID(String seatID) {
		int price =0;
		String sql = "SELECT price FROM seat WHERE seatid =?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql);) {
			psmt.setString(1, seatID);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				price = rs.getInt("price");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return price;
	}

	public int getSalePriceTotal(ArrayList<String> seatnolist) {
		int totalPrice = 0;
		String sql = "SELECT price FROM seat WHERE seatid = ?";

		try (Connection con = moviedatabase.connectDb(); PreparedStatement psmt = con.prepareStatement(sql)) {

			for (String seat : seatnolist) {
				psmt.setString(1, seat);
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					totalPrice += rs.getInt("price");
				}
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalPrice;
	}

	public void bookingSeat(String name, String phno, String remark) {
		// SQL queries
		String customerRegisterQuery = "INSERT INTO customer (customername, phone, remark) VALUES (?, ?, ?)";
		String getCustomerIdQuery = "SELECT customerid  FROM customer WHERE name = ? AND phone = ?";
		String bookingInsertQuery = "INSERT INTO booking (showid , seatid , customerid  ,BookedDate,userid) VALUES (?, ?, ?, ?,?)";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement customerStmt = con.prepareStatement(customerRegisterQuery,
						Statement.RETURN_GENERATED_KEYS);
				PreparedStatement getIdStmt = con.prepareStatement(getCustomerIdQuery);
				PreparedStatement bookingStmt = con.prepareStatement(bookingInsertQuery)) {

			// Insert customer details
			customerStmt.setString(1, name);
			customerStmt.setString(2, phno);
			customerStmt.setString(3, remark);

			customerStmt.executeUpdate();

			// Retrieve the generated customer ID
			int custId;
			try (ResultSet generatedKeys = customerStmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					custId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Failed to retrieve customer ID.");
				}
			}

			// Insert booking details for each seat
			for (String seatNo : seatnolist) {
				bookingStmt.setInt(1, showID);
				bookingStmt.setString(2, seatNo);
				bookingStmt.setInt(3, custId);
				bookingStmt.setObject(4, TodayDate);
				bookingStmt.setInt(5, CurrentUser.getUserID());
				bookingStmt.addBatch();
			}

			// Execute all booking inserts in a batch
			bookingStmt.executeBatch();
			refreshBookingTable();
			new Alert(AlertType.INFORMATION, "Sucessfully Input booking ", ButtonType.OK).showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("bookingSeat error");
		}
	}

	public void SalebookingSeat(Booking b) {
		ArrayList<String> seatnolist = getSeatByCusID(b.getCustomerid(),b.getShowID());

		try (Connection con = moviedatabase.connectDb()) {
			String voucherNo = generateUniqueVoucherNum(con);
			con.setAutoCommit(false); // Start a transaction

			boolean allSuccess = true;
			List<String> failedSeats = new ArrayList<>();

			for (String seat : seatnolist) {
				try (CallableStatement stmt = con.prepareCall("{call MoveBookingToSale(?, ?, ?, ? , ?)}")) {
					stmt.setInt(1, b.getShowID()); // showID
					stmt.setString(2, seat); // seatid
					stmt.setInt(3, CurrentUser.getUserID()); // userid
					stmt.setObject(4, TodayDate); // TodayDate (LocalDate)
					stmt.setString(5, voucherNo);
					stmt.execute();
				} catch (SQLException e) {
					e.printStackTrace();
					allSuccess = false;
					failedSeats.add(seat);
				}
			}

			if (allSuccess) {
				// 1. Calculate total sale from the seats
				int price = getSalePriceTotal(seatnolist);

				// 2. Update TotalSale in flimshow
				String sql = "UPDATE flimshow SET TotalSale = COALESCE(TotalSale, 0) + ? WHERE showid = ?;";
				try (PreparedStatement psmt = con.prepareStatement(sql)) {
					psmt.setInt(1, price);
					psmt.setInt(2, showID);
					psmt.executeUpdate();
				}
				con.commit();

				Platform.runLater(() -> {
					new Alert(AlertType.INFORMATION, "All seats sold successfully!", ButtonType.OK).showAndWait();
					try {
						LocalDateTime now = LocalDateTime.now();

						DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d/M/yyyy");
						String sell_date = now.format(dateformatter);

						DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("h:mm a");
						String sell_time = now.format(timeformatter);
						String seat_count = b.getSeatsBooked();
						String seatcount = seat_count.split(",").length + "";
						String showtime = b.getShowTime().split("-")[0];
						
						System.out.println(b.getMovieTitle());

						popup(sell_date, sell_time, b.getMovieTitle(),
								b.getBookedDate() + "", showtime, seatcount, b.getRoom(),
								b.getTotal(), b.getSeatsBooked(),voucherNo);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			} else {
				con.rollback(); // Rollback the transaction if any seat fails
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "Failed to sell seats: " + String.join(", ", failedSeats), ButtonType.OK)
					.showAndWait();
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Platform.runLater(() -> {
				new Alert(AlertType.ERROR, "Database error: " + e.getMessage(), ButtonType.OK).showAndWait();
			});
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Platform.runLater(() -> {
				new Alert(AlertType.ERROR, "Invalid movie ID format. Please provide a valid number.", ButtonType.OK)
				.showAndWait();
			});
		}
	}

	@FXML
	Label RoomName;
	@FXML
	public void EditBookingOnAction() {
		showTheaterForm();
		Booking_Confirm.setText("Update");
		selected_booking = booking_table.getSelectionModel().getSelectedItem();
		choicelist.clear();
		updateBooking = fetchMovieDetails(selected_booking.getMovieid());

		Image imagedata = loadImageFromProjectFolder(updateBooking.getImage());
		room_movie.setImage(imagedata);

		RoomName.setText(selected_booking.getRoom());
		selectedmovie_genre.setText(updateBooking.getGenre());
		selectedmovie_duration.setText(updateBooking.getDuration() + "");
		selectedmovie_description.setText(updateBooking.getDescription());
		selectedmovie_movieTitle.setText(updateBooking.getMoviename());

		booking_sale_form.setVisible(false);
		cs_input_form.setVisible(true);

		seatnolist.clear();
		seatnolist = getSeatByCusID(selected_booking.getCustomerid(),selected_booking.getShowID());
		txt_cs_name.setText(selected_booking.getName());
		txt_cs_remark.setText(selected_booking.getRemark());
		txt_cs_phone.setText(selected_booking.getPhone());
		disableSeats(selected_booking.getShowID());
		for (String seat : seatnolist) {
			for (Button button : seatlist) {
				if (button.getId().equals(seat) && !choicelist.contains(button)) {
					choicelist.add(button);
					System.out.println(choicelist.toString());
					System.out.println(choicelist.size());
					ImageView imageView = (ImageView) button.getGraphic();
					if (imageView != null) {
						Image currentImage = imageView.getImage();
						if (currentImage.equals(bookedImage)) {
							imageView.setImage(selectedImage);
						} else if (currentImage.equals(coup_booked)) {
							imageView.setImage(coup_selected);
						}
					}
				}
			}
		}
		total_price2.setText(getTotalPrice() + "");
		total_seat2.setText(seatnolist.size() + "");
		lb_choiced_seat2.setText(seatnolist.toString());
	}


	public void UpdateEndShowing() {
		String sql = "CALL `UpdateMovieEndShowing`();";
		try (Connection con = moviedatabase.connectDb(); CallableStatement csmt = con.prepareCall(sql);) {
			csmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void updateSeats(int customerId, int showid, List<String> newSeats) {
		Connection conn = null;
		PreparedStatement deleteStmt = null;
		PreparedStatement insertStmt = null;
		PreparedStatement selectStmt = null;
		ResultSet rs = null;

		try {
			// Connect to the database
			conn = moviedatabase.connectDb();
			conn.setAutoCommit(false); // Begin transaction

			// Step 1: Get existing booked seats
			Set<String> existingSeats = new HashSet<>();
			String selectQuery = "SELECT seatid FROM booking WHERE customerid = ? AND showid = ?";
			selectStmt = conn.prepareStatement(selectQuery);
			selectStmt.setInt(1, customerId);
			selectStmt.setInt(2, showid);
			rs = selectStmt.executeQuery();
			while (rs.next()) {
				existingSeats.add(rs.getString("seatid")); // âœ… Fixed column name
			}

			// Step 2: Find seats to add and remove
			Set<String> seatsToAdd = new HashSet<>(newSeats);
			Set<String> seatsToRemove = new HashSet<>(existingSeats);
			seatsToAdd.removeAll(existingSeats); // New seats not in old list
			seatsToRemove.removeAll(newSeats); // Old seats not in new list

			// Step 3: Remove unwanted seats
			if (!seatsToRemove.isEmpty()) {
				String deleteQuery = "DELETE FROM booking WHERE customerid = ? AND showid = ? AND seatid = ?";
				deleteStmt = conn.prepareStatement(deleteQuery);
				for (String seat : seatsToRemove) {
					deleteStmt.setInt(1, customerId);
					deleteStmt.setInt(2, showid);
					deleteStmt.setString(3, seat);
					deleteStmt.executeUpdate();
				}
			}

			// Step 4: Add new seats
			if (!seatsToAdd.isEmpty()) {
				String insertQuery = "INSERT INTO booking (customerid, showid, seatid, BookedDate,userid) VALUES (?, ?, ?,?,?)";
				insertStmt = conn.prepareStatement(insertQuery);
				for (String seat : seatsToAdd) {
					insertStmt.setInt(1, customerId);
					insertStmt.setInt(2, showid);
					insertStmt.setString(3, seat);
					insertStmt.setObject(4, TodayDate);
					insertStmt.setInt(5, CurrentUser.getUserID());
					insertStmt.executeUpdate();
				}
			}

			// Step 5: Commit the transaction
			conn.commit();
			new Alert(AlertType.INFORMATION, "Update Booking Successful", ButtonType.OK).showAndWait();

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback(); // Rollback on error
					System.out.println("Transaction rolled back due to error: " + e.getMessage());
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (selectStmt != null)
					selectStmt.close();
				if (deleteStmt != null)
					deleteStmt.close();
				if (insertStmt != null)
					insertStmt.close();
				if (conn != null) {
					conn.setAutoCommit(true); // Restore auto-commit
					conn.close();
				}
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
	}

	public void updateCustomer(int cusid, String name, String phone, String remark) {
		String sql = "UPDATE customer SET customername = ?, phone = ?, remark = ? WHERE customerid  = ?;";

		try (Connection con = moviedatabase.connectDb(); PreparedStatement psmt = con.prepareStatement(sql);) {
			psmt.setString(1, name);
			psmt.setString(2, phone);
			psmt.setString(3, remark);
			psmt.setInt(4, cusid);
			psmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private MovieData fetchMovieDetails(int movieid) {
		String sql = "SELECT * FROM movie WHERE movieid  = ?";
		MovieData movD = null;

		try (Connection con = moviedatabase.connectDb(); PreparedStatement psmt = con.prepareStatement(sql)) {

			psmt.setInt(1, movieid);
			try (ResultSet result = psmt.executeQuery()) {
				if (result.next()) {
					movD = new MovieData(movieid, result.getString("moviename"), result.getString("genre"),
							result.getTime("duration").toLocalTime(), result.getString("imagepath"),
							result.getString("description"), result.getString("current"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Platform.runLater(() -> {
				new Alert(AlertType.ERROR, "Database error: " + e.getMessage(), ButtonType.OK).showAndWait();
			});
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Platform.runLater(() -> {
				new Alert(AlertType.ERROR, "Invalid movie ID format. Please provide a valid number.", ButtonType.OK)
				.showAndWait();
			});
		}

		return movD;
	}

	public ArrayList<String> getSeatByCusID(int CusID ,int ShowID) {
		ArrayList<String> seatnolist = new ArrayList<>();
		String sql = "SELECT seatid FROM booking WHERE customerid = ? and showid = ?";

		try (Connection con = moviedatabase.connectDb(); PreparedStatement psmt = con.prepareStatement(sql)) {

			psmt.setInt(1, CusID);
			psmt.setInt(2, ShowID);

			try (ResultSet rs = psmt.executeQuery()) {
				while (rs.next()) {
					seatnolist.add(rs.getString("seatid")); // Use column name instead of index
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getSeatByCusID() error: " + e.getMessage());
		}

		return seatnolist; // Return the list
	}

	@FXML
	private TableView<Booking> booking_table;
	@FXML
	private TableColumn<Booking, String> booking_col_customerName;
	@FXML
	private TableColumn<Booking, String> booking_col_phoneNo;
	@FXML
	private TableColumn<Booking, String> booking_col_movieTitle;
	@FXML
	private TableColumn<Booking, String> booking_col_showDate;
	@FXML
	private TableColumn<Booking, String> booking_col_BookedDate;
	@FXML
	private TableColumn<Booking, String> booking_col_bookedBy;
	@FXML
	private TableColumn<Booking, String> booking_col_showTime;
	@FXML
	private TableColumn<Booking, String> booking_col_remark;
	@FXML
	private TableColumn<Booking, String> booking_col_Seat;
	@FXML
	private TableColumn<Booking, String> booking_col_totalcost;

	@FXML
	private TextField customer_search;
	@FXML
	DatePicker booking_search_booked_Date;
	
	@FXML
	DatePicker booking_search_show_Date;
	
	@FXML
	JFXTimePicker booking_search_show_Time;
	

	// Holds all bookings for search
	private ObservableList<Booking> masterBookingList = FXCollections.observableArrayList();

	public void AddToBooking() {
		// Setup column mappings
		booking_col_customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
		booking_col_phoneNo.setCellValueFactory(new PropertyValueFactory<>("phone"));
		booking_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
		booking_col_showTime.setCellValueFactory(new PropertyValueFactory<>("showTime"));
		booking_col_BookedDate.setCellValueFactory(new PropertyValueFactory<>("BookedDate"));
		booking_col_Seat.setCellValueFactory(new PropertyValueFactory<>("seatsBooked"));
		booking_col_totalcost.setCellValueFactory(new PropertyValueFactory<>("total"));
		booking_col_remark.setCellValueFactory(new PropertyValueFactory<>("remark"));
		booking_col_showDate.setCellValueFactory(new PropertyValueFactory<>("ShowDate"));
		booking_col_bookedBy.setCellValueFactory(new PropertyValueFactory<>("BookedStaff"));
		refreshBookingTable();
	}

	public void refreshBookingTable() {
		masterBookingList.setAll(getAllbookedSeat());

		FilteredList<Booking> filteredList = new FilteredList<>(masterBookingList, b -> true);

		customer_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(booking -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();


				return booking.getName().toLowerCase().contains(lowerCaseFilter)
						|| booking.getPhone().toLowerCase().contains(lowerCaseFilter)
						|| booking.getMovieTitle().toLowerCase().contains(lowerCaseFilter)
						|| booking.getSeatsBooked().toLowerCase().contains(lowerCaseFilter)
						|| booking.getBookedStaff().toLowerCase().contains(lowerCaseFilter)
						|| booking.getRemark().toLowerCase().contains(lowerCaseFilter);
			});
		});
		booking_search_booked_Date.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(booking -> {
				// If no date is selected, show all
				if (newValue == null) {
					return true;
				}
				return booking.getBookedDate().equals(newValue);
			});
		});
		booking_search_show_Date.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(booking -> {
				// If no date is selected, show all
				if (newValue == null) {
					return true;
				}
				return booking.getShowDate().equals(newValue);
			});
		});
		booking_search_show_Time.valueProperty().addListener((observable, oldValue, newValue) -> {
		    filteredList.setPredicate(booking -> {
		        // If no time is selected, show all
		        if (newValue == null) {
		            return true;
		        }

		        // Convert selected time to string
		        String selectedTime = newValue.toString().toLowerCase(); // e.g., "10:30"
		        String bookingTime = booking.getShowTime().toLowerCase();

		        return bookingTime.contains(selectedTime);
		    });
		});

		SortedList<Booking> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(booking_table.comparatorProperty());

		booking_table.setItems(sortedList);
		booking_table.refresh();
		
	}
	
	public ArrayList<Booking> getAllbookedSeat() {
		ArrayList<Booking> allbooked = new ArrayList<>();
		String sql = "CALL GetBookingDetails();";
		try (Connection conn = moviedatabase.connectDb();
				CallableStatement stmt = conn.prepareCall(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				LocalDate bookedDate = rs.getDate("BookedDate").toLocalDate();
				Booking booking = new Booking(
						rs.getString("customername"),
						rs.getString("phone"),
						rs.getString("moviename"),
						rs.getString("showtime"),
						bookedDate,
						rs.getString("roomname"),
						rs.getString("SeatsBooked"),
						rs.getString("TotalPrice"),
						rs.getString("remark"),
						rs.getInt("movieid"),
						rs.getInt("customerid"),
						rs.getDate("showdate").toLocalDate(),
						rs.getString("name"),
						rs.getInt("showid")
						);
				allbooked.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allbooked;
	}


	@FXML
	public void SaleSeatOnAction() {
		if (!seatnolist.isEmpty()) {
			// Show confirmation alert
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to proceed with the sale?",
					ButtonType.OK, ButtonType.CANCEL);
			Optional<ButtonType> result = alert.showAndWait();

			// Proceed only if user confirms
			if (result.isPresent() && result.get() == ButtonType.OK) {
				saleSeat(showID);
			}
		} else {
			new Alert(AlertType.ERROR, "Please choose a seat first!", ButtonType.OK).showAndWait();
		}
	}

	@FXML
	public void BookingDeleteOnAction() {
		Booking b = booking_table.getSelectionModel().getSelectedItem();
		if (b != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this booking?",
					ButtonType.OK, ButtonType.CANCEL);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.OK) {
				try (Connection con = moviedatabase.connectDb();
						CallableStatement stmt = con.prepareCall("{CALL delete_booking(?, ?)}")) {

					// Set parameters
					stmt.setInt(1, b.getShowID());
					stmt.setInt(2, b.getCustomerid());
					int rowsDeleted = stmt.executeUpdate();

					// Show appropriate message to the user
					if (rowsDeleted > 0) {
						Platform.runLater(() -> {
							new Alert(AlertType.INFORMATION, "Delete Successful", ButtonType.OK).showAndWait();
						});
					} else {
						Platform.runLater(() -> {
							new Alert(AlertType.WARNING, "No booking found with the specified details.", ButtonType.OK)
							.showAndWait();
						});
					}

				} catch (SQLException e) {
					e.printStackTrace();
					Platform.runLater(() -> {
						new Alert(AlertType.ERROR, "An error occurred while deleting the booking.", ButtonType.OK)
						.showAndWait();
					});
				}

				refreshBookingTable();
				disableSeats(b.getShowID());
				booking_table.refresh();
				refreshSeat();
			}
		} else {
			new Alert(AlertType.ERROR, "Please select a booking first!", ButtonType.OK).showAndWait();
		}
	}

	public void saleSeat(int showID) {
		
		Connection conn = null;
		try {
			conn = moviedatabase.connectDb();
			String vouchernum = generateUniqueVoucherNum(conn);
			conn.setAutoCommit(false); // Start a transaction

			String sql = "INSERT INTO SALE (showid, seatid, userid, saledate,voucherNum) VALUES (?, ?, ?, ?, ?)";
			String updateTotalSaleSql = "UPDATE flimshow SET TotalSale = COALESCE(TotalSale, 0) + ? WHERE showid = ?;";

			try (PreparedStatement psmt = conn.prepareStatement(sql);
					PreparedStatement updateSaleStmt = conn.prepareStatement(updateTotalSaleSql)) {

				// Calculate total sale price for the seats
				int totalSaleAmount = getSalePriceTotal(seatnolist); // Using your existing method to get the total
				// price

				// Prepare the sale records and insert them into the SALE table
				for (String seatno : seatnolist) {
					psmt.setInt(1, showID);
					psmt.setString(2, seatno);
					psmt.setInt(3, CurrentUser.getUserID());
					psmt.setObject(4, TodayDate);
					psmt.setString(5, vouchernum);
					psmt.addBatch(); // Add each seat to the batch
				}

				int[] results = psmt.executeBatch(); // Execute all inserts in one batch
				updateSaleStmt.setInt(1, totalSaleAmount);
				updateSaleStmt.setInt(2, showID);
				updateSaleStmt.executeUpdate();

				conn.commit();

				// Check for failures
				List<String> failedSeats = new ArrayList<>();
				for (int i = 0; i < results.length; i++) {
					if (results[i] == PreparedStatement.EXECUTE_FAILED) {
						failedSeats.add(seatnolist.get(i));
					}
				}

				if (failedSeats.isEmpty()) {
					new Alert(AlertType.INFORMATION, "All seats sold successfully!", ButtonType.OK).showAndWait();
					LocalDateTime now = LocalDateTime.now();

					DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d/M/yyyy");
					String sell_date = now.format(dateformatter);

					DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("h:mm a");
					String sell_time = now.format(timeformatter);
					try {
						String showtime = showTime.toString().split("-")[0];
						popup(sell_date, sell_time, choicedMovie.getMoviename(), ShowDate + "", showtime,
								seatnolist.size() + "", choice_room, getTotalPrice() + "", seatnolist.toString(),vouchernum);
					} catch (IOException e) {
						e.printStackTrace();
					}
					refreshSeat();
					disableSeats(showID);
					refreshSaleTable();
				} else {
					new Alert(AlertType.ERROR, "Failed to sell seats: " + String.join(", ", failedSeats), ButtonType.OK)
					.showAndWait();
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback(); // Rollback on error
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			new Alert(AlertType.ERROR, "Database error: " + ex.getMessage(), ButtonType.OK).showAndWait();
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true); // Reset auto-commit
					conn.close(); // Close connection
				} catch (SQLException closeEx) {
					closeEx.printStackTrace();
				}
			}
		}
	}
	  public static String generateUniqueVoucherNum(Connection conn) throws SQLException {
	        String voucherNum;
	        boolean isUnique = false;

	        while (!isUnique) {
	            // Generate unique voucher number using UUID
	            String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	            String randomPart = UUID.randomUUID().toString().substring(0, 8); // Keep it short
	            voucherNum = "VCH" + datePart + "_" + randomPart;

	            // Check if it already exists in the DB
	            String checkQuery = "SELECT COUNT(*) FROM sale WHERE voucherNum = ?";
	            try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
	                stmt.setString(1, voucherNum);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next() && rs.getInt(1) == 0) {
	                        isUnique = true;
	                        return voucherNum;
	                    }
	                }
	            }
	        }

	        // Should never reach here
	        return null;
	    }


	@FXML
	private AnchorPane selling_form;

	@FXML
	private TextField selling_search;
	
	@FXML
	DatePicker Sale_search_Sale_Date;
	
	@FXML
	DatePicker Sale_search_show_Date;
	
	@FXML
	JFXTimePicker Sale_search_show_Time;
	
	@FXML
	ComboBox<String> Sale_search_Room;

	@FXML
	private TableView<Sale> Sale_table;
    
	@FXML
	private TableColumn<Sale, String> selling_col_movietitle;

	@FXML
	private TableColumn<Sale, String> selling_col_show_date;

	@FXML
	private TableColumn<Sale, String> selling_col_show_time;

	@FXML
	private TableColumn<Sale, String> selling_col_room;

	@FXML
	private TableColumn<Sale, String> selling_col_seat;

	@FXML
	private TableColumn<Sale, String> selling_col_totalcost;

	@FXML
	private TableColumn<Sale, String> selling_col_staff;

	@FXML
	private TableColumn<Sale, String> selling_col_saletime;
	@FXML
	private TableColumn<Sale, String> selling_col_VoucherNo;

	private ObservableList<Sale> masterSaleList = FXCollections.observableArrayList();

	// Initialize table columns and search
	public void AddToSaleTable() {
		selling_col_movietitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
		selling_col_show_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		selling_col_show_time.setCellValueFactory(new PropertyValueFactory<>("showTime"));
		selling_col_room.setCellValueFactory(new PropertyValueFactory<>("room"));
		selling_col_seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
		selling_col_totalcost.setCellValueFactory(new PropertyValueFactory<>("price"));
		selling_col_saletime.setCellValueFactory(new PropertyValueFactory<>("SaleDate"));
		selling_col_staff.setCellValueFactory(new PropertyValueFactory<>("userName"));
		selling_col_VoucherNo.setCellValueFactory(new PropertyValueFactory<>("Voucher_No"));
		refreshSaleTable();
	}
	public ArrayList<Sale> getAllSaleSeat() {
		ArrayList<Sale> allSale = new ArrayList<>();
		String sql = "CALL getSaleDetails();";

		try (Connection conn = moviedatabase.connectDb();
				CallableStatement stmt = conn.prepareCall(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				LocalDate saleDate = rs.getDate("SaleDate").toLocalDate();

				Sale sale = new Sale(
						rs.getString("moviename"),
						rs.getDate("showdate").toLocalDate(),
						rs.getString("showtime"),
						rs.getString("roomname"),
						rs.getString("seatsSold"),
						rs.getString("TotalPrice"),
						saleDate,
						rs.getInt("userid"),
						rs.getString("username"),
						rs.getString("VoucherNum")
						);
				allSale.add(sale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allSale;
	}


	public void refreshSaleTable() {
		masterSaleList.setAll(getAllSaleSeat());

		Set<String> uniqueRoomNames = new HashSet<>();
		for (Sale sale : masterSaleList) {
		    uniqueRoomNames.add(sale.getRoom()); // Only unique names added
		}

		ObservableList<String> roomNameList = FXCollections.observableArrayList(uniqueRoomNames);
		Sale_search_Room.setItems(roomNameList);

		FilteredList<Sale> filteredList = new FilteredList<>(masterSaleList, b -> true);

		selling_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(sale -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				return sale.getMovieTitle().toLowerCase().contains(lowerCaseFilter)
						|| sale.getRoom().toLowerCase().contains(lowerCaseFilter)
						|| sale.getSeat().toLowerCase().contains(lowerCaseFilter)
						|| sale.getPrice().toLowerCase().contains(lowerCaseFilter)
						|| sale.getUserName().toLowerCase().contains(lowerCaseFilter)
						|| sale.getVoucher_No().toLowerCase().contains(lowerCaseFilter);


			});
		});
		Sale_search_Sale_Date.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(Sale -> {
				// If no date is selected, show all
				if (newValue == null) {
					return true;
				}
				return Sale.getSaleDate().equals(newValue);
			});
		});
		Sale_search_show_Date.valueProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(Sale -> {
				// If no date is selected, show all
				if (newValue == null) {
					return true;
				}
				return Sale.getDate().equals(newValue);
			});
		});
		Sale_search_show_Time.valueProperty().addListener((observable, oldValue, newValue) -> {
		    filteredList.setPredicate(Sale -> {
		        // If no time is selected, show all
		        if (newValue == null) {
		            return true;
		        }

		        // Convert selected time to string
		        String selectedTime = newValue.toString().toLowerCase(); // e.g., "10:30"
		        String bookingTime = Sale.getShowTime().toLowerCase();

		        return bookingTime.contains(selectedTime);
		    });
		});
		Sale_search_Room.valueProperty().addListener((observable, oldValue, newValue) -> {
		    filteredList.setPredicate(Sale -> {
		        // If no time is selected, show all
		        if (newValue == null) {
		            return true;
		        }

		        // Convert selected time to string
		        String selectedTime = newValue.toString().toLowerCase(); // e.g., "10:30"
		        String bookingTime = Sale.getRoom().toLowerCase();

		        return bookingTime.contains(selectedTime);
		    });
		});
		
		SortedList<Sale> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(Sale_table.comparatorProperty());

		Sale_table.setItems(sortedList);
		Sale_table.refresh();
	}


	public boolean check_couple(String seatID) {
		if (seatID.equals("J1") || seatID.equals("J2") || seatID.equals("J3") || seatID.equals("J4")
				|| seatID.equals("J5") || seatID.equals("J6")) {
			return true;
		} else {
			return false;
		}

	}

	public ObservableList<MovieData> getShowingMovie() {
		UpdateEndShowing();
		ObservableList<MovieData> listdata = FXCollections.observableArrayList();
		String sql = "CALL `getShowingMovie`()";
		try (Connection con = moviedatabase.connectDb();
				CallableStatement csmt = con.prepareCall(sql);
				ResultSet rs = csmt.executeQuery()) {

			while (rs.next()) {
				int movieid = rs.getInt("movieid");
				String moviename = rs.getString("moviename");
				String genre = rs.getString("genre");
				LocalTime duration = rs.getTime("duration").toLocalTime();
				String imagepath = rs.getString("imagepath");
				String description = rs.getString("description");
				String current = rs.getString("current");
				MovieData mdata = new MovieData(movieid, moviename, genre, duration, imagepath, description, current);
				listdata.add(mdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdata;
	}
	public ObservableList<MovieData> getTodayShowingMovie() {
		UpdateEndShowing();
		ObservableList<MovieData> listdata = FXCollections.observableArrayList();
		String sql = "CALL `getTodayShowingMovie`()";
		try (Connection con = moviedatabase.connectDb();
				CallableStatement csmt = con.prepareCall(sql);
				ResultSet rs = csmt.executeQuery()) {

			while (rs.next()) {
				int movieid = rs.getInt("movieid");
				String moviename = rs.getString("moviename");
				String genre = rs.getString("genre");
				LocalTime duration = rs.getTime("duration").toLocalTime();
				String imagepath = rs.getString("imagepath");
				String description = rs.getString("description");
				String current = rs.getString("current");
				MovieData mdata = new MovieData(movieid, moviename, genre, duration, imagepath, description, current);
				listdata.add(mdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdata;
	}

	public ObservableList<MovieData> getUpComingMovie() {
		ObservableList<MovieData> listdata = FXCollections.observableArrayList();
		String sql = "CALL `getUpcomingMovie`();";
		try (Connection con = moviedatabase.connectDb();
				CallableStatement csmt = con.prepareCall(sql);
				ResultSet rs = csmt.executeQuery()) {

			while (rs.next()) {
				int movieid = rs.getInt("movieid");
				String moviename = rs.getString("moviename");
				String genre = rs.getString("genre");
				LocalTime duration = rs.getTime("duration").toLocalTime();
				String imagepath = rs.getString("imagepath");
				String decription = rs.getString("description");
				String current = rs.getString("current");
				MovieData mdata = new MovieData(movieid, moviename, genre, duration, imagepath, decription, current);
				listdata.add(mdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdata;
	}
	

	@FXML
	public void AddAllShowing() {
		nowshowingbox.getChildren().clear();
		ObservableList<MovieData> AllShowing = FXCollections.observableArrayList();

		AllShowing.addAll(getShowingMovie()); // Fetch only showing movies

		nowshowingbox.getChildren().clear();

		for (MovieData movie : AllShowing) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/videoframe.fxml"));
				AnchorPane pane = loader.load();

				// Get the controller of the loaded FXML
				VideoFrameController controller = loader.getController();

				// Pass reference of DashboardControllerstaff to VideoFrameController
				controller.setDashboardController(this);

				// Set movie data
				controller.setMovieData(movie);

				nowshowingbox.getChildren().add(pane);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@FXML
	public void AddTodayShowing() {
		nowshowingbox.getChildren().clear();
		ObservableList<MovieData> AllShowing = FXCollections.observableArrayList();

		AllShowing.addAll(getTodayShowingMovie()); 

		nowshowingbox.getChildren().clear();

		for (MovieData movie : AllShowing) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/videoframe.fxml"));
				AnchorPane pane = loader.load();

				// Get the controller of the loaded FXML
				VideoFrameController controller = loader.getController();

				// Pass reference of DashboardControllerstaff to VideoFrameController
				controller.setDashboardController(this);

				// Set movie data
				controller.setMovieData(movie);

				nowshowingbox.getChildren().add(pane);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void AddUpComing() {
		ObservableList<MovieData> UpComing = FXCollections.observableArrayList(); // ✅ Initialize list

		UpComing.addAll(getUpComingMovie()); // Fetch upcoming movies instead

		upcomingbox.getChildren().clear();

		for (MovieData movie : UpComing) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/videoframe.fxml"));
				AnchorPane pane = loader.load();

				// Get the controller of the loaded FXML
				VideoFrameController controller = loader.getController();

				controller.setDashboardController(this);

				// Set movie data
				controller.setMovieData(movie);

				upcomingbox.getChildren().add(pane);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Image loadImageFromProjectFolder(String imageName) {
		 try {
		        File file = new File("resources/com/cinema/images/movieImage/", imageName);
		        if (file.exists()) {
		            String uri = file.toURI().toString();
		            System.out.println("Loading image from: " + uri);
		            return new Image(uri, 244, 144, false, true);
		        } else {
		            System.err.println("File not found: " + file.getAbsolutePath());
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
	}

	public void choicedMovie(MovieData moviedata) {
		if (moviedata == null || moviedata.getImage() == null) {
			System.err.println("Invalid movie data");
			return;
		}
		avaliable_time_box.getChildren().clear();
		choicedMovie = moviedata;
		ch_title.setText(choicedMovie.getMoviename());

		// Load movie details
		ch_description.setText(choicedMovie.getDescription());
		ch_genere.setText(choicedMovie.getGenre());
		ch_duration.setText(choicedMovie.getDuration() + "");
		Image image = loadImageFromProjectFolder(choicedMovie.getImage());
		choiced_image.setImage(image);

		// Fetch available dates
		ArrayList<LocalDate> availableDates = getAvaliableDateByMID(moviedata.getId());

		if (availableDates == null || availableDates.isEmpty()) {
			avaliable_date_box.setVisible(false);

			System.out.println("No available dates for this movie.");
			return;
		}

		avaliable_date_box.getChildren().clear();
		avaliable_date_box.setVisible(true);

		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd"); // "01", "02"
		DateTimeFormatter shortDayFormatter = DateTimeFormatter.ofPattern("E"); // "Mon", "Tue"

		for (LocalDate date : availableDates) {
			String dayNumber = date.format(dayFormatter);
			String shortDay = date.format(shortDayFormatter);
			ShowDate = date;
			Label dayLabel = new Label(dayNumber);
			dayLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

			Label shortDayLabel = new Label(shortDay);
			shortDayLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #dddddd;");

			VBox dateBox = new VBox(dayLabel, shortDayLabel);
			dateBox.setAlignment(Pos.CENTER);

			Button dateButton = new Button();
			dateButton.setGraphic(dateBox);
			dateButton.setPrefWidth(150);
			dateButton.setPrefHeight(100);
			dateButton.setStyle("-fx-background-color: #4CAF50; -fx-padding: 8px 12px; -fx-border-radius: 10px;");

			dateButton.setOnMouseClicked(event -> {
				getShowTimeByMID(choicedMovie.getId(), date);

			});

			avaliable_date_box.getChildren().add(dateButton);
			avaliable_date_box.setAlignment(Pos.CENTER);
		}
	}
	
	@FXML
	private Label lb_show_date;
	@FXML
	private Label lb_show_Time;
	@FXML
	private Label standard_price;
	@FXML
	private Label priminum_price;
	@FXML
	private Label couple_price;
	
	

	public void getShowTimeByMID(int movieID, LocalDate date) {
		String sql = "CALL `ShowTimeByMIDandDate`(?, ?);";

		ArrayList<LocalTime> availableTimes = new ArrayList<>();
		ArrayList<String> availableRooms = new ArrayList<>(); // Store room names
		ArrayList<Integer> availableRoomIDs = new ArrayList<>(); // Store room IDs

		// Clear previous entries
		avaliable_time_box.getChildren().clear();

		try (Connection con = moviedatabase.connectDb(); CallableStatement csmt = con.prepareCall(sql)) {

			csmt.setInt(1, movieID);
			csmt.setDate(2, Date.valueOf(date));

			try (ResultSet rs = csmt.executeQuery()) {
				while (rs.next()) {
					availableTimes.add(rs.getTime("showtime").toLocalTime());
					availableRooms.add(rs.getString("roomname"));
					availableRoomIDs.add(rs.getInt("roomid"));
				}
			}

			for (int i = 0; i < availableRooms.size(); i++) {
				String roomName = availableRooms.get(i);
				final int roomID = availableRoomIDs.get(i);
				final LocalTime selectedShowTime = availableTimes.get(i);

				Label roomLabel = new Label(roomName);
				roomLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

				Label timeLabel = new Label(selectedShowTime.toString());
				timeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #dddddd;");

				VBox timeBox = new VBox(roomLabel, timeLabel);
				timeBox.setAlignment(Pos.CENTER);

				Button timeButton = new Button();
				timeButton.setGraphic(timeBox);
				timeButton.setStyle("-fx-background-color: #444444; -fx-padding: 10px; -fx-border-radius: 5px;");
				timeButton.setOnMouseEntered(e -> timeButton.setStyle("-fx-background-color: #666666;"));
				timeButton.setOnMouseExited(e -> timeButton.setStyle("-fx-background-color: #444444;"));
				timeButton.setFocusTraversable(false);

				timeButton.setOnMouseClicked(event -> {
					showID = getShowID(choicedMovie.getId(), roomID, date, selectedShowTime);
					showTime = selectedShowTime;
					choice_room = roomName;
					lb_show_date.setText(date+"");
					lb_show_Time.setText(selectedShowTime+"");
					standard_price.setText(getSeatPricebySeatID("A1")+"");
					priminum_price.setText(getSeatPricebySeatID("D1")+"");
					couple_price.setText(getSeatPricebySeatID("J1")+"");
					showTheaterForm();
					Image image = loadImageFromProjectFolder(choicedMovie.getImage());
					room_movie.setImage(image);

					selectedmovie_genre.setText(choicedMovie.getGenre());
					selectedmovie_duration.setText(choicedMovie.getDuration() + "");
					selectedmovie_description.setText(choicedMovie.getDescription());
					selectedmovie_movieTitle.setText(choicedMovie.getMoviename());
					RoomName.setText(choice_room);
					disableSeats(showID);
				});

				avaliable_time_box.getChildren().add(timeButton);
				avaliable_time_box.setAlignment(Pos.CENTER);
			}

		} catch (Exception e) {
			System.out.println("getShowTimeByMID error");
			e.printStackTrace();
		}
	}
	


	public int getShowID(int movieID, int roomID, LocalDate showDate, LocalTime showTime) {
		int showID = 0;
		String sql = "SELECT showid FROM flimshow WHERE movieid = ? AND showdate = ? AND showtime = ?";

		try (Connection con = moviedatabase.connectDb(); PreparedStatement psmt = con.prepareStatement(sql)) {

			psmt.setInt(1, movieID);
			psmt.setDate(2, Date.valueOf(showDate));
			psmt.setTime(3, Time.valueOf(showTime));

			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) {
					showID = rs.getInt("showid"); // Using column name for clarity
				}
			}

		} catch (Exception e) {
			System.out.println("getShowID error");
			e.printStackTrace();
		}

		return showID;
	}

	public ArrayList<LocalDate> getAvaliableDateByMID(int movieID) {
		ArrayList<LocalDate> avaliableDate = new ArrayList<>();

		String sql = "CALL getShowingDatesByMID(?,?,?)"; // No need for backticks (`) in Java

		try (Connection con = moviedatabase.connectDb(); CallableStatement csmt = con.prepareCall(sql)) {

			csmt.setInt(1, movieID);
			csmt.setObject(2, TodayDate);
			csmt.setObject(3, nowTime);
			ResultSet rs = csmt.executeQuery();

			while (rs.next()) {
				avaliableDate.add(rs.getDate("showdate").toLocalDate());
			}

		} catch (Exception e) {
			System.out.println("getAvaliableDateByMID error");
			e.printStackTrace();
		}

		return avaliableDate;
	}

	@FXML
	void popup(String sellDate, String sellTime, String movietitle, String showdate, String showtime, String numSeats,
			String room, String totalcost, String seat,String voucherNo) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/receiptsPopup.fxml"));
		Parent p = fxmlLoader.load();
		PopupController pc = fxmlLoader.getController();
		pc.set_sell_date_Label(sellDate);
		pc.set_sell_time_Label(sellTime);
		pc.set_movie_name_Label(movietitle); // Pass label text
		pc.set_show_date_Label(showdate); // Pass label text
		pc.set_show_time_Label(showtime);
		pc.set_no_of_seats_Label(numSeats);
		pc.set_room_Label(room);
		pc.set_total_cost_Label(totalcost);
		pc.set_seats_Label(seat);
		pc.set_VoucherNo(voucherNo);

		Stage ps = new Stage();
		ps.initModality(Modality.APPLICATION_MODAL); // Blocks the main window
		ps.setTitle("Receipts");
		ps.setScene(new Scene(p));
		ps.showAndWait(); // Show and block interaction with main window
		// ps.initStyle(StageStyle.TRANSPARENT);
	}
	
	@FXML
	private ImageView Staff_Image;
	  
    User CurrentUser;

	public void displayUsername(User user) {
		
		if (user != null) {
			CurrentUser = user;
	         username.setText(user.getUserName());
		        try {
			        File file = new File("resources/com/cinema/images/userImage/", user.getImagePath());
			        if (file.exists()) {
			            String uri = file.toURI().toString();
			            System.out.println("Loading image from: " + uri);
			            Staff_Image.setImage(new Image(uri));
			            double radius = Math.min(Staff_Image.getFitWidth(), Staff_Image.getFitHeight()) / 2;
			            Circle clip = new Circle(radius, radius, radius);
			            Staff_Image.setClip(clip);
			            
			        } else {
			            System.err.println("File not found: " + file.getAbsolutePath());
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	     } else {
	         System.err.println("User is not set!");
	     }
		
		
	}
	@FXML
	void UpdateAccount() {
	    if (CurrentUser != null) {
	        System.out.println(CurrentUser.toString());
	        try {
	            FXMLLoader popupLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/userInfo.fxml"));
	            Parent popupRoot = popupLoader.load();

	            Stage popupStage = new Stage();
	            popupStage.setTitle("User Info");
	            popupStage.setScene(new Scene(popupRoot));
	            popupStage.show();

	            UserInfoController controller = popupLoader.getController();
	            controller.setStaffController(this);
	            controller.setData(CurrentUser);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.err.println("CurrentUser is not set!");
	    }
	}
	@FXML
	void Usersetting(ActionEvent event) {
		try {

			FXMLLoader popupLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/userSetting.fxml"));
			Parent popupRoot = popupLoader.load();
			
			UserSettingController controller =popupLoader.getController();
			controller.setCurrentUser(CurrentUser);


			Stage popupStage = new Stage();
			popupStage.setTitle("Setting");
			popupStage.setScene(new Scene(popupRoot));


			popupStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	@FXML
	private Button food_selling_btn;

	@FXML
	private AnchorPane foodDrink_form;

	private void loadFoodController() {
		try {
			URL fxmlPath = getClass().getResource("/com/cinema/views/foodshow.fxml");
			if (fxmlPath == null) {
				System.err.println(" FXML not found: /com/cinema/views/foodshow.fxml");
				return;
			}

			FXMLLoader loader = new FXMLLoader(fxmlPath);
			AnchorPane foodControllerPane = loader.load();
			foodDrink_form.getChildren().clear();
			foodDrink_form.getChildren().add(foodControllerPane);

			// Optional: send data to controller

		} catch (IOException e) {
			System.err.println("Failed to load foodshow.fxml");
			e.printStackTrace();
		}
	}


	Button activeButton = null;
	@FXML
	void switchForm(ActionEvent event) {
		if (event.getSource() == dashboard_btn) {
			showDashboardForm();
		} else if (event.getSource() == theater_btn) {
			showTheaterForm();
		} else if (event.getSource() == booking_btn) {
			showBookingForm();
		} else if (event.getSource() == selling_btn) {
			showSellingForm();
		} else if (event.getSource() == food_selling_btn) {
			showFoodForm();
		}
	}

	private void showDashboardForm() {
		setFormVisibility(true, false, false, false, false);
		setButtonStyle(dashboard_btn);
		refreshSeat();
	}

	private void showTheaterForm() {
		setFormVisibility(false, true, false, false, false);
		setButtonStyle(theater_btn);
	}

	private void showBookingForm() {
		setFormVisibility(false, false, true, false, false);
		setButtonStyle(booking_btn);
		refreshSeat();
	}

	private void showSellingForm() {
		setFormVisibility(false, false, false, true, false);
		setButtonStyle(selling_btn);
		refreshSeat();
	}

	private void showFoodForm() {
		setFormVisibility(false, false, false, false, true);
		setButtonStyle(food_selling_btn);
	}

	private void setFormVisibility(boolean showDashboard, boolean showTheater,
			boolean showBooking, boolean showSelling, boolean showFood) {
		dashboard_form.setVisible(showDashboard);
		theater_form.setVisible(showTheater);
		booking_form.setVisible(showBooking);
		selling_form.setVisible(showSelling);
		foodDrink_form.setVisible(showFood);
	}

	private void setButtonStyle(Button newActiveButton) {

		activeButton = newActiveButton;
	    String activeStyle = "-fx-background-color: linear-gradient(to top right,#FF69B4,#87CEEB);"
	        + "-fx-text-fill: black;"  // black text for selected
	        + "-fx-background-radius: 10;"
	        + "-fx-border-radius: 10;";

	    String defaultStyle = "-fx-background-color: linear-gradient(to top right,#66578e,#735fa2);"
	        + "-fx-text-fill: white;"  // white text for others
	        + "-fx-background-radius: 10;"
	        + "-fx-border-radius: 10;";
		Button[] buttons = {
				dashboard_btn, theater_btn, booking_btn,
				selling_btn, food_selling_btn
		};

		for (Button btn : buttons) {
			if (btn == activeButton) {
				btn.setStyle(activeStyle);
			} else {
				btn.setStyle(defaultStyle);
			}
		}
	}

	public static class HoverRainbowBorder {

		private static final String[] gradients = {
				"linear-gradient(to right, #ff6d1f, #ffb700, #47c2c0, #7f5fc4, #f43e7b, #40FFca, #a7cb8e, #ff6d1f)",
				"linear-gradient(to right, #ffb700, #47c2c0, #7f5fc4, #f43e7b, #40FFca, #a7cb8e, #ff6d1f, #ffb700)",
				"linear-gradient(to right, #47c2c0, #7f5fc4, #f43e7b, #40FFca, #a7cb8e, #ff6d1f, #ffb700, #47c2c0)",
				"linear-gradient(to right, #7f5fc4, #f43e7b, #40FFca, #a7cb8e, #ff6d1f, #ffb700, #47c2c0, #7f5fc4)",
				"linear-gradient(to right, #f43e7b, #40FFca, #a7cb8e, #ff6d1f, #ffb700, #47c2c0, #7f5fc4, #f43e7b)",
				"linear-gradient(to right, #40FFca, #a7cb8e, #ff6d1f, #ffb700, #47c2c0, #7f5fc4, #f43e7b, #40FFca)",
				"linear-gradient(to right, #a7cb8e, #ff6d1f, #ffb700, #47c2c0, #7f5fc4, #f43e7b, #40FFca, #a7cb8e)"
		};

		public static void apply(Button button,Supplier<Button> getActiveButton) {
			Timeline timeline = new Timeline();


			for (int i = 0; i < gradients.length; i++) {
				final int index = i;
				timeline.getKeyFrames().add(
						new KeyFrame(Duration.millis(100 * i), e -> {
							if (getActiveButton.get() != button) {
								button.setStyle(
										"-fx-background-radius: 10;"+
												"-fx-border-radius: 10;"+
												"-fx-text-fill: white;" +
												"-fx-border-color: " + gradients[index] + ";" +
												"-fx-border-width: 3;" 

										);
							}
						})
						);
			}
			timeline.setCycleCount(Timeline.INDEFINITE);

			// Start animation on hover
			button.setOnMouseEntered(e -> {
				if (getActiveButton.get() != button) {
					timeline.play();
				}
			});

			// Stop animation and reset style when not hovering
			button.setOnMouseExited(e -> {
				timeline.stop();
				if (getActiveButton.get() != button) {
					button.setStyle(
							"-fx-background-radius: 10;"+
									"-fx-border-radius: 10;"+
									"-fx-background-color: linear-gradient(to top right,#66578e,#735fa2);" +
									"-fx-text-fill: white;" +
									"-fx-border-color: transparent;"
							);
				}
			});
		}
	}
	
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createSeat();
		AddAllShowing();
		AddUpComing();
		AddToBooking();
		AddToSaleTable();
		refreshSaleTable();
		refreshBookingTable();
		loadFoodController();
		showDashboardForm();
		HoverRainbowBorder.apply(dashboard_btn, () -> activeButton);
		HoverRainbowBorder.apply(theater_btn, () -> activeButton);
		HoverRainbowBorder.apply(booking_btn, () -> activeButton);
		HoverRainbowBorder.apply(selling_btn, () -> activeButton);
		HoverRainbowBorder.apply(food_selling_btn, () -> activeButton);
		
		
		Sale_search_show_Time.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
		booking_search_show_Time.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
		booking_search_show_Time.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
		Sale_search_show_Time.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
	


	

	}

}
