package lab0;

public class Variant14 {

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
     * @param A some integer
     * @param B some integer
     * @param C some integer
     * @return true if only one number is positive
     */

    public boolean booleanTask(int A, int B, int C) {
        return (A > 0 && B <= 0 && C <= 0) || (A <= 0 && B > 0 && C <= 0) || (A <= 0 && B <= 0 && C > 0);
    }

    /**
     *
     * @param A some integer
     * @param B some integer
     * @param C some integer
     * @return Pair of min and max Integers
     */

    public Pair<Integer, Integer> conditionTask(int A, int B, int C) {

        final Integer min = Math.min(Math.min(A, B), C);
        final Integer max = Math.max(Math.max(A, B), C);

        return new Pair<Integer, Integer>(min, max);
    }

    /**
     *
     * @param pIndex index of parameter
     * @param pValue value of parameter
     * @return information about triangle
     */

    public TriangleInfo switchTask(int pIndex, double pValue) {

        TriangleInfo result = new TriangleInfo();

        final float sqrtThree = 1.73205080757f;

        switch (pIndex) {
            case 1:
                result.a = pValue;

                result.R1 = Utils.RoundTwoSigns(result.a * Math.pow(3, 1 / 2.0) / 6);
                result.R2 = Utils.RoundTwoSigns(result.R1 * 2);
                result.S = Utils.RoundTwoSigns(Math.pow(result.a, 2) * Math.pow(3, 1 / 2.0) / 4);

                break;
            case 2:
                result.R1 = pValue;

                result.R2 = Utils.RoundTwoSigns(result.R1 * 2);
                result.a = Utils.RoundTwoSigns(6 * result.R1 / sqrtThree);
                result.S = Utils.RoundTwoSigns((sqrtThree * Math.pow(result.a, 2)) / 4);

                break;
            case 3:
                result.R2 = pValue;

                result.R1 = Utils.RoundTwoSigns(result.R2 / 2);
                result.a = Utils.RoundTwoSigns(6 * result.R1 / sqrtThree);
                result.S = Utils.RoundTwoSigns((sqrtThree * Math.pow(result.a, 2)) / 4);

                break;
            case 4:
                result.S = pValue;

                result.a = Utils.RoundTwoSigns(Math.pow((result.S * 4) / sqrtThree, 1 / 2.0));
                result.R1 = Utils.RoundTwoSigns(result.a * Math.pow(3, 1 / 2.0) / 6);
                result.R2 = Utils.RoundTwoSigns(result.R1 * 2);

                break;
        }

        return result;
    }

    /**
     *
     * @param N integer
     * @return square of N
     */

    public int forTask(int N) {

        int result = 0;

        int n = (N < 0) ? -N : N;
        for (int i = 1; i <= n; i++) {
            result += 2 * i - 1;
        }

        return result;
    }

    /**
     *
     * @param A double ( A > 1 )
     * @return min K and Sum
     */

    public Pair<Integer, Double> whileTask(double A) {

        if (A <= 1)
            throw new IllegalArgumentException("A must be > 1");

        int K = 1;
        double sum = 0;
        while (sum < A) {
            sum += 1 / (double) K++;
        }

        return new Pair<Integer, Double>(--K, Utils.RoundTwoSigns(sum));
    }
}
