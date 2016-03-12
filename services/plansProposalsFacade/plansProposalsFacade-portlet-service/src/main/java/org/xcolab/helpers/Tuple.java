package org.xcolab.helpers;

/**
 * Created by steve on 04/03/16.
 */
public class Tuple<X, Y> {

    private final X left;
    private final Y right;

    public Tuple(X left, Y right) {
        this.left = left;
        this.right = right;
    }

    public X getLeft() {
        return left;
    }

    public Y getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (!left.equals(tuple.left)) return false;
        return right.equals(tuple.right);

    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
