package lib;
import java.util.ArrayList;

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
    public static int diameter(int Δx, int Δy) {
        return (int) Math.pow((Math.pow(Δx, 2)+Math.pow(Δy, 2)), 0.5);
    }
    public static double atan3(double x, double y) {
        if(x==0 && y==0) {
            return 0;
        } else if(x<0&&y==0){
            return -1*PI;
        }else if(x>0&&y==0){
            return PI;
        }else if (x==0 && y<0) {
            return -1* PI;
        } else if (x==0 && y>0) {
            return 1* PI;
        } else if(x<0&&y<0) {
            return -Math.atan(x/y);
        } else {
            return Math.atan(x/y);
        }
    }
}
