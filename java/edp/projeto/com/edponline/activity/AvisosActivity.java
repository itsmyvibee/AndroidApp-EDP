package edp.projeto.com.edponline.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.config.FirebaseConfiguracao;
import edp.projeto.com.edponline.helper.Animacao;


public class AvisosActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    private DatabaseReference database;
    private ImageView miniLogo;
    private ListView listAvisos;



    private String[] avisos = {
            "\n" + "02/08/2017 - 15:14" + "\n" + "Amanhã nosso funcionário irá fazer a analise do relógio em sua região" + "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Junho já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Previsão de fortes chuvas em sua região, possiveis quedas de energia poderão ocorrer."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Maio já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Abril já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Março já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Fevereiro já está disponivel." + "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Janeiro já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Dezembro já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Novembro já está disponivel." };

    private String[] avisos2 = {
            "\n" + getDateTime() + "\n" + "Foi notificado falta de luz em sua região, já estamos cientes do acontecido e iremos realizar a manutenção o mais rapido possivel." + "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Amanhã nosso funcionário irá fazer a analise do relógio em sua região" + "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Junho já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Previsão de fortes chuvas em sua região, possiveis quedas de energia poderão ocorrer."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Maio já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Abril já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Março já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Fevereiro já está disponivel." + "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Janeiro já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Dezembro já está disponivel."+ "\n",
            "\n" + "02/08/2017 - 15:14" + "\n" + "Sua fatura do mês de Novembro já está disponivel." };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);

        miniLogo = (ImageView) findViewById(R.id.mini_logo_avisos);
        listAvisos = (ListView) findViewById(R.id.list_avisos);

        animacao();

        database = FirebaseConfiguracao.getFirebaseDatabase();
        auth = FirebaseConfiguracao.getFirebaseAutenticacao();

        alertaAviso();
       // salvaDatabase();






    }

    private boolean notificacao() {

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, Menu.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("Falta de energia!!");
        builder.setContentTitle("Falta de energia!!");
        builder.setContentText("Foi notificado falta de energia na sua região.");
        builder.setSmallIcon(R.drawable.icone_notificacao);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources() , R.drawable.icone));

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        nm.notify(R.drawable.ic_picture_as_pdf , n);

        //TODO SettarClick da notificação.

        return true;
    }

    // ------------------------------------------------------------------------ NEW METODOS
  /* private void salvaDatabase(){

        Aviso aviso = new Aviso();

        for(int i = 0; i < 10; i++) {

            aviso.setData(datas[i]);
            aviso.setMensagem(avisos[i]);
            aviso.salvarAviso(Integer.toString(i));
        }
        }
*/

    private void listarAvisos(boolean teste){

        if(teste) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, avisos2) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView textView = (TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                    textView.setTextColor(Color.GRAY);

                    return view;
                }
            };

            listAvisos.setAdapter(adapter);

        }

        else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, avisos) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView textView = (TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                    textView.setTextColor(Color.GRAY);

                    return view;
                }
            };

            listAvisos.setAdapter(adapter);
        }

       }

    // Set Animações dos componentes
    private void animacao(){
        Animacao.AnimacaoAparecer(miniLogo);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void alertaAviso(){

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String teste = dataSnapshot.child("FaltaLuz").child( auth.getCurrentUser().getUid() ).child("Status").getValue().toString();
                if (teste.equals("0")) {
                    listarAvisos(notificacao());
                }
                else{
                    listarAvisos(false);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}
