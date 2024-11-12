package com.example.amoredapp.Fragmentos;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.amoredapp.Adaptador.Adaptador_senha;
import com.example.amoredapp.Bancodedados.BDHelper;
import com.example.amoredapp.Bancodedados.Constants;
import com.example.amoredapp.Opcaosenha.Adicionar_atualizar_registro;
import com.example.amoredapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class F_Todas extends Fragment {
    BDHelper helper;
    RecyclerView mostrar_registros;
    FloatingActionButton Fab_Adicionar_senha;

    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f__todas, container, false);
        mostrar_registros = view.findViewById(R.id.mostrar_registros);
        Fab_Adicionar_senha = view.findViewById(R.id.Fab_Adicionar_senha);
        helper= new BDHelper(getActivity());
        carregarRegistros();

        dialog = new Dialog(getActivity());
        Fab_Adicionar_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Adicionar_atualizar_registro.class);
                intent.putExtra("MOD_EDICAO", false);
                startActivity(intent);
            }
        });


        return view;
    }

    private void carregarRegistros() {
        Adaptador_senha adaptadorSenha = new Adaptador_senha(getActivity(), helper.Obterregistros(
                Constants.C_TITULO + " ASC"));
        mostrar_registros.setAdapter(adaptadorSenha);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragmento_todos, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Numero_Registros){
            visualizar_registros();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    private void visualizar_registros(){
        TextView Total;
        Button totalcerto;

        dialog.setContentView(R.layout.quadro_dialogo_registros);

        Total = dialog.findViewById(R.id.Total);

        totalcerto = dialog.findViewById(R.id.totalcerto);

        int total = helper.NumerosdoRegistro();

        String total_string = String.valueOf(total);

        Total.setText(total_string);

        totalcerto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });


    dialog.show();
    dialog.setCancelable(false);

    }

}