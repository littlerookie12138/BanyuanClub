public class randomInt {
    public static void main(String[] args) {
        System.out.println(ranInt(1000, 1078));
    }

    public static int ranInt(int from, int where) {
        int n = where - from;
        if (n > 0) {
            return nextInt(n) + from;
        } else {
            int r = 0;
            do {
                r = nextInt();
            } while (r < from || r >= where);
            return r;
        }
    }


}
