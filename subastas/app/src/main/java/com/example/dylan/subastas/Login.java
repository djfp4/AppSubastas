package com.example.dylan.subastas;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registro = (TextView)findViewById(R.id.registro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Button btnLogin = (Button)findViewById(R.id.btn_login);
        final EditText usuarioT = (EditText)findViewById(R.id.usuario);
        final EditText claveT = (EditText)findViewById(R.id.clave);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usuario = usuarioT.getText().toString();
                final String clave = claveT.getText().toString();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if (ok == true) {
                                String nombre = jsonRespuesta.getString("nombre");
                                String usuario = jsonRespuesta.getString("usuario");

                                Intent bienvenido = new Intent(Login.this, Inicio.class);
                                bienvenido.putExtra("nombre", nombre);
                                bienvenido.putExtra("usuario", usuario);

                                Login.this.startActivity(bienvenido);
                                Login.this.finish();
                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(Login.this);
                                alerta.setMessage("Fallo el inicio de sesión")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }
                };
                LoginRequest r = new LoginRequest(usuario, clave, respuesta);
                RequestQueue cola = Volley.newRequestQueue(Login.this);
                cola.add(r);

            }
        });
    }
}
