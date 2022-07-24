package com.example.stock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.stock.entidades.usuario;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue ;
    private static final String SERVIDOR_WEB = "https://conarperu.com/pruebas_postman/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        set_logging_usuario();
    }


    protected void set_logging_usuario(){
        LayoutInflater lny_inlf = this.getLayoutInflater();
        View view = lny_inlf.inflate(R.layout.acceso_usuario,null);
        LinearLayout lnc = findViewById(R.id.main_cuerpo);
        lnc.addView(view);

        EditText txt_usuario = view.findViewById(R.id.acceso_usu_nombre);
        EditText txt_usuario_clave = view.findViewById(R.id.acceso_usu_clave);

        Button bt_acceso = view.findViewById(R.id.acceso_usu_bt_acceder);
        bt_acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    get_valdiar_acceso(txt_usuario.getText().toString()
                            ,txt_usuario_clave.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "A OCURRIDO UN ERROR AL INTENTAR ACCEDER",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    protected  void get_valdiar_acceso(String usuario,String clave){
        String url = SERVIDOR_WEB.concat("valida_acceso_usuario.php?usuario="+usuario+"&clave="+clave);

     /*   Toast.makeText(getApplicationContext(), "ACCEDIENDO!! AL SERVIDOR "+url,
                Toast.LENGTH_SHORT).show();*/

        JsonObjectRequest jsonObjRequest =
                new JsonObjectRequest(Request.Method.GET, url,
                        null,
                        new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getInt("id") > 0){
                        com.example.stock.entidades.usuario.nombres = response.getString("nombres");
                        com.example.stock.entidades.usuario.identificacion = response.getString("identificacion");
                        com.example.stock.entidades.usuario.usuario = response.getString("usuario");
                        com.example.stock.entidades.usuario.id = response.getInt("id");
                        com.example.stock.entidades.usuario.estado = response.getInt("estado");
                        com.example.stock.entidades.usuario.clave = response.getString("clave");
                        com.example.stock.entidades.usuario.fecha_alta = response.getString("fecha_alta");
                    }else{
                        Toast.makeText(MainActivity.this,
                                "ERROR AL INTENTAR ACCEDER USUARIO O CLAVE ERRONEOS",
                                Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception w)
                {
                    Toast.makeText(MainActivity.this,"ERROR AL INTENTAR ACCEDER",
                            Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this,w.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        queue.add(jsonObjRequest);

    }



}