import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MyExecutorTest {
    private static final int MILLION = 1_000_000;
    private static MyExecutor executor;

    @BeforeAll
    public static void beforeAll() {
        List<Integer> million_items = Stream.iterate(1, n -> n).limit(1_000_000).collect(Collectors.toList());
        executor = new MyExecutor(million_items);
    }

    @Test
    public void getSum_Ok() {
        Assertions.assertEquals(MILLION, executor.calculate());
        Assertions.assertNotEquals(1, executor.calculate());
    }
}
