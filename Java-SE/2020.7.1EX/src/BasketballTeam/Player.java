package BasketballTeam;

import java.util.Objects;

public class Player {
    private String name;
    private int num;
    private PlayerPosition playerPosition;

    public Player(PlayerPosition playerPosition, String name, int num) {
        this.playerPosition = playerPosition;
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return num == player.num &&
                Objects.equals(name, player.name) &&
                playerPosition == player.playerPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, num, playerPosition);
    }
}
