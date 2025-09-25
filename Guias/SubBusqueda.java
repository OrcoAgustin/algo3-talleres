import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//EJERCICIO 15 GUIA 1
public class SubBusqueda {
    
    //esta puesto del 1 al 50, pero modificar array a gusto
    public static ArrayList<Integer> A = new ArrayList<>(Arrays.asList(
        50, 3, 14, 20, 28, 5, 32, 9, 29, 31, 
        15, 45, 41, 19, 1, 26, 37, 44, 10, 49, 
        36, 11, 27, 47, 2, 6, 7, 38, 18, 35, 
        25, 30, 16, 17, 24, 43, 48, 13, 4, 34, 
        46, 40, 42, 22, 8, 12, 33, 23, 39, 21
        ));

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

        printer.println("Ingrese numero a buscar entre 1 y 50");
        printer.flush();
        Integer e = scanner.nextInt();

        printer.print(ubicar(0, A.size()-1, e));
        printer.flush();
    

    }

    static public Integer ubicar (Integer l, Integer r, Integer e){
        if (l==r){
            return r;
        }
        else{
            Integer mid = (r+l)/2;
            if(aparece(l, mid, e)){
                return ubicar(l, mid, e);
            }
            else{
                return ubicar(mid+1, r, e);
            }
        }      
    }

    //la implementacion no es como el ej pero sirve para el caso
    static public boolean aparece(Integer l, Integer r, Integer e){
        Boolean res = false;
        for (int i = l; i <= r; i++) {
            if (A.get(i).equals(e)){
                res = true;
            }
        }
        return res;
    }


}
