package com.example.car_rental_project;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Data {
    public static ArrayList<Car> cars = new ArrayList<Car>();
    public static void addCar(Car c){
        cars.add(c);
    }
    public static String user = "ghyath@liu.edu.lb";
    public static int role;

    public static void LoggedInUser(String userr){
        user = userr;
    }

    public static void addRole(int r){
        role = r;
    }
}
