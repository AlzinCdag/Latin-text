/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

import java.util.Scanner;
import java.lang.Exception;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author William Krzanowski
 */
public abstract class Noun implements Location{
/** minimum constructor: String[] names, String[] adjectives, Strings ShortDescription, LongDescription, and FirstDescription; the Object in which it is located*/
    Noun(String shortDesc, String longDesc, String firstDesc, Noun location, String ...names) {
    setNames(names);
    setShortDescription(shortDesc);
    setLongDescription(longDesc);
    setFirstDescription(firstDesc);
    if (location != null) {
    location.setContained(this);
    }
    else
        setLocation(location);
    setFlags();
    }
    /** If an object should have flags from the start, override this method in the object
     * declaration and put the setting methods in it. This method is called by the constructor.*/
    public void setFlags() {};
    
    public  abstract boolean handle();
    public Location mLocation;
    public Room mRoom;
    
    public void setLocation(Location location) {
    mLocation = location;
    if(location != null)
    { mRoom = location.getRoom();
    //location.setContained(this);
 
//    System.out.println(this + "'s location has been set to " + mLocation + ", and its Room has been set to " + mRoom);}
    }
    }
  
     @Override
    public Location getLocation() {return mLocation;}
    public void setRoom(Room r) {mRoom = r;}
    
    public String[] mNames;
    public String[] mAdj;
    //public void setNames(String[] names) {mNames = names;}
    public void setNames(String...names) {mNames = names;}
    public void setAdjectives(String... adj) {mAdj = adj;}
   String mShortDescription, mLongDescription, mFirstDescription;
    
   /**the shortest description of the object, used often by the game engine for such phrases as "an [object] is here". */
   public void setShortDescription(String description) {mShortDescription = description;}
    public String getShortDescription() { return mShortDescription;}
   /** A longer description of the object, used when it is examined.*/ 
    public void setLongDescription(String description) {mLongDescription = description;}
    public String getLongDescription() {return mLongDescription;}
    /** If the object has a special state the first time you see it, give it a first description.*/
    public void setFirstDescription(String description) {mFirstDescription = description;}
    public String getFirstDescription() {return mFirstDescription;}
    /** tests whether the input String contains one of the objects names or adjectives,
     * returns true if it does, false otherwise.*/
    public boolean isName(String test) {
 /**  if ((test == null) || (test == "")|| (test == " "))
        { Exception e = new Exception("Empty string passed to Noun.isName");
          throw e;
          }*/
        
       String[] parts = test.split("\\s*");
        boolean containsName = false;
        boolean containsAdj = false;
        //for (String part:parts) {
       // System.out.println("testing the part of the test string: " + part);
        for(String n:mNames)
        {
            if (test.contains(n))
            containsName = true;
        }
        if (mAdj != null) {
            for(String a:mAdj) {
                if (test.contains(a))
                    containsAdj = true;
            }//for 
        }//if mAdj != null
        //}
        String prepPhrase;
        Noun prepLoc;
        //if this is a prepositional phrase, find out the location 
        if (Util.containsPreposition(test) != -1)
        {prepPhrase = test.substring(Util.containsPreposition(test),test.length());
        prepLoc = Objects.parse(prepPhrase);
        }
    if (containsName | containsAdj)
    {return true;}
    else
    {return false;}
    }
    
    //properties like mass, value
    int mass;
    int value;
    /** some containers, including the inventory, have a limit on how much mass they can carry at one time.*/
    public void setMass(int newMass) {mass = newMass;}
    public int getMass() {return mass;}
    
    /** monetary value of object */
    public void setValue(int newValue) {value = newValue;}
    public int getValue() {return value;}
    ArrayList<Noun> mContained = new ArrayList<>();
    /** sets what the object contains*/
     @Override
    public void setContained(Noun...contained) {
        for(Noun n:contained)
        {mContained.add(n);
        n.setLocation(this);
        }
    }
       /** sets what the object contains, and hides it from "look()" by setting the "invisible" flag.*/
    
    public void setContainedInvisible(Noun...contained) {
        for(Noun n:contained)
        {n.setInvisible(true);
            mContained.add(n);
        n.setLocation(this);
        }
    }
    
      /** gets what the object contains*/
   @Override
    public ArrayList<Noun> getContained() {return mContained;}
    
    /** returns true if the passed object is contained by this object.*/
  @Override
    public boolean getWhetherContained(Noun test) {
    boolean returnVal = false;
        if (mContained.isEmpty())
        {returnVal = false;}
        else {
    for(Noun n:mContained) {
    if (n == test)
        returnVal = true;
            }
        }
    return returnVal;
    }
    /** remove this object from its container, put it into the container's location.*/
    public void drop() {
        if (mLocation != null)
        {mLocation.getContained().remove(this);
        mLocation.getRoom().setContained(this);
        }
        
    }
        /** takes this object out of the game's rooms.*/
    public void removeThis() {
        if (mLocation != null)
        {mLocation.getContained().remove(this);
        this.setLocation(null);
        }
        
    }
    /** Remove a specified object from this object's container list.  Returns false if it wasn't on the
     * list to begin with. Sets its location to null. */
     public boolean remove(Noun containedObjectToRemove) {
        boolean retVal = false;
        if (getWhetherContained(containedObjectToRemove))
        {getContained().remove(containedObjectToRemove);
        containedObjectToRemove.setLocation(null);
        containedObjectToRemove.setRoom(null);
        retVal = true;}
        return retVal;
        }
    /** moves an object from its current location to a new one by removing it from its container's 
     * contained list and putting it in the new containers' contained list. First checks whether the 
     * two locations have a lock between them.
     * @param movee the Noun to be moved
     @param newContainer the new location of the Noun */ 
    public static boolean move(Noun movee, Location newContainer)
    { boolean retVal = false;
        if(movee!= null && newContainer != null)
            {if (movee.getLocation() != null)
                {// moving into the lock-handling code. This doesn't run the method if there's a locked lock. 
                if(!movee.getLocation().getLock().isEmpty() || !newContainer.getLock().isEmpty()) // if either container has a lock
                    {
    
                     if (!movee.getLocation().getLock().isEmpty()) // if one container has a lock
                         {
                             for (Lock lo:movee.getLocation().getLock()) { // tests whether a lock prevents the movee from being moved.
                                  if (lo != null) {// if the lock is a lock
                                      if(lo.getLocked(movee.getLocation(), newContainer))//if this lock prevents a move between the locations
                                      {System.out.println(lo.getLockedDesc()); retVal = true;}
                                  else { if ((lo.locked1 == movee) || (lo.locked2 == movee) )System.out.println(lo.getUnlockedDesc());}}
                                 }//for
                        }//if movee's location has a lock
        else if (!newContainer.getLock().isEmpty()) { 
           //System.out.println("starting second lock code with retVal at " + retVal);
           if (newContainer != Objects.Player) { 
           for (Lock lc:newContainer.getLock()) {
            if(lc.getLocked(movee.getLocation(), newContainer))
        { System.out.println(lc.getLockedDesc());  retVal = true;}
            else System.out.println(lc.getUnlockedDesc());
        System.out.println(retVal);    }//for
           }//player
        }//else if
 
    //if the transition between the two is locked
        if(retVal == false)
        {   
            movee.getLocation().getContained().remove(movee);  // These three lines do the work of this code
            newContainer.setContained(movee);
            movee.setSeen(true);
            movee.setLocation(newContainer); retVal = true;
        }
    }// if there's a lock
    else {movee.getLocation().getContained().remove(movee);  // These three lines do the work of this code
        newContainer.setContained(movee);
        movee.setSeen(true);
    movee.setLocation(newContainer); retVal = true; } //if no lock but the movee has an original location
        }//if movee.getLocation != null
    else { // if the movee has no original location, we don't care about the lock, so execute anyway
    newContainer.setContained(movee);
    movee.setSeen(true);
    movee.setLocation(newContainer);
   retVal = true; }
    } //if movee, newContainer != null
    else retVal = false; 
    
    return retVal;}
    
    /** Class room overrides these methods. Returns the location of the object.*/
        public Location getN() {return mLocation;}
    public Location getS() {return mLocation;}
    public Location getE() {return mLocation;}
    public Location getW() {return mLocation;}
    public Location getNE() {return mLocation;}
    public Location getNW() {return mLocation;}
    public Location getSE() {return mLocation;}
    public Location getSW() {return mLocation;}
    public Location getU() {return mLocation;}
    public Location getD() {return mLocation;}
    
    /** If this object is in a container other than a Room,
find the room it is in. Original purpose:  set the Syntax.currentPlayerLoc to the
* Room the player is in without going through getLocation(), which returns a Noun.
(The player could be in a vehicle in the room, like a boat or a ladder)
this method uses a cast. checks three levels.*/
     @Override
    public Room getRoom() {
   
    return mRoom;
    }
    
    //flags; information about an object that can be set like:
    /* is is takeable?
    is it a room? (this one is better in its own class rather than a flag)
    is it a container? (can be open/closed, stuff hidden in it) (better in own class)
        if so, is it transparent? 
    is it a vehicle? 
    has it been seen before?
    is it a character?
    is it locked?
    is it invisible?
    is it a flat surface? {stuff can be put on it}
    can it be lit?
    is it actually lit?
    can it be burned?
    is it a weapon? a tool? etc.
    bits involving its grammar, like gender, declension
    */
    boolean untakeable;
    public void setUntakeable(boolean newValue) {untakeable = newValue;}
    public boolean getUntakeable() {return untakeable;}
    
    boolean character;
    public void setCharacter(boolean newValue) {character = newValue;}
    public boolean getCharacter() {return character;}
    
        boolean seenAtLeastOnce;
    public void setSeen(boolean newValue) {seenAtLeastOnce = newValue;}
    public boolean getSeen() {return seenAtLeastOnce;}
    
        boolean invisible = false;
    public void setInvisible(boolean newValue) {invisible = newValue;}
   @Override
    public boolean getInvisible() {return invisible;}
    
            boolean gameinvisible = false;
    public void setGameInvisible(boolean newValue) {gameinvisible = newValue;}
  
    public boolean getGameInvisible() {return gameinvisible;}
    
            boolean surface;
    public void setSurface(boolean newValue) {surface = newValue;}
    public boolean getSurface() {return surface;}
    
            boolean lightable;
    public void setLightable(boolean newValue) {lightable = newValue;}
    public boolean getLightable() {return lightable;}
    
                boolean weapon;
    public void setWeapon(boolean newValue) {weapon = newValue;}
    public boolean getWeapon() {return weapon;}
    
                boolean tool;
    public void setTool(boolean newValue) {tool = newValue;}
    public boolean getTool() {return tool;}
                
                boolean wearable;
        public void setWearable(boolean newValue) {wearable = newValue;}
    public boolean getWearable() {return wearable;}
    
    
    boolean activated;
    Runnable activate, deactivate;
   /** if there is an activation state involved (e.g. putting on the helm of darkness vs. just having it) put this in the setFlags method. 
    * This causes things to happen when the object is activated or deactivated. */
    public void setActivationProcesses(Runnable runWhenActivating, Runnable runWhenDeactivating) {deactivate = runWhenDeactivating; activate = runWhenActivating;}
    public void setActivated(boolean newValue, Runnable runWhenChanging) {activated = newValue; runWhenChanging.run(); }
    public void setActivated(boolean newValue) {
    activated = newValue;
    if (activated) 
        activate.run();
    else
        deactivate.run();
    }
  
    public boolean getActivated() {return activated;}           
    
    ArrayList<Lock> mLocks = new ArrayList<>();
    
    public void setLock(Noun lockKey, String lockedDescription, String unlockedDescription, String unLockingDescription, String lockingDescription, boolean lockedAtFirst) {
     mLocks.add(new Lock(this, lockKey, lockedDescription, unlockedDescription, unLockingDescription, lockingDescription, lockedAtFirst));}
    
     public void setLock(Noun otherRoom, Noun lockKey, String lockedDescription, String unlockedDescription, String unLockingDescription, String lockingDescription, boolean lockedAtFirst) {
     mLocks.add(new Lock(this, otherRoom, lockKey, lockedDescription, unlockedDescription, unLockingDescription, lockingDescription, lockedAtFirst));}
    
    public void setLock(Lock lock) {mLocks.add(lock);}
     @Override
    public ArrayList<Lock> getLock() {return mLocks;}
    
    /** Gets an object's description. Then, get the description of what it contains.
     @param level: the level in the containment hierarchy the object is, used for indentation purposes. The room(outermost container) should be level zero. the player is -1.*/
    public void look(int level) {
     // System.out.println(level +" is the level.");
        String tabs = " ";
     if (level != -1)
     {
   //    if (level != 0) {
        List<String> tabc = new ArrayList<>();
       for (int i = 0; i<=level; i++) {
        tabc.add("  ");}
        tabs = String.join("",tabc);
        if (level == 1 && getSeen() && (!getInvisible() && (this != Objects.Player)))
            System.out.println("    hic est " + getLongDescription()); 
        else if (getSeen() && (!getInvisible() && (this != Objects.Player)))
             System.out.println( getLongDescription());
                else if (!getSeen() && (!getInvisible() && (this != Objects.Player)))
             System.out.println( getFirstDescription());
                    
        
     //  } 
    }
    
     
   if (!mContained.isEmpty() && !(mContained.contains(Objects.Player))) {
      if(level != -1)
       System.out.println(tabs + "The " + getShortDescription() + " contains:");
       else
      {System.out.println("Habes:");}
       for (Noun n : mContained)
    { if (!n.getInvisible() && (n != Objects.Player))
        n.look(level + 1);
    }
 }
   else if (level == -1)
   {System.out.println("aliquid non habes.");}

   
    }
    /** to be overriden by the Object Objects.Player*/
    public void makeLocationGlobal() {};
    
    @Override
         public String toString()
    {return mNames[0];}
}

