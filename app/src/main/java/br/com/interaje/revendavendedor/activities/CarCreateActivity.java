package br.com.interaje.revendavendedor.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.revendavendedor.R;
import br.com.interaje.revendavendedor.database.DatabaseHelper;

public class CarCreateActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText car_name;
    private EditText car_year;
    private EditText car_price;
    private Button bt_create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_create);

        car_name = (EditText) findViewById(R.id.et_car_create_name);
        car_year = (EditText) findViewById(R.id.et_car_create_year);
        car_price = (EditText) findViewById(R.id.et_car_create_price);

        bt_create = (Button) findViewById(R.id.bt_car_create);

        dbHelper = new DatabaseHelper(this);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               insertCar();
            }
        });

    }

    public void insertCar(){
        Log.i("DB", "Instancia db");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.i("DB", "Db instanciado");

        ContentValues values = new ContentValues();
        values.put("name", car_name.getText().toString());
        values.put("price", car_price.getText().toString());
        values.put("year", car_year.getText().toString());

        long result = db.insert("car", null, values);

        if(result != -1){
            Toast.makeText(CarCreateActivity.this, "Carro cadastrado com sucesso", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CarCreateActivity.this, MainActivity.class));

        }else{
            Toast.makeText(CarCreateActivity.this, "Erro ao inserir carr", Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(CarCreateActivity.this, "Cadastra o carro", Toast.LENGTH_SHORT).show();
    }


}
