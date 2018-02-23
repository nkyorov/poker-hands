/**
 * Part of CW2 - COMP1721
 * University of Leeds
 * @author sc16nsk
 */
import java.util.*;
public class PokerHand extends CardCollection {
  /**
   * Creates an empty hand.
   */
  public PokerHand(){
    super();
  }

  /**
   * Creates a hand given two-character representations of each card using spaces
   * @throws PokerException when the hand contains more than  five cards.
   * @param hand Card representation
   */
  public PokerHand(String hand){
    String[] cardsDealt = hand.split("\\s+");
    if(cardsDealt.length > 5){
      throw new PokerException("Too many cards given.");
    }
    else {
      for(int i = 0; i<cardsDealt.length; i++){
        Card card = new Card(cardsDealt[i]);
        cards.add(card);
      }
    }
  }

  /**
   * Adds a card to the hand, unless the hand already contains the card or hand is full
   * @throws PokerException if hand has 5 cards already or the card is in the hand
   * @param c Card to be added
   */
  public void add (Card cardToAdd){
    if (cards.size() >= 5 || cards.contains(cardToAdd)){
      throw new PokerException("Hand is full already");
    }
    else if (cards.contains(cardToAdd)){
      throw new PokerException("Hand contains this card alreay.");
    }
    else {
      cards.add(cardToAdd);
    }
  }

  /**
   * Empties hand, while adding each one to the given deck
   * @throws PokerException when called on an empty hand
   * @param deck Deck to which card will be added
   */
  public void discard(Deck deck){
    if(cards.isEmpty()){
      throw new PokerException("Hand contains no cards.");
    }
    else {
      for(int i =0; i<cards.size(); i++){
        deck.add(cards.get(i));
      }
      cards.clear();
    }
  }

  /**
   *
   * @return details of the hand, using two-character representation separated by space
   */
  @Override
  public String toString(){
    return cards.toString().replace("[", "").replace("]", "").replace(",", "");
  }

  /**
   * Similiar to toString but implements Unicode symbols
   * @return details of the hand, one char representation and unicode symbol
   */
  public String toFancyString(){
    return cards.toString().replace("[", "").replace("]", "").replace(",", "").replace("C", "\u2663").replace("H","\u2665").replace("S", "\u2660").replace("D", "\u2666");
  }

  /**
   * Checks if a hand contains four cards of the same rank in a five card hand
   * @return boolean value depending whether there is a four of a kind present
   */
  public boolean isFourOfAKind(){
    if(cards.size()!= 5){
      return false;
    }

    Map<Card.Rank, Integer> inHand = new HashMap<>();
    int fourOf = 0;
    for (Card c: cards){
      if(!inHand.containsKey(c.getRank())){
        inHand.put(c.getRank(), 1);
      }else{
        inHand.put(c.getRank(), inHand.get(c.getRank())+ 1);
      }
    }

    for(Card.Rank r: inHand.keySet()){
      if(inHand.get(r) == 4){
        fourOf++;
      }
    }
    if(fourOf == 1){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Checks if a hand contains five cards of the same suit
   * @return boolean value depending whether there is flush present
   */
  public boolean isFlush(){
    if(cards.size()!= 5){
      return false;
    }

    Map<Card.Suit, Integer> inHand = new HashMap<>();
    int flush = 0;
    for (Card c: cards){
      if(!inHand.containsKey(c.getSuit())){
        inHand.put(c.getSuit(), 1);
      }
      else{
        inHand.put(c.getSuit(), inHand.get(c.getSuit())+ 1);
      }
    }

    for(Card.Suit s: inHand.keySet()){
      if(inHand.get(s) == 5){
        flush++;
      }
    }

    if(flush == 1){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Checks if a hand contains two cards of the same rank in a five card hand
   * Takes into account whether there is more than a pair present.
   * @return boolean value depending whether there is a pair present
   */
  public boolean isPair(){
    if(cards.size()!= 5){
      return false;
    }

    Map<Card.Rank, Integer> inHand = new HashMap<>();

    int onlyPair,otherPairs;
    onlyPair=otherPairs = 0;

    for (Card c: cards){
      if(!inHand.containsKey(c.getRank())){
        inHand.put(c.getRank(), 1);
      }else{
        inHand.put(c.getRank(), inHand.get(c.getRank())+ 1);
      }
    }

    for(Card.Rank r: inHand.keySet()){
      if(inHand.get(r) == 2){
        onlyPair++;
      }

      if(inHand.get(r)== 3){
        otherPairs++;
      }
    }
    if(onlyPair == 1 && otherPairs != 1){
      return true;
    }else{
      return false;
    }
  }

  /**
   * Checks if a hand contains two pairs
   * @return boolean value depending whether there is a two-pair hand present
   */
  public boolean isTwoPairs(){

    Map<Card.Rank, Integer> inHand = new HashMap<>();

    if(cards.size()!= 5){
      return false;
    }
    int pairs = 0;

    for (Card c: cards){
      if(!inHand.containsKey(c.getRank())){
        inHand.put(c.getRank(), 1);
      }else{
        inHand.put(c.getRank(), inHand.get(c.getRank())+ 1);
      }
    }

    for(Card.Rank r: inHand.keySet()){
      if(inHand.get(r) == 2){
        pairs++;
      }
    }

    if(pairs == 2){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Checks if a hand contains three cards of the same rank in a five card hand
   * @return boolean value depending whether there is a three of a kind present
   */
  public boolean isThreeOfAKind(){
    if(cards.size()!= 5){
      return false;
    }

    //Which element is inserted fist does not matter, so we can use HashMap to store the hand
    Map<Card.Rank, Integer> inHand = new HashMap<>();

    //Store same ranks
    int threeOf,pair;
    threeOf=pair= 0;

    for (Card c: cards){
      if(!inHand.containsKey(c.getRank())){
        inHand.put(c.getRank(), 1);
      }
      else{
        inHand.put(c.getRank(), inHand.get(c.getRank())+ 1);
      }
    }

    for(Card.Rank r: inHand.keySet()){
      if(inHand.get(r) == 2){
        pair++;
      }
      if(inHand.get(r) == 3){
        threeOf++;
      }
    }
    //Three of a kind consists always consist of at least one pair made from the three cards
    if(threeOf == 1 && pair != 1){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Checks if a hand contains three cards of the same rank and a pair in a five card hand
   * @return boolean value depending whether there is a full house present.
   */
  public boolean isFullHouse(){
    //Which element is inserted fist does not matter, so we can use HashMap to store the hand
    Map<Card.Rank, Integer> inHand = new HashMap<>();

    if(cards.size()!= 5){
      return false;
    }

    int threeOf = 0;
    int pairs = 0;

    for (Card c: cards){
      if(!inHand.containsKey(c.getRank())){
        inHand.put(c.getRank(), 1);
      }
      else{
        inHand.put(c.getRank(), inHand.get(c.getRank())+ 1);
      }
    }

    for(Card.Rank r: inHand.keySet()){
      if(inHand.get(r) == 3){
        threeOf++;
      }
      else if(inHand.get(r)== 2)
      pairs++;
    }
    if(threeOf == 1 && pairs == 1){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Checks if a hand contains five cards of sequental rank in a five card hand
   * @return boolean value depending whether there is a straight present
   */
  public boolean isStraight(){
    if(cards.size()!= 5){
      return false;
    }
    return true;
  }
}
