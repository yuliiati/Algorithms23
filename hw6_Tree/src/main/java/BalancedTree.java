public interface BalancedTree {
    void add(int data);
    boolean search(int data);
    void remove(int data);
    int getDepth();
    int getSize();
    void traversalWidth();
    void traversalDepth();
}
