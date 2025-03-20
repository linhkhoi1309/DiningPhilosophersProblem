import java.util.Random;

public class Philosopher implements Runnable {
    private DiningPhilosophers dp;
    private int id;
    private Random random = new Random();

    public Philosopher(DiningPhilosophers dp, int id) {
        this.dp = dp;
        this.id = id;
    }

    @Override
    public void run() {
        for (int n = 0; n < 10000; n++) {
            try {
                dp.pickup(id);
                int sleepTime = random.nextInt(6) + 5;
                Thread.sleep(sleepTime * 1000);
                dp.putdown(id);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
