package com.example.demo;

public class Library {
    // Attributes of the library
    private String name;

    // Constructor to initialize the library name
    public Library(String name) {
        this.name = name;
    }

    // Getter method for the library name
    public String getName() {
        return name;
    }

    // Setter method to set the library name
    public void setName(String name) {
        this.name = name;
    }

    // Method to display library information
    public void displayInfo() {
        System.out.println("Library Name: " + name);
    }

    // Main method to test the Library class
   
}
