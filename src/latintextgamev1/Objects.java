/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

import java.util.regex.*;

/**
 *
 * @author William Krzanowski
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
 
 public static final Room Rooms = new Room("Rooms","You have entered a hyperspatial location that holds all the rooms in the game.", false) {
 public boolean handle() { 
     /*if (Syntax.V == Verbs.N) {
         Noun.move(Player, Syntax.getCurrentPlayerLoc().getN());
         return true; }
     else*/ return false;
         }
 };
 
 public static Room Forum = new Room("Forum Entrance","The Roman-style forum, the market and general hub of life in Placeholder City."
         + " \n you can see bustling market stalls to the south, while the Library of Heroes is to the north. ", false) {
         @Override
           public void setFlags() {
         setLocation(Rooms);
         }
         };
  public static final Room Market = new Room("Market","A row of bustling market stalls that continues to the south. The forum entrance is to the north.", false) {
    @Override
     public void setFlags() { 
         setN(Forum);
         Forum.setS(this);      
 }
 };
  
  public static Room Library = new Room("Library","A small library of magical volumes. There is a door to the Forum to the south.", false) {
   @Override
     public void setFlags() { 
         setS(Forum);
         Forum.setN(this); }
  };
  
  public static final Room Party = new Room("Party", "You are in a Bronze Age palace. There are lots of partygoers here with expensive presents. \nOne is lugging a Rolex sundial towards a huge pile.", false){
      // rolex sundials, fondant, lobster, guy splashed by a lady with wine, convertible chariots, lady painting a selfie 
         @Override
     public void setFlags() { 
        
          }
  };
  
    public static final Room OutsideParty = new Room("Outside Palace", "You are outside a Bronze Age palace. It is dark, and the stars glow, burning like the laughter of the voices within.\nThe entrance to the palace is east. A road winds to the west. \n You notice a trapdoor leading down into the earth next to the path. ", false){
      // rolex sundials, fondant, lobster, guy splashed by a lady with wine, convertible chariots, lady painting a selfie 
         @Override
     public void setFlags() { 
         Party.setW(this);
         setE(Party);
        
          }
  };
        public static final Room Road = new Room("Road", "A quiet, broad dirt road passing from the palace distant to the east to the ocean, distant to the west.  ", false){
      // rolex sundials, fondant, lobster, guy splashed by a lady with wine, convertible chariots, lady painting a selfie 
         @Override
     public void setFlags() { 
         OutsideParty.setW(this);
         setE(OutsideParty);
          }
  };
                public static final Room Cellar = new Room("Wine Cellar", "You are in the king's wine cellar he uses for parties. There is a stairway leading up. \nIt is dark and musty, and smells of the accumulated drippings of amphoras past.\n The party has been going on for some time, so it is almost empty.  ", false){
      // rolex sundials, fondant, lobster, guy splashed by a lady with wine, convertible chariots, lady painting a selfie 
         @Override
     public void setFlags() { 
         OutsideParty.setD(this);
         setU(OutsideParty);
          }
  };
    public static final Room Beach= new Room("Seriphos Western Shore","You are on the western shore of the island of Seriphos. \nA road goes east, while water extends, seemingly infinitely, to the west.", false){
     
         @Override
     public void setFlags() { 
          Road.setW(this);
         setE(Road);
          }
  };
      public static final Room WSea = new Room("Western Sea", " You fly across the surface of the sea to the west of Seriphos. Water extends in all directions.  ", true){
      
         @Override
     public void setFlags() { 
         Beach.setW(this);
         setE(Beach);
         setN(this);
         setS(this);
         setNE(this);
         setSE(this);
         setNW(this);
         setSW(this);
          }
  };
  
         public static final Room GreyShore = new Room("Island of the Grey Ladies","You are on the eastern shore of the island of the Grey Ladies.\n You know this is the place; the ground is grey, the sea is grey, and the sky is grey.\n A path extends to the north, and is hidden by rocks.", false){
     
         @Override
     public void setFlags() { 
          WSea.setW(this);
         setE(WSea);
          }
  };
         
          public static final Room GreyFire = new Room("The Grey Ladies' Outdoor Kitchen","This place scares the willies out of you.\n "
                  + "You are in a sandy clearing encircled by jagged rocks. There is a path to the south and a path to the west. \nIn the center is a huge cooking pot bubbling over a bone fire.\n"
                  + "Foul smells waft from it. But they are nothing compared to the smells of the people here.\n In the background, what is that you hear? \n Could that possibly be... music? ", false){
     
         @Override
     public void setFlags() { 
          GreyShore.setN(this);
         setS(GreyShore);
          }
  };
          
                   public static final Room GreyWShore = new Room("Island of the Grey Ladies' Western Shore","You are on the western shore of the island of the Grey Ladies. \n You know this is the place; the ground is grey, the sea is grey, and the sky is grey. A path extends to the east, and is hidden by rocks.", false){
     
         @Override
     public void setFlags() { 
          GreyFire.setW(this);
         setE(GreyFire);
          }
  };
  
                public static final Room WWSea = new Room("Sea Even Further To The West", " You fly across the surface of the sea to the west of the Grey Island. Water extends in all directions.  ", true){
      
         @Override
     public void setFlags() { 
         GreyWShore.setW(this);
         setE(GreyWShore);
         setW(this);
         setN(this);
         setS(this);
         setNE(this);
         setSE(this);
         setSW(this);
          }
  };
   
   public static final Room MedusaShore = new Room("Island of the Gorgons","You are on the southeastern shore of the island of the Gorgons. \nEver had a really bad feeling about a place?\nThis place trumps them all.\n"
           + "The whole place is covered in swirling, curving black volcanic stone. \nThere is no grass, no sand on the shore; no birds chirping, no insects humming, no trees rustling in the wind, no wind. The place is silent.\n"
           + "\n You saw this place from the air, and no vessel could make its way near it without danger. A reef- coral, it seems to be, from the color\n extends out from the island a far distance, just below the surface- a ship-wrecking monstrosity. "
           + "\nThe place has seen people, though. The most accurate sculptures you have ever seen, \n of warriors and shipwrecked merchants, men, women and children of every origin and class\n dot the shoreline.\n"
           + "And their gaze is always the same- fixed, frozen in horror.", false){
     
         @Override
     public void setFlags() { 
          WWSea.setNW(this);
         setSE(WWSea);
          }
  };

   public static final Room MedusaCave = new Room("Medusa's Cave","You are in a dark cave. Light comes only from the entrance you came from above you.", false){
     
         @Override
     public void setFlags() { 
          MedusaShore.setD(this);
         setU(MedusaShore);
          }
  };
  
  static final NounWord lobsterName = new NounWord("gammar", NounEndings.SECONDM);
  public static LatNoun Lobster = new LatNoun("Lobster Thermidor (gammarus, i)", "A steaming platter of lobster thermidor (gammarus, i) has just been placed on the table", Objects.Party, lobsterName ) {
  @Override 
        public boolean handle() {
        return false;
        }
  };
  static final NounWord wineName = new NounWord("vin",NounEndings.SECONDN);
  static final NounWord amphoraName = new NounWord("amphor", NounEndings.FIRST);
  public static LatNoun Amphora = new LatNoun("An amphora of finest Naxos wine.","In a corner sits a dusty amphora untouched by the kings' servers. ", Objects.Cellar, amphoraName) {
        public boolean handle() {
        return false;
        }
  };
  static final NounWord cupName = new NounWord("cali",NounEndings.THIRDMF,true,"x","c");
    public static LatNoun Cup = new LatNoun("A wooden cup (calix,calicis) for drinking wine.","On a shelf sits a wine-cup (calix, calicis). ", Objects.Cellar, cupName) {
        public boolean handle() {
          boolean retVal = false;
            if (Syntax.V == Verbs.GIVE && Syntax.IO == Mercury)
           {    retVal = true;
               Noun.move(Cup, Mercury);
               if (Objects.Cup.getWhetherContained(Objects.Wine))
                {System.out.println("Mercury drinks. \"Ah. That was great stuff.\" he says.");
              Noun.move(Objects.Wine, Objects.Amphora); Objects.Wine.setInvisible(true);
                Events.mercdrinks = true;}
        else {System.out.println("Mercury starts drinking. His eyes widen, and he looks embarassed.\n He makes a series of slurping noises, pretends to drain the cup in one swig,"
                + "\n and sighs, satisfied, as he puts it back down.\n Finally, he admits to you, \"You didn't pour me any wine.\".");}
           
           }
        return retVal;
        }
  };
  public static LatNoun Wine = new LatNoun("Wine (vinum,i), fine, aged, and dark-colored. ", "",Objects.Amphora, wineName) {
       public void setFlags() {
       setInvisible(true);
       }
      public boolean handle() {
        boolean retVal = false;
          if (Syntax.V == Verbs.GIVE && Syntax.IO == Mercury)
           {     retVal = true;
               Noun.move(Cup, Mercury);
               if (Objects.Cup.getWhetherContained(Objects.Wine))
                {System.out.println("Mercury drinks. \"Ah. That was great stuff.\" he says.");
              Noun.move(Objects.Wine, Objects.Amphora); Objects.Wine.setInvisible(true);
                Events.mercdrinks = true;}
        else {System.out.println("Mercury starts drinking. His eyes widen, and he looks embarassed.\n He makes a series of slurping noises, pretends to drain the cup in one swig,"
                + "\n and sighs, satisfied, as he puts it back down.\n Finally, he admits to you, \"You didn't pour me any wine.\".");}
           }
               return retVal;
        }
          
  };
  //
  
         
     static  final NounWord clemensName = new NounWord("clemen",NounEndings.THIRDMF, true, "s","t");
     static final NounWord rexName = new NounWord("re",NounEndings.THIRDMF,true, "x","g");
 
     static NounWord me = new NounWord("",NounEndings.EGO, false);
 static final LatNoun Player = new LatNoun("You are yourself","Look in a mirror!",Market,me) {
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
 
 @Override
 public String toString() {
 return "you";}
 };
 
 /**This is an important object. It represents the object that is supposed to execute the verb. Usually, it is set to Player, but if you use Verb TalkTo,
  * it sets Winner to be the person you are talking to. Then, the verb can be set to do different things depending on the person. This enables sentences like "Claudius, give me the poison."  */
 public static LatNoun Winner;
 
 static NounWord mercName = new NounWord("mercuri",NounEndings.SECONDM);
 static final LatNoun Mercury = new LatNoun("Mercury, god of travel","A man stands in the temple. He looks charming in his cap. He carries a staff wrapped in ornamental snakes, and his sandals have wings. \n He wears a nametag saying: \nHELLO, MY NAME IS \n Mercurius\n\n You get the impression that this man is more than mortal.\n As a side note, he looks kind of thirsty.",Objects.Road,mercName) {
 @Override
         public void setFlags() {
setUntakeable(true);
setCharacter(true);
         }
     @Override
 public boolean handle() {
 return false;}
 };
 
  static NounWord greyName = new NounWord("grae",NounEndings.FIRST,true);
 static final LatNoun Grey = new LatNoun("grey ladies (graea, or, since it is plural, graeae)","Three old women, the Grey Ladies (graea, or, since they are is plural, graeae) dance around the cooking pot.\n  They are as grey as sea foam. \n"
         + "Their eye sockets are hollow- wait, not all. One grey sister alone has an eyeball (oculus),\n which whirls furiously in its socket as if it is unconnected.",Objects.GreyFire,greyName) {
 @Override
         public void setFlags() {
setUntakeable(true);
setCharacter(true);
         }
     @Override
 public boolean handle() {
 return false;}
 };
   static NounWord medName = new NounWord("medus",NounEndings.FIRST);
   static boolean medLethal = true;
 static final LatNoun Medusa = new LatNoun("Medusa","In the image of the shield, you see MEDUSA sleeping here. \n The image of her head in the shield is so horrible, you can't believe it tells the truth.",Objects.MedusaCave,medName) {
 @Override
         public void setFlags() {
setUntakeable(true);
setCharacter(true);
         }
     @Override
 public boolean handle() {
 boolean retVal = false;
 if (Syntax.V == Verbs.LOOKAT || Syntax.V == Verbs.LOOK )
 {retVal = true;
 if (medLethal)
 {System.out.println("You look at Medusa, and too late the fear of the punishment of the gods falls on you. Too late. You freeze as still as stone, and then you are stone.");
Util.jigsUp();}
 else {
 System.out.println("Medusa is beautiful, even though you can't see her head.");
 }
 }
     return false;}
 };
 
 static final LatNoun Head = new LatNoun("head (caput, capitis) of Medusa","The snake-haired head of Medusa.",Rooms, new NounWord ("cap",NounEndings.THIRDN,true,"ut","it")) {
       public void setFlags() {
       setWearable(false);
       }
       public boolean handle() {
           boolean retVal = false;
           if (Syntax.V == Verbs.PUT && Syntax.IO == Bag)
           {System.out.println();
           medLethal = false;
           retVal = true;
           }
           else if (Syntax.V == Verbs.PUT)
           {System.out.println("You have to be very, very careful where you put this. The only safe place is your sack(saccus).");
           retVal = true;}
           return retVal;
       }
 };
 
  static final LatNoun Bag = new LatNoun("burlap sack (saccus)","A head-sized burlap sack.",Mercury, new NounWord("sacc",NounEndings.SECONDM,false)) {
       public void setFlags() {
       setInvisible(true);
       setWearable(true);
       setActivationProcesses(() -> {  if (Objects.Winner == Objects.Player)
            System.out.println("You put the sack (saccus) over your head.\n It is breathable, and you can see through it, \nbut nobody could see your face beneath it.");
                 else System.out.println(Objects.Winner + " puts the sack (saccus) on her head.");
       if (mLocation == Medusa)
       {medLethal = false;}
           }, 
               () -> {  if (Objects.Winner == Objects.Player)
            System.out.println("You take off the sack(saccus) and can see and breathe a little easier");
                 else {System.out.println(Objects.Winner + " takes off the sack from her head."); medLethal = true;}
               });
       }
       public boolean handle() {
           
           return false;}};
 
 
   static final LatNoun Helm = new LatNoun("Helmet (galea) of Darkness.","A helm of darkness, like the one Pluto wears, that makes the wearer invisible.",Mercury, new NounWord("gale",NounEndings.FIRST,false)) {
       public void setFlags() {
       setInvisible(true);
       setWearable(true);
       setActivationProcesses(() -> {Objects.Winner.setGameInvisible(true);  if (Objects.Winner == Objects.Player)
            System.out.println("As you put on the Helm of Invisibility, you notice yourself becoming as transparent as that last excuse you made for missing curfew.");
                 else System.out.println(Objects.Winner + " becomes invisible by putting on the Helm of Darkness.");}, () -> {Objects.Winner.setGameInvisible(false);   if (Objects.Winner == Objects.Player)
            System.out.println("As you take off the Helm of Invisibility, you notice yourself becoming as opaque as a wall. You knock yourself out trying to hang a picture on yourself before you realise your mistake.");
                 else System.out.println(Objects.Winner + " becomes visible by taking off the Helm of Darkness."); });
       }
       public boolean handle() {
           boolean retVal = false;
           if (Syntax.V == Verbs.GIVE && Syntax.IO == Medusa)
           {System.out.println("Medusa accepts it in her sleep, mumbling thanks, and then turns over. You might want to get out before she wakes up.");
           Noun.move(this, Medusa);
           Medusa.setGameInvisible(true);
           retVal = true;}
           
           return false;}};
   
 static final LatNoun Sandals = new LatNoun ("Winged Sandals (Sandalia, plural of sandalium)", "The winged sandals (Sandalia, plural of sandalium), which give the wearer the seriously overpowered gift of flight.", Mercury, new NounWord("sandali",NounEndings.SECONDN,true)) {
    public void setFlags() {
    setInvisible(true);
    setWearable(true);
    setActivationProcesses(() -> {System.out.println("As you put on the flying sandals, you begin to rise into the air. Nowhere now below the air is out of your reach.");},() -> {System.out.println("As you take off the flying sandals, you fall to the ground with a thump.");});
    }
     
     @Override
     public boolean handle() {
     return false;
     }
 };
 
 static final LatNoun Sword= new LatNoun ("Sword (gladius)", "An adamantine sword, the gift of Zeus", Mercury, new NounWord("gladi",NounEndings.SECONDM)) {
    public void setFlags() {
    setInvisible(true);
    setWeapon(true);
    }
     
     @Override
     public boolean handle() {
     return false;
     }
 };
 
  static final LatNoun Eye= new LatNoun ("Eye (oculus)", "A strange disembodied eye.", Grey, new NounWord("ocul",NounEndings.SECONDM)) {
    public void setFlags() {
    setInvisible(true);
    }
     
     @Override
     public boolean handle() {
         boolean retVal = false;
         if (Syntax.V == Verbs.TAKE)
         {Verbs.TAKE.handle();
         retVal = true;
         System.out.println("You snatch the horrible disgusting whirling eye out of the air. It is squishy in your fingers.\n You can hear the chorus of Grey Sisters start to falter as they wait for the eye to land.");
         }
         if (Syntax.V ==Verbs.GIVE && Syntax.IO == Objects.Grey)
         {System.out.println("\"Thank you very much!\" Cackle the Grey Sisters. \n \"Shall we kill him now?\" ask one. \"Ecch!\" exclaims another. \"He got our eye dirty! we need to clean it in water right away!\"\n \" Good thinking!\" The grey sisters busy themselves around their pot of water, ignoring you completely.");
         Noun.move(Objects.Eye, Objects.Grey);
         retVal = true;}
                  if (Syntax.V ==Verbs.DROP)
         {System.out.println("The grey sisters hear you drop the eye and swoop in to get it.\"We got it back!\" \n \"Shall we kill him now?\" ask one. \"Ecch!\" exclaims another. \"He got our eye dirty! we need to clean it in water right away!\"\n \" Good thinking!\" The grey sisters busy themselves around their pot of water, ignoring you completely.");
         Noun.move(Objects.Eye, Objects.Grey);
         retVal = true;}
             if (Syntax.V ==Verbs.PUT)
         {System.out.println("The grey sisters hear you put the eye down and swoop in to get it.\"We got it back!\" \n \"Shall we kill him now?\" ask one. \"Ecch!\" exclaims another. \"He got our eye dirty! we need to clean it in water right away!\"\n \" Good thinking!\" The grey sisters busy themselves around their pot of water, ignoring you completely.");
         Noun.move(Objects.Eye, Objects.Grey);
         retVal = true;}
                          if (Syntax.V ==Verbs.EAT)
         {System.out.println("Seriously? You're enough to nauseate a narrator, you know that?");
         retVal = true;}
     return retVal;
     }
 };
 
    static final LatNoun Shield= new LatNoun ("Shield (scutum)", "A highly polished bronze shield. You can see the reflection of the area in it.", Grey, new NounWord("scut",NounEndings.SECONDN)) {
    public void setFlags() {
    setInvisible(true);
    }
     
     @Override
     public boolean handle() {
         boolean retVal = false;
         if (Syntax.V == Verbs.LOOKAT)
         {
         retVal = true;
         System.out.println("You see the reflection of the area in the polished mirror-like bronze shield:");
         Syntax.getCurrentPlayerLoc().look(0);}
     return retVal;
     }
 };
 
 static final LatNoun Rex = new LatNoun("Malus Rex Seriphis.","Malus Rex Seriphis, stans in triclinium gustans pavones.", Party, rexName) {
@Override
         public void setFlags() {
setUntakeable(true);
setCharacter(true);
         }
     
     @Override
     public boolean handle() {
     return false;}
 };
 
  static final LatNoun Clemens = new LatNoun("Clemens","Clemens, your good friend, has brought you some lunch.", Market, clemensName )
 { public boolean handle() {return false;}
@Override
         public void setFlags() {
setUntakeable(true);
setCharacter(true);
         }
 };
  
  static final LatNoun Cena = new LatNoun("Cena.","Cena est bonam.",Clemens, new NounWord("cen",NounEndings.FIRST,false)) {
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
  
    static final LatNoun Cavemouth = new LatNoun("cave (cavum, i).","A cave-mouth (cavum, i) looms on the landscape, providing passage down for anyone climbing down or anything dropped down.",MedusaShore, new NounWord("cav",NounEndings.SECONDN,false)) {
        public void setFlags() {setUntakeable(true);}
        public boolean handle() {
            boolean retVal = false;
            if (Syntax.V == Verbs.PUT || Syntax.V == Verbs.DROP ) {
                retVal = true;
                if (Syntax.DO != null & Syntax.DO != this )
                {if (Player.getWhetherContained(Syntax.DO))
                {System.out.println("The " + " drops down the dark hole of the cave mouth.");
                   if (Medusa.getLocation() == MedusaCave)
                   {Noun.move(Syntax.DO, Medusa);}
                   else
                   {Noun.move(Syntax.DO, MedusaCave);}
                }//contained by player
                else
                    System.out.println("You don't have the " + Syntax.DO + "!");
                }//it exists and is allowed to drop through the hole
                else {
                if (Syntax.DO == null)
                    System.out.println("What do you want to drop into the hole?");
                else if (Syntax.DO == this)
                {System.out.println("You can't drop a cave-mouth!");}
                else System.out.println("You shouldn't be seeing this; something went horribly wrong in Objects.Cavemouth.");}
            }
            
            return retVal;}};
    
      static final LatNoun Medal = new LatNoun("Medal (encolpium, i)","The silver medal reads, \"The Wearer of this Medal\n Has Triumphed Against Medusa.\n Now I Challenge You:\n Do It Again Without Killing Her.\"",Rooms, new NounWord("encolpi",NounEndings.SECONDN,false)) {
  public boolean handle() {return false;}};
 
 static final LatNoun[] mNouns = { Player, Clemens, Cena, Book, Rex, Lobster, Mercury,Amphora,Wine,Cup,Sandals,Helm, Sword, Grey, Eye, Shield, Medusa, Head, Bag,Cavemouth, Medal};
}