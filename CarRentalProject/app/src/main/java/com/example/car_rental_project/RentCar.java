package com.example.car_rental_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RentCar extends AppCompatActivity implements View.OnClickListener{
    Button sbtnDatePicker,ebtnDatePicker,rentB,editB;
    EditText stxtDate, etxtDate;
    private int smYear, smMonth, smDay, emYear, emMonth, emDay;
    int position;
    ImageView imgv;
    TextView descriptionTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        Intent i = getIntent();
        imgv = findViewById(R.id.Carimg);
        descriptionTV = findViewById(R.id.DescTV);
        position = getIntent().getIntExtra("position",0);
        descriptionTV.setText(Data.cars.get(position).getDescreiption());
        sbtnDatePicker=(Button)findViewById(R.id.btn_sdate);
        ebtnDatePicker=(Button)findViewById(R.id.btn_edate);
        rentB=(Button)findViewById(R.id.rentB);
        editB = (Button)findViewById(R.id.editB);
        stxtDate=(EditText)findViewById(R.id.in_sdate);
        etxtDate=(EditText)findViewById(R.id.in_edate);
        sbtnDatePicker.setOnClickListener(this);
        rentB.setOnClickListener(this);
        ebtnDatePicker.setOnClickListener(this);
        editB.setOnClickListener(this);
        View v = findViewById(R.id.editB);
        if(Data.role == 1)
            v.setVisibility(View.VISIBLE);


        RequestQueue queue  = Volley.newRequestQueue(this);
        String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/getImage.php?img="+ Data.cars.get(position).getImageID();

        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgv.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RentCar.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);
    }

    @Override
    public void onClick(View v) {
        if (v == editB){
            Intent i = new Intent(this,EditCar.class);
            i.putExtra("position",position);
            startActivity(i);
        }
        if(v == rentB){
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/Rent.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(RentCar.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RentCar.this, error.toString(),Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("sd",stxtDate.getText().toString());
                    params.put("ed",etxtDate.getText().toString());
                    params.put("user",Data.user);
                    params.put("cn",Data.cars.get(position).getCarnumber().toString());
                    params.put("key","GFHrent");
                    return params;
                }
            };
            queue.add(request);
        }
        if (v == sbtnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            smYear = c.get(Calendar.YEAR);
            smMonth = c.get(Calendar.MONTH);
            smDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            stxtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, smYear, smMonth, smDay);
            datePickerDialog.show();
        }
        if (v == ebtnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            emYear = c.get(Calendar.YEAR);
            emMonth = c.get(Calendar.MONTH);
            emDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            etxtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, emYear, emMonth, emDay);
            datePickerDialog.show();
        }
    }
}