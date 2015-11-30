package pl.marcinwroblewski.zs6sobieski;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Admin on 2015-05-27.
 */
public class Screen {

    private float DPI;
    private Context c;

    public Screen(Context context){
        c = context;

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);

        float scaleFactor = metrics.density;

        DPI = Math.min(metrics.widthPixels/scaleFactor, metrics.heightPixels/scaleFactor);

        Log.d(context.getPackageName(), "Current DPI: " + DPI);
    }


    public float getDPI(){
        return DPI;
    }


}
