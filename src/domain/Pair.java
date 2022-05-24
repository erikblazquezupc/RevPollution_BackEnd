package domain;

public class Pair<T, K>{

    private T first;
    private K second;

    public Pair(T f, K s) {
        this.first = f;
        this.second = s;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public void setFirst(T f) {
        this.first = f;
    }

    public void setSecond(K s) {
        this.second = s;
    }

    public String toString() {
        return "[" + first + "," + second + "]";
    }
}