import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Cake[] cakes = new Cake[10];
        System.out.println("订单号" + "       单价（每公斤）  " + "      重量(类型)");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                cakes[i] = new OrderCake(i + 1, Math.random() * 15, Math.random() * 2);
            } else {
                cakes[i] = new ReadyMadeCake(i + 1, random.nextDouble(), random.nextInt(50));
            }
        }

        for (Cake o : cakes) {
            System.out.println(o.toString());
        }

        //计算所有蛋糕的总价
        double result = 0.0;
        for (Cake o : cakes) {
            result += o.calcPrice();
        }
        System.out.println("所有的蛋糕总价是" + result);

        double RMCakeTotal = 0.0;
        double RMCakeQuantities = 0.0;
        //显示ReadyMadeCake蛋糕的总价和数量之和；
        for (Cake i : cakes) {
            if (i instanceof ReadyMadeCake) {
                RMCakeTotal += i.calcPrice();
                RMCakeQuantities += ((ReadyMadeCake) i).getQuantity();
            }
        }
        System.out.println("ReadyMadeCake蛋糕的总价" + RMCakeTotal + ";数量之和" + RMCakeQuantities);

        //显示最高价格出售的蛋糕的信息。
        double highestPrice = 0.0;
        Cake highestCake = null;
        for (Cake i : cakes) {
            if (i.calcPrice() > highestPrice) {
                highestPrice = i.calcPrice();
                highestCake = i;
            }
        }
//        for (Cake i : cakes) {
//            if (i.calcPrice() == highestPrice) {
//                System.out.println("售价最高的蛋糕的编号为:" + i.getId() + ",单价为:" + i.getPrice() + " " + i.printType());
//            }
//        }

        System.out.println("售价最高的蛋糕的编号为:" + highestCake.getId() + ",单价为:" + highestCake.getPrice() + " " + highestCake.printType());
    }
}
