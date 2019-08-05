package edp.projeto.com.edponline.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import edp.projeto.com.edponline.R;



public class instalacao extends AppCompatActivity {

    private String[] instalacoes = {"Avenida Celso Garcia, 123"};
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instalacao);

        lista = (ListView) findViewById(R.id.lista_instalacoes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, instalacoes){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView = (TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);

                return view;
            }
        };

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(instalacao.this, Menu.class);
                startActivity(intent);
         }
        });


    }
}
