package com.vasmajom.tars.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.vasmajom.tars.model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tars.db";

    private static final String TABLE_WALLETS = "wallet";

    private static final String WALLET_COL_1 = "name";
    private static final String WALLET_COL_2 = "currency";
    private static final String WALLET_COL_3 = "address";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_WALLETS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT NOT NULL," +
                " currency TEXT NOT NULL," +
                " address TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public List<String> getPayees() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> payees = new ArrayList<>();
        String query = "SELECT DISTINCT name FROM wallet";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                payees.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return payees;
    }

    public List<Wallet> getWalletsOf(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Wallet> wallets = new ArrayList<>();
        String query = "SELECT * FROM wallet WHERE name = '" + name + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String currency = cursor.getString(2);
                String address = cursor.getString(3);
                wallets.add(new Wallet(currency, address));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wallets;
    }

    public boolean savePayee(String name, String currency, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues walletValues = new ContentValues();
        walletValues.put(WALLET_COL_1, name);
        walletValues.put(WALLET_COL_2, currency);
        walletValues.put(WALLET_COL_3, address);
        long res = db.insert(TABLE_WALLETS, null, walletValues);

        db.close();

        return res != -1;
    }

    public void panic() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_WALLETS);
        db.execSQL("CREATE TABLE " + TABLE_WALLETS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT NOT NULL," +
                " currency TEXT NOT NULL," +
                " address TEXT NOT NULL)");
    }
}
