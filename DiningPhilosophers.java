import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public void pickup(int i) {
    }

    public void putdown(int i) {
    }

    public static void main(String[] args) {
        DiningPhilosophers dp = new DiningPhilosophers();
        Thread[] philosophers = new Thread[5];

        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Thread(new Philosopher(dp, i));
            philosophers[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
