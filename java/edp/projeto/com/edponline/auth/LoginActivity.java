package edp.projeto.com.edponline.auth;

import android.animation.ObjectAnimator;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.activity.instalacao;
import edp.projeto.com.edponline.config.FirebaseConfiguracao;

public class LoginActivity extends AppCompatActivity {

    private ImageView logo;
    private EditText email, senha;
    private ImageView botaoEntrar;
    private TextView criarConta, esqueciSenha;

    private FirebaseAuth autenticacao;
    private DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        email = (EditText) findViewById(R.id.textoEmail);
        senha = (EditText) findViewById(R.id.textoSenha);
        botaoEntrar = (ImageView) findViewById(R.id.botaoEntrar);
        criarConta = (TextView) findViewById(R.id.criarConta);
        esqueciSenha = (TextView) findViewById(R.id.esqueciSenha);
        logo = (ImageView) findViewById(R.id.logo_id);

        logoAnimacao();

       verificaUsuarioLogado(); //Verifica se o usuario já está logado para pular a login activity

       criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setText(""); senha.setText("");
                Intent intent2 = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent2);

            }
        });

       esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, EsqueciSenhaActivity.class);
                startActivity(intent);

            }
        });



        botaoEntrar.setOnClickListener(new View.OnClickListener() { //Faz o login
            @Override
            public void onClick(View view) {
               validarUsuario();
            }
        });

    }





    // ------------------------------------------------------------------------ NEW METODOS
    // -------------- Animação.
    private void logoAnimacao(){
        ObjectAnimator animacao = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f);
        ObjectAnimator animacao2 = ObjectAnimator.ofFloat(botaoEntrar, "alpha", 0f, 1f);
        animacao2.setDuration(2000);
        animacao.setDuration(2000);
        animacao.start();
        animacao2.start();
    }


    // -------------- Verificação usuario logado.
    private void verificaUsuarioLogado(){
        autenticacao = FirebaseConfiguracao.getFirebaseAutenticacao();

            if(autenticacao.getCurrentUser() != null){
            //Usuario já logado, pular tela de login e ir para a instalacao;
            Intent intent = new Intent(LoginActivity.this, instalacao.class);
            startActivity(intent);
            finish();
        }
    }


    // -------------- Validar/Logar usuario.
    private void validarUsuario(){

        autenticacao = FirebaseConfiguracao.getFirebaseAutenticacao();
        dataRef = FirebaseConfiguracao.getFirebaseDatabase();

       if(email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()){
           Toast.makeText(LoginActivity.this, "E-mail ou senha inválidos, tente novamente.", Toast.LENGTH_SHORT).show();
           Log.i("Log.i: ", "Há dados não preenchidos");
       }

       else{
           Toast.makeText(LoginActivity.this, "Conectando... ", Toast.LENGTH_LONG).show();
        autenticacao.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).
                addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //Só pegando o email do banco de dados do usuario que logou, para printar no LOG.
                            dataRef.child("Users").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    Log.i("Log.i: Teste", dataSnapshot.child(autenticacao.getCurrentUser().getUid()).child("email").getValue().toString());


                                    irParaInstalacao();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.i("Log.i: ", "Error ao receber dado do banco.");
                                }
                            });
                        }

                        else {

                            String erroConectar = "";
                            try{
                                throw task.getException();
                            }
                            catch (FirebaseAuthInvalidCredentialsException e){
                                erroConectar = "Senha inválida.";
                            }
                            catch (FirebaseAuthInvalidUserException e){
                                erroConectar = "E-mail inválido ou não cadastrado, tente novamente";
                            }
                            catch (Exception e){
                                e.printStackTrace();
                                erroConectar = "Ocorreu um erro ao conectar, tente mais tarde.";
                            }

                            Toast.makeText(LoginActivity.this, erroConectar, Toast.LENGTH_LONG).show();
                            Log.i("Log.i: ", erroConectar);
                        }

                    }
                });

    }}


    // -------------- Método para mudar a activity para Instalação Após USER Logado.
    private void irParaInstalacao(){

        Intent intent = new Intent(LoginActivity.this, instalacao.class);
        startActivity(intent);
        finish();


    }


}
