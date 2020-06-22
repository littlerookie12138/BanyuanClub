import java.util.Random;
import java.util.Scanner;

    /*
	1. 用户输入多少人参与猜拳（2~5人）
	2. 计算机控制除1个玩家以外的其他人出拳
	3. 每次出拳比较后，输的人被淘汰，剩余玩家继续出拳直到一人胜出。
	4. 每次比较后，输出每个玩家（编号）的出拳和胜负情况
	*/
//1表示拳头 2表示剪刀 3表示布
//场上只能同时出现两种，如果有三种 继续

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        System.out.println("请输入几个人猜拳(2~5人):");
        int num = sc.nextInt();
        int index = num;//定义数组内有效人数

        //生成存放玩家对象的数组
        Player[] players = new Player[num];
        GuessResult gr = new GuessResult();

        //新建num个玩家对象
        for (int i = 0; i < num; i++) {
            players[i] = new Player();
            players[i].setId(i + 1);
        }

        //执行一次数组赋值并计算
        gr.giveNum(players, num);
        //打印一次数组
        for (Player i : players) {
            System.out.println(i.getId() + ":" + i.getResult());
        }
        gr.calVictory(players, num);
        gr.toString(players);
        for (Player player : players) {
            if (!player.isWinOrNot()) {
                index--;
            }
        }

        //当数组内有效用户数大于1时
        while (index > 1) {
            gr.giveNumTwice(players, num);
            for (Player i : players) {
                if (i.isWinOrNot()) {
                    System.out.println(i.getId() + ":" + i.getResult());
                }
            }
            gr.calVictoryTwice(players, num);
            gr.toStringTwice(players);
            for (Player player : players) {
                if (!player.isWinOrNot()) {
                    index--;
                }
            }
        }
    }
}