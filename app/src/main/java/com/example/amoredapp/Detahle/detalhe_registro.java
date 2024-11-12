package com.example.amoredapp.Detahle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amoredapp.Bancodedados.BDHelper;
import com.example.amoredapp.Bancodedados.Constants;
import com.example.amoredapp.R;

public class detalhe_registro extends AppCompatActivity {

    TextView mostrar_titulo, mostrar_conta, mostrar_usuario, mostrar_senha,
            mostrar_site, mostrar_nota;

    String id_registro;

    BDHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        id_registro = intent.getStringExtra("Id_registro");
        Toast.makeText(this, "ID"+id_registro, Toast.LENGTH_SHORT).show();
        iniciar_variaveis();
        helper = new BDHelper(this);
        ActionBar actionbar = getSupportActionBar();
        Mostrarinformacaodoregistro();

        String titulo_registro = mostrar_titulo.getText().toString();
        actionbar.setTitle(titulo_registro);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);

    }
    private void iniciar_variaveis(){
        mostrar_titulo = findViewById(R.id.mostrar_titulo);
        mostrar_conta = findViewById(R.id.mostrar_conta);
        mostrar_usuario = findViewById(R.id.mostrar_usuario);
        mostrar_senha = findViewById(R.id.mostrar_senha);
        mostrar_nota = findViewById(R.id.mostrar_nota);
        mostrar_site = findViewById(R.id.mostrar_site);

    }




        private void Mostrarinformacaodoregistro(){
            String consulta = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.C_ID + " =\"" + id_registro+"\"";

            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery(consulta, null);

            if (cursor.moveToFirst()){
                do {
                    @SuppressLint("Range") String id = ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID));
                    @SuppressLint("Range") String titulo = ""+cursor.getString(cursor.getColumnIndex(Constants.C_TITULO));
                    @SuppressLint("Range") String cuenta = ""+cursor.getString(cursor.getColumnIndex(Constants.C_CONTA));
                    @SuppressLint("Range") String nombre_usuario = ""+cursor.getString(cursor.getColumnIndex(Constants.C_USUARIO));
                    @SuppressLint("Range") String password = ""+cursor.getString(cursor.getColumnIndex(Constants.C_SENHA));
                    @SuppressLint("Range") String sitio_web = ""+cursor.getString(cursor.getColumnIndex(Constants.C_SITE));
                    @SuppressLint("Range") String nota = ""+cursor.getString(cursor.getColumnIndex(Constants.C_NOTA));


                    mostrar_titulo.setText(titulo);
                    mostrar_conta.setText(cuenta);
                    mostrar_usuario.setText(nombre_usuario);
                    mostrar_senha.setText(password);
                    mostrar_site.setText(sitio_web);
                    mostrar_nota.setText(nota);



                }while (cursor.moveToNext());

            }

            db.close();
        }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}

