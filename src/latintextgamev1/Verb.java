/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

/**
 *The representation of a verb's meaning in code.
 * @author William Krzanowski
 */
public abstract class Verb {
    public MoodVoiceTenseWrapper mv;
    public void setMoodVoiceTenseWrapper(MoodVoiceTenseWrapper m) {mv = m;}
    public MoodVoiceTenseWrapper getMoodVoiceTenseWrapper(){return mv;}
    public abstract boolean handle() ;
    public boolean dative;
    public boolean ablative;
    //public void setGoesWithDative(boolean dat) {dative = dat;}
    public boolean getGoesWithDative() {return dative;}
    public boolean getGoesWithAblative() {return ablative;}
    public Verb(boolean dat, boolean abl) {
        dative = dat;
        ablative = abl;
    } 
}

class Verbs {
static Verb LOOK = new Verb(false, false) {
public boolean handle() {
Syntax.getCurrentPlayerLoc().look(0);
    return true;}
}    ;
    
static Verb LOOKAT = new Verb(false, false) {
    @Override
public boolean handle() {
 
if (Syntax.DO == Objects.Player)
{Objects.Player.look(-1);}
else {
    try{

    if ((Syntax.DO != null) && (Syntax.DO != Objects.Nothing)){
    if ((Syntax.DO.getRoom() == Syntax.getCurrentPlayerLoc().getRoom()) && (Syntax.DO.getInvisible() == false))
    {   System.out.println(Syntax.DO.getLongDescription());
        Syntax.DO.look(0);
    }
    else  {System.out.println("Non vides " + Syntax.DO + "hic!");}
    
    }
    else System.out.println("There was no direct object in that sentence.");
}
catch (Exception E)
{System.out.println("Non vides " + Syntax.DO + "hic! (exception:)");
E.printStackTrace();}
}
    return true;}
}    ;

static Verb ATTACK = new Verb(false,true) {
    @Override
    public boolean handle() {
        if (Syntax.DO != null && Syntax.IO != null)
        {System.out.println("You hit the " + Syntax.DO + " with the " + Syntax.IO + "." );
    return true;}
        else {return false;}}
};

public static Verb TAKE = new Verb(true,true) 
{@Override
    public boolean handle() {

        boolean retVal = false;
try{
    if(Syntax.DO !=  null) {
    if (((Syntax.DO.getRoom() == Syntax.getCurrentPlayerLoc())) && (Syntax.DO.getInvisible() == false))
    {if(!Syntax.DO.getUntakeable())
    {
        if (Objects.Winner.getWhetherContained(Syntax.DO))
        {   System.out.println("You already have the "+ Syntax.DO + "!");}
        else
        {  Noun.move(Syntax.DO, Objects.Winner);
        System.out.println("taken.");
        }
    }//takeable
    else
    {System.out.println("How can you possibly pick up " + Syntax.DO + "?");}
    retVal = true;
    }//in the same room, visible  
    }// if DO != null
}//try
catch (Exception E)
{System.out.println("Non vides " + Syntax.DO + "hic!");}

return retVal;}
        };


public static Verb GIVE = new Verb(true,false) 
{@Override
    public boolean handle() {
    boolean retVal = false;
if (Syntax.IO != null) {
    if (Syntax.DO != null)
    {
    if ((Syntax.IO.getRoom() 
            == Syntax.getCurrentPlayerLoc())
            && (Syntax.IO.getInvisible() == false))
    {
        if (Objects.Winner.getWhetherContained(Syntax.DO)) {
     Noun.move(Syntax.DO, Syntax.IO);
            if (Objects.Winner == Objects.Player)
            System.out.println("You give the " + Syntax.DO + " to " + Syntax.IO + ".");
            else
                System.out.println(Objects.Winner + " gives the " + Syntax.DO + " to " + Syntax.IO + ".");
        }//DO contained
        else
            System.out.println("non habes " + Syntax.DO +"!");
    }//IO visible
    else
        System.out.println("non vides " + Syntax.IO + " hic!");
    }
    else System.out.println("What do you want to give?");
    }
else System.out.println("Who do you want to give it to?");

return true;}
};

public static Verb UNLOCK = new Verb(false,true) {
public boolean handle() {
   boolean retval = false;
if (Syntax.DO.getLock() != null)
{ for(Lock lo: Syntax.DO.getLock()){
    lo.lock(false);}
retval = true;
}       
       return retval;}
};

public static Verb LOCK = new Verb(false,true) {
public boolean handle() {
  boolean retval = false;
if (Syntax.DO.getLock() != null)
{ for(Lock lo: Syntax.DO.getLock()){
    lo.lock(true);}
retval = true;
}       
       return retval;
}};

public static Verb INVENTORY = new Verb(false,false) {
public boolean handle() {
Objects.Player.look(-1);
    return true;
}
};

public static Verb DROP = new Verb(false,false) {
public boolean handle() {
   
if (Objects.Player.getWhetherContained( Syntax.DO))
{ Syntax.DO.drop();
System.out.println("Dropped.");
}
else System.out.println("non " + Syntax.DO + " habes!");
    return true;
}
};

public static Verb EAT = new Verb(false,true) {
public boolean handle() {
   
    if (Syntax.DO == Objects.Cena) {
        if (Objects.Player.getWhetherContained(Syntax.DO))
    {Objects.Cena.removeThis();
     System.out.println("You eat the dinner. Satisfied, you think about heading up to the library to read (lego, legere) a book (liber, libris).");}
        else {System.out.println("You don't have the dinner, so how can you eat it?");}
} else
   if ( (Syntax.DO == Objects.Lobster))
   { if (Objects.Player.getWhetherContained(Syntax.DO))
   {System.out.println("Due to having spent a long time as a baby cramped up in a smelly, leaky box tossed about the high seas, you really dislike seafood.");}
   else {System.out.println("You don't have the lobster, so how can you eat it?");}
   }
   else {System.out.println("Have you gone mad? You can't eat the " + Syntax.DO + "!");}
   
   return true;
    
}
    
};

public static Verb DRINK = new Verb(false,false) {
    public boolean handle() {
        if (Objects.Winner == Objects.Player)
        {if (Util.doAndHas(Objects.Amphora))
            System.out.println("You pass out from the wine and wake up several hours later.");
        else if((Syntax.DO == Objects.Wine || Syntax.DO == Objects.Cup) && Objects.Player.getWhetherContained(Objects.Cup))
        {if (Objects.Cup.getWhetherContained(Objects.Wine))
                {System.out.println("As you drink the wine from the cup, you cannot help but feel this is potent stuff.\n You swoon and fall and pass out and wake up several hours later with a bizarre hangover. ");
              Noun.move(Objects.Wine, Objects.Amphora); Objects.Wine.setInvisible(true);}
        else {System.out.println("As you put your lips to the cup, you see too late that there is no wine in it."
                + " \n To cover your mistake, you make a series of slurping noises, pretend to drain the whole cup in one swig, and sigh, satisfied as you put it back down.");}
                }
            }
        else if (Objects.Winner == Objects.Mercury)
                    {if (Util.doAndHas(Objects.Amphora))
            System.out.println("Mercury looks disgusted. \"Straight from the amphora?!\" he says. ");
        else if((Syntax.DO == Objects.Wine || Syntax.DO == Objects.Cup) && Objects.Mercury.getWhetherContained(Objects.Cup))
        {if (Objects.Cup.getWhetherContained(Objects.Wine))
                {System.out.println("Mercury drinks. \"Ah. That was great stuff.\" he says.");
              Noun.move(Objects.Wine, Objects.Amphora); Objects.Wine.setInvisible(true);
                Events.mercdrinks = true;}
        else {System.out.println("Mercury starts drinking. His eyes widen, and he looks embarassed.\n He makes a series of slurping noises, pretends to drain the cup in one swig,"
                + "\n and sighs, satisfied, as he puts it back down.\n Finally, he admits to you, \"You didn't pour me any wine.\".");}
                }
        else if ((!Objects.Mercury.getWhetherContained(Objects.Cup)) && (Syntax.DO == Objects.Wine || Syntax.DO == Objects.Cup))
        {System.out.println("\"First give me the cup.\"");}
            }
        
    return true;}
};
public static Verb POUR= new Verb(false,false) {
    public boolean handle() {
        if ((Syntax.DO == Objects.Amphora) && (Objects.Winner.getWhetherContained(Objects.Amphora)) )
        { if ((Syntax.IO == Objects.Cup) && (Objects.Winner.getWhetherContained(Objects.Cup)))
            {System.out.println("You pour the wine from the amphora into the cup.");
            Noun.move(Objects.Wine, Objects.Cup);
            Objects.Wine.setInvisible(false);
            }
        else {System.out.println("Into what?");}
        }
        else if ((Syntax.DO == Objects.Wine) && (Objects.Winner.getWhetherContained(Objects.Amphora)))
                 { if ((Syntax.IO == Objects.Cup) && (Objects.Winner.getWhetherContained(Objects.Cup)))
            {System.out.println("You pour the wine from the amphora into the cup.");
            Noun.move(Objects.Wine, Objects.Cup);
            Objects.Wine.setInvisible(false);
            }
        else {System.out.println("Into what?");}
        }
    return true;}
};
public static Verb EQUIP = new Verb(false,false) {
@Override 
public boolean handle() {
boolean retVal = false;
if (Syntax.DO != null & Objects.Winner.getWhetherContained(Syntax.DO))
{
    //first deal with case of no io
        if (Syntax.DO.getWearable())
        {  Syntax.DO.setActivated(true);
       retVal = true;
        }
        else {System.out.println("Where do you want to put the "+Syntax.DO + "?");
        retVal = true;
    }

}
else {
    if (Syntax.DO != null)
    System.out.println("You don't have the " + Syntax.DO + "!");
    else
        System.out.println("What do you want to put?");
retVal = true;
}
return retVal;
}
};

public static Verb N = new Verb(false,false) {
public boolean handle() {
   boolean retVal = false;
    if(Objects.Player.getRoom().getN() != null) {
        if (!Objects.Player.getRoom().getN().onlyFlyable() || (Objects.Player.getRoom().getN().onlyFlyable() & Objects.Sandals.getActivated()) )
  
  retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getN());
        
        else {System.out.println("Noone without the ability to fly could go that way.");
        retVal = false;}
        
        if (retVal)
            Objects.Player.getRoom().look(0); 
    }else {System.out.println("There's no passage that way");} return true;
    }};

public static Verb S = new Verb(false,false) { 
public boolean handle() { 
   boolean retVal = false;
    if(Objects.Player.getRoom().getS() != null) {
      if (!Objects.Player.getRoom().getS().onlyFlyable() || (Objects.Player.getRoom().getS().onlyFlyable() & Objects.Sandals.getActivated()) )
        retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getS());
       else {System.out.println("Noone without the ability to fly could go that way.");        retVal = false;}
    if (retVal) Objects.Player.getRoom().look(0);
     } else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb E = new Verb(false,false) {
public boolean handle() { 
   boolean retVal = false;
     if(Objects.Player.getRoom().getE() != null) {
    
  if (!Objects.Player.getRoom().getE().onlyFlyable() || (Objects.Player.getRoom().getE().onlyFlyable() & Objects.Sandals.getActivated()) )
 retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getE());
   else {System.out.println("Noone without the ability to fly could go that way.");       retVal = false;}
 if (retVal) Objects.Player.getRoom().look(0);
     }else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb W = new Verb(false,false) {
public boolean handle() {
    boolean retVal = false;
    if(Objects.Player.getRoom().getW() != null) {
    if (!Objects.Player.getRoom().getW().onlyFlyable() || (Objects.Player.getRoom().getW().onlyFlyable() & Objects.Sandals.getActivated()) )
        retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getW());
     else {System.out.println("Noone without the ability to fly could go that way.");       retVal = false;}
      if (retVal) Objects.Player.getRoom().look(0);
    } else {System.out.println("There's no passage that way");} return true;
}};

public static Verb NE = new Verb(false,false) {
public boolean handle() {
    boolean retVal = false;
     if(Objects.Player.getRoom().getNE() != null) {
  if (!Objects.Player.getRoom().getNE().onlyFlyable() || (Objects.Player.getRoom().getNE().onlyFlyable() & Objects.Sandals.getActivated()) )
retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getNE());
   else {System.out.println("Noone without the ability to fly could go that way.");        retVal = false;}
 if (retVal) Objects.Player.getRoom().look(0);
     } else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb NW = new Verb(false,false) {
public boolean handle() { 
  boolean retVal = false;
    if(Objects.Player.getRoom().getNW() != null) {
  if (!Objects.Player.getRoom().getNW().onlyFlyable() || (Objects.Player.getRoom().getNW().onlyFlyable() & Objects.Sandals.getActivated()) )  
    retVal =Noun.move(Objects.Player, Objects.Player.getRoom().getNW());
   else {System.out.println("Noone without the ability to fly could go that way.");        retVal = false;}
 if (retVal) Objects.Player.getRoom().look(0);
     } else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb SE = new Verb(false,false) {
public boolean handle() {
    boolean retVal = false;
 if(Objects.Player.getRoom().getSE() != null) {    
  if (!Objects.Player.getRoom().getSE().onlyFlyable() || (Objects.Player.getRoom().getSE().onlyFlyable() & Objects.Sandals.getActivated()) )
retVal =Noun.move(Objects.Player, Objects.Player.getRoom().getSE());
  else { System.out.println("Noone without the ability to fly could go that way.");       retVal = false;}
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;
 }};

public static Verb SW = new Verb(false,false) {
public boolean handle() {
    boolean retVal = true;
 if(Objects.Player.getRoom().getSW() != null) {
  if (!Objects.Player.getRoom().getSW().onlyFlyable() || (Objects.Player.getRoom().getSW().onlyFlyable() & Objects.Sandals.getActivated()) )
retVal= Noun.move(Objects.Player, Objects.Player.getRoom().getSW());
   else {System.out.println("Noone without the ability to fly could go that way.");       retVal = false;}
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;}};

public static Verb U = new Verb(false,false) {
public boolean handle() {
 boolean retVal = false;
    if(Objects.Player.getRoom().getU() != null) {
   
  if (!Objects.Player.getRoom().getU().onlyFlyable() || (Objects.Player.getRoom().getU().onlyFlyable() & Objects.Sandals.getActivated()) )
retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getU());
   else {System.out.println("Noone without the ability to fly could go that way.");       retVal = false;}
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;
 }};

public static Verb D = new Verb(false,false) {
public boolean handle() {
 boolean retVal = false;
    if(Objects.Player.getRoom().getD() != null) {    
      if (!Objects.Player.getRoom().getD().onlyFlyable() || (Objects.Player.getRoom().getD().onlyFlyable() & Objects.Sandals.getActivated()) )
retVal =Noun.move(Objects.Player, Objects.Player.getRoom().getD());
       else {System.out.println("Noone without the ability to fly could go that way.");        retVal = false;}
 if (retVal) { 
     if (Objects.Player.getRoom() !=Objects.MedusaCave)
     {  Objects.Player.getRoom().look(0);}
     else {System.out.println("Knowing one glance at Medusa will turn you to stone, you close your eyes.\n The only way you're going to look at anything here is by looking specificaly at it with the verb \"video\".");}
 }
 } else {System.out.println("There's no passage that way");} return true;
 }};

public static Verb LOVE = new Verb(false,false) {
public boolean handle() {
 return true;}};

public static Verb READ = new Verb(false,false) {
    @Override
    public boolean handle() {
 return false;}};

public static Verb TALKTO = new Verb(false,false) {
@Override
         public boolean handle() {
             
return true;
}
};

public static Verb CORRECTMERC = new Verb(false,false) {
@Override
         public boolean handle() {
          System.out.println("To talk to Mercury, address him by the vocative form of his name. Since it usually ends in a -us, it will be Mercurie.");
return true;
}
};

public static Verb PUT = new Verb(false,true) {
@Override 
public boolean handle() {
boolean retVal = false;
if (Syntax.DO != null & Objects.Winner.getWhetherContained(Syntax.DO))
{
    //first deal with case of no io
    if (Syntax.IO == null)
    {
        if (Syntax.DO.getWearable())
        {  Syntax.DO.setActivated(true);
       retVal = true;
        }
        else {System.out.println("Where do you want to put the "+Syntax.DO + "?");
        retVal = true;
    }}
        else {
       if (Objects.Winner.getRoom().getWhetherContained(Syntax.IO) && !Syntax.IO.getInvisible())
       {Noun.move(Syntax.DO, Syntax.IO);
       if (Objects.Winner == Objects.Player)
           System.out.println("You put the " + Syntax.DO + " in the " + Syntax.IO +".");
       else System.out.println(Objects.Winner + " puts the " + Syntax.DO + " in the " + Syntax.IO + ".");
       
       retVal = true;
       }
       else {System.out.println("The " + Syntax.IO + " cannot have anything placed in it because it cannot be seen here.");}
    retVal = true;
    }
}
else {
    if (Syntax.DO != null)
    System.out.println("You don't have the " + Syntax.DO + "!");
    else
        System.out.println("What do you want to put?");
retVal = true;
}
return retVal;
}
};
public static Verb KILL = new Verb(true,false) {
@Override
         public boolean handle() {
         if (Syntax.DO != null) {
             if (Syntax.IO == Objects.Sword)
          {
              if (Syntax.DO.getCharacter()) {
          if (Syntax.DO ==Objects.Clemens)
          {System.out.println("Come on, now, what has Clemens ever done to you?");}
          else if (Syntax.DO == Objects.Rex)
          {System.out.println("The king's bodyguards stop you and drag you away and behead you.");
          Util.jigsUp();}
          else if (Syntax.DO == Objects.Mercury) {
          System.out.println("As the blade swishes towards him, Mercurius instantly moves five cubits to the left. \n \"You're going to find it harder than that to kill a god,\" he remarks.");
          }
                    else if (Syntax.DO == Objects.Grey) {
          System.out.println("The blade slices through the grey ladies, but it has no effect on them. \n \"You're going to find it harder than that to kill sea-foam godesses,\" they yell."
                  + " \nThen, they tear you to bits and divide your eyes among them. ");
        Util.jigsUp();
                    }
                    else if (Syntax.DO == Objects.Medusa)
                    {   
                        if (Objects.Player.getWhetherContained(Objects.Shield) && Objects.Player.getLocation() == Objects.MedusaCave) {
                            if (Objects.Player.getWhetherContained(Objects.Sword)) {
                            System.out.println("With a sharp, swift stroke, you cleave Medusa's head from her body, which dissolves.\n Immediately you pick up the head and stuff it in your bag. ");
         Noun.move(Objects.Head, Objects.Bag);
              Noun.move(Objects.Medusa, Objects.Rooms);
                            }
                            else {System.out.println("With a sharp, swift, stroke, you cleave Medusa's head from her body.\nOr at least you start doing it before you realise you have no sword. "
                                    + "\nTo cover your mistake, you pretend you're starting a new dance craze, and \ncontinue rhythmically moving to the beat of \'The Sea Claims All\',"
                                    + " \ninventing a few new steps and arm movements previously unknown to the Bronze Age,\n Before running out of momentum. You wipe sweat from your brow and sigh."
                                    + " \nIf you ever get back to that party, you can show them a thing or two!");}
                    }
                    else {System.out.println("How can you kill her when you can't see her?");}
                    }
              }
              else {System.out.println("How can you possibly kill a " + Syntax.DO + "?" );}
              
          }
          else {System.out.println("With (cum) what?");}
         
         }
return true;
}
};
}