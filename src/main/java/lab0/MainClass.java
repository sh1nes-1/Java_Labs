package lab0;

public class MainClass {

    public static void main(String[] args) {

        Variant14 variant14 = new Variant14();

        System.out.println("Task 1: " + variant14.integerTask(325));
        System.out.println("Task 2: " + variant14.booleanTask(-1, 0, 1));
        System.out.println("Task 3: " + variant14.conditionTask(2, 3, 1));
        System.out.println("Task 4: " + variant14.switchTask(1, 5));
        System.out.println("Task 5: " + variant14.forTask(5));
        System.out.println("Task 6: " + variant14.whileTask(5));
    }

}
