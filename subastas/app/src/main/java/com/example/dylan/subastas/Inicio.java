package com.example.dylan.subastas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        final TextView mensaje = (TextView)findViewById(R.id.Info);
        Intent i = this.getIntent();
        String nombre = i.getStringExtra("nombre");
        String usuario = i.getStringExtra("usuario");
        mensaje.setText(mensaje.getText()+" "+nombre+" "+ usuario+"");
    }
}
