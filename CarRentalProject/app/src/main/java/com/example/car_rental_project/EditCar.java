package com.example.car_rental_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditCar extends AppCompatActivity implements View.OnClickListener {
    TextView title;
    EditText desc, RPD;
    Button save;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);
        Intent i = new Intent();
        position = getIntent().getIntExtra("position",0);
        title = findViewById(R.id.EditTV);
        desc = findViewById(R.id.descTB);
        RPD = findViewById(R.id.RPDTB);
        title.setText("Edit "+Data.cars.get(position).getCarname());
        desc.setText(Data.cars.get(position).getDescreiption());
        RPD.setText(Data.cars.get(position).getRPD());
        save = findViewById(R.id.SaveB);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == save){
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/editCar.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(EditCar.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EditCar.this, error.toString(),Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("desc",desc.getText().toString());
                    params.put("RPD",RPD.getText().toString());
                    params.put("cn",Data.cars.get(position).getCarnumber().toString());
                    params.put("key","GFHedit");
                    return params;
                }
            };
            queue.add(request);
        }
    }
}