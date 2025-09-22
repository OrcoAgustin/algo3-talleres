import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static Integer [][] memo = null; //tiene l y r para determinar de donde me conviene acceder
  
    
    public static void main ( String [] args ) {
        //lector de entrada
        BufferedReader br = new BufferedReader( 
            new InputStreamReader(System.in)
        );
        //lector de salida
        BufferedWriter bw = new BufferedWriter (
            new OutputStreamWriter ( System . out )
        );
        //scanner
        Scanner scanner = new Scanner(br);
        //printer
        PrintWriter printer = new PrintWriter(bw);

        //datos
        Integer n = scanner.nextInt();
        String s = scanner.next();

        //incializo memo
        memo = new Integer[n][n];
       
        printer.print(contar(s, 0, n-1));
        printer.flush();
    }

    public static int contar (String s, int l , int r){
        //memo  
        if(memo[l][r] != null){
            return memo[l][r];
        }
        
        //casos bases
        if(l > r){
            return 0;
        }
        if(l == r){
            return 1;
        }

        //rec
        //borro s[l]   
        int res = 1 + contar(s, (l+1), r);

        //busco si se puede combinar    
        for (int i = l+1; i <=r; i++) {
            if(s.charAt(i)==s.charAt(l)){
                res = Math.min(res, contar(s,(l+1),(i-1)) + contar(s, i, r));
            }
        }
        memo[l][r] = res;
        return res;
    }

    //borrar el string no sirve, asi que tenes que o copiarlo en cada paso, o encontrar la forma de no borrarlo
    //es imposible hacerlo en tiempo sin algun borde o algo
    //agrego l y r
    //fijate post almuerzo si va bien

}