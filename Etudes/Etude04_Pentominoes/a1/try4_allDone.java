/**
 * ======================================================================
 * Pentomenoes solver.
 * University of Otago, 2021 Semester 2 (New Zealand)
 * Cosc326(Effective Programming):  Ettude-4 (Pentominoes)
 * @author Valentin Kiselev, Maaha Ahmad, Freeman Eckles, Leo Venn 
 * ======================================================================
 */


package pentomino;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
//import java.util.Arrays;
//import java.util.HashSet;

/**
 * Generator used for finding all possible combinations of m from set n (with Repetition)
 * @author Valentin Kiselev
 *
 */
class ComboGeneratorWithRepeat8 { 
    //---Stores a full set of given characters:
    private char[] all; 
    //---Stores indexes of current combination 
    //   (each index corresponds to an element in all[])
    private int [] indexes; 
    //---Total number if given characters
    private int total_figs;
    //---Max allowed index of an element in all[] 
    private final int max_index;
    //---Length of generated combinations.
    private int length;
    //---Store the computed total number of combinations it is possible to generate
    private long total_posibilities;
    //---Stores the total number of combinations it is still possible currently to generate
    private long total_posibilities_left;
    //---Flag to identify if generator has run out of all possible combinations
    private boolean isMoreCombos;

    /**
     * Constructor
     * @param all - full set of given characters to be used for generating combinations
     * @param length - Length of combinations to be generated
     */
    public ComboGeneratorWithRepeat8(char[] all,  int length) {
        this.all = all;
        this.indexes = new int[length];
        this.total_figs = all.length;
        this.length = length;
        this.max_index = all.length-1;
        System.out.println("******max_val --> " + max_index);
        //---Populate combos:
        this.isMoreCombos = true;
        for(int c=0; c<indexes.length; c++) {
            //this.indexes[c] +=c;
            this.indexes[c] = 0;
        }
        this.total_posibilities = countTotalPermutationPissibilitesWithRepeats();
        this.total_posibilities_left = total_posibilities;//-1;
    }//end--ComboGeneratorWithRepeat8();  

    /**
     * Used to fund a factorial of a given number
     * @param n - a number for which to find factorial
     * @return - factorial of a given number
     */
    private static BigInteger findFactorial(long n) {
        BigInteger fact = new BigInteger("1");
        for(long i=1; i<=n; i++) {
            //fact=fact*i;
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }//end--findFactorial();  

    /**
     * Finds a total number of  all possible combinations of m from set n (with Repetition)
     * @return - number of all possible combinations 
     */
    private long countTotalPermutationPissibilitesWithRepeats() {
        //------
        //****************************************************************************************//
        // Formula for finding all possible combinations of m from set n (with Repetition):       //
        // C = (r+n-1)! / ( r!*(n-1)! )                                                                 //
        //****************************************************************************************//
        //------
        int max_chars = total_figs;
        int combo_length = length;
        BigInteger total_possibilities = findFactorial(combo_length + max_chars - 1).divide(findFactorial(combo_length).multiply(findFactorial(max_chars - 1)));
        //System.out.println("Y===> total_possibilities = " + total_possibilities);
        return total_possibilities.longValue();
    }//end--countTotalPermutationPissibilitesWithRepeats();

    /**
     * Utility method for generating next combination.
     */
    private void genNextComboIteratively() {
        int last_index = indexes.length-1;
        int current_index = indexes.length-1;
        if(indexes[last_index] < max_index) {
            indexes[indexes.length-1] += 1;
        } else {
            while(indexes[current_index] >= indexes[last_index] && current_index >0) {
                current_index -= 1;
            }
            if(  indexes[current_index] < indexes[last_index]  ) {
                indexes[current_index] += 1; 
                for(int i=current_index; i<indexes.length; i++) {
                    indexes[i] = indexes[current_index]; 
                }
            } else {
                isMoreCombos = false;
                System.out.println("~~~ALL_OUT~~~");
            }
        }
    }//end--genNextCombo();

    /**
     * Used to generate next possible combination.
     * @return - next possible combination 
     *           or null if no more combination possible 
     */
    public char[] genNextCombo() {
        if (isMoreCombos) {
            total_posibilities_left -= 1;
            genNextComboIteratively();
        } else {
            System.out.println("---no more combos---");
        }
        //return null;
        return (isMoreCombos == true) ? returnCombo() : null;
    }

    /**
     * Returns recently generated combination
     * @return - currently generated combination
     */
    public char[] returnCombo() {
        char[] new_combo = new char[length];
        for(int c=0; c<indexes.length; c++) {
            new_combo[c] = all[indexes[c]];
        }
        return new_combo;
    }//end--returnCombo();

    /**
     * Prints indexes of currently generated combination.
     */
    public String toString() {
        String pent = "";
        for(int i=0; i<indexes.length; i++) {
            pent += " " + indexes[i];  //---GOOD              
            //pent += " " + (indexes[i]+1);    //---Debug-->delete            
        }  
        return pent;
    }//end--toString(); 

    /**
     * Prints currently generated combination
     */
    public void printCombo() {
        for(int i=0; i<indexes.length; i++) {
            System.out.print(all[indexes[i]]);
            //System.out.print(", ");
        }        
        System.out.println();
    }//end--printCombo();

    /**
     * Getter for the computed total number of combinations it is possible to generate
     * @return - number of combinations it is possible to generate
     */
    public long getTotal_posibilities() {
        return total_posibilities;
    }

    /**
     * Getter for the total number of combinations it is still possible currently to generate
     * @return - number of combinations it is still possible currently to generate
     */
    public long getTotalCombinationsLeft() {
        return total_posibilities_left;
    }

}//END--[ComboGeneratorWithRepeat8]


/**
 * Generator used for finding all possible combinations of m from set n (without Repetition)
 * @author Valentin Kiselev
 *
 */
class comboGenNoRepeat8 { 
    //---Stores a full set of given characters:
    private char[] all;
    //---Stores indexes of current combination 
    //   (each index corresponds to an element in all[])
    private int [] indexes; 
    //---Total number if given characters
    private int total_figs;
    //---Max allowed index of an element in all[] 
    private final int max_index;
    //---Length of generated combinations.
    private int length;
    //---Store the computed total number of combinations it is possible to generate
    private long total_posibilities;
    //---Stores the total number of combinations it is still possible currently to generate
    private long total_posibilities_left;
    //---Flag to identify if generator has run out of all possible combinations
    private boolean isMoreCombos;

    /**
     * Constructor
     * @param all - full set of given characters to be used for generating combinations
     * @param length - Length of combinations to be generated
     */
    public comboGenNoRepeat8(char[] all,  int length) {
        this.all = all;
        this.indexes = new int[length];
        this.total_figs = all.length;
        this.length = length;
        this.max_index = all.length-1;
        System.out.println("******max_val --> " + max_index);
        //---Populate combos:
        this.isMoreCombos = true;
        for(int c=0; c<indexes.length; c++) {
            this.indexes[c] +=c;
        }
        this.total_posibilities = countTotalPermutationPissibilitesNoRepeats();
        this.total_posibilities_left = total_posibilities;
    }//end--comboGenNoRepeat8();  

    /**
     * Used to fund a factorial of a given number
     * @param n - a number for which to find factorial
     * @return - factorial of a given number
     */
    private static BigInteger findFactorial(long n) {
        //int i, fact = 1;
        //long fact = 1;
        BigInteger fact = new BigInteger("1");
        for(long i=1; i<=n; i++) {
            //fact=fact*i;
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }//end--findFactorial();   

    /**
     * Finds a total number of  all possible combinations of m from set n (without Repetition)
     * @return - number of all possible combinations
     */
    private long countTotalPermutationPissibilitesNoRepeats() {
        //------
        //**********************************************************************//
        // Formula for finding all possible combinations of m from set n:       //
        // C = n! / ( m!*(n-m)! )                                               //
        //**********************************************************************//
        //------
        int max_chars = total_figs;
        int combo_length = length;
        BigInteger total_possibilities = findFactorial(max_chars).divide(  findFactorial(combo_length).multiply(findFactorial(max_chars - combo_length))  );
        //System.out.println("===> total_possibilities = " + total_possibilities);
        return total_possibilities.longValue();
    }//end--countTotalPermutationPissibilitesNoRepeats();

    /**
     * Utility method for generating next combination.
     */
    private void genNextComboIteratively() {
        int last_index = indexes.length-1;
        int current_index = indexes.length-1;
        int increment = 0;
        if(indexes[last_index] < max_index) {
            indexes[indexes.length-1] += 1;
        } else {
            while(indexes[current_index]+increment >= indexes[last_index] && current_index >0) {
                current_index -= 1;
                increment++;
            }
            if(  indexes[current_index]+increment < indexes[last_index]  ) {
                indexes[current_index] += 1; 
                int add = 0;
                for(int i=current_index; i<indexes.length; i++) {
                    indexes[i] = indexes[current_index] + add;
                    add++;
                }
            } else {
                isMoreCombos = false;
                //System.out.println("~~~ALL_OUT~~~");
            }
        }
    }//end--genNextComboIteratively();

    /**
     * Used to generate next possible combination.
     * @return - next possible combination 
     *           or null if no more combination possible 
     */
    public char[] genNextCombo() {
        if (isMoreCombos) {
            total_posibilities_left -= 1;
            genNextComboIteratively();
        } else {
            //System.out.println("---no more combos---"); //debug
        }
        return (isMoreCombos == true) ? returnCombo() : null;
    }//end

    /**
     * Returns recently generated combination
     * @return - currently generated combination
     */
    public char[] returnCombo() {
        char[] new_combo = new char[length];
        for(int c=0; c<indexes.length; c++) {
            new_combo[c] = all[indexes[c]];
        }
        return new_combo;
    }//end--returnCombo();

    /**
     * Prints indexes of currently generated combination.
     */
    public String toString() {
        String pent = "";
        for(int i=0; i<indexes.length; i++) {
            pent += " " + indexes[i];                
        }  
        return pent;
    }//end--toString(); 

    /**
     * Prints currently generated combination
     */
    public void printCombo() {
        for(int i=0; i<indexes.length; i++) {
            System.out.print(all[indexes[i]]);
            //System.out.print(", ");
        }        
        System.out.println();
    }//end--printCombo();

    /**
     * Getter for the computed total number of combinations it is possible to generate
     * @return - number of combinations it is possible to generate
     */
    public long getTotal_posibilities() {
        return total_posibilities;
    }

    /**
     * Getter for the total number of combinations it is still possible currently to generate
     * @return - number of combinations it is still possible currently to generate
     */
    public long getTotalCombinationsLeft() {
        return total_posibilities_left;
    }

}//END--[comboGenNoRepeat8]

/**
 * Linked list structure to represent unsolved table of Exact cover problem. 
 * Used to solve Exact cover problem by using Dancing Links 
 * of Algorithm X (proposed by  Donald Knuth)
 * @author Valentin Kiselev
 *
 */
class Node8 {
    Node8 left; 
    Node8 right;
    Node8 up;
    Node8 down;
    Node8 column;
    int rowID; 
    int colID;
    int countOfNode8s;
}//END--[Node8]

/**
 * Pentomino structure to represent pentomino set.
 * @author Valentin Kiselev
 * 
 */
class Pentomino8 {  
    //---Char name to identify pentominoes (using Conway's notation from O-Z)
    final char name;
    //---An array to store pentominoes actual shapes 
    int [][] data;

    /**
     * Constructor
     * @param name - Char name to identify pentominoes (using Conway's notation from O-Z)
     * @param data - An array to store pentominoes actual shape representation
     */
    public Pentomino8(char name, final int [][] data) {
        this.name = name;
        this.data = data;
    }    

    /**
     * Method used to rotate pentomino shape
     */
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

    /**
     * Method used to reflect/mirror pentomino shape
     */
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

    /**
     * Return string representation of an array 
     * which stores pentominoes actual shape representation
     * 
     */
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

}//END--[Pentomino8]

/**
 * This application is used to solve the Pentomino puzzle.
 * @author Valentin Kiselev
 *=======================================================================================
 *Input for this app  will be from stdin with each input representing a single puzzle. The format of the puzzle will be as follows:

--> the first line will specify the pieces to use
    (*) the remaining lines will be a rectangular grid of .s and *s representing the puzzle to solve.

--> The first line will be of one of two forms:
    (*). A simple list of letters from the range O-Z representing, in Conway notation, the pieces you have available (may include repeats). There may be more pieces available than places to fill, in which case a solution will only use some of the available pieces (but, e.g., if there is only one Z in the list, then you can only use Z once in the solution).
    As above, but followed by a *. Now you may use any number of pieces of any of the given types (so repetitions aren't relevant). That is, PQR* would mean we have to fill the puzzle with P, Q and R pentominoes but can use 0 or more of each.

--> In the remaining lines, .s represent empty spaces (to fill) and *s represent filled spaces (which your pieces must avoid). A solution fills all (and only) the empty spaces. In particular: no piece can cover a filled space, no piece can project over the edge of the board, and no two pieces can overlap.

--> Output is dependent on whether or not a solution exists. If a solution exists then the output is to:
    (*) echo (i.e., repeat) the first line of the input
    (*) draw the filled grid with appropriate letters replacing .s

--> If no solution exists then output should be:
    (*) echo the first line of the input
    (*) echo the given grid
    (*) add one line containing the word 'Impossible'

 *=======================================================================================
 *
 */
public class try4_allDone {    
    //---Stores amount of forbidden/inaccessible blocks on the given map 
    private static int holeAmount = 0;
    //---Header for the "linked list" table which represents Exact cover problem
    private static Node8 header = new Node8();
    //---Stores solved rows per current solution during each "Backtracking" traversing
    //   through Dancing Links matrix iteration 
    private static List < Node8 > solved_matrix_rows = new ArrayList < > (); 
    //---Contains all possible solutions for solving current Pentomino puzzles
    private static List < List < Integer >> solved_matrix_allSolutions = new ArrayList < > ();
    //---Matrix to store Linked List structure for "Dancing Links" iteration
    private static Node8[][] unsolved_matrix;
    //---Flag to identify if solution for the current Pentomino puzzle was found
    private static boolean solved = false;
    //---List containing all possible positions of each Pentomino piece on each map's grid:
    private static List < List < Integer > > finalPositionsCoordinatesList = new ArrayList < > ();
    //---Flag to identify if Pentomino set was followed by a * 
    //   (i.e. may use any number of pieces of any of the given types or don't use some at all)
    private static boolean use_any = false;

    //---------------Array representation of Pentominoes shapes:

    private static int[][] O = {     
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 }
    };

    private  static int[][] P = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private  static int[][] Q = {     
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] R = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] S = {  
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 0, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] T = {     
            { 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] U = {     
            { 0, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] V = {     
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 1, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] W = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] X = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 1, 1, 1, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0 }
    };

    private static int[][] Y = {     
            { 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0 }
    };

    private static int[][] Z = {     
            { 0, 0, 0, 0, 0 },
            { 0, 1, 1, 0, 0 },
            { 0, 0, 1, 0, 0 },
            { 0, 0, 1, 1, 0 },
            { 0, 0, 0, 0, 0 }
    };

    //---Map to be fille with a given Pentominoes set
    //   '0' - the map's block which are allowed to be filled with a Pentomino
    //   '2' - the map's block which are forbidden to be filled with a Pentomino
    private static int[][] map;
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


    /**
     * Removes duplicate rows from list containing all possible 
     * positions of each Pentomino piece on each map's grid
     * @param finalList -list containing all possible positions of each Pentomino piece on each map's grid
     * @return - filtered list with removed duplicate rows
     */
    private static  List <List< Integer >> removeSameRows(List <List<Integer>> finalList){
        return finalList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Utility function. Converts a given String to 
     * a char array.
     * @param s - String to be converted
     * @return - char array representing given String
     */
    private static char[] stringToArray(String s){
        char[] a = new char[s.length()];
        for(int c=0; c<s.length(); c++) {
            a[c] = s.charAt(c);
        }
        return a;
    }//end--stringToArray()

    /**
     * Process Pentominoes set received from standard input and which was given by a user.     *  
     * @param pent_to_use - Pentominoes set received from standard input
     * @param pentList_default - a list of allowed Pentominoes (i.e. of Conway notation from O-Z)
     * @return - return a list of processed and filterd Pentominoes set
     */
    private static List < Pentomino8 > processPentomino8esInput(String pent_to_use, List < Pentomino8 > pentList_default) {
        //===Process Pentomino8es input:
        //String pent_to_use = "OPQRSTUVWXYZ";      
        int total_Pentomino8es_given = pent_to_use.length();
        List < Pentomino8 > pentList_chosen = new ArrayList < > ();
        //---Check that given Pentomino follow the Conway notation from O-Z.
        for(int c=0; c<pent_to_use.length(); c++) {
            for(int p=0; p<pentList_default.size(); p++) {
                //System.out.println("Processing: " + pent_to_use.charAt(c) );
                int compareChars = Character.compare(Character.toUpperCase(pent_to_use.charAt(c)), pentList_default.get(p).name); 
                int isStar = Character.compare(pent_to_use.charAt(c), '*');
                //---Check if '*' was provided
                if(isStar == 0) {
                    use_any = true;
                    total_Pentomino8es_given -= 1; // minus 1 to compensate for *
                    System.out.println("==> USE ANY or NONE");
                    break;
                }
                if(compareChars == 0) {
                    pentList_chosen.add(pentList_default.get(p));
                    break;
                }              
            }
        }
        //---Check if proper Pentomino8es were provided:
        if(pentList_chosen.size() == 0 || pentList_chosen.size() < total_Pentomino8es_given) {
            System.out.println("Wrong Pentomino8es given!!!");
            System.out.println("Please use Conway's notation from O to Z");
            System.exit(0);
        }     
        return pentList_chosen;
    }//end--processPentomino8esInput();

    /**
     * Process puzzle Map(to be filled by Pentominoes) received from standard input 
     * and which was given by a user. 
     * @param map_
     */
    private static void processMapInput(List<List<Character>> map_) {
        //--- Check that given map only consist of '*' and '.' symbols 
        if(map_.size() != 0) {
            //int[] rw = new int[map_.get(0).size()];
            map = new int[map_.size()][map_.get(0).size()];
            for (int i = 0; i < map_.size(); i++) {
                for (int j = 0; j < map_.get(i).size(); j++ ) {
                    int isDot = Character.compare(map_.get(i).get(j), '.');
                    int isStar = Character.compare(map_.get(i).get(j), '*');
                    if(isDot == 0) { 
                        map[i][j] = 0;
                    }else if (isStar == 0) {
                        map[i][j] = 2;
                        holeAmount +=1; // increase map's forbidden grid count
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

    /**
     * Retrieves computed coordinates of Pentominoes pieces 
     * which were computed to cover the puzzle map
     * and hence solve the Pentominoe puzzle.
     * If no such coordinates were found, then the "no solution" warning will be 
     * displayed and the app will exit. 
     * @param pentList_given - a list of Pentominoes whcih were used to solve the puzzle
     * @return - a solved map containing all Pentominoes pieces which were used to solve it 
     *           (using Conway notation from O-Z)  
     */
    private static int[][] RetrieveSolutions(List < Pentomino8 > pentList_given) {
        int pieceAmount = pentList_given.size();
        if(solved_matrix_allSolutions.size() == 0) {
            System.out.println("No Solution is possible!");
            System.exit(0); 
        }
        int which_solution = 0;
        //---Retrieve solution rows:
        List < List < Integer > > solvedList = new ArrayList < > ();
        for (int rn = 0;  rn < solved_matrix_allSolutions.get(which_solution).size(); rn++) {
            solvedList.add(finalPositionsCoordinatesList.get(solved_matrix_allSolutions.get(which_solution).get(rn)));
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
        }//end--for();
        //System.out.println("---Solved map:");
        return solvedMap;
    }//end--RetrieveSolutions();

    /**
     * Print map with solved Pentomenoes set
     * @param map - puzzle map
     * @param pentList - Pentominoes set
     */
    private static void printSolvedMap(int map[][], List < Pentomino8 > pentList) {
        String output = "";
        //int fig_index = 1;
        //---Populate map with solved Pentomenoes set
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
    }//end--printSolvedMap();

    /**
     * Utility function for using to solve Exact cover problem by 
     * using Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * It creates "Linked List" representation of original list of all 
     * possible positions of each Pentomino piece on each map's grid.
     * @param finalList - list containing all possible positions of each 
     *                    Pentomino piece on each map's grid
     * @return - Liked List representing original list of all valid 
     *           Pentomino coordinates on the puzzle map
     */
    private static Node8 createDancingLinksMatrix(List < List < Integer > > finalList ) {
        int RowNum_ = finalList.size()-1;
        int ColNum_ = finalList.get(0).size();

        unsolved_matrix = new Node8[RowNum_+1][ColNum_];
        //System.out.println("finalList.size() = " + finalList.size());
        for(int i=0; i<=RowNum_; i++) { // one extra row to be used as as header
            for(int j=0; j<ColNum_; j++) {
                // if its 1 then create a Node8
                if(finalList.get(i).get(j) == 1) {
                    int tmp1;
                    int tmp2;
                    // increment Node8 counter for all 1 except the initial row
                    if (unsolved_matrix[0][j] == null) { unsolved_matrix[0][j] = new Node8();}
                    if(i!=0) {     
                        unsolved_matrix[0][j].countOfNode8s += 1;
                    }
                    // link pointer the column header Node8
                    if (unsolved_matrix[i][j] == null) {unsolved_matrix[i][j] = new Node8();}
                    unsolved_matrix[i][j].column = unsolved_matrix[0][j]; 
                    // set column and rows ids
                    unsolved_matrix[i][j].rowID = i;
                    unsolved_matrix[i][j].colID = j;   
                    //---Link this Node8 to it's neghbouring Node8s:
                    // left Node8:
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp2 = (tmp2-1<0) ? ColNum_-1 : tmp2-1; 
                    } while ( finalList.get(tmp1).get(tmp2) == 0 && tmp2 != j );
                    if(unsolved_matrix[i][tmp2] == null) { unsolved_matrix[i][tmp2] = new Node8(); } 
                    unsolved_matrix[i][j].left = unsolved_matrix[i][tmp2];
                    // right Node8
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp2 = (tmp2+1)%ColNum_;
                    } while(finalList.get(tmp1).get(tmp2) == 0 && tmp2 != j);
                    if(unsolved_matrix[i][tmp2] == null) {unsolved_matrix[i][tmp2] = new Node8(); } 
                    unsolved_matrix[i][j].right = unsolved_matrix[i][tmp2];
                    // up Node8
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp1 = (tmp1-1<0) ? RowNum_ : tmp1-1;
                        //System.out.println("tmp1 = " + tmp1 + " /// " + "tmp2 = " + tmp2);
                    }while ( finalList.get(tmp1).get(tmp2) == 0 &&  tmp1 != i );
                    if (unsolved_matrix[tmp1][j] == null) { unsolved_matrix[tmp1][j] = new Node8();} 
                    unsolved_matrix[i][j].up = unsolved_matrix[tmp1][j];
                    //down Node8
                    tmp1 = i;  tmp2 = j;
                    do {
                        tmp1 = (tmp1+1)%(RowNum_+1);
                    }while(finalList.get(tmp1).get(tmp2) == 0 && tmp1 != i);
                    if (unsolved_matrix[tmp1][j] == null) { unsolved_matrix[tmp1][j] = new Node8(); } 
                    unsolved_matrix[i][j].down = unsolved_matrix[tmp1][j];
                }
            }
        }      
        // link for right header column
        //System.out.println("unsolved_matrix[0][0] --> " + unsolved_matrix[0][0].right);
        if(unsolved_matrix[0][0] == null) { unsolved_matrix[0][0] = new Node8(); }
        header.right = unsolved_matrix[0][0];
        // link for left header column
        if (unsolved_matrix[0][ColNum_-1] == null) {
            unsolved_matrix[0][ColNum_-1] = new Node8();
        }
        header.left = unsolved_matrix[0][ColNum_-1];

        unsolved_matrix[0][0].left = header;
        unsolved_matrix[0][ColNum_-1].right = header;
        //---
        //System.out.println("unsolved_matrix.length --> ROWS: " + unsolved_matrix.length);
        //System.out.println("unsolved_matrix.length --> COLUMNS: " + unsolved_matrix[0].length);
        //print2DarrayNode8s(unsolved_matrix);
        //---
        return header;      
    }//end--createDancingLinksMatrix();

    
    /**
     * Utility function for backTrackLinks() for using to solve Exact cover problem by 
     * using Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * It efficiently covers (i.e.removes) linked list nodes which 
     * represent all possible coordinates/positions of each 
     * Pentomino piece on each map's grid
     * @param Node8 - Liked List representing original list of all valid 
     *                Pentomino coordinates on the puzzle map
     */
    private static void cover(Node8 Node8) {
        Node8 row;
        Node8 rightNode8;      
        // get column header
        Node8 colNode8 = Node8.column;      
        // unlink column header
        colNode8.left.right = colNode8.right;
        colNode8.right.left = colNode8.left;        
        // follow down the column and delete it's rows
        for(row=colNode8.down; row!=colNode8; row=row.down) {
            for(rightNode8=row.right; rightNode8!=row; rightNode8=rightNode8.right) {
                rightNode8.up.down = rightNode8.down;
                rightNode8.down.up = rightNode8.up;
                // decrease the Node8 count for removed row Node8
                unsolved_matrix[0][rightNode8.colID].countOfNode8s -=1;
            }
        }      
    }//end--cover();

    /**
     * Utility function for backTrackLinks() for using to solve Exact cover problem by 
     * using Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * It efficiently uncovers (i.e. un-deletes) linked list nodes which 
     * represent all possible coordinates/positions of each 
     * Pentomino piece on each map's grid
     * @param Node8 - Liked List representing original list of all valid 
     *                Pentomino coordinates on the puzzle map
     */
    private static void uncover(Node8 Node8) {
        Node8 rowNode8;
        Node8 leftNode8;
        // get column header
        Node8 coldNode8 = Node8.column;
        // follow down the column and un-delete it's rows
        for(rowNode8=coldNode8.up; rowNode8!=coldNode8; rowNode8=rowNode8.up) {
            for(leftNode8=rowNode8.left; leftNode8!=rowNode8; leftNode8=leftNode8.left) {
                leftNode8.up.down = leftNode8;
                leftNode8.down.up = leftNode8;
                // increase the Node8 count for recovered row Node8
                unsolved_matrix[0][leftNode8.colID].countOfNode8s += 1;             
            }
        }
        // link back the column header
        coldNode8.left.right = coldNode8;
        coldNode8.right.left = coldNode8;
    }//end--uncover();

    private  static Node8 findSmallestColumn() { 
        Node8 h = header;
        Node8 min = h.right;
        h = h.right.right;
        do {
            if(h.countOfNode8s < min.countOfNode8s) {
                min = h;              
            }
            h = h.right;          
        }while(h != header);
        return min;
    }//end--findSmallestColumn();

    /**
     * Main function for using Algorithm X to solve the Pentomino puzzle. 
     * It solves Exact cover problem by using Dancing Links 
     * of Algorithm X (proposed by  Donald Knuth).
     * It uses linked list representation of original coordinates table 
     * (i.e. table of all valid Pentomino coordinates on the puzzle map)
     * to efficiently cover/uncover each valid rows and columns which dont's violate 
     * Exact cover problem conditions: each pentomino peace can accupy ONLY ONE 
     * square/block in the puzzle map" AND also "each block in the puzzle map MUST 
     * contain only one pentomino peace. 
     * @param level - initial level of a search tree. The algorithm starts 
     *                at root level which represents the original problem. 
     *                After choosing first rows (i.e. first potential valid 
     *                Pentomino coordinates) it traverses to next lower level
     *                of the search tree now looking at the following number of potential 
     *                solutions with already considered ones from the levels above. 
     *                This level parameter can be used to control how deep
     *                the search tree should traverse. 
     */
    private static void backTrackLinks(int level) {
        Node8 rowNode8;
        Node8 column;
        Node8 rightNode8;
        Node8 leftNode8;
        //Check if no more column left (we have covered the puzzle space --> hence solution was found)
        if(header.right == header) {
            System.out.println("---PUzzle has been SOLVED !!!");
            //System.out.println(solved_matrix);
            solved =true;
            ///---Add the solution to other solutions
            List < Integer > found_solution = new ArrayList < > ();
            for(int s=0; s<solved_matrix_rows.size(); s++) {
                found_solution.add(solved_matrix_rows.get(s).rowID);
            }
            solved_matrix_allSolutions.add(found_solution); 
            //---
            return;
        }
        //Find column with smallest amount of 1's:
        column = findSmallestColumn();
        // cover the selected column
        cover(column);
        for(rowNode8 = column.down; rowNode8!=column; rowNode8=rowNode8.down) {
            solved_matrix_rows.add(rowNode8);
            for(rightNode8=rowNode8.right; rightNode8!=rowNode8; rightNode8=rightNode8.right) {
                cover(rightNode8);
            }
            // go deeper recursively
            if(!solved) {              
                backTrackLinks(level+1);
            }
            //---If no solution found remove it 
            solved_matrix_rows.remove(rowNode8);
            // and backtrack/uncover:
            column = rowNode8.column;
            for(leftNode8=rowNode8.left; leftNode8!=rowNode8; leftNode8=leftNode8.left ) {
                uncover(leftNode8);
            }          
        }
        uncover(column);      
    }//end--backTrackLinks();

    /**
     * Create a matrix containing all possible positions of each Pentomino piece on each map's grid
     * @param pentList - list of Pentomino objects each representing Pentomenoe's internal structure.
     */
    private static void createCoordinatesTable(List < Pentomino8 > pentList) {
        finalPositionsCoordinatesList.clear();
        int rowCount = 0;
        int rowAmount = map.length;
        int colAmount = map[0].length;
        List < Integer > finalRow = new ArrayList < > ();
        int pieceAmount = pentList.size();
        //========================
        //---Dancing links:
        //   Add extra row of '1' to represent the header nodes for each 
        //   column of the matrix which store Linked List structure for "Dancing Links" iteration
        List < Integer > column_row_Node8s = new ArrayList < > ();
        //for(int i=0; i<pieceAmount+(map.length * map[1].length) - holeAmount; i++) { //idte
        for(int i=0; i<pieceAmount+(map.length * map[0].length) - holeAmount; i++) {
            //column_row_Node8s[i] = 1;
            column_row_Node8s.add(1);
        }
        finalPositionsCoordinatesList.add(column_row_Node8s);
        //========================
        //---Flag to identify if current Pentomino shape/array was overlaid 
        //   over the inaccessible/forbidden puzzle map grids 
        boolean overHole = false;
        //---Go over each Pentomino8 piece figure:
        for (int v = 0; v < pieceAmount; v++) {  
            //---Go over each figures's reflection:
            for (int ref = 0; ref <= 1; ref++) { 
                //---Go over each figures's rotation:
                for (int r = 0; r < 4; r++) { 
                    //---Go over each map's square grid block:    
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[i].length; j++) {
                            //rowIndex = 0;
                            int[] row = new int[(map.length * map[0].length) - holeAmount];
                            int[] pieceRow = new int[pieceAmount];
                            finalRow = new ArrayList < Integer > ();
                            //---Place each Pentomino8's block over the map's block:
                            for (int k = 0; k < 5; k++) {
                                for (int h = 0; h < 5; h++) {
                                    if ((k + i - 2) >= 0 && (h + j - 2) >= 0 && (k + i - 2) < map.length && (h + j - 2) < map[i].length) {
                                        map[k + i - 2][h + j - 2] = map[k + i - 2][h + j - 2] + pentList.get(v).data[k][h];
                                    }
                                }
                            }
                            //---Check it the Pentomino has overlaid the forbidden map's grids
                            overHole = false;
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
                                            //System.out.println("Over Hall !!!");
                                        }
                                    }
                                }
                                //---If Pentomino piece coordinates did't overlap the forbidden map's grids
                                //   then add them to the table of all possible positions of the 
                                //   Pentomino piece on each map's grid
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
                                    for (int n = 0; n < (map.length * map[0].length) - holeAmount + pieceAmount; n++) { 
                                        if (n < pieceAmount) {
                                            finalRow.add(pieceRow[n]);

                                        } else {

                                            finalRow.add(row[n - pieceAmount]);
                                        }
                                    }
                                    //finalRow.add(rowCount); 
                                    finalPositionsCoordinatesList.add(finalRow);
                                    rowCount++;
                                }
                            }
                            //---Reset the puzzle map
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
                    pentList.get(v).rotate();
                }
                pentList.get(v).reflect();
            }
        } 
    }//end--createCoordinatesTable();

    /**
     * Clean the Pentomino set provided from the input.
     * If "*" was provided then it will be removed, otherwise 
     * the untuched original string will be returned. 
     * @param pent_to_use_input - original string representing Pentominoes 
     *                            set provided from the input
     * @return - clened string representing the given Pentominoes set
     */
    private static String cleanPentominoesInput(String pent_to_use_input) {
        String cleaned_string = pent_to_use_input;
        if(use_any) {
            cleaned_string = pent_to_use_input.substring(0, pent_to_use_input.length() - 1);
        }
        return cleaned_string;
    }//end--cleanPentominoesInput();

    /**
     * Checks if the provided puzzle map is of rectangular shape.
     * If it's not then warning will be displayed and the application will exit.
     * Otherwise, nothing will happen.
     * @param map_input - provided puzzle map 
     */
    private static void checkMapIsRectangular(List<List<Character>> map_input) {
        int prev_row_size = map_input.get(0).size(); 
        //if( map_input.get(1).size() != 0) {
        if( map_input.size() > 1) {
            for(int rw=1; rw<map_input.size(); rw++) {  
                if(map_input.get(rw).size() != prev_row_size) {
                    System.out.println("Map is not rectangular!");
                    //break;
                    System.exit(0);
                }
            }
        }
    }//end--checkMapIsRectangular();

    /**
     * Checks if the provided puzzle map can be completely covered by Pentomino.
     * It does so by checking if the free map's greed are divisible by 5.
     * @param free_map_blocks - number of free map's greed
     */
    private static void validatePieceAmount(int free_map_blocks) {
        if(free_map_blocks % 5 != 0) {
            System.out.println("This map cannot be coverd by Pentominoes as it's free blocks are not divisible by 5!");
            System.exit(0);
        }        
    }//end--validatePieceAmount();

    /**
     * Main trigger to start searching for solution.
     * First it calls method for creating a matrix containing all possible 
     * positions of each Pentomino piece on each map's grid.
     * Then it removes duplicate rows from this matrix (i.e. 
     * ignore reflections/rotations of such Pentominoes pieces as"X").
     * Then it creates "Linked List" representation of this matrix so it 
     * can be used as Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * Finally the Algorithm X with Dancing Links is used to solve the Pentominoes puzzle.
     * @param current_Pentomino8es_generated_set - current set of Pentomino to be used to 
     *                                             solve the puzzle.
     */
    private static void searchForSolution(List<Pentomino8> current_Pentomino8es_generated_set) {
        createCoordinatesTable(current_Pentomino8es_generated_set);
        finalPositionsCoordinatesList = removeSameRows(finalPositionsCoordinatesList);                 
        createDancingLinksMatrix(finalPositionsCoordinatesList);   
        backTrackLinks(0);
    }//end--searchForSolution();

    /**
     * Prints to the terminal the final solution.
     * First it prints the Pentominoes set which was used to solve the puzzle
     * and then the map displaying how this pieces were placed on the solved map. 
     * @param pentList_given - set of Pentominoes which was used to solve the puzzle
     */
    private static void printSolution(List<Pentomino8> pentList_given) {
        //System.out.println(pent_to_use);
        //---Print Pentominoes set:
        for(int i=0; i<pentList_given.size(); i++) {
            System.out.print(pentList_given.get(i).name);
        }
        System.out.println();
        //---Print map:
        int[][] solvedMap = RetrieveSolutions(pentList_given);
        //printSolvedMap(RetrieveSolutions(pentList_given), pentList_given); 
        printSolvedMap(solvedMap, pentList_given);
        //System.exit(0); 
    }//end--printSolution();

    /**
     * Main method. 
     * @param args - a text file containing the Pentominoes puzzle.
     *               The first line will specify the pieces to use 
     *               (using Conway notation from O-Z).
     *               The remaining lines will be a rectangular grid 
     *               of .s and *s representing the puzzle to solve. 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{ 
        System.out.println("===Start===");        
        //==============
        //---All the Pentominoes form O-Z
        List < Pentomino8 > pentList_default = new ArrayList < > ();
        //---Only user provided Pentominoes
        List < Pentomino8 > pentList_given = new ArrayList < > ();
        //---Pupulate with all default Pentominoes (using Conway notation from O-Z)
        pentList_default.add(new Pentomino8('O', O));    
        pentList_default.add(new Pentomino8('P', P));    
        pentList_default.add(new Pentomino8('Q', Q));    
        pentList_default.add(new Pentomino8('R', R));    
        pentList_default.add(new Pentomino8('S', S));    
        pentList_default.add(new Pentomino8('T', T));    
        pentList_default.add(new Pentomino8('U', U));    
        pentList_default.add(new Pentomino8('V', V));    
        pentList_default.add(new Pentomino8('W', W));    
        pentList_default.add(new Pentomino8('X', X));    
        pentList_default.add(new Pentomino8('Y', Y));    
        pentList_default.add(new Pentomino8('Z', Z));    
        //==============

        //----------------------------INPUT:

        //---Check if user input was provided
        if(args.length == 0) {
            System.out.println("No file given!");
            System.exit(0);
        }

        //---Get the input puzzle file
        String inputFile = args[0];      
        //System.out.println("File input: " + inputFile);
        File puzzle = new File(inputFile);
        //File puzzle = new File("test.txt");
        //System.out.println("puzzle --> " + puzzle);
        Scanner scan = new Scanner(puzzle);

        //===============Process the puzzle file:
        //---Get pentamino pieces:
        String pent_to_use_input = "";
        //---Get set of Pentominoes which will be used for solving the puzzle
        if(scan.hasNextLine()) { 
            pent_to_use_input = scan.nextLine().trim();            
        } else {
            System.out.println("File is empty!");
            System.exit(0);
        }
        List <List <Character> >  map_input = new ArrayList < > ();      
        //---Get puzzle map: 
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
                    //System.out.println("--EMPTY LINE DETECTED--");
                }
            }
            //System.out.println("====== map_input : ");
            //System.out.println(map_input);
            //---Process map:
            checkMapIsRectangular(map_input);
            processMapInput(map_input);
            //---
        } else {
            System.out.println("Please provide map !!!");
            System.exit(0);
        }
        //---close Scanner:
        scan.close();
        //--------------------------------------------    
        //--------------------------------------------Try to solve the Pentominoes puzzle:
        //pentList_given = pentList_chosen;
        //===Process Pentomino8es input:
        pentList_given = processPentomino8esInput(pent_to_use_input, pentList_default);
        String pent_to_use = cleanPentominoesInput(pent_to_use_input);
        //---Got amount of provided Pentominoes
        int pieceAmount = pentList_given.size();
        //---Get amount of free block on the puzzle map (to be filled with the Pentominoes) 
        int free_map_blocks = ( map.length * map[0].length ) - holeAmount;
        //---Count how many Pentominoes figures are needed to completely cover the puzzle map
        int figs_amount_needed = (((map.length*map[0].length)-holeAmount) / 5);
        //---Checks if the provided puzzle map can be completely covered by Pentominoes
        validatePieceAmount(free_map_blocks);
        //============================================
        //---Check if the amount of Pentominoes pieces given are exact amount needed to cover the map
        if( use_any==false &&  (free_map_blocks/ (double) pieceAmount) == 5.0) {  //for_useAny--start      
            searchForSolution(pentList_given);
            //---Print Final Solution:
            printSolution(pentList_given); 
        //---Check if the are Not enough Pentominoes pieces are given to cover the map  
        } if(use_any==false &&  (free_map_blocks/ (double) pieceAmount) > 5.0) {
            System.out.println("Not enough pieces is given to cover the map!");
            System.out.println(">>>NO_Soln<<<");
            System.exit(0);
        //---Check if there are more pieces are given then needed to cover the puzzle map
        } if(use_any==false &&  (free_map_blocks/ (double) pieceAmount) < 5.0){
            System.out.println("more pieces are given then needed!");
            System.out.println("***********************************************************"); 
            //---A list to hold a currently generated set of Pentominoes
            List < Pentomino8 > current_Pentomino8es_generated_set = new ArrayList < > ();
            //---Generate Pentomino8es set one at a time:
            comboGenNoRepeat8 comboGenNoRepeat8 = new comboGenNoRepeat8(stringToArray(pent_to_use), figs_amount_needed);
            //---Get the first generated set
            char[] combo =  comboGenNoRepeat8.returnCombo();            
            //---Keep generating a new Pentominoes set until no more combinations are 
            //   possible or a final solution was found:
            while(comboGenNoRepeat8.getTotalCombinationsLeft()>0 && solved == false) {
                System.out.println("===> possibilities_left = " + comboGenNoRepeat8.getTotalCombinationsLeft());
                if (combo != null) { 
                    //---Debug:
                    System.out.println("---Trying: ");
                    comboGenNoRepeat8.printCombo();
                    System.out.println(comboGenNoRepeat8);
                    //---
                    current_Pentomino8es_generated_set = processPentomino8esInput(new String(combo), pentList_default);
                    searchForSolution(current_Pentomino8es_generated_set);
                    combo = comboGenNoRepeat8.genNextCombo();
                }//end--if();
            }//end--while();
            //---Print Final Solution:
            printSolution(current_Pentomino8es_generated_set); 
        //---Check if '*' was provided (i.e. use any Pentominoes piece and as many or none)    
        } if(use_any==true) {
            System.out.println("use any piece or none!");   
            System.out.println("***********************************************************"); 
            //---A list to hold a currently generated set of Pentominoes
            List < Pentomino8 > current_Pentomino8es_generated_set = new ArrayList < > ();
            //---Generate Pentomino8es set one at a time:
            ComboGeneratorWithRepeat8 comboGenWithRepeat = new ComboGeneratorWithRepeat8(stringToArray(pent_to_use), figs_amount_needed);
            //---Get the first generated set
            char[] combo =  comboGenWithRepeat.returnCombo();            
            //---Keep generating a new Pentominoes set until no more combinations are 
            //   possible or a final solution was found:
            while(comboGenWithRepeat.getTotalCombinationsLeft()>0 && solved == false) {
                System.out.println("===> possibilities_left = " + comboGenWithRepeat.getTotalCombinationsLeft());
                if (combo != null) { 
                    //---Debug:
                    System.out.println("---Trying: ");
                    comboGenWithRepeat.printCombo();
                    System.out.println(comboGenWithRepeat);
                    //---
                    current_Pentomino8es_generated_set = processPentomino8esInput(new String(combo), pentList_default);
                    searchForSolution(current_Pentomino8es_generated_set);
                    combo = comboGenWithRepeat.genNextCombo();
                }//end--if();
            }//end--while();                        
            //---Print Final Solution:
            printSolution(current_Pentomino8es_generated_set); 
        }// else {
        //System.out.println("===NO_SOLUTION_==="); //---GOOD
        //}//for_useAny--end
        //System.out.println("===END===Go8--8");
    }//end--main();    
}//END--[AppGo8_vk08]
