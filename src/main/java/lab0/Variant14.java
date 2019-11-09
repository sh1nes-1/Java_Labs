package lab0;

public class Variant14 {

    /**
     * @param l length of circle
     * @return Pair with R and S of circle
     */

    public Pair<Double, Double> inputOutputTask(double l) {
        double r = l / 6.28f, s = r * r * 3.14f;
        return new Pair<Double, Double>(r, s);
    }

    /**
     * @param number 3 digit integer
     * @return number where last digit moved to start
     * @throws IllegalArgumentException if number < 100 or number > 999
     */

    public int integerTask(int number) throws IllegalArgumentException {

        if (number < 100 || number > 999)
            throw new IllegalArgumentException("Number should be 3 digit integer");

        final int firstDigits = number / 10;
        final int lastDigit = number % 10;

        return (lastDigit * 100) + firstDigits;
    }

    /**
     * @param a integer
     * @param b integer
     * @param c integer
     * @return true if only one number is positive
     */

    public boolean booleanTask(int a, int b, int c) {
        return (a > 0 && b <= 0 && c <= 0) || (a <= 0 && b > 0 && c <= 0) || (a <= 0 && b <= 0 && c > 0);
    }

    /**
     * @param a integer
     * @param b integer
     * @param c integer
     * @return Pair of min and max Integers
     */

    public Pair<Integer, Integer> conditionTask(int a, int b, int c) {

        if (a >= b) {
            if (b >= c) {
                return new Pair<Integer, Integer>(c, a);
            }
            return new Pair<Integer, Integer>(b, c);
        }

        if (a >= c) {
            return new Pair<Integer, Integer>(c, b);
        }
        return new Pair<Integer, Integer>(a, c);
    }

    /**
     * @param pIndex index of parameter (1 - a, 2 - r1, 3 - r2, 4 - s)
     * @param pValue value of parameter
     * @return information about triangle
     */

    public TriangleInfo switchTask(int pIndex, double pValue) {

        TriangleInfo result = new TriangleInfo();

        final float sqrtThree = 1.73205080757f;

        switch (pIndex) {
            case 1:
                result.a = pValue;

                result.r1 = Utils.RoundTwoSigns(result.a * Math.pow(3, 1 / 2.0) / 6);
                result.r2 = Utils.RoundTwoSigns(result.r1 * 2);
                result.s = Utils.RoundTwoSigns(Math.pow(result.a, 2) * sqrtThree / 4);

                break;
            case 2:
                result.r1 = pValue;

                result.r2 = Utils.RoundTwoSigns(result.r1 * 2);
                result.a = Utils.RoundTwoSigns(6 * result.r1 / sqrtThree);
                result.s = Utils.RoundTwoSigns((sqrtThree * Math.pow(result.a, 2)) / 4);

                break;
            case 3:
                result.r2 = pValue;

                result.r1 = Utils.RoundTwoSigns(result.r2 / 2);
                result.a = Utils.RoundTwoSigns(6 * result.r1 / sqrtThree);
                result.s = Utils.RoundTwoSigns((sqrtThree * Math.pow(result.a, 2)) / 4);

                break;
            case 4:
                result.s = pValue;

                result.a = Utils.RoundTwoSigns(Math.pow((result.s * 4) / sqrtThree, 1 / 2.0));
                result.r1 = Utils.RoundTwoSigns(result.a * sqrtThree / 6);
                result.r2 = Utils.RoundTwoSigns(result.r1 * 2);

                break;
        }

        return result;
    }

    /**
     * @param n integer
     * @return square of n
     */

    public int forTask(int n) {

        int result = 0;

        int absN = (n < 0) ? -n : n;
        for (int i = 1; i <= absN; i++) {
            result += 2 * i - 1;
        }

        return result;
    }

    /**
     * The number A (> 1) is given. Derive the largest integer K for which the sum of 1 + 1/2 +… + 1 / K
     * will be less than A, and this sum itself.
     *
     * @param a double ( a > 1 )
     * @return Pair of k and sum
     * @throws IllegalArgumentException if a <= 1
     */

    public Pair<Integer, Double> whileTask(double a) throws IllegalArgumentException {

        if (a <= 1)
            throw new IllegalArgumentException("A must be > 1");

        int k = 1;
        double sum = 0;
        while (sum < a) {
            sum += 1 / (double) k++;
        }

        return new Pair<Integer, Double>(--k, Utils.RoundTwoSigns(sum));
    }


    /**
     * @param arr array of Integer
     * @return Pair of two arrays. First - array of even numbers. Second - not even numbers
     */

    public Pair<Integer[], Integer[]> arrayTask(Integer[] arr) {

        Integer[] result1 = new Integer[arr.length / 2 + arr.length % 2];

        int k = 0;
        for (int i = 0; i < arr.length; i += 2)
            result1[k++] = arr[i];

        Integer[] result2 = new Integer[arr.length / 2];
        k = 0;
        for (int i = 1; i < arr.length; i += 2)
            result2[k++] = arr[i];

        return new Pair<Integer[], Integer[]>(result1, result2);
    }

    /**
     * @param arr two dimension array of Integer
     * @return index of last row which contains only even numbers
     */

    public Integer twoDimensionTask(Integer[][] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            boolean evenNumbersOnly = true;
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] % 2 != 0) {
                    evenNumbersOnly = false;
                    break;
                }
            }

            if (evenNumbersOnly)
                return i + 1;
        }
        return 0;
    }
}
