/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;

/**
 *contains a string with a regular expression and a corresponding verb. 
 * methods of uppercasing, lowercasing, automatically adding in things like definite articles to produce variations to add to the list in Syntax.
 * @author William Krzanowski
 */
public class SingleSyntax {
 Verb mVerb;
 String mString;
 String unchangedString;
 boolean reverse;
 /**@param string the string of the word pattern with "OBJECT" substituted in place of any nouns
  * @param verb the verb associated with this syntax
  * @param reverse_DO_and_IO should the first object be interpreted as a direct object and the second object as an indirect object, or the reverse? The former if false
  * This constructor change the string so that "OBJECT" is replaced with a regex */
 public SingleSyntax( String string,Verb verb, boolean reverse_DO_and_IO) {
 String newString = string.replace("OBJECT","(.*)");
 newString = string.replace("object","(.*)");
     
 mVerb = verb;
 mString = newString;
 reverse = reverse_DO_and_IO;
 
 unchangedString = string;
 }
 
 public SingleSyntax(String string, Verb verb) {
 this(string, verb, false);
 }
 
 public String getString() {return mString;}
 public Verb getVerb() {return mVerb;}
 
 /** returns the same object, but with "the" before each regex*/
 public SingleSyntax addDefiniteArticle() {
         String theString = mString.replace("(.*)", "the (.*)");
      SingleSyntax s = new SingleSyntax(theString, mVerb, reverse);
 return s;}
 
 
  /** returns a copy with an uppercase string */
 public SingleSyntax toUpper() {
         String upperString = unchangedString.toUpperCase();
      SingleSyntax s = new SingleSyntax(upperString, mVerb, reverse);
 return s;}
 
  /** returns a copy with lowercase string */
 public SingleSyntax toLower() {
         String lowerString = unchangedString.toLowerCase();
      SingleSyntax s = new SingleSyntax(lowerString, mVerb, reverse);
 return s;}
}
