package club.banyuan.judge;

public class N {
    static int i = 111;

    int j = 222;

    {
        // 111 - 223
        i = i++ - ++j; //-112
    }

}
