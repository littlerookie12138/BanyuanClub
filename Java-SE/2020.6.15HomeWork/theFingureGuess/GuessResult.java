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
        } else {
            return false;
        }

    }
}
