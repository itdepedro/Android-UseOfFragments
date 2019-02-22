package com.example.imt_atlantique.gestiondecontactsfragments;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    String lNameStr = new String();
    String fNameStr = new String();
    String dateStr = new String();
    int depSel = 0;
    String [] telBuf = new String [10];
    int ind = 32000;

    String depOtherStr = new String();
    String searchDep = new String();
    boolean flag = false;
    int fragment_flag = 1; //1 = main fragment --- 2 = display fragment

    protected MainFragment mf;
    protected DisplayFragment df;
    protected EditFragment ef;
    protected DateFragment datef;

    protected String nomAfficher;
    //PERSISTENCIA DE DATOS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("lifecycle", "1");

        mf = new MainFragment();
        df = new DisplayFragment();
        ef = new EditFragment();
        datef = new DateFragment();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.fragment, mf,"FRAGMENT_MAIN").commit();
        }else{
            mf = (MainFragment) getFragmentManager().getFragment(savedInstanceState,"FRAGMENT_MAIN");
            df = (DisplayFragment) getFragmentManager().getFragment(savedInstanceState,"FRAGMENT_DISPLAY");
            ef = (EditFragment) getFragmentManager().getFragment(savedInstanceState,"FRAGMENT_EDIT");
            datef = (DateFragment) getFragmentManager().getFragment(savedInstanceState,"FRAGMENT_DATE");
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle","2");
        if (fragment_flag == 1){
//            EditText firstname = (EditText) findViewById(R.id.editText2);
//            firstname.setText(fNameStr);
//            EditText date = (EditText) findViewById(R.id.editText3);
//            date.setText(dateStr);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle","4  "+String.valueOf(fragment_flag));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        android.app.FragmentManager manager = getFragmentManager();
        if(fragment_flag == 1) {
            manager.putFragment(outState, "FRAGMENT_MAIN", mf);
        }else {
            if (fragment_flag == 2) {
                manager.putFragment(outState, "FRAGMENT_DISPLAY", df);
            } else {
                if (fragment_flag == 3) {
                    manager.putFragment(outState, "FRAGMENT_EDIT", ef);
                } else {
                    if (fragment_flag == 4) {
                        manager.putFragment(outState, "FRAGMENT_DATE", datef);
                    }
                }
            }
        }
        Log.i("lifecycle","7");
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("lifecycle","8");
        super.onRestoreInstanceState(savedInstanceState);
    }
    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //MENU ITEMS
    public boolean editPrenom (MenuItem item){
        mf.saveUserData();
        String prenom = mf.getPrenom();
        if (ef == null)
            ef = new EditFragment();
        ef.setPrenom(prenom);
        getFragmentManager().beginTransaction().remove(mf).addToBackStack(null).add(R.id.fragment,ef).commit();

        return true;
    }
    public boolean webSearch (MenuItem item){
        String dep = mf.getDepartment();
        if (dep.equals(getString(R.string.item1))){
            Snackbar.make(findViewById(R.id.myRelativeLayout),getString(R.string.plsMessage)+" "+getString(R.string.ville), Snackbar.LENGTH_LONG).show();
        }else {

            if (dep.equals("Cotes dArmor"))
                dep = "Cotes-d%27Armor";

            String url =getString(R.string.wikiURL)+dep;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        return true;
    }
    public boolean share (MenuItem item){
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String cityStr = mySpinner.getSelectedItem().toString();

        if( mySpinner.getSelectedItem().toString().equals(getString(R.string.item1))){
            Snackbar.make(findViewById(R.id.myRelativeLayout),getString(R.string.plsMessage)+" "+getString(R.string.ville), Snackbar.LENGTH_LONG).show();
        }else {
            if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item5))) {
                EditText city = (EditText) findViewById(R.id.editText6);
                cityStr = city.getText().toString();
            }

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, cityStr);
            sendIntent.setType("text/plain");

            String title = getString(R.string.shareText); //"Partager ce contenu avec";
            Intent chooser = Intent.createChooser(sendIntent, title);

            // Verify the original intent will resolve to at least one activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }


        }
        return true;

    }
    public void viewNom (MenuItem item){
        mf.saveUserData();
        String nom = mf.getNom();
        if (df == null)
            df = new DisplayFragment();
        df.setNom(nom);
        getFragmentManager().beginTransaction().remove(mf).addToBackStack(null).add(R.id.fragment,df,"DISPLAY_FRAGMENT").commit();

    }
    public boolean resetAction (MenuItem item){
        EditText lastname = (EditText) findViewById(R.id.editText1);
        lastname.getText().clear();
        EditText firstname = (EditText) findViewById(R.id.editText2);
        firstname.getText().clear();
        EditText date = (EditText) findViewById(R.id.editText3);
        date.getText().clear();
        Spinner city = (Spinner) findViewById(R.id.spinner);
        city.setSelection(0);
        EditText cityO = (EditText) findViewById(R.id.editText6);
        cityO.getText().clear();

        int j=0;
        for (int i = 0;i<ind-32000;i++) {
            EditText tel = findViewById(32200+i);
            tel.getText().clear();
            j++;
            eraseLayout(32000+i);
        }
        ind = ind-j;
        return true;
    }
    public void setDate(String date){
        mf.saveUserData();
        if (datef == null)
            datef = new DateFragment();
        datef.setDate(date);
        getFragmentManager().beginTransaction().remove(mf).addToBackStack(null).add(R.id.fragment,datef).commit();
    }


    //FRAGMENT

    public void addNewLayout (int ind){

        final LinearLayout lm = (LinearLayout) findViewById(R.id.LAYVERT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // Create LinearLayout
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setId(ind);
        ll.setLayoutParams(params);

        // Create TextView
        TextView phone = new TextView(this);
        phone.setText(getString(R.string.phoneNumber));
        phone.setLayoutParams(new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT));
        phone.setId(ind+100);
        ll.addView(phone);

        //Create EditText
        EditText ePhone = new EditText(this);
        ePhone.setId(ind+200);
        ePhone.setHint("0621316866");
        ePhone.setLayoutParams(new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT));
        ePhone.setEms(10);
        ll.addView(ePhone);

        // Create Button Dynamically
        final Button btnShow = new Button(this);
        btnShow.setText(getString(R.string.buttonApeler));
        btnShow.setId(ind+300);
        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tel = (EditText) findViewById(btnShow.getId()-100);
                Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:"+tel.getText().toString())); //
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

        // Add Button to LinearLayout
        ll.addView(btnShow);
        lm.addView(ll);
    }
    public void eraseLayout (int indVal){
        LinearLayout placeHolder = (LinearLayout) findViewById(R.id.LAYVERT);
        placeHolder.removeView(findViewById(indVal));
    }
    //FRAGMENT
    public boolean checkDate (String Date){
        if (Date.length() != 10)
            return false;
        else if (!Date.regionMatches(2,"/",0,1))
            return false;
        else if (!Date.regionMatches(5,"/",0,1))
            return false;
        else if (Integer.valueOf(Date.substring(0,2))>31||
                Integer.valueOf(Date.substring(3,5))>12||
                Integer.valueOf(Date.substring(6,10))>2018||
                Integer.valueOf(Date.substring(6,10))<1900
                )
            return false;
        else
            return true;
    }
    public void setPrenom(String prenom){
        mf.setPrenom(prenom);
    }
    public void setDate1 (String date){ mf.setDateStr(date);}

}
