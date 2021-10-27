package com.example.car_rental_project;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewReservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);
        ListView ls= findViewById(R.id.lsviewreservation);
        TextView header = findViewById(R.id.tvName);
        final ArrayList<reservations> reservation = new ArrayList<reservations>();
        final ArrayAdapter<reservations> adapter = new ArrayAdapter<reservations>(ViewReservation.this, android.R.layout.simple_list_item_1, reservation);
        ls.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/ViewReservations.php?user="+Data.user;

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0;i < response.length();i++) {
                    try {
                        System.out.println(response);
                        JSONObject row = response.getJSONObject(i);
                        String Sdate = row.getString("startdate");
                        String Edate= row.getString("enddate");
                        String carname = row.getString("carname");
                        System.out.println(carname);
                        String fname = row.getString("firstname");
                        String lname = row.getString("lastname");
                        String RPD = row.getString("RPD");
                        String image = row.getString("imageID");
                        String total = (String) row.get("7").toString();
                        reservation.add(new reservations(Sdate, Edate, carname, fname, lname,RPD,image,total));
                        System.out.println(reservation.get(i).toString());
                        header.setText("Welcome "+reservation.get(i).fname+" "+reservation.get(i).lname);

                    }
                    catch (Exception ex) {
                        Toast.makeText(ViewReservation.this,ex.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, null);

        queue.add(request);

    }

}
