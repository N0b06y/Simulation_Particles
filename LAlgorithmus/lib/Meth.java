package lib;
import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Meth {
    public ArrayList<Integer> primeNumbers(int border){
        ArrayList<Integer> primes = new ArrayList<Integer>();
        ArrayList<Boolean> matrix = new ArrayList<Boolean>();
        for(int i=0; i<border; i++){
            matrix.add(true);
        }
        matrix.set(0, false);
        matrix.set(1, false);

        for(int i=0; i<border; i++){    //Sieb des ERATOTHENES
            if(matrix.get(i)){
                for(int j=0; i*j<border; j++){
                    matrix.set(i*j, false);
                }
                primes.add(i);
            }
        }
        System.out.println(primes);
        return primes;
    }
    public ArrayList<String> matthesiatAlgorithm(ArrayList<String> elements){
        //TODO: implement
        return elements;
    }
    public static int diameter(double Δx, double Δy) {
        return (int) pow((pow(Δx, 2)+ pow(Δy, 2)), 0.5);
    }
    public static double atan3(double x, double y) {//TODO: redundant, delete
        if(x==0 && y==0) {
            return 0;
        } else if(x<0&&y==0){
            return -1*PI;
        }else if(x>0&&y==0){
            return 0;
        }else if (x==0 && y<0) {
            return -1* PI/2;
        } else if (x==0 && y>0) {
            return 1* PI/2;
        } else if( x<0&&y>0) {//redundant
            //return -atan2(abs(x),abs(y));
            //return (PI)+atan2(y,x);
            return atan2(y,x);
        } else if(x>0&&y<0) {
            return atan2(y,x);
        }else {
            return atan2(y,x);
        }
    }
}
