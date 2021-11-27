//Leo Venn ID:3430125
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class App {
  //Checks if there is one duplicate in a row or if row has no duplicates
  public static int isValidCheck2Dup(List < Integer > original) {
    for (int i = 0; i < original.size(); i++) {
      List < Integer > distinctList = (List < Integer > ) original.stream().distinct().collect(Collectors.toList());

      int dublicates = original.size() - distinctList.size();

      if (distinctList.size() == original.size() - 1) {

        return 1;
      } else if (distinctList.size() == original.size()) {
        return 2;
      }

    }
    return 0;

  }

  //Find the number associated with the valid duplicate
  public static int checkValidDupNo(List < Integer > original) {
    List < Integer > test = new ArrayList < > ();
    for (int i = 0; i < original.size(); i++) {
      if (!test.contains(original.get(i).intValue())) {
        test.add(original.get(i).intValue());
      } else {
        return (original.get(i).intValue());
      }

    }

    return 0;

  }
  //List of unique board game outcomes
  public static List < List < Integer >> unique = new ArrayList < > ();
  
  //Takes passed in board state and level (row number) to try to fix within the board
  public static int solve(List < List < Integer >> list, int level) {
	//Copy of board passed in in case there are multiple valid values to push down
    List < List < Integer >> copy = new ArrayList < > ();
    //Copy passed board into copy variable
    for (int i = 0; i < list.size(); i++) {

      List < Integer > temp = new ArrayList < > ();

      for (int j = 0; j < list.get(0).size(); j++) {
        temp.add(list.get(i).get(j).intValue());

      }

      copy.add(temp);

    }
	//Value within row to push
    int potValidPush = -100;
    //if the row is fixed
    boolean fixedRow = false;
    //if there is a zero above a value to be pushed (invalid push)
    boolean zeroUp = false;
	//Check for potential valid push number in the row
    if (isValidCheck2Dup(copy.get(level)) == 1) {
      potValidPush = checkValidDupNo(copy.get(level));

    } else if (isValidCheck2Dup(copy.get(level)) == 0) {

      return 0;
	//If there is no valid push and the row how no duplicate values
    } else {

	  //Print out solved table
      for (int o = 0; o < copy.size(); o++) {
        for (int p = 0; p < copy.size(); p++) {
          if (p != copy.size() - 1) {
            if (copy.get(o).get(p) == 0) {

              System.out.print("_ ");

            } else {
              System.out.print(copy.get(o).get(p) + " ");
            }

          } else {
            if (copy.get(o).get(p) == 0) {

              System.out.print("_");

            } else {
              System.out.print(copy.get(o).get(p));
            }

          }

        }
        System.out.println("");

      }

      System.out.println("");

	  //Convert solved table (array of array representing rows) in one array to represent entire board
      List < Integer > temp = new ArrayList < > ();
      for (int i = 0; i < copy.size(); i++) {
        for (int j = 0; j < copy.size(); j++) {

          if (copy.get(j).get(i) == 0) {

            for (int k = 0; k < copy.size(); k++) {
              temp.add(copy.get(j).get(k));
            }

          }
        }
      }
      //Add board to unique board then remove duplicates
      unique.add(temp);
      for (int i = unique.size() - 1; i > -1; i--) {
        for (int j = i - 1; j > -1; j--) {
          if (Arrays.deepEquals(unique.get(i).toArray(), unique.get(j).toArray())) {
            unique.remove(i);
            break;
          }
        }

      }

      return 0;

    }

	//for every row do the potential valid pushes and find the resulting table
    for (int j = 0; j < copy.get(0).size(); j++) {
	  //Clear copy and re copy original table for new pushes
      copy.clear();
      for (int i = 0; i < list.size(); i++) {

        List < Integer > temp = new ArrayList < > ();

        for (int p = 0; p < list.get(0).size(); p++) {
          temp.add(list.get(i).get(p).intValue());

        }

        copy.add(temp);
      }

	  //Check for if theres only one duplicate or none
      if (isValidCheck2Dup(copy.get(level)) == 1) {
        potValidPush = checkValidDupNo(copy.get(level));

      } else if (isValidCheck2Dup(copy.get(level)) == 0) {

        return 0;

      } else {

        return 1;

      }

      //Push the value if there is no 0 above it and recursively solve the new table
      if (copy.get(level).get(j).intValue() == potValidPush) {

        for (int k = level; k >= 0; k--) {
          if (list.get(k).get(j).intValue() == 0) {
            zeroUp = true;

          }

        }

        if (!zeroUp) {
          for (int n = copy.size() - 1; n >= level; n--) {
            if (n != level) {
              copy.get(n).set(j, copy.get(n - 1).get(j).intValue());
            } else {
              copy.get(n).set(j, 0);
            }

          }
 		  //Solve the new table with the pushed value
          if (solve(copy, level + 1) == 1) {
            return 1;

          }

        }
        //Reset ZeroUp to check the other duplicate value that can be pushed
        zeroUp = false;
        if (fixedRow) {
          fixedRow = false;
          break;
        }

      }

    }

    return 0;

  }
//Main Method
  public static void main(String[] args) {
    List < List < Integer >> original = new ArrayList < > ();
    List < Integer > line = new ArrayList < > ();
    Scanner scan = new Scanner(System.in);
    //Check if there is input
    if (!scan.hasNextLine()) {
      System.out.print("Bad format");
      System.exit(0);
    }
    //Read in lines as ints and check for bad format (char that cant be converted to ints)
    while (scan.hasNextLine()) {
      String lineScan = scan.nextLine();
      Scanner tokenScan = new Scanner(lineScan);
      line = new ArrayList < > ();
      while (tokenScan.hasNext()) {
        String token = tokenScan.next();

        try {
          Integer validInt = Integer.parseInt(token);

          line.add(validInt);

        } catch (Exception e) {
          System.out.println("Bad format");
        }
      }
      original.add(line);

    }
    
    //Do checks for row sizes and whether the int values are in acceptable range
    for (int o = 0; o < original.size(); o++) {
      if (original.get(o).size() - 1 != original.size()) {
        System.out.println("Bad format");
        System.exit(0);
      }
      for (int p = 0; p < original.get(0).size(); p++) {
        if (original.get(o).get(p) < 1 || original.get(o).get(p) > original.size()) {
          System.out.println("Bad values");
          System.exit(0);
        }

      }
    }

	//Add row of 0's at bottom of table so values can be pushed into them 
    int rowSize = original.get(0).size();
    List < Integer > temp = new ArrayList < > ();
    for (int i = 0; i < rowSize; i++) {

      temp.add(0);
    }
    original.add(temp);

    solve(original, 0);
    //If there is no unique solutions
    if(unique.size() == 0){
    	 System.out.println("Inconsistent results");
 	//Otherwise print unique solutions
    }else{
    	System.out.println("Different results: " + unique.size());
    }
    }
    
};