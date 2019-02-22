package com.example.imt_atlantique.gestiondecontactsfragments;


import android.os.Parcel;
import android.os.Parcelable;


public class User implements Parcelable {
    //ArrayList<String> info;
//
    String nom;
    String prenom;
    String birthday;
    String depSel;
    String test = "test";

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel (Parcel out, int flags){
        out.writeString(nom);
        out.writeString(prenom);
        out.writeString(birthday);
        out.writeString(depSel);

    }
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        prenom = in.readString();
        nom =  in.readString();
        birthday = in.readString();
        depSel = in.readString();
    }

    public User (String nom,String prenom,String birthday, String depSel){

        this.nom = nom;
        this.prenom = prenom;
        this.birthday = birthday;
        this.depSel = depSel;
    }

    public String getNom(){
        return nom;
    }
    public String getPrenom(){
        return prenom;
    }
    public String getBirthday(){
        return birthday;
    }
    public String getDepSel(){
        return depSel;
    }
    public String getTest (){
        return test;
    }

}
