//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//make a Deck class
	public class DeckGao
	{
		public static final int NUMCARDS = 52;
		public static String[] SUITS = "CLUBS HEARTS DIAMONDS SPADES".split(" ");

		private List<CardGao> cards;
		private int top;

		//make a Deck constructor
		//refer cards to new ArrayList
		//set top to the top of the deck 51
		public DeckGao()
        {
            cards = new ArrayList<CardGao>();
            top = NUMCARDS - 1;

            for(String suit : SUITS)
            {
                for(int i = 1; i<14; i++)
                {
                    cards.add(new CardGao(suit, i));
                }
            }
        }
		//loop through all suits
		//loop through all faces 1 to 13
		//add a new TwentyOneCard to the deck

        public CardGao nextCard()
        {
            return cards.get(top--);
        }

        public int cardsLeft()
        {
            return top+1;
        }


        //make a dealCard() method that returns the top card
        public CardGao dealCard()
        {
            return cards.get(top--);

        }
		//write a shuffle() method
		//use Colletions.shuffle
		//reset the top card
        public void shuffle()
        {
            Collections.shuffle(cards);
            top = NUMCARDS - 1;
        }
	}

