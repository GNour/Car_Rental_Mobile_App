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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnrent,btnviewreservstion,btnaddcar;


   /* final int REQUEST_CODE_GALLERY = 999;*/

   /* public static SQLiteHelper sqLiteHelper;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnrent=findViewById(R.id.btnrent);
        View v = findViewById(R.id.btnaddcare);
        if(Data.role == 1)
            v.setVisibility(View.VISIBLE);
        btnrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ViewCars.class);
                startActivity(i);
            }
        });
        btnviewreservstion=findViewById(R.id.btnviewreservation);
        btnaddcar=findViewById(R.id.btnaddcare);
        btnaddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAddcar();
            }
        });
        btnviewreservstion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openviewreservation();
            }
        });
    }
    public void Openviewreservation(){
        Intent intent=new Intent(this,ViewReservation.class);
        startActivity(intent);
    }
    public void OpenAddcar(){
        Intent i=new Intent(this,AddCar.class);
        startActivity(i);

    }
}
