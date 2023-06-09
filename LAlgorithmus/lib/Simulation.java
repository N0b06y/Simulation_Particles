package lib;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.*;

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
        Turtle.refreshMode(2);
    }

    // Scenarios
    public void simple00() throws InterruptedException {
        Particle josh = new Particle(0, 0,0,0);
        Particle jonathan = new Particle(100,0,-5,0, 2);
        Simulation simulation=new Simulation();
        simulation.addParticle(josh);
        simulation.addParticle(jonathan);
        while(true) {
            simulation.update();
            TimeUnit.MICROSECONDS.sleep(100);
        }
    }

    //Tests
    public void testSimple00() {
        Particle particle_test0 = new Particle(-100, 0,0,0);
        Particle particle_test1 = new Particle(100,0,-5,0);
        Simulation simulation=new Simulation();
        collide(particle_test0, particle_test1);
        System.out.println("Particle_0: T_0| 0, t_1| "+ particle_test0.xSpeed);
        System.out.println("Particle_1: T_0|-1, t_1| "+ particle_test1.xSpeed);
    }

    public void addParticle(Particle newParticle){
        particles.add(newParticle);
    }
    public void update(){
        turtle.clear();
        stayInField();
        checkCollisions();
        //Particles_________________________
        for (Particle particle : particles) {
            updatePosition(particle);
            particle.printSpeeds();
        }
        for (Particle particle : particles) {
            turtle.up();
            turtle.setPosition(particle.xPos, particle.yPos);
            turtle.down();
            turtle.dot(Color.black, PARTICLE_SIZE);
            turtle.up();
        }
        Turtle.update();
    }
    public void updatePosition(Particle particle){
        particle.xPos += particle.xSpeed;
        particle.yPos += particle.ySpeed;
    }
    public void stayInField(){
        for (Particle particle : particles) {
            //collision with x border
            if ((particle.xPos + 0.5 * PARTICLE_SIZE > 0.5 * WINDOW_WIDTH) || (particle.xPos - 0.5 * PARTICLE_SIZE < -0.5 * WINDOW_WIDTH)) {
                particle.xSpeed *= -1;
            }
            //collision with y border
            if ((particle.yPos + 0.5 * PARTICLE_SIZE > 0.5 * WINDOW_HEIGHT) || (particle.yPos + 0.5 * PARTICLE_SIZE < -0.5 * WINDOW_HEIGHT)) {
                particle.ySpeed *= -1;
            }
        }
    }
    public void checkCollisions() {
        for(int i=0; i<particles.size(); i++) {
            for(int j=0; j<particles.size(); j++) {
                if(i<j){//Matthesiat algorithm
                    int distance = (int) pow((pow(particles.get(i).xPos-particles.get(j).xPos, 2)+ pow(particles.get(i).yPos-particles.get(j).yPos, 2)), 0.5);
                    if(distance<=PARTICLE_SIZE) {
                        System.out.println("distance: "+distance);
                        collide(particles.get(i), particles.get(j));
                    }
                }
            }
        }
    }
    public static void collide(Particle particle_0, Particle particle_1){
        double energie=Meth.diameter(particle_0.xSpeed, particle_0.ySpeed)+Meth.diameter(particle_1.xSpeed, particle_1.ySpeed);
        double a    =  atan2(particle_0.ySpeed, particle_0.xSpeed);
        double aSS  = atan2(particle_0.ySpeed-particle_1.ySpeed, particle_0.xSpeed-particle_1.xSpeed);
        double aS   = a - aSS;
        double V_s      =  sqrt((pow(particle_0.xSpeed,2)+ pow(particle_0.ySpeed, 2)));
        double s        =  sqrt(V_s*V_s-(sin(aS)*V_s)*(sin(aS)*V_s));
        double fullRel  =  pow(pow(particle_0.xSpeed-particle_1.xSpeed, 2)+ pow(particle_0.ySpeed-particle_1.ySpeed, 2), 0.5);
        double other        =  (fullRel -s);//think of 'other' being watched inventively

        //s is the relative vector of p_0
        //now switch s to p_1 and o(rel Vec of p_1) to p_0
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);
        //all speeds are already calculated, now multiplied with mass to get the impact
        double self_impact = s* particle_0.mass;
        double other_impact = other* particle_1.mass;
        //subtract old relative velocity
        particle_0.subSpeedFromAngle(self_impact, aSS);
        particle_1.subSpeedFromAngle(-other_impact, aSS);//o now two times inverted, works with one x value
        System.out.println("SET relSPEED TO 0");
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);
        //add the opposite's speed
        particle_0.addSpeedFromAngle(-other_impact, aSS);
        particle_1.addSpeedFromAngle(self_impact, aSS);
        int energy = 0;
        System.out.println("Final results");
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);

        energie=((particle_0.mass+particle_1.mass)/2)*Meth.diameter(particle_0.xSpeed, particle_0.ySpeed)+Meth.diameter(particle_1.xSpeed, particle_1.ySpeed);
        System.out.println("Energy:"+energie);
    }
}