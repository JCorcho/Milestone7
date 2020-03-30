package com.smotteh.milestone6;/*
 * the purpose of this class is to serve as a model for a contact
 * used for a person, because of this it is labelled PersonContact.
 * This class is a subclass of the superclass BaseContact and has extra
 * properties that would only pertain to a person and not another contact type
 * such as a business.
 *
 * @Version 2/20/2020
 * @Author Jacob Corcho
 */


import java.time.LocalDate;
import java.util.ArrayList;

public class PersonContact extends BaseContact { //start of PersonContact class.

    //START OF GLOBAL CLASS VARIABLES.

    private LocalDate birthdate; //holds the birthdate utilizing the LocalDate class.
    private String description; //holds the description of the person.

    // * NOTE * alternatively we could have a pre-set list of hobbies using an ArrayList of strings or an enum.
    private ArrayList<String> hobbies = new ArrayList<>(); //holds the ArrayList of hobbies in type String.
    private ArrayList<PersonContact> relatives = new ArrayList<>(); //holds the ArrayList of PersonContact objects, these objects are the list of any given PersonContact's relatives.
    private ArrayList<Integer> relativeIDs = new ArrayList<>();
    private ArrayList<Integer> photoIDs = new ArrayList<>();

    public ArrayList<Integer> getPhotoIDs() {
        return photoIDs;
    }

    public void setPhotoIDs(ArrayList<Integer> photoIDs) {
        this.photoIDs = photoIDs;
    }


    public ArrayList<Integer> getRelativeIDs() {
        return relativeIDs;
    }


    public void setRelativeIDs(ArrayList<Integer> relativeIDs) {
        this.relativeIDs = relativeIDs;
    }

    //END OF GLOBAL CLASS VARIABLES.


    //START OF CLASS METHODS.

    //the following method returns an ArrayList of relatives.
    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    //the following method sets the hobbies ArrayList to the passed argument.
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    //the following method adds a hobby to the hobbies ArrayList.
    public void addHobby(String hobby) {
        this.hobbies.add(hobby);
    }

    //the following method removes a hobby from the hobbies ArrayList based on the passed String argument.
    public void removeHobby(String hobby) {
        for (String _hobby : hobbies) {
            if (_hobby.equalsIgnoreCase(hobby)) {
                hobbies.remove(_hobby);
            }
        }
    }

    //the following method returns the birthdate variable which is of type LocalDate.
    public LocalDate getBirthdate() {
        return birthdate;
    }

    //the following method sets the birthdate variable to the passed LocalDate argument.
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    //the following method sets the description variable.
    public String getDescription() {
        return description;
    }

    //the following method sets the description variable to the passed String argument.
    public void setDescription(String description) {
        this.description = description;
    }

    //the following method returns the relatives ArrayList.
    public ArrayList<PersonContact> getRelatives() {
        return relatives;
    }

    //the following method adds a PersonContact to the relatives ArrayList.
    public void addRelative(PersonContact relative) {
        this.relatives.add(relative);
    }

    //the following method removes a PersonContact from the relatives ArrayList.
    public void removeRelative(PersonContact relative) {
        this.relatives.remove(relative);
    }

    //the following method sets the relatives ArrayList to the passed argument of an ArrayList of PersonContact objects.
    public void setRelatives(ArrayList<PersonContact> relatives) {
        this.relatives = relatives;
    }

    //the following method clears the relatives ArrayList.
    public void clearRelatives() {
        this.relatives.clear();
    }

    //END OF CLASS METHODS.

    //START OF CLASS CONSTRUCTORS


    //default constructor, no arguments.
    PersonContact() {

    }

    //custom constructor, all arguments.
    PersonContact(String name, int number, long phone, ArrayList<Photo> photos, Location location, LocalDate birthdate, String description, ArrayList<PersonContact> relatives, ArrayList<String> hobbies, String email) {
        super(name, number, phone, photos, location, Type.PERSON, email);


        this.birthdate = birthdate;
        this.description = description;
        this.relatives = relatives;
        this.hobbies = hobbies;

    }


    //END OF CLASS CONSTRUCTORS
} //end of PersonContact class.
