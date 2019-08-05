package edp.projeto.com.edponline.helper;


import android.animation.ObjectAnimator;
import android.widget.ImageView;

public class Animacao {

    public static void AnimacaoAparecer(ImageView imageView){
        ObjectAnimator animacao = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        animacao.setDuration(800);
        animacao.start();
            }


}
