package com.smotteh.milestone6;/*
 * The purpose of this class is to save contacts and read contacts to and from a text file.
 *
 * @Version 2/28/2020
 * @Author Jacob Corcho
 */

import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileAccessService implements DataAccessService {
    private static final String TAG = FileAccessService.class.getSimpleName();
    //START OF GLOBAL CLASS VARIABLES

    //ArrayList of BusinessContact objects to temporarily hold them when they are retrieved from the text file.
    private ArrayList<BusinessContact> businessContacts = new ArrayList<>();

    //ArrayList of PersonContact objects to temporarily hold them when they are retrieved from the text file.
    private ArrayList<PersonContact> personContacts = new ArrayList<>();
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/saved";
    private File personsFile = new File(path + "/Persons.txt");
    private File businessFile = new File(path + "/Businesses.txt");


    //END OF GLOBAL CLASS VARIABLES.

    //returns the businessContacts ArrayList of any given FileAccessService object.
    public ArrayList<BusinessContact> getBusinessContacts() {
        return businessContacts;
    }

    public void addBusinessContact(BusinessContact businessContact) {
        businessContacts.add(businessContact);
    }

    public void addPersonContact(PersonContact personContact) {
        personContacts.add(personContact);
    }

    //returns the personContacts ArrayList of any given FileAccessService object.
    public ArrayList<PersonContact> getPersonContacts() {
        return personContacts;
    }

    public void clearPersonContacts() {
        personContacts.clear();
    }

    @Override
    public void readAllBusinessContacts() throws FileNotFoundException {
        //make new Scanner object.
        Scanner bScanner = new Scanner(businessFile);
        ArrayList<BusinessContact> businesses = new ArrayList<>();
        while (bScanner.hasNextLine()) {
            int lineCheck = 0;
            int id = 0;
            String name = "";
            long phone = 0;
            ArrayList<Integer> photoIDs = new ArrayList();
            Location location = new Location();
            int openingTime = 0;
            int closingTime = 0;
            String websiteURL = "";

            String curLine = bScanner.nextLine();

            String[] parameters = curLine.split("=");

            for (String p : parameters) {
                p = p.replaceAll(" ", "");
            }

            try {
                //SET ID FROM FILE
                id = Integer.parseInt(parameters[0]);

                //SET NAME FROM FILE
                name = parameters[1].replaceAll("-", " ");

                //SET PHONE FROM FILE
                phone = Long.parseLong(parameters[2]);

                //SET OPENING TIME FROM FILE
                openingTime = Integer.parseInt(parameters[3]);

                //SET CLOSING TIME FROM FILE
                closingTime = Integer.parseInt(parameters[4]);

                //SET LOCATION FROM FILE
                String[] locationInfo = parameters[5].split(",");
                location = new Location(locationInfo[0], locationInfo[1], locationInfo[2]); //reads index 0,1 and 2 (STREET, CITY, STATE) in the file.

                websiteURL = parameters[6];

                //SET PHOTOS FROM FILE
                String[] _photoIDs = parameters[7].split(",");

                for (String _id : _photoIDs) {
                    photoIDs.add(Integer.parseInt(_id));
                }

                //add new business object to businesses list.
                BusinessContact tempBusiness = new BusinessContact(name, id, phone, new ArrayList<Photo>(), location, openingTime, closingTime, websiteURL, "abcde@gmail.com");
                tempBusiness.setPhotoIDs(photoIDs);
                businesses.add(tempBusiness);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void saveAllBusinessContacts() throws IOException {
        FileWriter fileWriter = new FileWriter(businessFile, false);
        for (BusinessContact b : businessContacts) {

            int id = b.getNumber();
            String name = b.getName();
            long phone = b.getPhone();
            ArrayList<Integer> photoIDs = b.getPhotoIDs();
            Location location = b.getLocation();
            int openingTime = b.getOpeningTime();
            int closingTime = b.getClosingTime();
            String websiteURL = b.getWebsiteURL();

            name = name.replaceAll(" ", "-");
            String _photos = "";


            for (int i = 0; i < photoIDs.size(); i++) {
                if ((photoIDs.size() - i) <= 1) {
                    _photos += photoIDs.get(i);
                } else {
                    _photos += photoIDs.get(i) + ",";
                }
            }

            String str = id + "=" + name + "=" + phone + "=" + openingTime + "=" + closingTime + "=" + location.getStreet() + "," + location.getCity() + "," + location.getState() + "=" + websiteURL + "=" + _photos + "\n";
            fileWriter.write(str);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void readAllPersonContacts() throws FileNotFoundException {
        clearPersonContacts();
        //make new Scanner object.
        Scanner pScanner = new Scanner(personsFile);
        Scanner dScanner = new Scanner(personsFile);
        Log.d(TAG, "READING ALL PERSON CONTACTS");

        while (dScanner.hasNextLine()) {
            Log.d(TAG, dScanner.nextLine());
        }

        ArrayList<PersonContact> persons = new ArrayList<>();
        int lineIndex = 1;
        while (pScanner.hasNextLine()) {
            int lineCheck = 0;
            //int id = 0;
            String name = "";
            long phone = 0;
            LocalDate birthday;
            String description = "";
            ArrayList<String> hobbies = new ArrayList<>();
            ArrayList<Integer> relativeIDs = new ArrayList<>();
            ArrayList<Integer> photoIDs = new ArrayList<>();
            Location location = new Location();
            String email = "";

            String curLine = pScanner.nextLine();

            String[] parameters = curLine.split("=");

            for (String p : parameters) {
                p = p.replaceAll(" ", "");
            }
            try {
                //SET ID FROM FILE
                // id = Integer.parseInt(parameters[0]);

                //SET NAME FROM FILE
                name = parameters[0].replaceAll("-", " ");

                //SET PHONE FROM FILE
                phone = Long.parseLong(parameters[1]);

                //SET BIRTHDAY FROM FILE
                birthday = LocalDate.parse(parameters[2]);

                //SET DESCRIPTION FROM FILE
                parameters[3] = parameters[3].replaceAll("-", " ");
                description = parameters[3];

                //SET HOBBIES FROM FILE
                hobbies = new ArrayList<String>(Arrays.asList(parameters[4]));

                //SET RELATIVES FROM FILE
                String[] _relativeIDs = parameters[5].split(",");

                for (String _id : _relativeIDs) {
                    relativeIDs.add(Integer.parseInt(_id));
                }

                //SET PHOTOS FROM FILE
                String[] _photoIDs = parameters[6].split(",");

                for (String _id : _photoIDs) {
                    photoIDs.add(Integer.parseInt(_id));
                }

                //SET LOCATION FROM FILE
                String[] locationInfo = parameters[7].split(",");
                location = new Location(locationInfo[0], locationInfo[1], locationInfo[2]); //reads index 0,1 and 2 (STREET, CITY, STATE) in the file.

                //SET EMAIL FROM FILE
                email = parameters[8];

                //add new person object to persons list
                PersonContact tempPerson = new PersonContact(name, lineIndex, phone, new ArrayList<Photo>(), location, birthday, description, new ArrayList<PersonContact>(), hobbies, email);
                tempPerson.setRelativeIDs(relativeIDs);
                tempPerson.setPhotoIDs(photoIDs);
                persons.add(tempPerson);
                lineIndex++;
                System.out.println("Added " + tempPerson.getName());
            } catch (Exception E) {
                System.out.println("File could not be read at line " + lineCheck);
            }

        }

        personContacts.addAll(persons);
        Log.d(TAG, "SIZE : " + personContacts.size());
    }

    //read person contacts from a file using the format defined in planning.


    //the following method is beyond the scope of this Milestone.
    @Override
    public void saveAllPersonContacts() throws IOException {
        FileWriter fileWriter = new FileWriter(personsFile, false);
        for (PersonContact p : personContacts) {

            Log.d(TAG, "NOW SAVING " + p.getName() + p.getNumber());

            // int id = p.getNumber();
            String name = p.getName();
            long phone = p.getPhone();
            LocalDate birthday = p.getBirthdate();
            String description = p.getDescription();
            ArrayList<String> hobbies = p.getHobbies();
            ArrayList<Integer> relativeIDs = p.getRelativeIDs();
            ArrayList<Integer> photoIDs = p.getPhotoIDs();
            Location location = p.getLocation();
            String email = p.getEmail();

            name = name.replaceAll(" ", "-");
            description = description.replaceAll(" ", "-");
            String _hobbies = "";
            String _photos = "";
            String _relatives = "";

            for (int i = 0; i < hobbies.size(); i++) {
                if ((hobbies.size() - i) <= 1) {
                    _hobbies += hobbies.get(i);
                } else {
                    _hobbies += hobbies.get(i) + ",";
                }
            }

            for (int i = 0; i < photoIDs.size(); i++) {
                if ((photoIDs.size() - i) <= 1) {
                    _photos += photoIDs.get(i);
                } else {
                    _photos += photoIDs.get(i) + ",";
                }
            }

            for (int i = 0; i < relativeIDs.size(); i++) {
                if ((relativeIDs.size() - i) <= 1) {
                    _relatives += relativeIDs.get(i);
                } else {
                    _relatives += relativeIDs.get(i) + ",";
                }
            }

            if (_relatives.equalsIgnoreCase(""))
                _relatives = "1";
            if (_photos.equalsIgnoreCase(""))
                _photos = "1";

            String str = /*id + "=" + */ name + "=" + phone + "=" + birthday.toString() + "=" + description + "=" + _hobbies + "=" + _relatives + "=" + _photos + "=" + location.getStreet() + "," + location.getCity() + "," + location.getState() + "=" + email + "\n";
            fileWriter.write(str);
        }
        fileWriter.close();

    }

    //START OF CONSTRUCTORS.

    //default constructor, no arguments.
    FileAccessService() throws IOException {

    }

    //custom constructor, all arguments.
    FileAccessService(String textFileLocation, ArrayList<BusinessContact> businessContacts, ArrayList<PersonContact> personContacts) throws IOException {
        this.businessContacts = businessContacts;
        this.personContacts = personContacts;
    }

    //END OF CONSTRUCTORS.
}
