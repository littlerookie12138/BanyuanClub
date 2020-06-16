public class Player {
    private int id = 0;
    private int result = 0;//出的是什么 1表示拳头 2表示剪刀 3表示布
    private boolean isWinOrNot = false;//false输了 true赢了

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isWinOrNot() {
        return isWinOrNot;
    }

    public void setWinOrNot(boolean winOrNot) {
        isWinOrNot = winOrNot;
    }
}