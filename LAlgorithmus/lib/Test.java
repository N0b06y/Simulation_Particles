package lib;

public class Test {
    public static void simulation() {
        Particle p0 = new Particle(0,0,0,0);
        Particle p1 = new Particle(0,0,-4,0);
        Simulation.collide(p0,p1);
    }
    /* DEBUG DATA
    * if x: 4, -4 -> override with negated values
    * if x: 0, -4 -> override with negated values
    * if 0:0,0;1:-4,-4 -> override p1 with negated values
     */
    /*
    * x: 0,-4:
        o*=-1;
        particle_0.addSpeedFromAngle(s, aSS);
        particle_1.addSpeedFromAngle(o, aSS);
        System.out.println("SET relSPEED TO 0");
        System.out.println("particle_0: xS="+particle_0.xSpeed+", yS="+particle_0.ySpeed);
        System.out.println("particle_1: xS="+particle_1.xSpeed+", yS="+particle_1.ySpeed);
        //add the opposite's speed
        particle_0.addSpeedFromAngle(-o, aSS);//TODO:implement functionality: needed VZ of o in the cases
        particle_1.addSpeedFromAngle(-s, aSS);
    * x: 4,-4;
        {same}
    * p0{0};p1{-4,-4):
        {out "o*=-1;"}
     */
}
