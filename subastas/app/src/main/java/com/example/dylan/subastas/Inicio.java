package com.example.dylan.subastas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class Inicio extends AppCompatActivity {

    ListView listado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        final TextView mensaje = (TextView)findViewById(R.id.Info);
        Intent i = this.getIntent();
        String nombre = i.getStringExtra("nombre");
        String usuario = i.getStringExtra("usuario");
        mensaje.setText(mensaje.getText()+" "+nombre+" "+ usuario+"");
        listado = (ListView) findViewById(R.id.lista);
        ObtenerDatos();

    }
    public void ObtenerDatos() {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://sellfasterrr.000webhostapp.com/listado.php";

        RequestParams parametros = new RequestParams();


        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    CargaLista(obtenerDatosJSON(new String(responseBody)));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
    public void CargaLista(ArrayList<String> datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> obtenerDatosJSON(String response) {
        ArrayList<String> listado = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("nombre") + " " +
                        jsonArray.getJSONObject(i).getString("precio") + " " +
                        jsonArray.getJSONObject(i).getString("descripcion") + " " +
                        jsonArray.getJSONObject(i).getString("fecha_hora") + " " +
                        jsonArray.getJSONObject(i).getString("direccion") + " " +
                        jsonArray.getJSONObject(i).getString("nombreDeUsuario") + " " ;
                listado.add(texto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return listado;
    }

}
