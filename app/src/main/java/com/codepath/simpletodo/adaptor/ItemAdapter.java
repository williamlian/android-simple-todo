package com.codepath.simpletodo.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        TextView tvUrgency = (TextView) convertView.findViewById(R.id.tvUrgency);

        tvDescription.setText(item.description);
        if(item.urgency != null)
            tvUrgency.setText(item.urgency.toString());

        return convertView;
    }
}
