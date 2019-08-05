package edp.projeto.com.edponline.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.activity.CameraRecogActivity;
import edp.projeto.com.edponline.helper.Animacao;

public class LeituraActivity extends AppCompatActivity {

    private ImageView miniLogo;
    private ListView list_consumo;
    private TextView fazerleitura;
    private String[] consumo = {"Agosto: 752 kWh", "Julho: 654 kWh", "Junho: 668 kWh", "Maio: 712 kWh", "Abril: 589 kWh", "Março: 721 kWh", "Fevereiro: 701 kWh", "Janeiro: 788 kWh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);

        miniLogo = (ImageView) findViewById(R.id.mini_logo_leitura);
        list_consumo = (ListView) findViewById(R.id.list_consumo);
        fazerleitura = (TextView) findViewById(R.id.fazer_leitura);

        animacao();

        listagem_consumo();

        fazerleitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LeituraActivity.this, CameraRecogActivity.class);
                startActivity(intent);

            }
        });


    }


    // ------------------------------------------------------------------------ NEW METODOS
     // Set Animações dos componentes
    private void animacao(){
     Animacao.AnimacaoAparecer(miniLogo);
    }

    private void listagem_consumo(){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, consumo){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.GRAY);

                return view;
            }
        };

        list_consumo.setAdapter(adapter);

    }

}
