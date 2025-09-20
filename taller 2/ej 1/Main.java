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
    public static ArrayList<Long> costos = new ArrayList<Long>();
    public static Long INF = Long.MAX_VALUE; 
    public static Long [][] memo = null;
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
        memo = new Long[n][2];


        for (int i = 0; i < n; i++) {
            costos.add(scanner.nextLong());
        }
        //saltea al final de la linea de los numeros
        scanner.nextLine();

        for (int i = 0; i < n; i++){
            StringBuilder input = new StringBuilder(scanner.nextLine());
            strings.add(new StringBuilder(input));
            stringsInvertidos.add(new StringBuilder(input).reverse());
        }
        //hasta aca tenemos la lista con todos los strings que se van a usar
        
        long ans =Math.min(alf((n-1), 0),alf((n-1), 1));
        if (ans>=INF){
            printer.print(-1);
            printer.flush();
        }else{
            printer.print(ans);
            printer.flush();
        }
    }

    public static Long alf(Integer  i,Integer estado) {
        if((memo[i][estado])!=null){
            return memo[i][estado];
        }
        
        
        if(i==0){
            if(estado==0){
                return (long) 0;
            }
            else{
                return (long) costos.get(0);
            }
        }

        Long mejor = INF;
        String prev = strings.get(i-1).toString();
        String prevInv = stringsInvertidos.get(i-1).toString();
        String actual = strings.get(i).toString();
        String actualInv = stringsInvertidos.get(i).toString();

        if(estado ==0){
            //si el compareto da <=0 quiere decir que es lexicograficamente meno el primero, 0 si son iguales, >0 si el segundo es mas chico (caso q no queres)  
            if(((prev).compareTo(actual))<=0){
                mejor= Math.min(mejor,alf((i-1),0));
            }
            if(((prevInv).compareTo(actual))<=0){
                mejor= Math.min(mejor,alf((i-1),1));
            }
        }else{
            if(((prev).compareTo(actualInv))<=0){
                mejor= Math.min(mejor,alf((i-1),0)+costos.get(i));
            }
            if(((prevInv).compareTo(actualInv))<=0){
                mejor= Math.min(mejor,alf((i-1),1)+costos.get(i));
            }
        }

        memo[i][estado] = mejor;
        return mejor;
    }

    //usar string builder para rotar arrays
    //para estado (invertido o no) es conv usar un numero en vez de un bool para la matriz
    // dp[i][estado]= costo min de ordenar correctamente hasta la pos i dejando i en el estado 1 o 0
    //1=inv; 0=normal
    //la dp es n *2 = O(N)

    
}
   