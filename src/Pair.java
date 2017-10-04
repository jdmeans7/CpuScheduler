public class Pair {
    int next;
    String state;
    public Pair(int next,String state){
        this.next = next;
        this.state = state;
    }

    public int getNext() {
        return next;
    }

    public String getState() {
        return state;
    }
}
