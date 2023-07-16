package com.example.listaconfotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActividadVer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ver);

        Intent intent = getIntent();
        String title = intent.getStringExtra("first_name");
        String id = intent.getStringExtra("email");
        String price = intent.getStringExtra("email");

        Uri imageUri = Uri.parse(intent.getStringExtra("avatar"));

        // Utiliza el identificador del ImageView en el diseño XML
        ImageView imageView = findViewById(R.id.imgUsr);
        TextView txtTitle = findViewById(R.id.txtTtitulo);
        TextView txtId = findViewById(R.id.txtid);
        TextView txtPrice = findViewById(R.id.txtPrice);



        // Utiliza una biblioteca como Glide para cargar la imagen desde la URI
        Glide.with(this).load(imageUri).into(imageView);

        txtTitle.setText(title);
        txtId.setText(id);
        txtPrice.setText(price);
    }
}