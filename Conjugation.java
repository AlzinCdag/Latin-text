/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textgameprototype;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/** Stores the information of verb endings in every mood, voice, and tense.
 *
 * @author Administrator
 */
public class Conjugation {
static Conjugation I = new Conjugation(1,"a","ate","ari","e"),
II = new Conjugation(2,"e","ete","eri","ea"),
III = new Conjugation(3,"e","ite","i","a"),
IIIio = new Conjugation(5,"e","ite","i","ia"),
IV = new Conjugation(4,"i","ite","iri","ia");

    

private final String[] futact1 = {"abo","abis","abit","abimus","abitis","abunt"};
private  final String[] futpas1 = {"abor","abiris","abitur","abimur","abimini","abuntur"};
private final String[] futact2 = {"ebo","ebis","ebit","ebimus","ebitis","ebunt"};
private final String[] futpas2 = {"ebor","ebiris","ebitur","ebimur","ebimini","ebuntur"};
private final String[] futact3 = {"am","es","et","emus","etis","ent"};
private final String[] futpas3 = {"ar","eris","etur","emur","emini","entur"};
private final String[] futpasIIIio = {"iar","ieris","ietur","iemur","iemini","ientur"};
private final String[] futactIIIio = {"iam","ies","iet","iemus","ietis","ient"};
private final String[][] futureActiveEndings = {futact1,futact2,futact3,futactIIIio,futactIIIio};
private  final String[][] futurePassiveEndings = {futpas1,futpas2,futpas3,futpasIIIio,futpasIIIio};

private final String infEnd;
private final String[] presentEnds;
private final String[] impEnds;
private final String[] perfEnds;
private final String[] plupEnds;
private final String[] futEnds;
private final String[] futPerfEnds;
private final String[] pasPresentEnds;
private final String[] pasImpEnds;
private final String[] pasPerfEnds;
private final String[] pasPlupEnds;
private final String[] pasFutEnds;
private final String[] pasFutPerfEnds;

private final String[] subjPresentEnds;
private final String[] subjImpEnds;
private final String[] subjPerfEnds;
private final String[] subjPlupEnds;
private final String[] subjPasPresentEnds;
private final String[] subjPasImpEnds;
private final String[] subjPasPerfEnds;
private final String[] subjPasPlupEnds;

//future imperative; see pg 57 ofW. Michael Wilson's Latin Essentials of grammar. for singular, "dico ha dic, duco has duc, facio has fac, fero has fer"
private final String[] futImpActiveSingAcrossConj = {"ato","eto","ito","ito","ito"};
private final String futImpActiveSing;
private final String[] futImpActivePluAcrossConj = {"atote","etote","itote","itote","itote"};
private final String[] futImpActivePluThirdPersonAcrossConj = {"anto","ento","unto","iunto","unto"};
private final String futImpActivePlu;
private final String futImpActiveThirdPersonPlu;
// no futImpAct third person sing because it is the same as the regular futImpActSing
private final String[] futImpPasSingAcrossConj = {"ator","etor","itor","itor","itor"};
private final String futImpPasSing;
private final String[] futImpPasPluAcrossConj = {"antor","entor","untor","iuntor","untor"};
private final String futImpPasPlu;

private final String pluralImp;
private final String passiveInf;
private final String[] pluralPasImps = {"amini","emini","imini","imini","imini"};
private final String pluralPasImp;
private final int num;
private final HashMap<String,String[]> allEndings = new HashMap<>();

/** Build your own verb.*/
public Conjugation(int number, String inf, String pasInf,
                                String[] presEnds, String[] impEnds, String[] perfEnds, String[]  plupEnds, String[] futEnds, String[] futPerfEnds,
                                String[] pasPresEnds, String[] pasImpEnds, String[] pasPerfEnds, String[] pasPlupEnds, String[] pasFutEnds, String[] pasFutPerfEnds,
                                String[] subjPresEnds, String[] subjImpEnds, String[] subjPerfEnds, String[]  subjPlupEnds,
                                String[] subjPasPresEnds, String[] subjPasImpEnds, String[] subjPasPerfEnds, String[] subjPasPlupEnds,
                                String impActSing, String impActPlu, String impPasSing, String impPasPlu,
                                String ImpFutActSing, String ImpFutActPlu, String ImpFutActIIIPl,
                                String ImpFutPasSing, String ImpFutPasPlu) {
num = number;
infEnd = inf;
passiveInf = pasInf;

presentEnds = presEnds;
this.impEnds = impEnds;
this.perfEnds = perfEnds;
this.plupEnds = plupEnds;
this.futEnds = futEnds;
this.futPerfEnds = futPerfEnds;

pasPresentEnds = pasPresEnds;
this.pasImpEnds = pasImpEnds;
this.pasPerfEnds = pasPerfEnds;
this.pasPlupEnds = pasPlupEnds;
this.pasFutEnds = pasFutEnds;
this.pasFutPerfEnds = pasFutPerfEnds;

subjPresentEnds = subjPresEnds;
this.subjImpEnds = subjImpEnds;
this.subjPerfEnds = subjPerfEnds;
this.subjPlupEnds = subjPlupEnds;
subjPasPresentEnds = subjPasPresEnds;
this.subjPasImpEnds = subjPasImpEnds;
this.subjPasPerfEnds = subjPasPerfEnds;
this.subjPasPlupEnds = subjPasPlupEnds;



allEndings.put("ind pre act",presentEnds);
allEndings.put("ind pre pas",pasPresentEnds);
allEndings.put("ind imp act", this.impEnds);
allEndings.put("ind per act", this.perfEnds);
allEndings.put("ind plu act", this.plupEnds);
allEndings.put("ind futp act", futPerfEnds);
allEndings.put("ind imp pas", pasImpEnds);
allEndings.put("ind per pas", pasPerfEnds);
allEndings.put("ind plu act", pasPlupEnds);
allEndings.put("ind ftp pas", pasFutPerfEnds);
allEndings.put("sbj pre act", subjPresentEnds);
allEndings.put("sbj pre pas", subjPasPresentEnds); 
allEndings.put("ind fut act", this.futEnds);
allEndings.put("ind fut pas", pasFutEnds);
allEndings.put("sbj imp act", subjImpEnds);
allEndings.put("sbj imp pas", subjPasImpEnds);
allEndings.put("sbj per act", subjPerfEnds);
allEndings.put("sbj per pas", subjPasPerfEnds);
allEndings.put("sbj plu act", subjPlupEnds);
allEndings.put("sbj plu pas", subjPasPlupEnds);


 futImpActiveSing = ImpFutActSing;
futImpActivePlu = ImpFutActPlu;
futImpActiveThirdPersonPlu = ImpFutActIIIPl;
futImpPasSing = ImpFutPasSing;
futImpPasPlu = ImpFutPasPlu;
pluralPasImp = impPasPlu;
pluralImp = impActPlu;
String[] impFutAct = {futImpActiveSing, futImpActivePlu,futImpActiveThirdPersonPlu};
String[] impFutPas = {futImpPasSing,futImpPasPlu};

allEndings.put("imp fut act", impFutAct );
allEndings.put("imp fut pas", impFutPas );

String[] impPresAct = {infEnd, impActPlu};
allEndings.put("imp pre act",impPresAct);
String[] impPresPas = {infEnd + "re",pluralPasImp};
allEndings.put("imp pre pas", impPresPas);

}


public Conjugation(int number, String infinitiveEndingMinusRE,  
        String pluralImperative, String wholePassiveEnding, String vowelChangeInSubjPresent ) {
    
infEnd = infinitiveEndingMinusRE;

pluralImp = pluralImperative;
passiveInf = wholePassiveEnding;
    
num = number;
switch (number) {
    case 1:
           presentEnds =e("o", "as", "at", "amus", "atis", "ant");
           pasPresentEnds =e("or", "aris", "atur", "amur",  "amini", "antur");
           
        break;
    case 2:
        presentEnds =e("eo", "es", "et", "emus", "etis", "ent");
        pasPresentEnds =e("eor", "eris", "etur",  "emur",  "emini", "entur");
        break;
            case 3:
    presentEnds = e("o","is","it","imus","itis","unt");
    pasPresentEnds = e("or","eris","itur","imur","imini","untur");
    break;
            case 5:
    presentEnds = e("io","is","it","imus","itis","iunt");
    pasPresentEnds = e("ior","eris","itur","imur","imini","iuntur");
break;
            case 4:
    presentEnds =e("io", "is","it", "imus",  "itis", "iunt");
    pasPresentEnds =e("ior", "iris","itur", "imur",  "imini", "iuntur");
break;
 default:
    presentEnds =e("o", infEnd +"s", infEnd +"t",infEnd + "mus",  infEnd +"tis",infEnd + "nt");
    pasPresentEnds =e("or", infEnd +"ris", infEnd +"tur",infEnd + "mur",  infEnd +"mini",infEnd + "ntur");
                break;
}
allEndings.put("ind pre act",presentEnds);
allEndings.put("ind pre pas",pasPresentEnds);

impEnds = e(infEnd +"bam",infEnd +"bas",infEnd +"bat",infEnd +"bamus",infEnd +"batis",infEnd +"bant");
allEndings.put("ind imp act", impEnds);

perfEnds = e("i","isti","it","imus","istis","erunt");
allEndings.put("ind per act", perfEnds);

plupEnds = e("eram","eras","erat","eramus","eratis","erant");
allEndings.put("ind plu act", plupEnds);

futPerfEnds =e("ero","eris","erit","erimus","eritis","erint");
allEndings.put("ind futp act", futPerfEnds);

if((number == 5) || (number == 4))
pasImpEnds = e("iebar","iebaris","iebatur","iebamur","iebamini","iebantur");
else
    pasImpEnds = e(infEnd +"bar",infEnd +"baris",infEnd +"batur",infEnd +"bamur",infEnd +"bamini",infEnd +"bantur");

allEndings.put("ind imp pas", pasImpEnds);

pasPerfEnds = e(" sum"," es"," est"," sumus", " estis"," sunt");
allEndings.put("ind per pas", pasPerfEnds);

pasPlupEnds = e(" eram"," eras"," erat"," eramus", " eratis"," erant");
allEndings.put("ind plu act", pasPlupEnds);

pasFutPerfEnds = e(" ero"," eris"," erit"," erimus", " eritis"," erunt");
allEndings.put("ind ftp pas", pasFutPerfEnds);

subjPresentEnds = e(vowelChangeInSubjPresent +"m", vowelChangeInSubjPresent + "s", vowelChangeInSubjPresent +"t", vowelChangeInSubjPresent + "mus", vowelChangeInSubjPresent + "tis", vowelChangeInSubjPresent + "nt");
allEndings.put("sbj pre act", subjPresentEnds);

subjPasPresentEnds = e(vowelChangeInSubjPresent +"m", vowelChangeInSubjPresent + "s", vowelChangeInSubjPresent +"t", vowelChangeInSubjPresent + "mus", vowelChangeInSubjPresent + "tis", vowelChangeInSubjPresent + "nt");
allEndings.put("sbj pre pas", subjPasPresentEnds);

          futEnds = futureActiveEndings[number -1]; 
          allEndings.put("ind fut act", futEnds);
          
          pasFutEnds = futurePassiveEndings[number -1];
          allEndings.put("ind fut pas", pasFutEnds);
          
subjImpEnds =e(infEnd +"rem", infEnd +"res", infEnd +"ret",infEnd + "remus",  infEnd +"retis",infEnd + "rent");
allEndings.put("sbj imp act", subjImpEnds);

subjPasImpEnds =e(infEnd +"rer", infEnd +"reris", infEnd +"retur",infEnd + "remur",  infEnd +"remini",infEnd + "rentur");        
allEndings.put("sbj imp pas", subjPasImpEnds);

subjPerfEnds = e("erim","eris","erit","erimus", "eritis","erint");
allEndings.put("sbj per act", subjPerfEnds);

subjPasPerfEnds = e(" sim"," sis"," sit"," simus", " sitis"," sint");
allEndings.put("sbj per pas", subjPasPerfEnds);

subjPlupEnds = e("issem", "isses", "isset", "issemus", "issetis", "issent");
allEndings.put("sbj plu act", subjPlupEnds);

subjPasPlupEnds =  e(" essem", " esses", " esset", " essemus", " essetis", " essent");
allEndings.put("sbj plu pas", subjPasPlupEnds);


 futImpActiveSing = futImpActiveSingAcrossConj[number -1];
futImpActivePlu = futImpActivePluAcrossConj[number-1];
futImpActiveThirdPersonPlu = futImpActivePluThirdPersonAcrossConj[number-1];
futImpPasSing = futImpPasSingAcrossConj[number-1];
futImpPasPlu = futImpPasPluAcrossConj[number-1];
pluralPasImp = pluralPasImps[number -1];
String[] impFutAct = {futImpActiveSing,futImpActivePlu,futImpActiveThirdPersonPlu};
String[] impFutPas = {futImpPasSing,futImpPasPlu};


allEndings.put("imp fut act", impFutAct );
allEndings.put("imp fut pas", impFutPas );

String[] impPresAct = {infEnd, pluralImp};
allEndings.put("imp pre act",impPresAct);
String[] impPresPas = {infEnd + "re",pluralPasImp};
allEndings.put("imp pre pas", impPresPas);
}

private String[] e(String...s) {
return s;}

/** given the mood(except for imperative), the voice, and the tense (except, in the Subjunctive mood, for future and future perfect), gets the 6-string array of singular and plural endings for each person.
 throws an exception if it is passed input that has no corresponding conjugation. 
 @return an array of the form {"first person singular","second person singular","third person singular","first person plural","second person plural","third person plural"}*/
public String[] getEndings(Mood m, Voice v, Tense t) throws Exception{
String[] retVal = {"1SING","2SING","3SING","1PLU","2PLU","3PLU"};
if (m == m.IMPERATIVE)
    throw new Exception("getEndings in Enum conjugation was passed the Mood Impeative. Use another method!"); 
        switch (m)
        {case INDICATIVE:
                switch (v) {
                    case ACTIVE:
                        switch (t) 
                            {case PRESENT:
                                retVal = presentEnds;
                                break;
                            case IMPERFECT:
                                 retVal = impEnds;
                                break;
                            case PERFECT:
                                 retVal = perfEnds;
                                break;
                            case PLUPERFECT:
                                 retVal = plupEnds;
                                break;
                            case FUTURE:
                                 retVal = futEnds;
                                break;
                            case FUTUREP:
                                 retVal = futPerfEnds;
                                break;
                        } // switch tense
                        break;
                    case PASSIVE:
                        switch (t) 
                            {case PRESENT:
                                retVal = pasPresentEnds;
                                break;
                            case IMPERFECT:
                                 retVal = pasImpEnds;
                                break;
                            case PERFECT:
                                 retVal = pasPerfEnds;
                                break;
                            case PLUPERFECT:
                                 retVal = pasPlupEnds;
                                break;
                            case FUTURE:
                                 retVal = pasFutEnds;
                                break;
                            case FUTUREP:
                                 retVal = pasFutPerfEnds;
                                break;
                        }// switch tense indicative passive
                        break;
                }// switch voice in indicative
            break;
        case SUBJUNCTIVE:
                 switch (v) {
                    case ACTIVE:
                        switch (t) 
                            {case PRESENT:
                                retVal = subjPresentEnds;
                                break;
                            case IMPERFECT:
                                 retVal = subjImpEnds;
                                break;
                            case PERFECT:
                                 retVal = subjPerfEnds;
                                break;
                            case PLUPERFECT:
                                 retVal = subjPlupEnds;
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
                                retVal = subjPasPresentEnds ;
                                break;
                            case IMPERFECT:
                                 retVal = subjPasImpEnds;
                                break;
                            case PERFECT:
                                 retVal = subjPasPerfEnds;
                                break;
                            case PLUPERFECT:
                                 retVal = subjPasPlupEnds;
                                break;
                            case FUTURE:
    
                            case FUTUREP:
                                 throw new Exception("future passive subjunctive tense does not exist!");
                                
                            default:
                                throw new Exception("non-valid tense in subjunctive passive"); 
                                
                        }// switch tense indicative passive
                        break;
                }// switch voice in indicative
            break;

        }
        return retVal;
}
/** given a list of parameters, returns the appropriate imperative ending for the verb. (third person only applies in the future tense.) */
public String imperative(boolean plural, boolean positive, boolean passive, boolean future, boolean thirdPerson) {
String retVal;
    if (!future) {
    
        if(positive) //present positive
        {
            if(!passive)//present positive active
            { if (plural) {retVal = pluralImp;} else {retVal = infEnd;}//singular
            }//active
            else{//passive
            if (plural) {retVal = pluralPasImp;} else {retVal = infEnd + "re";}//singular
            }//passive
        
        }//positive
        else//negative endings are just the infinitive endings.Noli an Nolite come in at a higher level. 
        {
            if(!passive)
            retVal = infEnd + "re"; //active
            else   retVal = passiveInf;
        }
    }//present
    else//future
    {
    if (!passive) {
        if (!plural) {retVal = futImpActiveSing;}// active sing
        else {
            if (thirdPerson)
                {retVal = futImpActiveThirdPersonPlu;} else {retVal = futImpActivePlu;}
            }//plural
    }//active
    else {
        if (!plural) {retVal = futImpPasSing;} else {retVal = futImpPasPlu;}
    }
    }
return retVal;
}
/** given an ending, spits out a code of the conjugation of this ending. If there are multiple possibilities, it tacks on more codes, separated by the "+" character. 
 this code is of the form: "+", to indicate a new code, "xxx xxx xxx", the classification of the code, 
 * and "y", the number, 1-6, of the ending. (there are usually 6 endings: "I, You, he/she/it, we, you all, they",
 * but the imperative verbs look different, usually just "singular, plural" although future imperative has "singular, plural, third person plural") 
 * each code should look like "+ xxx xxx xxx y". The first set of xes represents the mood: "imp" for imperative, "sbj" for subjunctive, and "ind" for indicative.
 * the second set represents the tense: "pre" for present, "imp" for imperfect, "per" for perfect, "plu" for pluperfect, "fut" for future, and "ftp" for future perfect.
 * the third set represents the voice: "act" for active and "pas" for passive.  */
public String unConjugate(String ending)
    {StringBuffer sb = new StringBuffer();
   
        
        allEndings.forEach((String s, String[] e)->{
           int i = 1;
            for(String r: e) {
            if (r.equalsIgnoreCase(ending))
            {String g = "+ " + s +" " +i ;
            sb.append(g);}
                i++;
                    } 
            i = 1;
        });
    String retVal = new String(sb);
    return retVal;
    }
/** returns a list of possible moods, voices, and tenses from a conjugated verb. */
public ArrayList<MoodVoiceTenseWrapper> unConjugateAndWrap(String verb)
    {  ArrayList<MoodVoiceTenseWrapper> retVal = new ArrayList<MoodVoiceTenseWrapper>();
        allEndings.forEach((String s, String[] e)->{
           int i = 1;
            for(String r: e) {
            if (r.equalsIgnoreCase(verb))
            {retVal.add(new MoodVoiceTenseWrapper(s));
            }
                i++;
                    } 
            i = 1;
        });
        
    return retVal;
    }
/** the number of the conjugation, 1-4, and 5 for IIIio.*/
public int getNumber() {return num;}
}

enum Mood{
INDICATIVE("IND"),IMPERATIVE("IMP"),SUBJUNCTIVE("SBJ");
private final String des;
private Mood(String s) {des = s;}
public String getString() {return des;}


}

enum Tense {
PRESENT("PRE"),IMPERFECT("IMP"),PERFECT("PER"),PLUPERFECT("PLU"),FUTURE("FUT"),FUTUREP("FTP");
private final String des;
private Tense(String s) {des = s;}
public String getString() {return des;}
}



enum Voice {
ACTIVE("ACT"),PASSIVE("PAS");
private final String des;
private Voice(String s) {des = s;}
public String getString() {return des;}
}

enum VerbalNoun {
PPP("PPP"),PAP("PAP"),FAP("FAP"),FPP("FPP"), NOTVERBALNOUN("Not a verbal noun");
String ID;
private VerbalNoun(String id) {ID = id;}
public String getString() {return ID;}
}

class MoodVoiceTenseWrapper{
Mood m;
Voice v;
Tense t;
int subjectDeclensionNumber = 0;
int subjectCase;
VerbalNoun VN = VerbalNoun.NOTVERBALNOUN;
public MoodVoiceTenseWrapper(Mood mo, Voice vo, Tense te, int scase){
m = mo;
v = vo;
t = te;
subjectCase = scase;}

public MoodVoiceTenseWrapper(Mood mo, Voice vo, Tense te, int scase,int subjectDeclensionNo)
{this(mo,vo,te,scase);
    subjectDeclensionNumber = subjectDeclensionNo;
if (vo == Voice.PASSIVE && ((te == Tense.PERFECT )|| (te == Tense.PLUPERFECT) || (te == Tense.FUTUREP)))
{mo = null;
vo = null;
te = null;
VN = VerbalNoun.PPP;
subjectCase = scase;
subjectDeclensionNumber = subjectDeclensionNo;
}
}




public MoodVoiceTenseWrapper(String code)
{
    Mood[] mood = {Mood.INDICATIVE,Mood.IMPERATIVE,Mood.SUBJUNCTIVE};
    Voice[] voice = {Voice.ACTIVE,Voice.PASSIVE};
    Tense[] tense = {Tense.PRESENT,Tense.IMPERFECT, Tense.PERFECT, Tense.FUTURE, Tense.FUTUREP};
    Mood retMo = null;
    Voice retVo = null;
    Tense retTe = null;
    boolean matched = false;
    Scanner s = new Scanner(code);
    String m = s.next();
    for (Mood mo:mood)
        {if (mo.getString().equalsIgnoreCase(m))
             {retMo = mo;}}
    String v = s.next();
    for (Voice vo:voice)
        {if (vo.getString().equalsIgnoreCase(v))
             {retVo = vo;}}
      String t = s.next();
    for (Tense te:tense)
        {if (te.getString().equalsIgnoreCase(t))
             { retTe = te;}}  
    this.m = retMo;
    this.v = retVo;
    this.t = retTe;
}
/** if the verb is a verbal noun like a 4th, store the declension and case.*/
public MoodVoiceTenseWrapper(VerbalNoun vNoun, int subjectDeclension, int subjectCase)
{this.subjectCase = subjectCase;
this.subjectDeclensionNumber = subjectDeclension;
VN = vNoun;}

public Mood getMood() {return m;}
public void setMood(Mood mood) {m = mood;}
public Tense getTense() {return t;}
public void setTense(Tense tense) {t = tense;}
public Voice getVoice() {return v;}
public void setVoice(Voice voice) {v = voice;}
public int getSubjectCase() {return subjectCase;}
public int getSubjectDeclension() {return subjectDeclensionNumber;}
public VerbalNoun getVerbalNoun() {return VN;}
public void setVerbalNoun (VerbalNoun verbalNoun) {VN = verbalNoun;}

public String getString() 
{String retVal = null;
    if (VN == VerbalNoun.NOTVERBALNOUN)
     retVal = m.getString() + " tense: " + t.getString() + " voice: " + v.getString() + " case: " + subjectCase + " subjectDeclension number: " + subjectDeclensionNumber;
else { retVal = "Verbal Noun Type: " + VN.getString() + " case: " + subjectCase + " Subject Declension: " + subjectDeclensionNumber;
}
return retVal;
}
}