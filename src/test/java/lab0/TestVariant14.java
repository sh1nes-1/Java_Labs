package lab0;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestVariant14 {

    private Variant14 variant14 = new Variant14();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] integerTaskProvider() {
        return new Object[][] { { 200, 20 }, { 325, 532 }, { 999, 999 } };
    }

    @Test(dataProvider = "integerTaskProvider")
    public void integerTaskTest(int number, int expected) {
        Assert.assertEquals(variant14.integerTask(number), expected);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] booleanTaskProvider() {
        return new Object[][] { { -1, 0, 1, true }, { 0, -1, 1, true }, { 0, 1, -1, true },
                { -1, -1, -1, false }, { 1, 1, 1, false }, { -1, 1, 1, false } };
    }

    @Test(dataProvider = "booleanTaskProvider")
    public void booleanTaskTest(int A, int B, int C, boolean expectedResult) {
        Assert.assertEquals(variant14.booleanTask(A, B, C), expectedResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] conditionTaskProvider() {
        return new Object[][] { { 2, 3, 1, 1, 3 } };
    }

    @Test(dataProvider = "conditionTaskProvider")
    public void conditionTaskTest(int A, int B, int C, int expectedMin, int expectedMax) {
        Assert.assertEquals(variant14.conditionTask(A, B, C), new Pair<Integer, Integer>(expectedMin, expectedMax));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] switchTaskProvider() {
        TriangleInfo expectedResult = new TriangleInfo(5.0, 1.44,2.88, 10.83);
        return new Object[][] { { 1, 5.0, expectedResult }, { 2, 1.44, expectedResult },
                { 3, 2.88, expectedResult }, { 4, 10.83, expectedResult } };
    }

    @Test(dataProvider = "switchTaskProvider")
    public void switchTaskTest(int pIndex, double pValue, TriangleInfo expectedResult) {
        Assert.assertEquals(variant14.switchTask(pIndex, pValue), expectedResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] forTaskProvider() {
        return new Object[][] { { 2, 4 }, { -3, 9 }, { 9, 81 } };
    }

    @Test(dataProvider = "forTaskProvider")
    public void forTaskTest(int N, int expectedResult) {
        Assert.assertEquals(variant14.forTask(N), expectedResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] whileTaskProvider() {
        return new Object[][] { { 2, 4, 2.08 }, { 3, 11, 3.02 }, { 5, 83, 5.0 }, { 10, 12367, 10.0 } };
    }

    @Test(dataProvider = "whileTaskProvider")
    public void whileTaskTest(int A, int expectedK, double expectedSum) {
        Assert.assertEquals(variant14.whileTask(A), new Pair<Integer, Double>(expectedK, expectedSum));
    }

}
