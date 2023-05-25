package lib;

import static java.lang.Math.*;

public class Particle {
    public int xPos;
    public int yPos;
    public int xSpeed;
    public int ySpeed;
    public Particle(int xPos, int yPos, int xSpeed, int ySpeed){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void addSpeedFromAngle(double speed, double angle) {
        //be confused! I think it's because the view is turned by 180 degrees
        // while(angle<0){angle+=2*PI;}
        //System.out.println(">"+speed);
        this.xSpeed  +=   round(cos(angle) *speed);
        //System.out.println("- xSpeed+"+(cos(angle) *speed)+"="+this.xSpeed);
        this.ySpeed  +=   round((sin(angle) *speed));
        //System.out.println("- ySpeed+"+(sin(angle) *speed)+"="+this.ySpeed);
    }
    public void subSpeedFromAngle(double speed, double angle) {
        //System.out.println(">"+(speed));
        this.xSpeed  -=   round(cos(angle) *speed);
        //System.out.println("- xSpeed-"+(cos(angle) *speed)+"="+this.xSpeed);
        this.ySpeed  -=   round((sin(angle) *speed));
        //System.out.println("- ySpeed-"+(sin(angle) *speed)+"="+this.ySpeed);
    }
    public void resetSpeed() {
        this.xSpeed=0;
        this.ySpeed=0;
    }
    public void printSpeeds() {
        System.out.println(this.xSpeed+", "+this.ySpeed);
    }
}
