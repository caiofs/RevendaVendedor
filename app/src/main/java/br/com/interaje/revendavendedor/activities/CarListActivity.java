package br.com.interaje.revendavendedor.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private ListView car_list;
    private CarListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        car_list = (ListView) findViewById(R.id.car_list);
        dbHelper = new DatabaseHelper(this);

        List<Car> listCars = retornaCar();
        listAdapter = new CarListAdapter(this,listCars);
        car_list.setAdapter(listAdapter);

        //Toast.makeText(CarListActivity.this, listCars.get(0).getName(), Toast.LENGTH_SHORT).show();
    }

    public List<Car> retornaCar(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM car", null);
        cursor.moveToFirst();

        List<Car> cars = new ArrayList<Car>();

        for(int i=0; i < cursor.getCount(); i++){
            Car car = new Car();
            car.setName(cursor.getString(cursor.getColumnIndex("name")));
            car.setYear(cursor.getInt(cursor.getColumnIndex("year")));
            car.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));

            cars.add(car);
            cursor.moveToNext();
        }
        return cars;

    }

}
