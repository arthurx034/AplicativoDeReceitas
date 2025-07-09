package com.arthur.aplicativodereceitas.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arthur.aplicativodereceitas.R;
import com.arthur.aplicativodereceitas.model.Receita;
import com.arthur.aplicativodereceitas.model.ReceitaAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ReceitaAdapter adapter;
    private List<Receita> listaReceitas;
    private RecyclerView recyclerView;
    private EditText campoBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewReceitas);
        campoBusca = findViewById(R.id.editTextBusca);

        // ✅ Inicializando a lista (vazia ou com dados)
        listaReceitas = new ArrayList<>();
        // Exemplo de receitas fictícias:
        listaReceitas.add(new Receita("Bolo de Chocolate", "Sobremesa", null));
        listaReceitas.add(new Receita("Macarrão Carbonara", "Almoço", null));
        listaReceitas.add(new Receita("Salada Grega", "Entrada", null));

        // Configurando o adapter
        adapter = new ReceitaAdapter(listaReceitas, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Campo de busca
        campoBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
