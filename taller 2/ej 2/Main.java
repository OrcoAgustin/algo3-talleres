import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static Integer[][] memo = null; //tiene l y r para determinar de donde me conviene acceder
  
    
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

        Integer n = scanner.nextInt();
        String s = scanner.next();


    }

    public static Integer contar (String s ){
        //casos bases
        if (s.length()==0){
            return 0;   
        }
        if (s.length()==1){
            return 1;   
        }


        return 0;
    }

    //es imposible hacerlo en tiempo sin algun borde o algo
    //agregar l y r? pensalo post almuerzo
}
   