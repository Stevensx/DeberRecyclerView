package com.example.listaconfotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.listaconfotos.Adaptadores.AdaptadorUsuario;
import com.example.listaconfotos.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Webservice.Asynchtask;
import Webservice.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask,AdapterView.OnItemClickListener{
    ListView lstOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstOpciones = (ListView)findViewById(R.id.listusuario);
        View header = getLayoutInflater().inflate(R.layout.ldheaderusuarios, null);
        lstOpciones.addHeaderView(header);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this,MainActivity.this );
        ws.execute("GET");
        lstOpciones.setOnItemClickListener(this);


    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject JSONlista = new JSONObject(result);
        JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");
        ArrayList<Usuario>lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);
        AdaptadorUsuario adapatorUsuario = new AdaptadorUsuario(this,lstUsuarios );
        lstOpciones.setAdapter(adapatorUsuario);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Usuario usuario = (Usuario) adapterView.getItemAtPosition(position);

        Intent intent = new Intent(MainActivity.this, ActividadVer.class);
        intent.putExtra("first_name", usuario.getNombres());
        intent.putExtra("email", usuario.getEmail());
        intent.putExtra("email", usuario.getWebsite());

        Uri imageUri = Uri.parse(usuario.getUrlavatar());
        intent.putExtra("avatar", imageUri.toString());

        startActivity(intent);

    }
}