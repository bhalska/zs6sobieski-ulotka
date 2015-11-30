package pl.marcinwroblewski.zs6sobieski;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;


public class SpalshScreen extends Activity {

    private View[] animationElements;
    private Animation fadeIn, fadeOut;
    private boolean nextActivityStarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.splash_screen);

        nextActivityStarted = false;

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);


        RelativeLayout body = (RelativeLayout)findViewById(R.id.root_container);
        animationElements = new View[body.getChildCount()];
        for(int i=0; i<animationElements.length; i++){
            animationElements[i] = body.getChildAt(i);
        }


        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nextActivityStarted) {
                    nextActivityStarted = true;


                    for (int i = 0; i < animationElements.length; i++) {
                        Animation currentAnimation = fadeOut;
                        currentAnimation.setStartOffset((i*2000)+2000);
                        animationElements[i].startAnimation(currentAnimation);
                    }

                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }


                        @Override
                        public void onAnimationEnd(Animation animation) {

                            startActivity(new Intent(getApplicationContext(), Menu.class));
                            for (View animationElement : animationElements) {
                                animationElement.setVisibility(View.INVISIBLE);
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                    });
                }
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

    @Override
    protected void onPause() {
        super.onPause();
        super.finish();
    }
}
