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

/**
 * Generator used for finding all possible combinations of m from set n (with Repetition)
 * @author Valentin Kiselev, Maaha Ahmad, Freeman Eckles, Leo Venn 
 *
 */
class ComboGeneratorWithRepeat { 
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
    public ComboGeneratorWithRepeat(char[] all,  int length) {
        this.all = all;
        this.indexes = new int[length];
        this.total_figs = all.length;
        this.length = length;
        this.max_index = all.length-1;
        //---Populate combos:
        this.isMoreCombos = true;
        for(int c=0; c<indexes.length; c++) {
            //this.indexes[c] +=c;
            this.indexes[c] = 0;
        }
        this.total_posibilities = countTotalPermutationPissibilitesWithRepeats();
        this.total_posibilities_left = total_posibilities;//-1;
    } 

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
    } 

    /**
     * Finds a total number of  all possible combinations of m from set n (with Repetition)
     * @return - number of all possible combinations 
     */
    private long countTotalPermutationPissibilitesWithRepeats() {
        //------
        //****************************************************************************************//
        // Formula for finding all possible combinations of m from set n (with Repetition):       //
        // C = (r+n-1)! / ( r!*(n-1)! )                                                           //
        //****************************************************************************************//
        //------
        int max_chars = total_figs;
        int combo_length = length;
        BigInteger total_possibilities = findFactorial(combo_length + max_chars - 1).divide(findFactorial(combo_length).multiply(findFactorial(max_chars - 1)));
        return total_possibilities.longValue();
    } 

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
                //System.out.println("no more combinations left"); //debug
            }
        }
    } 

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
            //System.out.println("no more combinations left"); //debug
        }
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
    } 

    /**
     * Prints indexes of currently generated combination.
     */
    public String toString() {
        String pent = "";
        for(int i=0; i<indexes.length; i++) {
            pent += " " + indexes[i];  //---GOOD              
        }  
        return pent;
    } 

    /**
     * Prints currently generated combination
     */
    public void printCombo() {
        for(int i=0; i<indexes.length; i++) {
            System.out.print(all[indexes[i]]);
            //System.out.print(", ");
        }        
        System.out.println();
    } 

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

}//END--[ComboGeneratorWithRepeat]


/**
 * Generator used for finding all possible combinations of m from set n (without Repetition)
 * @author Valentin Kiselev
 *
 */
class ComboGeneratorNoRepeat { 
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
    public ComboGeneratorNoRepeat(char[] all,  int length) {
        this.all = all;
        this.indexes = new int[length];
        this.total_figs = all.length;
        this.length = length;
        this.max_index = all.length-1;
        //---Populate combos:
        this.isMoreCombos = true;
        for(int c=0; c<indexes.length; c++) {
            this.indexes[c] +=c;
        }
        this.total_posibilities = countTotalPermutationPissibilitesNoRepeats();
        this.total_posibilities_left = total_posibilities;
    }  

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
    }   

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
        return total_possibilities.longValue();
    } 

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
                //System.out.println("no more combinations left"); //debug
            }
        }
    } 

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
            //System.out.println("no more combinations left"); //debug
        }
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
    } 

    /**
     * Prints indexes of currently generated combination.
     */
    public String toString() {
        String pent = "";
        for(int i=0; i<indexes.length; i++) {
            pent += " " + indexes[i];                
        }  
        return pent;
    } 

    /**
     * Prints currently generated combination
     */
    public void printCombo() {
        for(int i=0; i<indexes.length; i++) {
            System.out.print(all[indexes[i]]);
            //System.out.print(", ");
        }        
        System.out.println();
    } 

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

}//END--[ComboGeneratorNoRepeat]

/**
 * Linked list structure to represent unsolved table of Exact cover problem. 
 * Used to solve Exact cover problem by using Dancing Links 
 * of Algorithm X (proposed by  Donald Knuth)
 * @author Valentin Kiselev
 *
 */
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

/**
 * Pentomino structure to represent pentomino set.
 * @author Valentin Kiselev
 * 
 */
class Pentomino {  
    //---Char name to identify pentominoes (using Conway's notation from O-Z)
    final char name;
    //---An array to store pentominoes actual shapes 
    int [][] data;

    /**
     * Constructor
     * @param name - Char name to identify pentominoes (using Conway's notation from O-Z)
     * @param data - An array to store pentominoes actual shape representation
     */
    public Pentomino(char name, final int [][] data) {
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

}//END--[Pentomino]

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
public class try5_cleaned {    
    //---Stores amount of forbidden/inaccessible blocks on the given map 
    private static int holeAmount = 0;
    //---Header for the "linked list" table which represents Exact cover problem
    private static Node header = new Node();
    //---Stores solved rows per current solution during each "Backtracking" traversing
    //   through Dancing Links matrix iteration 
    private static List < Node > solved_matrix_rows = new ArrayList < > (); 
    //---Contains all possible solutions for solving current Pentomino puzzles
    private static List < List < Integer >> solved_matrix_allSolutions = new ArrayList < > ();
    //---Matrix to store Linked List structure for "Dancing Links" iteration
    private static Node[][] unsolved_matrix;
    //---Flag to identify if solution for the current Pentomino puzzle was found
    private static boolean solved = false;
    //---List containing all possible positions of each Pentomino piece on each map's grid:
    private static List < List < Integer > > finalPositionsCoordinatesList = new ArrayList < > ();
    //---Flag to identify if Pentomino set was followed by a * 
    //   (i.e. may use any number of pieces of any of the given types or don't use some at all)
    private static boolean use_any = false;    
    //---First line of input containing user's specified string containing set of Pentominoes to use
    private static String first_line_of_input = "";
    //---To store the given grid from input representing the puzzle map:
    private static List <List <Character> >  map_input = new ArrayList < > (); 

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
    private static int[][] map;
    //---Example: 
    //   '0' - the map's blocks which are allowed to be filled with a Pentomino
    //   '2' - the map's blocks which are forbidden to be filled with a Pentomino    //     
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 2, 2, 0, 0, 0 },
    //            { 0, 0, 0, 2, 2, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //            { 0, 0, 0, 0, 0, 0, 0, 0 },
    //       


    /**
     * Removes duplicate rows from list containing all possible 
     * positions of each Pentomino piece on each map's grid
     * @param finalList -list containing all possible positions of each Pentomino piece on each map's grid
     * @return - filtered list with removed duplicate rows
     */
    private static  List <List< Integer >> removeSameRows(List <List<Integer>> finalList){
        if(finalList.size() > 2) { // ignore the column header and the first row
            return finalList.stream().distinct().collect(Collectors.toList()); 
        }
        return finalList;
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
    } 

    /**
     * Process Pentominoes set received from standard input and which was given by a user.     *  
     * @param pent_to_use - Pentominoes set received from standard input
     * @param pentList_default - a list of allowed Pentominoes (i.e. of Conway notation from O-Z)
     * @param first_line_of_input - First line of input containing user's specified string 
     *                              containing set of Pentominoes to use   
     * @param map_input -  the given grid from input representing the puzzle map
     * @return - return a list of processed and filterd Pentominoes set
     */
    private static List < Pentomino > processPentominoesInput(String pent_to_use, List < Pentomino > pentList_default) {
        //===Process Pentominoes input:
        //String pent_to_use = "OPQRSTUVWXYZ";      //debug
        int total_Pentominoes_given = pent_to_use.length();
        List < Pentomino > pentList_chosen = new ArrayList < > ();
        //---Check that given Pentomino follow the Conway notation from O-Z.
        for(int c=0; c<pent_to_use.length(); c++) {
            for(int p=0; p<pentList_default.size(); p++) {
                //System.out.println("Processing: " + pent_to_use.charAt(c) ); //debug
                int compareChars = Character.compare(Character.toUpperCase(pent_to_use.charAt(c)), pentList_default.get(p).name); 
                int isStar = Character.compare(pent_to_use.charAt(c), '*');
                //---Check if '*' was provided
                if(isStar == 0) {
                    use_any = true;
                    total_Pentominoes_given -= 1; // minus 1 to compensate for *
                    //System.out.println("==> USE ANY or NONE"); //debug
                    break;
                }
                if(compareChars == 0) {
                    pentList_chosen.add(pentList_default.get(p));
                    break;
                }              
            }
        }
        //---Check if proper Pentominoes were provided:
        if(pentList_chosen.size() == 0 || pentList_chosen.size() < total_Pentominoes_given) {
            //System.out.println("Wrong Pentominoes given!!!"); //debug
            //System.out.println("Please use Conway's notation from O to Z"); //debug
            printSolutionNotPossible();
            System.exit(0);
        }     
        return pentList_chosen;
    }

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
                        //System.out.println("================= WRONG map input !!!!!"); //debug
                        //System.out.println("Allowed map characters: . for free block and * for used block"); //debug
                        printSolutionNotPossible();
                        System.exit(0);
                    }
                }
            }      
        } else {
            //System.out.println("Please provied proper map!"); //debug
            printSolutionNotPossible();
            System.exit(0);
        }
    } 

    /**
     * Retrieves computed coordinates of Pentominoes pieces 
     * which were computed to cover the puzzle map
     * and hence solve the Pentominoe puzzle.
     * If no such coordinates were found, then the "no solution" warning will be 
     * displayed and the app will exit. 
     * @param pentList_given - a list of Pentominoes whcih were used to solve the puzzle
     * @param first_line_of_input - First line of input containing user's specified string 
     *                              containing set of Pentominoes to use   
     * @param map_input -  the given grid from input representing the puzzle map
     * @return - a solved map containing all Pentominoes pieces which were used to solve it 
     *           (using Conway notation from O-Z)  
     */
    private static int[][] RetrieveSolutions(List < Pentomino > pentList_given) {
        int pieceAmount = pentList_given.size();
        if(solved_matrix_allSolutions.size() == 0) {
            //System.out.println("No Solution is possible!"); //debug
            printSolutionNotPossible();
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
        } 
        //System.out.println("---Solved map:"); //debug
        return solvedMap;
    } 

    /**
     * Print map with solved Pentomenoes set
     * @param map - puzzle map
     * @param pentList - Pentominoes set
     */
    private static void printSolvedMap(int map[][], List < Pentomino > pentList) {
        String output = "";
        //---Populate map with solved Pentomenoes set
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 0){                  
                    //output += " " + pentList.get((map[i][j]-1)).name;  // with space
                    output += pentList.get((map[i][j]-1)).name;  
                } else {
                    //output += " " + 0; //debug
                    //output += " " + "*"; // with space
                    output += "*";
                }
            }
            output += "\r\n";
        }
        System.out.println(output);
    } 

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
    private static Node createDancingLinksMatrix(List < List < Integer > > finalList ) {
        int RowNum_ = finalList.size()-1;
        int ColNum_ = finalList.get(0).size();

        unsolved_matrix = new Node[RowNum_+1][ColNum_];
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
        if(unsolved_matrix[0][0] == null) { unsolved_matrix[0][0] = new Node(); }
        header.right = unsolved_matrix[0][0];
        // link for left header column
        if (unsolved_matrix[0][ColNum_-1] == null) {
            unsolved_matrix[0][ColNum_-1] = new Node();
        }
        header.left = unsolved_matrix[0][ColNum_-1];

        unsolved_matrix[0][0].left = header;
        unsolved_matrix[0][ColNum_-1].right = header;
        return header;      
    } 


    /**
     * Utility function for backTrackLinks() for using to solve Exact cover problem by 
     * using Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * It efficiently covers (i.e.removes) linked list nodes which 
     * represent all possible coordinates/positions of each 
     * Pentomino piece on each map's grid
     * @param Node - Liked List representing original list of all valid 
     *                Pentomino coordinates on the puzzle map
     */
    private static void cover(Node Node) {
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
    } 

    /**
     * Utility function for backTrackLinks() for using to solve Exact cover problem by 
     * using Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * It efficiently uncovers (i.e. un-deletes) linked list nodes which 
     * represent all possible coordinates/positions of each 
     * Pentomino piece on each map's grid
     * @param Node - Liked List representing original list of all valid 
     *                Pentomino coordinates on the puzzle map
     */
    private static void uncover(Node Node) {
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
    } 

    private  static Node findSmallestColumn() { 
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
    } 

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
        Node rowNode;
        Node column;
        Node rightNode;
        Node leftNode;
        //Check if no more column left (we have covered the puzzle space --> hence solution was found)
        if(header.right == header) {
            //System.out.println("---PUzzle has been SOLVED !!!"); //debug
            solved =true;
            ///---Add the solution to other solutions
            List < Integer > found_solution = new ArrayList < > ();
            for(int s=0; s<solved_matrix_rows.size(); s++) {
                found_solution.add(solved_matrix_rows.get(s).rowID);
            }
            solved_matrix_allSolutions.add(found_solution); 
            return;
        }
        //Find column with smallest amount of 1's:
        column = findSmallestColumn();
        // cover the selected column
        cover(column);
        for(rowNode = column.down; rowNode!=column; rowNode=rowNode.down) {
            solved_matrix_rows.add(rowNode);
            for(rightNode=rowNode.right; rightNode!=rowNode; rightNode=rightNode.right) {
                cover(rightNode);
            }
            // go deeper recursively
            if(!solved) {              
                backTrackLinks(level+1);
            }
            //---If no solution found remove it 
            solved_matrix_rows.remove(rowNode);
            // and backtrack/uncover:
            column = rowNode.column;
            for(leftNode=rowNode.left; leftNode!=rowNode; leftNode=leftNode.left ) {
                uncover(leftNode);
            }          
        }
        uncover(column);      
    }

    /**
     * Create a matrix containing all possible positions of each Pentomino piece on each map's grid
     * @param pentList - list of Pentomino objects each representing Pentomenoe's internal structure.
     */
    private static void createCoordinatesTable(List < Pentomino > pentList) {
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
        List < Integer > column_row_Nodes = new ArrayList < > ();
        for(int i=0; i<pieceAmount+(map.length * map[0].length) - holeAmount; i++) {
            column_row_Nodes.add(1);
        }
        finalPositionsCoordinatesList.add(column_row_Nodes);
        //========================
        //---Flag to identify if current Pentomino shape/array was overlaid 
        //   over the inaccessible/forbidden puzzle map grids 
        boolean overHole = false;
        //---Go over each Pentomino piece figure:
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
                            //---Place each Pentomino's block over the map's block:
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
                                            //System.out.println("Over Hall !!!"); //debug
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
                                                //System.out.println(rowIndex); //debug
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
    } 

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
    } 

    /**
     * Checks if the provided puzzle map is of rectangular shape.
     * If it's not then warning will be displayed and the application will exit.
     * Otherwise, nothing will happen.
     * @param map_input - provided puzzle map 
     */
    private static void checkMapIsRectangular(List<List<Character>> map_input) {
        int prev_row_size = map_input.get(0).size(); 
        if( map_input.size() > 1) {
            for(int rw=1; rw<map_input.size(); rw++) {  
                if(map_input.get(rw).size() != prev_row_size) {
                    //System.out.println("Map is not rectangular!"); //debug
                    printSolutionNotPossible();
                    //break;
                    System.exit(0);
                }
            }
        }
    } 

    /**
     * Checks if the provided puzzle map can be completely covered by Pentomino.
     * It does so by checking if the free map's greed are divisible by 5.
     * @param free_map_blocks - number of free map's greed
     */
    private static void validatePieceAmount(int free_map_blocks) {
        if(free_map_blocks % 5 != 0) {
            //System.out.println("This map cannot be coverd by Pentominoes as it's free blocks are not divisible by 5!"); //debuggg
            printSolutionNotPossible();
            System.exit(0);
        }        
    } 

    /**
     * Main trigger to start searching for solution.
     * First it calls method for creating a matrix containing all possible 
     * positions of each Pentomino piece on each map's grid.
     * Then it removes duplicate rows from this matrix (i.e. 
     * ignore reflections/rotations of such Pentominoes pieces as"X").
     * Then it creates "Linked List" representation of this matrix so it 
     * can be used as Dancing Links of Algorithm X (proposed by  Donald Knuth).
     * Finally the Algorithm X with Dancing Links is used to solve the Pentominoes puzzle.
     * @param current_Pentominoes_generated_set - current set of Pentomino to be used to 
     *                                             solve the puzzle.
     */
    private static void searchForSolution(List<Pentomino> current_Pentominoes_generated_set) {
        createCoordinatesTable(current_Pentominoes_generated_set);
        finalPositionsCoordinatesList = removeSameRows(finalPositionsCoordinatesList);                 
        createDancingLinksMatrix(finalPositionsCoordinatesList);   
        backTrackLinks(0);
    } 

    /**
     * Prints to the terminal the final solution.
     * First it prints the Pentominoes set which was used to solve the puzzle
     * and then the map displaying how this pieces were placed on the solved map. 
     * @param pentList_given - set of Pentominoes which was used to solve the puzzle
     * @param first_line_of_input - First line of input containing user's specified string 
     *                              containing set of Pentominoes to use   
     * @param map_input -  the given grid from input representing the puzzle map
     */
    private static void printSolution(List<Pentomino> pentList_given) {
        int[][] solvedMap = RetrieveSolutions(pentList_given);
        //---Print Pentominoes set:
        for(int i=0; i<pentList_given.size(); i++) {
            System.out.print(pentList_given.get(i).name);
        }
        System.out.println();
        //---Print map:        
        //printSolvedMap(RetrieveSolutions(pentList_given), pentList_given); 
        printSolvedMap(solvedMap, pentList_given);
        //---Exit application:
        System.exit(0); 
    }

    /**
     * Prints to the terminal in case if no solution was not found.
     * If no solution exists then output printed:
     *       (1). echo the first line of the input
     *       (2). echo the given grid
     *       (3). add one line containing the word 'Impossible'.
     * @param first_line_of_input - First line of input containing user's specified string 
     *                              containing set of Pentominoes to use   
     * @param map_input -  the given grid from input representing the puzzle map
     */
    private static void printSolutionNotPossible() { 
        //---Print Pentominoes set:
        System.out.println(first_line_of_input);
        //---Print map:
        for(int i=0; i<map_input.size(); i++) {
            for(int j=0; j<map_input.get(i).size(); j++) {
                System.out.print(map_input.get(i).get(j));
            }
            System.out.println();
        }
        //---Print "Impossible":
        System.out.println("Impossible");
        //---Exit application:
        System.exit(0); 
    }

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
        //---All the Pentominoes form O-Z
        List < Pentomino > pentList_default = new ArrayList < > ();
        //---Only user provided Pentominoes
        List < Pentomino > pentList_given = new ArrayList < > ();
        //---Pupulate with all default Pentominoes (using Conway notation from O-Z)
        pentList_default.add(new Pentomino('O', O));    
        pentList_default.add(new Pentomino('P', P));    
        pentList_default.add(new Pentomino('Q', Q));    
        pentList_default.add(new Pentomino('R', R));    
        pentList_default.add(new Pentomino('S', S));    
        pentList_default.add(new Pentomino('T', T));    
        pentList_default.add(new Pentomino('U', U));    
        pentList_default.add(new Pentomino('V', V));    
        pentList_default.add(new Pentomino('W', W));    
        pentList_default.add(new Pentomino('X', X));    
        pentList_default.add(new Pentomino('Y', Y));    
        pentList_default.add(new Pentomino('Z', Z));  

        //---Check if user input was provided
        if(args.length == 0) {
            System.out.println("No file given!"); 
            printSolutionNotPossible();
            System.exit(0);
        }

        //---Get the input puzzle file
        String inputFile = args[0];      
        //System.out.println("File input: " + inputFile); //debug
        File puzzle = new File(inputFile);
        Scanner scan = new Scanner(puzzle);

        //===============Process the puzzle file:
        //---Get pentamino pieces:
        String pent_to_use_input = "";
        //---Get set of Pentominoes which will be used for solving the puzzle
        if(scan.hasNextLine()) { 
            pent_to_use_input = scan.nextLine().trim(); 
            first_line_of_input = pent_to_use_input;
        } else {
            //System.out.println("File is empty!"); //debug
            printSolutionNotPossible();
            System.exit(0);
        }             
        //---Get puzzle map: 
        if(scan.hasNextLine()) {        
            while(scan.hasNextLine()) {
                String tmp = scan.nextLine();
                if(!tmp.trim().isEmpty()) {
                    tmp = tmp.trim();          
                    List <Character> rw = new ArrayList < > ();
                    for(int i=0; i<tmp.length(); i++) {
                        rw.add(tmp.charAt(i));
                    }
                    map_input.add(rw);                    
                } else {
                    //System.out.println("--EMPTY LINE DETECTED--"); //debug
                }
            }
            //---Process map:
            checkMapIsRectangular(map_input);
            processMapInput(map_input);
            //---
        } else {
            //System.out.println("Please provide map !!!"); //debug
            printSolutionNotPossible();
            System.exit(0);
        }
        //---close Scanner:
        scan.close();

        //===============Try to solve the Pentominoes puzzle:
        //---Process Pentominoes input:
        pentList_given = processPentominoesInput(pent_to_use_input, pentList_default);
        String pent_to_use = cleanPentominoesInput(pent_to_use_input);
        //---Got amount of provided Pentominoes
        int pieceAmount = pentList_given.size();
        //---Get amount of free block on the puzzle map (to be filled with the Pentominoes) 
        int free_map_blocks = ( map.length * map[0].length ) - holeAmount;
        //---Count how many Pentominoes figures are needed to completely cover the puzzle map
        int figs_amount_needed = (((map.length*map[0].length)-holeAmount) / 5);
        //---Checks if the provided puzzle map can be completely covered by Pentominoes
        validatePieceAmount(free_map_blocks);
        //===============
        //---Check if the amount of Pentominoes pieces given are exact amount needed to cover the map:
        if( use_any==false &&  (free_map_blocks/ (double) pieceAmount) == 5.0) {  //for_useAny--start   
            //System.out.println("enough pieces are given!"); //debug
            searchForSolution(pentList_given);
            //---Print Final Solution:
            printSolution(pentList_given); 
            //---Check if the are Not enough Pentominoes pieces are given to cover the map: 
        } if(use_any==false &&  (free_map_blocks/ (double) pieceAmount) > 5.0) {
            //System.out.println("Not enough pieces is given to cover the map!"); //debug
            printSolutionNotPossible();
            //---Check if there are more pieces are given then needed to cover the puzzle map:
        } if(use_any==false &&  (free_map_blocks/ (double) pieceAmount) < 5.0){
            //System.out.println("more pieces are given then needed!"); //debug
            //---A list to hold a currently generated set of Pentominoes
            List < Pentomino > current_Pentominoes_generated_set = new ArrayList < > ();
            //---Generate Pentominoes set one at a time:
            ComboGeneratorNoRepeat ComboGeneratorNoRepeat = new ComboGeneratorNoRepeat(stringToArray(pent_to_use), figs_amount_needed);
            //---Get the first generated set
            char[] combo =  ComboGeneratorNoRepeat.returnCombo();            
            //---Keep generating a new Pentominoes set until no more combinations are 
            //   possible or a final solution was found:
            while(ComboGeneratorNoRepeat.getTotalCombinationsLeft()>0 && solved == false) {
                //System.out.println("combinations left: " + ComboGeneratorNoRepeat.getTotalCombinationsLeft()); //debug
                if (combo != null) { 
                    //System.out.println("---Trying: "); //debug
                    //ComboGeneratorNoRepeat.printCombo(); //debug
                    current_Pentominoes_generated_set = processPentominoesInput(new String(combo), pentList_default);
                    searchForSolution(current_Pentominoes_generated_set);
                    combo = ComboGeneratorNoRepeat.genNextCombo();
                } 
            } 
            //---Print Final Solution:
            printSolution(current_Pentominoes_generated_set); 
            //---Check if '*' was provided (i.e. use any Pentominoes piece and as many or none):    
        } if(use_any==true) {
            //System.out.println("use any piece or none!");   //debug
            //---A list to hold a currently generated set of Pentominoes
            List < Pentomino > current_Pentominoes_generated_set = new ArrayList < > ();
            //---Generate Pentominoes set one at a time:
            ComboGeneratorWithRepeat comboGenWithRepeat = new ComboGeneratorWithRepeat(stringToArray(pent_to_use), figs_amount_needed);
            //---Get the first generated set
            char[] combo =  comboGenWithRepeat.returnCombo();            
            //---Keep generating a new Pentominoes set until no more combinations are 
            //   possible or a final solution was found:
            while(comboGenWithRepeat.getTotalCombinationsLeft()>0 && solved == false) {
                //System.out.println("combinations left: " + comboGenWithRepeat.getTotalCombinationsLeft()); //debug
                if (combo != null) { 
                    //System.out.println("---Trying: "); //debug
                    //comboGenWithRepeat.printCombo(); //debug
                    current_Pentominoes_generated_set = processPentominoesInput(new String(combo), pentList_default);
                    searchForSolution(current_Pentominoes_generated_set);
                    combo = comboGenWithRepeat.genNextCombo();
                } 
            }                        
            //---Print Final Solution:
            printSolution(current_Pentominoes_generated_set); 
        } 
    }//end--main();    
}//END--[App]
