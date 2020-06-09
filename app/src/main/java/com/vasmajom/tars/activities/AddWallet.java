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

public class AddWallet extends AppCompatActivity {

    DatabaseHelper db;

    String payeeName;

    EditText currency;
    EditText address;
    EditText confirmAddress;
    Button addButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_wallet);

        Intent intent = getIntent();
        payeeName = intent.getStringExtra(getString(R.string.payee_name));

        db = new DatabaseHelper(this);

        currency = (EditText) findViewById(R.id.wallet_edit_currency);
        address = (EditText) findViewById(R.id.wallet_edit_address);
        confirmAddress = (EditText) findViewById(R.id.wallet_confirm_address);
        addButton = (Button) findViewById(R.id.add_wallet);

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
            db.savePayee(payeeName, currency.getText().toString(), address.getText().toString());
            throwToast(this, getString(R.string.db_success));
            Intent intent = new Intent(this, WalletList.class);
            intent.putExtra(getString(R.string.payee_name), payeeName);
            startActivity(intent);
        } else {
            throwToast(this, getString(R.string.db_address_error));
        }
    }

}
