package com.example.imt_atlantique.gestiondecontactsfragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DateActivity extends Activity {
    boolean dateOut = false;
    DateData dateDataD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        // you need to have a list of data that you want the spinner to display
        List<String> spinnerArray =  new ArrayList<String>();
        for (int i=1900;i<2019;i++){
            spinnerArray.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item , spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItemsY = (Spinner) findViewById(R.id.spinnerY);
        sItemsY.setAdapter(adapter);
        Spinner sItemsM = (Spinner) findViewById(R.id.spinnerM);
        Spinner sItemsD = (Spinner) findViewById(R.id.spinnerD);
        Intent intent = getIntent();

        String str = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        Log.i("TEST STR",str);
        dateDataD = new DateData(str.substring(0,2),
                str.substring(2,4),
                str.substring(4,8));

        //dateDataD = getIntent().getParcelableExtra("Date");
        sItemsY.setSelection(Integer.valueOf(dateDataD.getYear())-1900);
        sItemsM.setSelection(Integer.valueOf(dateDataD.getMonth())-1);
        sItemsD.setSelection(Integer.valueOf(dateDataD.getDay())-1);

        // Log.i("DateData",dateDataD);
    }
    public void DateOk (View v){
        int resultCode = 2;
        Spinner sItemsY = (Spinner) findViewById(R.id.spinnerY);
        Spinner sItemsM = (Spinner) findViewById(R.id.spinnerM);
        Spinner sItemsD = (Spinner) findViewById(R.id.spinnerD);
        String.valueOf(sItemsD.getSelectedItemPosition());
        DateData answer = new DateData(sItemsD.getItemAtPosition(sItemsD.getSelectedItemPosition()).toString(),
                sItemsM.getItemAtPosition(sItemsM.getSelectedItemPosition()).toString(),
                sItemsY.getItemAtPosition(sItemsY.getSelectedItemPosition()).toString());
        Intent intent=new Intent();
        intent.putExtra("ANSWER",answer);

        setResult(resultCode,intent);
        finish();//finishing activity
    }

    public void DateCancel (View v){
        int resultCode = 2;
        Intent intent=new Intent();
        intent.putExtra("ANSWER",dateDataD);

        setResult(resultCode,intent);
        finish();//finishing activity
    }
}











