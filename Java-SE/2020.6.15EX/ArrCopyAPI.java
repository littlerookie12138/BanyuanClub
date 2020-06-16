class ArrCopyAPI {
    public static void main(String[] args) {
        int[] a = new int[0x7FFFFFF];
        int[] b = new int[0xFFFFFFF];

        long start = System.currentTimeMillis();

        System.arraycopy(a, 0, b, 0, a.length);

        long end = System.currentTimeMillis();
        System.out.println("API复制数组执行时间为： " + (end - start));
    }
}
