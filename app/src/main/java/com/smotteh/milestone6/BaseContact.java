package com.smotteh.milestone6;/*
 * The purpose of this class is to represent as a model for AddressBook objects which will be used
 * within the full scope of this project. This class contains two lists, one for businessContacts and one for personContatcts.
 *
 * Within the final version of this class you will also find the logic needed to interact with any given instance of an
 * AddressBook object, such as standard getters and setters, list sorting and more.
 *
 * @Version 2/20/2020
 * @Author Jacob Corcho
 */

import java.util.ArrayList;

public class BaseContact { //start of BaseContact class.

    //START OF GLOBAL CLASS VARIABLES

    private String name; //contact name.
    private int number; //contact ID number.
    private long phone; //contact phone number.
    private String email; //email address.
    private ArrayList<Photo> photos; //ArrayList of Photo objects to hold the contacts photos.
    private Location location; //Location object to hold the contacts location information.

    //enum to distinguish rather the contact is of type PersonContact or BusinessContact.
    enum Type {
        BUSINESS, PERSON
    }

    //END OF GLOBAL CLASS VARIABLES.

    //START OF CLASS METHODS.

    //private object instance of the above enum.
    private Type type;

    //returns the type variable of any given BaseContact or its subclasses.
    public Type getType() {
        return type;
    }

    //sets the type variable of any given BaseContact or its subclasses.
    public void setType(Type type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //returns the name of any given BaseContact or its subclasses.
    public String getName() {
        return name;
    }

    //sets the name of any given BaseContact or its subclasses.
    public void setName(String name) {
        this.name = name;
    }

    //returns the ID of any given BaseContact or its subclasses.
    public int getNumber() {
        return number;
    }

    //sets the ID of any given BaseContact or its subclasses.
    public void setNumber(int number) {
        this.number = number;
    }

    //returns the phone number of any given BaseContact or its subclasses.
    public long getPhone() {
        return phone;
    }

    //sets the phone number of any given BaseContact or its subclasses.
    public void setPhone(long phone) {
        this.phone = phone;
    }

    //returns the ArrayList of Photo objects of any given BaseContact or its subclasses.
    public ArrayList getPhotos() {
        return photos;
    }

    //sets the ArrayList of Photo objects of any given BaseContact or its subclasses.
    public void setPhotos(ArrayList photos) {
        this.photos = photos;
    }

    //adds a Photo object to the ArrayList of Photo objects 'photos' to any given BaseContact or its subclasses.
    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }

    //removes a Photo object from the ArrayList of Photo objects 'photos' to any given BaseContact or its subclasses.
    public void removePhoto(Photo photo) {
        this.photos.remove(photo);
    }

    //returns the Location of any given BaseContact or its subclasses.
    public Location getLocation() {
        return location;
    }

    //sets the Location of any given BaseContact or its subclasses.
    public void setLocation(Location location) {
        this.location = location;
    }

    //END OF CLASS METHODS.

    //START OF CLASS CONSTRUCTORS.

    //custom constructor, all arguments.
    BaseContact(String name, int number, long phone, ArrayList<Photo> photos, Location location, Type type, String email) {
        this.name = name;
        this.number = number;
        this.phone = phone;
        this.photos = photos;
        this.location = location;
        this.type = type;
        this.email = email;
    }

    //default constructor, no arguments.
    BaseContact() {
    }

    //END OF CLASS CONSTRUCTORS.

} //end of BaseContact class.
