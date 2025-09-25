import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

//EJERCICIO 13 GUIA 1
public class MergeSelectivo{
    
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

        printer.println("Ingrese I");
        printer.flush();

        Integer I = scanner.nextInt();

        printer.print("El valor en la posicion " + I + " es " +iesimoMerge(I) );
        printer.flush();
    }


    public static Integer iesimoMerge(Integer i){
        // primero sobre A
        Integer izquierdaA = 0;
        Integer derechaA = A.size()-1;

        while (izquierdaA <= derechaA){
            Integer mid = (izquierdaA + derechaA) / 2;
            Integer pos = contar(B, A.get(mid)) + (mid + 1);

            if(pos.equals(i)){
                return A.get(mid);
            }
            else if(pos < i ){
                izquierdaA = mid + 1;
            }
            else{
                derechaA = mid - 1; // ojo acá
            }
        }

        // si no lo encontramos en A, buscamos sobre B
        Integer izquierdaB = 0;
        Integer derechaB = B.size()-1;

        while (izquierdaB <= derechaB){
            Integer mid = (izquierdaB + derechaB) / 2;
            Integer pos = contar(A, B.get(mid)) + (mid + 1);

            if(pos.equals(i)){
                return B.get(mid);
            }            
            else if(pos < i ){
                izquierdaB = mid + 1;
            }
            else{
                derechaB = mid - 1; // ojo acá también
            }        
        }
        
        return -1;
    }


    public static Integer contar(ArrayList<Integer> lista, Integer obj){
        Integer res= 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)<= obj){
                res += 1;
            }else{
                return res;
            }     
        }
        return res;
    }

}
