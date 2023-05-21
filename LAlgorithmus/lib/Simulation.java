package lib;

import java.util.ArrayList;
import java.awt.*;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.*;
import static lib.Meth.atan3;

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
        Turtle.refreshMode(2);  //refresh screen with #update() every frame
    }

    // Scenarios
    public void simple00() throws InterruptedException {
        Particle josh = new Particle(0, 0,0,0);
        Particle jonathan = new Particle(100,100,-5,-5);
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
        // System.out.print(particles.get(0).xSpeed);System.out.print(", ");System.out.println(particles.get(1).xSpeed);
        //Particles_________________________
        for (Particle particle : particles) {
            // System.out.println("update");
            updatePosition(particle);
            particle.printSpeeds();
        }
        for (Particle particle : particles) {
            turtle.up();
            turtle.setPosition(particle.xPos, particle.yPos);
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
                if(i<j){//Matthesiat algorithm: (//TODO: load on website, link it here)
                    int distance = (int) pow((pow(particles.get(i).xPos-particles.get(j).xPos, 2)+ pow(particles.get(i).yPos-particles.get(j).yPos, 2)), 0.5);
                    if(distance<=PARTICLE_SIZE) {
                        System.out.println("distance: "+distance);
                        collide(particles.get(i), particles.get(j));
                    }
                }
            }
        }
    }
    //TODO: ISSUE collide doesn't work with collisions with 45 degrees
    public static void collide(Particle particle_0, Particle particle_1){
        double energie=Meth.diameter(particle_0.xSpeed, particle_0.ySpeed)+Meth.diameter(particle_1.xSpeed, particle_1.ySpeed);
        System.out.println("Energy:"+energie);
        //if(particle_0.xSpeed==0) { particle_0.xSpeed=0;}
        //if(particle_1.xSpeed==0) { particle_1.xSpeed=0;}
        System.out.println("----------------- collision -----------------");
        System.out.println("particle_0: "+particle_0.xSpeed+", "+particle_0.ySpeed);
        System.out.println("particle_1: "+particle_1.xSpeed+", "+particle_1.ySpeed);
        double a    =  atan3(particle_0.xSpeed, particle_0.ySpeed);//
        System.out.println("Alpha: "+toDegrees(a));
        double aSS  = atan3(particle_0.xSpeed-particle_1.xSpeed, particle_0.ySpeed-particle_1.ySpeed);
        //aSS=PI;//TODO:^
        System.out.println(particle_0.xSpeed+particle_1.xSpeed);
        System.out.println(particle_0.ySpeed+particle_1.ySpeed);
        System.out.println("AlphaSS: "+toDegrees(aSS));
        double aS   = a - aSS;
        //aS=PI;//TODO:dont forget to delete
        System.out.println("AlphaS: "+toDegrees(aS));
        double V_s      =  sqrt((pow(particle_0.xSpeed,2)+ pow(particle_0.ySpeed, 2)));
        System.out.println("V_s: "+V_s);
        double s        =  sqrt(V_s*V_s-(sin(aS)*V_s)*(sin(aS)*V_s));
        System.out.println("s: "+s);
        double fullRel  =  pow(pow(particle_0.xSpeed-particle_1.xSpeed, 2)+ pow(particle_0.ySpeed-particle_1.ySpeed, 2), 0.5);
        System.out.println("fullRel: "+fullRel);
        double o        =  (fullRel -s);
        System.out.println("o: "+o);
        System.out.println("-----------------------------------------------------------------");
        //if(particle_0.xSpeed==0 && particle_0.ySpeed==0){s=0;System.out.println(">Debug_00");}
        //if(particle_1.xSpeed==0 && particle_1.ySpeed==0){o=0;System.out.println(">Debug_01");}
        //if(particle_0.xSpeed==0){ o= (int) Math.sqrt(particle_1.xSpeed^2+particle_1.ySpeed^2); s=0;System.out.println(">Debug_02");}
        //if(particle_1.xSpeed==0){ o= 0; s=(int) Math.sqrt(particle_0.xSpeed^2+particle_0.ySpeed^2);System.out.println(">Debug_03");}

        //s is the relative vector of p_0
        //now switch s to p_1 and o(rel Vec of p_1) to p_0
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);
        //subtract old relative velocity
        System.out.println("DEBUG_______________\no_0:"+o+"\no_1:"+(-o)+"\no_2:"+-(-o)+"\n/DEBUG________________");
        //TODO: dont forget
        o*=1;
        System.out.println("<"+o);
        particle_0.subSpeedFromAngle(s, aSS);
        particle_1.subSpeedFromAngle(o, aSS);
        System.out.println("SET relSPEED TO 0");
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);
        //add the opposite's speed
        particle_0.addSpeedFromAngle(o, aSS);//TODO:implement functionality: needed VZ of o in the cases
        particle_1.addSpeedFromAngle(s, aSS);
        int energy = 0;
        System.out.println("Final results");
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);

        energie=Meth.diameter(particle_0.xSpeed, particle_0.ySpeed)+Meth.diameter(particle_1.xSpeed, particle_1.ySpeed);
        System.out.println("Energy:"+energie);
        //DEBUGGING_____________________________________________________________________
        System.out.println("angle: "+ aS);
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