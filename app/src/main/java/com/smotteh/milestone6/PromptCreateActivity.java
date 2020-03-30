package com.smotteh.milestone6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PromptCreateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_prompt);

        Button businessButton = (Button) findViewById(R.id.cancel_activity);
        Button personButton = (Button) findViewById(R.id.person_button);
        Button cancelButton = (Button) findViewById(R.id.cancel_activity);

        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBusinessCreate();
            }
        });

        personButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonCreate();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    public void openPersonCreate() {
        Intent intent = new Intent(this, CreatePersonActivity.class);
        startActivity(intent);
    }

    public void openBusinessCreate() {
        Intent intent = new Intent(this, CreateBusinessActivity.class);
        startActivity(intent);
    }

    public void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
