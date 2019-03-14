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
import android.widget.ImageView;
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
    private final static int MAX_LEN_MEDIUM = 4;
    private final static int MAX_LEN_HARD = 5;

    private final static int LETTERS_SPEED = 40;

    private Boolean firstTimeMsg = true;
    private Game mGame;
    private GameTimers mGameTimers;
    private String mNumber = "";
    private String mGameMode = "";
    private Boolean mOverAnimText = true;
    private Timer mTimerBlinking;
    private Timer timerAnimText;

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
    private TextView mTextViewElapsedTime;
    private ImageView mImageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_playing);

        hideNavigationBar();

        mGame = new Game();
        mGameTimers = new GameTimers();
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
                    mGame.setRobotMessage(getString(R.string.robot_say_hi_there) + " " + Game.NUM_EASY + ".");
                    mTextViewRobotMessage.setText(mGame.getRobotMessage());
                    setFirstTimeMsg(true);
                    animateTextWithButtons(mGame.getRobotMessage(), mTextViewRobotMessage,
                            mLinearLayoutButtons);
                    Logging.show(PlayingActivity.this,
                            "Easy random ales: " + mGame.getNumberToBeGuessed());

                } else if (getGameMode().equals("medium")) {
                    mTextViewShowGameMode.setText(getString(R.string.show_medium_gamemode_selection));
                    mGame.randomizeMediumMode();
                    //animate first robot message
                    mGame.setRobotMessage(getString(R.string.robot_say_hi_there) + " " + Game.NUM_MEDIUM + ".");
                    mTextViewRobotMessage.setText(mGame.getRobotMessage());
                    setFirstTimeMsg(true);
                    animateTextWithButtons(mGame.getRobotMessage(), mTextViewRobotMessage,
                            mLinearLayoutButtons);
                    Logging.show(PlayingActivity.this,
                            "Medium random ales: " + mGame.getNumberToBeGuessed());

                } else if (getGameMode().equals("hard")) {
                    mTextViewShowGameMode.setText(getString(R.string.show_hard_gamemode_selection));
                    mGame.randomizeHardMode();
                    //animate first robot message
                    mGame.setRobotMessage(getString(R.string.robot_say_hi_there) + " " + Game.NUM_HARD + ".");
                    mTextViewRobotMessage.setText(mGame.getRobotMessage());
                    setFirstTimeMsg(true);
                    animateTextWithButtons(mGame.getRobotMessage(), mTextViewRobotMessage,
                            mLinearLayoutButtons);
                    Logging.show(PlayingActivity.this,
                            "HARD random ales: " + mGame.getNumberToBeGuessed());
                }
            }
        }

        mTextViewRobotMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeRobotDialog();
            }
        });

        mButtonYesIcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide first time buttons
                mButtonYesIcan.setVisibility(View.GONE);
                mButtonNoIcant.setVisibility(View.GONE);
                showMyGameKeyboard();

                //start ElapsedTime waiting finish effect fade in
                final Timer timerTemp = new Timer();
                timerTemp.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mGameTimers.startElapsedTime(mTextViewElapsedTime, PlayingActivity.this);
                        timerTemp.cancel();
                    }
                }, 1666);
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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
                } else if (getGameMode().equals("medium") && getNumber().length() < MAX_LEN_MEDIUM) {
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

        mTextViewPlayerNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewPlayerNumber.setText("");
                setNumber("");
            }
        });

        mButtonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winningMethod();
            }
        });

        mButtonOneMoreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        mButtonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mImageViewBack.setOnClickListener(new View.OnClickListener() {
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
        mTextViewElapsedTime = findViewById(R.id.textview_elapsed_time);
        mTextViewElapsedTime.setText(MessageFormat.format("{0} 00:00",
                getString(R.string.elapsed_time)));
        mImageViewBack = findViewById(R.id.image_view_back);
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

    private void completeRobotDialog() {
        final Timer tempTimeGap = new Timer();

        if (isFirstTimeMsg() && mLinearLayoutButtons.getVisibility() != View.VISIBLE) {
            timerAnimText.cancel(); //stop animation text timer
            setOverAnimText(true);

            //show the first buttons
            mLinearLayoutButtons.setVisibility(View.VISIBLE);
            Animation animationFadeIn =
                    AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_in);
            mLinearLayoutButtons.startAnimation(animationFadeIn);

            //some time gap - the same as the speed of showing letters for robot msg
            tempTimeGap.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewRobotMessage.setText(mGame.getRobotMessage());
                            tempTimeGap.cancel();
                        }
                    });
                }
            }, 0, LETTERS_SPEED);

        } else {
            timerAnimText.cancel(); //stop animation text timer
            setOverAnimText(true);

            //some time gap - the same as the speed of showing letters for robot msg
            tempTimeGap.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewRobotMessage.setText(mGame.getRobotMessage());
                            tempTimeGap.cancel();
                        }
                    });
                }
            }, 0, LETTERS_SPEED);
        }
    }

    private void winningMethod() {
        //start verifications
        if (mTextViewPlayerNumber.getText().toString().startsWith("0")) {
            //if is zero
            if (mTextViewPlayerNumber.getText().toString().equals("0")) {
                if (getGameMode().equals("easy") && isOverAnimText()) {
                    mGame.setRobotMessage(getString(R.string.zero_no) + " " + Game.NUM_EASY + ".");
                    animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                    attemptPlus(); //count attempt
                } else if (getGameMode().equals("medium") && isOverAnimText()) {
                    mGame.setRobotMessage(getString(R.string.zero_no) + " " + Game.NUM_MEDIUM +
                            ".");
                    animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                    attemptPlus(); //count attempt
                } else {
                    if (isOverAnimText()) {
                        mGame.setRobotMessage(getString(R.string.zero_no) + " " + Game.NUM_HARD + ".");
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

                if (getGameMode().equals("easy") && num > Game.NUM_EASY && isOverAnimText()) {
                    mGame.setRobotMessage(getString(R.string.wow_no_no_no) + " "
                            + getString(R.string.the) + " " + num + " "
                            + getString(R.string.is_huge_num) + " " + Game.NUM_EASY + ".");
                    animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                    attemptPlus(); //count attempt
                } else if (getGameMode().equals("medium") && num > Game.NUM_MEDIUM && isOverAnimText()) {
                    mGame.setRobotMessage(getString(R.string.wow_no_no_no) + " "
                            + getString(R.string.the) + " " + num + " "
                            + getString(R.string.is_huge_num) + " " + Game.NUM_MEDIUM + ".");
                    animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
                    attemptPlus(); //count attempt
                } else if (getGameMode().equals("hard") && num > Game.NUM_HARD && isOverAnimText()) {
                    mGame.setRobotMessage(getString(R.string.wow_no_no_no) + " "
                            + getString(R.string.the) + " " + num + " "
                            + getString(R.string.is_huge_num) + " " + Game.NUM_HARD + ".");
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

                    //WINNING METHOD
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

                    //stop elapsedTime
                    mGameTimers.stopElapsedTime();

                    //showing last buttons with small delay
                    Timer tempTimer = new Timer();
                    tempTimer.schedule(new TimerTask() {
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
                                }
                            });
                        }
                    }, 666);
                }
            }
        }
    }

    private void playAgain() {

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
                mGame.setRobotMessage(getString(R.string.all_right) + " " + Game.NUM_EASY + ".");
                animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
            } else if (getGameMode().equals("medium")) {
                mGame.randomizeMediumMode();
                Logging.show(PlayingActivity.this,
                        "new MEDIUM random ales: " + mGame.getNumberToBeGuessed());
                mGame.setRobotMessage(getString(R.string.all_right) + " " + Game.NUM_MEDIUM + ".");
                animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
            } else if (getGameMode().equals("hard")) {
                mGame.randomizeHardMode();
                Logging.show(PlayingActivity.this,
                        "new HARD random ales: " + mGame.getNumberToBeGuessed());
                mGame.setRobotMessage(getString(R.string.all_right) + " " + Game.NUM_HARD + ".");
                animateText(mGame.getRobotMessage(), mTextViewRobotMessage);
            }

            mTextViewElapsedTime.setText(MessageFormat.format("{0} 00:00",
                    getString(R.string.elapsed_time)));

            //small delay on showing keyboard buttons
            Timer tempTimer = new Timer();
            tempTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showOnlyTheButtons();
                        }
                    });
                }
            }, 666);

            //delay on start elapsed time
            final Timer timerTemp = new Timer();
            timerTemp.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //reset and start the elapsed time from zero only if robot
                            // msg is over
                            if (isOverAnimText()) {
                                mGameTimers.resetElapsedTime();
                                mGameTimers.startElapsedTime(mTextViewElapsedTime,
                                        PlayingActivity.this);
                                timerTemp.cancel();
                            }
                        }
                    });
                }
            }, 0, 100);
        }

    }

    private void showOnlyTheButtons() {
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

    private void animateTextWithButtons(final String txt, final TextView tv,
                                        final LinearLayout linearLayoutButtons) {
        timerAnimText = new Timer();
        final char[] charTxt = txt.toCharArray();
        tv.setText("");
        final int[] i = {-1};

        timerAnimText.schedule(new TimerTask() {
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
                            i[0] = -1; //buggy thing SOLVED
                            setFirstTimeMsg(false);
                            timerAnimText.cancel();
                        }
                    }
                });
            }
        }, 0, LETTERS_SPEED);
    }

    private void animateText(final String txt, final TextView tv) {
        setOverAnimText(false);
        timerAnimText = new Timer();
        final char[] charTxt = txt.toCharArray();
        tv.setText("");
        final int[] i = {-1};

        timerAnimText.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        i[0]++;
                        tv.setText(MessageFormat.format("{0}{1}", tv.getText(), charTxt[i[0]]));

                        if (i[0] == charTxt.length - 1) {
                            setOverAnimText(true);
                            i[0] = -1; //buggy thing SOLVED
                            timerAnimText.cancel();
                        }
                    }
                });
            }
        }, 0, LETTERS_SPEED);
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
        mTextViewElapsedTime.setVisibility(View.VISIBLE);
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
        mTextViewElapsedTime.startAnimation(animationFadeIn);
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

    public Boolean isFirstTimeMsg() {
        return firstTimeMsg;
    }

    public void setFirstTimeMsg(Boolean firstTimeMsg) {
        this.firstTimeMsg = firstTimeMsg;
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
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


