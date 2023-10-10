//Java Program for Bankers Algorithm
public class Test
{
	int n = 5; // Number of processes 
	int m = 4; // Number of resources
	int need[][] = new int[n][m];
	int [][]max;
	int [][]alloc;
	int []avail;
	int safeSequence[] = new int[n];

	void initializeValues()
	{
	// P0, P1, P2, P3, P4 are the Process names here 
	// Allocation Matrix 
	alloc = new int[][] { { 2, 0,0,1 }, //P0 
				{ 3,1,2,1}, //P1
				{ 2,1,0,3 }, //P2
				{ 1,3,1,2 }, //P3
				{ 1, 4 ,3 ,2 } }; //P4
		
	// MAX Matrix
	max = new int[][] { { 4,2,1,2 }, //P0
			{ 5,2,5,2 }, //P1
			{ 2,3,1,6 }, //P2
			{ 1,4,2,4 }, //P3 
			{ 3,6,6,5 } }; //P4
	
	// Available Resources 
	avail = new int[] { 3, 3, 2,1 }; 
	}

	void isSafe()
	{
	int count=0;
	
	//visited array to find the already allocated process
	boolean visited[] = new boolean[n]; 
	for (int i = 0;i < n; i++)
	{
		visited[i] = false;
	}
		
	//work array to store the copy of available resources
	int work[] = new int[m]; 
	for (int i = 0;i < m; i++)
	{
		work[i] = avail[i];
	}

	while (count<n)
	{
		boolean flag = false;
		for (int i = 0;i < n; i++)
		{
			if (visited[i] == false)
			{
			int j;
			for (j = 0;j < m; j++)
			{	 
				if (need[i][j] > work[j])
				break;
			}
			if (j == m)
			{
			safeSequence[count++]=i;
			visited[i]=true;
			flag=true;
						
				for (j = 0;j < m; j++)
				{
				work[j] = work[j]+alloc[i][j];
				}
			}
			}
		}
		if (flag == false)
		{
			break;
		}
	}
	if (count < n)
	{
		System.out.println("The System is UnSafe!");
	}
	else
	{
		//System.out.println("The given System is Safe");
		System.out.println("Following is the SAFE Sequence");
				for (int i = 0;i < n; i++)
		{
			System.out.print("P" + safeSequence[i]);
			if (i != n-1)
			System.out.print(" -> ");
		}
	}
	}
	
	void calculateNeed()
	{
	for (int i = 0;i < n; i++)
	{
		for (int j = 0;j < m; j++)
		{
		need[i][j] = max[i][j]-alloc[i][j];
		}
	}	 
	}

	public static void main(String[] args)
	{ 
	int i, j, k; 
	Test gfg = new Test();
		
	gfg.initializeValues(); 
	//Calculate the Need Matrix 
	gfg.calculateNeed();		 
			
	// Check whether system is in safe state or not 
	gfg.isSafe();	 
	}
}
