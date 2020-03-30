package com.smotteh.milestone6;/*
 * The purpose of this class is to model the information needed for a Photo.
 * we wont actually store image data within the Photo class, rather a location to
 * a file which contains the data of a photo. This model will include a photoID, filePath,
 * photoDate and description. This class will also contain two constructors as well as
 * getters and setters.
 *
 * @Version 2/20/2020
 * @Author Jacob Corcho
 */


import java.time.LocalDate;

public class Photo { //start of Photo class.

    //START OF GLOBAL CLASS VARIABLES.

    private int photoID; //stores the ID of a given Photo object.
    private String filePath; //stores the file path of a given Photo object.
    private LocalDate photoDate; //stores the date that the photo of a given Photo object was taken or saves, this utilizes the LocalDate class.
    private String description; //stores the description of a given Photo object.

    //END OF GLOBAL CLASS VARIABLES.

    //START CLASS METHODS.

    //the following method returns the photoID of the Photo object it was called from.
    public int getPhotoID() {
        return photoID;
    }

    //the following method sets the PhotoID of the Photo object the method is called from.
    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    //the following method returns the filePath of Photo object the method is called from.
    public String getFilePath() {
        return filePath;
    }

    //the following method sets the filePath of the Photo object the method is called from.
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //the following method returns the photoDate of the Photo object the method is called from.
    public LocalDate getPhotoDate() {
        return photoDate;
    }

    //the following method sets the photoDate Photo object the method is called from.
    public void setPhotoDate(LocalDate photoDate) {
        this.photoDate = photoDate;
    }

    //the following method returns the description of Photo object the method is called from.
    public String getDescription() {
        return description;
    }

    //the following method sets the description of the Photo object the method is called from.
    public void setDescription(String description) {
        this.description = description;
    }

    //END CLASS METHODS.

    //START CLASS CONSTRUCTORS.

    //default constructor, no arguments.
    Photo() {

    }

    //custom constructor, all arguments.
    Photo(int photoID, String filePath, LocalDate photoDate, String description) {
        this.photoID = photoID;
        this.filePath = filePath;
        this.photoDate = photoDate;
        this.description = description;
    }

    //END CLASS CONSTRUCTORS.
}
