public class FenwickTree {
    private int[] tree;
    private int size;
    public int iterations;

    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size + 1];
    }

    public void update(int index, int value) {
        index++;
        while (index <= size) {
            tree[index] += value;
            index += index & -index;
            iterations++;
        }
    }
    public int prefSum(int index) {
        index++;
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
            iterations++;
        }
        return sum;
    }
    public void delete(int index) {
        int valueToRemove = prefSum(index) - prefSum(index - 1);
        update(index, -valueToRemove);
    }

    public int rangeSum(int left, int right) {
        return prefSum(right) - prefSum(left - 1);
    }
}
