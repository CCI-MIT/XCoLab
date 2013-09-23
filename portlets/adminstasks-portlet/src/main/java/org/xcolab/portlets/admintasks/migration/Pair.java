package org.xcolab.portlets.admintasks.migration;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/19/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pair<L,R> {

    private L left;
    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public void setLeft(L left){
        this.left = left;
    }
    public void setRight(R right){
        this.right = right;
    }
    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

    @Override
    public String toString(){
        return "Pair(" + getLeft() + "," + getRight() + ")";
    }

}
