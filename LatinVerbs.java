/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textgameprototype;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
/**
 *
 * @author Administrator
 */
public class LatinVerbs {
    public static Mood cMood;
    public static Voice cVoice;
    public static Tense cTense;
    public static int cSubjectCase;
    public static int cSubjectDeclension;
    
    public static ArrayList<LatinVerb> verbList = new ArrayList<>();
  
   public static LatinVerb SUM = new LatinVerb(IrregularVerbs.SumConj,"to be","","","","",null)
    {};
    
  public static boolean isSum(String test) {
  boolean retVal = false;
if (!SUM.conj.unConjugateAndWrap(test).isEmpty())
    {retVal  = true;}
   return retVal;
  }
    
    public LatinVerbs()
    {
        verbList.add( new LatinVerb(Conjugation.I,"to love", "amo","am","amav","amat", Verbs.LOVE));
         verbList.add( new LatinVerb(Conjugation.III,"to read", "lego","leg","leg","lect", Verbs.READ));
        verbList.add (new LatinVerb(Conjugation.IIIio,"to look around","circumspicio","circumspic","circumspex","circumspect",Verbs.LOOK));
        verbList.add (new LatinVerb(Conjugation.II,"to examine","video","vid","vid","vis",Verbs.LOOKAT));
        verbList.add (new LatinVerb(Conjugation.I,"to examine","specto","spect","spectav","spectat",Verbs.LOOKAT));
        verbList.add (new LatinVerb(Conjugation.I,"to give","do","d","ded","dat",Verbs.GIVE));
        verbList.add (new LatinVerb(Conjugation.IIIio,"to take","capio","cap","cep","capt",Verbs.TAKE));
        verbList.add (new LatinVerb(Conjugation.I,"to count, catalog (see what you have)","numero","numer","numerav","numerat",Verbs.INVENTORY));
    }
    
    public ArrayList<LatinVerb> getVerbList() {return verbList;}
    
    /** checks what verb is meant*/
    public Verb parse(String x) {
    Entry<String,MoodVoiceTenseWrapper> retEnt = null;
    LatinVerb retVerb = null;
    Verb retVal = null;
        for (LatinVerb lv:verbList)
        {
            for (Entry<String,MoodVoiceTenseWrapper> e:lv.getConjugationArray().entrySet())
            {if (e.getKey().equalsIgnoreCase(x)) 
                 {retEnt = e;
                  retVerb = lv;
            }//if
            }//for
        }
        if (retEnt != null)
        {
            retVal = retVerb.getVerb();
            retVal.setMoodVoiceTenseWrapper(retEnt.getValue());
        }
       
        return retVal;
    }
    
}
