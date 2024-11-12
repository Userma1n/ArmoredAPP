package com.example.amoredapp.Adaptador;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amoredapp.Bancodedados.BDHelper;
import com.example.amoredapp.Detahle.detalhe_registro;
import com.example.amoredapp.MainActivity;
import com.example.amoredapp.Modelo.Senha;
import com.example.amoredapp.Opcaosenha.Adicionar_atualizar_registro;
import com.example.amoredapp.R;

import java.util.ArrayList;

public class Adaptador_senha extends RecyclerView.Adapter<Adaptador_senha.HolderPassword>{

    private Context context;
    private ArrayList<Senha> Senhalist;
    Dialog dialog;

    BDHelper bdHelper;

    public Adaptador_senha(Context context, ArrayList<Senha> senhalist) {
        this.context = context;
        Senhalist = senhalist;
        dialog = new Dialog(context);
        bdHelper = new BDHelper(context);
    }

    @NonNull
    @Override
    public HolderPassword onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_senha, parent, false);
        return new HolderPassword(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPassword holder, @SuppressLint("RecyclerView") int position) {
        Senha mode_senha = Senhalist.get(position);
        String id = mode_senha.getId();
        String titulo = mode_senha.getTitulo();
        String conta = mode_senha.getConta();
        String usuario = mode_senha.getUsuario();
        String senha = mode_senha.getSenha();
        String site = mode_senha.getSite();
        String nota = mode_senha.getNota();
        String tempo_registro = mode_senha.getT_registro();
        String tempo_atualziacao = mode_senha.getT_atualizacao();

        holder.item_titulo.setText(titulo);
        holder.item_conta.setText(conta);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, detalhe_registro.class);

                intent.putExtra("Id_registro", id);
                context.startActivity(intent);
            }
        });

        holder.b_mais_opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opcao_editar_eliminar(
                        ""+ position,
                        ""+ id,
                        ""+ titulo,
                        ""+ conta,
                        ""+ usuario,
                        ""+ senha,
                        ""+ site,
                        ""+ nota
                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return Senhalist.size();
    }

    class HolderPassword extends RecyclerView.ViewHolder{


        TextView item_titulo, item_conta, item_usuario, item_senha, item_site, item_nota;

        ImageButton b_mais_opcoes;

        public HolderPassword(@NonNull View itemView) {
            super(itemView);

        item_titulo = itemView.findViewById(R.id.item_titulo);
        item_conta = itemView.findViewById(R.id.item_conta);
        item_usuario = itemView.findViewById(R.id.item_usuario);
        item_senha = itemView.findViewById(R.id.item_senha);
        item_site = itemView.findViewById(R.id.item_site);
        item_nota = itemView.findViewById(R.id.item_nota);

        b_mais_opcoes = itemView.findViewById(R.id.b_mais_opcoes);

        }
    }

    private void Opcao_editar_eliminar(String position, String id, String titulo, String conta, String usuario,
                                       String senha, String site, String nota){
        Button Btn_Editar_Registro, Btn_Eliminar_Registro;

        dialog.setContentView(R.layout.dialogo_editar_eliminar_registro);

        Btn_Editar_Registro = dialog.findViewById(R.id.Btn_Editar_Registro);
        Btn_Eliminar_Registro = dialog.findViewById(R.id.Btn_Eliminar_Registro);

        Btn_Editar_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Adicionar_atualizar_registro.class);
                intent.putExtra("POSITION", position);
                intent.putExtra("ID", id);
                intent.putExtra("TITULO", titulo);
                intent.putExtra("CONTA", conta);
                intent.putExtra("USUARIO", usuario);
                intent.putExtra("SENHA", senha);
                intent.putExtra("SITE", site);
                intent.putExtra("NOTA", nota);
                intent.putExtra("MOD_EDICAO", true);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        Btn_Eliminar_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdHelper.Exlucluir_registro(id);
                Intent intent = new Intent(context, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(context, "Excluído com Sucesso!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });

        dialog.show();
        dialog.setCancelable(true);
    }

}


