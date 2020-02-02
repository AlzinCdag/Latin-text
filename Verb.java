/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textgameprototype;

/**
 *
 * @author Administrator
 */
public abstract class Verb {
    public MoodVoiceTenseWrapper mv;
    public void setMoodVoiceTenseWrapper(MoodVoiceTenseWrapper m) {mv = m;}
    public MoodVoiceTenseWrapper getMoodVoiceTenseWrapper(){return mv;}
    public abstract boolean handle() ;
}

class Verbs {
static Verb LOOK = new Verb() {
public boolean handle() {
Syntax.getCurrentPlayerLoc().look(0);
    return true;}
}    ;
    
static Verb LOOKAT = new Verb() {
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

static Verb ATTACK = new Verb() {
    @Override
    public boolean handle() {
        if (Syntax.DO != null && Syntax.IO != null)
        {System.out.println("You hit the " + Syntax.DO + " with the " + Syntax.IO + "." );
    return true;}
        else {return false;}}
};

public static Verb TAKE = new Verb() 
{@Override
    public boolean handle() {
    boolean retVal = false;
try{
    if(Syntax.DO !=  null) {
    if (((Syntax.DO.getRoom() == Syntax.getCurrentPlayerLoc())) && (Syntax.DO.getInvisible() == false))
    {if(!Syntax.DO.getUntakeable())
    {
        Noun.move(Syntax.DO, Objects.Player);
    System.out.println("taken.");
    }//takeable
    else
    {System.out.println("You try with all your might to pick up the " + Syntax.DO + ", but it does not seem to want to leave its spot.");}
    retVal = true;
    }//in the same room, visible  
    }// if DO != null
}//try
catch (Exception E)
{System.out.println("Non vides " + Syntax.DO + "hic!");}

return retVal;}
        };


public static Verb GIVE = new Verb() 
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
        if (Objects.Player.getWhetherContained(Syntax.DO)) {
     Noun.move(Syntax.DO, Syntax.IO);
    System.out.println("You give the " + Syntax.DO + " to " + Syntax.IO + ".");
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

public static Verb UNLOCK = new Verb() {
public boolean handle() {
   boolean retval = false;
if (Syntax.DO.getLock() != null)
{ for(Lock lo: Syntax.DO.getLock()){
    lo.lock(false);}
retval = true;
}       
       return retval;}
};

public static Verb LOCK = new Verb() {
public boolean handle() {
  boolean retval = false;
if (Syntax.DO.getLock() != null)
{ for(Lock lo: Syntax.DO.getLock()){
    lo.lock(true);}
retval = true;
}       
       return retval;
}};

public static Verb INVENTORY = new Verb() {
public boolean handle() {
Objects.Player.look(-1);
    return true;
}
};

public static Verb DROP = new Verb() {
public boolean handle() {
   
if (Objects.Player.getWhetherContained( Syntax.DO))
{ Syntax.DO.drop();
System.out.println("Dropped.");
}
else System.out.println("non " + Syntax.DO + " habes!");
    return true;
}
};

public static Verb N = new Verb() {
public boolean handle() {
   boolean retVal = false;
    if(Objects.Player.getRoom().getN() != null) {
  
  retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getN());
        if (retVal)
            Objects.Player.getRoom().look(0); 
    }else {System.out.println("There's no passage that way");} return true;
    }};

public static Verb S = new Verb() { 
public boolean handle() { 
   boolean retVal = false;
    if(Objects.Player.getRoom().getS() != null) {
    retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getS());
    if (retVal) Objects.Player.getRoom().look(0);
     } else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb E = new Verb() {
public boolean handle() { 
   boolean retVal = false;
     if(Objects.Player.getRoom().getE() != null) {
    

 retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getE());
 if (retVal) Objects.Player.getRoom().look(0);
     }else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb W = new Verb() {
public boolean handle() {
    boolean retVal = false;
    if(Objects.Player.getRoom().getW() != null) {
     Noun.move(Objects.Player, Objects.Player.getRoom().getW());
      if (retVal) Objects.Player.getRoom().look(0);
    } else {System.out.println("There's no passage that way");} return true;
}};

public static Verb NE = new Verb() {
public boolean handle() {
    boolean retVal = false;
     if(Objects.Player.getRoom().getNE() != null) {

retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getNE());
 if (retVal) Objects.Player.getRoom().look(0);
     } else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb NW = new Verb() {
public boolean handle() { 
  boolean retVal = false;
    if(Objects.Player.getRoom().getNW() != null) {
  
retVal =Noun.move(Objects.Player, Objects.Player.getRoom().getNW());
 if (retVal) Objects.Player.getRoom().look(0);
     } else {System.out.println("There's no passage that way");} return true;
     }};

public static Verb SE = new Verb() {
public boolean handle() {
    boolean retVal = false;
 if(Objects.Player.getRoom().getSE() != null) {    

retVal =Noun.move(Objects.Player, Objects.Player.getRoom().getSE());
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;
 }};

public static Verb SW = new Verb() {
public boolean handle() {
    boolean retVal = true;
 if(Objects.Player.getRoom().getSW() != null) {

retVal= Noun.move(Objects.Player, Objects.Player.getRoom().getSW());
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;}};

public static Verb U = new Verb() {
public boolean handle() {
 boolean retVal = false;
    if(Objects.Player.getRoom().getU() != null) {
   

retVal = Noun.move(Objects.Player, Objects.Player.getRoom().getU());
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;
 }};

public static Verb D = new Verb() {
public boolean handle() {
 boolean retVal = false;
    if(Objects.Player.getRoom().getD() != null) {    
    
retVal =Noun.move(Objects.Player, Objects.Player.getRoom().getD());
 if (retVal) Objects.Player.getRoom().look(0);
 } else {System.out.println("There's no passage that way");} return true;
 }};

public static Verb LOVE = new Verb() {
public boolean handle() {
 return true;}};

public static Verb READ = new Verb() {
    @Override
    public boolean handle() {
 return false;}};
}