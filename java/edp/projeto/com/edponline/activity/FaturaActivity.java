package edp.projeto.com.edponline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.helper.Animacao;
import edp.projeto.com.edponline.helper.FaturaAdapter;
import edp.projeto.com.edponline.model.Fatura;


public class FaturaActivity extends AppCompatActivity {

    private ImageView miniLogo;
    private ListView list_suasfaturas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura);

        setIDs();
        animacao();
        listagem();


        list_suasfaturas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){

                    case 0:
                        Toast.makeText(FaturaActivity.this, "Sua fatura está em aberto, pressione por alguns segundos sobre ela para ter acesso a seu boleto.", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(FaturaActivity.this, "Fatura vencida, pressione por alguns segundos sobre ela para ter acesso a sua 2ºVia.", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(FaturaActivity.this, "Essa fatura já foi paga com sucesso.", Toast.LENGTH_SHORT).show();
                        break;

                }


            }});



        list_suasfaturas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                    WebView webView=new WebView(FaturaActivity.this);
                    webView.getSettings().setJavaScriptEnabled(true);

        /*Necessário sobescrever o método shouldOverrideUrlLoading retornando false
        * pois por padrão o WebView irá passar para o Activity Manager escolher qual
        * a melhor opção para abrir a URL, dessa forma o próprio WebView irá abrir a URL */
                    webView.setWebViewClient( new Callback() );

        /*Coloque aqui o LINK para o PDF, mantido um link apenas para teste*/
                    String pdfURL = "https://www.boletobancario.com/boletofacil/img/boleto-facil-exemplo.pdf";

        /*Carrega a URL utilizando visualizador do Google Docs */
                    webView.loadUrl(
                            "http://docs.google.com/gview?embedded=true&url=" + pdfURL);

                    setContentView(webView);

            return false;
            }

            });

    }

    // ------------------------------------------------------------------------ NEW METODOS
    // Set IDS dos componentes
    private void setIDs(){
        miniLogo = (ImageView) findViewById(R.id.mini_logo_fatura);
        list_suasfaturas = (ListView) findViewById(R.id.list_suasfaturas);

    }

    // Set Animações dos componentes
    private void animacao(){
        Animacao.AnimacaoAparecer(miniLogo);

    }

    private void listagem(){

      ArrayList<Fatura> faturaslist = new ArrayList<Fatura>();

            Fatura faturaAberta = new Fatura();
            Fatura faturaAberta2 = new Fatura();
            faturaAberta.setValor(32.77f);
            faturaAberta.setAno(2017);
            faturaAberta.setMes(9);
            faturaAberta.setStatus(false);
            faturaslist.add(faturaAberta);

            faturaAberta2.setValor(67.27f);
            faturaAberta2.setAno(2017);
            faturaAberta2.setMes(8);
            faturaAberta2.setStatus(false);
            faturaslist.add(faturaAberta2);



        for(int i = 7; i > 1; i--){

            Fatura faturas = new Fatura();
            faturas.setValor(32.77f * i);
            faturas.setAno(2017);
            faturas.setMes(i);
            faturas.setStatus(true);

            faturaslist.add(faturas);
        }

        list_suasfaturas.setAdapter(new FaturaAdapter(this, faturaslist));


    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
    }


}
