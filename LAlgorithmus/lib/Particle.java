package lib;

import static java.lang.Math.*;

public class Particle {
    public int xPos;
    public int yPos;
    public double xSpeed;
    public double ySpeed;
    public double mass;
    //Init without mass input (mass=1)
    public Particle(int _xPos, int _yPos, int _xSpeed, int _ySpeed){
        this.xPos = _xPos;
        this.yPos = _yPos;
        this.xSpeed = _xSpeed;
        this.ySpeed = _ySpeed;
        this.mass = 1;
    }
    //Init with mass input
    public Particle(int _xPos, int _yPos, int _xSpeed, int _ySpeed, float _mass){
        this.xPos = _xPos;
        this.yPos = _yPos;
        this.xSpeed = _xSpeed;
        this.ySpeed = _ySpeed;
        this.mass = _mass;
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
