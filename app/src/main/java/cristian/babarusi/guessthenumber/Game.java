package cristian.babarusi.guessthenumber;

import java.util.Random;

public class Game {

    private int mNumberToBeGuessed;
    private int mAttempts;
    private String mRobotMessage;
    private int currentGamesPlayed;

    public void randomizeEasyMode() {
        setNumberToBeGuessed(1 + new Random().nextInt(200));
    }

    public void randomizeHardMode() {
        setNumberToBeGuessed(1 + new Random().nextInt(1000));
    }

    public int getNumberToBeGuessed() {
        return mNumberToBeGuessed;
    }

    public void setNumberToBeGuessed(int numberToBeGuessed) {
        this.mNumberToBeGuessed = numberToBeGuessed;
    }

    public int getAttempts() {
        return mAttempts;
    }

    public void setAttempts(int attempts) {
        mAttempts = attempts;
    }

    public void incrementAttempts() {
        setAttempts(getAttempts()+1);
    }

    public String getRobotMessage() {
        return mRobotMessage;
    }

    public void setRobotMessage(String robotMessage) {
        mRobotMessage = robotMessage;
    }

    public int getCurrentGamesPlayed() {
        return currentGamesPlayed;
    }

    public void setCurrentGamesPlayed(int currentGamesPlayed) {
        this.currentGamesPlayed = currentGamesPlayed;
    }
}
