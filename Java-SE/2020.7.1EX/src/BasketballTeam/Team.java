package BasketballTeam;

import java.util.ArrayList;
import java.util.List;

public class Team {
    List<Player> predicatePlayer = new ArrayList<>();
    List<Player> latePlayer = new ArrayList<>();
    private int index;

    public void replacePlayer(PlayerPosition playerPosition) {
        int indexX = 0;
        Player temp = null;
        for (Player player : predicatePlayer) {
            if (player.getPlayerPosition() == playerPosition) {
                temp = player;
                break;
            }
            indexX++;
        }
        for (Player player : latePlayer) {
            if (player.getPlayerPosition() == playerPosition) {
                predicatePlayer.set(indexX, player);
                player = temp;
            }
        }
    }



    public void addPlayer(Player player) {
        if (index < 5 && !predicatePlayer.contains(player)) {
            predicatePlayer.add(player);
            index++;
        } else {
            latePlayer.add(player);
        }
    }

    public void addLatePlayer(Player player) {
        latePlayer.add(player);
    }

    public void removePlayer(Player player) {
        predicatePlayer.removeIf(player1 -> player1.equals(player));
        latePlayer.removeIf(player1 -> player1.equals(player));
    }

    public void replaceListPlayer(List<Player> list) {
        for (Player player : list) {
            for (Player player1 : latePlayer) {
                if (player.equals(player1)) {

                }
            }
        }
    }
}
