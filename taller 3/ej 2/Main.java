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

        ArrayList<Integer[]>[] listaDeAdyacencia = new ArrayList[n];        
        for (int i = 0; i < n; i++) {
            listaDeAdyacencia[i] = new ArrayList<Integer[]>();
        }


        for (int i = 0; i < m; i++) {
            int primerP = scanner.nextInt()-1;
            int segundoP = scanner.nextInt()-1;
            Integer costo = scanner.nextInt();

            listaDeAdyacencia[primerP].add(new Integer[]{segundoP,costo});
            listaDeAdyacencia[segundoP].add(new Integer[]{primerP,costo});
        }
    

        //conflictos

        //indice es planeta-1 y los valores son los momentos donde esta ocupado
        ArrayList<Integer>[] matrizDeConflictos = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            ArrayList<Integer>  nuevaLinea = new ArrayList<>();

            int cantidadDeConflictos = scanner.nextInt();
            for (int j = 0; j < cantidadDeConflictos ; j++) {
                int nuevoNum = scanner.nextInt();
                nuevaLinea.add(nuevoNum);  
              
            }

            matrizDeConflictos[i]=nuevaLinea;
        }


        printer.println(pasosHastaM(listaDeAdyacencia, matrizDeConflictos));
        printer.flush();
    }

    public static long pasosHastaM(ArrayList<Integer[]>[] listaDeAdyacencia, ArrayList<Integer>[] matrizDeConflictos){
        //dijkstra v2 (echu's dream)
        //proba si pasa con matriz, sino sufrimos con la queue
        //yo del futuro: no funciona. Usa queue
    
        int n = listaDeAdyacencia.length;
        //almacenar (tiempo, planeta)
        PriorityQueue<int[]> restantes = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0]) 
        );

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Integer.MAX_VALUE;
        }
        res[0] = 0;

        restantes.add(new int[]{0,0});

        while(!restantes.isEmpty()){
            
            int[] actual = restantes.poll();
            int tiempoActual = actual[0];
            int planetaActual= actual[1];

            //ya calculado?
            if(tiempoActual > res[planetaActual]){
                continue;
            }

            //llegamos?
            if(planetaActual == n-1){
                return tiempoActual;
            }
            
            //calculamos el tiempo de salida
            int tiempoSalida = encontrarTiempo(tiempoActual, planetaActual, listaDeAdyacencia, matrizDeConflictos);
            

            
            for (int i = 0; i < listaDeAdyacencia[planetaActual].size(); i++) {
                //primero: planeta, segundo: costo de llegar
                int planetaDestino = listaDeAdyacencia[planetaActual].get(i)[0];
                int costoDeViaje =  listaDeAdyacencia[planetaActual].get(i)[1];
                 
                int nuevoTiempoDeLlegada = tiempoSalida + costoDeViaje;

                if (nuevoTiempoDeLlegada < res[planetaDestino]){
                    res[planetaDestino] = nuevoTiempoDeLlegada;
                    restantes.add(new int[]{nuevoTiempoDeLlegada,planetaDestino});
                }
            }
        }

        //inalcanzable el destino
        return -1;
    }
    
    public static int encontrarTiempo(int tiempoLlegada, int planetaActual, ArrayList<Integer[]>[] listaDeAdyacencia, ArrayList<Integer>[] matrizDeConflictos ){ 
        //si le implementas busqueda binaria en otra f re sale segun tobi
        int tiempoSalida = tiempoLlegada;
        //catch por si esta vacia

        if(matrizDeConflictos[planetaActual].isEmpty()){
            return tiempoSalida;
        }

        int indice = busquedaBinaria(matrizDeConflictos[planetaActual], tiempoSalida);

        if(indice == -1){
            return tiempoLlegada;
        }

        while(indice < matrizDeConflictos[planetaActual].size()){
            int conflicto = matrizDeConflictos[planetaActual].get(indice);
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

    public static int busquedaBinaria(ArrayList<Integer> lista, int objetivo){
        
        int izquierda = 0;
        int derecha = lista.size()-1;

        while (izquierda < derecha){
            int indice = (izquierda + (derecha-izquierda))/2;

            int valor = lista.get(indice);

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