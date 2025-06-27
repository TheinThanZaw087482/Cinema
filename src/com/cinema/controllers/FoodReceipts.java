package com.cinema.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodReceipts {

    @FXML
    private Label food_date_Label;
    @FXML
    private Label food_time_Label;
    @FXML
    private Label total_cost;
    @FXML
    private VBox foodList; // VBox to hold food name labels
    @FXML
    private VBox priceList; // VBox to hold price labels

    // Custom FoodItem class to hold food name, count, and price
    static class FoodItem {
        String name;
        int count;
        int price;

        // Constructor
        public FoodItem(String name, int price) {
            this.name = name;
            this.price = price;
            this.count = 1; // Initially count is 1
        }

        // Method to increment the count of food items
        public void incrementCount() {
            this.count++;
        }

        @Override
        public String toString() {
            return name + " x" + count;  // Display as food name with count
        }
    }

    // Method to set the date and time labels
    public void set_food_date_Label(String date) {
        food_date_Label.setText(date);
    }

    public void set_food_time_Label(String time) {
        food_time_Label.setText(time);
    }

    public void set_foodList(ObservableList<String> foodNames, ObservableList<String> foodPrices) {
        foodList.getChildren().clear(); 
        priceList.getChildren().clear();

        List<FoodItem> foodItems = new ArrayList<>();

        for (int i = 0; i < foodNames.size(); i++) {
            String foodName = foodNames.get(i);

    	    
            int foodPrice = Integer.parseInt(foodPrices.get(i).replace(" MMK", "")); // Remove "MMK" and parse to int

            // Check if the food item already exists in the list
            boolean found = false;
            for (FoodItem item : foodItems) {
                if (item.name.equals(foodName)) {
                    item.incrementCount();  // Increment the count if the food item already exists
                    found = true;
                    break;
                }
            }

            if (!found) {
                foodItems.add(new FoodItem(foodName, foodPrice));
            }
        }

        // Now create labels for each food with the count and price
        for (FoodItem foodItem : foodItems) {
            // Create the label for food and price
            Label foodLabel = new Label(foodItem.toString()); // Display the food with its count

            DecimalFormat formatter = new DecimalFormat("#,###");
    	    String formattedPrice = formatter.format(foodItem.price);
            Label priceLabel = new Label(formattedPrice + " MMK");

            // Add food label and price label to their respective VBox containers
            foodList.getChildren().add(foodLabel);
            priceList.getChildren().add(priceLabel);
        }
    }

    // Method to set the total cost label
    public void set_total_cost_Label(String cost) {
        total_cost.setText(cost+" MMK");
    }
}
