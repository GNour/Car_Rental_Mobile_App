package com.example.car_rental_project;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class reservations {
    private String Sdate,Edate;
    public String carname,fname,lname;
    private String RPD;
    private String image;
    private String total;
    public String getfname(){
        return this.fname;
    }
    public String getlname(){
        return this.lname;
    }
    public reservations(String Sdate, String Edate, String carname, String fname, String lname,String RPD,String image,String total) {
        this.carname = carname;
        this.fname = fname;
        this.lname = lname;
        this.RPD = RPD;
        this.Sdate = Sdate;
        this.Edate = Edate;
        this.image=image;
        if(Integer.parseInt(total) == 0){
            this.total = RPD;
        }
        else{
            this.total = total+"$";
        }

    }

    @Override
    public String toString() {
        return  "Car: "+carname+
                "\nFrom: "+Sdate+
                "\nTo: "+Edate+
                "\nTotal Price: "+total;
    }
}
