package br.com.interaje.revendavendedor.activities;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.revendavendedor.R;
import br.com.interaje.revendavendedor.adapter.HomeMenuAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView homeMenuList;
    private List<String> listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList listMenu = new ArrayList<String>();
        listMenu.add("Cadastrar Carro");
        listMenu.add("Lista de Carros");
        listMenu.add("Pedidos");

        homeMenuList = (ListView) findViewById(R.id.home_menu_list);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,R.layout.home_item_layout, R.id.tv_item_list, listMenu);
        homeMenuList.setAdapter(itemsAdapter);

        //Log.i("ADAPTER", "Instancia hmAdapter");
        //HomeMenuAdapter hmAdapter = new HomeMenuAdapter(this);
        //Log.i("ADAPTER","Set home adapter");
        //homeMenuList.setAdapter(hmAdapter);

        homeMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, CarCreateActivity.class));
                        //Toast.makeText(MainActivity.this,"Cadastrar carro", Toast.LENGTH_SHORT).show();
                    break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, CarListActivity.class));
                        //Toast.makeText(MainActivity.this,"Listar Carros", Toast.LENGTH_SHORT).show();
                    break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                        //Toast.makeText(MainActivity.this,"Pedidos", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });


    }
}
