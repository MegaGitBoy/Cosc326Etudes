//package cosc326lab02go5Cleaned;
package pentomino;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Node {
    Node left;
    Node right;
    Node up;
    Node down;
    Node column;
    int rowID;
    int colID;
    int countOfNodes;
}//END--[Node]

class Pentomino {    
    final char name;
    int [][] data;

    public Pentomino(char name, final int [][] data) {
        this.name = name;
        this.data = data;
    }    

    public void rotate() {
        int size = data.length;
        for (int i = 0; i<(size/2); i++) {
            for(int j=i; j<(size-i-1); j++) {
                int tmp = data[i][j];
                data[i][j] = data[size-1-j][i];
                data[size-1-j][i] = data[size-1-i][size-1-j];
                data[size-1-i][size-1-j] = data[j][size-1-i];
                data[j][size-1-i] = tmp;
            }
        }        
    }

    public void reflect() {
        for (int i = 0; i<data.length; i++) {
            int [] rw = data[i];
            for (int j = 0; j<(rw.length/2); j++) {
                int tmp = rw[j];
                rw[j] = data[i][rw.length-j-1];
                rw[rw.length-j-1] = tmp;
            }
        }
    }

    public String toString() {
        String pent = "";
        for(int i=0; i<data.length; i++) {
            for(int j=0; j<data[i].length; j++) {
                //System.out.print(data[i][j] + "   ");                
                pent += " " + data[i][j];                
            }
            //System.out.println();
            pent += "\r\n";
        }  
        return pent;
    }    

}//END--[Pentomino]


public class try3_dl {    

    public static int holeAmount = 0;
    static Node header = new Node();
    static List < Node > solved_matrix = new ArrayList < > (); 
    static List < List < Integer >> solved_matrix_allSolutions = new ArrayList < > ();
    static Node[][] unsolved_matrix;
    static boolean solved = false;
    //static List < List < Integer > > finalList = new ArrayList < > ();
    static List < List < Integer > > finalList = new ArrayList < > ();

    public static int[][] O = {     
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 }
    };

    public static int[][] P = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] Q = {     
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] R = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] S = {  
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 0, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] T = {     
            { 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] U = {     
            { 0, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] V = {     
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 1, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] W = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] X = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] Y = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 }
    };

    public static int[][] Z = {     
            { 0, 0, 0, 0, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };

    public static int[][] map;
    //    public static int[][] map = {  
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 2, 2, 0, 0, 0 },
    //            { 0, 0, 0, 2, 2, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //    };    

    public static List < Pentomino > processPentominoesInput(String pent_to_use, List < Pentomino > pentList_default) {
        //===Process Pentominoes input:
        //String pent_to_use = "OPQRSTUVWXYZ";      
        boolean use_any = false;
        List < Pentomino > pentList_chosen = new ArrayList < > ();
        for(int c=0; c<pent_to_use.length(); c++) {
            for(int p=0; p<pentList_default.size(); p++) {
                //System.out.println("Processing: " + pent_to_use.charAt(c) );
                int compareChars = Character.compare(Character.toUpperCase(pent_to_use.charAt(c)), pentList_default.get(p).name);  
                if(compareChars == 0) {
                    pentList_chosen.add(pentList_default.get(p));
                    //if(use_any == true) {}                     
                    break;
                }              
            }
        }
        //---Check if proper Pentomino were provided:
        if(pentList_chosen.size() == 0 || pentList_chosen.size() < pent_to_use.length()) {
            System.out.println("Wrong Pentominoes given!!!");
            System.out.println("Please use Conway's notation from O to Z");
            System.exit(0);
        }     
        return pentList_chosen;
    }//end--processPentominoesInput();

    public static void processMapInput(List<List<Character>> map_) {
        if(map_.size() != 0) {
            int[] rw = new int[map_.get(0).size()];
            map = new int[map_.size()][map_.get(0).size()];
            for (int i = 0; i < map_.size(); i++) {
                for (int j = 0; j < map_.get(i).size(); j++ ) {
                    int isDot = Character.compare(map_.get(i).get(j), '.');
                    int isStar = Character.compare(map_.get(i).get(j), '*');
                    if(isDot == 0) { 
                        map[i][j] = 0;
                    }else if (isStar == 0) {
                        map[i][j] = 2;
                        holeAmount +=1;
                    } else {
                        System.out.println("================= WRONG map input !!!!!");
                        System.out.println("Allowed map characters: . for free block and * for used block");
                        System.exit(0);
                    }
                }//end--for(j)
            }//end--for(i)      
        } else {
            System.out.println("Please provied proper map!");
            System.exit(0);
        }
    }//end--processMapInput();

    public static int[][] RetrieveSolutions(int pieceAmount, List < Pentomino > pentList_given) {
        if(solved_matrix_allSolutions.size() == 0) {
            System.out.println("No Solution is possible!");
            System.exit(0);
        }
        int which_solution = 0;
        //---Retrieve solution rows:
        List < List < Integer > > solvedList = new ArrayList < > ();
        for (int rn = 0;  rn < solved_matrix_allSolutions.get(which_solution).size(); rn++) {
            solvedList.add(finalList.get(solved_matrix_allSolutions.get(which_solution).get(rn)));
        }
        //---Build map:
        int[][] solvedMap = new int[map.length][map[0].length];
        for (int rw = 0; rw < solvedList.size(); rw++) {
            //---Get Figure index:
            int fig_index = 0;
            for (int p = 0; p < pieceAmount; p++) {
                if (solvedList.get(rw).get(p) == 1) {
                    fig_index = p;
                }                  
            }
            int block = pieceAmount ; 
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if(map[i][j] != 2){
                        if (solvedList.get(rw).get(block) == 1) {     
                            solvedMap[i][j] += solvedList.get(rw).get(block) + fig_index;
                        }
                        block++;
                    }                    
                }
            }            
        }//end
        //System.out.println("---Solved map:");
        return solvedMap;
    }//end--RetrieveSolutions();

    public static void printSolvedMap(int map[][], List < Pentomino > pentList) {
        String output = "";
        int fig_index = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0){                  
                    output += " " + pentList.get((map[i][j]-1)).name;  
                } else {
                    //output += " " + 0;
                    output += " " + "*";
                }
            }
            output += "\r\n";
        }
        System.out.println(output);
    }

    public static Node createDancingLinksMatrix(List < List < Integer > > finalList ) {
        int RowNum_ = finalList.size()-1;
        int ColNum_ = finalList.get(0).size();

        unsolved_matrix = new Node[RowNum_+1][ColNum_];
        //System.out.println("finalList.size() = " + finalList.size());
        for(int i=0; i<=RowNum_; i++) { // one extra row to be used as as header
            for(int j=0; j<ColNum_; j++) {
                // if its 1 then create a Node
                if(finalList.get(i).get(j) == 1) {
                    int tmp1;
                    int tmp2;
                    // increment Node counter for all 1 except the initial row
                    if (unsolved_matrix[0][j] == null) { unsolved_matrix[0][j] = new Node();}
                    if(i!=0) {     
                        unsolved_matrix[0][j].countOfNodes += 1;
                    }
                    // link pointer the column header Node
                    if (unsolved_matrix[i][j] == null) {unsolved_matrix[i][j] = new Node();}
                    unsolved_matrix[i][j].column = unsolved_matrix[0][j]; 
                    // set column and rows ids
                    unsolved_matrix[i][j].rowID = i;
                    unsolved_matrix[i][j].colID = j;   
                    //---Link this Node to it's neghbouring Nodes:
                    // left Node:
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp2 = (tmp2-1<0) ? ColNum_-1 : tmp2-1; 
                    } while ( finalList.get(tmp1).get(tmp2) == 0 && tmp2 != j );
                    if(unsolved_matrix[i][tmp2] == null) { unsolved_matrix[i][tmp2] = new Node(); } 
                    unsolved_matrix[i][j].left = unsolved_matrix[i][tmp2];
                    // right Node
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp2 = (tmp2+1)%ColNum_;
                    } while(finalList.get(tmp1).get(tmp2) == 0 && tmp2 != j);
                    if(unsolved_matrix[i][tmp2] == null) {unsolved_matrix[i][tmp2] = new Node(); } 
                    unsolved_matrix[i][j].right = unsolved_matrix[i][tmp2];
                    // up Node
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp1 = (tmp1-1<0) ? RowNum_ : tmp1-1;
                        //System.out.println("tmp1 = " + tmp1 + " /// " + "tmp2 = " + tmp2);
                    }while ( finalList.get(tmp1).get(tmp2) == 0 &&  tmp1 != i );
                    if (unsolved_matrix[tmp1][j] == null) { unsolved_matrix[tmp1][j] = new Node();} 
                    unsolved_matrix[i][j].up = unsolved_matrix[tmp1][j];
                    //down Node
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp1 = (tmp1+1)%(RowNum_+1);
                    }while(finalList.get(tmp1).get(tmp2) == 0 && tmp1 != i);
                    if (unsolved_matrix[tmp1][j] == null) { unsolved_matrix[tmp1][j] = new Node(); } 
                    unsolved_matrix[i][j].down = unsolved_matrix[tmp1][j];
                }
            }
        }      
        // link for right header column
        //System.out.println("unsolved_matrix[0][0] --> " + unsolved_matrix[0][0].right);
        if(unsolved_matrix[0][0] == null) { unsolved_matrix[0][0] = new Node(); }
        header.right = unsolved_matrix[0][0];
        // link for left header column
        if (unsolved_matrix[0][ColNum_-1] == null) {
            unsolved_matrix[0][ColNum_-1] = new Node();
        }
        header.left = unsolved_matrix[0][ColNum_-1];

        unsolved_matrix[0][0].left = header;
        unsolved_matrix[0][ColNum_-1].right = header;
        //---
        //System.out.println("unsolved_matrix.length --> ROWS: " + unsolved_matrix.length);
        //System.out.println("unsolved_matrix.length --> COLUMNS: " + unsolved_matrix[0].length);
        //print2DarrayNodes(unsolved_matrix);
        //---
        return header;      
    }//end--createDancingLinksMatrix();

    public static void cover(Node Node) {
        Node row;
        Node rightNode;      
        // get column header
        Node colNode = Node.column;      
        // unlink column header
        colNode.left.right = colNode.right;
        colNode.right.left = colNode.left;        
        // follow down the column and delete it's rows
        for(row=colNode.down; row!=colNode; row=row.down) {
            for(rightNode=row.right; rightNode!=row; rightNode=rightNode.right) {
                rightNode.up.down = rightNode.down;
                rightNode.down.up = rightNode.up;
                // decrease the Node count for removed row Node
                unsolved_matrix[0][rightNode.colID].countOfNodes -=1;
            }
        }      
    }//end--cover();

    public static void uncover(Node Node) {
        Node rowNode;
        Node leftNode;
        // get column header
        Node coldNode = Node.column;
        // follow down the column and un-delete it's rows
        for(rowNode=coldNode.up; rowNode!=coldNode; rowNode=rowNode.up) {
            for(leftNode=rowNode.left; leftNode!=rowNode; leftNode=leftNode.left) {
                leftNode.up.down = leftNode;
                leftNode.down.up = leftNode;
                // increase the Node count for recovered row Node
                unsolved_matrix[0][leftNode.colID].countOfNodes += 1;             
            }
        }
        // link back the column header
        coldNode.left.right = coldNode;
        coldNode.right.left = coldNode;
    }//end--uncover();

    public  static Node findSmallestColumn() { 
        Node h = header;
        Node min = h.right;
        h = h.right.right;
        do {
            if(h.countOfNodes < min.countOfNodes) {
                min = h;              
            }
            h = h.right;          
        }while(h != header);
        return min;
    }//end--findSmallestColumn();

    public static void backTrackLinks(int level) {
        Node rowNode;
        Node column;
        Node rightNode;
        Node leftNode;
        //Check if no more column left (we have covered the puzzle space --> hence solution was found)
        if(header.right == header) {
            System.out.println("---PUzzle has been SOLVED !!!");
            //System.out.println(solved_matrix);
            solved =true;
            ///---Add the solution to other solutions
            List < Integer > found_solution = new ArrayList < > ();
            for(int s=0; s<solved_matrix.size(); s++) {
                found_solution.add(solved_matrix.get(s).rowID);
            }
            solved_matrix_allSolutions.add(found_solution); 
            //---
            return;
        }
        //Find column with smallest amount of 1's:
        column = findSmallestColumn();
        // cover the selected column
        cover(column);
        for(rowNode = column.down; rowNode!=column; rowNode=rowNode.down) {
            solved_matrix.add(rowNode);
            for(rightNode=rowNode.right; rightNode!=rowNode; rightNode=rightNode.right) {
                cover(rightNode);
            }
            // go deeper recursively
            if(!solved) {              
                backTrackLinks(level+1);
            }
            //---If no solution found remove it 
            solved_matrix.remove(rowNode);
            // and backtrack/uncover:
            column = rowNode.column;
            for(leftNode=rowNode.left; leftNode!=rowNode; leftNode=leftNode.left ) {
                uncover(leftNode);
            }          
        }
        uncover(column);      
    }//end--backTrackLinks();


    public static void createCoordinatesTable(int pieceAmount, List < Pentomino > pentList) {
        int rowCount = 0;
        int rowAmount = map.length;
        int colAmount = map[0].length;
        List < Integer > finalRow = new ArrayList < > ();

        //========================
        //---Dancing links:
        //int[] column_row_Nodes = new int[pieceAmount+(map.length * map[1].length) - holeAmount];
        List < Integer > column_row_Nodes = new ArrayList < > ();
        for(int i=0; i<pieceAmount+(map.length * map[1].length) - holeAmount; i++) {
            //column_row_Nodes[i] = 1;
            column_row_Nodes.add(1);
        }
        finalList.add(column_row_Nodes);
        //========================

        boolean overHole = false;

        //----------[main MEGA loop - start]:  
        //---Go over each Pentomino piece figure:
        for (int v = 0; v < pieceAmount; v++) {  // [main loop start]
            //System.out.println("--------------------------PICECE----------------------------" + v);
            //---Go over each figures's reflection:
            for (int ref = 0; ref <= 1; ref++) { // [main --reflection--  loop start]
                //---Go over each figures's rotation:
                for (int r = 0; r < 4; r++) {  // [main rotation loop start]

                    //---Go over each map's square grid block:    
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[i].length; j++) {
                            //rowIndex = 0;
                            int[] row = new int[(map.length * map[1].length) - holeAmount];
                            int[] pieceRow = new int[pieceAmount];
                            finalRow = new ArrayList < Integer > ();

                            //---Place each Pentomino's block over the map's block:
                            for (int k = 0; k < 5; k++) {
                                for (int h = 0; h < 5; h++) {
                                    if ((k + i - 2) >= 0 && (h + j - 2) >= 0 && (k + i - 2) < map.length && (h + j - 2) < map[i].length) {
                                        map[k + i - 2][h + j - 2] = map[k + i - 2][h + j - 2] + pentList.get(v).data[k][h];
                                    }
                                }
                            }
                            overHole = false;
                            //                            for (int o = 0; o < rowAmount; o++) {
                            //                                for (int p = 0; p < colAmount; p++) {
                            //                                    if (map[o][p] == 3) {
                            //                                        //System.out.println("---Break"); // vk_mod
                            //                                        break;
                            //                                    }
                            //                                }
                            //                            }
                            //sum = 0;
                            int sum_ = 0;
                            for (int o = 0; o < rowAmount; o++) {
                                for (int p = 0; p < colAmount; p++) {
                                    sum_ += map[o][p];
                                }
                            }
                            if (sum_ == 5 + holeAmount * 2) {   
                                for (int o = 0; o < rowAmount; o++) {
                                    for (int p = 0; p < colAmount; p++) {
                                        if (map[o][p] == 3) {
                                            overHole = true;
                                            //System.out.println("~~~~~~~~~~~~~~~~~~~~~overHall !!!");
                                        }
                                    }
                                }
                                if (!overHole) {

                                    pieceRow[v] = 1;
                                    int rowIndex_ = 0;
                                    for (int n = 0; n < rowAmount; n++) {
                                        for (int m = 0; m < colAmount; m++) {
                                            if (map[n][m] != 2) {
                                                //System.out.println(rowIndex);
                                                row[rowIndex_] = map[n][m];
                                                rowIndex_++;
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
                                    //finalRow.add(rowCount); 
                                    finalList.add(finalRow);
                                    rowCount++;
                                    //System.out.println(Arrays.toString(finalRow.toArray()));
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
                    //rotate90Clockwise(pieceList[v]);
                    pentList.get(v).rotate();

                }// [main rotation loop end]
                pentList.get(v).reflect();
            }// [main --reflection--  loop end]
        } // [main loop end]
    }//end--createCoordinatesTable();

    public static void main(String[] args) throws IOException{ //"main"
        System.out.println("===Start===");        
        //==============
        List < Pentomino > pentList_default = new ArrayList < > ();
        List < Pentomino > pentList_given = new ArrayList < > ();
        //---
        pentList_default.add(new Pentomino('O', O));      //pentList.add(O_p);
        pentList_default.add(new Pentomino('P', P));      //pentList.add(P_p);
        pentList_default.add(new Pentomino('Q', Q));      //pentList.add(Q_p);
        pentList_default.add(new Pentomino('R', R));      //pentList.add(R_p);
        pentList_default.add(new Pentomino('S', S));      //pentList.add(S_p);
        pentList_default.add(new Pentomino('T', T));      //pentList.add(T_p);
        pentList_default.add(new Pentomino('U', U));      //pentList.add(U_p);
        pentList_default.add(new Pentomino('V', V));      //pentList.add(V_p);
        pentList_default.add(new Pentomino('W', W));      //pentList.add(W_p);
        pentList_default.add(new Pentomino('X', X));      //pentList.add(X_p);
        pentList_default.add(new Pentomino('Y', Y));      //pentList.add(Y_p);
        pentList_default.add(new Pentomino('Z', Z));      //pentList.add(Z_p);
        //==============

        //----------------------------INPUT:

        if(args.length == 0) {
            System.out.println("No file given!");
            System.exit(0);
        }

        String inputFile = args[0];      
        //System.out.println("File input: " + inputFile);

        File puzzle = new File(inputFile);
        //File puzzle = new File("test.txt");
        System.out.println("puzzle --> " + puzzle);
        Scanner scan = new Scanner(puzzle);

        //===============SCANNER:
        //---Get pentamino pieces:
        String pent_to_use = "";
        if(scan.hasNextLine()) { 
            pent_to_use = scan.nextLine().trim();            
        } else {
            System.out.println("File is empty!");
            System.exit(0);
        }
        List <List <Character> >  map_input = new ArrayList < > ();      
        //---Get map: 
        if(scan.hasNextLine()) {        
            while(scan.hasNextLine()) {
                String tmp = scan.nextLine();
                if(!tmp.trim().isEmpty()) {
                    tmp = tmp.trim();          
                    List <Character> rw = new ArrayList < > ();
                    for(int i=0; i<tmp.length(); i++) {
                        //System.out.print(tmp.charAt(i));              
                        rw.add(tmp.charAt(i));
                    }
                    //System.out.println();
                    map_input.add(rw);
                } else {
                    System.out.println("--EMPTY LINE DETECTED--");
                }
            }
            //System.out.println("====== map_input : ");
            //System.out.println(map_input);
            //---Process map:
            processMapInput(map_input);
            //---
        } else {
            System.out.println("Please provide map !!!");
            System.exit(0);
        }
        //===============
        //--------------------------------------------    
        //--------------------------------------------

        //pentList_given = pentList_chosen;
        //===Process Pentominoes input:
        pentList_given = processPentominoesInput(pent_to_use, pentList_default);
        int pieceAmount = pentList_given.size();
        //System.out.println("pieceAmount --> " + pieceAmount);

        int free_map_blocks = ( map.length * map[0].length ) - holeAmount;
        //System.out.println("Maps free blocks: " + free_map_blocks);
        if((free_map_blocks/ (double) pieceAmount) == 5.0) {  //for_useAny--start      
            //System.out.println("free_map_blocks/pieceAmount = " + free_map_blocks/ (double) pieceAmount);
            createCoordinatesTable(pieceAmount, pentList_given);
            createDancingLinksMatrix(finalList);   
            backTrackLinks(0); //---OFF
            //======================= 
            //System.out.println("===========solved_matrix_allSolutions: **********************************");
            //System.out.println(solved_matrix); 
            //System.out.println("solved_matrix_allSolutions --> ROWS = " + solved_matrix_allSolutions.size()); 
            //System.out.println("solved_matrix_allSolutions --> COLUMNS = " + solved_matrix_allSolutions.get(0).size());
            //System.out.println(solved_matrix_allSolutions);
            //=======================
            //printSolvedMap(solvedMap, pentList_given);
            //---Print Final Solution:
            System.out.println(pent_to_use);
            printSolvedMap(RetrieveSolutions(pieceAmount, pentList_given), pentList_given);
        }else {
            System.out.println("===NO_SOLUTION===");
        }//for_useAny--end
        System.out.println("===END===Go5--7");
    }//end--main();

}//END--[AppGo5_vk01]
