package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Minimarket> listaMinimarkete = new ArrayList<>();
    private MinimarketAdapter adapter;
    private ListView lvMinimarkete;
    private int pozitieSelectata = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdauga = findViewById(R.id.btnAdaugaMagazin);
        Button btnAdaugaAI = findViewById(R.id.btnAdaugaMagazinAI);
        Button btnGalerie = findViewById(R.id.btnGalerie);
        lvMinimarkete = findViewById(R.id.lvMinimarkete);
        adapter = new MinimarketAdapter(this, R.layout.item_minimarket, listaMinimarkete);
        lvMinimarkete.setAdapter(adapter);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Minimarket minimarket = result.getData().getParcelableExtra("OBIECT_MINIMARKET");

                        if (minimarket != null) {
                            if (pozitieSelectata != -1) {
                                listaMinimarkete.set(pozitieSelectata, minimarket);
                                pozitieSelectata = -1;
                                Toast.makeText(this, "Obiect modificat cu succes!", Toast.LENGTH_LONG).show();
                            } else {
                                listaMinimarkete.add(minimarket);
                                Toast.makeText(this, "Obiect adaugat cu succes!", Toast.LENGTH_LONG).show();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
        );
        lvMinimarkete.setOnItemClickListener((parent, view, position, id) -> {
        pozitieSelectata = position;
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra("EDIT_MINIMARKET", listaMinimarkete.get(position));
        launcher.launch(intent);
    });

        btnAdauga.setOnClickListener(v -> {
        pozitieSelectata = -1;
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            launcher.launch(intent);
        });

        btnGalerie.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GalerieActivity.class);
            startActivity(intent);
        });

        btnAdaugaAI.setOnClickListener(v -> {
        pozitieSelectata = -1;
            Intent intent = new Intent(MainActivity.this, AddActivityAI.class);
            launcher.launch(intent);
        });
    }
}