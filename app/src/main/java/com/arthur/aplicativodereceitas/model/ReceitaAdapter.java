package com.arthur.aplicativodereceitas.model;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arthur.aplicativodereceitas.R;

import java.util.ArrayList;
import java.util.List;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder> {

    private List<Receita> lista;
    private List<Receita> listaOriginal;
    private Context context;

    public ReceitaAdapter(List<Receita> lista, Context context) {
        this.lista = new ArrayList<>(lista);
        this.listaOriginal = new ArrayList<>(lista); // guarda lista original
        this.context = context;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_receita, parent, false);
        return new ReceitaViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        Receita receita = lista.get(position);
        holder.nome.setText(receita.getNome());
        holder.categoria.setText(receita.getCategoria());

        if (receita.getFotoPath() != null) {
            holder.imagem.setImageURI(Uri.parse(receita.getFotoPath()));
        } else {
            holder.imagem.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ReceitaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagem;
        TextView nome, categoria;

        public ReceitaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imageViewReceita);
            nome = itemView.findViewById(R.id.textViewNomeReceita);
            categoria = itemView.findViewById(R.id.textViewCategoria);
        }
    }

    // ✅ FUNÇÃO FILTRAR:
    public void filtrar(String texto) {
        lista.clear();
        if (texto.isEmpty()) {
            lista.addAll(listaOriginal);
        } else {
            for (Receita r : listaOriginal) {
                if (r.getNome().toLowerCase().contains(texto.toLowerCase())) {
                    lista.add(r);
                }
            }
        }
        notifyDataSetChanged();
    }
}
