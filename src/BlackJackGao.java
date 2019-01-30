import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class BlackJackGao
{
    // create an arraylist for player and dealer
    private PlayerGao dealer;
    private PlayerGao player;
    private PlayerGao starter;
    private String playAgain;

    private int pWinCount, dWinCount; // keeping track of who is winning


    //create my constructor
    public BlackJackGao()
    {

        starter = new PlayerGao();
        starter.shuffle();
        dealer = new PlayerGao(); // create a dealer
        player = new PlayerGao(); // create a player
        do{
            try{
                Scanner kb = new Scanner(System.in);

                // this is where I will put all my methods once i create them
                player.clearHand();
                dealer.clearHand();
                startHand();   //give two cards to player / dealer

                System.out.print("DEALER");
                System.out.println(dealer.giveInfo());
                System.out.print("YOUR");
                System.out.println(player.giveInfo()); // tells each person their hand

                player.hit(); // only ask the player to hit we will make code for logical dealer
                System.out.println(dealer.dealersTurn());  // make this method to find if the dealer needs a card

                System.out.println("\n" + blackJackRules()+ "\n"); // make a method for winning loosing

                System.out.println("Dealer Score: " + dWinCount);
                System.out.println("Player Score: " + pWinCount);

                System.out.println("Would you like to play again? Y/N");
                playAgain = kb.next();
            }catch (IndexOutOfBoundsException e)
            {
                System.out.println("\n \n O NO RAN OUT OF CARDS!");
                System.out.println("I am shuffling more cards now!\n\n");
                starter = new PlayerGao();
            }
        }while (playAgain.equals("y") || playAgain.equals("Y"));

        System.out.println("Thanks for playing!");


    }

    //starts with 2 cards
    public void startHand()
    {
        player.deal();
        player.deal();
        dealer.deal();
        dealer.deal();
    }

    public String blackJackRules()
    {
        if(player.getHandValue() > 21 && dealer.getHandValue() <= 21)
        {
            dWinCount++;
            return "Dealer WINS --- Player Busted";

        }
        else if(player.getHandValue() <= 21 && dealer.getHandValue() > 21)
        {
            pWinCount++;
            return "Player WINS --- Dealer Busted";
        }
        else if(player.getHandValue()  > 21 && dealer.getHandValue() > 21)
        {
            return "Both of you BUSTED --- no one wins";
        }
        else if(player.getHandValue() < dealer.getHandValue())
        {
            dWinCount++;
            return "Dealer had a larger score --- Dealer Wins";
        }
        else if(player.getHandValue() > dealer.getHandValue())
        {
            pWinCount++;
            return "Player had a larger score --- Player Wins";
        }
        else
        {
            return "ITS A TIE!";
        }

    }



    public static void main (String [] args)
    {
        BlackJackGao test = new BlackJackGao();


    }




}


