/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

import java.util.List;
import java.util.ArrayList;

/** Located in Noun Rooms. 
 * Should have rooms stored as being North, south, east, west, NE, NW,SE,SW, Up, Down. 
 *
 * @author William Krzanowski
 */
public  class Room extends Noun implements Location{
    
    Room N, S, E, W, NE, SE, NW, SW, U, D;
    @Override
    public Room getN() {return N;}
    @Override
    public Room getS() {return S;}
    @Override
    public Room getE() {return E;}
    @Override
    public Room getW() {return W;}
    @Override
    public Room getNE() {return NE;}
    @Override
    public Room getNW() {return NW;}
    @Override
    public Room getSE() {return SE;}
    @Override
    public Room getSW() {return SW;}
    @Override
    public Room getU() {return U;}
    @Override
    public Room getD() {return D;}
    
        public void setN(Room newVal) {N= newVal;}
    public void setS(Room newVal) {
       // System.out.println("S: " +S + "newVal: " + newVal);
        S= newVal;
   // System.out.println("S: " +S);
    }
    public void setE(Room newVal) {E= newVal;}
    public void setW(Room newVal) {W = newVal;}
    public void setNE(Room newVal) {NE= newVal;}
    public void setNW(Room newVal) { NW= newVal;}
    public void setSE(Room newVal) { SE= newVal;}
    public void setSW(Room newVal) {W= newVal;}
    public void setU(Room newVal) {U = newVal;}
    public void setD(Room newVal) {D= newVal;}
    
    final boolean onlyAirAccessible;
    public boolean onlyFlyable() {return onlyAirAccessible;}
    
    public Room(String name, String description, boolean onlyAccessibleViaAir ) {
    super(name, description, description, Objects.Rooms, name);
 //   Objects.Rooms.setContained(this);
 onlyAirAccessible = onlyAccessibleViaAir;
            }
   
   public Room getRoom() {return this;}
//   public Noun getNounLocation() {return Objects.Rooms;}
   
    /** sets what the object contains*/
    public void setContained(Noun...contained) {
        for(Noun n:contained)
        {mContained.add(n);
        n.setLocation(this);
        }
    }
    
      /** Gets an object's description. Then, get the description of what it contains.
     @param level: the level in the containment hierarchy the object is, used for indentation purposes. The room(outermost container) should be level zero.*/
 @Override
    public void look(int level) {
       String tabs = " ";
       System.out.println(mShortDescription);
       System.out.println( tabs + getLongDescription());
     
   if (!mContained.isEmpty()) {
       for (Noun n : mContained)
    { if (!n.getInvisible() && (n != Objects.Player))
        n.look(level + 1);
    }
       System.out.println(); }
    }
    @Override
     public void setLock(Lock lock) {super.setLock(lock);}
    @Override
    public ArrayList<Lock> getLock() {return super.getLock();}
    

    @Override
    public boolean handle() {return false;}
    //conditional exits
    //descriptions for blocked exits
  
@Override
public String toString() {return super.getShortDescription();}
    
}
/** if the exit of a room is conditional(e.g. needs a key) or blocked, use this class*/
class Lock {
Location locked1, locked2, key;
String lockedDesc, unlockedDesc, unlockingDesc, lockingDesc;
boolean locked;
        
public Lock(Location containerToBeLocked, Location lockKey, String lockedDescription, String unlockedDescription, String unLockingDescription, String lockingDescription, boolean lockedAtFirst) {
locked1 = containerToBeLocked;
key = lockKey;
lockedDesc=lockedDescription;
unlockedDesc= unlockedDescription;
unlockingDesc = unLockingDescription;
lockingDesc = lockingDescription;
locked = lockedAtFirst;
}

public Lock(Location lockedRoom1, Location lockedRoom2, Location lockKey, String lockedDescription, String unlockedDescription, String unLockingDescription, String lockingDescription, boolean lockedAtFirst) {

 this(lockedRoom1,lockKey, lockedDescription, unlockedDescription,unLockingDescription, lockingDescription, lockedAtFirst);
locked2 = lockedRoom2;

}

/** set the lock boolean if the player has the key. print the appropriate description. */
public boolean lock(boolean on) {
    boolean retval = false;
if (key.getLocation() == Objects.Player)
{if (on == locked)
{   System.out.println("id est.");}
else
{ locked = on;
if (on == true)
    System.out.println(lockingDesc);
else if (on == false)
   System.out.println(unlockingDesc); }
retval = true;
}
else System.out.println("Non " + key.toString() + " habes.");

return retval;
}

public boolean getLocked() {return locked;}
public Location getSecondRoom() {return locked2;}
public String getLockedDesc() {return lockedDesc;}
public String getUnlockedDesc() {return unlockedDesc;}
public String getLockingDesc() {return lockingDesc;}
public String getUnlockingDesc() {return unlockingDesc;}

/** Is the passage between Noun1 and Noun2 locked? (ex. container, currentLocation)*/
public boolean getLocked(Location Noun1, Location Noun2) {
//set both of the internally held locked objects
    Location l2;
boolean retVal = false;
    if (locked2 == null)
    l2 = Syntax.getCurrentPlayerLoc();
    else {l2 = locked2;}
    //check if the 
    if (((Noun1 == locked1) && (Noun2 == l2)) || ((Noun2 == locked1) && (Noun1 == l2)))
        {if (locked)
            retVal = true;}
    else retVal = false;  
    return retVal;
}

}