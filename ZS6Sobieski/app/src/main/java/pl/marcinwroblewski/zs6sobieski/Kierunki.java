package pl.marcinwroblewski.zs6sobieski;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pl.marcinwroblewski.zs6sobieski.R;


public class Kierunki extends Activity {

    private View[] animationElements;
    private Animation fadeIn, fadeOut;
    private RelativeLayout body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_kierunki);


        body = (RelativeLayout)findViewById(R.id.main_holder);
        animationElements = new View[body.getChildCount()];
        for(int i=0; i<animationElements.length; i++){
            animationElements[i] = body.getChildAt(i);
        }

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_with_rotate);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout_with_rotate);

       TextView appTitle = (TextView)findViewById(R.id.app_title);

        if(appTitle != null) {
            appTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    onBackPressed();
                }
            });
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        for (View animationElement : animationElements) {
            animationElement.startAnimation(fadeIn);
        }
    }
}
