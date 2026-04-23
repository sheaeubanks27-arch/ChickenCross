import java.awt.*;

public class Chicken {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;             //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public Chicken(){
        xpos = 100;
        ypos = 60;
        width = 20;
        height = 20;


    }

    public void move(){}


}
