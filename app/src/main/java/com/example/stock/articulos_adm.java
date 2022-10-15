package com.example.stock;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.stock.entidades.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class articulos_adm extends AppCompatActivity {

    private ArrayList<articulo> lista_articulos;
    RequestQueue queue ;
    private static final String SERVIDOR_WEB = "https://conarperu.com/pruebas_postman/";
    private LinearLayout layout_lista_articulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_adm);

        //implemento una nueva instancia de la lista
        lista_articulos= new ArrayList<articulo>();

        //implemento la el contenedor del scrollview
        layout_lista_articulos = findViewById(R.id.adm_articulos_lista);
        queue = Volley.newRequestQueue(this);// incializo la instancia volley
        get_cargar_lista_articulos();//cargo la lista de articulos


    }


    protected  void set_Listar_articulos(){
        LayoutInflater inflater = getLayoutInflater();

        for(int pos=0;pos<lista_articulos.size();pos++){
            View v = inflater.inflate(R.layout.articulo_formato_lista,null);
            TextView txt_codigo = v.findViewById(R.id.art_formulario_lista_codigo);
            TextView txt_descripcion = v.findViewById(R.id.art_formulario_lista_descripcion);

            txt_codigo.setText(lista_articulos.get(pos).getCodido());
            txt_descripcion.setText(lista_articulos.get(pos).getDescripcion());

            layout_lista_articulos.addView(v);
        }

    }


    protected void get_cargar_lista_articulos(){

        String servicio = SERVIDOR_WEB.concat("lista_articulos.php");

        JsonArrayRequest myJsonArray =
                new JsonArrayRequest(
                        Request.Method.GET
                        , servicio
                        , null
                        , new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int pos=0;pos<response.length();pos++) {
                            try {
                                JSONObject myObjArticulo = response.getJSONObject(pos);
                                articulo myArticulo = new articulo();
                                myArticulo.setCodido(myObjArticulo.getString("codigo"));
                                myArticulo.setDescripcion(myObjArticulo.getString("art_des"));
                                myArticulo.setCantidad(myObjArticulo.getInt("cantidad"));
                                myArticulo.setMin_stock(myObjArticulo.getInt("cantidad_min"));
                                myArticulo.setPrecio(myObjArticulo.getDouble("precio"));
                                myArticulo.setCategoria(myObjArticulo.getString("categoria"));
                                myArticulo.setCategoria_descripcion(myObjArticulo.getString("cat_desc"));

                             //   System.out.println("articulo "+myArticulo.getDescripcion());
                                lista_articulos.add(myArticulo);

                                LayoutInflater inflater = getLayoutInflater();
                                View v = inflater.inflate(R.layout.articulo_formato_lista,null);
                                TextView txt_codigo = v.findViewById(R.id.art_formulario_lista_codigo);
                                TextView txt_descripcion = v.findViewById(R.id.art_formulario_lista_descripcion);

                                txt_codigo.setText(myArticulo.getCodido());
                                txt_descripcion.setText(myArticulo.getDescripcion());
                                layout_lista_articulos.addView(v);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(articulos_adm.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(myJsonArray);
    }

}