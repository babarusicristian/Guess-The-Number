package cristian.babarusi.guessthenumber.utils;

import android.util.Log;
import cristian.babarusi.guessthenumber.BuildConfig;

public class Logging {
    public static void show(Object obj, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(obj.getClass().getName(), message);
        }
    }
}
