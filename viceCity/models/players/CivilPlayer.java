package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    protected static int CIVIL_PLAYER_LIFE_POINTS = 50;

    public CivilPlayer(String name) {
        super(name, CIVIL_PLAYER_LIFE_POINTS);
        this.name=name;
    }
}
