package com.example.car_rental_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Login_Form extends AppCompatActivity implements View.OnClickListener {
    EditText etEmail , etPassword, etFirstName, etLastName, etEmailAddress, etPhoneNumber,etConfirmPassword;
    Button btLogin, btRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");
        etEmail = findViewById(R.id.Text1);
        etPassword = (EditText) findViewById(R.id.Text2);
        btLogin = findViewById(R.id.login);
        btLogin.setOnClickListener(this);
    }

    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }

    @Override
    public void onClick(View v) {
        if(v == btLogin){
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://mobile-project-car-rental.000webhostapp.com/PHP/Login.php";
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("1") || response.equals("0")){
                        Toast.makeText(Login_Form.this,"Logged in",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login_Form.this,MainActivity.class);
                        Data.LoggedInUser(etEmail.getText().toString());
                        Data.addRole(Integer.parseInt(response));
                        startActivity(i);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login_Form.this, error.toString(),Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email",etEmail.getText().toString());
                    params.put("password",etPassword.getText().toString());
                    params.put("key","GFHlogin");
                    return params;
                }
            };
            queue.add(request);
        }
    }
}