package cristian.babarusi.guessthenumber;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimers extends AppCompatActivity {

    private int mSeconds = 0;
    private int mMinutes = 0;

    private Timer timerElapTime;

    public void startElapsedTime(final TextView textView, final Context context) {

        timerElapTime = new Timer();
        timerElapTime.schedule(new TimerTask() {

            String showMinutes = "00";
            String showSeconds = "00";

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        setSeconds(getSeconds() + 1);

                        if (getSeconds() == 60) {
                            setSeconds(0);
                            setMinutes(getMinutes() + 1);
                        }

                        //styling timer in textView
                        if (getSeconds() < 10) {
                            showSeconds = "0" + getSeconds();
                        }
                        if (getMinutes() < 10) {
                            showMinutes = "0" + getMinutes();
                        }

                        if (getSeconds() >= 10) {
                            showSeconds = "" + getSeconds();
                        }

                        if (getMinutes() >= 10) {
                            showMinutes = "" + getMinutes();
                        }

                        textView.setText(MessageFormat.format("{0} {1}:{2}",
                                context.getString(R.string.elapsed_time), showMinutes,
                                showSeconds));
                    }
                });
            }
        }, 0, 1000);
    }

    public void stopElapsedTime() {
        timerElapTime.cancel();
    }

    public void resetElapsedTime() {
        setSeconds(0);
        setMinutes(0);
    }

    public int getSeconds() {
        return mSeconds;
    }

    public void setSeconds(int seconds) {
        mSeconds = seconds;
    }

    public int getMinutes() {
        return mMinutes;
    }

    public void setMinutes(int minutes) {
        mMinutes = minutes;
    }
}
