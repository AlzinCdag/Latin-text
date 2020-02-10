/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textgameprototype;
import java.util.regex.*;

/**
 *
 * @author Administrator
 */
public class Objects {
// Objects: Hammer, Nail, Nothing
    
 
    /**takes in a String, for example, one of the groups captured in Syntax.match(),
     * and iterates through all the nouns until one noun matches it in name or adjective, then returns the noun.*/ 
     public static LatNoun parse(String p) {
         boolean matched = false;
         LatNoun matchNoun = Nothing;
         for(LatNoun n:mNouns)
         {
            if (n.isNameWithCase(p) != null)
        {matched = true;
        matchNoun = n;}
        }//for 
        if (matched == false)
         return Nothing;
        else 
            return matchNoun;}

  
 
 static final  String[] hamAdj = {"large", "heavy","iron"};
 static final Noun Hammer = new Noun("hammer","A large, heavy, iron hammer",
         "Next to the furnace, a large, heavy iron hammer is gathering dust.", null, "hammer") {
    @Override
        public boolean handle() {if (Syntax.V == Verbs.ATTACK)
        {System.out.println("The hammer clangs as it hits the " + Syntax.userIO+".");
        return true;}
        else 
            return false;
        }
        @Override
        public void setFlags() {setTool(true); setAdjectives("large","heavy","iron");}
}; //Hammer
   
 
 
 //use this as the default object to return if the parser fails
 static final LatNoun Nothing = new LatNoun(null,null,null, new NounWord("nothing",NounEndings.FIRST, false)) {
 @Override
 public boolean handle() 
 {System.out.println("I didn't understand the way you referred to the noun, . How else can you describe it?"); return true;}
 };
 
 public static final Room Rooms = new Room("Rooms","You have entered a hyperspatial location that holds all the rooms in the game.") {
 public boolean handle() { 
     /*if (Syntax.V == Verbs.N) {
         Noun.move(Player, Syntax.getCurrentPlayerLoc().getN());
         return true; }
     else*/ return false;
         }
 };
 
 public static Room Forum = new Room("Forum Entrance","The Roman-style forum, the market and general hub of life in Placeholder City."
         + " \n you can see bustling market stalls to the south, while the Library of Heroes is to the north. ") {
         @Override
           public void setFlags() {
         setLocation(Rooms);
         }
         };
  public static Room Market = new Room("Market","A row of bustling market stalls that continues to the south. The forum entrance is to the north.") {
    @Override
     public void setFlags() { 
         setN(Forum);
         Forum.setS(this);      
 }
 };
  
  public static Room Library = new Room("Library","A small library of magical volumes. There is a door to the Forum to the south.") {
   @Override
     public void setFlags() { 
         setS(Forum);
         Forum.setN(this); }
  };
  
  public static Room Party = new Room("Party", "You are in a Bronze Age palace. There are lots of partygoers here with expensive presents. One is lugging a Rolex sundial towards a huge pile."){ // rolex sundials, fondant, lobster, guy splashed by a lady with wine, convertible chariots, lady painting a selfie 
         @Override
     public void setFlags() { 
          }
  };
  //
  
         
     static  NounWord clemensName = new NounWord("clemen",NounEndings.THIRDMF, true, "s","t");
    static NounWord evilKing = new Nounword("re",NounEndings.THIRDMF, true, "x", "g");
 
     static NounWord me = new NounWord("",NounEndings.EGO, false);
 static final LatNoun Player = new LatNoun("You are yourself","Look in a mirror!",Forum,me) {
     public void setFlags() {
     invisible = false;
     }
     @Override
     public boolean handle() {
    makeLocationGlobal();
     return false;}
 
 /** During testing of moving from room to room, the global variable of the player's location
  * in syntax lagged behind the actual player location because it was set before, not after the 
  move took place (through the player's handling code.) This method is an attempt to separate the player's
  * handle method from the mere process of setting the location so the handle method can be used for other purposes.
  */
 @Override
 public void makeLocationGlobal() { try
     {Syntax.setCurrentPlayerLoc( getLocation());}
     catch (Exception E) {
       E.printStackTrace();
     }
 }// makelocationglobal
 
 };
 
 
 
 
 
 
  static final LatNoun Clemens = new LatNoun("Clemens, servus Caecilii.","Clemens, servus Caecilii, in horto est cenam gustantis.", Market, clemensName )
 { public boolean handle() {return false;}
};
  static final LatNoun Rex = new LatNoun("Malus Rex.", "Polydectes, rex insulae Seriphi, stat accipens donos.", Party,evilKing) {
  public boolean handle() {
      boolean retVal = false;
  if (Syntax.V = Verbs.SPEAK)
  {}
 return retVal;
  }
  
  };
    
  static final LatNoun Cena = new LatNoun("Cena.","Cena est bonam.",Player, new NounWord("cen",NounEndings.FIRST,false)) {
  public boolean handle() {return false;}};
  
  static final LatNoun Book = new LatNoun("Liber, Persei Medusaeque.", " Liber Persei Medusaeque in mensa. Voles legere?", Library, new NounWord("lib",NounEndings.THIRDMF, true, "er","r",false) )
  {
      @Override
  public boolean handle() {
  
      boolean retVal = false;
      if (Syntax.V == Verbs.READ) {
  System.out.println("As often happens when reading, though rarely this literally, you find yourself sucked into the book. \n\n PERSEUS ET MEDUSA \n \n In Ancient Greece, a fisherman found a princess and her baby in a box washed up on the shore. "
          + "\n The princess and her son had been cast out of her kingdom because she had a child with Zeus, \n and the king thought the child would kill him.\n"
          + "\n Unfortunately, this island's king wanted to marry the princess. She thought he was a jerk.\n You, her demigod child, are the only person who can keep your mother safe from him.\n\n One day, the king sends you an invitation to a party. \n \"EVERYONE BRING PRESENTS!\" says the invitation. \n you have nothing to give...  ");
          move(Player,Party);
          Syntax.setCurrentPlayerLoc(Party);
          Verbs.LOOK.handle();
          retVal = true;
  
  }
      return retVal;
  }
  };
 
 static final LatNoun[] mNouns = { Player, Clemens, Cena, Book, Rex};
}
