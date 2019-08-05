package edp.projeto.com.edponline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.config.FirebaseConfiguracao;

public class FaltaLuzFeedBack extends AppCompatActivity {

    private ImageView bt_enviar;
    private DatabaseReference database;
    private FirebaseAuth autenticacao;
    private EditText infosAdic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falta_luz_feed_back);

        bt_enviar = (ImageView) findViewById(R.id.bt_enviarFeedBack);
        database = FirebaseConfiguracao.getFirebaseDatabase();
        autenticacao = FirebaseConfiguracao.getFirebaseAutenticacao();
        infosAdic = (EditText) findViewById(R.id.infosAdic);

        bt_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            database.child( "FaltaLuz" ).child( autenticacao.getCurrentUser().getUid() ).child("Status").setValue(0);
            database.child( "FaltaLuz" ).child( autenticacao.getCurrentUser().getUid() ).child("Infos adicionais").setValue(infosAdic.getText().toString());
                Toast.makeText(FaltaLuzFeedBack.this, "Seu aviso foi enviado com sucesso.", Toast.LENGTH_SHORT).show();

            infosAdic.setText("");
                finish();
            }
        });

    }
}
