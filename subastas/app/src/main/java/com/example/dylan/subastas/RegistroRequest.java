package com.example.dylan.subastas;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {
    private static final String ruta = "https://sellfasterrr.000webhostapp.com/registroP.php";
    private Map<String,String> parametros;
    public RegistroRequest(String usuario, String clave, String nombre, String apellido, String email, String telefono, Response.Listener<String> listener)
    {
        super(Request.Method.POST, ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("usuario", usuario+"");
        parametros.put("clave",  clave+"");
        parametros.put("nombre",  nombre+"");
        parametros.put("apellido",  apellido+"");
        parametros.put("email",  email+"");
        parametros.put("telefono",  telefono+"");

    }

    @Override
    protected Map<String, String> getParams()  {
        return parametros;
    }
}
