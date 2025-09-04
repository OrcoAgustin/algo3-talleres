import java.io.*;
import java.util.*;

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
    
        ArrayList<Long> lista = new ArrayList<>();

        lista.add(scanner.nextLong()); //n
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        if(lista.get(0)!=0){
        //operamos y devuelve lista "limpia"
        lista = operarLista(lista,printer);

        //contamos
        printer.print(contar(lista, (l-1), r));
        printer.flush();
        }
        else{
            //ahorra tiempo en caso borde
            printer.print(0);
            printer.flush();
        }
    }

    public static ArrayList<Long> operarLista (ArrayList<Long> lista, PrintWriter printer){
        boolean cambio = true;
        while(cambio){
            cambio = false;
        
            int i=0;
            while(i<lista.size()){
                Long valor = lista.get(i);

                if (valor>1){
                    lista.remove(i);
                    lista.add(i, (valor/2));    //entero 1
                    lista.add((i+1), (valor%2)); //resto
                    lista.add((i+2), (valor/2));    //entero 2
                    i+=3;
                    cambio = true;
                }
                else{
                    i++;
                }
            }
        }
        return lista;        
    }

    public static Integer contar(ArrayList<Long> lista, Integer l, Integer r) {
        int suma = 0;

        for (int i = l; i<r; i++ ){
            suma += lista.get(i);
        }
        return suma;
    }
}
