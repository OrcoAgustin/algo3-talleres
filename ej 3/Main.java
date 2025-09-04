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
    
        long n = scanner.nextLong();
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        
        printer.print(contar(n,l,r,(long) 1,obtenerLargo(n)));       
        printer.flush();
    }

    
    public static Long obtenerLargo(long n){
        if(n<=1){
            return (long) 1;
        }
        return obtenerLargo(n/2)+1+obtenerLargo(n/2);
    }
   
    public static Long contar(Long n, Integer l, Integer r, Long bordeIzq, Long bordeDer) {
        //si los bordes pedidos l y r caen fuera del numero que estamos operando
        if((l<bordeIzq)||(r>bordeDer)){
            return (long) 0;
        }
        //casos base
        if(n<=1){

            if((n==1) && (bordeIzq>=l) && (bordeDer<=r)){
                return (long) 1;
            }
            else{
            return (long) 0;
            }
        }
        //paso recursivo
        long medio = bordeIzq+ obtenerLargo(n/2);
        long res = 0;

        //yendo por izquierda 
        res +=contar(n/2,l,r,bordeIzq,medio-1);
        //por derecha
        res +=contar(n/2,l,r,medio+1,bordeDer);
        //caso del medio
        if(l<=medio && medio<=r){
            res+=1;
        }
        return res;
    }
}




    //esto da problemas al iterar toda la lista se puede hacer muy costoso
    //tratar de buscar alguna estructura mas util, quiza arbol?

    //cada caso genera f1(i) + f2(i) +f1(i) ent, puedo usar memorizacion para ahorrar calculo ?(no se que tan correcto es usarlo pero vamos a testear)
    
    //capaz hacer algo con el arbol sea mejor, sabiendo los bordes podemos buscar algo que interseque y solo operar ahi?
    //f recursiva me dice largo, de ahi solo creo lista para los que interseque?
    /* 
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
    */

