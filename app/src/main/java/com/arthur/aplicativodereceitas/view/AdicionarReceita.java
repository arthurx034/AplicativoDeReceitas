package com.arthur.aplicativodereceitas.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.arthur.aplicativodereceitas.R;
import com.arthur.aplicativodereceitas.controller.DBController_Receita;
import com.arthur.aplicativodereceitas.model.Receita;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;

public class AdicionarReceita extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private ImageView imageViewFotoCadastro;

    @Override
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
        Button btnSelecionarFoto = findViewById(R.id.btnSelecionarFoto);
        imageViewFotoCadastro = findViewById(R.id.imageViewFoto);

        DBController_Receita dbController_Receita = new DBController_Receita(this);

        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(
                this,
                R.array.categorias_receita,
                android.R.layout.simple_spinner_item
        );
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        ArrayAdapter<CharSequence> adapterDificuldade = ArrayAdapter.createFromResource(
                this,
                R.array.dificuldade_receita,
                android.R.layout.simple_spinner_item
        );
        adapterDificuldade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDificuldade.setAdapter(adapterDificuldade);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        buttonVoltar.setOnClickListener(v -> finish());

        btnSelecionarFoto.setOnClickListener(v -> {
            String[] options = {"Galeria", "Câmera"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Selecionar Foto");
            builder.setItems(options, (dialog, which) -> {
                if (which == 0) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), PICK_IMAGE_REQUEST);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
            });
            builder.show();
        });

        buttonSalvar.setOnClickListener(v -> {
            String nome = editTextNome.getText().toString().trim();
            String modoPreparo = editTextModoPreparo.getText().toString().trim();
            String tempo = editTextTempo.getText().toString().trim();
            String porcoes = editTextPorcoes.getText().toString().trim();
            String categoria = (String) spinnerCategoria.getSelectedItem();
            String dificuldade = (String) spinnerDificuldade.getSelectedItem();

            if (nome.isEmpty() || modoPreparo.isEmpty() || tempo.isEmpty() || porcoes.isEmpty()
                    || categoria.equals("Selecione uma categoria") || dificuldade.equals("Selecione a dificuldade")) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            int usuarioId = getSharedPreferences("usuarioLogado", MODE_PRIVATE).getInt("usuarioId", -1);
            if (usuarioId == -1) {
                Toast.makeText(this, "Usuário não está logado.", Toast.LENGTH_SHORT).show();
                return;
            }

            Receita novaReceita = new Receita();
            novaReceita.setNome(nome);
            novaReceita.setModoPreparo(modoPreparo);
            novaReceita.setTempoPreparo(Integer.parseInt(tempo));
            novaReceita.setPorcoes(Integer.parseInt(porcoes));
            novaReceita.setCategoria(categoria);
            novaReceita.setDificuldade(dificuldade);
            novaReceita.setUsuarioId(usuarioId);

            if (imageViewFotoCadastro.getTag() != null && imageViewFotoCadastro.getDrawable() != null) {
                imageViewFotoCadastro.setDrawingCacheEnabled(true);
                Bitmap bitmap = imageViewFotoCadastro.getDrawingCache();
                String caminhoImagem = salvarImagemEmArquivo(bitmap, "receita_" + System.currentTimeMillis());
                novaReceita.setCaminhoImagem(caminhoImagem);
            }

            dbController_Receita.adicionarReceita(novaReceita);

            Toast.makeText(this, "Receita salva com sucesso!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra("novaReceita", nome);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private String salvarImagemEmArquivo(Bitmap bitmap, String nomeArquivo) {
        File diretorio = new File(getFilesDir(), "receitas");
        if (!diretorio.exists()) diretorio.mkdirs();

        File arquivoImagem = new File(diretorio, nomeArquivo + ".jpg");
        try (FileOutputStream out = new FileOutputStream(arquivoImagem)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            return arquivoImagem.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
                Uri selectedImage = data.getData();
                imageViewFotoCadastro.setImageURI(selectedImage);
                imageViewFotoCadastro.setTag("imagem_selecionada");
            } else if (requestCode == CAMERA_REQUEST && data != null && data.getExtras() != null) {
                Bitmap foto = (Bitmap) data.getExtras().get("data");
                imageViewFotoCadastro.setImageBitmap(foto);
                imageViewFotoCadastro.setTag("imagem_selecionada");
            }
        }
    }
}
