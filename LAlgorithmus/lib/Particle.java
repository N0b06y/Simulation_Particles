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
    public void addSpeedFromAngle(int speed, int angle) {
        this.xSpeed +=   (int) (Math.cos(angle) *speed);
        this.ySpeed +=   (int) (Math.sin(angle) *speed);
    }
}
