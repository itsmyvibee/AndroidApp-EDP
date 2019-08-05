package edp.projeto.com.edponline.config;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfiguracao {

    private static DatabaseReference referenciaFirebase; //Banco de dadps
    private static FirebaseAuth autenticacao; // Auth


    // -------- Método para receber a referencia do Banco de dados
    public static DatabaseReference getFirebaseDatabase(){

        if (referenciaFirebase == null){

            referenciaFirebase = FirebaseDatabase.getInstance().getReference();

        }

        return referenciaFirebase;

    }

    // -------- Método para receber a referencia do Auth
    public static FirebaseAuth getFirebaseAutenticacao(){

        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }


}



