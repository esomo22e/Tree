//TreePanel.java

//awt
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;


public class TreePainter extends JComponent implements MouseListener
{
	/** Number of generations to create branches. Play with this for coarser/finer detail. **/	
	public static final int NUM_GENERATIONS = 8;
 
	/** Number of children for each branch. Play with this for sparser/fluffier trees. **/
	public static final int NUM_CHILDREN = 3; 
 
	/** Golden ratio makes the child branches aesthetically appealing **/
	public static final double GOLDEN_RATIO = 1.618;
 
	/** Maximum branching angle of children from a parent stick **/
	public static final double MAX_BRANCHING_ANGLE = .5*Math.PI;
 
	/** Diameter of the blossoms. **/
	public static final int BLOSSOM_DIAM = 4;

       
    /**Instance Variable**/
    Point2D startPoint;
    Point2D endPoint;//global variables used on the trunk
    int X;// this will be implemented int he fill oval
    int Y;//this will get the diameter of the blossom
     

    /**
     * Construcor will add the mouselistener so that the tree could be dragged. 
     **/
    public TreePainter()
    {
	    addMouseListener(this);
		    
    }
    /**
     * Implement a mouseListener methods like mouseClicked,mouseEntered, mouseExited
     * This will allow the mouse to be visible and conduct the tree. 
     **/
    public void mouseClicked( MouseEvent e )
    {

    }
    public void mouseEntered( MouseEvent e )
    {

    }
    public void mouseExited( MouseEvent e )
    {

    }
    /**
     * mousePressed will invoke the startPoint because when you pressed the startPoint you are getting the first X, Y coordinates that will paint the tree
     * you will also determine the length of the children branches of the trunk. 
     **/
    public void mousePressed( MouseEvent e)
    {
	//invoke the startPoint 
	startPoint = new Point2D.Double( e.getX() , e.getY() );
	
    }
    /**
     * mouseRelsease will invoke the endPoint and draw the line and get another set of tw X, Y points that will paint the tree.
     **/
    public void mouseReleased( MouseEvent e )
    {
	//invoke the endPoint
	endPoint = new Point2D.Double( e.getX() , e.getY() );
	//it will call to the paint method and repaint the tree
	repaint();
    }
    /**
     * implement the length that will get the length of the branches using the start and endpoints 
     **/
    public double length()
    {
	//local variable of the length
	double localLength;
	//this will get me the square a 
	double a = Math.pow( (startPoint.getX() - endPoint.getX()), 2);
	//this will square b 
	double b = Math.pow((startPoint.getY() - endPoint.getY()), 2 );
	//this will do the pythagoreum theroem and give you the length of the trun. 
	localLength = Math.sqrt(a+b);
	return localLength;
     }
    /**
     * The treeAngle will get the angle ussing the start and endpoints. 
     **/
    public static double treeAngle( Point2D cd, Point2D ef)

    {
	//this will produce the angle between the startPoint and endPoint
	double c= ef.getY()-cd.getY();
	double d=ef.getX()-cd.getX();
	
	//implent the atan2 and give you the angle
	double angle = (double)(Math.atan2(c,d ));
	//return angle
	return angle;
    }
    /**
     * This would 
     **/
       public int randomBlossomDiameter()
    	{		
	    
		
    		// now generate a random diameter in the range [MIN_SPOT_SIZE, maxSize)
    		int randomDiam = (int)Math.floor(Math.random()*( BLOSSOM_DIAM));

			
    		// return it
    		return randomDiam;
    	}
      
    /**
     * The paint method will draw the branch using start point, length, angle, and generation number to guide recursion
     **/
    public void paint( Graphics g)
    {
	
	if( startPoint != null){
	    paintTree( g, NUM_GENERATIONS, startPoint, length(), treeAngle( startPoint, endPoint) );
	}
    }
    /** 
     * Compute the point that is length away from p at the angle.
     * Uses cosine to get the new x coordinate, sine to get the new y coordinate.
     **/
   public Point2D computeEndpoint( Point2D endPoint, double length, double angle )
    {
	    return new Point2D.Double( 	endPoint.getX() + length*Math.cos(angle), // x is cos
              				endPoint.getY() + length*Math.sin(angle));// y is sin
    } 
    /**
     * The paintTree will implement the recursive method which will produce the blossom, trunks, and branches
     **/
    public void paintTree( Graphics g, int generation, Point2D localStartPoint, double localLength, double localAngle )
    {
	//Local variables 
	double newLocalLength;
        Point2D localEndPoint;

       /**
	 * This part of the code will display a random blossom. 
	 **/
	if(generation == 0)
	{
	   
	    // randomly set color from greenish to orangeish reddish
	    	g.setColor(new Color((float) (Math.random()*.6f + .3f), // more red
	    			(float)(.1f+.5f*Math.random()), // some green
	    			(float)(.1f+.1f*Math.random()))); // low blue
		  

    		// fill oval (upper left x, upper left y, width, height)
     		g.fillOval( (int)localStartPoint.getX(), (int)localStartPoint.getY(), 4, 4 );


	            
	 }
	/**
	 * This should display the trunk of the tree which using the endpoints of length ana angle. 
	 **/
	else
	    {
		    //this will set the color of the branches
		      g.setColor(new Color(0f, .25f*generation/NUM_GENERATIONS + .25f, 0f));
		      //when you release an endPoint you have a new startPoint
		     localEndPoint = computeEndpoint(localStartPoint, localLength , localAngle);
		     //draw the trunk
		     g.drawLine( (int)localStartPoint.getX(), (int)localStartPoint.getY(), (int)localEndPoint.getX(), (int)localEndPoint.getY() );
		     //local variables  taht will be used within the for loop that will get a new length and angle for the branches
			  double newAngle;
			 double newLength;
		 /** 
		   * This should produce the latter generations which will produce the children branches. 
		   **/ 
		     for( int n = 1; n <= NUM_CHILDREN; n++ )
			 {
			     //get a new angle of the children used from the angle of the trunk and the randomly generated 
			     newAngle = localAngle + randomAngle( localAngle );
			     //this will be a fraction of the trunk
			     newLength = localLength/GOLDEN_RATIO;
			     //this will paint the branches of the tree
			     paintTree( g, generation-1, localEndPoint, newLength, newAngle );

			 }   
		      }
		

         } 
    /**
     * This will generate a random angle used for the branches
     **/
    public double randomAngle( double angle)
    {
	double newAngle;
	//this will generate random angles
	newAngle= (double)( 2*Math.random()-1 )* MAX_BRANCHING_ANGLE;
	//return it 
	return newAngle;
    }
}


































