package lib;
import java.util.ArrayList;
import java.awt.*;
import java.math.BigDecimal;

public class Simulation {

    int PARTICLE_SIZE = 30;//particle diameter size in pixels
    int WINDOW_HEIGHT = 800;
    int WINDOW_WIDTH  = 1600;

    ArrayList<Particle> particles = new ArrayList<Particle>();
    Turtle turtle;

    public Simulation(){
        this.turtle = new Turtle();
        Turtle.setCanvasSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.turtle.speed(0);
        this.turtle.hide();
        Turtle.refreshMode(2);  //refrsh screen with #update() every frame
    }

    public void addParticle(Particle newParticle){
        particles.add(newParticle);
    }

    public void update(){
        turtle.clear();
        stayInField();
        checkCollisions();
        // System.out.print(particles.get(0).xSpeed);System.out.print(", ");System.out.println(particles.get(1).xSpeed);
        for(int i=0; i<particles.size(); i++){
            // System.out.println("update");
            updatePosition(particles.get(i));
        }
        for(int i=0; i<particles.size(); i++){
            turtle.up();
            turtle.setPosition(particles.get(i).xPos, particles.get(i).yPos);
            turtle.down();
            turtle.dot(Color.black, PARTICLE_SIZE);
            // turtle.forward(1);
            turtle.up();
        }
        Turtle.update();
    }

    public void updatePosition(Particle particle){
        // System.out.println("update");
        particle.xPos += particle.xSpeed;
        particle.yPos += particle.ySpeed;
    }
    public void stayInField(){
        for(int i=0; i<particles.size(); i++){
            //collision with x border
            if((particles.get(i).xPos+0.5*PARTICLE_SIZE>0.5*WINDOW_WIDTH) || (particles.get(i).xPos-0.5*PARTICLE_SIZE<-0.5*WINDOW_WIDTH)){
                particles.get(i).xSpeed *= -1;
            }
            //collision with y border
            if((particles.get(i).yPos+0.5*PARTICLE_SIZE>0.5*WINDOW_HEIGHT) || (particles.get(i).yPos+0.5*PARTICLE_SIZE<-0.5*WINDOW_HEIGHT)){
                particles.get(i).ySpeed*=-1;
            }
        }
    }
    public void checkCollisions() {
        for(int i=0; i<particles.size(); i++) {
            for(int j=0; j<particles.size(); j++) {
                if(i<j){//Matthesiat algorithm: (//TODO: load on website, link it here)
                    int distance = (int) Math.pow((Math.pow(particles.get(i).xPos-particles.get(j).xPos, 2)+Math.pow(particles.get(i).yPos-particles.get(j).yPos, 2)), 0.5);
                    if(distance<=2*PARTICLE_SIZE) {
                        System.out.println("distance: "+distance);
                        collide(particles.get(i), particles.get(j));
                    }
                }
            }
        }
    }
    //TODO: ISSUE collide doesn't work with particles out speed
    public void collide(Particle particle_0, Particle particle_1){
        if(particle_0.xSpeed==0) { particle_0.xSpeed=0;}
        if(particle_1.xSpeed==0) { particle_1.xSpeed=0;}
        System.out.println("----------------- collision -----------------");
        int alpha    = (int) Math.atan2(particle_0.xSpeed, particle_0.ySpeed);
        int alphaSS  = (int) Math.atan2(particle_0.xSpeed-particle_1.xSpeed, particle_0.ySpeed-particle_1.ySpeed);
        int alphaS   = (int) alpha - alphaSS;
        int angle    = (int) alphaS;
        int V_s      = (int) Math.pow((Math.pow(particle_0.xSpeed,2)+Math.pow(particle_0.ySpeed, 2)), 0.5);
        int s        = (int) Math.pow(V_s*V_s-(Math.sin(alphaS)*V_s)*(Math.sin(alphaS)*V_s), 0.5);
        int fullRel  = (int) Math.pow(Math.pow(particle_0.xSpeed-particle_1.xSpeed, 2)+Math.pow(particle_0.ySpeed-particle_1.ySpeed, 2), 0.5);
        int o        = (int) fullRel -s;
        if(particle_0.xSpeed==0 && particle_0.ySpeed==0){s=0;System.out.println(">Debug_00");}
        if(particle_1.xSpeed==0 && particle_1.ySpeed==0){o=0;System.out.println(">Debug_01");}
        if(particle_0.xSpeed==0){ o= (int) Math.sqrt(particle_1.xSpeed^2+particle_1.ySpeed^2); s=0;System.out.println(">Debug_02");}
        if(particle_1.xSpeed==0){ o= 0; s=(int) Math.sqrt(particle_0.xSpeed^2+particle_0.ySpeed^2);System.out.println(">Debug_03");}

        //s is the relative vector of p_0
        //now switch s to p_1 and o(rel Vec of p_1) to p_0
        System.out.println("particle_0: yS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: yS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);

        //subtract old relative velocity
        particle_0.addSpeedFromAngle(-s, angle);
        particle_1.addSpeedFromAngle(o, angle);
        //add the opposite's speed
        particle_0.addSpeedFromAngle(-o, angle);
        particle_1.addSpeedFromAngle(s, angle);

        // System.out.println("Energie im System: ")

        //DEBUGGING_____________________________________________________________________
        System.out.println("angle: "+angle);
        System.out.println("V_s: "+V_s);
        System.out.println("s: "+s);
        System.out.println("o: "+o);
        System.out.println("fullRel: "+fullRel);
    
        // particle_0.xSpeed *= -1;
        // particle_0.ySpeed *= -1;
        // particle_1.xSpeed *= -1;
        // particle_1.ySpeed *= -1;
    }
}