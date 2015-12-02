package br.com.interaje.revendavendedor.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.revendavendedor.R;
import br.com.interaje.revendavendedor.adapter.CarListAdapter;
import br.com.interaje.revendavendedor.database.DatabaseHelper;
import br.com.interaje.revendavendedor.model.Car;

public class CarListActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView lv_car_list;
    private static CarListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        lv_car_list = (ListView) findViewById(R.id.car_list);
        dbHelper = new DatabaseHelper(this);

        final List<Car> listCars = retornaCar();
        listAdapter = new CarListAdapter(this,listCars);
        lv_car_list.setAdapter(listAdapter);

        lv_car_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarListActivity.this, CarInfoActivity.class);
                intent.putExtra("carro_id", listAdapter.getItemId(position) );
                startActivity(intent);
                /*
                Toast.makeText(CarListActivity.this,
                        "Position: " + Integer.toString(position) +
                        "Id: " + Long.toString(id),
                        Toast.LENGTH_SHORT).show();
                */
                //String item_id = (String) Long.toString();
                //String get_count = (String) Integer.toString(listAdapter.getCount());
                //String get_id = Long.toString(listAdapter.getItemId(position));

                //Toast.makeText(CarListActivity.this, get_id , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<Car> retornaCar(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM car", null);
        cursor.moveToFirst();

        List<Car> cars = new ArrayList<Car>();

        for(int i=0; i < cursor.getCount(); i++){
            Car car = new Car();
            car.setId(cursor.getLong(cursor.getColumnIndex("_id")));
            car.setName(cursor.getString(cursor.getColumnIndex("name")));
            car.setYear(cursor.getInt(cursor.getColumnIndex("year")));
            car.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));

            cars.add(car);
            cursor.moveToNext();
        }
        return cars;

    }

}
