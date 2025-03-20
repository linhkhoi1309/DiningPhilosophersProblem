import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    enum State {THINKING, HUNGRY, EATING};
    State[] states = new State[5];
    private Semaphore[] forks = new Semaphore[5];
    private Semaphore mutex = new Semaphore(1);

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(0);
            states[i] = State.THINKING;
        }
    }

    public void pickup(int i)  throws InterruptedException {
        mutex.acquire();
        states[i] = State.HUNGRY;
        test(i);
        mutex.release();
        forks[i].acquire();
    }

    public void putdown(int i) throws InterruptedException {
        mutex.acquire();
        states[i] = State.THINKING;
        test((i + 1) % 5);
        test((i + 4) % 5);
        mutex.release();
    }

    public void test(int i){
        if(states[i] == State.HUNGRY && states[(i + 1) % 5] != State.EATING && states[(i + 4) % 5] != State.EATING){
            states[i] = State.EATING;
            forks[i].release();
        }
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
