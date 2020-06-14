/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
@author William Krzanowski
 */

package latintextgamev1;


import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class TextgamePrototype {
  static Scanner sc = new Scanner(System.in);
  
Syntax syntax = new Syntax();
 static LatinVerb LOVE = new LatinVerb(Conjugation.I,"to love", "amo","am","amav","amat", Verbs.LOVE);
 static EventScheduler eventScheduler = new EventScheduler();
    static boolean end = false;
 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        
        boolean finished = false;
        Syntax.fillSyntaxList();
        eventScheduler.setUpEvents();
        
        
        Objects.Player.makeLocationGlobal();
        Objects.Winner = Objects.Player;
        System.out.println("Welcome to MAGE! \nA game where you type what you want to do in latin, and we respond with what happens in english!\n"
                + "To see your inventory, type \"i\".\n"
                + "To look around, type \"circumspicio\". \nTo go in a cardinal direction, type n to go north, sw to go southwest, etc.\n"
                + "To stop the program, type \"end\" or \"stop\" or \"close\" or \"shut off\". \n"
                + "\n To start off with, you're in the market of the thriving Roman city, Placeholder City.\n Your good friend Clemens just brought you some lunch to eat\n Before you go off to test your mettle against the fantastic adventures hidden in the Library of Heroes, north of here.\n Good luck!");
        while (!end) {
          
            eventScheduler.run();
            System.out.println("what do you want to do now?");
          // try{
           // System.out.println(Objects.Player.getRoom());}
           //catch (Exception E)
           //{System.out.println(E.getMessage());}
      String p = sc.nextLine().toLowerCase();
      if (p.contains("end") || p.contains("stop") || p.contains("close")|| p.contains("shut off"))
      {end = true;
      System.out.println("ending.");}
      //sc.close();
       // System.out.println("input string = \"" + p + "\"");
        Syntax.match(p);
/*try{
   
    Syntax.match(p);

}catch (Exception e)
{e.printStackTrace();}
        } */
//System.out.println(LatinVerbs.isSum(p));
    } 
    }
    
    //public void run() {}

    
}

