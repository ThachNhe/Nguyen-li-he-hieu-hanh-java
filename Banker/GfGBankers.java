//Java Program for Bankers Algorithm
public class GfGBankers {

  private int processes = 5, resources =
    4, max[][], allocate[][], need[][], available[];

  public static void main(String[] args) {
    System.out.println(
      "=======================Deadlock detection algorithm========= "
    );
    GfGBankers dd = new GfGBankers();
    dd.init();
    dd.show();
    dd.cal();
  }

  public void init() {
    need = new int[processes][resources];
    max = new int[processes][resources];
    allocate = new int[processes][resources];
    available = new int[resources];
    allocate =
      new int[][] {
            { 2, 0, 0, 1 }, { 3, 1, 2, 1 },
            { 2, 1, 0, 3 }, { 1, 3, 1, 2 }, { 1, 4, 3, 2 }
      }; //P4
    max =
      new int[][] {
            { 4, 2, 1, 2 }, { 5, 2, 5, 2 }, { 2, 3, 1, 6 },
            { 1, 4, 2, 4 }, { 2, 6, 6, 5 }
      }; //P4
    available = new int[] { 3, 3, 2,1 };
  }

  public void show() {
    System.out.println(
      "Processes     Allocation          Max         Available"
    );
    for (int i = 0; i < processes; i++) {
      System.out.print("P" + (i + 1));
      System.out.print("       ");
      for (int j = 0; j < resources; j++) {
        System.out.print("    " + allocate[i][j]);
      }
      System.out.print("       ");
      for (int j = 0; j < resources; j++) {
        System.out.print("  " + max[i][j]);
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
    int finish[], temp, k;
    int safe[] = new int[100];
    boolean flag = true;
    int dead[];
    int i, j;
    finish = new int[100];
    dead = new int[100];

    for (i = 0; i < processes; i++) {
      finish[i] = 0;
    }
    //find need matrix
    for (i = 0; i < processes; i++) {
      for (j = 0; j < resources; j++) {
        need[i][j] = max[i][j] - allocate[i][j];
      }
    }
     System.out.println("======NEED=======");
    for (int l = 0; l < processes; l++) {
      for (int h = 0; h < resources; h++) {
        System.out.print("    " + need[l][h]);
      }
      System.out.println("\n");
    }
    int c = 0;
   
    while (flag == true) {
      flag = false;
      for (i = 0; i < processes; i++) {
        int count = 0;
        for (j = 0; j < resources; j++) {
          if ((finish[i] == 0) && (need[i][j] <= available[j])) {
            count++;
            if (count == resources) {
              safe[c] = i;
              c++;
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
    boolean tmp = false;
    for (i = 0; i < processes; i++) {
      // System.out.print(finish[i] + "    ");
      if (finish[i] == 0) {
        System.out.println(
          "=============================================================="
        );
        System.out.println("UNSAFE");
        tmp = true;
        // break;
        break;
      }
    }
    if (tmp == false) {
      System.out.println(
        "=============================================================="
      );
      System.out.println("SAFE");
      for (i = 0; i < processes; i++) {
        System.out.print("P" + safe[i] + " ");
      }
    }
  }
}
