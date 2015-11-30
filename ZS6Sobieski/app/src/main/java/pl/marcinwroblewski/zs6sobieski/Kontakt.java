package pl.marcinwroblewski.zs6sobieski;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class Kontakt extends Activity {


    private View[] animationElements;
    private Animation fadeIn, fadeOut;
    private RelativeLayout body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setContentView(R.layout.activity_kontakt);



        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_with_rotate);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        Screen screen = new Screen(getApplicationContext());

        if(screen.getDPI() < 650f){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            fadeIn.setDuration(700);
            fadeOut.setDuration(700);
        }


        body = (RelativeLayout)findViewById(R.id.main_holder);
        body.post(new Runnable() {
            @Override
            public void run() {
                animationElements = new View[body.getChildCount()];
                for(int i=0; i<animationElements.length; i++){
                    animationElements[i] = body.getChildAt(i);

                    animationElements[i].startAnimation(fadeIn);

                }
            }
        });


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

        body.post(new Runnable() {
            @Override
            public void run() {
                animationElements = new View[body.getChildCount()];
                for (int i = 0; i < animationElements.length; i++) {
                    animationElements[i] = body.getChildAt(i);
                    animationElements[i].startAnimation(fadeIn);
                }
            }
        });
    }

    public void call(View view) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:324717264"));
        startActivity(intent);
    }

    public void navigate(View view) {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=49.9515929,18.611209"));
        startActivity(intent);

    }

    public void sendEmail(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("mailto:"+getResources().getString(R.string.kontakt_content2)));
        startActivity(intent);
    }
}
