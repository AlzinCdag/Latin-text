/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textgameprototype;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class TextgamePrototype {
static Scanner sc = new Scanner(System.in);
Syntax syntax = new Syntax();
 static LatinVerb LOVE = new LatinVerb(Conjugation.I,"to love", "amo","am","amav","amat", Verbs.LOVE);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        
        boolean finished = false;
        Syntax.fillSyntaxList();
       System.out.println("NS: clemens:" + Objects.clemensName.getCase("clemens"));  
       System.out.println("GS: clementis:" + Objects.clemensName.getCase("clementis"));
       System.out.println("DS:clementi:" + Objects.clemensName.getCase("clementi"));
      System.out.println("AS: clementem: " + Objects.clemensName.getCase("clementem"));
      System.out.println("OS: clemente" + Objects.clemensName.getCase("clemente"));
        
        
        
        Objects.Player.makeLocationGlobal();
        while (true) {
            System.out.println("what do you want to do now?");
          // try{
           // System.out.println(Objects.Player.getRoom());}
           //catch (Exception E)
           //{System.out.println(E.getMessage());}
      String p = sc.nextLine().toLowerCase();
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
