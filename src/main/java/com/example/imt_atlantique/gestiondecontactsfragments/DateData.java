package com.example.imt_atlantique.gestiondecontactsfragments;


import android.os.Parcel;
import android.os.Parcelable;


public class DateData implements Parcelable {

    String day;
    String month;
    String year;


    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel (Parcel out, int flags){
        out.writeString(day);
        out.writeString(month);
        out.writeString(year);

    }
    public static final Parcelable.Creator<DateData> CREATOR = new Parcelable.Creator<DateData>() {
        public DateData createFromParcel(Parcel in) {
            return new DateData(in);
        }

        public DateData[] newArray(int size) {
            return new DateData[size];
        }
    };

    private DateData(Parcel in) {
        day =  in.readString();
        month = in.readString();
        year = in.readString();
    }

    public DateData (String day,String month,String year){

        this.year = year;
        this.month = month;
        this.day = day;

    }

    public String getDay(){
        return day;
    }
    public String getMonth(){ return month; }
    public String getYear(){
        return year;
    }

}
