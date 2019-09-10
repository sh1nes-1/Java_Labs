package lab0;

public class Pair<T1, T2> {

    public final T1 P1;
    public final T2 P2;

    public Pair(T1 p1, T2 p2) {
        this.P1 = p1;
        this.P2 = p2;
    }

    public boolean equals(Pair<T1, T2> pair) {
        return P1.equals(pair.P1) && P2.equals(pair.P2);
    }

    @Override
    public String toString() {
        return P1.toString() + " " + P2.toString();
    }
}
