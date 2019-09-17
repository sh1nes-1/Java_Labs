package lab0;

public class TriangleInfo {

    public double a;
    public double r1;
    public double r2;
    public double s;

    public TriangleInfo() {
        a = r1 = r2 = s = 0;
    }

    public TriangleInfo(double a, double r1, double r2, double s) {
        this.a = a;
        this.r1 = r1;
        this.r2 = r2;
        this.s = s;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        TriangleInfo tr = (TriangleInfo)obj;
        return Utils.RoundTwoSigns(a) == Utils.RoundTwoSigns(tr.a)
                && Utils.RoundTwoSigns(r1) == Utils.RoundTwoSigns(tr.r1)
                && Utils.RoundTwoSigns(r2) == Utils.RoundTwoSigns(tr.r2)
                && Utils.RoundTwoSigns(s) == Utils.RoundTwoSigns(tr.s);
    }

    @Override
    public String toString() {
        return "a = " + a + ", R1 = " + r1 + ", R2 = " + r2 + ", S = " + s;
    }
}
