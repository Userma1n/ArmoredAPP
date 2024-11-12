package com.example.amoredapp.Opcaosenha;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amoredapp.Bancodedados.BDHelper;
import com.example.amoredapp.MainActivity;
import com.example.amoredapp.R;

public class Adicionar_atualizar_registro extends AppCompatActivity {

    EditText add_titulo,add_conta,add_usuario,add_senha,add_site,add_nota;

    String id, titulo, conta, usuario, senha, site, nota, tempo_registro;

    private boolean MOD_EDICAO = false;

    private BDHelper bdHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_senha);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");
        Iniciar_variaveis();
        obter_informacoes_adptador();
    }

    private void Iniciar_variaveis(){
        add_titulo = findViewById(R.id.add_titulo);
        add_conta = findViewById(R.id.add_conta);
        add_usuario = findViewById(R.id.add_usuario);
        add_senha = findViewById(R.id.add_senha);
        add_site = findViewById(R.id.add_site);
        add_nota = findViewById(R.id.add_nota);

        bdHelper = new BDHelper(this);


    }

    private void obter_informacoes_adptador(){
        Intent intent = getIntent();
        MOD_EDICAO = intent.getBooleanExtra("MOD_EDICAO", false);

        if (MOD_EDICAO){
            id = intent.getStringExtra("ID");
            titulo = intent.getStringExtra("TITULO");
            conta = intent.getStringExtra("CONTA");
            usuario = intent.getStringExtra("USUARIO");
            senha = intent.getStringExtra("SENHA");
            site = intent.getStringExtra("SITE");
            nota = intent.getStringExtra("NOTA");

           add_titulo.setText(titulo);
           add_conta.setText(conta);
           add_usuario.setText(usuario);
           add_senha.setText(senha);
           add_site.setText(site);
           add_nota.setText(nota);


        }
        else{

        }
    }

    private  void SALVAR_E_ATUALIZAR_SENHA(){

        titulo = add_titulo.getText().toString().trim();
        conta = add_conta.getText().toString().trim();
        usuario = add_usuario.getText().toString().trim();
        senha = add_senha.getText().toString().trim();
        site = add_site.getText().toString().trim();
        nota = add_nota.getText().toString().trim();


        if (MOD_EDICAO){

            String Tempo_Atual = ""+ System.currentTimeMillis();
            bdHelper.atualziarregistro(
                    ""+ id,
                    ""+ titulo,
                    ""+ conta,
                    ""+ usuario,
                    ""+ senha,
                    ""+ site,
                    ""+ nota,
                    ""+ tempo_registro,
                    ""+ Tempo_Atual
            );

            Toast.makeText(this, "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Adicionar_atualizar_registro.this, MainActivity.class));
            finish();

        }else {

            if (!titulo.equals("")){

                String tempo = ""+System.currentTimeMillis();
                long id = bdHelper.inserirregistro(
                        "" + titulo,
                        "" + conta,
                        "" + usuario,
                        ""+ senha,
                        ""+ site,
                        ""+ nota,
                        "" + tempo,
                        "" + tempo
                );

                Toast.makeText(this, "Salvo com Sucesso!: "+id, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Adicionar_atualizar_registro.this, MainActivity.class));
                finish();
            }
            else {
                add_titulo.setError("Campo obrigatório!");
                add_titulo.setFocusable(true);
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.salvar_senha){
            SALVAR_E_ATUALIZAR_SENHA();
        }
        return super.onOptionsItemSelected(item);
    }
}


