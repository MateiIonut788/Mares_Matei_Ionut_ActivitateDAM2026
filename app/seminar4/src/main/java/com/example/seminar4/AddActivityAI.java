package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivityAI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ai);

        EditText etNume = findViewById(R.id.etNumeAI);
        CheckBox cbDeschis = findViewById(R.id.cbDeschisAI);
        RadioGroup rgMarime = findViewById(R.id.rgMarimeAI);
        Spinner spinnerCategorie = findViewById(R.id.spinnerCategorieAI);
        RatingBar ratingBar = findViewById(R.id.ratingBarAI);
        Switch switchLivrare = findViewById(R.id.switchLivrareAI);
        Button btnSalveaza = findViewById(R.id.btnSalveazaAI);

        String[] categorii = {"Alimentar", "Electronice", "Haine", "Mixt"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorii);
        spinnerCategorie.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {
            String nume = etNume.getText().toString();
            boolean esteDeschis = cbDeschis.isChecked();

            int selectedId = rgMarime.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            String marime = (radioButton != null) ? radioButton.getText().toString() : "Nespecificat";

            String categorie = spinnerCategorie.getSelectedItem().toString();
            float rating = ratingBar.getRating();
            boolean areLivrare = switchLivrare.isChecked();

            Minimarket minimarket = new Minimarket(nume, esteDeschis, marime, categorie, rating, areLivrare);

            Intent intent = new Intent();
            intent.putExtra("OBIECT_MINIMARKET", minimarket);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}