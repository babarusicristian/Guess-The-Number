package cristian.babarusi.guessthenumber;

class GameDatas {

    private static int sTotalGamesPlayed;

    static int getTotalGamesPlayed() {
        return sTotalGamesPlayed;
    }

    static void setTotalGamesPlayed(int sTotalGamesPlayed) {
        GameDatas.sTotalGamesPlayed = sTotalGamesPlayed;
    }
}
