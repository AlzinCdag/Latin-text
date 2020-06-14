/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

/**
 * miscellaneous methods
 * @author William Krzanowski
 */
public class Util {
 public static   String[] provideUpperLowerCamel(String[] list) {
 String[] newList = new String[4*list.length];
 for(int i = 0; i < list.length; i++) {
 newList[4*i] = list[i];
 newList[4*i + 1] = list[i].toUpperCase();
 newList[4*i + 2] = list[i].toLowerCase();
 newList[4*i + 3] = list[i].substring(0,1).toUpperCase() + list[i].substring(1).toLowerCase();
 }
 
 
 return newList;
 }
 
  public static   String[] threeDefiniteArticles(String[] list) {
 String[] newList = new String[4*list.length];
 for(int i = 0; i < list.length; i++) {
 newList[4*i] = list[i];
 newList[4*i + 1] = list[i].replace("(.*)", "the (.*)");
 newList[4*i + 2] = list[i].substring(0,list[i].lastIndexOf("(.*)")).replace("(.*)", "the (.*)") + list[i].substring((list[i].lastIndexOf("(.*)")),(list[i].length()));
 newList[4*i + 3] = list[i].substring(0,list[i].indexOf("(.*)") + 4) + list[i].substring((list[i].indexOf("(.*)") + 4),(list[i].length())).replace("(.*)", "the (.*)");
 
 }
 
 
 return newList;
 }
  static final String[] prepositions = {"in front of", "on top of","in", "on", "behind", "above", "under", "below", "near", "far from" };
  /** if the string contains a preposition, returns the index of the last letter of that preposition, else returns -1.*/
  public static int containsPreposition(String test) {
      boolean c;
      int i = -1;
      for (String p:prepositions) 
      {if (test.contains(p))
      {c = true;
      i = test.indexOf(p) + p.length();
      }//if
      }//for
      return i;
  }//method
  
  public static boolean doAndHas(Noun n) {
  boolean retVal = false;
  if (Syntax.DO == n & Objects.Winner.getWhetherContained(n))
      retVal = true;
  return retVal;
  }
    public static boolean ioAndHas(Noun n) {
  boolean retVal = false;
  if (Syntax.IO == n & Objects.Winner.getWhetherContained(n))
      retVal = true;
  return retVal;
  }
    
    public static void jigsUp()
    {Noun.move(Objects.Player, Objects.Library);
    Syntax.setCurrentPlayerLoc(Objects.Library);
    Noun.move(Objects.Book, Objects.Library);
    System.out.println(" You feel nauseous. When your vision clears, you find yourself back in the Library of Heroes.");
    
    }
}//util
