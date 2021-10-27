package com.example.car_rental_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewCars extends AppCompatActivity {
    public static ArrayList<Car> cars = new ArrayList<Car>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car);
        Intent i = getIntent();
        ImageView imgv = findViewById(R.id.headerIV);
        imgv.setImageResource(R.drawable.header);
        final ListView carsLV = findViewById(R.id.carsLV);
        ArrayList<Car> cars = new ArrayList<Car>();
        ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(this, android.R.layout.simple_list_item_1, cars);
        carsLV.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/getCars.php";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i < response.length() ; i++){
                    try {
                        JSONObject row = response.getJSONObject(i);
                        cars.add(new Car(row.getString("carname"),row.getString("model"),row.getString("year"),row.getString("RPD"),row.getString("carnumber"),row.getString("imageID"),row.getString("description")));
                        Data.addCar(new Car(row.getString("carname"),row.getString("model"),row.getString("year"),row.getString("RPD"),row.getString("carnumber"),row.getString("imageID"),row.getString("description")));

                    } catch (JSONException e) {
                        Toast.makeText(ViewCars.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, null);
        queue.add(request);

        carsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ViewCars.this,RentCar.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });
    }
}