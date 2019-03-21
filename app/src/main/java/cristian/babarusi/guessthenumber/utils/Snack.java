package cristian.babarusi.guessthenumber.utils;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class Snack {
    public static void bar(View view, String message) {
        if (!TextUtils.isEmpty(message)) {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(Color.parseColor("#191919"));
            TextView tv =  snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(Color.parseColor("#9E7041"));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            }

            snackbar.show();
        }
    }
}
