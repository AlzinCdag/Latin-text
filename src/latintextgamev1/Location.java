/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

import java.util.ArrayList;

/** Created so that Noun and Room can extend it so that the container system works.
 *
 * 
 */
public interface Location {

public Room getRoom();


public ArrayList<Noun> getContained();
public void setContained(Noun...contained);
public boolean getWhetherContained(Noun test);
public void look(int level);
public boolean getInvisible();
public ArrayList<Lock> getLock();
public Location getLocation();
}