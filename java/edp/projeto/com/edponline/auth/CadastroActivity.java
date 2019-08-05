package edp.projeto.com.edponline.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.config.FirebaseConfiguracao;
import edp.projeto.com.edponline.helper.CpfValidator;
import edp.projeto.com.edponline.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText textoNome, textoEmail, textoSenha, textoCpf, textoConfirmaSenha;
    private ImageView botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textoNome = (EditText) findViewById(R.id.textoCadastroNome);
        textoEmail = (EditText) findViewById(R.id.textoCadastroEmail);
        textoSenha = (EditText) findViewById(R.id.textoCadastroSenha);
        textoCpf = (EditText) findViewById(R.id.textoCadastroCPF);
        textoConfirmaSenha = (EditText) findViewById(R.id.textoCadastroConfirmaSenha);
        botaoCadastrar = (ImageView) findViewById(R.id.botaoCadastrar);


        // -------- Formatador de texto
        formataTexto();

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                //Armazena todos os dados no user
                usuario.setAll(textoNome.getText().toString(), textoEmail.getText().toString(), textoSenha.getText().toString(), textoCpf.getText().toString());

                //Verificando todos os dados para poder fazer o cadastro.
                if(usuario.getNome().equals("") ||  (usuario.getCpf().equals(""))) {

                    Toast.makeText(CadastroActivity.this, "Há dados não preenchidos.", Toast.LENGTH_LONG).show();}

                else {

                    //Verificando se o usuario digitou as senhas iguais.
                    if (usuario.getSenha().equals(textoConfirmaSenha.getText().toString())) {

                        //Verificando CPF...(CLASSE VALIDATOR)
                        CpfValidator cpfValidator = new CpfValidator();
                        if (cpfValidator.isCPF(usuario.getCpf())) {

                            cadastrarUsuario();}

                        else {
                            Toast.makeText(CadastroActivity.this, "CPF Inválido.", Toast.LENGTH_LONG).show();} }

                    else {
                        Toast.makeText(CadastroActivity.this, "As senhas digitadas devem ser iguais, tente novamente.", Toast.LENGTH_LONG).show();} } } });

    }


    // ------------------------------------------------------------------------ NEW METODOS

    // -------- Método para formatar o texto do CPF
    public void formataTexto(){
        //O construtor dessa classe vai ser o formato que queremos sendo N - numbes  L - letters
        //Criamos um formato de mascara
        SimpleMaskFormatter simpleMaskCpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskTextCpf = new MaskTextWatcher(textoCpf, simpleMaskCpf);
        textoCpf.addTextChangedListener(maskTextCpf);

    }


    // -------- Cadastrar usuario
    public void cadastrarUsuario(){

        autenticacao = FirebaseConfiguracao.getFirebaseAutenticacao();

        //Criar conta com email e senha
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(CadastroActivity.this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Esse método verifica se o cadastro foi um sucesso ou não.
                if(task.isSuccessful()){
                    Log.i("Log.i", "Sucesso ao cadastrar usuario");

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid()); //Adicionando o id ao Model Usuario.
                    usuario.salvar(); // Método da classe usuario que salva no banco de dados.

                    autenticacao.signOut(); //Desloga o usuario.
                    voltarLogin();
                }
                else { //Tratamento de excessoes

                    String erroExcessao = "";
                    try{
                        throw task.getException(); //Recupero a excessao
                    }
                    catch (FirebaseAuthWeakPasswordException e){
                        erroExcessao = "Digite uma senha mais forte.";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        erroExcessao = "O e-mail selecionado já está em uso, tente outro.";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcessao = "O e-mail digitado é invalido, digite um novo e-mail.";
                    }
                    catch (Exception e){
                        erroExcessao = "Erro ao efetuar cadastro.";
                        e.printStackTrace();
                    }

                    Log.i("Log.i: ", erroExcessao);
                    Toast.makeText(CadastroActivity.this, erroExcessao, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    // -------- Voltar para login
    public void voltarLogin(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }



}
