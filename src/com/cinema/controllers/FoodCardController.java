package com.cinema.controllers;
import java.io.File;

import com.cinema.models.FoodData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FoodCardController {

    @FXML
    private ImageView food_image_card;

    @FXML
    private Label fname_card;

    @FXML 
    private Label foodprice_card;
    private FoodController fc; // Add this as a field

    FoodData fooddata;

   public void setfoodController(FoodController controller) {
       this.fc = controller;
   }
    
   public void setfooddata(FoodData fooddata) {
	   try {
	        File file = new File("resources/com/cinema/images/foodImage/", fooddata.getFimage());
	        if (file.exists()) {
	            String uri = file.toURI().toString();
	            System.out.println("Loading image from: " + uri);
	            food_image_card.setImage(new Image(uri, 244, 144, false, true));
	            
	        } else {
	            System.err.println("File not found: " + file.getAbsolutePath());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

       fname_card.setText(fooddata.getFname());
       foodprice_card.setText(fooddata.getFprice()+"");
       
       food_image_card.setOnMouseClicked(event -> {
           if (fooddata != null && fc != null) {
               fc.choicedFood(fooddata);
               System.out.println(fooddata);
           }
       });
   }
   
}
