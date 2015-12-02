package br.com.interaje.revendavendedor.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.interaje.revendavendedor.R;
import br.com.interaje.revendavendedor.database.DatabaseHelper;
import br.com.interaje.revendavendedor.model.Car;

public class CarInfoActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private long carro_id;
    private DatabaseHelper dbHelper;
    private ImageView iv_car_info_photo;
    private TextView tv_car_info_name;
    private TextView tv_car_info_year;
    private TextView tv_car_info_price;
    private Button bt_car_info_add_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        dbHelper = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        carro_id = extras.getLong("carro_id");

        iv_car_info_photo = (ImageView) findViewById(R.id.iv_car_info_photo);
        tv_car_info_name = (TextView) findViewById(R.id.tv_car_info_name);
        tv_car_info_year = (TextView) findViewById(R.id.tv_car_info_year);
        tv_car_info_price = (TextView) findViewById(R.id.tv_car_info_price);
        bt_car_info_add_photo = (Button) findViewById(R.id.bt_car_info_add_photo);

        Car car = getCar(carro_id);

        tv_car_info_name.setText(car.getName());
        tv_car_info_year.setText(car.getYear().toString());
        tv_car_info_price.setText(car.getPrice().toString());

        bt_car_info_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Toast.makeText(CarInfoActivity.this, "Iniciar camera", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });

    }
    @Override
    protected void onActivityResult ( int requestCode , int resultCode , Intent data ){
        Toast.makeText(CarInfoActivity.this, "requestCode: " + Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
        Toast.makeText(CarInfoActivity.this, "resultCode: " + Integer.toString(resultCode), Toast.LENGTH_SHORT).show();

        if( requestCode == 100){
            //Toast.makeText(CarInfoActivity.this, "Resultcode = 100", Toast.LENGTH_SHORT).show();
            if(resultCode == RESULT_OK){
                Toast.makeText(CarInfoActivity.this, "URI: " + data.getData() , Toast.LENGTH_SHORT).show();
                //Uri imgUri = Uri.parse(data.getData());
                //iv_car_info_photo.setImageDrawable(Drawable.createFromPath(data.getDataString()));
                //Bitmap bmg = BitmapFactory.decodeFile(data.getData());
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                //iv_car_info_photo.setImageURI(data.getData());
                iv_car_info_photo.setImageBitmap(photo);
                //iv_car_info_photo.setImageURI();

            }
        }else{
            //Toast.makeText(CarInfoActivity.this, "Resultcode != 100", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.car_info_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(CarInfoActivity.this, "Editar carro", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_delete:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                //Toast.makeText(CarInfoActivity.this, "Apaga registro", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(CarInfoActivity.this);

                AlertDialog show = builder.setMessage("Deseja apagar esse registro?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                if (deleteCar(carro_id)) {
                                    Toast.makeText(CarInfoActivity.this, "Registro apagado", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(CarInfoActivity.this, MainActivity.class));
                                    //Toast.makeText(CarInfoActivity.this, getParent().toString(), Toast.LENGTH_SHORT).show();
                                    /*TODO
                                      Finish na Acitivty anterior
                                    */

                                    finish();
                                } else {
                                    Toast.makeText(CarInfoActivity.this, "Não foi possível apagar o registro", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }

                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .show();


                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public Car getCar(long id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM car WHERE _id =" + id, null);
        cursor.moveToFirst();

        Car car = new Car();
        car.setName(cursor.getString(cursor.getColumnIndex("name")));
        car.setYear(cursor.getInt(cursor.getColumnIndex("year")));
        car.setPrice(cursor.getDouble(cursor.getColumnIndex("year")));

        return car;

    }
    public boolean deleteCar(long id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String where[] = new String[] {Long.toString(carro_id)};
        if(db.delete("car","_id = ?", where) != 0 ){
            return true;
        }else{
            return false;
        }

    }
}
