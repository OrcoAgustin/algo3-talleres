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

        //1ro salida, 2do llegada, 3ro w
        ArrayList<ArrayList<Integer>> matrizDeAristas = new ArrayList<>();
        //indice es planeta y los valores son los minutos donde esta ocupado
        ArrayList<ArrayList<Integer>> matrizDeConflictos = new ArrayList<>();
        

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        

        printer.println("n es: " + n);
        printer.println("m es: " + m);
        printer.flush();

        for (int i = 0; i < m; i++) {
            ArrayList<Integer>  nuevaArista = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                nuevaArista.add(scanner.nextInt());    
            }
            
            matrizDeAristas.add(nuevaArista);
        }

        //print
        printer.println("la matriz de aristas se ve asi:");
        for (int i = 0; i < matrizDeAristas.size(); i++) {
            for (int j = 0; j < matrizDeAristas.get(i).size(); j++) {
                printer.print((matrizDeAristas.get(i)).get(j)+" ");
            }
            printer.println();
        }
        printer.flush();


        //conflictos
        for (int i = 0; i < n; i++) {
            ArrayList<Integer>  nuevaLinea = new ArrayList<>();

            for (int j = 0; j < scanner.nextInt(); j++) {
                int nuevoNum = scanner.nextInt();
                nuevaLinea.add(nuevoNum);  
              
            }

            matrizDeConflictos.add(nuevaLinea);
        }

        //print
        printer.println("la matriz de conflictos se ve asi:");
        for (int i = 0; i < matrizDeConflictos.size(); i++) {
            printer.print("i = " + i + " ");
            
            ArrayList linea=matrizDeConflictos.get(i);

            if(linea.size()!=0){
                for (int j = 0; j < linea.size(); j++) {
                    printer.print(linea.get(j) + " ");
                }
            }else{
                printer.print(0);
            }
            printer.println();
        }
        printer.flush();
    }
    
}
