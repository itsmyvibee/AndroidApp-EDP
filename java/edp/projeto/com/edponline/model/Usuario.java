package edp.projeto.com.edponline.model;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import edp.projeto.com.edponline.config.FirebaseConfiguracao;

public class Usuario {

    private String id, nome, email, senha, cpf;

    // --------- Construtor vazio
    public Usuario() {
    }

    // --------- Getters and Setters
    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        //Formatar primeira letra do nome maiuscula
        nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        cpf = tirarFormatacao(cpf);
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    // --------- setAll
    public void setAll(String nome, String email, String senha, String cpf){
        setSenha(senha);
        setEmail(email);
        setNome(nome);
        setCpf(cpf);
    }


    // --------- Método Salvar (Salva os dados no Banco de dados)
    public void salvar(){

        DatabaseReference referenciaFirebase = FirebaseConfiguracao.getFirebaseDatabase();

        Log.i("Log.i: ", "Foi adicionado ao DataBase todas as informações do User: " + getNome() + "      ID: " + getId() );
        referenciaFirebase.child( "Users" ).child( getId() ).setValue( this );


    }

    // --------- Tirar formatação de texto do cpf.
    public String tirarFormatacao(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");

        return texto;
    }



}
