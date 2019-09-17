package lab0;

public class Variant14 {

    public Pair<Float, Float> inputOutputTask(int l) {
        return null;
    }

    /**
     *
     * @param number 3 digit integer
     * @return number where last digit moved to start
     */

    public int integerTask(int number) {

        if (number < 100 || number > 999)
            throw new IllegalArgumentException("Number should be 3 digit integer");

        final int firstDigits = number / 10;
        final int lastDigit = number % 10;

        return (lastDigit * 100) + firstDigits;
    }

    /**
     *
     * @param a some integer
     * @param b some integer
     * @param c some integer
     * @return true if only one number is positive
     */

    public boolean booleanTask(int a, int b, int c) {
        return (a > 0 && b <= 0 && c <= 0) || (a <= 0 && b > 0 && c <= 0) || (a <= 0 && b <= 0 && c > 0);
    }

    /**
     *
     * @param a some integer
     * @param b some integer
     * @param c some integer
     * @return Pair of min and max Integers
     */

    public Pair<Integer, Integer> conditionTask(int a, int b, int c) {

        int min, max;

        if (a >= b) {
            if (b >= c) {
                min = c;
                max = a;
            } else {
                min = b;
                max = c;
            }
        } else {
            if (a >= c) {
                min = c;
                max = b;
            } else {
                min = a;
                max = c;
            }
        }

        return new Pair<Integer, Integer>(min, max);
    }

    /**
     *
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
     *
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
     *
     * @param a double ( a > 1 )
     * @return min k and sum
     */

    public Pair<Integer, Double> whileTask(double a) {

        if (a <= 1)
            throw new IllegalArgumentException("A must be > 1");

        int k = 1;
        double sum = 0;
        while (sum < a) {
            sum += 1 / (double) k++;
        }

        return new Pair<Integer, Double>(--k, Utils.RoundTwoSigns(sum));
    }
}
