import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.PriorityQueue;
//import java.util.Comparator;


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


        int[][] matrizDeAdyacencia = new int[n][n];

        //indice es planeta-1 y los valores son los momentos  donde esta ocupado
        ArrayList<Integer>[] matrizDeConflictos = new ArrayList[n];

        for (int i = 0; i < m; i++) {
            int primerCoord = scanner.nextInt()-1;
            int segundaCoord = scanner.nextInt()-1;

            matrizDeAdyacencia[primerCoord][segundaCoord] = scanner.nextInt();
        }
    
        //conflictos
        for (int i = 0; i < n; i++) {
            ArrayList<Integer>  nuevaLinea = new ArrayList<>();

            int cantidadDeConflictos = scanner.nextInt();
            for (int j = 0; j < cantidadDeConflictos ; j++) {
                int nuevoNum = scanner.nextInt();
                nuevaLinea.add(nuevoNum);  
              
            }

            matrizDeConflictos[i]=nuevaLinea;
        }


        printer.println(pasosHastaM(matrizDeAdyacencia, matrizDeConflictos));
        
        //print de adyacencia
        printer.println("la matriz de adyacencia se ve asi:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                printer.print(matrizDeAdyacencia[i][j]+" ");
            }
            printer.println();
        }
        printer.flush();

    }

    public static int pasosHastaM(int[][] matrizDeAdyacencia, ArrayList<Integer>[] matrizDeConflictos){
        //dijkstra v2 (echu's dream)
        //proba si pasa con matriz, sino sufrimos con la queue
        
        for (int i = 0; i < matrizDeAdyacencia.length; i++) {
            for (int j = 0; j < matrizDeAdyacencia.length; j++) {
                if (matrizDeAdyacencia[i][j] == 0 && i != j){
                    matrizDeAdyacencia[i][j] = 10001;
                }
            }
        }
        ArrayList<Integer> visitados = new ArrayList<>();
        
        Integer raiz = matrizDeAdyacencia[0][0];
        visitados.add(raiz);

        


        return 0;
    }
    






    

    
}







/* printer de input
        printer.println("n es: " + n);
        printer.println("m es: " + m);
        printer.flush();


        //print de adyacencia
        printer.println("la matriz de adyacencia se ve asi:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                printer.print(matrizDeAdyacencia[i][j]+" ");
            }
            printer.println();
        }
        printer.flush();


        //print
        printer.println("la matriz de conflictos se ve asi:");
        for (int i = 0; i < matrizDeConflictos.length; i++) {
            printer.print("i = " + i + ":");
            
            ArrayList linea=matrizDeConflictos[i];

            if(!linea.isEmpty()){
                for (int j = 0; j < linea.size(); j++) {
                    printer.print(linea.get(j) + " ");
                }
            }else{
                printer.print(0);
            }
            printer.println();
        }
        printer.flush();

        */