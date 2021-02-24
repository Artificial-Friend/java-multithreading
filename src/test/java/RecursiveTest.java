import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecursiveTest {
    private static final int MILLION = 1_000_000;
    private static Recursive recursive;
    private static ForkJoinPool forkJoinPool;

    @BeforeAll
    public static void beforeAll() {
        List<Integer> million_items = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
        recursive = new Recursive(million_items, 0, million_items.size() - 1);
        forkJoinPool = ForkJoinPool.commonPool();
    }

    @Test
    public void getSum_Ok() {
        Assertions.assertEquals(MILLION, forkJoinPool.invoke(recursive));
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        Assertions.assertEquals(60, forkJoinPool.invoke(new Recursive(list, 0, list.size() - 1)));
    }
}
