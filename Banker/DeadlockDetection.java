

public class DeadlockDetection {

  private int processes = 4, resources =
    2, allocate[][], request[][], available[];

  public static void main(String[] args) {
    System.out.println(
      "=======================Deadlock detection algorithm========= "
    );
    DeadlockDetection dd = new DeadlockDetection();
    dd.init();
    dd.show();
    dd.cal();
  }

  public void init() {
    request = new int[processes][resources];

    allocate = new int[processes][resources];
    available = new int[resources];
    allocate =
      new int[][] { { 1,3 }, { 4,1 }, {1,2 }, { 2,0 } };
    request =
      new int[][] { { 1,2 }, { 4,3 }, { 1,7 }, { 5,1 } };
    available = new int[] { 1,4 };
  }

  public void show() {
    System.out.println(
      "Processes     Allocation          Request         Available"
    );
    for (int i = 0; i < processes; i++) {
      System.out.print("P" + (i + 1));
      System.out.print("       ");
      for (int j = 0; j < resources; j++) {
        System.out.print("    " + allocate[i][j]);
      }
      System.out.print("       ");
      for (int j = 0; j < resources; j++) {
        System.out.print("  " + request[i][j]);
      }
      System.out.print("       ");
      if (i == 0) {
        for (int j = 0; j < resources; j++) {
          System.out.print("  " + available[j]);
        }
      }
      System.out.print("\n");
    }
  }

  public void cal() {
    int finish[], k;
    boolean flag = true;
    int dead[];
    int i, j;

    finish = new int[100];
    dead = new int[100];

    for (i = 0; i < processes; i++) {
      finish[i] = 0;
    }
    while (flag == true) {
      flag = false;
      for (i = 0; i < processes; i++) {
        int count = 0;
        for (j = 0; j < resources; j++) {
          //check finish and request <= available
          if ((finish[i] == 0) && (request[i][j] <= available[j])) {
            count++;
            if (count == resources) {
              for (k = 0; k < resources; k++) {
                available[k] += allocate[i][k];
                finish[i] = 1;
                flag = true;
              }
              if (finish[i] == 1) {
                i = 0;
              }
            }
          }
        }
      }
    }
    int countNumberDeadlock = 0;
    boolean isDeadlock = false;
    for (i = 0; i < processes; i++) {
      //System.out.print(finish[i]);
      if (finish[i] == 0) {
        dead[countNumberDeadlock] = i;
        countNumberDeadlock++;
        isDeadlock = true;
      }
    }
    if (isDeadlock == true) {
      System.out.println(
        "======================================================= "
      );
      System.out.println(
        "\n\nSystem is in Deadlock and the Deadlock process are\n"
      );
      for (i = 0; i < countNumberDeadlock; i++) {
        System.out.println("P" + (dead[i] + 1));
      }
    } else {
      System.out.println(
        "======================================================= "
      );
      System.out.println("No deadlock occur");
    }
  }
}
