package com.vasmajom.tars.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vasmajom.tars.R;
import com.vasmajom.tars.adapters.PayeeAdapter;
import com.vasmajom.tars.db.DatabaseHelper;

public class PayeeList extends AppCompatActivity {

    DatabaseHelper dbHelper;

    RecyclerView payeeList;
    PayeeAdapter payeeAdapter;
    RecyclerView.LayoutManager layoutManager;

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.tars_layout);

        dbHelper = new DatabaseHelper(this);

        payeeList = findViewById(R.id.list_view);
        payeeList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        payeeList.setLayoutManager(layoutManager);
        payeeAdapter = new PayeeAdapter(this, dbHelper.getPayees());
        payeeList.setAdapter(payeeAdapter);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addPayee();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tars, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contact_menu) {
            Intent intent = new Intent(this, Contact.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.reset) {
            Intent intent = new Intent(this, Reset.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addPayee() {
        Intent intent = new Intent(this, AddPayee.class);
        startActivity(intent);
    }
}
