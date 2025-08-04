package com.arthur.aplicativodereceitas.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.arthur.aplicativodereceitas.R;
import com.arthur.aplicativodereceitas.controller.DBController_Receita;
import com.arthur.aplicativodereceitas.model.Receita;

public class AdicionarReceita extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.adicionar_receita);

        EditText editTextNome = findViewById(R.id.editTextNome);
        EditText editTextModoPreparo = findViewById(R.id.editTextModoPreparo);
        EditText editTextTempo = findViewById(R.id.editTextTempo);
        EditText editTextPorcoes = findViewById(R.id.editTextPorcoes);
        ImageButton buttonVoltar = findViewById(R.id.buttonVoltar);
        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);
        Spinner spinnerDificuldade = findViewById(R.id.spinnerDificuldade);
        Button buttonSalvar = findViewById(R.id.buttonSalvar);

        DBController_Receita dbController_Receita = new DBController_Receita(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonVoltar.setOnClickListener(v -> {
            finish();
        });

        // Adicione aqui a lógica para salvar a receita
        buttonSalvar.setOnClickListener(v -> {
            String nome = editTextNome.getText().toString().trim();
            String modoPreparo = editTextModoPreparo.getText().toString().trim();
            String tempo = editTextTempo.getText().toString().trim();
            String porcoes = editTextPorcoes.getText().toString().trim();
            String categoria = (String) spinnerCategoria.getSelectedItem();
            String dificuldade = (String) spinnerDificuldade.getSelectedItem();

            if (nome.isEmpty() || modoPreparo.isEmpty() || tempo.isEmpty() || porcoes.isEmpty() || categoria.isEmpty() || dificuldade.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pega o ID do usuário logado do SharedPreferences
            int usuarioId = getSharedPreferences("usuarioLogado", MODE_PRIVATE).getInt("usuarioId", -1);
            if (usuarioId == -1) {
                Toast.makeText(this, "Usuário não está logado.", Toast.LENGTH_SHORT).show();
                return;
            }

            Receita novaReceita = new Receita();

            dbController_Receita.adicionarReceita(novaReceita);

            Toast.makeText(this, "Receita salva com sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra("novaReceita", nome);
            setResult(RESULT_OK, intent);

            finish();
        });
    }
}
