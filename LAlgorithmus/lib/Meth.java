package lib;
import java.util.ArrayList;
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
}
