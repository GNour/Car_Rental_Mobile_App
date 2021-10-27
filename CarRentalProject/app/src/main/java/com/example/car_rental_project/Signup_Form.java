package com.example.car_rental_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Signup_Form extends AppCompatActivity implements View.OnClickListener {
    EditText etPassword, etFirstName, etLastName, etEmailAddress, etPhoneNumber,etConfirmPassword;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");
        etFirstName= findViewById(R.id.t1);
        etLastName= findViewById(R.id.t2);
        etEmailAddress= findViewById(R.id.t3);
        etPhoneNumber= findViewById(R.id.t4);
        etPassword =findViewById(R.id.t5);
        etConfirmPassword=findViewById(R.id.t6);
        btRegister=findViewById(R.id.register);
        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/Register.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Signup_Form.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Signup_Form.this, error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fname",etFirstName.getText().toString());
                params.put("lname",etLastName.getText().toString());
                params.put("email",etEmailAddress.getText().toString());
                params.put("pn",etPhoneNumber.getText().toString());
                params.put("pwd",etPassword.getText().toString());
                params.put("key","GFHregister");
                return params;
            }
        };
        queue.add(request);
    }

    }
}