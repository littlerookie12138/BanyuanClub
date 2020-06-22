import java.util.Random;
import java.util.Scanner;

public class GuessResult {
    //判断是否重复猜拳出现重复，如果出现重复，重来一轮
    public boolean isRedundantOrNot(Player[] players) {
        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;

        for (int i = 0; i < players.length; i++) {
            switch (players[i].getResult()) {
                case 1:
                    countOne++;
                    break;
                case 2:
                    countTwo++;
                    break;
                case 3:
                    countThree++;
                    break;
                default:
                    break;
            }
        }

        if (countOne >= 1 && countTwo >= 1 && countThree >= 1) {
            return true;
        } else if (countOne == players.length || countTwo == players.length || countThree == players.length) {
            return true;
        } else {
            return false;
        }
    }

    //系统帮助其他用户出拳
    public void giveNum(Player[] players, int num) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        //获取用户输入，默认用户为1号用户
        System.out.println("请输入您要出什么:(1表示拳头 2表示剪刀 3表示布)");
        players[0].setResult(sc.nextInt());
        //电脑负责其他人的猜拳
        for (int i = 1; i < num; i++) {
            players[i].setResult(randomInt(1, 4, ran));
        }

        //判断出拳是否有重复
        while (isRedundantOrNot(players)) {
            for (int i = 1; i < num; i++) {
                players[i].setResult(randomInt(1, 4, ran));
            }
            isRedundantOrNot(players);
        }

    }

    //运算过一次结果后的再次出拳
    public void giveNumTwice(Player[] players, int num) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        if (players[0].isWinOrNot()) {
            System.out.println("请输入您要出什么:(1表示拳头 2表示剪刀 3表示布)");
            players[0].setResult(sc.nextInt());
        }
        for (int i = 1; i < num; i++) {
            if (players[i].isWinOrNot()) {
                players[i].setResult(randomInt(1, 4, ran));
            } else {
                break;
            }
        }

        while (isRedundantOrNotTwice(players)) {
            for (int i = 1; i < num; i++) {
                if (players[i].isWinOrNot()) {
                    players[i].setResult(randomInt(1, 4, ran));
                }
            }
            isRedundantOrNot(players);
        }
    }

    //运算过一次结果的再次判断出拳是否重复
    public boolean isRedundantOrNotTwice(Player[] players) {
        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;
        int index = 0;

        for (int i = 0; i < players.length; i++) {
            if (!players[i].isWinOrNot()) {
                continue;
            } else {
                index++;
            }
            switch (players[i].getResult()) {
                case 1:
                    countOne++;
                    break;
                case 2:
                    countTwo++;
                    break;
                case 3:
                    countThree++;
                    break;
                default:
                    break;
            }

        }

        if (countOne >= 1 && countTwo >= 1 && countThree >= 1) {
            return true;
        } else if (countOne == index || countTwo == index || countThree == index) {
            return true;
        } else {
            return false;
        }
    }

    //计算胜负
    public void calVictory(Player[] players, int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (players[i].getResult() == 1 && players[j].getResult() == 2 || players[i].getResult() == 2 && players[j].getResult() == 3 || players[i].getResult() == 3 && players[j].getResult() == 1) {
                    players[i].setWinOrNot(true);
                } else if (players[i].getResult() == players[j].getResult()) {
                    continue;
                } else {
                    players[i].setWinOrNot(false);
                }
            }

        }

    }

    //计算胜负
    public void calVictoryTwice(Player[] players, int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (!players[i].isWinOrNot()) {
                    continue;
                }
                if (players[i].getResult() == 1 && players[j].getResult() == 2 || players[i].getResult() == 2 && players[j].getResult() == 3 || players[i].getResult() == 3 && players[j].getResult() == 1) {
                    players[i].setWinOrNot(true);
                } else if (players[i].getResult() == players[j].getResult()) {
                    continue;
                } else {
                    players[i].setWinOrNot(false);
                }
            }

        }

    }

    //生成随机数
    private static int randomInt(int from, int where, Random ran) {
        int n = where - from;
        if (n > 0) {
            return ran.nextInt(n) + from;
        } else {
            int r = 0;
            do {
                r = ran.nextInt();
            } while (r < from || r >= where);

            return r;
        }
    }

    //打印数组内所有内容
    public void toString(Player[] players) {
        for (Player player : players) {
            String result = "";
            switch (player.getResult()) {
                case 1:
                    result += "拳头";
                    break;
                case 2:
                    result += "剪刀";
                    break;
                case 3:
                    result += "布";
                    break;
                default:
                    result += "还没出呢";
                    break;
            }
            System.out.println("第" + player.getId() + "个玩家出拳为" + result + ",他" + (player.isWinOrNot() ? "赢了" : "输了"));
        }
    }

    //打印数组内所有内容
    public void toStringTwice(Player[] players) {
        for (Player player : players) {
            if (!player.isWinOrNot()) {
                continue;
            }
            String result = "";
            switch (player.getResult()) {
                case 1:
                    result += "拳头";
                    break;
                case 2:
                    result += "剪刀";
                    break;
                case 3:
                    result += "布";
                    break;
                default:
                    result += "还没出呢";
                    break;
            }
            System.out.println("第" + player.getId() + "个玩家出拳为" + result + ",他" + (player.isWinOrNot() ? "赢了" : "输了"));
        }
    }

}
