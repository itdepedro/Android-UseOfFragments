package com.example.imt_atlantique.gestiondecontactsfragments;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Fragment;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFragment extends Fragment {
    DateData dateDataD;
    protected MainActivity mainActivity;
    private Spinner sItemsY;
    private Spinner sItemsM;
    private Spinner sItemsD;
    private String years;
    private String months;
    private String days;
    private String date;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            years = savedInstanceState.getString("years");
            months = savedInstanceState.getString("months");
            days = savedInstanceState.getString("days");
            date = savedInstanceState.getString("date");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("lifecycle", "df 2");
        final View rootView = inflater.inflate(R.layout.fragment_date, container, false);
        mainActivity.fragment_flag = 4;

        days = date.substring(0,2);
        months = date.substring(2,4);
        years = date.substring(4,8);

        sItemsY = (Spinner) rootView.findViewById(R.id.spinnerY);
        sItemsM = (Spinner) rootView.findViewById(R.id.spinnerM);
        sItemsD = (Spinner) rootView.findViewById(R.id.spinnerD);
        sItemsY.setSelection(Integer.valueOf(years)-1900);
        sItemsM.setSelection(Integer.valueOf(months)-1);
        sItemsD.setSelection(Integer.valueOf(days)-1);

        Button DateOk = (Button)rootView.findViewById (R.id.buttonDF1) ; if (DateOk != null) {
            DateOk.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DateOk();
                }
            });
        }
        Button DateCancel = (Button)rootView.findViewById (R.id.buttonDF2) ; if (DateCancel != null) {
            DateCancel.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DateCancel();
                }
            });
        }
        return rootView;
    }

    public void onSaveInstanceState(Bundle outState) {
        //saveUserDetails();
        outState.putString("years",years);
        outState.putString("months",months);
        outState.putString("days",days);
        outState.putString("date",date);
        super.onSaveInstanceState(outState);
    }
    public void DateOk (){
        saveUserDetails();
        mainActivity.setDate1(date);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack ();
    }

    public void DateCancel (){
        mainActivity.setDate1(date);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack ();

    }
    public void saveUserDetails(){
        sItemsY = (Spinner) getActivity().findViewById(R.id.spinnerY);
        sItemsM = (Spinner) getActivity().findViewById(R.id.spinnerM);
        sItemsD = (Spinner) getActivity().findViewById(R.id.spinnerD);
        years = sItemsY.getItemAtPosition(sItemsY.getSelectedItemPosition()).toString();
        months = sItemsM.getItemAtPosition(sItemsM.getSelectedItemPosition()).toString();
        days = sItemsD.getItemAtPosition(sItemsD.getSelectedItemPosition()).toString();
        date = days+"/"+months+"/"+years;
    }
    public void setUserData(){
        sItemsY = (Spinner) getActivity().findViewById(R.id.spinnerY);
        sItemsM = (Spinner) getActivity().findViewById(R.id.spinnerM);
        sItemsD = (Spinner) getActivity().findViewById(R.id.spinnerD);
        sItemsY.setSelection(Integer.valueOf(years)-1900);
        sItemsM.setSelection(Integer.valueOf(months)-1);
        sItemsD.setSelection(Integer.valueOf(days)-1);

    }
    public void setDate(String date){
        this.date = date;
    }

}











