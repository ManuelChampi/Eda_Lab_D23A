import java.util.Arrays;
public class Ejercicio01 {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int[] Ain = invertirArray(A);
        
        System.out.println("A = " + Arrays.toString(A));
        System.out.println("Ain = " + Arrays.toString(Ain));
    }
    
    public static int[] invertirArray(int[] A) {
        int n = A.length;
        int[] Ain = new int[n];
        
        for (int i = 0; i < n; i++) {
            Ain[i] = A[n - 1 - i];
        }
        
        return Ain;
    }
}
