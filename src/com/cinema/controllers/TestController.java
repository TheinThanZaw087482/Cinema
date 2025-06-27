package com.cinema.controllers;

import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;

public class TestController {
    @FXML private ImageView myimage;
    @FXML private JFXTimePicker timepicker;

    @FXML
    public void initialize() {
        // Load image
        try {
            Image image = new Image(getClass().getResourceAsStream("/com/cinema/images/icon/booked.png"));
            myimage.setImage(image);
        } catch (Exception e) {
            System.err.println("ERROR: Image failed to load!");
            e.printStackTrace();
        }

        // Force JFXTimePicker to use 24-hour format
        setup24HourTimePicker(timepicker);

        // Get selected time (ensure time is in 24-hour format)
        Platform.runLater(() -> {
            System.out.println(getSelectedTimeWithoutAMPM());
        });
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
                timepicker.getEditor().setPromptText("HH:mm");
            }
        });

        // **Print selected time when user selects a new value**
        timepicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected time: " + newValue.format(formatter));
            }
        });
		
    }


    public String getSelectedTimeWithoutAMPM() {
        LocalTime selectedTime = timepicker.getValue();
        if (selectedTime != null) {
            return selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return ""; // Return empty string if no time is selected
    }
}
