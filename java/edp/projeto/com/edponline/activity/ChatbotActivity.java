package edp.projeto.com.edponline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import edp.projeto.com.edponline.R;

public class ChatbotActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        webView = (WebView) findViewById(R.id.wv_id);

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        webView.loadUrl("http://www.baixaki.com.br/android/download/simsimi.htm");

    }
}
