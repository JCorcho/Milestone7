package com.smotteh.milestone6;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreatePersonActivity extends AppCompatActivity {

    private static final String TAG = CreatePersonActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_person);

        Button cancelButton = findViewById(R.id.cancel_activity);
        Button finishButton = findViewById(R.id.finish_button);

        Log.d(TAG, "onCreate: sssss");

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loadFieldsIntoContact();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadFieldsIntoContact() throws IOException {
        TextInputEditText name = findViewById(R.id.name_input);
        TextInputEditText address = findViewById(R.id.address_input);
        TextInputEditText phone = findViewById(R.id.phone_input);
        TextInputEditText birthday = findViewById(R.id.birthday_input);
        TextInputEditText hobbies = findViewById(R.id.hobbies_input);
        TextInputEditText description = findViewById(R.id.description_input);
        TextInputEditText email = findViewById(R.id.email_input);

        //SET NAME FROM INPUT FIELD
        String nameStr = name.getText().toString();

        //SET LOCATION FROM INPUT FIELD
        String addressStr = address.getText().toString().replaceAll(" ", "");
        String[] addressSplit = addressStr.split(",");
        Location location = new Location(addressSplit[0], addressSplit[1], addressSplit[2]);

        Log.d(TAG, "address: " + addressSplit[0] + addressSplit[1] + addressSplit[2]);

        //SET PHONE NUMBER FROM INPUT FIELD
        long phoneNumber = Long.parseLong(phone.getText().toString());
        Log.d(TAG, "phone:" + phoneNumber);

        //SET BIRTHDAY FROM INPUT FIELD
        LocalDate birthdate = LocalDate.parse(birthday.getText().toString());
        Log.d(TAG, "birthday: " + birthdate.toString());

        //SET HOBBIES FROM INPUT FIELD
        String hobbiesStr = hobbies.getText().toString();
        hobbiesStr = hobbiesStr.replaceAll(" ", "");
        String[] hobbiesList = hobbiesStr.split(",");
        ArrayList<String> _hobbies = new ArrayList<>();
        for (String h : hobbiesList) {
            _hobbies.add(h);
            Log.d(TAG, "added hobby: " + h);
        }

        //SET DESCRIPTION FROM INPUT FIELD
        String descStr = description.getText().toString();
        Log.d(TAG, "Description: " + descStr);
        FileAccessService fas = new FileAccessService();
        fas.readAllPersonContacts();
        Log.d(TAG, "SIZE OF PERSONS " + fas.getPersonContacts().size());

        //SET EMAIL FROM INPUT FIELD
        String emailStr = email.getText().toString();
        emailStr = emailStr.replaceAll(" ", "");


        PersonContact tempContact = new PersonContact(nameStr,
                fas.getPersonContacts().size() + 1,
                phoneNumber,
                new ArrayList<Photo>(),
                location,
                birthdate,
                descStr,
                new ArrayList<PersonContact>(),
                _hobbies,
                emailStr);

        Log.d(TAG, "loadFieldsIntoContact: EMAIL SAVED AS " + tempContact.getEmail());


        fas.readAllPersonContacts();
        fas.addPersonContact(tempContact);
        Log.d(TAG, "succesfully added " + tempContact.getName() + " to FAS!");
        fas.saveAllPersonContacts();
        Log.d(TAG, "succesfully saved persons");
        Log.d(TAG, tempContact.getName() + tempContact.getLocation().getCity() + tempContact.getPhone() + tempContact.getBirthdate().toString());
        goHome();
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
