package com.example.seminar4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.content.Intent;

public class GalerieActivity extends AppCompatActivity {

    private ArrayList<ItemGalerie> listaIteme = new ArrayList<>();
    private GalerieAdapter adapter;
    private ListView lvGalerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie);

        lvGalerie = findViewById(R.id.lvGalerie);

        listaIteme.add(new ItemGalerie(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_j4UuaXpJksWRn4DNNl8zb4q6PVx1Av8bUHGVE7LQfQ&s=10",
                "Fructe proaspete - Mere rosii",
                "https://ro.wikipedia.org/wiki/M%C4%83r"
        ));
        listaIteme.add(new ItemGalerie(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxuOB_Wzij3-kmoyO4VBubVNPlPDMvS0JM5GsjWO6-ew&s=10",
                "Fructe exotice - Banane",
                "https://ro.wikipedia.org/wiki/Banan%C4%83"
        ));
        listaIteme.add(new ItemGalerie(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBeavFsrw9UurY8EYYUPVJYK54hC48pDq7ss7JPjguQQ&s=10",
                "Panificatie - Paine si chifle proaspete",
                "https://ro.wikipedia.org/wiki/P%C3%A2ine"
        ));
        listaIteme.add(new ItemGalerie(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaIviQx7yWozJosB5dh7VmQyAyXIz4FtCLDdsKnPmP2g&s=10",
                "Lactate - Lapte proaspat de consum",
                "https://ro.wikipedia.org/wiki/Lapte"
        ));
        listaIteme.add(new ItemGalerie(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdX_1BqbEEV86ReDMjrLxNm2qlrlVAxuGQSTW6souOtA&s=10",
                "Bauturi - Cafea prajita",
                "https://ro.wikipedia.org/wiki/Cafea"
        ));

        adapter = new GalerieAdapter(this, R.layout.item_galerie, listaIteme);
        lvGalerie.setAdapter(adapter);

        incarcaImagini();

        lvGalerie.setOnItemClickListener((parent, view, position, id) -> {
            ItemGalerie itemSelectat = listaIteme.get(position);
            Intent intent = new Intent(GalerieActivity.this, WebActivity.class);
            intent.putExtra("URL_PAGINA", itemSelectat.getUrlPagina());
            startActivity(intent);
        });
    }

    private void incarcaImagini() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            for (ItemGalerie item : listaIteme) {
                try {
                    URL url = new URL(item.getUrlImagine());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("User-Agent", "Seminar4App/1.0 (https://ase.ro; student@ase.ro)");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(10000);
                    connection.connect();

                    int status = connection.getResponseCode();
                    if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER) {
                        String newUrl = connection.getHeaderField("Location");
                        connection = (HttpURLConnection) new URL(newUrl).openConnection();
                        connection.setRequestProperty("User-Agent", "Seminar4App/1.0 (https://ase.ro; student@ase.ro)");
                        connection.connect();
                    }

                    InputStream input = connection.getInputStream();
                    java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
                    byte[] data = new byte[4096];
                    int nRead;
                    while ((nRead = input.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    byte[] imageBytes = buffer.toByteArray();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    if (bitmap != null) {
                        item.setImagine(bitmap);
                        runOnUiThread(() -> adapter.notifyDataSetChanged());
                    } else {
                        android.util.Log.e("GALERIE", "Nu s-a putut decoda: " + item.getUrlImagine());
                    }
                } catch (Exception e) {
                    android.util.Log.e("GALERIE", "Eroare la descarcare: " + e.getMessage());
                }
            }
        });
    }
}