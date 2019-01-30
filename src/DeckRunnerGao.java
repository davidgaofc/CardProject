//(c) A+ Computer Science
//www.apluscompsci.com
//Name -

import static java.lang.System.*;

public class DeckRunnerGao
{
	public static void main( String args[] )
	{
		DeckGao test = new DeckGao();
		
		System.out.println( "All cards in order." );
		for( int j = 1; j <= 52; j++ )
		{
			System.out.println( test.dealCard() );
		}
		
		test.shuffle();	
		
		System.out.println( "\n\nAll cards after shuffling." );
		for( int j = 1; j <= 52; j++ )
		{
			System.out.println( test.dealCard() );
		}				
	}
}