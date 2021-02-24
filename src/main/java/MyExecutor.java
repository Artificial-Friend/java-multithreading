import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.collections4.ListUtils;

public class MyExecutor {
    public static final int THREADS = Runtime.getRuntime().availableProcessors();
    private final List<Integer> numbers;

    public MyExecutor(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Integer calculate() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        List<List<Integer>> partition = ListUtils.partition(numbers, numbers.size() / THREADS);
        List<MyCallable> callables = new ArrayList<>();
        for (int i = 0; i < partition.size(); i++) {
            callables.add(new MyCallable(partition.get(i)));
        }
        try {
            int result = 0;
            List<Future<Integer>> futures = executorService.invokeAll(callables);
            executorService.shutdown();
            for (Future<Integer> future : futures) {
                result += future.get();
            }
            executorService.shutdown();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(getClass().getName()
                    + " Threw an error and those threads has failed to complete: "
                    + executorService.shutdownNow().toString());
        }
    }
}
