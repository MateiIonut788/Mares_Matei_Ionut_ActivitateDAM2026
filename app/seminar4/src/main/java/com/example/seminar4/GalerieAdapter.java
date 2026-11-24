package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class GalerieAdapter extends ArrayAdapter<ItemGalerie> {

    private Context context;
    private int resource;

    public GalerieAdapter(@NonNull Context context, int resource, @NonNull List<ItemGalerie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        ItemGalerie item = getItem(position);

        if (item != null) {
            ImageView ivImagine = convertView.findViewById(R.id.ivImagine);
            TextView tvDescriere = convertView.findViewById(R.id.tvDescriere);

            tvDescriere.setText(item.getDescriere());

            if (item.getImagine() != null) {
                ivImagine.setImageBitmap(item.getImagine());
            } else {
                ivImagine.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        }

        return convertView;
    }
}