import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerGao
{
    //create the array list
    private ArrayList<CardGao> hand; // this is my card class so i can use any method from it.
    public static DeckGao deckOfCards;

    private int total; // player and dealers card totals
    private String answer;
    private boolean isOver;
    //constructor
    public PlayerGao()
    {
        deckOfCards = new DeckGao();
        hand = new ArrayList<CardGao>();
        isOver = false;

    }

    public void setOver()
    {
        isOver = true;
    }

    public boolean isOver()
    {
        return isOver;
    }

    public DeckGao shuffle() // there is a shuffle class aleardy in deck class but this is just
    {                              // refereencing it so it works correctly
        deckOfCards = new DeckGao();
        deckOfCards.shuffle();     // call this once at the beginning to shuffle the singular deck

        return deckOfCards;
    }

    public CardGao deal()
    {
        CardGao addedCard = deckOfCards.nextCard(); // this is getting a card off the top of the
        hand.add(addedCard);  // deck and making sure the number of cards are going down.
        return addedCard;

    }

    public int getHandValue() // this is just going through my arraylist of hand and getting the
    {                         // total by adding up whats in the hand.
        int total = 0;
        for (CardGao card : hand)
        {
            total = total + card.getValue(); // i use this to get the value from card (i copied this
            // from blackjackcard because i was lazy...ILL SHOW U
        }
        return total;
    }
    public void clearHand()
    {
        hand.clear();
        isOver = false;
    }

    public void hit()  // lets add a do while
    {
        do {
            if (getHandValue() <= 21)
            {                 //STARTS HERE
                Scanner kb = new Scanner(System.in);

                System.out.println("Would you like to hit? Y/N");
                answer = kb.next(); // this will give me if they said yes or no

                if (answer.equals( "y" ) || answer.equals( "Y" ))
                {
                    deal();   // currently cant see new card given....
                    System.out.print("Your");
                    giveInfo();
                }
            }else
            {
                System.out.println("Sorry you bust!");
                answer = "n";
            }

        }while( answer.equals("y") || answer.equals("Y")); //need to make this public answer

    }

    public String dealersTurn()  // this will be the dealers turn.
    {
        String ret = "";
        while (getHandValue() < 15) // any time the dealer hits tell me
        {
            ret += ("Dealer HITS" + "\n");
            deal();
        }
        ret += (" Dealer's \n");  // lets let people know dealer hit
        ret += giveInfo();
        return ret;

    }

    public String giveInfo() // give me the info of the cards in my current hand
    {

         return (" current hand is: " + hand.toString() + "\n Total Value " + getHandValue() + "\n Deck size is : " + deckOfCards.cardsLeft() + "\n");

    }

}
