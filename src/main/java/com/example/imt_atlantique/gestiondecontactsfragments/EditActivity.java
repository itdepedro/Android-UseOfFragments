package com.example.imt_atlantique.gestiondecontactsfragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends Activity {
    String prenom;
    EditText editTextPrenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent  = getIntent();
        prenom = intent.getStringExtra(Intent.EXTRA_TEXT);
        editTextPrenom = (EditText) findViewById(R.id.editText);
        editTextPrenom.setText(prenom);
    }
    public void PrenomOk (View v){
        int resultCode = 20;

        Intent intent=new Intent();
        intent.putExtra("ANSWER",editTextPrenom.getText().toString());
        setResult(resultCode,intent);
        finish();//finishing activity
    }

    public void PrenomCancel (View v){
        int resultCode = 20;

        Intent intent=new Intent();
        intent.putExtra("ANSWER",prenom);
        setResult(resultCode,intent);
        finish();//finishing activity
    }
}
