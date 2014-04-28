// TreeApplication.java
// Audrey St. John

// swing 
import javax.swing.JFrame;

/** 
 * Main application to show a HoroscopePanel 
 **/
public class TreeApplication
{
	/**
	 * main method starts the program
	 **/
	public static void main( String[] args )
	{
		// create a new JFrame to hold TreePanel
		JFrame treeFrame = new JFrame();
		
		// set size
		treeFrame.setSize( 600, 1200 );

		// create a TreePanel and add it
		treeFrame.add( new TreePanel() );

		// exit normally on closing the window
		treeFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		treeFrame.setVisible( true );
	}
}