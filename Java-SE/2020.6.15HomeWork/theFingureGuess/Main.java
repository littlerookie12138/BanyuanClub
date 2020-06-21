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

        //生成存放玩家对象的数组
        Player[] players = new Player[num];

        //生成获取存放玩家出拳的数组
        int[] result = new int[num];

        //新建num个玩家对象
        for (int i = 0; i < num; i++) {
            players[i] = new Player();
            players[i].setId(i + 1);
        }

        //获取用户输入，默认用户为1号用户
        System.out.println("请输入您要出什么:(1表示拳头 2表示剪刀 3表示布)");
        players[0].setResult(sc.nextInt());
        //电脑负责其他人的猜拳
        for (int i = 1; i < num; i++) {
            players[i].setResult(randomInt(1, 4, ran));
        }

        //判断出拳是否有重复
        GuessResult guessNum = new GuessResult();
        while(guessNum.isRedundantOrNot(players)){
            for (int i = 1; i < num; i++) {
                players[i].setResult(randomInt(1, 4, ran));
            }
        }


        for (Player i : players) {
            System.out.println(i.getId() + ":" + i.getResult());
        }
    }

    //计算猜拳结果
//    public static int getResult(Player[] players, int num) {
//        for (int i = 0; i < num; i++) {
////            players[i]
//        }
//        int[] temp = new int[num];
//        System.arraycopy(result, 0, temp, 0, len);
//        int index = len;
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                if (result[i] == 0) {
//                    index--;
//                    break;
//                }
//                //如果result[i] == 1 2删掉，3留下删1
//                if (result[i] == 1 && result[j] == 2 || result[i] == 2 && result[j] == 3 || result[i] == 3 && result[j] == 1) {
//                    result[j] = 0;
//                    index--;//记录剩下几个元素
//                }
//
//            }
//
//        }
//        for (int i = 0; i < len; i++) {
//            if (result[i] == 0) {
//                String tempp = "";
//                switch (temp[i]) {
//                    case 0:
//                        break;
//                    case 1:
//                        tempp = "拳头";
//                        break;
//                    case 2:
//                        tempp = "剪刀";
//                        break;
//                    case 3:
//                        tempp = "布";
//                        break;
//                }
//                System.out.println("第" + (i + 1) + "号出了" + tempp + "输了");
//            } else {
//                String tempp = "";
//                switch (temp[i]) {
//                    case 1:
//                        tempp = "拳头";
//                        break;
//                    case 2:
//                        tempp = "剪刀";
//                        break;
//                    case 3:
//                        tempp = "布";
//                        break;
//                }
//                System.out.println("第" + (i + 1) + "号出了" + tempp + "赢了");
//            }
//
//        }
//        return index;
//    }
//
    //生成随机数
    public static int randomInt(int from, int where, Random ran) {
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
}