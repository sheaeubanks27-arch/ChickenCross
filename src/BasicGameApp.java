//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	//public Image astroPic;
    public Image carPic;
    public Image backgroundPic;
    public Image chickenPic;
    public Image turtlePic;
    public Image GameOverPic;


   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
    //private Car car;
    private Chicken chick;
    private Turtle turtle;
    public Car[] cars;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
        backgroundPic = Toolkit.getDefaultToolkit().getImage("road.png");
        carPic = Toolkit.getDefaultToolkit().getImage("Car.png");
        chickenPic = Toolkit.getDefaultToolkit().getImage("Chicken.png");
        turtlePic = Toolkit.getDefaultToolkit().getImage("Turtle.png");
        GameOverPic = Toolkit.getDefaultToolkit().getImage("GameOver.jpeg");


        chick = new Chicken();
        turtle = new Turtle();




       // }
        carLocation();
    }


	//BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}
//method that gives locations to all of the cars in the cars array
public void carLocation(){
        cars= new Car[4];

        Car a1 = new Car(0,0);
        a1.move();

    cars[0] = new Car(320,10);
    cars[0].xpos = 320;
    cars[0].ypos = 10;
    cars[0].move();

    cars[1] = new Car(470,10);
    cars[1].xpos = 470;
    cars[1].ypos = 10;
    cars[1].dy = 10;
    cars[1].move();

    cars[2] = new Car(620,10);
    cars[2].xpos = 620;
    cars[2].ypos = 10;
    cars[2].ypos = 3;
    cars[2].move();

    cars[3] = new Car(170,10);
    cars[3].xpos = 170 ;
    cars[3].ypos = 10;
    cars[3].ypos = 7;
    cars[3].move();




}
//method that makes the cars stop when they intersect with the turtle
public void Block(){
    //if (turtle.hitbox.intersects(car.hitbox) && turtle.isAlive == true) {
       // System.out.println("Turtle Block!");
       // car.dy = 0;

    for(int i = 0; i < cars.length; i++){
        if(cars[i].hitbox.intersects(turtle.hitbox)){
            cars[i].dy=0;

        }
    }

   // }
}
	public void moveThings()
	{
      //calls the move( ) code in the objects
        chick.move();
        crashing();
        turtle.move();
        Block();
//makes the cars move
        for(int n = 0; n < cars.length; n++) {
            cars[n].move();
        }

	}

    public void crashing() {
        //check to see if the cars crash into the chicken

        for(int i = 0; i < cars.length; i++){
            if(cars[i].hitbox.intersects(chick.hitbox)){
            chick.isAlive = false;

            }
        }


    }


        //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();

       //step 2: set canvas as the KeyListener
       canvas.addKeyListener(this);
       //step 2: set canvas as the MouseListener
       canvas.addMouseListener(this);
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);
        //if(car.notCrashed == true) {
           // g.drawImage(carPic, car.xpos, car.ypos, car.width, car.height, null);
       // }
       //draws chicken only when it is alive in the game(makes it dissapear when it dies)
        if(chick.isAlive == true) {
           g.drawImage(chickenPic, chick.xpos, chick.ypos, chick.width, chick.height, null);
       }
        //Draws turtle
       if(turtle.isAlive == true){
           g.drawImage(turtlePic, turtle.xpos, turtle.ypos, turtle.width, turtle.height, null);
       }
       //draws the game over screen when chick dies
       if(chick.isAlive == false){
          g.drawImage(GameOverPic, 0, 0, WIDTH, HEIGHT, null);
      }




// draws all of the car images in the array
        for(int z = 0; z < cars.length; z++){
            g.drawImage(carPic, cars[z].xpos, cars[z].ypos, cars[z].width, cars[z].height, null);
        }


		g.dispose();

		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }
//lets the player controll the chicken's movement in the right and left direction using arrow keys
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 39) {
            System.out.println("pressed right arrow");
            chick.dx = Math.abs(chick.dx);
        }

        if(e.getKeyCode() == 37){
            System.out.println("pressed left arrow");
            chick.dx = -Math.abs(chick.dx);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
// lets the control when the turtle is in screen or not using the mouse(when mouse enters, the turtle is there, when the mouse exits, the turtle isn't there)
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("turtle entered");
        turtle.isAlive = true;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        turtle.isAlive = false;


    }
}