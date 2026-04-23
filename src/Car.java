import java.awt.*;

public class Car {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;             //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;


    public Car() {
        xpos = 10;
        ypos = 20;
        width = 40;
        height = 30;

        //dy=0;
        //cars = new Car[3];

    }

    public void move() {
        if (xpos >= 1000) {//wrap when hits right wall
            xpos = 1;

        }

        if (xpos <= 0) {//wrap when hits left wall
            xpos = 999;

        }

        if (ypos >= 700) {//wrap when hits bottom wall
            ypos = 1;
        }

        if (ypos < 0) {//wrap when hits top wall
            ypos = 699 - height;
        }


    }
}
