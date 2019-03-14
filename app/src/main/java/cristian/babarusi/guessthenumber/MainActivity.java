package cristian.babarusi.guessthenumber;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cristian.babarusi.guessthenumber.Utils.Logging;

public class MainActivity extends AppCompatActivity {

    static final String GAME_MODE = "gameMode";

    private TextView mTextViewVersion;
    private TextView mTextViewGameBy;
    private Button mButtonStartGame;
    private RadioButton mRadioButtonEasy;
    private RadioButton mRadioButtonMedium;
    private RadioButton mRadioButtonHard;
    private TextView mTextViewTotalGamesPlayed;
    private Button mButtonHowToPlay;
    private TextView mTextViewOne;
    private TextView mTextViewTwo;
    private TextView mTextViewThree;
    private TextView mTextViewFour;
    private TextView mTextViewFive;
    private TextView mTextViewSix;
    private TextView mTextViewSeven;
    private TextView mTextViewEight;
    private TextView mTextViewNine;
    private TextView mTextViewZero;
    private TextView mTextViewQuestionMark;
    private ImageView mImageViewQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        hideNavigationBar();
        setContentView(R.layout.activity_main);

        initView();
        displayVersion();
        //start automatic animation
        AutomaticAnimation automaticAnimation = new AutomaticAnimation(MainActivity.this,
                mTextViewZero, mTextViewOne, mTextViewTwo, mTextViewThree, mTextViewFour,
                mTextViewFive, mTextViewSix, mTextViewSeven, mTextViewEight, mTextViewNine,
                mTextViewQuestionMark);
        automaticAnimation.startIt(MainActivity.this);

        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        mButtonHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHowToPlay();
            }
        });

        mImageViewQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void initView() {
        mTextViewVersion = findViewById(R.id.textview_version);
        mTextViewGameBy = findViewById(R.id.textview_game_by);
        mTextViewGameBy.setText(getString(R.string.game_by_babarusi_cristian));
        mButtonStartGame = findViewById(R.id.button_start_game);
        mRadioButtonEasy = findViewById(R.id.radioButton_easy);
        mRadioButtonMedium = findViewById(R.id.radioButton_medium);
        mRadioButtonHard = findViewById(R.id.radioButton_hard);
        mTextViewTotalGamesPlayed = findViewById(R.id.textview_total_games_played);
        mTextViewTotalGamesPlayed.setText(MessageFormat.format("{0} {1}",
                getString(R.string.total_games_played), GameDatas.getTotalGamesPlayed()));
        mButtonHowToPlay = findViewById(R.id.button_how_to_play);
        mTextViewOne = findViewById(R.id.text_view_one);
        mTextViewTwo = findViewById(R.id.text_view_two);
        mTextViewThree = findViewById(R.id.text_view_three);
        mTextViewFour = findViewById(R.id.text_view_four);
        mTextViewFive = findViewById(R.id.text_view_five);
        mTextViewSix = findViewById(R.id.text_view_six);
        mTextViewSeven = findViewById(R.id.text_view_seven);
        mTextViewEight = findViewById(R.id.text_view_eight);
        mTextViewNine = findViewById(R.id.text_view_nine);
        mTextViewZero = findViewById(R.id.text_view_zero);
        mTextViewQuestionMark = findViewById(R.id.text_view_question_mark);
        mImageViewQuit = findViewById(R.id.image_view_quit);
    }

    private void startGame() {
        if (mRadioButtonEasy.isChecked()) {
            //Sending data for EASY mode
            Intent intentPlaying = new Intent(MainActivity.this, PlayingActivity.class);
            intentPlaying.putExtra(GAME_MODE, "easy");
            startActivity(intentPlaying);
        } else if (mRadioButtonMedium.isChecked()) {
            //Sending data for MEDIUM mode
            Intent intentPlaying = new Intent(MainActivity.this, PlayingActivity.class);
            intentPlaying.putExtra(GAME_MODE, "medium");
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

    public void dialogHowToPlay() {
        final Dialog dialogHowToPlay = new Dialog(MainActivity.this);
        dialogHowToPlay.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogHowToPlay.setContentView(R.layout.dialog_how_to_play);
        //help to hide navigation bar into dialog
        View decorView = dialogHowToPlay.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);

        Button dialogButtonOK = dialogHowToPlay.findViewById(R.id.button_dialog_how_to_play_ok);

        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHowToPlay.dismiss();
                hideNavigationBar();
            }
        });

        dialogHowToPlay.show();
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
        SharedPreferences sharedPreferences = getSharedPreferences(PlayingActivity.SHARED_PREFS,
                MODE_PRIVATE);
        GameDatas.setTotalGamesPlayed(sharedPreferences.getInt(PlayingActivity.TOTAL_GAMES_PLAYED
                , 0));
    }

    //help hidding navigation bar on dialog box
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            hideNavigationBar();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        hideNavigationBar();
        loadData();
        mTextViewTotalGamesPlayed.setText(MessageFormat.format("{0} {1}",
                getString(R.string.total_games_played), GameDatas.getTotalGamesPlayed()));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //TODO button de BACK si de EXIT
}
