package com.smotteh.milestone6;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Locale;

public class ViewPersonActivity extends AppCompatActivity {
    private static final String TAG = ViewPersonActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_person);


        //FIND ALL BUTTONS
        Button deleteButton = findViewById(R.id.delete_button);
        Button cancelButton = findViewById(R.id.cancel_activity);
        Button editButton = findViewById(R.id.edit_button);
        ImageButton callButton = findViewById(R.id.call_button);
        ImageButton textButton = findViewById(R.id.text_button);
        ImageButton emailButton = findViewById(R.id.email_button);
        ImageButton mapButton = findViewById(R.id.map_button);

        //CANCEL ACTIVITY
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });


        //FIND ALL TEXTVIEWS
        TextView nameView = findViewById(R.id.name_textView);
        TextView phoneView = findViewById(R.id.phone_textView);
        TextView addressView = findViewById(R.id.address_textView);
        TextView birthdayView = findViewById(R.id.birthday_textView);
        TextView hobbiesView = findViewById(R.id.hobbies_textView);
        TextView descriptionView = findViewById(R.id.description_textView);


        Bundle b = getIntent().getExtras();
        int id = -1;
        if (b != null) {
            id = b.getInt("key");
        }

        try {
            FileAccessService fas = new FileAccessService();
            fas.readAllPersonContacts();
            AddressBook addressBook = new AddressBook();
            addressBook.setPersonContacts(fas.getPersonContacts());

            final PersonContact tempContact = addressBook.searchPersonByID(id);

            nameView.setText(tempContact.getName());
            phoneView.setText(String.format(Locale.getDefault(), "%d", tempContact.getPhone()));
            addressView.setText(String.format("%s. %s, %s.", tempContact.getLocation().getStreet(), tempContact.getLocation().getCity(), tempContact.getLocation().getState()));

            String addressStr = "Birthday: " + tempContact.getBirthdate().toString();
            birthdayView.setText(addressStr);
            String hobbiesStr = "Likes: ";
            for (String h : tempContact.getHobbies()) {
                hobbiesStr += h + " ";
            }
            hobbiesView.setText(hobbiesStr);
            descriptionView.setText(tempContact.getDescription());

            //EDIT CONTACT BUTTON
            final int finalId1 = id;
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openEditActivity(finalId1);
                }
            });

            //DELETE CONTACT
            final int finalId = id;
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        deleteContact(finalId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            //CALL CONTACT
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call(v, tempContact.getPhone() + "");
                }
            });

            //TEXT CONTACT
            textButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message(tempContact.getPhone() + "");
                }
            });


            //EMAIL CONTACT
            emailButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Email(tempContact.getEmail());
                }
            });

            //SHOW CONTACT ON MAP
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMap(tempContact.getLocation());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void selectImage(View v) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Image"), 101);
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openEditActivity(int id) {
        Intent intent = new Intent(this, EditPersonActivity.class);
        startActivity(intent);
        Bundle b = new Bundle();
        b.putInt("key", id);
        intent.putExtras(b);
        this.startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void deleteContact(int id) throws IOException {
        FileAccessService fas = new FileAccessService();
        fas.readAllPersonContacts();
        for (PersonContact p : fas.getPersonContacts()) {
            if (p.getNumber() == id) {
                fas.getPersonContacts().remove(p);
                fas.saveAllPersonContacts();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    // The following method is called when the user clicks the callButton.
    public void Call(View v, String number) {
        // show() method display the toast with message
        // "clicked"
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG)
                .show();

        // Use format with "tel:" and phoneNumber created is
        // stored in u.
        Uri u = Uri.parse("tel:" + number);

        // Create the intent and set the data for the
        // intent as the phone number.
        Intent i = new Intent(Intent.ACTION_DIAL, u);

        try {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            startActivity(i);
        } catch (SecurityException s) {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, "Could not open dialer!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    //the following method is called when the user clicks the message button
    public void Message(String number) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("sms:" + number));
        startActivity(smsIntent);
    }

    public void Email(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", email, null));
        String[] addresses = {email};
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses); // String[] addresses
        startActivity(Intent.createChooser(emailIntent, "Send Email..."));
    }

    public void openMap(Location location) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://maps.google.co.in/maps?q="
                + location.getCity()
                + ", " + location.getState()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
