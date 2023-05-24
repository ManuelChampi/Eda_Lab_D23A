public class Ejercicio03 {
    public static void main(String[] args) {
        int b = 5;
        trianguloRecursivo(b);
    }
    
    public static void trianguloRecursivo(int b) {
        if (b > 0) {
            trianguloRecursivo(b - 1);
            for (int i = 0; i < b; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

/*
run:
*
**
***
****
*****
BUILD SUCCESSFUL (total time: 0 seconds)
*/
