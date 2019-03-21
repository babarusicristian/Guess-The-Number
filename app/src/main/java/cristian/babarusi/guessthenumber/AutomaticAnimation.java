package cristian.babarusi.guessthenumber;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cristian.babarusi.guessthenumber.utils.Logging;

public class AutomaticAnimation extends AppCompatActivity {

    private Context mContext;
    private int mPreviousRandValue;
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

    //constructor
    public AutomaticAnimation(Activity activity, TextView textView0, TextView textViewOne,
                              TextView textViewTwo, TextView textViewThree, TextView textViewFour,
                              TextView textViewFive, TextView textViewSix, TextView textViewSeven,
                              TextView textViewEight, TextView textViewNine,
                              TextView textViewQuestionMark) {

        this.mTextViewZero = textView0;
        this.mTextViewOne = textViewOne;
        this.mTextViewTwo = textViewTwo;
        this.mTextViewThree = textViewTwo;
        this.mTextViewThree = textViewThree;
        this.mTextViewFour = textViewFour;
        this.mTextViewFive = textViewFive;
        this.mTextViewSix = textViewSix;
        this.mTextViewSeven = textViewSeven;
        this.mTextViewEight = textViewEight;
        this.mTextViewNine = textViewNine;
        this.mTextViewQuestionMark = textViewQuestionMark;

        initView(activity);
    }

    private void initView(Activity activity) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        mTextViewZero = view.findViewById(R.id.text_view_zero);
        mTextViewOne = view.findViewById(R.id.text_view_one);
        mTextViewTwo = view.findViewById(R.id.text_view_two);
        mTextViewThree = view.findViewById(R.id.text_view_three);
        mTextViewFour = view.findViewById(R.id.text_view_four);
        mTextViewFive = view.findViewById(R.id.text_view_five);
        mTextViewSix = view.findViewById(R.id.text_view_six);
        mTextViewSeven = view.findViewById(R.id.text_view_seven);
        mTextViewEight = view.findViewById(R.id.text_view_eight);
        mTextViewNine = view.findViewById(R.id.text_view_nine);
        mTextViewQuestionMark = view.findViewById(R.id.text_view_question_mark);
    }

    public void startIt(Context context) {
        mContext = context;
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
                        Logging.show(mContext, "mRandValue: " + mRandValue);
                        Logging.show(mContext, "mRandValue2: " + mRandValue2);

                        //if random is duplicate
                        if (mRandValue == mPreviousRandValue) {
                            return;
                        }

                        Animation animZoomIn =
                                AnimationUtils.loadAnimation(mContext,
                                        R.anim.zoom_in);

                        final Animation animZoomBack =
                                AnimationUtils.loadAnimation(mContext,
                                        R.anim.zoom_back);

                        Animation animZoomIn2 =
                                AnimationUtils.loadAnimation(mContext,
                                        R.anim.zoom_in_2);

                        final Animation animZoomBack2 =
                                AnimationUtils.loadAnimation(mContext,
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
        }, 0, 1300);
    }
}
