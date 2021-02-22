import java.util.Date;
import org.apache.log4j.Logger;

public class Counter {
    private static final Logger logger = Logger.getLogger(Counter.class);
    private int num = 0;

    public void race() {
        while (num < Math.sqrt(Math.sqrt(new Date().getTime()))) {
            ++num;
            logger.info(Thread.currentThread().getName() + " : " + num);
        }
    }
}
