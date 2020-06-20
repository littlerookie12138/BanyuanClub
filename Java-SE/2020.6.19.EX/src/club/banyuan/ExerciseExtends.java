package club.banyuan;

public class ExerciseExtends extends Father{
    private int childId = 0;

    public ExerciseExtends(int num) {
        super();
        FatherId = 3;
    }


    public void printFather() {
        System.out.println(getFatherId());
        System.out.println(FatherId);
    }

    public static void main(String[] args) {
        ExerciseExtends e = new ExerciseExtends(4);
        e.printFather();
    }
}
