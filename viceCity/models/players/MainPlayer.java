package viceCity.models.players;

public class MainPlayer extends BasePlayer{

    protected static int MAIN_PLAYER_LIFE_POINTS = 100;
    protected static String MAIN_PLAYER_NAME = "Tommy Vercetti";

    public MainPlayer() {
        super(MAIN_PLAYER_NAME, MAIN_PLAYER_LIFE_POINTS);
    }
}
