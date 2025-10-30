import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
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
        
        printer.println(bfsDesdeRaiz(n, m ));
        printer.flush();
    }
    
    public static int bfsDesdeRaiz(int raiz, int lim){

        HashSet<Integer> memo = new HashSet<>();

        boolean alcanzado = false;

        ArrayList<Integer> prevNivel = new ArrayList<>();
        prevNivel.add(raiz);
        
        //agregamos raiz al arbol

        int nivel = 0;

        while (!alcanzado){
            ArrayList<Integer> nuevoNivel = new ArrayList<>();
            for (int i = 0; i < prevNivel.size(); i++) {
                
                Integer nodo = prevNivel.get(i);
                
                if(!memo.contains(i)){
                    boolean alcanzablePorDerecha = true;
                    if (nodo > lim){
                        alcanzablePorDerecha = false;
                    }

                    if(alcanzablePorDerecha){
                        nuevoNivel.add(nodo*2);
                    }
                    
                    if (nodo>1){
                        nuevoNivel.add(nodo-1);
                    }

                    //se alcanzo?
                    if (nodo*2 == lim || nodo-1 == lim){
                        alcanzado=true;
                    }
                }

                memo.add(nodo);
            }   
            
            prevNivel = nuevoNivel;
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
        

        printer.println("el nivel donde se encontro fue: " + nivel);
        printer.flush();
        */

        return nivel;
    }
}
