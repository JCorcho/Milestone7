package com.smotteh.milestone6;/*
 * the purpose of this class is to serve as a model for a contact
 * used for a business, because of this it is labelled BusinessContact.
 * This class is a subclass of the superclass BaseContact and has extra
 * properties that would only pertain to a business and not another contact type
 * such as a person.
 *
 *
 * @Version 2/20/2020
 * @Author Jacob Corcho
 */


import java.util.ArrayList;

public class BusinessContact extends BaseContact {

    //START OF GLOBAL CLASS VARIABLES.

    private int openingTime; //holds the openingTime of the BusinessContact.
    private int closingTime; //holds the closingTime of the BusinessContact.
    private String websiteURL; //holds the websiteURl of the BusinessContact.
    private ArrayList<Integer> photoIDs;


    //END OF GLOBAL CLASS VARIABLES.


    //START OF CLASS METHODS.

    public ArrayList<Integer> getPhotoIDs() {
        return photoIDs;
    }

    public void setPhotoIDs(ArrayList<Integer> photoIDs) {
        this.photoIDs = photoIDs;
    }

    //the following method returns the openingTime of the BusinessContact.
    public int getOpeningTime() {
        return openingTime;
    }

    //the following method sets the openingTime of the BusinessContact.
    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    //the following method returns the closing time of the BusinessContact.
    public int getClosingTime() {
        return closingTime;
    }

    //the following method sets the closingTime of the BusinessContact.
    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

    //the following method returns the websiteURL of the BusinessContact.
    public String getWebsiteURL() {
        return websiteURL;
    }

    //the following method sets the websiteURL of the BusinessContact.
    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }


    //END OF CLASS METHODS.

    //START OF CLASS CONSTRUCTORS.

    //default constructor, no arguments.
    BusinessContact() {

    }

    //custom constructor, all arguments.
    BusinessContact(String name, int number, long phone, ArrayList<Photo> photos, Location location, int openingTime, int closingTime, String websiteURL, String email) {
        super(name, number, phone, photos, location, Type.BUSINESS, email);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.websiteURL = websiteURL;
    }


    //END OF CLASS CONSTRUCTORS.


}
