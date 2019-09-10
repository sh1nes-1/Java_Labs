package lab0;

public class TriangleInfo {

    public double a;
    public double R1;
    public double R2;
    public double S;

    public TriangleInfo() {
        a = R1 = R2 = S = 0;
    }

    public TriangleInfo(double a, double R1, double R2, double S) {
        this.a = a;
        this.R1 = R1;
        this.R2 = R2;
        this.S = S;
    }

    public boolean equals(TriangleInfo tr) {
        return Math.round(a) == Math.round(tr.a)
                && Math.round(R1) == Math.round(tr.R1)
                && Math.round(R2) == Math.round(tr.R2)
                && Math.round(S) == Math.round(tr.S);
    }

    @Override
    public String toString() {
        return "a = " + a + ", R1 = " + R1 + ", R2 = " + R2 + ", S = " + S;
    }
}
