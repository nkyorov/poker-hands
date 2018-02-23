/**
 * Part of CW2 - COMP1721
 * University of Leeds
 * @author sc16nsk
 */
import java.util.*;

/**
 * Deck representation
 * @author sc16nsk
 */
public class Deck extends CardCollection{
  public Deck(){
    super();
    for (Card.Rank r : Card.Rank.values()) {
      for (Card.Suit s : Card.Suit.values()) {
        super.add(new Card(r, s));
      }
    }
  }

  /**
   * Shuffles selected deck
   */
  public void shuffle(){
    Collections.shuffle(cards);
  }

  /**
   * Deals a card
   * @throws PokerException If applied on empty deck
   * @return Card removed card
   */
  public Card deal() throws PokerException{
    if(cards.isEmpty()) {
      throw new PokerException("Deck is empty");
    }
    return cards.remove(0);
  }
}
