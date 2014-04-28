//TreePanel.java
// Audrey St. John

// awt
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

// swing 

import javax.swing.JLabel;
import javax.swing.JPanel;


/** 
 * Refactored SpotsPanel takes a name as input, then uses it to draw random spots.
 * @author Audrey St. John
 **/
public class TreePanel extends JPanel 
{
	

	/**
	 * constructor
	 **/
	public TreePanel()
	{
		// call super constructor
		super( new BorderLayout() );
		
		// create the GUI components
		initGUI();
	}
	
	/** 
	 * Create GUI components.
	 * returns the created main panel
	 **/
	public void initGUI()
	{
	    //create a JLabel to display the directions
	     JLabel directions = new JLabel("<html><b><u>TreePainting!!!</u></b><br/>Click and drag to start a tree painting,<br/>Be patient, as painting is a lot of work!<br/></html>");
	     this.add(directions,BorderLayout.NORTH);

	    add( new TreePainter(),  BorderLayout.CENTER );
	}
	
 
	
}