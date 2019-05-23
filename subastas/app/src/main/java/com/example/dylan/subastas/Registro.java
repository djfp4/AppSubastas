package com.example.dylan.subastas;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText usuarioT = (EditText)findViewById(R.id.usuario);
        final EditText claveT = (EditText)findViewById(R.id.clave);
        final EditText nombreT = (EditText)findViewById(R.id.nombre);
        final EditText apellidoT = (EditText)findViewById(R.id.apellido);
        final EditText emailT = (EditText)findViewById(R.id.email);
        final EditText telefonoT = (EditText)findViewById(R.id.telefono);
        Button btn_registro = (Button)findViewById(R.id.btn_registro);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usuarioT.getText().toString();
                String clave = claveT.getText().toString();
                String nombre = nombreT.getText().toString();
                String apellido = apellidoT.getText().toString();
                String email = emailT.getText().toString();
                String telefono = telefonoT.getText().toString();


                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");

                            if (ok == true) {

                                Intent i = new Intent(Registro.this, Login.class);

                                Registro.this.startActivity(i);
                                Registro.this.finish();
                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
                                alerta.setMessage("Fallo el registro")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }
                };

                RegistroRequest r = new RegistroRequest(usuario,clave,nombre,apellido,email,telefono,respuesta);
                RequestQueue cola = Volley.newRequestQueue(Registro.this);
                cola.add(r);
            }
        });
    }
}
