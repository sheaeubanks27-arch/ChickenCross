import java.awt.*;

public class Turtle {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;             //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public Turtle(){
        width=50;
        height=50;
        isAlive = true;
       //gives random x and y position
        xpos = (int)(Math.random()*1000) + 1;
        ypos = (int)(Math.random()*700) +1 ;
        dx=7;
        dy=8;
    }

    public void move() {
        if(xpos >= 1000-width){//bounce off the right wall
            dx = -dx;

        }


        if(xpos <=0){//bounce off the left wall
            dx = -dx;
        }


        if(ypos >= 700-height){//bounce off the bottom wall
            dy = -dy;
        }

        if(ypos <=0){//bounce off the top wall
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}
