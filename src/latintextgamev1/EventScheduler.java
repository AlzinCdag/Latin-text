/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latintextgamev1;
import java.util.ArrayList;

/**
 *Throughout the game, various events will be triggered by certain conditions. This class makes sure they happen at the right time.
 * @author krzan
 */
public class EventScheduler {
    public ArrayList<Event> eventList = new ArrayList<Event>();
    public void setUpEvents() {
    eventList.add(Events.kingHunger);
    eventList.add(Events.kingNotice);
        eventList.add(Events.MercDrunk);
    eventList.add(Events.greyConversation);
     eventList.add(Events.greyNotice);
     eventList.add(Events.medusaNotice);
     eventList.add(Events.medusaFollow);
     eventList.add(Events.triumphReturn);
    }
    
    public void run() {
    for (Event e:eventList) {
    if (e.shouldStart() && !e.shouldStop())
        e.run();
    }
    }
    
}

/** This has three components: a list of conditions that must be satisfied to trigger the event, the event itself, and the thing that must happen for the event to stop happening. */
 abstract class Event {

    public abstract boolean shouldStart() ;
    public abstract void run();
    public abstract boolean shouldStop();

}

class Events{

    public static Event kingHunger = new Event() {
    @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (Objects.Player.getLocation() == Objects.Party && Objects.Lobster.getLocation() != null) {
    retVal = true;}
       return retVal; }
    public void run() { System.out.println("The king announces: \"I'm hungry! Somebody bring me some lobster!\"");}
    public boolean shouldStop(){
            boolean retVal = false;
            if (Objects.Lobster.mLocation==Objects.Rex)
            {retVal = true;
            Objects.Lobster.setInvisible(true);}
    return retVal;
    }
    };
    
        public static Event kingNotice = new Event() {
   boolean occured = false;
   boolean noOrMaybeAnswered;
            @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (Objects.Lobster.getInvisible()) {
    retVal = true;}
       return retVal; }
    public void run() {//Objects.Lobster.setInvisible(true);
      if (!occured) {
          if (noOrMaybeAnswered)
              System.out.println("The king sidles up to you. \"Have you changed your mind yet?\"");
          else {
          System.out.println( "As you hand him the lobster, the king notices you for the first time.\"Aah. You've made it,\" he says with an evil grin. \n" 
                  +"\" So, you've come to my party. Did you bring a gift? - Ah, no, I see you have nothing that would interest a king. \" \n"
            + "The king seems to have an idea. \"Ah, but there's not a person on this island who can compete with you in feats of courage.\n Maybe- no, I dare say it not...\" \n"
            + "You motion for him to go on. "); }
            System.out.println( "\"Maybe as my gift, you can give me your service. I ask for but one task. Will you perform it?\n Answer, \'ita vero\' for yes or \'minime\' for no.\" ");
    int answer=SpecialInput.specialMatch("ita vero", "minime");
    if (answer == 1)
    {System.out.println("\"Very good. Bring me... \n The head of Medusa!\"\n\n Everyone at the party stops and stares. One man spills his wineglass.\n\n"
            + "The king gestures west. \"The door is that way.\"");
    occured = true;}
    else if (answer ==2) {System.out.println("\" Huh. I thought you were more hero than that. You have insulted me by not giving me a present.\n Guards!\" A bunch of burly guards in security jeckets drag you away and chop off your head."); 
    Objects.Lobster.setInvisible(false);
    Noun.move(Objects.Lobster, Objects.Party);
    Util.jigsUp();
    noOrMaybeAnswered = true;}
    else if (answer == -1)
    {System.out.println("\"I didn't catch that. You have to answer \'ita vero\', \'yes\', or \'minime\', \'no\'. If you want to talk again,  bring me another lobster. ");
    Objects.Lobster.setInvisible(false);
    Noun.move(Objects.Lobster, Objects.Party);
    noOrMaybeAnswered = true;}
      }
      else {System.out.println("The king shouts at you, \"I told you, the door is that way! West! Bring me the head of Medusa!\""
              );}
      }
    public boolean shouldStop(){
 boolean retVal = false;

 if (Objects.Player.getLocation() != Objects.Party)
            retVal = true;
            
        return retVal;
    }
    };

    public static boolean mercdrinks = false;
        public static Event MercDrunk = new Event() {
        public boolean occured;
                      @Override
    public boolean shouldStart() {
        return mercdrinks;
        }
        public boolean shouldStop(){ return !mercdrinks;}
        public void run() {
        System.out.println("Mercury looks at you kindly. \"So,\" he says, \"Now that I'm refreshed, I remember my mission.\n You're Jupiter's kid, right?\" You nod.\n \"Well, I've been instructed by the big J himelf"
                + "\nto give you some stuff and information you'll need to conquer Medusa.\n\n \"Medusa,\", he continues, \" Has no real menace to the wide world\n because she lives in a secret location and never bothers anybody. \n"
                + "Unfortunately for those suicidal idiots -\" he glances at you-\n \"Oops, sorry, incredibly brave heroes- who go looking for her,\n if you directly look at her, you'll turn to stone. You'll need these things:\"\n"
                + "Mercury gives you a sword. \n\"This sword,\" he says, \"This gladius, is a gift from Jupiter. Happy Birthday. \n"
                + "Mercury gives you a helmet (galea).\n It's a nice helmet, but rather behind the times.\n \"This,\" he says, \"is a Helm of Darkness. Like the one Hades uses. \nIf you wear it, you turn invisible.\""
                + "\nMercury gives you a pair of sandals (sandalium, or, since they are plural, sandalia),\n which have wings on them. \"These,\" he says, \"will help you fly anywhere. \" \n"
                +"Mercury gives you a burlap sack (saccus). \"This sack will help you safely store Medusa's head,\" he says.\n"
                + "Finally, Mercury gives you some directions. \"I don't know where Medusa is. Nobody does; she keeps off the grid so noone tries to kill her.\n The only people who do are the Grey Ladies. \n"
                + "They live on an island as far west as you can go. You will have to get that information out of them.\"");
        Noun.move(Objects.Helm,Objects.Player);
        Noun.move(Objects.Sandals,Objects.Player);
        Noun.move(Objects.Sword, Objects.Player);
        Noun.move(Objects.Bag, Objects.Player);
        Objects.Helm.setInvisible(false);
        Objects.Sandals.setInvisible(false);
        Objects.Sword.setInvisible(false);
        Objects.Bag.setInvisible(false);
       mercdrinks = false; 
        Objects.Mercury.setFirstDescription("Mercurius, god of travel, stands in the temple. \nOnce, he was so thirsty he forgot his mission.\n Now, he's just waiting for the latest episode of Siren Idol to start.");
        }
        };
        
        static int greyConversationNumber = 0;
        boolean eyeTaken = false;
            public static Event greyConversation = new Event() {
    @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (Objects.Player.getLocation() == Objects.GreyFire && Objects.Eye.getLocation() != Objects.Player) {
    retVal = true;}
       return retVal; }
    public void run() { 
        if (Objects.GreyFire.getS() == null)
            {Objects.GreyFire.setS(Objects.GreyShore);
            Objects.GreyFire.setW(Objects.GreyWShore);
            System.out.println("The sisters unblock the exits in their rush to make sure the eye is all right.\n After a few minutes, they start singing again. They have forgotten about you.");}
        switch (greyConversationNumber) {
            case (0):
        System.out.println(" Sister 1: \"Olympus may claim the clamoring sea\n Dethroning its deathless diety\nFor our birthright repayment we call\n The sea claims all.\"");
        System.out.println(" Sister 2: \" Deino, pass me the eye!\"\nSister 1: \"All right, here you are, Enyo.\" She pulls out her single eye and tosses it into the air.");
        Noun.move(Objects.Eye, Objects.GreyFire);
        Objects.Eye.setInvisible(false);
        greyConversationNumber++;
        break;
                    case (1):
        System.out.println("Sister 2 catches the eye and puts it in her forehead.\n");
        System.out.println("Sister 2: \"Pushed from our palace Pushed from our pool\n Nothing but malice for Neptune's rule\n Forced into nothing, though ever we call\n the sea claims all. \"");
              Noun.move(Objects.Eye, Objects.Grey);
        Objects.Eye.setInvisible(true);
        greyConversationNumber++;
        break;
                    case (2):
        System.out.println("Sister 3: \"They could have had help in the world of the wet\nBut they left us with nothing, so nothing they'll get!\nAs mortals to our death-traps fall\nThe sea claims all.\"");
        System.out.println("Sister 1: \"All right, toss the eye back to me for the finale!\"\nSister 2:\" Here you go, Deino.\" \n She tosses the first sister the eye. It arcs, whirling, into the air.");
                Noun.move(Objects.Eye, Objects.GreyFire);
        Objects.Eye.setInvisible(false);
        greyConversationNumber++;
        break;
                    case (3):
       System.out.println("Sister 1 catches the eye and puts it back into her forehead.");
                        System.out.println("Sister 1: \"Three are the gorgons, like the Fates\nAnd no man knows where their ambush waits\nAnd ever as they die we'll triumphantly call\nThe sea claims all! \"");
                      Noun.move(Objects.Eye, Objects.Grey);
        Objects.Eye.setInvisible(true);
        greyConversationNumber = 0;  
        break;
            default:
                break; 
        }
        

        }
    public boolean shouldStop(){
            boolean retVal = false;
            if (Objects.Eye.mLocation==Objects.Player)
            {retVal = true;
}
    return retVal;
    }
    };
            
            public static boolean greyMaybe;
            public static int greyAdvance = 10;
            
        public static Event greyNotice = new Event() {
    @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (Objects.Player.getLocation() == Objects.GreyFire && Objects.Eye.getLocation() == Objects.Player) {
    retVal = true;}
       return retVal; }
        public int noticeStage = 1;
    public void run() { 
        switch (noticeStage) {
            case 1:
                switch (greyConversationNumber) {
                    case 1:
                    System.out.println("Sister 2 holds her hand out, waiting to catch the eye. But the pace of the eternal song will not wait for her. \nAfter a long, long, moment, she starts, \"pushed from our palace...\" \n\"Oh, nuts, I can't remember the rest without the eye! Hurry up and toss it!\"\n She stands gesturing for sister 1 to hand it over."); 
                    break;
                    case 3:
                    System.out.println("Sister 1 tries to grab the eye from the air. She grasps at nothingness. \n Seeing she has to keep going, she starts, \"Three are the Gorgons, like the Graces, and - uh- no man can wait to see their faces- no, that can't be right!\"\n She sighs. \"Blast! We'll never be on Siren Idol at this rate! \n I can't remember the lyrics without the eye to look at the sheet! Come on, Enyo, pass me the eye so we can start over again!\" She holds out her hand angrily. ");
                            break;
                    default:
                        System.out.println("The sister who just asked for the eye holds her hand out. \nShe stands there for a while. Finally, she bursts out, \"Just give me the eye already!\"");
                }
                  System.out.println("\n\"I did pass the eye! You must have dropped it!\"\n\"We can't lose the eye! Everyone start looking for it!\" \n They all drop to their knees and begin looking for the eye.");
                noticeStage++;
                break;

            case 2:
                if (!greyMaybe)
                System.out.println("As they continue to not find the eye, Sister 1, drawing near you, seems to notice something. \"Hey, Enyo! Pemphredo! There's someone else here!\" "
                        + "\nThe sisters turn to you, hissing. Two of them block off the exits. Your cap of invisibility (galea) won't help you against three blind people. \n"
                        + "\"Hey, you!\" shouts Sister 2. \"Did you take our eye?\" (answer ita vero for yes and minime for no.)");
                Objects.GreyFire.setS(null); Objects.GreyFire.setW(null);
                
                int answerGrey1 =SpecialInput.specialMatch("ita vero", "minime");
                    switch (answerGrey1) {
                        case 1:
                         System.out.println("\"Oh, you did!\" they shout.\n \"Do you know we're going to be on Siren Idol next week? We need that eye to learn our lines!\"\nthey sigh. \"All right, we'll bargain for it.\"");
                            noticeStage++;
                            break;
                        case 2:
                            System.out.println("\"All right then! You probably have eyes; help us look for it! We must have that eye!\"\n You wait a minute, pretending to search, and tell them you found it.");
                       noticeStage++;
                            break;
                        default:
                            System.out.println("\"We didn't catch that. Did you take our eye?\" (answer ita vero for yes and minime for no.)");
                            break;
                    }
                break;
            case 3:// ask them to tell you where medusa is
                System.out.println("\"What do you want us to give you for our eye back?\"\n"
                        + "Sister 1 pipes up. \"Medusa's location?\" (locus medusae) \n"
                        + "Sister 2 guesses, \"Our shield, without which you can't possibly hope to defeat Medusa? - why'd I say that?\" (scutum) \n"
                        + "\"Both?\" (amb) guesses sister 3.");
                 int answerGrey2 =SpecialInput.specialMatch("locus medusae", "scutum","amb");
                    switch (answerGrey2) {
                        case 3:System.out.println("\"You drive a hard bargain, kid,\" says sister 2. \nShe gropes along the ground until she reaches a secret place on a nearby tree.\n She digs in the earth and finds a shield(scutum). She tosses the shield into the air and you catch it.");
                      Noun.move(Objects.Shield, Objects.Player);
                      Objects.Shield.setInvisible(false);
                        case 1: 
                            System.out.println("\"Medusa lives in an island far, far northwest of the sea west of here!\"");
                            noticeStage ++;
                            break;
                        case 2: System.out.println("\"You want the shield, kid?\" sneers sister 3. \nShe gropes along the ground until she reaches a secret place on a nearby tree.\n She digs in the earth and finds a shield(scutum). She tosses the shield into the air and you catch it."
                                + "\n\"But you can't use it without finding Medusa! You'll have to try every direction of the compass after you leave our Western Shore! Mwahahaha!\" ");
                             Noun.move(Objects.Shield, Objects.Player);
                             Objects.Shield.setInvisible(false);
                              noticeStage ++;
                            break;
                        default: System.out.println("\"What was that, kid?\"");
                    }
                break;
            case 4: 
               System.out.println("\"All right, kid, enough is enough! Give us the eye!\"\n They're " + greyAdvance +  " cubits away, and they're advancing on you.");
                  if (greyAdvance == 0)
                  {System.out.println("\"Time's up!\" cackle the Grey Sisters. They are upon you now, and despite your sandals, they hold you down and suffocate you. \nAs your consciousness fades, you hear them talking about which of them has to keep the old eye, and which two get one of yours.");
                 Util.jigsUp();
                 greyAdvance = 10;
                 noticeStage = 1;
                 greyConversationNumber = 0;
                 Noun.move(Objects.Eye, Objects.Grey);
                  }
                  else
               greyAdvance--;
                    break;
        }

    }
    public boolean shouldStop(){
            boolean retVal = false;
            if (Objects.Eye.mLocation==Objects.Grey)
            {retVal = true;
            
            }
    return retVal;
    }
    };
        public static boolean medusaNoticed = false;
          public static Event medusaNotice = new Event() {
    @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (Objects.Player.getLocation() == Objects.MedusaShore && Objects.Helm.getLocation() == Objects.Medusa) {
    retVal = true;}
       return retVal; }
    public void run() { System.out.println("You wait a while. The sun rises. reflecting off the pink coral of the island.\nThen, you hear a shriek from within the cave. \n"
            + "\"Free at last!\" cries a soft female voice- a mutilated, distorted voice, a voice hollowed by years of pain and despair,\nbut a voice that in its undercurrents bespeaks a noble upbringing, an inner knowledge of beauty.\n"
            + "You hear the source of the voice climb up out of the cave, and make its way towards you.\n\"Oh, thank you, thank you, thank you a dozen times over!\" cries the invisible Medusa. \n"
            + "\"I don't have to be shunned by the world anymore!\" she shouts. \"I can walk about freely- and no one will look at me horrified!\"\n" 
           + "Then, her voice becomes sly. \" I suppose you were ordered here to bring back my head?\" You nod.\n\"And I suppose you really dislike the task?\" you nod.\n\"Well, if you bring me back to civilisation, I'll help you out! "
            + "You'll get to fulfill your task-\nNobody says anything about whether they want the rest of me attached to that head, after all- \nAnd if you need revenge on them, I'll be more than happy to take off this helm at an appropriate moment.\n"
            + "You agree, and grasp her hand. She is pulled into the air. \"Goodbye, Stheno! Goodbye, Eurayle! I'm leaving now, at least for a while!\"");
    medusaNoticed = true;}
    public boolean shouldStop(){
            boolean retVal = false;
            if (medusaNoticed)
            {retVal = true;
            }
    return retVal;
    }
    };
          
  public static Event medusaFollow = new Event() {
    @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (medusaNoticed) {
    retVal = true;}
       return retVal; }
    public void run() {
        if (Objects.Medusa.getLocation() != null) {
        if (Objects.Medusa.getLocation() != Objects.Player.getLocation()) {
        Noun.move(Objects.Medusa,Objects.Player.getLocation());
       System.out.println("Medusa comes with you.");
        }
        }
            }
    public boolean shouldStop(){
            boolean retVal = false;
            if (Objects.Player.getLocation() == Objects.Library)
            {retVal = true;
            }
    return retVal;
    }
    };
  
public static Event triumphReturn = new Event() {
    @Override
    public boolean shouldStart() {
    boolean retVal = false;
    if (Objects.Player.getLocation() == Objects.Party && (Objects.Medusa.getLocation() == Objects.Party || ((Objects.Head.getLocation() == Objects.Bag) && (Objects.Bag.getLocation() == Objects.Player)))) {
    retVal = true;}
       return retVal; }
    public void run() { System.out.println("The party is different from before.\n"
            + "It is a wedding. The evil king of Seriphos and all his buddies are here to witness his marriage to your mother.\n"
            +"You curse yourself for leaving your mother alone with him. \nYou are the only one, after all, who has been able to buy her time against this forced marriage.\n"
            + "The king is muttering to himself. \"At last, when I marry this princess, the throne of both Seriphos and Argos are mine!\"\n "
            + "He is shouting to the priest to hurry up the ceremony when he sees you enter.\n"
            + "\"Ah, the little hero is back,\" he says.\n"
            + "\"And were you able to find me the HEAD OF MEDUSA? If you didn't, you broke your promise, and I'll ask my guards to kill you now.\"\n");
    if (Objects.Head.getLocation() != Objects.Rooms)
        System.out.println("Your grip tightens on a certain bloody prize wrapped in your burlap sack.");
    else System.out.println("You nod at Medusa, invisible beside you, and whisper, \"when I say, now...\"");
    System.out.println("\"Whoever is on my side, turn your eyes away!\" you shout. Most of the crowd\n just laughs, but your mother averts her face.\nYour breathing slows.\n\n....\n\n\n.....\n\n\n\n\"NOW!\"");
   if (Objects.Head.getLocation() != Objects.Rooms)
   System.out.println("You whip the bloody head out of your sack, and hold it high, turning your own eyes away. \n There is a horrible repeated popping noise as the crowd, one by one, turns to stone.\n Polydectes, evil king, is last."
                + " \"Guards!\" he shouts. He looks around. \"Guards?\"\nAnd then he is gone, frozen like the rest, mouth half-open forever.\nYou embrace your mother. The two of you are the only surviving nobles of the island of Seriphos, so by default, she is queen.\n Despite the loneliness and the cost in frozen blood to get you there, \n you live somewhat happily ever after. ");
    else {System.out.println("Medusa takes off her invisibity cap. \n The crowd petrifies around her, except for the king, who hides his eyes after looking at the others.\n\"Hey, you!\" she shouts at the evil king. \"She doesn't want to be married, don't marry her! Else you have the Gorgon to answer to!\""
            + "\n\"Oh, I see how it is,\" she says, advancing on the king. \"Avert your eyes, maybe she won't get you.\n Maybe if you don't see the pain she's in, every single day, you don't have to do anything about it.\n"
            + "She reaches him. \"Well, let me tell you something, sir,\", she says, as she begins prying his arms open.\n \"It doesn't disappear if you don't look at it. It builds up, day by day, what you do to me. \n"
            + "Just beneath the surface, like a reef beneath the waves.\n You look at it indirectly, you see only water. And soon- all too soon- the boat bursts and you wonder how you missed it.\n"
            + "I'll tell you how you missed it. You never looked properly! Now look!\" she pries his arms away from his face. \"Look into my eyes!\"\n"
            + "She pries his eyelids open. The evil king of Seriphos is forced in her divine eyes to look on the built-up wrath within."
            + " \nThe elements within him try to flee their condemnation- \nthe air flows out of his lungs and the water evaporates and the fire in his eyes snuffs out.\n All that is left, that cannot escape, is the earth, left as an imprint of his existence, forever stone."
            + "\n\n Medusa puts her helmet back on. \"Thanks, I needed that,\" she says. \"Oh, so you're the hero's mother?\" she asks your mother. Your mother nods in shock. \"Come on, let's have some tea! It's been ages since I had decent company.\"\n"
            + "\n As you sit and sample the wedding feast, you can even stomach the lobster. You saved your mother and you saved Medusa. You and your mother are the only nobles left on the island, so the three of you share the role of ruling it. As time goes on, your happiness is unsullied by guilt,\n"
            + "because you fulfilled your mission without having to end an innocent life.\n\n As the first in the long line of spectacular Greek heroes, you live happily ever after."); 
   Objects.Medal.setLongDescription("The gold medal reads, \"Congratulations.\n You fulfilled your vow without ending an innocent life.\n You are a hero of the highest order.\n Thank you very much for playing MAGE.\"");
   Objects.Medal.setFirstDescription("The gold medal reads, \"Congratulations.\n You fulfilled your vow without ending an innocent life.\n You are a hero of the highest order.\n Thank you very much for playing MAGE.\"");
   }
   Noun.move(Objects.Medal, Objects.Player);
   System.out.println("You get a medal for your heroic efforts.");
  Util.jigsUp();
    }
    public boolean shouldStop(){
return false;
    }
    };
          
}



