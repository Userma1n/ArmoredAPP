package com.example.amoredapp.registro_de_usuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amoredapp.MainActivity;
import com.example.amoredapp.R;
import com.example.amoredapp.login_usuario.login_usuario;

public class Registro extends AppCompatActivity {

    EditText senha_mestre, senha_mestre_C;
    Button botao_registrar;

    SharedPreferences sharedpreference;

    private static final String SHARED_PREF = "mi_pref";
    private static final String KEY_SENHA = "SENHA";
    private static final String KEY_SENHA_C = "SENHA_C";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InicializarVariavel();
        Verificar_senha_usuario();
        botao_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Senha = senha_mestre.getText().toString().trim();
                String Senha_C = senha_mestre_C.getText().toString().trim();

                if (TextUtils.isEmpty(Senha)){
                    Toast.makeText(Registro.this, "Insira a senha!", Toast.LENGTH_SHORT).show();
                }
                else if (Senha.length()<6){
                    Toast.makeText(Registro.this, "Insira uma senha com mais de 6 caracteres", Toast.LENGTH_SHORT).show();

                }
                else if (TextUtils.isEmpty(Senha_C)){
                    Toast.makeText(Registro.this, "Por favor, insira a senha novamente!", Toast.LENGTH_SHORT).show();
                }
                else if (!Senha.equals(Senha_C)){
                    Toast.makeText(Registro.this, "As senhas não são iguais", Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor editor = sharedpreference.edit();
                    editor.putString(KEY_SENHA, Senha);
                    editor.putString(KEY_SENHA_C, Senha_C);
                    editor.apply();


                    Intent intent = new Intent(Registro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
             }
        });
    }

    private void InicializarVariavel(){
        senha_mestre = findViewById(R.id.senha_mestre);
        senha_mestre_C = findViewById(R.id.senha_mestre_C);
        botao_registrar = findViewById(R.id.botao_registrar);
        sharedpreference = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
    }

    private void Verificar_senha_usuario(){
        String minha_senha = sharedpreference.getString(KEY_SENHA, null);
        if (minha_senha!=null){
        Intent intent = new Intent(Registro.this, login_usuario.class);
        startActivity(intent);
        finish();
        }
    }
}