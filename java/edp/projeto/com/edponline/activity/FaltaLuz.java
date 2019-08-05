package edp.projeto.com.edponline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import edp.projeto.com.edponline.R;

public class FaltaLuz extends AppCompatActivity {

    private ImageView botao_flt_luz_regiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falta_luz);

        //TODO Aviso estou sem luz, para gerar resultado no banco e mandar notificação no app. Já adicionando no avisos tmb...

        botao_flt_luz_regiao = (ImageView) findViewById(R.id.flt_luz_regiao);

        botao_flt_luz_regiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FaltaLuz.this, FaltaLuzFeedBack.class);
                startActivity(intent);


            }
        });



    }
}
