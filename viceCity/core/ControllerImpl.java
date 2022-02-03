package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.*;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.*;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private Player mainPlayer;
    private Map<String, Player> players;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl(){
        this.mainPlayer = new MainPlayer();
        this.players = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        players.put(name, new CivilPlayer(name));
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        if(type.equals("Rifle")){
            Gun gun = new Rifle(name);
            guns.offer(gun);
        } else if (type.equals("Pistol")){
            Gun gun = new Pistol(name);
            guns.offer(gun);
        } else {
            return GUN_TYPE_INVALID;
        }

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = guns.poll();
        if(gun == null){
            return GUN_QUEUE_IS_EMPTY;
        } else if(name.equals("Vercetti")){
           mainPlayer.getGunRepository().add(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER,gun.getName() ,mainPlayer.getName());
        } else if(players.containsKey(name)){
            players.get(name).getGunRepository().add(gun);
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
        }
        guns.offer(gun);
        return CIVIL_PLAYER_DOES_NOT_EXIST;
    }

    @Override
    public String fight() {

        neighbourhood.action(mainPlayer,players.values());
        ArrayDeque<Player> deadPlayers = new ArrayDeque<>();
        for (Player player : players.values()) {
            if(!player.isAlive()){
                deadPlayers.offer(player);
            }
        }

        if(mainPlayer.getLifePoints()==100 && players
                .values().stream().allMatch(p -> p.getLifePoints() == 50)){
            return FIGHT_HOT_HAPPENED;
        } else {
            StringBuilder build = new StringBuilder();
            build.append(FIGHT_HAPPENED).append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE,mainPlayer.getLifePoints()))
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,deadPlayers.size() ))
                    .append(System.lineSeparator())
                    .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, players.size() - deadPlayers.size()));
            return build.toString();
        }
    }
}
