import java.util.concurrent.Semaphore;

public class SemaphoreTest {
  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(0);
    
    Thread P1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          semaphore.acquire();
          System.out.println("1");
          System.out.println("2");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    
    Thread P2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("3");
        System.out.println("4");
        semaphore.release();
      }
    });
    
    P1.start();
    P2.start();
  }
}