package com.example.amoredapp.login_usuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class login_usuario extends AppCompatActivity {

    EditText senha_mestre;
    Button botao_Entrar;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF = "mi_pref";
    private static final String KEY_SENHA = "SENHA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Iniciar_variavel();

        botao_Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Senha = senha_mestre.getText().toString().trim();
                String Senha_confirma = sharedPreferences.getString(KEY_SENHA, null);

                if (Senha.equals("")){
                    Toast.makeText(login_usuario.this, "Por favor, digite sua senha!", Toast.LENGTH_SHORT).show();
                }
                else if (!Senha.equals(Senha_confirma)){
                    Toast.makeText(login_usuario.this, "Senha incorreta, por favor, tente novamente!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(login_usuario.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void Iniciar_variavel(){
       senha_mestre = findViewById(R.id.senha_mestre);
       botao_Entrar = findViewById(R.id.botao_Entrar);
       sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

    }
}