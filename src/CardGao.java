//(c) A+ Computer Science
//www.apluscompsci.com
//Name -


public class CardGao
{
	public static final String FACES[] = {"ZERO","ACE","TWO","THREE","FOUR",
			"FIVE","SIX","SEVEN","EIGHT","NINE","TEN","JACK","QUEEN","KING"};

	//instance variables
      private String suit;
      private int face;

  	//constructors
   public CardGao(String s, int v)
   {
      suit = s;
      face = v;
   }
   
   // modifiers
   public void setFace( int v )
   {
      face = v;
   }
   // modifiers
   public void setSuit( String s )
   {
      suit = s;
   }
	//accessors
	public String getSuit()
   {
      return suit;
   }	
   //accessors
   public int getFace()
   {
      return face;
   }

   public int getValue()
   {
      if( getFace() == 1 )
         return 11;
      if( getFace() >= 10 )
         return 10;
      return getFace();
   }
  	//toString
   public String toString()
   {
      
      return FACES[face] + " of " + suit;
   
   }

 }