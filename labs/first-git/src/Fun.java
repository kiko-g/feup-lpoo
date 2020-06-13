import java.util.Vector;

public class Fun
{
   public void hello() { System.out.println("Hello, World!\n"); }


   public Vector<Integer> getPrimes(int n) {
      Vector<Integer> V = new Vector<>();
      for(int i=1; i<n; i++) if(isPrime(i)) V.add(i);
      return V;
   }


   private boolean isPrime(int number) {
      for(int i=2; i<=number/2; i++) 
        if(number % i == 0) return false;

      return true;
   }
   
}
