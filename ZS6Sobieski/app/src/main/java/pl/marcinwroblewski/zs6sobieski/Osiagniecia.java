package pl.marcinwroblewski.zs6sobieski;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Osiagniecia extends Activity {

    private View[] animationElements;
    private Animation fadeIn, fadeOut;
    private RelativeLayout body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_osiagniecia);

        Screen screen = new Screen(getApplicationContext());

        if(screen.getDPI() < 650f){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }



        body = (RelativeLayout)findViewById(R.id.main_holder);
        animationElements = new View[body.getChildCount()];
        for(int i=0; i<animationElements.length; i++){
            animationElements[i] = body.getChildAt(i);
        }

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_with_rotate);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        TextView appTitle = (TextView)findViewById(R.id.app_title);
        appTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        for(int i=0; i<animationElements.length; i++){
            animationElements[i].startAnimation(fadeIn);
        }
    }
}
