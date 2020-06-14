/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

/**
 *
 * @author Administrator
 */
public abstract class LatNoun extends Noun implements Location {
 NounWord[] words;
    /**
     @param longDesc the long description of the noun.
     * @param firstDesc the description of the object in its primary state
     * @param location what noun currently holds this latin noun- like a room or a container
     * @param word one or a series of words that can refer to this noun.
     */
    public LatNoun(String longDesc, String firstDesc, Noun location, NounWord... word) {
    super(word[0].getDeclined()[0][1], longDesc, firstDesc, location, word[0].getDeclined()[0][1]);
   words = word;
    }
    
    /** Gets whether the input is a declined version of one of this Noun's names, returns the case it could be or null.*/
    public String isNameWithCase(String s) {
     String retVal = null;
   for (NounWord n:words)
   {if (( n.getCase(s)) != null)
       retVal = n.getCase(s);
       }
     return retVal;
    }
    

}

enum NounEndings {
FIRST(false, new String[][] {{"NS","a"},{"GS","ae"},{"DS","ae"},{"AS","am"},{"OS","a"},{"NP","ae"},{"GP","arum"},{"DP","is"},{"AP","as"},{"OP","is"}}),
SECONDM(false, new String[][] {{"NS","us"},{"GS","i"},{"DS","o"},{"AS","um"},{"OS","o"},{"NP","i"},{"GP","orum"},{"DP","is"},{"AP","os"},{"OP","is"}}),
SECONDN(false, new String[][] {{"NS","um"},{"GS","i"},{"DS","o"},{"AS","um"},{"OS","o"},{"NP","a"},{"GP","orum"},{"DP","is"},{"AP","a"},{"OP","is"}}),
THIRDMF(true, new String[][] {{"NS","is"},{"GS","is"},{"DS","i"},{"AS","em"},{"OS","e"},{"NP","es"},{"GP","um"},{"DP","ibus"},{"AP","es"},{"OP","ibus"}}),
THIRDN(true, new String[][] {{"NS","is"},{"GS","is"},{"DS","i"},{"AS","is"},{"OS","e"},{"NP","es"},{"GP","um"},{"DP","ibus"},{"AP","es"},{"OP","ibus"}}),
FOURTHM(false, new String[][] {{"NS","us"},{"GS","us"},{"DS","ui"},{"AS","um"},{"OS","u"},{"NP","us"},{"GP","uum"},{"DP","ibus"},{"AP","us"},{"OP","ibus"}}),
FOURTHN(false, new String[][] {{"NS","u"},{"GS","us"},{"DS","u"},{"AS","u"},{"OS","u"},{"NP","ua"},{"GP","uum"},{"DP","ibus"},{"AP","ua"},{"OP","ibus"}}),
FIFTH(false, new String[][] {{"NS","es"},{"GS","ei"},{"DS","ei"},{"AS","em"},{"OS","e"},{"NP","es"},{"GP","erum"},{"DP","ebus"},{"AP","es"},{"OP","ebus"}}),
EGO(false, new String[][] {{"NS","ego"},{"GS","mei"},{"DS","mihi"},{"AS","me"},{"OS","me"},{"NP","nos"},{"GP","nostrum"},{"DP","nobis"},{"AP","nos"},{"OP","nobis"}}),
TU(false, new String[][] {{"NS","tu"},{"GS","tui"},{"DS","tibi"},{"AS","te"},{"OS","te"},{"NP","vos"},{"GP","vestrum"},{"DP","vobis"},{"AP","vos"},{"OP","vobis"}});
  private final String[][] sing;
  private final boolean isAnotherEnding;
  
  private  NounEndings(boolean possibleOtherEnding ,String[][] endings) {
  sing = endings;
      isAnotherEnding = possibleOtherEnding;
      
  }
 /** given a grammatical case abbreviation, return the ending of this enum that goes with that case. If the noun is to be declined in the third person, replace the is's with the word REPLACE to indicate to the next level up to replace them with the word-specific ending.
  @param cas a String that represents a grammatical case:
  * NS for nominative singular,
  * GS for genitive singular,
  * DS for dative singular,
  * AS for accusative singular,
  * OS for object of a preposition singular,
  * and NP, GP,DP,AP, and OP for their plural counterparts. */
  private String getEnding(String cas)  {
      boolean passedValueIsACase = false;
      String retVal = "";
      for(String[] s: sing)
      { if (cas.equalsIgnoreCase(s[0]))
      {passedValueIsACase = true;
      retVal = s[1];}
      }
     
  return retVal;
  }
  
  public String[][] getEndings() {return sing;}
  public boolean isAnotherEndingPossible() {return isAnotherEnding;}
  
}//enum





/** Stores a root word, a declension(an instance of NounEndings), and if the declension is the third declension, the ending to replace the is/other with.
 Should contain the method for determining if the input is the name.
 */
class NounWord {
final String root;
final NounEndings declension;
/** if this is a third declension word, if this boolean is true, then this word should be declined with the "other" ending from "is/other" rather than the is. Prevents "clemens, clementis" from being "clementis, clementis"*/
final boolean otherNotIS;
/** if this is third declension, where the nominative singular is "is/other", the other ending to tack on rather than is.*/
final String other;
/**in the case of "clemens, clementis", the "t" in clementis. */
final String inBetweenRootAndNormalEndings ;

/** 
 all forms of the noun, in the form:
 * [NS] [clemens]
 * [GS] [clementis]
 * [DS] [clementi]
 * [AS] [clementem]
 * [OS] [clemente]
 * [NP] [clementes]
 * [GP] [clementum]
 * [DP] [clementibus]
 * [AP] [clementes]
 * [OP] [clementibus]
 
 */
String[][] declined = new String[10][2]; 

boolean plural;

public NounWord(String root, NounEndings declension) {
this.root = root;
this.declension = declension;
otherNotIS = false;
other = null;
inBetweenRootAndNormalEndings = null;
decline();
}

public NounWord(String root, NounEndings declension, boolean plural) {
this.root = root;
this.declension = declension;
otherNotIS = false;
other = null;
inBetweenRootAndNormalEndings = null;
this.plural = plural;
decline();
}

public NounWord(String root, NounEndings declension, boolean otherNotIS, String other, String inBetweenRootAndNormalEndings) {
this.root = root;
this.declension = declension;
this.otherNotIS = otherNotIS;
this.other = other;
this.inBetweenRootAndNormalEndings = inBetweenRootAndNormalEndings;
decline();
}

public NounWord(String root, NounEndings declension, boolean otherNotIS, String other, String inBetweenRootAndNormalEndings, boolean plural) {
this.root = root;
this.declension = declension;
this.otherNotIS = otherNotIS;
this.other = other;
this.inBetweenRootAndNormalEndings = inBetweenRootAndNormalEndings;
this.plural = plural;
decline();
}
    
private String[][] decline() {
String[][] d =declension.getEndings();
    if(declension.isAnotherEndingPossible() && otherNotIS) {
    for(int i = 0; i<10;i++)
    {
        declined[i][0] = d[i][0];
        if (d[i][1].equalsIgnoreCase("is") && !d[i][0].equalsIgnoreCase("GS"))
         {declined[i][1]= root.concat(other);
         }
        else {declined[i][1] = root.concat(inBetweenRootAndNormalEndings).concat(d[i][1]);}
        
 // System.out.println(declined[i][0] + " is " + declined[i][1]);
    }//for
}//if
    else {
    for(int i = 0; i<10;i++)
    {
        declined[i][0] = d[i][0];
        declined[i][1] = root.concat(d[i][1]);
     //   System.out.println(declined[i][0] + " is " + declined[i][1]);
    }//for
    }
    return declined;
}

public String[][] getDeclined() {return declined;}

/** if the string given to it matches at least one of the cases, and that case's number(singular or plural) matches this noun, then return the last case abbreviation that matches.*/
public String getCase(String test){
String retVal= null;
for (String[] s:declined) {
if ((plural && s[0].contains("P")) || ( !plural && s[0].contains("S"))) {// make it so that you do not confuse a singular with a plural ending
    if(s[1].equalsIgnoreCase(test))
    {
       retVal = s[0];
        //test for the case confusions
        for (String[] f:declension.getEndings()) {
             if(f[0].equalsIgnoreCase( s[0])) {
                 if (!plural)
                 {  
                     if (identifyCaseConfusionsSing(f[1]) != null) {retVal = identifyCaseConfusionsSing(f[1]);}
                 }//if singular
                 else
                 {
                      if (identifyCaseConfusionsPlu(f[1]) != null) {retVal = identifyCaseConfusionsPlu(f[1]);}
                 }
             }// if
        }// for; end case confusions test
       
        }
    }//outer if
    
}//for
return retVal;
}

private String identifyCaseConfusionsPlu(String t) {
String retVal = null;
switch (declension) {
    case SECONDN:
         if (t.equalsIgnoreCase("a"))
        {retVal = "NP|AP";}
    case FIRST:
    case SECONDM:
        if (t.equalsIgnoreCase("is"))
        {retVal = "DP|OP";}
        break;
    
    case THIRDN:
         if (t.equalsIgnoreCase("a"))
        {retVal = "NP|AP";}
       
    case THIRDMF:
         if (t.equalsIgnoreCase("ibus"))
        {retVal = "DP|OP";}
        break;
    case FOURTHM:
         if (t.equalsIgnoreCase("us"))
        {retVal = "NP|AP";}
         if (t.equalsIgnoreCase("ibus"))
        {retVal = "DP|OP";}
        break;  
    case FOURTHN:
         if (t.equalsIgnoreCase("ua"))
        {retVal = "NP|AP";}
         if (t.equalsIgnoreCase("ibus"))
        {retVal = "DP|OP";}
        break;
    case FIFTH:
         if (t.equalsIgnoreCase("ebus"))
        {retVal = "DP|OP";}
         if (t.equalsIgnoreCase("es"))
        {retVal = "NS";}
        break;
    case EGO:
        if (t.equalsIgnoreCase("nos"))
        {retVal = "NP|AP";}
        if (t.equalsIgnoreCase("nobis"))
        {retVal = "DP|OP";}
        break;
     case TU:
        if (t.equalsIgnoreCase("vos"))
        {retVal = "NP|AP";}
        if (t.equalsIgnoreCase("vobis"))
        {retVal = "DP|OP";}
        break;
}//switch
return retVal;
}//method


private String identifyCaseConfusionsSing(String t) {
String retVal = null;
switch (declension) {
    case FIRST:
        if (t.equalsIgnoreCase("a"))
        {retVal = "NS|OS";}
        else if (t.equalsIgnoreCase("ae"))
        {retVal = "GS|DS";}
        break;
    case SECONDN:
        if (t.equalsIgnoreCase("um"))
        {retVal = "NS|AS";}
        
    case SECONDM:
         if (t.equalsIgnoreCase("o"))
        {retVal = "DS|OS";}
        break;
    case THIRDMF:
         if (t.equalsIgnoreCase("is") && !otherNotIS)
        {retVal = "NS|GS";}
         else if (t.equalsIgnoreCase("is") && otherNotIS){retVal = "GS";}
        break;
    case THIRDN:
         if (t.equalsIgnoreCase("is") && !otherNotIS)
        {retVal = "NS|GS|AS";}
         else if(t.equalsIgnoreCase("is") && otherNotIS)
             retVal = "GS";
        break;
    case FOURTHM:
         if (t.equalsIgnoreCase("us"))
        {retVal = "NS|GS|AS";}
        break;  
    case FOURTHN:
         if (t.equalsIgnoreCase("u"))
        {retVal = "NS|DS|AS|OS";}
        break;
    case FIFTH:
         if (t.equalsIgnoreCase("ei"))
        {retVal = "GS|DS";}
        break;
    case EGO:
        if (t.equalsIgnoreCase("me"))
        {retVal = "AS|OS";}
        break;
        case TU:
        if (t.equalsIgnoreCase("te"))
        {retVal = "AS|OS";}
        break;
}//switch
return retVal;
}//method

/** takes in a string identifying the case of a noun (NS, GS, DS, AS, OS, NP, GP, DP, AP, OP)
 and returns the form of the noun which matches that case. If the case code is not one of the above, throws an exception.*/
  public String getDeclinedString(String caseCode) throws Exception
    { String retVal = null;
        for(String[] pair:declined)
    {if (pair[0].equalsIgnoreCase(caseCode))
    {retVal = pair[1];}
    }
        if (retVal == null)
        {Exception e = new Exception("bad caseCode passed to getDeclinedString: " + caseCode );
        throw e;
        }
        return retVal;
    }
}