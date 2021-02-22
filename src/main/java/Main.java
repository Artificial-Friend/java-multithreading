public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread runnable = new Thread(new MyRunnable(counter));
        MyThread thread = new MyThread(counter);
        runnable.start();
        thread.start();
    }
}
