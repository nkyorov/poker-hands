/**
 * Program simulating dealing a large number of poker hands
 * Part of CW2 - COMP1721
 * University of Leeds
 * @author sc16nsk
 */

import java.util.*;
import java.io.*;
public class PokerStats{
  public static void main (String[] args){
    if (args.length == 0) {
      System.out.println("Usage: java PokerStats <total number of decks>");
      System.exit(1);
    }

    int decks = Integer.parseInt(args[0]);
    PokerHand hand = new PokerHand();

    //Initialise holders for total number of hand types
    int pair,twoPairs,threeOf,fourOf,fullH,flush, totalDeals;
    pair=twoPairs=threeOf=fourOf=fullH=flush=totalDeals=0;

    for (int decksGiven =0; decksGiven<decks; decksGiven++){
      Deck deck = new Deck();
      deck.shuffle();
      for(int numberOfCards =0; numberOfCards < 51; numberOfCards++){
        Card card = deck.deal();
        if(!hand.contains(card) && numberOfCards != 0)
        hand.add(card);
        if(numberOfCards % 5 == 0 && numberOfCards != 0){
          totalDeals++;
          System.out.println(hand.toFancyString());

          //Count occurances of hands
          if (hand.isPair()){
            pair++;
          }
          if (hand.isTwoPairs()){
            twoPairs++;
          }
          if (hand.isThreeOfAKind()){
            threeOf++;
          }
          if (hand.isFourOfAKind()){
            fourOf++;
          }
          if (hand.isFullHouse()){
            fullH++;
          }
          if (hand.isFlush()){
            flush++;
          }
          //Empty hand
          hand.discard();
        }
      }
      //Empty deck
      deck.discard();
    }

    System.out.println("\nTotal decks: "+ totalDeals);
    System.out.printf("P(Pair)              =  %.3f %%       \n", (float)pair / totalDeals * 100 );
    System.out.printf("P(Two pairs)         =  %.3f %%       \n", (float)twoPairs / totalDeals * 100);
    System.out.printf("P(Three of a Kind)   =  %.3f %%       \n", (float)threeOf / totalDeals * 100 );
    System.out.printf("P(Four of a Kind)    =  %.3f %%       \n", (float)fourOf / totalDeals * 100);
    System.out.printf("P(Full House)        =  %.3f %%       \n", (float)fullH / totalDeals * 100 );
    System.out.printf("P(Flush)             =  %.3f %%       \n", (float)flush / totalDeals * 100);
  }
}
