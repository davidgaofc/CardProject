//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import static java.lang.System.*;
import java.awt.Color;

public class CardRunnerGao
{
	public static void main( String args[] )
	{
		  //uncomment once CardBarker is built
		 
		CardGao one = new CardGao("SPADES", 9);
		out.println(one.getSuit());
		out.println(one.getFace());

		CardGao two = new CardGao("DIAMONDS", 1);
		out.println(two);
		two.setFace(3);
		out.println(two);

		CardGao three = new CardGao("CLUBS", 4);
		out.println(three);

		CardGao four = new CardGao("SPADES", 12);
		out.println(four);

		CardGao five = new CardGao("HEARTS", 12);
		out.println(five);
		
	}
}