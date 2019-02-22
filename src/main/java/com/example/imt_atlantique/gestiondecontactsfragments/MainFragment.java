package com.example.imt_atlantique.gestiondecontactsfragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {

    private String nom;
    private String prenom;
    private String date;
    private int depSel;
    private String depString;
    private String [] telBuf = new String [10];

    protected MainActivity mainActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false) ;

        Button Validate = (Button)rootView.findViewById (R.id.button) ; if (Validate != null) {
            Validate.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        validateAction();
                }
            });
        }
        Button selDate = (Button)rootView.findViewById (R.id.button1) ; if (selDate != null) {
            selDate.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        setDate();
                }
            });
        }
        Button addPhone = (Button)rootView.findViewById (R.id.floatingActionButtonAdd) ; if (addPhone != null) {
            addPhone.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addNumberAction();
                }
            });
        }
        final Button suprPhone = (Button)rootView.findViewById (R.id.floatingActionButtonSupr) ; if (suprPhone != null) {
            suprPhone.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    suprNumberAction();
                }
            });
        }
        mainActivity.fragment_flag = 1;
        Log.i("lifecycle","mf 0");

        return rootView ;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("lifecycle","mf 1");
        if (savedInstanceState != null) {
            Log.i("lifecycle","mf 1.1");
            nom = savedInstanceState.getString("nom");
            prenom = savedInstanceState.getString("prenom");
            date = savedInstanceState.getString("date");
            depSel = savedInstanceState.getInt("depSel");
            depString = savedInstanceState.getString("depString");
            setUserData();
        }
        MainFragment myFragment = (MainFragment) getFragmentManager().findFragmentByTag("FRAGMENT_MAIN");
        mainActivity.mf = myFragment;

    }

    @Override
    public void onPause() {
        super.onPause();
        saveUserData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("nom",nom);
        outState.putString("prenom",prenom);
        outState.putString("date",date);
        outState.putInt("depSel",depSel);
        outState.putString("depString",depString);
        super.onSaveInstanceState(outState);
    }


    //FRAGMENT

    public void validateAction (){
        EditText lastname = (EditText) getActivity().findViewById(R.id.editText1);
        String lnStr = lastname.getText().toString();
        EditText firstname = (EditText) getActivity().findViewById(R.id.editText2);
        String fnStr = firstname.getText().toString();
        EditText date = (EditText) getActivity().findViewById(R.id.editText3);
        String dateStr = date.getText().toString();
        Spinner mySpinner = (Spinner) getActivity().findViewById(R.id.spinner);
        String cityStr = mySpinner.getSelectedItem().toString();
        String telStr = new String();
        telStr = "-no phone";
        String textToShow = new String ();
        int i;
        for (i = 0;i<mainActivity.ind-32000;i++) {
            EditText tel = getActivity().findViewById(32200+i);
            telStr =  "-" + tel.getText().toString();
            mainActivity.telBuf[i] = telStr;
            telStr = "";
        }
        if (lnStr.isEmpty())
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.lastname);
        else if (fnStr.isEmpty())
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.firstname);
        else if (dateStr.isEmpty())
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.date);
        else if (!mainActivity.checkDate(dateStr))
            textToShow = getString(R.string.wrongDate);
        else if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item1)))
            textToShow = getString(R.string.plsMessage)+" "+getString(R.string.ville);
        else {
            if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item5))){
                EditText city = (EditText) getActivity().findViewById(R.id.editText6);
                cityStr = city.getText().toString();
            }
            if (cityStr.isEmpty())
                textToShow = getString(R.string.plsMessage)+" "+getString(R.string.ville);
            else{

                for (i = 0;i<mainActivity.ind-32000;i++) {
                    telStr += mainActivity.telBuf[i];
                }

                textToShow = lnStr + "-" + fnStr + "-" + dateStr + "-" + cityStr + telStr;
            }

        }
        //Toast.makeText(getContext(),textToShow, Toast.LENGTH_LONG).show();
        Snackbar.make(getActivity().findViewById(R.id.myRelativeLayout),textToShow, Snackbar.LENGTH_LONG).show();

    }

    //FRAGMENT
    public void setDate (){
        saveUserData();
        if (date.isEmpty()){
            date = "01012000";
        }
        if (mainActivity.checkDate(date)) {

        }else{
            //Snackbar.make(getActivity().findViewById(R.id.mainLayout),getString(R.string.wrongDate), Snackbar.LENGTH_LONG).show();
            date = "01012000";
        }
        mainActivity.setDate(date);
    }
    public void addNumberAction () {
        mainActivity.addNewLayout(mainActivity.ind);
        mainActivity.ind++;

    }

    public void suprNumberAction (){
        if (mainActivity.ind != 32000) {
            mainActivity.ind--;
            mainActivity.eraseLayout(mainActivity.ind);
        }
    }
    public void setUserData(){
        EditText lastname = (EditText) getActivity().findViewById(R.id.editText1);
        lastname.setText(nom);
        EditText firstname = (EditText) getActivity().findViewById(R.id.editText2);
        firstname.setText(prenom);
        EditText dateET = (EditText) getActivity().findViewById(R.id.editText3);
        dateET.setText(date);
        Spinner mySpinner = (Spinner) getActivity().findViewById(R.id.spinner);
        mySpinner.setSelection(depSel);
        EditText city = (EditText) getActivity().findViewById(R.id.editText6);
        city.setText(depString);
        for (int i = 0;i<mainActivity.ind-32000;i++) {
            EditText tel = getActivity().findViewById(32200+i);
            tel.setText(telBuf[i]);
        }

    }
    public void saveUserData(){

        EditText lastname = (EditText) getActivity().findViewById(R.id.editText1);
        nom = lastname.getText().toString();
        EditText firstname = (EditText) getActivity().findViewById(R.id.editText2);
        prenom = firstname.getText().toString();
        EditText dateET = (EditText) getActivity().findViewById(R.id.editText3);
        date = dateET.getText().toString();
        Spinner mySpinner = (Spinner) getActivity().findViewById(R.id.spinner);
        depSel = mySpinner.getSelectedItemPosition();;
        EditText city = (EditText) getActivity().findViewById(R.id.editText6);
        depString = city.getText().toString();

        for (int i = 0;i<mainActivity.ind-32000;i++) {
            EditText tel = getActivity().findViewById(32200+i);
            telBuf[i] = tel.getText().toString();
        }
    }

    public String getNom() { return this.nom; }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenom(){return this.prenom;}


    public String getDepartment(){
        Spinner mySpinner = (Spinner) getActivity().findViewById(R.id.spinner);
        if (mySpinner.getSelectedItem().toString().equals(getString(R.string.item5))){
            return depString;
        }else
            return mySpinner.getSelectedItem().toString();
    }
    public void setDateStr(String date){ this.date = date;}
}
