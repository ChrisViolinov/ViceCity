package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {

    public ArrayDeque<Player> deadPlayers;


    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        Repository<Gun> gunRepository = mainPlayer.getGunRepository();
        ArrayDeque<Gun> guns = new ArrayDeque<>(gunRepository.getModels());
        ArrayDeque<Player> players = new ArrayDeque<>(civilPlayers);
        deadPlayers = new ArrayDeque<>();

        Player player = players.poll();
        Gun gun = guns.poll();

        while (player != null && gun != null) {
            while (gun.canFire() && player.isAlive()) {
                int fire = gun.fire();
                player.takeLifePoints(fire);
            }

            if (gun.canFire()) {
                deadPlayers.offer(player);
                player = players.poll();
            } else if (player.isAlive()) {
                gun = guns.poll();
            }

        }

        ArrayDeque<Player> playersAliveAndWithGuns = new ArrayDeque<>();
        for (Player player1 : players) {
            if(player1.getGunRepository()!=null){
                playersAliveAndWithGuns.offer(player1);
            }
        }

        for (Player civilPlayer : playersAliveAndWithGuns) {
            ArrayDeque<Gun> playerGuns = new ArrayDeque<>(player.getGunRepository().getModels());
            Gun playerGun = playerGuns.pop();
            while (mainPlayer.isAlive()) {
                while (playerGun.canFire()) {
                    int fire = playerGun.fire();
                    mainPlayer.takeLifePoints(fire);
                }
            }
        }


    }


}


//    The main player starts shooting at all the civil players. When he starts shooting at a civil player, the following rules apply:
//        •	He takes a gun from his guns.
//        •	Every time he shoots, he takes life points from the civil player, which are equal to the bullets that the current gun shoots when the trigger is pulled.
//        •	If the barrel of his gun becomes empty, he reloads from his bullets stock and continues shooting with the current gun, until he uses all of its bullets.
//        •	If his gun runs out of total bullets, he takes the next gun he has and continues shooting.
//        •	He shoots at the current civil player until he / she is alive.
//        •	If the civil player dies, he starts shooting at the next one.
//        •	The main player stops shooting only if he runs out of guns or until all the civil players are dead.
