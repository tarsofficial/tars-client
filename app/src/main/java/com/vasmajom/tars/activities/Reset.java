package com.vasmajom.tars.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vasmajom.tars.R;
import com.vasmajom.tars.db.DatabaseHelper;

public class Reset extends AppCompatActivity {

    DatabaseHelper dbHelper;

    Button buttonNo;
    Button buttonYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset);

        dbHelper = new DatabaseHelper(this);

        buttonNo = findViewById(R.id.button_no);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PayeeList.class);
                startActivity(intent);
            }
        });

        buttonYes = findViewById(R.id.button_yes);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.panic();
                Intent intent = new Intent(v.getContext(), PayeeList.class);
                startActivity(intent);
            }
        });
    }

}
