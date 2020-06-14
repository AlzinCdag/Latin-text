/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latintextgamev1;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
/** go back to getEndings, learn more about imperatives
 *
 * @author William Krzanowski
 */
public class LatinVerb {
    Conjugation conj;
    String definition;
    String first,inf,third,fourth;
    int dNum;
    Verb verb;
    LinkedHashMap<String,MoodVoiceTenseWrapper> conjugationStorer;
    
    LatinVerb(Conjugation conjugation,String definition, String firstpp,String infinitiveMinusEntireEnd,String thirdpp,String fourthpp, Verb thisVerb)
    {conj = conjugation;
    this.definition = definition;
    first = firstpp;
    inf = infinitiveMinusEntireEnd;
    third = thirdpp;
    fourth = fourthpp;
    
    dNum = conj.getNumber();
    verb = thisVerb;
    conjugationStorer = buildConjugationArray();
    
    }
    /** for irregular verbs*/
    LatinVerb(String definition, String firstpp,String infinitiveMinusEntireEnd,String thirdpp,String fourthpp)
    {
    this.definition = definition;
    first = firstpp;
    inf = infinitiveMinusEntireEnd;
    third = thirdpp;
    fourth = fourthpp;
    conjugationStorer = buildConjugationArray();
    }
    
    public Verb getVerb() {return verb;}
    
    Mood[] mo = {Mood.INDICATIVE,Mood.SUBJUNCTIVE};
    Voice[] vo = {Voice.ACTIVE,Voice.PASSIVE};
    Tense[] te = {Tense.PRESENT,Tense.IMPERFECT,Tense.PERFECT,Tense.PLUPERFECT,Tense.FUTURE,Tense.FUTUREP};
    int[] subjectDeclension = {1,2,3,4,5,6,7,8};
    int[] subjectCase = {1,2,3,4,5,6};
    
    public LinkedHashMap<String,MoodVoiceTenseWrapper> buildConjugationArray()      
    {LinkedHashMap<String,MoodVoiceTenseWrapper> retVal = new LinkedHashMap<>();
    
    for(Mood M :mo)
    {
            for (Voice V:vo)
            {
                for (Tense T:te)
                {
                    try { 
                        //this try/catch statement intends to take care of the exception produced when the loops get to the 
                        //Subjunctive future/future perfect stage. It exits the current tense loop (currently at its final spots, future and future perfect)
                        //and immediately begins the loop in the next iteration of the voice loop.
                    for (int c: subjectCase) {
                        for (int d:subjectDeclension) {
                          boolean bool = false;
                          if(!retVal.containsValue(getDeclension(M,V,T,d,c)) ) {
//retVal.keySet().stream().filter(getDeclension(M,V,T,d,c)::equalsIgnoreCase).collect(Collectors.joining()).isEmpty()) {
                                 
                            retVal.put( getDeclension(M,V,T,d,c),new MoodVoiceTenseWrapper(M,V,T,c,d));
                          // System.out.println(M.getString() + V.getString() + T.getString() + c + d);
                          }
                            }
                        }
                     }
                            catch (Exception e)
                            {break;}//catch
                    }
            }
            
        }
       
  /*  for (Entry<String, MoodVoiceTenseWrapper> e:retVal.entrySet())
    {System.out.println(e.getKey() + "    " + e.getValue().getString());} 
    */
    return retVal;}
    
      public LinkedHashMap<String,MoodVoiceTenseWrapper> getConjugationArray() {
      return conjugationStorer;
      } 
    
    
    /**check the section about passive perfect indicative endings for correctness on the supine endings.
     * The subject declension matters when dealing with cases which have the supine stem; 1-5 for the normal cases, 6 for 2n, 7 for 3n, and 8 for 4n. */
    public String getDeclension(Mood m, Voice v, Tense t, int subjectDeclension, int scase) throws Exception{
String retVal = "1SING";
if (m == m.IMPERATIVE)
    throw new Exception("getEndings in Enum conjugation was passed the Mood Impeative. Use another method!"); 
        switch (m)
        {case INDICATIVE:
                
                        switch (t) 
                            {case PRESENT:
                            case IMPERFECT:
                            case FUTURE:
                                 retVal = inf + conj.getEndings(m,v,t)[scase-1];
                                break;
                            case PERFECT:
                            case PLUPERFECT:
                            case FUTUREP:
                                if (v == Voice.ACTIVE)
                                 retVal = third + conj.getEndings(m,v,t)[scase-1];
                                else 
                                    switch (subjectDeclension){
                                        //endings 1sing, 6-7 plu are the same. Endings 2sing and 4th sing and plural are the same.
                                        case 1:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "a" ;
                                            else
                                            retVal = fourth +"ae" ;    
                                        break;
                                       case 2:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "us";
                                            else
                                            retVal = fourth +"i" ;    
                                        break; 
                                        case 3:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "is";
                                            else
                                            retVal = fourth +"es";    
                                        break;
                                    case 4:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "us";
                                            else
                                            retVal = fourth +"us";    
                                        break;
                                    case 5:
                                            retVal = fourth + "es";     
                                        break;
                                    case 6://2nd n
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "um";
                                            else
                                            retVal = fourth +"a";    
                                        break;
                                    case 7: //3N
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "is";
                                            else
                                            retVal = fourth +"a";    
                                        break;
                                   case 8://4N
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "u" ;
                                            else
                                            retVal = fourth +"ua" ; //+ conj.getEndings(m,v,t)[scase-1]    
                                        break;
                                    }
                                break;
                        } // switch tense
                        break;
                   
                // switch voice in indicative
            
        case SUBJUNCTIVE:
                 switch (v) {
                    case ACTIVE:
                        switch (t) 
                            {case PRESENT:
                            case IMPERFECT:
                                 retVal =inf + conj.getEndings(m,v,t)[scase-1];
                                break;
                            case PERFECT:
                            case PLUPERFECT:
                                 retVal = third + conj.getEndings(m,v,t)[scase-1];
                                break;
                            case FUTURE:
                            case FUTUREP:
                                  throw new Exception("future Active subjunctive tense does not exist!");
                                
                            default:
                                throw new Exception("non-valid tense in subjunctive active");
                        } // switch tense
                        break;
                    case PASSIVE:
                        switch (t) 
                            {case PRESENT:
                            case IMPERFECT:
                                 retVal =inf + conj.getEndings(m,v,t)[scase-1];
                                break;
                            case PERFECT:
                            case PLUPERFECT:
                                 switch (subjectDeclension){
                                        case 1:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "a";
                                            else
                                            retVal = fourth +"ae" ;    
                                        break;
                                       case 2:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "us" ;
                                            else
                                            retVal = fourth +"i" ;    
                                        break; 
                                        case 3:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "is" ;
                                            else
                                            retVal = fourth +"es" ;    
                                        break;
                                    case 4:
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "us" ;
                                            else
                                            retVal = fourth +"us" ;    
                                        break;
                                    case 5:
                                            retVal = fourth + "es" ;     
                                        break;
                                    case 6://2nd n
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "um" ;
                                            else
                                            retVal = fourth +"a";    
                                        break;
                                    case 7: //3N
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "is" ;
                                            else
                                            retVal = fourth +"a";    
                                        break;
                                   case 8://4N
                                            if (scase == 1 || scase == 2 || scase ==  3)
                                            retVal = fourth + "u" ;
                                            else
                                            retVal = fourth +"ua" ;    
                                        break;
                                    }
                                break;
                            case FUTURE:
    
                            case FUTUREP:
                                 throw new Exception("future passive subjunctive tense does not exist!");
                                
                            default:
                                throw new Exception("non-valid tense in subjunctive passive"); 
                                
                        }// switch tense subjunctive passive
                        break;
                }// switch voice in indicative
            break;

        }
        return retVal;
}
    
    public String getImperative(boolean plural, boolean positive, boolean passive, boolean future, boolean thirdPerson)
    {String retVal = "";
        
        if(positive) //present positive
        {
            retVal = inf + conj.imperative(plural,positive, passive,future,thirdPerson);//singular
        }//positive
        else//negative endings are just the infinitive endings. 
        {
            if(!plural)
            retVal ="noli " + inf + conj.imperative(false,false, passive,future,thirdPerson); //active
            else   retVal = "nolite " + inf + conj.imperative(true,false, passive,future,thirdPerson);
        }
    //present
    
    return retVal;
    }
    
    public void setConjugationArray(Entry<String[],MoodVoiceTenseWrapper>...tweaks) throws Exception
    {
        for(Entry<String[],MoodVoiceTenseWrapper> e:tweaks) {
            
            for(int i = 1; i <= 6; i++)
            { conjugationStorer.remove(getDeclension(e.getValue().getMood(),e.getValue().getVoice(),e.getValue().getTense(),e.getValue().getSubjectDeclension(),i));
             MoodVoiceTenseWrapper mvt = new MoodVoiceTenseWrapper(e.getValue().getMood(),e.getValue().getVoice(),e.getValue().getTense(),e.getValue().getSubjectDeclension(), i);
            conjugationStorer.put(e.getKey()[i-1], mvt);
            }
            
                }
    }
}
