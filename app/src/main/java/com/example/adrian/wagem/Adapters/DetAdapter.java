package com.example.adrian.wagem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Category;
import com.example.adrian.wagem.R;

import java.text.SimpleDateFormat;

/**
 * Created by Adrian on 12/16/2016.
 */

public class DetAdapter extends BaseAdapter {
    private Context context;
    private Category category;

    public DetAdapter(Context context, Category category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        return category.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return category.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView;
        if (convertView == null) {

            rowView = inflater.inflate(R.layout.list_row, null);
            TextView name = (TextView) rowView.findViewById(R.id.det_name);
            TextView cost = (TextView) rowView.findViewById(R.id.det_cost);
            TextView date = (TextView) rowView.findViewById(R.id.det_date);
            name.setText(category.getItems().get(position).getName());
            cost.setText(String.valueOf(category.getItems().get(position).getCost()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString=dateFormat.format(category.getItems().get(position).getDate());
            date.setText(dateString);

        } else {
            rowView = convertView;
        }


        return convertView;


    }
}
