package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdauga = findViewById(R.id.btnAdaugaMagazin);
        Button btnAdaugaAI = findViewById(R.id.btnAdaugaMagazinAI);
        tvRezultat = findViewById(R.id.tvRezultat);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Minimarket minimarket = (Minimarket) result.getData().getSerializableExtra("OBIECT_MINIMARKET");

                        if (minimarket != null) {
                            tvRezultat.setText(minimarket.toString());
                            Toast.makeText(this, "Obiect adăugat cu succes!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            launcher.launch(intent);
        });

        btnAdaugaAI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivityAI.class);
            launcher.launch(intent);
        });
    }
}