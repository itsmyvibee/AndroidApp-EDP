package edp.projeto.com.edponline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.helper.Animacao;

public class Atendimento extends AppCompatActivity {

    private ImageView botao_email;
    private ImageView botao_chat;
    private ImageView mini_logo_atendimento;
    private ListView list_faq;
    private AlertDialog.Builder dialog;

    private String[] duvidas = {"O que é a opção horária e qual a melhor para mim?", "Quero consultar ou alterar dados pessoais do meu contrato, como prosseguir?", "Quero cancelar o contrato, o que faço?",
    "O que é o serviço funciona e o que está incluído?", "Quero saber mais sobre o serviço de assistência técnica", "Quanto custam os painéis solares e quanto posso poupar?",
    "Posso ter tarifa social no mercado livre?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento);

        setIDs();
        animacao();
        apresentarDialog();

        //TODO AlertDialog ou Toast para falar do chatbot que está online no app...

        //Transito entre activities ...
        botao_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Atendimento.this, Email.class);
                startActivity(intent);
            }
        });

        botao_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamarChatbot();

            }
        });

        //Fim transito entre activities ...

        //LISTA FAQ
        faq();

    }


    // ------------------------------------------------------------------------ NEW METODOS
    private void setIDs(){
        botao_email = (ImageView) findViewById(R.id.botao_email);
        botao_chat = (ImageView) findViewById(R.id.botao_chat);
        mini_logo_atendimento = (ImageView) findViewById(R.id.mini_logo_atendimento);
        list_faq = (ListView) findViewById(R.id.list_faq);
    }

    private void animacao(){
        Animacao.AnimacaoAparecer(botao_email);
        Animacao.AnimacaoAparecer(botao_chat);
        Animacao.AnimacaoAparecer(mini_logo_atendimento);
    }

    private void faq(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, duvidas){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.GRAY);

                return view;
            }
        };

        list_faq.setAdapter(adapter);

    }

    private void apresentarDialog(){

        dialog = new AlertDialog.Builder (Atendimento.this);
        dialog.setTitle("ChatBot disponível!");
        dialog.setMessage("Você já pode utilizar nosso chatbot para tirar dúvidas ou receber atendimento sobre algumas ações.");
        dialog.setPositiveButton("Abrir chat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                chamarChatbot();
            }
        });

        dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.setIcon(R.drawable.icone);
        dialog.create();
        dialog.show();

    }

    private void chamarChatbot(){
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.me/761407437347929"));
        startActivity(browser);
    }

}
