// TreeApplet.java


// awt
import java.awt.BorderLayout;


// swing 

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** 
 * TreeApplet takes a name as input, then uses it to draw random spots.
 * @author Audrey St. John
 **/
public class TreeApplet extends JApplet
{
	// constructor
	public TreeApplet()
	{
		// call super constructor
		super();
	}
	
	/** 
	 * special method that will be invoked when applet is created
	 **/
	public void start()
	{
		// create an instance of a HoroscopePanel and add it
		add( new TreePanel() );
	}
}
