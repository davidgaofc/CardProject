import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class BlackJackGUIGao extends JFrame implements ActionListener
{
    //sets the JFrame size
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    //declare win count variables
    private int pWinCount, dWinCount;

    //these variables are the areaa in which all text is written
    private JTextArea text;
    private JTextArea score;

    //these are the buttons that can be clicked
    private JButton hit;
    private JButton stand;
    private JButton replay;
    private JButton shuffle;

    //use these variables to keep track of the players
    private PlayerGao dealer;
    private PlayerGao player;
    private PlayerGao starter;
    private PlayerGao hitPlayer;


    public BlackJackGUIGao()  //constructor
    {
        //set the size of the jframe
        setSize(WIDTH,HEIGHT);
        //makes so it can't be changed as size
        this.setResizable(false);



        //this is the panel to which all objects will be added


        JPanel main = new JPanel();
        //implement layout of the main panel
        main.setLayout(new BorderLayout());

        //these panels will be used to divide the screen
        JPanel top = new JPanel();
        JPanel bot = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        //title label for the top
        JLabel bljk = new JLabel("BLACKJACK");
        //set the layout for the jpanels within the layout
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        //instantiate JText Areas
        text = new JTextArea();
        score = new JTextArea(5,5);

        //instantiate all of the players
        dealer = new PlayerGao(); // create a dealer
        player = new PlayerGao(); // create a player
        starter = new PlayerGao();

        //start the game
        starter.shuffle();
        startHand();



        // make everything visually appealling
        top.setBackground(new java.awt.Color(96, 31, 31));
        bot.setBackground(new java.awt.Color(96, 31, 31));
        left.setBackground(new java.awt.Color(96, 31, 31));
        right.setBackground(new java.awt.Color(96, 31, 31));
        text.setBackground(new java.awt.Color(53, 155, 51));
        text.setForeground(Color.WHITE);
        bljk.setForeground(Color.CYAN);

        //instantiate a new JButton and refer hit to it
        hit = new JButton("Hit");
        hit.setActionCommand("hit");
        hit.addActionListener(this);
        hit.setForeground(Color.GREEN);



        //instantiate a new JButton and refer stand to it
        stand = new JButton("Stand");
        stand.setActionCommand("stand");
        stand.addActionListener(this);
        stand.setForeground(Color.RED);

        //instantiate a new JButton and refer shuffle to it
        shuffle = new JButton("Shuffle");
        shuffle.setActionCommand("shuffle");
        shuffle.addActionListener(this);
        shuffle.setForeground(Color.ORANGE);

        //instantiate a new JButton and refer replay to it
        replay = new JButton("Replay");
        replay.setActionCommand("replay");
        replay.addActionListener(this);
        replay.setForeground(Color.BLUE);

        //add stand and hit buttons to the bottom jpanel
        bot.add(stand);
        bot.add(hit);
        //add bottom jpanel to the bottom of the layout
        main.add(bot, BorderLayout.SOUTH);

        //add the buttons to the left panel
        left.add(replay);
        left.add(score);
        //add pictures to the left panel
        try
        {
            BufferedImage img = ImageIO.read(getClass().getResource("pokerchip.png"));
            Image resizedimg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            JLabel pic1 = new JLabel(new ImageIcon(resizedimg));
            JLabel pic2 = new JLabel(new ImageIcon(resizedimg));
            JLabel pic3 = new JLabel(new ImageIcon(resizedimg));
            left.add(pic1);
            left.add(pic2);
            left.add(pic3);

        }
        catch (Exception ex)
        {

        }
        //add the left panel to the layout
        main.add(left, BorderLayout.WEST);
        //add the shuffle button to the right panel
        right.add(shuffle);
        //add pics
        try
        {
            BufferedImage img = ImageIO.read(getClass().getResource("pokerchip.png"));
            Image resizedimg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            JLabel pic1 = new JLabel(new ImageIcon(resizedimg));
            JLabel pic2 = new JLabel(new ImageIcon(resizedimg));
            JLabel pic3 = new JLabel(new ImageIcon(resizedimg));
            JLabel pic4 = new JLabel(new ImageIcon(resizedimg));
            right.add(pic1);
            right.add(pic2);
            right.add(pic3);
            right.add(pic4);

        }
        catch (Exception ex)
        {

        }
        //add the right panel to the right part of the layout
        main.add(right, BorderLayout.EAST);


        //add the title jlabel to the top
        top.add(bljk);
        main.add(text, BorderLayout.CENTER);
        main.add(top, BorderLayout.NORTH);

        //set all of the fonts
        bljk.setFont(new Font("BiauKai", Font.BOLD,50));
        text.setFont(new Font("Courier", Font.BOLD,20));
        score.setFont(new Font("Goudy Old Style", Font.ITALIC,40));
        hit.setFont(new Font("Hiragino Kaku Gothic Pro", Font.BOLD,40));
        stand.setFont(new Font("Hiragino Maru Gothic Pro", Font.BOLD,40));
        replay.setFont(new Font("Marker Felt", Font.BOLD,40));
        shuffle.setFont(new Font("Marker Felt", Font.BOLD,40));


        //add the main panel to the frame
        getContentPane().add(main);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public void startHand() //method that begins a round
    {

        player.clearHand();;
        player.deal();
        player.deal();
        text.setText("Player: " + player.giveInfo());
        dealer.clearHand();
        dealer.deal();
        dealer.deal();
        text.append("Dealer: " + dealer.giveInfo());
        hitPlayer = player;
    }

    public void actionPerformed (ActionEvent e) //method that is for button actions
    {

        score.setText("Player: " + pWinCount + "\nDealer: " + dWinCount); //update score text area
        if (e.getActionCommand().equals("hit"))
        {
            if ( hitPlayer != null  && !hitPlayer.isOver()) //check if you are allowed to hit
            {
                hitPlayer.deal(); //give card
                text.setText("Player: " + hitPlayer.giveInfo()); //display info
                dealer.dealersTurn(); //dealer's stuff
                text.append("Dealer: " + dealer.giveInfo());
                text.append(overRules()); //check if anyone busted
            }
        }
        else if (e.getActionCommand().equals("stand"))
        {

            dealer.dealersTurn(); //dealer's stuff
            text.setText(allRules()); //checks busting and if you are more than the dealer
            if (hitPlayer != null)
            {
                hitPlayer.setOver(); //makes it so that you cannot hit anymore
            }
            // Check if deal wants move card
            // Then caculate, display result
        }
        if(e.getActionCommand().equals("shuffle"))
        {
            starter.shuffle(); //makes a new deck and shuffles it
            startHand(); //starts a new hand
        }
        if (e.getActionCommand().equals("replay"))
        {

            startHand(); //lets you play again

        }


    }
    public String overRules() //rules if you do bust or not
    {
        if(player.getHandValue() > 21 && dealer.getHandValue() <= 21)
        {
            dWinCount++;
            hitPlayer = null;
            return "Dealer WINS --- Player Busted \nclick replay to play again ";

        }
        else if(player.getHandValue() <= 21 && dealer.getHandValue() > 21)
        {
            pWinCount++;
            hitPlayer = null;
            return "Player WINS --- Dealer Busted\nclick replay to play again";
        }
        else if(player.getHandValue()  > 21 && dealer.getHandValue() > 21)
        {
            hitPlayer = null;
            return "Both of you BUSTED --- no one wins\nclick replay to play again";

        }
        else
        {
            return "";
        }

    }
    public String allRules() //checks over all of the rules
    {
        if(player.getHandValue() > 21 && dealer.getHandValue() <= 21) //when player busts and dealer doesn't
        {
            dWinCount++;
            return "Dealer WINS --- Player Busted\nclick replay to play again";

        }
        else if(player.getHandValue() <= 21 && dealer.getHandValue() > 21) //when dealer busts but player doesn't
        {
            pWinCount++;
            return "Player WINS --- Dealer Busted\nclick replay to play again";
        }
        else if(player.getHandValue()  > 21 && dealer.getHandValue() > 21) //when both bust
        {
            return "Both of you BUSTED --- no one wins\nclick replay to play again";
        }
        else if(player.getHandValue() < dealer.getHandValue()) //if dealer has larger score
        {
            dWinCount++;
            return "Dealer had a larger score --- Dealer Wins\nclick replay to play again";
        }
        else if(player.getHandValue() > dealer.getHandValue()) //if player has larger score
        {
            pWinCount++;
            return "Player had a larger score --- Player Wins\nclick replay to play again";
        }
        else
        {
            return "ITS A TIE!\nclick replay to play again";
        }

    }
    public static void main(String[] args)
    {
        BlackJackGUIGao run = new BlackJackGUIGao();
    }

}
