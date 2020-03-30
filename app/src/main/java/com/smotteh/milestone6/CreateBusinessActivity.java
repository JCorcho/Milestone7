package com.smotteh.milestone6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CreateBusinessActivity extends AppCompatActivity {

    private static final String TAG = "CreateBusinessActivity";

    public CreateBusinessActivity() throws IOException {
    }

    int newID = 0;
    FileAccessService fas = new FileAccessService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_business);


        assert fas != null;
        try {
            fas.readAllBusinessContacts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        newID = fas.getBusinessContacts().size();

        Button finishButton = (Button) findViewById(R.id.finish_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_activity);


        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveFieldsToNewContact();
                    goHome();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    public void checkIfInputFormatIsCorrect() {
        //try catch in case user did not input fields correctly.
        //change fields to red to highlight what needs to eb fixed then
        //call the saveFieldsToNewContact() method, this avoids needing
        //try catches for every parameter in the saveFieldsToNewContact() method.
    }

    //** THIS METHOD STILL NEEDS PHOTO IMPLEMENTATION BY ADDING THE PHOTO IDS OF THE PHOTOS THE USER **
    //** WANTED TO ADD **
    public void saveFieldsToNewContact() throws IOException {
        TextInputEditText name = findViewById(R.id.name_input);
        TextInputEditText address = findViewById(R.id.address_input);
        TextInputEditText phone = findViewById(R.id.phone_input);
        TextInputEditText hours = findViewById(R.id.birthday_input);
        TextInputEditText websiteURL = findViewById(R.id.hobbies_input);

        //extract name from TextInputEditText
        String nameStr = name.getText().toString();

        //extract phone number from TextInputEditText
        long phoneNumber = Long.parseLong(phone.getText().toString());

        //extract location from TextInputEditText
        String addressStr = address.getText().toString();
        String[] addressSplit = addressStr.split(",");
        Location location = new Location(addressSplit[0], addressSplit[1], addressSplit[2]);

        //extract Hours from TextInputEditText
        String hoursStr = hours.getText().toString();
        hoursStr = hoursStr.replaceAll("am", ""); //LOGIC FOR AM/PM SAVING NEEDS TO BE ADDED
        hoursStr = hoursStr.replaceAll("pm", ""); //LOGIC FOR AM/PM SAVING NEEDS TO BE ADDED
        hoursStr = hoursStr.replaceAll(" ", "");
        String[] hoursSplit = hoursStr.split(",");
        Log.d(TAG, "saveFieldsToNewContact: " + hoursSplit[0] + hoursSplit[1]);
        int openingTime = Integer.parseInt(hoursSplit[0]);
        int closingTime = Integer.parseInt(hoursSplit[1]);

        //extract websiteURL from TextInputEditText
        String websiteStr = websiteURL.getText().toString();

        BusinessContact tempContact = new BusinessContact(nameStr, newID, phoneNumber, new ArrayList<Photo>(), location, openingTime, closingTime, websiteStr, "abcde@gmail.com");
        fas.addBusinessContact(tempContact); //adds the contact to the list of businessContacts in the FileAccessService object.
        fas.saveAllBusinessContacts(); //saves the new contact to the text file of businessContacts by calling the saveAllBusinessContacts method.
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
