import java.util.List;
import java.util.concurrent.Callable;

public class CallableSumCalculator implements Callable<Integer> {
    private final List<Integer> list;

    public CallableSumCalculator(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() {
        return list.stream().reduce(0, Integer::sum);
    }
}
