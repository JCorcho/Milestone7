package com.smotteh.milestone6;/*
 * The purpose of this class is to model a Location for any given
 * BaseContact object or its subclasses. It will be used to hold location data
 * such as street, city and state. Each location object will have an ID.
 *
 * @Version 2/20/2020
 * @Author Jacob Corcho
 */


public class Location {

    //START GLOBAL CLASS VARIABLES
    private int locationID;
    private String street;
    private String city;
    private String state;
    //END GLOBAL CLASS VARIABLES

    //the following method returns the locationID from the Location object the method is called.
    public int getLocationID() {
        return locationID;
    }

    //the following method sets the locationID of the Location objected the method is called from.
    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    //the following method returns the street String from the location object the method is called from.
    public String getStreet() {
        return street;
    }

    //the following method sets the street variable of the Location object the method is called on.
    public void setStreet(String street) {
        this.street = street;
    }

    //the following method returns the city variable from the Location object the method is called from.
    public String getCity() {
        return city;
    }

    //the following method sets the city variable of the Location object the method is called from.
    public void setCity(String city) {
        this.city = city;
    }

    //the following method returns the state variable from the Location object the method is called from.
    public String getState() {
        return state;
    }

    //the following method sets the state variable of which the method is called on.
    public void setState(String state) {
        this.state = state;
    }

    //START CLASS CONSTRUCTORS

    //default constructor, no arguments.
    Location() {

    }

    //custom constructor, all arguments.
    Location(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    //END CLASS CONSTRUCTORS
}
