package com.example.adrian.wagem.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Category;
import com.example.adrian.wagem.R;

import java.util.ArrayList;

/**
 * Created by Adrian on 12/12/2016.
 */

public class CatGridAdapter extends BaseAdapter {
    private Context context;
    private Categories categories;

    public CatGridAdapter(Context context, Categories categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return categories.getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView=new View(context);;
        View fakeview;
        if (convertView == null) {
            if (categories.getCategories().get(position).getItems().size() > 0) {


                gridView = inflater.inflate(R.layout.grid_layout, null);
                ImageView catImage = (ImageView) gridView.findViewById(R.id.cat_iv);
                TextView catName = (TextView) gridView.findViewById(R.id.catname_tv);
                TextView catSum = (TextView) gridView.findViewById(R.id.catsum_tv);
                Uri uri = Uri.parse(categories.getCategories().get(position).getIconName());
                catImage.setImageURI(uri);
                catName.setText(categories.getCategories().get(position).getName());
                catSum.setText(String.valueOf(categories.getCategories().get(position).getSum()));

            }else{
                gridView.setVisibility(View.GONE);
            }


        } else {
            gridView = convertView;

        }


        return gridView;

    }
}
