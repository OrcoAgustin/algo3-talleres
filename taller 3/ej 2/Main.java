import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
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


        //aristas

        ArrayList<long[]>[] listaDeAdyacencia = new ArrayList[n];        
        for (int i = 0; i < n; i++) {
            listaDeAdyacencia[i] = new ArrayList<long[]>();
        }


        for (int i = 0; i < m; i++) {
            int primerP = scanner.nextInt()-1;
            int segundoP = scanner.nextInt()-1;
            long costo = scanner.nextLong();

            listaDeAdyacencia[primerP].add(new long[]{segundoP,costo});
            listaDeAdyacencia[segundoP].add(new long[]{primerP,costo});
        }
    

        //conflictos

        //indice es planeta-1 y los valores son los momentos donde esta ocupado
        ArrayList<Long>[] matrizDeConflictos = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            ArrayList<Long>  nuevaLinea = new ArrayList<>();

            int cantidadDeConflictos = scanner.nextInt();
            for (int j = 0; j < cantidadDeConflictos ; j++) {
                long nuevoNum = scanner.nextLong();
                nuevaLinea.add(nuevoNum);  
              
            }

            matrizDeConflictos[i]=nuevaLinea;
        }


        printer.println(pasosHastaM(listaDeAdyacencia, matrizDeConflictos));
        printer.flush();
    }

    public static long pasosHastaM(ArrayList<long[]>[] listaDeAdyacencia, ArrayList<Long>[] matrizDeConflictos){
        //dijkstra v2 (echu's dream)
        //proba si pasa con matriz, sino sufrimos con la queue
        //yo del futuro: no funciona. Usa queue
    
        int n = listaDeAdyacencia.length;
        //almacenar (tiempo, planeta)
        PriorityQueue<long[]> restantes = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0]) 
        );

        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = Long.MAX_VALUE;
        }
        res[0] = 0L;

        restantes.add(new long[]{0L,0L});

        while(!restantes.isEmpty()){
            
            long[] actual = restantes.poll();
            long tiempoActual = actual[0];
            int planetaActual = (int) actual[1];

            //ya calculado?
            if(tiempoActual > res[planetaActual]){
                continue;
            }

            //llegamos?
            if(planetaActual == n-1){
                return tiempoActual;
            }
            
            //calculamos el tiempo de salida
            long tiempoSalida = encontrarTiempo(tiempoActual, planetaActual, listaDeAdyacencia, matrizDeConflictos);
            
            for (int i = 0; i < listaDeAdyacencia[planetaActual].size(); i++) {
                //primero: planeta, segundo: costo de llegar
                int planetaDestino = (int) listaDeAdyacencia[planetaActual].get(i)[0];
                long costoDeViaje =  listaDeAdyacencia[planetaActual].get(i)[1];
                 
                long nuevoTiempoDeLlegada = tiempoSalida + costoDeViaje;

                if (nuevoTiempoDeLlegada < res[planetaDestino]){
                    res[planetaDestino] = nuevoTiempoDeLlegada;
                    restantes.add(new long[]{(long) nuevoTiempoDeLlegada,(long) planetaDestino});
                }
            }
        }

        //inalcanzable el destino
        return -1;
    }
    
    //CREO el problema esta aca, es lineal toma demasiado. Pensa como mejorarlo
    public static long encontrarTiempo(long  tiempoLlegada, int planetaActual, ArrayList<long[]>[] listaDeAdyacencia, ArrayList<Long>[] matrizDeConflictos ){ 
        //si le implementas busqueda binaria en otra f re sale segun tobi
        long tiempoSalida = tiempoLlegada;

        //catch por si esta vacia
        if(matrizDeConflictos[planetaActual].isEmpty()){
            return tiempoSalida;
        }

        int indice = busquedaBinaria(matrizDeConflictos[planetaActual], tiempoSalida);

        while(indice < matrizDeConflictos[planetaActual].size()){
            long conflicto = matrizDeConflictos[planetaActual].get(indice);
            if(conflicto==tiempoSalida){
                tiempoSalida ++;
                indice ++;
            }
            else if(conflicto > tiempoSalida ){
                break;
            }
        }
        
        return tiempoSalida;
    }

    public static int busquedaBinaria(ArrayList<Long> lista, long objetivo){
        
        int izquierda = 0;
        int derecha = lista.size();

        while (izquierda < derecha){
            int indice = (izquierda + (derecha-izquierda))/2;

            long valor = lista.get(indice);

            if(valor < objetivo){
                izquierda = indice + 1;
            }
            else{
                derecha = indice ;
            }
        }

        return izquierda;
    }
}

//LOS INT TIENEN LIMITE; CABEZA DE TERMO USA LONG :)

 /*
    public static int encontrarTiempo(int tiempoLlegada, int planetaActual, ArrayList<Integer[]>[] listaDeAdyacencia, ArrayList<Integer>[] matrizDeConflictos ){ 
        int tiempoSalida = tiempoLlegada;
        
        boolean encontrado = false;
        int indice =matrizDeConflictos[planetaActual].size();
        while(!encontrado){
            if(indice/2 == tiempoSalida){
                tiempoSalida +=1;
            }
        }

        return tiempoSalida;
    }        
 */

/* 
        for (int i = 0; i < matrizDeConflictos[planetaActual].size(); i++) {
            if (tiempoSalida == matrizDeConflictos[planetaActual].get(i)){
                tiempoSalida += 1;
            }
        }
        */


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