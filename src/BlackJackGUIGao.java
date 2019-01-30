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

    private int pWinCount, dWinCount;
    //this variable is the area to which all text is written
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

    //use these variables to keep track of the score

    public BlackJackGUIGao()  // this is the constructor need to change it to gao
    {

        setSize(WIDTH,HEIGHT);
        //makes so it can't be changed as size
        this.setResizable(false);

        //initialize all variables - player / pooter and the counts


        //this is the panel to which all objects will be added


        JPanel main = new JPanel();
        //use X.AXIS or Y.AXIS
        main.setLayout(new BorderLayout());

        //these panels will be used to divide the screen
        JPanel top = new JPanel();
        JPanel bot = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();

        JLabel bljk = new JLabel("BLACKJACK");
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        //instantiate a new JTextArea
        text = new JTextArea();
        score = new JTextArea(5,5);

        dealer = new PlayerGao(); // create a dealer
        player = new PlayerGao(); // create a player
        starter = new PlayerGao();
        starter.shuffle();
        startHand();

        //instantiate a new JButton and refer stat to it


        top.setBackground(new java.awt.Color(96, 31, 31));
        bot.setBackground(new java.awt.Color(96, 31, 31));
        left.setBackground(new java.awt.Color(96, 31, 31));
        right.setBackground(new java.awt.Color(96, 31, 31));
        text.setBackground(new java.awt.Color(53, 155, 51));
        text.setForeground(Color.WHITE);

        //instantiate a new JButton and refer rock to it
        hit = new JButton("Hit");
        hit.setActionCommand("hit");
        hit.addActionListener(this);
        hit.setForeground(Color.GREEN);

        //adds a picture

        //instantiate a new JButton and refer paper to it
        // HERE I NEED TO CREATE THE BUTTONS FOR ALL THINGS
        stand = new JButton("Stand");
        stand.setActionCommand("stand");
        stand.addActionListener(this);
        stand.setForeground(Color.RED);

        shuffle = new JButton("Shuffle");
        shuffle.setActionCommand("shuffle");
        shuffle.addActionListener(this);
        shuffle.setForeground(Color.ORANGE);

        //adds a picture

        replay = new JButton("Replay");
        replay.setActionCommand("replay");
        replay.addActionListener(this);
        replay.setForeground(Color.BLUE);

        bljk.setForeground(Color.CYAN);
        //instantiate a new JButton and refer scissors to it
        bot.add(stand);
        bot.add(hit);
        main.add(bot, BorderLayout.SOUTH);

        //add all three buttons to the bottom panel
        left.add(replay);
        left.add(score);
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
        main.add(left, BorderLayout.WEST);
        right.add(shuffle);
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
        main.add(right, BorderLayout.EAST);


        //add the text to the top panel
        top.add(bljk);
        main.add(text, BorderLayout.CENTER);
        main.add(top, BorderLayout.NORTH);

        //add both panels to the main panel
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

    //starts with 2 cards
    public void startHand()
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

    public void actionPerformed (ActionEvent e)
    {

        score.setText("Player: " + pWinCount + "\nDealer: " + dWinCount);
        if (e.getActionCommand().equals("hit"))
        {
            if ( hitPlayer != null  && !hitPlayer.isOver())
            {
                hitPlayer.deal();
                text.setText("Player: " + hitPlayer.giveInfo());
                dealer.dealersTurn();
                text.append("Dealer: " + dealer.giveInfo());
                text.append(overRules());
            }
        }
        else if (e.getActionCommand().equals("stand"))
        {

            dealer.dealersTurn();
            text.setText(allRules());
            if (hitPlayer != null)
            {
                hitPlayer.setOver();
            }
            // Check if deal wants move card
            // Then caculate, display result
        }
        if(e.getActionCommand().equals("shuffle"))
        {
            starter.shuffle();
            startHand();
        }
        if (e.getActionCommand().equals("replay"))
        {

            startHand();

        }


    }
    public String overRules()
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
    public String allRules()
    {
        if(player.getHandValue() > 21 && dealer.getHandValue() <= 21)
        {
            dWinCount++;
            return "Dealer WINS --- Player Busted\nclick replay to play again";

        }
        else if(player.getHandValue() <= 21 && dealer.getHandValue() > 21)
        {
            pWinCount++;
            return "Player WINS --- Dealer Busted\nclick replay to play again";
        }
        else if(player.getHandValue()  > 21 && dealer.getHandValue() > 21)
        {
            return "Both of you BUSTED --- no one wins\nclick replay to play again";
        }
        else if(player.getHandValue() < dealer.getHandValue())
        {
            dWinCount++;
            return "Dealer had a larger score --- Dealer Wins\nclick replay to play again";
        }
        else if(player.getHandValue() > dealer.getHandValue())
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
