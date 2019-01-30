//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

public class BlackJackCardGao extends CardGao
{
  	//constructors
  	public BlackJackCardGao( String s, int f)
  	{
  		super( s, f );
  	}

  	//accessors
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
  		return super.toString() + " " + getValue();
  	}




 }