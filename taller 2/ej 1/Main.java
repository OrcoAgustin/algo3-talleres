import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //creo las var como static para poder llamarlas de cualq lado
    public static ArrayList<StringBuilder> strings = new ArrayList<StringBuilder>();
    public static ArrayList<StringBuilder> stringsInvertidos = new ArrayList<StringBuilder>();
    public static ArrayList<Integer> costos = new ArrayList<Integer>();
    public static Integer[][] memo = null;
    public static int n = 0;
    
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

        //inputs
        n = scanner.nextInt();
        //inicializar memo 
        memo = new Integer[n][2];


        for (int i = 0; i < n; i++) {
            costos.add(scanner.nextInt());
        }
        //saltea al final de la linea de los numeros
        scanner.nextLine();

        for (int i = 0; i < n; i++){
            StringBuilder input = new StringBuilder(scanner.nextLine());
            strings.add(new StringBuilder(input));
            stringsInvertidos.add(new StringBuilder(input).reverse());
        }

        //hasta aca tenemos la lista con todos los strings que se van a usar
        
    }

    public static Integer alf(int i,int  estado) {
        if(i==n){
            if(estado==0){
                return 0;
            }
            else{
                return costos.get(i-1);
            }
        }
        if((memo[i][estado])!=null){
            return memo[i][estado];
        }

        




        return 0;
    }

    //usar string builder para rotar arrays
    //para estado (invertido o no) es conv usar un numero en vez de un bool para la matriz
    //1=inv; 0=normal


    //PENSA LA F REC NO SEAS VAGO
}
   