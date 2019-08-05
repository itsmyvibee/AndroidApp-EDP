package edp.projeto.com.edponline.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.config.FirebaseConfiguracao;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private EditText email;
    private FirebaseAuth autenticacao;
    private TextView resposta;
    private ImageView botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        email = (EditText) findViewById(R.id.email_recup);
        botao = (ImageView) findViewById(R.id.botao_prosseguirRecuperacao);
        resposta = (TextView) findViewById(R.id.resposta_eas);

        autenticacao = FirebaseConfiguracao.getFirebaseAutenticacao();


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(email.getText().toString().isEmpty()){ resposta.setText("Por favor, preencha corretamente o e-mail");}
                else{

                  Toast.makeText(EsqueciSenhaActivity.this, "Carregando..." ,
                          Toast.LENGTH_SHORT).show();

              autenticacao.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(EsqueciSenhaActivity.this, "Foi enviado uma mensagem em seu e-mail para dar continuidade ao processo de recuperação de senha." ,
                                     Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(EsqueciSenhaActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{ resposta.setText("Não foi possivel continuar com a recuperação. O email é inválido ou ocorreu um erro no processo."); }

                    }
                });}

            }
        });

    }


}
