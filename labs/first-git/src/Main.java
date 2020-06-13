import java.util.Vector;

public class Main
{
    public static Format format = new Format();
    public static void main(String[] args)
    {
        format.start();

        Fun f = new Fun();
        Vector<Integer> V = f.getPrimes(10); //get primes until n
        for(int value : V) System.out.println(value);

        format.end();
    }
}
