package br.com.interaje.revendavendedor.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.revendavendedor.R;

/**
 * Created by caio on 20/11/15.
 */
public class HomeMenuAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<String> menuList;

    public HomeMenuAdapter(Context context, List<String> list){
    //public HomeMenuAdapter(Context context){
        inflater  = LayoutInflater.from(context);
        menuList = list;
        Log.i("ADAPTER","Adapter Construct");
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("ADAPTER","executa getView");
        View rowView = inflater.inflate(R.layout.home_item_layout,parent, false);
        TextView tv1 = (TextView) rowView.findViewById(R.id.tv_item_list);
        tv1.setText(menuList.get(position));
        //tv1.setText("Teste");
        return rowView;
    }
}
