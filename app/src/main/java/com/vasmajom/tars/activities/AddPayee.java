package com.vasmajom.tars.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vasmajom.tars.R;
import com.vasmajom.tars.db.DatabaseHelper;

import static com.vasmajom.tars.util.Toaster.throwToast;

public class AddPayee extends AppCompatActivity {

    DatabaseHelper db;

    EditText name;
    EditText currency;
    EditText address;
    EditText confirmAddress;
    Button addButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_payee);

        db = new DatabaseHelper(this);

        name = findViewById(R.id.payee_edit_name);
        currency = findViewById(R.id.payee_edit_currency);
        address = findViewById(R.id.payee_edit_address);
        confirmAddress = findViewById(R.id.payee_confirm_address);
        addButton = findViewById(R.id.add_payee);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addPayee();
                    }
                }
        );
    }

    public void addPayee() {
        if (address.getText().toString().equals(confirmAddress.getText().toString())) {
            if (!db.savePayee(name.getText().toString(), currency.getText().toString(), address.getText().toString())) {
                throwToast(this, getString(R.string.db_save_error));
            } else {
                throwToast(this, getString(R.string.db_success));
                Intent intent = new Intent(this, PayeeList.class);
                startActivity(intent);
            }
        } else {
            throwToast(this, getString(R.string.db_address_error));
        }
    }

}
