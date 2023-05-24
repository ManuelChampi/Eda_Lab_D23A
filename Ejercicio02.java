import java.util.Arrays;
public class Ejercicio02 {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int d = 2;
        int[] Aiz = rotarIzquierdaArray(A, d);
        
        System.out.println("A = " + Arrays.toString(A));
        System.out.println("Aiz = " + Arrays.toString(Aiz));
    }
    
    public static int[] rotarIzquierdaArray(int[] A, int d) {
        int n = A.length;
        int[] Aiz = new int[n];
        
        for (int i = 0; i < n; i++) {
            int rotar = (i + d)% n;
            Aiz[i] = A[rotar];
        }
        
        return Aiz;
    }
}
/*
run:
A = [1, 2, 3, 4, 5]
Aiz = [3, 4, 5, 1, 2]
BUILD SUCCESSFUL (total time: 0 seconds)
*/
