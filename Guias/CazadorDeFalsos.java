import clasesExtra.par;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class CazadorDeFalsos {
    public static ArrayList<ArrayList<Boolean>> matriz = new ArrayList<>();

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

        printer.println("Ingrese tamaño de la matriz:");
        printer.flush();
        int n = scanner.nextInt();

        
        // inicializar matriz aleatoria
        matriz.clear();
        ArrayList<par> falsos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Boolean> fila = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                Random random = new Random();
                boolean val = random.nextBoolean();
                if(val == false){
                    par<Integer, Integer> falso = new par<Integer,Integer>(i, j);
                    falsos.add(falso);
                } 
                fila.add(val);
            }
            matriz.add(fila);
        }

        // aseguramos al menos un false
        Random random1 = new Random();
        int falseRow = random1.nextInt(n);
        Random random2 = new Random();
        int falseCol = random2.nextInt(n);
        matriz.get(falseRow).set(falseCol, false);


        printer.println("los falsos estan en ");
        for (int i = 0; i < falsos.size()-1; i++) {
            printer.println(falsos.get(i).toString());
        } 
        printer.println("el programa lo encontro en " + encontrarF(0, n-1, 0, n-1));
        printer.flush();
    }

    public static par encontrarF (Integer i0 // izquierda fila
                                , Integer i1 // derecha fila
                                , Integer j0 // abajo col
                                , Integer j1 // arriba col
                                ){
        par res = new par<Integer,Integer>(0, 0);
        //caso base
        if(i0.equals(i1) && j0.equals(j1)){
            //no hace falta chequear que este aca xq el ej dice se asume que hay 1 al menos
            res.setFirst(i0);
            res.setSecond(j0);
            return res;
        }

        //rec divide en 4
        Integer midFila = (i0+i1)/2;
        Integer midCol = (j0+j1)/2;
        
        // cuadrante superior izq
        if (notConjuncionSubmatriz(i0, midFila, j0, midCol)) {
            return encontrarF(i0, midFila, j0, midCol);
        }

        // cuadrante superior der
        if (notConjuncionSubmatriz(i0, midFila, midCol + 1, j1)) {
            return encontrarF(i0, midFila, midCol + 1, j1);
        }

        // cuadrante inferior izq
        if (notConjuncionSubmatriz(midFila + 1, i1, j0, midCol)) {
            return encontrarF(midFila + 1, i1, j0, midCol);
        }

        // cuadrante inferior der
        return encontrarF(midFila + 1, i1, midCol + 1, j1);
    }

    public static boolean notConjuncionSubmatriz(Integer i // izquierda fila
                                , Integer j // derecha fila
                                , Integer k // abajo col
                                , Integer l // arriba col
                                ){
            for (int idx1 = i ; idx1 <= j; idx1++) {
                for (int idx2 = k; idx2 <= l; idx2++) {
                    if (matriz.get(idx1).get(idx2).equals(false)){
                        return true;
                    }   
                }
            }                            
        return false;
    }
}
