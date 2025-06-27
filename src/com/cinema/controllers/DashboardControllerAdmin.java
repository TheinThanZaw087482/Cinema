package com.cinema.controllers;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cinema.models.FoodData;
import com.cinema.models.MovieData;
import com.cinema.models.Sale;
import com.cinema.models.Screening;
import com.cinema.models.User;
import com.cinema.models.getData;
import com.cinema.utils.moviedatabase;
import com.jfoenix.controls.JFXTimePicker;
import java.io.*;
import java.math.BigDecimal;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.chart.*;

public class DashboardControllerAdmin implements Initializable {

	@FXML
	private AnchorPane topform;

	@FXML
	private Button closebtn;

	@FXML
	private Button minimize;

	@FXML
	private Label username;

	@FXML
	private Button dashboard_btn;

	@FXML
	private Button addMovies_btn;

	@FXML
	private Button editScreening_btn;

	@FXML
	private Button selling_btn;

	@FXML
	private Button report_btn;

	@FXML
	private Button SignOut;

	@FXML
	private AnchorPane dashboard_form;
	@FXML
	private AnchorPane report_form;

	@FXML
	private AnchorPane dashboard_totalsoldticket;

	@FXML
	private Label totalTicket;

	@FXML
	private AnchorPane dashboard_availablemovies;

	@FXML
	private Label avaliableMovie;

	@FXML
	private AnchorPane dashboard_totalearntoday;

	@FXML
	private Label totalSold;

	@FXML
	private Label report_movie_label;
	@FXML
	private Label Show_adding_Form_label;

	@FXML
	private AnchorPane addmovies_form;

	@FXML
	private ImageView addmovies_imageView;

	@FXML
	private ImageView Show_adding_Form_image;

	@FXML
	private Button addmovies_import;
	@FXML
	private Button btn_add_to_show_cancel;

	@FXML
	private TextField addmovies_movieTitle;

	@FXML
	private ComboBox<String> addmovies_genre;

	@FXML
	private TextArea addmovies_description;

	@FXML
	private Button addmovies_insertbtn;

	@FXML
	private Button addmovies_endbtn;

	@FXML
	private Button addmovies_delete;

	@FXML
	private Button btn_add_to_show;

	@FXML
	private AnchorPane Add_Movie_Form;

	@FXML
	private AnchorPane Show_adding_Form;

	@FXML
	private JFXTimePicker addmovies_duration;

	@FXML
	private JFXTimePicker editscreening_showTime;

	@FXML
	private JFXTimePicker Show_adding_Form_showtime;





	@FXML
	private Button setting;
	@FXML
	private TableView<MovieData> addmovies_tableView;

	@FXML
	private TableColumn<MovieData, String> addmovies_col_movieTitle;

	@FXML
	private TableColumn<MovieData, String> addmovies_col_genre;

	@FXML
	private TableColumn<MovieData, Time> addmovies_col_duration;
	@FXML
	private TableColumn<MovieData, String> addmovies_col_description;
	@FXML
	private TableColumn<MovieData, String> addmovies_col_current;


	@FXML
	private TableView<MovieData> report_movie_Table;

	@FXML
	private TableColumn<MovieData, String> report_movie_Table_movieName;

	@FXML
	private TableColumn<MovieData, String> report_movie_Table_genre;

	@FXML
	private TableColumn<MovieData, Time> report_movie_Table_duration;

	@FXML
	private AnchorPane editscreening_form;

	@FXML
	private ImageView editscreening_imageView;

	@FXML
	private Label editscreening_title;

	@FXML
	private Label report_lb_show_times;

	@FXML
	private Label report_lb_sold_ticket;

	@FXML
	private Label report_lb_cancel_book;

	@FXML
	private Label report_lb_showedRoom;

	@FXML
	private Label report_lb_showedDate;

	@FXML
	private Label report_lb_revenue;

	@FXML
	private ComboBox<String> cb_add_to_show_room;

	@FXML
	private ComboBox<String> editscreening_room;


	@FXML
	private Button editscreening_updatebtn;

	@FXML
	private Button editscreening_endbtn;

	@FXML
	private TextField editscreening_search;

	@FXML
	private ComboBox<String> edit_movie_search_room;

	@FXML
	private ComboBox<String> edit_movie_search_current;

	@FXML
	private DatePicker edit_movie_showDate;

	@FXML
	private JFXTimePicker edit_movie_showTime;

	@FXML
	private TableView<Screening> editscreening_tableView;

	@FXML
	private TableColumn<Screening, String> editscreening_col_movieTitle;
	@FXML
	private TableColumn<Screening, String> editscreening_col_current;
	@FXML
	private TableColumn<Screening, String> editscreening_col_showDate;

	@FXML
	private TableColumn<Screening, String> editscreening_col_ShowTime;
	@FXML
	private TableColumn<Screening, String> editscreening_col_room;

	@FXML
	private TableColumn<Screening, String> editscreening_col_genre;

	@FXML
	private TableColumn<Screening, String> editscreening_col_duration;
	@FXML
	private TableColumn<Screening, String> editscreening_col_totalRevenue;



	@FXML
	private DatePicker Show_adding_Form_end_date;

	@FXML
	private DatePicker editscreening_showDate;

	@FXML
	private DatePicker Show_adding_Form_start_date;

	@FXML
	private Button signin_createAccount;




	MovieData ToUpdate;
	String duration;

	private Image image;
	private double x = 0;
	private double y = 0;

	private Connection connect;
	private PreparedStatement prepare;
	private Statement statement;
	private ResultSet result;

	private String[] currentList = { "Showing", "End Showing", "Upcoming" };

	private String[] genreList = {"Action","Romance","Horror","Comedy","Fantasy"};

	ObservableList<String> current = FXCollections.observableArrayList(currentList);
	ObservableList<String> genere = FXCollections.observableArrayList(genreList);
	public ObservableList<String> getAllRoomList(){
		ObservableList<String> roomList = FXCollections.observableArrayList(); ;
		String sql = "Select roomname from room";
		try(Connection con = moviedatabase.connectDb();

				PreparedStatement psmt = con.prepareStatement(sql);){

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				roomList.add(rs.getString(1));
			}


		} catch (Exception e) {
			System.out.println("getAllRoomList()+error");

		}
		return roomList;


	}

	private void setup24HourTimePicker(JFXTimePicker timepicker) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		timepicker.set24HourView(true); // Ensure 24-hour format

		// Unbind promptText to prevent "A bound value cannot be set" error
		timepicker.getEditor().promptTextProperty().unbind();
		timepicker.setPromptText("HH:mm");

		// Custom StringConverter to ensure 24-hour format
		timepicker.setConverter(new StringConverter<LocalTime>() {
			@Override
			public String toString(LocalTime time) {
				return (time != null) ? formatter.format(time) : "";
			}

			@Override
			public LocalTime fromString(String string) {
				return (string != null && !string.isEmpty()) ? LocalTime.parse(string, formatter) : null;
			}
		});

		// Ensure editor text is also 24-hour format
		Platform.runLater(() -> {
			if (timepicker.getEditor() != null) {
				timepicker.getEditor().promptTextProperty().unbind(); // Unbind again for safety
				timepicker.getEditor().setPromptText("00:00");
			}
		});
		timepicker.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				duration = newValue.format(formatter);

			}
		});

	}

	public void clearEditScreening() {
		editscreening_title.setText("");
		editscreening_imageView.setImage(null);
		// editscreening_current.setSelectionModel(null);
	}

	public void searchEditScreening() {
		Set<String> uniqueRoomNames = new HashSet<>();
		for (Screening room : editScreeningL) {
			uniqueRoomNames.add(room.getRoomname()); // Only unique names added
		}
		ObservableList<String> roomNameList = FXCollections.observableArrayList(uniqueRoomNames);
		edit_movie_search_room.setItems(roomNameList);
		edit_movie_search_current.setItems(current);

		FilteredList<Screening> filter = new FilteredList<>(editScreeningL, e -> true);

		editscreening_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(predicateMoviesData -> {
				if (newValue == null || newValue.isEmpty()) {
					return true; // Show all if search field is empty
				}

				String searchKey = newValue.toLowerCase();
				if (predicateMoviesData.getMoviename().toLowerCase().contains(searchKey)) {
					return true;
				} else if (predicateMoviesData.getGenre().toLowerCase().contains(searchKey)) {
					return true;
				}
				return false; // No match found
			});
		});
		edit_movie_search_room.valueProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(movie -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}


				return movie.getRoomname().equalsIgnoreCase(newValue);
			});
		});
		edit_movie_search_current.valueProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(movie -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}


				return movie.getCurrent().equalsIgnoreCase(newValue);
			});
		});
		edit_movie_showDate.valueProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(movie -> {
				// If no date is selected, show all
				if (newValue == null) {
					return true;
				}
				return movie.getShowdate().equals(newValue);
			});
		});
		edit_movie_showTime.valueProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(movie -> {
				// If no time is selected, show all
				if (newValue == null) {
					return true;
				}

				// Convert selected time to string
				String selectedTime = newValue.toString().toLowerCase(); // e.g., "10:30"
				String showtime = movie.getShowtime().toString().toLowerCase();

				return showtime.contains(selectedTime);
			});
		});
		SortedList<Screening> sortedData = new SortedList<>(filter);
		sortedData.comparatorProperty().bind(editscreening_tableView.comparatorProperty());

		// Set sorted data to TableView
		editscreening_tableView.setItems(sortedData);
	}

	Screening ScreentoUpdate;
	public void selectEditScreening() {
		ScreentoUpdate = editscreening_tableView.getSelectionModel().getSelectedItem();
		if(editscreening_tableView !=null && ScreentoUpdate.getCurrent().equals("Showing")) {
			editscreening_endbtn.setVisible(true);
			editscreening_updatebtn.setVisible(true);
		}else if(editscreening_tableView !=null && ScreentoUpdate.getCurrent().equals("End Showing")){
			editscreening_endbtn.setVisible(false);
			editscreening_updatebtn.setVisible(false);
			editscreening_room.setEditable(false);
			editscreening_showDate.setEditable(false);
			editscreening_showTime.setEditable(false);
		}

		editscreening_imageView.setImage(loadImageFromProjectFolder(ScreentoUpdate.getImagepath()));

		editscreening_title.setText(ScreentoUpdate.getMoviename());
		editscreening_room.setValue(ScreentoUpdate.getRoomname());
		editscreening_showDate.setValue(ScreentoUpdate.getShowdate());
		editscreening_showTime.setValue(ScreentoUpdate.getShowtime());
		addmovies_description.setText(ScreentoUpdate.getDescription());
		editscreening_room.setItems(getAllRoomList());
	}
	@FXML
	public void EditScreenDeletebtnAction() {
		if(ScreentoUpdate!=null && haveBookingAndSaleBySID(ScreentoUpdate.getShowID()) ) {
			showAlert("Information", "This show have booking or sale,You should not delete it!");
		}else {
			String sql ="DELETE FROM flimshow WHERE `flimshow`.`showid` = ?";
			try(Connection con = moviedatabase.connectDb();
					PreparedStatement psmt = con.prepareStatement(sql);){
				psmt.setInt(1, ScreentoUpdate.getShowID());
				psmt.execute();

				showAlert("Information", "Delete Succefful!");
				showEditScreening();
				editscreening_tableView.refresh();

			} catch (Exception e) {
				e.getMessage();
			}

		}
	}
	@FXML
	public void EditScreenUpdateOnAction() {
		if (ScreentoUpdate == null) {
			showAlert("Error", "Select show first!");
			return;
		} else if (haveBookingAndSaleBySID(ScreentoUpdate.getShowID())) {
			Alert alert = new Alert(AlertType.CONFIRMATION, 
					"This show has booked or sold seats. You should contact the booked customers!", 
					ButtonType.CANCEL, ButtonType.OK);

			Optional<ButtonType> option = alert.showAndWait();

			if (option.isPresent() && ButtonType.OK.equals(option.get())) {
				String sql = "UPDATE flimshow SET showtime = ?, showdate = ?, roomid = ? WHERE showid = ?";

				try (Connection con = moviedatabase.connectDb();
						PreparedStatement psmt = con.prepareStatement(sql)) {

					psmt.setTime(1, Time.valueOf(editscreening_showTime.getValue()));
					psmt.setDate(2, Date.valueOf(editscreening_showDate.getValue()));
					psmt.setInt(3, getRoomByRoomName(editscreening_room.getValue()));
					psmt.setInt(4, ScreentoUpdate.getShowID()); // Fix: Set the show ID

					int rowsUpdated = psmt.executeUpdate();

					if (rowsUpdated > 0) {
						showAlert("Information", "Successful Update!");
						showEditScreening();
						editscreening_tableView.refresh();
					} else {
						showAlert("Error", "Update failed. Please try again.");
					}

				} catch (Exception e) {
					e.printStackTrace(); // Fix: Print exception details
					showAlert("Error", "An error occurred: " + e.getMessage());
				}

			}
		}else {
			UpdateShow();
		}
	}
	public void UpdateShow() {
		String sql = "UPDATE flimshow SET showtime = ?, showdate = ?, roomid = ? WHERE showid = ?";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			psmt.setTime(1, Time.valueOf(editscreening_showTime.getValue()));
			psmt.setDate(2, Date.valueOf(editscreening_showDate.getValue()));
			psmt.setInt(3, getRoomByRoomName(editscreening_room.getValue()));
			psmt.setInt(4, ScreentoUpdate.getShowID()); // Fix: Set the show ID

			int rowsUpdated = psmt.executeUpdate();

			if (rowsUpdated > 0) {
				showAlert("Information", "Successful Update!");
				showEditScreening();
				editscreening_tableView.refresh();
			} else {
				showAlert("Error", "Update failed. Please try again.");
			}

		} catch (Exception e) {
			e.printStackTrace(); // Fix: Print exception details
			showAlert("Error", "An error occurred: " + e.getMessage());
		}
	}

	public int getRoomByRoomName(String roomname) {
		int roomid= 0;
		String sql = "Select roomid from room where roomname=? ";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setString(1, roomname);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				roomid = rs.getInt(1);
			}

		} catch (Exception e) {
			e.getMessage();

		}
		return roomid;
	}

	public ObservableList<Screening> fetchScreeningList() {
		ObservableList<Screening> screeningList = FXCollections.observableArrayList();
		String sql = "CALL getFlimshow();";

		try (Connection con = moviedatabase.connectDb();
				CallableStatement csmt = con.prepareCall(sql)) {

			csmt.execute(); // Ensure stored procedure executes before fetching results

			try (ResultSet rs = csmt.getResultSet()) {
				if (!rs.isBeforeFirst()) {
					System.out.println("No data found in getFlimshow()");
				} else {
					while (rs.next()) {
						int movieID = rs.getInt("movieid");
						String moviename = rs.getString("moviename");
						String genre = rs.getString("genre");
						LocalTime duration = rs.getTime("duration") != null ? rs.getTime("duration").toLocalTime() : null;
						String imagepath = rs.getString("imagepath");
						String description = rs.getString("description");
						int showID = rs.getInt("showid");
						LocalTime showtime = rs.getTime("showtime") != null ? rs.getTime("showtime").toLocalTime() : null;
						LocalDate showdate = rs.getDate("showdate") != null ? rs.getDate("showdate").toLocalDate() : null;
						int roomID = rs.getInt("roomid");
						String roomname = rs.getString("roomname");
						String current = rs.getString("current");
						int Revenue = rs.getInt("TotalSale");
						

						Screening screen = new Screening(movieID, moviename, genre, duration, imagepath, description, showID, showtime, showdate, roomID, roomname,current, Revenue);
						screeningList.add(screen);
					}
				}
			}

		} catch (SQLException e) {
			System.err.println("Error fetching screenings: " + e.getMessage());
		}
		return screeningList;
	}

	private ObservableList<Screening> editScreeningL = FXCollections.observableArrayList();

	public void showEditScreening() {

		// Fetch the screening list
		ObservableList<Screening> fetchedList = fetchScreeningList();
		if (fetchedList != null) {
			editScreeningL.setAll(fetchedList); // Updates the list safely
		}

		// Set up table columns
		editscreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("moviename"));
		editscreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
		editscreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		editscreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));
		editscreening_col_showDate.setCellValueFactory(new PropertyValueFactory<>("showdate"));
		editscreening_col_ShowTime.setCellValueFactory(new PropertyValueFactory<>("showtime"));
		editscreening_col_room.setCellValueFactory(new PropertyValueFactory<>("roomname"));
		editscreening_col_totalRevenue.setCellValueFactory(new PropertyValueFactory<>("Revenue"));

		// Set items to table
		editscreening_tableView.setItems(editScreeningL);
	}

	public boolean haveBookingAndSaleBySID(int showid) {
		String sql = "SELECT (SELECT COUNT(*) FROM booking WHERE showid = ?) + "
				+ "(SELECT COUNT(*) FROM sale WHERE showid = ?) AS total_count";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			psmt.setInt(1, showid);
			psmt.setInt(2, showid);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) { // If a result exists
				return rs.getInt("total_count") > 0; // Returns true if count > 0
			}

		} catch (Exception e) {
			e.printStackTrace(); // Print the exception
		}
		return false; // If an error occurs or no rows are returned, return false
	}

	@FXML
	private TextField addmovies_search;

	@FXML
	private ComboBox<String> movie_genre_search;

	@FXML
	private ComboBox<String> movie_Current_search;


	@FXML
	public void searchAddMovies() {
		FilteredList<MovieData> filter = new FilteredList<>(listAddMovies, e -> true);
		movie_genre_search.setItems(genere);
		movie_Current_search.setItems(current);
		addmovies_search.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicateMoviesData -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String keySearch = newValue.toLowerCase();

				if (predicateMoviesData.getMoviename().toLowerCase().contains(keySearch)) {
					return true;
				}else if (predicateMoviesData.getDuration().toString().toLowerCase().contains(keySearch)) {
					return true;
				} 
				return false;
			});
		});
		movie_genre_search.valueProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(movie -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}


				return movie.getGenre().equalsIgnoreCase(newValue);
			});
		});
		movie_Current_search.valueProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(movie -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}


				return movie.getCurrent().equalsIgnoreCase(newValue);
			});
		});

		SortedList<MovieData> sortData = new SortedList<>(filter);
		sortData.comparatorProperty().bind(addmovies_tableView.comparatorProperty());
		addmovies_tableView.setItems(sortData);
	}


	@FXML
	void importImage(ActionEvent event) {
		FileChooser open = new FileChooser();
		open.setTitle("Open Image File");
		open.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

		Stage stage = (Stage) addmovies_form.getScene().getWindow();
		File file = open.showOpenDialog(stage);

		if (file != null) {
			// Load and display image in ImageView
			image = new Image(file.toURI().toString(), 215, 223, false, true);
			addmovies_imageView.setImage(image);
			saveMovieImageToProjectFolder(file);
		}
	}

	// Function to copy the image to "images/" folder
	
//	public void openEditScreening() {
//		try {
//			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/com/cinema/views/dashboardadmin.fxml"));
//			Parent root2 = loader2.load();
//			Stage stage2 = (Stage) addmovies_movieTitle.getScene().getWindow();
//			Scene scene2 = new Scene(root2);
//			stage2.setScene(scene2);
//			stage2.show();
//
//			DashboardControllerAdmin controller2 = loader2.getController();
//			controller2.showEditScreening();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	void insertAddMovies() {
		String sqlCheck = "SELECT * FROM movie WHERE moviename=?";
		String sqlInsert = "INSERT INTO movie (moviename, genre, duration, imagepath, description,current) VALUES (?,?,?,?,?,?)";

		connect = moviedatabase.connectDb();
		Alert alert;

		try {
			prepare = connect.prepareStatement(sqlCheck);
			prepare.setString(1, addmovies_movieTitle.getText());
			result = prepare.executeQuery();

			if (result.next()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText(addmovies_movieTitle.getText() + " already exists!");
				alert.showAndWait();
			} else {
				// Get selected genre
				String selectedGenre = addmovies_genre.getSelectionModel().getSelectedItem();

				// Validate input fields
				if (addmovies_movieTitle.getText().isEmpty() || selectedGenre == null || duration == null ||
						addmovies_imageView.getImage() == null || addmovies_description.getText().isEmpty()) {

					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Please fill all blank fields!");
					alert.showAndWait();
				} else {
					// Convert duration to Time format (Ensure duration is a valid string like "01:30")
					Time time;
					try {
						time = Time.valueOf(duration + ":00"); // Ensure proper format
					} catch (IllegalArgumentException e) {
						alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error Message");
						alert.setHeaderText(null);
						alert.setContentText("Invalid duration format! Please use HH:MM.");
						alert.showAndWait();
						return;
					}

					// Insert into the database
					prepare = connect.prepareStatement(sqlInsert);
					prepare.setString(1, addmovies_movieTitle.getText());
					prepare.setString(2, selectedGenre);
					prepare.setTime(3, time);
					prepare.setString(4, getData.path);  // Ensure `getData.path` is set correctly
					prepare.setString(5, addmovies_description.getText());
					prepare.setString(6,"Upcoming");
					prepare.executeUpdate();

					// Success message
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully added new movie!");

					alert.showAndWait();
					addmovies_tableView.refresh();

					// Optionally refresh UI or reset fields
					
					showAddMoviesList();
					MovieCancel();
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close database resources to avoid memory leaks
			try {
				if (result != null) result.close();
				if (prepare != null) prepare.close();
				if (connect != null) connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void showAlert(Alert.AlertType type, String title, String message, Stage ownerStage) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		if (ownerStage != null) {
			alert.initOwner(ownerStage);
		}
		alert.showAndWait();
	}

	public void updateMovie() {
	    String sql = "UPDATE `movie` SET `moviename` = ?, `genre` = ?, `duration` = ?, `imagepath` = ?, `description` = ? WHERE `movie`.`movieid` = ?;";

	    connect = moviedatabase.connectDb();
	    try {
	        String selectedGenre = addmovies_genre.getSelectionModel().getSelectedItem();

	        // Check if all required fields are filled
	        if (addmovies_movieTitle.getText().isEmpty() || selectedGenre == null || duration == null ||
	                addmovies_imageView.getImage() == null || addmovies_description.getText().isEmpty()) {

	            Stage stage = (Stage) addmovies_movieTitle.getScene().getWindow();
	            showAlert(Alert.AlertType.ERROR, "Error Message", "Please select the movie first!", stage);

	        } else {
	            prepare = connect.prepareStatement(sql);
	            prepare.setString(1, addmovies_movieTitle.getText());
	            prepare.setString(2, selectedGenre);
	            prepare.setString(3, duration.toString());
	            prepare.setString(4, getData.path);
	            prepare.setString(5, addmovies_description.getText());
	            prepare.setInt(6, getData.movieId); // ⚡ safer as INT

	            prepare.executeUpdate();

	            Stage stage = (Stage) addmovies_movieTitle.getScene().getWindow();
	            showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully updated " + addmovies_movieTitle.getText(), stage);

	            // Refresh after update
	            showAddMoviesList();
	            MovieCancel();
	            addmovies_tableView.refresh();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
    @FXML
	public void MovieCancel() {
	    addmovies_insertbtn.setText("Insert");
	    addmovies_movieTitle.clear();
	    addmovies_description.clear();
	    duration = "00:00";
	    addmovies_genre.getSelectionModel().clearSelection(); 
	    addmovies_imageView.setImage(null); 
	}



	@FXML
	public void Insert_OR_Update(ActionEvent event) {
		if(addmovies_insertbtn.getText().equals("Update")) {
			updateMovie();

		}else {
			insertAddMovies();
		}
	}
	@FXML
	public void btn_addmove_show(ActionEvent event) {
		if(ToUpdate !=null) {
			Add_Movie_Form.setVisible(false);
			Show_adding_Form.setVisible(true);
			cb_add_to_show_room.setItems(getAllRoomList());
		}else {
			showAlert("Error", "Select Movie First!");
		}

	}
	@FXML
	public void btn_addmove_show_cancel(ActionEvent event) {
		Add_Movie_Form.setVisible(true);
		Show_adding_Form.setVisible(false);


	}
	@FXML
	public void AddScreening(ActionEvent event) {
	    String sql = "CALL `insert_flimshow`(?, ?, ?, ?)";
	    MovieData movie = addmovies_tableView.getSelectionModel().getSelectedItem();
	    int movieid = movie.getId();
	    int roomid = getRoomIDByRoomName(cb_add_to_show_room.getSelectionModel().getSelectedItem());
	    LocalDate startDate = Show_adding_Form_start_date.getValue();
	    LocalDate endDate = Show_adding_Form_end_date.getValue();
	    LocalTime showtime = Show_adding_Form_showtime.getValue();
	    LocalTime duration = movie.getDuration(); // get duration from selected movie

	    if (startDate == null || endDate == null) {
	        showAlert("Error", "Please select both start and end dates.");
	        return;
	    }

	    if (startDate.isAfter(endDate)) {
	        showAlert("Error", "Start date cannot be after end date.");
	        return;
	    }

	    // Check if the room is free for all dates before inserting
	    LocalDate date = startDate;
	    while (!date.isAfter(endDate)) {
	        if (!isRoomFree(roomid, date, showtime, duration)) {
	            return;
	        }
	        date = date.plusDays(1);
	    }

	    try (Connection con = moviedatabase.connectDb();
	         CallableStatement csmt = con.prepareCall(sql)) {

	        date = startDate; // Reset date back to startDate for insertion
	        while (!date.isAfter(endDate)) {
	            csmt.setInt(1, movieid);
	            csmt.setInt(2, roomid);
	            csmt.setDate(3, java.sql.Date.valueOf(date));
	            csmt.setTime(4, java.sql.Time.valueOf(showtime));
	            csmt.executeUpdate();
	            date = date.plusDays(1); // Move to the next day
	        }

	        showAlert("Success", "Inserted successfully!");
	        showEditScreening();
	        editscreening_tableView.refresh();

	    } catch (Exception e) {
	        System.out.println("AddScreening error");
	        e.printStackTrace();
	    }
	}


	public boolean isRoomFree(int roomID, LocalDate date, LocalTime newShowTime, LocalTime newMovieDuration) {
	    String sql = "SELECT fs.showtime " +
	                 "FROM flimshow fs " +
	                 "WHERE fs.roomid = ? AND fs.showdate = ?";

	    try (Connection con = moviedatabase.connectDb();
	         PreparedStatement psmt = con.prepareStatement(sql)) {

	        psmt.setInt(1, roomID);
	        psmt.setDate(2, java.sql.Date.valueOf(date));

	        ResultSet rs = psmt.executeQuery();
	        while (rs.next()) {
	            Time existingShowTime = rs.getTime("showtime");
	            LocalTime existingStart = existingShowTime.toLocalTime();

	            LocalTime existingEnd = existingStart.plusHours(newMovieDuration.getHour())
	                                                 .plusMinutes(newMovieDuration.getMinute());

	            LocalTime newStart = newShowTime;
	            LocalTime newEnd = newShowTime.plusHours(newMovieDuration.getHour())
	                                          .plusMinutes(newMovieDuration.getMinute());

	            // Check if times overlap
	            boolean overlap = !(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd));
	            if (overlap) {
	                new Alert(Alert.AlertType.WARNING,
	                        "This room is not free during this time!\n" +
	                        "Another movie is showing from " + existingStart + " to " + existingEnd,
	                        ButtonType.OK
	                ).showAndWait();
	                return false;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return true;
	}
	public int getRoomIDByRoomName(String RoomName){
		int roomID= 0;
		String sql ="Select roomid from room where roomname =?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setString(1, RoomName);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				roomID = rs.getInt(1);	
			}



		} catch (Exception e) {
			System.out.println("Get RoomID error");
			e.printStackTrace();
		}
		return roomID;

	}
	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
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
	private Image loadFoodImageFromProjectFolder(String imageName) {
		 try {
		        File file = new File("resources/com/cinema/images/foodImage/", imageName);
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
	private ObservableList<MovieData> addMoviesList() {
		ObservableList<MovieData> listData = FXCollections.observableArrayList();

		String sql = "SELECT * FROM movie";

		connect = moviedatabase.connectDb();

		try {

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			MovieData movD;

			while (result.next()) {
				movD = new MovieData(result.getInt("movieid"), result.getString("moviename"), result.getString("genre"),
						result.getTime("duration").toLocalTime(), result.getString("imagepath"), 
						result.getString("description"),result.getString("current"));
				listData.add(movD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listData;

	}

	private ObservableList<MovieData> listAddMovies;

	public void showAddMoviesList() {

		if (addmovies_tableView == null) {
			System.out.println("Error: addmovies_tableView is null");
			return;
		}

		listAddMovies = addMoviesList();

		if (listAddMovies == null) {
			System.out.println("Error: listAddMovies is null");
			return;
		}

		addmovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("moviename"));
		addmovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
		addmovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		addmovies_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		addmovies_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));
		addmovies_tableView.setItems(listAddMovies);

		addmovies_tableView.refresh();
	}
	@FXML
	public void selectAddMoviesList() {
	    ToUpdate = addmovies_tableView.getSelectionModel().getSelectedItem();
	    if (ToUpdate != null) {
	        // Control buttons visibility
	        if ("Showing".equals(ToUpdate.getCurrent())) {
	            addmovies_endbtn.setVisible(true);
	            addmovies_delete.setVisible(false);
	        } else if ("Upcoming".equals(ToUpdate.getCurrent())) {
	            addmovies_delete.setVisible(true);
	            addmovies_endbtn.setVisible(false);
	        } else {
	            addmovies_endbtn.setVisible(false);
	            addmovies_delete.setVisible(false);
	        }

	        // Set basic data
	        getData.path = ToUpdate.getImage();
	        getData.movieId = ToUpdate.getId();

	        addmovies_movieTitle.setText(ToUpdate.getMoviename());
	        addmovies_genre.setValue(ToUpdate.getGenre());

	        // Duration
	        LocalTime localTime = ToUpdate.getDuration();
	        if (localTime != null) {
	            addmovies_duration.setValue(localTime);
	        }

	        addmovies_description.setText(ToUpdate.getDescription());
	        Image imagedata = loadImageFromProjectFolder(ToUpdate.getImage());
	        if (imagedata != null) {
	            addmovies_imageView.setImage(imagedata);
	            Show_adding_Form_image.setImage(imagedata);
	        } else {
	            addmovies_imageView.setImage(null);
	            Show_adding_Form_image.setImage(null);
	        }

	        Show_adding_Form_label.setText(ToUpdate.getMoviename());

	        addmovies_insertbtn.setText("Update");
	    }
	}

	@FXML
	public void EndShowAction() {
		showAlert("Comformation ","Are you sure you end this movie?");
		String sql = "UPDATE `movie` SET `current` = 'End Showing' WHERE `movie`.`movieid` = ?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql);) {
			psmt.setInt(1, ToUpdate.getId());
			psmt.executeUpdate();

			showAddMoviesList();
			addmovies_tableView.refresh();
			
			showEditScreening();
			editscreening_tableView.refresh();

		} catch (Exception e) {
			e.getMessage();
		}

	}
	@FXML
	public void DeleteUpcomingAction() {
		showAlert("Comformation ","Are you sure you Delete this movie?");
		String sql = "DELETE FROM movie WHERE `movie`.`movieid` = ?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql);) {
			psmt.setInt(1, ToUpdate.getId());
			psmt.executeUpdate();

			showAddMoviesList();
			addmovies_tableView.refresh();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@FXML
	void logout(ActionEvent event) {
		getData.username = null;
		getData.userId = 0;

		try {
			SignOut.getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("/com/cinema/views/login.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			// Allow dragging the window
			root.setOnMousePressed((MouseEvent e) -> {
				x = e.getSceneX();
				y = e.getSceneY();
			});

			root.setOnMouseDragged((MouseEvent e) -> {
				stage.setX(e.getScreenX() - x);
				stage.setY(e.getScreenY() - y);
			});

			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}







	@FXML
	private BarChart<String, Number> BarChart;

	@FXML
	private BarChart<String, Number> sellingfrom_barchart;

	private ObservableList<MovieData> ListShowingMovie = FXCollections.observableArrayList();


	public ObservableList<MovieData> getShowingMovieGropBYName() {
		if (ListShowingMovie == null) {
			ListShowingMovie = FXCollections.observableArrayList();
		}
		ListShowingMovie.clear(); // Clear old data

		String sql = "{CALL GetTodayMovie()}";

		try (Connection con = moviedatabase.connectDb();
				CallableStatement psmt = con.prepareCall(sql);
				ResultSet rs = psmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("movieid");
				String title = rs.getString("moviename");
				String genre = rs.getString("genre");
				LocalTime duration = rs.getTime("duration").toLocalTime(); // Convert SQL Time to LocalTime
				String image = rs.getString("imagepath");
				String description = rs.getString("description");
				String current = rs.getString("current");

				MovieData movD = new MovieData(id, title, genre, duration, image, description,current);
				ListShowingMovie.add(movD);
			}

		} catch (SQLException e) {
			System.out.println("getShowingMovie error");
			e.printStackTrace();
		}

		return ListShowingMovie;
	}
	public ObservableList<MovieData> getEndShowingMovie() {
		ObservableList<MovieData> listdata = FXCollections.observableArrayList();
		String sql = "CALL `getEndShowingMovie`();";
		try (Connection con = moviedatabase.connectDb();
				CallableStatement csmt = con.prepareCall(sql);
				ResultSet rs = csmt.executeQuery()) {

			while (rs.next()) {
				int movieid =rs.getInt("movieid");
				String moviename = rs.getString("moviename");
				String genre = rs.getString("genre");
				LocalTime duration= rs.getTime("duration").toLocalTime();
				String imagepath= rs.getString("imagepath");
				String decription = rs.getString("description");
				String current = rs.getString("current");
				MovieData mdata = new MovieData(movieid, moviename, genre, duration, imagepath, decription,current);
				listdata.add(mdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdata;
	}
	public ObservableList<MovieData> getShowingMovie() {
		ObservableList<MovieData> listdata = FXCollections.observableArrayList();
		String sql = "CALL `getShowingMovie`()";
		try (Connection con = moviedatabase.connectDb();
				CallableStatement csmt = con.prepareCall(sql);
				ResultSet rs = csmt.executeQuery()) {

			while (rs.next()) {
				int movieid =rs.getInt("movieid");
				String moviename = rs.getString("moviename");
				String genre = rs.getString("genre");
				LocalTime duration= rs.getTime("duration").toLocalTime();
				String imagepath= rs.getString("imagepath");
				String description = rs.getString("description");
				String current = rs.getString("current");
				MovieData mdata = new MovieData(movieid, moviename, genre, duration, imagepath, description,current);
				listdata.add(mdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdata;
	}

	ObservableList<MovieData>  list = getShowingMovie();
	public void ShowMovieReport() {

		list.addAll(getEndShowingMovie());
		report_movie_Table_movieName.setCellValueFactory(new PropertyValueFactory<>("moviename"));
		report_movie_Table_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
		report_movie_Table_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		report_movie_Table.setItems(list);
		Platform.runLater(() -> {
			if (!report_movie_Table.getItems().isEmpty()) {
				report_movie_Table.getSelectionModel().selectFirst();
				Report_Movie_ChoiceOnAction();
			}
		});

		// Add listener to auto update when selection changes
		report_movie_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
			if (newSel != null) {
				report_choice_movie = newSel;
				Report_Movie_ChoiceOnAction(); 
				loadGenreSalesPieChart();
			}
		});
	}


	@FXML TextField report_search;
	public void searchReport() {
		FilteredList<MovieData> filter = new FilteredList<>(list, e -> true);

		report_search.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicateMoviesData -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String keySearch = newValue.toLowerCase();

				if (predicateMoviesData.getMoviename().toLowerCase().contains(keySearch)) {
					return true;
				} else if (predicateMoviesData.getGenre().toLowerCase().contains(keySearch)) {
					return true;
				}else if (predicateMoviesData.getDuration().toString().toLowerCase().contains(keySearch)) {
					return true;
				} 
				return false;
			});
		});

		SortedList<MovieData> sortData = new SortedList<>(filter);
		sortData.comparatorProperty().bind(report_movie_Table.comparatorProperty());
		report_movie_Table.setItems(sortData);
	}



	MovieData report_choice_movie;

	@FXML
	public void Report_Movie_ChoiceOnAction() {
		report_choice_movie = report_movie_Table.getSelectionModel().getSelectedItem();
		loadSaleByMovie();
		int movieID = report_choice_movie.getId();
		report_movie_label.setText(report_choice_movie.getMoviename());
		report_lb_show_times.setText(getShowCountByMID(movieID)+"");
		report_lb_sold_ticket.setText(getToatalSaleTicketByMID(movieID)+"");
		report_lb_cancel_book.setText(getToatalBookingTicketByMID(movieID)+"");
		report_lb_showedRoom.setText(getShowedRoomsByMID(movieID));
		report_lb_showedDate.setText(getShowedDateByMID(movieID));
		report_lb_revenue.setText(getToatalRevenueByMID(movieID)+"");
	}
	public int getShowCountByMID(int movieID) {
		int showtimes =0;
		String sql = "Select count(*) from flimshow where movieid=?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, movieID);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				showtimes=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getShowCountByMID error: "+e.getMessage());
		}

		return showtimes;


	}
	public int getToatalSaleTicketByMID(int movieID) {
		int totalticket =0;
		String sql = "Select count(*) from flimshow fs JOIN Sale s ON fs.showid=s.showid where movieid=?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, movieID);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				totalticket=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getShowCountByMID error: "+e.getMessage());
		}

		return totalticket;


	}
	public int getToatalBookingTicketByMID(int movieID) {
		int totalticket =0;
		String sql = "Select count(*) from flimshow fs JOIN booking s ON fs.showid=s.showid where movieid=?";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, movieID);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				totalticket=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getShowCountByMID error: "+e.getMessage());
		}

		return totalticket;


	}
	public String getShowedRoomsByMID(int movieID) {
		String Rooms =null;
		String sql = "SELECT GROUP_CONCAT(DISTINCT ro.roomname ORDER BY ro.roomname ASC)"
				+ " AS room_names FROM movie m JOIN flimshow fs ON m.movieid = fs.movieid "
				+ "JOIN room ro ON fs.roomid = ro.roomid WHERE fs.movieid = ?;";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, movieID);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				Rooms=rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("getShowCountByMID error: "+e.getMessage());
		}

		return Rooms;

	}
	public String getShowedDateByMID(int movieID) {
		String Date =null;
		String sql = "SELECT GROUP_CONCAT(DISTINCT fs.showdate ORDER BY fs.showdate ASC) "
				+ "FROM flimshow fs JOIN movie m ON m.movieid=fs.movieid WHERE fs.movieid=?;";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, movieID);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				Date=rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("getShowCountByMID error: "+e.getMessage());
		}

		return Date;

	}
	public int getToatalRevenueByMID(int movieID) {
		int totalRevenue =0;
		String sql = "SELECT TotalSale FROM flimshow WHERE movieid =?;";
		try(Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)){
			psmt.setInt(1, movieID);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				totalRevenue+=rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getShowCountByMID error: "+e.getMessage());
		}

		return totalRevenue;


	}



	//Method to get total price from stored procedure
	public int getTotalPriceByMovieID(int movieid) {
		int totalPrice = 0; // Default value
		String sql = "CALL GetTotalPriceByMovieId(?, ?)"; // Calling stored procedure

		try (Connection conn = moviedatabase.connectDb();
				CallableStatement stmt = conn.prepareCall(sql)) {

			stmt.setInt(1, movieid);
			stmt.registerOutParameter(2, java.sql.Types.DECIMAL); // Correct type for DECIMAL(10,2)

			stmt.execute();

			BigDecimal price = stmt.getBigDecimal(2); // Retrieve DECIMAL value
			if (price != null) {
				totalPrice = price.intValue(); // Convert to int
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPrice;
	}

	public void loadBarChart() {
		BarChart.getData().clear();           // Clear old data from first chart
		sellingfrom_barchart.getData().clear(); // Clear old data from second chart

		// Create FIRST series for BarChart
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Total Sales (Chart 1)");

		// Create SECOND series for sellingfrom_barchart
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Total Sales (Chart 2)");

		ObservableList<MovieData> movies = getShowingMovieGropBYName();

		for (MovieData movie : movies) {
			int totalPrice = getTotalPriceByMovieID(movie.getId());
			series1.getData().add(new XYChart.Data<>(movie.getMoviename(), totalPrice));
			series2.getData().add(new XYChart.Data<>(movie.getMoviename(), totalPrice));
		}


		BarChart.getData().add(series1);
		sellingfrom_barchart.getData().add(series2);

		totalSold.setText(getTotalSalesPrice() + "");
		totalTicket.setText(getTotalSalesCount() + "");
		avaliableMovie.setText(ListShowingMovie.size() + "");


	}



	@FXML
	private PieChart piechart;

	public void loadPieChart() {
		piechart.getData().clear(); // Clear old data

		ObservableList<MovieData> movies = getShowingMovieGropBYName();
		Map<String, Integer> movieSalesMap = new HashMap<>();

		// Sum up total sales per movie
		for (MovieData movie : movies) {
			int totalPrice = getTotalPriceByMovieID(movie.getId()); // Get total sales

			// Truncate movie name if longer than 10
			String name = movie.getMoviename();
			if (name.length() > 15) {
				name = name.substring(0, 15) + "...";
			}

			movieSalesMap.put(name, movieSalesMap.getOrDefault(name, 0) + totalPrice);
		}

		// Convert to PieChart Data
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		for (Map.Entry<String, Integer> entry : movieSalesMap.entrySet()) {
			pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
		}

		piechart.setData(pieChartData);

		Platform.runLater(() -> {
			for (Node node : piechart.lookupAll(".chart-pie-label")) {
				if (node instanceof Text) {
					((Text) node).setFill(Color.WHITE);
				}
			}
		});

	}



	public int getTotalSalesPrice() {
		int totalPrice = 0;
		String sql = "SELECT SUM(se.price) FROM sale s JOIN seat se ON s.seatid = se.seatid JOIN "
				+ "flimshow fs ON s.showid = fs.showid WHERE fs.showdate= CURDATE();";

		try (Connection conn = moviedatabase.connectDb();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				totalPrice = rs.getInt(1); // Get the total sales price
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPrice;
	}
	public int getTotalSalesCount() {
		int totalRows = 0;
		String sql = "SELECT COUNT(*) FROM sale s JOIN flimshow fs ON s.showid = fs.showid WHERE fs.showdate = CURDATE();";

		try (Connection conn = moviedatabase.connectDb();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				totalRows = rs.getInt(1); // Get the count result
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRows;
	}


	@FXML
	private AnchorPane selling_form;


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
	private TableColumn<Sale, String> selling_col_saletime;
	@FXML
	private TableColumn<Sale, String> selling_col_staff;



	public ArrayList<Sale> getSaleByMovieID(int movieID) {
		ArrayList<Sale> allSale = new ArrayList<>();
		String sql = "{CALL getSaleDetailsBYMID(?)}";

		try (Connection conn = moviedatabase.connectDb();
				CallableStatement cstmt = conn.prepareCall(sql)) {
			cstmt.setInt(1, movieID);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					LocalDate saleDate = rs.getDate("SaleDate").toLocalDate();
					Sale sale = new Sale(
							rs.getString("moviename"),
							rs.getDate("showdate").toLocalDate(),
							rs.getString("showtime"),
							rs.getString("roomname"),
							rs.getString("seatid"),
							rs.getString("price"), 
							saleDate,
							rs.getInt("userid"),
							rs.getString("name"),
							rs.getString("voucherNum")
							);
					allSale.add(sale);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error fetching sales data: " + e.getMessage());
		}
		return allSale;
	}

	public void AddToSaleTable() {
		selling_col_movietitle.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
		selling_col_show_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		selling_col_show_time.setCellValueFactory(new PropertyValueFactory<>("showTime"));
		selling_col_room.setCellValueFactory(new PropertyValueFactory<>("room"));
		selling_col_seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
		selling_col_totalcost.setCellValueFactory(new PropertyValueFactory<>("price"));
		selling_col_saletime.setCellValueFactory(new PropertyValueFactory<>("SaleDate"));
		selling_col_staff.setCellValueFactory(new PropertyValueFactory<>("userName"));

	}
	public void refreshSaleTable(int movieID) {
		ObservableList<Sale> sale = FXCollections.observableArrayList(getSaleByMovieID(movieID));
		Sale_table.setItems(sale);
		Sale_table.refresh();
	}

	@FXML
	private ComboBox<MovieData> cbSelecteMovie;
	@FXML
	private Label cb_selected_Mtitle;

	@FXML
	private ImageView cb_selected_Mimage;

	@FXML
	private Label selecteM_LbSoldSeat;

	@FXML
	private Label selecteM_LbFreeSeat;

	@FXML
	private Label selecteM_LbBookedSeat;

	@FXML
	private Label selecteM_LbTotalprice;

	public void ShowSelectedInfo() {

		cbSelecteMovie.setItems(ListShowingMovie);

		if (!ListShowingMovie.isEmpty()) {
			MovieData firstMovie = ListShowingMovie.get(0); // Get first movie
			cbSelecteMovie.getSelectionModel().selectFirst();
			updateMovieInfo(firstMovie);
		}

		// 3. Add listener for future selections
		cbSelecteMovie.valueProperty().addListener((obs, oldValue, selectedData) -> {
			if (selectedData != null) {
				updateMovieInfo(selectedData); // Update UI on selection change
				loadSeatTypePieChart(selectedData.getId());
				System.out.println(selectedData.getId());
			}
		});
	}

	// Helper method to update UI elements
	private void updateMovieInfo(MovieData movie) {
		try {
			// Validate input
			if (movie == null) {
				throw new IllegalArgumentException("MovieData cannot be null");
			}

			// Calculate seat availability
			int soldSeat = getSaleConuntByMovieID(movie.getId());
			int bookedSeat = getBookedConuntByMovieID(movie.getId());

			// Update UI elements
			cb_selected_Mtitle.setText(movie.getMoviename());
			selecteM_LbTotalprice.setText(String.valueOf(getTotalPriceByMovieID(movie.getId())));
			selecteM_LbSoldSeat.setText(String.valueOf(soldSeat));
			selecteM_LbBookedSeat.setText(String.valueOf(bookedSeat));

			// Refresh sales table
			refreshSaleTable(movie.getId());

			Image imagdata = loadImageFromProjectFolder(movie.getImage());
			cb_selected_Mimage.setImage(imagdata);

		} catch (Exception e) {
			System.err.println("Error updating movie info: " + e.getMessage());
			e.printStackTrace();
			// Optional: show an alert to the user
		}
	}




	public int getSaleConuntByMovieID(int movieID) {
		int count = 0;
		String sql = "CALL `getSoldSeatForTodaybyMID`(?)";


		try(Connection con = moviedatabase.connectDb();
				CallableStatement psmt = con.prepareCall(sql);) {
			psmt.setInt(1, movieID);

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("GetSaleContByMovieID method error");
			e.printStackTrace();
		}
		return count;

	}
	public int getBookedConuntByMovieID(int showID) {
		int count = 0;
		String sql = "CALL getBookedSeatForTodaybyMID(?)";


		try(Connection con = moviedatabase.connectDb();
				CallableStatement psmt = con.prepareCall(sql);) {
			psmt.setInt(1, showID);

			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("GetbookedContByMovieID method error");
			e.printStackTrace();
		}
		return count;

	}

	@FXML
	private PieChart selling_form_pichart;


	public void loadSeatTypePieChart(int movieId) {
		// Clear previous data
		selling_form_pichart.getData().clear();

		// Query to count sold seats by type for the given movie
		String sql = "SELECT s.type, COUNT(*) as count FROM sale sa "
				+ "JOIN flimshow fs ON sa.showid = fs.showid JOIN seat s ON sa.seatid = s.seatid"
				+ " WHERE fs.movieid = ? and fs.showdate=CURDATE() GROUP BY s.type;";

		try (Connection conn = moviedatabase.connectDb();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, movieId);
			ResultSet rs = pstmt.executeQuery();

			// Create pie chart data
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

			while (rs.next()) {
				String seatType = rs.getString("type");
				int count = rs.getInt("count");
				pieChartData.add(new PieChart.Data(seatType + " (" + count + ")", count));
			}

			// Set up the pie chart
			selling_form_pichart.setData(pieChartData);

			// Delay color change until after animation
			Platform.runLater(() -> {
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
					for (Node node : selling_form_pichart.lookupAll(".chart-pie-label")) {
						if (node instanceof Text) {
							((Text) node).setFill(Color.WHITE); 
						}
					}
				}));
				timeline.play();
			});

		} catch (SQLException e) {
			System.out.println("Error loading pie chart data: " + e.getMessage());
		}
	}


  



 @FXML
	private ImageView Admin_Image;
	  
 User CurrentUser;

	public void displayUsername(User user) {
		if (user != null) {
			this.CurrentUser = user;
	         username.setText(user.getUserName());

	         try {
			        File file = new File("resources/com/cinema/images/userImage/", user.getImagePath());
			        if (file.exists()) {
			            String uri = file.toURI().toString();
			            System.out.println("Loading image from: " + uri);
			            Admin_Image.setImage(new Image(uri));
			            double radius = Math.min(Admin_Image.getFitWidth(), Admin_Image.getFitHeight()) / 2;
			            Circle clip = new Circle(radius, radius, radius);
			            Admin_Image.setClip(clip);
			            
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
	            controller.setAdminController(this);
	            controller.setData(CurrentUser);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.err.println("CurrentUser is not set!");
	    }
	}


	@FXML
	void closebtn(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void minimize(ActionEvent event) {
		Stage stage = (Stage) topform.getScene().getWindow();
		stage.setIconified(true);
	}




	//setting 
	@FXML
	void setting(ActionEvent event) {
		try {

			FXMLLoader popupLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/setting.fxml"));
			Parent popupRoot = popupLoader.load();
			
			settingcontroller controller = popupLoader.getController();
			controller.SetDashboardAdminController(this);
			controller.CurrentUser= CurrentUser;
			Stage popupStage = new Stage();
			popupStage.setTitle("Setting");
			popupStage.setScene(new Scene(popupRoot));


			popupStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	@FXML
	void createAccount() {
		try {

			FXMLLoader popupLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/signup.fxml"));
			Parent popupRoot = popupLoader.load();


			Stage popupStage = new Stage();
			popupStage.setTitle("Add Account");
			popupStage.setScene(new Scene(popupRoot));


			popupStage.show();

			signupController controller = popupLoader.getController();
			controller.setAdminController(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
	private AnchorPane food_form;

	@FXML
	private AnchorPane food_adding_form;

	@FXML
	private AnchorPane Staff_Form;

	@FXML
	private ImageView addfood_imageview;

	@FXML
	private Button foodDrink_btn;

	@FXML
	private Button manage_staff__btn;

	@FXML
	private Button addfood_import;

	@FXML
	private TextField add_food_drink;

	@FXML
	private ComboBox<String> add_category;

	@FXML
	private TextField add_price;

	@FXML
	private TableView<FoodData> addfood_tableview;

	@FXML
	private TableColumn<FoodData, String> add_food_col;

	@FXML
	private TableColumn<FoodData, String> add_col_category;

	@FXML
	private TableColumn<FoodData, String> add_col_price;

	@FXML
	private Button food_insert_btn;

	@FXML
	private Button food_clear;

	@FXML
	private Button food_delete_btn;

	@FXML
	private TextField food_search;

	private String[] categoryList = {"food","drink","set","other"};

	ObservableList<String> category = FXCollections.observableArrayList(categoryList);
	@FXML
	public void searchfood() {
		FilteredList<FoodData> filter = new FilteredList<>(listFood, e -> true);

		food_search.textProperty().addListener((Observable, oldValue, newValue) -> {
			filter.setPredicate(predicateFoodData -> {
				if (newValue.isEmpty() || newValue == null) {
					return true;
				}
				String keySearch = newValue.toLowerCase();

				if (predicateFoodData.getFname().toLowerCase().contains(keySearch)) {
					return true;
				} else if (predicateFoodData.getFcategory().toLowerCase().contains(keySearch)) {
					return true;
				}
				return false;
			});
		});

		SortedList<FoodData> sortData = new SortedList<>(filter);
		sortData.comparatorProperty().bind(addfood_tableview.comparatorProperty());
		addfood_tableview.setItems(sortData);
	}

	@FXML
	void deleteFood(ActionEvent event) {
		String sql = "DELETE FROM food WHERE food_name = '" + add_food_drink.getText() + "'";
		connect = moviedatabase.connectDb();

		try {
			statement = connect.createStatement();
			Alert alert;

			if (add_food_drink.getText().isEmpty() || add_category.getPromptText().isEmpty()
					|| add_price.getText().isEmpty()) {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please select the food first");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText("Are you sure you want to delete " + add_food_drink.getText() + "?");

				Optional<ButtonType> option = alert.showAndWait();

				if (ButtonType.OK.equals(option.get())) {
					statement.executeUpdate(sql);

					showAddMoviesList();
					//clearAddMoviesList(event);
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Delete");
					alert.showAndWait();
					addfoodList();
					showFoodList();
					addfood_tableview.refresh();
				} else {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void importFImage(ActionEvent event) {
		FileChooser open = new FileChooser();
		open.setTitle("Open Image File");
		open.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

		Stage stage = (Stage) food_form.getScene().getWindow();
		File file = open.showOpenDialog(stage);

		if (file != null) {
			// Load and display image in ImageView
			image = new Image(file.toURI().toString(), 215, 223, false, true);
			addfood_imageview.setImage(image);

			// Save image to project folder and store only the name
			saveFImageToProjectFolder(file);
		}
	}

	private void saveFImageToProjectFolder(File selectedFile) {
		// Define the target directory relative to the project
		File directory = new File("resources/com/cinema/images/foodImage");

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
	private void saveMovieImageToProjectFolder(File selectedFile) {
		// Define the target directory relative to the project
		File directory = new File("resources/com/cinema/images/movieImage");

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
	public void foodId() {
		String sql = "SELECT COUNT(id) FROM food";
		connect = moviedatabase.connectDb();
		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				getData.foodId = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void updateFood() {
		String sql = "UPDATE 'food' SET food_name=?, food_image=?, food_price=?, food_category=?;";

		connect = moviedatabase.connectDb();
		Alert alert;

		try {
			String selectedCategory =add_category.getValue();
			if (add_food_drink.getText().isEmpty() || selectedCategory == null ||
					addfood_imageview.getImage() == null || add_price.getText().isEmpty()) {

				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields!");
				alert.showAndWait();
			} else {
				// Insert into the database
				prepare = connect.prepareStatement(sql);
				prepare.setString(1, add_food_drink.getText());
				prepare.setString(2, getData.path);  // Ensure `getData.path` is set correctly
				prepare.setInt(3, Integer.parseInt(add_price.getText()));  // Ensure `getData.path` is set correctly
				prepare.setString(4, selectedCategory);
				prepare.executeUpdate();

				// Success message
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully updat food!");
				alert.showAndWait();
				addfoodList();
				showFoodList();
				addfood_tableview.refresh();

				// Optionally refresh UI or reset fields
				clearFoodList(null);
				showFoodList();
				//openEditScreening();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@FXML
	public boolean validFoodPrice() {

		Pattern pattern = Pattern.compile("^[1-9][0-9]*$");

		Matcher match = pattern.matcher(add_price.getText());
		Alert alert;
		if (match.find() && match.group().matches(add_price.getText())) {

			return true;
		} else {

			alert = new Alert(AlertType.ERROR);

			alert.setTitle("Error Message");
			alert.setHeaderText(null);
			alert.setContentText("Invalid food price");
			alert.showAndWait();

			return false;
		}
	}
	
	@FXML
	void insertFood(ActionEvent event) {
		String sqlCheck = "SELECT * FROM food WHERE food_name=?";
		String sqlInsert = "INSERT INTO food (food_name, food_image, food_price, food_category) VALUES (?,?,?,?)";

		connect = moviedatabase.connectDb();
		Alert alert;

		try {
			prepare = connect.prepareStatement(sqlCheck);
			prepare.setString(1, add_food_drink.getText());
			result = prepare.executeQuery();

			if (result.next()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText(add_food_drink.getText() + " already exists!");
				alert.showAndWait();
			} else {
				String selectedCategory =add_category.getValue();

				// Validate input fields
				if (add_food_drink.getText().isEmpty() || selectedCategory == null ||
						addfood_imageview.getImage() == null || add_price.getText().isEmpty()) {

					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Please fill all blank fields!");
					alert.showAndWait();
				} else {
					// Insert into the database
					if( validFoodPrice()) {
					prepare = connect.prepareStatement(sqlInsert);
					prepare.setString(1, add_food_drink.getText());
					prepare.setString(2, getData.path);  // Ensure `getData.path` is set correctly
					prepare.setInt(3, Integer.parseInt(add_price.getText()));  // Ensure `getData.path` is set correctly
					prepare.setString(4, selectedCategory);
					prepare.executeUpdate();

					// Success message
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully added new food!");
					alert.showAndWait();
					addfoodList();
					showFoodList();
					addfood_tableview.refresh();

					// Optionally refresh UI or reset fields
					clearFoodList(null);
					showFoodList();
					//openEditScreening();
				}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close database resources to avoid memory leaks
			try {
				if (result != null) result.close();
				if (prepare != null) prepare.close();
				if (connect != null) connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	void clearFoodList(ActionEvent event) {
		add_food_drink.setText("");
		add_category.getSelectionModel().clearSelection();
		add_category.setValue(null);
		add_price.setText("");
		addfood_imageview.setImage(null);
	}

	private ObservableList<FoodData> addfoodList() {
		ObservableList<FoodData> listData = FXCollections.observableArrayList();

		String sql = "SELECT * FROM food";

		connect = moviedatabase.connectDb();

		try {

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			FoodData selected_food;

			while (result.next()) {
				selected_food = new FoodData(result.getInt("food_id"), result.getString("food_name"), result.getString("food_image"),
						result.getInt("food_price"), result.getString("food_category"));
				listData.add(selected_food);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listData;

	}

	private ObservableList<FoodData> listFood;

	public void showFoodList() {

		if (addfood_tableview == null) {
			System.out.println("Error: addfood_tableview is null");
			return;
		}

		listFood = addfoodList();

		if (listFood == null) {
			System.out.println("Error: listFood is null");
			return;
		}

		add_food_col.setCellValueFactory(new PropertyValueFactory<>("fname"));
		add_col_category.setCellValueFactory(new PropertyValueFactory<>("fcategory"));
		add_col_price.setCellValueFactory(new PropertyValueFactory<>("fprice"));
		addfood_tableview.setItems(listFood);

		addfood_tableview.refresh();
	}

	FoodData selected_food ;
	public void selectFoodList() {
		selected_food = addfood_tableview.getSelectionModel().getSelectedItem();
		System.out.println(selected_food.getFname()+"1234");

		add_food_drink.setText(selected_food.getFname());
		add_category.setPromptText(selected_food.getFcategory());
		add_price.setText(selected_food.getFprice()+"");
		Image image = loadFoodImageFromProjectFolder(selected_food.getFimage());
		addfood_imageview.setImage(image);
		addfood_tableview.refresh();
	}
	@FXML
	private LineChart<String, Number> report_ticket_sale_over_time;

	@FXML
	private LineChart<String, Number> report_sale_over_time_byMovie;


	@FXML
	private DatePicker startDatePicker;

	@FXML
	private DatePicker endDatePicker;

	@FXML
	private RadioButton rbWeekly;

	@FXML
	private RadioButton rbMonthly;

	@FXML
	private RadioButton rbYearly;
	@FXML
	private RadioButton rbDaily;

	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	@FXML
	private CategoryAxis sale_over_time_byMoviexAxis;
	@FXML
	private NumberAxis sale_over_time_byMovieyAxis;
	private void setupToggleGroup() {
		ToggleGroup group = new ToggleGroup();
		rbWeekly.setToggleGroup(group);
		rbMonthly.setToggleGroup(group);
		rbYearly.setToggleGroup(group);
		rbDaily.setToggleGroup(group);

		// Set default selection
		rbWeekly.setSelected(true);  // Set the default option to Weekly or any option you prefer

		// Set up listeners for radio buttons
		rbWeekly.selectedProperty().addListener((observable, oldValue, newValue) -> updateChart());
		rbMonthly.selectedProperty().addListener((observable, oldValue, newValue) -> updateChart());
		rbYearly.selectedProperty().addListener((observable, oldValue, newValue) -> updateChart());

		// Set up listeners for DatePickers
		startDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> updateChart());
		endDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> updateChart());

		LocalDate today = LocalDate.now();
		LocalDate lastMonth = today.minusDays(60);
		startDatePicker.setValue(lastMonth);
		endDatePicker.setValue(today);
		updateChart();
	}
	private void updateChart() {
		// Determine the selected mode (Weekly, Monthly, Yearly, or Date Range)
		String mode = "DAYLIY";
		if (rbWeekly.isSelected()) {
			mode = "WEEK";
		} else if (rbMonthly.isSelected()) {
			mode = "MONTH";
		} else if (rbYearly.isSelected()) {
			mode = "YEAR";
		} else if (rbDaily.isSelected()) {
			mode="DAILY";
		}

		// Get the selected date range
		LocalDate start = startDatePicker.getValue();
		LocalDate end = endDatePicker.getValue();

		// If no date range is selected, default to last 30 days
		if (start == null || end == null) {
			LocalDate today = LocalDate.now();
			start = today.minusDays(60);
			end = today;
		}

		loadSalesData(mode, start, end);
		loadSalesByDayOfWeek(start, end);
	}

	@FXML
	private BarChart<String, Number> barChartByDayOfWeek;
	@FXML
	private CategoryAxis dayOfWeekXAxis;
	@FXML
	private NumberAxis ticketSalesYAxis;

	private void loadSalesData(String mode, LocalDate start, LocalDate end) {
		// Clear previous chart data first
		report_ticket_sale_over_time.getData().clear();
		report_ticket_sale_over_time.getXAxis().setAnimated(false); 
		report_ticket_sale_over_time.getYAxis().setAnimated(false);

		XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
		revenueSeries.setName("Revenue");

		String sql = "";
		Map<String, Double> dataMap = new LinkedHashMap<>();

		if (mode.equals("WEEK")) {
			sql = "SELECT YEARWEEK(showdate) AS Label, SUM(TotalSale) AS Total " +
					"FROM flimshow WHERE showdate BETWEEN ? AND ? GROUP BY YEARWEEK(showdate)";
		} else if (mode.equals("MONTH")) {
			sql = "SELECT MONTH(showdate) AS MonthNumber, SUM(TotalSale) AS Total " +
					"FROM flimshow WHERE showdate BETWEEN ? AND ? GROUP BY MONTH(showdate)";
		} else if (mode.equals("YEAR")) {
			sql = "SELECT YEAR(showdate) AS YearLabel, SUM(TotalSale) AS Total " +
					"FROM flimshow WHERE showdate BETWEEN ? AND ? GROUP BY YEAR(showdate)";
		} else {
			// DAILY
			sql = "SELECT showdate AS Label, SUM(TotalSale) AS Total " +
					"FROM flimshow WHERE showdate BETWEEN ? AND ? GROUP BY showdate";
		}

		try (Connection conn = moviedatabase.connectDb();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDate(1, java.sql.Date.valueOf(start));
			stmt.setDate(2, java.sql.Date.valueOf(end));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String label;
				double total = rs.getDouble(2);

				if (mode.equals("WEEK")) {
					int yearWeek = rs.getInt(1);
					int week = yearWeek % 100;
					int year = yearWeek / 100;
					label = "Week " + week + " (" + year + ")";
				} else if (mode.equals("MONTH")) {
					int monthNum = rs.getInt(1);
					label = Month.of(monthNum).name(); // JANUARY, etc.
					label = label.substring(0, 1).toUpperCase() + label.substring(1).toLowerCase();
				} else if (mode.equals("YEAR")) {
					label = rs.getString(1); // Just the year
				} else {
					label = rs.getDate(1).toString(); // yyyy-MM-dd
				}

				dataMap.put(label, total);
			}

			for (Map.Entry<String, Double> entry : dataMap.entrySet()) {
				revenueSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
			}

			report_ticket_sale_over_time.getData().add(revenueSeries);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void loadSalesByDayOfWeek(LocalDate start, LocalDate end) {
		XYChart.Series<String, Number> dayOfWeekSeries = new XYChart.Series<>();
		dayOfWeekSeries.setName("Sales by Day of Week");

		String[] dayLabels = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		double[] daySales = new double[7]; // Default 0 for all days

		String sql = "SELECT DAYOFWEEK(showdate) AS DayNumber, SUM(TotalSale) AS Total " +
				"FROM flimshow WHERE showdate BETWEEN ? AND ? " +
				"GROUP BY DAYOFWEEK(showdate)";

		try (Connection conn = moviedatabase.connectDb();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDate(1, java.sql.Date.valueOf(start));
			stmt.setDate(2, java.sql.Date.valueOf(end));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int dayNum = rs.getInt("DayNumber"); // 1 = Sunday, 7 = Saturday
				double total = rs.getDouble("Total");
				daySales[dayNum - 1] = total; // Fill correct index
			}

			// Add all 7 days to chart, even if sales = 0
			for (int i = 0; i < 7; i++) {
				dayOfWeekSeries.getData().add(new XYChart.Data<>(dayLabels[i], daySales[i]));
			}

			barChartByDayOfWeek.getData().clear();
			barChartByDayOfWeek.getData().add(dayOfWeekSeries);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void loadSaleByMovie() {
		if (report_choice_movie == null) {
			return;
		}

		XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
		revenueSeries.setName("Revenue - " + report_choice_movie.getMoviename());

		String sql = "SELECT fs.showdate, SUM(fs.TotalSale) AS total " +
				"FROM flimshow fs " +
				"JOIN movie m ON fs.movieid = m.movieid " +
				"WHERE m.movieid = ? " +
				"GROUP BY fs.showdate " +
				"ORDER BY fs.showdate";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			psmt.setInt(1, report_choice_movie.getId());
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String showDate = rs.getString("showdate");
				int totalSale = rs.getInt("total");

				revenueSeries.getData().add(new XYChart.Data<>(showDate, totalSale));
			}


			report_sale_over_time_byMovie.getData().clear();
			report_sale_over_time_byMovie.getData().add(revenueSeries);

		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private PieChart genreSalesPieChart;
	public void loadGenreSalesPieChart() {
		String sql = "SELECT m.genre, SUM(fs.TotalSale) AS Total " +
				"FROM flimshow fs " +
				"JOIN movie m ON fs.movieid = m.movieid " +
				"GROUP BY m.genre";

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql)) {

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				String genre = rs.getString("genre");
				int totalSale = rs.getInt("Total");

				// Add each genre with total sales to the pie chart data
				pieChartData.add(new PieChart.Data(genre, totalSale));
			}

			// Update the pie chart with the new data
			genreSalesPieChart.setData(pieChartData);

			Platform.runLater(() -> {
				Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
					for (Node node : genreSalesPieChart.lookupAll(".chart-pie-label")) {
						if (node instanceof Text) {
							((Text) node).setFill(Color.WHITE); 
						}
					}
				}));
				timeline.play();
			});

		} catch (SQLException e) {
			System.err.println(" Error fetching genre sales: " + e.getMessage());
			e.printStackTrace();
		}
	}
	@FXML
	private StackedBarChart<String, Number> sal_par_movie_chart;

	@FXML
	private CategoryAxis sal_par_movie_xAxis;

	@FXML
	private NumberAxis sal_par_movie_yAxis;

	public void loadMovieSalesData() {
		String sql = "SELECT m.moviename, SUM(fs.TotalSale) AS total " +
				"FROM flimshow fs " +
				"JOIN movie m ON fs.movieid = m.movieid " +
				"WHERE fs.showdate >= CURDATE() - INTERVAL 30 DAY " +
				"GROUP BY m.moviename";

		try (Connection conn = moviedatabase.connectDb();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName("Total Sales (Last 30 Days)");

			while (rs.next()) {
				String fullName = rs.getString("moviename");
				String shortName = fullName.length() > 10 ? fullName.substring(0, 10) + "..." : fullName;
				double totalSale = rs.getDouble("total");

				XYChart.Data<String, Number> data = new XYChart.Data<>(shortName, totalSale);

				// Tooltip with full name and sale value
				Tooltip tooltip = new Tooltip(fullName + ": " + totalSale);
				data.nodeProperty().addListener((obs, oldNode, newNode) -> {
					if (newNode != null) {
						Tooltip.install(newNode, tooltip);
					}
				});

				series.getData().add(data);
			}

			sal_par_movie_chart.getData().clear();
			sal_par_movie_chart.getData().add(series);

			// Rotate labels for readability
			sal_par_movie_chart.getXAxis().setTickLabelRotation(45);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ObservableList<User> getAllUsers() {
		ObservableList<User> users = FXCollections.observableArrayList();
		String sql = "SELECT * FROM user";

		try (Connection con = moviedatabase.connectDb();
				PreparedStatement psmt = con.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery()) {

			while (rs.next()) {
				LocalDate hireDate = rs.getDate("Hire_Date") != null ? rs.getDate("Hire_Date").toLocalDate() : null;

				User user = new User(
						rs.getInt("userid"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("usertype"),
						rs.getString("phone"),
						hireDate,
						rs.getString("address"),
						rs.getInt("salary"),
						rs.getString("status"),
						rs.getString("imagepath"),
						rs.getString("Password")
						);
				users.add(user);
			}

		} catch (SQLException e) {
			System.err.println("Error fetching users: " + e.getMessage());
			e.printStackTrace();
		}

		return users;
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
	        dashboard_btn, addMovies_btn, editScreening_btn,
	        selling_btn, report_btn, foodDrink_btn, manage_staff__btn
	    };

	    for (Button btn : buttons) {
	      if (btn == activeButton) {
	        btn.setStyle(activeStyle);
	      } else {
	        btn.setStyle(defaultStyle);
	      }
	    }
	  }

	@FXML
	private VBox staff_box;

	public void loadAllUsers() {
		ObservableList<User> users = FXCollections.observableArrayList();
		users.addAll(getAllUsers()); // Fetch all users

		staff_box.getChildren().clear();

		for (User user : users) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cinema/views/UserRow.fxml"));
				AnchorPane pane = loader.load();

				UserRowController controller = loader.getController();
				controller.setAdmincontroller(this); // Pass this controller if needed
				controller.setUserData(user);        // Set user data in the row

				staff_box.getChildren().add(pane);
			} catch (IOException e) {
				// You can also show an alert here
				e.printStackTrace();
			}
		}
	}
	public void SelectUserRow(User user) {
		try {

			FXMLLoader popupLoader = new FXMLLoader(getClass().getResource("/com/cinema/views/signup.fxml"));
			Parent popupRoot = popupLoader.load();
			Stage popupStage = new Stage();
			popupStage.setTitle("Setting");
			popupStage.setScene(new Scene(popupRoot));
			popupStage.show();
			signupController controller = popupLoader.getController();
			controller.setSignUpData(user);
			controller.setAdminController(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	Button activeButton = null;

	@FXML
	void switchForm(ActionEvent event) {
		if (event.getSource() == dashboard_btn) {
			showDashboardForm();
		} else if (event.getSource() == addMovies_btn) {
			showAddMoviesForm();
		} else if (event.getSource() == editScreening_btn) {
			showEditScreeningForm();
		} else if (event.getSource() == selling_btn) {
			showSellingForm();
		} else if (event.getSource() == report_btn) {
			showReportForm();  
		}
		else if (event.getSource() == foodDrink_btn) {
			showFoodForm();
		}else if(event.getSource() == manage_staff__btn ) {
			showStaffForm();
		}
	}

	private void showDashboardForm() {
		setFormVisibility(true, false, false, false,false,false,false);
		setButtonStyle(dashboard_btn);

	}

	private void showAddMoviesForm() {
		setFormVisibility(false, true, false, false,false,false,false);
		setButtonStyle(addMovies_btn);
	}

	private void showEditScreeningForm() {
		setFormVisibility(false, false, true, false,false,false,false);
		setButtonStyle(editScreening_btn);
	}

	private void showSellingForm() {
		setFormVisibility(false, false, false, true,false,false,false);
		setButtonStyle(selling_btn);
	}
	private void showReportForm() {
		setFormVisibility(false, false, false, false,true,false,false);
		setButtonStyle(report_btn);
	}
	private void showFoodForm() {
		setFormVisibility(false, false, false, false,false,true,false);
		setButtonStyle(foodDrink_btn);
	}

	private void showStaffForm() {
		setFormVisibility(false, false, false, false,false,false,true);
		setButtonStyle(manage_staff__btn);


	}

	private void setFormVisibility(boolean showDashboard, boolean showAddMovies,
			boolean showEditScreening, boolean showSelling,boolean showHistory ,boolean showFoodForm,boolean staffForm) {
		dashboard_form.setVisible(showDashboard);
		addmovies_form.setVisible(showAddMovies);
		editscreening_form.setVisible(showEditScreening);
		selling_form.setVisible(showSelling);
		report_form.setVisible(showHistory);
		food_form.setVisible(showFoodForm);
		Staff_Form.setVisible(staffForm);

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

	public void initialize(URL location, ResourceBundle resources) {

		//displayUser();
		showAddMoviesList();

		Platform.runLater(() -> {
			showEditScreening();
			ShowMovieReport();
		});
		add_category.setItems(category);

		showFoodList();
		searchfood();

		getShowingMovieGropBYName();
		loadBarChart();
		loadPieChart();
		AddToSaleTable();
		ShowSelectedInfo();
		addmovies_genre.setItems(genere);
		setup24HourTimePicker(addmovies_duration);
		getAllRoomList();
		setupToggleGroup();
		loadMovieSalesData();
		getAllUsers();
		loadAllUsers();
		HoverRainbowBorder.apply(dashboard_btn, () -> activeButton);
		HoverRainbowBorder.apply(addMovies_btn, () -> activeButton);
		HoverRainbowBorder.apply(editScreening_btn, () -> activeButton);
		HoverRainbowBorder.apply(selling_btn, () -> activeButton);
		HoverRainbowBorder.apply(report_btn, () -> activeButton);
		HoverRainbowBorder.apply(foodDrink_btn, () -> activeButton);
		HoverRainbowBorder.apply(manage_staff__btn, () -> activeButton);

	
		addmovies_duration.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
		editscreening_showTime.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
		Show_adding_Form_showtime.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");

		edit_movie_showTime.getEditor().setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white;");
		showFoodList();
		showDashboardForm();






	}

}
