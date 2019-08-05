package edp.projeto.com.edponline.helper;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edp.projeto.com.edponline.R;
import edp.projeto.com.edponline.model.Fatura;

public class FaturaAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Fatura> lista;

    public FaturaAdapter(Context context, ArrayList<Fatura> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Fatura fatura = lista.get(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.faturas , null);

        TextView mes = (TextView) layout.findViewById(R.id.texto_fatura1);
        mes.setTextColor(Color.GRAY);
        mes.setText("Mês: " + fatura.getNomeMes(fatura.getMes()));

        TextView ano = (TextView) layout.findViewById(R.id.texto_fatura2);
        ano.setTextColor(Color.GRAY);
        ano.setText("Ano: " + fatura.getAno());

        TextView valor = (TextView) layout.findViewById(R.id.texto_fatura3);
        DecimalFormat df = new DecimalFormat("0.00");
        valor.setTextColor(Color.GRAY);
        valor.setText( "Valor: R$ " + df.format(fatura.getValor()) );

        ImageView iv = (ImageView) layout.findViewById(R.id.iv_fatura);
        iv.setImageResource(R.drawable.ic_picture_as_pdf);


        ImageView iv2 = (ImageView) layout.findViewById(R.id.iv_fatura2);
        if(fatura.isPaga()){ iv2.setImageResource(R.drawable.checked);    }

        else if( fatura.getMes() == 8 ){ iv2.setImageResource(R.drawable.redalert); }

        else { iv2.setImageResource(R.drawable.alert_icon); }

        return layout;
    }
}
