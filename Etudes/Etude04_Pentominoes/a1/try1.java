import java.util.*;
import java.io.*;
class App {
	
  	public static int[][] I = {  	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 }
    };
    
    public static int[][] F = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Fm = {  { 0, 0, 0, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] L = {  	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Lm = {  { 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] P = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Pm = {  { 0, 0, 0, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] N = {  	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 1, 0, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Nm = {  { 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 0, 1, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] T = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 1, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] U = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 1, 0, 1, 0 },
                 	{ 0, 1, 1, 1, 0 },
                 	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
	
	public static int[][] V = {  	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 1, 1, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
	
	public static int[][] W = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 0, 1, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] X = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 1, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Y = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 }
    };
    
    public static int[][] Ym = {  { 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 }
    };
    
    public static int[][] Z = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Zm = {  { 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] map = {  
    { 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 2, 2, 0, 0, 0 },
    { 0, 0, 0, 2, 2, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0 },
                 	
                 	};
                 	
                 	
	public static int[][] map2 = {  
    { 0, 0, 0, 0, 0, 0, 0, 0 ,0,0,0,0,0,0,0,0,0,0,0,0},
    { 0, 0, 0, 0, 0, 0, 0, 0 ,0,0,0,0,0,0,0,0,0,0,0,0},
    { 0, 0, 0, 0, 0, 0, 0, 0 ,0,0,0,0,0,0,0,0,0,0,0,0}
                 	
                 	};
    
    //static int N = 5;
    
    static void rotate90Clockwise(int a[][]) {
    int N = 5;
    // Traverse each cycle
    for (int i = 0; i < N / 2; i++) {
        for (int j = i; j < N - i - 1; j++) {

            // Swap elements of each cycle
            // in clockwise direction
            int temp = a[i][j];
            a[i][j] = a[N - 1 - j][i];
            a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
            a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
            a[j][N - 1 - i] = temp;
        }
    }
}

public static void main(String[] args) {
    //System.out.println(Arrays.deepToString(I));
    //rotate90Clockwise(I);
    //System.out.println(Arrays.deepToString(I));
    int[][][] pieceList = {
        I,
        F,
        //Fm,
        L,
        //Lm,
        P,
        //Pm,
        N,
        //Nm,
        T,
        U,
        V,
        W,
        X,
        Y,
        //Ym,
        Z
        //Zm
    };
    int holeAmount = 0;
    //int[] row = new int[(map.length * map[1].length) - holeAmount];
    //int[] pieceRow = {0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int sum = 0;
    //int[] finalRow = new int[(map.length * map[1].length) - holeAmount + 18];
    //int [][] finalMatrix = new int[map.length * map[1].length) - holeAmount + 18][]
    int[] finalRow = new int[(map.length * map[1].length) - holeAmount + 12];
    List < int[] > finalList = new ArrayList < int[] > ();
    boolean overHole = false;
    int rowIndex = 0;
    for (int v = 0; v < 12; v++) {
        System.out.println("--------------------------PICECE----------------------------" + v);
        rowIndex = 0;



        for (int r = 0; r < 4; r++) {


            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    rowIndex = 0;
                    int[] row = new int[(map.length * map[1].length) - holeAmount];
                    int[] pieceRow = {
                        //0,
                        //0,
                        //0,
                        //0,
                        //0,
                        //0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0,
                        0
                    };
                    finalRow = new int[(map.length * map[1].length) - holeAmount + 12];




                    for (int k = 0; k < 5; k++) {
                        for (int h = 0; h < 5; h++) {
                            if ((k + i - 2) >= 0 && (h + j - 2) >= 0 && (k + i - 2) < map[i].length && (h + j - 2) < map.length) {
                                map[k + i - 2][h + j - 2] = map[k + i - 2][h + j - 2] + pieceList[v][k][h];
                            }
                        }

                    }
                    overHole = false;
                    for (int o = 0; o < 8; o++) {
                        for (int p = 0; p < 8; p++) {
                            if (map[o][p] == 3) {
                                System.out.println("Break");
                                break;
                            }
                        }
                    }
                    sum = 0;
                    for (int o = 0; o < 8; o++) {
                        for (int p = 0; p < 8; p++) {
                            sum += map[o][p];
                        }
                    }

                    if (sum == 13) {
                        for (int o = 0; o < 8; o++) {
                            for (int p = 0; p < 8; p++) {
                                if (map[o][p] == 3) {
                                    overHole = true;
                                }
                            }
                        }
                        if (!overHole) {

                            pieceRow[v] = 1;

                            for (int n = 0; n < 8; n++) {
                                for (int m = 0; m < 8; m++) {
                                    if (map[n][m] != 2) {
                                        //System.out.println(rowIndex);
                                        row[rowIndex] = map[n][m];
                                        rowIndex++;

                                    }
                                }
                            }
                            for (int n = 0; n < (map.length * map[1].length) - holeAmount + 12; n++) {
                                if (n < 12) {
                                    finalRow[n] = pieceRow[n];

                                } else {

                                    finalRow[n] = row[n - 12];
                                }




                            }
                            finalList.add(finalRow);
                            System.out.println(Arrays.toString(finalRow));
							

                            for (int o = 0; o < 8; o++) {




                                System.out.println(Arrays.toString(map[o]));




                            }
                        }




                    }
                    System.out.println("");
                    for (int o = 0; o < 8; o++) {
                        for (int p = 0; p < 8; p++) {
                            if (map[o][p] == 1 || map[o][p] == 0) {
                                map[o][p] = 0;
                            } else {
                                map[o][p] = 2;
                            }
                        }
                    }




                }
            }
			


            System.out.println("rotation " + r);
            rotate90Clockwise(pieceList[v]);

        }







    }
   
	for (int o = 0; o < finalList.size(); o++) {
		
		System.out.println(Arrays.toString(finalList.get(o)));
		
	}
	System.out.println(finalList.size());





}

};
