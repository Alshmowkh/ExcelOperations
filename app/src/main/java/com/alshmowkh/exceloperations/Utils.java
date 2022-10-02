package com.alshmowkh.exceloperations;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    private Context context;

    public Utils(Context context) {
        this.context = context;
    }

    void message(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
