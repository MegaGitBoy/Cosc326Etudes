import java.util.*;
import java.io.*;
public class App {

public static int[][] O = {  	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 }
    };
    
    public static int[][] R = {  	{ 0, 0, 0, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Rm = {  { 0, 0, 0, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Q = {  	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 1, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] Qm = {  { 0, 0, 1, 0, 0 },
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
    
    public static int[][] Sm = {  	
    				{ 0, 0, 1, 0, 0 },
                 	{ 0, 0, 1, 0, 0 },
                 	{ 0, 1, 1, 0, 0 },
                 	{ 0, 1, 0, 0, 0 },
                 	{ 0, 0, 0, 0, 0 }
    };
    
    public static int[][] S = {  
    				{ 0, 0, 1, 0, 0 },
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
    public static int[][] maperwtwert = {  
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
                 	
                 	};

    public static int[][] mapert = {
        {
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
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        },
        {
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
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        },
        {
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
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        }

    };

    public static int[][] map5tyf = {
        {
            0,
            0,
            0,
            2
        },
        {
            0,
            0,
            0,
            2
        },
        {
            0,
            0,
            0,
            0
        }

    };
    
    public static int[][] maewrp = {
    
    	{0,0,2,2},
    	{0,0,2,0},
    	{0,0,0,0},
    	{2,2,0,0},
    	{2,0,0,0},
    	{2,2,0,2}
    
    };
    
    public static int[][] map234 = {
    	
    	{0,0,0,0,0,0},
    	{0,0,0,0,0,0},
    	{0,0,0,0,2,2},
    	{2,0,0,0,0,2},
    	
    	
    
    };
    
    public static int[][] map234234 = {
    	
    	{0,0,0},
    	{0,0,0},
    	{0,0,0},
    	{2,0,2},
    	
    	
    
    };
	public static int indexOfSmallest(int[] array){

    // add this
    if (array.length == 0)
        return -1;

    int index = 0;
    int min = array[index];

    for (int i = 1; i < array.length; i++){
        if (array[i] < min && array[i] != 0){
        min = array[i];
        index = i;
        }
    }
    return index;
}
    //static int N = 5;
	public static int pieceAmount = 12;
	public static int holeAmount = 4;
    public static int[] oneArray = new int[(map.length * map[0].length) - holeAmount + pieceAmount];
    public static List < Integer > rowSolved = new ArrayList < > ();


  


    public static int backTrack(List < List < Integer >> l) {
        List < Integer > colDel = new ArrayList < > ();
        List < List < Integer >> copy = new ArrayList < > ();
		int[] one= new int[l.get(0).size()-1];

        int colExpand = 0                                                                                                                                   ;
        int rowNum = 0;
		


			
		
			for (int n = 0; n < l.size(); n++) {
			for (int j = 0; j < l.get(0).size() - 1; j++) {
                one[j] += l.get(n).get(j);
                }
                }
            for (int j = 0; j < l.get(0).size() - 1; j++) {
        	//System.out.print(one[j] +" ");   
        	}            
			colExpand = indexOfSmallest(one);
			
			
			
			 //for (int o = 0; o < l.size(); o++) {

            //System.out.println(Arrays.toString(l.get(o).toArray()));

        //}
			//System.out.println(colExpand);
			
			boolean noSol = false;
			
			for(int f = 0; f < one.length; f++){
        	if(one[f] == 0){
        		noSol = true;
        	}
        
        
        }
        //for(int f = 0; f < one.length; f++){
        //	System.out.print(one[f]+",");
        //	}
		//System.out.println(noSol);
		
        //if(copy.size() == map.length * map[1].length - 4 + pieceAmount + 1){
        //	colExpand = oneArray[(getMin(oneArray))];
        //}
        //int asdf = 0;
        for (int r = 0; r < l.size(); r++) {
        if(l.get(r).get(colExpand) == 1 && !noSol){
        
        //asdf++;
        
        
        
        //if(l.get(0).size() == (map.length * map[0].length) - holeAmount + pieceAmount){
		//	System.out.println(asdf);
		
		//}
			copy.clear();
			
            for (int i = 0; i < l.size(); i++) {

                List < Integer > temp = new ArrayList < > ();

                for (int j = 0; j < l.get(0).size(); j++) {
                    temp.add(l.get(i).get(j).intValue());

                }

                copy.add(temp);
				//System.out.println(temp.size() + "sdf");


            }



            colDel.clear();
            rowNum = copy.get(r).get(copy.get(r).size() - 1);
            //System.out.println(rowNum);
            if (copy.get(r).get(colExpand) == 1) {
                for (int i = 0; i < copy.get(r).size() - 1; i++) {
                    if (copy.get(r).get(i).intValue() == 1) {
                        colDel.add(i);
                    }
                }
                //System.out.println(colDel);
                int rowAm = copy.size();
                for (int i = rowAm - 1; i >= 0; i--) {
                    for (int j = 0; j < copy.get(0).size() - 1; j++) {
                        if (copy.get(i).get(j) == 1) {
                            if (colDel.contains(j)) {
                                copy.remove(i);
                                break;

                            }

                            //System.out.println(colDel);
                        }

                    }
                }
                //System.out.println(copy + "Empty?");
                //System.out.println(colDel.size());
                //System.out.println(copy.size() + "size");
                //System.out.println(colDel.size() + "size");
                for (int o = 0; o < copy.size(); o++) {

                    //System.out.println(copy.get(o));

                }
				//System.out.println(l.size()-1);
                if (copy.size() == 0 && colDel.size() == l.get(0).size() - 1) {
                    rowSolved.add(rowNum);
                    //System.out.println("SOLVED");
                    return 1;
                } else if (copy.size() == 0 && colDel.size() != l.size() - 1 ) {
					//map.length * map[1].length - 4 + pieceAmount + 1
					
					
                    //System.out.println("not SOLVED");
                    
                }

                for (int i = 0; i < copy.size(); i++) {
                    for (int j = colDel.size() - 1; j >= 0; j--) {
                        //System.out.println(colDel.get(j));
                        copy.get(i).remove(colDel.get(j).intValue());
                    }
                }
                //System.out.println("start");
                
				//System.out.println("end");
				if(copy.size() != 0){
                if (backTrack(copy) == 1) {
                    rowSolved.add(rowNum);
                    return 1;
                }else{
                	//System.out.println("Recursion");
                
                }
                }


            }
        }
        }
        return 0;

    }

    public static void rotate90Clockwise(int a[][]) {
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
            O,
            //R,
            Rm,
            Q,
            //Qm,
            P,
            //Pm,
            //Sm,
            S,
            T,
            U,
            V,
            W,
            X,
            //Y,
            Ym,
            Z,
            //Zm
        };
        
        int rowCount = 0;
        //int[] row = new int[(map.length * map[1].length) - holeAmount];
        //int[] pieceRow = {0, 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int sum = 0;
        //int[] finalRow = new int[(map.length * map[1].length) - holeAmount + 18];
        //int [][] finalMatrix = new int[map.length * map[1].length) - holeAmount + 18][]
        int rowAmount = map.length;
        int colAmount = map[0].length;
        //int[] finalRow = new int[(map.length * map[1].length) - holeAmount + 18];
        List < Integer > finalRow = new ArrayList < > ();
        List < List < Integer > > finalList = new ArrayList < > ();

        boolean overHole = false;
        int rowIndex = 0;
        for (int v = 0; v < pieceAmount; v++) {
            //System.out.println("--------------------------PICECE----------------------------" + v);
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
                        finalRow = new ArrayList < Integer > ();




                        for (int k = 0; k < 5; k++) {
                            for (int h = 0; h < 5; h++) {
                                if ((k + i - 2) >= 0 && (h + j - 2) >= 0 && (k + i - 2) < map.length && (h + j - 2) < map[i].length) {
                                    //System.out.println(k + i - 2);
                                    //System.out.println(h + j - 2);
                                    map[k + i - 2][h + j - 2] = map[k + i - 2][h + j - 2] + pieceList[v][k][h];
                                }
                            }

                        }
                        overHole = false;
                        for (int o = 0; o < rowAmount; o++) {
                            for (int p = 0; p < colAmount; p++) {
                                if (map[o][p] == 3) {
                                    //System.out.println("Break");
                                    break;
                                }
                            }
                        }
                        sum = 0;
                        for (int o = 0; o < rowAmount; o++) {
                            for (int p = 0; p < colAmount; p++) {
                                sum += map[o][p];
                            }
                        }

                        if (sum == 5 + holeAmount * 2) {
                            for (int o = 0; o < rowAmount; o++) {
                                for (int p = 0; p < colAmount; p++) {
                                    if (map[o][p] == 3) {
                                        overHole = true;
                                    }
                                }
                            }
                            if (!overHole) {

                                pieceRow[v] = 1;

                                for (int n = 0; n < rowAmount; n++) {
                                    for (int m = 0; m < colAmount; m++) {
                                        if (map[n][m] != 2) {
                                            //System.out.println(rowIndex);
                                            row[rowIndex] = map[n][m];
                                            rowIndex++;

                                        }
                                    }
                                }
                                for (int n = 0; n < (map.length * map[1].length) - holeAmount + pieceAmount; n++) {
                                    if (n < pieceAmount) {
                                        finalRow.add(pieceRow[n]);

                                    } else {

                                        finalRow.add(row[n - pieceAmount]);
                                    }




                                }
                                //System.out.println(finalRow.size());
                                

                                finalRow.add(rowCount);
                                finalList.add(finalRow);
                                rowCount++;
                                //System.out.println(Arrays.toString(finalRow.toArray()));


                                for (int o = 0; o < rowAmount; o++) {




                                    //System.out.println(Arrays.toString(map[o]));




                                }
                            }




                        }
                        //System.out.println("");
                        for (int o = 0; o < rowAmount; o++) {
                            for (int p = 0; p < colAmount; p++) {
                                if (map[o][p] == 1 || map[o][p] == 0) {
                                    map[o][p] = 0;
                                } else {
                                    map[o][p] = 2;
                                }
                            }
                        }




                    }
                }



                //System.out.println("rotation " + r);
                rotate90Clockwise(pieceList[v]);

            }






        }

        for (int o = 0; o < finalList.size(); o++) {

            System.out.println(Arrays.toString(finalList.get(o).toArray()));

        }

        for (int o = 0; o < finalList.size(); o++) {

            //System.out.println(finalList.get(o));

        }

        backTrack(finalList);


        System.out.println(rowSolved.size());
         for (int o = 0; o < rowSolved.size(); o++) {

            System.out.println(finalList.get(rowSolved.get(o)));

        }
	int index = 11;
	int indexR = -1;
    
   
    for (int i = 0; i < 8; i++) {
    
        for (int j = 0; j < 8; j++) {
        	index++;
        	
        		if(map[i][j] != 2){
        		for(int k = 0; k < 12; k++){
        			if(finalList.get(rowSolved.get(k)).get(index).intValue() == 1){
        			//System.out.println(finalList.get(rowSolved.get(k)));
        			
        				System.out.print(k + "   ");
        			}
        		
        		}
        		}else{
					
                System.out.print("h");
                index--;
				}
                	
			
			
			
		}
            
            
        System.out.println("");    
            
            
            
            
            
            
            
            
            
            
            
            
            
                

    }
        
    

		
    
    }
    


};
