import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Recursive extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 100_000;
    private final List<Integer> list;
    private final int start;
    private final int end;

    public Recursive(List<Integer> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) > THRESHOLD) {
            int half = (start + end) / 2;
            Recursive firstHalf = new Recursive(list, start, half);
            Recursive secondHalf = new Recursive(list, half + 1, end);
            firstHalf.fork();
            Integer integer1 = secondHalf.compute();
            Integer integer2 = firstHalf.join();
            return integer1 + integer2;
        }
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += list.get(i);
        }
        return sum;
    }
}
