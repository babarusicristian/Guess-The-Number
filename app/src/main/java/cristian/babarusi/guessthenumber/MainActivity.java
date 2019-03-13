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
    //private int mRandValue;
    private int mPreviousRandValue;

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
        automaticAnimation();

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
    }

    private void automaticAnimation() {
        Timer timerAutoAnim = new Timer();
        timerAutoAnim.schedule(new TimerTask() {
            Random rand = new Random();
            Random rand2 = new Random();

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        random values from 0 to 10
                        10 - for question mark
                        */
                        int mRandValue = rand.nextInt(11); //from 0 to 10
                        int mRandValue2 = rand2.nextInt(2) + 1; //from 1 to 2
                        Logging.show(MainActivity.this, "mRandValue: " + mRandValue);
                        Logging.show(MainActivity.this, "mRandValue2: " + mRandValue2);

                        //if random is duplicate
                        if (mRandValue == mPreviousRandValue) {
                            return;
                        }

                        Animation animZoomIn =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.zoom_in);

                        final Animation animZoomBack =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.zoom_back);

                        Animation animZoomIn2 =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.zoom_in_2);

                        final Animation animZoomBack2 =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.zoom_back_2);

                        switch (mRandValue) {
                            case 0:
                                mPreviousRandValue = 0;

                                if (mRandValue2 == 1) {
                                    mTextViewZero.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewZero.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewZero.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewZero.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 1:
                                mPreviousRandValue = 1;

                                if (mRandValue2 == 1) {
                                    mTextViewOne.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewOne.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewOne.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewOne.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 2:
                                mPreviousRandValue = 2;

                                if (mRandValue2 == 1) {
                                    mTextViewTwo.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewTwo.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewTwo.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewTwo.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 3:
                                mPreviousRandValue = 3;

                                if (mRandValue2 == 1) {
                                    mTextViewThree.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewThree.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewThree.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewThree.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 4:
                                mPreviousRandValue = 4;

                                if (mRandValue2 == 1) {
                                    mTextViewFour.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewFour.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewFour.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewFour.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 5:
                                mPreviousRandValue = 5;

                                if (mRandValue2 == 1) {
                                    mTextViewFive.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewFive.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewFive.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewFive.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 6:
                                mPreviousRandValue = 6;

                                if (mRandValue2 == 1) {
                                    mTextViewSix.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewSix.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewSix.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewSix.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 7:
                                mPreviousRandValue = 7;

                                if (mRandValue2 == 1) {
                                    mTextViewSeven.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewSeven.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewSeven.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewSeven.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 8:
                                mPreviousRandValue = 8;

                                if (mRandValue2 == 1) {
                                    mTextViewEight.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewEight.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewEight.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewEight.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 9:
                                mPreviousRandValue = 9;

                                if (mRandValue2 == 1) {
                                    mTextViewNine.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewNine.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewNine.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewNine.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                            case 10:
                                mPreviousRandValue = 10;

                                if (mRandValue2 == 1) {
                                    mTextViewQuestionMark.startAnimation(animZoomIn);
                                    animZoomIn.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewQuestionMark.startAnimation(animZoomBack);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                } else {
                                    mTextViewQuestionMark.startAnimation(animZoomIn2);
                                    animZoomIn2.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            mTextViewQuestionMark.startAnimation(animZoomBack2);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                }
                                break;
                        }
                    }
                });
            }
        }, 0, 1800);
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

        Button dialogButtonOK = dialogHowToPlay.findViewById(R.id.button_dialog_how_to_play_ok);

        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHowToPlay.dismiss();
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
