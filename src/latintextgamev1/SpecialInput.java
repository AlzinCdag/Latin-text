/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;
import java.util.Scanner;

/**
 *There will be times when the game requires specific input from the player, like yes or no. This class will receive that input and output a number.

 * @author krzan
 */
public class SpecialInput {
     
    /** creates a scanner that takes the next line and searches it to see if it contains any of the arguments.
     * @return a number corresponding to the argument matched. No match gives -1. if it matches the first argument, gives 1, second, 2, and so on.*/
    public static int specialMatch(String... possibleInputs) {
    Scanner sc = new Scanner(System.in);
      String p = sc.nextLine().toLowerCase();
   int retVal = -1;
      int i = 1;
      for (String pos:possibleInputs)
   {if (p.contains(pos))
   {retVal = i;}
   i++;
       }
    
    return retVal;
    }
    
}
