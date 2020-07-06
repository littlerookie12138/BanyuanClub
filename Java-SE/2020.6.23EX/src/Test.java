public class Test
{
    public static void main(String[] args)
    {
        String s1 = "good";
        String str = new String(s1);
        char[] char1 = {'a', 'b', 'c'};
        change(str, char1);

        System.out.println(str + char1[0] );

    }

    public static void change (String str, char[] char1) {
        str = "test ok";
        char1[0] = 'g';
    }

}

