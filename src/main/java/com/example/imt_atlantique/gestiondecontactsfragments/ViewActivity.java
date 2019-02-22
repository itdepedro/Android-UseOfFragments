package com.example.imt_atlantique.gestiondecontactsfragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        String nom = intent.getStringExtra("nom");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(nom);
    }
}
