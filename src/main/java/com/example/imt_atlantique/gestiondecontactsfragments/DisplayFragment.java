package com.example.imt_atlantique.gestiondecontactsfragments;

import android.app.Fragment;
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

public class DisplayFragment extends Fragment {

    protected MainActivity mainActivity;
    protected String nom;

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
            nom = savedInstanceState.getString("nomAffiche");
            Log.i("lifecycle","df 1.1");
            TextView textView = (TextView) getActivity().findViewById(R.id.textView8);
            textView.setText(nom);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("lifecycle","df 2");
        final View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView8);
        textView.setText(nom);
        mainActivity.fragment_flag = 2;
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("lifecycle","df 3");
        TextView textView = (TextView) getActivity().findViewById(R.id.textView8);
        outState.putString("nomAffiche",textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public void setNom (String nom){
        this.nom = nom;
    }
}
