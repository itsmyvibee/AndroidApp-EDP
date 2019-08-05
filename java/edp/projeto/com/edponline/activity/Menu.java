package edp.projeto.com.edponline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.auth.LoginActivity;
import edp.projeto.com.edponline.config.FirebaseConfiguracao;
import edp.projeto.com.edponline.helper.Animacao;

public class Menu extends AppCompatActivity {

    //Buttons
    private ImageView botao_atendimento, botao_semluz, botao_leitura, botao_fatura, botao_alerta, botao_economize, logo, bt_exit;
    private FirebaseAuth autenticacao;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

       setIDs();
       animacao();

         autenticacao = FirebaseConfiguracao.getFirebaseAutenticacao();

        //Botao para Deslogar usuario, EXIT...
         bt_exit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 autenticacao.signOut();
                 Intent intent = new Intent(Menu.this, LoginActivity.class);
                 startActivity(intent);
                 finish();
             }
         });


        //Transito entre activities...
        botao_semluz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, FaltaLuz.class);
                startActivity(intent);
            }
        });

        botao_atendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Atendimento.class);
                startActivity(intent);
            }
        });


        botao_leitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, LeituraActivity.class);
                startActivity(intent);
            }
        });


        botao_fatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, FaturaActivity.class);
                startActivity(intent);
            }
        });

        botao_alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, AvisosActivity.class);
                startActivity(intent);
            }
        });
        //Fim Transito entre activities...


    }



    // ------------------------------------------------------------------------ NEW METODOS
    // Set IDS dos componentes
    private void setIDs(){
        botao_alerta = (ImageView) findViewById(R.id.botao_alerta);
        botao_atendimento = (ImageView) findViewById(R.id.botao_atendimentoID);
        botao_economize = (ImageView) findViewById(R.id.botao_economize);
        botao_semluz = (ImageView) findViewById(R.id.botao_semluzID);
        botao_leitura = (ImageView) findViewById(R.id.botao_leituraID);
        botao_fatura = (ImageView) findViewById(R.id.botao_faturaID);
        logo = (ImageView) findViewById(R.id.logo_menu);
        bt_exit = (ImageView) findViewById(R.id.bt_exit);
    }

    // Set Animações dos componentes
    private void animacao(){
        Animacao.AnimacaoAparecer(botao_alerta);
        Animacao.AnimacaoAparecer(botao_atendimento);
        Animacao.AnimacaoAparecer(botao_economize);
        Animacao.AnimacaoAparecer(botao_fatura);
        Animacao.AnimacaoAparecer(botao_leitura);
        Animacao.AnimacaoAparecer(botao_semluz);
        Animacao.AnimacaoAparecer(logo);
        Animacao.AnimacaoAparecer(bt_exit);
    }



}

