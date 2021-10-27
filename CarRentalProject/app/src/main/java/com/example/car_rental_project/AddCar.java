package com.example.car_rental_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddCar extends AppCompatActivity implements View.OnClickListener {
    Button btnaddcare;
    EditText etcarname, etnumber, etmodel, etyear, etdesc,etRPD,etimgid;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    Bitmap bitmap;
    String urladdcar="https://fadelweb2021.000webhostapp.com/car/addcar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        etcarname = findViewById(R.id.etcarname);
        etnumber = findViewById(R.id.etnumber2);
        etmodel = findViewById(R.id.etcarmodel);
        etyear = findViewById(R.id.etyear);
        etdesc = findViewById(R.id.etcardesc);
        etRPD = findViewById(R.id.etRPD);
        etimgid = findViewById(R.id.etimageID);

        btnaddcare = (Button) findViewById(R.id.btnaddcare);
        btnaddcare.setOnClickListener(this);


}

    @Override
    public void onClick(View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/AddCar.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddCar.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCar.this, error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("carname",etcarname.getText().toString());
                params.put("carmodel",etmodel.getText().toString());
                params.put("year",etyear.getText().toString());
                params.put("carnumber",etnumber.getText().toString());
                params.put("cardesc",etdesc.getText().toString());
                params.put("RPD",etRPD.getText().toString());
                params.put("imgid",etimgid.getText().toString());
                return params;
            }
        };
        queue.add(request);
    }
}