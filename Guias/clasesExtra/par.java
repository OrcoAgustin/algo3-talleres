package clasesExtra;

public class par<T1, T2> {
    private T1 first;
    private T2 second;

    public par(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setFirst(T1 f) {
        this.first = f;
    }

    public void setSecond(T2 f) {
        this.second= f;
    }
    // Optional: Override toString() for better printing
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
