package Concurrency;

public class PollingExp {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        // Polling with sleep()
        new Thread(() -> {

            for (int i = 0; i < 500; i++)
                PollingExp.counter++;

        }).start();
        while (PollingExp.counter < 100) {
            Thread.sleep(1000);
        }
        System.out.println("Reached");
    }
}
