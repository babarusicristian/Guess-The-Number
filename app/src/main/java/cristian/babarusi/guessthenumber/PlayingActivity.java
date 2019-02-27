package cristian.babarusi.guessthenumber;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import cristian.babarusi.guessthenumber.Utils.Logging;

public class PlayingActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOTAL_GAMES_PLAYED = "totalGamesPlayed";

    private final static int MAX_LEN_EASY = 3;
    private final static int MAX_LEN_HARD = 4;

    private Game mGame;
    private String mNumber = "";
    private String mGameMode = "";
    private Boolean mOverAnimText = true;
    private Timer mTimerBlinking;

    private TextView mTextViewRobotMessage;
    private Button mButtonYesIcan;
    private Button mButtonNoIcant;
    private TextView mTextViewPlayerNumber;
    private TextView mTextViewAttempts;
    private Button mButtonNum0;
    private Button mButtonNum1;
    private Button mButtonNum2;
    private Button mButtonNum3;
    private Button mButtonNum4;
    private Button mButtonNum5;
    private Button mButtonNum6;
    private Button mButtonNum7;
    private Button mButtonNum8;
    private Button mButtonNum9;
    private Button mButtonBackSpace;
    private Button mButtonGuess;
    private LinearLayout mLinearLayoutButtons;
    private TextView mTextViewShowGameMode;
    private TextView mTextViewCurrentGamesPlayed;
    private Button mButtonOneMoreTime;
    private Button mButtonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_playing);

        mGame = new Game();
        initView();

        //Receive and set the game mode data
        Bundle extras = getIntent().getExtras();
        mTextViewCurrentGamesPlayed.setText(MessageFormat.format("{0} {1}",
                getString(R.string.current_games_played), mGame.getCurrentGamesPlayed()));

        if (extras != null) {
            setGameMode(extras.getString(MainActivity.GAME_MODE));

            if (getGameMode() != null) {
                if (getGameMode().equals("easy")) {
                    mTextViewShowGameMode.setText(getString(R.string.show_easy_gamemode_selection));
                    mGame.randomizeEasyMode();
                    //animate first robot message
                    mGame.setRobotMessage(getString(R.string.robot_say_hi_there_easy));
                    mTextViewRobotMessage.setText(mGame.getRobotMessage());
                    animateTextWithButtons(mGame.getRobotMessage(), mTextViewRobotMessage,
                            mLinearLayoutButtons);
                    Logging.show(PlayingActivity.this,
                            "Easy random ales: " + mGame.getNumberToBeGuessed());

                } else if (getGameMode().equals("hard")) {
                    mTextViewShowGameMode.setText(getString(R.string.show_hard_gamemode_selection));
                    mGame.randomizeHardMode();
                    //animate first robot message
                    mGame.setRobotMessage(getString(R.string.robot_say_hi_there_hard));
                    mTextViewRobotMessage.setText(mGame.getRobotMessage());
                    animateTextWithButtons(mGame.getRobotMessage(), mTextViewRobotMessage,
                            mLinearLayoutButtons);
                    Logging.show(PlayingActivity.this,
                            "HARD random ales: " + mGame.getNumberToBeGuessed());
                }
            }
        }

        mButtonYesIcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide first time buttons
                mButtonYesIcan.setVisibility(View.GONE);
                mButtonNoIcant.setVisibility(View.GONE);
                showMyGameKeyboard();
            }
        });

        mButtonNoIcant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mButtonNum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "0";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "0";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "1";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "1";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "2";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "2";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "3";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "3";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "4";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "4";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "5";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "5";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "6";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "6";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "7";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "7";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "8";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "8";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (getGameMode().equals("easy") && getNumber().length() < MAX_LEN_EASY) {
                    sVal += "9";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                } else if (getGameMode().equals("hard") && getNumber().length() < MAX_LEN_HARD) {
                    sVal += "9";
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVal = getNumber();

                if (!sVal.isEmpty()) {
                    sVal = sVal.substring(0, sVal.length() - 1);
                    setNumber(sVal);
                    mTextViewPlayerNumber.setText(getNumber());
                }
            }
        });

        mButtonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //start verifications
                if (mTextViewPlayerNumber.getText().toString().startsWith("0")) {
                    //if is zero
                    if (mTextViewPlayerNumber.getText().toString().equals("0")) {
                        if (getGameMode().equals("easy") && isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.zero_no_easy));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            attemptPlus(); //count attempt
                        } else {
                            if (isOverAnimText()) {
                                mGame.setRobotMessage(getString(R.string.zero_no_hard));
                                animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                                attemptPlus(); //count attempt
                            }
                        }
                    } else {
                        //if start only with zero
                        if (isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.ohh_start_with_zero) + " "
                                    + getNumber() + " " + getString(R.string.is_not_valid));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            //don't count attempt
                        }
                    }
                } else {
                    //if no number is inserted
                    if (mTextViewPlayerNumber.getText().equals("") && isOverAnimText()) {
                        mGame.setRobotMessage(getString(R.string.you_did_not_enter_number));
                        animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                        //don't count attempt
                    } else if (!mTextViewPlayerNumber.getText().equals("")) {
                        //all verification has finished AND the number is inserted
                        int num = Integer.valueOf(mTextViewPlayerNumber.getText().toString());

                        if (getGameMode().equals("easy") && num > 200 && isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.wow_no_no_no) + " "
                                    + getString(R.string.the) + " " + num + " "
                                    + getString(R.string.is_huge_200));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            attemptPlus(); //count attempt
                        } else if (getGameMode().equals("hard") && num > 1000 && isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.wow_no_no_no) + " "
                                    + getString(R.string.the) + " " + num + " "
                                    + getString(R.string.is_huge_1000));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            attemptPlus(); //count attempt
                        } else if (num < mGame.getNumberToBeGuessed() && isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.no_is_not) + " " + num + getString(R.string.my_num_is_bigger));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            attemptPlus(); //count attempt
                        } else if (num > mGame.getNumberToBeGuessed() && isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.no_is_not) + " " + num + getString(R.string.my_num_is_smaller));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            attemptPlus(); //count attempt
                        } else if (num == mGame.getNumberToBeGuessed() && isOverAnimText()) {
                            mGame.setRobotMessage(getString(R.string.yupii_yes) + " " + num + " " + getString(R.string.is_my_num));
                            animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                            attemptPlus(); //count attempt

                            //increment games played and display
                            mGame.setCurrentGamesPlayed(mGame.getCurrentGamesPlayed() + 1);
                            mTextViewCurrentGamesPlayed.setText(MessageFormat.format("{0} {1}",
                                    getString(R.string.current_games_played),
                                    mGame.getCurrentGamesPlayed()));

                            //increment total games played
                            GameDatas.setTotalGamesPlayed(GameDatas.getTotalGamesPlayed() + 1);

                            //hide buttons
                            mButtonNum0.setVisibility(View.INVISIBLE);
                            mButtonNum1.setVisibility(View.INVISIBLE);
                            mButtonNum2.setVisibility(View.INVISIBLE);
                            mButtonNum3.setVisibility(View.INVISIBLE);
                            mButtonNum4.setVisibility(View.INVISIBLE);
                            mButtonNum5.setVisibility(View.INVISIBLE);
                            mButtonNum6.setVisibility(View.INVISIBLE);
                            mButtonNum7.setVisibility(View.INVISIBLE);
                            mButtonNum8.setVisibility(View.INVISIBLE);
                            mButtonNum9.setVisibility(View.INVISIBLE);
                            mButtonBackSpace.setVisibility(View.INVISIBLE);
                            mButtonGuess.setVisibility(View.INVISIBLE);

                            //diplaying winner text
                            mTextViewPlayerNumber.setText(getString(R.string.you_ve_guessed));
                            mTextViewAttempts.setText(MessageFormat.format("{0} {1} {2}",
                                    getString(R.string.from), mGame.getAttempts(),
                                    getString(R.string.attempts_point)));

                            //animate the winning text
                            mTimerBlinking = new Timer();
                            mTimerBlinking.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mTextViewPlayerNumber.getText().equals(getString(R.string.you_ve_guessed))) {
                                                mTextViewPlayerNumber.setText("");
                                            } else {
                                                mTextViewPlayerNumber.setText(getString(R.string.you_ve_guessed));
                                            }
                                        }
                                    });
                                }
                            }, 335, 888);

                            //showing the next buttons with DELAY
                            final Timer timerLate = new Timer();
                            timerLate.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mButtonOneMoreTime.setVisibility(View.VISIBLE);
                                            mButtonGoBack.setVisibility(View.VISIBLE);
                                            //put fade in animations on buttons
                                            Animation animationFadeIn =
                                                    AnimationUtils.loadAnimation(getApplicationContext(),
                                                            R.anim.fade_in);
                                            mButtonOneMoreTime.startAnimation(animationFadeIn);
                                            mButtonGoBack.startAnimation(animationFadeIn);
                                            timerLate.cancel();
                                        }
                                    });
                                }
                            }, 2666);
                        }
                    }
                }
            }
        });

        mButtonOneMoreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOverAnimText()) {
                    mTimerBlinking.cancel(); // cancel winning timer
                    setNumber("");//reset number
                    mTextViewPlayerNumber.setText("");
                    mGame.setAttempts(0); //reset attempts
                    mTextViewAttempts.setText(MessageFormat.format("{0} {1}",
                            getString(R.string.attempts), mGame.getAttempts()));

                    mButtonOneMoreTime.setVisibility(View.GONE);
                    mButtonGoBack.setVisibility(View.GONE);

                    //new randomize and new messages for robot
                    if (getGameMode().equals("easy")) {
                        mGame.randomizeEasyMode();
                        Logging.show(PlayingActivity.this,
                                "new EASY random ales: " + mGame.getNumberToBeGuessed());
                        mGame.setRobotMessage(getString(R.string.all_right_easy));
                        animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                    } else if (getGameMode().equals("hard")) {
                        mGame.randomizeHardMode();
                        Logging.show(PlayingActivity.this,
                                "new HARD random ales: " + mGame.getNumberToBeGuessed());
                        mGame.setRobotMessage(getString(R.string.all_right_hard));
                        animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                    }
                    showMyGameKeyboard();
                }
            }
        });

        mButtonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //my methods
    private void initView() {
        mButtonYesIcan = findViewById(R.id.button_yes_i_can);
        mButtonNoIcant = findViewById(R.id.button_no_i_cant);
        mLinearLayoutButtons = findViewById(R.id.linearlayout_buttons);
        mTextViewPlayerNumber = findViewById(R.id.textview_player_number);
        mTextViewAttempts = findViewById(R.id.textview_attempts);
        mTextViewAttempts.setText(MessageFormat.format("{0} {1}", getString(R.string.attempts),
                mGame.getAttempts()));
        mButtonNum0 = findViewById(R.id.button_num0);
        mButtonNum1 = findViewById(R.id.button_num1);
        mButtonNum2 = findViewById(R.id.button_num2);
        mButtonNum3 = findViewById(R.id.button_num3);
        mButtonNum4 = findViewById(R.id.button_num4);
        mButtonNum5 = findViewById(R.id.button_num5);
        mButtonNum6 = findViewById(R.id.button_num6);
        mButtonNum7 = findViewById(R.id.button_num7);
        mButtonNum8 = findViewById(R.id.button_num8);
        mButtonNum9 = findViewById(R.id.button_num9);
        mButtonBackSpace = findViewById(R.id.button_backspace);
        mButtonGuess = findViewById(R.id.button_guess);
        mTextViewRobotMessage = findViewById(R.id.textview_robot_message);
        mTextViewShowGameMode = findViewById(R.id.textview_show_gamemode);
        mTextViewCurrentGamesPlayed = findViewById(R.id.textview_current_games_played);
        mButtonOneMoreTime = findViewById(R.id.button_one_more_time);
        mButtonGoBack = findViewById(R.id.button_go_back);
    }

    private void animateTextWithButtons(final String txt, final TextView tv,
                                        final LinearLayout linearLayoutButtons) {
        final Timer timer = new Timer();
        final char[] charTxt = txt.toCharArray();
        tv.setText("");
        final int[] i = {-1};

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i[0]++;
                        tv.setText(MessageFormat.format("{0}{1}", tv.getText(), charTxt[i[0]]));

                        if (i[0] == charTxt.length - 1) {
                            linearLayoutButtons.setVisibility(View.VISIBLE);
                            //fade in the button
                            Animation animationFadeIn =
                                    AnimationUtils.loadAnimation(getApplicationContext(),
                                            R.anim.fade_in);
                            linearLayoutButtons.startAnimation(animationFadeIn);
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 66);
    }

    private void animateText(final String txt, final TextView tv) {
        setOverAnimText(false);
        final Timer timer = new Timer();
        final char[] charTxt = txt.toCharArray();
        tv.setText("");
        final int[] i = {-1};

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i[0]++;
                        tv.setText(MessageFormat.format("{0}{1}", tv.getText(), charTxt[i[0]]));

                        if (i[0] == charTxt.length - 1) {
                            setOverAnimText(true);
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 66);
    }

    //increment attempts
    private void attemptPlus() {
        mGame.incrementAttempts();
        mTextViewAttempts.setText(MessageFormat.format("{0} {1}", getString(R.string.attempts),
                mGame.getAttempts()));
    }

    private void showMyGameKeyboard() {
        mTextViewPlayerNumber.setVisibility(View.VISIBLE);
        mTextViewAttempts.setVisibility(View.VISIBLE);
        mButtonNum0.setVisibility(View.VISIBLE);
        mButtonNum1.setVisibility(View.VISIBLE);
        mButtonNum2.setVisibility(View.VISIBLE);
        mButtonNum3.setVisibility(View.VISIBLE);
        mButtonNum4.setVisibility(View.VISIBLE);
        mButtonNum5.setVisibility(View.VISIBLE);
        mButtonNum6.setVisibility(View.VISIBLE);
        mButtonNum7.setVisibility(View.VISIBLE);
        mButtonNum8.setVisibility(View.VISIBLE);
        mButtonNum9.setVisibility(View.VISIBLE);
        mButtonBackSpace.setVisibility(View.VISIBLE);
        mButtonGuess.setVisibility(View.VISIBLE);

        //fade in all
        Animation animationFadeIn =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        mTextViewPlayerNumber.startAnimation(animationFadeIn);
        mTextViewAttempts.startAnimation(animationFadeIn);
        mButtonNum0.startAnimation(animationFadeIn);
        mButtonNum1.startAnimation(animationFadeIn);
        mButtonNum2.startAnimation(animationFadeIn);
        mButtonNum3.startAnimation(animationFadeIn);
        mButtonNum4.startAnimation(animationFadeIn);
        mButtonNum5.startAnimation(animationFadeIn);
        mButtonNum6.startAnimation(animationFadeIn);
        mButtonNum7.startAnimation(animationFadeIn);
        mButtonNum8.startAnimation(animationFadeIn);
        mButtonNum9.startAnimation(animationFadeIn);
        mButtonBackSpace.startAnimation(animationFadeIn);
        mButtonGuess.startAnimation(animationFadeIn);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        //folosim constanta de la globale SHARE_PREF
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(TOTAL_GAMES_PLAYED, GameDatas.getTotalGamesPlayed());
        editor.commit();
    }

    //getters setters
    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getGameMode() {
        return mGameMode;
    }

    public void setGameMode(String gameMode) {
        mGameMode = gameMode;
    }

    public Boolean isOverAnimText() {
        return mOverAnimText;
    }

    public void setOverAnimText(Boolean overAnimText) {
        mOverAnimText = overAnimText;
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
    }
}


