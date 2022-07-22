package com.example.stock;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue = Volley.newRequestQueue(this);
    private static final String SERVIDOR_WEB = "http://conarperu.com/pruebas_postman/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_logging_usuario();
    }


    protected void set_logging_usuario(){
        LayoutInflater lny_inlf = this.getLayoutInflater();
        View view = lny_inlf.inflate(R.layout.acceso_usuario,null);
        LinearLayout lnc = findViewById(R.id.main_cuerpo);
        lnc.addView(view);

        Button bt_acceso = view.findViewById(R.id.acceso_usu_bt_acceder);
        bt_acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    protected  void get_valdiar_acceso(){
        String url = SERVIDOR_WEB.concat("conexion.php");
        Toast.makeText(getApplicationContext(), "ACCEDIENDO!! AL SERVIDOR "+url,
                Toast.LENGTH_SHORT).show();

        StringRequest
                stringRequest
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(getApplicationContext(), "ACCESO CORRECTO"+url,
                                Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), "ACCESO INCORRECTO"+url,
                                Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(stringRequest);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
      //  getMenuInflater().inflate(R.menu.menu_inicio,menu);
        MenuInflater inflater=getMenuInflater();
        
        inflater.inflate(R.menu.menu_inicio,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id = opcion_menu.getItemId();

       switch (id){
           case R.id.ini_menu_usuarios:
               Intent lnz = new Intent(this,alta_usuario.class);
               startActivity(lnz);
               break;
       }

        return super.onOptionsItemSelected(opcion_menu);
    }

}