package lab0;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Pair<T1, T2> {

    public final T1 p1;
    public final T2 p2;

    public Pair(T1 p1, T2 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Pair pair = (Pair)obj;

        if (p1 instanceof Integer[]) {
            return Arrays.equals((Integer[])p1, (Integer[])pair.p1) && Arrays.equals((Integer[])p2, (Integer[])pair.p2);
        }

        return p1.equals(pair.p1) && p2.equals(pair.p2);
    }

    @Override
    public String toString() {
        return p1.toString() + " " + p2.toString();
    }
}
