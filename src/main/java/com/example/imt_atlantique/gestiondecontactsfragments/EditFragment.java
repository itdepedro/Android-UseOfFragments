package com.example.imt_atlantique.gestiondecontactsfragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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

public class EditFragment extends Fragment {

    protected MainActivity mainActivity;
    private String prenom;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("lifecycle","df 1");

        if (savedInstanceState != null) {
            prenom = savedInstanceState.getString("prenom");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("lifecycle", "df 2");
        final View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        mainActivity.fragment_flag = 3;
        EditText firstname = (EditText) rootView.findViewById(R.id.editTextEF);
        firstname.setText(prenom);
        Button PrenomOK = (Button)rootView.findViewById (R.id.buttonEF1) ; if (PrenomOK != null) {
            PrenomOK.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PrenomOK();
                }
            });
        }
        Button PrenomCancel = (Button)rootView.findViewById (R.id.buttonEF2) ; if (PrenomCancel != null) {
            PrenomCancel.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PrenomCancel();
                }
            });
        }
        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveUserDetails();
        outState.putString("prenom",prenom);
        super.onSaveInstanceState(outState);
    }
        public void PrenomOK (){
            saveUserDetails();
            mainActivity.setPrenom(prenom);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack ();
        }

        public void PrenomCancel (){
            mainActivity.setPrenom(prenom);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack ();
        }
        public void saveUserDetails(){
            EditText firstname = (EditText) getActivity().findViewById(R.id.editTextEF);
            prenom = firstname.getText().toString();
        }
        public void setUserDetails() {
            EditText firstname = (EditText) getActivity().findViewById(R.id.editTextEF);
            firstname.setText(prenom);
        }
        public void setPrenom(String prenom){this.prenom = prenom;}

}
