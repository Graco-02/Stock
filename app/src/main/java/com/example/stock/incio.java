package com.example.stock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.stock.entidades.usuario;

public class incio extends AppCompatActivity {

    private TextView label_saludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incio);
        label_saludo = findViewById(R.id.inicio_saludo);

        label_saludo.setText("Bienvenido SR@. "+usuario.nombres);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_inicio,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id = opcion_menu.getItemId();
        switch (id){
            case R.id.ini_menu_articulos:
                Intent i = new Intent(incio.this,articulos_adm.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }

}