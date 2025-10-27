import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        printer.print(bfsGenMod(n, m, printer));
        printer.flush();


    }

    public static int bfsGenMod(int raiz , int lim, PrintWriter printer) {
        boolean alcanzado = false;

        ArrayList<ArrayList<Integer>> arbol = new ArrayList<>();
        arbol.add(new ArrayList<>());
        arbol.get(0).add(raiz);
        //agregamos raiz al arbol

        //crea arbol entero
        int nivel = 0;
        while (!alcanzado){
            ArrayList<Integer> nuevoNivel = new ArrayList<>();
            for (int i = 0; i < (arbol.get(nivel)).size(); i++) {
                
                Integer nodo = arbol.get(nivel).get(i);
        
                nuevoNivel.add(nodo*2);
                if ((nodo-1)>=1){
                    nuevoNivel.add(nodo-1);
                }

                //se alcanzo?
                if (nodo*2 == lim || nodo-1 == lim){
                    alcanzado=true;
                }
            }   
            
            arbol.add(nuevoNivel);
            nivel+=1;
        }
        
        /* 
        //print
        for (int idx = 0; idx < arbol.size(); idx++) {
            for (int i = 0; i < arbol.get(idx).size(); i++) {
                printer.print(arbol.get(idx).get(i));
                printer.print(" ");
            }
            printer.println();
        }
        printer.flush();
        

        printer.print("el nivel donde se encontro fue: " + nivel);
        printer.flush();
        */
        return nivel-1;  
        
    }
}