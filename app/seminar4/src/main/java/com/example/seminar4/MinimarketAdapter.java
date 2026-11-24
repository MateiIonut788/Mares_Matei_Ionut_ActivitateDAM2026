package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class MinimarketAdapter extends ArrayAdapter<Minimarket> {

    private Context context;
    private int resource;

    public MinimarketAdapter(@NonNull Context context, int resource, @NonNull List<Minimarket> objects) {
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

        Minimarket item = getItem(position);

        if (item != null) {
            TextView tvNume = convertView.findViewById(R.id.tvNume);
            TextView tvCategorieMarime = convertView.findViewById(R.id.tvCategorieMarime);
            TextView tvRating = convertView.findViewById(R.id.tvRating);
            TextView tvStatus = convertView.findViewById(R.id.tvStatus);

            tvNume.setText(item.getNume());
            tvCategorieMarime.setText(item.getCategorie() + " | Marime: " + item.getMarime());
            tvRating.setText("Rating: " + item.getRating() + " stele");
            tvStatus.setText((item.isEsteDeschis() ? "Deschis" : "Inchis") + " | Livrare: " + (item.isAreLivrare() ? "Da" : "Nu"));
        }

        return convertView;
    }
}