package com.arthur.aplicativodereceitas.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.arthur.aplicativodereceitas.R;

public class AdicionarReceita extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.adicionar_receita);

        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextModoPreparo = findViewById(R.id.editTextModoPreparo);
        EditText editTextTempo = findViewById(R.id.editTextTempo);
        EditText editTextPorcoes = findViewById(R.id.editTextPorcoes);
        Spinner spinnerDificuldade = findViewById(R.id.spinnerDificuldade);
        Button buttonSalvar = findViewById(R.id.buttonSalvar);
    }
}
