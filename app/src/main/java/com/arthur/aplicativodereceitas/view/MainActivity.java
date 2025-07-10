package com.arthur.aplicativodereceitas.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arthur.aplicativodereceitas.R;
import com.arthur.aplicativodereceitas.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Receita> listaReceitas;
    private RecyclerView recyclerView;
    private EditText campoBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageButton btnAdicionar = findViewById(R.id.btnAdicionar);

        // Botão de adicionar
        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdicionarReceita.class);
            startActivity(intent);
        });
    }
}
