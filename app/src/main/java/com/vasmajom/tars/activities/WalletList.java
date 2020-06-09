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
import com.vasmajom.tars.adapters.WalletAdapter;
import com.vasmajom.tars.db.DatabaseHelper;

public class WalletList extends AppCompatActivity {

    DatabaseHelper dbHelper;

    String payeeName;

    RecyclerView walletList;
    WalletAdapter walletAdapter;
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

        Intent intent = getIntent();
        payeeName = intent.getStringExtra(getString(R.string.payee_name));
        setTitle(String.format(getString(R.string.wallet_header), payeeName));

        walletList = findViewById(R.id.list_view);
        walletList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        walletList.setLayoutManager(layoutManager);
        walletAdapter = new WalletAdapter(this, dbHelper.getWalletsOf(payeeName));
        walletList.setAdapter(walletAdapter);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addWallet();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void addWallet() {
        Intent intent = new Intent(this, AddWallet.class);
        intent.putExtra(getString(R.string.payee_name), payeeName);
        startActivity(intent);
    }

}
