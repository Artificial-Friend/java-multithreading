public class MyRunnable implements Runnable {
    private final Counter counter;

    public MyRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.race();
    }
}
