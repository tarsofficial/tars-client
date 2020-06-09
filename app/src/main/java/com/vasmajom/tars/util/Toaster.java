package com.vasmajom.tars.util;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public static void throwToast(Context context, String message) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

}
