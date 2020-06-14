/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;
/**
 *
 * @author William Krzanowski
 */
public class IrregularVerbs {
    //thos does not include "erim" or "erint"
  static  Conjugation SumConj = new Conjugation(6, "esse", "PASSIVEINFINITIVE",
            e("sum","es","est","sumus","estis","sunt"),e("eram","eras","erat","eramus","eratis","erant"),e("fui","fuisti","fuit","fuimus","fuistis","fuerunt"),e("fueram","fueras","fuerat","fueramus","fueratis", "fuerant"), e("ero","eris","erit","erimus","eritis","erunt"),e("fuero","fueris","fuerit","fuerimus","fueritis","fuerint"),
            e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),
            e("sim","sis","sit","simus","sitis","sint"),e("essem","esses","esset","essemus","essetis","essent"),e("fuerim","fueris","fuerit","fuerimus","fueritis","fuerint"),e("fuissem", "fuisses","fuisset","fuissemus","fuissetis","fuissent"),
  e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),e("NOPASSIVEFORMOFSUM"),
    "es","este","NOPASSIVEFORMOFSUM","NOPASSIVEFORMOFSUM",
     "esto","estote","sunto",
     "NOPASSIVEFORMOFSUM","NOPASSIVEFORMOFSUM"
     
    );


private static String[] e(String...s) {
return s;}
}
