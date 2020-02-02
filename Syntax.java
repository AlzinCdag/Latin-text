/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textgameprototype;
import java.util.regex.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Administrator
 */
public class Syntax {
  // HashMap inputToVerb
    //plan: make a verb class and a sentence structure class; sentence structure holdas a pattern and a 
    //verb. A for loop loops through a collection of these structures, plugging in the input, until a match is found.
    //at that point, the verb, the direct object, and the indirect object are set. After that, the indirect object and direct object are matched to an object in the game.
    //first the indirect object, then the direct object, then the verb are given the chance to handle the action.
public static LatinVerbs LVerbs = new LatinVerbs();
    public static String userDO;
 public static Noun DO;
 public static String userIO;
 public static Noun IO;
 public static Verb V;
 public static Location currentPlayerLoc;
 public static Location getCurrentPlayerLoc() {return currentPlayerLoc;}
 public static void setCurrentPlayerLoc(Location loc) {currentPlayerLoc = loc; }
    
   static ArrayList<SingleSyntax> syntaxes = new ArrayList<SingleSyntax>();
   static ArrayList<SingleSyntax> justVerbSyntaxes = new ArrayList<SingleSyntax>();
   static ArrayList<SingleSyntax> NoIOSyntax = new ArrayList<SingleSyntax>();
   /** only call this once*/
   public static void fillSyntaxList() {
       
       //capturing group for two words: (\\w+\\s*\\w*). For any characters: (.*)
       syntaxes.add(new SingleSyntax("hit (.*) with (.*)",Verbs.ATTACK,false));
      // syntaxes.add(syntaxes.get(syntaxes.size() - 1).addDefiniteArticle());
       justVerbSyntaxes.add(new SingleSyntax("look",Verbs.LOOK,false));
       justVerbSyntaxes.add(new SingleSyntax("i",Verbs.INVENTORY,false));
      justVerbSyntaxes.add(new SingleSyntax("n",Verbs.N,false));
       justVerbSyntaxes.add(new SingleSyntax("s",Verbs.S,false));
       justVerbSyntaxes.add(new SingleSyntax("e",Verbs.E,false));
       justVerbSyntaxes.add(new SingleSyntax("w",Verbs.W,false));
       justVerbSyntaxes.add(new SingleSyntax("ne",Verbs.NE,false));
       justVerbSyntaxes.add(new SingleSyntax("se",Verbs.SE,false));
       justVerbSyntaxes.add(new SingleSyntax("nw",Verbs.NW,false));
       justVerbSyntaxes.add(new SingleSyntax("sw",Verbs.SW,false));
       justVerbSyntaxes.add(new SingleSyntax("u",Verbs.U,false));
       justVerbSyntaxes.add(new SingleSyntax("up",Verbs.U,false));
       justVerbSyntaxes.add(new SingleSyntax("d",Verbs.D,false));
       justVerbSyntaxes.add(new SingleSyntax("down",Verbs.D,false));
                

       
       NoIOSyntax.add(new SingleSyntax ("take (.*)",Verbs.TAKE, false));
       NoIOSyntax.add(new SingleSyntax ("drop (.*)",Verbs.DROP, false));
       NoIOSyntax.add(new SingleSyntax ("look at (.*)",Verbs.LOOKAT, false));
       NoIOSyntax.add(new SingleSyntax ("look at the (.*)",Verbs.LOOKAT, false));
       NoIOSyntax.add(new SingleSyntax ("examine (.*)",Verbs.LOOKAT, false));
       NoIOSyntax.add(new SingleSyntax ("examine the (.*)",Verbs.LOOKAT, false));
       NoIOSyntax.add(new SingleSyntax ("unlock (.*)",Verbs.UNLOCK, false));
       NoIOSyntax.add(new SingleSyntax ("unlock the (.*)",Verbs.UNLOCK, false));
       NoIOSyntax.add(new SingleSyntax ("lock (.*)",Verbs.LOCK, false));
       NoIOSyntax.add(new SingleSyntax ("lock the (.*)",Verbs.LOCK, false));
}
   
    /** Matches the input to a specified syntax from a predefined list. 
     * Captures the specific noun phrases from that list as the Direct and indirect objects of the sentence.
     matches these grammatical direct and indirect objects to the Noun objects in the code, and obtains a verb from the matched SingleSyntax object.
     Calls the methods of first the indirect object, then the direct object, and if nothing else works, the verb to see if it handles it.*/
    public static void match(String x) {
  boolean matched = false;
    Scanner s = new Scanner(x);
    
    boolean isOddSyntax = false;
    boolean isNoIOSyntax = false;
    for (SingleSyntax odd : justVerbSyntaxes)
    {if (odd.getString().equalsIgnoreCase(x) )
        {isOddSyntax = true;
        matched = true;
       
        if (Objects.Player.handle() == false) {
       if ( odd.getVerb().handle() == false)
           System.out.println("You can't do that.");
        }// if the player object doesn't handle it
           }//if there's a match
    }
    
if(isOddSyntax == false)    
{
    
    for(SingleSyntax nios:NoIOSyntax) {
     if (s.findInLine(nios.getString()) != null) {
     matched = true;
     isNoIOSyntax = true;
         MatchResult result = s.match();
         
         if(result.group(1) !=  null)
     userDO = result.group(1);
     
     
     try{DO = Objects.parse(userDO);
     }//try
        catch (Exception e)
     {e.printStackTrace();}
         
        //if this is the correct syntax
        //put handling code here for other objects
   if (Objects.Player.handle() == false) {
        if (DO != null) {
        if (DO.handle() == false){
        if (nios.getVerb().handle() == false)
        System.out.println("I didn't understand that sentence.");
        }//DO
         }//DO is not null
         else if (nios.getVerb().handle() == false)
             System.out.println("I didn't understand that sentence.");
         }// if the player object doesn't handle it
     }//if this is the correct syntax
    }//for
    
    
    
    
    if (!isNoIOSyntax) { // for loop of syntaxes with verbs, IOs, and DOs.
    for(SingleSyntax syntax:syntaxes) {
     if (s.findInLine(syntax.getString()) != null) {
     matched = true;
         MatchResult result = s.match();
         
         if(result.group(1) !=  null) 
   //  System.out.println("DO = " + result.group(1));
   //  System.out.println(syntax.getString());
    // System.out.println("Captured groups: " +result.group(1) + " and " + result.group(2));
     userDO = result.group(1);
     
     
     try{DO = Objects.parse(userDO);
     
        if (result.groupCount() > 1) { // if more than one word is captured in this pattern
        userIO = result.group(2);
        IO = Objects.parse(userIO);
        //System.out.println("userIO = " + userIO);
       // System.out.println(IO.getFirstDescription());
               }//if more than one group
     }//try
        catch (Exception e)
     {e.printStackTrace();}
  
     someoneHandles(syntax);       
         }//if this is the correct syntax
        //put handling code here for other objects
  
        
    
     
    }//for
    }// if not a No IO syntax
}//if not an odd syntax                 
    
//if you need to assemble the sentence from scratch
     if (!matched) {
         V = null;
         String[] words = x.split(" ");
         for (int i = 0; i<words.length; i++)
         {
         if (LVerbs.parse(words[i]) != null)
         {V = LVerbs.parse(words[i]);
         }
         else if ((Objects.parse(words[i]) != Objects.Nothing) & (Objects.parse(words[i]) != null) )
         // ****put code here to sort out what the DO, IO, and OP are via the endings. If other cases are used besides 
             //accusative and dative, try to put them in an established format so that we can avoid case confusions.
         {
             String cTest = Objects.parse(words[i]).isNameWithCase(words[i]);
         if (cTest.contains("AP") || cTest.contains("AS"))
                 {DO = Objects.parse(words[i]);}
         if (cTest.contains("DP") || cTest.contains("DS") || cTest.contains("OP") || (cTest.contains("OS") && (!words[i].equalsIgnoreCase("me"))) ||(cTest.contains("OS") && (!words[i].equalsIgnoreCase("te"))))
                 {IO = Objects.parse(words[i]);}

         
         }
             
         }
        if ((IO != Objects.Nothing) && (IO != null) && (DO != Objects.Nothing) && (DO != null) && (V != null)) {
              //System.out.println("II" + DO.getLongDescription() + "II");
        if (IO.handle() == false) {
        if (DO.handle() == false){
        if (V.handle() == false)
        System.out.println("I didn't understand that sentence. V.handle = false, ");
        }//DO
        }//IO
         }//IO is not null
        
      
         
      else   if ((DO != Objects.Nothing) && (DO != null) && (V != null)) {
              //System.out.println("II" + DO.getLongDescription() + "II");
        if (DO.handle() == false){
        if (V.handle() == false)
        System.out.println("I didn't understand that sentence. V.handle = false, ");
        }//DO
         }//DO is not null
          else if (V != null)
          {if (V.handle() == false)
             System.out.println("I didn't understand that sentence. DO is null.");
          }
         else
         System.out.println("that sentence was not phrased in a way I understood.");}
     s.close(); 
     Objects.Player.makeLocationGlobal();
     V = null;
     DO = null;
     IO = null;
     
    }

    static private void someoneHandles(SingleSyntax syntax)  {
        
         if (Objects.Player.handle() == false) {
        if ((IO != null) && (DO != null)) {
        //if (IO == Objects.Nothing)
          //  System.out.println("IO = Nothing, userIO =" + userIO);
        //if (DO == Objects.Nothing)
          //  System.out.println("DO = Nothing, userDO = " + userDO);
        if (IO.handle() == false) {
        if (DO.handle() == false){
        if (syntax.getVerb().handle() == false)
        System.out.println("I didn't understand that sentence. IO, DO, and the verb didn't work.");
        }//DO
        }//IO
         }// both are not null
         else if ( DO != null)
         {if (DO.handle() == false){
        if (syntax.getVerb().handle() == false)
        System.out.println("I didn't understand that sentence. The DO and the Verb didn't work.");
        }//DO
         }// if there's only a direct object and a verb
         else if (syntax.getVerb().handle() == false)
             System.out.println("I didn't understand that sentence. The lone verb failed.");
         }// if the player object doesn't handle it
    } 
}
