import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RecursiveAdderTest {
    private static final int EXPECTED_SIZE_MILLION = 1_000_000;
    private static final int EXPECTED_SIZE_SIXTY = 60;
    private static RecursiveAdder recursiveAdder;
    private static ForkJoinPool forkJoinPool;
    public static List<Integer> testList;

    @BeforeAll
    public static void beforeAll() {
        List<Integer> million_items
                = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
        recursiveAdder = new RecursiveAdder(million_items, 0, million_items.size() - 1);
        forkJoinPool = ForkJoinPool.commonPool();
        testList = new ArrayList<>();
        testList.add(10);
        testList.add(20);
        testList.add(30);

    }

    @Test
    public void getSum_Ok() {
        Assertions.assertEquals(EXPECTED_SIZE_MILLION, forkJoinPool.invoke(recursiveAdder));
        Assertions.assertEquals(EXPECTED_SIZE_SIXTY,
                forkJoinPool.invoke(new RecursiveAdder(testList, 0, testList.size() - 1)));
    }
}
