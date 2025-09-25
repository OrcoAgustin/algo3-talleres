import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//EJERCICIO 14 GUIA 1
public class DiferenciaMinima {    

    public static ArrayList<Integer> A = new ArrayList<>();
    public static ArrayList<Integer> B = new ArrayList<>();

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

        printer.println("Ingrese lista A");
        printer.flush();

        String inputA = scanner.nextLine();
        inputA = inputA.replaceAll("\\[|\\]|\\s", "");
        for(String num : inputA.split(",")) A.add(Integer.parseInt(num));

        printer.println("Ingrese lista B");
        printer.flush();

        String inputB = scanner.nextLine();
        inputB = inputB.replaceAll("\\[|\\]|\\s", "");
        for(String num : inputB.split(",")) B.add(Integer.parseInt(num));

        Integer res = minDif(0, A.size()-1);
        printer.print("La diferencia minima es " + res);
        printer.flush();
    }

    public static Integer minDif(int l, int r){
        if (l == r){
            return Math.min(
                Math.abs(A.get(l) - B.get(l)),
                Math.abs(A.get(r) - B.get(r))
            );
        }
        else{
            Integer idx = (r + l)/2;

            if( Math.abs(A.get(idx)-B.get(idx)) > Math.abs(A.get(idx+1)-B.get(idx+1))){
                return minDif(idx+1, r);
            }
            else{
                return minDif(l, idx);
            }
        }
    }
}


