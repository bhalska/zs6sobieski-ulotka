package pl.marcinwroblewski.zs6sobieski;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class Menu extends Activity {

    private Animation fadeIn, fadeOut;
    private View[] animationElements;
    private RelativeLayout body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        body = (RelativeLayout)findViewById(R.id.root_container);
        Screen screen = new Screen(getApplicationContext());



        if(screen.getDPI() < 650f){
            setContentView(R.layout.activity_phone_menu);
            RelativeLayout body = (RelativeLayout)findViewById(R.id.main_holder);

            animationElements = new View[body.getChildCount()];
            for(int i=0; i<animationElements.length; i++){
                animationElements[i] = body.getChildAt(i);
            }
        }else{
            setContentView(R.layout.activity_tablet_menu);
            LinearLayout body = (LinearLayout)findViewById(R.id.main_holder);

            animationElements = new View[body.getChildCount()];
            for(int i=0; i<animationElements.length; i++){
                animationElements[i] = body.getChildAt(i);
            }
        }

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_with_rotate);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_with_rotate);
    }


    @Override
    protected void onStart() {
        super.onStart();

        for (View animationElement : animationElements) {
            animationElement.startAnimation(fadeIn);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        for (View animationElement : animationElements) {
            animationElement.startAnimation(fadeIn);
        }
    }

    @Override
    public void onBackPressed() {
        for (View animationElement : animationElements) {
            animationElement.startAnimation(fadeOut);
        }

        finish();
    }

    public void goTo(View view) {

        Intent intent = null;

        switch (view.getId()){
            case R.id.rekrutacja_holder: intent = new Intent(Menu.this, Rekrutacja.class); break;
            case R.id.kontakt_holder: intent = new Intent(Menu.this, Kontakt.class); break;
            case R.id.kierunki_holder: intent = new Intent(Menu.this, Kierunki.class); break;
            case R.id.osiagniecia_holder: intent = new Intent(Menu.this, Osiagniecia.class); break;
            case R.id.logoZS6: intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://zs6sobieski.pl/")); break;
            case R.id.logoB: intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://marcinwroblewski.24tm.pl/")); break;
            default: break;
        }

        if(intent != null) startActivity(intent);

    }
}

