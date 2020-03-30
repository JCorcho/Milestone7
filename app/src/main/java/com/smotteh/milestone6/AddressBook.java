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


public class AddressBook //start of class.
{

    ArrayList<BusinessContact> businessContacts = new ArrayList<>(); //ArrayList to store businessContact objects within the AddressBook.

    ArrayList<PersonContact> personContacts = new ArrayList<>(); //ArrayList to store the personContact objects within the AddressBook.

    ArrayList<Photo> photos = new ArrayList<>();


    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }


    public void findAllPersonExtras() {
        //add relatives
        for (PersonContact p : personContacts) {
            for (Integer id : p.getRelativeIDs()) {
                for (PersonContact _p : personContacts) {
                    if (_p.getNumber() == id) {
                        p.addRelative(_p);
                    }
                }
            }
        }

        //add photos
        for (PersonContact p : personContacts) {
            for (Integer id : p.getPhotoIDs()) {
                for (Photo photo : photos) {
                    if (photo.getPhotoID() == id) {
                        p.addPhoto(photo);
                    }
                }
            }
        }
    }

    //the following method is used to set any given instance of an AddressBook objects businessContact ArrayList to
    //a passed argument of type ArrayList of BusinessContacts.
    public void setBusinessContacts(ArrayList<BusinessContact> businessContacts) {
        this.businessContacts = businessContacts;
    }

    //the following method is used to return the ArrayList of businessContacts of any given instance of an AddressBook object.
    public ArrayList<BusinessContact> getBusinessContacts() {
        return businessContacts;
    }

    //the following method is used to return the ArrayList of personContacts of any given instance of an AddressBook object.
    public ArrayList<PersonContact> getPersonContacts() {
        return personContacts;
    }


    //the following method is used to set any given instance of an AddressBook objects businessContact ArrayList to
    //a passed argument of type ArrayList of BusinessContacts.
    public void setPersonContacts(ArrayList<PersonContact> personContacts) {
        this.personContacts = personContacts;
    }


    //the following method's purpose is to add the passed BusinessContact argument to any given AddressBook object's businessContacts ArrayList.
    public void addBusinessContact(BusinessContact contact) {
        this.businessContacts.add(contact);
    }

    //the following methods purpose is to remove the passed BusinessContact argument from any given AddressBook objects businessContact ArrayList.
    public void removeBusinessContact(BusinessContact contact) {
        this.businessContacts.remove(contact);
    }

    //the following methods purpose is to add the passed PersonContact argument to any given AddressBook objects personContacts list.
    public void addPersonContact(PersonContact contact) {
        this.personContacts.add(contact);
    }

    //the following methods purpose is to remove the passed PersonContact argument from any given AddressBook objects personContacts ArrayList.
    public void removePersonContact(PersonContact contact) {
        this.personContacts.remove(contact);
    }

    //the following methods purpose to display the passed PersonContact argument to the console.
    public void displayPersonContact(PersonContact contact) {
        System.out.println("Name: " + contact.getName());
        System.out.println("Phone Number: " + contact.getPhone());
        System.out.println("Location: " + contact.getLocation().getStreet() + ". " + contact.getLocation().getCity() + ", " + contact.getLocation().getState() + ".");

        if (contact.getHobbies().size() >= 1) {

            System.out.println("Hobbies: ");
            for (String hobby : contact.getHobbies()) {
                System.out.println(hobby + " ");
            }

        }

        if (contact.getRelatives().size() >= 1) {

            System.out.println("Relatives: ");
            for (PersonContact relative : contact.getRelatives()) {
                System.out.println(relative.getName());
            }

        }

        System.out.println("Contact ID: " + contact.getNumber());
    }

    //the following methods purpose is to display the passed BusinessContact argument to the console.
    public void displayBusinessContact(BusinessContact contact) {
        System.out.println("Name: " + contact.getName());
        System.out.println("Phone Number: " + contact.getPhone());
        System.out.println("Location: " + contact.getLocation().getStreet() + ". " + contact.getLocation().getCity() + ", " + contact.getLocation().getState() + ".");
        System.out.println("Opening Time: " + contact.getOpeningTime() + " | Closing Time: " + contact.getClosingTime());
        System.out.println("Contact ID: " + contact.getNumber());
    }

    //the following methods purpose is to sort contacts by a certain parameter.
    //this method is beyond the scope of this milestone and the logic will be added later.
    //I will either be using generics or two methods sortPersonContacts and sortBusinessContacts.
    public void Sort(/*sortbywhat*/) {
        //sorting logic goes here.
    }

    //the following methods purpose is to search the personContacts ArrayList of any given AddressBook object
    //by the PersonContact's ID variable utilizing the passed integer argument.
    public PersonContact searchPersonByID(int contactID) {
        for (PersonContact contact : personContacts) {

            if (contactID == contact.getNumber()) {

                return contact;


            }

        }

        return personContacts.get(contactID);
    }

    //the following methods purpose is to search the businessContacts ArrayList of any given AddressBook object
    //by the BusinessContact's ID variable utilizing the passed integer argument.
    public BusinessContact searchBusinessByID(int contactID) {
        for (BusinessContact contact : businessContacts) {

            if (contactID == contact.getNumber()) {

                return contact;


            }

        }

        return businessContacts.get(contactID);
    }

    //the following methods purpose is to search the personContacts ArrayList of any given AddressBook object
    //by the PersonContact's name variable utilizing the passed String argument.
    public ArrayList<PersonContact> searchPeopleByName(String name) {
        ArrayList<PersonContact> results = new ArrayList<>();
        for (PersonContact contact : personContacts) {
            if (contact.getName().equals(name)) {
                results.add(contact);
            }
        }

        return results;
    }

    //the following methods purpose is to search the businessContacts ArrayList of any given AddressBook object
    //by the BusinessContact's name variable utilizing the passed String argument.
    public ArrayList<BusinessContact> searchBusinessByName(String name) {
        ArrayList<BusinessContact> results = new ArrayList<>();
        for (BusinessContact contact : businessContacts) {
            if (contact.getName() == name) {
                results.add(contact);
            }
        }

        return results;
    }

    //the following methods purpose is to search the personContacts ArrayList of any given AddressBook object
    //by the PersonContact's City variable utilizing the passed String argument.
    public ArrayList<PersonContact> searchPeopleByCity(String city) {
        ArrayList<PersonContact> results = new ArrayList<>();
        for (PersonContact contact : personContacts) {
            if (contact.getLocation().getCity().equals(city)) {
                results.add(contact);
            }
        }

        return results;
    }

    //the following methods purpose is to search the businessContacts ArrayList of any given AddressBook object
    //by the BusinessContact's city variable utilizing the passed String argument.
    public ArrayList<BusinessContact> searchBusinessByCity(String city) {
        ArrayList<BusinessContact> results = new ArrayList<>();
        for (BusinessContact contact : businessContacts) {
            if (contact.getLocation().getCity().equals(city)) {
                results.add(contact);
            }
        }

        return results;
    }

    //the following methods purpose is to search the personContacts ArrayList of any given AddressBook object
    //by the PersonContact's hobby variable utilizing the passed String argument.
    public ArrayList<PersonContact> searchPeopleByHobby(String hobby) {
        ArrayList<PersonContact> results = new ArrayList<>();

        //loops through each PersonContact object in the personContact ArrayList
        for (PersonContact contact : personContacts) {
            //loops through each String "hobby" in the ArrayList of strings called hobbies contained in the PersonContact object.
            for (String _hobby : contact.getHobbies()) {
                if (_hobby == hobby) {
                    results.add(contact);
                }
            }//end child loop.
        }//end parent loop.

        return results;
    }//end searchPeopleByHobby method.


    //custom constructor, all arguments.
    AddressBook(ArrayList<BusinessContact> businessContacts, ArrayList<PersonContact> personContacts) {
        this.businessContacts = businessContacts;
        this.personContacts = personContacts;
    }

    //default constructor, no arguments.
    AddressBook() {

    }

}
