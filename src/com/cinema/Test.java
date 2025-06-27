package com.cinema;

import java.io.File;

public class Test {

   

    public static void main(String[] args) {
    	File file = new File("images/movieImage/ARealPain.jpg");
    	System.out.println(file.exists()); // Should print true

    }
}