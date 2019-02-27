package cristian.babarusi.guessthenumber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.MessageFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    static final String GAME_MODE = "gameMode";

    private TextView mTextViewVersion;
    private TextView mTextViewGameBy;
    private Button mButtonStartGame;
    private RadioButton mRadioButtonEasy;
    private RadioButton mRadioButtonHard;
    private TextView mTextViewTotalGamesPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        initView();
        displayVersion();

        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mRadioButtonEasy.isChecked()) {
                    //Sending data for EASY mode
                    Intent intentPlaying = new Intent(MainActivity.this, PlayingActivity.class);
                    intentPlaying.putExtra(GAME_MODE, "easy");
                    startActivity(intentPlaying);

                } else if (mRadioButtonHard.isChecked()) {
                    //Sending data for HARD mode
                    Intent intentPlaying = new Intent(MainActivity.this, PlayingActivity.class);
                    intentPlaying.putExtra(GAME_MODE, "hard");
                    startActivity(intentPlaying);

                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.please_select_game_mode)
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        mTextViewVersion = findViewById(R.id.textview_version);
        mTextViewGameBy = findViewById(R.id.textview_game_by);
        mTextViewGameBy.setText(getString(R.string.game_by_babarusi_cristian));
        mButtonStartGame = findViewById(R.id.button_start_game);
        mRadioButtonEasy = findViewById(R.id.radioButton_easy);
        mRadioButtonHard = findViewById(R.id.radioButton_hard);
        mTextViewTotalGamesPlayed = findViewById(R.id.textview_total_games_played);
        //se incarca de la shared pref
        mTextViewTotalGamesPlayed.setText(MessageFormat.format("{0} {1}",
                getString(R.string.total_games_played), GameDatas.getTotalGamesPlayed()));
    }

    private void displayVersion() {
        PackageManager manager = this.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            mTextViewVersion.setText(MessageFormat.format("{0} {1}", getString(R.string.version),
                    info.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PlayingActivity.SHARED_PREFS, MODE_PRIVATE);
        GameDatas.setTotalGamesPlayed(sharedPreferences.getInt(PlayingActivity.TOTAL_GAMES_PLAYED, 0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        mTextViewTotalGamesPlayed.setText(MessageFormat.format("{0} {1}",
                getString(R.string.total_games_played), GameDatas.getTotalGamesPlayed()));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
