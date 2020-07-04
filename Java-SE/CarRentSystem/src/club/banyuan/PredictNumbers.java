package club.banyuan;

import java.util.function.Predicate;

public class PredictNumbers implements Predicate<String> {

    private int from;
    private int to;

    public PredictNumbers(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean test(String s) {
        try {
            int i = Integer.parseInt(s);
            return i >= from && i <= to;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
