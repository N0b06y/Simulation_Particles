import lib.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static lib.Test.*;

public class LAlgorithmus {

    public String scan(String call){
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println(call);
        input=scanner.nextLine();
        scanner.close();
        return input;
    }
    public static int scanInt(String msg, Scanner scanner) throws InputMismatchException{
        int input=0;
        System.out.println(msg);
        System.out.println("please answer in integer type only");
        // TimeUnit.SECONDS.sleep(10);
        input=scanner.nextInt();
        return input;
    }
    public static void main(String[] args) throws Exception {   //TODO: function for calc runtimes
//Math
        // String name = scan("name");
        // System.out.println("Name:"+name);
        // Friend first = new Friend(name, 17);
        // first.printValues(); 
        // Meth Promethium = new Meth();
        // Promethium.primeNumbers(100);
//Simulation
        //TODO: delete all redundant comments
        Simulation simulation = new Simulation();
        simulation.simple00();
        //simulation.testSimple00();
        //simulationTest();
//Graph
        /*
        Scanner scanner = new Scanner(System.in);

        int input = 0;
        ArrayList<Integer> storage = new ArrayList<Integer>();
        storage.add(0);
        input=scanInt("enter next value or exit with 666", scanner);
        while(input!=666){
            if(input!=storage.get(storage.size()-1)){
                storage.add(input);
            }
            input = scanInt("enter next value or exit with 666", scanner);
        }
        Turtle turtle = new Turtle();
        int width = 1600;
        int height = 800;
        Turtle.setCanvasSize(width, height);
        int xStretch = width/storage.size();
        int STRETCH_Y = 1;
        turtle.speed(0);
        Turtle.refreshMode(2);
        turtle.setPosition(0.5*width,0);
        turtle.setPosition(-0.5*width, 0);
        System.out.print("Storage:");
        System.out.println(storage);
        System.out.println("storage.size():");
        System.out.println(storage.size());
        System.out.println("xStretch:");
        System.out.println(xStretch);
        System.out.println("STRETCH_Y:");
        System.out.println(STRETCH_Y);
        for(int i=0; i<storage.size(); i++){
            // turtle.up()
            turtle.setPosition(i*xStretch-0.5*width, storage.get(i)*STRETCH_Y);
            turtle.down();
            System.out.println(i*xStretch-0.5*width);
            System.out.println(storage.get(i)*STRETCH_Y);
        }
         */
    }
}