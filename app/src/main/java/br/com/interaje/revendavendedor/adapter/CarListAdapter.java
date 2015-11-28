package br.com.interaje.revendavendedor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.revendavendedor.R;
import br.com.interaje.revendavendedor.model.Car;

/**
 * Created by caio on 27/11/15.
 */
public class CarListAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Car> carList;

    public CarListAdapter(Context c, List<Car> list){
        layoutInflater = layoutInflater.from(c);
        carList = list;

    }

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rview = layoutInflater.inflate(R.layout.car_list_item_layout, parent, false);
        TextView tvName = (TextView) rview.findViewById(R.id.tv_car_name);
        TextView tvYear = (TextView) rview.findViewById(R.id.tv_car_year);
        TextView tvPrice = (TextView) rview.findViewById(R.id.tv_car_price);

        tvName.setText(carList.get(position).getName());
        tvYear.setText(carList.get(position).getYear().toString());
        tvPrice.setText(carList.get(position).getPrice().toString());

        return rview;
    }
}
