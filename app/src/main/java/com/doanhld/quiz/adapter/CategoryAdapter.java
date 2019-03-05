package com.doanhld.quiz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.doanhld.quiz.R;
import com.doanhld.quiz.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private int resource;
    private List<Category> catelogyModels;

    public CategoryAdapter(@NonNull Context context, int resource, ArrayList<Category> catelogyModels1) {
        super(context, resource, catelogyModels1);
        this.context = context;
        this.resource = resource;
        this.catelogyModels = catelogyModels1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_dis = convertView.findViewById(R.id.txt_dis);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Category catelogyModel = catelogyModels.get(position);
        viewHolder.txt_dis.setText(catelogyModel.getTitleCatelogy());
        return convertView;
    }

    @Override
    public int getCount() {
        return null != catelogyModels ? catelogyModels.size() : 0;
    }

    public class ViewHolder {
        TextView txt_dis;
    }
}
